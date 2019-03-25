package com.stock.capital.enterprise.ipoCase.service;

import com.stock.capital.enterprise.ipoCase.dao.IpoProcessMapper;
import com.stock.capital.enterprise.ipoCase.dto.IpoFileRelationDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoProListDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoProgressDto;
import com.stock.capital.enterprise.ipoCase.dto.TreeTypeProgressDto;
import com.stock.core.service.BaseService;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class IpoProcessService extends BaseService {
    final static Logger logger = LoggerFactory.getLogger(IpoProcessService.class);
    @Autowired
    private IpoProcessMapper ipoProcessMapper;
    @Value("#{app['pdf.baseUrl']}")
    private String pdfBaseUrl;
    @Value("#{app['file.viewPath']}")
    private String fileViewPath;

    public TreeTypeProgressDto selectProcessList(String id, String sortType) {
        TreeTypeProgressDto resultDto = ipoProcessMapper.selectProcessList(id);
        List<IpoProgressDto> treeList = resultDto == null ? new ArrayList<>() : resultDto.getTreeList();
        //循环计算距离上一个进程时间
        for (int i = 0; i < treeList.size(); i++) {
            treeList.get(i).setSpread(false);
            treeList.get(i).setSpreadFlag(false);
            List<IpoProListDto> proList = treeList.get(i) == null ? new ArrayList<>() : treeList.get(i).getProList();
            for (int j = 0; j < proList.size(); j++) {
                proList.get(j).setProgressIndex(treeList.get(i).getTreeTypeCode()+proList.get(j).getProSort());
                //每个进程只有第一个节点储存了时间，补全进程时间
                List<IpoFileRelationDto> fileList = proList.get(j).getRelaList();
                if (CollectionUtils.isNotEmpty(fileList)) {
                    proList.get(j).setProcessTime(fileList.get(0).getPublishTime());
                    //发审会审核是有第一个节点存了审核结果，赋值到进程dto
                    proList.get(j).setIecResult(fileList.get(0).getIecResult());
                    if (fileList.size() > 1) {
                        for (int k = 1; k < fileList.size(); k++) {
                            //每个进程只有第一个节点储存了时间，补全进程时间
                            fileList.get(k).setPublishTime(fileList.get(0).getPublishTime());
                        }
                    }
                }

                //如果该进程没有关联文件，则从文件list中删除
                List<IpoFileRelationDto> newFileList = new ArrayList<>();
                for (IpoFileRelationDto fileDto : fileList) {
                    if (StringUtils.isNotEmpty(fileDto.getRelaId())) {
                        //拼接文件打开URL
                        if ("02".equals(treeList.get(i).getTreeTypeCode())) {
                            String baseUrl = pdfBaseUrl + "web/viewer.html?file=" + pdfBaseUrl + "pdf/H2_" +
                                    fileDto.getRelaId() + "_1.pdf&originTitle=" + fileDto.getRelationFileTitle();
                            fileDto.setBaseUrl(baseUrl);
                        } else {
                            if ("htm".equals(fileDto.getSuffix().toLowerCase()) || "html".equals(fileDto.getSuffix().toLowerCase())) {
                                String baseUrl = fileViewPath + "open/ipoFile/" + fileDto.getRelaId() + ".png";
                                fileDto.setBaseUrl(baseUrl);
                            }else{
                                String baseUrl = fileViewPath + "open/ipoFile/" + fileDto.getRelaId() + "." + fileDto.getSuffix();
                                fileDto.setBaseUrl(baseUrl);
                            }
                        }
                        newFileList.add(fileDto);
                    }
                }
                proList.get(j).setRelaList(newFileList);
                proList.get(j).setFlag(false);
                //如果不是发审会公告，即不是第一个树，则计算该树的第一个与上一个树的最后一个进程相差时间
                if (i != 0 && j == 0) {
                    String sdate = treeList.get(i).getProList().get(0).getProcessTime();
                    String edate = treeList.get(i - 1).getProList().
                            get(treeList.get(i - 1).getProList().size() - 1).getProcessTime();
                    String outLastDay = "0";
                    if (StringUtils.isNotEmpty(sdate) && StringUtils.isNotEmpty(edate)) {
                        outLastDay = getLastDays(sdate, edate);
                    }
                    treeList.get(i).getProList().get(0).setLastDay(outLastDay);
                }
                if (j > 0) {
                    String lastDay = "0";
                    String sdate = proList.get(j).getProcessTime();
                    String edate = proList.get(j - 1).getProcessTime();
                    if (StringUtils.isNotEmpty(sdate) && StringUtils.isNotEmpty(edate)) {
                        lastDay = getLastDays(sdate, edate);
                    }
                    proList.get(j).setLastDay(lastDay);
                }
            }
        }
        //添加股份公司设立时间
        IpoProgressDto publishDto = new IpoProgressDto();
        publishDto.setTreeTypeCode("03");
        String publishDate = ipoProcessMapper.getPublishDate(id);
        publishDto.setPublishDate(publishDate);
        publishDto.setTreeTypeName("股份公司设立时间");
        treeList.add(publishDto);

        //默认为正序，如果要求倒序序排序，则在计算完距离上个进程天数后，重新排序
        if ("02".equals(sortType) && CollectionUtils.isNotEmpty(treeList)) {
            treeList.sort((IpoProgressDto d1, IpoProgressDto d2) -> d2.getTreeTypeCode().compareTo(d1.getTreeTypeCode()));
            for (IpoProgressDto dto : treeList) {
                if (CollectionUtils.isNotEmpty(dto.getProList())) {
                    dto.getProList().sort((IpoProListDto c1, IpoProListDto c2) -> c2.getProSort().compareTo(c1.getProSort()));
                }
            }
        }
        for (int i = 0; i < treeList.size(); i++) {
            if (i == 0) {
                treeList.get(i).setSandian(true);
            } else {
                treeList.get(i).setSandian(false);
            }
        }
        return resultDto;
    }


    /**
     * 计算两个日期相差天数
     */
    private String getLastDays(String sdateStr, String edateStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        long lastDays = 0;
        try {
            Date sDate = dateFormat.parse(sdateStr);
            Date eDate = dateFormat.parse(edateStr);
            lastDays = (sDate.getTime() - eDate.getTime()) / (1000 * 3600 * 24);
        } catch (ParseException e) {
            e.printStackTrace();
            logger.error("计算日期间隔异常");
        }
        return String.valueOf(lastDays);
    }

}

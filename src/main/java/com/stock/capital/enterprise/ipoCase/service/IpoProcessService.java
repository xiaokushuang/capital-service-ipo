package com.stock.capital.enterprise.ipoCase.service;

import com.stock.capital.enterprise.ipoCase.dao.IpoProcessMapper;
import com.stock.capital.enterprise.ipoCase.dto.IpoProListDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoProgressDto;
import com.stock.capital.enterprise.ipoCase.dto.TreeTypeProgressDto;
import com.stock.core.service.BaseService;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    public TreeTypeProgressDto selectProcessList(String id,String sortType) {
        TreeTypeProgressDto resultDto = ipoProcessMapper.selectProcessList(id);
        List<IpoProgressDto> treeList = resultDto == null?new ArrayList<>():resultDto.getTreeList();
        //循环计算距离上一个进程时间
        for (int i = 0; i < treeList.size(); i++) {
            List<IpoProListDto> proList = treeList.get(i)==null?new ArrayList<>():treeList.get(i).getProList();
            for (int j = 0; j < proList.size(); j++) {
                //如果不是发审会公告，即不是第一个树，则计算该树的第一个与上一个树的最后一个进程相差时间
                if (i != 0 && j == 0) {
                    String sdate = treeList.get(i).getProList().get(0).getProcessTime();
                    String edate = treeList.get(i - 1).getProList().
                            get(treeList.get(i - 1).getProList().size() - 1).getProcessTime();
                    String outLastDay = getLastDays(sdate,edate);
                    treeList.get(i).getProList().get(0).setLastDay(outLastDay);
                }
                if(j>0) {
                    String lastDay = getLastDays(proList.get(j).getProcessTime(), proList.get(j - 1).getProcessTime());
                    proList.get(j).setLastDay(lastDay);
                }
            }
        }
        //默认为正序，如果要求倒序序排序，则在计算完距离上个进程天数后，重新排序
        if("02".equals(sortType) && CollectionUtils.isNotEmpty(treeList)){
            treeList.sort((IpoProgressDto d1,IpoProgressDto d2) -> d2.getTreeTypeCode().compareTo(d1.getTreeTypeCode()));
            for(IpoProgressDto dto:treeList){
                if(CollectionUtils.isNotEmpty(dto.getProList())){
                    dto.getProList().sort((IpoProListDto c1,IpoProListDto c2) -> c2.getProSort().compareTo(c1.getProSort()));
                }
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

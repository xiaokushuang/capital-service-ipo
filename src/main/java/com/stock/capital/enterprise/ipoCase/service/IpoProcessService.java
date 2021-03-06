package com.stock.capital.enterprise.ipoCase.service;

import com.google.common.base.Throwables;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import com.stock.capital.enterprise.common.constant.Global;
import com.stock.capital.enterprise.common.dao.AttachmentMapper;
import com.stock.capital.enterprise.common.entity.Attachment;
import com.stock.capital.enterprise.common.service.CommonService;
import com.stock.capital.enterprise.ipoCase.dao.IpoCaseListMapper;
import com.stock.capital.enterprise.ipoCase.dao.IpoProcessMapper;
import com.stock.capital.enterprise.ipoCase.dto.IpoCaseListBo;
import com.stock.capital.enterprise.ipoCase.dto.IpoFileRelationDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoProListDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoProgressDto;
import com.stock.capital.enterprise.ipoCase.dto.TreeTypeProgressDto;
import com.stock.core.dto.JsonResponse;
import com.stock.core.exception.FileDownloadException;
import com.stock.core.rest.RestClient;
import com.stock.core.service.BaseService;
import com.stock.core.util.CompressUtil;

import com.stock.core.util.JsonUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.elasticsearch.common.Glob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class IpoProcessService extends BaseService {
    final static Logger logger = LoggerFactory.getLogger(IpoProcessService.class);

    @Autowired
    private IpoProcessMapper ipoProcessMapper;
    @Autowired
    private IpoCaseListMapper ipoCaseListMapper;
    @Autowired
    private RestClient restClient;

    @Autowired
    private AttachmentMapper attachmentMapper;

    @Autowired
    private CommonService commonService;


    @Value("#{app['pdf.baseUrl']}")
    private String pdfBaseUrl;
    @Value("#{app['file.viewPath']}")
    private String fileViewPath;
    @Value("#{app['api.baseUrl']}")
    private String apiBaseUrl;
    @Value("#{app['file.path']}")
    private String filePath;
    @Value("#{app['commonfile.path']}")
    private String commonFilePath;
    @Value("#{app['declare.baseUrl']}")
    private String declarePdfUrl;
    /**
     * 微服地址前缀
     */
    @Value("#{app['service.gui.baseUrl']}")
    private String serviceGuiBaseUrl;

    public TreeTypeProgressDto selectProcessList(String id, String sortType) {
        TreeTypeProgressDto resultDto = ipoProcessMapper.selectProcessList(id);
        List<IpoProgressDto> treeList = resultDto == null ? new ArrayList<>() : resultDto.getTreeList();
        //循环计算距离上一个进程时间
        for (int i = 0; i < treeList.size(); i++) {
            treeList.get(i).setSpread(false);
            treeList.get(i).setSpreadFlag(false);
            List<IpoProListDto> proList = treeList.get(i) == null ? new ArrayList<>() : treeList.get(i).getProList();
            int inquiryTimes = 1;
            int responseTimes = 1;
            for (int j = 0; j < proList.size(); j++) {
                if (CollectionUtils.isNotEmpty(proList.get(j).getRelaList())) {// 针对 科创板 审核中止、审核终止。其他不进
                    if (proList.get(j).getProgressType().equals("39")) {// 针对 审核中止 只有一行 或者没有行
                        IpoFileRelationDto dto = proList.get(j).getRelaList().get(0);
                        List<String> subTitles = ipoProcessMapper.selectConfLabelBy("IPO_PAUSE_REVIEW", dto.getIecReason());
                        proList.get(j).setSubtitle(subTitles);
                        String lawId = getLawId();
                        proList.get(j).setAddressId(lawId);
                    } else if (proList.get(j).getProgressType().equals("40")) {// 针对 审核终止 只有一行或者 没有行
                        IpoFileRelationDto dto = proList.get(j).getRelaList().get(0);
                        List<String> subTitles = ipoProcessMapper.selectConfLabelBy("IPO_STOP_REVIEW", dto.getIecReason());
                        proList.get(j).setSubtitle(subTitles);
                        String lawId = getLawId();
                        proList.get(j).setAddressId(lawId);
                    }
                }
                proList.get(j).setProgressIndex(treeList.get(i).getTreeTypeCode() + proList.get(j).getProSort());

                //标出第几次问询，第几次回复
                if ("问询".equals(proList.get(j).getProgressName())) {
                    proList.get(j).setProgressName("第 " + inquiryTimes + " 次问询");
                    inquiryTimes++;
                }
                if ("回复".equals(proList.get(j).getProgressName())) {
                    proList.get(j).setProgressName("第 " + responseTimes + " 次回复");
                    responseTimes++;
//                    if(inquiryTimes == 1){
//                        proList.get(j).setProgressName("第 "+inquiryTimes+" 次回复");
//                    }else{
//                        proList.get(j).setProgressName("第 "+(inquiryTimes-1)+" 次回复");
//                    }
                }

                //每个进程只有第一个节点储存了时间，补全进程时间
                List<IpoFileRelationDto> fileList = proList.get(j).getRelaList();
                if (CollectionUtils.isNotEmpty(fileList)) {
                    proList.get(j).setProcessTime(fileList.get(0).getPublishTime());
                    //发审会审核只有第一个节点存了审核结果，赋值到进程dto
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
                            String baseUrl = pdfBaseUrl + "web/viewer.html?file=" + declarePdfUrl + "pdf/H2_" +
                                    fileDto.getRelaId() + "_1.pdf&originTitle=" + fileDto.getRelationFileTitle();
                            fileDto.setBaseUrl(baseUrl);
                        } else {
                            if ("xsb".equals(fileDto.getProcessType())) {
                                if ("pdf".equals(fileDto.getSuffix())) {
                                    String baseUrl = pdfBaseUrl + "web/viewer.html?file=" + fileDto.getUrl() + "&originTitle=" + fileDto.getRelationFileTitle();
                                    fileDto.setBaseUrl(baseUrl);
                                }else{
                                    fileDto.setBaseUrl(fileDto.getUrl());
                                }
                            } else {
                                if ("htm".equals(fileDto.getSuffix()) || "html".equals(fileDto.getSuffix())) {
                                    String baseUrl = fileViewPath + "open/ipoFile/" + fileDto.getRelaId() + ".png";
                                    fileDto.setBaseUrl(baseUrl);
                                } else {
                                    String baseUrl = fileViewPath + "open/ipoFile/" + fileDto.getRelaId() + "." + fileDto.getSuffix();
                                    fileDto.setBaseUrl(baseUrl);
                                }
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
                    if (StringUtils.isNotEmpty(sdate) && StringUtils.isNotEmpty(edate)) {
                        String outLastDay = "0";
                        if (StringUtils.isNotEmpty(sdate) && StringUtils.isNotEmpty(edate)) {
                            outLastDay = getLastDays(sdate, edate);
                        }
                        treeList.get(i).getProList().get(0).setLastDay(outLastDay);
                    } else {
                        treeList.get(i).getProList().get(0).setLastDay(null);
                    }
                }
                if (j > 0) {
                    String lastDay = "0";
                    String sdate = proList.get(j).getProcessTime();
                    String edate = proList.get(j - 1).getProcessTime();
                    if (StringUtils.isNotEmpty(sdate) && StringUtils.isNotEmpty(edate)) {
                        lastDay = getLastDays(sdate, edate);
                        proList.get(j).setLastDay(lastDay);
                    } else {
                        proList.get(j).setLastDay(null);
                    }

                }
                //判断当前时间和进程时间，如果进程时间大于当前时间，则置灰
                proList.get(j).setDateCompare(1);
                proList.get(j).setHandle(true);
                if ("02".equals(treeList.get(i).getTreeTypeCode())) {
                    try {
                        Date nowDate = new Date();
                        if (StringUtils.isNotEmpty(proList.get(j).getProcessTime())) {
                            Date proDate = DateUtils.parseDate(proList.get(j).getProcessTime(), "yyyy-MM-dd");
                            if (proDate.compareTo(nowDate) > 0) {
                                proList.get(j).setDateCompare(0);
                                proList.get(j).setHandle(false);
                            }
                        } else {
                            proList.get(j).setDateCompare(0);
                            proList.get(j).setHandle(false);
                        }
                    } catch (ParseException e) {
                        logger.error("进程日期转换错误");
                    }
                }
            }
        }

        //添加股份公司设立时间
        IpoProgressDto publishDto = new IpoProgressDto();
        publishDto.setTreeTypeCode("03");
        String publishDate = ipoProcessMapper.getPublishDate(id);
        publishDto.setPublishDate(publishDate);
        publishDto.setTreeTypeName("股份公司设立时间");
        //默认为正序，如果要求倒序序排序，则在计算完距离上个进程天数后，重新排序
        if ("02".equals(sortType) && CollectionUtils.isNotEmpty(treeList)) {
            treeList.sort((IpoProgressDto d1, IpoProgressDto d2) -> d2.getTreeTypeCode().compareTo(d1.getTreeTypeCode()));
            for (IpoProgressDto dto : treeList) {
                if (CollectionUtils.isNotEmpty(dto.getProList())) {
                    dto.getProList().sort((IpoProListDto c1, IpoProListDto c2) -> c2.getProSort().compareTo(c1.getProSort()));
                }
            }
            treeList.add(publishDto);
        } else {
            treeList.add(0, publishDto);
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


    private String getLawId() {
        String result = "";
        IpoCaseListBo law = ipoProcessMapper.querylawId();
        if (law != null) {
            if (StringUtils.isNoneBlank(law.getIssueLawId())) {
                result = law.getIssueLawId();
            } else {
                result = "746412002825256480";
            }
        } else {
            result = "746412002825256480";
        }
        return result;
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

    /**
     * 下载单个公告
     */
    public String downloadSingleAnnounce(String id, HttpServletResponse response, HttpServletRequest request) {
        Map<String, Object> index = commonService.getAnnoById(id);
        String title = StringUtils.EMPTY;
        String url;
        String infoUrl = String.valueOf(index.get("infoUrl"));
        if (infoUrl.contains("html")) {
            url = infoUrl.substring(0, infoUrl.indexOf("html") + 4);
        } else {
            url = infoUrl;
        }

        String titles = String.valueOf(index.get("title"));
        String titleTemp = StringUtils.EMPTY;
        //文件名字过长导致无法下载
        if (titles.length() >= 40) {
            titles = titles.substring(0, 40);
        }
        if (titles.contains(":")) {
            String titleName[] = titles.split(":");
            titleTemp = titleName[titleName.length - 1];
        } else {
            titleTemp = titles;
        }
        title = transformMetacharactor(String.valueOf(index.get("code")) +
                "-" + String.valueOf(index.get("companyShortName"))
                + String.valueOf(index.get("publishDate")) + "]" + titleTemp) + "." + Files.getFileExtension(url);
        //公司代码_公司简称_公告日期_公告标题
        InputStream in = null;
        try {
            String fileName = title;
            String userAgent = request.getHeader("user-agent").toLowerCase();
            if (userAgent.contains("msie") || userAgent.contains("like gecko")) {
                // win10 ie edge 浏览器 和其他系统的ie
                fileName = URLEncoder.encode(fileName, "UTF-8");
            } else {
                // fe
                fileName = new String(fileName.getBytes("utf-8"), "iso-8859-1");
            }

//            fileName = new String(fileName.getBytes(), "ISO-8859-1");
            in = Resources.asByteSource(new URL(url)).openBufferedStream();
            // 设置输出的格式
            response.reset();
            response.setContentType("text/html;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
            // 循环取出流中的数据
            byte[] b = new byte[100];
            int len;
            while ((len = in.read(b)) > 0) {
                response.getOutputStream().write(b, 0, len);
            }
        } catch (Exception e) {
            throw new FileDownloadException("下载文件失败");
        } finally {
            IOUtils.closeQuietly(in);
        }
        return title;
    }

    private String transformMetacharactor(String input) {
        String regex = "[+&|!(){}\\[\\]^\"~*?:(\\)/]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.replaceAll(Matcher.quoteReplacement("_"));
    }

    /**
     * 下载多个公告
     */
    public String downloadMultiplyAnnounce(String ids, HttpServletResponse response, HttpServletRequest request) {
        List<String> selIdList = new ArrayList<>();
        List<Map<String, String>> srcFileList = new ArrayList<>();
        String fileName = "";
        if (StringUtils.isNotEmpty(ids)) {
            if (ids.contains(",")) {
                selIdList = Arrays.asList(ids.split(","));
            } else {
                selIdList.add(ids);
            }
            //当公告不在索引中而在数据库中时，传companyId和userId进行查询
            for (String indexId : selIdList) {
                Map<String, Object> index = commonService.getAnnoById(indexId);

                Map<String, String> srcFile = new HashMap<>();
                String URL;
                String infoUrl = String.valueOf(index.get("infoUrl"));
                if (infoUrl.contains("html")) {
                    URL = infoUrl.substring(0, infoUrl.indexOf("html") + 4);
                } else {
                    URL = infoUrl;
                }
                String title = StringUtils.EMPTY;
                String titles = String.valueOf(index.get("title"));
                String titleTemp = StringUtils.EMPTY;
                //文件名字过长导致无法下载
                if (titles.length() >= 40) {
                    titles = titles.substring(0, 40);
                }
                //对于港股页面下载的公告名称格式做调整
                String companyCode = String.valueOf(index.get("code"));
                if (titles.contains(":")) {
                    String titleName[] = titles.split(":");
                    titleTemp = titleName[titleName.length - 1];
                } else {
                    titleTemp = titles;
                }
                //公司代码_公司简称_公告日期_公告标题
                title = transformMetacharactor(String.valueOf(index.get("code")) + "-" + String.valueOf(index.get("companyShortName")) + String.valueOf(index.get("publishDate")) + "]" + titleTemp) + "." + Files.getFileExtension(URL);

                if (srcFileList != null) {
                    for (Map<String, String> checkSrcFile : srcFileList) {
                        Set<Map.Entry<String, String>> set = checkSrcFile.entrySet();
                        for (Map.Entry<String, String> entry : set) {
                            if (entry.getKey().equals(title)) {
                                title = transformMetacharactor(String.valueOf(index.get("title"))) + "-" + indexId + "." + Files.getFileExtension(URL);
                            }
                        }
                    }
                }
                srcFile.put(title, URL);
                srcFileList.add(srcFile);
            }
            InputStream in = null;
            try {
                fileName = "所选公告下载" + System.currentTimeMillis() + ".zip";
                String userAgent = request.getHeader("user-agent").toLowerCase();
                if (userAgent.contains("msie") || userAgent.contains("like gecko")) {
                    // win10 ie edge 浏览器 和其他系统的ie
                    fileName = URLEncoder.encode(fileName, "UTF-8");
                } else {
                    // fe
                    fileName = new String(fileName.getBytes("utf-8"), "iso-8859-1");
                }
//                fileName = new String(fileName.getBytes(), "ISO-8859-1");
                in = CompressUtil.multiURLCompressZip(srcFileList);
                // 设置输出的格式
                response.reset();
                response.setContentType("text/html;charset=utf-8");
                response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
                // 循环取出流中的数据
                byte[] b = new byte[100];
                int len;
                while ((len = in.read(b)) > 0) {
                    response.getOutputStream().write(b, 0, len);
                }
            } catch (Exception e) {
                logger.error("下载公告错误cause by：{}", Throwables.getStackTraceAsString(e));
            } finally {
                IOUtils.closeQuietly(in);
            }
        }
        return fileName;
    }

    public String downloadSingleFile(String id, HttpServletResponse response, HttpServletRequest request) {
        //根据文件id查询相关信息
        IpoFileRelationDto fileDto = ipoProcessMapper.selectFileDto(id);
        String suffix = fileDto.getSuffix();
        String processType = fileDto.getProcessType();
        if (!"xsb".equals(processType)){
            if ("htm".equals(suffix) || "html".equals(suffix)) {
                suffix = "png";
            }
        }
        String title = fileDto.getRelationFileTitle();
        if (title.indexOf(".") > 0) {
            title = fileDto.getRelationFileTitle().substring(0, title.indexOf("."));
        }
        if (title.length() >= 40) {
            title = title.substring(0, 40);
        }
        String fileName = title + System.currentTimeMillis() + "." + suffix;
//        String url = fileViewPath + "open/ipoFile/" + id + "." + suffix;
//        String url = "D:\\data\\capital\\upload\\cloud\\open\\ipoFile\\" + id + "." + suffix;
        String url = filePath + id + "." + suffix;
//        如果是新三板公告的下载处理
        if ("xsb".equals(fileDto.getProcessType())){
            url = fileDto.getUrl();
            InputStream in = null;
            try {
                String userAgent = request.getHeader("user-agent").toLowerCase();
                if (userAgent.contains("msie") || userAgent.contains("like gecko")) {
                    // win10 ie edge 浏览器 和其他系统的ie
                    fileName = URLEncoder.encode(fileName, "UTF-8");
                } else {
                    // fe
                    fileName = new String(fileName.getBytes("utf-8"), "iso-8859-1");
                }

//            fileName = new String(fileName.getBytes(), "ISO-8859-1");
                in = Resources.asByteSource(new URL(url)).openBufferedStream();
                // 设置输出的格式
                response.reset();
                response.setContentType("text/html;charset=utf-8");
                response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
                // 循环取出流中的数据
                byte[] b = new byte[100];
                int len;
                while ((len = in.read(b)) > 0) {
                    response.getOutputStream().write(b, 0, len);
                }
            } catch (Exception e) {
                throw new FileDownloadException("下载文件失败");
            } finally {
                IOUtils.closeQuietly(in);
            }
            return title;
        }

        InputStream in = null;
        try {
            in = new FileInputStream(url);

            String userAgent = request.getHeader("user-agent").toLowerCase();
            if (userAgent.contains("msie") || userAgent.contains("like gecko")) {
                // win10 ie edge 浏览器 和其他系统的ie
                fileName = URLEncoder.encode(fileName, "UTF-8");
            } else {
                // fe
                fileName = new String(fileName.getBytes("utf-8"), "iso-8859-1");
            }
//            fileName = new String(fileName.getBytes(), "ISO-8859-1");

//            in = Resources.asByteSource(new URL(url)).openBufferedStream();
            // 设置输出的格式
            response.reset();
            response.setContentType("text/html;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
            // 循环取出流中的数据
            byte[] b = new byte[100];
            int len;
            while ((len = in.read(b)) > 0) {
                response.getOutputStream().write(b, 0, len);
            }
        } catch (Exception e) {
            logger.error("下载公告错误cause by：{}", Throwables.getStackTraceAsString(e));
        } finally {
            IOUtils.closeQuietly(in);
        }
        return fileName;
    }

    public String downloadMultiplyFile(String ids, HttpServletResponse response, HttpServletRequest request) {
        List<String> selIdList = new ArrayList<>();
        List<Map<String, String>> srcFileList = new ArrayList<>();
        List<Map<String, String>> srcXSBList = new ArrayList<>();
        List<Map<String,InputStream>> srcIn = new ArrayList<>();
        String downFileName = "";
        if (StringUtils.isNotEmpty(ids)) {
            if (ids.contains(",")) {
                selIdList = Arrays.asList(ids.split(","));
            } else {
                selIdList.add(ids);
            }
            InputStream in = null;
            try {
            for (String indexId : selIdList) {
                Map<String, InputStream> srcFile = new HashMap<>();
                IpoFileRelationDto fileDto = ipoProcessMapper.selectFileDto(indexId);
                String suffix = fileDto.getSuffix();
                if (!"xsb".equals(fileDto.getProcessType())){
                    if ("htm".equals(suffix) || "html".equals(suffix)) {
                        suffix = "png";
                    }
                }
                String title = fileDto.getRelationFileTitle();
                if (title.indexOf(".") > 0) {
                    title = fileDto.getRelationFileTitle().substring(0, title.indexOf("."));
                }
                if (title.length() >= 40) {
                    title = title.substring(0, 40);
                }
                String fileName = title + System.currentTimeMillis() + "." + suffix;
                fileName = fileName.replaceAll("\\:", "-");
//                采用压缩zip的一个方法  将流放到map中然后放在list中  通过调用zip压缩方法获取zip文件
                if ("xsb".equals(fileDto.getProcessType())){
                    srcFile.put(fileName,new URL(fileDto.getUrl()).openStream());
                    srcIn.add(srcFile);
                }else{
                    String url = filePath + indexId + "." + suffix;
                    srcFile.put(fileName,new FileInputStream(url));
                    srcIn.add(srcFile);
                }
            }

                downFileName = "所选文件下载" + System.currentTimeMillis() + ".zip";

                String userAgent = request.getHeader("user-agent").toLowerCase();
                if (userAgent.contains("msie") || userAgent.contains("like gecko")) {
                    // win10 ie edge 浏览器 和其他系统的ie
                    downFileName = URLEncoder.encode(downFileName, "UTF-8");
                } else {
                    // fe
                    downFileName = new String(downFileName.getBytes("utf-8"), "iso-8859-1");
                }
                in = CompressUtil.multiFileCompressZip(srcIn);
//                downFileName = new String(downFileName.getBytes(), "ISO-8859-1");
                response.reset();
                response.setContentType("text/html;charset=utf-8");
                response.addHeader("Content-Disposition", "attachment; filename=" + downFileName);
                // 循环取出流中的数据
                byte[] b = new byte[100];
                int len;
                while ((len = in.read(b)) > 0) {
                    response.getOutputStream().write(b, 0, len);
                }
            } catch (Exception e) {
                logger.error("下载公告错误cause by：{}", Throwables.getStackTraceAsString(e));
            } finally {
                IOUtils.closeQuietly(in);
            }
        }
        return downFileName;
    }

    public String downloadSplitFile(String id, HttpServletResponse response, HttpServletRequest request) {
        //根据文件id查询相关信息
//        IpoFileRelationDto fileDto = ipoProcessMapper.selectSplitFileDto(id);
        Attachment attachment = attachmentMapper.selectByPrimaryKey(id);
//        String title = fileDto.getRelationFileTitle();
        String title = attachment.getAttName();
        String suffix = title.substring(title.indexOf(".") + 1);
        if (title.length() >= 40) {
            title = title.substring(0, 40);
        }
        String fileName = attachment.getAttName();
//        String url = fileViewPath + "open/ipoFile/" + id + "." + suffix;
//        String url = "E:\\upload_file\\" +attachment.getAttUrl().substring(1,attachment.getAttUrl().length()).replace("/","\\");
        String url = commonFilePath + attachment.getAttUrl().substring(1, attachment.getAttUrl().length());
        InputStream in = null;
        try {
            in = new FileInputStream(url);

            String userAgent = request.getHeader("user-agent").toLowerCase();
            if (userAgent.contains("msie") || userAgent.contains("like gecko")) {
                // win10 ie edge 浏览器 和其他系统的ie
                fileName = URLEncoder.encode(fileName, "UTF-8");
            } else {
                // fe
                fileName = new String(fileName.getBytes("utf-8"), "iso-8859-1");
            }
//            fileName = new String(fileName.getBytes(), "ISO-8859-1");

//            in = Resources.asByteSource(new URL(url)).openBufferedStream();
            // 设置输出的格式
            response.reset();
            response.setContentType("text/html;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
            // 循环取出流中的数据
            byte[] b = new byte[100];
            int len;
            while ((len = in.read(b)) > 0) {
                response.getOutputStream().write(b, 0, len);
            }
        } catch (Exception e) {
            logger.error("下载公告错误cause by：{}", Throwables.getStackTraceAsString(e));
        } finally {
            IOUtils.closeQuietly(in);
        }
        return fileName;
    }

    private InputStream compress(List<Map<String, String>> srcFileList) {
        OutputStream out = null;
        String srcDir = FileUtils.getTempDirectoryPath();
        if (File.separator.equals(srcDir.substring(srcDir.length() - 1))) {
            srcDir = srcDir + System.currentTimeMillis();
        } else {
            srcDir = srcDir + File.separator + System.currentTimeMillis();
        }

        File srcDirFile = new File(srcDir);
        boolean isCreated = srcDirFile.mkdir();
        if (!isCreated) {
            return null;
        } else {
            String destZip = FileUtils.getTempDirectoryPath();
            if (File.separator.equals(destZip.substring(destZip.length() - 1))) {
                destZip = destZip + System.currentTimeMillis() + ".zip";
            } else {
                destZip = destZip + File.separator + System.currentTimeMillis() + ".zip";
            }
            FileInputStream zipFile = null;
            InputStream in = null;
            for (Map<String, String> fileMap : srcFileList) {
                try {
                    String fileUrl = srcDir + File.separator;
                    String fileName = fileMap.get("fileName");
                    String indexId = fileMap.get("indexId");
                    String suffix = fileMap.get("suffix");
//                    String url = "D:\\data\\capital\\upload\\cloud\\open\\ipoFile\\" + indexId + "." + suffix;
                    String url = filePath + indexId + "." + suffix;
                    out = new FileOutputStream(fileUrl + fileName);
                    in = new FileInputStream(url);
                    IOUtils.copy(in, out, 1024);
                } catch (Exception e) {
                    throw new FileDownloadException("下载文件失败");
                } finally {
                    IOUtils.closeQuietly(in);
                    IOUtils.closeQuietly(out);
                }
            }
            CompressUtil.compressZip(srcDir, destZip);
            try {
                zipFile = new FileInputStream(destZip);
            } catch (Exception e) {
                logger.error("下载公告错误cause by：{}", Throwables.getStackTraceAsString(e));
            }
            return zipFile;
        }
    }

    public String checkSingleAnnounce(String id) {
        String result = "1";
        if (StringUtils.isNotEmpty(id)) {
            Map<String, Object> index = commonService.getAnnoById(id);
            if (null == index) {
                result = "0";
            }
        } else {
            result = "0";
        }
        return result;
    }

    public String checkMultiplyAnnounce(String ids) {
        List<String> selIdList = new ArrayList<>();
        List<Map<String, String>> srcFileList = new ArrayList<>();
        String result = "1";
        if (StringUtils.isNotEmpty(ids)) {
            if (ids.contains(",")) {
                selIdList = Arrays.asList(ids.split(","));
            } else {
                selIdList.add(ids);
            }
            //当公告不在索引中而在数据库中时，传companyId和userId进行查询
            for (String indexId : selIdList) {
                Map<String, Object> index = commonService.getAnnoById(indexId);
                if (null == index) {
                    result = "0";
                }
            }
        } else {
            result = "0";
        }
        return result;
    }

    public String checkSingleFile(String id) {
        String result = "1";
        IpoFileRelationDto fileDto = ipoProcessMapper.selectFileDto(id);
        if (null == fileDto) {
            result = "0";
        }
        return result;
    }

    public String checkMultiplyFile(String ids) {
        String result = "1";
        List<String> selIdList = new ArrayList<>();
        if (StringUtils.isNotEmpty(ids)) {
            if (ids.contains(",")) {
                selIdList = Arrays.asList(ids.split(","));
            } else {
                selIdList.add(ids);
            }
            for (String indexId : selIdList) {
                IpoFileRelationDto fileDto = ipoProcessMapper.selectFileDto(indexId);
                if (null == fileDto) {
                    result = "0";
                }
            }
        }
        return result;
    }

    public IpoProListDto getIssueData(String id) {
        return ipoProcessMapper.getIssueData(id);
    }
}

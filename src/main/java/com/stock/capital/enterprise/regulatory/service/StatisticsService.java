package com.stock.capital.enterprise.regulatory.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.support.ServletContextResource;

import com.alibaba.druid.util.StringUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.stock.capital.enterprise.regulatory.dao.StatisticsBizMapper;
import com.stock.capital.enterprise.regulatory.dto.StatisticsCompanyDto;
import com.stock.capital.enterprise.regulatory.dto.StatisticsParamDto;
import com.stock.capital.enterprise.regulatory.dto.StatisticsResultDto;
import com.stock.capital.enterprise.regulatory.dto.StatisticsReturnDto;
import com.stock.core.dto.JsonResponse;
import com.stock.core.dto.OptionDto;
import com.stock.core.dto.Page;
import com.stock.core.dto.QueryInfo;
import com.stock.core.dto.TreeDto;
import com.stock.core.rest.RestClient;
import com.stock.core.service.BaseService;
import com.stock.core.util.DateUtil;

@Service
public class StatisticsService extends BaseService implements ServletContextAware {

    /**
     * 接口调用
     */
    @Autowired
    private RestClient restClient;

    /**
     * 接口地址前缀
     */
    @Value("${api.baseUrl}")
    private String apiBaseUrl;
    
    @Autowired
    private StatisticsService statisticsService;
    
    @Autowired
    private StatisticsBizMapper statisticsBizMapper;
    
    private ServletContext servletContext;
    
    @Override
    public void setServletContext(ServletContext servletContext) {
    this.servletContext = servletContext;
    }

    /**
     * IPO在审项目数据统计
     *
     * @param letterId
     * @return
     */
    public List<StatisticsResultDto> getIPOReviewingStts() {
//        ParameterizedTypeReference<JsonResponse<List<StatisticsResultDto>>> responseType = new ParameterizedTypeReference<JsonResponse<List<StatisticsResultDto>>>() {
//        };
//        String url = apiBaseUrl + "regulatory_statistics/getIPOReviewingStts";
//        List<StatisticsResultDto> response = restClient.post(url, "", responseType).getResult();
//        return response;
        String updateTime = statisticsBizMapper.getIPOLastTime();
        Map<String, Object> params = Maps.newHashMap();
        params.put("updateTime", updateTime);
        List<StatisticsResultDto> list = statisticsBizMapper.getIPOReviewingStts(params);
        return list;
    }

    //需求4399 2018/5/24 by liuh Start
    /**
     * IPO在审项目数据统计-历史
     *
     * @param
     * @return
     */
    public List<StatisticsResultDto> getIPOHistory() {
//        ParameterizedTypeReference<JsonResponse<List<StatisticsResultDto>>> responseType = new ParameterizedTypeReference<JsonResponse<List<StatisticsResultDto>>>() {
//        };
//        String url = apiBaseUrl + "regulatory_statistics/getIPOHistory";
//        List<StatisticsResultDto> response = restClient.post(url, "", responseType).getResult();
        return statisticsBizMapper.getIPOHistory(null);
    }

    /**
     * IPO保荐机构统计
     *
     * @param letterId
     * @return
     */
    public Page<StatisticsResultDto> getIPORecommendOrgStts(QueryInfo<Map<String, Object>> queryInfo) {
        // ParameterizedTypeReference<JsonResponse<List<StatisticsResultDto>>> responseType = new
        // ParameterizedTypeReference<JsonResponse<List<StatisticsResultDto>>>() {
        // };
        // MultiValueMap<String, String> parameter = new LinkedMultiValueMap<String, String>();
        // parameter.add("registAddr", dto.getRegistAddr());
        // parameter.add("industry", dto.getIndustry());
        // List<StatisticsResultDto> response = restClient.post(url, parameter, responseType).getResult();
        // String url = apiBaseUrl + "regulatory_statistics/getIPORecommendOrgStts";
        // ParameterizedTypeReference<JsonResponse<Page<StatisticsResultDto>>> responseType = new
        // ParameterizedTypeReference<JsonResponse<Page<StatisticsResultDto>>>() {
        // };
        // Page<StatisticsResultDto> page = restClient.post(url, queryInfo, responseType).getResult();
        Map<String, Object> map = makeParamMap(queryInfo);
        // 获取每页数据
        Page<StatisticsResultDto> page = super
                .getPageList(
                        queryInfo.getStartRow(),
                        queryInfo.getPageSize(),
                        "com.stock.capital.enterprise.regulatory.dao.StatisticsBizMapper.getIPORecommendOrgStts",
                        map);
        return page;
    }

    /**
     * IPO会计师事务所统计
     *
     * @param letterId
     * @return
     */
    public Page<StatisticsResultDto> getIPOAccountantOfficeStts(QueryInfo<Map<String, Object>> queryInfo) {
//        ParameterizedTypeReference<JsonResponse<List<StatisticsResultDto>>> responseType = new ParameterizedTypeReference<JsonResponse<List<StatisticsResultDto>>>() {
//        };
//        String url = apiBaseUrl + "regulatory_statistics/getIPOAccountantOfficeStts";
//        MultiValueMap<String, String> parameter = new LinkedMultiValueMap<String, String>();
//        parameter.add("registAddr", dto.getRegistAddr());
//        parameter.add("industry", dto.getIndustry());
//        List<StatisticsResultDto> response = restClient.post(url, parameter, responseType).getResult();
//        return response;
//        String url = apiBaseUrl + "regulatory_statistics/getIPOAccountantOfficeStts";
//        ParameterizedTypeReference<JsonResponse<Page<StatisticsResultDto>>> responseType = new ParameterizedTypeReference<JsonResponse<Page<StatisticsResultDto>>>() {
//        };
//        Page<StatisticsResultDto> page = restClient.post(url, queryInfo, responseType).getResult();

        Map<String, Object> map = makeParamMap(queryInfo);
        // 获取每页数据
        Page<StatisticsResultDto> page = super
                .getPageList(
                        queryInfo.getStartRow(),
                        queryInfo.getPageSize(),
                        "com.stock.capital.enterprise.regulatory.dao.StatisticsBizMapper.getIPOAccountantOfficeStts",
                        map);
        return page;
    }

    /**
     * IPO律师事务所统计
     *
     * @param letterId
     * @return
     */
    public Page<StatisticsResultDto> getIPOLawFirmStts(QueryInfo<Map<String, Object>> queryInfo) {
//        ParameterizedTypeReference<JsonResponse<List<StatisticsResultDto>>> responseType = new ParameterizedTypeReference<JsonResponse<List<StatisticsResultDto>>>() {
//        };
//        String url = apiBaseUrl + "regulatory_statistics/getIPOLawFirmStts";
//        MultiValueMap<String, String> parameter = new LinkedMultiValueMap<String, String>();
//        parameter.add("registAddr", dto.getRegistAddr());
//        parameter.add("industry", dto.getIndustry());
//        List<StatisticsResultDto> response = restClient.post(url, parameter, responseType).getResult();
//        return response;
//        String url = apiBaseUrl + "regulatory_statistics/getIPOLawFirmStts";
//        ParameterizedTypeReference<JsonResponse<Page<StatisticsResultDto>>> responseType = new ParameterizedTypeReference<JsonResponse<Page<StatisticsResultDto>>>() {
//        };
//        Page<StatisticsResultDto> page = restClient.post(url, queryInfo, responseType).getResult();
        Map<String, Object> map = makeParamMap(queryInfo);
        // 获取每页数据
        Page<StatisticsResultDto> page = super
                .getPageList(
                        queryInfo.getStartRow(),
                        queryInfo.getPageSize(),
                        "com.stock.capital.enterprise.regulatory.dao.StatisticsBizMapper.getIPOLawFirmStts",
                        map);
        return page;
    }
    //需求4399 2018/5/24 by liuh end

    /**
     * IPO最新一期日期获取
     *
     * @param letterId
     * @return
     */
    public String getIPOLastTime() {
        ParameterizedTypeReference<JsonResponse<String>> responseType = new ParameterizedTypeReference<JsonResponse<String>>() {
        };
        String url = apiBaseUrl + "regulatory_statistics/getIPOLastTime";
        String response = restClient.post(url, "", responseType).getResult();
        if (!StringUtils.isEmpty(response)) {
            Date updateTime = DateUtil.getDate(response, DateUtil.YYYY_MM_DD);
            response = DateUtil.getDateStr(updateTime, DateUtil.YYYY_MM_DD_ZH);
        }
        return response;
    }

    /**
     * 发行监管部再融资企业审核状态
     *
     * @param letterId
     * @return
     */
    public List<StatisticsResultDto> getRefinanceApproveStatusStts() {
        ParameterizedTypeReference<JsonResponse<List<StatisticsResultDto>>> responseType = new ParameterizedTypeReference<JsonResponse<List<StatisticsResultDto>>>() {
        };
        String url = apiBaseUrl + "regulatory_statistics/getRefinanceApproveStatusStts";
        List<StatisticsResultDto> response = restClient.post(url, "", responseType).getResult();
        return response;
    }

    /**
     * 再融资申请类型情况
     *
     * @param letterId
     * @return
     */
    public List<StatisticsResultDto> getRefinanceAppTypeStts() {
        ParameterizedTypeReference<JsonResponse<List<StatisticsResultDto>>> responseType = new ParameterizedTypeReference<JsonResponse<List<StatisticsResultDto>>>() {
        };
        String url = apiBaseUrl + "regulatory_statistics/getRefinanceAppTypeStts";
        List<StatisticsResultDto> response = restClient.post(url, "", responseType).getResult();
        return response;
    }

    /**
     * 发行监管部再融资审核保荐机构保荐企业数量
     *
     * @param letterId
     * @return
     */
    public Page<StatisticsResultDto> getRefinanceRecommendOrgStts(QueryInfo<Map<String, Object>> queryInfo) {
        ParameterizedTypeReference<JsonResponse<Page<StatisticsResultDto>>> responseType = new ParameterizedTypeReference<JsonResponse<Page<StatisticsResultDto>>>() {
        };
        String url = apiBaseUrl + "regulatory_statistics/getRefinanceRecommendOrgStts";
        Page<StatisticsResultDto> response = restClient.post(url, queryInfo, responseType).getResult();
        return response;
    }

    /**
     * 再融资最新一期日期获取
     *
     * @param letterId
     * @return
     */
    public String getRefinanceLastTime() {
        ParameterizedTypeReference<JsonResponse<String>> responseType = new ParameterizedTypeReference<JsonResponse<String>>() {
        };
        String url = apiBaseUrl + "regulatory_statistics/getRefinanceLastTime";
        String response = restClient.post(url, "", responseType).getResult();
        if (!StringUtils.isEmpty(response)) {
            Date updateTime = DateUtil.getDate(response, DateUtil.YYYY_MM_DD);
            response = DateUtil.getDateStr(updateTime, DateUtil.YYYY_MM_DD_ZH);
        }
        return response;
    }

    public List<StatisticsResultDto> getIPOAreaDataStts(StatisticsParamDto statisticsParamDto) {
        ParameterizedTypeReference<JsonResponse<List<StatisticsResultDto>>> responseType = new ParameterizedTypeReference<JsonResponse<List<StatisticsResultDto>>>() {
        };
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
        parameters.add("belongsPlate", statisticsParamDto.getBelongsPlate());
        parameters.add("industry", statisticsParamDto.getIndustry());
        parameters.add("registAddr", statisticsParamDto.getRegistAddr());
        String ipoOrder = "";
        if(org.apache.commons.lang3.StringUtils.isNotBlank(statisticsParamDto.getIpoOrder())){
            ipoOrder = statisticsParamDto.getIpoOrder();
        }
        parameters.add("ipoOrder", ipoOrder);
        String url = apiBaseUrl + "regulatory_statistics/getIPOAreaDataStts";
        List<StatisticsResultDto> response = restClient.post(url, parameters, responseType).getResult();
        return response;
    }

    public List<OptionDto> getCodeAndName(String codeNo) {
        ParameterizedTypeReference<JsonResponse<List<OptionDto>>> responseType = new ParameterizedTypeReference<JsonResponse<List<OptionDto>>>() {
        };
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
        parameters.add("codeNo", codeNo);
        String url = apiBaseUrl + "regulatory_statistics/getCodeAndName";
        List<OptionDto> list = restClient.post(url, parameters, responseType).getResult();
        return list;
    }

    public List<OptionDto> getAreaList() {
//        ParameterizedTypeReference<JsonResponse<List<OptionDto>>> responseType = new ParameterizedTypeReference<JsonResponse<List<OptionDto>>>() {
//        };
//        String url = apiBaseUrl + "regulatory_statistics/getAreaList";
//        List<OptionDto> list = restClient.post(url, "", responseType).getResult();
//        return list;
        return statisticsBizMapper.getAreaList();
    }

    public List<TreeDto> getIndustryList() {
//        ParameterizedTypeReference<JsonResponse<List<TreeDto>>> responseType = new ParameterizedTypeReference<JsonResponse<List<TreeDto>>>() {
//        };
//        String url = apiBaseUrl + "declareInfo/postDeclareIndexIndustry";
//        List<TreeDto> list = restClient.post(url, null, responseType).getResult();
//        return list;
        return statisticsBizMapper.postDeclareIndexIndustry();
    }

    public List<StatisticsCompanyDto> queryAreaDetail(StatisticsParamDto statisticsParamDto) {
        ParameterizedTypeReference<JsonResponse<List<StatisticsCompanyDto>>> responseType = new ParameterizedTypeReference<JsonResponse<List<StatisticsCompanyDto>>>() {
        };
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
        parameters.add("registAddr", statisticsParamDto.getRegistAddr());
        parameters.add("lastUpadteTime", statisticsParamDto.getLastUpadteTime());
        parameters.add("belongsPlate", statisticsParamDto.getBelongsPlate());
        parameters.add("industry", statisticsParamDto.getIndustry());
        parameters.add("approveStatus", statisticsParamDto.getApproveStatus());
        String url = apiBaseUrl + "regulatory_statistics/queryAreaDetail";
        List<StatisticsCompanyDto> list = restClient.post(url, parameters, responseType).getResult();
        return list;
    }

    public ByteArrayInputStream ipoDetailExport(StatisticsParamDto statisticsParamDto) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        List<StatisticsCompanyDto> comDtos = getIpoItemDataDetail(statisticsParamDto).getIpoItemDataDetailList();
        HSSFSheet sheet = workbook.createSheet("Sheet1");
        HSSFRow row = null;
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setWrapText(true);
        HSSFFont f = workbook.createFont();
        f.setFontHeightInPoints((short) 12);
        f.setBold(true);
        cellStyle.setFont(f);
        HSSFCellStyle cs = workbook.createCellStyle();
        cs.setWrapText(true);
        HSSFCell cell = null;
        cell = sheet.createRow(0).createCell((int) 0);
        cell.setCellValue("URL Link");
        // 内容居中
        HSSFCellStyle conCenterStyle = workbook.createCellStyle();
        conCenterStyle.setAlignment(HorizontalAlignment.CENTER);
        conCenterStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        conCenterStyle.setWrapText(true);   
        // 设置标题
        row = sheet.createRow(0);
        row.setHeight((short) 600);
        //  协会机构不显示排行
        String[] titles = new String[] {"申报企业","注册地","所属行业","拟上市地","保荐机构","会计师事务所","律师事务所","审核状态","是否已参加抽查\r\n抽签或现场检查"};
        for (int i = 0; i < 9; i++) {
            sheet.setDefaultColumnStyle(i, cs);
            cell = row.createCell(i);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(titles[i]);
        }
        row = sheet.createRow(1);
        row.setHeight((short) 600);
        
        if (comDtos != null && comDtos.size() > 0) {
            // 设置内容
            for (int i = 0; i < comDtos.size(); i++) {
                row = sheet.createRow(i+1);
                row.setHeight((short) 600);
                cell = row.createCell(0);
                cell.setCellValue(comDtos.get(i).getAppCompany());
                cell.setCellStyle(conCenterStyle);
                cell = row.createCell(1);
                cell.setCellValue(changeAreaName(comDtos.get(i).getRegistAddr()));
                cell.setCellStyle(conCenterStyle);
                cell = row.createCell(2);
                if(!StringUtils.isEmpty(comDtos.get(i).getIndustry())) {
                    cell.setCellValue(comDtos.get(i).getIndustry());
                } else {
                    cell.setCellValue("--");
                }
                cell.setCellStyle(conCenterStyle);
                cell = row.createCell(3);
                cell.setCellValue(comDtos.get(i).getIpoAreaLabel());
                cell.setCellStyle(conCenterStyle);
                cell = row.createCell(4);
                cell.setCellValue(comDtos.get(i).getRecommendOrg());
                cell.setCellStyle(conCenterStyle);
                cell = row.createCell(5);
                cell.setCellValue(comDtos.get(i).getAccOffice());
                cell.setCellStyle(conCenterStyle);
                cell = row.createCell(6);
                cell.setCellValue(comDtos.get(i).getLawFirm());
                cell.setCellStyle(conCenterStyle);
               /* cell = row.createCell(7);
                cell.setCellValue(comDtos.get(i).getStatusLabel());
                cell.setCellStyle(conCenterStyle);*/
                if(comDtos.get(i).getIpoAreaLabel().contains("科创板")){
                	if("已反馈".equals(comDtos.get(i).getStatusLabel())){
                		comDtos.get(i).setStatusLabel("已问询");
                	}
                	if("已通过发审会".equals(comDtos.get(i).getStatusLabel())){
                		comDtos.get(i).setStatusLabel("上市委会议通过");
                	}
                	if("中止审查".equals(comDtos.get(i).getStatusLabel())){
                		comDtos.get(i).setStatusLabel("中止");
                	}
                	cell = row.createCell(7);
                	cell.setCellValue(comDtos.get(i).getStatusLabel());
                	cell.setCellStyle(conCenterStyle);
                	cell.setCellStyle(conCenterStyle);
                }else{
                	cell = row.createCell(7);
                	cell.setCellValue(comDtos.get(i).getStatusLabel());
                	cell.setCellStyle(conCenterStyle);
                	cell.setCellStyle(conCenterStyle);
                }
                cell = row.createCell(8);
                if(!StringUtils.isEmpty(comDtos.get(i).getAttendLabel())) {
                    cell.setCellValue(comDtos.get(i).getAttendLabel());
                } else {
                    cell.setCellValue("--");
                }
                cell.setCellStyle(conCenterStyle);
            }
        }
        sheet.setColumnWidth(0, 6000);
        sheet.setColumnWidth(1, 4000);
        sheet.setColumnWidth(2, 6000);
        sheet.setColumnWidth(3, 6000);
        sheet.setColumnWidth(4, 6000);
        sheet.setColumnWidth(5, 5000);
        sheet.setColumnWidth(6, 6000);
        sheet.setColumnWidth(7, 6000);
        sheet.setColumnWidth(8, 6000);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            workbook.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ByteArrayInputStream(os.toByteArray());
    }
    
    //地区特殊处理
    public String changeAreaName(String name) {
        String resultName = "";
        if (org.apache.commons.lang3.StringUtils.isNotBlank(name)) {
            if ("广东".equals(name)) {
                resultName = "广东(不含深圳)";
            } else if ("辽宁".equals(name)) {
                resultName = "辽宁(不含大连)";
            } else if ("浙江".equals(name)) {
                resultName = "浙江(不含宁波)";
            } else if ("福建".equals(name)) {
                resultName = "福建(不含厦门)";
            } else if ("山东".equals(name)) {
                resultName = "山东(不含青岛)";
            } else {
                resultName = name;
            }
        }
        return resultName;
    }
    
    public List<StatisticsResultDto> queryCommendDetail(StatisticsParamDto statisticsParamDto) {
        ParameterizedTypeReference<JsonResponse<List<StatisticsResultDto>>> responseType = new ParameterizedTypeReference<JsonResponse<List<StatisticsResultDto>>>() {
        };
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
        parameters.add("label",statisticsParamDto.getLabel());
        parameters.add("quasiListedLand",statisticsParamDto.getQuasiListedLand());
        parameters.add("industry",statisticsParamDto.getIndustry());
        parameters.add("registAddr",statisticsParamDto.getRegistAddr());
        parameters.add("approveStatus",statisticsParamDto.getApproveStatus());
        String url = apiBaseUrl + "regulatory_statistics/viewCommendDetail";
        List<StatisticsResultDto> list = restClient.post(url, parameters, responseType).getResult();
        return list;
    }
    
    public List<StatisticsResultDto> queryLawDetail(StatisticsParamDto statisticsParamDto) {
        ParameterizedTypeReference<JsonResponse<List<StatisticsResultDto>>> responseType = new ParameterizedTypeReference<JsonResponse<List<StatisticsResultDto>>>() {
        };
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
        parameters.add("label",statisticsParamDto.getLabel());
        parameters.add("quasiListedLand",statisticsParamDto.getQuasiListedLand());
        parameters.add("industry",statisticsParamDto.getIndustry());
        parameters.add("registAddr",statisticsParamDto.getRegistAddr());
        String url = apiBaseUrl + "regulatory_statistics/viewLawDetail";
        List<StatisticsResultDto> list = restClient.post(url, parameters, responseType).getResult();
        return list;
    }
    
    public List<StatisticsResultDto> queryAccountDetail(StatisticsParamDto statisticsParamDto) {
        ParameterizedTypeReference<JsonResponse<List<StatisticsResultDto>>> responseType = new ParameterizedTypeReference<JsonResponse<List<StatisticsResultDto>>>() {
        };
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
        parameters.add("label",statisticsParamDto.getLabel());
        parameters.add("quasiListedLand",statisticsParamDto.getQuasiListedLand());
        parameters.add("industry",statisticsParamDto.getIndustry());
        parameters.add("registAddr",statisticsParamDto.getRegistAddr());
        String url = apiBaseUrl + "regulatory_statistics/viewAccountDetail";
        List<StatisticsResultDto> list = restClient.post(url, parameters, responseType).getResult();
        return list;
    }
    
    public ByteArrayInputStream ipoCommendDetailExport(StatisticsParamDto statisticsParamDto , String flag) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        List<StatisticsResultDto> comDtos = new ArrayList<StatisticsResultDto>();
//        if("1".equals(flag)){
//        	comDtos = queryCommendDetail(statisticsParamDto);
//	  	}else if("2".equals(flag)){
//	  		comDtos = queryLawDetail(statisticsParamDto);
//	  	}else if("3".equals(flag)){
//	  		comDtos = queryAccountDetail(statisticsParamDto);
//	  	}
        comDtos = getIpoDataOverviewDetail(statisticsParamDto).getIpoDetailList();
        HSSFSheet sheet = workbook.createSheet("Sheet1");
        HSSFRow row = null;
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setWrapText(true);
        HSSFFont f = workbook.createFont();
        f.setFontHeightInPoints((short) 12);
        f.setBold(true);
        cellStyle.setFont(f);
        HSSFCellStyle cs = workbook.createCellStyle();
        cs.setWrapText(true);
        HSSFCell cell = null;
        cell = sheet.createRow(0).createCell((int) 0);
        cell.setCellValue("URL Link");
        // 内容居中
        HSSFCellStyle conCenterStyle = workbook.createCellStyle();
        conCenterStyle.setAlignment(HorizontalAlignment.CENTER);
        conCenterStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        conCenterStyle.setWrapText(true);   
        // 设置标题
        row = sheet.createRow(0);
        row.setHeight((short) 600);
        //  协会机构不显示排行
        String[] titles = new String[] {"申报企业","注册地","所属行业","拟上市地","保荐机构","会计师事务所","律师事务所","审核状态","是否已参加抽查\r\n抽签或现场检查"};
        for (int i = 0; i < 9; i++) {
            sheet.setDefaultColumnStyle(i, cs);
            cell = row.createCell(i);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(titles[i]);
        }
        row = sheet.createRow(1);
        row.setHeight((short) 600);
        
        if (comDtos != null && comDtos.size() > 0) {
            // 设置内容
            for (int i = 0; i < comDtos.size(); i++) {
                row = sheet.createRow(i+1);
                row.setHeight((short) 600);
                cell = row.createCell(0);
                cell.setCellValue(comDtos.get(i).getAppCompany());
                cell.setCellStyle(conCenterStyle);
                cell = row.createCell(1);
                cell.setCellValue(changeAreaName(comDtos.get(i).getRegistAddr()));
                cell.setCellStyle(conCenterStyle);
                cell = row.createCell(2);
                if(!StringUtils.isEmpty(comDtos.get(i).getBelongTrade())) {
                    cell.setCellValue(comDtos.get(i).getBelongTrade());
                } else {
                    cell.setCellValue("--");
                }
                cell.setCellStyle(conCenterStyle);
                cell = row.createCell(3);
                cell.setCellValue(comDtos.get(i).getQuasiListedLand());
                cell.setCellStyle(conCenterStyle);
                cell = row.createCell(4);
                cell.setCellValue(comDtos.get(i).getRecommendOrganization());
                cell.setCellStyle(conCenterStyle);
                cell = row.createCell(5);
                cell.setCellValue(comDtos.get(i).getAccountantOffice());
                cell.setCellStyle(conCenterStyle);
                cell = row.createCell(6);
                cell.setCellValue(comDtos.get(i).getLawFirm());
                cell.setCellStyle(conCenterStyle);
                
                /*cell = row.createCell(7);
                cell.setCellValue(comDtos.get(i).getApproveStatus());
                cell.setCellStyle(conCenterStyle);*/
                if(comDtos.get(i).getQuasiListedLand().contains("科创板")){
                	if("已反馈".equals(comDtos.get(i).getApproveStatus())){
                		comDtos.get(i).setApproveStatus("已问询");
                	}
                	if("已通过发审会".equals(comDtos.get(i).getApproveStatus())){
                		comDtos.get(i).setApproveStatus("上市委会议通过");
                	}
                	if("中止审查".equals(comDtos.get(i).getApproveStatus())){
                		comDtos.get(i).setApproveStatus("中止");
                	}
                	cell = row.createCell(7);
                	if(!StringUtils.isEmpty(comDtos.get(i).getApproveStatus())) {
                	    cell.setCellValue(comDtos.get(i).getApproveStatus());
                	} else {
                	    cell.setCellValue("--");
                	}
                	cell.setCellStyle(conCenterStyle);
                	cell.setCellStyle(conCenterStyle);
                }else{
                	cell = row.createCell(7);
                	if(!StringUtils.isEmpty(comDtos.get(i).getApproveStatus())) {
                            cell.setCellValue(comDtos.get(i).getApproveStatus());
                        } else {
                            cell.setCellValue("--");
                        }
                	cell.setCellStyle(conCenterStyle);
                	cell.setCellStyle(conCenterStyle);
                }
                
                
                cell = row.createCell(8);
                if("1".equals(comDtos.get(i).getHasedRandomInspection())){
                	cell.setCellValue("是");
                }else if("0".equals(comDtos.get(i).getHasedRandomInspection())){
                	cell.setCellValue("否");
                }
                else if("2".equals(comDtos.get(i).getHasedRandomInspection())){
                	cell.setCellValue("不适用");
                } else {
                    cell.setCellValue("--");
                }
                cell.setCellStyle(conCenterStyle);
            }
        }
        sheet.setColumnWidth(0, 6000);
        sheet.setColumnWidth(1, 4000);
        sheet.setColumnWidth(2, 6000);
        sheet.setColumnWidth(3, 6000);
        sheet.setColumnWidth(4, 6000);
        sheet.setColumnWidth(5, 5000);
        sheet.setColumnWidth(6, 6000);
        sheet.setColumnWidth(7, 6000);
        sheet.setColumnWidth(8, 6000);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            workbook.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ByteArrayInputStream(os.toByteArray());
    }
    
    public List<StatisticsResultDto> queryRefinanceDetail(StatisticsParamDto statisticsParamDto) {
        ParameterizedTypeReference<JsonResponse<List<StatisticsResultDto>>> responseType = new ParameterizedTypeReference<JsonResponse<List<StatisticsResultDto>>>() {
        };
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
        parameters.add("label",statisticsParamDto.getLabel());
        parameters.add("quasiListedLand",statisticsParamDto.getQuasiListedLand());
        parameters.add("industry",statisticsParamDto.getIndustry());
        parameters.add("registAddr",statisticsParamDto.getRegistAddr());
        parameters.add("stockCode",statisticsParamDto.getStockCode());
        String url = apiBaseUrl + "regulatory_statistics/queryRefinanceDetail";
        List<StatisticsResultDto> list = restClient.post(url, parameters, responseType).getResult();
        return list;
    }
    
    public ByteArrayInputStream ipoRefinanceDetailExport(StatisticsParamDto statisticsParamDto) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        List<StatisticsResultDto> comDtos = new ArrayList<StatisticsResultDto>();
        comDtos = queryRefinanceDetail(statisticsParamDto);
        HSSFSheet sheet = workbook.createSheet("Sheet1");
        HSSFRow row = null;
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setWrapText(true);
        HSSFFont f = workbook.createFont();
        f.setFontHeightInPoints((short) 12);
        f.setBold(true);
        cellStyle.setFont(f);
        HSSFCellStyle cs = workbook.createCellStyle();
        cs.setWrapText(true);
        HSSFCell cell = null;
        cell = sheet.createRow(0).createCell((int) 0);
        cell.setCellValue("URL Link");
        // 内容居中
        HSSFCellStyle conCenterStyle = workbook.createCellStyle();
        conCenterStyle.setAlignment(HorizontalAlignment.CENTER);
        conCenterStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        conCenterStyle.setWrapText(true);   
        // 设置标题
        row = sheet.createRow(0);
        row.setHeight((short) 600);
        //  协会机构不显示排行
        String[] titles = new String[] {"申报企业","注册地","所属行业","拟上市地","保荐机构","审核状态"};
        for (int i = 0; i < 6; i++) {
            sheet.setDefaultColumnStyle(i, cs);
            cell = row.createCell(i);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(titles[i]);
        }
        row = sheet.createRow(1);
        row.setHeight((short) 600);
        
        if (comDtos != null && comDtos.size() > 0) {
            // 设置内容
            for (int i = 0; i < comDtos.size(); i++) {
                row = sheet.createRow(i+1);
                row.setHeight((short) 600);
                cell = row.createCell(0);
                cell.setCellValue(comDtos.get(i).getAppCompany());
                cell.setCellStyle(conCenterStyle);
                cell = row.createCell(1);
                cell.setCellValue(changeAreaName(comDtos.get(i).getRegistAddr()));
                cell.setCellStyle(conCenterStyle);
                cell = row.createCell(2);
                cell.setCellValue(comDtos.get(i).getBelongTrade());
                cell.setCellStyle(conCenterStyle);
                cell = row.createCell(3);
                cell.setCellValue(comDtos.get(i).getQuasiListedLand());
                cell.setCellStyle(conCenterStyle);
                cell = row.createCell(4);
                cell.setCellValue(comDtos.get(i).getRecommendOrganization());
                cell.setCellStyle(conCenterStyle);
                //审核状态
                if(comDtos.get(i).getQuasiListedLand().contains("科创板")){
                	if("已反馈".equals(comDtos.get(i).getApproveStatus())){
                		comDtos.get(i).setApproveStatus("已问询");
                	}
                	if("已通过发审会".equals(comDtos.get(i).getApproveStatus())){
                		comDtos.get(i).setApproveStatus("上市委会议通过");
                	}
                	if("中止审查".equals(comDtos.get(i).getApproveStatus())){
                		comDtos.get(i).setApproveStatus("中止");
                	}
                	cell = row.createCell(5);
                	cell.setCellValue(comDtos.get(i).getApproveStatus());
                	cell.setCellStyle(conCenterStyle);
                	cell.setCellStyle(conCenterStyle);
                }else{
                	cell = row.createCell(5);
                	cell.setCellValue(comDtos.get(i).getApproveStatus());
                	cell.setCellStyle(conCenterStyle);
                	cell.setCellStyle(conCenterStyle);
                }
            }
        }
        sheet.setColumnWidth(0, 6000);
        sheet.setColumnWidth(1, 4000);
        sheet.setColumnWidth(2, 6000);
        sheet.setColumnWidth(3, 6000);
        sheet.setColumnWidth(4, 6000);
        sheet.setColumnWidth(5, 6000);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            workbook.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ByteArrayInputStream(os.toByteArray());
    }
    
    //excel down
    public InputStream exportExcel(String filePath, QueryInfo<StatisticsParamDto> statisticsParamDto) throws Exception {
        // InputStream templateFile = new FileInputStream(templatePath);
        // XSSFWorkbook workbook = new XSSFWorkbook(templateFile);
        Resource resource = new ServletContextResource(this.servletContext, filePath);
        InputStream file = resource.getInputStream();// 源文件流
        Workbook workbook = WorkbookFactory.create(file);

        // 参数表
        Sheet sheet = null;

        sheet = workbook.getSheetAt(0);
        Row row = null;
        Cell cell = null;
        // XSSFSheet sheet = workbook.getSheetAt(0);
//        workbook.setActiveSheet(0);
        // XSSFRow row = null;
        // XSSFCell cell = null;
        // 黑边框
        CellStyle blackBorder = blackBorderStyle(workbook);
        // 文本居中
        CellStyle centerCellStyle = blackBorderStyle(workbook);
        centerCellStyle.setAlignment(HorizontalAlignment.CENTER);

        int rowNum = 0;
        row = sheet.getRow(rowNum) == null ? sheet.createRow(rowNum) : sheet.getRow(rowNum);

        rowNum++;
        row = sheet.getRow(rowNum) == null ? sheet.createRow(rowNum) : sheet.getRow(rowNum);
        cell = row.getCell(12) == null ? row.createCell(12) : row.getCell(12);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        cell.setCellValue(sdf.format(new Date()) + "年");

        // StatisticsParamDto statisticsParamDto = new StatisticsParamDto();
        // statisticsParamDto.setBelongsPlate(belongsPlate);
        // statisticsParamDto.setRegistAddr(registAddr);
        // List<StatisticsResultDto> lists = statisticsService.getIPOAreaDataStts(statisticsParamDto);
        List<StatisticsResultDto> lists = ipoItemDataQuery(statisticsParamDto).getIpoItemDataList();

        int i = 0;
        for (StatisticsResultDto dto : lists) {
            i++;
            rowNum++;
            if (org.apache.commons.lang3.StringUtils.isNotEmpty(dto.getRegistAddr())) {
                row = sheet.getRow(rowNum) == null ? sheet.createRow(rowNum) : sheet.getRow(rowNum);
                cell = row.getCell(0) == null ? row.createCell(0) : row.getCell(0);
                cell.setCellStyle(blackBorder);
                cell.setCellStyle(centerCellStyle);
                cell.setCellValue(i);
            } else {
                row = sheet.getRow(rowNum) == null ? sheet.createRow(rowNum) : sheet.getRow(rowNum);
                cell = row.getCell(0) == null ? row.createCell(0) : row.getCell(0);
                cell.setCellStyle(blackBorder);
                cell.setCellStyle(centerCellStyle);
            }

            cell = row.getCell(1) == null ? row.createCell(1) : row.getCell(1);
            cell.setCellStyle(blackBorder);
            cell.setCellStyle(centerCellStyle);
            if (org.apache.commons.lang3.StringUtils.isNotEmpty(dto.getRegistAddr())) {
                cell.setCellValue(dto.getRegistAddr());
                if ((dto.getRegistAddr().equals("广东"))) {
                    cell.setCellValue("广东(不含深圳)");
                }
                if ((dto.getRegistAddr().equals("辽宁"))) {
                    cell.setCellValue("辽宁(不含大连)");
                }
                if ((dto.getRegistAddr().equals("浙江"))) {
                    cell.setCellValue("浙江(不含宁波)");
                }
                if ((dto.getRegistAddr().equals("福建"))) {
                    cell.setCellValue("福建(不含厦门)");
                }
                if ((dto.getRegistAddr().equals("山东"))) {
                    cell.setCellValue("山东(不含青岛)");
                }
            } else {
                cell.setCellValue("合计");
            }

            /*
             * if(name == "广东"){ resultName = "广东(不含深圳)"; } else if(name == "辽宁"){ resultName = "辽宁(不含大连)"; } else
             * if(name == "浙江"){ resultName = "浙江(不含宁波)"; } else if(name == "福建"){ resultName = "福建(不含厦门)"; } else
             * if(name == "山东"){ resultName = "山东(不含青岛)";
             */

            cell = row.getCell(2) == null ? row.createCell(2) : row.getCell(2);
            cell.setCellStyle(blackBorder);
            cell.setCellStyle(centerCellStyle);
            cell.setCellValue(dto.getApplied());

            cell = row.getCell(3) == null ? row.createCell(3) : row.getCell(3);
            cell.setCellStyle(blackBorder);
            cell.setCellStyle(centerCellStyle);
            cell.setCellValue(dto.getReviewed());

            cell = row.getCell(4) == null ? row.createCell(4) : row.getCell(4);
            cell.setCellStyle(blackBorder);
            cell.setCellStyle(centerCellStyle);
            cell.setCellValue(dto.getPreUpdate());

            cell = row.getCell(5) == null ? row.createCell(5) : row.getCell(5);
            cell.setCellStyle(blackBorder);
            cell.setCellStyle(centerCellStyle);
            cell.setCellValue(dto.getEndYet());

            cell = row.getCell(6) == null ? row.createCell(6) : row.getCell(6);
            cell.setCellStyle(blackBorder);
            cell.setCellStyle(centerCellStyle);
            cell.setCellValue(dto.getProcessing());

            cell = row.getCell(7) == null ? row.createCell(7) : row.getCell(7);
            cell.setCellStyle(blackBorder);
            cell.setCellStyle(centerCellStyle);
            cell.setCellValue(dto.getPassed());

            cell = row.getCell(8) == null ? row.createCell(8) : row.getCell(8);// 提交注册
            cell.setCellStyle(blackBorder);
            cell.setCellStyle(centerCellStyle);
            cell.setCellValue(dto.getSubmited());

            cell = row.getCell(9) == null ? row.createCell(9) : row.getCell(9);// 提交注册
            cell.setCellStyle(blackBorder);
            cell.setCellStyle(centerCellStyle);
            cell.setCellValue(dto.getZc());

            cell = row.getCell(10) == null ? row.createCell(10) : row.getCell(10);
            cell.setCellStyle(blackBorder);
            cell.setCellStyle(centerCellStyle);
            cell.setCellValue(dto.getAreaCount());

            cell = row.getCell(11) == null ? row.createCell(11) : row.getCell(11);
            cell.setCellStyle(blackBorder);
            cell.setCellStyle(centerCellStyle);
            cell.setCellValue(dto.getWeekStopYet());

            cell = row.getCell(12) == null ? row.createCell(12) : row.getCell(12);
            cell.setCellStyle(blackBorder);
            cell.setCellStyle(centerCellStyle);
            cell.setCellValue(dto.getStopYet());

        }

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        workbook.write(os);
        os.flush();
        os.close();
        return new ByteArrayInputStream(os.toByteArray());
    }
    
    private CellStyle blackBorderStyle(Workbook workbook) {
        CellStyle blackBorder = workbook.createCellStyle();
        blackBorder.setBorderRight(BorderStyle.THIN);
        blackBorder.setRightBorderColor(IndexedColors.BLACK.getIndex());
        blackBorder.setBorderLeft(BorderStyle.THIN);
        blackBorder.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        blackBorder.setBorderTop(BorderStyle.THIN);
        blackBorder.setTopBorderColor(IndexedColors.BLACK.getIndex());
        blackBorder.setBorderBottom(BorderStyle.THIN);
        blackBorder.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        return blackBorder;
    }
    
    /**
     * 拼接查询条件
     */
    public Map<String, Object> makeParamMap(QueryInfo<Map<String, Object>> queryInfo) {
        Map<String, Object> params = Maps.newHashMap();
        String updateTime = statisticsBizMapper.getIPOLastTime();
        List<String> areaList = new ArrayList<String>();
        List<String> industryList = new ArrayList<String>();
        // 截止时间
        params.put("lastUpadteTime", updateTime);
        //排序
        if(!StringUtils.isEmpty(queryInfo.getOrderByName())) {
            params.put("orderByName", queryInfo.getOrderByName());
        }
        String orderByOrder = "";
        if(!StringUtils.isEmpty(queryInfo.getOrderByOrder())) {
            if("descending".equals(queryInfo.getOrderByOrder())) {
                orderByOrder = "DESC";
            } else {
                orderByOrder = "ASC";
            }
            params.put("orderByOrder", orderByOrder);
        }
        
        // 注册地
        if (queryInfo.getCondition().get("registAddr") != null && queryInfo.getCondition().get("registAddr") != "") {
            areaList = Arrays.asList(queryInfo.getCondition().get("registAddr").toString().split(","));
            params.put("areaList", areaList);
        }
        // 所属行业
        if (queryInfo.getCondition().get("industry") != null && queryInfo.getCondition().get("industry") != "") {
            industryList = Arrays.asList(queryInfo.getCondition().get("industry").toString().split(","));
            params.put("industryList", industryList);
        }
        return params;
    }
    
    /**
     * 取得ipo数据概览详情数据
     */
    public StatisticsReturnDto getIpoDataOverviewDetail(StatisticsParamDto dto) {
        List<String> areaList = new ArrayList<String>();
        List<String> industryList = new ArrayList<String>();
        Map<String, Object> map = new HashMap<String, Object>();
        String updateTime = statisticsBizMapper.getIPOLastTime();
        //截止时间
        map.put("lastUpadteTime", updateTime);
        //会计师事务所名称
        map.put("label", dto.getLabel());
        //所属板块
        map.put("quasiListedLand", dto.getQuasiListedLand());
        //所属行业
        if(!dto.getIndustry().isEmpty()){
            industryList = Arrays.asList(dto.getIndustry().toString().split(","));
            map.put("industryList", industryList);
        }
        //注册地
        if(!dto.getRegistAddr().isEmpty()){
                areaList = Arrays.asList(dto.getRegistAddr().toString().split(","));
            map.put("areaList", areaList);
        }
        List<StatisticsResultDto> list = Lists.newArrayList();
        if("first".equals(dto.getTabFlag())) {
            //保荐机构详情页数据
            list = statisticsBizMapper.queryCommendDetail(map);
        } else if("second".equals(dto.getTabFlag())) {
            //律师事务所详情页数据
            list = statisticsBizMapper.queryLawDetail(map);
        } else if("third".equals(dto.getTabFlag())) {
            //律师事务所详情页数据
            list = statisticsBizMapper.queryAccountDetail(map);
        }
        StatisticsReturnDto returnDto = new StatisticsReturnDto();
        returnDto.setIpoDetailList(list);
        return returnDto;
    }
    
    /**
     * 取得ipo数据概览详情数据
     */
    public StatisticsReturnDto ipoItemDataQuery(QueryInfo<StatisticsParamDto> dto) {
        Map<String, Object> map = new HashMap<String, Object>();
        String updateTime = statisticsBizMapper.getIPOLastTime();
        //截止时间
        map.put("lastUpadteTime", updateTime);
        //最近一周时间
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(format.parse(updateTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, - 7);
        map.put("lastWeek", format.format(c.getTime()));
        
        //排序
        if(!StringUtils.isEmpty(dto.getOrderByName()) && !StringUtils.isEmpty(dto.getOrderByOrder())) {
            String ipoOrder = "";
            String order = "DESC";
            if("ascending".equals(dto.getOrderByOrder())) {
                order = "ASC";
            }
            ipoOrder = dto.getOrderByName() + " " + order + ",";
            map.put("ipoOrder", ipoOrder);
        }
        
        List<String> plateList = new ArrayList<String>();
        List<String> areaList = new ArrayList<String>();
        StatisticsParamDto statisticsParamDto = dto.getCondition();
        //所属板块
        if(!StringUtils.isEmpty(statisticsParamDto.getBelongsPlate())){
            plateList = Arrays.asList(statisticsParamDto.getBelongsPlate().split(","));
            map.put("plateList", plateList);
        }
        //注册地
        if(!StringUtils.isEmpty(statisticsParamDto.getRegistAddr())){
            areaList = Arrays.asList(statisticsParamDto.getRegistAddr().split(","));
            map.put("areaList", areaList);
        }
        List<StatisticsResultDto> list = statisticsBizMapper.getIpoItemDataQuery(map);
        StatisticsReturnDto returnDto = new StatisticsReturnDto();
        returnDto.setIpoItemDataList(list);
        return returnDto;
    }
    
    /**
     * 取得ipo在审项目详情数据
     */
    public StatisticsReturnDto getIpoItemDataDetail(StatisticsParamDto dto) {
        List<String> plateList = new ArrayList<String>();
        List<String> areaList = new ArrayList<String>();
        Map<String, Object> map = new HashMap<String, Object>();
        // String updateTime = statisticsBizMapper.getIPOLastTime();
        // 截止时间
        map.put("lastUpadteTime", dto.getLastUpadteTime());
        // 所属板块
        if (!StringUtils.isEmpty(dto.getBelongsPlate())) {
            plateList = Arrays.asList(dto.getBelongsPlate().split(","));
            map.put("plateList", plateList);
        }
        // 注册地
        if (!StringUtils.isEmpty(dto.getRegistAddr())) {
            areaList = Arrays.asList(dto.getRegistAddr().toString().split(","));
            map.put("areaList", areaList);
        }
        map.put("approveStatus", dto.getApproveStatus());
        map.put("lastUpadteTime", dto.getLastUpadteTime());
        // 最近一周时间
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(format.parse(dto.getLastUpadteTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, -7);
        map.put("lastWeek", format.format(c.getTime()));
        List<StatisticsCompanyDto> list = Lists.newArrayList();
        list = statisticsBizMapper.getIpoItemDataDetail(map);
        if(list != null && list.size() > 0) {
            for (StatisticsCompanyDto statisticsCompanyDto : list) {
                if (statisticsCompanyDto.getIpoAreaLabel().contains("科创板")) {
                    if ("已反馈".equals(statisticsCompanyDto.getStatusLabel())) {
                        statisticsCompanyDto.setStatusLabel("已问询");
                    }
                    if ("已通过发审会".equals(statisticsCompanyDto.getStatusLabel())) {
                        statisticsCompanyDto.setStatusLabel("上市委会议通过");
                    }
                    if ("中止审查".equals(statisticsCompanyDto.getStatusLabel())) {
                        statisticsCompanyDto.setStatusLabel("中止");
                    }
                }
            }
        }
        StatisticsReturnDto returnDto = new StatisticsReturnDto();
        returnDto.setIpoItemDataDetailList(list);
        return returnDto;
    }
}

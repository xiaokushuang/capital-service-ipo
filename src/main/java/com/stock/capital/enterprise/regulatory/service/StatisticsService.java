package com.stock.capital.enterprise.regulatory.service;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.alibaba.druid.util.StringUtils;
import com.stock.capital.enterprise.regulatory.dto.StatisticsCompanyDto;
import com.stock.capital.enterprise.regulatory.dto.StatisticsParamDto;
import com.stock.capital.enterprise.regulatory.dto.StatisticsResultDto;
//import com.stock.capital.enterprise.regulatory.dto.StatisticsParamDto;
import com.stock.core.dto.JsonResponse;
import com.stock.core.dto.OptionDto;
import com.stock.core.dto.QueryInfo;
import com.stock.core.dto.TreeDto;
import com.stock.core.rest.RestClient;
import com.stock.core.service.BaseService;
import com.stock.core.util.DateUtil;
import com.stock.core.dto.Page;

@Service
public class StatisticsService extends BaseService {

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

    /**
     * IPO在审项目数据统计
     *
     * @param letterId
     * @return
     */
    public List<StatisticsResultDto> getIPOReviewingStts() {
        ParameterizedTypeReference<JsonResponse<List<StatisticsResultDto>>> responseType = new ParameterizedTypeReference<JsonResponse<List<StatisticsResultDto>>>() {
        };
        String url = apiBaseUrl + "regulatory_statistics/getIPOReviewingStts";
        List<StatisticsResultDto> response = restClient.post(url, "", responseType).getResult();
        return response;
    }

    //需求4399 2018/5/24 by liuh Start
    /**
     * IPO在审项目数据统计-历史
     *
     * @param
     * @return
     */
    public List<StatisticsResultDto> getIPOHistory() {
        ParameterizedTypeReference<JsonResponse<List<StatisticsResultDto>>> responseType = new ParameterizedTypeReference<JsonResponse<List<StatisticsResultDto>>>() {
        };
        String url = apiBaseUrl + "regulatory_statistics/getIPOHistory";
        List<StatisticsResultDto> response = restClient.post(url, "", responseType).getResult();
        return response;
    }

    /**
     * IPO保荐机构统计
     *
     * @param letterId
     * @return
     */
    public Page<StatisticsResultDto> getIPORecommendOrgStts(QueryInfo<Map<String, Object>> queryInfo) {
//        ParameterizedTypeReference<JsonResponse<List<StatisticsResultDto>>> responseType = new ParameterizedTypeReference<JsonResponse<List<StatisticsResultDto>>>() {
//        };
//        MultiValueMap<String, String> parameter = new LinkedMultiValueMap<String, String>();
//        parameter.add("registAddr", dto.getRegistAddr());
//        parameter.add("industry", dto.getIndustry());
//        List<StatisticsResultDto> response = restClient.post(url, parameter, responseType).getResult();
        String url = apiBaseUrl + "regulatory_statistics/getIPORecommendOrgStts";
        ParameterizedTypeReference<JsonResponse<Page<StatisticsResultDto>>> responseType = new ParameterizedTypeReference<JsonResponse<Page<StatisticsResultDto>>>() {
        };
        Page<StatisticsResultDto> page = restClient.post(url, queryInfo, responseType).getResult();
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
        String url = apiBaseUrl + "regulatory_statistics/getIPOAccountantOfficeStts";
        ParameterizedTypeReference<JsonResponse<Page<StatisticsResultDto>>> responseType = new ParameterizedTypeReference<JsonResponse<Page<StatisticsResultDto>>>() {
        };
        Page<StatisticsResultDto> page = restClient.post(url, queryInfo, responseType).getResult();
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
        String url = apiBaseUrl + "regulatory_statistics/getIPOLawFirmStts";
        ParameterizedTypeReference<JsonResponse<Page<StatisticsResultDto>>> responseType = new ParameterizedTypeReference<JsonResponse<Page<StatisticsResultDto>>>() {
        };
        Page<StatisticsResultDto> page = restClient.post(url, queryInfo, responseType).getResult();
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
        ParameterizedTypeReference<JsonResponse<List<OptionDto>>> responseType = new ParameterizedTypeReference<JsonResponse<List<OptionDto>>>() {
        };
        String url = apiBaseUrl + "regulatory_statistics/getAreaList";
        List<OptionDto> list = restClient.post(url, "", responseType).getResult();
        return list;
    }

    public List<TreeDto> getIndustryList() {
        ParameterizedTypeReference<JsonResponse<List<TreeDto>>> responseType = new ParameterizedTypeReference<JsonResponse<List<TreeDto>>>() {
        };
        String url = apiBaseUrl + "declareInfo/postDeclareIndexIndustry";
        List<TreeDto> list = restClient.post(url, null, responseType).getResult();
        return list;
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
        List<StatisticsCompanyDto> comDtos = queryAreaDetail(statisticsParamDto);
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
                cell.setCellValue(comDtos.get(i).getIndustry());
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
                cell = row.createCell(7);
                cell.setCellValue(comDtos.get(i).getStatusLabel());
                cell.setCellStyle(conCenterStyle);
                cell = row.createCell(8);
                cell.setCellValue(comDtos.get(i).getAttendLabel());
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
        if("1".equals(flag)){
        	comDtos = queryCommendDetail(statisticsParamDto);
	  	}else if("2".equals(flag)){
	  		comDtos = queryLawDetail(statisticsParamDto);
	  	}else if("3".equals(flag)){
	  		comDtos = queryAccountDetail(statisticsParamDto);
	  	}
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
                cell.setCellValue(comDtos.get(i).getBelongTrade());
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
                	cell.setCellValue(comDtos.get(i).getApproveStatus());
                	cell.setCellStyle(conCenterStyle);
                	cell.setCellStyle(conCenterStyle);
                }else{
                	cell = row.createCell(7);
                	cell.setCellValue(comDtos.get(i).getApproveStatus());
                	cell.setCellStyle(conCenterStyle);
                	cell.setCellStyle(conCenterStyle);
                }
                
                
                cell = row.createCell(8);
                if("1".equals(comDtos.get(i).getHasedRandomInspection())){
                	cell.setCellValue("是");
                }else if("0".equals(comDtos.get(i).getHasedRandomInspection())){
                	cell.setCellValue("否");
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
    public InputStream exportExcel(InputStream templateFile,String belongsPlate,String registAddr) throws IOException {
        //InputStream templateFile = new FileInputStream(templatePath);
        XSSFWorkbook workbook = new XSSFWorkbook(templateFile);
        try {

            // 参数表
            XSSFSheet sheet = workbook.getSheetAt(0);
            workbook.setActiveSheet(0);
            XSSFRow row = null;
            XSSFCell cell = null;
            // 黑边框
            XSSFCellStyle blackBorder = blackBorderStyle(workbook);
            // 文本居中
            XSSFCellStyle centerCellStyle = blackBorderStyle(workbook);
            centerCellStyle.setAlignment(HorizontalAlignment.CENTER);
            
            int rowNum = 0;
            row = sheet.getRow(rowNum) == null ? sheet.createRow(rowNum) : sheet.getRow(rowNum);
            
            rowNum ++;
            row = sheet.getRow(rowNum) == null ? sheet.createRow(rowNum) : sheet.getRow(rowNum);
            cell = row.getCell(11) == null ? row.createCell(11) : row.getCell(11);
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        	cell.setCellValue(sdf.format(new Date())+"年");
        	
            StatisticsParamDto statisticsParamDto = new StatisticsParamDto();
            statisticsParamDto.setBelongsPlate(belongsPlate);
            statisticsParamDto.setRegistAddr(registAddr);
            List<StatisticsResultDto> lists = statisticsService.getIPOAreaDataStts(statisticsParamDto);
            
            int i =0;
            for(StatisticsResultDto dto : lists){
            	i ++;
            	rowNum ++;
            	if(org.apache.commons.lang3.StringUtils.isNotEmpty(dto.getRegistAddr())){
	            	row = sheet.getRow(rowNum) == null ? sheet.createRow(rowNum) : sheet.getRow(rowNum);
	            	cell = row.getCell(0) == null ? row.createCell(0) : row.getCell(0);
	            	cell.setCellStyle(blackBorder);
	            	cell.setCellStyle(centerCellStyle);
	            	cell.setCellValue(i);
            	}else{
            		row = sheet.getRow(rowNum) == null ? sheet.createRow(rowNum) : sheet.getRow(rowNum);
	            	cell = row.getCell(0) == null ? row.createCell(0) : row.getCell(0);
	            	cell.setCellStyle(blackBorder);
	            	cell.setCellStyle(centerCellStyle);
            	}
            	
            	cell = row.getCell(1) == null ? row.createCell(1) : row.getCell(1);
            	cell.setCellStyle(blackBorder);
            	cell.setCellStyle(centerCellStyle);
            	if(org.apache.commons.lang3.StringUtils.isNotEmpty(dto.getRegistAddr())){
            		cell.setCellValue(dto.getRegistAddr());
            		if((dto.getRegistAddr().equals("广东"))){
            			cell.setCellValue("广东(不含深圳)");
            		}
            		if((dto.getRegistAddr().equals("辽宁"))){
            			cell.setCellValue("辽宁(不含大连)");
            		}
            		if((dto.getRegistAddr().equals("浙江"))){
            			cell.setCellValue("浙江(不含宁波)");
            		}
            		if((dto.getRegistAddr().equals("福建"))){
            			cell.setCellValue("福建(不含厦门)");
            		}
            		if((dto.getRegistAddr().equals("山东"))){
            			cell.setCellValue("山东(不含青岛)");
            		}
            	}else{
            		cell.setCellValue("合计");
            	}
            	
            	/*if(name == "广东"){
        			resultName = "广东(不含深圳)";
        		} else if(name == "辽宁"){
        			resultName = "辽宁(不含大连)";
        		} else if(name == "浙江"){
        			resultName = "浙江(不含宁波)";
        		} else if(name == "福建"){
        			resultName = "福建(不含厦门)";
        		} else if(name == "山东"){
        			resultName = "山东(不含青岛)";*/
            	
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
            	
            	cell = row.getCell(8) == null ? row.createCell(8) : row.getCell(8);//提交注册
            	cell.setCellStyle(blackBorder);
            	cell.setCellStyle(centerCellStyle);
            	cell.setCellValue(dto.getPassed());
            	
            	cell = row.getCell(9) == null ? row.createCell(9) : row.getCell(9);
            	cell.setCellStyle(blackBorder);
            	cell.setCellStyle(centerCellStyle);
            	cell.setCellValue(dto.getAreaCount());
            	
            	cell = row.getCell(10) == null ? row.createCell(10) : row.getCell(10);
            	cell.setCellStyle(blackBorder);
            	cell.setCellStyle(centerCellStyle);
            	cell.setCellValue(dto.getWeekStopYet());
            	
            	cell = row.getCell(11) == null ? row.createCell(11) : row.getCell(11);
            	cell.setCellStyle(blackBorder);
            	cell.setCellStyle(centerCellStyle);
            	cell.setCellValue(dto.getStopYet());
            	
            }
            
            
           /* rowNum ++;
            row = sheet.getRow(rowNum) == null ? sheet.createRow(rowNum) : sheet.getRow(rowNum);
            cell = row.getCell(0) == null ? row.createCell(0) : row.getCell(0);
            cell.setCellStyle(blackBorder);
            cell.setCellStyle(centerCellStyle);
            cell.setCellValue(1);*/
            
           /* List excelList = new ArrayList<>();
            if (excelList != null && excelList.size() >= 1) {*/
                // 行复制
               /* int starRow = 1;
                // list size
                int rows = 1;
                sheet.shiftRows(starRow+1, sheet.getLastRowNum() + 2, 1, true, false);
                starRow = starRow - 1;
                for (int i = 0; i < rows+1; i++) {
                    XSSFRow sourceRow = null;
                    XSSFRow targetRow = null;
                    XSSFCell sourceCell = null;
                    XSSFCell targetCell = null;
                    short m;

                    // 隔行变色
                    if ((i + 1) % 2 == 0) {
                        starRow = starRow + 1;
                        sourceRow = sheet.getRow(sheet.getLastRowNum()+1);
                        targetRow = sheet.createRow(starRow + 1);
                    } else {
                        starRow = starRow + 1;
                        sourceRow = sheet.getRow(1);
                        targetRow = sheet.createRow(starRow + 1);
                    }

                    // 设定单元格的样式
                    for (m = sourceRow.getFirstCellNum(); m < sourceRow.getLastCellNum(); m++) {
                        sourceCell = sourceRow.getCell(m);
                        targetCell = targetRow.createCell(m);
                        targetCell.setCellStyle(sourceCell.getCellStyle());
                        targetCell.setCellType(sourceCell.getCellTypeEnum());
                    }

                    
                      targetRow.getCell(0).setCellValue(i+1);
                      targetRow.getCell(0).getCellStyle().setVerticalAlignment(VerticalAlignment.CENTER);
                    // 数据名称
                    targetRow.getCell(1).setCellValue("注册地");
                    targetRow.getCell(1).getCellStyle().setVerticalAlignment(VerticalAlignment.CENTER);
					targetRow.getCell(2).setCellValue("已受理");
					targetRow.getCell(2).getCellStyle().setVerticalAlignment(VerticalAlignment.CENTER);
                    targetRow.getCell(3).setCellValue("已反馈");
                    targetRow.getCell(3).getCellStyle().setVerticalAlignment(VerticalAlignment.CENTER);
                    targetRow.getCell(4).setCellValue("预先披露更新");
                    targetRow.getCell(4).getCellStyle().setVerticalAlignment(VerticalAlignment.CENTER);
                    
                    targetRow.getCell(5).setCellValue("中止审查");
                    targetRow.getCell(5).getCellStyle().setVerticalAlignment(VerticalAlignment.CENTER);
                    
                    targetRow.getCell(6).setCellValue("已提交发审会讨论，暂缓表决");
                    targetRow.getCell(6).getCellStyle().setVerticalAlignment(VerticalAlignment.CENTER);
                    
                    targetRow.getCell(7).setCellValue("已通过发审会");
                    targetRow.getCell(7).getCellStyle().setVerticalAlignment(VerticalAlignment.CENTER);
                    
                    targetRow.getCell(8).setCellValue("合计");
                    targetRow.getCell(8).getCellStyle().setVerticalAlignment(VerticalAlignment.CENTER);
                    
                    targetRow.getCell(9).setCellValue("最近一周");
                    targetRow.getCell(9).getCellStyle().setVerticalAlignment(VerticalAlignment.CENTER);
                    
                    targetRow.getCell(10).setCellValue("2018年");
                    targetRow.getCell(10).getCellStyle().setVerticalAlignment(VerticalAlignment.CENTER);
                    
                    targetRow.getCell(9).setCellValue("2018年");
                    targetRow.getCell(9).getCellStyle().setVerticalAlignment(VerticalAlignment.CENTER);
               // }
                sheet.shiftRows(2, sheet.getLastRowNum(), -1);*/
            //}
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            workbook.write(os);
            os.flush();
            os.close();
            return new ByteArrayInputStream(os.toByteArray());
        } finally {
            workbook.close();
        }
    }
    
    private XSSFCellStyle blackBorderStyle(XSSFWorkbook workbook) {
        XSSFCellStyle blackBorder = workbook.createCellStyle();
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
}

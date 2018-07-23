package com.stock.capital.enterprise.regulatory.service;

import java.io.ByteArrayInputStream;
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
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;

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
import com.google.common.collect.Lists;

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
    public List<StatisticsResultDto> getRefinanceRecommendOrgStts() {
        ParameterizedTypeReference<JsonResponse<List<StatisticsResultDto>>> responseType = new ParameterizedTypeReference<JsonResponse<List<StatisticsResultDto>>>() {
        };
        String url = apiBaseUrl + "regulatory_statistics/getRefinanceRecommendOrgStts";
        List<StatisticsResultDto> response = restClient.post(url, "", responseType).getResult();
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
                cell = row.createCell(7);
                cell.setCellValue(comDtos.get(i).getApproveStatus());
                cell.setCellStyle(conCenterStyle);
                cell = row.createCell(8);
                cell.setCellValue(comDtos.get(i).getHasedRandomInspection());
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
}

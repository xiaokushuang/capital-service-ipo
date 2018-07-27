package com.stock.capital.enterprise.regulatory.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.stock.capital.enterprise.regulatory.dto.PunishmentSearchDto;
import com.stock.capital.enterprise.regulatory.dto.ViolationDetailDto;
import com.stock.capital.enterprise.regulatory.dto.ViolationDetailPersonDto;
import com.stock.capital.enterprise.regulatory.dto.ViolationOverviewDto;
import com.stock.capital.enterprise.regulatory.dto.ViolationParamDto;
import com.stock.capital.enterprise.regulatory.dto.ViolationSearchDto;
import com.stock.core.dto.FacetResult;
import com.stock.core.dto.JsonResponse;
import com.stock.core.dto.OptionDto;
import com.stock.core.dto.QueryInfo;
import com.stock.core.rest.RestClient;
import com.stock.core.search.SolrSearchUtil;
import com.stock.core.service.BaseService;
import com.stock.core.util.DateUtil;
import com.stock.core.util.JsonUtil;

@Service
public class ViolationService extends BaseService {

    /**
     * 接口调用
     */
    @Autowired
    private RestClient restClient;

    
    private String apiBaseUrl = "http://dev-sz.valueonline.cn/capital-cloud-api/";
    
     
    
    /**
     * 违规类型CODE_NO
     */
    public final String VIOLATE_TYPE_TREE_TYPE = "1";
    /**
     * 处罚类型CODE_NO
     */
    public final String PUNISH_TYPE_TREE_TYPE = "2";
    /**
     * 处罚对象身份CODE_NO
     */
    public final String PERSON_IDT_CODE_NO = "PERSON_TYPE";
    /**
     * 申辩情况CODE_NO
     */
    public final String ADOPT_CONDITION_CODE_NO = "ADOPT_CONDITION";
    /**
     * 所属板块CODE_NO
     */
    public final String STOCK_BOARD_CODE_NO = "STOCK_BOARD";

    /**
     * 获取页面初始化需要的各种列表
     *
     * @param queryInfo
     * @return
     */
    public ViolationParamDto getPageInitData() {
        ViolationParamDto dto = new ViolationParamDto();
        dto.setPunishManIdtList(getPunishManIdtList());
        dto.setAverSituationList(getAverSituationList());
        return dto;
    }

    /**
     * 获取收藏页面初始化需要的各种列表
     *
     * @param queryInfo
     * @return
     */
    public ViolationParamDto getFavourPageInitData() {
        ViolationParamDto dto = new ViolationParamDto();
//        dto.setViolationType(JsonUtil.toJsonNoNull(getViolateTypeList()));
        dto.setPunishType(JsonUtil.toJsonNoNull(getPunishTypeList()));
        dto.setAverSituationList(getAverSituationList());
        dto.setPunishManIdtList(getPunishManIdtList());
        return dto;
    }

    /**
     * 获取违规类型列表
     *
     * @param queryInfo
     * @return
     */
    public List<Map<String, Object>> getViolateTypeList() {
        return getTypeList(null, VIOLATE_TYPE_TREE_TYPE);
    }

    /**
     * 获取处罚类型列表
     *
     * @param queryInfo
     * @return
     */
    public List<Map<String, Object>> getPunishTypeList() {
        return getTypeList(null, PUNISH_TYPE_TREE_TYPE);
    }

    /**
     * 获取处罚对象身份
     *
     * @param queryInfo
     * @return
     */
    public List<OptionDto> getPunishManIdtList() {
        return getCodeList(PERSON_IDT_CODE_NO);
    }

    /**
     * 获取申辩情况列表
     *
     * @param queryInfo
     * @return
     */
    public List<OptionDto> getAverSituationList() {
        return getCodeList(ADOPT_CONDITION_CODE_NO);
    }

    /**
     * 获取所属板块
     *
     * @param queryInfo
     * @return
     */
    public List<OptionDto> getStockBoardList() {
        return getCodeList(STOCK_BOARD_CODE_NO);
    }

    /**
     * 获取所属地区列表
     *
     * @param queryInfo
     * @return
     */
    public List<OptionDto> getAreaList() {
        ParameterizedTypeReference<JsonResponse<List<OptionDto>>> responseType = new ParameterizedTypeReference<JsonResponse<List<OptionDto>>>() {
        };
        String url = apiBaseUrl + "declareInfo/postDeclareIndexAreas";
        List<OptionDto> list = restClient.post(url, null, responseType).getResult();
        return list;
    }

    /**
     * 获取CodeList
     *
     * @param queryInfo
     * @return
     */
    public List<OptionDto> getCodeList(String codeNo) {
        ParameterizedTypeReference<JsonResponse<List<OptionDto>>> responseType = new ParameterizedTypeReference<JsonResponse<List<OptionDto>>>() {
        };
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        String url = apiBaseUrl + "violation/getCodeList";
        params.add("codeNo", codeNo);
        List<OptionDto> list = restClient.post(url, params, responseType).getResult();
        return list;
    }

    /**
     * 获取TypeList
     *
     * @param queryInfo
     * @return
     */
    public List<Map<String, Object>> getTypeList(String ids, String treeType) {
        ParameterizedTypeReference<JsonResponse<List<Map<String, Object>>>> responseType = new ParameterizedTypeReference<JsonResponse<List<Map<String, Object>>>>() {
        };
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        String url = apiBaseUrl + "violation/getTypeList";
        params.add("ids", ids);
        params.add("treeType", treeType);
        List<Map<String, Object>> list = restClient.post(url, params, responseType).getResult();
        return list;
    }

    /**
     * 获取处罚机构列表
     *
     * @param queryInfo
     * @return
     */
    public List<Map<String, Object>> getPunishClassList(String ids) {
        ParameterizedTypeReference<JsonResponse<List<Map<String, Object>>>> responseType = new ParameterizedTypeReference<JsonResponse<List<Map<String, Object>>>>() {
        };
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        String url = apiBaseUrl + "violation/getPunishClassList";
        params.add("ids", ids);
        List<Map<String, Object>> list = restClient.post(url, params, responseType).getResult();
        return list;
    }

    /**
     * 检索处罚明细
     *
     * @param queryInfo
     * @return
     */
    public FacetResult<PunishmentSearchDto> searchPunishment(QueryInfo<Map<String, String>> queryInfo) {
        ParameterizedTypeReference<JsonResponse<FacetResult<PunishmentSearchDto>>> responseType = new ParameterizedTypeReference<JsonResponse<FacetResult<PunishmentSearchDto>>>() {
        };
        String url = apiBaseUrl + "violation/ajaxPunishmentIndex";
        FacetResult<PunishmentSearchDto> result = restClient.post(url, queryInfo, responseType).getResult();
        return result;
    }

    /**
     * 检索违规案例
     *
     * @param queryInfo
     * @return
     */
    public FacetResult<ViolationSearchDto> searchViolation(QueryInfo<Map<String, String>> queryInfo) {
        ParameterizedTypeReference<JsonResponse<FacetResult<ViolationSearchDto>>> responseType = new ParameterizedTypeReference<JsonResponse<FacetResult<ViolationSearchDto>>>() {
        };
        String url = apiBaseUrl + "violation/ajaxViolationIndex";
        FacetResult<ViolationSearchDto> result = restClient.post(url, queryInfo, responseType).getResult();
        return result;
    }
 

    /**
     * 收藏违规案例
     *
     * @param queryInfo
     * @return
     */
    public ViolationDetailDto getViolationDetail(String id) {
        ParameterizedTypeReference<JsonResponse<ViolationDetailDto>> responseType = new ParameterizedTypeReference<JsonResponse<ViolationDetailDto>>() {
        };
        String url = apiBaseUrl + "violation/getViolationDetail";
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("id", id);
        ViolationDetailDto result = restClient.post(url, params, responseType).getResult();
        return result;
    }

    /**
     * 获取处罚对象列表（主要用于排序）
     *
     * @param queryInfo
     * @return
     */
    public List<ViolationDetailPersonDto> getDetailPersonList(String id, String orderColumn, String orderByOrder) {
        ParameterizedTypeReference<JsonResponse<List<ViolationDetailPersonDto>>> responseType = new ParameterizedTypeReference<JsonResponse<List<ViolationDetailPersonDto>>>() {
        };
        String url = apiBaseUrl + "violation/getDetailPersonList";
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("id", id);
        params.add("orderColumn", orderColumn);
        params.add("orderByOrder", orderByOrder);
        List<ViolationDetailPersonDto> result = restClient.post(url, params, responseType).getResult();
        return result;
    }

   

    //niuxin bug3959 start 20170518
    /**
     * bug3959特殊需求
     *
     * @return
     * @throws ParseException 
     */
    public String transDateStrToConditionStr(String conditionsStr, String dateStr, String key, String keyString) throws ParseException {
        if (StringUtils.isNotEmpty(dateStr)) {
            String eL = "[0-9]{4}-[0-9]{2}-[0-9]{2} 至 [0-9]{4}-[0-9]{2}-[0-9]{2}";
            Pattern p = Pattern.compile(eL);
            Matcher m = p.matcher(dateStr.trim());
            boolean dateFlag = m.matches();
            if (dateFlag) {
            	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
            	String strList = "";
                String[] dateRange = dateStr.split(" 至 ");
                Date startDate = null;
                Date endDate = null;
                Date startDateM = null;
                Date endDateM = null;
                startDate = DateUtil.getDate(dateRange[0], DateUtil.YYYY_MM_DD);
                endDate = DateUtil.getDate(dateRange[1], DateUtil.YYYY_MM_DD);
				startDateM = sdf.parse(dateRange[0]);
				endDateM = sdf.parse(dateRange[1]);
                Calendar dd = Calendar.getInstance();//定义日期实例               

                dd.setTime(startDateM);//设置日期起始时间

                while(dd.getTime().before(endDateM)){//判断是否到结束日期                
	
	                String str = sdf.format(dd.getTime());
	
	                strList = strList+","+str;
	
	                dd.add(Calendar.MONTH, 1);//进行当前日期月份加1

                }
                String endStr = sdf.format(dd.getTime());
                strList = strList+","+endStr;
                strList = strList.substring(1, strList.length());
                String[] array = strList.split(",");
                String solrDateStr = SolrSearchUtil.parseDateKeyWords(key, startDate, endDate);
                if (StringUtils.isNotEmpty(solrDateStr)) {
                    if (StringUtils.isNotEmpty(conditionsStr)) {
                        conditionsStr = conditionsStr + " AND " + "(" + solrDateStr + " OR " + keyString + ":(\"" + array[0] + "\"";
                    } else {
                        conditionsStr = "(" + solrDateStr + " OR " + keyString + ":(\"" + array[0] + "\"";
                    }
                    for (int i = 1; i < array.length; i++) {
                        conditionsStr = conditionsStr + " OR \"" + array[i] + "\"";
                    }
                    conditionsStr = conditionsStr + ")";
                    conditionsStr += ")";
                }
            }
        }     
        return conditionsStr;
    }   
    //niuxin bug3959 end 20170518
    
    /**
     * 违规统计-违规概况Tab1-Service
     * xing.j 2017/10/20
     * @param queryInfo
     * @return
     */
    public Map<String, String> getViolationOverview(ViolationOverviewDto searchDto) {
        ParameterizedTypeReference<JsonResponse<Map<String, String>>> responseType = new ParameterizedTypeReference<JsonResponse<Map<String, String>>>() {
        };
        String url = apiBaseUrl + "violation/getViolationOverview";
        Map<String, String> queryInfo = getDateMap(searchDto);
        JsonResponse<Map<String, String>> list = restClient.post(url, queryInfo, responseType);
        return list.getResult();
    }
    
    
    /**
     * 
     * @param map
     * @return
     */
    private Map<String, String> getDateMap(ViolationOverviewDto dto) {
        Map<String, String> map = new HashMap<String, String>();
        String dateStart = "";
        String dateEnd = DateUtil.getDateStr(getDBTime(), DateUtil.YYYY_MM_DD);
        if ("0".equals(dto.getCountType())) {
            dateStart = DateUtil.datePlusToStr(DateUtil.datePlusWeeksToDate(getDBTime(), -1), DateUtil.YYYY_MM_DD, 1);
            map.put("countType", dto.getCountType());
        } else if ("1".equals(dto.getCountType())) {
            dateStart = DateUtil.datePlusToStr(DateUtil.datePlusMonthsToDate(getDBTime(), -1), DateUtil.YYYY_MM_DD, 1);
            map.put("countType", dto.getCountType());
        } else if ("2".equals(dto.getCountType())) {
            dateStart = DateUtil.datePlusToStr(DateUtil.datePlusMonthsToDate(getDBTime(), -3), DateUtil.YYYY_MM_DD, 1);
            map.put("countType", dto.getCountType());
        } else if ("3".equals(dto.getCountType())) {
            dateStart = DateUtil.datePlusToStr(DateUtil.datePlusMonthsToDate(getDBTime(), -6), DateUtil.YYYY_MM_DD, 1);
            map.put("countType", dto.getCountType());
        } else if ("4".equals(dto.getCountType())) {
            dateStart = DateUtil.datePlusToStr(DateUtil.datePlusYearToDate(getDBTime(), -1), DateUtil.YYYY_MM_DD, 1);
            map.put("countType", dto.getCountType());
        } else if ("5".equals(dto.getCountType())) {
            dateStart = DateUtil.datePlusToStr(DateUtil.datePlusYearToDate(getDBTime(), -3), DateUtil.YYYY_MM_DD, 1);
            map.put("countType", dto.getCountType());
        } else if ("6".equals(dto.getCountType())) {
            if (StringUtils.isNotEmpty(dto.getDateSelect())) {
                String[] dateRange = dto.getDateSelect().split(" 至 ");
                dateStart = dateRange[0];
                dateEnd = dateRange[1];
                int total = DateUtil.dateCompareDays(DateUtil.getDate(dateStart, DateUtil.YYYY_MM_DD),
                        DateUtil.getDate(dateEnd, DateUtil.YYYY_MM_DD));
                if (total <= 7) {
                    map.put("countType", "0");
                } else if (total <= 90) {
                    map.put("countType", "1");
                } else if (total <= 365) {
                    map.put("countType", "3");
                } else if (total <= 1095) {
                    map.put("countType", "5");
                } else {
                    map.put("countType", "7");
                }
            }
        }

        map.put("dateStart", dateStart);
        map.put("dateEnd", dateEnd);

        map.put("activeTypes", dto.getActiveTypes());
        // 0:违规案例 1:处罚对象数量 2:处罚金额
        map.put("dataSourceType", dto.getDataSourceType());
        return map;
    }
    
   

    /**
     * 
     * excel cell style
     *
     * @param workbook
     * @return blackBorder style
     *
     */
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

    /**
     * 
     * 处理违规类型字符串
     *
     * @param violateTypeVOList
     *            处罚明细违规列表
     * @param violateTypeList
     *            违规列表
     * @return 违规结果
     *
     */
    private String dealViolateType(List<String> violateTypeVOList, List<Map<String, Object>> violateTypeList) {
        StringBuffer typeBuffer = new StringBuffer();
        if (violateTypeVOList != null) {
            Set<String> violateSet = new LinkedHashSet<String>();
            violateSet.addAll(violateTypeVOList);
            for (Map<String, Object> map : violateTypeList) {
                if ("1".equals(String.valueOf(map.get("level")))
                        && violateSet.contains(String.valueOf(map.get("value")))
                        && StringUtils.isNotBlank(String.valueOf(map.get("label")))) {
                    typeBuffer.append(map.get("label")).append(",");
                }
            }
            if (typeBuffer.length() > 0) {
                typeBuffer.deleteCharAt(typeBuffer.lastIndexOf(","));
            }
        }
        return typeBuffer.toString();
    }

    /**
     * 
     * 处理处罚类型字符串
     *
     * @param punishTypeVOList
     *            处罚明细处罚列表
     * @param punishTypeList
     *            处罚列表
     * @return 处罚结果
     *
     */
    private String dealPunishType(List<String> punishTypeVOList, List<Map<String, Object>> punishTypeList) {
        StringBuffer punishBuffer = new StringBuffer();
        if (punishTypeVOList != null) {
            Set<String> punishTypeSet = new LinkedHashSet<String>();
            punishTypeSet.addAll(punishTypeVOList);
            for (Map<String, Object> map : punishTypeList) {
                if ("2".equals(String.valueOf(map.get("level"))) && punishTypeSet.contains(map.get("value"))
                        && StringUtils.isNotBlank(String.valueOf(map.get("label")))) {
                    punishBuffer.append(map.get("label")).append(",");
                }
            }
            if (punishBuffer.length() > 0) {
                punishBuffer.deleteCharAt(punishBuffer.lastIndexOf(","));
            }
        }
        return punishBuffer.toString();
    }
 
 

  
 
}
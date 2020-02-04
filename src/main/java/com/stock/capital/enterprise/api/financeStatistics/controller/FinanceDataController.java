package com.stock.capital.enterprise.api.financeStatistics.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.stock.capital.enterprise.api.financeStatistics.dto.FinanceDataDto;
import com.stock.capital.enterprise.api.financeStatistics.dto.FinanceIndTypeCode;
import com.stock.capital.enterprise.api.financeStatistics.dto.FinanceParamDto;
import com.stock.capital.enterprise.api.financeStatistics.dto.FinanceStatisticsIndexDto;
import com.stock.capital.enterprise.api.financeStatistics.service.FinanceDataService;
import com.stock.capital.enterprise.common.constant.Global;
import com.stock.capital.enterprise.common.service.CommonService;
import com.stock.capital.enterprise.regulatory.service.ViolationService;
import com.stock.core.Constant;
import com.stock.core.controller.BaseController;
import com.stock.core.dto.FacetResult;
import com.stock.core.dto.JsonResponse;
import com.stock.core.dto.Page;
import com.stock.core.dto.QueryInfo;
import com.stock.core.search.SearchClient;
import com.stock.core.util.DateUtil;
import com.stock.core.web.DownloadView;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.*;


@Controller
@RequestMapping("financeStatistics")
public class FinanceDataController extends BaseController {

    @Autowired
    private FinanceDataService financeDataService;

//    @Autowired
//    private SearchServer searchServer;

    @Autowired
    private ViolationService violationService;

    @Autowired
    private CommonService commonService;

    @Autowired
    private CacheManager cacheManager;
    @Autowired
    private SearchClient searchClient;


    /**
     * 取得证券发行图的数据
     *
     * @return response（JSON格式）
     */
    @RequestMapping(value = "getChartForFinanceData")
    @ResponseBody
    public JsonResponse<List<Map<String, Object>>> getResearchStatisticsData(@RequestBody FinanceDataDto dto) {
        Map<String, String> queryInfo = financeDataService.getDateMap(dto);
        JsonResponse<List<Map<String, Object>>> response = new JsonResponse<List<Map<String, Object>>>();
        response.setResult(financeDataService.getResearchStatisticsDataInfo(queryInfo));
        return response;
    }

    /**
     * 取得债权发行图的数据
     *
     * @return response（JSON格式）
     */
    @RequestMapping(value = "/getChartForBondData", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse<List<Map<String, Object>>> getResearchBondData(@RequestBody FinanceDataDto dto) {
        Map<String, String> queryInfo = financeDataService.getDateMap(dto);
        JsonResponse<List<Map<String, Object>>> response = new JsonResponse<List<Map<String, Object>>>();
        response.setResult(financeDataService.getResearchBondDataInfo(queryInfo));
        return response;
    }


    /**
     * 融资查询-索引查询
     *
     * @param queryInfo
     * @return
     */
    @RequestMapping(value = "financeSearchData")
    @ResponseBody
    public JsonResponse<Map<String, Object>> financeSearchApi(@RequestBody QueryInfo<FinanceParamDto> queryInfo) {
//        if (Global.ES_FINANCE_STATISTICS_FLAG.equals("0")) {
            Map<String, Object> condition = new HashMap<>();
        	//改变所属行业对应分类的下拉多选框内容
            
            // 行业分类
            if (StringUtils.isNotEmpty(queryInfo.getCondition().getFinanceIndustry())) {
                List<String> financeIndtypecod = new ArrayList<>();
                String[] type = queryInfo.getCondition().getFinanceIndustry().split(",");
                for (int i = 0; i < type.length; i++) {
                    financeIndtypecod.add(type[i]);
                }
                FinanceIndTypeCode financeIndTypeCodes = new FinanceIndTypeCode();
                financeIndTypeCodes.setFinanceIndTypeCodeKey("finance_indtypecode" + queryInfo.getCondition().getFinanceIndustry() + "_t");
                financeIndTypeCodes.setFinanceIndTypeCodeValue(financeIndtypecod);
                condition.put("financeIndTypeCodes", financeIndTypeCodes);
            }
            //所属行业code
            if (StringUtils.isNotEmpty(queryInfo.getCondition().getIndustrySelect())) {
                List<String> financeIndcode = new ArrayList<>();
                String[] type = queryInfo.getCondition().getIndustrySelect().split(",");
                for (int i = 0; i < type.length; i++) {
                	financeIndcode.add(type[i]);
                }
                FinanceIndTypeCode financeIndCodes = new FinanceIndTypeCode();
                financeIndCodes.setFinanceIndCodeKey("finance_indcode" + queryInfo.getCondition().getFinanceIndustry() + "_t");
                financeIndCodes.setFinanceIndCodeValue(financeIndcode);
                condition.put("financeIndCodes", financeIndCodes);
            }

            condition.put("haveCitycodeFlag",false);
            condition.put("haveBelongplateFlag",false);
            // 所在地区
            if (StringUtils.isNotEmpty(queryInfo.getCondition().getAreaSelect())) {
                List<String> citycode = new ArrayList<>();
                String[] code = queryInfo.getCondition().getAreaSelect().split(",");
                for (int i = 0; i < code.length; i++) {
                    citycode.add(code[i]);
                }
                condition.put("haveCitycodeFlag",true);
                condition.put("finance_citycode_t", citycode);
            }
            // 所属板块
            if (StringUtils.isNotEmpty(queryInfo.getCondition().getStockBoardSelect())) {
                List<String> belongplate = new ArrayList<>();
                String[] plate = queryInfo.getCondition().getStockBoardSelect().split(",");
                for (int i = 0; i < plate.length; i++) {
                    belongplate.add(plate[i]);
                }
                condition.put("haveBelongplateFlag",true);
                condition.put("finance_belongplate_t", belongplate);
            }

            // 公司名称
            if (StringUtils.isNotEmpty(queryInfo.getCondition().getCompanyName())) {
                // conditionsStr += " AND finance_companyname_t:*" + financeParamDto.getCompanyName() + "*";
                List<String> companyname = new ArrayList<>();
                String[] name = queryInfo.getCondition().getCompanyName().split(" ");
                for (int i = 0; i < name.length; i++) {
                    companyname.add(name[i]);
                }
                condition.put("finance_companyname_t", companyname);
                // 高亮字段设定
                condition.put("highLight", "finance_companyname_t");
            }

            // 证券代码
            if (StringUtils.isNotEmpty(queryInfo.getCondition().getSecurityCode())) {
                String financeCode[] = queryInfo.getCondition().getSecurityCode().split(" ");
                List<String> securityCode = new ArrayList<>();
                for (int i = 0; i < financeCode.length; i++) {
                    securityCode.add(financeCode[i]);
                }
                condition.put("finance_securitycode_t", securityCode);
            }

            // 证券简称
            if (StringUtils.isNotEmpty(queryInfo.getCondition().getSecurityShortName())) {
                String financeShortName[] = queryInfo.getCondition().getSecurityShortName().split(" ");
                List<String> securityShort = new ArrayList<>();
                for (int i = 0; i < financeShortName.length; i++) {
                    securityShort.add(financeShortName[i]);
                }
                condition.put("finance_securityshortname_t", securityShort);
            }

            // 融资方式
            if (StringUtils.isNotEmpty(queryInfo.getCondition().getFinancingMode())) {
                String financingMode[] = queryInfo.getCondition().getFinancingMode().split(",");
                List<String> finatype = new ArrayList<>();
                for (int i = 0; i < financingMode.length; i++) {
                    finatype.add(financingMode[i]);
                }
                condition.put("financeFinaTypeT", finatype);
            }

            // 依据日期
            if (queryInfo.getCondition().getFinanceDate() != null && queryInfo.getCondition().getFinanceDate().length > 0) {
                String[] time = queryInfo.getCondition().getFinanceDate();
                String start = DateUtil.datePlusToStr(time[0], DateUtil.YYYY_MM_DD, 0);
                String end = DateUtil.datePlusToStr(time[1], DateUtil.YYYY_MM_DD, 0);
                condition.put("dateStart", start);
                condition.put("dateEnd", end);
            }


            QueryInfo<Map<String, Object>> query = new QueryInfo<Map<String, Object>>();
            query.setCondition(condition);

            // 排序顺序
            String OrderByOrder = "DESC,DESC";
            // 排序顺序
            if ("ascending".equals(queryInfo.getOrderByOrder())) {
                OrderByOrder = "ASC";
            } else if ("descending".equals(queryInfo.getOrderByOrder())) {
                OrderByOrder = "DESC";
            }
            String OrderByName = "finance_startdate_dt,finance_sumfina_d";
            // 排序根据
            if ("companyName".equals(queryInfo.getOrderByName())) {// 公司名称
                //orderName = "finance_companyname_t";
                OrderByName = "finance_companyname_sort_s";
            } else if ("financeDate".equals(queryInfo.getOrderByName())) {// 融资日期
                OrderByName = "finance_startdate_dt";
            } else if ("securityCode".equals(queryInfo.getOrderByName())) {// 证券代码
                //orderName = "finance_securitycode_t";
                OrderByName = "finance_securitycode_sort_s";
            } else if ("securityShortName".equals(queryInfo.getOrderByName())) {// 证券简称
                //orderName = "finance_securityshortname_t";
                OrderByName = "finance_securityshortname_sort_s";
            } else if ("sumFina".equals(queryInfo.getOrderByName())) {// 融资金额
                OrderByName = "finance_sumfina_d";
            }
            query.setOrderByName(OrderByName);
            query.setOrderByOrder(OrderByOrder);
            query.setPageSize(queryInfo.getPageSize());
            query.setStartRow(queryInfo.getStartRow());
            query.setQueryId("com.stock.capital.enterprise.api.financeStatistics.dao.FinanceStatistics.financeSearchData");
            FacetResult<FinanceStatisticsIndexDto> facetResult = searchClient.searchWithFacet(Global.ES_FINANCE_STATISTICS, query, FinanceStatisticsIndexDto.class);

            Page<FinanceStatisticsIndexDto> page = facetResult.getPage();
            int total = 0;
            List<FinanceStatisticsIndexDto> resultList = Lists.newArrayList();
            if (page != null) {
                resultList = page.getData();
                total = page.getTotal();
            }
            Map<String, Object> map = Maps.newHashMap();
            map.put("data", resultList);
            map.put("total", total);
            JsonResponse<Map<String, Object>> response = new JsonResponse<>();
            response.setResult(map);
            return response;
//        } else {
//            Map<String, String> condition = Maps.newHashMap();
//            String conditionsStr = "index_type_t: \"finance\"";
//
//            // 所属行业
//            String financeIndCode = "finance_indtypecode" + queryInfo.getCondition().getFinanceIndustry() + "_t";
//            if (StringUtils.isNotEmpty(queryInfo.getCondition().getFinanceIndustry())) {
//                conditionsStr = SolrSearchUtil.transformValueToString(conditionsStr, queryInfo.getCondition().getFinanceIndustry(),
//                        financeIndCode, false, false, false);
//            }
//
//            // 所在地区
//            conditionsStr = SolrSearchUtil.transArrayStrToConditionStr(conditionsStr, queryInfo.getCondition().getAreaSelect(),
//                    "finance_citycode_t");
//
//            // 所属板块
//            conditionsStr = SolrSearchUtil.transArrayStrToConditionStr(conditionsStr, queryInfo.getCondition().getStockBoardSelect(),
//                    "finance_belongplate_t");
//
//            // 公司名称
//            if (StringUtils.isNotEmpty(queryInfo.getCondition().getCompanyName())) {
//                // conditionsStr += " AND finance_companyname_t:*" + financeParamDto.getCompanyName() + "*";
//                conditionsStr = SolrSearchUtil.transformValueToString(conditionsStr, queryInfo.getCondition().getCompanyName(), " ", "finance_companyname_t", false, false, false);
//                // 高亮字段设定
//                condition.put(Constant.SEARCH_HIGHTLIGHT_FIELD, "finance_companyname_t");
//            }
//
//            // 证券代码
//            if (StringUtils.isNotEmpty(queryInfo.getCondition().getSecurityCode())) {
//                conditionsStr = SolrSearchUtil.transformValueToString(conditionsStr, queryInfo.getCondition().getSecurityCode(), " ", "finance_securitycode_t", false, false, false);
//            }
//
//            // 证券简称
//            if (StringUtils.isNotEmpty(queryInfo.getCondition().getSecurityShortName())) {
//                conditionsStr = SolrSearchUtil.transformValueToString(conditionsStr, queryInfo.getCondition().getSecurityShortName(), " ", "finance_securityshortname_t", false, false, false);
//            }
//
//            // 融资方式
//            conditionsStr = SolrSearchUtil.transArrayStrToConditionStr(conditionsStr, queryInfo.getCondition().getFinancingMode(),
//                    "finance_finatype_t");
//
//            // 依据日期
//            if (queryInfo.getCondition().getFinanceDate() != null && queryInfo.getCondition().getFinanceDate().length > 0) {
//                String[] time = queryInfo.getCondition().getFinanceDate();
//                String startDateStr = new String();
//                for (String timeFlag : time) {
//                    startDateStr = startDateStr + " 至 " + timeFlag;
//                }
//                startDateStr = startDateStr.substring(3);
//                conditionsStr = SolrSearchUtil.transDateStrToConditionStr(conditionsStr, startDateStr, "finance_startdate_dt");
//            }
//
//
//            // 处理关键字的检索条件
//            condition.put(Constant.SEARCH_CONDIATION, conditionsStr);
//
//            QueryInfo<Map<String, String>> query = new QueryInfo<Map<String, String>>();
//            query.setCondition(condition);
//
//            // 排序顺序
//            String orderby = "DESC,DESC";
//            // 排序顺序
//            if ("ascending".equals(queryInfo.getOrderByOrder())) {
//                orderby = "ASC";
//            } else if ("descending".equals(queryInfo.getOrderByOrder())) {
//                orderby = "DESC";
//            }
//            String orderName = "finance_startdate_dt , finance_sumfina_d";
//            // 排序根据
//            if ("companyName".equals(queryInfo.getOrderByName())) {// 公司名称
//                //orderName = "finance_companyname_t";
//                orderName = "finance_companyname_sort_s";
//            } else if ("financeDate".equals(queryInfo.getOrderByName())) {// 融资日期
//                orderName = "finance_startdate_dt";
//            } else if ("securityCode".equals(queryInfo.getOrderByName())) {// 证券代码
//                //orderName = "finance_securitycode_t";
//                orderName = "finance_securitycode_sort_s";
//            } else if ("securityShortName".equals(queryInfo.getOrderByName())) {// 证券简称
//                //orderName = "finance_securityshortname_t";
//                orderName = "finance_securityshortname_sort_s";
//            } else if ("sumFina".equals(queryInfo.getOrderByName())) {// 融资金额
//                orderName = "finance_sumfina_d";
//            }
//            query.setOrderByName(orderName);
//            query.setOrderByOrder(orderby);
//            query.setPageSize(queryInfo.getPageSize());
//            query.setStartRow(queryInfo.getStartRow());
//            FacetResult<FinanceStatisticsIndexDto> facetResult = searchServer.searchWithFacet(
//                    Global.FINANCE_INDEX_NAME, query, FinanceStatisticsIndexDto.class);
//
//            Page<FinanceStatisticsIndexDto> page = facetResult.getPage();
//            int total = 0;
//            List<FinanceStatisticsIndexDto> resultList = Lists.newArrayList();
//            if (page != null) {
//                resultList = page.getData();
//                total = page.getTotal();
//            }
//            Map<String, Object> map = Maps.newHashMap();
//            map.put("data", resultList);
//            map.put("total", total);
//            JsonResponse<Map<String, Object>> response = new JsonResponse<>();
//            response.setResult(map);
//            return response;
//        }
    }

    @RequestMapping(value = "getStockBoardList")
    @ResponseBody
    public Map<String, Object> getStockBoardList() {
        // 设定table返回值
        Map<String, Object> response = Maps.newHashMap();
        response.put("data", violationService.getStockBoardList());
        return response;
    }

    @RequestMapping(value = "getProvincesList")
    @ResponseBody
    public Map<String, Object> getProvincesList() {
        // 设定table返回值
        Map<String, Object> response = Maps.newHashMap();

        response.put("data", commonService.getProvincesList());//JsonUtil.toJsonNoNull(commonService.getProvincesList())

        return response;
    }

    @RequestMapping(value = "getFinanceList")
    @ResponseBody
    public Map<String, Object> getFinanceList() {
        // 设定table返回值
        Map<String, Object> response = Maps.newHashMap();
        Cache cache = cacheManager.getCache(Constant.DEFAULT_CACHE);
        @SuppressWarnings("unchecked")
        List<Map<String, String>> itemsObject = (List<Map<String, String>>) cache.get("Paramch_Name", List.class);
        response.put("data", itemsObject);

        return response;
    }
    
    //行业类别(随行业分类改变下拉选项数据)
    @RequestMapping(value = "getIndustryList")
    @ResponseBody
    public Map<String, Object> getIndustryList(@RequestBody String financeIndustry) {
        // 设定table返回值
        Map<String, Object> response = Maps.newHashMap();
        String regx = "=";
        financeIndustry = financeIndustry.replaceAll(regx," ");
        
        response.put("data", financeDataService.getIndustryList(financeIndustry));

        return response;
    }

    /**
     * 公司详情页查询
     *
     * @param queryInfo
     * @return
     */
    @RequestMapping(value = "searchCompanyDetail")
    @ResponseBody
    public JsonResponse<Map<String, Object>> searchCompanyDetail(@RequestBody QueryInfo<FinanceParamDto> queryInfo) {

        QueryInfo<Map<String, Object>> query = new QueryInfo<Map<String, Object>>();
        queryInfo.getCondition().setPageSize(queryInfo.getPageSize());
        queryInfo.getCondition().setStartRow(queryInfo.getStartRow());
        queryInfo.getCondition().setOrderByName(queryInfo.getOrderByName());
        queryInfo.getCondition().setOrderByOrder(queryInfo.getOrderByOrder());
        query = financeDataService.getQuery(queryInfo.getCondition());
//        if (Global.ES_FINANCE_STATISTICS_FLAG.equals("0")) {
            FacetResult<FinanceStatisticsIndexDto> facetResult = searchClient.searchWithFacet(
                    Global.ES_FINANCE_STATISTICS, query, FinanceStatisticsIndexDto.class);
            Page<FinanceStatisticsIndexDto> page = facetResult.getPage();
            int total = 0;
            List<FinanceStatisticsIndexDto> resultList = Lists.newArrayList();
            if (page != null) {
                resultList = page.getData();
                total = page.getTotal();
            }
            Map<String, Object> map = Maps.newHashMap();
            map.put("data", resultList);
            map.put("total", total);
            JsonResponse<Map<String, Object>> response = new JsonResponse<>();
            response.setResult(map);
            return response;
//        } else {
//            FacetResult<FinanceStatisticsIndexDto> facetResult = searchServer.searchWithFacet(
//                    Global.FINANCE_INDEX_NAME, query, FinanceStatisticsIndexDto.class);
//            Page<FinanceStatisticsIndexDto> page = facetResult.getPage();
//            int total = 0;
//            List<FinanceStatisticsIndexDto> resultList = Lists.newArrayList();
//            if (page != null) {
//                resultList = page.getData();
//                total = page.getTotal();
//            }
//            Map<String, Object> map = Maps.newHashMap();
//            map.put("data", resultList);
//            map.put("total", total);
//            JsonResponse<Map<String, Object>> response = new JsonResponse<>();
//            response.setResult(map);
//            return response;
//        }
    }

    /**
     * 导出Excel
     * <p>
     * param  key
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "exportExcel")
    public ModelAndView exportExcel(@RequestBody FinanceParamDto dto, HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.setView(new DownloadView());
        //拼接查询条件
        QueryInfo<Map<String, Object>> query = new QueryInfo<Map<String, Object>>();
        query = financeDataService.getQuery(dto);
        String timeStr = DateUtil.getDateStr(new Date(), "yyyyMMddHHmmssSSS");
        String filePath = "";
        String statistics = "";
        if ("004".equals(dto.getFinaType()) && "undefined".equals(dto.getTypeFlag())) {//证券发行全部(有融资统计)
            filePath = "/WEB-INF/templates/financeStatistics/companyDetailAllExportModel.xlsx";
            statistics = "1";
        } else {
            filePath = "/WEB-INF/templates/financeStatistics/companyDetailExportModel.xlsx";
        }
        mv.addObject(DownloadView.EXPORT_FILE, financeDataService.exportExcel(query, filePath, dto.getChartType(), dto.getFinanceIndustry(), statistics));
        mv.addObject(DownloadView.EXPORT_FILE_NAME, "公司详情导出" + timeStr + ".xlsx");
        mv.addObject(DownloadView.EXPORT_FILE_TYPE, DownloadView.FILE_TYPE.XLSX);
        response.setHeader("fileName", java.net.URLEncoder.encode("公司详情导出" + timeStr + ".xlsx", "utf-8"));
        return mv;
    }
}

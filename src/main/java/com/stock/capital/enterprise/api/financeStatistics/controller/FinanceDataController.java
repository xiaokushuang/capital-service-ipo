package com.stock.capital.enterprise.api.financeStatistics.controller;

import com.google.common.collect.Maps;
import com.stock.capital.enterprise.api.financeStatistics.dto.FinanceParamDto;
import com.stock.capital.enterprise.api.financeStatistics.dto.FinanceStatisticsIndexDto;
import com.stock.capital.enterprise.api.regulatory.service.ViolationService;
import com.stock.capital.enterprise.common.constant.Global;
import com.stock.capital.enterprise.api.financeStatistics.service.FinanceDataService;
import com.stock.capital.enterprise.common.entity.Code;
import com.stock.capital.enterprise.common.service.CommonService;
import com.stock.capital.enterprise.api.financeStatistics.dto.FinanceDataDto;
import com.stock.capital.enterprise.api.financeStatistics.dto.Param;
import com.stock.core.Constant;
import com.stock.core.controller.BaseController;
import com.stock.core.dto.FacetResult;
import com.stock.core.dto.JsonResponse;
import com.stock.core.dto.Page;
import com.stock.core.dto.QueryInfo;
import com.stock.core.search.SearchServer;
import com.stock.core.search.SolrSearchUtil;
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

import javax.xml.ws.Response;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("financeStatistics")
public class FinanceDataController extends BaseController{

    @Autowired
    private FinanceDataService financeDataService;

    @Autowired
    private SearchServer searchServer;

    @Autowired
    private ViolationService violationService;

    @Autowired
    private CommonService commonService;

    @Autowired
    private CacheManager cacheManager;


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
     *
     */
    @RequestMapping(value = "financeSearchData")
    @ResponseBody
    public JsonResponse<Page<FinanceStatisticsIndexDto>> financeSearchApi(@RequestBody QueryInfo<FinanceParamDto> queryInfo) {
        Map<String, String> condition = Maps.newHashMap();
        String conditionsStr = "index_type_t: \"finance\"";

        // 证券公司代码
        if (StringUtils.isNotEmpty(queryInfo.getCondition().getCompanyCodeSearch())) {
            conditionsStr += "AND finance_securitycode_t:*" + queryInfo.getCondition().getCompanyCodeSearch() + "*";
        }

        // 所属板块
        conditionsStr = SolrSearchUtil.transArrayStrToConditionStr(conditionsStr, queryInfo.getCondition().getStockBoardSelect(),
                "finance_belongplate_t");

        // 所在地区
        conditionsStr = SolrSearchUtil.transArrayStrToConditionStr(conditionsStr, queryInfo.getCondition().getAreaSelect(),
                "finance_citycode_t");

        // 所属行业
        String financeIndCode = "finance_indtypecode" + queryInfo.getCondition().getFinanceIndustry() + "_t";
        if (StringUtils.isNotEmpty(queryInfo.getCondition().getFinanceIndustry())) {
            conditionsStr = SolrSearchUtil.transformValueToString(conditionsStr, queryInfo.getCondition().getFinanceIndustry(),
                    financeIndCode, false, false, false);
        }

        // 依据日期
        conditionsStr = SolrSearchUtil.transDateStrToConditionStr(conditionsStr.toString(),
                queryInfo.getCondition().getFinanceDate(), "finance_startdate_dt");

        // 融资方式
        conditionsStr = SolrSearchUtil.transArrayStrToConditionStr(conditionsStr, queryInfo.getCondition().getFinancingMode(),
                "finance_finatype_t");

        // 处理关键字的检索条件
        condition.put(Constant.SEARCH_CONDIATION, conditionsStr);

        QueryInfo<Map<String, String>> query = commonSearch(condition);
        String orderByName = "finance_startdate_dt";
        String orderByOrder = "desc";
        if (StringUtils.isNotEmpty(queryInfo.getOrderByName())) {
            switch (queryInfo.getOrderByName()) {
                case "financeDate":
                    orderByName = "finance_startdate_dt";
                    break;
                case "securityName":
                    orderByName = "finance_securitycode_t";
                    break;
                case "sumFina":
                    orderByName = "finance_sumfina_d";
                    break;
                default:
                    break;
            }
        }
        if (StringUtils.isNotEmpty(queryInfo.getOrderByOrder())) {
            orderByOrder = queryInfo.getOrderByOrder();
        }
        query.setOrderByName(orderByName);
        query.setOrderByOrder(orderByOrder);
        query.setPageSize(queryInfo.getPageSize());
        query.setStartRow(queryInfo.getStartRow());
        FacetResult<FinanceStatisticsIndexDto> facetResult = searchServer.searchWithFacet(
                Global.FINANCE_INDEX_NAME, query, FinanceStatisticsIndexDto.class);
        Page<FinanceStatisticsIndexDto> page = new Page<>();
        page.setData(facetResult.getPage().getData());
        page.setTotal(facetResult.getPage().getTotal());
        JsonResponse<Page<FinanceStatisticsIndexDto>> response = new JsonResponse<>();
        response.setResult(page);
        return response;
    }

    @RequestMapping(value = "getStockBoardList")
    @ResponseBody
    public Map<String, Object> getStockBoardList(@RequestBody Param params) {
        // 设定table返回值
        Map<String, Object> response = Maps.newHashMap();
        String param = params.getParam();
        response.put("data", violationService.getStockBoardList());
        return response;
    }

    @RequestMapping(value = "getProvincesList")
    @ResponseBody
    public Map<String, Object> getProvincesList() {
        // 设定table返回值
        Map<String, Object> response = Maps.newHashMap();

        response.put("data",commonService.getProvincesList() );//JsonUtil.toJsonNoNull(commonService.getProvincesList())

        return response;
    }

    @RequestMapping(value = "getFinanceList")
    @ResponseBody
    public Map<String, Object> getFinanceList() {
        // 设定table返回值
        Map<String, Object> response = Maps.newHashMap();
        Cache cache =  cacheManager.getCache(Constant.DEFAULT_CACHE);
        @SuppressWarnings("unchecked")
        List<Map<String, String>> itemsObject = (List<Map<String, String>>) cache.get("Paramch_Name", List.class);
        response.put("data", itemsObject);

        return response;
    }
}

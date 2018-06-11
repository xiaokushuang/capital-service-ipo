package com.stock.capital.enterprise.financeStatistics.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.cache.Cache;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.stock.capital.enterprise.common.service.CommonService;
import com.stock.capital.enterprise.declare.service.DeclareService;
import com.stock.capital.enterprise.financeStatistics.dto.FinanceDataDto;
import com.stock.capital.enterprise.financeStatistics.dto.FinanceIndexDto;
import com.stock.capital.enterprise.financeStatistics.dto.FinanceParamDto;
import com.stock.capital.enterprise.financeStatistics.dto.Param;
import com.stock.capital.enterprise.financeStatistics.service.FinaceDataService;
import com.stock.capital.enterprise.regulatory.service.ViolationService;
import com.stock.core.Constant;
import com.stock.core.controller.BaseController;
import com.stock.core.dto.FacetResult;
import com.stock.core.dto.JsonResponse;
import com.stock.core.dto.Page;
import com.stock.core.dto.QueryInfo;
import com.stock.core.search.SolrSearchUtil;
import com.stock.core.util.JsonUtil;

@Controller
@RequestMapping("financeStatistics")
public class FinanceStatisticsController extends BaseController {

    @Autowired
    private FinaceDataService finaceDataService;
    
    @Autowired
    private CommonService commonService; 
 
    @Autowired
    private ViolationService violationService; 
    
    @Autowired
  	private CacheManager cacheManager;

    @Autowired
    private DeclareService declareService;
    /**
     * 接口地址前缀
     */
    @Value("${api.baseUrl}")
    private String apiBaseUrl;

    /**
     * 拿到echart图所需的数据
     * 
     * @author Wang Guili & Gerry
     *
     * @return
     */
    @RequestMapping(value = "getChartForFinanceData")
    @ResponseBody
    public JsonResponse<List<Map<String, Object>>> getChartForFinanceData(@RequestBody FinanceDataDto dto) {
        Map<String, String> map = finaceDataService.getDateMap(dto);
        List<Map<String, Object>> data = finaceDataService.getResearchStatisticsDataForSecurity(map);
        JsonResponse<List<Map<String, Object>>> result = new JsonResponse<List<Map<String, Object>>>();
        result.setResult(data);
        return result;
    }

    /**
     * 拿到echart图所需的数据
     * 
     * @author Wang Guili & Gerry
     *
     * @return
     */
    @RequestMapping(value = "getChartForBondData")
    @ResponseBody
    public JsonResponse<List<Map<String, Object>>> getChartForBondData(@RequestBody FinanceDataDto dto) {
        Map<String, String> map = finaceDataService.getDateMap(dto);
        List<Map<String, Object>> data = finaceDataService.getResearchStatisticsDataForBond(map);
        JsonResponse<List<Map<String, Object>>> result = new JsonResponse<List<Map<String, Object>>>();
        result.setResult(data);
        return result;
    }

    /**
     * 融资统查询
     *
     * @author liulin
     */
    @RequestMapping(value = "financeSearchData")
    @ResponseBody
    public JsonResponse<Page<FinanceIndexDto>> financeSearchData(@RequestBody QueryInfo<FinanceParamDto> queryInfo) {
        Map<String, String> condition = Maps.newHashMap();
        String conditionsStr = "index_type_t: \"finance\"";

        // 证券公司代码
        if (StringUtils.isNotEmpty(queryInfo.getCondition().getCompanyCodeSearch())) {
            conditionsStr += "AND finance_securitycode_t:*" + queryInfo.getCondition().getCompanyCodeSearch() + "*";
            // String companyCode = StringUtils.EMPTY;
            // Pattern pattern = Pattern.compile("\\d+");
            // Matcher matcher = pattern.matcher(financeParamDto.getCompanyCodeSearch());
            // while (matcher.find()) {
            // companyCode = matcher.group(0);
            // }
            //
            // if (StringUtils.isNotEmpty(companyCode)) {
            // conditionsStr += "finance_securitycode_t:*" + companyCode + "*";
            // }
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

//        // 排序顺序
//        String orderby = "DESC,DESC";
//        // 排序顺序
//        if (StringUtils.isNotEmpty(queryInfo.getOrderByOrder())) {
//            orderby = queryInfo.getOrderByOrder();
//        }
//
//        String orderName = "finance_startdate_dt , finance_sumfina_d";
//        // 排序根据
//        if ("1".equals(queryInfo.getOrderByName())) {// 公司代码
//            orderName = "finance_securitycode_t";
//        } else if ("2".equals(queryInfo.getOrderByName())) {// 融资日期
//            orderName = "finance_startdate_dt";
//        } else if ("6".equals(queryInfo.getOrderByName())) {// 融资金额
//            orderName = "finance_sumfina_d";
//        }

        // 处理关键字的检索条件
        condition.put(Constant.SEARCH_CONDIATION, conditionsStr);
        
        QueryInfo<Map<String, String>> query = commonSearch(condition);

        queryInfo.setOrderByName(queryInfo.getOrderByName());
        queryInfo.setOrderByOrder(queryInfo.getOrderByOrder());

        // 全文检索
        FacetResult<FinanceIndexDto> facetResult = finaceDataService.searchFinanceRecordList(query);
        JsonResponse<Page<FinanceIndexDto>> response = new JsonResponse<>();
        response.setResult(facetResult.getPage());

        return response;
    }
    
    
    /**
	 * 根据参数返回所需List
	 * 
	 * 
	 * 
	 */
    @RequestMapping(value = "getStockBoardList")
    @ResponseBody
    public Map<String, Object> getStockBoardList(@RequestBody Param params) {  
    	  // 设定table返回值
        Map<String, Object> response = Maps.newHashMap(); 
    	 String param = params.getParam();
    	 response.put("data", violationService.getStockBoardList()); 
//    	 if(param!=null&&!param.equals("")){
//    		 if(param.equals("stockBoardList")){
//    			// 板块信息
//    			 response.put("data", JsonUtil.toJsonNoNull(violationService.getStockBoardList()));
//    			 return response;
//        	 }else if(param.equals("provincesList")){
//        		// 所在地区 
//        		 response.put("data", JsonUtil.toJsonNoNull(commonService.getProvincesList()));
//    			 return response;
//        	 }else if(param.equals("financeIndustry")){ 
//        		// 证监会行业分类
//        		 Cache cache =  cacheManager.getCache(Constant.DEFAULT_CACHE); 
//		        @SuppressWarnings("unchecked")
//		        List<Map<String, String>> itemsObject = (List<Map<String, String>>) cache.get("Paramch_Name", List.class);
//		        response.put("data", JsonUtil.toJsonNoNull(itemsObject));
//        	 } 
//    	 }else{ 
//    		 return response;
//    	 }
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
    	System.out.println(apiBaseUrl); 
        @SuppressWarnings("unchecked")
        List<Map<String, String>> itemsObject = (List<Map<String, String>>) cache.get("Paramch_Name", List.class);
        response.put("data", itemsObject);
 
    	 return response;
    } 
    
}

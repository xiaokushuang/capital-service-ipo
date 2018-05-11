package com.stock.capital.enterprise.financeStatistics.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.stock.capital.enterprise.financeStatistics.dto.FinanceDataDto;
import com.stock.capital.enterprise.financeStatistics.dto.FinanceIndexDto;
import com.stock.capital.enterprise.financeStatistics.dto.FinanceParamDto;
import com.stock.capital.enterprise.financeStatistics.service.FinaceDataService;
import com.stock.core.Constant;
import com.stock.core.controller.BaseController;
import com.stock.core.dto.FacetResult;
import com.stock.core.dto.JsonResponse;
import com.stock.core.dto.Page;
import com.stock.core.dto.QueryInfo;
import com.stock.core.search.SolrSearchUtil;

@Controller
@RequestMapping("financeStatistics")
public class FinanceStatisticsController extends BaseController {

    @Autowired
    private FinaceDataService finaceDataService;

    @Value("#{app['api.baseUrl']}")
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
    public JsonResponse<List<Map<String, Object>>> getChartForFinanceData(FinanceDataDto dto) {
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
    public JsonResponse<List<Map<String, Object>>> getChartForBondData(FinanceDataDto dto) {
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
    public Map<String, Object> financeSearchData(FinanceParamDto financeParamDto, int draw) {
        String startRow = getRequest().getParameter("start");
        String pageSize = getRequest().getParameter("length");
        String orderColumn = getRequest().getParameter("order[0][column]");
        String orderByOrder = getRequest().getParameter("order[0][dir]");

        Map<String, String> condition = Maps.newHashMap();
        String conditionsStr = "index_type_t: \"finance\"";

        // 证券公司代码
        if (StringUtils.isNotEmpty(financeParamDto.getCompanyCodeSearch())) {
            conditionsStr += "AND finance_securitycode_t:*" + financeParamDto.getCompanyCodeSearch() + "*";
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
        conditionsStr = SolrSearchUtil.transArrayStrToConditionStr(conditionsStr, financeParamDto.getStockBoardSelect(),
                "finance_belongplate_t");

        // 所在地区
        conditionsStr = SolrSearchUtil.transArrayStrToConditionStr(conditionsStr, financeParamDto.getAreaSelect(),
                "finance_citycode_t");

        // 所属行业
        String financeIndCode = "finance_indtypecode" + financeParamDto.getFinanceIndustry() + "_t";
        if (StringUtils.isNotEmpty(financeParamDto.getFinanceIndustry())) {
            conditionsStr = SolrSearchUtil.transformValueToString(conditionsStr, financeParamDto.getFinanceIndustry(),
                    financeIndCode, false, false, false);
        }

        // 依据日期
        conditionsStr = SolrSearchUtil.transDateStrToConditionStr(conditionsStr.toString(),
                financeParamDto.getFinanceDate(), "finance_startdate_dt");

        // 融资方式
        conditionsStr = SolrSearchUtil.transArrayStrToConditionStr(conditionsStr, financeParamDto.getFinancingMode(),
                "finance_finatype_t");

        // 排序顺序
        String orderby = "DESC,DESC";
        // 排序顺序
        if (StringUtils.isNotEmpty(orderByOrder)) {
            orderby = orderByOrder;
        }

        String orderName = "finance_startdate_dt , finance_sumfina_d";
        // 排序根据
        if ("1".equals(orderColumn)) {// 公司代码
            orderName = "finance_securitycode_t";
        } else if ("2".equals(orderColumn)) {// 融资日期
            orderName = "finance_startdate_dt";
        } else if ("6".equals(orderColumn)) {// 融资金额
            orderName = "finance_sumfina_d";
        }

        // 处理关键字的检索条件
        condition.put(Constant.SEARCH_CONDIATION, conditionsStr);

        QueryInfo<Map<String, String>> queryInfo = commonSearch(condition);
        queryInfo.setOrderByName(orderName);
        queryInfo.setOrderByOrder(orderby);
        queryInfo.setPageSize(Integer.parseInt(pageSize));
        queryInfo.setStartRow(Integer.parseInt(startRow));

        // 全文检索
        FacetResult<FinanceIndexDto> facetResult = finaceDataService.searchFinanceRecordList(queryInfo);
        Page<FinanceIndexDto> page = facetResult.getPage();
        List<FinanceIndexDto> resultList = Lists.newArrayList();
        int total = 0;
        if (page != null) {
            resultList = page.getData();
            total = page.getTotal();
        }

        // 设定table返回值
        Map<String, Object> response = Maps.newHashMap();
        response.put("draw", draw);
        response.put("recordsTotal", total);
        response.put("recordsFiltered", total);
        response.put("data", resultList);
        return response;
    }
}

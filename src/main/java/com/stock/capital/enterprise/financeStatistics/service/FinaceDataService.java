package com.stock.capital.enterprise.financeStatistics.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import com.stock.capital.enterprise.financeStatistics.dto.FinanceDataDto;
import com.stock.capital.enterprise.financeStatistics.dto.FinanceIndexDto;
import com.stock.core.dto.FacetResult;
import com.stock.core.dto.JsonResponse;
import com.stock.core.dto.QueryInfo;
import com.stock.core.rest.RestClient;
import com.stock.core.service.BaseService;
import com.stock.core.util.DateUtil;


@Service
public class FinaceDataService extends BaseService {

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
     * 获取echart图数据
     * 
     * @author Wang Guili & Gerry
     * 
     * @return 获取echart图数据
     */
    public List<Map<String, Object>> getResearchStatisticsDataForSecurity(Map<String, String> queryInfo) {
        ParameterizedTypeReference<JsonResponse<List<Map<String, Object>>>> responseType = new ParameterizedTypeReference<JsonResponse<List<Map<String, Object>>>>() {
        };
        JsonResponse<List<Map<String, Object>>> list = restClient.post(apiBaseUrl + "statistics/getResearchStatisticsData",
                queryInfo, responseType);
        return list.getResult();
    }
    
    /**
     * 获取echart图数据
     * 
     * @author Wang Guili & Gerry
     * 
     * @return 获取echart图数据
     */
    public List<Map<String, Object>> getResearchStatisticsDataForBond(Map<String, String> queryInfo) {
        ParameterizedTypeReference<JsonResponse<List<Map<String, Object>>>> responseType = new ParameterizedTypeReference<JsonResponse<List<Map<String, Object>>>>() {
        };
        JsonResponse<List<Map<String, Object>>> list = restClient.post(apiBaseUrl + "statistics/getResearchBondData",
                queryInfo, responseType);
        return list.getResult();
    }

    /**
     * 对日期进行处理
     * 
     * @author Wang Guili
     * 
     * @param map
     * @return map
     * 
     * @category 0 近半年，1 近一年，2 近三年，3 近五年，4 近十年，5 自定义时间段
     */
    public Map<String, String> getDateMap(FinanceDataDto dto) {
        Map<String, String> map = new HashMap<String, String>();
        String dateStart = "";
        String dateEnd = DateUtil.getDateStr(getDBTime(), DateUtil.YYYY_MM_DD);
        if ("0".equals(dto.getCountType())) {
            dateStart = DateUtil.datePlusToStr(DateUtil.datePlusMonthsToDate(getDBTime(), -6), DateUtil.YYYY_MM_DD, 1);
            map.put("countType", dto.getCountType());
        } else if ("1".equals(dto.getCountType())) {
            dateStart = DateUtil.datePlusToStr(DateUtil.datePlusMonthsToDate(getDBTime(), -12), DateUtil.YYYY_MM_DD, 1);
            map.put("countType", dto.getCountType());
        } else if ("2".equals(dto.getCountType())) {
            dateStart = DateUtil.datePlusToStr(DateUtil.datePlusSeasonsToDate(getDBTime(), -12), DateUtil.YYYY_MM_DD, 1);
            map.put("countType", dto.getCountType());
        } else if ("3".equals(dto.getCountType())) {
            dateStart = DateUtil.datePlusToStr(DateUtil.datePlusYearToDate(getDBTime(), -5), DateUtil.YYYY_MM_DD, 1);
            map.put("countType", dto.getCountType());
        } else if ("4".equals(dto.getCountType())) {
            dateStart = DateUtil.datePlusToStr(DateUtil.datePlusYearToDate(getDBTime(), -10), DateUtil.YYYY_MM_DD, 1);
            map.put("countType", dto.getCountType());
        } else if ("5".equals(dto.getCountType())) {
            dateStart = DateUtil.datePlusToStr(DateUtil.datePlusMonthsToDate(getDBTime(), -1), DateUtil.YYYY_MM_DD, 1);
            map.put("countType", dto.getCountType());
        } else if ("6".equals(dto.getCountType())) {
            dateStart = DateUtil.datePlusToStr(DateUtil.datePlusMonthsToDate(getDBTime(), -3), DateUtil.YYYY_MM_DD, 1);
            map.put("countType", dto.getCountType());
        } else if ("7".equals(dto.getCountType())) {
            if (StringUtils.isNotEmpty(dto.getDateSelect())) {
                String[] dateRange = dto.getDateSelect().split(" 至 ");
                dateStart = dateRange[0];
                dateEnd = dateRange[1];
                int total = DateUtil.dateCompareDays(DateUtil.getDate(dateStart, DateUtil.YYYY_MM_DD),
                        DateUtil.getDate(dateEnd, DateUtil.YYYY_MM_DD));
                if (total <= 30) {
                    map.put("countType", "5");
                } else if (total <= 90) {
                    map.put("countType", "6");
                } else if (total <= 182) {
                    map.put("countType", "0");
                } else if (total <= 547) {
                    map.put("countType", "2");
                } else {
                    map.put("countType", "7");
                }
            }
        }

        map.put("dateStart", dateStart);
        map.put("dateEnd", dateEnd);

        map.put("chartType", dto.getChartType());
        map.put("industrySelect", dto.getIndustrySelect());
        map.put("regionalSelect", dto.getRegionalSelect());
        map.put("finaType", dto.getFinaType());
        
        return map;
    }
    
    /**
     * 融资查询(用索引查询)
     * 
     * 
     * @return 融资记录
     */
    public FacetResult<FinanceIndexDto> searchFinanceRecordList(QueryInfo<Map<String, String>> queryInfo) {
        ParameterizedTypeReference<FacetResult<FinanceIndexDto>> responseType = new ParameterizedTypeReference<FacetResult<FinanceIndexDto>>() {
        };
        FacetResult<FinanceIndexDto> facetResult = restClient.post(apiBaseUrl + "statistics/financeSearchApi", queryInfo, responseType);
        return facetResult;
    }
}

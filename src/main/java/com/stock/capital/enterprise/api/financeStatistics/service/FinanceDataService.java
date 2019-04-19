package com.stock.capital.enterprise.api.financeStatistics.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.solr.client.solrj.response.PivotField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.support.ServletContextResource;

import com.google.common.base.Throwables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.stock.capital.enterprise.api.financeStatistics.dto.BondStatisticsIndexDto;
import com.stock.capital.enterprise.api.financeStatistics.dto.FinanceDataDto;
import com.stock.capital.enterprise.api.financeStatistics.dto.FinanceParamDto;
import com.stock.capital.enterprise.api.financeStatistics.dto.FinanceStatisticsIndexDto;
import com.stock.capital.enterprise.common.constant.Global;
import com.stock.capital.enterprise.common.dao.CodeMapper;
import com.stock.capital.enterprise.common.entity.Code;
import com.stock.capital.enterprise.common.entity.CodeExample;
import com.stock.capital.enterprise.common.service.IndexFieldBindService;
import com.stock.core.Constant;
import com.stock.core.dto.FacetResult;
import com.stock.core.dto.Page;
import com.stock.core.dto.QueryInfo;
import com.stock.core.dto.StatsResult;
import com.stock.core.search.SearchServer;
import com.stock.core.search.SolrSearchUtil;
import com.stock.core.service.BaseService;
import com.stock.core.util.DateSplitUtil;
import com.stock.core.util.DateUtil;
import com.stock.core.util.JsonUtil;


@Service
public class FinanceDataService extends BaseService implements ServletContextAware{

    private Logger logger = LoggerFactory.getLogger(FinanceDataService.class);
	
    @Autowired
    private SearchServer searchServer;

    @Autowired
    private CodeMapper codeMapper;
    
    @Autowired
    private IndexFieldBindService indexFieldBindService;
    
    private ServletContext servletContext;
    
    @Override
    public void setServletContext(ServletContext servletContext) {
    this.servletContext = servletContext;
    }
    
    /**
     * 融资统计-证券发行图表数据
     * @author Wang Guili & Gerry
     * @param map
     * @return List
     */
    public List<Map<String, Object>> getResearchStatisticsDataInfo(Map<String, String> map) {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        Map<String, Object> resultNum = new HashMap<String, Object>();
        //List<Map<String, String>> resultTotalNum = new ArrayList<Map<String, String>>();
        List<Map<String, Object>> resultSum = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> resultTotalSum = new ArrayList<Map<String, Object>>();
        // 日期开始时间
        String dateStartStr = map.get("dateStart");
        // 日期结束时间
        String dateEndStr = map.get("dateEnd");
        // 图表类型 0：柱状图 1：扇形图 2地图
        String chartType = map.get("chartType");
        // 查询日期范围 0：半年 1：一年 2：三年 3：五年 4：十年 5：一个月 6：三个月
        String countType = map.get("countType");
        
        String facetField = "";
        String conditionsStr = "";
        
        logger.debug(
                "*******getResearchStatisticsDataInfo*******param ： dateStartStr = {},dateEndStr = {},chartType = {}, countType = {}",
                dateStartStr, dateEndStr, chartType, countType);
        if (StringUtils.isNotEmpty(dateStartStr) && StringUtils.isNotEmpty(dateEndStr)) {
            // 格式化开始结束日期
            Date dateStart = DateUtil.getDate(dateStartStr, DateUtil.YYYY_MM_DD);
            Date dateEnd = DateUtil.getDate(dateEndStr, DateUtil.YYYY_MM_DD);
            // 添加日期查询的条件
            conditionsStr = SolrSearchUtil.parseDateKeyWords("finance_startdate_dt", dateStart, dateEnd);
            conditionsStr = SolrSearchUtil.transformValueToString(conditionsStr, "004", "finance_finatype_t", false, false, true);
            // 各图表数据取得
            if ("1".equals(chartType)) {//柱状图数据
                //按月为单位处理
                //拿到融资类型数据
                String finaType = map.get("finaType");
                if ("0".equals(countType) || "1".equals(countType) || "5".equals(countType) || "6".equals(countType)) {
                    List<Map<String, String>> dateList = DateSplitUtil.getDateSplit(dateStartStr, dateEndStr, DateSplitUtil.SPLIT_MONTH);
                    for (Map<String, String> param : dateList) {
                        Date start = DateUtil.getDate(param.get("startDate"), DateUtil.YYYY_MM_DD);
                        Date end = DateUtil.getDate(param.get("endDate"), DateUtil.YYYY_MM_DD);
                        
                        //统计记录数量，总和
                        conditionsStr = SolrSearchUtil.parseDateKeyWords("finance_startdate_dt", start, end);
                        conditionsStr = SolrSearchUtil.transformValueToString(conditionsStr, "004", "finance_finatype_t", false, false, true);
                        facetField = "finance_finatype_t";
                        String newCon = null;
                        if(StringUtils.isNotEmpty(finaType)){
                            String[] type = finaType.split(",");
                            newCon = SolrSearchUtil.transformArrayToString(conditionsStr, type, "finance_finatype_t", false, false, false);
                            resultNum = searchWithFacetInfo(null, newCon);
                            resultSum = searchWithStatsInfo(facetField, newCon,"1");
                        }else{
                            resultNum = searchWithFacetInfo(facetField, conditionsStr);
                            resultSum = searchWithStatsInfo(facetField, conditionsStr,"1");
                        }
                        
                        Map<String, Object> dataMap = new HashMap<String, Object>();
                        // 日期
                        dataMap.put("date", DateUtil.changeDateFormat(start, DateUtil.YYYY_MM_DD)+"至"+DateUtil.changeDateFormat(end, DateUtil.YYYY_MM_DD));
                        dataMap.put("dataNum", resultNum);
                        dataMap.put("dataSum", resultSum);
                        result.add(dataMap);
                    }
                //按季度为单位处理    
                } else if ("2".equals(countType)) {
                    List<Map<String, String>> dateList = DateSplitUtil.getDateSplit(dateStartStr, dateEndStr, DateSplitUtil.SPLIT_SEASON);
                    for (Map<String, String> param : dateList) {
                        Date start = DateUtil.getDate(param.get("startDate"), DateUtil.YYYY_MM_DD);
                        Date end = DateUtil.getDate(param.get("endDate"), DateUtil.YYYY_MM_DD);
                        
                        //统计记录数量，总和
                        conditionsStr = SolrSearchUtil.parseDateKeyWords("finance_startdate_dt", start, end);
                        conditionsStr = SolrSearchUtil.transformValueToString(conditionsStr, "004", "finance_finatype_t", false, false, true);
                        facetField = "finance_finatype_t";
                        resultNum = searchWithFacetInfo(facetField, conditionsStr);
                        resultSum = searchWithStatsInfo(facetField, conditionsStr,"1");
                        
                        Map<String, Object> dataMap = new HashMap<String, Object>();
                        // 日期
                        dataMap.put("date", DateUtil.changeDateFormat(start, DateUtil.YYYY_MM_DD)+"至"+DateUtil.changeDateFormat(end, DateUtil.YYYY_MM_DD));
                        dataMap.put("dataNum", resultNum);
                        dataMap.put("dataSum", resultSum);
                        result.add(dataMap);
                    }
                //按年为单位处理
                } else if ("3".equals(countType) || "4".equals(countType)) {
                    List<Map<String, String>> dateList = DateSplitUtil.getDateSplit(dateStartStr, dateEndStr, DateSplitUtil.SPLIT_YEARS);
                    for (Map<String, String> param : dateList) {
                        Date start = DateUtil.getDate(param.get("startDate"), DateUtil.YYYY_MM_DD);
                        Date end = DateUtil.getDate(param.get("endDate"), DateUtil.YYYY_MM_DD);

                        //统计记录数量，总和
                        conditionsStr = SolrSearchUtil.parseDateKeyWords("finance_startdate_dt", start, end);
                        conditionsStr = SolrSearchUtil.transformValueToString(conditionsStr, "004", "finance_finatype_t", false, false, true);
                        facetField = "finance_finatype_t";
                        resultNum = searchWithFacetInfo(facetField, conditionsStr);
                        resultSum = searchWithStatsInfo(facetField, conditionsStr,"1");
                        
                        Map<String, Object> dataMap = new HashMap<String, Object>();
                        // 日期
                        dataMap.put("date", DateUtil.changeDateFormat(start, DateUtil.YYYY_MM_DD)+"至"+DateUtil.changeDateFormat(end, DateUtil.YYYY_MM_DD));
                        dataMap.put("dataNum", resultNum);
                        dataMap.put("dataSum", resultSum);
                        result.add(dataMap);
                    }
                } else if ("7".equals(countType)) {
                    List<Map<String, String>> dateList = DateSplitUtil.getDateSplit(dateStartStr, dateEndStr, DateSplitUtil.SPLIT_YEARS);
                    for (Map<String, String> param : dateList) {
                        Date start = DateUtil.getDate(param.get("startDate"), DateUtil.YYYY_MM_DD);
                        Date end = DateUtil.getDate(param.get("endDate"), DateUtil.YYYY_MM_DD);

                        //统计记录数量，总和
                        conditionsStr = SolrSearchUtil.parseDateKeyWords("finance_startdate_dt", start, end);
                        conditionsStr = SolrSearchUtil.transformValueToString(conditionsStr, "004", "finance_finatype_t", false, false, true);
                        facetField = "finance_finatype_t";
                        resultNum = searchWithFacetInfo(facetField, conditionsStr);
                        resultSum = searchWithStatsInfo(facetField, conditionsStr,"1");
                        
                        Map<String, Object> dataMap = new HashMap<String, Object>();
                        // 日期
                        dataMap.put("date", DateUtil.getDateStr(start, DateUtil.YYYY_MM_DD));
                        dataMap.put("dataNum", resultNum);
                        dataMap.put("dataSum", resultSum);
                        result.add(dataMap);
                    }
                }
            } else if ("2".equals(chartType)) {// 取得扇形图
                String industrySelect = map.get("industrySelect");
                String finaType = map.get("finaType");
                //用于扇形图右侧的tab数据的查询条件
                String conditionsStrOther = "";
                String condition = "";
                conditionsStrOther = SolrSearchUtil.transformValueToString(conditionsStr, map.get("industrySelect"), "finance_indtypecode"+industrySelect+"_t", false, false, false);
                conditionsStr = SolrSearchUtil.transformValueToString(conditionsStr, map.get("industrySelect"), "finance_indtypecode"+industrySelect+"_t", false, false, false);
                
                if(finaType != null && !"".equals(finaType)){
                    if(finaType.indexOf(',') < 0){
                        conditionsStr = SolrSearchUtil.transformValueToString(conditionsStr, finaType, "finance_finatype_t", false, false, false);
                    }else{
                        String[] type = finaType.split(",");
                        conditionsStr = SolrSearchUtil.transformArrayToString(conditionsStr, type, "finance_finatype_t", false, false, false);
                    }
                }
                facetField = "finance_pindname"+industrySelect+"_s";
                resultSum = searchWithStatsInfo(facetField, conditionsStr,"1");
                Map<String, Object> dataMap = new HashMap<String, Object>();
                dataMap.put("dataSum", resultSum);        
                result.add(dataMap); 
                
                //得到证券行业分布图右侧列表数据
                //得到全部数据
                resultTotalSum = searchWithStatsInfo(facetField, conditionsStrOther,"1");
                Map<String, Object> rightDataMap = new HashMap<String, Object>();
                rightDataMap.put("dataSum", resultTotalSum);
                result.add(rightDataMap); 
                //IPO 数据
                condition = SolrSearchUtil.transformValueToString(conditionsStrOther, "001", "finance_finatype_t", false, false, false);
                resultTotalSum = searchWithStatsInfo(facetField, condition,"1");
                Map<String, Object> rightDataMapOne = new HashMap<String, Object>();
                rightDataMapOne.put("dataSum", resultTotalSum);
                result.add(rightDataMapOne); 
                //增发 数据
                condition = SolrSearchUtil.transformValueToString(conditionsStrOther, "002", "finance_finatype_t", false, false, false);
                resultTotalSum = searchWithStatsInfo(facetField, condition,"1");
                Map<String, Object> rightDataMapTwo = new HashMap<String, Object>();
                rightDataMapTwo.put("dataSum", resultTotalSum);
                result.add(rightDataMapTwo); 
                //配股 数据
                condition = SolrSearchUtil.transformValueToString(conditionsStrOther, "003", "finance_finatype_t", false, false, false);
                resultTotalSum = searchWithStatsInfo(facetField, condition,"1");
                Map<String, Object> rightDataMapThree = new HashMap<String, Object>();
                rightDataMapThree.put("dataSum", resultTotalSum);
                result.add(rightDataMapThree); 
            } else if ("3".equals(chartType)) {// 取得地图数据
                String conditionsStrOther = "";
                String condition = "";
                String finaType = map.get("finaType");
                conditionsStr = SolrSearchUtil.transformValueToString(conditionsStr, null, null, false, false, false);
                conditionsStrOther = SolrSearchUtil.transformValueToString(conditionsStr, null, null, false, false, false);
                if(finaType != null && !"".equals(finaType)){
                    if(finaType.indexOf(',') < 0){
                        conditionsStr = SolrSearchUtil.transformValueToString(conditionsStr, finaType, "finance_finatype_t", false, false, false);
                    }else{
                        String[] type = finaType.split(",");
                        conditionsStr = SolrSearchUtil.transformArrayToString(conditionsStr, type, "finance_finatype_t", false, false, false);
                    }
                }
                facetField = "finance_cityname_s";
                resultSum = searchWithStatsInfo(facetField, conditionsStr,"1");
                Map<String, Object> dataMap = new HashMap<String, Object>();
                dataMap.put("dataSum", areaString(resultSum));
                result.add(dataMap);
                
                //得到证券行业分布图右侧列表数据
                //得到全部数据
                resultTotalSum = searchWithStatsInfo(facetField, conditionsStrOther,"1");
                Map<String, Object> rightDataMap = new HashMap<String, Object>();
                rightDataMap.put("dataSum", areaString(resultTotalSum));
                result.add(rightDataMap); 
                //IPO 数据
                condition = SolrSearchUtil.transformValueToString(conditionsStrOther, "001", "finance_finatype_t", false, false, false);
                resultTotalSum = searchWithStatsInfo(facetField, condition,"1");
                Map<String, Object> rightDataMapOne = new HashMap<String, Object>();
                rightDataMapOne.put("dataSum", areaString(resultTotalSum));
                result.add(rightDataMapOne); 
                //增发 数据
                condition = SolrSearchUtil.transformValueToString(conditionsStrOther, "002", "finance_finatype_t", false, false, false);
                resultTotalSum = searchWithStatsInfo(facetField, condition,"1");
                Map<String, Object> rightDataMapTwo = new HashMap<String, Object>();
                rightDataMapTwo.put("dataSum", areaString(resultTotalSum));
                result.add(rightDataMapTwo); 
                //配股 数据
                condition = SolrSearchUtil.transformValueToString(conditionsStrOther, "003", "finance_finatype_t", false, false, false);
                resultTotalSum = searchWithStatsInfo(facetField, condition,"1");
                Map<String, Object> rightDataMapThree = new HashMap<String, Object>();
                rightDataMapThree.put("dataSum", areaString(resultTotalSum));
                result.add(rightDataMapThree); 
            }
        }
        logger.debug("*******getResearchStatisticsDataInfo*******DATA ： {}", JsonUtil.toJson(result));
        return result;
    }
    /**
     * 融资统计-债权发行图表数据
     * @author Wang Guili & Gerry
     * @param map
     * @return List
     */
    public List<Map<String, Object>> getResearchBondDataInfo(Map<String, String> map) {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> resultSum = new ArrayList<Map<String, Object>>();
        // 日期开始时间
        String dateStartStr = map.get("dateStart");
        // 日期结束时间
        String dateEndStr = map.get("dateEnd");
        // 图表类型 0：柱状图 1：扇形图 2地图
        String chartType = map.get("chartType");
        // 查询日期范围 0：半年 1：一年 2：三年 3：五年 4：十年 5：一个月 6：三个月
        String countType = map.get("countType");
        
        String facetField = "";
        String conditionsStr = "";
        logger.debug("*******getResearchBondDataInfo*******param ： dateStartStr = {},dateEndStr = {},chartType = {}, countType = {}",
                dateStartStr, dateEndStr, chartType, countType);
        if (StringUtils.isNotEmpty(dateStartStr) && StringUtils.isNotEmpty(dateEndStr)) {
            // 格式化开始结束日期
            Date dateStart = DateUtil.getDate(dateStartStr, DateUtil.YYYY_MM_DD);
            Date dateEnd = DateUtil.getDate(dateEndStr, DateUtil.YYYY_MM_DD);
            // 添加日期查询的条件
            conditionsStr = SolrSearchUtil.parseDateKeyWords("finance_startdate_dt", dateStart, dateEnd);
            conditionsStr = SolrSearchUtil.transformValueToString(conditionsStr, "004", "finance_finatype_t", false, false, false);
            // 各图表数据取得
            if ("1".equals(chartType)) {//柱状图数据
                //按月为单位处理
                if ("0".equals(countType) || "1".equals(countType) || "5".equals(countType) || "6".equals(countType)) {
                    List<Map<String, String>> dateList = DateSplitUtil.getDateSplit(dateStartStr, dateEndStr, DateSplitUtil.SPLIT_MONTH);
                    for (Map<String, String> param : dateList) {
                        Date start = DateUtil.getDate(param.get("startDate"), DateUtil.YYYY_MM_DD);
                        Date end = DateUtil.getDate(param.get("endDate"), DateUtil.YYYY_MM_DD);
                        conditionsStr = SolrSearchUtil.parseDateKeyWords("finance_startdate_dt", start, end);
                        conditionsStr = SolrSearchUtil.transformValueToString(conditionsStr, "004", "finance_finatype_t", false, false, false);
                        facetField = "finance_finatype_t";
                        resultSum = searchWithStatsInfo(facetField, conditionsStr,"2");
                        Map<String, Object> dataMap = new HashMap<String, Object>();
                        // 日期
                        dataMap.put("date", DateUtil.changeDateFormat(start, DateUtil.YYYY_MM_DD)+"至"+DateUtil.changeDateFormat(end, DateUtil.YYYY_MM_DD));
                        dataMap.put("dataSum", resultSum);
                        result.add(dataMap);
                    }
                //按季度为单位处理    
                } else if ("2".equals(countType)) {
                    List<Map<String, String>> dateList = DateSplitUtil.getDateSplit(dateStartStr, dateEndStr, DateSplitUtil.SPLIT_SEASON);
                    for (Map<String, String> param : dateList) {
                        Date start = DateUtil.getDate(param.get("startDate"), DateUtil.YYYY_MM_DD);
                        Date end = DateUtil.getDate(param.get("endDate"), DateUtil.YYYY_MM_DD);
                        conditionsStr = SolrSearchUtil.parseDateKeyWords("finance_startdate_dt", start, end);
                        conditionsStr = SolrSearchUtil.transformValueToString(conditionsStr, "004", "finance_finatype_t", false, false, false);
                        facetField = "finance_finatype_t";
                        resultSum = searchWithStatsInfo(facetField, conditionsStr,"2");                      
                        Map<String, Object> dataMap = new HashMap<String, Object>();
                        dataMap.put("date", DateUtil.changeDateFormat(start, DateUtil.YYYY_MM_DD)+"至"+DateUtil.changeDateFormat(end, DateUtil.YYYY_MM_DD));
                        dataMap.put("dataSum", resultSum);
                        result.add(dataMap);
                    }
                //按年为单位处理
                } else if ("3".equals(countType) || "4".equals(countType)) {
                    List<Map<String, String>> dateList = DateSplitUtil.getDateSplit(dateStartStr, dateEndStr, DateSplitUtil.SPLIT_YEARS);
                    for (Map<String, String> param : dateList) {
                        Date start = DateUtil.getDate(param.get("startDate"), DateUtil.YYYY_MM_DD);
                        Date end = DateUtil.getDate(param.get("endDate"), DateUtil.YYYY_MM_DD);
                        conditionsStr = SolrSearchUtil.parseDateKeyWords("finance_startdate_dt", start, end);
                        conditionsStr = SolrSearchUtil.transformValueToString(conditionsStr, "004", "finance_finatype_t", false, false, false);
                        facetField = "finance_finatype_t";
                        resultSum = searchWithStatsInfo(facetField, conditionsStr,"2"); 
                        Map<String, Object> dataMap = new HashMap<String, Object>();
                        dataMap.put("date", DateUtil.changeDateFormat(start, DateUtil.YYYY_MM_DD)+"至"+DateUtil.changeDateFormat(end, DateUtil.YYYY_MM_DD));
                        dataMap.put("dataSum", resultSum);
                        result.add(dataMap);
                    }
                // 以年为单位处理
                } else if ("5".equals(countType)) {
                    List<Map<String, String>> dateList = DateSplitUtil.getDateSplit(dateStartStr, dateEndStr, DateSplitUtil.SPLIT_SEASON);
                    for (Map<String, String> param : dateList) {
                        Date start = DateUtil.getDate(param.get("startDate"), DateUtil.YYYY_MM_DD);
                        Date end = DateUtil.getDate(param.get("endDate"), DateUtil.YYYY_MM_DD);
                        conditionsStr = SolrSearchUtil.parseDateKeyWords("finance_startdate_dt", start, end);
                        conditionsStr = SolrSearchUtil.transformValueToString(conditionsStr, "004", "finance_finatype_t", false, false, false);
                        facetField = "finance_finatype_t";
                        resultSum = searchWithStatsInfo(facetField, conditionsStr,"2");
                        Map<String, Object> dataMap = new HashMap<String, Object>();
                        dataMap.put("date", DateUtil.changeDateFormat(start, DateUtil.YYYY_MM_DD)+"至"+DateUtil.changeDateFormat(end, DateUtil.YYYY_MM_DD));
                        dataMap.put("dataSum", resultSum);
                        result.add(dataMap);
                    }
                } else if ("7".equals(countType)) {
                    List<Map<String, String>> dateList = DateSplitUtil.getDateSplit(dateStartStr, dateEndStr, DateSplitUtil.SPLIT_YEARS);
                    for (Map<String, String> param : dateList) {
                        Date start = DateUtil.getDate(param.get("startDate"), DateUtil.YYYY_MM_DD);
                        Date end = DateUtil.getDate(param.get("endDate"), DateUtil.YYYY_MM_DD);
                        conditionsStr = SolrSearchUtil.parseDateKeyWords("finance_startdate_dt", start, end);
                        conditionsStr = SolrSearchUtil.transformValueToString(conditionsStr, "004", "finance_finatype_t", false, false, false);
                        facetField = "finance_finatype_t";
                        resultSum = searchWithStatsInfo(facetField, conditionsStr,"2");
                        Map<String, Object> dataMap = new HashMap<String, Object>();
                        dataMap.put("date", DateUtil.changeDateFormat(start, DateUtil.YYYY_MM_DD)+"至"+DateUtil.changeDateFormat(end, DateUtil.YYYY_MM_DD));
                        dataMap.put("dataSum", resultSum);
                        result.add(dataMap);
                    }
                }
            } else if ("2".equals(chartType)) {// 取得扇形图
                String industrySelect = map.get("industrySelect");
                conditionsStr = SolrSearchUtil.transformValueToString(conditionsStr, map.get("industrySelect"), "finance_indtypecode"+industrySelect+"_t", false, false, false);
                facetField = "finance_pindname"+industrySelect+"_s";
                resultSum = searchWithStatsInfo(facetField, conditionsStr,"2");
                Map<String, Object> dataMap = new HashMap<String, Object>();
                dataMap.put("dataSum", resultSum);        
                result.add(dataMap); 
            } else if ("3".equals(chartType)) {// 取得地图数据
                conditionsStr = SolrSearchUtil.transformValueToString(conditionsStr, null, null, false, false, false);
                facetField = "finance_cityname_s";
                resultSum = searchWithStatsInfo(facetField, conditionsStr,"2");
                Map<String, Object> dataMap = new HashMap<String, Object>();
                dataMap.put("dataSum", areaString(resultSum));
                result.add(dataMap); 
            }
        }
        logger.debug("*******getResearchBondDataInfo*******DATA ： {}", JsonUtil.toJson(result));
        return result;
    }
    
    private Map<String, Object> searchWithFacetInfo(String facetField, String conditionsStr) {
        Map<String, Object> result = new HashMap<String, Object>();
        QueryInfo<Map<String, String>> queryInfo = new QueryInfo<Map<String, String>>();

        Map<String, String> condition = Maps.newHashMap();
        // 设置查询条件
        condition.put(Constant.SEARCH_CONDIATION, conditionsStr); 
        // 设置分组字段(发行家数不需要有分组字段)
        /*if(StringUtils.isNotEmpty(facetField)){
            condition.put(Constant.SEARCH_FACET_FIELD, facetField);
        }*/
        queryInfo.setCondition(condition);
        logger.debug("*******search index data*******");
        FacetResult<FinanceStatisticsIndexDto> page = searchServer.searchWithFacet("finance", queryInfo, FinanceStatisticsIndexDto.class);
        
        //List<StatisticsField> field = page.getStatisticsFieldMap().get(facetField);
        //Map<String, String> dataMap = new HashMap<String, String>();
        result.put("number", page.getPage().getTotal());
        /*for (StatisticsField sf : field) {
             dataMap = new HashMap<String, String>();
             dataMap.put("name", sf.getFieldId());
             dataMap.put("value", String.valueOf(sf.getCount()));
            
             result.add(dataMap);
        }   */            
        return result;
    }
    
    private List<Map<String, Object>> searchWithStatsInfo(String facetField, String conditionsStr,String flag) {
        List<Map<String, Object>> tempResult = new ArrayList<Map<String, Object>>();
        QueryInfo<Map<String, String>> queryInfo = new QueryInfo<Map<String, String>>();
        Map<String, String> condition = Maps.newHashMap();
        
        // 设置查询条件
        condition.put(Constant.SEARCH_CONDIATION, conditionsStr);        
        condition.put(Constant.SEARCH_FACET_PIVOT, "{!stats=piv1}" + facetField);
        condition.put(Constant.SEARCH_STATS_PIVOT, "{!tag=piv1 sum=true}finance_sumfina_d");
        
        queryInfo.setCondition(condition);
        logger.debug("*******search index data*******");
        StatsResult<FinanceStatisticsIndexDto> page1 = null;
        StatsResult<BondStatisticsIndexDto> page2 = null;
        List<PivotField> field = null;
        if("1".equals(flag)){
            page1 = searchServer.searchWithStats("finance", queryInfo, FinanceStatisticsIndexDto.class);
            field = page1.getStatisticsFieldMap().get(facetField);
        } else if("2".equals(flag)){
            page2 = searchServer.searchWithStats("finance", queryInfo, BondStatisticsIndexDto.class);
            field = page2.getStatisticsFieldMap().get(facetField);
        }
                
        //List<PivotField> field = page.getStatisticsFieldMap().get(facetField);
        Map<String, Object> dataMap = Maps.newHashMap();
        if(field != null){
            for (PivotField sf : field) {
                dataMap = new HashMap<String, Object>();
                dataMap.put("name", String.valueOf(sf.getValue())); 
                double sum = 0;
                BigDecimal totalSum = new BigDecimal(0);
                try {
                    if("1".equals(flag)){
                        sum = (double) sf.getFieldStatsInfo().get("finance_sumfina_d").getSum();
                        BigDecimal bd = new BigDecimal(sum);
                        totalSum = bd.divide(new BigDecimal(100000000)).setScale(4, BigDecimal.ROUND_HALF_UP);
                        dataMap.put("value", totalSum);
                    }else{
                        sum = (double) sf.getFieldStatsInfo().get("finance_sumfina_d").getSum();
                        BigDecimal bd = new BigDecimal(sum);
                        totalSum = bd.setScale(4, BigDecimal.ROUND_HALF_UP);
                        dataMap.put("value", totalSum);
                    }
                } catch (Exception e) {
                    logger.error("cause by：{}", Throwables.getStackTraceAsString(e));
                } 
                dataMap.put("num", sf.getCount()); 
                dataMap.put("cityName", String.valueOf(sf.getValue())); 
                dataMap.put("condition", conditionsStr); //增加查询条件
                tempResult.add(dataMap);
            }
        }
        return tempResult;
    }
    
    //获取月份
    /*private String getMonthStr(Date date, String format) {
        DateTime d = new DateTime(date);
        return d.toString(format);
    }*/
    //获取季度
    private String getSeason(Date date) {  
        
        String season = null;
        Calendar c = Calendar.getInstance();
        c.setTime(date);  
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);  
        switch (month) {  
        case Calendar.JANUARY:  
        case Calendar.FEBRUARY:  
        case Calendar.MARCH:  
            season = "一";  
            break;  
        case Calendar.APRIL:  
        case Calendar.MAY:  
        case Calendar.JUNE:  
            season = "二";  
            break;  
        case Calendar.JULY:  
        case Calendar.AUGUST:  
        case Calendar.SEPTEMBER:  
            season = "三";  
            break;  
        case Calendar.OCTOBER:  
        case Calendar.NOVEMBER:  
        case Calendar.DECEMBER:  
            season = "四";  
            break;  
        default:  
            break;  
        }  
        return year+"第"+season+"季度";  
    }
    private int getYear(Date date) {  
        Calendar c = Calendar.getInstance();
        c.setTime(date);  
        int year = c.get(Calendar.YEAR);
        return year;  
    }
    private List<Map<String, Object>> areaString(List<Map<String, Object>> area){
        for(Map<String, Object> map: area){
            if(((String)map.get("name")).indexOf("黑龙江") < 0 && ((String)map.get("name")).indexOf("内蒙古")<0){
                map.put("name", ((String)map.get("name")).substring(0, 2));
            }else{
                map.put("name", ((String)map.get("name")).substring(0, 3));
            }
        }
        return area;
    }

    public List<Code> getIndustry(){
        CodeExample example = new CodeExample();
        example.createCriteria().andCodeNoEqualTo("Paramch_Name");
        List<Code> list = codeMapper.selectByExample(example);
        return list;
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
     * 拼接查询公司详情条件
     */
    public QueryInfo<Map<String, String>> getQuery(FinanceParamDto queryInfo) {
        QueryInfo<Map<String, String>> query = new QueryInfo<Map<String, String>>();
        Map<String, String> condition = Maps.newHashMap();
        String conditionsStr = "";
        if("1".equals(queryInfo.getChartType())) {//柱状图
            conditionsStr = "index_type_t: \"finance\"";
            if(StringUtils.isNotEmpty(queryInfo.getSelCondition())) {
                String[] time = queryInfo.getSelCondition().split("至");
                String startDateStr = new String();
                for (String timeFlag : time) {
                    startDateStr = startDateStr + " 至 " + timeFlag;
                }
                startDateStr = startDateStr.substring(3);
                conditionsStr = SolrSearchUtil.transDateStrToConditionStr(conditionsStr,startDateStr, "finance_startdate_dt");
            }
            if("债券发行".equals(queryInfo.getTypeFlag())) {//债券发行
                conditionsStr = SolrSearchUtil.transformValueToString(conditionsStr, queryInfo.getFinaType(), "finance_finatype_t", false, false, false);
            } else {//证券发行
                if("004".equals(queryInfo.getFinaType())) {
                    conditionsStr = SolrSearchUtil.transformValueToString(conditionsStr, "004", "finance_finatype_t", false, false, true);
                } else {
                    conditionsStr = SolrSearchUtil.transformValueToString(conditionsStr, queryInfo.getFinaType(), "finance_finatype_t", false, false, false);
                }
            }
        } else if("2".equals(queryInfo.getChartType())){//饼状图
            conditionsStr = queryInfo.getConditionStr();
            if(!"004".equals(queryInfo.getFinaType())) {
                conditionsStr = SolrSearchUtil.transformValueToString(conditionsStr, queryInfo.getFinaType(), "finance_finatype_t", false, false, false);
            } 
            conditionsStr = SolrSearchUtil.transformValueToString(conditionsStr, queryInfo.getSelCondition(), " ", "finance_pindname"+queryInfo.getFinanceIndustry()+"_s", false, false, false);
        } else if("3".equals(queryInfo.getChartType())){//地图
            conditionsStr = queryInfo.getConditionStr();
            if(!"004".equals(queryInfo.getFinaType())) {
                conditionsStr = SolrSearchUtil.transformValueToString(conditionsStr, queryInfo.getFinaType(), "finance_finatype_t", false, false, false);
            } 
            conditionsStr = SolrSearchUtil.transformValueToString(conditionsStr, queryInfo.getSelCondition(), " ", "finance_cityname_s", false, false, false);
        }

        // 处理关键字的检索条件
        condition.put(Constant.SEARCH_CONDIATION, conditionsStr);
        
        query.setCondition(condition);
        String orderby = "DESC";
        // 排序顺序
        if ("ascending".equals(queryInfo.getOrderByOrder())) {
            orderby = "ASC";
        } else if("descending".equals(queryInfo.getOrderByOrder())) {
            orderby = "DESC";
        }
        String orderName = "finance_startdate_dt";
        // 排序根据
        if ("companyName".equals(queryInfo.getOrderByName())) {// 公司名称
            orderName = "finance_companyname_sort_s";
        } else if ("financeDate".equals(queryInfo.getOrderByName())) {// 上市日期
            orderName = "finance_startdate_dt";
        } else if ("securityCode".equals(queryInfo.getOrderByName())) {// 证券代码
            orderName = "finance_securitycode_sort_s";
        } else if ("securityShortName".equals(queryInfo.getOrderByName())) {// 证券简称
            orderName = "finance_securityshortname_sort_s";
        } else if ("financeIndustry".equals(queryInfo.getOrderByName())) {// 所属行业
            if("2".equals(queryInfo.getChartType())) {
                orderName = "finance_pindcode"+queryInfo.getFinanceIndustry()+"_t";
            } else {
                orderName = "finance_pindcode001_t";
            }
        } else if ("cityName".equals(queryInfo.getOrderByName())) {// 所属地区
            orderName = "finance_citycode_t";
        } else if ("belongPlate".equals(queryInfo.getOrderByName())) {// 所属板块
            orderName = "finance_belongplate_t";
        } else if ("sumFina".equals(queryInfo.getOrderByName())) {// 融资金额
            orderName = "finance_sumfina_d";
        }
        query.setOrderByName(orderName);
        query.setOrderByOrder(orderby);
        query.setPageSize(queryInfo.getPageSize());
        query.setStartRow(queryInfo.getStartRow());
        return query;
    }
    
    /**
     * 导出Excel方法
     * @param strPath
     * @return
     * @throws Exception 
     */
    public InputStream exportExcel(QueryInfo<Map<String, String>> query,String filePath,String chartType, String financeIndustry) throws Exception {
        FacetResult<FinanceStatisticsIndexDto> facetResult = searchServer.searchWithFacet(
                Global.FINANCE_INDEX_NAME, query, FinanceStatisticsIndexDto.class);
        Page<FinanceStatisticsIndexDto> page = facetResult.getPage();
        List<FinanceStatisticsIndexDto> resultList = Lists.newArrayList();
        if(page != null) {
            resultList = page.getData();
        }
        // 设置Excel内容
        Workbook workbook = excelContentSetting(resultList, filePath, chartType, financeIndustry);
        // 写成文件
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        workbook.write(os);
        os.flush();
        os.close();
        return new ByteArrayInputStream(os.toByteArray());
    }
    
    /**
     * 设置Excel内容
     * @param qaLst
     * @param strPath
     * @return
     * @throws Exception
     *
     */
    private Workbook excelContentSetting(List<FinanceStatisticsIndexDto> qaLst, String strPath,String chartType, String financeIndustry) throws Exception {
        Resource resource = new ServletContextResource(this.servletContext, strPath);
        InputStream file = resource.getInputStream();// 源文件流
        Workbook workbook = WorkbookFactory.create(file);
        
         Sheet sheet = null;

         sheet = workbook.getSheetAt(0);
         Row row = null;
         Cell cell = null;
         FinanceStatisticsIndexDto dto = null;
         CellStyle cellStyleWCS = workbook.createCellStyle();
         DataFormat format = workbook.createDataFormat();
         cellStyleWCS.setDataFormat(format.getFormat("@"));
         cellStyleWCS.setWrapText(true);
         cellStyleWCS.setAlignment(HorizontalAlignment.CENTER);
         cellStyleWCS.setVerticalAlignment(VerticalAlignment.CENTER);
         if(qaLst != null){
            // 设置内容
            for (int i = 0; i < qaLst.size(); i++) {
                dto = qaLst.get(i);
                row = sheet.getRow(i+1);
                if(row==null){
                   row = sheet.createRow(i+1);
                 }
                //序号
                cell=row.getCell(0);
                if(cell == null) {
                         cell = row.createCell(0);
                }
                cell.setCellStyle(cellStyleWCS);
                cell.setCellValue(i+1);
              
                // 设置公司名称
                cell = row.getCell(1);
                if (cell == null) {
                    cell = row.createCell(1);
                }
                cell.setCellStyle(cellStyleWCS);
                cell.setCellValue(dto.getCompanyName());
                
                // 上市日期
                cell = row.getCell(2);
                if (cell == null) {
                    cell = row.createCell(2);
                }
                cell.setCellStyle(cellStyleWCS);
                if (dto.getFinanceDate() != null) {
                    cell.setCellValue(DateUtil.getDateStr(dto.getFinanceDate(), DateUtil.YYYY_MM_DD));
                }
              
                //证券代码
                cell = row.getCell(3);
                if (cell == null) {
                    cell = row.createCell(3);
                }
                cell.setCellStyle(cellStyleWCS);
                cell.setCellValue(dto.getSecurityCode());
              
                // 证券简称
                cell = row.getCell(4);
                if (cell == null) {
                    cell = row.createCell(4);
                }
                cell.setCellStyle(cellStyleWCS);
                cell.setCellValue(dto.getSecurityShortName());
                
                // 所属行业
                cell = row.getCell(5);
                if (cell == null) {
                    cell = row.createCell(5);
                }
                cell.setCellStyle(cellStyleWCS);
                if("2".equals(chartType)) {
                    cell.setCellValue(getpIndName(dto,financeIndustry));
                } else {
                    cell.setCellValue(dto.getpIndName001());
                }
                
                // 所属地区
                cell = row.getCell(6);
                if (cell == null) {
                    cell = row.createCell(6);
                }
                cell.setCellStyle(cellStyleWCS);
                cell.setCellValue(dto.getCityName());
                
                // 所属行业
                cell = row.getCell(7);
                if (cell == null) {
                    cell = row.createCell(7);
                }
                cell.setCellStyle(cellStyleWCS);
                String belongPlate = "";
                if("00".equals(dto.getBelongPlate())) {
                    belongPlate = "深圳主板";
                } else if("01".equals(dto.getBelongPlate())) {
                    belongPlate = "深圳中小板";
                } else if("02".equals(dto.getBelongPlate())) {
                    belongPlate = "深圳创业板";
                } else if("04".equals(dto.getBelongPlate())) {
                    belongPlate = "上交所主板";
                } else if("05".equals(dto.getBelongPlate())) {
                    belongPlate = "新三板";
                } else if("07".equals(dto.getBelongPlate())) {
                    belongPlate = "上交所科创板";
                }
                cell.setCellValue(belongPlate);
                
                // 融资金额(亿元)
                cell = row.getCell(8);
                if (cell == null) {
                    cell = row.createCell(8);
                }
                cell.setCellStyle(cellStyleWCS);
                if("004".equals(dto.getFinaType())) {
                    DecimalFormat df = new DecimalFormat("0.0000");
                    cell.setCellValue(df.format(dto.getSumFina()).toString());
                } else {
                    cell.setCellValue(calcRates(dto.getSumFina(),null,null));
                }
            }
        }
        return workbook;
     }
    
    /**
     * 获取行业名称
     */
    public String getpIndName(FinanceStatisticsIndexDto dto, String financeIndustry) {
        String pIndName = "";
        if("001".equals(financeIndustry)) {
            pIndName = dto.getpIndName001();
        } else if("002".equals(financeIndustry)) {
            pIndName = dto.getpIndName002();
        } else if("003".equals(financeIndustry)) {
            pIndName = dto.getpIndName003();
        } else if("004".equals(financeIndustry)) {
            pIndName = dto.getpIndName004();
        } else if("005".equals(financeIndustry)) {
            pIndName = dto.getpIndName005();
        } else if("006".equals(financeIndustry)) {
            pIndName = dto.getpIndName006();
        } else if("008".equals(financeIndustry)) {
            pIndName = dto.getpIndName008();
        } else if("009".equals(financeIndustry)) {
            pIndName = dto.getpIndName009();
        } 
        return pIndName;
    }
    
    /**
     * 
     * 计算比例
     *
     * @param attend 除数
     * @param total 总数
     * @param scale 除法结果小数位数（默认为6）
     * @param power 结果扩大比例（默认2:百分比比例）
     * @return 比例（总数为0时，返回1）
     */
    private String calcRates(Double attend, Integer scale, Integer power) {
        
        // 设置默认值
        if (scale == null) {
            scale = 6;
        }
        if (power == null) {
            power = 2;
        }
        BigDecimal rate = BigDecimal.ZERO;
        BigDecimal totalBd = new BigDecimal(100000000);
        BigDecimal attendBd = doubleBigDecimal(attend);
        if (totalBd != BigDecimal.ZERO) {
            
            BigDecimal divide = attendBd.divide(totalBd, scale, BigDecimal.ROUND_HALF_DOWN);
            rate = divide.setScale(4, BigDecimal.ROUND_HALF_UP);
        }
        
        return rate.toString();
    }
    
    /**
     * Double型转为BigDecimal
     *
     * @param l
     * @return 转换后的BigDecimal，如果NULL场合，返回0
     *
     */
    private BigDecimal doubleBigDecimal(Double l) {
        
        BigDecimal ret = BigDecimal.ZERO;
        
        if (l != null) {
            ret = BigDecimal.valueOf(l);
        }
        
        return ret;
    }
}

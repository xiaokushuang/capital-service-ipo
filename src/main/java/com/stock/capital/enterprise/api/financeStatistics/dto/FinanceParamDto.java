package com.stock.capital.enterprise.api.financeStatistics.dto;

import java.io.Serializable;

public class FinanceParamDto implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 公司代码
     */
    private String companyCodeSearch;
    /**
     * 所属板块
     */
    private String stockBoardSelect;
    /**
     * 所属行业
     */
    private String financeIndustry;
    /**
     * 所在地区
     */
    private String areaSelect;
    /**
     * 融资方式
     */
    private String financingMode;

    /**
     * 日期
     */
    private String[] financeDate;
    
    // 公司名称
    private String companyName;

    // 证券代码
    private String securityCode;

    // 证券简称
    private String securityShortName;
    
    //图的类型：1柱状图
    private String chartType;
    
    //选中的查询条件
    private String selCondition;
    
    //选择的模块:001IPO,002增发，003配股，004全部
    private String finaType;
    
    //索引查询条件
    private String conditionStr;
    
    public String getSelCondition() {
        return selCondition;
    }

    public void setSelCondition(String selCondition) {
        this.selCondition = selCondition;
    }

    public String getConditionStr() {
        return conditionStr;
    }

    public void setConditionStr(String conditionStr) {
        this.conditionStr = conditionStr;
    }

    public String getChartType() {
        return chartType;
    }

    public String getFinaType() {
        return finaType;
    }

    public void setChartType(String chartType) {
        this.chartType = chartType;
    }

    public void setFinaType(String finaType) {
        this.finaType = finaType;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public String getSecurityShortName() {
        return securityShortName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    public void setSecurityShortName(String securityShortName) {
        this.securityShortName = securityShortName;
    }

    public String getCompanyCodeSearch() {
        return companyCodeSearch;
    }

    public void setCompanyCodeSearch(String companyCodeSearch) {
        this.companyCodeSearch = companyCodeSearch;
    }

    public String getStockBoardSelect() {
        return stockBoardSelect;
    }

    public void setStockBoardSelect(String stockBoardSelect) {
        this.stockBoardSelect = stockBoardSelect;
    }

    public String getFinanceIndustry() {
        return financeIndustry;
    }

    public void setFinanceIndustry(String financeIndustry) {
        this.financeIndustry = financeIndustry;
    }

    public String getAreaSelect() {
        return areaSelect;
    }

    public void setAreaSelect(String areaSelect) {
        this.areaSelect = areaSelect;
    }

    public String getFinancingMode() {
        return financingMode;
    }

    public void setFinancingMode(String financingMode) {
        this.financingMode = financingMode;
    }

    public String[] getFinanceDate() {
        return financeDate;
    }

    public void setFinanceDate(String[] financeDate) {
        this.financeDate = financeDate;
    }

}

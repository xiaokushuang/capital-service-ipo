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
    private String financeDate;

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

    public String getFinanceDate() {
        return financeDate;
    }

    public void setFinanceDate(String financeDate) {
        this.financeDate = financeDate;
    }
}

package com.stock.capital.enterprise.financeStatistics.dto;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class FinanceDataDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String countType;
    
    private String chartType;

    private String industrySelect;

    private String regionalSelect;

    private String dataSourceType;

    private String dateSelect;
    
    private String finaType;

    public String getFinaType() {
        return finaType;
    }

    public void setFinaType(String finaType) {
        this.finaType = finaType;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date startDateStart;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date startDateEnd;

    public String getCountType() {
        return countType;
    }

    public void setCountType(String countType) {
        this.countType = countType;
    }

    public Date getStartDateStart() {
        return startDateStart;
    }

    public void setStartDateStart(Date startDateStart) {
        this.startDateStart = startDateStart;
    }

    public Date getStartDateEnd() {
        return startDateEnd;
    }

    public void setStartDateEnd(Date startDateEnd) {
        this.startDateEnd = startDateEnd;
    }

    public String getIndustrySelect() {
        return industrySelect;
    }

    public void setIndustrySelect(String industrySelect) {
        this.industrySelect = industrySelect;
    }

    public String getRegionalSelect() {
        return regionalSelect;
    }

    public void setRegionalSelect(String regionalSelect) {
        this.regionalSelect = regionalSelect;
    }

    public String getDataSourceType() {
        return dataSourceType;
    }

    public void setDataSourceType(String dataSourceType) {
        this.dataSourceType = dataSourceType;
    }

    public String getDateSelect() {
        return dateSelect;
    }

    public void setDateSelect(String dateSelect) {
        this.dateSelect = dateSelect;
    }

    public String getChartType() {
        return chartType;
    }

    public void setChartType(String chartType) {
        this.chartType = chartType;
    }
    
}

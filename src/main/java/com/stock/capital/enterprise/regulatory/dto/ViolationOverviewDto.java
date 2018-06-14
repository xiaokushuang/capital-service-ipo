package com.stock.capital.enterprise.regulatory.dto;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ViolationOverviewDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String countType;

    private String activeTypes;

    private String dataSourceType;

    private String dateSelect;

    private String selectTypeId;

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

    public String getActiveTypes() {
        return activeTypes;
    }

    public void setActiveTypes(String activeTypes) {
        this.activeTypes = activeTypes;
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

    public String getSelectTypeId() {
        return selectTypeId;
    }

    public void setSelectTypeId(String selectTypeId) {
        this.selectTypeId = selectTypeId;
    }

}

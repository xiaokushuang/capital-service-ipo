package com.stock.capital.enterprise.ipoInterfaceH5.dto;

public class IpoH5DetailDto {
    private static final long serialVersionUID = 1L;

    private String id;// 主键
    private String bid;
    private String columnComment;//数据指标
    private String reportDate;
    private String year;
    private String currVal;
    private String plateType;//科创板：0，创业板：1
    private String orgCode;
    private String industryCsrc;//行业
    private String currValAvg;//平均

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getIndustryCsrc() {
        return industryCsrc;
    }

    public void setIndustryCsrc(String industryCsrc) {
        this.industryCsrc = industryCsrc;
    }

    public String getCurrValAvg() {
        return currValAvg;
    }

    public void setCurrValAvg(String currValAvg) {
        this.currValAvg = currValAvg;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCurrVal() {
        return currVal;
    }

    public void setCurrVal(String currVal) {
        this.currVal = currVal;
    }

    public String getPlateType() {
        return plateType;
    }

    public void setPlateType(String plateType) {
        this.plateType = plateType;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }
}

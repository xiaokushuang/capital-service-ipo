package com.stock.capital.enterprise.ipoInterfaceH5.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class IpoFinanceH5Dto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private String year;

    private BigDecimal issueData;

    private BigDecimal kcbData;

    private BigDecimal cybData;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public BigDecimal getIssueData() {
        return issueData;
    }

    public void setIssueData(BigDecimal issueData) {
        this.issueData = issueData;
    }

    public BigDecimal getKcbData() {
        return kcbData;
    }

    public void setKcbData(BigDecimal kcbData) {
        this.kcbData = kcbData;
    }

    public BigDecimal getCybData() {
        return cybData;
    }

    public void setCybData(BigDecimal cybData) {
        this.cybData = cybData;
    }
}

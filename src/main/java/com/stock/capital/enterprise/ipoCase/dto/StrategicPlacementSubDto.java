package com.stock.capital.enterprise.ipoCase.dto;

import org.springframework.format.annotation.NumberFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class StrategicPlacementSubDto implements Serializable {
    private static final long serialVersionUID = 6409269076041221062L;



    private String id;
    private String pid;

    private String strategicInvestorName;
    private String shareholderType;
    @NumberFormat(pattern = "#,###,##0.0000")
    private BigDecimal initialNumberSingle;
    @NumberFormat(pattern = "#,###,##0.0000")
    private BigDecimal initialNumberTenThousand;
    @NumberFormat(pattern = "#,###,##0.0000")
    private BigDecimal allottedNumberSingle;
    @NumberFormat(pattern = "#,###,##0.0000")
    private BigDecimal allottedNumberTenThousand;
    @NumberFormat(pattern = "#,###,##0.0000")
    private BigDecimal allottedAmountSingle;
    @NumberFormat(pattern = "#,###,##0.0000")
    private BigDecimal allottedAmountTenThousand;
    private String salesRestrictionPeriodMonth;

    private Date createTime;
    private String createUser;
    private String updateUser;
    private Date updateTime;

    private BigDecimal radio;



    private String status;

    public BigDecimal getRadio() {
        return radio;
    }

    public void setRadio(BigDecimal radio) {
        this.radio = radio;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getStrategicInvestorName() {
        return strategicInvestorName;
    }

    public void setStrategicInvestorName(String strategicInvestorName) {
        this.strategicInvestorName = strategicInvestorName;
    }

    public String getShareholderType() {
        return shareholderType;
    }

    public void setShareholderType(String shareholderType) {
        this.shareholderType = shareholderType;
    }

    public BigDecimal getInitialNumberSingle() {
        return initialNumberSingle;
    }

    public void setInitialNumberSingle(BigDecimal initialNumberSingle) {
        this.initialNumberSingle = initialNumberSingle;
    }

    public BigDecimal getInitialNumberTenThousand() {
        return initialNumberTenThousand;
    }

    public void setInitialNumberTenThousand(BigDecimal initialNumberTenThousand) {
        this.initialNumberTenThousand = initialNumberTenThousand;
    }

    public BigDecimal getAllottedNumberSingle() {
        return allottedNumberSingle;
    }

    public void setAllottedNumberSingle(BigDecimal allottedNumberSingle) {
        this.allottedNumberSingle = allottedNumberSingle;
    }

    public BigDecimal getAllottedNumberTenThousand() {
        return allottedNumberTenThousand;
    }

    public void setAllottedNumberTenThousand(BigDecimal allottedNumberTenThousand) {
        this.allottedNumberTenThousand = allottedNumberTenThousand;
    }

    public BigDecimal getAllottedAmountSingle() {
        return allottedAmountSingle;
    }

    public void setAllottedAmountSingle(BigDecimal allottedAmountSingle) {
        this.allottedAmountSingle = allottedAmountSingle;
    }

    public BigDecimal getAllottedAmountTenThousand() {
        return allottedAmountTenThousand;
    }

    public void setAllottedAmountTenThousand(BigDecimal allottedAmountTenThousand) {
        this.allottedAmountTenThousand = allottedAmountTenThousand;
    }

    public String getSalesRestrictionPeriodMonth() {
        return salesRestrictionPeriodMonth;
    }

    public void setSalesRestrictionPeriodMonth(String salesRestrictionPeriodMonth) {
        this.salesRestrictionPeriodMonth = salesRestrictionPeriodMonth;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

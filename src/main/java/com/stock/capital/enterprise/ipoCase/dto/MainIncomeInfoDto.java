package com.stock.capital.enterprise.ipoCase.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * MainIncomeInfoDto.
 *
 * @author user.
 * @version 0.0.1.
 * @serial 2019/1/31 : base version.
 */
public class MainIncomeInfoDto implements Serializable {

    private static final long serialVersionUID = -581073570344781762L;

    @ApiModelProperty("主键")
    private String id;

    @ApiModelProperty("案例id")
    private String bid;

    @ApiModelProperty("主营业务名称")
    private String businessName;

    @ApiModelProperty("第一年金额")
    private BigDecimal firstYearAmount;

    @ApiModelProperty("第一年占比")
    private BigDecimal firstYearRatio;

    @ApiModelProperty("第二年金额")
    private BigDecimal secondYearAmount;

    @ApiModelProperty("第二年占比")
    private BigDecimal secondYearRatio;

    @ApiModelProperty("第三年金额")
    private BigDecimal thirdYearAmount;

    @ApiModelProperty("第三年占比")
    private BigDecimal thirdYearRatio;

    @ApiModelProperty("一期金额")
    private BigDecimal onePeriodAmount;

    @ApiModelProperty("一期占比")
    private BigDecimal onePeriodRatio;

    @ApiModelProperty("报告期终止时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date reportPeriod;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public BigDecimal getFirstYearAmount() {
        return firstYearAmount;
    }

    public void setFirstYearAmount(BigDecimal firstYearAmount) {
        this.firstYearAmount = firstYearAmount;
    }

    public BigDecimal getFirstYearRatio() {
        return firstYearRatio;
    }

    public void setFirstYearRatio(BigDecimal firstYearRatio) {
        this.firstYearRatio = firstYearRatio;
    }

    public BigDecimal getSecondYearAmount() {
        return secondYearAmount;
    }

    public void setSecondYearAmount(BigDecimal secondYearAmount) {
        this.secondYearAmount = secondYearAmount;
    }

    public BigDecimal getSecondYearRatio() {
        return secondYearRatio;
    }

    public void setSecondYearRatio(BigDecimal secondYearRatio) {
        this.secondYearRatio = secondYearRatio;
    }

    public BigDecimal getThirdYearAmount() {
        return thirdYearAmount;
    }

    public void setThirdYearAmount(BigDecimal thirdYearAmount) {
        this.thirdYearAmount = thirdYearAmount;
    }

    public BigDecimal getThirdYearRatio() {
        return thirdYearRatio;
    }

    public void setThirdYearRatio(BigDecimal thirdYearRatio) {
        this.thirdYearRatio = thirdYearRatio;
    }

    public BigDecimal getOnePeriodAmount() {
        return onePeriodAmount;
    }

    public void setOnePeriodAmount(BigDecimal onePeriodAmount) {
        this.onePeriodAmount = onePeriodAmount;
    }

    public BigDecimal getOnePeriodRatio() {
        return onePeriodRatio;
    }

    public void setOnePeriodRatio(BigDecimal onePeriodRatio) {
        this.onePeriodRatio = onePeriodRatio;
    }

    public Date getReportPeriod() {
        return reportPeriod;
    }

    public void setReportPeriod(Date reportPeriod) {
        this.reportPeriod = reportPeriod;
    }
}

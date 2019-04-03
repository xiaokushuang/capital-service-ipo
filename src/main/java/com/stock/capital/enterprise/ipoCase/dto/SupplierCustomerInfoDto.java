package com.stock.capital.enterprise.ipoCase.dto;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * SupplierCustomerInfoDto.
 *
 * @author chenzhengxiu.
 * @version 0.0.1.
 * @serial 2019/3/19 : base version.
 */
public class SupplierCustomerInfoDto implements Serializable {

    private static final long serialVersionUID = -1294360425031170599L;

    @ApiModelProperty("主键")
    private String id;

    @ApiModelProperty("主表id")
    private String bid;

    @ApiModelProperty("公司代码")
    private String companyCode;

    @ApiModelProperty("证券简称")
    private String companyName;

    @ApiModelProperty("第一年内容")
    private String firstYearContent;

    @ApiModelProperty("第一年金额")
    private BigDecimal firstYearAmount;

    @ApiModelProperty("第一年占比")
    private BigDecimal firstYearRatio;

    @ApiModelProperty("第二年内容")
    private String secondYearContent;

    @ApiModelProperty("第二年金额")
    private BigDecimal secondYearAmount;

    @ApiModelProperty("第二年占比")
    private BigDecimal secondYearRatio;

    @ApiModelProperty("第三年内容")
    private String thirdYearContent;

    @ApiModelProperty("第三年金额")
    private BigDecimal thirdYearAmount;

    @ApiModelProperty("第三年占比")
    private BigDecimal thirdYearRatio;

    @ApiModelProperty("一期内容")
    private String onePeriodContent;

    @ApiModelProperty("一期金额")
    private BigDecimal onePeriodAmount;

    @ApiModelProperty("一期占比")
    private BigDecimal onePeriodRatio;

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

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getFirstYearContent() {
        return firstYearContent;
    }

    public void setFirstYearContent(String firstYearContent) {
        this.firstYearContent = firstYearContent;
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

    public String getSecondYearContent() {
        return secondYearContent;
    }

    public void setSecondYearContent(String secondYearContent) {
        this.secondYearContent = secondYearContent;
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

    public String getThirdYearContent() {
        return thirdYearContent;
    }

    public void setThirdYearContent(String thirdYearContent) {
        this.thirdYearContent = thirdYearContent;
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

    public String getOnePeriodContent() {
        return onePeriodContent;
    }

    public void setOnePeriodContent(String onePeriodContent) {
        this.onePeriodContent = onePeriodContent;
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
}

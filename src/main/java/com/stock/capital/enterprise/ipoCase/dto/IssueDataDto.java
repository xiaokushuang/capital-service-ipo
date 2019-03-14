package com.stock.capital.enterprise.ipoCase.dto;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * IssueDataDto.
 *
 * @author chenzhengxiu.
 * @version 0.0.1.
 * @serial 2019/3/12 : base version.
 */
public class IssueDataDto implements Serializable {

    private static final long serialVersionUID = 8941670707763464963L;

    @ApiModelProperty("每股面值")
    private BigDecimal parValue;

    @ApiModelProperty("发行价格")
    private BigDecimal issuePrice;

    @ApiModelProperty("发行数量")
    private BigDecimal shareIssued;

    @ApiModelProperty("占发行后总股本比例")
    private BigDecimal issuedRatio;

    @ApiModelProperty("募集资金总额")
    private BigDecimal sumFina;

    @ApiModelProperty("募集资金净额")
    private BigDecimal netSumFina;

    @ApiModelProperty("老股转让数量(股)")
    private BigDecimal exseNumBse;

    @ApiModelProperty("发行方式")
    private String issueMethod;

    @ApiModelProperty("发行前每股收益(全面摊薄)")
    private BigDecimal epsIssueB;

    @ApiModelProperty("发行后每股收益(全面摊薄)")
    private BigDecimal epsIssueA;

    @ApiModelProperty("发行后市盈率")
    private BigDecimal peIssueA;

    @ApiModelProperty("网上发行数量")
    private BigDecimal shareIssueOn;

    @ApiModelProperty("网下配售数量")
    private BigDecimal sharePlaceOff;

    @ApiModelProperty("承销方式")
    private String uwMethod;

    public BigDecimal getParValue() {
        return parValue;
    }

    public void setParValue(BigDecimal parValue) {
        this.parValue = parValue;
    }

    public BigDecimal getIssuePrice() {
        return issuePrice;
    }

    public void setIssuePrice(BigDecimal issuePrice) {
        this.issuePrice = issuePrice;
    }

    public BigDecimal getShareIssued() {
        return shareIssued;
    }

    public void setShareIssued(BigDecimal shareIssued) {
        this.shareIssued = shareIssued;
    }

    public BigDecimal getIssuedRatio() {
        return issuedRatio;
    }

    public void setIssuedRatio(BigDecimal issuedRatio) {
        this.issuedRatio = issuedRatio;
    }

    public BigDecimal getSumFina() {
        return sumFina;
    }

    public void setSumFina(BigDecimal sumFina) {
        this.sumFina = sumFina;
    }

    public BigDecimal getNetSumFina() {
        return netSumFina;
    }

    public void setNetSumFina(BigDecimal netSumFina) {
        this.netSumFina = netSumFina;
    }

    public BigDecimal getExseNumBse() {
        return exseNumBse;
    }

    public void setExseNumBse(BigDecimal exseNumBse) {
        this.exseNumBse = exseNumBse;
    }

    public String getIssueMethod() {
        return issueMethod;
    }

    public void setIssueMethod(String issueMethod) {
        this.issueMethod = issueMethod;
    }

    public BigDecimal getEpsIssueB() {
        return epsIssueB;
    }

    public void setEpsIssueB(BigDecimal epsIssueB) {
        this.epsIssueB = epsIssueB;
    }

    public BigDecimal getEpsIssueA() {
        return epsIssueA;
    }

    public void setEpsIssueA(BigDecimal epsIssueA) {
        this.epsIssueA = epsIssueA;
    }

    public BigDecimal getPeIssueA() {
        return peIssueA;
    }

    public void setPeIssueA(BigDecimal peIssueA) {
        this.peIssueA = peIssueA;
    }

    public BigDecimal getShareIssueOn() {
        return shareIssueOn;
    }

    public void setShareIssueOn(BigDecimal shareIssueOn) {
        this.shareIssueOn = shareIssueOn;
    }

    public BigDecimal getSharePlaceOff() {
        return sharePlaceOff;
    }

    public void setSharePlaceOff(BigDecimal sharePlaceOff) {
        this.sharePlaceOff = sharePlaceOff;
    }

    public String getUwMethod() {
        return uwMethod;
    }

    public void setUwMethod(String uwMethod) {
        this.uwMethod = uwMethod;
    }
}

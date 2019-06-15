package com.stock.capital.enterprise.ipoInterfaceH5.dto;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;

public class IpoH5IssueDataDto implements Serializable {

  @ApiModelProperty("发行价格")
  private BigDecimal issuePrice;

  @ApiModelProperty("发行后市盈率")
  private BigDecimal peIssueA;

  @ApiModelProperty("发行数量")
  private BigDecimal shareIssued;

  @ApiModelProperty("占发行后总股本比例")
  private BigDecimal issuedRatio;

  @ApiModelProperty("募集资金净额")
  private BigDecimal netSumFina;

  public BigDecimal getIssuePrice() {
    return issuePrice;
  }

  public void setIssuePrice(BigDecimal issuePrice) {
    this.issuePrice = issuePrice;
  }

  public BigDecimal getPeIssueA() {
    return peIssueA;
  }

  public void setPeIssueA(BigDecimal peIssueA) {
    this.peIssueA = peIssueA;
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

  public BigDecimal getNetSumFina() {
    return netSumFina;
  }

  public void setNetSumFina(BigDecimal netSumFina) {
    this.netSumFina = netSumFina;
  }
}

package com.stock.capital.enterprise.ipoCase.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;

@ApiModel("专利情况")
public class IpoTechnologyPatentDto implements Serializable {
  private static final long serialVersionUID = 1L;

  @ApiModelProperty("专利名称")
  private String labelName;

  @ApiModelProperty("发明专利")
  private BigDecimal fm;

  @ApiModelProperty("实用新型专利")
  private BigDecimal sy;

  @ApiModelProperty("外观设计专利")
  private BigDecimal wg ;

  @ApiModelProperty("国外专利")
  private BigDecimal gw;

  @ApiModelProperty("合计")
  private BigDecimal hj;

  public String getLabelName() {
    return labelName;
  }

  public void setLabelName(String labelName) {
    this.labelName = labelName;
  }

  public BigDecimal getFm() {
    return fm;
  }

  public void setFm(BigDecimal fm) {
    this.fm = fm;
  }

  public BigDecimal getSy() {
    return sy;
  }

  public void setSy(BigDecimal sy) {
    this.sy = sy;
  }

  public BigDecimal getWg() {
    return wg;
  }

  public void setWg(BigDecimal wg) {
    this.wg = wg;
  }

  public BigDecimal getGw() {
    return gw;
  }

  public void setGw(BigDecimal gw) {
    this.gw = gw;
  }

  public BigDecimal getHj() {
    return hj;
  }

  public void setHj(BigDecimal hj) {
    this.hj = hj;
  }
}

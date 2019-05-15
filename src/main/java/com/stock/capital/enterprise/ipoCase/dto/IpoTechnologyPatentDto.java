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
  @ApiModelProperty("合计")
  private BigDecimal hj;
  @ApiModelProperty("占比")
  private BigDecimal zb;

  public String getLabelName() {
    return labelName;
  }

  public void setLabelName(String labelName) {
    this.labelName = labelName;
  }

  public BigDecimal getFm() {
    if (fm == null){
      return fm;
    }
    return fm.stripTrailingZeros();
  }

  public void setFm(BigDecimal fm) {
    this.fm = fm;
  }

  public BigDecimal getSy() {
    if (sy == null){
      return sy;
    }
    return sy.stripTrailingZeros();
  }

  public void setSy(BigDecimal sy) {
    this.sy = sy;
  }

  public BigDecimal getWg() {
    if (wg == null){
      return wg;
    }
    return wg.stripTrailingZeros();
  }

  public void setWg(BigDecimal wg) {
    this.wg = wg;
  }

  public BigDecimal getHj() {
    if (hj == null){
      return hj;
    }
    return hj.stripTrailingZeros();
  }

  public void setHj(BigDecimal hj) {
    this.hj = hj;
  }

  public BigDecimal getZb() {
    if (zb == null){
      return zb;
    }
    return zb.stripTrailingZeros();
  }

  public void setZb(BigDecimal zb) {
    this.zb = zb;
  }
}

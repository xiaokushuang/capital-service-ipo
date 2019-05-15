package com.stock.capital.enterprise.ipoCase.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;

@ApiModel("研发投入和核心技术及技术人员")
public class IpoTechnologyTableDto implements Serializable {
  private static final long serialVersionUID = 1L;


  @ApiModelProperty("项目名称")
  private String labelName;
  @ApiModelProperty("第一年值")
  private BigDecimal firstYearValue;
  @ApiModelProperty("第二年值")
  private BigDecimal secondYearValue;
  @ApiModelProperty("第三年值")
  private BigDecimal thirdYearValue ;
  @ApiModelProperty("第四年值")
  private BigDecimal forthYearValue;
  @ApiModelProperty("合计")
  private BigDecimal allNumValue;

  @ApiModelProperty("第一年占比")
  private BigDecimal firstYearPro;
  @ApiModelProperty("第二年占比")
  private BigDecimal secondYearPro;
  @ApiModelProperty("第三年占比")
  private BigDecimal thirdYearPro ;
  @ApiModelProperty("第四年占比")
  private BigDecimal forthYearPro;

  public String getLabelName() {
    return labelName;
  }

  public void setLabelName(String labelName) {
    this.labelName = labelName;
  }

  public BigDecimal getFirstYearValue() {
    if (firstYearValue == null){
      return firstYearValue;
    }
    return firstYearValue.stripTrailingZeros();
  }

  public void setFirstYearValue(BigDecimal firstYearValue) {
    this.firstYearValue = firstYearValue;
  }

  public BigDecimal getSecondYearValue() {
    if (secondYearValue == null){
      return secondYearValue;
    }
    return secondYearValue.stripTrailingZeros();

  }

  public void setSecondYearValue(BigDecimal secondYearValue) {
    this.secondYearValue = secondYearValue;
  }

  public BigDecimal getThirdYearValue() {

    if (thirdYearValue == null){
      return thirdYearValue;
    }
    return thirdYearValue.stripTrailingZeros();

  }

  public void setThirdYearValue(BigDecimal thirdYearValue) {
    this.thirdYearValue = thirdYearValue;
  }

  public BigDecimal getForthYearValue() {
    if (forthYearValue == null){
      return forthYearValue;
    }
    return forthYearValue.stripTrailingZeros();

  }

  public void setForthYearValue(BigDecimal forthYearValue) {
    this.forthYearValue = forthYearValue;
  }

  public BigDecimal getAllNumValue() {
    if (allNumValue == null){
      return allNumValue;
    }
    return allNumValue.stripTrailingZeros();
  }

  public void setAllNumValue(BigDecimal allNumValue) {
    this.allNumValue = allNumValue;
  }

  public BigDecimal getFirstYearPro() {
    if (firstYearPro == null){
      return firstYearPro;
    }
    return firstYearPro.stripTrailingZeros();
  }

  public void setFirstYearPro(BigDecimal firstYearPro) {
    this.firstYearPro = firstYearPro;
  }

  public BigDecimal getSecondYearPro() {
    if (secondYearPro == null){
      return secondYearPro;
    }
    return secondYearPro.stripTrailingZeros();
  }

  public void setSecondYearPro(BigDecimal secondYearPro) {
    this.secondYearPro = secondYearPro;
  }

  public BigDecimal getThirdYearPro() {
    if (thirdYearPro == null){
      return thirdYearPro;
    }
    return thirdYearPro.stripTrailingZeros();
  }

  public void setThirdYearPro(BigDecimal thirdYearPro) {
    this.thirdYearPro = thirdYearPro;
  }

  public BigDecimal getForthYearPro() {
    if (forthYearPro == null){
      return forthYearPro;
    }
    return forthYearPro.stripTrailingZeros();
  }

  public void setForthYearPro(BigDecimal forthYearPro) {
    this.forthYearPro = forthYearPro;
  }
}

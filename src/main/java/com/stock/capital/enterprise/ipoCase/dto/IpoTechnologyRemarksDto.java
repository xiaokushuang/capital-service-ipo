package com.stock.capital.enterprise.ipoCase.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("科技创新备注")
public class IpoTechnologyRemarksDto {

  private static final long serialVersionUID = 1L;

  @ApiModelProperty("研发投入备注")
  private String devRemarks;

  @ApiModelProperty("专利情况备注")
  private String patentRemarks;

  @ApiModelProperty("核心技术及研发技术人员备注")
  private String coreRemarks;

  public String getDevRemarks() {
    return devRemarks;
  }

  public void setDevRemarks(String devRemarks) {
    this.devRemarks = devRemarks;
  }

  public String getPatentRemarks() {
    return patentRemarks;
  }

  public void setPatentRemarks(String patentRemarks) {
    this.patentRemarks = patentRemarks;
  }

  public String getCoreRemarks() {
    return coreRemarks;
  }

  public void setCoreRemarks(String coreRemarks) {
    this.coreRemarks = coreRemarks;
  }
}

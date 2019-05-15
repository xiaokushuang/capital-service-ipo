package com.stock.capital.enterprise.ipoCase.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.List;

@ApiModel("科技创新")
public class IpoTechnologyVo implements Serializable {

  private static final long serialVersionUID = 1L;

  @ApiModelProperty("专利情况")
  private List<IpoTechnologyPatentDto> patentData;

  @ApiModelProperty("研发投入")
  private List<IpoTechnologyTableDto> devData;

  @ApiModelProperty("核心技术")
  private List<IpoTechnologyTableDto> coreData;

  @ApiModelProperty("研发投入时间")
  private IpoTechnologyDateDto devDate;

  @ApiModelProperty("核心技术及研发技术人员时间")
  private IpoTechnologyDateDto coreDate;

  @ApiModelProperty("科技创新备注")
  private IpoTechnologyRemarksDto remarksData;

  public List<IpoTechnologyPatentDto> getPatentData() {
    return patentData;
  }

  public void setPatentData(
      List<IpoTechnologyPatentDto> patentData) {
    this.patentData = patentData;
  }

  public List<IpoTechnologyTableDto> getDevData() {
    return devData;
  }

  public void setDevData(
      List<IpoTechnologyTableDto> devData) {
    this.devData = devData;
  }

  public List<IpoTechnologyTableDto> getCoreData() {
    return coreData;
  }

  public void setCoreData(
      List<IpoTechnologyTableDto> coreData) {
    this.coreData = coreData;
  }

  public IpoTechnologyDateDto getDevDate() {
    return devDate;
  }

  public void setDevDate(IpoTechnologyDateDto devDate) {
    this.devDate = devDate;
  }

  public IpoTechnologyDateDto getCoreDate() {
    return coreDate;
  }

  public void setCoreDate(IpoTechnologyDateDto coreDate) {
    this.coreDate = coreDate;
  }

  public IpoTechnologyRemarksDto getRemarksData() {
    return remarksData;
  }

  public void setRemarksData(IpoTechnologyRemarksDto remarksData) {
    this.remarksData = remarksData;
  }
}

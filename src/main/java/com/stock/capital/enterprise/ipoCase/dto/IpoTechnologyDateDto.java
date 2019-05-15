package com.stock.capital.enterprise.ipoCase.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

@ApiModel("表头时间")
public class IpoTechnologyDateDto implements Serializable {

  private static final long serialVersionUID = 1L;

  @ApiModelProperty("时间名称")
  private String dateName;

  @ApiModelProperty("第一列时间")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private Date firstYearDate;

  @ApiModelProperty("第二列时间")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private Date secondYearDate;

  @ApiModelProperty("第三列时间")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private Date thirdYearDate;

  @ApiModelProperty("第四列时间")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private Date forthYearDate;

  public Date getFirstYearDate() {
    return firstYearDate;
  }

  public void setFirstYearDate(Date firstYearDate) {
    this.firstYearDate = firstYearDate;
  }

  public Date getSecondYearDate() {
    return secondYearDate;
  }

  public void setSecondYearDate(Date secondYearDate) {
    this.secondYearDate = secondYearDate;
  }

  public Date getThirdYearDate() {
    return thirdYearDate;
  }

  public void setThirdYearDate(Date thirdYearDate) {
    this.thirdYearDate = thirdYearDate;
  }

  public Date getForthYearDate() {
    return forthYearDate;
  }

  public void setForthYearDate(Date forthYearDate) {
    this.forthYearDate = forthYearDate;
  }

  public String getDateName() {
    return dateName;
  }

  public void setDateName(String dateName) {
    this.dateName = dateName;
  }
}

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
  private String firstYearDate;

  @ApiModelProperty("第二列时间")
  private String secondYearDate;

  @ApiModelProperty("第三列时间")
  private String thirdYearDate;

  @ApiModelProperty("第四列时间")
  private String forthYearDate;

  public String getFirstYearDate() {
    return firstYearDate;
  }

  public void setFirstYearDate(String firstYearDate) {
    this.firstYearDate = firstYearDate;
  }

  public String getSecondYearDate() {
    return secondYearDate;
  }

  public void setSecondYearDate(String secondYearDate) {
    this.secondYearDate = secondYearDate;
  }

  public String getThirdYearDate() {
    return thirdYearDate;
  }

  public void setThirdYearDate(String thirdYearDate) {
    this.thirdYearDate = thirdYearDate;
  }

  public String getForthYearDate() {
    return forthYearDate;
  }

  public void setForthYearDate(String forthYearDate) {
    this.forthYearDate = forthYearDate;
  }

  public String getDateName() {
    return dateName;
  }

  public void setDateName(String dateName) {
    this.dateName = dateName;
  }
}

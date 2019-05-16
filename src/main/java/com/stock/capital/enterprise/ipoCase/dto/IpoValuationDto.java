package com.stock.capital.enterprise.ipoCase.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

@ApiModel("IPO最近一次估值情况")
public class IpoValuationDto implements Serializable {

  private static final long serialVersionUID = 1L;

  @ApiModelProperty("估值类型")
  private String valuationType;

  @ApiModelProperty("估值日期")
//  @DateTimeFormat(pattern = "yyyy-MM-dd")
//  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private String valuationDate;

  @ApiModelProperty("股价")
  private BigDecimal valuationPrice;

  @ApiModelProperty("股本")
  private BigDecimal valuationEquity;

  @ApiModelProperty("市值")
  private BigDecimal valuationValue;

  @ApiModelProperty("估值详情")
  private String valuationMemo;

  public String getValuationType() {
    return valuationType;
  }

  public void setValuationType(String valuationType) {
    this.valuationType = valuationType;
  }

  public String getValuationDate() {
    return valuationDate;
  }

  public void setValuationDate(String valuationDate) {
    this.valuationDate = valuationDate;
  }

  public BigDecimal getValuationPrice() {
    return valuationPrice;
  }

  public void setValuationPrice(BigDecimal valuationPrice) {
    this.valuationPrice = valuationPrice;
  }

  public BigDecimal getValuationEquity() {
    return valuationEquity;
  }

  public void setValuationEquity(BigDecimal valuationEquity) {
    this.valuationEquity = valuationEquity;
  }

  public BigDecimal getValuationValue() {
    return valuationValue;
  }

  public void setValuationValue(BigDecimal valuationValue) {
    this.valuationValue = valuationValue;
  }

  public String getValuationMemo() {
    return valuationMemo;
  }

  public void setValuationMemo(String valuationMemo) {
    this.valuationMemo = valuationMemo;
  }
}

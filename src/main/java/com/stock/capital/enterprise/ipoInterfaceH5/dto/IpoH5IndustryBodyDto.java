package com.stock.capital.enterprise.ipoInterfaceH5.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class IpoH5IndustryBodyDto implements Serializable {

  private String year;

  private BigDecimal company;

  private BigDecimal lowLevel;

  private BigDecimal highLevel;


  public String getYear() {
    return year;
  }

  public void setYear(String year) {
    this.year = year;
  }

  public BigDecimal getCompany() {
    return company;
  }

  public void setCompany(BigDecimal company) {
    this.company = company;
  }

  public BigDecimal getLowLevel() {
    return lowLevel;
  }

  public void setLowLevel(BigDecimal lowLevel) {
    this.lowLevel = lowLevel;
  }

  public BigDecimal getHighLevel() {
    return highLevel;
  }

  public void setHighLevel(BigDecimal highLevel) {
    this.highLevel = highLevel;
  }
}

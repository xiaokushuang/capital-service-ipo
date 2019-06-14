package com.stock.capital.enterprise.ipoInterfaceH5.dto;

import java.io.Serializable;

public class IpoH5TechnologyDevDto implements Serializable {


  private String year;// 年度

  private String company;//公司研发费用

  private String STIB; //科创版平均研发费用

  private String proportion; //公司研发占比

  private String industryProportion;//科创板平均研发占比


  public String getYear() {
    return year;
  }

  public void setYear(String year) {
    this.year = year;
  }

  public String getCompany() {
    return company;
  }

  public void setCompany(String company) {
    this.company = company;
  }

  public String getSTIB() {
    return STIB;
  }

  public void setSTIB(String STIB) {
    this.STIB = STIB;
  }

  public String getProportion() {
    return proportion;
  }

  public void setProportion(String proportion) {
    this.proportion = proportion;
  }

  public String getIndustryProportion() {
    return industryProportion;
  }

  public void setIndustryProportion(String industryProportion) {
    this.industryProportion = industryProportion;
  }
}

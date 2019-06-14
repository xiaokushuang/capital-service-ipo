package com.stock.capital.enterprise.ipoInterfaceH5.dto;

import java.io.Serializable;
import java.util.List;

public class IpoH5IndustryTitleDto implements Serializable {


    private String title;

    private List<String> companys;

    private List<String> years;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public List<String> getCompanys() {
    return companys;
  }

  public void setCompanys(List<String> companys) {
    this.companys = companys;
  }

  public List<String> getYears() {
    return years;
  }

  public void setYears(List<String> years) {
    this.years = years;
  }
}

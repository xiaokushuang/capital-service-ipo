package com.stock.capital.enterprise.ipoInterfaceH5.dto;

import java.io.Serializable;

public class IpoH5CoreDevDto implements Serializable {

  private String core;

  private String dev;

  private String people;

  private String indexDate;

  public String getCore() {
    return core;
  }

  public void setCore(String core) {
    this.core = core;
  }

  public String getDev() {
    return dev;
  }

  public void setDev(String dev) {
    this.dev = dev;
  }

  public String getPeople() {
    return people;
  }

  public void setPeople(String people) {
    this.people = people;
  }

  public String getIndexDate() {
    return indexDate;
  }

  public void setIndexDate(String indexDate) {
    this.indexDate = indexDate;
  }
}

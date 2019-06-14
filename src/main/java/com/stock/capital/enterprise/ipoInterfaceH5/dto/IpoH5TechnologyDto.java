package com.stock.capital.enterprise.ipoInterfaceH5.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class IpoH5TechnologyDto implements Serializable {

  private Map<String, List<IpoH5TechnologyDevDto>> devData;//研发投入营业收入

  private List<Map<String, IpoH5CoreDevDto>> coreData; // 核心以及研发人员

  public Map<String, List<IpoH5TechnologyDevDto>> getDevData() {
    return devData;
  }

  public void setDevData(
      Map<String, List<IpoH5TechnologyDevDto>> devData) {
    this.devData = devData;
  }

  public List<Map<String, IpoH5CoreDevDto>> getCoreData() {
    return coreData;
  }

  public void setCoreData(
    List<Map<String, IpoH5CoreDevDto>> coreData) {
    this.coreData = coreData;
  }
}

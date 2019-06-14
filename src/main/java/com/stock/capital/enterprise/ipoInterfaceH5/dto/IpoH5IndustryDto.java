package com.stock.capital.enterprise.ipoInterfaceH5.dto;

import java.io.Serializable;
import java.util.List;

public class IpoH5IndustryDto implements Serializable {

  private List<IpoH5IndustryTitleDto> titles;

  private List<List<IpoH5IndustryBodyDto>> body;

  public List<IpoH5IndustryTitleDto> getTitles() {
    return titles;
  }

  public void setTitles(
      List<IpoH5IndustryTitleDto> titles) {
    this.titles = titles;
  }

  public List<List<IpoH5IndustryBodyDto>> getBody() {
    return body;
  }

  public void setBody(
      List<List<IpoH5IndustryBodyDto>> body) {
    this.body = body;
  }
}

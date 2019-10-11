package com.stock.capital.enterprise.ipoCoachCase.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class OtherMarketInfoDto implements Serializable{

  private static final long serialVersionUID = 8297134563989644198L;

  private String id;

  private String bid;

  private String marketType;

  private String companyCode;

  private String companyName;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private Date listTime;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private Date delistTime;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getBid() {
    return bid;
  }

  public void setBid(String bid) {
    this.bid = bid;
  }

  public String getMarketType() {
    return marketType;
  }

  public void setMarketType(String marketType) {
    this.marketType = marketType;
  }

  public String getCompanyCode() {
    return companyCode;
  }

  public void setCompanyCode(String companyCode) {
    this.companyCode = companyCode;
  }

  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public Date getListTime() {
    return listTime;
  }

  public void setListTime(Date listTime) {
    this.listTime = listTime;
  }

  public Date getDelistTime() {
    return delistTime;
  }

  public void setDelistTime(Date delistTime) {
    this.delistTime = delistTime;
  }
}

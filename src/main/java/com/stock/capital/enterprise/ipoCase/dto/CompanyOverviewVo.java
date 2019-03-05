package com.stock.capital.enterprise.ipoCase.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * CompanyOverviewVo.
 *
 * @author chenzhengxiu.
 * @version 0.0.1.
 * @serial 2019/3/5 : base version.
 */
public class CompanyOverviewVo implements Serializable{

  private static final long serialVersionUID = -1251014733325978558L;

  private String companyZhName;// 公司名称

  private String ipoPlate; //上市板块

  private String industryCsrc; //所属行业(证监会)

  private String companyName;// 证券简称

  private String companyCode;// 证券代码

  private String address;// 注册地

  private BigDecimal registeredAssets; //注册资本（万元）

  private String actualController; //实际控制人

  private String controlShareholder; //控股股东

  private String companyNature;//企业性质

  private String majorBusinesses; // 主营业务

  public String getCompanyZhName() {
    return companyZhName;
  }

  public void setCompanyZhName(String companyZhName) {
    this.companyZhName = companyZhName;
  }

  public String getIpoPlate() {
    return ipoPlate;
  }

  public void setIpoPlate(String ipoPlate) {
    this.ipoPlate = ipoPlate;
  }

  public String getIndustryCsrc() {
    return industryCsrc;
  }

  public void setIndustryCsrc(String industryCsrc) {
    this.industryCsrc = industryCsrc;
  }

  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public String getCompanyCode() {
    return companyCode;
  }

  public void setCompanyCode(String companyCode) {
    this.companyCode = companyCode;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public BigDecimal getRegisteredAssets() {
    return registeredAssets;
  }

  public void setRegisteredAssets(BigDecimal registeredAssets) {
    this.registeredAssets = registeredAssets;
  }

  public String getActualController() {
    return actualController;
  }

  public void setActualController(String actualController) {
    this.actualController = actualController;
  }

  public String getControlShareholder() {
    return controlShareholder;
  }

  public void setControlShareholder(String controlShareholder) {
    this.controlShareholder = controlShareholder;
  }

  public String getCompanyNature() {
    return companyNature;
  }

  public void setCompanyNature(String companyNature) {
    this.companyNature = companyNature;
  }

  public String getMajorBusinesses() {
    return majorBusinesses;
  }

  public void setMajorBusinesses(String majorBusinesses) {
    this.majorBusinesses = majorBusinesses;
  }
}

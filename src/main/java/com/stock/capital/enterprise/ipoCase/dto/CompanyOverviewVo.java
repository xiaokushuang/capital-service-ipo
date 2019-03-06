package com.stock.capital.enterprise.ipoCase.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * CompanyOverviewVo.
 *
 * @author chenzhengxiu.
 * @version 0.0.1.
 * @serial 2019/3/5 : base version.
 */
@ApiModel("IPO公司概览返回类")
public class CompanyOverviewVo implements Serializable {

    private static final long serialVersionUID = -1251014733325978558L;

    @ApiModelProperty("案例id")
    private String id;// 案例id

    @ApiModelProperty("公司名称")
    private String companyZhName;// 公司名称

    @ApiModelProperty("上市板块")
    private String ipoPlate; //上市板块

    @ApiModelProperty("所属行业(证监会)")
    private String industryCsrc; //所属行业(证监会)

    @ApiModelProperty("证券简称")
    private String companyName;// 证券简称

    @ApiModelProperty("证券代码")
    private String companyCode;// 证券代码

    @ApiModelProperty("注册地（省）")
    private String addrProv;// 注册地（省）

    @ApiModelProperty("注册地（市）")
    private String addrCity;// 注册地（市）

    @ApiModelProperty("注册地（区）")
    private String addrArea;// 注册地（区）

    @ApiModelProperty("注册资本（万元）")
    private BigDecimal registeredAssets; //注册资本（万元）

    @ApiModelProperty("实际控制人")
    private String actualController; //实际控制人

    @ApiModelProperty("控股股东")
    private String controlShareholder; //控股股东

    @ApiModelProperty("企业性质")
    private String companyNature;//企业性质

    @ApiModelProperty("主营业务")
    private String majorBusinesses; // 主营业务

    @ApiModelProperty("其他资本市场情况")
    private List<OtherMarketInfoDto> otherMarketInfoList; //其他资本市场情况

    @ApiModelProperty("股权结构标签")
    private String structureLabel; //股权结构标签

    @ApiModelProperty("股权结构图")
    private String structureUrl; //股权结构图

    @ApiModelProperty("股东列表")
    private List<IpoPersonInfoDto> shareHolderList; //股东列表

    @ApiModelProperty("主营业务收入-一期")
    private String onePeriodForIncome;//主营业务收入-一期

    @ApiModelProperty("主营业务收入-第三年")
    private String thirdYearForIncome;//主营业务收入-第三年

    @ApiModelProperty("主营业务收入-第二年")
    private String secondYearForIncome;//主营业务收入-第二年

    @ApiModelProperty("主营业务收入-第一年")
    private String firstYearForIncome;//主营业务收入-第一年

    @ApiModelProperty("主营业务收入构成列表")
    private List<MainIncomeInfoDto> mainIncomeInfoList; //主营业务收入构成

    @ApiModelProperty("主要竞争对手列表")
    private List<MainCompetitorInfoDto> mainCompetitorInfoList; //主要竞争对手

    @ApiModelProperty("主要供应商-第一年")
    private String firstYearForSupplier;

    @ApiModelProperty("主要供应商-第二年")
    private String secondYearForSupplier;

    @ApiModelProperty("主要供应商-第三年")
    private String thirdYearForSupplier;

    @ApiModelProperty("主要供应商-一期")
    private String onePeriodForSupplier;

    @ApiModelProperty("报告期主要供应商情况列表")
    private List<SupplierCustomerInfoDto> supplierInfoList; //报告期主要供应商情况

    @ApiModelProperty("主要客户-第一年")
    private String firstYearForCustomer;

    @ApiModelProperty("主要客户-第二年")
    private String secondYearForCustomer;

    @ApiModelProperty("主要客户-第三年")
    private String thirdYearForCustomer;

    @ApiModelProperty("主要客户-一期")
    private String onePeriodForCustomer;

    @ApiModelProperty("报告期主要客户情况列表")
    private List<SupplierCustomerInfoDto> customerInfoList; //报告期主要客户情况

    @ApiModelProperty("募集资金运用列表")
    private List<IpoInvestItemDto> investItemList; //募集资金运用

    @ApiModelProperty("中介机构列表")
    private List<IntermediaryOrgDto> intermediaryOrgList; //中介机构

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getAddrProv() {
        return addrProv;
    }

    public void setAddrProv(String addrProv) {
        this.addrProv = addrProv;
    }

    public String getAddrCity() {
        return addrCity;
    }

    public void setAddrCity(String addrCity) {
        this.addrCity = addrCity;
    }

    public String getAddrArea() {
        return addrArea;
    }

    public void setAddrArea(String addrArea) {
        this.addrArea = addrArea;
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

    public List<OtherMarketInfoDto> getOtherMarketInfoList() {
        return otherMarketInfoList;
    }

    public void setOtherMarketInfoList(
        List<OtherMarketInfoDto> otherMarketInfoList) {
        this.otherMarketInfoList = otherMarketInfoList;
    }

    public String getStructureLabel() {
        return structureLabel;
    }

    public void setStructureLabel(String structureLabel) {
        this.structureLabel = structureLabel;
    }

    public String getStructureUrl() {
        return structureUrl;
    }

    public void setStructureUrl(String structureUrl) {
        this.structureUrl = structureUrl;
    }

    public List<IpoPersonInfoDto> getShareHolderList() {
        return shareHolderList;
    }

    public void setShareHolderList(
        List<IpoPersonInfoDto> shareHolderList) {
        this.shareHolderList = shareHolderList;
    }

    public String getOnePeriodForIncome() {
        return onePeriodForIncome;
    }

    public void setOnePeriodForIncome(String onePeriodForIncome) {
        this.onePeriodForIncome = onePeriodForIncome;
    }

    public String getThirdYearForIncome() {
        return thirdYearForIncome;
    }

    public void setThirdYearForIncome(String thirdYearForIncome) {
        this.thirdYearForIncome = thirdYearForIncome;
    }

    public String getSecondYearForIncome() {
        return secondYearForIncome;
    }

    public void setSecondYearForIncome(String secondYearForIncome) {
        this.secondYearForIncome = secondYearForIncome;
    }

    public String getFirstYearForIncome() {
        return firstYearForIncome;
    }

    public void setFirstYearForIncome(String firstYearForIncome) {
        this.firstYearForIncome = firstYearForIncome;
    }

    public List<MainIncomeInfoDto> getMainIncomeInfoList() {
        return mainIncomeInfoList;
    }

    public void setMainIncomeInfoList(
        List<MainIncomeInfoDto> mainIncomeInfoList) {
        this.mainIncomeInfoList = mainIncomeInfoList;
    }

    public List<MainCompetitorInfoDto> getMainCompetitorInfoList() {
        return mainCompetitorInfoList;
    }

    public void setMainCompetitorInfoList(
        List<MainCompetitorInfoDto> mainCompetitorInfoList) {
        this.mainCompetitorInfoList = mainCompetitorInfoList;
    }

    public String getFirstYearForSupplier() {
        return firstYearForSupplier;
    }

    public void setFirstYearForSupplier(String firstYearForSupplier) {
        this.firstYearForSupplier = firstYearForSupplier;
    }

    public String getSecondYearForSupplier() {
        return secondYearForSupplier;
    }

    public void setSecondYearForSupplier(String secondYearForSupplier) {
        this.secondYearForSupplier = secondYearForSupplier;
    }

    public String getThirdYearForSupplier() {
        return thirdYearForSupplier;
    }

    public void setThirdYearForSupplier(String thirdYearForSupplier) {
        this.thirdYearForSupplier = thirdYearForSupplier;
    }

    public String getOnePeriodForSupplier() {
        return onePeriodForSupplier;
    }

    public void setOnePeriodForSupplier(String onePeriodForSupplier) {
        this.onePeriodForSupplier = onePeriodForSupplier;
    }

    public List<SupplierCustomerInfoDto> getSupplierInfoList() {
        return supplierInfoList;
    }

    public void setSupplierInfoList(
        List<SupplierCustomerInfoDto> supplierInfoList) {
        this.supplierInfoList = supplierInfoList;
    }

    public String getFirstYearForCustomer() {
        return firstYearForCustomer;
    }

    public void setFirstYearForCustomer(String firstYearForCustomer) {
        this.firstYearForCustomer = firstYearForCustomer;
    }

    public String getSecondYearForCustomer() {
        return secondYearForCustomer;
    }

    public void setSecondYearForCustomer(String secondYearForCustomer) {
        this.secondYearForCustomer = secondYearForCustomer;
    }

    public String getThirdYearForCustomer() {
        return thirdYearForCustomer;
    }

    public void setThirdYearForCustomer(String thirdYearForCustomer) {
        this.thirdYearForCustomer = thirdYearForCustomer;
    }

    public String getOnePeriodForCustomer() {
        return onePeriodForCustomer;
    }

    public void setOnePeriodForCustomer(String onePeriodForCustomer) {
        this.onePeriodForCustomer = onePeriodForCustomer;
    }

    public List<SupplierCustomerInfoDto> getCustomerInfoList() {
        return customerInfoList;
    }

    public void setCustomerInfoList(
        List<SupplierCustomerInfoDto> customerInfoList) {
        this.customerInfoList = customerInfoList;
    }

    public List<IpoInvestItemDto> getInvestItemList() {
        return investItemList;
    }

    public void setInvestItemList(
        List<IpoInvestItemDto> investItemList) {
        this.investItemList = investItemList;
    }

    public List<IntermediaryOrgDto> getIntermediaryOrgList() {
        return intermediaryOrgList;
    }

    public void setIntermediaryOrgList(
        List<IntermediaryOrgDto> intermediaryOrgList) {
        this.intermediaryOrgList = intermediaryOrgList;
    }
}

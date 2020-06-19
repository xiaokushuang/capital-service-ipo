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

    @ApiModelProperty("注册地（国家）")
    private String addrCountry;// 注册地（国家）

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

    @ApiModelProperty("主营业务")
    private String industryStatusOverview; // 主营业务

    @ApiModelProperty("股权结构标签")
    private String structureLabel; //股权结构标签

    @ApiModelProperty("股权结构图")
    private String structureUrl; //股权结构图

    @ApiModelProperty("发行人选择的上市条件")
    private String issueCondition; //发行人选择的上市条件

    @ApiModelProperty("东财内码")
    private String orgCode; //东财内码

    @ApiModelProperty("战略新兴行业分类")
    private String strageticIndustries; //战略新兴行业分类

    @ApiModelProperty("配售机制")
    private String placingMechanism; //配售机制

    @ApiModelProperty("上市条件")
    private String issueConditionName; //上市条件

    @ApiModelProperty("公司治理特殊安排")
    private String specialArrange;//公司治理特殊安排

    @ApiModelProperty("行业code")
    private String industryCsrcCode;

    @ApiModelProperty("板块code")
    private String plateCode;

    @ApiModelProperty("国民经济行业")
    private String nationalIndustries;

    private String issueConditionShortName;

    private String caseStatus;
    private String greenPassage;

    public String getGreenPassage() {
        return greenPassage;
    }

    public void setGreenPassage(String greenPassage) {
        this.greenPassage = greenPassage;
    }

    public String getCaseStatus() {
        return caseStatus;
    }

    public void setCaseStatus(String caseStatus) {
        this.caseStatus = caseStatus;
    }

    public String getIssueConditionShortName() {
        return issueConditionShortName;
    }

    public void setIssueConditionShortName(String issueConditionShortName) {
        this.issueConditionShortName = issueConditionShortName;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getPlateCode() {
        return plateCode;
    }

    public void setPlateCode(String plateCode) {
        this.plateCode = plateCode;
    }

    public String getNationalIndustries() {
        return nationalIndustries;
    }

    public void setNationalIndustries(String nationalIndustries) {
        this.nationalIndustries = nationalIndustries;
    }

    public String getAddrCountry() {
        return addrCountry;
    }

    public void setAddrCountry(String addrCountry) {
        this.addrCountry = addrCountry;
    }

    private String launchcompanycode;

    public String getLaunchcompanycode() {
        return launchcompanycode;
    }

    public void setLaunchcompanycode(String launchcompanycode) {
        this.launchcompanycode = launchcompanycode;
    }

    public String getIndustryCsrcCode() {
        return industryCsrcCode;
    }

    public void setIndustryCsrcCode(String industryCsrcCode) {
        this.industryCsrcCode = industryCsrcCode;
    }

    public String getSpecialArrange() {
        return specialArrange;
    }

    public void setSpecialArrange(String specialArrange) {
        this.specialArrange = specialArrange;
    }

    public String getIssueConditionName() {
        return issueConditionName;
    }

    public void setIssueConditionName(String issueConditionName) {
        this.issueConditionName = issueConditionName;
    }

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

    public String getIndustryStatusOverview() {
        return industryStatusOverview;
    }

    public void setIndustryStatusOverview(String industryStatusOverview) {
        this.industryStatusOverview = industryStatusOverview;
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

    public String getIssueCondition() {
        return issueCondition;
    }

    public void setIssueCondition(String issueCondition) {
        this.issueCondition = issueCondition;
    }

    public String getStrageticIndustries() {
        return strageticIndustries;
    }

    public void setStrageticIndustries(String strageticIndustries) {
        this.strageticIndustries = strageticIndustries;
    }

    public String getPlacingMechanism() {
        return placingMechanism;
    }

    public void setPlacingMechanism(String placingMechanism) {
        this.placingMechanism = placingMechanism;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }
}

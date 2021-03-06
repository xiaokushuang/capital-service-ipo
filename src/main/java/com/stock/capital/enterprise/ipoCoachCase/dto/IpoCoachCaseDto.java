package com.stock.capital.enterprise.ipoCoachCase.dto;

import com.stock.capital.enterprise.ipoCase.dto.IpoProgressDto;
import com.stock.capital.enterprise.ipoCase.dto.TreeTypeProgressDto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class IpoCoachCaseDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;//主键

    private Integer serialNum;//序号

    private String title;//标题

    private String fdProcess;//辅导进程

    private String securitiesRegulatory;//证监局
    private String securitiesRegulatoryText;

    private String ifRelevance;//是否关联ipo案例

    private String fdKeyword;//辅导公司关键字

    private String companyName; //公司名称

    private String companyShortName;//公司简称

    private String corporateRepresentative;//法人代表

    private String foundingTime;//公司成立时间

    private String registerCountry;//注册国家
    private String registerCountryText;

    private String registerProvience;//注册省份
    private String registerProvienceText;

    private String registerCity;//注册市
    private String registerCityText;

    private String registerArea;//注册区
    private String registerAreaText;

    private String address;//详细地址

    private String establishTime;//设立时间

    private String businessCountry;//办公国家
    private String businessCountryText;

    private String businessProvience;//办公省份
    private String businessProvienceText;

    private String businessCity;//办公市
    private String businessCityText;

    private String businessArea;//办公区
    private String businessAreaText;

    private String openFlag;

    private String phone;//联系方式

    private String plate;//拟上市板块
    private String plateText;//拟上市板块

    private String mainBusiness;//主营业务

    private String businessScope;//经营范围

    private String createUser;

    private String updateUser;

    private Date createTime;

    private Date updateTime;

    private List<IpoProgressDto> treeList;

    private List<IntermediaryOrgDto> intermediaryOrgList;

    private List<OtherMarketInfoDto> otherMarketInfoList;

    private String lastDate;

    private String allTime;

    private List<IpoRelatedCaseDto> caseList;

    private List<IpoRelatedCaseDto> relatedCaseDtoList;

    private String signStatus;

    private String serviceBaseUrl;

    public String getServiceBaseUrl() {
        return serviceBaseUrl;
    }

    public void setServiceBaseUrl(String serviceBaseUrl) {
        this.serviceBaseUrl = serviceBaseUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(Integer serialNum) {
        this.serialNum = serialNum;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFdProcess() {
        return fdProcess;
    }

    public void setFdProcess(String fdProcess) {
        this.fdProcess = fdProcess;
    }

    public String getSecuritiesRegulatory() {
        return securitiesRegulatory;
    }

    public void setSecuritiesRegulatory(String securitiesRegulatory) {
        this.securitiesRegulatory = securitiesRegulatory;
    }

    public String getIfRelevance() {
        return ifRelevance;
    }

    public void setIfRelevance(String ifRelevance) {
        this.ifRelevance = ifRelevance;
    }

    public String getFdKeyword() {
        return fdKeyword;
    }

    public void setFdKeyword(String fdKeyword) {
        this.fdKeyword = fdKeyword;
    }

    public List<IpoProgressDto> getTreeList() {
        return treeList;
    }

    public void setTreeList(List<IpoProgressDto> treeList) {
        this.treeList = treeList;
    }

    public List<IpoRelatedCaseDto> getCaseList() {
        return caseList;
    }

    public void setCaseList(List<IpoRelatedCaseDto> caseList) {
        this.caseList = caseList;
    }

    public String getCompanyShortName() {
        return companyShortName;
    }

    public void setCompanyShortName(String companyShortName) {
        this.companyShortName = companyShortName;
    }

    public String getCorporateRepresentative() {
        return corporateRepresentative;
    }

    public void setCorporateRepresentative(String corporateRepresentative) {
        this.corporateRepresentative = corporateRepresentative;
    }

    public String getFoundingTime() {
        return foundingTime;
    }

    public void setFoundingTime(String foundingTime) {
        this.foundingTime = foundingTime;
    }

    public String getRegisterCountry() {
        return registerCountry;
    }

    public void setRegisterCountry(String registerCountry) {
        this.registerCountry = registerCountry;
    }

    public String getRegisterCountryText() {
        return registerCountryText;
    }

    public void setRegisterCountryText(String registerCountryText) {
        this.registerCountryText = registerCountryText;
    }

    public String getRegisterProvience() {
        return registerProvience;
    }

    public void setRegisterProvience(String registerProvience) {
        this.registerProvience = registerProvience;
    }

    public String getRegisterCity() {
        return registerCity;
    }

    public void setRegisterCity(String registerCity) {
        this.registerCity = registerCity;
    }

    public String getRegisterArea() {
        return registerArea;
    }

    public void setRegisterArea(String registerArea) {
        this.registerArea = registerArea;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEstablishTime() {
        return establishTime;
    }

    public void setEstablishTime(String establishTime) {
        this.establishTime = establishTime;
    }

    public String getBusinessCountry() {
        return businessCountry;
    }

    public void setBusinessCountry(String businessCountry) {
        this.businessCountry = businessCountry;
    }

    public String getBusinessCountryText() {
        return businessCountryText;
    }

    public void setBusinessCountryText(String businessCountryText) {
        this.businessCountryText = businessCountryText;
    }

    public String getBusinessProvience() {
        return businessProvience;
    }

    public void setBusinessProvience(String businessProvience) {
        this.businessProvience = businessProvience;
    }

    public String getBusinessCity() {
        return businessCity;
    }

    public void setBusinessCity(String businessCity) {
        this.businessCity = businessCity;
    }

    public String getBusinessArea() {
        return businessArea;
    }

    public void setBusinessArea(String businessArea) {
        this.businessArea = businessArea;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getMainBusiness() {
        return mainBusiness;
    }

    public void setMainBusiness(String mainBusiness) {
        this.mainBusiness = mainBusiness;
    }

    public String getBusinessScope() {
        return businessScope;
    }

    public void setBusinessScope(String businessScope) {
        this.businessScope = businessScope;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public List<IntermediaryOrgDto> getIntermediaryOrgList() {
        return intermediaryOrgList;
    }

    public void setIntermediaryOrgList(List<IntermediaryOrgDto> intermediaryOrgList) {
        this.intermediaryOrgList = intermediaryOrgList;
    }

    public List<OtherMarketInfoDto> getOtherMarketInfoList() {
        return otherMarketInfoList;
    }

    public void setOtherMarketInfoList(List<OtherMarketInfoDto> otherMarketInfoList) {
        this.otherMarketInfoList = otherMarketInfoList;
    }

    public String getOpenFlag() {
        return openFlag;
    }

    public void setOpenFlag(String openFlag) {
        this.openFlag = openFlag;
    }

    public String getLastDate() {
        return lastDate;
    }

    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
    }

    public String getAllTime() {
        return allTime;
    }

    public void setAllTime(String allTime) {
        this.allTime = allTime;
    }

    public String getSecuritiesRegulatoryText() {
        return securitiesRegulatoryText;
    }

    public void setSecuritiesRegulatoryText(String securitiesRegulatoryText) {
        this.securitiesRegulatoryText = securitiesRegulatoryText;
    }

    public String getRegisterProvienceText() {
        return registerProvienceText;
    }

    public void setRegisterProvienceText(String registerProvienceText) {
        this.registerProvienceText = registerProvienceText;
    }

    public String getRegisterCityText() {
        return registerCityText;
    }

    public void setRegisterCityText(String registerCityText) {
        this.registerCityText = registerCityText;
    }

    public String getRegisterAreaText() {
        return registerAreaText;
    }

    public void setRegisterAreaText(String registerAreaText) {
        this.registerAreaText = registerAreaText;
    }

    public String getBusinessProvienceText() {
        return businessProvienceText;
    }

    public void setBusinessProvienceText(String businessProvienceText) {
        this.businessProvienceText = businessProvienceText;
    }

    public String getBusinessCityText() {
        return businessCityText;
    }

    public void setBusinessCityText(String businessCityText) {
        this.businessCityText = businessCityText;
    }

    public String getBusinessAreaText() {
        return businessAreaText;
    }

    public void setBusinessAreaText(String businessAreaText) {
        this.businessAreaText = businessAreaText;
    }

    public List<IpoRelatedCaseDto> getRelatedCaseDtoList() {
        return relatedCaseDtoList;
    }

    public void setRelatedCaseDtoList(List<IpoRelatedCaseDto> relatedCaseDtoList) {
        this.relatedCaseDtoList = relatedCaseDtoList;
    }

    public String getPlateText() {
        return plateText;
    }

    public void setPlateText(String plateText) {
        this.plateText = plateText;
    }

    public String getSignStatus() {
        return signStatus;
    }

    public void setSignStatus(String signStatus) {
        this.signStatus = signStatus;
    }
}

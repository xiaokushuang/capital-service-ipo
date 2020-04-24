package com.stock.capital.enterprise.ipoCase.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Company implements Serializable {
    private String id;

    private String companyCode;

    private String zhName;

    private String usName;

    private String zhSortName;

    private String usSortName;

    private String corporate;

    private String licenseNo;

    private String taxNo;

    private String organisationNo;

    private String registerAddress;

    private String registerPost;

    private String checkinAddress;

    private Date registerDate;

    private String officeAddress;

    private String officePost;

    private String companyWebsite;

    private String companyMail;

    private BigDecimal registerCapital;

    private String secretaries;

    private String industry;

    private String phone;

    private String fax;

    private String ipoDate;

    private String stockInitData;

    private BigDecimal ipoNum;

    private BigDecimal ipoPrice;

    private BigDecimal peRatio;

    private String ipoMode;

    private String ipoSeller;

    private String ipoReferee;

    private String ipoSponsor;

    private BigDecimal equityNum;

    private String pyName;

    private String belongsPlate;

    private String industryCode;

    private String city;

    private String relaCode;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;

    private String status;

    private String ipoFlag;

    private String stockType;

    private String hkStockPlate;

    private String liststate;

    private String supOrder;

    private String supOpenFlag;

    private String svResult;

    private String terminalInitFlag;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode == null ? null : companyCode.trim();
    }

    public String getZhName() {
        return zhName;
    }

    public void setZhName(String zhName) {
        this.zhName = zhName == null ? null : zhName.trim();
    }

    public String getUsName() {
        return usName;
    }

    public void setUsName(String usName) {
        this.usName = usName == null ? null : usName.trim();
    }

    public String getZhSortName() {
        return zhSortName;
    }

    public void setZhSortName(String zhSortName) {
        this.zhSortName = zhSortName == null ? null : zhSortName.trim();
    }

    public String getUsSortName() {
        return usSortName;
    }

    public void setUsSortName(String usSortName) {
        this.usSortName = usSortName == null ? null : usSortName.trim();
    }

    public String getCorporate() {
        return corporate;
    }

    public void setCorporate(String corporate) {
        this.corporate = corporate == null ? null : corporate.trim();
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo == null ? null : licenseNo.trim();
    }

    public String getTaxNo() {
        return taxNo;
    }

    public void setTaxNo(String taxNo) {
        this.taxNo = taxNo == null ? null : taxNo.trim();
    }

    public String getOrganisationNo() {
        return organisationNo;
    }

    public void setOrganisationNo(String organisationNo) {
        this.organisationNo = organisationNo == null ? null : organisationNo.trim();
    }

    public String getRegisterAddress() {
        return registerAddress;
    }

    public void setRegisterAddress(String registerAddress) {
        this.registerAddress = registerAddress == null ? null : registerAddress.trim();
    }

    public String getRegisterPost() {
        return registerPost;
    }

    public void setRegisterPost(String registerPost) {
        this.registerPost = registerPost == null ? null : registerPost.trim();
    }

    public String getCheckinAddress() {
        return checkinAddress;
    }

    public void setCheckinAddress(String checkinAddress) {
        this.checkinAddress = checkinAddress == null ? null : checkinAddress.trim();
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getOfficeAddress() {
        return officeAddress;
    }

    public void setOfficeAddress(String officeAddress) {
        this.officeAddress = officeAddress == null ? null : officeAddress.trim();
    }

    public String getOfficePost() {
        return officePost;
    }

    public void setOfficePost(String officePost) {
        this.officePost = officePost == null ? null : officePost.trim();
    }

    public String getCompanyWebsite() {
        return companyWebsite;
    }

    public void setCompanyWebsite(String companyWebsite) {
        this.companyWebsite = companyWebsite == null ? null : companyWebsite.trim();
    }

    public String getCompanyMail() {
        return companyMail;
    }

    public void setCompanyMail(String companyMail) {
        this.companyMail = companyMail == null ? null : companyMail.trim();
    }

    public BigDecimal getRegisterCapital() {
        return registerCapital;
    }

    public void setRegisterCapital(BigDecimal registerCapital) {
        this.registerCapital = registerCapital;
    }

    public String getSecretaries() {
        return secretaries;
    }

    public void setSecretaries(String secretaries) {
        this.secretaries = secretaries == null ? null : secretaries.trim();
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry == null ? null : industry.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax == null ? null : fax.trim();
    }

    public String getIpoDate() {
        return ipoDate;
    }

    public void setIpoDate(String ipoDate) {
        this.ipoDate = ipoDate == null ? null : ipoDate.trim();
    }

    public String getStockInitData() {
        return stockInitData;
    }

    public void setStockInitData(String stockInitData) {
        this.stockInitData = stockInitData == null ? null : stockInitData.trim();
    }

    public BigDecimal getIpoNum() {
        return ipoNum;
    }

    public void setIpoNum(BigDecimal ipoNum) {
        this.ipoNum = ipoNum;
    }

    public BigDecimal getIpoPrice() {
        return ipoPrice;
    }

    public void setIpoPrice(BigDecimal ipoPrice) {
        this.ipoPrice = ipoPrice;
    }

    public BigDecimal getPeRatio() {
        return peRatio;
    }

    public void setPeRatio(BigDecimal peRatio) {
        this.peRatio = peRatio;
    }

    public String getIpoMode() {
        return ipoMode;
    }

    public void setIpoMode(String ipoMode) {
        this.ipoMode = ipoMode == null ? null : ipoMode.trim();
    }

    public String getIpoSeller() {
        return ipoSeller;
    }

    public void setIpoSeller(String ipoSeller) {
        this.ipoSeller = ipoSeller == null ? null : ipoSeller.trim();
    }

    public String getIpoReferee() {
        return ipoReferee;
    }

    public void setIpoReferee(String ipoReferee) {
        this.ipoReferee = ipoReferee == null ? null : ipoReferee.trim();
    }

    public String getIpoSponsor() {
        return ipoSponsor;
    }

    public void setIpoSponsor(String ipoSponsor) {
        this.ipoSponsor = ipoSponsor == null ? null : ipoSponsor.trim();
    }

    public BigDecimal getEquityNum() {
        return equityNum;
    }

    public void setEquityNum(BigDecimal equityNum) {
        this.equityNum = equityNum;
    }

    public String getPyName() {
        return pyName;
    }

    public void setPyName(String pyName) {
        this.pyName = pyName == null ? null : pyName.trim();
    }

    public String getBelongsPlate() {
        return belongsPlate;
    }

    public void setBelongsPlate(String belongsPlate) {
        this.belongsPlate = belongsPlate == null ? null : belongsPlate.trim();
    }

    public String getIndustryCode() {
        return industryCode;
    }

    public void setIndustryCode(String industryCode) {
        this.industryCode = industryCode == null ? null : industryCode.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getRelaCode() {
        return relaCode;
    }

    public void setRelaCode(String relaCode) {
        this.relaCode = relaCode == null ? null : relaCode.trim();
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getIpoFlag() {
        return ipoFlag;
    }

    public void setIpoFlag(String ipoFlag) {
        this.ipoFlag = ipoFlag == null ? null : ipoFlag.trim();
    }

    public String getStockType() {
        return stockType;
    }

    public void setStockType(String stockType) {
        this.stockType = stockType == null ? null : stockType.trim();
    }

    public String getHkStockPlate() {
        return hkStockPlate;
    }

    public void setHkStockPlate(String hkStockPlate) {
        this.hkStockPlate = hkStockPlate == null ? null : hkStockPlate.trim();
    }

    public String getListstate() {
        return liststate;
    }

    public void setListstate(String liststate) {
        this.liststate = liststate == null ? null : liststate.trim();
    }

    public String getSupOrder() {
        return supOrder;
    }

    public void setSupOrder(String supOrder) {
        this.supOrder = supOrder == null ? null : supOrder.trim();
    }

    public String getSupOpenFlag() {
        return supOpenFlag;
    }

    public void setSupOpenFlag(String supOpenFlag) {
        this.supOpenFlag = supOpenFlag == null ? null : supOpenFlag.trim();
    }

    public String getSvResult() {
        return svResult;
    }

    public void setSvResult(String svResult) {
        this.svResult = svResult == null ? null : svResult.trim();
    }

    public String getTerminalInitFlag() {
        return terminalInitFlag;
    }

    public void setTerminalInitFlag(String terminalInitFlag) {
        this.terminalInitFlag = terminalInitFlag == null ? null : terminalInitFlag.trim();
    }
}
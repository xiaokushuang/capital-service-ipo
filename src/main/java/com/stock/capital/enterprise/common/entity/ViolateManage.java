package com.stock.capital.enterprise.common.entity;

import java.io.Serializable;
import java.util.Date;

public class ViolateManage implements Serializable {
    private String id;

    private String violateTitle;

    private String companyCode;

    private String businessType;

    private String companyName;

    private String punishTimeBegin;

    private String punishTimeEnd;

    private String ignoranceFlag;

    private String profile;

    private String disciplinaryType;

    private String disciplinaryOrg;

    private String violateType;

    private String originalUrl;

    private String newsUrl;

    private String companyId;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;

    private String status;

    private String isPublish;

    private String linkCases;

    private Date dispatchTime;

    private Date bulletinTime;

    private String bulletinTitle;

    private String belongTrade;

    private String violateTypeValue;

    private Integer sort;

    private String ifAdopt;

    private String adoptCondition;

    private String liveNode;

    private String punishOrganization;

    private String newOctMarketFlg;

    private String documentNumber;

    private String region;

    private String punishProperty;

    private String years;

    private String customNumber;

    private Integer linkNumber;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getViolateTitle() {
        return violateTitle;
    }

    public void setViolateTitle(String violateTitle) {
        this.violateTitle = violateTitle == null ? null : violateTitle.trim();
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode == null ? null : companyCode.trim();
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType == null ? null : businessType.trim();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getPunishTimeBegin() {
        return punishTimeBegin;
    }

    public void setPunishTimeBegin(String punishTimeBegin) {
        this.punishTimeBegin = punishTimeBegin == null ? null : punishTimeBegin.trim();
    }

    public String getPunishTimeEnd() {
        return punishTimeEnd;
    }

    public void setPunishTimeEnd(String punishTimeEnd) {
        this.punishTimeEnd = punishTimeEnd == null ? null : punishTimeEnd.trim();
    }

    public String getIgnoranceFlag() {
        return ignoranceFlag;
    }

    public void setIgnoranceFlag(String ignoranceFlag) {
        this.ignoranceFlag = ignoranceFlag == null ? null : ignoranceFlag.trim();
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile == null ? null : profile.trim();
    }

    public String getDisciplinaryType() {
        return disciplinaryType;
    }

    public void setDisciplinaryType(String disciplinaryType) {
        this.disciplinaryType = disciplinaryType == null ? null : disciplinaryType.trim();
    }

    public String getDisciplinaryOrg() {
        return disciplinaryOrg;
    }

    public void setDisciplinaryOrg(String disciplinaryOrg) {
        this.disciplinaryOrg = disciplinaryOrg == null ? null : disciplinaryOrg.trim();
    }

    public String getViolateType() {
        return violateType;
    }

    public void setViolateType(String violateType) {
        this.violateType = violateType == null ? null : violateType.trim();
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl == null ? null : originalUrl.trim();
    }

    public String getNewsUrl() {
        return newsUrl;
    }

    public void setNewsUrl(String newsUrl) {
        this.newsUrl = newsUrl == null ? null : newsUrl.trim();
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
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

    public String getIsPublish() {
        return isPublish;
    }

    public void setIsPublish(String isPublish) {
        this.isPublish = isPublish == null ? null : isPublish.trim();
    }

    public String getLinkCases() {
        return linkCases;
    }

    public void setLinkCases(String linkCases) {
        this.linkCases = linkCases == null ? null : linkCases.trim();
    }

    public Date getDispatchTime() {
        return dispatchTime;
    }

    public void setDispatchTime(Date dispatchTime) {
        this.dispatchTime = dispatchTime;
    }

    public Date getBulletinTime() {
        return bulletinTime;
    }

    public void setBulletinTime(Date bulletinTime) {
        this.bulletinTime = bulletinTime;
    }

    public String getBulletinTitle() {
        return bulletinTitle;
    }

    public void setBulletinTitle(String bulletinTitle) {
        this.bulletinTitle = bulletinTitle == null ? null : bulletinTitle.trim();
    }

    public String getBelongTrade() {
        return belongTrade;
    }

    public void setBelongTrade(String belongTrade) {
        this.belongTrade = belongTrade == null ? null : belongTrade.trim();
    }

    public String getViolateTypeValue() {
        return violateTypeValue;
    }

    public void setViolateTypeValue(String violateTypeValue) {
        this.violateTypeValue = violateTypeValue == null ? null : violateTypeValue.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getIfAdopt() {
        return ifAdopt;
    }

    public void setIfAdopt(String ifAdopt) {
        this.ifAdopt = ifAdopt == null ? null : ifAdopt.trim();
    }

    public String getAdoptCondition() {
        return adoptCondition;
    }

    public void setAdoptCondition(String adoptCondition) {
        this.adoptCondition = adoptCondition == null ? null : adoptCondition.trim();
    }

    public String getLiveNode() {
        return liveNode;
    }

    public void setLiveNode(String liveNode) {
        this.liveNode = liveNode == null ? null : liveNode.trim();
    }

    public String getPunishOrganization() {
        return punishOrganization;
    }

    public void setPunishOrganization(String punishOrganization) {
        this.punishOrganization = punishOrganization == null ? null : punishOrganization.trim();
    }

    public String getNewOctMarketFlg() {
        return newOctMarketFlg;
    }

    public void setNewOctMarketFlg(String newOctMarketFlg) {
        this.newOctMarketFlg = newOctMarketFlg == null ? null : newOctMarketFlg.trim();
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber == null ? null : documentNumber.trim();
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region == null ? null : region.trim();
    }

    public String getPunishProperty() {
        return punishProperty;
    }

    public void setPunishProperty(String punishProperty) {
        this.punishProperty = punishProperty == null ? null : punishProperty.trim();
    }

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years == null ? null : years.trim();
    }

    public String getCustomNumber() {
        return customNumber;
    }

    public void setCustomNumber(String customNumber) {
        this.customNumber = customNumber == null ? null : customNumber.trim();
    }

    public Integer getLinkNumber() {
        return linkNumber;
    }

    public void setLinkNumber(Integer linkNumber) {
        this.linkNumber = linkNumber;
    }
}
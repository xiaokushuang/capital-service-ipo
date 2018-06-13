package com.stock.capital.enterprise.common.entity;

import java.util.Date;

public class DeclareInfo {
    private String id;

    private String declareName;

    private String declareNo;

    private Date disclosureDate;

    private String context;

    private String associatedMeeting;

    private String capitalPlus;

    private String declareStatus;

    private String companyCode;

    private String companyId;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;

    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getDeclareName() {
        return declareName;
    }

    public void setDeclareName(String declareName) {
        this.declareName = declareName == null ? null : declareName.trim();
    }

    public String getDeclareNo() {
        return declareNo;
    }

    public void setDeclareNo(String declareNo) {
        this.declareNo = declareNo == null ? null : declareNo.trim();
    }

    public Date getDisclosureDate() {
        return disclosureDate;
    }

    public void setDisclosureDate(Date disclosureDate) {
        this.disclosureDate = disclosureDate;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context == null ? null : context.trim();
    }

    public String getAssociatedMeeting() {
        return associatedMeeting;
    }

    public void setAssociatedMeeting(String associatedMeeting) {
        this.associatedMeeting = associatedMeeting == null ? null : associatedMeeting.trim();
    }

    public String getCapitalPlus() {
        return capitalPlus;
    }

    public void setCapitalPlus(String capitalPlus) {
        this.capitalPlus = capitalPlus == null ? null : capitalPlus.trim();
    }

    public String getDeclareStatus() {
        return declareStatus;
    }

    public void setDeclareStatus(String declareStatus) {
        this.declareStatus = declareStatus == null ? null : declareStatus.trim();
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode == null ? null : companyCode.trim();
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
}
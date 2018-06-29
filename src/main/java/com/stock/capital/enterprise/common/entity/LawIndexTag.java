package com.stock.capital.enterprise.common.entity;

import java.io.Serializable;
import java.util.Date;

public class LawIndexTag implements Serializable {
    private String id;

    private String tagName;

    private String keyType;

    private String keyContent;

    private String keyAndContent;

    private String keyNotContent;

    private String companySearchCode;

    private String personId;

    private String companyId;

    private Date publishedStartDate;

    private Date publishedEndDate;

    private Date invalidStartDate;

    private Date invalidEndDate;

    private String applicationScope;

    private String units;

    private String flag;

    private String keyTitle;

    private String keyAndTitle;

    private String keyNotTitle;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;

    private String status;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName == null ? null : tagName.trim();
    }

    public String getKeyType() {
        return keyType;
    }

    public void setKeyType(String keyType) {
        this.keyType = keyType == null ? null : keyType.trim();
    }

    public String getKeyContent() {
        return keyContent;
    }

    public void setKeyContent(String keyContent) {
        this.keyContent = keyContent == null ? null : keyContent.trim();
    }

    public String getKeyAndContent() {
        return keyAndContent;
    }

    public void setKeyAndContent(String keyAndContent) {
        this.keyAndContent = keyAndContent == null ? null : keyAndContent.trim();
    }

    public String getKeyNotContent() {
        return keyNotContent;
    }

    public void setKeyNotContent(String keyNotContent) {
        this.keyNotContent = keyNotContent == null ? null : keyNotContent.trim();
    }

    public String getCompanySearchCode() {
        return companySearchCode;
    }

    public void setCompanySearchCode(String companySearchCode) {
        this.companySearchCode = companySearchCode == null ? null : companySearchCode.trim();
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId == null ? null : personId.trim();
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    public Date getPublishedStartDate() {
        return publishedStartDate;
    }

    public void setPublishedStartDate(Date publishedStartDate) {
        this.publishedStartDate = publishedStartDate;
    }

    public Date getPublishedEndDate() {
        return publishedEndDate;
    }

    public void setPublishedEndDate(Date publishedEndDate) {
        this.publishedEndDate = publishedEndDate;
    }

    public Date getInvalidStartDate() {
        return invalidStartDate;
    }

    public void setInvalidStartDate(Date invalidStartDate) {
        this.invalidStartDate = invalidStartDate;
    }

    public Date getInvalidEndDate() {
        return invalidEndDate;
    }

    public void setInvalidEndDate(Date invalidEndDate) {
        this.invalidEndDate = invalidEndDate;
    }

    public String getApplicationScope() {
        return applicationScope;
    }

    public void setApplicationScope(String applicationScope) {
        this.applicationScope = applicationScope == null ? null : applicationScope.trim();
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units == null ? null : units.trim();
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag == null ? null : flag.trim();
    }

    public String getKeyTitle() {
        return keyTitle;
    }

    public void setKeyTitle(String keyTitle) {
        this.keyTitle = keyTitle == null ? null : keyTitle.trim();
    }

    public String getKeyAndTitle() {
        return keyAndTitle;
    }

    public void setKeyAndTitle(String keyAndTitle) {
        this.keyAndTitle = keyAndTitle == null ? null : keyAndTitle.trim();
    }

    public String getKeyNotTitle() {
        return keyNotTitle;
    }

    public void setKeyNotTitle(String keyNotTitle) {
        this.keyNotTitle = keyNotTitle == null ? null : keyNotTitle.trim();
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
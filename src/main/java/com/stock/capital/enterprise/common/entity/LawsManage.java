package com.stock.capital.enterprise.common.entity;

import java.io.Serializable;
import java.util.Date;

public class LawsManage implements Serializable {
    private String id;

    private String typeNo;

    private String typeChNo;

    private String lawsName;

    private String lawSource;

    private Date published;

    private String lawRank;

    private String lawEffectDate;

    private String lawInvalid;

    private String hintInfo;

    private String keyword;

    private String sxyj;

    private String lawGrade;

    private String lawStatus;

    private String lawSign;

    private String isShow;

    private String lawDigest;

    private String lawRange;

    private Integer sortNo;

    private String failed;

    private String companyId;

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

    public String getTypeNo() {
        return typeNo;
    }

    public void setTypeNo(String typeNo) {
        this.typeNo = typeNo == null ? null : typeNo.trim();
    }

    public String getTypeChNo() {
        return typeChNo;
    }

    public void setTypeChNo(String typeChNo) {
        this.typeChNo = typeChNo == null ? null : typeChNo.trim();
    }

    public String getLawsName() {
        return lawsName;
    }

    public void setLawsName(String lawsName) {
        this.lawsName = lawsName == null ? null : lawsName.trim();
    }

    public String getLawSource() {
        return lawSource;
    }

    public void setLawSource(String lawSource) {
        this.lawSource = lawSource == null ? null : lawSource.trim();
    }

    public Date getPublished() {
        return published;
    }

    public void setPublished(Date published) {
        this.published = published;
    }

    public String getLawRank() {
        return lawRank;
    }

    public void setLawRank(String lawRank) {
        this.lawRank = lawRank == null ? null : lawRank.trim();
    }

    public String getLawEffectDate() {
        return lawEffectDate;
    }

    public void setLawEffectDate(String lawEffectDate) {
        this.lawEffectDate = lawEffectDate == null ? null : lawEffectDate.trim();
    }

    public String getLawInvalid() {
        return lawInvalid;
    }

    public void setLawInvalid(String lawInvalid) {
        this.lawInvalid = lawInvalid == null ? null : lawInvalid.trim();
    }

    public String getHintInfo() {
        return hintInfo;
    }

    public void setHintInfo(String hintInfo) {
        this.hintInfo = hintInfo == null ? null : hintInfo.trim();
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword == null ? null : keyword.trim();
    }

    public String getSxyj() {
        return sxyj;
    }

    public void setSxyj(String sxyj) {
        this.sxyj = sxyj == null ? null : sxyj.trim();
    }

    public String getLawGrade() {
        return lawGrade;
    }

    public void setLawGrade(String lawGrade) {
        this.lawGrade = lawGrade == null ? null : lawGrade.trim();
    }

    public String getLawStatus() {
        return lawStatus;
    }

    public void setLawStatus(String lawStatus) {
        this.lawStatus = lawStatus == null ? null : lawStatus.trim();
    }

    public String getLawSign() {
        return lawSign;
    }

    public void setLawSign(String lawSign) {
        this.lawSign = lawSign == null ? null : lawSign.trim();
    }

    public String getIsShow() {
        return isShow;
    }

    public void setIsShow(String isShow) {
        this.isShow = isShow == null ? null : isShow.trim();
    }

    public String getLawDigest() {
        return lawDigest;
    }

    public void setLawDigest(String lawDigest) {
        this.lawDigest = lawDigest == null ? null : lawDigest.trim();
    }

    public String getLawRange() {
        return lawRange;
    }

    public void setLawRange(String lawRange) {
        this.lawRange = lawRange == null ? null : lawRange.trim();
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public String getFailed() {
        return failed;
    }

    public void setFailed(String failed) {
        this.failed = failed == null ? null : failed.trim();
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
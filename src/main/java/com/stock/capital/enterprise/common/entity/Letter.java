package com.stock.capital.enterprise.common.entity;

import java.io.Serializable;
import java.util.Date;

public class Letter implements Serializable {
    private String id;

    private String letterName;

    private String letterNameOld;

    private String letterOrg;

    private Date letterDate;

    private String letterType;

    private Date endDate;

    private Date returnDate;

    private String letterStatus;

    private String charge;

    private String remark;

    private String letterPerson;

    private String rsType;

    private String ynFlag;

    private String companyId;

    private String companyName;

    private String letterValid;

    private String labelStatus;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;

    private String status;

    private String companyReply;

    private String letterFileNo;

    private Integer sortNo;

    private String postStatus;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getLetterName() {
        return letterName;
    }

    public void setLetterName(String letterName) {
        this.letterName = letterName == null ? null : letterName.trim();
    }

    public String getLetterNameOld() {
        return letterNameOld;
    }

    public void setLetterNameOld(String letterNameOld) {
        this.letterNameOld = letterNameOld == null ? null : letterNameOld.trim();
    }

    public String getLetterOrg() {
        return letterOrg;
    }

    public void setLetterOrg(String letterOrg) {
        this.letterOrg = letterOrg == null ? null : letterOrg.trim();
    }

    public Date getLetterDate() {
        return letterDate;
    }

    public void setLetterDate(Date letterDate) {
        this.letterDate = letterDate;
    }

    public String getLetterType() {
        return letterType;
    }

    public void setLetterType(String letterType) {
        this.letterType = letterType == null ? null : letterType.trim();
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public String getLetterStatus() {
        return letterStatus;
    }

    public void setLetterStatus(String letterStatus) {
        this.letterStatus = letterStatus == null ? null : letterStatus.trim();
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge == null ? null : charge.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getLetterPerson() {
        return letterPerson;
    }

    public void setLetterPerson(String letterPerson) {
        this.letterPerson = letterPerson == null ? null : letterPerson.trim();
    }

    public String getRsType() {
        return rsType;
    }

    public void setRsType(String rsType) {
        this.rsType = rsType == null ? null : rsType.trim();
    }

    public String getYnFlag() {
        return ynFlag;
    }

    public void setYnFlag(String ynFlag) {
        this.ynFlag = ynFlag == null ? null : ynFlag.trim();
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getLetterValid() {
        return letterValid;
    }

    public void setLetterValid(String letterValid) {
        this.letterValid = letterValid == null ? null : letterValid.trim();
    }

    public String getLabelStatus() {
        return labelStatus;
    }

    public void setLabelStatus(String labelStatus) {
        this.labelStatus = labelStatus == null ? null : labelStatus.trim();
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

    public String getCompanyReply() {
        return companyReply;
    }

    public void setCompanyReply(String companyReply) {
        this.companyReply = companyReply == null ? null : companyReply.trim();
    }

    public String getLetterFileNo() {
        return letterFileNo;
    }

    public void setLetterFileNo(String letterFileNo) {
        this.letterFileNo = letterFileNo == null ? null : letterFileNo.trim();
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public String getPostStatus() {
        return postStatus;
    }

    public void setPostStatus(String postStatus) {
        this.postStatus = postStatus == null ? null : postStatus.trim();
    }
}
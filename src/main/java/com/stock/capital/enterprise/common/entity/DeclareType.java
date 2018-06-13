package com.stock.capital.enterprise.common.entity;

import java.io.Serializable;
import java.util.Date;

public class DeclareType implements Serializable{

    private static final long serialVersionUID = -1316694822844436178L;

    private String id;

    private String parentId;

    private Integer level;

    private String declareTypeName;

    private String declareTypeNo;

    private String note;

    private String typeNo;

    private String typeClass;

    private String suspendingFlag;

    private String followClassId;

    private Short followDateInterval;

    private Short beforeDays;

    private Short afterDays;

    private String validFlag;

    private Integer oftenFlag;

    private Integer billFlag;

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

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getDeclareTypeName() {
        return declareTypeName;
    }

    public void setDeclareTypeName(String declareTypeName) {
        this.declareTypeName = declareTypeName == null ? null : declareTypeName.trim();
    }

    public String getDeclareTypeNo() {
        return declareTypeNo;
    }

    public void setDeclareTypeNo(String declareTypeNo) {
        this.declareTypeNo = declareTypeNo == null ? null : declareTypeNo.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public String getTypeNo() {
        return typeNo;
    }

    public void setTypeNo(String typeNo) {
        this.typeNo = typeNo == null ? null : typeNo.trim();
    }

    public String getTypeClass() {
        return typeClass;
    }

    public void setTypeClass(String typeClass) {
        this.typeClass = typeClass == null ? null : typeClass.trim();
    }

    public String getSuspendingFlag() {
        return suspendingFlag;
    }

    public void setSuspendingFlag(String suspendingFlag) {
        this.suspendingFlag = suspendingFlag == null ? null : suspendingFlag.trim();
    }

    public String getFollowClassId() {
        return followClassId;
    }

    public void setFollowClassId(String followClassId) {
        this.followClassId = followClassId == null ? null : followClassId.trim();
    }

    public Short getFollowDateInterval() {
        return followDateInterval;
    }

    public void setFollowDateInterval(Short followDateInterval) {
        this.followDateInterval = followDateInterval;
    }

    public Short getBeforeDays() {
        return beforeDays;
    }

    public void setBeforeDays(Short beforeDays) {
        this.beforeDays = beforeDays;
    }

    public Short getAfterDays() {
        return afterDays;
    }

    public void setAfterDays(Short afterDays) {
        this.afterDays = afterDays;
    }

    public String getValidFlag() {
        return validFlag;
    }

    public void setValidFlag(String validFlag) {
        this.validFlag = validFlag == null ? null : validFlag.trim();
    }

    public Integer getOftenFlag() {
        return oftenFlag;
    }

    public void setOftenFlag(Integer oftenFlag) {
        this.oftenFlag = oftenFlag;
    }

    public Integer getBillFlag() {
        return billFlag;
    }

    public void setBillFlag(Integer billFlag) {
        this.billFlag = billFlag;
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
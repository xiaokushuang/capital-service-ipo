package com.stock.capital.enterprise.common.entity;

import java.io.Serializable;
import java.util.Date;

public class MaterialPonderMap implements Serializable {
    private String id;

    private String typeId;

    private String pointContext;

    private String type;

    private String completeType;

    private String supervisionRelated;

    private String declareId;

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

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId == null ? null : typeId.trim();
    }

    public String getPointContext() {
        return pointContext;
    }

    public void setPointContext(String pointContext) {
        this.pointContext = pointContext == null ? null : pointContext.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getCompleteType() {
        return completeType;
    }

    public void setCompleteType(String completeType) {
        this.completeType = completeType == null ? null : completeType.trim();
    }

    public String getSupervisionRelated() {
        return supervisionRelated;
    }

    public void setSupervisionRelated(String supervisionRelated) {
        this.supervisionRelated = supervisionRelated == null ? null : supervisionRelated.trim();
    }

    public String getDeclareId() {
        return declareId;
    }

    public void setDeclareId(String declareId) {
        this.declareId = declareId == null ? null : declareId.trim();
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
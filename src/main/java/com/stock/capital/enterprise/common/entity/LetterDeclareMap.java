package com.stock.capital.enterprise.common.entity;

import java.io.Serializable;
import java.util.Date;

public class LetterDeclareMap implements Serializable {
    private String id;

    private String letterId;

    private String declareName;

    private String declareUrl;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;

    private String status;

    private String sort;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getLetterId() {
        return letterId;
    }

    public void setLetterId(String letterId) {
        this.letterId = letterId == null ? null : letterId.trim();
    }

    public String getDeclareName() {
        return declareName;
    }

    public void setDeclareName(String declareName) {
        this.declareName = declareName == null ? null : declareName.trim();
    }

    public String getDeclareUrl() {
        return declareUrl;
    }

    public void setDeclareUrl(String declareUrl) {
        this.declareUrl = declareUrl == null ? null : declareUrl.trim();
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

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort == null ? null : sort.trim();
    }
}
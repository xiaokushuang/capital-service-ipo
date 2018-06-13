package com.stock.capital.enterprise.common.entity;

import java.io.Serializable;
import java.util.Date;

public class DeclareIdea implements Serializable {
    private String id;

    private String declareTypeId;

    private String ideaContent;

    private String personId;

    private Date addTicapitalEnterpriseDevme;

    private String companyId;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;

    private String status;

    private String privateStatus;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getDeclareTypeId() {
        return declareTypeId;
    }

    public void setDeclareTypeId(String declareTypeId) {
        this.declareTypeId = declareTypeId == null ? null : declareTypeId.trim();
    }

    public String getIdeaContent() {
        return ideaContent;
    }

    public void setIdeaContent(String ideaContent) {
        this.ideaContent = ideaContent == null ? null : ideaContent.trim();
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId == null ? null : personId.trim();
    }

    public Date getAddTicapitalEnterpriseDevme() {
        return addTicapitalEnterpriseDevme;
    }

    public void setAddTicapitalEnterpriseDevme(Date addTicapitalEnterpriseDevme) {
        this.addTicapitalEnterpriseDevme = addTicapitalEnterpriseDevme;
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

    public String getPrivateStatus() {
        return privateStatus;
    }

    public void setPrivateStatus(String privateStatus) {
        this.privateStatus = privateStatus == null ? null : privateStatus.trim();
    }
}
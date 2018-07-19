package com.stock.capital.enterprise.common.entity;

import java.io.Serializable;
import java.util.Date;

public class UserInputIndex implements Serializable {
    private String id;

    private String inputIndex;

    private String inputIndexQuery;

    private String indexModule;

    private String indexProject;

    private String processStatus;

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

    public String getInputIndex() {
        return inputIndex;
    }

    public void setInputIndex(String inputIndex) {
        this.inputIndex = inputIndex == null ? null : inputIndex.trim();
    }

    public String getInputIndexQuery() {
        return inputIndexQuery;
    }

    public void setInputIndexQuery(String inputIndexQuery) {
        this.inputIndexQuery = inputIndexQuery == null ? null : inputIndexQuery.trim();
    }

    public String getIndexModule() {
        return indexModule;
    }

    public void setIndexModule(String indexModule) {
        this.indexModule = indexModule == null ? null : indexModule.trim();
    }

    public String getIndexProject() {
        return indexProject;
    }

    public void setIndexProject(String indexProject) {
        this.indexProject = indexProject == null ? null : indexProject.trim();
    }

    public String getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(String processStatus) {
        this.processStatus = processStatus == null ? null : processStatus.trim();
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
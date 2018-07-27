package com.stock.capital.enterprise.common.entity;

import java.io.Serializable;
import java.util.Date;

public class LabelRtnFile implements Serializable {
    private String id;

    private String letterrtnId;

    private String fileId;

    private String fileName;

    private String sort;

    private String markFlag;

    private String updateUser;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getLetterrtnId() {
        return letterrtnId;
    }

    public void setLetterrtnId(String letterrtnId) {
        this.letterrtnId = letterrtnId == null ? null : letterrtnId.trim();
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId == null ? null : fileId.trim();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort == null ? null : sort.trim();
    }

    public String getMarkFlag() {
        return markFlag;
    }

    public void setMarkFlag(String markFlag) {
        this.markFlag = markFlag == null ? null : markFlag.trim();
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
}
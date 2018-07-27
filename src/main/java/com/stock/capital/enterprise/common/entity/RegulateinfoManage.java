package com.stock.capital.enterprise.common.entity;

import java.io.Serializable;
import java.util.Date;

public class RegulateinfoManage implements Serializable {
    private String id;

    private String infoTitle;

    private String author;

    private String infoSource;

    private Date releaseTime;

    private String publishFlag;

    private String url;

    private String fileFlag;

    private String companyId;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;

    private String status;

    private Long browsenum;

    private String infoContent;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getInfoTitle() {
        return infoTitle;
    }

    public void setInfoTitle(String infoTitle) {
        this.infoTitle = infoTitle == null ? null : infoTitle.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getInfoSource() {
        return infoSource;
    }

    public void setInfoSource(String infoSource) {
        this.infoSource = infoSource == null ? null : infoSource.trim();
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getPublishFlag() {
        return publishFlag;
    }

    public void setPublishFlag(String publishFlag) {
        this.publishFlag = publishFlag == null ? null : publishFlag.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getFileFlag() {
        return fileFlag;
    }

    public void setFileFlag(String fileFlag) {
        this.fileFlag = fileFlag == null ? null : fileFlag.trim();
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

    public Long getBrowsenum() {
        return browsenum;
    }

    public void setBrowsenum(Long browsenum) {
        this.browsenum = browsenum;
    }

    public String getInfoContent() {
        return infoContent;
    }

    public void setInfoContent(String infoContent) {
        this.infoContent = infoContent == null ? null : infoContent.trim();
    }
}
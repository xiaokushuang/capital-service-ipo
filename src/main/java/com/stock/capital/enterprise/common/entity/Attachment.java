package com.stock.capital.enterprise.common.entity;

import java.io.Serializable;
import java.util.Date;

public class Attachment implements Serializable {
    private String id;

    private String relaId;

    private String attName;

    private String attNo;

    private String attUrl;

    private String businessId;

    private String businessType;

    private String subType;

    private String version;

    private Long size;

    private String documentType;

    private Integer downloadCnt;

    private Integer phoneDownloadCnt;

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

    public String getRelaId() {
        return relaId;
    }

    public void setRelaId(String relaId) {
        this.relaId = relaId == null ? null : relaId.trim();
    }

    public String getAttName() {
        return attName;
    }

    public void setAttName(String attName) {
        this.attName = attName == null ? null : attName.trim();
    }

    public String getAttNo() {
        return attNo;
    }

    public void setAttNo(String attNo) {
        this.attNo = attNo == null ? null : attNo.trim();
    }

    public String getAttUrl() {
        return attUrl;
    }

    public void setAttUrl(String attUrl) {
        this.attUrl = attUrl == null ? null : attUrl.trim();
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId == null ? null : businessId.trim();
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType == null ? null : businessType.trim();
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType == null ? null : subType.trim();
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType == null ? null : documentType.trim();
    }

    public Integer getDownloadCnt() {
        return downloadCnt;
    }

    public void setDownloadCnt(Integer downloadCnt) {
        this.downloadCnt = downloadCnt;
    }

    public Integer getPhoneDownloadCnt() {
        return phoneDownloadCnt;
    }

    public void setPhoneDownloadCnt(Integer phoneDownloadCnt) {
        this.phoneDownloadCnt = phoneDownloadCnt;
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
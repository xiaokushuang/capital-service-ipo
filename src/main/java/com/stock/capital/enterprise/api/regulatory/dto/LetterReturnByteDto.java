package com.stock.capital.enterprise.api.regulatory.dto;

import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;

public class LetterReturnByteDto implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String id;

    private String returnRemark;

    private String letterId;

    private String letterPerson;

    private Date returnDate;

    private String returnStatus;

    private String companyId;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;

    private String status;

    private InputStream byteString;

    private String attTypeName;

    private String filename;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getReturnRemark() {
        return returnRemark;
    }

    public void setReturnRemark(String returnRemark) {
        this.returnRemark = returnRemark == null ? null : returnRemark.trim();
    }

    public String getLetterId() {
        return letterId;
    }

    public void setLetterId(String letterId) {
        this.letterId = letterId == null ? null : letterId.trim();
    }

    public String getLetterPerson() {
        return letterPerson;
    }

    public void setLetterPerson(String letterPerson) {
        this.letterPerson = letterPerson == null ? null : letterPerson.trim();
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public String getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(String returnStatus) {
        this.returnStatus = returnStatus == null ? null : returnStatus.trim();
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

    public InputStream getByteString() {
        return byteString;
    }

    public void setByteString(InputStream byteString) {
        this.byteString = byteString;
    }

    public String getAttTypeName() {
        return attTypeName;
    }

    public void setAttTypeName(String attTypeName) {
        this.attTypeName = attTypeName;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
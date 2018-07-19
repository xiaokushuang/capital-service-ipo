package com.stock.capital.enterprise.api.regulatory.dto;

import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;

public class LetterByteDto implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String id;

    private String letterName;

    private String letterOrg;

    private Date letterDate;

    private String letterType;

    private Date endDate;

    private String letterStatus;

    private String charge;

    private String remark;

    private String letterPerson;

    private String rsType;

    private String ynFlag;

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
        this.id = id;
    }

    public String getLetterName() {
        return letterName;
    }

    public void setLetterName(String letterName) {
        this.letterName = letterName;
    }

    public String getLetterOrg() {
        return letterOrg;
    }

    public void setLetterOrg(String letterOrg) {
        this.letterOrg = letterOrg;
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
        this.letterType = letterType;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getLetterStatus() {
        return letterStatus;
    }

    public void setLetterStatus(String letterStatus) {
        this.letterStatus = letterStatus;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getLetterPerson() {
        return letterPerson;
    }

    public void setLetterPerson(String letterPerson) {
        this.letterPerson = letterPerson;
    }

    public String getRsType() {
        return rsType;
    }

    public void setRsType(String rsType) {
        this.rsType = rsType;
    }

    public String getYnFlag() {
        return ynFlag;
    }

    public void setYnFlag(String ynFlag) {
        this.ynFlag = ynFlag;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
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
        this.updateUser = updateUser;
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
        this.status = status;
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

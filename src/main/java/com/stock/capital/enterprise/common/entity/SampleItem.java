package com.stock.capital.enterprise.common.entity;

import java.math.BigDecimal;
import java.util.Date;

public class SampleItem {
    private String id;

    private String sampleId;

    private String itemName;

    private String itemType;

    private Date itemDate;

    private Date itemTime;

    private String itemCheckbox;

    private String itemRadiobutton;

    private Integer itemNumInt;

    private BigDecimal itemNumDouble;

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

    public String getSampleId() {
        return sampleId;
    }

    public void setSampleId(String sampleId) {
        this.sampleId = sampleId == null ? null : sampleId.trim();
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType == null ? null : itemType.trim();
    }

    public Date getItemDate() {
        return itemDate;
    }

    public void setItemDate(Date itemDate) {
        this.itemDate = itemDate;
    }

    public Date getItemTime() {
        return itemTime;
    }

    public void setItemTime(Date itemTime) {
        this.itemTime = itemTime;
    }

    public String getItemCheckbox() {
        return itemCheckbox;
    }

    public void setItemCheckbox(String itemCheckbox) {
        this.itemCheckbox = itemCheckbox == null ? null : itemCheckbox.trim();
    }

    public String getItemRadiobutton() {
        return itemRadiobutton;
    }

    public void setItemRadiobutton(String itemRadiobutton) {
        this.itemRadiobutton = itemRadiobutton == null ? null : itemRadiobutton.trim();
    }

    public Integer getItemNumInt() {
        return itemNumInt;
    }

    public void setItemNumInt(Integer itemNumInt) {
        this.itemNumInt = itemNumInt;
    }

    public BigDecimal getItemNumDouble() {
        return itemNumDouble;
    }

    public void setItemNumDouble(BigDecimal itemNumDouble) {
        this.itemNumDouble = itemNumDouble;
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
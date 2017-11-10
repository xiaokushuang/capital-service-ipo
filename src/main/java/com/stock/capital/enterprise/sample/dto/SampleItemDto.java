package com.stock.capital.enterprise.sample.dto;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class SampleItemDto implements java.io.Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 944855243034658886L;

    private String id;
    
    private String itemName;
    
    private String itemType;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date itemDate;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
    private Date itemTime;
    
    private String itemCheckbox;

    private String itemRadiobutton;

    private Integer itemNumInt;

    private BigDecimal itemNumDouble;
    
    private String createUser;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String updateUser;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private String status;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
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
        this.itemCheckbox = itemCheckbox;
    }

    public String getItemRadiobutton() {
        return itemRadiobutton;
    }

    public void setItemRadiobutton(String itemRadiobutton) {
        this.itemRadiobutton = itemRadiobutton;
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

}

package com.stock.capital.enterprise.sample.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

public class SampleDto implements java.io.Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 944855243034658886L;

    private String id;
    
    private String name;
    
    private String type;

    private String typeName;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
    private Date testDate;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm", timezone="GMT+8")
    private Date testTime;
    
    private String checkbox;
    
    private String radiobutton;

    private Integer numInt;

    private BigDecimal numDouble;
    
    private String createUser;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String updateUser;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private String status;
    
    private List<SampleItemDto> items;
    
    private List<String> fileId;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Date getTestDate() {
        return testDate;
    }

    public void setTestDate(Date testDate) {
        this.testDate = testDate;
    }

    public Date getTestTime() {
        return testTime;
    }

    public void setTestTime(Date testTime) {
        this.testTime = testTime;
    }

    public List<String> getCheckbox1() {
        if (StringUtils.isNotEmpty(checkbox)) {
            return Splitter.on(",").splitToList(checkbox);
        }
        return null;
    }

    public void setCheckbox1(List<String> checkbox1) {
        if (checkbox1 != null)
        this.checkbox = Joiner.on(",").join(checkbox1);
    }
    
    public String getCheckbox() {
        return checkbox;
    }
    
    public void setCheckbox(String checkbox) {
        this.checkbox = checkbox;
    }

    public String getRadiobutton() {
        return radiobutton;
    }

    public void setRadiobutton(String radiobutton) {
        this.radiobutton = radiobutton;
    }

    public Integer getNumInt() {
        return numInt;
    }

    public void setNumInt(Integer numInt) {
        this.numInt = numInt;
    }

    public BigDecimal getNumDouble() {
        return numDouble;
    }

    public void setNumDouble(BigDecimal numDouble) {
        this.numDouble = numDouble;
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

    public List<SampleItemDto> getItems() {
        return items;
    }

    public void setItems(List<SampleItemDto> items) {
        this.items = items;
    }

    public List<String> getFileId() {
        return fileId;
    }

    public void setFileId(List<String> fileId) {
        this.fileId = fileId;
    }

}

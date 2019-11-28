package com.stock.capital.enterprise.ipoCase.dto;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class StrategicPlacementMainDto implements Serializable {


    private static final long serialVersionUID = 2177845340984568703L;

    private String id;
    private String bid;
    private String remark;
    private String bussinessType;

    private Date createTime;
    private String createUser;
    private String updateUser;
    private Date updateTime;

    private String status;

    private List<StrategicPlacementSubDto> subs;

    public List<StrategicPlacementSubDto> getSubs() {
        return subs;
    }

    public void setSubs(List<StrategicPlacementSubDto> subs) {
        this.subs = subs;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getBussinessType() {
        return bussinessType;
    }

    public void setBussinessType(String bussinessType) {
        this.bussinessType = bussinessType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
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

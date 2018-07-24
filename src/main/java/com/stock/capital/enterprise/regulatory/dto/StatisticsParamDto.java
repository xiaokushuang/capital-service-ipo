package com.stock.capital.enterprise.regulatory.dto;

import java.io.Serializable;

public class StatisticsParamDto implements Serializable {

    private static final long serialVersionUID = -4451444384801160989L;
    
    private String lastUpadteTime;    //截止时间--查询条件
    
    private String belongsPlate;  //市场类型--查询条件
    
    private String industry;    //行业--查询条件
    
    private String registAddr;  //注册地--查询条件
    
    private String ipoOrder; //排序--查询条件
    
    private String approveStatus; //IPO审核状态--查询条件

    public String getLastUpadteTime() {
        return lastUpadteTime;
    }

    public void setLastUpadteTime(String lastUpadteTime) {
        this.lastUpadteTime = lastUpadteTime;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getRegistAddr() {
        return registAddr;
    }

    public void setRegistAddr(String registAddr) {
        this.registAddr = registAddr;
    }

    public String getBelongsPlate() {
        return belongsPlate;
    }

    public void setBelongsPlate(String belongsPlate) {
        this.belongsPlate = belongsPlate;
    }

    public String getIpoOrder() {
        return ipoOrder;
    }

    public void setIpoOrder(String ipoOrder) {
        this.ipoOrder = ipoOrder;
    }

    public String getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(String approveStatus) {
        this.approveStatus = approveStatus;
    }

}


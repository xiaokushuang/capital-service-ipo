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
    
    private String label;
    
    private String quasiListedLand;
    
    //tab页
    private String tabFlag;

    private String intermediaryType;

    public String getIntermediaryType() {
        return intermediaryType;
    }

    public void setIntermediaryType(String intermediaryType) {
        this.intermediaryType = intermediaryType;
    }

    public String getTabFlag() {
        return tabFlag;
    }

    public void setTabFlag(String tabFlag) {
        this.tabFlag = tabFlag;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
    public String getQuasiListedLand() {
        return quasiListedLand;
    }

    public void setQuasiListedLand(String quasiListedLand) {
        this.quasiListedLand = quasiListedLand;
    }

    
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

    // demand 5955 所属板块
    private String stockCode;
    
    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }
}


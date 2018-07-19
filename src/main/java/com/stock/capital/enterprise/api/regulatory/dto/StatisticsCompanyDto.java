package com.stock.capital.enterprise.api.regulatory.dto;

import java.io.Serializable;

public class StatisticsCompanyDto implements Serializable{

    private static final long serialVersionUID = 6786653836833856457L;
    
    private String id;
    
    private String appCompany;
    
    private String registAddr;
    
    private String industry;
    
    private String ipoArea; 
    
    private String ipoAreaLabel;
    
    private String recommendOrg;
    
    private String accOffice;
    
    private String lawFirm;
    
    private String status;
    
    private String statusLabel;
    
    private String attend;
    
    private String attendLabel;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppCompany() {
        return appCompany;
    }

    public void setAppCompany(String appCompany) {
        this.appCompany = appCompany;
    }

    public String getRegistAddr() {
        return registAddr;
    }

    public void setRegistAddr(String registAddr) {
        this.registAddr = registAddr;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getIpoArea() {
        return ipoArea;
    }

    public void setIpoArea(String ipoArea) {
        this.ipoArea = ipoArea;
    }

    public String getIpoAreaLabel() {
        return ipoAreaLabel;
    }

    public void setIpoAreaLabel(String ipoAreaLabel) {
        this.ipoAreaLabel = ipoAreaLabel;
    }

    public String getRecommendOrg() {
        return recommendOrg;
    }

    public void setRecommendOrg(String recommendOrg) {
        this.recommendOrg = recommendOrg;
    }

    public String getAccOffice() {
        return accOffice;
    }

    public void setAccOffice(String accOffice) {
        this.accOffice = accOffice;
    }

    public String getLawFirm() {
        return lawFirm;
    }

    public void setLawFirm(String lawFirm) {
        this.lawFirm = lawFirm;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusLabel() {
        return statusLabel;
    }

    public void setStatusLabel(String statusLabel) {
        this.statusLabel = statusLabel;
    }

    public String getAttend() {
        return attend;
    }

    public void setAttend(String attend) {
        this.attend = attend;
    }

    public String getAttendLabel() {
        return attendLabel;
    }

    public void setAttendLabel(String attendLabel) {
        this.attendLabel = attendLabel;
    }

}


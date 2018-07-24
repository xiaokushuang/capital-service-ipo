package com.stock.capital.enterprise.regulatory.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PunishmentSearchDto implements Serializable {

    private static final long serialVersionUID = -5283286219691106934L;

    private String id;

    private String violateId;

    private String violateSort;

    private String companyCode;

    private String companyName;

    private String person;

    private String violatePersonIdtLabel;

    private List<String> violateTypeVOList;

    private List<String> punishTypeList;

    private String violateTypeVOLabel;

    private String punishTypeLabel;

    private Double confiscateAmount;

    private String confiscateAmountType;

    private Double punishAmount;

    private String punishAmountType;

    private Integer limitYears;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date updateTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date releaseTime;

    private String isRevoke;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getViolateId() {
        return violateId;
    }

    public void setViolateId(String violateId) {
        this.violateId = violateId;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getViolatePersonIdtLabel() {
        return violatePersonIdtLabel;
    }

    public void setViolatePersonIdtLabel(String violatePersonIdtLabel) {
        this.violatePersonIdtLabel = violatePersonIdtLabel;
    }

    public String getViolateTypeVOLabel() {
        return violateTypeVOLabel;
    }

    public void setViolateTypeVOLabel(String violateTypeVOLabel) {
        this.violateTypeVOLabel = violateTypeVOLabel;
    }

    public String getPunishTypeLabel() {
        return punishTypeLabel;
    }

    public void setPunishTypeLabel(String punishTypeLabel) {
        this.punishTypeLabel = punishTypeLabel;
    }

    public Double getConfiscateAmount() {
        return confiscateAmount;
    }

    public void setConfiscateAmount(Double confiscateAmount) {
        this.confiscateAmount = confiscateAmount;
    }

    public Double getPunishAmount() {
        return punishAmount;
    }

    public void setPunishAmount(Double punishAmount) {
        this.punishAmount = punishAmount;
    }

    public Integer getLimitYears() {
        return limitYears;
    }

    public void setLimitYears(Integer limitYears) {
        this.limitYears = limitYears;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getConfiscateAmountType() {
        return confiscateAmountType;
    }

    public void setConfiscateAmountType(String confiscateAmountType) {
        this.confiscateAmountType = confiscateAmountType;
    }

    public String getPunishAmountType() {
        return punishAmountType;
    }

    public void setPunishAmountType(String punishAmountType) {
        this.punishAmountType = punishAmountType;
    }

    public String getViolateSort() {
        return violateSort;
    }

    public void setViolateSort(String violateSort) {
        this.violateSort = violateSort;
    }

    public List<String> getViolateTypeVOList() {
        return violateTypeVOList;
    }

    public void setViolateTypeVOList(List<String> violateTypeVOList) {
        this.violateTypeVOList = violateTypeVOList;
    }

    public List<String> getPunishTypeList() {
        return punishTypeList;
    }

    public void setPunishTypeList(List<String> punishTypeList) {
        this.punishTypeList = punishTypeList;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getIsRevoke() {
        return isRevoke;
    }

    public void setIsRevoke(String isRevoke) {
        this.isRevoke = isRevoke;
    }

}

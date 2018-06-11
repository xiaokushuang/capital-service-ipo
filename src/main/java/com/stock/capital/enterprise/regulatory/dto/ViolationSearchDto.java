package com.stock.capital.enterprise.regulatory.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ViolationSearchDto implements Serializable {

    private static final long serialVersionUID = -5283286219691106934L;

    private String id;

    private String violateId;

    private String title;

    private String content;

    private String companyCode;

    private String companyName;

    private String violatePerson;

    private List<String> violateTypeVOList;

    private List<String> punishTypeList;

    private String punishTypeLabel;

    private String punishOrg;

    private String sort;

    private String adoptCondition;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date updateTime;

    private String updateTimeStr;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date releaseTime;

    private Boolean isFavour;
    
    /**
     * 父级行业-需求3752：违规案例提供导出功能-xuliuzhong
     */
    private String pIndustryName;
    
    /**
     * 父级地区-需求3752：违规案例提供导出功能-xuliuzhong
     */
    private String pComCityName;
    
    /**
     * 违规人身份-需求3752：违规案例提供导出功能-xuliuzhong
     */
    private List<String> violatePersonIdtList;
    
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getViolatePerson() {
        return violatePerson;
    }

    public void setViolatePerson(String violatePerson) {
        this.violatePerson = violatePerson;
    }

    public String getPunishTypeLabel() {
        return punishTypeLabel;
    }

    public void setPunishTypeLabel(String punishTypeLabel) {
        this.punishTypeLabel = punishTypeLabel;
    }

    public String getPunishOrg() {
        return punishOrg;
    }

    public void setPunishOrg(String punishOrg) {
        this.punishOrg = punishOrg;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getAdoptCondition() {
        return adoptCondition;
    }

    public void setAdoptCondition(String adoptCondition) {
        this.adoptCondition = adoptCondition;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getIsFavour() {
        return isFavour;
    }

    public void setIsFavour(Boolean isFavour) {
        this.isFavour = isFavour;
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

    public String getUpdateTimeStr() {
        return updateTimeStr;
    }

    public void setUpdateTimeStr(String updateTimeStr) {
        this.updateTimeStr = updateTimeStr;
    }

    public String getpIndustryName() {
        return pIndustryName;
    }

    public void setpIndustryName(String pIndustryName) {
        this.pIndustryName = pIndustryName;
    }

    public String getpComCityName() {
        return pComCityName;
    }

    public void setpComCityName(String pComCityName) {
        this.pComCityName = pComCityName;
    }

    public List<String> getViolatePersonIdtList() {
        return violatePersonIdtList;
    }

    public void setViolatePersonIdtList(List<String> violatePersonIdtList) {
        this.violatePersonIdtList = violatePersonIdtList;
    }

}

package com.stock.capital.enterprise.regulatory.dto;

import java.io.Serializable;
import java.util.List;

import com.stock.core.dto.OptionDto;
import com.stock.core.dto.TreeDto;

public class StatisticsReturnDto implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    //地区下拉列表
    private List<OptionDto> areaList;
    
    //所属行业下拉列表
    private List<TreeDto> industryList;
    
    //数量
    private int total;
    
    //保荐机构
    private List<StatisticsResultDto> recommendOrgSttsList;
    
    //律师事务所
    private List<StatisticsResultDto> LawFirmSttsList;
    
    //会计事务所
    private List<StatisticsResultDto> accountantOfficeSttsList;
    
    public int getTotal() {
        return total;
    }

    public List<StatisticsResultDto> getRecommendOrgSttsList() {
        return recommendOrgSttsList;
    }

    public List<StatisticsResultDto> getLawFirmSttsList() {
        return LawFirmSttsList;
    }

    public List<StatisticsResultDto> getAccountantOfficeSttsList() {
        return accountantOfficeSttsList;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setRecommendOrgSttsList(List<StatisticsResultDto> recommendOrgSttsList) {
        this.recommendOrgSttsList = recommendOrgSttsList;
    }

    public void setLawFirmSttsList(List<StatisticsResultDto> lawFirmSttsList) {
        LawFirmSttsList = lawFirmSttsList;
    }

    public void setAccountantOfficeSttsList(List<StatisticsResultDto> accountantOfficeSttsList) {
        this.accountantOfficeSttsList = accountantOfficeSttsList;
    }

    public List<OptionDto> getAreaList() {
        return areaList;
    }

    public List<TreeDto> getIndustryList() {
        return industryList;
    }

    public void setAreaList(List<OptionDto> areaList) {
        this.areaList = areaList;
    }

    public void setIndustryList(List<TreeDto> industryList) {
        this.industryList = industryList;
    }

}

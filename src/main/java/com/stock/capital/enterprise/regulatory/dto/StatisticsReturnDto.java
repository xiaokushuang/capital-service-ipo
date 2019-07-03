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

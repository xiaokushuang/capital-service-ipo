package com.stock.capital.enterprise.regulatory.dao;

import java.util.List;
import java.util.Map;

import com.stock.capital.enterprise.regulatory.dto.StatisticsResultDto;
import com.stock.core.dto.OptionDto;
import com.stock.core.dto.TreeDto;

public interface StatisticsBizMapper {
    
    //获取地区
    List<OptionDto> getAreaList();
    
    //获取行业
    List<TreeDto> postDeclareIndexIndustry();
    
    //获取截止时间
    String getIPOLastTime();
    
    //保荐机构
    List<StatisticsResultDto> getIPORecommendOrgStts(Map<String, Object> map);
    
    //律师事务所
    List<StatisticsResultDto> getIPOLawFirmStts(Map<String, Object> map);
    
    //会计师事务所
    List<StatisticsResultDto> getIPOAccountantOfficeStts(Map<String, Object> map);

}

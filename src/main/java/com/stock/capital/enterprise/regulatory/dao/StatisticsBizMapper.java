package com.stock.capital.enterprise.regulatory.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.stock.capital.enterprise.regulatory.dto.StatisticsCompanyDto;
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
    
    //保荐机构详情页数据
    List<StatisticsResultDto> queryCommendDetail(Map<String, Object> params);
    
    //律师事务所详情页数据
    List<StatisticsResultDto> queryLawDetail(Map<String, Object> params);
    
    //会计事务所详情页数据
    List<StatisticsResultDto> queryAccountDetail(Map<String, Object> params);
    
    //会计事务所详情页数据
    List<StatisticsResultDto> getIpoItemDataQuery(Map<String, Object> map);
    
    //ipo在审项目详情页数据
    List<StatisticsCompanyDto> getIpoItemDataDetail(Map<String, Object> map);
    
    // IPO
    List<StatisticsResultDto> getIPOReviewingStts(Map<String, Object> params);
    
    List<StatisticsResultDto> getIPOHistory(Map<String, Object> params);

    Map<String, Object> getIpoItemByCompanyCodeSelectId(@Param("companyCode") String companyCode);

}

/**
 *
 */
package com.stock.capital.enterprise.api.regulatory.dao;

import com.stock.capital.enterprise.api.regulatory.dto.StatisticsCompanyDto;
import com.stock.capital.enterprise.api.regulatory.dto.StatisticsParamDto;
import com.stock.capital.enterprise.api.regulatory.dto.StatisticsResultDto;
import com.stock.core.dto.OptionDto;

import java.util.List;
import java.util.Map;

public interface StatisticsBizMapper {

    // IPO
    List<StatisticsResultDto> getIPOReviewingStts(Map<String, Object> params);

    //需求4439 2018/5/24 by liuh Start
    //保荐机构
    List<StatisticsResultDto> getIPORecommendOrgStts(Map<String, Object> map);
    int getIPORecommendOrgSttsTotalCount(Map<String, Object> map);

    //会计师事务所
    List<StatisticsResultDto> getIPOAccountantOfficeStts(Map<String, Object> map);
    int getIPOAccountantOfficeSttsTotalCount(Map<String, Object> map);

    //律师事务所
    List<StatisticsResultDto> getIPOLawFirmStts(Map<String, Object> map);
    int getIPOLawFirmSttsTotalCount(Map<String, Object> map);
    //需求4439 2018/5/24 by liuh end

    String getIPOLastTime();

    List<StatisticsResultDto> getIPOHistory(Map<String, Object> params);
    
    // 再融资

    List<StatisticsResultDto> getRefinanceApproveStatusStts(Map<String, Object> params);

    List<StatisticsResultDto> getRefinanceAppTypeStts(Map<String, Object> params);

    List<StatisticsResultDto> getRefinanceRecommendOrgStts(Map<String, Object> params);

    String getRefinanceLastTime();

    List<StatisticsResultDto> getIPOAreaDataStts(StatisticsParamDto statisticsParamDto);

    List<OptionDto> getCodeAndName(String codeNo);

    List<OptionDto> getAreaList();

    List<StatisticsCompanyDto> queryAreaDetail(StatisticsParamDto statisticsParamDto);

}

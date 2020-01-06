package com.stock.capital.enterprise.api.financeStatistics.dao;

import java.util.List;

import com.stock.core.dto.TreeDto;

public interface FinanceStatisticsBizMapper {
    
    List<String> getProvincesMap();

	List<TreeDto> postDeclareIndexIndustry();

	List<TreeDto> postDeclareIndexIndustry(String financeIndustry);

}

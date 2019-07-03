package com.stock.capital.enterprise.regulatory.dao;

import java.util.List;

import com.stock.core.dto.OptionDto;
import com.stock.core.dto.TreeDto;

public interface StatisticsBizMapper {
    
    //获取地区
    List<OptionDto> getAreaList();
    
    //获取行业
    List<TreeDto> postDeclareIndexIndustry();

}

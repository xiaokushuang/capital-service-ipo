/**
 * 
 */
package com.stock.capital.enterprise.api.regulatory.dao;

import com.stock.capital.enterprise.api.regulatory.dto.RegulateinfoManageDto;
import com.stock.capital.enterprise.api.regulatory.dto.RegulatoryFavourDto;
import com.stock.core.dto.OptionDto;

import java.util.List;
import java.util.Map;

public interface RegulatoryBizMapper {

    List<OptionDto> getSourceList();

    List<RegulatoryFavourDto> getFavourList(Map<String, Object> params);

    int getFavourListTotalCount(Map<String, Object> params);

    RegulateinfoManageDto getRegulateinfoManage(String id);
}

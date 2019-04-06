package com.stock.capital.enterprise.ipoCase.dao;

import com.stock.capital.enterprise.ipoCase.dto.RegTreeDto;
import java.util.List;
import java.util.Map;

public interface IpoCaseListMapper {

    List<RegTreeDto> getTreeTagByCode(String labelCode);

    List<RegTreeDto> getLabelByCode(String labelCode);

    List<Map<String, Object>> queryIntermediary(String intermediaryName);

    int queryAuthByCompanyId(String companyId);
}
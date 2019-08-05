package com.stock.capital.enterprise.ipoCase.dao;

import com.stock.capital.enterprise.ipoCase.dto.IpoCaseListBo;
import com.stock.capital.enterprise.ipoCase.dto.RegTreeDto;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface IpoCaseListMapper {

    List<RegTreeDto> getTreeTagByCode(String labelCode);

    List<RegTreeDto> getLabelByCode(String labelCode);

    List<Map<String, Object>> queryIntermediary(String intermediaryName);

    int queryAuthByCompanyId(String companyId);

    IpoCaseListBo querylawId();

    List<String> queryIpoFavoriteList(@Param("companyId") String companyId,@Param("userId") String userId);
}
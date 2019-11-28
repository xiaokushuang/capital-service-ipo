package com.stock.capital.enterprise.ipoCase.dao;

import com.stock.capital.enterprise.ipoCase.dto.IssueDataDto;
import com.stock.capital.enterprise.ipoCase.dto.IssueFeeDto;
import com.stock.capital.enterprise.ipoCase.dto.StrategicPlacementMainDto;
import com.stock.capital.enterprise.ipoCase.dto.StrategicPlacementSubDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IpoCaseIssueMapper {

    List<IssueFeeDto> getIssueFeeData(String bid);

    /**
     * 从东财获取数据(如果自己数据库好用后期删除)
     * @param orgCode
     * @return
     */
    IssueDataDto getIssueData(String orgCode);

    /**
     * 从库获取数据
     * @param orgCode
     * @return
     */
    IssueDataDto getIssueDataFromLocal(String orgCode);

    StrategicPlacementMainDto getPlacementMainData(@Param("id") String id);

    List<StrategicPlacementSubDto> getPlacementSubData(@Param("id") String id);

    List<Map<String, Object>> getPriceAfterIssuance(@Param("processTime") String processTime, @Param("companyCode") String companyCode);
}
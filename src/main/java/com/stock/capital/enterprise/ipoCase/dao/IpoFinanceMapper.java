package com.stock.capital.enterprise.ipoCase.dao;

import com.stock.capital.enterprise.ipoCase.dto.IpoItemDto;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IpoFinanceMapper {

    /**
     * 查询财务信息 资产类项目
     */
    public List<IpoItemDto> selectFinanceList(@Param("id") String id, @Param("date") Date date);

    /**
     * 查询三年一期时间
     */
    Date getForthYear(String id);

    /**
     * 查询财务信息 负债类项目
     */
    List<IpoItemDto> selectDebtFinanceList(@Param("id") String id, @Param("date") Date date);

    /**
     * 查询财务信息 权益类项目
     */
    List<IpoItemDto> selectEquityItemList(@Param("id") String id, @Param("date") Date date);
}

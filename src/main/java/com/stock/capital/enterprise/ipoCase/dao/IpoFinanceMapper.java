package com.stock.capital.enterprise.ipoCase.dao;

import com.stock.capital.enterprise.ipoCase.dto.IpoCaseListVo;
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
    public List<IpoItemDto> selectFinanceList(IpoCaseListVo ipoCaseListVo);

    /**
     * 查询三年一期时间
     */
    Date getForthYear(String id);

    /**
     * 查询财务信息 负债类项目
     */
    List<IpoItemDto> selectDebtFinanceList(IpoCaseListVo ipoCaseListVo);

    /**
     * 查询财务信息 权益类项目
     */
    List<IpoItemDto> selectEquityItemList(IpoCaseListVo ipoCaseListVo);

    /**
     * 查询财务总体情况
     */
    List<IpoItemDto> selectFinanceOverList(IpoCaseListVo ipoCaseListVo);

    /**
     * 查询收入与利润情况
     */
    List<IpoItemDto> selectFinanceProfitList(IpoCaseListVo ipoCaseListVo);

    /**
     * 查询成本类项目财务情况
     */
    List<IpoItemDto> selectCostFinanceList(IpoCaseListVo ipoCaseListVo);

    /**
     * 查询利润类项目财务情况
     */
    List<IpoItemDto> selectReturnItemList(IpoCaseListVo ipoCaseListVo);

    /**
     * 查询主要财务指标
     */
    List<IpoItemDto> selectMainIndexList(IpoCaseListVo ipoCaseListVo);

    /**
     * 查询无形资产占净资产比例
     */
    List<IpoItemDto> selectRatio(IpoCaseListVo ipoCaseListVo);

    /**
     * 查询H5财务信息
     */
    List<IpoItemDto> selectFinanceOverListH5(@Param("id") String id, @Param("date") Date date);

    List<IpoItemDto> selectFinanceOverJxcList(IpoCaseListVo ipoCaseListVo);

    List<IpoItemDto> selectFinanceProfitJxcList(IpoCaseListVo ipoCaseListVo);

    List<IpoItemDto> selectFinanceJxcList(IpoCaseListVo ipoCaseListVo);

    List<IpoItemDto> selectMainIndexJxcList(IpoCaseListVo ipoCaseListVo);

    List<IpoItemDto> selectJxcRatio(IpoCaseListVo ipoCaseListVo);

    List<IpoItemDto> selectDebtFinanceJxcList(IpoCaseListVo ipoCaseListVo);

    List<IpoItemDto> selectEquityItemJxcList(IpoCaseListVo ipoCaseListVo);

    List<IpoItemDto> selectCostFinanceJxcList(IpoCaseListVo ipoCaseListVo);

    List<IpoItemDto> selectReturnItemJxcList(IpoCaseListVo ipoCaseListVo);
}

package com.stock.capital.enterprise.ipoCase.dao;

import com.stock.capital.enterprise.ipoCase.dto.*;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface IpoCaseBizMapper {

    CompanyOverviewVo getIpoCaseDetail(String id);

    Map<String, String> getCodeAndNameById(String id);

    List<OtherMarketInfoDto> getIpoMarketData(String bid);

    List<IpoSplitDto> getSpliteData(String bid);

    List<IpoValuationDto> getVluationData(String bid);

    List<IpoPersonInfoDto> getIpoShareData(String bid);

    List<MainCompetitorInfoDto> getIpoCompetitorData(String bid);

    List<SupplierCustomerMainDto> getSupplierOrCustomerData(
        @Param("bid") String bid, @Param("mainType") String mainType);

    List<MainIncomeInfoDto> getIncomeData(String bid);

    List<IpoTechnologyPatentDto> getCompetitorData(@Param("bid") String bid);

    List<IpoTechnologyTableDto> getDevCompute(@Param("bid") String bid);

    List<IpoTechnologyTableDto> getCoreCompute(@Param("bid") String bid);

    List<IpoTechnologyDateDto> getDate(@Param("bid") String bid);

    List<Map> selectTechnologyByBid(@Param("bid") String bid);

    List<Map<String, Integer>> selectPatentById(@Param("id") String id);

    IpoTechnologyRemarksDto getRemarks(@Param("bid") String bid);

    List<IntermediaryOrgDto> getIntermediaryOrgData(
        @Param("bid") String bid, @Param("validFlag") String validFlag);

    HeadDataVo getHeadData(String id);

    List<IpoAssociatedCaseVo> getAssociatedCaseList(@Param("id") String id);

    Map<String, String> getCaseFavoriteAndNote(Map<String, String> map);

    /**
     * 估算费用类型
     * @param id
     * @return
     */
    String getEstimateType(@Param("id") String id);

    List<OtherMarketInfoDto> getOtcData(String id);

    List<IpoCaseListVo> queryCompanyForFin(String id);
}
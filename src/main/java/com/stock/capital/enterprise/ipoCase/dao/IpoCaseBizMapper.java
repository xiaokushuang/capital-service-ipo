package com.stock.capital.enterprise.ipoCase.dao;

import com.stock.capital.enterprise.ipoCase.dto.CompanyOverviewVo;
import com.stock.capital.enterprise.ipoCase.dto.HeadDataVo;
import com.stock.capital.enterprise.ipoCase.dto.IntermediaryOrgDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoAssociatedCaseVo;
import com.stock.capital.enterprise.ipoCase.dto.IpoPersonInfoDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoSplitDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoTechnologyDateDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoTechnologyPatentDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoTechnologyRemarksDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoTechnologyTableDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoValuationDto;
import com.stock.capital.enterprise.ipoCase.dto.MainCompetitorInfoDto;
import com.stock.capital.enterprise.ipoCase.dto.MainIncomeInfoDto;
import com.stock.capital.enterprise.ipoCase.dto.OtherMarketInfoDto;
import com.stock.capital.enterprise.ipoCase.dto.SupplierCustomerMainDto;
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
}
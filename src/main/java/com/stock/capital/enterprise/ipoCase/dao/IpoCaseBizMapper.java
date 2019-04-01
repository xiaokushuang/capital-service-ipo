package com.stock.capital.enterprise.ipoCase.dao;

import com.stock.capital.enterprise.ipoCase.dto.CompanyOverviewVo;
import com.stock.capital.enterprise.ipoCase.dto.HeadDataVo;
import com.stock.capital.enterprise.ipoCase.dto.IntermediaryOrgDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoPersonInfoDto;
import com.stock.capital.enterprise.ipoCase.dto.MainCompetitorInfoDto;
import com.stock.capital.enterprise.ipoCase.dto.MainIncomeInfoDto;
import com.stock.capital.enterprise.ipoCase.dto.OtherMarketInfoDto;
import com.stock.capital.enterprise.ipoCase.dto.SupplierCustomerMainDto;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IpoCaseBizMapper {

    CompanyOverviewVo getIpoCaseDetail(String id);

    String getCodeById(String id);

    List<OtherMarketInfoDto> getIpoMarketData(String bid);

    List<IpoPersonInfoDto> getIpoShareData(String bid);

    List<MainCompetitorInfoDto> getIpoCompetitorData(String bid);

    List<SupplierCustomerMainDto> getSupplierOrCustomerData(
        @Param("bid") String bid, @Param("mainType") String mainType);

    List<MainIncomeInfoDto> getIncomeData(String bid);

    List<IntermediaryOrgDto> getIntermediaryOrgData(
        @Param("bid") String bid, @Param("validFlag") String validFlag);

    HeadDataVo getHeadData(String id);

}
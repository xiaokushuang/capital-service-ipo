package com.stock.capital.enterprise.ipoCoachCase.dao;

import com.stock.capital.enterprise.ipoCase.dto.IpoCaseIndexDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoCaseListVo;
import com.stock.capital.enterprise.ipoCoachCase.dto.IntermediaryOrgDto;
import com.stock.capital.enterprise.ipoCoachCase.dto.IpoCoachCaseDto;
import com.stock.capital.enterprise.ipoCoachCase.dto.OtherMarketInfoDto;
import com.stock.capital.enterprise.ipoInterfaceH5.dto.IpoH5CoreDevDto;
import com.stock.capital.enterprise.ipoInterfaceH5.dto.IpoH5DetailDto;
import com.stock.capital.enterprise.ipoInterfaceH5.dto.IpoH5Dto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface IpoCoachCaseDetailBizMapper {

    IpoCoachCaseDto queryCoachBasicInfo(String id);

    List<IntermediaryOrgDto> selectOrgByBid(String bid);

    List<OtherMarketInfoDto> selectMarketByBid(String bid);
}

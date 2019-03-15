package com.stock.capital.enterprise.ipoCase.dao;

import com.stock.capital.enterprise.ipoCase.dto.IndustryCompareRateDto;
import java.util.List;

public interface IpoIndustryRateBizMapper {

    List<IndustryCompareRateDto> selectIndustryRateByBid(String bid);
}
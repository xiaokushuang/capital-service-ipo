package com.stock.capital.enterprise.ipoCase.dao;

import com.stock.capital.enterprise.ipoCase.dto.IssueDataDto;
import com.stock.capital.enterprise.ipoCase.dto.IssueFeeDto;
import java.util.List;

public interface IpoCaseIssueMapper {

    List<IssueFeeDto> getIssueFeeData(String bid);

    IssueDataDto getIssueData(String orgCode);
}
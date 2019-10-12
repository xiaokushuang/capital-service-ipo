package com.stock.capital.enterprise.ipoCase.dao;

import com.stock.capital.enterprise.ipoCase.dto.IssueDataDto;
import com.stock.capital.enterprise.ipoCase.dto.IssueFeeDto;
import java.util.List;

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
}
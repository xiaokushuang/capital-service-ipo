package com.stock.capital.enterprise.ipoCase.service;

import com.stock.capital.enterprise.ipoCase.dao.IpoCaseIssueMapper;
import com.stock.capital.enterprise.ipoCase.dto.IssueDataDto;
import com.stock.capital.enterprise.ipoCase.dto.IssueFeeDto;
import com.stock.core.dao.DynamicDataSourceHolder;
import com.stock.core.service.BaseService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IssueSituationService extends BaseService {

    @Autowired
    private IpoCaseIssueMapper ipoCaseIssueMapper;

    /**
     * 发行数据
     *
     * @param orgCode 东财内码
     * @return dto
     */
    public IssueDataDto getIssueData(String orgCode) {
        DynamicDataSourceHolder.setDataSource("dongcai");
        IssueDataDto issueDataDto = ipoCaseIssueMapper.getIssueData(orgCode);
        DynamicDataSourceHolder.cleanDataSource();
        return issueDataDto;
    }

    /**
     * 发行费用
     *
     * @param id 案例id
     * @return list
     */
    public List<IssueFeeDto> getIssueFeeData(String id) {
        return ipoCaseIssueMapper.getIssueFeeData(id);
    }


}

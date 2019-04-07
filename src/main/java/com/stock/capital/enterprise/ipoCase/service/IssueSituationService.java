package com.stock.capital.enterprise.ipoCase.service;

import com.stock.capital.enterprise.ipoCase.dao.IpoCaseBizMapper;
import com.stock.capital.enterprise.ipoCase.dao.IpoCaseIssueMapper;
import com.stock.capital.enterprise.ipoCase.dao.IpoIndustryRateBizMapper;
import com.stock.capital.enterprise.ipoCase.dto.IndustryCompareRateDetailDto;
import com.stock.capital.enterprise.ipoCase.dto.IndustryCompareRateDto;
import com.stock.capital.enterprise.ipoCase.dto.IssueDataDto;
import com.stock.capital.enterprise.ipoCase.dto.IssueFeeDto;
import com.stock.core.dao.DynamicDataSourceHolder;
import com.stock.core.service.BaseService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IssueSituationService extends BaseService {

    @Autowired
    private IpoCaseIssueMapper ipoCaseIssueMapper;

    @Autowired
    private IpoIndustryRateBizMapper ipoIndustryRateBizMapper;

    @Autowired
    private IpoCaseBizMapper ipoCaseBizMapper;

    /**
     * 发行数据
     *
     * @param id 案例id
     * @return dto
     */
    public IssueDataDto getIssueData(String id) {
        String orgCode = ipoCaseBizMapper.getCodeById(id);
        if (StringUtils.isBlank(orgCode)) {
            return null;
        }
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
        List<IssueFeeDto> issueFeeList = ipoCaseIssueMapper.getIssueFeeData(id);
        if (issueFeeList != null && !issueFeeList.isEmpty()) {
            BigDecimal feeAmountS = BigDecimal.ZERO;
            BigDecimal feeRatioS = BigDecimal.ZERO;
            for (IssueFeeDto issueFeeDto : issueFeeList) {
                if (issueFeeDto.getFeeAmount() != null) {
                    feeAmountS = feeAmountS.add(issueFeeDto.getFeeAmount());
                }
                if (issueFeeDto.getFeeRatio() != null) {
                    feeRatioS = feeRatioS.add(issueFeeDto.getFeeRatio());
                }
            }
            IssueFeeDto feeDto = new IssueFeeDto();
            feeDto.setFeeType("合计");
            feeDto.setFeeAmount(feeAmountS);
            feeDto.setFeeRatio(feeRatioS);
            issueFeeList.add(feeDto);
        }
        return issueFeeList;
    }

    /**
     * 同行业上市公司综合毛利率
     *
     * @param id 案例id
     * @return list
     */
    public List<IndustryCompareRateDto> getIndustryRateData(String id) {
        List<IndustryCompareRateDto> industryCompareRateList =
            ipoIndustryRateBizMapper.selectIndustryRateByBid(id);
        if (industryCompareRateList != null && !industryCompareRateList.isEmpty()) {
            for (IndustryCompareRateDto industryCompareRateDto : industryCompareRateList) {
                int lastYear = Integer.valueOf(industryCompareRateDto.getReportPeriod());
                industryCompareRateDto.setThirdYear(industryCompareRateDto.getReportPeriod() + "年");
                industryCompareRateDto.setSecondYear((lastYear - 1) + "年");
                industryCompareRateDto.setFirstYear((lastYear - 2) + "年");
                List<IndustryCompareRateDetailDto> detailList = new ArrayList<>();
                if (industryCompareRateDto.getIndustryCompareRateDetailList() != null
                    && !industryCompareRateDto.getIndustryCompareRateDetailList().isEmpty()) {
                    detailList.addAll(industryCompareRateDto.getIndustryCompareRateDetailList());
                    IndustryCompareRateDetailDto averageDetailDto =
                        new IndustryCompareRateDetailDto();
                    averageDetailDto.setCompanyName("平均值");
                    averageDetailDto.setThirdYearRate(industryCompareRateDto.getThirdAvg());
                    averageDetailDto.setSecondYearRate(industryCompareRateDto.getSecondAvg());
                    averageDetailDto.setFirstYearRate(industryCompareRateDto.getFirstAvg());
                    detailList.add(averageDetailDto);
                }
                IndustryCompareRateDetailDto selfDetailDto = new IndustryCompareRateDetailDto();
                selfDetailDto.setCompanyName(industryCompareRateDto.getCompanyName());
                selfDetailDto.setThirdYearRate(industryCompareRateDto.getThirdYearRate());
                selfDetailDto.setSecondYearRate(industryCompareRateDto.getSecondYearRate());
                selfDetailDto.setFirstYearRate(industryCompareRateDto.getFirstYearRate());
                detailList.add(selfDetailDto);
                industryCompareRateDto.setIndustryCompareRateDetailList(detailList);
            }
        }
        return industryCompareRateList;
    }
}

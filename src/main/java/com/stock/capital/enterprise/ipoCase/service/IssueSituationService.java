package com.stock.capital.enterprise.ipoCase.service;

import com.stock.capital.enterprise.ipoCase.dao.IpoCaseBizMapper;
import com.stock.capital.enterprise.ipoCase.dao.IpoCaseIssueMapper;
import com.stock.capital.enterprise.ipoCase.dao.IpoIndustryRateBizMapper;
import com.stock.capital.enterprise.ipoCase.dto.*;
import com.stock.core.service.BaseService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        Map<String, String> resultMap = ipoCaseBizMapper.getCodeAndNameById(id);
        if (resultMap == null) {
            return null;
        }
        String orgCode = resultMap.get("orgCode");
        if (StringUtils.isBlank(orgCode)) {
            return null;
        }
//        DynamicDataSourceHolder.setDataSource("dongcai");
//        IssueDataDto issueDataDto1 = ipoCaseIssueMapper.getIssueData(orgCode);
//        DynamicDataSourceHolder.cleanDataSource();
        IssueDataDto issueDataDto = ipoCaseIssueMapper.getIssueDataFromLocal(orgCode);//测试案例org_code  10092703
//        禅道 - 10425 - yangj 增加发行前股本，发行后股本
//        根据id获取发行前股本 即 注册资本
        CompanyOverviewVo ipoCaseDetail = ipoCaseBizMapper.getIpoCaseDetail(id);
        if (issueDataDto != null && ipoCaseDetail != null && ipoCaseDetail.getRegisteredAssets() != null) {
            issueDataDto.setPreIssueNum(ipoCaseDetail.getRegisteredAssets());
            if (issueDataDto.getShareIssued() != null) {
                issueDataDto.setNextIssueNum(ipoCaseDetail.getRegisteredAssets().add(issueDataDto.getShareIssued()));
            }
        }
        return issueDataDto;
    }

    public String getEstimateType(String id) {
        return ipoCaseBizMapper.getEstimateType(id);
    }

    /**
     * 发行费用
     *
     * @param id 案例id
     * @return list
     */
    public List<IssueFeeDto> getIssueFeeData(String id) {

        List<IssueFeeDto> issueFeeList = ipoCaseIssueMapper.getIssueFeeData(id);
        IssueDataDto issueData = getIssueData(id);
        BigDecimal sumFina = null;
        if (issueData != null) {
            sumFina = issueData.getSumFina();
//        原本的计算比例错误，从这里获取后从新计算并赋值
            for (IssueFeeDto issueFeeDatum : issueFeeList) {
                if (sumFina != null) {

                    BigDecimal feeAmount = issueFeeDatum.getFeeAmount();
                    issueFeeDatum.setFeeRatio(feeAmount.multiply(new BigDecimal(100)).divide(sumFina, 4, BigDecimal.ROUND_HALF_DOWN));
                }
            }
        }
        if (issueFeeList != null && !issueFeeList.isEmpty()) {
            BigDecimal feeAmountS = BigDecimal.ZERO;
            BigDecimal feeRatioS = BigDecimal.ZERO;
            for (IssueFeeDto issueFeeDto : issueFeeList) {
                if (issueFeeDto.getFeeAmount() != null) {
                    feeAmountS = feeAmountS.add(issueFeeDto.getFeeAmount());
                }
                if (sumFina != null) {
                    if (issueFeeDto.getFeeRatio() != null && sumFina != null) {
                        feeRatioS = feeRatioS.add(issueFeeDto.getFeeRatio());
                    }
                } else {
                    issueFeeDto.setFeeRatio(null);
                }
            }
            IssueFeeDto feeDto = new IssueFeeDto();
            feeDto.setFeeType("合计");
            if (sumFina != null) {
                feeDto.setFeeRatio(feeRatioS);
            } else {
                feeDto.setFeeRatio(null);
            }
            feeDto.setFeeAmount(feeAmountS);
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
                //重新查询 企业简称
                Map<String, String> resultMap = ipoCaseBizMapper.getCodeAndNameById(id);
                if (resultMap != null) {
                    String companyName = resultMap.get("companyName");
                    if (StringUtils.isNotBlank(companyName)) {
                        IndustryCompareRateDetailDto selfDetailDto =
                                new IndustryCompareRateDetailDto();
                        selfDetailDto.setCompanyName(companyName);
                        selfDetailDto.setThirdYearRate(industryCompareRateDto.getThirdYearRate());
                        selfDetailDto.setSecondYearRate(industryCompareRateDto.getSecondYearRate());
                        selfDetailDto.setFirstYearRate(industryCompareRateDto.getFirstYearRate());
                        detailList.add(selfDetailDto);
                    }
                }
                industryCompareRateDto.setIndustryCompareRateDetailList(detailList);
            }
        }
        return industryCompareRateList;
    }

    //  获取战略配售情况数据
    public StrategicPlacementMainDto getPlacementData(String id) {
        IssueDataDto issueData = getIssueData(id);
        StrategicPlacementMainDto mainDto = ipoCaseIssueMapper.getPlacementMainData(id);
        if (mainDto != null && StringUtils.isNotEmpty(mainDto.getId())) {
            mainDto.setSubs(ipoCaseIssueMapper.getPlacementSubData(mainDto.getId()));
            for (StrategicPlacementSubDto sub : mainDto.getSubs()) {
                if (issueData!= null && issueData.getNextIssueNum() != null && sub.getAllottedNumberTenThousand() != null) {
                    sub.setRadio(sub.getAllottedNumberTenThousand().divide(issueData.getNextIssueNum(), 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)));
                } else {
                    sub.setRadio(null);
                }
            }
        }
        return mainDto;
    }

    //    获取发行后股价数据
    public List<Map<String, Object>> getPriceAfterIssuance(String processTime, String companyCode) {
        return ipoCaseIssueMapper.getPriceAfterIssuance(processTime, companyCode);
    }
}

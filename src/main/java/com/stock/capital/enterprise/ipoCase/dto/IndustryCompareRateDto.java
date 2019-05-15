package com.stock.capital.enterprise.ipoCase.dto;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * IndustryCompareRateDto.
 *
 * @author chenzhengxiu.
 * @version 0.0.1.
 * @serial 2019/3/13 : base version.
 */
public class IndustryCompareRateDto implements Serializable {

    private static final long serialVersionUID = 4056930669848069244L;

    @ApiModelProperty("主键")
    private String id;

    @ApiModelProperty("案例id")
    private String bid;

    @ApiModelProperty("报告期终止时间")
    private String reportPeriod;

    @ApiModelProperty("发行人公司代码")
    private String companyCode;

    @ApiModelProperty("发行人证券简称")
    private String companyName;

    @ApiModelProperty("第一年毛利率")
    private BigDecimal firstYearRate;

    @ApiModelProperty("第二年毛利率")
    private BigDecimal secondYearRate;

    @ApiModelProperty("第三年毛利率")
    private BigDecimal thirdYearRate;
    
    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("同行业公司毛利率列表")
    private List<IndustryCompareRateDetailDto> industryCompareRateDetailList;

    @ApiModelProperty("第一年")
    private String firstYear;

    @ApiModelProperty("第二年")
    private String secondYear;

    @ApiModelProperty("第三年")
    private String thirdYear;

    @ApiModelProperty("第一年毛利率平均值")
    private BigDecimal firstAvg;

    @ApiModelProperty("第二年毛利率平均值")
    private BigDecimal secondAvg;

    @ApiModelProperty("第三年毛利率平均值")
    private BigDecimal thirdAvg;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getReportPeriod() {
        return reportPeriod;
    }

    public void setReportPeriod(String reportPeriod) {
        this.reportPeriod = reportPeriod;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public BigDecimal getFirstYearRate() {
        return firstYearRate;
    }

    public void setFirstYearRate(BigDecimal firstYearRate) {
        this.firstYearRate = firstYearRate;
    }

    public BigDecimal getSecondYearRate() {
        return secondYearRate;
    }

    public void setSecondYearRate(BigDecimal secondYearRate) {
        this.secondYearRate = secondYearRate;
    }

    public BigDecimal getThirdYearRate() {
        return thirdYearRate;
    }

    public void setThirdYearRate(BigDecimal thirdYearRate) {
        this.thirdYearRate = thirdYearRate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<IndustryCompareRateDetailDto> getIndustryCompareRateDetailList() {
        return industryCompareRateDetailList;
    }

    public void setIndustryCompareRateDetailList(
        List<IndustryCompareRateDetailDto> industryCompareRateDetailList) {
        this.industryCompareRateDetailList = industryCompareRateDetailList;
    }

    public String getFirstYear() {
        return firstYear;
    }

    public void setFirstYear(String firstYear) {
        this.firstYear = firstYear;
    }

    public String getSecondYear() {
        return secondYear;
    }

    public void setSecondYear(String secondYear) {
        this.secondYear = secondYear;
    }

    public String getThirdYear() {
        return thirdYear;
    }

    public void setThirdYear(String thirdYear) {
        this.thirdYear = thirdYear;
    }

    public BigDecimal getFirstAvg() {
        return firstAvg;
    }

    public void setFirstAvg(BigDecimal firstAvg) {
        this.firstAvg = firstAvg;
    }

    public BigDecimal getSecondAvg() {
        return secondAvg;
    }

    public void setSecondAvg(BigDecimal secondAvg) {
        this.secondAvg = secondAvg;
    }

    public BigDecimal getThirdAvg() {
        return thirdAvg;
    }

    public void setThirdAvg(BigDecimal thirdAvg) {
        this.thirdAvg = thirdAvg;
    }
}

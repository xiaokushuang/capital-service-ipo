package com.stock.capital.enterprise.ipoCase.dto;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * IndustryCompareRateDetailDto.
 *
 * @author chenzhengxiu.
 * @version 0.0.1.
 * @serial 2019/3/13 : base version.
 */
public class IndustryCompareRateDetailDto implements Serializable {

    private static final long serialVersionUID = 7053102205303152108L;

    @ApiModelProperty("主键")
    private String id;

    @ApiModelProperty("毛利率主表id")
    private String bid;

    @ApiModelProperty("公司代码")
    private String companyCode;

    @ApiModelProperty("证券简称")
    private String companyName;

    @ApiModelProperty("第一年毛利率")
    private BigDecimal firstYearRate;

    @ApiModelProperty("第二年毛利率")
    private BigDecimal secondYearRate;

    @ApiModelProperty("第三年毛利率")
    private BigDecimal thirdYearRate;

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
}

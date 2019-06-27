package com.stock.capital.enterprise.ipoCase.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("财务信息类")
public class IpoItemDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("项目名称")
    private String itemName;

    @ApiModelProperty("第一年财务数据")
    private BigDecimal firstYearValue;

    @ApiModelProperty("第二年财务数据")
    private BigDecimal secondYearValue;

    @ApiModelProperty("第三年期财务数据")
    private BigDecimal thirdYearValue;

    @ApiModelProperty("最近一期财务数据")
    private BigDecimal forthYearValue;

    @ApiModelProperty("近三年复合增长率")
    private BigDecimal growthRate;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public BigDecimal getFirstYearValue() {
        return firstYearValue;
    }

    public void setFirstYearValue(BigDecimal firstYearValue) {
        this.firstYearValue = firstYearValue;
    }

    public BigDecimal getSecondYearValue() {
        return secondYearValue;
    }

    public void setSecondYearValue(BigDecimal secondYearValue) {
        this.secondYearValue = secondYearValue;
    }

    public BigDecimal getThirdYearValue() {
        return thirdYearValue;
    }

    public void setThirdYearValue(BigDecimal thirdYearValue) {
        this.thirdYearValue = thirdYearValue;
    }

    public BigDecimal getForthYearValue() {
        return forthYearValue;
    }

    public void setForthYearValue(BigDecimal forthYearValue) {
        this.forthYearValue = forthYearValue;
    }

    public BigDecimal getGrowthRate() {
        return growthRate;
    }

    public void setGrowthRate(BigDecimal growthRate) {
        this.growthRate = growthRate;
    }
}

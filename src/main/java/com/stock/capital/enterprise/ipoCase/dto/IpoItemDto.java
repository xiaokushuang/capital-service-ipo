package com.stock.capital.enterprise.ipoCase.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("财务信息类")
public class IpoItemDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("项目名称")
    private String itemName;

    @ApiModelProperty("第一年财务数据")
    private String firstYearValue;

    @ApiModelProperty("第二年财务数据")
    private String secondYearValue;

    @ApiModelProperty("第三年期财务数据")
    private String thirdYearValue;

    @ApiModelProperty("最近一期财务数据")
    private String forthYearValue;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getFirstYearValue() {
        return firstYearValue;
    }

    public void setFirstYearValue(String firstYearValue) {
        this.firstYearValue = firstYearValue;
    }

    public String getSecondYearValue() {
        return secondYearValue;
    }

    public void setSecondYearValue(String secondYearValue) {
        this.secondYearValue = secondYearValue;
    }

    public String getThirdYearValue() {
        return thirdYearValue;
    }

    public void setThirdYearValue(String thirdYearValue) {
        this.thirdYearValue = thirdYearValue;
    }

    public String getForthYearValue() {
        return forthYearValue;
    }

    public void setForthYearValue(String forthYearValue) {
        this.forthYearValue = forthYearValue;
    }
}

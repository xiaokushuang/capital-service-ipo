package com.stock.capital.enterprise.ipoCase.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("财务信息日期类")
public class IpoFinanceDateDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("第一年日期")
    private String firstYearDate;

    @ApiModelProperty("第二年日期")
    private String secondYearDate;

    @ApiModelProperty("第三年日期")
    private String thirdYearDate;

    @ApiModelProperty("第四年日期")
    private String forthYearDate;

    public String getFirstYearDate() {
        return firstYearDate;
    }

    public void setFirstYearDate(String firstYearDate) {
        this.firstYearDate = firstYearDate;
    }

    public String getSecondYearDate() {
        return secondYearDate;
    }

    public void setSecondYearDate(String secondYearDate) {
        this.secondYearDate = secondYearDate;
    }

    public String getThirdYearDate() {
        return thirdYearDate;
    }

    public void setThirdYearDate(String thirdYearDate) {
        this.thirdYearDate = thirdYearDate;
    }

    public String getForthYearDate() {
        return forthYearDate;
    }

    public void setForthYearDate(String forthYearDate) {
        this.forthYearDate = forthYearDate;
    }
}

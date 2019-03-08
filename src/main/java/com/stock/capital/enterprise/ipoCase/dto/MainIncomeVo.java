package com.stock.capital.enterprise.ipoCase.dto;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.List;

/**
 * MainIncomeVo.
 *
 * @author chenzhengxiu.
 * @version 0.0.1.
 * @serial 2019/3/7 : base version.
 */
public class MainIncomeVo implements Serializable {

    private static final long serialVersionUID = -1130911949823936965L;

    @ApiModelProperty("主营业务收入-一期")
    private String onePeriodForIncome;//主营业务收入-一期

    @ApiModelProperty("主营业务收入-第三年")
    private String thirdYearForIncome;//主营业务收入-第三年

    @ApiModelProperty("主营业务收入-第二年")
    private String secondYearForIncome;//主营业务收入-第二年

    @ApiModelProperty("主营业务收入-第一年")
    private String firstYearForIncome;//主营业务收入-第一年

    @ApiModelProperty("主营业务收入构成列表")
    private List<MainIncomeInfoDto> mainIncomeInfoList; //主营业务收入构成

    public String getOnePeriodForIncome() {
        return onePeriodForIncome;
    }

    public void setOnePeriodForIncome(String onePeriodForIncome) {
        this.onePeriodForIncome = onePeriodForIncome;
    }

    public String getThirdYearForIncome() {
        return thirdYearForIncome;
    }

    public void setThirdYearForIncome(String thirdYearForIncome) {
        this.thirdYearForIncome = thirdYearForIncome;
    }

    public String getSecondYearForIncome() {
        return secondYearForIncome;
    }

    public void setSecondYearForIncome(String secondYearForIncome) {
        this.secondYearForIncome = secondYearForIncome;
    }

    public String getFirstYearForIncome() {
        return firstYearForIncome;
    }

    public void setFirstYearForIncome(String firstYearForIncome) {
        this.firstYearForIncome = firstYearForIncome;
    }

    public List<MainIncomeInfoDto> getMainIncomeInfoList() {
        return mainIncomeInfoList;
    }

    public void setMainIncomeInfoList(
        List<MainIncomeInfoDto> mainIncomeInfoList) {
        this.mainIncomeInfoList = mainIncomeInfoList;
    }
}

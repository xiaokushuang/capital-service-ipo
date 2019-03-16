package com.stock.capital.enterprise.ipoCase.dto;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("财务信息列表类")
public class IpoFinanceDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("第一年日期")
    private String firstYearDate;

    @ApiModelProperty("第二年日期")
    private String secondYearDate;

    @ApiModelProperty("第三年日期")
    private String thirdYearDate;

    @ApiModelProperty("第四年日期")
    private String forthYearDate;

    @ApiModelProperty("资产类项目列表")
    private List<IpoItemDto> IpoAssetItemList;

    @ApiModelProperty("负债类项目列表")
    private List<IpoItemDto> IpoDebtItemList;

    @ApiModelProperty("权益类项目列表")
    private List<IpoItemDto> IpoEquityItemList;

    @ApiModelProperty("财务总体情况项目列表")
    private List<IpoItemDto> IpoFinanceOverList;

    @ApiModelProperty("收益类项目列表")
    private List<IpoItemDto> IpoProfitItemList;

    @ApiModelProperty("成本类项目列表")
    private List<IpoItemDto> IpoCostItemList;

    @ApiModelProperty("利润类项目列表")
    private List<IpoItemDto> IpoReturnOverList;


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

    public List<IpoItemDto> getIpoAssetItemList() {
        return IpoAssetItemList;
    }

    public void setIpoAssetItemList(List<IpoItemDto> ipoAssetItemList) {
        IpoAssetItemList = ipoAssetItemList;
    }

    public List<IpoItemDto> getIpoDebtItemList() {
        return IpoDebtItemList;
    }

    public void setIpoDebtItemList(List<IpoItemDto> ipoDebtItemList) {
        IpoDebtItemList = ipoDebtItemList;
    }

    public List<IpoItemDto> getIpoEquityItemList() {
        return IpoEquityItemList;
    }

    public void setIpoEquityItemList(List<IpoItemDto> ipoEquityItemList) {
        IpoEquityItemList = ipoEquityItemList;
    }

    public List<IpoItemDto> getIpoFinanceOverList() {
        return IpoFinanceOverList;
    }

    public void setIpoFinanceOverList(List<IpoItemDto> ipoFinanceOverList) {
        IpoFinanceOverList = ipoFinanceOverList;
    }

    public List<IpoItemDto> getIpoProfitItemList() {
        return IpoProfitItemList;
    }

    public void setIpoProfitItemList(List<IpoItemDto> ipoProfitItemList) {
        IpoProfitItemList = ipoProfitItemList;
    }

    public List<IpoItemDto> getIpoCostItemList() {
        return IpoCostItemList;
    }

    public void setIpoCostItemList(List<IpoItemDto> ipoCostItemList) {
        IpoCostItemList = ipoCostItemList;
    }

    public List<IpoItemDto> getIpoReturnOverList() {
        return IpoReturnOverList;
    }

    public void setIpoReturnOverList(List<IpoItemDto> ipoReturnOverList) {
        IpoReturnOverList = ipoReturnOverList;
    }
}

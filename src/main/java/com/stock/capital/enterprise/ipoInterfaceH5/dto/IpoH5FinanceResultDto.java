package com.stock.capital.enterprise.ipoInterfaceH5.dto;

import com.stock.capital.enterprise.ipoCase.dto.IpoFinanceDateDto;

import java.io.Serializable;
import java.util.List;

public class IpoH5FinanceResultDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private List<IpoFinanceH5Dto> incomeList;

    private List<IpoFinanceH5Dto> grossList;

    private List<IpoFinanceH5Dto> profitList;

    private List<IpoFinanceH5Dto> profitRateList;

    private List<IpoFinanceH5Dto> mainIndexList;

    private List<IpoFinanceH5Dto> cashFlowList;

    private List<IpoFinanceH5Dto> cashList;

    private List<IpoH5FinanceListDto> debtList;

    private IpoFinanceH5Dto incomeRate;

    private IpoFinanceH5Dto profitRate;

    private IpoFinanceH5Dto sumAssetRate;

    private IpoFinanceH5Dto cashFlowRate;

    private IpoFinanceH5Dto cashRate;

    private IpoFinanceDateDto dateList;






    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<IpoFinanceH5Dto> getIncomeList() {
        return incomeList;
    }

    public void setIncomeList(List<IpoFinanceH5Dto> incomeList) {
        this.incomeList = incomeList;
    }

    public List<IpoFinanceH5Dto> getGrossList() {
        return grossList;
    }

    public void setGrossList(List<IpoFinanceH5Dto> grossList) {
        this.grossList = grossList;
    }

    public List<IpoFinanceH5Dto> getProfitList() {
        return profitList;
    }

    public void setProfitList(List<IpoFinanceH5Dto> profitList) {
        this.profitList = profitList;
    }

    public List<IpoFinanceH5Dto> getProfitRateList() {
        return profitRateList;
    }

    public void setProfitRateList(List<IpoFinanceH5Dto> profitRateList) {
        this.profitRateList = profitRateList;
    }

    public List<IpoFinanceH5Dto> getMainIndexList() {
        return mainIndexList;
    }

    public void setMainIndexList(List<IpoFinanceH5Dto> mainIndexList) {
        this.mainIndexList = mainIndexList;
    }

    public List<IpoH5FinanceListDto> getDebtList() {
        return debtList;
    }

    public void setDebtList(List<IpoH5FinanceListDto> debtList) {
        this.debtList = debtList;
    }

    public IpoFinanceH5Dto getIncomeRate() {
        return incomeRate;
    }

    public void setIncomeRate(IpoFinanceH5Dto incomeRate) {
        this.incomeRate = incomeRate;
    }

    public IpoFinanceH5Dto getProfitRate() {
        return profitRate;
    }

    public void setProfitRate(IpoFinanceH5Dto profitRate) {
        this.profitRate = profitRate;
    }

    public IpoFinanceH5Dto getSumAssetRate() {
        return sumAssetRate;
    }

    public void setSumAssetRate(IpoFinanceH5Dto sumAssetRate) {
        this.sumAssetRate = sumAssetRate;
    }

    public IpoFinanceH5Dto getCashFlowRate() {
        return cashFlowRate;
    }

    public void setCashFlowRate(IpoFinanceH5Dto cashFlowRate) {
        this.cashFlowRate = cashFlowRate;
    }

    public IpoFinanceH5Dto getCashRate() {
        return cashRate;
    }

    public void setCashRate(IpoFinanceH5Dto cashRate) {
        this.cashRate = cashRate;
    }

    public IpoFinanceDateDto getDateList() {
        return dateList;
    }

    public void setDateList(IpoFinanceDateDto dateList) {
        this.dateList = dateList;
    }

    public List<IpoFinanceH5Dto> getCashFlowList() {
        return cashFlowList;
    }

    public void setCashFlowList(List<IpoFinanceH5Dto> cashFlowList) {
        this.cashFlowList = cashFlowList;
    }

    public List<IpoFinanceH5Dto> getCashList() {
        return cashList;
    }

    public void setCashList(List<IpoFinanceH5Dto> cashList) {
        this.cashList = cashList;
    }
}

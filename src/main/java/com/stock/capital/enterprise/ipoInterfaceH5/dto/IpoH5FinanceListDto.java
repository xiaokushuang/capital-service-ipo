package com.stock.capital.enterprise.ipoInterfaceH5.dto;

import java.io.Serializable;
import java.util.List;

public class IpoH5FinanceListDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private IpoFinanceH5Dto beforeYear;

    private IpoFinanceH5Dto lastYear;

    private IpoFinanceH5Dto todayYear;

    private String orgCount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public IpoFinanceH5Dto getBeforeYear() {
        return beforeYear;
    }

    public void setBeforeYear(IpoFinanceH5Dto beforeYear) {
        this.beforeYear = beforeYear;
    }

    public IpoFinanceH5Dto getLastYear() {
        return lastYear;
    }

    public void setLastYear(IpoFinanceH5Dto lastYear) {
        this.lastYear = lastYear;
    }

    public IpoFinanceH5Dto getTodayYear() {
        return todayYear;
    }

    public void setTodayYear(IpoFinanceH5Dto todayYear) {
        this.todayYear = todayYear;
    }

    public String getOrgCount() {
        return orgCount;
    }

    public void setOrgCount(String orgCount) {
        this.orgCount = orgCount;
    }
}

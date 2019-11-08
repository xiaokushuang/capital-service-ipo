package com.stock.capital.enterprise.api.financeStatistics.dto;

import java.io.Serializable;
import java.util.List;

public class FinanceIndTypeCode implements Serializable {
    private static final long serialVersionUID = -5947504118081212341L;
    private String financeIndTypeCodeKey;
    private List<String> financeIndTypeCodeValue;

    public String getFinanceIndTypeCodeKey() {
        return financeIndTypeCodeKey;
    }

    public void setFinanceIndTypeCodeKey(String financeIndTypeCodeKey) {
        this.financeIndTypeCodeKey = financeIndTypeCodeKey;
    }

    public List<String> getFinanceIndTypeCodeValue() {
        return financeIndTypeCodeValue;
    }

    public void setFinanceIndTypeCodeValue(List<String> financeIndTypeCodeValue) {
        this.financeIndTypeCodeValue = financeIndTypeCodeValue;
    }
}

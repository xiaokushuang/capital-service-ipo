package com.stock.capital.enterprise.api.financeStatistics.dto;

import java.io.Serializable;
import java.util.List;

public class FinanceIndTypeCode implements Serializable {
    private static final long serialVersionUID = -5947504118081212341L;
    //行业类别
    private String financeIndTypeCodeKey;
    private List<String> financeIndTypeCodeValue;
    //所属行业
    private String financeIndCodeKey;
    private List<String> financeIndCodeValue;

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

	public String getFinanceIndCodeKey() {
		return financeIndCodeKey;
	}

	public void setFinanceIndCodeKey(String financeIndCodeKey) {
		this.financeIndCodeKey = financeIndCodeKey;
	}

	public List<String> getFinanceIndCodeValue() {
		return financeIndCodeValue;
	}

	public void setFinanceIndCodeValue(List<String> financeIndCodeValue) {
		this.financeIndCodeValue = financeIndCodeValue;
	}
    
    
}

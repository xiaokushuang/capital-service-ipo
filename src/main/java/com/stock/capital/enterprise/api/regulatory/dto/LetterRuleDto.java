package com.stock.capital.enterprise.api.regulatory.dto;

import java.io.Serializable;

public class LetterRuleDto implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

    private String lawId;
    
    private String lawsName;
    
    private String count;
    
    private String relevantRulesText;
    
    private String allLawFlag;

    public String getLawId() {
        return lawId;
    }

    public void setLawId(String lawId) {
        this.lawId = lawId;
    }

    public String getLawsName() {
        return lawsName;
    }

    public void setLawsName(String lawsName) {
        this.lawsName = lawsName;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getRelevantRulesText() {
        return relevantRulesText;
    }

    public void setRelevantRulesText(String relevantRulesText) {
        this.relevantRulesText = relevantRulesText;
    }

    public String getAllLawFlag() {
        return allLawFlag;
    }
    
    public void setAllLawFlag(String allLawFlag) {
        this.allLawFlag = allLawFlag;
    }
    
    
}


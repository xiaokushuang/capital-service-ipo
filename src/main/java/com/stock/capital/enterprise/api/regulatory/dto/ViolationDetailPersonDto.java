package com.stock.capital.enterprise.api.regulatory.dto;

import java.io.Serializable;

public class ViolationDetailPersonDto implements Serializable {

    private static final long serialVersionUID = -5283286219691106934L;

    private String id;

    private String person;

    private String identity;

    private String violateTypeVO;

    private String punishType;

    private Double punishAmount;

    private String punishAmountType;

    private Double confiscateAmount;

    private String confiscateAmountType;

    private Double penaltyAmount;

    private String penaltyAmountType;

    private String limitDate;

    private String limitYears;

    private String imprisonment;

    private String imprisonmentMonth;

    private String isRevoke;
    //by panqigeng bug 5237 start
    private String punishId;
    
    private String punishLevel;
    //by panqigeng bug 5237 END
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getViolateTypeVO() {
        return violateTypeVO;
    }

    public void setViolateTypeVO(String violateTypeVO) {
        this.violateTypeVO = violateTypeVO;
    }

    public String getPunishType() {
        return punishType;
    }

    public void setPunishType(String punishType) {
        this.punishType = punishType;
    }

    public Double getPunishAmount() {
        return punishAmount;
    }

    public void setPunishAmount(Double punishAmount) {
        this.punishAmount = punishAmount;
    }

    public String getPunishAmountType() {
        return punishAmountType;
    }

    public void setPunishAmountType(String punishAmountType) {
        this.punishAmountType = punishAmountType;
    }

    public Double getConfiscateAmount() {
        return confiscateAmount;
    }

    public void setConfiscateAmount(Double confiscateAmount) {
        this.confiscateAmount = confiscateAmount;
    }

    public String getConfiscateAmountType() {
        return confiscateAmountType;
    }

    public void setConfiscateAmountType(String confiscateAmountType) {
        this.confiscateAmountType = confiscateAmountType;
    }

    public Double getPenaltyAmount() {
        return penaltyAmount;
    }

    public void setPenaltyAmount(Double penaltyAmount) {
        this.penaltyAmount = penaltyAmount;
    }

    public String getPenaltyAmountType() {
        return penaltyAmountType;
    }

    public void setPenaltyAmountType(String penaltyAmountType) {
        this.penaltyAmountType = penaltyAmountType;
    }

    public String getLimitDate() {
        return limitDate;
    }

    public void setLimitDate(String limitDate) {
        this.limitDate = limitDate;
    }

    public String getLimitYears() {
        return limitYears;
    }

    public void setLimitYears(String limitYears) {
        this.limitYears = limitYears;
    }

    public String getImprisonment() {
        return imprisonment;
    }

    public void setImprisonment(String imprisonment) {
        this.imprisonment = imprisonment;
    }

    public String getImprisonmentMonth() {
        return imprisonmentMonth;
    }

    public void setImprisonmentMonth(String imprisonmentMonth) {
        this.imprisonmentMonth = imprisonmentMonth;
    }

    public String getIsRevoke() {
        return isRevoke;
    }

    public void setIsRevoke(String isRevoke) {
        this.isRevoke = isRevoke;
    }

	public String getPunishId() {
		return punishId;
	}

	public void setPunishId(String punishId) {
		this.punishId = punishId;
	}

	public String getPunishLevel() {
		return punishLevel;
	}

	public void setPunishLevel(String punishLevel) {
		this.punishLevel = punishLevel;
	}

}

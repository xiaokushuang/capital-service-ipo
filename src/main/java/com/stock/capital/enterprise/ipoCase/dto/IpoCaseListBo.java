package com.stock.capital.enterprise.ipoCase.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * IpoCaseListBo.
 *
 * @author chenzhengxiu.
 * @version 0.0.1.
 * @serial 2019/3/21 : base version.
 */
public class IpoCaseListBo implements Serializable {

    private static final long serialVersionUID = -6098304607047534794L;

    private String title;

    private String industryCsrc;

    private String codeOrName;

    private String companyNature;

    private String ipoNum;

    private String iecResult;

    private String caseStatus;

    private String ipoPlate;

    private String greenPassage;

    private Date[] fsProcessTime;

    private Date[] ypProcessTime;

    private String intermediaryCode;

    private String marketType;

    private String belongsBureau;

    private Integer limitNum;

    private Double[] profitOne;

    private Double[] profitTwo;

    private Double[] profitThree;

    private Double[] reveOne;

    private Double[] reveTwo;

    private Double[] reveThree;

    private Double[] cashFlowOne;

    private Double[] cashFlowTwo;

    private Double[] cashFlowThree;

    private Double[] sunAsset;

    private Double[] sumShareQuity;

    private Double[] intangibleAssetRatio;

    private Double[] totalShareIssueB;

    private Double[] totalShareIssueA;

    private Double[] peIssueA;

    private Double[] issueFee;

    private Integer[] timeDiff;

    private String companyId;

    private String specialArrange;

    private String issueCondition;

    private String strageticIndustries;

    private String placingMechanism;

    private Double[] valuationValue;
    private String ipoPublicTimeDt;

    public String getIpoPublicTimeDt() {
        return ipoPublicTimeDt;
    }

    public void setIpoPublicTimeDt(String ipoPublicTimeDt) {
        this.ipoPublicTimeDt = ipoPublicTimeDt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIndustryCsrc() {
        return industryCsrc;
    }

    public void setIndustryCsrc(String industryCsrc) {
        this.industryCsrc = industryCsrc;
    }

    public String getCodeOrName() {
        return codeOrName;
    }

    public void setCodeOrName(String codeOrName) {
        this.codeOrName = codeOrName;
    }

    public String getCompanyNature() {
        return companyNature;
    }

    public void setCompanyNature(String companyNature) {
        this.companyNature = companyNature;
    }

    public String getIpoNum() {
        return ipoNum;
    }

    public void setIpoNum(String ipoNum) {
        this.ipoNum = ipoNum;
    }

    public String getIecResult() {
        return iecResult;
    }

    public void setIecResult(String iecResult) {
        this.iecResult = iecResult;
    }

    public String getCaseStatus() {
        return caseStatus;
    }

    public void setCaseStatus(String caseStatus) {
        this.caseStatus = caseStatus;
    }

    public String getIpoPlate() {
        return ipoPlate;
    }

    public void setIpoPlate(String ipoPlate) {
        this.ipoPlate = ipoPlate;
    }

    public String getGreenPassage() {
        return greenPassage;
    }

    public void setGreenPassage(String greenPassage) {
        this.greenPassage = greenPassage;
    }

    public Date[] getFsProcessTime() {
        return fsProcessTime;
    }

    public void setFsProcessTime(Date[] fsProcessTime) {
        this.fsProcessTime = fsProcessTime;
    }

    public Date[] getYpProcessTime() {
        return ypProcessTime;
    }

    public void setYpProcessTime(Date[] ypProcessTime) {
        this.ypProcessTime = ypProcessTime;
    }

    public String getIntermediaryCode() {
        return intermediaryCode;
    }

    public void setIntermediaryCode(String intermediaryCode) {
        this.intermediaryCode = intermediaryCode;
    }

    public String getMarketType() {
        return marketType;
    }

    public void setMarketType(String marketType) {
        this.marketType = marketType;
    }

    public String getBelongsBureau() {
        return belongsBureau;
    }

    public void setBelongsBureau(String belongsBureau) {
        this.belongsBureau = belongsBureau;
    }

    public Integer getLimitNum() {
        return limitNum;
    }

    public void setLimitNum(Integer limitNum) {
        this.limitNum = limitNum;
    }

    public Double[] getProfitOne() {
        return profitOne;
    }

    public void setProfitOne(Double[] profitOne) {
        this.profitOne = profitOne;
    }

    public Double[] getProfitTwo() {
        return profitTwo;
    }

    public void setProfitTwo(Double[] profitTwo) {
        this.profitTwo = profitTwo;
    }

    public Double[] getProfitThree() {
        return profitThree;
    }

    public void setProfitThree(Double[] profitThree) {
        this.profitThree = profitThree;
    }

    public Double[] getReveOne() {
        return reveOne;
    }

    public void setReveOne(Double[] reveOne) {
        this.reveOne = reveOne;
    }

    public Double[] getReveTwo() {
        return reveTwo;
    }

    public void setReveTwo(Double[] reveTwo) {
        this.reveTwo = reveTwo;
    }

    public Double[] getReveThree() {
        return reveThree;
    }

    public void setReveThree(Double[] reveThree) {
        this.reveThree = reveThree;
    }

    public Double[] getCashFlowOne() {
        return cashFlowOne;
    }

    public void setCashFlowOne(Double[] cashFlowOne) {
        this.cashFlowOne = cashFlowOne;
    }

    public Double[] getCashFlowTwo() {
        return cashFlowTwo;
    }

    public void setCashFlowTwo(Double[] cashFlowTwo) {
        this.cashFlowTwo = cashFlowTwo;
    }

    public Double[] getCashFlowThree() {
        return cashFlowThree;
    }

    public void setCashFlowThree(Double[] cashFlowThree) {
        this.cashFlowThree = cashFlowThree;
    }

    public Double[] getSunAsset() {
        return sunAsset;
    }

    public void setSunAsset(Double[] sunAsset) {
        this.sunAsset = sunAsset;
    }

    public Double[] getSumShareQuity() {
        return sumShareQuity;
    }

    public void setSumShareQuity(Double[] sumShareQuity) {
        this.sumShareQuity = sumShareQuity;
    }

    public Double[] getIntangibleAssetRatio() {
        return intangibleAssetRatio;
    }

    public void setIntangibleAssetRatio(Double[] intangibleAssetRatio) {
        this.intangibleAssetRatio = intangibleAssetRatio;
    }

    public Double[] getTotalShareIssueB() {
        return totalShareIssueB;
    }

    public void setTotalShareIssueB(Double[] totalShareIssueB) {
        this.totalShareIssueB = totalShareIssueB;
    }

    public Double[] getTotalShareIssueA() {
        return totalShareIssueA;
    }

    public void setTotalShareIssueA(Double[] totalShareIssueA) {
        this.totalShareIssueA = totalShareIssueA;
    }

    public Double[] getPeIssueA() {
        return peIssueA;
    }

    public void setPeIssueA(Double[] peIssueA) {
        this.peIssueA = peIssueA;
    }

    public Double[] getIssueFee() {
        return issueFee;
    }

    public void setIssueFee(Double[] issueFee) {
        this.issueFee = issueFee;
    }

    public Integer[] getTimeDiff() {
        return timeDiff;
    }

    public void setTimeDiff(Integer[] timeDiff) {
        this.timeDiff = timeDiff;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getSpecialArrange() {
        return specialArrange;
    }

    public void setSpecialArrange(String specialArrange) {
        this.specialArrange = specialArrange;
    }

    public String getIssueCondition() {
        return issueCondition;
    }

    public void setIssueCondition(String issueCondition) {
        this.issueCondition = issueCondition;
    }

    public String getStrageticIndustries() {
        return strageticIndustries;
    }

    public void setStrageticIndustries(String strageticIndustries) {
        this.strageticIndustries = strageticIndustries;
    }

    public String getPlacingMechanism() {
        return placingMechanism;
    }

    public void setPlacingMechanism(String placingMechanism) {
        this.placingMechanism = placingMechanism;
    }

    public Double[] getValuationValue() {
        return valuationValue;
    }

    public void setValuationValue(Double[] valuationValue) {
        this.valuationValue = valuationValue;
    }
}

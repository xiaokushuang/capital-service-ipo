package com.stock.capital.enterprise.ipoCase.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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

    private List<String> titleList;

    private String industryCsrc;
    private List<String> industryCsrcList;

    private String codeOrName;

    private String companyNature;
    private List<String> companyNatureList;

    private String ipoNum;
    private List<String> ipoNumList;

    private String iecResult;
    private List<String> iecResultList;

    private String caseStatus;
    private List<String> caseStatusList;

    private String ipoPlate;
    private List<String> ipoPlateList;

    private String greenPassage;
    private List<String> greenPassageList;

    private Date[] fsProcessTime;
    private String fsProcessTimeStart;
    private String fsProcessTimeEnd;

    private Date[] ypProcessTime;
    private String ypProcessTimeStart;
    private String ypProcessTimeEnd;

    private String intermediaryCode;
    private List<String> intermediaryCodeList;

    private String marketType;
    private List<String> marketTypeList;

    private String belongsBureau;
    private List<String> belongsBureauList;

    private Integer limitNum;

    private Double[] profitOne;
    private Double profitOneStart;
    private Double profitOneEnd;

    private Double[] profitTwo;
    private Double profitTwoStart;
    private Double profitTwoEnd;

    private Double[] profitThree;
    private Double profitThreeStart;
    private Double profitThreeEnd;

    private Double[] reveOne;
    private Double reveOneStart;
    private Double reveOneEnd;

    private Double[] reveTwo;
    private Double reveTwoStart;
    private Double reveTwoEnd;

    private Double[] reveThree;
    private Double reveThreeStart;
    private Double reveThreeEnd;

    private Double[] cashFlowOne;
    private Double cashFlowOneStart;
    private Double cashFlowOneEnd;


    private Double[] cashFlowTwo;
    private Double cashFlowTwoStart;
    private Double cashFlowTwoEnd;

    private Double[] cashFlowThree;
    private Double cashFlowThreeStart;
    private Double cashFlowThreeEnd;

    private Double[] sunAsset;
    private Double sunAssetStart;
    private Double sunAssetEnd;

    private Double[] sumShareQuity;
    private Double sumShareQuityStart;
    private Double sumShareQuityEnd;

    private Double[] intangibleAssetRatio;
    private Double intangibleAssetRatioStart;
    private Double intangibleAssetRatioEnd;

    private Double[] totalShareIssueB;
    private Double totalShareIssueBStart;
    private Double totalShareIssueBEnd;

    private Double[] totalShareIssueA;
    private Double totalShareIssueAStart;
    private Double totalShareIssueAEnd;

    private Double[] peIssueA;
    private Double peIssueALow;
    private Double peIssueAHigh;

    private Double[] issueFee;
    private Double issueFeeLow;
    private Double issueFeeHigh;

    private Integer[] timeDiff;
    private Integer timeDiffLow;
    private Integer timeDiffHigh;

    private String companyId;

    private String specialArrange;
    private List<String> specialArrangeList;

    private String issueCondition;
    private List<String> issueConditionList;

    private String strageticIndustries;


    private String placingMechanism;
    private List<String> placingMechanismList;

    private Double[] valuationValue;
    private Double valuationValueStart;
    private Double valuationValueEnd;


    private String issueLawName;//上市条件法规名称
    private String issueLawId;//上市条件法规id

    private String registerArea;    //注册地

    private String isHidden;
    private List<String> isHiddenList;

    public String getIsHidden() {
        return isHidden;
    }

    public void setIsHidden(String isHidden) {
        this.isHidden = isHidden;
    }

    public List<String> getIsHiddenList() {
        return isHiddenList;
    }

    public void setIsHiddenList(List<String> isHiddenList) {
        this.isHiddenList = isHiddenList;
    }

    public List<String> getSpecialArrangeList() {
        return specialArrangeList;
    }

    public void setSpecialArrangeList(List<String> specialArrangeList) {
        this.specialArrangeList = specialArrangeList;
    }

    public List<String> getIpoPlateList() {
        return ipoPlateList;
    }

    public void setIpoPlateList(List<String> ipoPlateList) {
        this.ipoPlateList = ipoPlateList;
    }

    public List<String> getGreenPassageList() {
        return greenPassageList;
    }

    public void setGreenPassageList(List<String> greenPassageList) {
        this.greenPassageList = greenPassageList;
    }

    public List<String> getIntermediaryCodeList() {
        return intermediaryCodeList;
    }

    public void setIntermediaryCodeList(List<String> intermediaryCodeList) {
        this.intermediaryCodeList = intermediaryCodeList;
    }

    public List<String> getMarketTypeList() {
        return marketTypeList;
    }

    public void setMarketTypeList(List<String> marketTypeList) {
        this.marketTypeList = marketTypeList;
    }

    public List<String> getBelongsBureauList() {
        return belongsBureauList;
    }

    public void setBelongsBureauList(List<String> belongsBureauList) {
        this.belongsBureauList = belongsBureauList;
    }

    public Double getValuationValueStart() {
        return valuationValueStart;
    }

    public void setValuationValueStart(Double valuationValueStart) {
        this.valuationValueStart = valuationValueStart;
    }

    public Double getValuationValueEnd() {
        return valuationValueEnd;
    }

    public void setValuationValueEnd(Double valuationValueEnd) {
        this.valuationValueEnd = valuationValueEnd;
    }

    public Integer getTimeDiffLow() {
        return timeDiffLow;
    }

    public void setTimeDiffLow(Integer timeDiffLow) {
        this.timeDiffLow = timeDiffLow;
    }

    public Integer getTimeDiffHigh() {
        return timeDiffHigh;
    }

    public void setTimeDiffHigh(Integer timeDiffHigh) {
        this.timeDiffHigh = timeDiffHigh;
    }

    public Double getIssueFeeLow() {
        return issueFeeLow;
    }

    public void setIssueFeeLow(Double issueFeeLow) {
        this.issueFeeLow = issueFeeLow;
    }

    public Double getIssueFeeHigh() {
        return issueFeeHigh;
    }

    public void setIssueFeeHigh(Double issueFeeHigh) {
        this.issueFeeHigh = issueFeeHigh;
    }

    public Double getPeIssueALow() {
        return peIssueALow;
    }

    public void setPeIssueALow(Double peIssueALow) {
        this.peIssueALow = peIssueALow;
    }

    public Double getPeIssueAHigh() {
        return peIssueAHigh;
    }

    public void setPeIssueAHigh(Double peIssueAHigh) {
        this.peIssueAHigh = peIssueAHigh;
    }

    public List<String> getIssueConditionList() {
        return issueConditionList;
    }

    public void setIssueConditionList(List<String> issueConditionList) {
        this.issueConditionList = issueConditionList;
    }
    public List<String> getPlacingMechanismList() {
        return placingMechanismList;
    }

    public void setPlacingMechanismList(List<String> placingMechanismList) {
        this.placingMechanismList = placingMechanismList;
    }

    public List<String> getIecResultList() {
        return iecResultList;
    }

    public void setIecResultList(List<String> iecResultList) {
        this.iecResultList = iecResultList;
    }

    public List<String> getCaseStatusList() {
        return caseStatusList;
    }

    public void setCaseStatusList(List<String> caseStatusList) {
        this.caseStatusList = caseStatusList;
    }

    public List<String> getIpoNumList() {
        return ipoNumList;
    }

    public void setIpoNumList(List<String> ipoNumList) {
        this.ipoNumList = ipoNumList;
    }

    public List<String> getIndustryCsrcList() {
        return industryCsrcList;
    }

    public void setIndustryCsrcList(List<String> industryCsrcList) {
        this.industryCsrcList = industryCsrcList;
    }

    public List<String> getCompanyNatureList() {
        return companyNatureList;
    }

    public void setCompanyNatureList(List<String> companyNatureList) {
        this.companyNatureList = companyNatureList;
    }

    private List<String> registerAreaList; //注册地List

    private String caseType; //案例类型 all 全部案例，ipo ipo案例，ipofd ipo辅导案例

    private Date[] fdProcessTime; //辅导备案时间
    private String fdProcessTimeStart; //辅导备案时间
    private String fdProcessTimeEnd; //辅导备案时间


    public Double getProfitOneStart() {
        return profitOneStart;
    }

    public void setProfitOneStart(Double profitOneStart) {
        this.profitOneStart = profitOneStart;
    }

    public Double getProfitOneEnd() {
        return profitOneEnd;
    }

    public void setProfitOneEnd(Double profitOneEnd) {
        this.profitOneEnd = profitOneEnd;
    }

    public Double getProfitTwoStart() {
        return profitTwoStart;
    }

    public void setProfitTwoStart(Double profitTwoStart) {
        this.profitTwoStart = profitTwoStart;
    }

    public Double getProfitTwoEnd() {
        return profitTwoEnd;
    }

    public void setProfitTwoEnd(Double profitTwoEnd) {
        this.profitTwoEnd = profitTwoEnd;
    }

    public Double getProfitThreeStart() {
        return profitThreeStart;
    }

    public void setProfitThreeStart(Double profitThreeStart) {
        this.profitThreeStart = profitThreeStart;
    }

    public Double getProfitThreeEnd() {
        return profitThreeEnd;
    }

    public void setProfitThreeEnd(Double profitThreeEnd) {
        this.profitThreeEnd = profitThreeEnd;
    }

    public Double getReveOneStart() {
        return reveOneStart;
    }

    public void setReveOneStart(Double reveOneStart) {
        this.reveOneStart = reveOneStart;
    }

    public Double getReveOneEnd() {
        return reveOneEnd;
    }

    public void setReveOneEnd(Double reveOneEnd) {
        this.reveOneEnd = reveOneEnd;
    }

    public Double getReveTwoStart() {
        return reveTwoStart;
    }

    public void setReveTwoStart(Double reveTwoStart) {
        this.reveTwoStart = reveTwoStart;
    }

    public Double getReveTwoEnd() {
        return reveTwoEnd;
    }

    public void setReveTwoEnd(Double reveTwoEnd) {
        this.reveTwoEnd = reveTwoEnd;
    }

    public Double getReveThreeStart() {
        return reveThreeStart;
    }

    public void setReveThreeStart(Double reveThreeStart) {
        this.reveThreeStart = reveThreeStart;
    }

    public Double getReveThreeEnd() {
        return reveThreeEnd;
    }

    public void setReveThreeEnd(Double reveThreeEnd) {
        this.reveThreeEnd = reveThreeEnd;
    }

    public Double getCashFlowOneStart() {
        return cashFlowOneStart;
    }

    public void setCashFlowOneStart(Double cashFlowOneStart) {
        this.cashFlowOneStart = cashFlowOneStart;
    }

    public Double getCashFlowOneEnd() {
        return cashFlowOneEnd;
    }

    public void setCashFlowOneEnd(Double cashFlowOneEnd) {
        this.cashFlowOneEnd = cashFlowOneEnd;
    }

    public Double getCashFlowTwoStart() {
        return cashFlowTwoStart;
    }

    public void setCashFlowTwoStart(Double cashFlowTwoStart) {
        this.cashFlowTwoStart = cashFlowTwoStart;
    }

    public Double getCashFlowTwoEnd() {
        return cashFlowTwoEnd;
    }

    public void setCashFlowTwoEnd(Double cashFlowTwoEnd) {
        this.cashFlowTwoEnd = cashFlowTwoEnd;
    }

    public Double getCashFlowThreeStart() {
        return cashFlowThreeStart;
    }

    public void setCashFlowThreeStart(Double cashFlowThreeStart) {
        this.cashFlowThreeStart = cashFlowThreeStart;
    }

    public Double getCashFlowThreeEnd() {
        return cashFlowThreeEnd;
    }

    public void setCashFlowThreeEnd(Double cashFlowThreeEnd) {
        this.cashFlowThreeEnd = cashFlowThreeEnd;
    }

    public Double getSunAssetStart() {
        return sunAssetStart;
    }

    public void setSunAssetStart(Double sunAssetStart) {
        this.sunAssetStart = sunAssetStart;
    }

    public Double getSunAssetEnd() {
        return sunAssetEnd;
    }

    public void setSunAssetEnd(Double sunAssetEnd) {
        this.sunAssetEnd = sunAssetEnd;
    }

    public Double getSumShareQuityStart() {
        return sumShareQuityStart;
    }

    public void setSumShareQuityStart(Double sumShareQuityStart) {
        this.sumShareQuityStart = sumShareQuityStart;
    }

    public Double getSumShareQuityEnd() {
        return sumShareQuityEnd;
    }

    public void setSumShareQuityEnd(Double sumShareQuityEnd) {
        this.sumShareQuityEnd = sumShareQuityEnd;
    }

    public Double getIntangibleAssetRatioStart() {
        return intangibleAssetRatioStart;
    }

    public void setIntangibleAssetRatioStart(Double intangibleAssetRatioStart) {
        this.intangibleAssetRatioStart = intangibleAssetRatioStart;
    }

    public Double getIntangibleAssetRatioEnd() {
        return intangibleAssetRatioEnd;
    }

    public void setIntangibleAssetRatioEnd(Double intangibleAssetRatioEnd) {
        this.intangibleAssetRatioEnd = intangibleAssetRatioEnd;
    }

    public Double getTotalShareIssueBStart() {
        return totalShareIssueBStart;
    }

    public void setTotalShareIssueBStart(Double totalShareIssueBStart) {
        this.totalShareIssueBStart = totalShareIssueBStart;
    }

    public Double getTotalShareIssueBEnd() {
        return totalShareIssueBEnd;
    }

    public void setTotalShareIssueBEnd(Double totalShareIssueBEnd) {
        this.totalShareIssueBEnd = totalShareIssueBEnd;
    }

    public Double getTotalShareIssueAStart() {
        return totalShareIssueAStart;
    }

    public void setTotalShareIssueAStart(Double totalShareIssueAStart) {
        this.totalShareIssueAStart = totalShareIssueAStart;
    }

    public Double getTotalShareIssueAEnd() {
        return totalShareIssueAEnd;
    }

    public void setTotalShareIssueAEnd(Double totalShareIssueAEnd) {
        this.totalShareIssueAEnd = totalShareIssueAEnd;
    }

    public String getFsProcessTimeStart() {
        return fsProcessTimeStart;
    }

    public void setFsProcessTimeStart(String fsProcessTimeStart) {
        this.fsProcessTimeStart = fsProcessTimeStart;
    }

    public String getFsProcessTimeEnd() {
        return fsProcessTimeEnd;
    }

    public void setFsProcessTimeEnd(String fsProcessTimeEnd) {
        this.fsProcessTimeEnd = fsProcessTimeEnd;
    }

    public String getYpProcessTimeStart() {
        return ypProcessTimeStart;
    }

    public void setYpProcessTimeStart(String ypProcessTimeStart) {
        this.ypProcessTimeStart = ypProcessTimeStart;
    }

    public String getYpProcessTimeEnd() {
        return ypProcessTimeEnd;
    }

    public void setYpProcessTimeEnd(String ypProcessTimeEnd) {
        this.ypProcessTimeEnd = ypProcessTimeEnd;
    }

    public String getFdProcessTimeStart() {
        return fdProcessTimeStart;
    }

    public void setFdProcessTimeStart(String fdProcessTimeStart) {
        this.fdProcessTimeStart = fdProcessTimeStart;
    }

    public String getFdProcessTimeEnd() {
        return fdProcessTimeEnd;
    }

    public void setFdProcessTimeEnd(String fdProcessTimeEnd) {
        this.fdProcessTimeEnd = fdProcessTimeEnd;
    }

    public List<String> getTitleList() {
        return titleList;
    }

    public void setTitleList(List<String> titleList) {
        this.titleList = titleList;
    }

    public Date[] getFdProcessTime() {
        return fdProcessTime;
    }

    public void setFdProcessTime(Date[] fdProcessTime) {
        this.fdProcessTime = fdProcessTime;
    }

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }

    public String getRegisterArea() {
        return registerArea;
    }

    public void setRegisterArea(String registerArea) {
        this.registerArea = registerArea;
    }

    public List<String> getRegisterAreaList() {
        return registerAreaList;
    }

    public void setRegisterAreaList(List<String> registerAreaList) {
        this.registerAreaList = registerAreaList;
    }

    public String getIssueLawName() {
        return issueLawName;
    }

    public void setIssueLawName(String issueLawName) {
        this.issueLawName = issueLawName;
    }

    public String getIssueLawId() {
        return issueLawId;
    }

    public void setIssueLawId(String issueLawId) {
        this.issueLawId = issueLawId;
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

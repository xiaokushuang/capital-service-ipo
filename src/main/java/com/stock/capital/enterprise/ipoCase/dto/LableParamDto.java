package com.stock.capital.enterprise.ipoCase.dto;

import io.swagger.annotations.ApiModelProperty;
import com.stock.capital.enterprise.ipoCase.dto.CommonSearchDto;
import java.io.Serializable;
import java.util.List;

public class LableParamDto extends CommonSearchDto implements Serializable {

    private static final long serialVersionUID = -547262716974854728L;
    
    @ApiModelProperty(value= "函件ID",dataType = "String")
    private String letterId;
    
    @ApiModelProperty(value= "问题ID",dataType = "String")
    private String askAndAnswerId;
    
    @ApiModelProperty(value= "收藏回调函数",dataType = "String")
    private String sourceFlag;
    
    @ApiModelProperty(value= "form表单包含以下关键字",dataType = "String")
    private String key;
    
    @ApiModelProperty(value= "form表单包含以下全部关键字",dataType = "String")
    private String keyAnd;
    
    @ApiModelProperty(value= "form表单不包含以下关键字",dataType = "String")
    private String keyNot;
    
    @ApiModelProperty(value= "用于页面判断点击树",dataType = "String")
    private String lastClickTreeIndex;
    
    @ApiModelProperty(value= "是否选取联想公司名称下拉列表中的数据",dataType = "boolean")
    private boolean checkCompanyBoolean;
    
    @ApiModelProperty(value= "公司代码",dataType = "String")
    private String companyCode;
    
    @ApiModelProperty(value= "关注公司代码",dataType = "String")
    private String focusCompanySelect;
    
    @ApiModelProperty(value= "依据关注公司Tab页",dataType = "String")
    private String tabFrom;
    
    @ApiModelProperty(value= "所属板块",dataType = "String")
    private String stockBoardSelect;
    
    @ApiModelProperty(value= "所属行业",dataType = "String")
    private String industrySelect;
    
    @ApiModelProperty(value= "所在地区",dataType = "String")
    private String areaSelect;
    
    @ApiModelProperty(value= "审核结果",dataType = "String")
    private String auditSelect;
    
    @ApiModelProperty(value= "发函单位",dataType = "String")
    private String lableClass;
    
    @ApiModelProperty(value= "发函日期",dataType = "String[]")
    private String[] sendLetterTime;
    
    @ApiModelProperty(value= "回函日期",dataType = "String[]")
    private String[] backLetterTime;
    
    @ApiModelProperty(value= "回复状态",dataType = "String")
    private String replyStatus;
    
    @ApiModelProperty(value= "函件类别",dataType = "String")
    private String letterType;
    
    @ApiModelProperty(value= "问题类别",dataType = "String")
    private String questionType;
    
    @ApiModelProperty(value= "所属证监局",dataType = "String")
    private String belongsToSFCType;

    @ApiModelProperty(value= "热门分类",dataType = "String")
    private String hotClass;
    
    @ApiModelProperty(value= "如果是监管端首页进来，第一次只查询对应的数据，之后查询都正常",dataType = "String")
    private String FromFlag;
    
    @ApiModelProperty(value= "收藏的函件id",dataType = "String")
    private String bizId;
    
    @ApiModelProperty(value= "函件适用板块",dataType = "String")
    private String letterApplyModule;
    
    @ApiModelProperty(value= "所属函件主体",dataType = "String")
    private String belongsToRelaBodyType;
    
    @ApiModelProperty(value= "IPO一级标签问题id",dataType = "String")
    private String firstLabelId;
    
    @ApiModelProperty(value= "IPO二级标签问题id",dataType = "String")
    private String secondLabelId;
    
    @ApiModelProperty(value= "IPO是否只展示回复问题 1:是",dataType = "String")
    private String onlyResponse;
    
    @ApiModelProperty(value= "法规ID",dataType = "String")
    private String lawId;
    
    @ApiModelProperty(value= "法规条目ID",dataType = "String")
    private String lawItemId;
    
    //选中的节点的所有子节点id集合
    private List<String> classIdList;
    
    //是否为点击节点时的查询
    private String treeClickFlag;

    @ApiModelProperty(value= "登陆其他资本市场",dataType = "String")
    private String ipoMarketSelect;

    @ApiModelProperty(value= "拟上市板块",dataType = "String")
    private String ipoPlateSelect;

    @ApiModelProperty(value= "发函单位",dataType = "String")
    private String ipoSendUnitSelect;

    @ApiModelProperty(value= "发行人选择的上市条件",dataType = "String")
    private String ipoIssueSelect;

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

    @ApiModelProperty(value= "再融资非公开发行目的",dataType = "String")
    private String issuePurPoseSelect;

    
    public List<String> getClassIdList() {
        return classIdList;
    }

    public String getTreeClickFlag() {
        return treeClickFlag;
    }

    public void setClassIdList(List<String> classIdList) {
        this.classIdList = classIdList;
    }

    public void setTreeClickFlag(String treeClickFlag) {
        this.treeClickFlag = treeClickFlag;
    }

    public String getFirstLabelId() {
        return firstLabelId;
    }

    public void setFirstLabelId(String firstLabelId) {
        this.firstLabelId = firstLabelId;
    }

    public String getSecondLabelId() {
        return secondLabelId;
    }

    public void setSecondLabelId(String secondLabelId) {
        this.secondLabelId = secondLabelId;
    }

    public String getOnlyResponse() {
        return onlyResponse;
    }

    public void setOnlyResponse(String onlyResponse) {
        this.onlyResponse = onlyResponse;
    }

    public String getLawId() {
        return lawId;
    }

    public void setLawId(String lawId) {
        this.lawId = lawId;
    }

    public String getLawItemId() {
        return lawItemId;
    }

    public void setLawItemId(String lawItemId) {
        this.lawItemId = lawItemId;
    }

    public String getLetterApplyModule() {
        return letterApplyModule;
    }

    public void setLetterApplyModule(String letterApplyModule) {
        this.letterApplyModule = letterApplyModule;
    }

    public String getBelongsToRelaBodyType() {
        return belongsToRelaBodyType;
    }

    public void setBelongsToRelaBodyType(String belongsToRelaBodyType) {
        this.belongsToRelaBodyType = belongsToRelaBodyType;
    }

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    public String getFromFlag() {
        return FromFlag;
    }

    public void setFromFlag(String fromFlag) {
        FromFlag = fromFlag;
    }

    public boolean isCheckCompanyBoolean() {
        return checkCompanyBoolean;
    }

    public void setCheckCompanyBoolean(boolean checkCompanyBoolean) {
        this.checkCompanyBoolean = checkCompanyBoolean;
    }

    public String getLastClickTreeIndex() {
        return lastClickTreeIndex;
    }

    public void setLastClickTreeIndex(String lastClickTreeIndex) {
        this.lastClickTreeIndex = lastClickTreeIndex;
    }

    public String getKey() {
        return key;
    }

    public String getKeyAnd() {
        return keyAnd;
    }

    public String getKeyNot() {
        return keyNot;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public String getFocusCompanySelect() {
        return focusCompanySelect;
    }

    public String getTabFrom() {
        return tabFrom;
    }

    public String getStockBoardSelect() {
        return stockBoardSelect;
    }

    public String getIndustrySelect() {
        return industrySelect;
    }

    public String getAreaSelect() {
        return areaSelect;
    }

    public String getLableClass() {
        return lableClass;
    }

    public String[] getSendLetterTime() {
        return sendLetterTime;
    }

    public String[] getBackLetterTime() {
        return backLetterTime;
    }

    public String getReplyStatus() {
        return replyStatus;
    }

    public String getLetterType() {
        return letterType;
    }

    public String getQuestionType() {
        return questionType;
    }

    public String getBelongsToSFCType() {
        return belongsToSFCType;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setKeyAnd(String keyAnd) {
        this.keyAnd = keyAnd;
    }

    public void setKeyNot(String keyNot) {
        this.keyNot = keyNot;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public void setFocusCompanySelect(String focusCompanySelect) {
        this.focusCompanySelect = focusCompanySelect;
    }

    public void setTabFrom(String tabFrom) {
        this.tabFrom = tabFrom;
    }

    public void setStockBoardSelect(String stockBoardSelect) {
        this.stockBoardSelect = stockBoardSelect;
    }

    public void setIndustrySelect(String industrySelect) {
        this.industrySelect = industrySelect;
    }

    public void setAreaSelect(String areaSelect) {
        this.areaSelect = areaSelect;
    }

    public void setLableClass(String lableClass) {
        this.lableClass = lableClass;
    }

    public void setSendLetterTime(String[] sendLetterTime) {
        this.sendLetterTime = sendLetterTime;
    }

    public void setBackLetterTime(String[] backLetterTime) {
        this.backLetterTime = backLetterTime;
    }

    public void setReplyStatus(String replyStatus) {
        this.replyStatus = replyStatus;
    }

    public void setLetterType(String letterType) {
        this.letterType = letterType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public void setBelongsToSFCType(String belongsToSFCType) {
        this.belongsToSFCType = belongsToSFCType;
    }

	public String getLetterId() {
		return letterId;
	}

	public void setLetterId(String letterId) {
		this.letterId = letterId;
	}

	public String getAskAndAnswerId() {
		return askAndAnswerId;
	}

	public void setAskAndAnswerId(String askAndAnswerId) {
		this.askAndAnswerId = askAndAnswerId;
	}

	public String getSourceFlag() {
		return sourceFlag;
	}

	public void setSourceFlag(String sourceFlag) {
		this.sourceFlag = sourceFlag;
	}

	public String getAuditSelect() {
		return auditSelect;
	}

	public void setAuditSelect(String auditSelect) {
		this.auditSelect = auditSelect;
	}

    public String getHotClass() {
        return hotClass;
    }

    public void setHotClass(String hotClass) {
        this.hotClass = hotClass;
    }

    public String getIpoMarketSelect() {
        return ipoMarketSelect;
    }

    public void setIpoMarketSelect(String ipoMarketSelect) {
        this.ipoMarketSelect = ipoMarketSelect;
    }

    public String getIpoPlateSelect() {
        return ipoPlateSelect;
    }

    public void setIpoPlateSelect(String ipoPlateSelect) {
        this.ipoPlateSelect = ipoPlateSelect;
    }

    public String getIpoSendUnitSelect() {
        return ipoSendUnitSelect;
    }

    public void setIpoSendUnitSelect(String ipoSendUnitSelect) {
        this.ipoSendUnitSelect = ipoSendUnitSelect;
    }

    public String getIpoIssueSelect() {
        return ipoIssueSelect;
    }

    public void setIpoIssueSelect(String ipoIssueSelect) {
        this.ipoIssueSelect = ipoIssueSelect;
    }

    public Double[] getProfitOne() {
        return profitOne;
    }

    public void setProfitOne(Double[] profitOne) {
        this.profitOne = profitOne;
    }

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

    public Double[] getProfitTwo() {
        return profitTwo;
    }

    public void setProfitTwo(Double[] profitTwo) {
        this.profitTwo = profitTwo;
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

    public Double[] getProfitThree() {
        return profitThree;
    }

    public void setProfitThree(Double[] profitThree) {
        this.profitThree = profitThree;
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

    public Double[] getReveOne() {
        return reveOne;
    }

    public void setReveOne(Double[] reveOne) {
        this.reveOne = reveOne;
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

    public Double[] getReveTwo() {
        return reveTwo;
    }

    public void setReveTwo(Double[] reveTwo) {
        this.reveTwo = reveTwo;
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

    public Double[] getReveThree() {
        return reveThree;
    }

    public void setReveThree(Double[] reveThree) {
        this.reveThree = reveThree;
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

    public Double[] getCashFlowOne() {
        return cashFlowOne;
    }

    public void setCashFlowOne(Double[] cashFlowOne) {
        this.cashFlowOne = cashFlowOne;
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

    public Double[] getCashFlowTwo() {
        return cashFlowTwo;
    }

    public void setCashFlowTwo(Double[] cashFlowTwo) {
        this.cashFlowTwo = cashFlowTwo;
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

    public Double[] getCashFlowThree() {
        return cashFlowThree;
    }

    public void setCashFlowThree(Double[] cashFlowThree) {
        this.cashFlowThree = cashFlowThree;
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

    public Double[] getSunAsset() {
        return sunAsset;
    }

    public void setSunAsset(Double[] sunAsset) {
        this.sunAsset = sunAsset;
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

    public Double[] getSumShareQuity() {
        return sumShareQuity;
    }

    public void setSumShareQuity(Double[] sumShareQuity) {
        this.sumShareQuity = sumShareQuity;
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

    public Double[] getIntangibleAssetRatio() {
        return intangibleAssetRatio;
    }

    public void setIntangibleAssetRatio(Double[] intangibleAssetRatio) {
        this.intangibleAssetRatio = intangibleAssetRatio;
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

    public Double[] getTotalShareIssueB() {
        return totalShareIssueB;
    }

    public void setTotalShareIssueB(Double[] totalShareIssueB) {
        this.totalShareIssueB = totalShareIssueB;
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

    public Double[] getTotalShareIssueA() {
        return totalShareIssueA;
    }

    public void setTotalShareIssueA(Double[] totalShareIssueA) {
        this.totalShareIssueA = totalShareIssueA;
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

    public String getIssuePurPoseSelect() {
        return issuePurPoseSelect;
    }

    public void setIssuePurPoseSelect(String issuePurPoseSelect) {
        this.issuePurPoseSelect = issuePurPoseSelect;
    }
}

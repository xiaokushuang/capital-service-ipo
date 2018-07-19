package com.stock.capital.enterprise.api.regulatory.dto;

import java.io.Serializable;
import java.util.List;

import com.stock.core.dto.OptionDto;

public class ViolationParamDto implements Serializable {

    private static final long serialVersionUID = -5283286219691106934L;

    private String key;
    private String keyAnd;
    private String keyNot;
    /**
     * 公司代码
     */
    private String companyCode;
    /**
     * 更新日期
     */
    private String updateTime;
    /**
     * 处罚金额起始
     */
    private String punishAmountStart;
    /**
     * 处罚金额结束
     */
    private String punishAmountEnd;
    /**
     * 没收金额起始
     */
    private String confiscateAmountStart;
    /**
     * 没收金额结束
     */
    private String confiscateAmountEnd;
    /**
     * 违规类型
     */
    private String violateTypes;
    /**
     * 处罚类型
     */
    private String punishTypes;
    
    // 需求3851 by weishisheng 2018/03/19 start
    /**
     * 所属证监局
     */
    private String belongsToSRBTypes;
    private String belongsToSRBtSelect;
    // 需求3851 by weishisheng 2018/03/19 end
    
    /**
     * 违规类型
     */
    private String violationType;
    private List<String> violationTypeSelect;
    /**
     * 处罚类型
     */
    private String punishType;
    private List<String> punishTypeSelect;
    /**
     * 处罚对象身份
     */
    private String punishManIdtSelect;
    /**
     * 申辩情况
     */
    private String averSituationSelect;
    /**
     * 所属板块
     */
    private String stockBoardSelect;
    /**
     * 所属行业
     */
    private String industrySelect;
    /**
     * 所在地区
     */
    private String areaSelect;
    /**
     * 处罚机构
     */
    private String punishClassSelect;
    /**
     * 是否撤销
     */
    private String isRevokeSelect;

    private String startRow;
    private String pageSize;
    private String orderColumn;
    private String orderByOrder;

    private String lawId;
    private String lawItemId;
    private String person;
    private String type;

    private List<OptionDto> averSituationList;
    private List<OptionDto> punishManIdtList;
    private List<String> averSituationSelectTmp;
    private List<String> punishManIdtSelectTmp;
    
    private String typeIdParam;
    
    // 所选公司-需求3722-违规案例增加关注公司菜单-xuliuzhong-2018/04/18-start
    private String focusCompanySelect;

    private String tabFrom;
    // 需求3722-违规案例增加关注公司菜单-xuliuzhong-2018/04/18-end

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKeyAnd() {
        return keyAnd;
    }

    public void setKeyAnd(String keyAnd) {
        this.keyAnd = keyAnd;
    }

    public String getKeyNot() {
        return keyNot;
    }

    public void setKeyNot(String keyNot) {
        this.keyNot = keyNot;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getPunishAmountStart() {
        return punishAmountStart;
    }

    public void setPunishAmountStart(String punishAmountStart) {
        this.punishAmountStart = punishAmountStart;
    }

    public String getPunishAmountEnd() {
        return punishAmountEnd;
    }

    public void setPunishAmountEnd(String punishAmountEnd) {
        this.punishAmountEnd = punishAmountEnd;
    }

    public String getConfiscateAmountStart() {
        return confiscateAmountStart;
    }

    public void setConfiscateAmountStart(String confiscateAmountStart) {
        this.confiscateAmountStart = confiscateAmountStart;
    }

    public String getConfiscateAmountEnd() {
        return confiscateAmountEnd;
    }

    public void setConfiscateAmountEnd(String confiscateAmountEnd) {
        this.confiscateAmountEnd = confiscateAmountEnd;
    }

    public String getViolateTypes() {
        return violateTypes;
    }

    public void setViolateTypes(String violateTypes) {
        this.violateTypes = violateTypes;
    }

    public String getPunishTypes() {
        return punishTypes;
    }

    public void setPunishTypes(String punishTypes) {
        this.punishTypes = punishTypes;
    }

    // 需求3851 by weishisheng 2018/03/19 start
    public String getBelongsToSRBTypes() {
        return belongsToSRBTypes;
    }

    public void setBelongsToSRBTypes(String belongsToSRBTypes) {
        this.belongsToSRBTypes = belongsToSRBTypes;
    }
    
    public String getBelongsToSRBtSelect() {
        return belongsToSRBtSelect;
    }

    public void setBelongsToSRBtSelect(String belongsToSRBtSelect) {
        this.belongsToSRBtSelect = belongsToSRBtSelect;
    }
    // 需求3851 by weishisheng 2018/03/19 end

    public String getStartRow() {
        return startRow;
    }

    public void setStartRow(String startRow) {
        this.startRow = startRow;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderColumn() {
        return orderColumn;
    }

    public void setOrderColumn(String orderColumn) {
        this.orderColumn = orderColumn;
    }

    public String getOrderByOrder() {
        return orderByOrder;
    }

    public void setOrderByOrder(String orderByOrder) {
        this.orderByOrder = orderByOrder;
    }

    public String getLawId() {
        return lawId;
    }

    public void setLawId(String lawId) {
        this.lawId = lawId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLawItemId() {
        return lawItemId;
    }

    public void setLawItemId(String lawItemId) {
        this.lawItemId = lawItemId;
    }

    public String getPunishManIdtSelect() {
        return punishManIdtSelect;
    }

    public void setPunishManIdtSelect(String punishManIdtSelect) {
        this.punishManIdtSelect = punishManIdtSelect;
    }

    public String getAverSituationSelect() {
        return averSituationSelect;
    }

    public void setAverSituationSelect(String averSituationSelect) {
        this.averSituationSelect = averSituationSelect;
    }

    public String getStockBoardSelect() {
        return stockBoardSelect;
    }

    public void setStockBoardSelect(String stockBoardSelect) {
        this.stockBoardSelect = stockBoardSelect;
    }

    public String getIndustrySelect() {
        return industrySelect;
    }

    public void setIndustrySelect(String industrySelect) {
        this.industrySelect = industrySelect;
    }

    public String getAreaSelect() {
        return areaSelect;
    }

    public void setAreaSelect(String areaSelect) {
        this.areaSelect = areaSelect;
    }

    public String getPunishClassSelect() {
        return punishClassSelect;
    }

    public void setPunishClassSelect(String punishClassSelect) {
        this.punishClassSelect = punishClassSelect;
    }

    public String getIsRevokeSelect() {
        return isRevokeSelect;
    }

    public void setIsRevokeSelect(String isRevokeSelect) {
        this.isRevokeSelect = isRevokeSelect;
    }

    public List<OptionDto> getAverSituationList() {
        return averSituationList;
    }

    public void setAverSituationList(List<OptionDto> averSituationList) {
        this.averSituationList = averSituationList;
    }

    public List<OptionDto> getPunishManIdtList() {
        return punishManIdtList;
    }

    public void setPunishManIdtList(List<OptionDto> punishManIdtList) {
        this.punishManIdtList = punishManIdtList;
    }

    public List<String> getAverSituationSelectTmp() {
        return averSituationSelectTmp;
    }

    public void setAverSituationSelectTmp(List<String> averSituationSelectTmp) {
        this.averSituationSelectTmp = averSituationSelectTmp;
    }

    public List<String> getPunishManIdtSelectTmp() {
        return punishManIdtSelectTmp;
    }

    public void setPunishManIdtSelectTmp(List<String> punishManIdtSelectTmp) {
        this.punishManIdtSelectTmp = punishManIdtSelectTmp;
    }

    public List<String> getViolationTypeSelect() {
        return violationTypeSelect;
    }

    public void setViolationTypeSelect(List<String> violationTypeSelect) {
        this.violationTypeSelect = violationTypeSelect;
    }

    public String getPunishType() {
        return punishType;
    }

    public void setPunishType(String punishType) {
        this.punishType = punishType;
    }

    public List<String> getPunishTypeSelect() {
        return punishTypeSelect;
    }

    public void setPunishTypeSelect(List<String> punishTypeSelect) {
        this.punishTypeSelect = punishTypeSelect;
    }

    public String getViolationType() {
        return violationType;
    }

    public void setViolationType(String violationType) {
        this.violationType = violationType;
    }

    public String getTypeIdParam() {
        return typeIdParam;
    }

    public void setTypeIdParam(String typeIdParam) {
        this.typeIdParam = typeIdParam;
    }

    public String getFocusCompanySelect() {
        return focusCompanySelect;
    }

    public void setFocusCompanySelect(String focusCompanySelect) {
        this.focusCompanySelect = focusCompanySelect;
    }

    public String getTabFrom() {
        return tabFrom;
    }

    public void setTabFrom(String tabFrom) {
        this.tabFrom = tabFrom;
    }

}

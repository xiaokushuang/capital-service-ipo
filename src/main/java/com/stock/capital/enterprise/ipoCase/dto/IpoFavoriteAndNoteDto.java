package com.stock.capital.enterprise.ipoCase.dto;

import java.io.Serializable;
import java.util.List;

public class IpoFavoriteAndNoteDto implements Serializable {
	
	/**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String id;

    private String startRow;

    private String pageSize;

    private String caseTitle;

    private String reptitle;

    private List<String> caseTitleIsList;

    private String note;

    private String userId;

    private String companyId;

    private String companyCode;

    private String companyName;

    private String type;

    private String noteTitle;

    private String bizId;

    private String caseId;

    private String ipaddr;

    private String noticedate;

    private String updateTime;

    private String openFlag;

    private String orderByName;

    private String orderByOrder;

    private String signStatus;

    private String companyCodeName;

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

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


    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }


    public String getNoticedate() {
        return noticedate;
    }

    public void setNoticedate(String noticedate) {
        this.noticedate = noticedate;
    }


    public String getReptitle() {
        return reptitle;
    }

    public void setReptitle(String reptitle) {
        this.reptitle = reptitle;
    }


    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public List<String> getCaseTitleIsList() {
        return caseTitleIsList;
    }

    public void setCaseTitleIsList(List<String> caseTitleIsList) {
        this.caseTitleIsList = caseTitleIsList;
    }

    public String getCaseTitle() {
        return caseTitle;
    }

    public void setCaseTitle(String caseTitle) {
        this.caseTitle = caseTitle;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getIpaddr() {
        return ipaddr;
    }

    public void setIpaddr(String ipaddr) {
        this.ipaddr = ipaddr;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getOpenFlag() {
        return openFlag;
    }

    public void setOpenFlag(String openFlag) {
        this.openFlag = openFlag;
    }

    public String getOrderByName() {
        return orderByName;
    }

    public void setOrderByName(String orderByName) {
        this.orderByName = orderByName;
    }

    public String getOrderByOrder() {
        return orderByOrder;
    }

    public void setOrderByOrder(String orderByOrder) {
        this.orderByOrder = orderByOrder;
    }

    public String getSignStatus() {
        return signStatus;
    }

    public void setSignStatus(String signStatus) {
        this.signStatus = signStatus;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyCodeName() {
        return companyCodeName;
    }

    public void setCompanyCodeName(String companyCodeName) {
        this.companyCodeName = companyCodeName;
    }

}

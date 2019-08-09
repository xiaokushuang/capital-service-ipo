package com.stock.capital.enterprise.ipoCase.dto;

import java.io.Serializable;

public class CollectionsAndNotesDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String caseId;//对应数据库biz_id
    private String userId;//对应数据库user_id
    private String companyId;//对应数据库company_id
    private String ip;//获取用户IP
    private String note;//笔记内容
    private Boolean favoriteFlag;//判断是否收藏


    public Boolean getFavoriteFlag() {
        return favoriteFlag;
    }

    public void setFavoriteFlag(Boolean favoriteFlag) {
        this.favoriteFlag = favoriteFlag;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
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

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}

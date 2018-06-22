package com.stock.capital.enterprise.lawLibrary.dto;

public class LawFavourNoteDto {
    private String id;

    private String lawId;

    private String lawItemId;

    private String userId;

    private String noteContent;

    private String noteContentHL;

    private String updateTime;

    private String publishTime;

    private String ipAddr;

    private String lawName;

    private String lawNameHL;

    private String lawItemName;

    private String lawItemNameHL;

    private String lawItemContent;

    private String lawItemContentHL;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getLawId() {
        return lawId;
    }

    public void setLawId(String lawId) {
        this.lawId = lawId == null ? null : lawId.trim();
    }

    public String getLawItemId() {
        return lawItemId;
    }

    public void setLawItemId(String lawItemId) {
        this.lawItemId = lawItemId == null ? null : lawItemId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr == null ? null : ipAddr.trim();
    }

    public String getLawItemName() {
        return lawItemName;
    }

    public void setLawItemName(String lawItemName) {
        this.lawItemName = lawItemName;
    }

    public String getLawItemContent() {
        return lawItemContent;
    }

    public void setLawItemContent(String lawItemContent) {
        this.lawItemContent = lawItemContent;
    }

    public String getLawName() {
        return lawName;
    }

    public void setLawName(String lawName) {
        this.lawName = lawName;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }

    public String getNoteContentHL() {
        return noteContentHL;
    }

    public void setNoteContentHL(String noteContentHL) {
        this.noteContentHL = noteContentHL;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getLawNameHL() {
        return lawNameHL;
    }

    public void setLawNameHL(String lawNameHL) {
        this.lawNameHL = lawNameHL;
    }

    public String getLawItemNameHL() {
        return lawItemNameHL;
    }

    public void setLawItemNameHL(String lawItemNameHL) {
        this.lawItemNameHL = lawItemNameHL;
    }

    public String getLawItemContentHL() {
        return lawItemContentHL;
    }

    public void setLawItemContentHL(String lawItemContentHL) {
        this.lawItemContentHL = lawItemContentHL;
    }

}
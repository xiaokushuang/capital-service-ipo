package com.stock.capital.enterprise.regulatory.dto;

import java.io.Serializable;
import java.util.List;

import com.stock.core.dto.OptionDto;

public class RegulatoryParamDto implements Serializable {

    private static final long serialVersionUID = -5283286219691106934L;

    private String key;
    private String keyAnd;
    private String keyNot;
    private String releaseTime;
    private String sources;
    private List<OptionDto> sourceList;
    private List<String> sourceSelect;
    private String startRow;
    private String pageSize;
    private String orderColumn;
    private String orderByOrder;

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

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getSources() {
        return sources;
    }

    public void setSources(String sources) {
        this.sources = sources;
    }

    public List<OptionDto> getSourceList() {
        return sourceList;
    }

    public void setSourceList(List<OptionDto> sourceList) {
        this.sourceList = sourceList;
    }

    public List<String> getSourceSelect() {
        return sourceSelect;
    }

    public void setSourceSelect(List<String> sourceSelect) {
        this.sourceSelect = sourceSelect;
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

}

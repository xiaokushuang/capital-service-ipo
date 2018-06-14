package com.stock.capital.enterprise.common.entity;

public class DeclareInfoTypeMap {
    private String id;

    private String declareId;

    private String typeId;

    private Integer sort;

    private String declareTypeName;

    private String declareTypeNo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getDeclareId() {
        return declareId;
    }

    public void setDeclareId(String declareId) {
        this.declareId = declareId == null ? null : declareId.trim();
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId == null ? null : typeId.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getDeclareTypeName() {
        return declareTypeName;
    }

    public void setDeclareTypeName(String declareTypeName) {
        this.declareTypeName = declareTypeName == null ? null : declareTypeName.trim();
    }

    public String getDeclareTypeNo() {
        return declareTypeNo;
    }

    public void setDeclareTypeNo(String declareTypeNo) {
        this.declareTypeNo = declareTypeNo == null ? null : declareTypeNo.trim();
    }
}
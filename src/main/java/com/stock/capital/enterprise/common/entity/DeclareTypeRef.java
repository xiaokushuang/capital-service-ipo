package com.stock.capital.enterprise.common.entity;

public class DeclareTypeRef {
    private String id;

    private String typeId;

    private String decTypeId;

    private Integer sort;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId == null ? null : typeId.trim();
    }

    public String getDecTypeId() {
        return decTypeId;
    }

    public void setDecTypeId(String decTypeId) {
        this.decTypeId = decTypeId == null ? null : decTypeId.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
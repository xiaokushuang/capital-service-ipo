package com.stock.capital.enterprise.common.presistence;

public enum DataSourceEnum {

    CLOUD("cloud"),DONGCAI("dongcai");

    private String value;

    DataSourceEnum(String value){this.value=value;}

    public String getValue() {
        return value;
    }
}

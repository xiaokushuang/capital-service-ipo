package com.stock.capital.enterprise.common.presistence;

public enum DataSourceEnum {

    CLOUD("cloud"),DONGCAI("dongcai"),TOUGUAN("touguan");

    private String value;

    DataSourceEnum(String value){this.value=value;}

    public String getValue() {
        return value;
    }
}

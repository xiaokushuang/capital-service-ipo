package com.stock.capital.enterprise.ipoCase.sample;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel("样例返回类")
public class ResponseBean implements Serializable {

    private static final long serialVersionUID = -5159178663546906786L;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("公司名")
    private String companyName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}

package com.stock.capital.enterprise.ipoCase.dto;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("IPO进程类型列表类")
public class IpoProgressDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("进程类型编码")
    private String treeTypeCode;
    @ApiModelProperty("进程列表")
    private List<IpoProListDto> proList;

    public String getTreeTypeCode() {
        return treeTypeCode;
    }

    public void setTreeTypeCode(String treeTypeCode) {
        this.treeTypeCode = treeTypeCode;
    }

    public List<IpoProListDto> getProList() {
        return proList;
    }

    public void setProList(List<IpoProListDto> proList) {
        this.proList = proList;
    }
}

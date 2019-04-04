package com.stock.capital.enterprise.ipoCase.dto;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("IPO进程类")
public class TreeTypeProgressDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("进程类型列表")
    private List<IpoProgressDto> treeList;

    @ApiModelProperty("主表id")
    private String caseId;

    public List<IpoProgressDto> getTreeList() {
        return treeList;
    }

    public void setTreeList(List<IpoProgressDto> treeList) {
        this.treeList = treeList;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

}

package com.stock.capital.enterprise.ipoCase.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("IPO函件为题标签类")
public class IpoQuestionLabelDto implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty("标签code")
    private String labelCode;
    @ApiModelProperty("标签名称")
    private String labelName;
    @ApiModelProperty("标签包含问题个数")
    private String labelCount;
    @ApiModelProperty("排序序号")
    private int sort;

    public String getLabelCode() {
        return labelCode;
    }

    public void setLabelCode(String labelCode) {
        this.labelCode = labelCode;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public String getLabelCount() {
        return labelCount;
    }

    public void setLabelCount(String labelCount) {
        this.labelCount = labelCount;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}

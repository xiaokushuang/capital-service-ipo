package com.stock.capital.enterprise.ipoCase.dto;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("IPO进程列表类")
public class IpoProListDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("进程编码")
    private String progressType;

    @ApiModelProperty("进程名称")
    private String progressName;

    @ApiModelProperty("进程排序")
    private Integer proSort;

    @ApiModelProperty("进程时间")
    private Integer processTime;

    @ApiModelProperty("文件列表")
    private List<IpoFileRelationDto> relaList;

    public String getProgressType() {
        return progressType;
    }

    public void setProgressType(String progressType) {
        this.progressType = progressType;
    }

    public String getProgressName() {
        return progressName;
    }

    public void setProgressName(String progressName) {
        this.progressName = progressName;
    }

    public Integer getProSort() {
        return proSort;
    }

    public void setProSort(Integer proSort) {
        this.proSort = proSort;
    }

    public List<IpoFileRelationDto> getRelaList() {
        return relaList;
    }

    public void setRelaList(List<IpoFileRelationDto> relaList) {
        this.relaList = relaList;
    }

    public Integer getProcessTime() {
        return processTime;
    }

    public void setProcessTime(Integer processTime) {
        this.processTime = processTime;
    }
}


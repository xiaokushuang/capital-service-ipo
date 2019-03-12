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
    private String processTime;

    @ApiModelProperty("审核结果")
    private String iecResult;

    @ApiModelProperty("距离上次进程时间")
    private String lastDay;

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

    public String getProcessTime() {
        return processTime;
    }

    public void setProcessTime(String processTime) {
        this.processTime = processTime;
    }

    public String getIecResult() {
        return iecResult;
    }

    public void setIecResult(String iecResult) {
        this.iecResult = iecResult;
    }

    public String getLastDay() {
        return lastDay;
    }

    public void setLastDay(String lastDay) {
        this.lastDay = lastDay;
    }
}


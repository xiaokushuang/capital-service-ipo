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

    @ApiModelProperty("是否展示公告")
    private boolean flag;

    @ApiModelProperty("文件列表")
    private List<IpoFileRelationDto> relaList;

    @ApiModelProperty("进程唯一标识")
    private String progressIndex;

    @ApiModelProperty("进程日期是否大于当前日期 0：大于 1：小与")
    private Integer dateCompare;

    @ApiModelProperty("ipoh5所用字段，该步骤是否处理")
    private Boolean handle;

    @ApiModelProperty("审核终止、审核终止进程时 返回前台的法规对应地址id")
    private String addressId;
    @ApiModelProperty("审核终止、审核终止进程时 选项显示")
    List<String> subtitle;

    public Boolean getHandle() {
        return handle;
    }

    public void setHandle(Boolean handle) {
        this.handle = handle;
    }

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

    public Integer getProSort() {
        return proSort;
    }

    public void setProSort(Integer proSort) {
        this.proSort = proSort;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getProgressIndex() {
        return progressIndex;
    }

    public void setProgressIndex(String progressIndex) {
        this.progressIndex = progressIndex;
    }

    public Integer getDateCompare() {
        return dateCompare;
    }

    public void setDateCompare(Integer dateCompare) {
        this.dateCompare = dateCompare;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public List<String> getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(List<String> subtitle) {
        this.subtitle = subtitle;
    }
}


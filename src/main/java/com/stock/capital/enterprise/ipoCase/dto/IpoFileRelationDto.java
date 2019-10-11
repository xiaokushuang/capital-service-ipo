package com.stock.capital.enterprise.ipoCase.dto;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("IPO进程文件类")
public class IpoFileRelationDto implements Serializable {
    @ApiModelProperty("id")
    private String id;
    @ApiModelProperty("关联文件id")
    private String relaId;
    @ApiModelProperty("文件名")
    private String relationFileTitle;
    @ApiModelProperty("公告发布时间")
    private String publishTime;
    @ApiModelProperty("文件排序")
    private Integer tableSort;
    @ApiModelProperty("文件后缀")
    private String suffix;
    @ApiModelProperty("访问路径")
    private String baseUrl;
    @ApiModelProperty("审核结果")
    private String iecResult;
    @ApiModelProperty("状态")
    private String status;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRelaId() {
        return relaId;
    }

    public void setRelaId(String relaId) {
        this.relaId = relaId;
    }

    public String getRelationFileTitle() {
        return relationFileTitle;
    }

    public void setRelationFileTitle(String relationFileTitle) {
        this.relationFileTitle = relationFileTitle;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public Integer getTableSort() {
        return tableSort;
    }

    public void setTableSort(Integer tableSort) {
        this.tableSort = tableSort;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getIecResult() {
        return iecResult;
    }

    public void setIecResult(String iecResult) {
        this.iecResult = iecResult;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
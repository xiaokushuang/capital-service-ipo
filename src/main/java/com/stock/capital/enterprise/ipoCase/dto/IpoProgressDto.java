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
    @ApiModelProperty("是否是第一个进程树")
    private boolean sandian;
    @ApiModelProperty("是否展开")
    private boolean spreadFlag;
    @ApiModelProperty("是否展开")
    private boolean spread;
    @ApiModelProperty("进程类型名称")
    private String treeTypeName;
    @ApiModelProperty("股份公司设立时间")
    private String publishDate;
    //    针对 IPO 导出 word 新增字段
    @ApiModelProperty("进程总历时")
    private String durationDay;

    private String ipoPlate;

    public String getIpoPlate() {
        return ipoPlate;
    }

    public void setIpoPlate(String ipoPlate) {
        this.ipoPlate = ipoPlate;
    }

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

    public boolean isSandian() {
        return sandian;
    }

    public void setSandian(boolean sandian) {
        this.sandian = sandian;
    }

    public boolean isSpreadFlag() {
        return spreadFlag;
    }

    public void setSpreadFlag(boolean spreadFlag) {
        this.spreadFlag = spreadFlag;
    }

    public boolean isSpread() {
        return spread;
    }

    public void setSpread(boolean spread) {
        this.spread = spread;
    }

    public String getTreeTypeName() {
        return treeTypeName;
    }

    public void setTreeTypeName(String treeTypeName) {
        this.treeTypeName = treeTypeName;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getDurationDay() {
        return durationDay;
    }

    public void setDurationDay(String durationDay) {
        this.durationDay = durationDay;
    }
}

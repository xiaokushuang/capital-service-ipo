package com.stock.capital.enterprise.ipoCase.dto;

import org.springframework.format.annotation.NumberFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("募集资金运用")
public class IpoInvestItemDto implements Serializable {
    @ApiModelProperty("id")
    private String id;

    @ApiModelProperty("caseId")
    private String bid;

    @ApiModelProperty("项目名称")
    private String itemName;

    @ApiModelProperty("项目类型编码")
    private String itemType;

    @ApiModelProperty("项目类型名称")
    private String itemTypeStr;

    @NumberFormat(pattern = "#,###,##0.0000")
    @ApiModelProperty("拟募集资金最大净额")
    private BigDecimal investPlanLimit;

    @NumberFormat(pattern = "#,###,##0.0000")
    @ApiModelProperty("拟投入资金")
    private BigDecimal investPlan;

    @NumberFormat(pattern = "#,###,##0.0000")
    @ApiModelProperty("项目总投资")
    private BigDecimal investTotal;

    @NumberFormat(pattern = "#,###,##0.0000")
    @ApiModelProperty("前期已投入资金")
    private BigDecimal investPre;

    @ApiModelProperty("占拟募集资金净额比例")
    private BigDecimal investRate;

    @ApiModelProperty("占拟募集资金净额比例字符串")
    private String investRateStr;

    @ApiModelProperty("排序")
    private Integer sortNo;

    @ApiModelProperty("创建人")
    private String createUser;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("修改人")
    private String updateUser;

    @ApiModelProperty("修改时间")
    private Date updateTime;

    @ApiModelProperty("状态")
    private String status;

    @ApiModelProperty("编辑状态")
    private String editStatus;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid == null ? null : bid.trim();
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
    }

    public BigDecimal getInvestPlan() {
        return investPlan;
    }

    public void setInvestPlan(BigDecimal investPlan) {
        this.investPlan = investPlan;
    }

    public BigDecimal getInvestTotal() {
        return investTotal;
    }

    public void setInvestTotal(BigDecimal investTotal) {
        this.investTotal = investTotal;
    }

    public BigDecimal getInvestPre() {
        return investPre;
    }

    public void setInvestPre(BigDecimal investPre) {
        this.investPre = investPre;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getEditStatus() {
        return editStatus;
    }

    public void setEditStatus(String editStatus) {
        this.editStatus = editStatus;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getItemTypeStr() {
        return itemTypeStr;
    }

    public void setItemTypeStr(String itemTypeStr) {
        this.itemTypeStr = itemTypeStr;
    }

    public BigDecimal getInvestRate() {
        return investRate;
    }

    public void setInvestRate(BigDecimal investRate) {
        this.investRate = investRate;
    }

    public BigDecimal getInvestPlanLimit() {
        return investPlanLimit;
    }

    public void setInvestPlanLimit(BigDecimal investPlanLimit) {
        this.investPlanLimit = investPlanLimit;
    }

    public String getInvestRateStr() {
        return investRateStr;
    }

    public void setInvestRateStr(String investRateStr) {
        this.investRateStr = investRateStr;
    }
}
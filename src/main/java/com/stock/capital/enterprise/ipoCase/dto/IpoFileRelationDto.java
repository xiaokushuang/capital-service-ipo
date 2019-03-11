package com.stock.capital.enterprise.ipoCase.dto;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("IPO进程文件类")
public class IpoFileRelationDto implements Serializable {
    @ApiModelProperty("id")
    private String id;
    @ApiModelProperty("主表案例id")
    private String bid;
    @ApiModelProperty("进程类型编码")
    private String treeTypeCode;
    @ApiModelProperty("进程编码")
    private String processTypeCode;
    @ApiModelProperty("关联文件id")
    private String relaId;
    @ApiModelProperty("文件名")
    private String relationFileTitle;
    @ApiModelProperty("进程时间")
    private Date processTime;
    @ApiModelProperty("股份公司设立方式")
    private String setupType;
    @ApiModelProperty("辅导机构名称")
    private String coachOrgName;
    @ApiModelProperty("是否参加抽查抽签或现场")
    private String drawOrCheck;
    @ApiModelProperty("发审会届次")
    private String iecSession;
    @ApiModelProperty("发审会审核结果")
    private String iecResult;
    @ApiModelProperty("法定批露报纸")
    private String disclosureMedia;
    @ApiModelProperty("创建人")
    private String createUser;
    @ApiModelProperty("创建时间")
    private Date createTime;
    @ApiModelProperty("更新人")
    private String updateUser;
    @ApiModelProperty("更新时间")
    private Date updateTime;
    @ApiModelProperty("状态")
    private String status;
    @ApiModelProperty("公告发布时间")
    private String publishTime;
    @ApiModelProperty("文件排序")
    private Integer tableSort;
    @ApiModelProperty("文件后缀")
    private String suffix;
    @ApiModelProperty("所属进程排序")
    private Integer proSort;

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

    public String getTreeTypeCode() {
        return treeTypeCode;
    }

    public void setTreeTypeCode(String treeTypeCode) {
        this.treeTypeCode = treeTypeCode == null ? null : treeTypeCode.trim();
    }

    public String getProcessTypeCode() {
        return processTypeCode;
    }

    public void setProcessTypeCode(String processTypeCode) {
        this.processTypeCode = processTypeCode == null ? null : processTypeCode.trim();
    }



    public Date getProcessTime() {
        return processTime;
    }

    public void setProcessTime(Date processTime) {
        this.processTime = processTime;
    }

    public String getSetupType() {
        return setupType;
    }

    public void setSetupType(String setupType) {
        this.setupType = setupType == null ? null : setupType.trim();
    }

    public String getCoachOrgName() {
        return coachOrgName;
    }

    public void setCoachOrgName(String coachOrgName) {
        this.coachOrgName = coachOrgName == null ? null : coachOrgName.trim();
    }

    public String getDrawOrCheck() {
        return drawOrCheck;
    }

    public void setDrawOrCheck(String drawOrCheck) {
        this.drawOrCheck = drawOrCheck == null ? null : drawOrCheck.trim();
    }

    public String getIecSession() {
        return iecSession;
    }

    public void setIecSession(String iecSession) {
        this.iecSession = iecSession == null ? null : iecSession.trim();
    }

    public String getIecResult() {
        return iecResult;
    }

    public void setIecResult(String iecResult) {
        this.iecResult = iecResult == null ? null : iecResult.trim();
    }

    public String getDisclosureMedia() {
        return disclosureMedia;
    }

    public void setDisclosureMedia(String disclosureMedia) {
        this.disclosureMedia = disclosureMedia == null ? null : disclosureMedia.trim();
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

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getRelaId() {
        return relaId;
    }

    public void setRelaId(String relaId) {
        this.relaId = relaId;
    }

    public Integer getTableSort() {
        return tableSort;
    }

    public void setTableSort(Integer tableSort) {
        this.tableSort = tableSort;
    }

    public String getRelationFileTitle() {
        return relationFileTitle;
    }

    public void setRelationFileTitle(String relationFileTitle) {
        this.relationFileTitle = relationFileTitle;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public Integer getProSort() {
        return proSort;
    }

    public void setProSort(Integer proSort) {
        this.proSort = proSort;
    }
}
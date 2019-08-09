package com.stock.capital.enterprise.ipoCase.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

/**
 * HeadDataVo.
 *
 * @author chenzhengxiu.
 * @version 0.0.1.
 * @serial 2019/4/1 : base version.
 */
@ApiModel("IPO头部数据返回类")
public class HeadDataVo implements Serializable {

    private static final long serialVersionUID = 8285629715166203686L;

    @ApiModelProperty("案例标题")
    private String title;

    @ApiModelProperty("股份公司设立时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date establishDate;

    @ApiModelProperty("绿色通道")
    private String greenPassage;

    @ApiModelProperty("进程")
    private String processLabel;

    @ApiModelProperty("审核结果")
    private String iecResult;//00：获通过 01：未获通过 02：暂缓表决 03：取消审核

    @ApiModelProperty("辅导工作历时")
    private Integer supportDuration;

    @ApiModelProperty("申报审核历时")
    private Integer auditDuration;

    @ApiModelProperty("公司治理特殊安排")
    private String specialArrange;

    @ApiModelProperty("是否有反馈意见数据 0:显示 1:没有反馈进程 2:没有问题列表")
    private Integer haveFeedback;

    @ApiModelProperty("是否有审核结果数据 0:显示 1:没有发审会进程")
    private Integer haveExamine;

    @ApiModelProperty("是否有发行上市进程 0:显示 1:没有发审会进程")
    private Integer havePublic;

    @ApiModelProperty("是否是科创板 0:否 1:是")
    private Integer isTechBoard;

    @ApiModelProperty("页面是否置灰显示 1: 灰色 0: 不是灰色")
    private Integer isGray;

    @ApiModelProperty("注册结果 07：注册生效 08：不予注册")
    private String registerResult;

    @ApiModelProperty("复核结果")
    private String reviewResult;

    @ApiModelProperty("最近一次结果")
    private String labelResult;

    @ApiModelProperty("收藏主键")
    private String favoriteId;

    @ApiModelProperty("案例笔记")
    private String caseNote;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getEstablishDate() {
        return establishDate;
    }

    public void setEstablishDate(Date establishDate) {
        this.establishDate = establishDate;
    }

    public String getGreenPassage() {
        return greenPassage;
    }

    public void setGreenPassage(String greenPassage) {
        this.greenPassage = greenPassage;
    }

    public String getProcessLabel() {
        return processLabel;
    }

    public void setProcessLabel(String processLabel) {
        this.processLabel = processLabel;
    }

    public String getIecResult() {
        return iecResult;
    }

    public void setIecResult(String iecResult) {
        this.iecResult = iecResult;
    }

    public Integer getSupportDuration() {
        return supportDuration;
    }

    public void setSupportDuration(Integer supportDuration) {
        this.supportDuration = supportDuration;
    }

    public Integer getAuditDuration() {
        return auditDuration;
    }

    public void setAuditDuration(Integer auditDuration) {
        this.auditDuration = auditDuration;
    }

    public String getSpecialArrange() {
        return specialArrange;
    }

    public void setSpecialArrange(String specialArrange) {
        this.specialArrange = specialArrange;
    }

    public Integer getHaveFeedback() {
        return haveFeedback;
    }

    public void setHaveFeedback(Integer haveFeedback) {
        this.haveFeedback = haveFeedback;
    }

    public Integer getHaveExamine() {
        return haveExamine;
    }

    public void setHaveExamine(Integer haveExamine) {
        this.haveExamine = haveExamine;
    }

    public Integer getIsTechBoard() {
        return isTechBoard;
    }

    public void setIsTechBoard(Integer isTechBoard) {
        this.isTechBoard = isTechBoard;
    }

    public Integer getHavePublic() {
        return havePublic;
    }

    public void setHavePublic(Integer havePublic) {
        this.havePublic = havePublic;
    }

    public Integer getIsGray() {
        return isGray;
    }

    public void setIsGray(Integer isGray) {
        this.isGray = isGray;
    }

    public String getRegisterResult() {
        return registerResult;
    }

    public void setRegisterResult(String registerResult) {
        this.registerResult = registerResult;
    }

    public String getReviewResult() {
        return reviewResult;
    }

    public void setReviewResult(String reviewResult) {
        this.reviewResult = reviewResult;
    }

    public String getLabelResult() {
        return labelResult;
    }

    public void setLabelResult(String labelResult) {
        this.labelResult = labelResult;
    }

    public String getFavoriteId() {
        return favoriteId;
    }

    public void setFavoriteId(String favoriteId) {
        this.favoriteId = favoriteId;
    }

    public String getCaseNote() {
        return caseNote;
    }

    public void setCaseNote(String caseNote) {
        this.caseNote = caseNote;
    }
}

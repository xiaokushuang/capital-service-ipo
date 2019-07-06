package com.stock.capital.enterprise.ipoCase.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("IPO审核反馈类")
public class IpoExamineBaseDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("会议标题")
    private String relationFileTitle;
    @ApiModelProperty("公司名称")
    private String companyName;
    @ApiModelProperty("审核结果 00:获通过 01:未获通过 02:暂缓表决 03:取消审核")
    private String iecResult;
    @ApiModelProperty("审核结果")
    private String iecResultStr;
    @ApiModelProperty("发审会委员")
    private String member;
    @ApiModelProperty("发审会日期")
    private String examineDate;
    @ApiModelProperty("发审会日期,年月日格式")
    private String examineDateStr;
    @ApiModelProperty("进程类型")
    private String processTypeCode;

    public String getRelationFileTitle() {
        return relationFileTitle;
    }

    public void setRelationFileTitle(String relationFileTitle) {
        this.relationFileTitle = relationFileTitle;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getIecResult() {
        return iecResult;
    }

    public void setIecResult(String iecResult) {
        this.iecResult = iecResult;
    }

    public String getIecResultStr() {
        return iecResultStr;
    }

    public void setIecResultStr(String iecResultStr) {
        this.iecResultStr = iecResultStr;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public String getExamineDate() {
        return examineDate;
    }

    public void setExamineDate(String examineDate) {
        this.examineDate = examineDate;
    }

    public String getExamineDateStr() {
        return examineDateStr;
    }

    public void setExamineDateStr(String examineDateStr) {
        this.examineDateStr = examineDateStr;
    }

    public String getProcessTypeCode() {
        return processTypeCode;
    }

    public void setProcessTypeCode(String processTypeCode) {
        this.processTypeCode = processTypeCode;
    }
}

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
}

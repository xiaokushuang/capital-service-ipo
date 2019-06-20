package com.stock.capital.enterprise.ipoCase.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * IpoCaseListVo.
 *
 * @author chenzhengxiu.
 * @version 0.0.1.
 * @serial 2019/3/21 : base version.
 */
public class IpoCaseListVo implements Serializable {

    private static final long serialVersionUID = 7646055094108212396L;

    @ApiModelProperty("案例id")
    private String id;

    @ApiModelProperty("东财内码")
    private String orgCode;

    @ApiModelProperty("公司编码")
    private String companyCode;

    @ApiModelProperty("公司名称")
    private String companyName;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("进程")
    private String processLabel;

    @ApiModelProperty("审核结果")
    private String iecResultLabel;

    @ApiModelProperty("净利润")
    private BigDecimal netProfit;

    @ApiModelProperty("营业收入")
    private BigDecimal totalOperateReve;

    @ApiModelProperty("总资产")
    private BigDecimal sunAsset;

    @ApiModelProperty("拟上市板块")
    private String ipoPlate;

    @ApiModelProperty("发审会审核时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date fsProcessTime;

    @ApiModelProperty("审核历时")
    private Integer timeDiff;

    @ApiModelProperty("行业")
    private Integer industryCsrc;


    public Integer getIndustryCsrc() {
        return industryCsrc;
    }

    public void setIndustryCsrc(Integer industryCsrc) {
        this.industryCsrc = industryCsrc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProcessLabel() {
        return processLabel;
    }

    public void setProcessLabel(String processLabel) {
        this.processLabel = processLabel;
    }

    public String getIecResultLabel() {
        return iecResultLabel;
    }

    public void setIecResultLabel(String iecResultLabel) {
        this.iecResultLabel = iecResultLabel;
    }

    public BigDecimal getNetProfit() {
        return netProfit;
    }

    public void setNetProfit(BigDecimal netProfit) {
        this.netProfit = netProfit;
    }

    public BigDecimal getTotalOperateReve() {
        return totalOperateReve;
    }

    public void setTotalOperateReve(BigDecimal totalOperateReve) {
        this.totalOperateReve = totalOperateReve;
    }

    public BigDecimal getSunAsset() {
        return sunAsset;
    }

    public void setSunAsset(BigDecimal sunAsset) {
        this.sunAsset = sunAsset;
    }

    public String getIpoPlate() {
        return ipoPlate;
    }

    public void setIpoPlate(String ipoPlate) {
        this.ipoPlate = ipoPlate;
    }

    public Date getFsProcessTime() {
        return fsProcessTime;
    }

    public void setFsProcessTime(Date fsProcessTime) {
        this.fsProcessTime = fsProcessTime;
    }

    public Integer getTimeDiff() {
        return timeDiff;
    }

    public void setTimeDiff(Integer timeDiff) {
        this.timeDiff = timeDiff;
    }
}

package com.stock.capital.enterprise.ipoCase.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class OtherMarketInfoDto implements Serializable {

    private static final long serialVersionUID = -8905898062160718903L;

    @ApiModelProperty("主键")
    private String id;

    @ApiModelProperty("案例id")
    private String bid;

    @ApiModelProperty("资本市场")
    private String marketType;
    private String marketTypeText;

    @ApiModelProperty("资本市场Code")
    private String marketTypeCode;

    @ApiModelProperty("公司代码")
    private String companyCode;

    @ApiModelProperty("证券简称")
    private String companyName;

    @ApiModelProperty("上市日/挂牌日")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date listTime;

    @ApiModelProperty("退市日/摘牌日")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date delistTime;

    private String otcLevelName;

    private String hostBrokerName;

    private String otcPublishTime;

    public String getOtcLevelName() {
        return otcLevelName;
    }

    public void setOtcLevelName(String otcLevelName) {
        this.otcLevelName = otcLevelName;
    }

    public String getHostBrokerName() {
        return hostBrokerName;
    }

    public void setHostBrokerName(String hostBrokerName) {
        this.hostBrokerName = hostBrokerName;
    }

    public String getOtcPublishTime() {
        return otcPublishTime;
    }

    public void setOtcPublishTime(String otcPublishTime) {
        this.otcPublishTime = otcPublishTime;
    }

    public String getMarketTypeCode() {
        return marketTypeCode;
    }

    public void setMarketTypeCode(String marketTypeCode) {
        this.marketTypeCode = marketTypeCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getMarketType() {
        return marketType;
    }

    public void setMarketType(String marketType) {
        this.marketType = marketType;
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

    public Date getListTime() {
        return listTime;
    }

    public void setListTime(Date listTime) {
        this.listTime = listTime;
    }

    public Date getDelistTime() {
        return delistTime;
    }

    public void setDelistTime(Date delistTime) {
        this.delistTime = delistTime;
    }

    public String getMarketTypeText() {
        return marketTypeText;
    }

    public void setMarketTypeText(String marketTypeText) {
        this.marketTypeText = marketTypeText;
    }
}

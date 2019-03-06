package com.stock.capital.enterprise.ipoCase.dto;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import org.springframework.format.annotation.NumberFormat;

public class IpoPersonInfoDto implements Serializable {

    private static final long serialVersionUID = 8739532255241662818L;

    @ApiModelProperty("主键")
    private String id;

    @ApiModelProperty("案例id")
    private String bid;

    @ApiModelProperty("人员类型")
    private String personType; //1:实际控制人 2:控股股东 3:股东

    @ApiModelProperty("人员名称")
    private String personName;

    @ApiModelProperty("股东性质")
    private String shareHolderNature;

    @ApiModelProperty("持股数量")
    private BigDecimal shareNum;

    @ApiModelProperty("持股比例")
    private BigDecimal shareRatio;


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

    public String getPersonType() {
        return personType;
    }

    public void setPersonType(String personType) {
        this.personType = personType;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getShareHolderNature() {
        return shareHolderNature;
    }

    public void setShareHolderNature(String shareHolderNature) {
        this.shareHolderNature = shareHolderNature;
    }

    public BigDecimal getShareNum() {
        return shareNum;
    }

    public void setShareNum(BigDecimal shareNum) {
        this.shareNum = shareNum;
    }

    public BigDecimal getShareRatio() {
        return shareRatio;
    }

    public void setShareRatio(BigDecimal shareRatio) {
        this.shareRatio = shareRatio;
    }
}

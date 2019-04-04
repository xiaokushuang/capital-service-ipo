package com.stock.capital.enterprise.ipoCase.dto;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * IssueFeeDto.
 *
 * @author chenzhengxiu.
 * @version 0.0.1.
 * @serial 2019/3/11 : base version.
 */
public class IssueFeeDto implements Serializable {

    private static final long serialVersionUID = -3220463588936630838L;

    @ApiModelProperty("主键")
    private String id;

    @ApiModelProperty("案例id")
    private String bid;

    @ApiModelProperty("费用明细")
    private String feeType;

    @ApiModelProperty("金额")
    private BigDecimal feeAmount;

    @ApiModelProperty("占募集资金总额比例")
    private BigDecimal feeRatio;

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

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public BigDecimal getFeeAmount() {
        return feeAmount;
    }

    public void setFeeAmount(BigDecimal feeAmount) {
        this.feeAmount = feeAmount;
    }

    public BigDecimal getFeeRatio() {
        return feeRatio;
    }

    public void setFeeRatio(BigDecimal feeRatio) {
        this.feeRatio = feeRatio;
    }
}

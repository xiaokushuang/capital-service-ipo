package com.stock.capital.enterprise.ipoCase.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * SupplierCustomerVo.
 *
 * @author chenzhengxiu.
 * @version 0.0.1.
 * @serial 2019/3/7 : base version.
 */
@ApiModel("IPO报告期主要供应商及客户情况返回类")
public class SupplierCustomerVo implements Serializable {

    private static final long serialVersionUID = 264553881506091437L;

    @ApiModelProperty("主要供应商-第一年")
    private String firstYearForSupplier;

    @ApiModelProperty("主要供应商-第二年")
    private String secondYearForSupplier;

    @ApiModelProperty("主要供应商-第三年")
    private String thirdYearForSupplier;

    @ApiModelProperty("主要供应商-一期")
    private String onePeriodForSupplier;

    @ApiModelProperty("报告期主要供应商情况列表")
    private List<SupplierCustomerInfoDto> supplierInfoList; //报告期主要供应商情况

    @ApiModelProperty("主要客户-第一年")
    private String firstYearForCustomer;

    @ApiModelProperty("主要客户-第二年")
    private String secondYearForCustomer;

    @ApiModelProperty("主要客户-第三年")
    private String thirdYearForCustomer;

    @ApiModelProperty("主要客户-一期")
    private String onePeriodForCustomer;

    @ApiModelProperty("报告期主要客户情况列表")
    private List<SupplierCustomerInfoDto> customerInfoList; //报告期主要客户情况

    public String getFirstYearForSupplier() {
        return firstYearForSupplier;
    }

    public void setFirstYearForSupplier(String firstYearForSupplier) {
        this.firstYearForSupplier = firstYearForSupplier;
    }

    public String getSecondYearForSupplier() {
        return secondYearForSupplier;
    }

    public void setSecondYearForSupplier(String secondYearForSupplier) {
        this.secondYearForSupplier = secondYearForSupplier;
    }

    public String getThirdYearForSupplier() {
        return thirdYearForSupplier;
    }

    public void setThirdYearForSupplier(String thirdYearForSupplier) {
        this.thirdYearForSupplier = thirdYearForSupplier;
    }

    public String getOnePeriodForSupplier() {
        return onePeriodForSupplier;
    }

    public void setOnePeriodForSupplier(String onePeriodForSupplier) {
        this.onePeriodForSupplier = onePeriodForSupplier;
    }

    public List<SupplierCustomerInfoDto> getSupplierInfoList() {
        return supplierInfoList;
    }

    public void setSupplierInfoList(
        List<SupplierCustomerInfoDto> supplierInfoList) {
        this.supplierInfoList = supplierInfoList;
    }

    public String getFirstYearForCustomer() {
        return firstYearForCustomer;
    }

    public void setFirstYearForCustomer(String firstYearForCustomer) {
        this.firstYearForCustomer = firstYearForCustomer;
    }

    public String getSecondYearForCustomer() {
        return secondYearForCustomer;
    }

    public void setSecondYearForCustomer(String secondYearForCustomer) {
        this.secondYearForCustomer = secondYearForCustomer;
    }

    public String getThirdYearForCustomer() {
        return thirdYearForCustomer;
    }

    public void setThirdYearForCustomer(String thirdYearForCustomer) {
        this.thirdYearForCustomer = thirdYearForCustomer;
    }

    public String getOnePeriodForCustomer() {
        return onePeriodForCustomer;
    }

    public void setOnePeriodForCustomer(String onePeriodForCustomer) {
        this.onePeriodForCustomer = onePeriodForCustomer;
    }

    public List<SupplierCustomerInfoDto> getCustomerInfoList() {
        return customerInfoList;
    }

    public void setCustomerInfoList(
        List<SupplierCustomerInfoDto> customerInfoList) {
        this.customerInfoList = customerInfoList;
    }
}

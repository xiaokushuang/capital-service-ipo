package com.stock.capital.enterprise.ipoCase.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * SupplierCustomerMainDto.
 *
 * @author chenzhengxiu.
 * @version 0.0.1.
 * @serial 2019/3/19 : base version.
 */
public class SupplierCustomerMainDto implements Serializable {

    private static final long serialVersionUID = 2088437319242035270L;

    @ApiModelProperty("主键")
    private String id;

    @ApiModelProperty("案例id")
    private String bid;

    @ApiModelProperty("报告期终止时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date reportPeriod;

    @ApiModelProperty("主要类型")
    private String mainType;//1:供应商 2:客户

    private String createUser;

    private Date createTime;

    @ApiModelProperty("备注")
    private String remark;
    
    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("报告期供应商或客户详细数据")
    private List<SupplierCustomerInfoDto> supplierCustomerInfoList;

    @ApiModelProperty("供应商第一年")
    private String firstYearForSupplier;

    @ApiModelProperty("供应商第二年")
    private String secondYearForSupplier;

    @ApiModelProperty("供应商第三年")
    private String thirdYearForSupplier;

    @ApiModelProperty("客户第一年")
    private String firstYearForCustomer;

    @ApiModelProperty("客户第二年")
    private String secondYearForCustomer;

    @ApiModelProperty("客户第三年")
    private String thirdYearForCustomer;

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

    public Date getReportPeriod() {
        return reportPeriod;
    }

    public void setReportPeriod(Date reportPeriod) {
        this.reportPeriod = reportPeriod;
    }

    public String getMainType() {
        return mainType;
    }

    public void setMainType(String mainType) {
        this.mainType = mainType;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<SupplierCustomerInfoDto> getSupplierCustomerInfoList() {
        return supplierCustomerInfoList;
    }

    public void setSupplierCustomerInfoList(
        List<SupplierCustomerInfoDto> supplierCustomerInfoList) {
        this.supplierCustomerInfoList = supplierCustomerInfoList;
    }

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
}

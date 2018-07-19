package com.stock.capital.enterprise.api.financeStatistics.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.stock.capital.enterprise.common.constant.Global;
import com.stock.core.annotation.FieldAnnotation;
import com.stock.core.dto.Indexable;
import org.apache.solr.client.solrj.beans.Field;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Liulin on 2017/10/20.
 */
@FieldAnnotation(name = "公告",value = Global.FINANCE_INDEX_NAME)
public class FinanceIndexDto extends Indexable implements Serializable {
    private static final long serialVersionUID = -5002003750383159169L;
    //公司代码
    @FieldAnnotation(name = "公司代码",value = "finance_companyCode_t")
    @Field("finance_companyCode_t")
    private String code;
    //公司名字
    @FieldAnnotation(name = "公司代码",value = "finance_companyName_t")
    @Field("finance_companyName_s")
    private String companyName;
    //所属板块
    @FieldAnnotation(name = "所属板块",value = "finance_belongPlate_s")
    @Field("finance_belongPlate_s")
    private List<String> plates;
    //所属地区
    @FieldAnnotation(name = "所属地区",value = "finance_cityName_s")
    @Field("finance_cityName_s")
    private String regionName;
    //所属行业
    @FieldAnnotation(name = "所属行业",value = "finance_pindname001_t")
    @Field("finance_pindname001_t")
    private String industriesName1;
    
    @FieldAnnotation(name = "所属行业",value = "finance_pindname002_t")
    @Field("finance_pindname002_t")
    private String industriesName2;
    
    @FieldAnnotation(name = "所属行业",value = "finance_pindname003_t")
    @Field("finance_pindname003_t")
    private String industriesName3;
    
    @FieldAnnotation(name = "所属行业",value = "finance_pindname004_t")
    @Field("finance_pindname004_t")
    private String industriesName4;
    
    @FieldAnnotation(name = "所属行业",value = "finance_pindname005_t")
    @Field("finance_pindname005_t")
    private String industriesName5;
    
    @FieldAnnotation(name = "所属行业",value = "finance_pindname006_t")
    @Field("finance_pindname006_t")
    private String industriesName6;
    
    @FieldAnnotation(name = "所属行业",value = "finance_pindname008_t")
    @Field("finance_pindname008_t")
    private String industriesName8;
    
    @FieldAnnotation(name = "所属行业",value = "finance_pindname009_t")
    @Field("finance_pindname009_t")
    private String industriesName9;
    //时间
    @FieldAnnotation(name = "融资时间",value = "finance_startDate_t")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Field("finance_startDate_t")
    private Date financeDate;
    //融资方式
    @FieldAnnotation(name = "融资方式",value = "finance_finaType_t")
    @Field("finance_finaType_t")
    private String financeMode;
    //融资金额
    @FieldAnnotation(name = "融资金额",value = "finance_sumFina_d")
    @Field("finance_sumFina_d")
    private String financeAmount;
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public List<String> getPlates() {
        return plates;
    }
    public void setPlates(List<String> plates) {
        this.plates = plates;
    }
    public String getRegionName() {
        return regionName;
    }
    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }
    public String getIndustriesName1() {
        return industriesName1;
    }
    public void setIndustriesName1(String industriesName1) {
        this.industriesName1 = industriesName1;
    }
    public String getIndustriesName2() {
        return industriesName2;
    }
    public void setIndustriesName2(String industriesName2) {
        this.industriesName2 = industriesName2;
    }
    public String getIndustriesName3() {
        return industriesName3;
    }
    public void setIndustriesName3(String industriesName3) {
        this.industriesName3 = industriesName3;
    }
    public String getIndustriesName4() {
        return industriesName4;
    }
    public void setIndustriesName4(String industriesName4) {
        this.industriesName4 = industriesName4;
    }
    public String getIndustriesName5() {
        return industriesName5;
    }
    public void setIndustriesName5(String industriesName5) {
        this.industriesName5 = industriesName5;
    }
    public String getIndustriesName6() {
        return industriesName6;
    }
    public void setIndustriesName6(String industriesName6) {
        this.industriesName6 = industriesName6;
    }
    public String getIndustriesName8() {
        return industriesName8;
    }
    public void setIndustriesName8(String industriesName8) {
        this.industriesName8 = industriesName8;
    }
    public String getIndustriesName9() {
        return industriesName9;
    }
    public void setIndustriesName9(String industriesName9) {
        this.industriesName9 = industriesName9;
    }
    public Date getFinanceDate() {
        return financeDate;
    }
    public void setFinanceDate(Date financeDate) {
        this.financeDate = financeDate;
    }
    public String getFinanceMode() {
        return financeMode;
    }
    public void setFinanceMode(String financeMode) {
        this.financeMode = financeMode;
    }
    public String getFinanceAmount() {
        return financeAmount;
    }
    public void setFinanceAmount(String financeAmount) {
        this.financeAmount = financeAmount;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
   
    
}

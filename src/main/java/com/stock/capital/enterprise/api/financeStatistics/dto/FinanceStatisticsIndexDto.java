package com.stock.capital.enterprise.api.financeStatistics.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.stock.core.annotation.Document;
import com.stock.core.annotation.IndexField;
import com.stock.core.dto.Indexable;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
@Document(indexName = "finance_statistics")
public class FinanceStatisticsIndexDto extends Indexable implements Serializable {

    /**
     * serialVersionUID:TODO(用一句话描述这个变量表示什么).
     */
    private static final long serialVersionUID = 1L;
    // 地区
    //@Field("finance_cityname_s")
    @IndexField(name = "finance_cityname_s")
    private String cityName;
    
    // 公司名
    //@Field("finance_securityname_t")
    @IndexField(name = "finance_securityname_t")
    private String securityName;
    
    // 公司代码
    //@Field("finance_securitycode_t")
    @IndexField(name = "finance_securitycode_t")
    private String securityCode;
    
    // 融资日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    //@Field("finance_startdate_dt")
    @IndexField(name = "finance_startdate_dt")
    private Date financeDate;
    
    // 融资方式
    //@Field("finance_finatype_t")
    @IndexField(name = "finance_finatype_t")
    private String finaType;
    
    // 融资金额
    //@Field("finance_sumfina_d")
    @IndexField(name = "finance_sumfina_d")
    private double sumFina;

    //@Field("finance_finance_id_t")
    @IndexField(name = "finance_finance_id_t")
    private String financeId;

    //@Field("finance_financecode_t")
    @IndexField(name = "finance_financecode_t")
    private String financeCode;

    //@Field("finance_datetypecode_t")
    @IndexField(name = "finance_datetypecode_t")
    private String dateTypeCode;

    //@Field("finance_datetype_t")
    @IndexField(name = "finance_datetype_t")
    private String dateType;

    //@Field("finance_startdate_t")
    @IndexField(name = "finance_startdate_t")
    private String startDate;

    //@Field("finance_enddate_t")
    @IndexField(name = "finance_enddate_t")
    private String endDate;

    //@Field("finance_companycode_t")
    @IndexField(name = "finance_companycode_t")
    private String companyCode;

    //@Field("finance_companyname_t")
    @IndexField(name = "finance_companyname_t")
    private String companyName;

    //@Field("finance_belongplate_t")
    @IndexField(name = "finance_belongplate_t")
    private String belongPlate;

    //@Field("finance_securitytypecode_t")
    @IndexField(name = "finance_securitytypecode_t")
    private String securityTypeCode;

    //@Field("finance_securitytype_t")
    @IndexField(name = "finance_securitytype_t")
    private String securityType;

    //@Field("finance_indtypecode009_t")
    @IndexField(name = "finance_indtypecode009_t")
    private String indTypeCode009;

    //@Field("finance_paramcode009_t")
    @IndexField(name = "finance_paramcode009_t")
    private String paramCode009;

    //@Field("finance_indcode009_t")
    @IndexField(name = "finance_indcode009_t")
    private String indCode009;

    //@Field("finance_indname009_s")
    @IndexField(name = "finance_indname009_s")
    private String indName009;

    //@Field("finance_pindcode009_t")
    @IndexField(name = "finance_pindcode009_t")
    private String pIndCode009;

    //@Field("finance_pindname009_s")
    @IndexField(name = "finance_pindname009_s")
    private String pIndName009;

    //@Field("finance_indtypecode008_t")
    @IndexField(name = "finance_indtypecode008_t")
    private String indTypeCode008;

    //@Field("finance_paramcode008_t")
    @IndexField(name = "finance_paramcode008_t")
    private String paramCode008;

    //@Field("finance_indcode008_t")
    @IndexField(name = "finance_indcode008_t")
    private String indCode008;

    //@Field("finance_indname008_s")
    @IndexField(name = "finance_indname008_s")
    private String indName008;

    //@Field("finance_pindcode008_t")
    @IndexField(name = "finance_pindcode008_t")
    private String pIndCode008;

    //@Field("finance_pindname008_s")
    @IndexField(name = "finance_pindname008_s")
    private String pIndName008;

    //@Field("finance_indtypecode006_t")
    @IndexField(name = "finance_indtypecode006_t")
    private String indTypeCode006;

    //@Field("finance_paramcode006_t")
    @IndexField(name = "finance_paramcode006_t")
    private String paramCode006;

    //@Field("finance_indcode006_t")
    @IndexField(name = "finance_indcode006_t")
    private String indCode006;

    //@Field("finance_indname006_s")
    @IndexField(name = "finance_indname006_s")
    private String indName006;

    //@Field("finance_pindcode006_t")
    @IndexField(name = "finance_pindcode006_t")
    private String pIndCode006;

    //@Field("finance_pindname006_s")
    @IndexField(name = "finance_pindname006_s")
    private String pIndName006;

    //@Field("finance_indtypecode005_t")
    @IndexField(name = "finance_indtypecode005_t")
    private String indTypeCode005;

    //@Field("finance_paramcode005_t")
    @IndexField(name = "finance_paramcode005_t")
    private String paramCode005;

    //@Field("finance_indcode005_t")
    @IndexField(name = "finance_indcode005_t")
    private String indCode005;

    //@Field("finance_indname005_s")
    @IndexField(name = "finance_indname005_s")
    private String indName005;

    //@Field("finance_pindcode005_t")
    @IndexField(name = "finance_pindcode005_t")
    private String pIndCode005;

    //@Field("finance_pindname005_s")
    @IndexField(name = "finance_pindname005_s")
    private String pIndName005;

    //@Field("finance_indtypecode004_t")
    @IndexField(name = "finance_indtypecode004_t")
    private String indTypeCode004;

    //@Field("finance_paramcode004_t")
    @IndexField(name = "finance_paramcode004_t")
    private String paramCode004;

    //@Field("finance_indcode004_t")
    @IndexField(name = "finance_indcode004_t")
    private String indCode004;

    //@Field("finance_indname004_s")
    @IndexField(name = "finance_indname004_s")
    private String indName004;

    //@Field("finance_pindcode004_t")
    @IndexField(name = "finance_pindcode004_t")
    private String pIndCode004;

    //@Field("finance_pindname004_s")
    @IndexField(name = "finance_pindname004_s")
    private String pIndName004;

    //@Field("finance_indtypecode003_t")
    @IndexField(name = "finance_indtypecode003_t")
    private String indTypeCode003;

    //@Field("finance_paramcode003_t")
    @IndexField(name = "finance_paramcode003_t")
    private String paramCode003;

    //@Field("finance_indcode003_t")
    @IndexField(name = "finance_indcode003_t")
    private String indCode003;

    //@Field("finance_indname003_s")
    @IndexField(name = "finance_indname003_s")
    private String indName003;

    //@Field("finance_pindcode003_t")
    @IndexField(name = "finance_pindcode003_t")
    private String pIndCode003;

    //@Field("finance_pindname003_s")
    @IndexField(name = "finance_pindname003_s")
    private String pIndName003;

    //@Field("finance_indtypecode002_t")
    @IndexField(name = "finance_indtypecode002_t")
    private String indTypeCode002;

    //@Field("finance_paramcode002_t")
    @IndexField(name = "finance_paramcode002_t")
    private String paramCode002;

    //@Field("finance_indcode002_t")
    @IndexField(name = "finance_indcode002_t")
    private String indCode002;

    //@Field("finance_indname002_s")
    @IndexField(name = "finance_indname002_s")
    private String indName002;

    //@Field("finance_pindcode002_t")
    @IndexField(name = "finance_pindcode002_t")
    private String pIndCode002;

    //@Field("finance_pindname002_s")
    @IndexField(name = "finance_pindname002_s")
    private String pIndName002;

    //@Field("finance_indtypecode001_t")
    @IndexField(name = "finance_indtypecode001_t")
    private String indTypeCode001;

    //@Field("finance_paramcode001_t")
    @IndexField(name = "finance_paramcode001_t")
    private String paramCode001;

    //@Field("finance_indcode001_t")
    @IndexField(name = "finance_indcode001_t")
    private String indCode001;

    //@Field("finance_indname001_s")
    @IndexField(name = "finance_indname001_s")
    private String indName001;

    //@Field("finance_pindcode001_t")
    @IndexField(name = "finance_pindcode001_t")
    private String pIndCode001;

    //@Field("finance_pindname001_s")
    @IndexField(name = "finance_pindname001_s")
    private String pIndName001;
    
    // 公司简称
    //@Field("finance_securityshortname_t")
    @IndexField(name = "finance_securityshortname_t")
    private String securityShortName;
    
    public String getSecurityShortName() {
        return securityShortName;
    }

    public void setSecurityShortName(String securityShortName) {
        this.securityShortName = securityShortName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getBelongPlate() {
        return belongPlate;
    }

    public void setBelongPlate(String belongPlate) {
        this.belongPlate = belongPlate;
    }

    public void setSumFina(double sumFina) {
        this.sumFina = sumFina;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    //@Field("finance_citycode_t")
    @IndexField(name = "finance_citycode_t")
    private String cityCode;

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public Date getFinanceDate() {
        return financeDate;
    }

    public void setFinanceDate(Date financeDate) {
        this.financeDate = financeDate;
    }

    public String getFinaType() {
        return finaType;
    }

    public void setFinaType(String finaType) {
        this.finaType = finaType;
    }

    public double getSumFina() {
        return sumFina;
    }

    public String getIndTypeCode009() {
        return indTypeCode009;
    }

    public void setIndTypeCode009(String indTypeCode009) {
        this.indTypeCode009 = indTypeCode009;
    }

    public String getIndTypeCode008() {
        return indTypeCode008;
    }

    public void setIndTypeCode008(String indTypeCode008) {
        this.indTypeCode008 = indTypeCode008;
    }

    public String getIndTypeCode006() {
        return indTypeCode006;
    }

    public void setIndTypeCode006(String indTypeCode006) {
        this.indTypeCode006 = indTypeCode006;
    }

    public String getIndTypeCode005() {
        return indTypeCode005;
    }

    public void setIndTypeCode005(String indTypeCode005) {
        this.indTypeCode005 = indTypeCode005;
    }

    public String getIndTypeCode004() {
        return indTypeCode004;
    }

    public void setIndTypeCode004(String indTypeCode004) {
        this.indTypeCode004 = indTypeCode004;
    }

    public String getIndTypeCode003() {
        return indTypeCode003;
    }

    public void setIndTypeCode003(String indTypeCode003) {
        this.indTypeCode003 = indTypeCode003;
    }

    public String getIndTypeCode002() {
        return indTypeCode002;
    }

    public void setIndTypeCode002(String indTypeCode002) {
        this.indTypeCode002 = indTypeCode002;
    }

    public String getIndTypeCode001() {
        return indTypeCode001;
    }

    public void setIndTypeCode001(String indTypeCode001) {
        this.indTypeCode001 = indTypeCode001;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    public String getSecurityName() {
        return securityName;
    }

    public void setSecurityName(String securityName) {
        this.securityName = securityName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public String getFinanceId() {
        return financeId;
    }

    public void setFinanceId(String financeId) {
        this.financeId = financeId;
    }

    public String getFinanceCode() {
        return financeCode;
    }

    public void setFinanceCode(String financeCode) {
        this.financeCode = financeCode;
    }

    public String getDateTypeCode() {
        return dateTypeCode;
    }

    public void setDateTypeCode(String dateTypeCode) {
        this.dateTypeCode = dateTypeCode;
    }

    public String getDateType() {
        return dateType;
    }

    public void setDateType(String dateType) {
        this.dateType = dateType;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getSecurityTypeCode() {
        return securityTypeCode;
    }

    public void setSecurityTypeCode(String securityTypeCode) {
        this.securityTypeCode = securityTypeCode;
    }

    public String getSecurityType() {
        return securityType;
    }

    public void setSecurityType(String securityType) {
        this.securityType = securityType;
    }

    public String getParamCode009() {
        return paramCode009;
    }

    public void setParamCode009(String paramCode009) {
        this.paramCode009 = paramCode009;
    }

    public String getIndCode009() {
        return indCode009;
    }

    public void setIndCode009(String indCode009) {
        this.indCode009 = indCode009;
    }

    public String getIndName009() {
        return indName009;
    }

    public void setIndName009(String indName009) {
        this.indName009 = indName009;
    }

    public String getpIndCode009() {
        return pIndCode009;
    }

    public void setpIndCode009(String pIndCode009) {
        this.pIndCode009 = pIndCode009;
    }

    public String getpIndName009() {
        return pIndName009;
    }

    public void setpIndName009(String pIndName009) {
        this.pIndName009 = pIndName009;
    }

    public String getParamCode008() {
        return paramCode008;
    }

    public void setParamCode008(String paramCode008) {
        this.paramCode008 = paramCode008;
    }

    public String getIndCode008() {
        return indCode008;
    }

    public void setIndCode008(String indCode008) {
        this.indCode008 = indCode008;
    }

    public String getIndName008() {
        return indName008;
    }

    public void setIndName008(String indName008) {
        this.indName008 = indName008;
    }

    public String getpIndCode008() {
        return pIndCode008;
    }

    public void setpIndCode008(String pIndCode008) {
        this.pIndCode008 = pIndCode008;
    }

    public String getpIndName008() {
        return pIndName008;
    }

    public void setpIndName008(String pIndName008) {
        this.pIndName008 = pIndName008;
    }

    public String getParamCode006() {
        return paramCode006;
    }

    public void setParamCode006(String paramCode006) {
        this.paramCode006 = paramCode006;
    }

    public String getIndCode006() {
        return indCode006;
    }

    public void setIndCode006(String indCode006) {
        this.indCode006 = indCode006;
    }

    public String getIndName006() {
        return indName006;
    }

    public void setIndName006(String indName006) {
        this.indName006 = indName006;
    }

    public String getpIndCode006() {
        return pIndCode006;
    }

    public void setpIndCode006(String pIndCode006) {
        this.pIndCode006 = pIndCode006;
    }

    public String getpIndName006() {
        return pIndName006;
    }

    public void setpIndName006(String pIndName006) {
        this.pIndName006 = pIndName006;
    }

    public String getParamCode005() {
        return paramCode005;
    }

    public void setParamCode005(String paramCode005) {
        this.paramCode005 = paramCode005;
    }

    public String getIndCode005() {
        return indCode005;
    }

    public void setIndCode005(String indCode005) {
        this.indCode005 = indCode005;
    }

    public String getIndName005() {
        return indName005;
    }

    public void setIndName005(String indName005) {
        this.indName005 = indName005;
    }

    public String getpIndCode005() {
        return pIndCode005;
    }

    public void setpIndCode005(String pIndCode005) {
        this.pIndCode005 = pIndCode005;
    }

    public String getpIndName005() {
        return pIndName005;
    }

    public void setpIndName005(String pIndName005) {
        this.pIndName005 = pIndName005;
    }

    public String getParamCode004() {
        return paramCode004;
    }

    public void setParamCode004(String paramCode004) {
        this.paramCode004 = paramCode004;
    }

    public String getIndCode004() {
        return indCode004;
    }

    public void setIndCode004(String indCode004) {
        this.indCode004 = indCode004;
    }

    public String getIndName004() {
        return indName004;
    }

    public void setIndName004(String indName004) {
        this.indName004 = indName004;
    }

    public String getpIndCode004() {
        return pIndCode004;
    }

    public void setpIndCode004(String pIndCode004) {
        this.pIndCode004 = pIndCode004;
    }

    public String getpIndName004() {
        return pIndName004;
    }

    public void setpIndName004(String pIndName004) {
        this.pIndName004 = pIndName004;
    }

    public String getParamCode003() {
        return paramCode003;
    }

    public void setParamCode003(String paramCode003) {
        this.paramCode003 = paramCode003;
    }

    public String getIndCode003() {
        return indCode003;
    }

    public void setIndCode003(String indCode003) {
        this.indCode003 = indCode003;
    }

    public String getIndName003() {
        return indName003;
    }

    public void setIndName003(String indName003) {
        this.indName003 = indName003;
    }

    public String getpIndCode003() {
        return pIndCode003;
    }

    public void setpIndCode003(String pIndCode003) {
        this.pIndCode003 = pIndCode003;
    }

    public String getpIndName003() {
        return pIndName003;
    }

    public void setpIndName003(String pIndName003) {
        this.pIndName003 = pIndName003;
    }

    public String getParamCode002() {
        return paramCode002;
    }

    public void setParamCode002(String paramCode002) {
        this.paramCode002 = paramCode002;
    }

    public String getIndCode002() {
        return indCode002;
    }

    public void setIndCode002(String indCode002) {
        this.indCode002 = indCode002;
    }

    public String getIndName002() {
        return indName002;
    }

    public void setIndName002(String indName002) {
        this.indName002 = indName002;
    }

    public String getpIndCode002() {
        return pIndCode002;
    }

    public void setpIndCode002(String pIndCode002) {
        this.pIndCode002 = pIndCode002;
    }

    public String getpIndName002() {
        return pIndName002;
    }

    public void setpIndName002(String pIndName002) {
        this.pIndName002 = pIndName002;
    }

    public String getParamCode001() {
        return paramCode001;
    }

    public void setParamCode001(String paramCode001) {
        this.paramCode001 = paramCode001;
    }

    public String getIndCode001() {
        return indCode001;
    }

    public void setIndCode001(String indCode001) {
        this.indCode001 = indCode001;
    }

    public String getIndName001() {
        return indName001;
    }

    public void setIndName001(String indName001) {
        this.indName001 = indName001;
    }

    public String getpIndCode001() {
        return pIndCode001;
    }

    public void setpIndCode001(String pIndCode001) {
        this.pIndCode001 = pIndCode001;
    }

    public String getpIndName001() {
        return pIndName001;
    }

    public void setpIndName001(String pIndName001) {
        this.pIndName001 = pIndName001;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}

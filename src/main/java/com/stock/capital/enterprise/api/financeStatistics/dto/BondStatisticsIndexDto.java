package com.stock.capital.enterprise.api.financeStatistics.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.stock.core.dto.Indexable;
import org.apache.solr.client.solrj.beans.Field;

import java.io.Serializable;

@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class BondStatisticsIndexDto extends Indexable implements Serializable{
    
    /**
     * serialVersionUID:TODO(用一句话描述这个变量表示什么).
     */
    private static final long serialVersionUID = 1L;
    @Field("bond_bondid_t")
    private String bondId;
    
    @Field("bond_securitycode_t")
    private String securityCode;
    
    @Field("bond_securityname_t")
    private String securityName;
    
    @Field("bond_securitysname_t")
    private String securitysName;
    
    @Field("bond_securitytype_t")
    private String securityType;
    
    @Field("bond_securitycodename_t")
    private String securityCodeName;
    
    @Field("bond_issuenum_t")
    private String issueNum;
    
    @Field("bond_bondperiod_t")
    private String bondPeriod;
    
    
    @Field("bond_issuevol_d")
    private double issueVol;
    
    @Field("bond_issuedate_t")
    private String issueDate;
    
    @Field("bond_listpubdate_t")
    private String listPubDate;
    
    @Field("bond_listdate_t")
    private String listDate;
    
    @Field("bond_companycode_s")
    private String companyCode;
    
    @Field("bond_companycode_s")
    private String companyName;
    
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    @Field("bond_indname001_s")
    private String indName001;
    
    @Field("bond_pindcode001_t")
    private String pIndCode001;
    
    @Field("bond_pindname001_s")
    private String pIndName001;

    @Field("bond_cityname_s")
    private String cityName;
    
    @Field("bond_citycode_t")
    private String cityCode;
    
    @Field("bond_belongplate_t")
    private String belongPlate;
    
    @Field("bond_indtypecode009_t")
    private String indTypeCode009;
    
    @Field("bond_paramcode009_t")
    private String paramCode009;
    
    @Field("bond_indcode009_t")
    private String indCode009;
    
    @Field("bond_indname009_s")
    private String indName009;
    
    @Field("bond_pindcode009_s")
    private String pIndCode009;
    
    @Field("bond_pindname009_s")
    private String pIndName009;


    @Field("bond_indtypecode008_t")
    private String indTypeCode008;
    
    @Field("bond_paramcode008_t")
    private String paramCode008;
    
    @Field("bond_indcode008_t")
    private String indCode008;
    
    @Field("bond_indname008_s")
    private String indName008;
    
    @Field("bond_pindcode008_t")
    private String pIndCode008;
    
    @Field("bond_pindname008_s")
    private String pIndName008;


    @Field("bond_indtypecode006_t")
    private String indTypeCode006;
    
    @Field("bond_paramcode006_t")
    private String paramCode006;
    
    @Field("bond_indcode006_t")
    private String indCode006;
    
    @Field("bond_indname006_s")
    private String indName006;
    
    @Field("bond_pindcode006_t")
    private String pIndCode006;
    
    @Field("bond_pindname006_s")
    private String pIndName006;


    @Field("bond_indtypecode005_t")
    private String indTypeCode005;
    
    @Field("bond_paramcode005_t")
    private String paramCode005;
    
    @Field("bond_indcode005_t")
    private String indCode005;
    
    @Field("bond_indname005_s")
    private String indName005;
    
    @Field("bond_pindcode005_t")
    private String pIndCode005;
    
    @Field("bond_pindname005_s")
    private String pIndName005;

    @Field("bond_indtypecode004_t")
    private String indTypeCode004;
    
    @Field("bond_paramcode004_t")
    private String paramCode004;
    
    @Field("bond_indcode004_t")
    private String indCode004;
    
    @Field("bond_indname004_s")
    private String indName004;
    
    @Field("bond_pindcode004_t")
    private String pIndCode004;
    
    @Field("bond_pindname004_s")
    private String pIndName004;
    

    @Field("bond_indtypecode003_t")
    private String indTypeCode003;
    
    @Field("bond_paramcode003_t")
    private String paramCode003;
    
    @Field("bond_indcode003_t")
    private String indCode003;
    
    @Field("bond_indname003_s")
    private String indName003;
    
    @Field("bond_pindcode003_t")
    private String pIndCode003;
    
    @Field("bond_pindname003_s")
    private String pIndName003;
    

    @Field("bond_indtypecode002_t")
    private String indTypeCode002;
    
    @Field("bond_paramcode002_t")
    private String paramCode002;
    
    @Field("bond_indcode002_t")
    private String indCode002;
    
    @Field("bond_indname002_s")
    private String indName002;
    
    @Field("bond_pindcode002_t")
    private String pIndCode002;
    
    @Field("bond_pindname002_s")
    private String pIndName002;

    @Field("bond_indtypecode001_t")
    private String indTypeCode001;
    
    @Field("bond_paramcode001_t")
    private String paramCode001;
    
    @Field("bond_indcode001_t")
    private String indCode001;
    
    
    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
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

    public String getSecuritysName() {
        return securitysName;
    }

    public void setSecuritysName(String securitysName) {
        this.securitysName = securitysName;
    }

    

    public String getSecurityType() {
        return securityType;
    }

    public void setSecurityType(String securityType) {
        this.securityType = securityType;
    }

    public String getSecurityCodeName() {
        return securityCodeName;
    }

    public void setSecurityCodeName(String securityCodeName) {
        this.securityCodeName = securityCodeName;
    }

    public String getIssueNum() {
        return issueNum;
    }

    public void setIssueNum(String issueNum) {
        this.issueNum = issueNum;
    }

    public String getBondPeriod() {
        return bondPeriod;
    }

    public void setBondPeriod(String bondPeriod) {
        this.bondPeriod = bondPeriod;
    }

    public double getIssueVol() {
        return issueVol;
    }

    public void setIssueVol(double issueVol) {
        this.issueVol = issueVol;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getListPubDate() {
        return listPubDate;
    }

    public void setListPubDate(String listPubDate) {
        this.listPubDate = listPubDate;
    }

    public String getListDate() {
        return listDate;
    }

    public void setListDate(String listDate) {
        this.listDate = listDate;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
    public String getBelongPlate() {
        return belongPlate;
    }

    public void setBelongPlate(String belongPlate) {
        this.belongPlate = belongPlate;
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

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getBondId() {
        return bondId;
    }

    public void setBondId(String bondId) {
        this.bondId = bondId;
    }
    
}
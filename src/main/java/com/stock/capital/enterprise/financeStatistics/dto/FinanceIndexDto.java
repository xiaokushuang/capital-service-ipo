package com.stock.capital.enterprise.financeStatistics.dto;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Created by liulin on 2017/10/22.
 */


    public class FinanceIndexDto implements Serializable {

    private static final long serialVersionUID = -5002003750383159169L;
    //公司代码
    private String securityCode;
    //公司名字
    private String securityName;

    //所属地区
    private String cityName;
    //所属行业
    private String indName001;
    
    private String indName002;
    
    private String indName003;
    
    private String indName004;
    
    private String indName005;
    
    private String indName006;
    
    private String indName008;
    
    private String indName009;

    private String pIndName001;
    
    private String pIndName002;
    
    private String pIndName003;
    
    private String pIndName004;
    
    private String pIndName005;
    
    private String pIndName006;
    
    private String pIndName008;
    
    private String pIndName009;
    
    //时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date financeDate;
    //融资方式
    private String finaType;
    //融资金额
    private Double sumFina;
  
 
   

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    
 
    public Double getSumFina() {
        return sumFina;
    }
    public void setSumFina(Double sumFina) {
        this.sumFina = sumFina;
    }
    public String getFinaType() {
        return finaType;
    }
    public void setFinaType(String finaType) {
        this.finaType = finaType;
    }
    public String getCityName() {
        return cityName;
    }
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
    public String getIndName006() {
        return indName006;
    }
    public void setIndName006(String indName006) {
        this.indName006 = indName006;
    }
    public String getIndName009() {
        return indName009;
    }
    public void setIndName009(String indName009) {
        this.indName009 = indName009;
    }
    public String getIndName008() {
        return indName008;
    }
    public void setIndName008(String indName008) {
        this.indName008 = indName008;
    }
    public String getIndName005() {
        return indName005;
    }
    public void setIndName005(String indName005) {
        this.indName005 = indName005;
    }
    public String getIndName004() {
        return indName004;
    }
    public void setIndName004(String indName004) {
        this.indName004 = indName004;
    }
    public String getIndName003() {
        return indName003;
    }
    public void setIndName003(String indName003) {
        this.indName003 = indName003;
    }
    public String getIndName002() {
        return indName002;
    }
    public void setIndName002(String indName002) {
        this.indName002 = indName002;
    }
    public String getIndName001() {
        return indName001;
    }
    public void setIndName001(String indName001) {
        this.indName001 = indName001;
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


    public Date getFinanceDate() {
        return financeDate;
    }


    public void setFinanceDate(Date financeDate) {
        this.financeDate = financeDate;
    }


    public String getpIndName001() {
        return pIndName001;
    }


    public void setpIndName001(String pIndName001) {
        this.pIndName001 = pIndName001;
    }


    public String getpIndName002() {
        return pIndName002;
    }


    public void setpIndName002(String pIndName002) {
        this.pIndName002 = pIndName002;
    }


    public String getpIndName003() {
        return pIndName003;
    }


    public void setpIndName003(String pIndName003) {
        this.pIndName003 = pIndName003;
    }


    public String getpIndName004() {
        return pIndName004;
    }


    public void setpIndName004(String pIndName004) {
        this.pIndName004 = pIndName004;
    }


    public String getpIndName005() {
        return pIndName005;
    }


    public void setpIndName005(String pIndName005) {
        this.pIndName005 = pIndName005;
    }


    public String getpIndName006() {
        return pIndName006;
    }


    public void setpIndName006(String pIndName006) {
        this.pIndName006 = pIndName006;
    }


    public String getpIndName008() {
        return pIndName008;
    }


    public void setpIndName008(String pIndName008) {
        this.pIndName008 = pIndName008;
    }


    public String getpIndName009() {
        return pIndName009;
    }


    public void setpIndName009(String pIndName009) {
        this.pIndName009 = pIndName009;
    }
}

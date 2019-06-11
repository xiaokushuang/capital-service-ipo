package com.stock.capital.enterprise.ipoInterfaceH5.dto;

import java.io.Serializable;

public class IpoH5Dto implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;// 主键
    private String bid;//  案例主键
    private String research;//  研发投入
    private String researchRanking;//  研发投入排名
    private String patent;//  专利情况
    private String patentRanking;//  专利情况排名
    private String patentRankingYear;//  专利情况年度
    private String researchPeo;//  研发人员
    private String researchPeoRanking;//  研发人员占比排名
    private String taking;//  营业收入
    private String takingRanking;//  营业收入排名
    private String takingRankingYear;//  营业收入年度
    private String profits;//  净利润
    private String profitsRanking;//  净利润排名
    private String profitsRankingYear;//  净利润年度
    private String assets;//  资产总额
    private String assetsRanking;//  资产总额排名
    private String assetsRankingYear;//  资产总额年度
    private String researchYear;//研发投入排名年度
    private String researchPeoYear;//研发人员占比排名年度
    private String dongcaiYear;//东财年度
    private String researchPlateFiavg;//研发投入平均
    private String researchPlateSeavg;//研发投入平均
    private String researchPlateThavg;//研发投入平均
    private String takingFiavg;//营业收入平均
    private String takingSeavg;//营业收入平均
    private String takingThavg;//营业收入平均
    private String industryCsrc;//行业
    private String researchPeoCore;//核心研发
    private String companyPeo;//公司总员工
    private String orgCode;

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getResearchPeoCore() {
        return researchPeoCore;
    }

    public void setResearchPeoCore(String researchPeoCore) {
        this.researchPeoCore = researchPeoCore;
    }

    public String getCompanyPeo() {
        return companyPeo;
    }

    public void setCompanyPeo(String companyPeo) {
        this.companyPeo = companyPeo;
    }

    public String getResearch() {
        return research;
    }

    public void setResearch(String research) {
        this.research = research;
    }

    public String getPatent() {
        return patent;
    }

    public void setPatent(String patent) {
        this.patent = patent;
    }

    public String getResearchPeo() {
        return researchPeo;
    }

    public void setResearchPeo(String researchPeo) {
        this.researchPeo = researchPeo;
    }

    public String getTaking() {
        return taking;
    }

    public void setTaking(String taking) {
        this.taking = taking;
    }

    public String getProfits() {
        return profits;
    }

    public void setProfits(String profits) {
        this.profits = profits;
    }

    public String getAssets() {
        return assets;
    }

    public void setAssets(String assets) {
        this.assets = assets;
    }

    public String getIndustryCsrc() {
        return industryCsrc;
    }

    public void setIndustryCsrc(String industryCsrc) {
        this.industryCsrc = industryCsrc;
    }

    public String getResearchPlateFiavg() {
        return researchPlateFiavg;
    }

    public void setResearchPlateFiavg(String researchPlateFiavg) {
        this.researchPlateFiavg = researchPlateFiavg;
    }

    public String getResearchPlateSeavg() {
        return researchPlateSeavg;
    }

    public void setResearchPlateSeavg(String researchPlateSeavg) {
        this.researchPlateSeavg = researchPlateSeavg;
    }

    public String getResearchPlateThavg() {
        return researchPlateThavg;
    }

    public void setResearchPlateThavg(String researchPlateThavg) {
        this.researchPlateThavg = researchPlateThavg;
    }

    public String getTakingFiavg() {
        return takingFiavg;
    }

    public void setTakingFiavg(String takingFiavg) {
        this.takingFiavg = takingFiavg;
    }

    public String getTakingSeavg() {
        return takingSeavg;
    }

    public void setTakingSeavg(String takingSeavg) {
        this.takingSeavg = takingSeavg;
    }

    public String getTakingThavg() {
        return takingThavg;
    }

    public void setTakingThavg(String takingThavg) {
        this.takingThavg = takingThavg;
    }



    public String getPatentRankingYear() {
        return patentRankingYear;
    }

    public void setPatentRankingYear(String patentRankingYear) {
        this.patentRankingYear = patentRankingYear;
    }

    public String getTakingRankingYear() {
        return takingRankingYear;
    }

    public void setTakingRankingYear(String takingRankingYear) {
        this.takingRankingYear = takingRankingYear;
    }

    public String getProfitsRankingYear() {
        return profitsRankingYear;
    }

    public void setProfitsRankingYear(String profitsRankingYear) {
        this.profitsRankingYear = profitsRankingYear;
    }

    public String getAssetsRankingYear() {
        return assetsRankingYear;
    }

    public void setAssetsRankingYear(String assetsRankingYear) {
        this.assetsRankingYear = assetsRankingYear;
    }


    public String getResearchYear() {
        return researchYear;
    }

    public void setResearchYear(String researchYear) {
        this.researchYear = researchYear;
    }

    public String getResearchPeoYear() {
        return researchPeoYear;
    }

    public void setResearchPeoYear(String researchPeoYear) {
        this.researchPeoYear = researchPeoYear;
    }

    public String getDongcaiYear() {
        return dongcaiYear;
    }

    public void setDongcaiYear(String dongcaiYear) {
        this.dongcaiYear = dongcaiYear;
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

    public String getResearchRanking() {
        return researchRanking;
    }

    public void setResearchRanking(String researchRanking) {
        this.researchRanking = researchRanking;
    }

    public String getPatentRanking() {
        return patentRanking;
    }

    public void setPatentRanking(String patentRanking) {
        this.patentRanking = patentRanking;
    }

    public String getResearchPeoRanking() {
        return researchPeoRanking;
    }

    public void setResearchPeoRanking(String researchPeoRanking) {
        this.researchPeoRanking = researchPeoRanking;
    }

    public String getTakingRanking() {
        return takingRanking;
    }

    public void setTakingRanking(String takingRanking) {
        this.takingRanking = takingRanking;
    }

    public String getProfitsRanking() {
        return profitsRanking;
    }

    public void setProfitsRanking(String profitsRanking) {
        this.profitsRanking = profitsRanking;
    }

    public String getAssetsRanking() {
        return assetsRanking;
    }

    public void setAssetsRanking(String assetsRanking) {
        this.assetsRanking = assetsRanking;
    }
}

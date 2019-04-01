package com.stock.capital.enterprise.ipoCase.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.stock.core.dto.Indexable;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.apache.solr.client.solrj.beans.Field;

/**
 * IpoCaseIndexDto.
 *
 * @author chenzhengxiu.
 * @version 0.0.1.
 * @serial 2019/3/26 : base version.
 */
public class IpoCaseIndexDto extends Indexable implements Serializable {


    private static final long serialVersionUID = 5630506009938244217L;

    @Field("ipo_org_code_t")
    private String orgCode;//东财内码

    @Field("ipo_company_code_t")
    private String companyCode;//公司编码

    @Field("ipo_company_name_t")
    private String companyName;//公司简称

    @Field("ipo_title_t")
    private String title;//标题

    @Field("ipo_process_t")
    private String process;//进程

    @Field("ipo_process_label_t")
    private String processLabel;//进程名称

    @Field("ipo_iec_result_t")
    private String iecResult;//审核结果

    @Field("ipo_iec_result_label_t")
    private String iecResultLabel;//审核结果名称

    @Field("ipo_profit_one_d")
    private Double netProfitOne;//最近一个年度净利润累计

    @Field("ipo_operate_reve_one_d")
    private Double operateReveOne;//最近一个年度营业收入累计

    @Field("ipo_operate_cash_one_d")
    private Double netOperateCashFlowOne;//最近一个年度经营活动现金流量净额累计

    @Field("ipo_profit_two_d")
    private Double netProfitTwo;//最近二个年度净利润累计

    @Field("ipo_operate_reve_two_d")
    private Double operateReveTwo;//最近二个年度营业收入累计

    @Field("ipo_operate_cash_two_d")
    private Double netOperateCashFlowTwo;//最近二个年度经营活动现金流量净额累计

    @Field("ipo_profit_three_d")
    private Double netProfitThree;//最近三个年度净利润累计

    @Field("ipo_operate_reve_three_d")
    private Double operateReveThree;//最近三个年度营业收入累计

    @Field("ipo_operate_cash_three_d")
    private Double netOperateCashFlowThree;//最近三个年度经营活动现金流量净额累计

    @Field("ipo_sum_asset_d")
    private Double sunAsset;//总资产

    @Field("ipo_plate_t")
    private String ipoPlate;//拟上市板块编码

    @Field("ipo_plate_name_t")
    private String ipoPlateName;//拟上市板块名称

    @Field("ipo_advance_disclosure_time_dt")
    private Date adDisclosureTime;//预先披露时间

    @Field("ipo_review_meeting_time_dt")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date reMeetingTime;//发审会审核时间

    @Field("ipo_audit_duration_i")
    private Integer auditDuration;//申报审核历时

    @Field("ipo_company_nature_t")
    private String companyNature;//企业性质

    @Field("ipo_declare_num_i")
    private Integer declareNum;//申报次数

    @Field("ipo_green_passage_t")
    private String greenPassage;//绿色通道

    @Field("ipo_issue_fee_d")
    private Double issueFee;//发行费用

    @Field("ipo_pe_issuea_d")
    private Double peIssueA;//发行后市盈率

    @Field("ipo_sum_share_quity_d")
    private Double sumShareQuity;//净资产

    @Field("ipo_intangible_asset_ratio_d")
    private Double intangibleAssetRatio;//无形资产占净资产的比例

    @Field("ipo_total_share_issueb_d")
    private Double totalShareIssueB;//发行前股本总额

    @Field("ipo_total_share_issuea_d")
    private Double totalShareIssueA;//发行后股本总额

    @Field("ipo_intermediary_code_txt")
    private List<String> intermediaryCodes;//中介机构

    @Field("ipo_market_type_txt")
    private List<String> marketTypes;//其他资本市场

    @Field("ipo_belongs_bureau_t")
    private String belongsBureau;//所属证监局

    @Field("ipo_open_flag_t")
    private String openFlag;//是否开放 0 未开放 1开放

    @Field("ipo_industry_csrc_t")
    private String industryCsrc;//发行人行业（证监会）


    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public String getProcessLabel() {
        return processLabel;
    }

    public void setProcessLabel(String processLabel) {
        this.processLabel = processLabel;
    }

    public String getIecResult() {
        return iecResult;
    }

    public void setIecResult(String iecResult) {
        this.iecResult = iecResult;
    }

    public String getIecResultLabel() {
        return iecResultLabel;
    }

    public void setIecResultLabel(String iecResultLabel) {
        this.iecResultLabel = iecResultLabel;
    }

    public Double getNetProfitOne() {
        return netProfitOne;
    }

    public void setNetProfitOne(Double netProfitOne) {
        this.netProfitOne = netProfitOne;
    }

    public Double getOperateReveOne() {
        return operateReveOne;
    }

    public void setOperateReveOne(Double operateReveOne) {
        this.operateReveOne = operateReveOne;
    }

    public Double getNetOperateCashFlowOne() {
        return netOperateCashFlowOne;
    }

    public void setNetOperateCashFlowOne(Double netOperateCashFlowOne) {
        this.netOperateCashFlowOne = netOperateCashFlowOne;
    }

    public Double getNetProfitTwo() {
        return netProfitTwo;
    }

    public void setNetProfitTwo(Double netProfitTwo) {
        this.netProfitTwo = netProfitTwo;
    }

    public Double getOperateReveTwo() {
        return operateReveTwo;
    }

    public void setOperateReveTwo(Double operateReveTwo) {
        this.operateReveTwo = operateReveTwo;
    }

    public Double getNetOperateCashFlowTwo() {
        return netOperateCashFlowTwo;
    }

    public void setNetOperateCashFlowTwo(Double netOperateCashFlowTwo) {
        this.netOperateCashFlowTwo = netOperateCashFlowTwo;
    }

    public Double getNetProfitThree() {
        return netProfitThree;
    }

    public void setNetProfitThree(Double netProfitThree) {
        this.netProfitThree = netProfitThree;
    }

    public Double getOperateReveThree() {
        return operateReveThree;
    }

    public void setOperateReveThree(Double operateReveThree) {
        this.operateReveThree = operateReveThree;
    }

    public Double getNetOperateCashFlowThree() {
        return netOperateCashFlowThree;
    }

    public void setNetOperateCashFlowThree(Double netOperateCashFlowThree) {
        this.netOperateCashFlowThree = netOperateCashFlowThree;
    }

    public Double getSunAsset() {
        return sunAsset;
    }

    public void setSunAsset(Double sunAsset) {
        this.sunAsset = sunAsset;
    }

    public String getIpoPlate() {
        return ipoPlate;
    }

    public void setIpoPlate(String ipoPlate) {
        this.ipoPlate = ipoPlate;
    }

    public String getIpoPlateName() {
        return ipoPlateName;
    }

    public void setIpoPlateName(String ipoPlateName) {
        this.ipoPlateName = ipoPlateName;
    }

    public Date getAdDisclosureTime() {
        return adDisclosureTime;
    }

    public void setAdDisclosureTime(Date adDisclosureTime) {
        this.adDisclosureTime = adDisclosureTime;
    }

    public Date getReMeetingTime() {
        return reMeetingTime;
    }

    public void setReMeetingTime(Date reMeetingTime) {
        this.reMeetingTime = reMeetingTime;
    }

    public Integer getAuditDuration() {
        return auditDuration;
    }

    public void setAuditDuration(Integer auditDuration) {
        this.auditDuration = auditDuration;
    }

    public String getCompanyNature() {
        return companyNature;
    }

    public void setCompanyNature(String companyNature) {
        this.companyNature = companyNature;
    }

    public Integer getDeclareNum() {
        return declareNum;
    }

    public void setDeclareNum(Integer declareNum) {
        this.declareNum = declareNum;
    }

    public String getGreenPassage() {
        return greenPassage;
    }

    public void setGreenPassage(String greenPassage) {
        this.greenPassage = greenPassage;
    }

    public Double getIssueFee() {
        return issueFee;
    }

    public void setIssueFee(Double issueFee) {
        this.issueFee = issueFee;
    }

    public Double getPeIssueA() {
        return peIssueA;
    }

    public void setPeIssueA(Double peIssueA) {
        this.peIssueA = peIssueA;
    }

    public Double getSumShareQuity() {
        return sumShareQuity;
    }

    public void setSumShareQuity(Double sumShareQuity) {
        this.sumShareQuity = sumShareQuity;
    }

    public Double getIntangibleAssetRatio() {
        return intangibleAssetRatio;
    }

    public void setIntangibleAssetRatio(Double intangibleAssetRatio) {
        this.intangibleAssetRatio = intangibleAssetRatio;
    }

    public Double getTotalShareIssueB() {
        return totalShareIssueB;
    }

    public void setTotalShareIssueB(Double totalShareIssueB) {
        this.totalShareIssueB = totalShareIssueB;
    }

    public Double getTotalShareIssueA() {
        return totalShareIssueA;
    }

    public void setTotalShareIssueA(Double totalShareIssueA) {
        this.totalShareIssueA = totalShareIssueA;
    }

    public List<String> getIntermediaryCodes() {
        return intermediaryCodes;
    }

    public void setIntermediaryCodes(List<String> intermediaryCodes) {
        this.intermediaryCodes = intermediaryCodes;
    }

    public List<String> getMarketTypes() {
        return marketTypes;
    }

    public void setMarketTypes(List<String> marketTypes) {
        this.marketTypes = marketTypes;
    }

    public String getBelongsBureau() {
        return belongsBureau;
    }

    public void setBelongsBureau(String belongsBureau) {
        this.belongsBureau = belongsBureau;
    }

    public String getOpenFlag() {
        return openFlag;
    }

    public void setOpenFlag(String openFlag) {
        this.openFlag = openFlag;
    }

    public String getIndustryCsrc() {
        return industryCsrc;
    }

    public void setIndustryCsrc(String industryCsrc) {
        this.industryCsrc = industryCsrc;
    }
}

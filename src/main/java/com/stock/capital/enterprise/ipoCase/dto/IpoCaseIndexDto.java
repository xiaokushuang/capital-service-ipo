package com.stock.capital.enterprise.ipoCase.dto;

import com.stock.core.annotation.Document;
import com.stock.core.annotation.IndexField;
import com.stock.core.dto.Indexable;
import org.apache.solr.client.solrj.beans.Field;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * IpoCaseIndexDto.
 * @update yangj
 * @author chenzhengxiu.
 * @version 0.0.1.
 * @serial 2019/3/22 : base version.
 */
@Document(indexName = "ipo_case")
public class IpoCaseIndexDto extends Indexable implements Serializable {

    private static final long serialVersionUID = 9178446977295191333L;

    @Field("ipo_org_code_t")
    @IndexField(name = "ipo_org_code_t",description = "东财内码")
    private String orgCode;//东财内码

    @Field("ipo_company_code_s")
    @IndexField(name = "ipo_company_code_s",description = "公司编码")
    private String companyCode;//公司编码


    @Field("ipo_company_name_s")
    @IndexField(name = "ipo_company_name_s",description = "公司简称")
    private String companyName;//公司简称

    @Field("ipo_company_full_name_s")
    @IndexField(name = "ipo_company_full_name_s",description = "公司全称")
    private String companyFullName;//公司全称

    @Field("ipo_title_t")
    @IndexField(name = "ipo_title_t",description = "标题")
    private String title;//标题

    @Field("ipo_title_s")
    @IndexField(name = "ipo_title_s",description = "标题")
    private String titleStr;//标题

    @Field("ipo_process_t")
    @IndexField(name = "ipo_process_t",description = "进程")
    private String process;//进程

    @Field("ipo_process_label_t")
    @IndexField(name = "ipo_process_label_t",description = "进程名称")
    private String processLabel;//进程名称

    @Field("ipo_iec_result_t")
    @IndexField(name = "ipo_iec_result_t",description = "审核结果")
    private String iecResult;//审核结果

    @Field("ipo_iec_result_label_t")
    @IndexField(name = "ipo_iec_result_label_t",description = "审核结果名称")
    private String iecResultLabel;//审核结果名称

    @Field("ipo_profit_one_d")
    @IndexField(name = "ipo_profit_one_d",description = "最近一个年度净利润累计")
    private Double netProfitOne;//最近一个年度净利润累计

    @Field("ipo_operate_reve_one_d")
    @IndexField(name = "ipo_operate_reve_one_d",description = "最近一个年度营业收入累计")
    private Double operateReveOne;//最近一个年度营业收入累计

    @Field("ipo_operate_cash_one_d")
    @IndexField(name = "ipo_operate_cash_one_d",description = "最近一个年度经营活动现金流量净额累计")
    private Double netOperateCashFlowOne;//最近一个年度经营活动现金流量净额累计

    @Field("ipo_profit_two_d")
    @IndexField(name = "ipo_profit_two_d",description = "最近二个年度净利润累计")
    private Double netProfitTwo;//最近二个年度净利润累计

    @Field("ipo_operate_reve_two_d")
    @IndexField(name = "ipo_operate_reve_two_d",description = "最近二个年度营业收入累计")
    private Double operateReveTwo;//最近二个年度营业收入累计

    @Field("ipo_operate_cash_two_d")
    @IndexField(name = "ipo_operate_cash_two_d",description = "最近二个年度经营活动现金流量净额累计")
    private Double netOperateCashFlowTwo;//最近二个年度经营活动现金流量净额累计

    @Field("ipo_profit_three_d")
    @IndexField(name = "ipo_profit_three_d",description = "最近三个年度净利润累计")
    private Double netProfitThree;//最近三个年度净利润累计

    @Field("ipo_operate_reve_three_d")
    @IndexField(name = "ipo_operate_reve_three_d",description = "最近三个年度营业收入累计")
    private Double operateReveThree;//最近三个年度营业收入累计

    @Field("ipo_operate_cash_three_d")
    @IndexField(name = "ipo_operate_cash_three_d",description = "最近三个年度经营活动现金流量净额累计")
    private Double netOperateCashFlowThree;//最近三个年度经营活动现金流量净额累计

    @Field("ipo_sum_asset_d")
    @IndexField(name = "ipo_sum_asset_d",description = "总资产")
    private Double sunAsset;//总资产

    @Field("ipo_plate_t")
    @IndexField(name = "ipo_plate_t",description = "拟上市板块编码")
    private String ipoPlate;//拟上市板块编码

    @Field("ipo_plate_name_t")
    @IndexField(name = "ipo_plate_name_t",description = "拟上市板块名称")
    private String ipoPlateName;//拟上市板块名称

    @Field("ipo_advance_disclosure_time_dt")
    @IndexField(name = "ipo_advance_disclosure_time_dt",description = "预先披露时间")
    private Date adDisclosureTime;//预先披露时间

    @Field("ipo_review_meeting_time_dt")
    @IndexField(name = "ipo_review_meeting_time_dt",description = "发审会审核时间")
    private Date reMeetingTime;//发审会审核时间

    @Field("ipo_audit_duration_i")
    @IndexField(name = "ipo_audit_duration_i",description = "申报审核历时")
    private Integer auditDuration;//申报审核历时

    @Field("ipo_company_nature_t")
    @IndexField(name = "ipo_company_nature_t",description = "企业性质")
    private String companyNature;//企业性质

    @Field("ipo_declare_num_i")
    @IndexField(name = "ipo_declare_num_i",description = "申报次数")
    private Integer declareNum;//申报次数

    @Field("ipo_green_passage_t")
    @IndexField(name = "ipo_green_passage_t",description = "绿色通道")
    private String greenPassage;//绿色通道

    @Field("ipo_issue_fee_d")
    @IndexField(name = "ipo_issue_fee_d",description = "发行费用")
    private Double issueFee;//发行费用

    @Field("ipo_pe_issuea_d")
    @IndexField(name = "ipo_pe_issuea_d",description = "发行后市盈率")
    private Double peIssueA;//发行后市盈率

    @Field("ipo_sum_share_quity_d")
    @IndexField(name = "ipo_sum_share_quity_d",description = "净资产")
    private Double sumShareQuity;//净资产

    @Field("ipo_intangible_asset_ratio_d")
    @IndexField(name = "ipo_intangible_asset_ratio_d",description = "无形资产占净资产的比例")
    private Double intangibleAssetRatio;//无形资产占净资产的比例

    @Field("ipo_total_share_issueb_d")
    @IndexField(name = "ipo_total_share_issueb_d",description = "发行前股本总额")
    private Double totalShareIssueB;//发行前股本总额

    @Field("ipo_total_share_issuea_d")
    @IndexField(name = "ipo_total_share_issuea_d",description = "发行后股本总额")
    private Double totalShareIssueA;//发行后股本总额

    @Field("ipo_intermediary_code_ss")
    @IndexField(name = "ipo_intermediary_code_ss",description = "中介机构")
    private List<String> intermediaryCodes;//中介机构

    @Field("ipo_intermediary_name_ss")
    @IndexField(name = "ipo_intermediary_name_ss",description = "中介机构")
    private List<String> intermediaryName;//中介机构

    @Field("ipo_market_type_ss")
    @IndexField(name = "ipo_market_type_ss",description = "其他资本市场")
    private List<String> marketTypes;//其他资本市场

    @Field("ipo_belongs_bureau_t")
    @IndexField(name = "ipo_belongs_bureau_t",description = "所属证监局")
    private String belongsBureau;//所属证监局

    @Field("ipo_open_flag_t")
    @IndexField(name = "ipo_open_flag_t",description = "是否开放")
    private String openFlag;//是否开放 0 未开放 1开放

    @Field("ipo_industry_csrc_t")
    @IndexField(name = "ipo_industry_csrc_t",description = "发行人行业")
    private String industryCsrc;//发行人行业（证监会）

    //    给ES单独添加
    @IndexField(name = "ipo_industry_csrc_list_t",description = "发行人行业")
    private List<String> industryCsrcList;//发行人行业（证监会）

    @Field("ipo_special_arrange_ss")
    @IndexField(name = "ipo_special_arrange_ss",description = "公司治理特殊安排")
    private List<String> specialArrange;//公司治理特殊安排

    @Field("ipo_issue_condition_ss")
    @IndexField(name = "ipo_issue_condition_ss",description = "发行人选择的上市条件")
    private List<String> issueCondition;//发行人选择的上市条件

    @Field("ipo_valuation_d")
    @IndexField(name = "ipo_valuation_d",description = "最近一次估值")
    private Double ipoValuationValue;//最近一次估值
    @Field("ipo_valuation_unit_t")
    @IndexField(name = "ipo_valuation_unit_t",description = "估值单位")
    private String valuationValueUnit;

    @Field("ipo_placing_mechanism_t")
    @IndexField(name = "ipo_placing_mechanism_t",description = "配售机制")
    private String placingMechanism;//配售机制

    @Field("ipo_stragetic_industries_t")
    @IndexField(name = "ipo_stragetic_industries_t",description = "发行人行业")
    private String strageticIndustries;//发行人行业（战略新兴）

    @Field("ipo_register_result_t")
    @IndexField(name = "ipo_register_result_t",description = "注册结果")
    private String registerResult;//注册结果

    @Field("ipo_register_result_label_t")
    @IndexField(name = "ipo_register_result_label_t",description = "注册结果名称")
    private String registerResultLabel;//注册结果名称

    @Field("ipo_review_result_t")
    @IndexField(name = "ipo_review_result_t",description = "复审会议结果")
    private String reviewResult;//复审会议结果

    @Field("ipo_review_result_label_t")
    @IndexField(name = "ipo_review_result_label_t",description = "复审会议结果名称")
    private String reviewResultLabel;//复审会议结果名称

    @Field("ipo_review_time_dt")
    @IndexField(name = "ipo_review_time_dt",description = "复审会议时间")
    private Date reviewTime;//复审会议时间

    @Field("ipo_final_result_t")
    @IndexField(name = "ipo_final_result_t",description = "结果")
    private String finalResult;//如果复审，则取复审结果，无复审，则取上市委/发审委结果

    @Field("ipo_label_result_t")
    @IndexField(name = "ipo_label_result_t",description = "结果")
    private String labelResult;//用于前台展示标签

    @Field("ipo_final_time_dt")
    @IndexField(name = "ipo_final_time_dt",description = "最后一次审核时间")
    private Date finalTime;//最后一次审核时间

    @Field("ipo_public_time_dt")
    @IndexField(name = "ipo_public_time_dt",description = "上市节点时间")
    private Date publicTime;//上市节点时间

    @Field("ipo_industry_csrc_name_t")
    @IndexField(name = "ipo_industry_csrc_name_t",description = "发行人行业名称")
    private String industryCsrcName;//发行人行业名称（证监会）

    @Field("ipo_addr_country_t")
    @IndexField(name = "ipo_addr_country_t",description = "国家")
    private String addrCountry;//国家

    @Field("ipo_addr_prov_t")
    @IndexField(name = "ipo_addr_prov_t",description = "省份")
    private String addrProv;//省份

    @Field("ipo_addr_city_t")
    @IndexField(name = "ipo_addr_city_t",description = "市")
    private String addrCity;//市

    @Field("ipo_addr_area_t")
    @IndexField(name = "ipo_addr_area_t",description = "地区")
    private String addrArea;//地区

    @Field("ipo_type_t")
    @IndexField(name = "ipo_type_t",description = "ipocase和ipofd 区分字段")
    private String ipoType;//ipocase 和 ipofd 区分字段

    @Field("ipo_fd_time_dt")
    @IndexField(name = "ipo_fd_time_dt",description = "辅导备案时间")
    private Date ipoFdTime;//辅导备案时间


    public String getValuationValueUnit() {
        return valuationValueUnit;
    }

    public void setValuationValueUnit(String valuationValueUnit) {
        this.valuationValueUnit = valuationValueUnit;
    }

    public List<String> getIndustryCsrcList() {
        return industryCsrcList;
    }

    public void setIndustryCsrcList(List<String> industryCsrcList) {
        this.industryCsrcList = industryCsrcList;
    }

    public List<String> getIntermediaryName() {
        return intermediaryName;
    }

    public void setIntermediaryName(List<String> intermediaryName) {
        this.intermediaryName = intermediaryName;
    }

    public Date getIpoFdTime() {
        return ipoFdTime;
    }

    public void setIpoFdTime(Date ipoFdTime) {
        this.ipoFdTime = ipoFdTime;
    }

    public String getIpoType() {
        return ipoType;
    }

    public void setIpoType(String ipoType) {
        this.ipoType = ipoType;
    }

    public String getAddrCountry() {
        return addrCountry;
    }

    public void setAddrCountry(String addrCountry) {
        this.addrCountry = addrCountry;
    }

    public String getAddrProv() {
        return addrProv;
    }

    public void setAddrProv(String addrProv) {
        this.addrProv = addrProv;
    }

    public String getAddrCity() {
        return addrCity;
    }

    public void setAddrCity(String addrCity) {
        this.addrCity = addrCity;
    }

    public String getAddrArea() {
        return addrArea;
    }

    public void setAddrArea(String addrArea) {
        this.addrArea = addrArea;
    }

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

    public String getTitleStr() {
        return titleStr;
    }

    public void setTitleStr(String titleStr) {
        this.titleStr = titleStr;
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

    public List<String> getSpecialArrange() {
        return specialArrange;
    }

    public void setSpecialArrange(List<String> specialArrange) {
        this.specialArrange = specialArrange;
    }

    public List<String> getIssueCondition() {
        return issueCondition;
    }

    public void setIssueCondition(List<String> issueCondition) {
        this.issueCondition = issueCondition;
    }

    public Double getIpoValuationValue() {
        return ipoValuationValue;
    }

    public void setIpoValuationValue(Double ipoValuationValue) {
        this.ipoValuationValue = ipoValuationValue;
    }

    public String getPlacingMechanism() {
        return placingMechanism;
    }

    public void setPlacingMechanism(String placingMechanism) {
        this.placingMechanism = placingMechanism;
    }

    public String getStrageticIndustries() {
        return strageticIndustries;
    }

    public void setStrageticIndustries(String strageticIndustries) {
        this.strageticIndustries = strageticIndustries;
    }

    public String getRegisterResult() {
        return registerResult;
    }

    public void setRegisterResult(String registerResult) {
        this.registerResult = registerResult;
    }

    public String getRegisterResultLabel() {
        return registerResultLabel;
    }

    public void setRegisterResultLabel(String registerResultLabel) {
        this.registerResultLabel = registerResultLabel;
    }

    public String getReviewResult() {
        return reviewResult;
    }

    public void setReviewResult(String reviewResult) {
        this.reviewResult = reviewResult;
    }

    public String getReviewResultLabel() {
        return reviewResultLabel;
    }

    public void setReviewResultLabel(String reviewResultLabel) {
        this.reviewResultLabel = reviewResultLabel;
    }

    public Date getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(Date reviewTime) {
        this.reviewTime = reviewTime;
    }

    public String getFinalResult() {
        return finalResult;
    }

    public void setFinalResult(String finalResult) {
        this.finalResult = finalResult;
    }

    public String getLabelResult() {
        return labelResult;
    }

    public void setLabelResult(String labelResult) {
        this.labelResult = labelResult;
    }

    public Date getFinalTime() {
        return finalTime;
    }

    public void setFinalTime(Date finalTime) {
        this.finalTime = finalTime;
    }

    public Date getPublicTime() {
        return publicTime;
    }

    public void setPublicTime(Date publicTime) {
        this.publicTime = publicTime;
    }

    public String getIndustryCsrcName() {
        return industryCsrcName;
    }

    public void setIndustryCsrcName(String industryCsrcName) {
        this.industryCsrcName = industryCsrcName;
    }

    public String getCompanyFullName() {
        return companyFullName;
    }

    public void setCompanyFullName(String companyFullName) {
        this.companyFullName = companyFullName;
    }
}

package com.stock.capital.enterprise.ipoInterfaceH5.controller;

import com.google.common.base.Throwables;
import com.google.common.collect.Maps;

import com.stock.capital.enterprise.ipoCase.dto.CompanyOverviewVo;
import com.stock.capital.enterprise.ipoCase.dto.IndustryCompareRateDto;
import com.stock.capital.enterprise.ipoCase.dto.IntermediaryOrgDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoCaseIndexDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoCaseListBo;
import com.stock.capital.enterprise.ipoCase.dto.IpoFeedbackDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoFinanceDateDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoFinanceDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoItemDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoPersonInfoDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoTechnologyDateDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoTechnologyPatentDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoTechnologyTableDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoTechnologyVo;
import com.stock.capital.enterprise.ipoCase.dto.IpoValuationDto;
import com.stock.capital.enterprise.ipoCase.dto.IssuerIndustryStatusDto;
import com.stock.capital.enterprise.ipoCase.dto.MainCompetitorInfoDto;
import com.stock.capital.enterprise.ipoCase.dto.MainIncomeVo;
import com.stock.capital.enterprise.ipoCase.dto.OtherMarketInfoDto;
import com.stock.capital.enterprise.ipoCase.dto.SupplierCustomerMainDto;
import com.stock.capital.enterprise.ipoCase.dto.TreeTypeProgressDto;
import com.stock.capital.enterprise.ipoCase.service.CompanyOverviewService;
import com.stock.capital.enterprise.ipoCase.service.IpoCaseListService;
import com.stock.capital.enterprise.ipoCase.service.IpoFeedbackService;
import com.stock.capital.enterprise.ipoCase.service.IpoFinanceService;
import com.stock.capital.enterprise.ipoCase.service.IpoProcessService;
import com.stock.capital.enterprise.ipoInterfaceH5.dto.IpoFinanceH5Dto;
import com.stock.capital.enterprise.ipoInterfaceH5.dto.IpoH5Dto;
import com.stock.capital.enterprise.ipoInterfaceH5.dto.IpoH5IndustryDto;
import com.stock.capital.enterprise.ipoInterfaceH5.dto.IpoH5FinanceListDto;
import com.stock.capital.enterprise.ipoInterfaceH5.dto.IpoH5FinanceResultDto;
import com.stock.capital.enterprise.ipoInterfaceH5.service.IpoInterfaceService;
import com.stock.core.controller.BaseController;
import com.stock.core.dto.QueryInfo;
import com.stock.core.util.JsonUtil;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = {"IPOH5接口"}, description = "IPOH5接口")
@Controller
@RequestMapping("ipoInterfaceH5")
public class IpoInterfaceController extends BaseController {

    @Autowired
    private IpoInterfaceService ipoInterfaceService;

    @Autowired
    private CompanyOverviewService companyOverviewService;
    @Autowired
    private IpoFinanceService ipoFinanceService;
    @Autowired
    private IpoCaseListService ipoCaseListService;
    @Autowired
    private IpoFeedbackService ipoFeedbackService;
    @Autowired
    private IpoProcessService ipoProcessService;

    private static final Logger logger = LoggerFactory.getLogger(IpoInterfaceController.class);

    /**
     * IpoH5接口汇总
     *
     * @param id 案例主键
     */
    @ApiOperation(value = "IPOH5接口", notes = "IPOH5接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "案例id", required = true, paramType = "query",
                    dataType = "String")
    })
    @RequestMapping(value = "ipoCaseH5")
    @ResponseBody
    public Map<String, Object> ipoCaseH5(String id) {
        Map<String, Object> resultMap = Maps.newHashMap();
        Map<String, Object> dataMap = Maps.newHashMap();
        String ipoPlate = "069001001006";//科创版
        IpoCaseListBo ipoCaseListBo = new IpoCaseListBo();
        ipoCaseListBo.setIpoPlate(ipoPlate);
        //获取科创板IPO数据
        /*try {
            Map<String, Object> ipoCaseList = getIpoCaseList(ipoCaseListBo);
            resultMap.put("ipoCaseList",ipoCaseList);
        }catch  (Exception e){
            resultMap.put("ipoCaseList","0");
            logger.error("ipoCaseH5获取科创板IPO数据发生错误:{}", Throwables.getStackTraceAsString(e));
        }
        //下拉框数据
        try {
            Map<String,Object> selectData = querySelectData();
            resultMap.put("selectData",selectData);
        }catch  (Exception e){
            resultMap.put("selectData","0");
            logger.error("ipoCaseH5获取下拉框数据发生错误:{}", Throwables.getStackTraceAsString(e));
        }*/
        //上市进展

        try {
            TreeTypeProgressDto processList = selectProcessList(id);
            if (null != processList) {
                dataMap.put("paramName", "上市进展");
                dataMap.put("paramData", JsonUtil.toJsonNoNull(processList));
            } else {
                dataMap.put("paramName", "上市进展");
                dataMap.put("paramData", "0");
            }
            resultMap.put("processList", dataMap);
        } catch (Exception e) {
            dataMap.put("paramName", "上市进展");
            dataMap.put("paramData", "0");
            resultMap.put("processList", dataMap);
            logger.error("ipoCaseH5获取上市进展发生错误:{}", Throwables.getStackTraceAsString(e));
        }

        //最新估值
        dataMap = new HashMap<>();
        try {
            List<IpoValuationDto> valuationData = valuationData(id);
            if (CollectionUtils.isNotEmpty(valuationData)) {
                dataMap.put("paramName", "最新估值");
                dataMap.put("paramData", JsonUtil.toJsonNoNull(valuationData));
            } else {
                dataMap.put("paramName", "最新估值");
                dataMap.put("paramData", "0");
            }
            resultMap.put("valuationData", dataMap);
        } catch (Exception e) {
            dataMap.put("paramName", "最新估值");
            dataMap.put("paramData", "0");
            resultMap.put("valuationData", dataMap);
            logger.error("ipoCaseH5获取最新估值发生错误:{}", Throwables.getStackTraceAsString(e));
        }

        IpoCaseIndexDto ipoCaseIndex = new IpoCaseIndexDto();
        //上市条件、公司信息
        try {
            CompanyOverviewVo caseDetaild = caseDetail(id);
            if (caseDetaild != null) {
                ipoCaseIndex.setIndustryCsrc(caseDetaild.getIndustryCsrc());

            }
            dataMap = new HashMap<>();
            dataMap.put("paramName", "公司信息");
            dataMap.put("paramData", JsonUtil.toJsonNoNull(caseDetaild));
            resultMap.put("caseDetaild", dataMap);
            dataMap = new HashMap<>();
            if (StringUtils.isNotEmpty(caseDetaild.getIssueCondition())) {
                dataMap.put("paramName", "上市条件");
                dataMap.put("paramData", JsonUtil.toJsonNoNull(caseDetaild));
            } else {
                dataMap.put("paramName", "上市条件");
                dataMap.put("paramData", 0);
            }
            resultMap.put("issueCondition", dataMap);
        } catch (Exception e) {
            dataMap = new HashMap<>();
            dataMap.put("paramName", "上市条件");
            dataMap.put("paramData", 0);
            resultMap.put("caseDetaild", dataMap);
            dataMap = new HashMap<>();
            dataMap.put("paramName", "公司信息");
            dataMap.put("paramData", 0);
            resultMap.put("issueCondition", dataMap);
            logger.error("ipoCaseH5获取上市条件、公司信息发生错误:{}", Throwables.getStackTraceAsString(e));
        }

        //主营业务构成
        try {
            MainIncomeVo incomeData = incomeData(id);
            if (null != incomeData) {
                dataMap = new HashMap<>();
                dataMap.put("paramName", "主营业务构成");
                dataMap.put("paramData", JsonUtil.toJsonNoNull(incomeData));
                resultMap.put("incomeData", dataMap);
            } else {
                dataMap = new HashMap<>();
                dataMap.put("paramName", "主营业务构成");
                dataMap.put("paramData", "0");
                resultMap.put("incomeData", dataMap);
            }
        } catch (Exception e) {
            dataMap = new HashMap<>();
            dataMap.put("paramName", "主营业务构成");
            dataMap.put("paramData", "0");
            resultMap.put("incomeData", dataMap);
            logger.error("ipoCaseH5获取主营业务构成发生错误:{}", Throwables.getStackTraceAsString(e));
        }
        //股东信息
        try {
            List<IpoPersonInfoDto> shareHolderData = shareHolderData(id);
            resultMap.put("shareHolderData", shareHolderData);
            if (null != shareHolderData) {
                dataMap = new HashMap<>();
                dataMap.put("paramName", "股权结构");
                dataMap.put("paramData", JsonUtil.toJsonNoNull(shareHolderData));
                resultMap.put("shareHolderData", dataMap);
            } else {
                dataMap = new HashMap<>();
                dataMap.put("paramName", "股权结构");
                dataMap.put("paramData", "0");
                resultMap.put("shareHolderData", dataMap);
            }
        } catch (Exception e) {
            dataMap = new HashMap<>();
            dataMap.put("paramName", "股权结构");
            dataMap.put("paramData", "0");
            resultMap.put("shareHolderData", dataMap);
            logger.error("ipoCaseH5获取股东信息发生错误:{}", Throwables.getStackTraceAsString(e));
        }
        //登录其他资本市场
        try {
            List<OtherMarketInfoDto> marketData = marketData(id);
            if (CollectionUtils.isNotEmpty(marketData)) {
                dataMap = new HashMap<>();
                dataMap.put("paramName", "登录其他资本市场");
                dataMap.put("paramData", JsonUtil.toJsonNoNull(marketData));
                resultMap.put("marketData", dataMap);
            } else {
                dataMap = new HashMap<>();
                dataMap.put("paramName", "登录其他资本市场");
                dataMap.put("paramData", "0");
                resultMap.put("marketData", dataMap);
            }
        } catch (Exception e) {
            dataMap = new HashMap<>();
            dataMap.put("paramName", "登录其他资本市场");
            dataMap.put("paramData", "0");
            resultMap.put("marketData", dataMap);
            logger.error("ipoCaseH5获取登录其他资本市场发生错误:{}", Throwables.getStackTraceAsString(e));
        }
        //上交所问询情况
        try {
            List<IpoFeedbackDto> selectNewFeedbackList = selectNewFeedbackList(id);
            if (CollectionUtils.isNotEmpty(selectNewFeedbackList)) {
                dataMap = new HashMap<>();
                dataMap.put("paramName", "上交所问询情况");
                dataMap.put("paramData", JsonUtil.toJsonNoNull(selectNewFeedbackList));
                resultMap.put("selectNewFeedbackList", dataMap);
            } else {
                dataMap = new HashMap<>();
                dataMap.put("paramName", "上交所问询情况");
                dataMap.put("paramData", "0");
                resultMap.put("selectNewFeedbackList", dataMap);
            }
        } catch (Exception e) {
            dataMap = new HashMap<>();
            dataMap.put("paramName", "上交所问询情况");
            dataMap.put("paramData", "0");
            resultMap.put("selectNewFeedbackList", dataMap);
            logger.error("ipoCaseH5获取上交所问询情况发生错误:{}", Throwables.getStackTraceAsString(e));
        }
        //中介结构
        try {
            Map<String, List<IntermediaryOrgDto>> intermediaryOrgData = intermediaryOrgData(id, "");
            if (null != intermediaryOrgData && intermediaryOrgData.size() > 0) {
                dataMap = new HashMap<>();
                dataMap.put("paramName", "中介机构");
                dataMap.put("paramData", JsonUtil.toJsonNoNull(intermediaryOrgData));
                resultMap.put("intermediaryOrgData", dataMap);
            } else {
                dataMap = new HashMap<>();
                dataMap.put("paramName", "中介机构");
                dataMap.put("paramData", "0");
                resultMap.put("intermediaryOrgData", dataMap);
            }
        } catch (Exception e) {
            dataMap = new HashMap<>();
            dataMap.put("paramName", "中介机构");
            dataMap.put("paramData", "0");
            resultMap.put("intermediaryOrgData", dataMap);
            logger.error("ipoCaseH5获取中介结构发生错误:{}", Throwables.getStackTraceAsString(e));
        }
        //可能还想看
        try {
            List<IpoCaseIndexDto> otherIpoCase = otherIpoCase(ipoCaseIndex);
            if (CollectionUtils.isNotEmpty(otherIpoCase)) {
                dataMap = new HashMap<>();
                dataMap.put("paramName", "可能还想看");
                dataMap.put("paramData", JsonUtil.toJsonNoNull(otherIpoCase));
                resultMap.put("otherIpoCase", dataMap);
            } else {
                dataMap = new HashMap<>();
                dataMap.put("paramName", "可能还想看");
                dataMap.put("paramData", "0");
                resultMap.put("otherIpoCase", dataMap);
            }
        } catch (Exception e) {
            dataMap = new HashMap<>();
            dataMap.put("paramName", "可能还想看");
            dataMap.put("paramData", "0");
            resultMap.put("otherIpoCase", dataMap);
            logger.error("ipoCaseH5获取可能还想看发生错误:{}", Throwables.getStackTraceAsString(e));
        }

        //行业与技术接口

        try {
            Map technology = getTechnology(id);
            //行业地位
            List<IssuerIndustryStatusDto> industryStatusDtoList = (List<IssuerIndustryStatusDto>) technology.get("industryStatusInfo");
            if (CollectionUtils.isNotEmpty(industryStatusDtoList)) {
                dataMap = new HashMap<>();
                dataMap.put("paramName", "行业地位");
                dataMap.put("paramData", JsonUtil.toJsonNoNull(industryStatusDtoList));
                resultMap.put("industryStatusInfo", dataMap);
            } else {
                dataMap = new HashMap<>();
                dataMap.put("paramName", "行业地位");
                dataMap.put("paramData", "0");
                resultMap.put("industryStatusInfo", dataMap);
            }
            //主要竞争对手
            List<MainCompetitorInfoDto> mainCompetitorInfoDtoList = (List<MainCompetitorInfoDto>) technology.get("mainCompetitorInfo");
            if (CollectionUtils.isNotEmpty(mainCompetitorInfoDtoList)) {
                dataMap = new HashMap<>();
                dataMap.put("paramName", "主要竞争对手");
                dataMap.put("paramData", JsonUtil.toJsonNoNull(mainCompetitorInfoDtoList));
                resultMap.put("mainCompetitorInfo", dataMap);
            } else {
                dataMap = new HashMap<>();
                dataMap.put("paramName", "主要竞争对手");
                dataMap.put("paramData", "0");
                resultMap.put("mainCompetitorInfo", dataMap);
            }
            //毛利率对比
            IpoH5IndustryDto ipoH5IndustryDto = (IpoH5IndustryDto) technology.get("industryCompareRateInfo");
            if(CollectionUtils.isNotEmpty(ipoH5IndustryDto.getTitles()) || CollectionUtils.isNotEmpty(ipoH5IndustryDto.getBody())){
                dataMap = new HashMap<>();
                dataMap.put("paramName", "毛利率对比");
                dataMap.put("paramData", JsonUtil.toJsonNoNull(ipoH5IndustryDto));
                resultMap.put("industryCompareRateInfo", dataMap);
            }else{
                dataMap = new HashMap<>();
                dataMap.put("paramName", "毛利率对比");
                dataMap.put("paramData", "0");
                resultMap.put("industryCompareRateInfo", dataMap);
            }
            //科技创新
            IpoTechnologyVo ipoTechnologyVo = (IpoTechnologyVo) technology.get("technologyInfo");
            if(ipoTechnologyVo != null){
                //研发投入
                List<IpoTechnologyTableDto> devData = ipoTechnologyVo.getDevData();
                if(CollectionUtils.isNotEmpty(devData)){
                    dataMap = new HashMap<>();
                    dataMap.put("paramName", "研发投入");
                    dataMap.put("paramData", JsonUtil.toJsonNoNull(devData));
                    resultMap.put("devData", dataMap);
                }else{
                    dataMap = new HashMap<>();
                    dataMap.put("paramName", "研发投入");
                    dataMap.put("paramData", "0");
                    resultMap.put("devData", dataMap);
                }
                //研发投入时间
                IpoTechnologyDateDto devDate = ipoTechnologyVo.getDevDate();
                dataMap = new HashMap<>();
                dataMap.put("paramName", "研发投入时间");
                dataMap.put("paramData", JsonUtil.toJsonNoNull(devDate));
                resultMap.put("devDate", dataMap);
                //专利情况
                List<IpoTechnologyPatentDto> patentData = ipoTechnologyVo.getPatentData();
                if(CollectionUtils.isNotEmpty(patentData)){
                    dataMap = new HashMap<>();
                    dataMap.put("paramName", "专利情况");
                    dataMap.put("paramData", JsonUtil.toJsonNoNull(patentData));
                    resultMap.put("patentData", dataMap);
                }else{
                    dataMap = new HashMap<>();
                    dataMap.put("paramName", "专利情况");
                    dataMap.put("paramData", "0");
                    resultMap.put("patentData", dataMap);
                }
                //核心技术及研发技术人员
                List<IpoTechnologyTableDto> coreData = ipoTechnologyVo.getCoreData();
                if(CollectionUtils.isNotEmpty(coreData)){
                    dataMap = new HashMap<>();
                    dataMap.put("paramName", "核心技术及研发技术人员");
                    dataMap.put("paramData", JsonUtil.toJsonNoNull(coreData));
                    resultMap.put("coreData", dataMap);
                }else{
                    dataMap = new HashMap<>();
                    dataMap.put("paramName", "核心技术及研发技术人员");
                    dataMap.put("paramData", "0");
                    resultMap.put("coreData", dataMap);
                }
            }
        } catch (Exception e) {
            dataMap = new HashMap<>();
            dataMap.put("paramName", "行业地位");
            dataMap.put("paramData", "0");
            resultMap.put("industryStatusInfo", dataMap);
            dataMap = new HashMap<>();
            dataMap.put("paramName", "主要竞争对手");
            dataMap.put("paramData", "0");
            resultMap.put("mainCompetitorInfo", dataMap);
            dataMap = new HashMap<>();
            dataMap.put("paramName", "毛利率对比");
            dataMap.put("paramData", "0");
            resultMap.put("industryCompareRateInfo", dataMap);
            dataMap = new HashMap<>();
            dataMap.put("paramName", "研发投入");
            dataMap.put("paramData", "0");
            resultMap.put("devData", dataMap);
            dataMap = new HashMap<>();
            dataMap.put("paramName", "专利情况");
            dataMap.put("paramData", "0");
            resultMap.put("patentData", dataMap);
            dataMap = new HashMap<>();
            dataMap.put("paramName", "核心技术及研发技术人员");
            dataMap.put("paramData", "0");
            resultMap.put("coreData", dataMap);
            logger.error("ipoCaseH5获取行业与技术发生错误:{}", Throwables.getStackTraceAsString(e));
        }

        //报告期主要供应商及客户情况
        try {
            Map<String, List<SupplierCustomerMainDto>> supplierCustomerData = supplierCustomerData(id);
            List<SupplierCustomerMainDto> supplierMainList = supplierCustomerData.get("supplierMainList");
            if(CollectionUtils.isNotEmpty(supplierMainList)){
                dataMap = new HashMap<>();
                dataMap.put("paramName", "前五大供应商占比情况");
                dataMap.put("paramData", JsonUtil.toJsonNoNull(supplierMainList));
                resultMap.put("supplierMainList", dataMap);
            }else{
                dataMap = new HashMap<>();
                dataMap.put("paramName", "前五大供应商占比情况");
                dataMap.put("paramData", "0");
                resultMap.put("supplierMainList", dataMap);
            }
            List<SupplierCustomerMainDto> customerList = supplierCustomerData.get("customerMainList");
            if(CollectionUtils.isNotEmpty(customerList)){
                dataMap = new HashMap<>();
                dataMap.put("paramName", "前五大客户占比情况");
                dataMap.put("paramData", JsonUtil.toJsonNoNull(customerList));
                resultMap.put("customerMainList", dataMap);
            }else{
                dataMap = new HashMap<>();
                dataMap.put("paramName", "前五大客户占比情况");
                dataMap.put("paramData", "0");
                resultMap.put("customerMainList", dataMap);
            }
        } catch (Exception e) {
            dataMap = new HashMap<>();
            dataMap.put("paramName", "前五大供应商占比情况");
            dataMap.put("paramData", "0");
            resultMap.put("supplierMainList", dataMap);
            dataMap = new HashMap<>();
            dataMap.put("paramName", "前五大客户占比情况");
            dataMap.put("paramData", "0");
            resultMap.put("customerMainList", dataMap);
            logger.error("ipoCaseH5获取报告期主要供应商及客户情况发生错误:{}", Throwables.getStackTraceAsString(e));
        }
        //发行人财务数据
        try {
            IpoH5FinanceResultDto financeOverListH5 = selectFinanceOverListH5(id);
            List<IpoFinanceH5Dto> incomeList = financeOverListH5.getIncomeList();
            if(CollectionUtils.isNotEmpty(incomeList)){
                dataMap = new HashMap<>();
                dataMap.put("paramName", "营业收入");
                dataMap.put("paramData", JsonUtil.toJsonNoNull(incomeList));
                resultMap.put("incomeList", dataMap);
            }else{
                dataMap = new HashMap<>();
                dataMap.put("paramName", "营业收入");
                dataMap.put("paramData", "0");
                resultMap.put("incomeList", dataMap);
            }
            List<IpoFinanceH5Dto> grossList = financeOverListH5.getGrossList();
            if(CollectionUtils.isNotEmpty(grossList)){
                dataMap = new HashMap<>();
                dataMap.put("paramName", "毛利率");
                dataMap.put("paramData", JsonUtil.toJsonNoNull(incomeList));
                resultMap.put("grossList", dataMap);
            }else{
                dataMap = new HashMap<>();
                dataMap.put("paramName", "毛利率");
                dataMap.put("paramData", "0");
                resultMap.put("grossList", dataMap);
            }
            IpoFinanceH5Dto incomeRate = financeOverListH5.getIncomeRate();
            if(null != incomeRate){
                dataMap = new HashMap<>();
                dataMap.put("paramName", "营业收入复合增长率");
                dataMap.put("paramData", JsonUtil.toJsonNoNull(incomeRate));
                resultMap.put("incomeRate", dataMap);
            }else{
                dataMap = new HashMap<>();
                dataMap.put("paramName", "营业收入复合增长率");
                dataMap.put("paramData", "0");
                resultMap.put("incomeRate", dataMap);
            }
            List<IpoFinanceH5Dto> profitList = financeOverListH5.getProfitList();
            if(CollectionUtils.isNotEmpty(profitList)){
                dataMap = new HashMap<>();
                dataMap.put("paramName", "净利润");
                dataMap.put("paramData", JsonUtil.toJsonNoNull(profitList));
                resultMap.put("profitList", dataMap);
            }else{
                dataMap = new HashMap<>();
                dataMap.put("paramName", "净利润");
                dataMap.put("paramData", "0");
                resultMap.put("profitList", dataMap);
            }
            List<IpoFinanceH5Dto> profitRateList = financeOverListH5.getProfitRateList();
            if(CollectionUtils.isNotEmpty(profitRateList)){
                dataMap = new HashMap<>();
                dataMap.put("paramName", "净利润率");
                dataMap.put("paramData", JsonUtil.toJsonNoNull(profitRateList));
                resultMap.put("profitRateList", dataMap);
            }else{
                dataMap = new HashMap<>();
                dataMap.put("paramName", "净利润率");
                dataMap.put("paramData", "0");
                resultMap.put("profitRateList", dataMap);
            }
            IpoFinanceH5Dto profitRate = financeOverListH5.getProfitRate();
            if(null != profitRate){
                dataMap = new HashMap<>();
                dataMap.put("paramName", "净利润复合增长率");
                dataMap.put("paramData", JsonUtil.toJsonNoNull(profitRate));
                resultMap.put("profitRate", dataMap);
            }else{
                dataMap = new HashMap<>();
                dataMap.put("paramName", "净利润复合增长率");
                dataMap.put("paramData", "0");
                resultMap.put("profitRate", dataMap);
            }
            List<IpoFinanceH5Dto> indexList = financeOverListH5.getMainIndexList();
            if(CollectionUtils.isNotEmpty(indexList)){
                dataMap = new HashMap<>();
                dataMap.put("paramName", "财务比率");
                dataMap.put("paramData", JsonUtil.toJsonNoNull(indexList));
                resultMap.put("indexList", dataMap);
            }else{
                dataMap = new HashMap<>();
                dataMap.put("paramName", "财务比率");
                dataMap.put("paramData", "0");
                resultMap.put("indexList", dataMap);
            }
            List<IpoH5FinanceListDto> debtList = financeOverListH5.getDebtList();
            if(CollectionUtils.isNotEmpty(debtList)){
                dataMap = new HashMap<>();
                dataMap.put("paramName", "资产负债情况");
                dataMap.put("paramData", JsonUtil.toJsonNoNull(debtList));
                resultMap.put("debtList", dataMap);
            }else{
                dataMap = new HashMap<>();
                dataMap.put("paramName", "资产负债情况");
                dataMap.put("paramData", "0");
                resultMap.put("debtList", dataMap);
            }
            List<IpoFinanceH5Dto> cashFlowList =financeOverListH5.getCashFlowList();
            if(CollectionUtils.isNotEmpty(cashFlowList)){
                dataMap = new HashMap<>();
                dataMap.put("paramName", "现金流量情况");
                dataMap.put("paramData", JsonUtil.toJsonNoNull(cashFlowList));
                resultMap.put("cashFlowList", dataMap);
            }else{
                dataMap = new HashMap<>();
                dataMap.put("paramName", "现金流量情况");
                dataMap.put("paramData", "0");
                resultMap.put("cashFlowList", dataMap);
            }
            IpoFinanceH5Dto cashFlowRate = financeOverListH5.getCashFlowRate();
            if(null != cashFlowRate){
                dataMap = new HashMap<>();
                dataMap.put("paramName", "现金流量复合增长率");
                dataMap.put("paramData", JsonUtil.toJsonNoNull(cashFlowRate));
                resultMap.put("cashFlowRate", dataMap);
            }else{
                dataMap = new HashMap<>();
                dataMap.put("paramName", "现金流量复合增长率");
                dataMap.put("paramData", "0");
                resultMap.put("cashFlowRate", dataMap);
            }
            List<IpoFinanceH5Dto> cashList = financeOverListH5.getCashList();
            if(CollectionUtils.isNotEmpty(cashList)){
                dataMap = new HashMap<>();
                dataMap.put("paramName", "现金及现金等价物净增加额");
                dataMap.put("paramData", JsonUtil.toJsonNoNull(cashList));
                resultMap.put("cashList", dataMap);
            }else{
                dataMap = new HashMap<>();
                dataMap.put("paramName", "现金及现金等价物净增加额");
                dataMap.put("paramData", "0");
                resultMap.put("cashList", dataMap);
            }
            IpoFinanceH5Dto cashRate = financeOverListH5.getCashRate();
            if(null != cashRate){
                dataMap = new HashMap<>();
                dataMap.put("paramName", "现金及现金等价物净增加额复合增长率");
                dataMap.put("paramData", JsonUtil.toJsonNoNull(cashRate));
                resultMap.put("cashRate", dataMap);
            }else{
                dataMap = new HashMap<>();
                dataMap.put("paramName", "现金及现金等价物净增加额复合增长率");
                dataMap.put("paramData", "0");
                resultMap.put("cashRate", dataMap);
            }
            IpoFinanceH5Dto sumAssetRate = financeOverListH5.getSumAssetRate();
            if(null != sumAssetRate){
                dataMap = new HashMap<>();
                dataMap.put("paramName", "总资产复合增长率");
                dataMap.put("paramData", JsonUtil.toJsonNoNull(sumAssetRate));
                resultMap.put("sumAssetRate", dataMap);
            }else{
                dataMap = new HashMap<>();
                dataMap.put("paramName", "总资产复合增长率");
                dataMap.put("paramData", "0");
                resultMap.put("sumAssetRate", dataMap);
            }
            IpoFinanceDateDto dateDto = financeOverListH5.getDateList();
            if(null != dateDto){
                dataMap = new HashMap<>();
                dataMap.put("paramName", "统计日期");
                dataMap.put("paramData", JsonUtil.toJsonNoNull(dateDto));
                resultMap.put("dateDto", dataMap);
            }
        } catch (Exception e) {
            dataMap = new HashMap<>();
            dataMap.put("paramName", "营业收入");
            dataMap.put("paramData", "0");
            resultMap.put("incomeList", dataMap);
            dataMap = new HashMap<>();
            dataMap.put("paramName", "毛利润");
            dataMap.put("paramData", "0");
            resultMap.put("grossList", dataMap);
            dataMap = new HashMap<>();
            dataMap.put("paramName", "营业收入复合增长率");
            dataMap.put("paramData", "0");
            resultMap.put("incomeRate", dataMap);
            dataMap = new HashMap<>();
            dataMap.put("paramName", "净利润");
            dataMap.put("paramData", "0");
            resultMap.put("profitList", dataMap);
            dataMap = new HashMap<>();
            dataMap.put("paramName", "净利润率");
            dataMap.put("paramData", "0");
            resultMap.put("profitRateList", dataMap);
            dataMap = new HashMap<>();
            dataMap.put("paramName", "净利润复合增长率");
            dataMap.put("paramData", "0");
            resultMap.put("profitRate", dataMap);
            dataMap = new HashMap<>();
            dataMap.put("paramName", "财务比率");
            dataMap.put("paramData", "0");
            resultMap.put("indexList", dataMap);
            dataMap = new HashMap<>();
            dataMap.put("paramName", "资产负债情况");
            dataMap.put("paramData", "0");
            resultMap.put("debtList", dataMap);
            dataMap = new HashMap<>();
            dataMap.put("paramName", "现金流量情况");
            dataMap.put("paramData", "0");
            resultMap.put("cashFlowList", dataMap);
            dataMap = new HashMap<>();
            dataMap.put("paramName", "现金流量复合增长率");
            dataMap.put("paramData", "0");
            resultMap.put("cashFlowRate", dataMap);
            dataMap = new HashMap<>();
            dataMap.put("paramName", "现金及现金等价物净增加额");
            dataMap.put("paramData", "0");
            resultMap.put("cashList", dataMap);
            dataMap = new HashMap<>();
            dataMap.put("paramName", "现金及现金等价物净增加额复合增长率");
            dataMap.put("paramData", "0");
            resultMap.put("cashRate", dataMap);
            dataMap = new HashMap<>();
            dataMap.put("paramName", "总资产复合增长率");
            dataMap.put("paramData", "0");
            resultMap.put("sumAssetRate", dataMap);
            logger.error("ipoCaseH5获取发行人财务数据发生错误:{}", Throwables.getStackTraceAsString(e));
        }
        //公司的所有排名情况
        try {
            IpoH5Dto dto = new IpoH5Dto();
            dto.setBid(id);
            IpoH5Dto ipoCompanyRank = ipoInterfaceService.ipoCompanyRank(dto);
            if(ipoCompanyRank != null){
                dataMap = new HashMap<>();
                dataMap.put("paramName", "公司排名情况");
                dataMap.put("paramData", JsonUtil.toJsonNoNull(ipoCompanyRank));
                resultMap.put("ipoCompanyRank", dataMap);
            }else{
                dataMap = new HashMap<>();
                dataMap.put("paramName", "公司排名情况");
                dataMap.put("paramData", "0");
                resultMap.put("ipoCompanyRank", dataMap);
            }
        }catch (Exception e){
            dataMap = new HashMap<>();
            dataMap.put("paramName", "公司排名情况");
            dataMap.put("paramData", "0");
            resultMap.put("ipoCompanyRank", dataMap);
            logger.error("ipoCaseH5获取公司排名情况数据发生错误:{}", Throwables.getStackTraceAsString(e));
        }
        return resultMap;

    }

    /**
     * GK 报告期主要供应商及客户情况
     */
    @RequestMapping(value = "/supplierCustomerData", method = RequestMethod.GET)
    public Map<String, List<SupplierCustomerMainDto>> supplierCustomerData(
            @RequestParam("id") String id) {
        return companyOverviewService.getSupCusData(id);
    }

    /**
     * GK 发行人财务数据
     */
    @RequestMapping(value = "/selectFinanceOverListH5", method = RequestMethod.GET)
    public IpoH5FinanceResultDto selectFinanceOverListH5(@RequestParam("id") String id) {
        return ipoFinanceService.selectFinanceOverListH5(id);
    }

    /**
     * 获取下拉框数据
     *
     * @author yangj
     */
    @RequestMapping("/querySelectData")
    @ResponseBody
    public Map<String, Object> querySelectData() {
        Map<String, Object> result = ipoInterfaceService.querySelectData();
        return result;
    }

    /**
     * 获取科创板IPO数据
     *
     * @param ipoCaseListBo industryCsrc（所属行业）   codeOrName（简称代码）  iecResult（审核状态）
     *                      belongsBureau(注册地)
     * @author yangj
     */
    @RequestMapping(value = "/getIpoCaseList", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getIpoCaseList(@RequestBody IpoCaseListBo ipoCaseListBo) {
        QueryInfo<IpoCaseListBo> page = new QueryInfo<>();
//        没有查询条件时 实例化dto
        if (null == ipoCaseListBo) {
            ipoCaseListBo = new IpoCaseListBo();
        }
//        目前设置科创板条件  通过前台传递科创板条件 方便复用接口
//        ipoCaseListBo.setIpoPlate(Global.OPO_BOARD_61);
        page.setCondition(ipoCaseListBo);
//        获取总数 全部取出来 目前
        Map<String, Object> ipoCaseList = ipoCaseListService.getIpoCaseList(page, false);
        Integer total = (Integer) ipoCaseList.get("total");
//        设置分页参数
        page.setStartRow(0);
        page.setPageSize(total);
        Map<String, Object> result = ipoCaseListService.getIpoCaseList(page, false);
//        可能需要  对重复数据 进行名称的处理
        return result;
    }


    /**
     * dxy 行业与技术接口
     *
     * @param id 案例id
     */
    @RequestMapping(value = "/getTechnology", method = RequestMethod.GET)
    public Map getTechnology(@RequestParam("id") String id) {
        return ipoInterfaceService.getIpoTechnology(id);
    }

    /**
     * lixinwei 上市进展
     *
     * @param id 案例主键
     */
    @RequestMapping(value = "/selectProcessList", method = RequestMethod.GET)
    public TreeTypeProgressDto selectProcessList(String id) {
        TreeTypeProgressDto resultList = ipoProcessService.selectProcessList(id, "02");
        return resultList;
    }

    /**
     * lixinwei 最新估值
     *
     * @param id 案例主键
     */
    @RequestMapping(value = "/valuationData", method = RequestMethod.GET)
    public List<IpoValuationDto> valuationData(String id) {
        return companyOverviewService.getVluationData(id);
    }

    /**
     * lixinwei 上市条件、公司信息
     *
     * @param id 案例主键
     */
    @RequestMapping(value = "/caseDetail", method = RequestMethod.GET)
    public CompanyOverviewVo caseDetail(String id) {
        return companyOverviewService.getIpoCaseDetail(id);
    }

    /**
     * lixinwei 主营业务构成
     *
     * @param id 案例主键
     */
    @RequestMapping(value = "/incomeData", method = RequestMethod.GET)
    public MainIncomeVo incomeData(String id) {
        return companyOverviewService.getIncomeData(id);
    }

    /**
     * lixinwei 股东信息
     *
     * @param id 案例主键
     */
    @RequestMapping(value = "/shareHolderData", method = RequestMethod.GET)
    public List<IpoPersonInfoDto> shareHolderData(String id) {
        return companyOverviewService.getShareData(id);
    }

    /**
     * lixinwei 登录其他资本市场
     *
     * @param id 案例主键
     */
    @RequestMapping(value = "/marketData", method = RequestMethod.GET)
    public List<OtherMarketInfoDto> marketData(String id) {
        return companyOverviewService.getMarketData(id);
    }

    /**
     * lixinwei 上交所问询情况
     *
     * @param id 案例主键
     */
    @RequestMapping(value = "/selectNewFeedbackList", method = RequestMethod.GET)
    public List<IpoFeedbackDto> selectNewFeedbackList(String id) {
        return ipoFeedbackService.selectNewFeedbackList(id);
    }

    /**
     * lixinwei 中介结构
     *
     * @param id        案例主键
     * @param validFlag 生效失效
     */
    @RequestMapping(value = "/intermediaryOrgData", method = RequestMethod.GET)
    public Map<String, List<IntermediaryOrgDto>> intermediaryOrgData(
            @RequestParam("id") String id,
            @RequestParam(value = "validFlag", required = false) String validFlag) {
        return companyOverviewService.getIntermediaryOrgData(id, validFlag);
    }

    /**
     * lixinwei 可能还想看
     */
    @RequestMapping(value = "/otherIpoCase", method = RequestMethod.GET)
    public List<IpoCaseIndexDto> otherIpoCase(IpoCaseIndexDto ipoCaseIndexDto) {
        return ipoInterfaceService.otherIpoCase(ipoCaseIndexDto);
    }

}

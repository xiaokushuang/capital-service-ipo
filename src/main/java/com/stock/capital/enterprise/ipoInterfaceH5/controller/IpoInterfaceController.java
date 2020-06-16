package com.stock.capital.enterprise.ipoInterfaceH5.controller;

import com.google.common.base.Throwables;
import com.google.common.collect.Maps;

import com.stock.capital.enterprise.ipoCase.dao.IpoFeedbackMapper;
import com.stock.capital.enterprise.ipoCase.dto.*;
import com.stock.capital.enterprise.ipoCase.service.CompanyOverviewService;
import com.stock.capital.enterprise.ipoCase.service.IpoCaseListService;
import com.stock.capital.enterprise.ipoCase.service.IpoExamineService;
import com.stock.capital.enterprise.ipoCase.service.IpoFeedbackService;
import com.stock.capital.enterprise.ipoCase.service.IpoFinanceService;
import com.stock.capital.enterprise.ipoCase.service.IpoProcessService;
import com.stock.capital.enterprise.ipoInterfaceH5.dto.IpoFinanceH5Dto;
import com.stock.capital.enterprise.ipoInterfaceH5.dto.IpoH5CoreDevDto;
import com.stock.capital.enterprise.ipoInterfaceH5.dto.IpoH5DetailDto;
import com.stock.capital.enterprise.ipoInterfaceH5.dto.IpoH5Dto;
import com.stock.capital.enterprise.ipoInterfaceH5.dto.IpoH5FinanceListDto;
import com.stock.capital.enterprise.ipoInterfaceH5.dto.IpoH5FinanceResultDto;
import com.stock.capital.enterprise.ipoInterfaceH5.dto.IpoH5IndustryDto;
import com.stock.capital.enterprise.ipoInterfaceH5.dto.IpoH5IssueDataDto;
import com.stock.capital.enterprise.ipoInterfaceH5.dto.IpoH5TechnologyDto;
import com.stock.capital.enterprise.ipoInterfaceH5.service.IpoInterfaceService;
import com.stock.capital.enterprise.utils.SensitiveWord;
import com.stock.capital.enterprise.utils.WXUtils;
import com.stock.core.controller.BaseController;
import com.stock.core.dto.JsonResponse;
import com.stock.core.dto.QueryInfo;
import com.stock.core.rest.RestClient;
import com.stock.core.util.DateUtil;
import com.stock.core.util.JsonUtil;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletResponse;

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
    @Autowired
    private IpoFeedbackMapper ipoFeedbackMapper;
    @Autowired
    private IpoExamineService ipoExamineService;

    @Value("${wechat.appid}")
    private String wechatAppid;
    @Value("${wechat.secret}")
    private String wechatSecret;

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
    public Map<String, Object> ipoCaseH5(String id,String ipoPlate) {
        Map<String, Object> resultMap = Maps.newHashMap();
        Map<String, Object> dataMap = Maps.newHashMap();
        IpoCaseListBo ipoCaseListBo = new IpoCaseListBo();
        ipoCaseListBo.setIpoPlate(ipoPlate);

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
        String industryCsrcCode = "";
        //上市条件、公司信息
        try {
            CompanyOverviewVo caseDetaild = caseDetail(id);
            if (caseDetaild != null) {
                industryCsrcCode = caseDetaild.getIndustryCsrcCode();
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
        //募集资金
        try {
            List<IpoInvestItemDto> investList = ipoInterfaceService.selectInvestItem(id);
            if (null != investList && investList.size()>0) {
                dataMap = new HashMap<>();
                dataMap.put("paramName", "前三募集资金");
                dataMap.put("paramData", JsonUtil.toJsonNoNull(investList));
                resultMap.put("investListData", dataMap);
                dataMap = new HashMap<>();
                dataMap.put("paramName", "募集资金总额");
                dataMap.put("paramData", JsonUtil.toJsonNoNull(investList.get(0).getInvestPlanLimit()));
                resultMap.put("invest", dataMap);
            } else {
                dataMap = new HashMap<>();
                dataMap.put("paramName", "前三募集资金");
                dataMap.put("paramData", "0");
                resultMap.put("investListData", dataMap);
                dataMap = new HashMap<>();
                dataMap.put("paramName", "募集资金总额");
                dataMap.put("paramData", "0");
                resultMap.put("invest", dataMap);
            }
        } catch (Exception e) {
            dataMap = new HashMap<>();
            dataMap.put("paramName", "前三募集资金");
            dataMap.put("paramData", "0");
            resultMap.put("investListData", dataMap);
            dataMap = new HashMap<>();
            dataMap.put("paramName", "募集资金总额");
            dataMap.put("paramData", "0");
            resultMap.put("invest", dataMap);
            logger.error("ipoCaseH5获取募集资金发生错误:{}", Throwables.getStackTraceAsString(e));
        }
        //股东信息
        try {
            List<IpoPersonInfoDto> shareHolderData = shareHolderData(id);
            resultMap.put("shareHolderData", shareHolderData);
            if (null != shareHolderData) {
                if (shareHolderData.size() != 0){
                    dataMap = new HashMap<>();
                    dataMap.put("paramName", "股权结构");
                    dataMap.put("paramData", JsonUtil.toJsonNoNull(shareHolderData));
                    resultMap.put("shareHolderData", dataMap);
                }else {
                    dataMap = new HashMap<>();
                    dataMap.put("paramName", "股权结构");
                    dataMap.put("paramData", "0");
                    resultMap.put("shareHolderData", dataMap);
                }
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
            Map<String,List<IpoFeedbackDto>> selectNewFeedbackList = selectNewFeedbackList(id);
            if (selectNewFeedbackList != null) {
                if (selectNewFeedbackList.get("registerList") != null){
                    dataMap = new HashMap<>();
                    dataMap.put("paramName", "注册制问询情况");
                    dataMap.put("paramData", JsonUtil.toJsonNoNull(selectNewFeedbackList.get("registerList")));
                    resultMap.put("selectNewFeedbackZczList", dataMap);
                }else {
                    dataMap = new HashMap<>();
                    dataMap.put("paramName", "注册制问询情况");
                    dataMap.put("paramData", "0");
                    resultMap.put("selectNewFeedbackZczList", dataMap);
                }
                if (selectNewFeedbackList.get("ratifyList") != null){
                    dataMap = new HashMap<>();
                    dataMap.put("paramName", "核准制问询情况");
                    dataMap.put("paramData", JsonUtil.toJsonNoNull(selectNewFeedbackList.get("ratifyList")));
                    resultMap.put("selectNewFeedbackHzzList", dataMap);
                }else {
                    dataMap = new HashMap<>();
                    dataMap.put("paramName", "核准制问询情况");
                    dataMap.put("paramData", "0");
                    resultMap.put("selectNewFeedbackHzzList", dataMap);
                }
            } else {
                dataMap = new HashMap<>();
                dataMap.put("paramName", "注册制问询情况");
                dataMap.put("paramData", "0");
                resultMap.put("selectNewFeedbackZczList", dataMap);
                dataMap = new HashMap<>();
                dataMap.put("paramName", "核准制问询情况");
                dataMap.put("paramData", "0");
                resultMap.put("selectNewFeedbackHzzList", dataMap);
            }
        } catch (Exception e) {
            dataMap = new HashMap<>();
            dataMap.put("paramName", "注册制问询情况");
            dataMap.put("paramData", "0");
            resultMap.put("selectNewFeedbackZczList", dataMap);
            dataMap = new HashMap<>();
            dataMap.put("paramName", "核准制问询情况");
            dataMap.put("paramData", "0");
            resultMap.put("selectNewFeedbackHzzList", dataMap);
            logger.error("ipoCaseH5获取问询情况发生错误:{}", Throwables.getStackTraceAsString(e));
        }
        //中介结构
        try {
            Map<String, List<IntermediaryOrgDto>> intermediaryOrgData = intermediaryOrgData(id, "",ipoPlate);
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
            if (StringUtils.isNotEmpty(industryCsrcCode)){
                ipoCaseIndex.setIndustryCsrc(industryCsrcCode);
            }
            String orgCode = ipoFeedbackMapper.getOrgCode(id).getOrgCode();
            ipoCaseIndex.setOrgCode(orgCode);
            ipoCaseIndex.setIpoPlate(ipoPlate);
            List<IpoCaseListVo> otherIpoCase = otherIpoCase(ipoCaseIndex);
            if (otherIpoCase != null) {
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

        //发行情况
        dataMap = Maps.newHashMap();
        try {
            IpoH5IssueDataDto issueData = issueData(id);
            if (issueData != null ) {
                dataMap.put("paramName", "发行情况");
                dataMap.put("paramData", JsonUtil.toJsonNoNull(issueData));
            } else {
                dataMap.put("paramName", "发行情况");
                dataMap.put("paramData", "0");
            }
            resultMap.put("issueData", dataMap);
        } catch (Exception e) {
            dataMap.put("paramName", "发行情况");
            dataMap.put("paramData", "0");
            resultMap.put("issueData", dataMap);
            logger.error("ipoCaseH5获取发行情况发生错误:{}", Throwables.getStackTraceAsString(e));
        }

        //拆分上市情况
        dataMap = Maps.newHashMap();
        try {
            List<IpoSplitDto> spliteDataList = spliteData(id);
            if (CollectionUtils.isNotEmpty(spliteDataList) ) {
                dataMap.put("paramName", "拆分上市情况");
                dataMap.put("paramData", JsonUtil.toJsonNoNull(spliteDataList));
            } else {
                dataMap.put("paramName", "拆分上市情况");
                dataMap.put("paramData", "0");
            }
            resultMap.put("spliteDataList", dataMap);
        } catch (Exception e) {
            dataMap.put("paramName", "拆分上市情况");
            dataMap.put("paramData", "0");
            resultMap.put("spliteDataList", dataMap);
            logger.error("ipoCaseH5获取拆分上市情况发生错误:{}", Throwables.getStackTraceAsString(e));
        }


        //行业与技术接口
        try {
            Map technology = getTechnology(id);
            //行业地位
            if ((List<IssuerIndustryStatusDto>) technology.get("industryStatusInfo") != null){
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
            }
            //主要竞争对手
            if ((List<MainCompetitorInfoDto>) technology.get("mainCompetitorInfo") != null){
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
            }
            //毛利率对比
            if ((IpoH5IndustryDto) technology.get("industryCompareRateInfo") != null){
                IpoH5IndustryDto ipoH5IndustryDto = (IpoH5IndustryDto) technology.get("industryCompareRateInfo");
                if(CollectionUtils.isNotEmpty(ipoH5IndustryDto.getTitles()) || CollectionUtils.isNotEmpty(ipoH5IndustryDto.getBody())){
                    dataMap = new HashMap<>();
                    dataMap.put("paramName", "毛利率对比");
                    dataMap.put("paramData", JsonUtil.toJsonNoNull(ipoH5IndustryDto));
                    resultMap.put("industryCompareRateInfo", dataMap);
                } else {
                    dataMap = new HashMap<>();
                    dataMap.put("paramName", "毛利率对比");
                    dataMap.put("paramData", "0");
                    resultMap.put("industryCompareRateInfo", dataMap);
                }
            }
            //科技创新
            if ((IpoH5TechnologyDto) technology.get("technologyInfo") != null){
                IpoH5TechnologyDto ipoTechnologyVo = (IpoH5TechnologyDto) technology.get("technologyInfo");
                if (ipoTechnologyVo != null) {
                    //研发投入
                    Map<String, List> devData = ipoTechnologyVo.getDevData();
                    if (CollectionUtils.isNotEmpty(devData.get("income")) &&
                            CollectionUtils.isNotEmpty(devData.get("expensesCost"))) {
                        dataMap = new HashMap<>();
                        dataMap.put("paramName", "研发投入");
                        dataMap.put("paramData", JsonUtil.toJsonNoNull(devData));
                        resultMap.put("devData", dataMap);
                    } else {
                        dataMap = new HashMap<>();
                        dataMap.put("paramName", "研发投入");
                        dataMap.put("paramData", "0");
                        resultMap.put("devData", dataMap);
                    }
//                //专利情况
//                List<IpoTechnologyPatentDto> patentData = ipoTechnologyVo.getPatentData();
//                if(CollectionUtils.isNotEmpty(patentData)){
//                    dataMap = new HashMap<>();
//                    dataMap.put("paramName", "专利情况");
//                    dataMap.put("paramData", JsonUtil.toJsonNoNull(patentData));
//                    resultMap.put("patentData", dataMap);
//                }else{
//                    dataMap = new HashMap<>();
//                    dataMap.put("paramName", "专利情况");
//                    dataMap.put("paramData", "0");
//                    resultMap.put("patentData", dataMap);
//                }
                    //核心技术及研发技术人员
                    List<Map<String, IpoH5CoreDevDto>> coreData = ipoTechnologyVo.getCoreData();
                    if(StringUtils.isNotBlank(coreData.get(0).get("companyStaff").getIndexDate()) &&
                            StringUtils.isNotBlank(coreData.get(1).get("industryStaff").getIndexDate())){
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
//            dataMap = new HashMap<>();
//            dataMap.put("paramName", "专利情况");
//            dataMap.put("paramData", "0");
//            resultMap.put("patentData", dataMap);
            dataMap = new HashMap<>();
            dataMap.put("paramName", "核心技术及研发技术人员");
            dataMap.put("paramData", "0");
            resultMap.put("coreData", dataMap);
            logger.error("ipoCaseH5获取行业与技术发生错误:{}", Throwables.getStackTraceAsString(e));
        }

        // 专利情况
        try{
            IpoH5Dto result = getPatentSituation(id,ipoPlate);
            if (result != null && (StringUtils.isNotBlank(result.getPatent()) || StringUtils.isNotBlank(result.getPatentNew()) ||
                StringUtils.isNotBlank(result.getPatentDesign()) || StringUtils.isNotBlank(result.getPatentInternation()))){
                dataMap = new HashMap<>();
                dataMap.put("paramName", "专利情况");
                dataMap.put("paramData", JsonUtil.toJsonNoNull(result));
                resultMap.put("patentSituation", dataMap);
            }else {
                dataMap = new HashMap<>();
                dataMap.put("paramName", "专利情况");
                dataMap.put("paramData", "0");
                resultMap.put("patentSituation", dataMap);
            }
        }catch (Exception e){
            dataMap = new HashMap<>();
            dataMap.put("paramName", "专利情况");
            dataMap.put("paramData", "0");
            resultMap.put("patentSituation", dataMap);
            logger.error("ipoCaseH5获取专利情况发生错误:{}", Throwables.getStackTraceAsString(e));
        }

        //报告期主要供应商及客户情况
        try {
            Map<String, Object> supplierCustomerData = supplierCustomerData(id);
            List<Map<String, Object>> supplierMainList = (List<Map<String, Object>>) supplierCustomerData.get("supplierMainList");
            if (CollectionUtils.isNotEmpty(supplierMainList)) {
                dataMap = new HashMap<>();
                dataMap.put("paramName", "前五大供应商占比情况");
                dataMap.put("paramData", JsonUtil.toJsonNoNull(supplierMainList));
                resultMap.put("supplierMainList", dataMap);
            } else {
                dataMap = new HashMap<>();
                dataMap.put("paramName", "前五大供应商占比情况");
                dataMap.put("paramData", "0");
                resultMap.put("supplierMainList", dataMap);
            }
            List<Map<String, Object>> customerList = (List<Map<String, Object>>) supplierCustomerData.get("customerMainList");
            if (CollectionUtils.isNotEmpty(customerList)) {
                dataMap = new HashMap<>();
                dataMap.put("paramName", "前五大客户占比情况");
                dataMap.put("paramData", JsonUtil.toJsonNoNull(customerList));
                resultMap.put("customerMainList", dataMap);
            } else {
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
            if (CollectionUtils.isNotEmpty(incomeList)) {
                dataMap = new HashMap<>();
                dataMap.put("paramName", "营业收入");
                dataMap.put("paramData", JsonUtil.toJsonNoNull(incomeList));
                resultMap.put("incomeList", dataMap);
            } else {
                dataMap = new HashMap<>();
                dataMap.put("paramName", "营业收入");
                dataMap.put("paramData", "0");
                resultMap.put("incomeList", dataMap);
            }
            List<IpoFinanceH5Dto> grossList = financeOverListH5.getGrossList();
            if (CollectionUtils.isNotEmpty(grossList)) {
                dataMap = new HashMap<>();
                dataMap.put("paramName", "毛利率");
                dataMap.put("paramData", JsonUtil.toJsonNoNull(grossList));
                resultMap.put("grossList", dataMap);
            } else {
                dataMap = new HashMap<>();
                dataMap.put("paramName", "毛利率");
                dataMap.put("paramData", "0");
                resultMap.put("grossList", dataMap);
            }
            IpoFinanceH5Dto incomeRate = financeOverListH5.getIncomeRate();
            if (null != incomeRate) {
                dataMap = new HashMap<>();
                dataMap.put("paramName", "营业收入复合增长率");
                dataMap.put("paramData", JsonUtil.toJsonNoNull(incomeRate));
                resultMap.put("incomeRate", dataMap);
            } else {
                dataMap = new HashMap<>();
                dataMap.put("paramName", "营业收入复合增长率");
                dataMap.put("paramData", "0");
                resultMap.put("incomeRate", dataMap);
            }
            List<IpoFinanceH5Dto> profitList = financeOverListH5.getProfitList();
            if (CollectionUtils.isNotEmpty(profitList)) {
                dataMap = new HashMap<>();
                dataMap.put("paramName", "净利润");
                dataMap.put("paramData", JsonUtil.toJsonNoNull(profitList));
                resultMap.put("profitList", dataMap);
            } else {
                dataMap = new HashMap<>();
                dataMap.put("paramName", "净利润");
                dataMap.put("paramData", "0");
                resultMap.put("profitList", dataMap);
            }
            List<IpoFinanceH5Dto> profitRateList = financeOverListH5.getProfitRateList();
            if (CollectionUtils.isNotEmpty(profitRateList)) {
                dataMap = new HashMap<>();
                dataMap.put("paramName", "净利润率");
                dataMap.put("paramData", JsonUtil.toJsonNoNull(profitRateList));
                resultMap.put("profitRateList", dataMap);
            } else {
                dataMap = new HashMap<>();
                dataMap.put("paramName", "净利润率");
                dataMap.put("paramData", "0");
                resultMap.put("profitRateList", dataMap);
            }
            IpoFinanceH5Dto profitRate = financeOverListH5.getProfitRate();
            if (null != profitRate) {
                dataMap = new HashMap<>();
                dataMap.put("paramName", "净利润复合增长率");
                dataMap.put("paramData", JsonUtil.toJsonNoNull(profitRate));
                resultMap.put("profitRate", dataMap);
            } else {
                dataMap = new HashMap<>();
                dataMap.put("paramName", "净利润复合增长率");
                dataMap.put("paramData", "0");
                resultMap.put("profitRate", dataMap);
            }
            List<IpoFinanceH5Dto> indexList = financeOverListH5.getMainIndexList();
            if (CollectionUtils.isNotEmpty(indexList)) {
                dataMap = new HashMap<>();
                dataMap.put("paramName", "财务比率");
                dataMap.put("paramData", JsonUtil.toJsonNoNull(indexList));
                resultMap.put("indexList", dataMap);
            } else {
                dataMap = new HashMap<>();
                dataMap.put("paramName", "财务比率");
                dataMap.put("paramData", "0");
                resultMap.put("indexList", dataMap);
            }
            List<IpoH5FinanceListDto> debtList = financeOverListH5.getDebtList();
            if (CollectionUtils.isNotEmpty(debtList)) {
                dataMap = new HashMap<>();
                dataMap.put("paramName", "资产负债情况");
                dataMap.put("paramData", JsonUtil.toJsonNoNull(debtList));
                resultMap.put("debtList", dataMap);
            } else {
                dataMap = new HashMap<>();
                dataMap.put("paramName", "资产负债情况");
                dataMap.put("paramData", "0");
                resultMap.put("debtList", dataMap);
            }
            List<IpoFinanceH5Dto> cashFlowList = financeOverListH5.getCashFlowList();
            if (CollectionUtils.isNotEmpty(cashFlowList)) {
                dataMap = new HashMap<>();
                dataMap.put("paramName", "现金流量情况");
                dataMap.put("paramData", JsonUtil.toJsonNoNull(cashFlowList));
                resultMap.put("cashFlowList", dataMap);
            } else {
                dataMap = new HashMap<>();
                dataMap.put("paramName", "现金流量情况");
                dataMap.put("paramData", "0");
                resultMap.put("cashFlowList", dataMap);
            }
            IpoFinanceH5Dto cashFlowRate = financeOverListH5.getCashFlowRate();
            if (null != cashFlowRate) {
                dataMap = new HashMap<>();
                dataMap.put("paramName", "现金流量复合增长率");
                dataMap.put("paramData", JsonUtil.toJsonNoNull(cashFlowRate));
                resultMap.put("cashFlowRate", dataMap);
            } else {
                dataMap = new HashMap<>();
                dataMap.put("paramName", "现金流量复合增长率");
                dataMap.put("paramData", "0");
                resultMap.put("cashFlowRate", dataMap);
            }
            List<IpoFinanceH5Dto> cashList = financeOverListH5.getCashList();
            if (CollectionUtils.isNotEmpty(cashList)) {
                dataMap = new HashMap<>();
                dataMap.put("paramName", "现金及现金等价物净增加额");
                dataMap.put("paramData", JsonUtil.toJsonNoNull(cashList));
                resultMap.put("cashList", dataMap);
            } else {
                dataMap = new HashMap<>();
                dataMap.put("paramName", "现金及现金等价物净增加额");
                dataMap.put("paramData", "0");
                resultMap.put("cashList", dataMap);
            }
            IpoFinanceH5Dto cashRate = financeOverListH5.getCashRate();
            if (null != cashRate) {
                dataMap = new HashMap<>();
                dataMap.put("paramName", "现金及现金等价物净增加额复合增长率");
                dataMap.put("paramData", JsonUtil.toJsonNoNull(cashRate));
                resultMap.put("cashRate", dataMap);
            } else {
                dataMap = new HashMap<>();
                dataMap.put("paramName", "现金及现金等价物净增加额复合增长率");
                dataMap.put("paramData", "0");
                resultMap.put("cashRate", dataMap);
            }
            IpoFinanceH5Dto sumAssetRate = financeOverListH5.getSumAssetRate();
            if (null != sumAssetRate) {
                dataMap = new HashMap<>();
                dataMap.put("paramName", "总资产复合增长率");
                dataMap.put("paramData", JsonUtil.toJsonNoNull(sumAssetRate));
                resultMap.put("sumAssetRate", dataMap);
            } else {
                dataMap = new HashMap<>();
                dataMap.put("paramName", "总资产复合增长率");
                dataMap.put("paramData", "0");
                resultMap.put("sumAssetRate", dataMap);
            }
            IpoFinanceDateDto dateDto = financeOverListH5.getDateList();
            if (null != dateDto) {
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
            logger.info("#######【公司排名查询条件id："+id+"###########");
            IpoH5Dto ipoCompanyRank = ipoInterfaceService.ipoCompanyRank(dto);
            if (ipoCompanyRank != null) {
                dataMap = new HashMap<>();
                dataMap.put("paramName", "公司排名情况");
                dataMap.put("paramData", JsonUtil.toJsonNoNull(ipoCompanyRank));
                resultMap.put("ipoCompanyRank", dataMap);
                logger.info("#######【公司排名查询条件json："+JsonUtil.toJsonNoNull(ipoCompanyRank)+"###########");
            } else {
                dataMap = new HashMap<>();
                dataMap.put("paramName", "公司排名情况");
                dataMap.put("paramData", "0");
                resultMap.put("ipoCompanyRank", dataMap);
                logger.info("#######【公司排名查询为空###########");
            }
        } catch (Exception e) {
            dataMap = new HashMap<>();
            dataMap.put("paramName", "公司排名情况");
            dataMap.put("paramData", "0");
            resultMap.put("ipoCompanyRank", dataMap);
            logger.error("ipoCaseH5获取公司排名情况数据发生错误:{}", Throwables.getStackTraceAsString(e));
        }
        //案例是否通过
        try {
            HeadDataVo headDataVo = getHeadData(id);
            if (headDataVo != null) {
                dataMap = new HashMap<>();
                dataMap.put("paramName", "案例是否通过");
                dataMap.put("paramData", JsonUtil.toJsonNoNull(headDataVo));
                resultMap.put("headDataVo", dataMap);
            } else {
                dataMap = new HashMap<>();
                dataMap.put("paramName", "案例是否通过");
                dataMap.put("paramData", "0");
                resultMap.put("headDataVo", dataMap);
            }
        } catch (Exception e) {
            dataMap = new HashMap<>();
            dataMap.put("paramName", "案例是否通过");
            dataMap.put("paramData", "0");
            resultMap.put("headDataVo", dataMap);
            logger.error("ipoCaseH5获取案例是否通过数据发生错误:{}", Throwables.getStackTraceAsString(e));
        }

        //上市委关注问题
        try {
            List<IpoFeedbackDto> examineList = selectNewExamineList(id);
            if (examineList != null) {
                List<IpoFeedbackDto> examineHzzList = new ArrayList<>();
                List<IpoFeedbackDto> examineZczList = new ArrayList<>();
                for (int i=0;i<examineList.size();i++){
                    if (StringUtils.isNotEmpty(examineList.get(i).getLetterName())){
                        if (examineList.get(i).getLetterName().equals("发审会关注问题")){
                            examineHzzList.add(examineList.get(i));
                        }
                        if (examineList.get(i).getLetterName().equals("上市会关注问题")){
                            examineZczList.add(examineList.get(i));
                        }
                    }
                }
                if (examineZczList != null && examineHzzList.size()>0){
                    dataMap = new HashMap<>();
                    dataMap.put("paramName", "上市委关注问题");
                    dataMap.put("paramData", JsonUtil.toJsonNoNull(examineZczList));
                    resultMap.put("examineZczList", dataMap);
                }else {
                    dataMap = new HashMap<>();
                    dataMap.put("paramName", "上市委关注问题");
                    dataMap.put("paramData", "0");
                    resultMap.put("examineZczList", dataMap);
                }
                if (examineHzzList != null && examineHzzList.size()>0){
                    dataMap = new HashMap<>();
                    dataMap.put("paramName", "发审委关注问题");
                    dataMap.put("paramData", JsonUtil.toJsonNoNull(examineHzzList));
                    resultMap.put("examineHzzList", dataMap);
                }else {
                    dataMap = new HashMap<>();
                    dataMap.put("paramName", "发审委关注问题");
                    dataMap.put("paramData", "0");
                    resultMap.put("examineHzzList", dataMap);
                }
            } else {
                dataMap = new HashMap<>();
                dataMap.put("paramName", "上市委关注问题");
                dataMap.put("paramData", "0");
                resultMap.put("examineZczList", dataMap);
                dataMap = new HashMap<>();
                dataMap.put("paramName", "发审委关注问题");
                dataMap.put("paramData", "0");
                resultMap.put("examineHzzList", dataMap);
            }
        } catch (Exception e) {
            dataMap = new HashMap<>();
            dataMap.put("paramName", "上市委关注问题");
            dataMap.put("paramData", "0");
            resultMap.put("examineZczList", dataMap);
            dataMap = new HashMap<>();
            dataMap.put("paramName", "发审委关注问题");
            dataMap.put("paramData", "0");
            resultMap.put("examineHzzList", dataMap);
            logger.error("ipoCaseH5获取上市委关注问题发生错误:{}", Throwables.getStackTraceAsString(e));
        }
        return resultMap;

    }

    /**
     * GK 报告期主要供应商及客户情况
     */
    @RequestMapping(value = "/supplierCustomerData", method = RequestMethod.GET)
    public Map<String, Object> supplierCustomerData(
            @RequestParam("id") String id) {
        Map<String, Object> resultMap = Maps.newHashMap();
        Map<String, List<SupplierCustomerMainDto>> result = companyOverviewService.getSupCusData(id);
        List<SupplierCustomerMainDto> supplierMainList = result.get("supplierMainList");
        List<Map<String, Object>> supplierResultList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(supplierMainList)){
            for (int i = 0; i < supplierMainList.size(); i++) {
                Map<String, Object> supplierTitleMap = Maps.newHashMap();
                List<Map<String, Object>> supplierDataList = new ArrayList<>();
                supplierTitleMap.put("name", supplierMainList.get(i).getTitle());
                List<SupplierCustomerInfoDto> dataList = supplierMainList.get(i).getSupplierCustomerInfoList();
                List<String> timeList = new ArrayList<>();
                List<BigDecimal> sumValueList = new ArrayList<>();
                String onePeriodStr = DateUtil.getDateStr(supplierMainList.get(i).getReportPeriod(), "yyyy-MM-dd");
//            String onePeriodStr = DateUtils.formatDate(supplierMainList.get(i).getReportPeriod(), "yyyy-MM-dd");
                if (StringUtils.isNotEmpty(onePeriodStr) && onePeriodStr.indexOf("12-31") >= 0) {
                    for (int j = 0; j < dataList.size() - 1; j++) {
                        //列表只展示最近一年的5家供应商和客户，所以判断最近一年数据不为空，则放入列表
                        if(null != dataList.get(j).getOnePeriodAmount()){
                            Map<String, Object> supplierDataMap = Maps.newHashMap();
                            supplierDataMap.put("name", dataList.get(j).getCompanyName());
                            supplierDataMap.put("ratio", dataList.get(j).getOnePeriodRatio());
                            supplierDataMap.put("number", dataList.get(j).getOnePeriodAmount());
                            supplierDataList.add(supplierDataMap);
                        }
                    }

                    timeList.add(supplierMainList.get(i).getSecondYearForSupplier());
                    timeList.add(supplierMainList.get(i).getThirdYearForSupplier());
                    timeList.add(DateUtil.getDateStr(supplierMainList.get(i).getReportPeriod(), "yyyy-MM-dd"));
//                timeList.add(DateUtils.formatDate(supplierMainList.get(i).getReportPeriod(), "yyyy-MM-dd"));

                    if (dataList.size() > 1) {
                        sumValueList.add(dataList.get(dataList.size() - 1).getSecondYearRatio());
                        sumValueList.add(dataList.get(dataList.size() - 1).getThirdYearRatio());
                        sumValueList.add(dataList.get(dataList.size() - 1).getOnePeriodRatio());
                    }
                } else {
                    for (int j = 0; j < dataList.size() - 1; j++) {
                        if(null != dataList.get(j).getThirdYearAmount()){
                            Map<String, Object> supplierDataMap = Maps.newHashMap();
                            supplierDataMap.put("name", dataList.get(j).getCompanyName());
                            supplierDataMap.put("ratio", dataList.get(j).getThirdYearRatio());
                            supplierDataMap.put("number", dataList.get(j).getThirdYearAmount());
                            supplierDataList.add(supplierDataMap);
                        }
                    }

                    timeList.add(supplierMainList.get(i).getFirstYearForSupplier());
                    timeList.add(supplierMainList.get(i).getSecondYearForSupplier());
                    timeList.add(supplierMainList.get(i).getThirdYearForSupplier());

                    if (dataList.size() > 1) {
                        sumValueList.add(dataList.get(dataList.size() - 1).getFirstYearRatio());
                        sumValueList.add(dataList.get(dataList.size() - 1).getSecondYearRatio());
                        sumValueList.add(dataList.get(dataList.size() - 1).getThirdYearRatio());
                    }
                }

                supplierTitleMap.put("supplierDataList", supplierDataList);
                supplierTitleMap.put("timeList", timeList);
                supplierTitleMap.put("sumValueList", sumValueList);
                supplierResultList.add(supplierTitleMap);
            }
        }

        resultMap.put("supplierMainList", supplierResultList);


        List<SupplierCustomerMainDto> customerMainList = result.get("customerMainList");
        List<Map<String, Object>> customerResultList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(customerMainList)){
            for (int i = 0; i < customerMainList.size(); i++) {
                Map<String, Object> customerTitleMap = Maps.newHashMap();
                List<Map<String, Object>> customerDataList = new ArrayList<>();
                customerTitleMap.put("name", customerMainList.get(i).getTitle());
                List<SupplierCustomerInfoDto> dataList = customerMainList.get(i).getSupplierCustomerInfoList();
                List<String> timeList = new ArrayList<>();
                List<BigDecimal> sumValueList = new ArrayList<>();
                String onePeriodStr = DateUtil.getDateStr(customerMainList.get(i).getReportPeriod(), "yyyy-MM-dd");
//            String onePeriodStr = DateUtils.formatDate(customerMainList.get(i).getReportPeriod(), "yyyy-MM-dd");
                if (StringUtils.isNotEmpty(onePeriodStr) && onePeriodStr.indexOf("12-31") >= 0) {
                    for (int j = 0; j < dataList.size() - 1; j++) {
                        if(null != dataList.get(j).getOnePeriodAmount()){
                            Map<String, Object> customerDataMap = Maps.newHashMap();
                            customerDataMap.put("name", dataList.get(j).getCompanyName());
                            customerDataMap.put("ratio", dataList.get(j).getOnePeriodRatio());
                            customerDataMap.put("number", dataList.get(j).getOnePeriodAmount());
                            customerDataList.add(customerDataMap);
                        }
                    }

                    timeList.add(customerMainList.get(i).getSecondYearForCustomer());
                    timeList.add(customerMainList.get(i).getThirdYearForCustomer());
                    timeList.add(DateUtil.getDateStr(customerMainList.get(i).getReportPeriod(), "yyyy-MM-dd"));
//                timeList.add(DateUtils.formatDate(customerMainList.get(i).getReportPeriod(), "yyyy-MM-dd"));

                    if (dataList.size() > 1) {
                        sumValueList.add(dataList.get(dataList.size() - 1).getSecondYearRatio());
                        sumValueList.add(dataList.get(dataList.size() - 1).getThirdYearRatio());
                        sumValueList.add(dataList.get(dataList.size() - 1).getOnePeriodRatio());
                    }
                } else {
                    for (int j = 0; j < dataList.size() - 1; j++) {
                        if(null != dataList.get(j).getThirdYearAmount()) {
                            Map<String, Object> customerDataMap = Maps.newHashMap();
                            customerDataMap.put("name", dataList.get(j).getCompanyName());
                            customerDataMap.put("ratio", dataList.get(j).getThirdYearRatio());
                            customerDataMap.put("number", dataList.get(j).getThirdYearAmount());
                            customerDataList.add(customerDataMap);
                        }
                    }

                    timeList.add(customerMainList.get(i).getFirstYearForCustomer());
                    timeList.add(customerMainList.get(i).getSecondYearForCustomer());
                    timeList.add(customerMainList.get(i).getThirdYearForCustomer());

                    if (dataList.size() > 1) {
                        sumValueList.add(dataList.get(dataList.size() - 1).getFirstYearRatio());
                        sumValueList.add(dataList.get(dataList.size() - 1).getSecondYearRatio());
                        sumValueList.add(dataList.get(dataList.size() - 1).getThirdYearRatio());
                    }
                }

                customerTitleMap.put("timeList", timeList);
                customerTitleMap.put("customerDataList", customerDataList);
                customerTitleMap.put("sumValueList", sumValueList);
                customerResultList.add(customerTitleMap);
            }
        }
        resultMap.put("customerMainList", customerResultList);
        return resultMap;
    }

    /**
     * GK 发行人财务数据
     */
    @RequestMapping(value = "/selectFinanceOverListH5", method = RequestMethod.GET)
    public IpoH5FinanceResultDto selectFinanceOverListH5(@RequestParam("id") String id) {
        IpoH5FinanceResultDto resultDto = ipoFinanceService.selectFinanceOverListH5(id);
        List<IpoFinanceH5Dto> incomeList = resultDto.getIncomeList();
        IpoFinanceH5Dto incomeRate = resultDto.getIncomeRate();
        insertAverageData(incomeList, id, "016",incomeRate);
        List<IpoFinanceH5Dto> grossList = resultDto.getGrossList();
        insertAverageData(grossList, id, "115");
        List<IpoFinanceH5Dto> profitList = resultDto.getProfitList();
        List<IpoFinanceH5Dto> profitRateList = resultDto.getProfitRateList();
        insertAverageData(profitRateList, id, "114");
        IpoFinanceH5Dto profitRate = resultDto.getProfitRate();
        insertAverageData(profitList, id, "056",profitRate);
        List<IpoFinanceH5Dto> mainIndexList = resultDto.getMainIndexList();
        insertIndexAbgData(mainIndexList,id);
        List<IpoH5FinanceListDto> debtList = resultDto.getDebtList();
        insertDebtAbgData(debtList,id);
        List<IpoFinanceH5Dto> cashFlowList = resultDto.getCashFlowList();
        IpoFinanceH5Dto cashFlowRate = resultDto.getCashFlowRate();
        insertAverageData(cashFlowList, id, "046",cashFlowRate);
        List<IpoFinanceH5Dto> cashList = resultDto.getCashList();
        IpoFinanceH5Dto cashRate = resultDto.getCashRate();
        insertAverageData(cashList, id, "091",cashRate);

        return resultDto;
    }

    /**
     * 查询并规范财务数据 平均值
     */
    private void insertAverageData(List<IpoFinanceH5Dto> dataList, String id, String columnComment) {
        if (CollectionUtils.isNotEmpty(dataList)) {
            IpoH5DetailDto avgParam = new IpoH5DetailDto();
            avgParam.setBid(id);
            avgParam.setColumnComment(columnComment);
            avgParam.setPlateType("0");
            List<IpoH5DetailDto> KcbAverageList = ipoInterfaceService.ipoAvg(avgParam);
            avgParam.setPlateType("1");
            List<IpoH5DetailDto> CybAverageList = ipoInterfaceService.ipoAvg(avgParam);
            for (IpoFinanceH5Dto dataDto : dataList) {
                if(CollectionUtils.isNotEmpty(KcbAverageList)){
                    boolean flag = true;
                    for (IpoH5DetailDto avgDto : KcbAverageList) {
                        if (StringUtils.isNotEmpty(dataDto.getYear()) && dataDto.getYear().equals(avgDto.getYear())) {
                            BigDecimal kcbData = new BigDecimal(avgDto.getCurrValAvg());
                            dataDto.setKcbData(kcbData);
                            dataDto.setOrgCount(avgDto.getOrgCount());
                            flag = false;
                        }
                    }
                    if(flag){
                        dataDto.setKcbData(new BigDecimal("0"));
                    }
                }else{
                    dataDto.setKcbData(new BigDecimal("0"));
                }
                if(CollectionUtils.isNotEmpty(CybAverageList)){
                    boolean flag = true;
                    for (IpoH5DetailDto avgDto : CybAverageList) {
                        if (StringUtils.isNotEmpty(dataDto.getYear()) && dataDto.getYear().equals(avgDto.getYear())) {
                            BigDecimal cybData = new BigDecimal(avgDto.getCurrValAvg());
                            dataDto.setCybData(cybData);
                            flag = false;
                        }
                    }
                    if(flag){
                        dataDto.setCybData(new BigDecimal("0"));
                    }
                }else{
                    dataDto.setCybData(new BigDecimal("0"));
                }
            }
        }
    }

    /**
     * 查询并规范财务数据 平均值 （包含复合增长率）
     */
    private void insertAverageData(List<IpoFinanceH5Dto> dataList, String id, String columnComment,IpoFinanceH5Dto rateDto) {
        if (CollectionUtils.isNotEmpty(dataList)) {
            IpoH5DetailDto avgParam = new IpoH5DetailDto();
            avgParam.setBid(id);
            avgParam.setColumnComment(columnComment);
            avgParam.setPlateType("0");
            List<IpoH5DetailDto> KcbAverageList = ipoInterfaceService.ipoAvg(avgParam);
            avgParam.setPlateType("1");
            List<IpoH5DetailDto> CybAverageList = ipoInterfaceService.ipoAvg(avgParam);
            for (IpoFinanceH5Dto dataDto : dataList) {
                if(CollectionUtils.isNotEmpty(KcbAverageList)){
                    boolean flag = true;
                    for (IpoH5DetailDto avgDto : KcbAverageList) {
                        if (StringUtils.isNotEmpty(dataDto.getYear()) && dataDto.getYear().equals(avgDto.getYear())) {
                            BigDecimal kcbData = new BigDecimal(avgDto.getCurrValAvg());
                            dataDto.setKcbData(kcbData);
                            dataDto.setOrgCount(avgDto.getOrgCount());
                            flag = false;
                        }
                    }
                    if(flag){
                        dataDto.setKcbData(new BigDecimal("0"));
                    }
                }else{
                    dataDto.setKcbData(new BigDecimal("0"));
                }
                if(CollectionUtils.isNotEmpty(CybAverageList)){
                    boolean flag = true;
                    for (IpoH5DetailDto avgDto : CybAverageList) {
                        if (StringUtils.isNotEmpty(dataDto.getYear()) && dataDto.getYear().equals(avgDto.getYear())) {
                            BigDecimal cybData = new BigDecimal(avgDto.getCurrValAvg());
                            dataDto.setCybData(cybData);
                            flag = false;
                        }
                    }
                    if(flag){
                        dataDto.setCybData(new BigDecimal("0"));
                    }
                }else{
                    dataDto.setCybData(new BigDecimal("0"));
                }

            }

            //计算科创板复合增长率
            if(KcbAverageList.size() == 3){
                BigDecimal kcbNowValue = new BigDecimal(KcbAverageList.get(0).getCurrValAvg());
                BigDecimal kcbBeforeValue = new BigDecimal(KcbAverageList.get(2).getCurrValAvg());
                Double kcbRate = getGrowthRate(kcbBeforeValue,kcbNowValue);
                rateDto.setKcbData(new BigDecimal(String.valueOf(kcbRate)));
            }else{
                rateDto.setKcbData(new BigDecimal("0"));
            }
            if(CybAverageList.size() == 3){
                BigDecimal cybNowValue = new BigDecimal(CybAverageList.get(0).getCurrValAvg());
                BigDecimal cybBeforeValue = new BigDecimal(CybAverageList.get(2).getCurrValAvg());
                Double cybRate = getGrowthRate(cybBeforeValue,cybNowValue);
                rateDto.setCybData(new BigDecimal(String.valueOf(cybRate)));
            }else{
                rateDto.setCybData(new BigDecimal("0"));
            }

        }
    }

    private double getGrowthRate(BigDecimal beforeValue,BigDecimal nowValue) {
        if (null != beforeValue && null != nowValue) {
            double param = nowValue.divide(beforeValue, 4, BigDecimal.ROUND_HALF_UP).doubleValue();
            double rate = 0;
            DecimalFormat df = new DecimalFormat("#.0000");
            if(param <0){
                if(nowValue.compareTo(BigDecimal.ZERO) == -1){
                    param = param * -1;
                    rate = Math.pow(param, 1.0 / 3);
                    Double rateStr = Double.valueOf(df.format(rate));
                    rate = new BigDecimal((rateStr - 1D) * 100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                }else if(nowValue.compareTo(BigDecimal.ZERO) == 1){
                    param = param * -1;
                    rate = Math.pow(param, 1.0 / 3);
                    rate = rate * -1;
                    Double rateStr = Double.valueOf(df.format(rate));
                    rate = new BigDecimal(( 1D - rateStr) * 100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                }
            }else{
                rate = Math.pow(param, 1.0 / 3);
                Double rateStr = Double.valueOf(df.format(rate));
                rate = new BigDecimal((rateStr - 1D) * 100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            }
            return rate;
        } else {
            return 0;
        }
    }

    /**
     * 查询并规范财务数据 平均值
     */
    private void insertIndexAbgData(List<IpoFinanceH5Dto> dataList, String id) {
        if (CollectionUtils.isNotEmpty(dataList)) {
            for (IpoFinanceH5Dto dataDto : dataList) {
                if ("加权平均净资产收益率".equals(dataDto.getName())) {
                    IpoH5DetailDto avgParam = new IpoH5DetailDto();
                    avgParam.setBid(id);
                    avgParam.setColumnComment("029");
                    avgParam.setPlateType("0");
                    List<IpoH5DetailDto> KcbAverageList = ipoInterfaceService.ipoAvg(avgParam);
                    if(CollectionUtils.isNotEmpty(KcbAverageList)){
                        dataDto.setKcbData(new BigDecimal(KcbAverageList.get(0).getCurrValAvg()));
                        dataDto.setOrgCount(KcbAverageList.get(0).getOrgCount());
                    }else{
                        dataDto.setKcbData(new BigDecimal("0"));
                    }
                    avgParam.setPlateType("1");
                    List<IpoH5DetailDto> CybAverageList = ipoInterfaceService.ipoAvg(avgParam);
                    if(CollectionUtils.isNotEmpty(CybAverageList)){
                        dataDto.setCybData(new BigDecimal(CybAverageList.get(0).getCurrValAvg()));
                    }else{
                        dataDto.setCybData(new BigDecimal("0"));
                    }
                }else if("资产负债率".equals(dataDto.getName())){
                    IpoH5DetailDto avgParam = new IpoH5DetailDto();
                    avgParam.setBid(id);
                    avgParam.setColumnComment("157");
                    avgParam.setPlateType("0");
                    List<IpoH5DetailDto> KcbAverageList = ipoInterfaceService.ipoAvg(avgParam);
                    if(CollectionUtils.isNotEmpty(KcbAverageList)){
                        dataDto.setKcbData(new BigDecimal(KcbAverageList.get(0).getCurrValAvg()));
                        dataDto.setOrgCount(KcbAverageList.get(0).getOrgCount());
                    }else{
                        dataDto.setKcbData(new BigDecimal("0"));
                    }
                    avgParam.setPlateType("1");
                    List<IpoH5DetailDto> CybAverageList = ipoInterfaceService.ipoAvg(avgParam);
                    if(CollectionUtils.isNotEmpty(CybAverageList)){
                        dataDto.setCybData(new BigDecimal(CybAverageList.get(0).getCurrValAvg()));
                    }else{
                        dataDto.setCybData(new BigDecimal("0"));
                    }
                }else if("总资产周转率".equals(dataDto.getName())){
                    IpoH5DetailDto avgParam = new IpoH5DetailDto();
                    avgParam.setBid(id);
                    avgParam.setColumnComment("182");
                    avgParam.setPlateType("0");
                    List<IpoH5DetailDto> KcbAverageList = ipoInterfaceService.ipoAvg(avgParam);
                    if(CollectionUtils.isNotEmpty(KcbAverageList)){
                        dataDto.setKcbData(new BigDecimal(KcbAverageList.get(0).getCurrValAvg()));
                        dataDto.setOrgCount(KcbAverageList.get(0).getOrgCount());
                    }else{
                        dataDto.setKcbData(new BigDecimal("0"));
                    }
                    avgParam.setPlateType("1");
                    List<IpoH5DetailDto> CybAverageList = ipoInterfaceService.ipoAvg(avgParam);
                    if(CollectionUtils.isNotEmpty(CybAverageList)){
                        dataDto.setCybData(new BigDecimal(CybAverageList.get(0).getCurrValAvg()));
                    }else{
                        dataDto.setCybData(new BigDecimal("0"));
                    }
                }else if("销售现金比率".equals(dataDto.getName())){
                    IpoH5DetailDto avgParam = new IpoH5DetailDto();
                    avgParam.setBid(id);
                    avgParam.setColumnComment("184");
                    avgParam.setPlateType("0");
                    List<IpoH5DetailDto> KcbAverageList = ipoInterfaceService.ipoAvg(avgParam);
                    if(CollectionUtils.isNotEmpty(KcbAverageList)){
                        dataDto.setKcbData(new BigDecimal(KcbAverageList.get(0).getCurrValAvg()));
                        dataDto.setOrgCount(KcbAverageList.get(0).getOrgCount());
                    }else{
                        dataDto.setKcbData(new BigDecimal("0"));
                    }
                    avgParam.setPlateType("1");
                    List<IpoH5DetailDto> CybAverageList = ipoInterfaceService.ipoAvg(avgParam);
                    if(CollectionUtils.isNotEmpty(CybAverageList)){
                        dataDto.setCybData(new BigDecimal(CybAverageList.get(0).getCurrValAvg()));
                    }else{
                        dataDto.setCybData(new BigDecimal("0"));
                    }
                }else if("基本每股收益".equals(dataDto.getName())){
                    IpoH5DetailDto avgParam = new IpoH5DetailDto();
                    avgParam.setBid(id);
                    avgParam.setColumnComment("062");
                    avgParam.setPlateType("0");
                    List<IpoH5DetailDto> KcbAverageList = ipoInterfaceService.ipoAvg(avgParam);
                    if(CollectionUtils.isNotEmpty(KcbAverageList)){
                        dataDto.setKcbData(new BigDecimal(KcbAverageList.get(0).getCurrValAvg()));
                        dataDto.setOrgCount(KcbAverageList.get(0).getOrgCount());
                    }else{
                        dataDto.setKcbData(new BigDecimal("0"));
                    }
                    avgParam.setPlateType("1");
                    List<IpoH5DetailDto> CybAverageList = ipoInterfaceService.ipoAvg(avgParam);
                    if(CollectionUtils.isNotEmpty(CybAverageList)){
                        dataDto.setCybData(new BigDecimal(CybAverageList.get(0).getCurrValAvg()));
                    }else{
                        dataDto.setCybData(new BigDecimal("0"));
                    }
                }else if("净利润增长率".equals(dataDto.getName())){
                    IpoH5DetailDto avgParam = new IpoH5DetailDto();
                    avgParam.setBid(id);
                    avgParam.setColumnComment("056");
                    avgParam.setPlateType("0");
                    List<IpoH5DetailDto> KcbAverageList = ipoInterfaceService.ipoAvg(avgParam);
                    avgParam.setPlateType("1");
                    List<IpoH5DetailDto> CybAverageList = ipoInterfaceService.ipoAvg(avgParam);
                    BigDecimal kcbGrowthRate = BigDecimal.ZERO;
                    if(null != KcbAverageList && KcbAverageList.size()>1){
                        if (!(null == KcbAverageList.get(0).getCurrValAvg() || "0".equals(KcbAverageList.get(0).getCurrValAvg())
                                || null == KcbAverageList.get(1).getCurrValAvg() || "0".equals(KcbAverageList.get(1).getCurrValAvg()))) {
                            kcbGrowthRate = new BigDecimal(KcbAverageList.get(0).getCurrValAvg()).
                                    divide(new BigDecimal(KcbAverageList.get(1).getCurrValAvg()), 4, BigDecimal.ROUND_HALF_UP).
                                    subtract(new BigDecimal("1")).multiply(new BigDecimal("100"));
                        }
                    }
                    BigDecimal cybGrowthRate = BigDecimal.ZERO;
                    if(null != CybAverageList && CybAverageList.size()>1) {
                        if (!(null == CybAverageList.get(0).getCurrValAvg() || "0".equals(CybAverageList.get(0).getCurrValAvg())
                                || null == CybAverageList.get(1).getCurrValAvg() || "0".equals(CybAverageList.get(1).getCurrValAvg()))) {
                            cybGrowthRate = new BigDecimal(CybAverageList.get(0).getCurrValAvg()).
                                    divide(new BigDecimal(CybAverageList.get(1).getCurrValAvg()), 4, BigDecimal.ROUND_HALF_UP).
                                    subtract(new BigDecimal("1")).multiply(new BigDecimal("100"));
                        }
                    }
                    dataDto.setKcbData(kcbGrowthRate);
                    dataDto.setCybData(cybGrowthRate);
                }
            }
        }
    }

    /**
     * 查询并规范财务数据 平均值
     */
    private void insertDebtAbgData(List<IpoH5FinanceListDto> dataList, String id) {
        if (CollectionUtils.isNotEmpty(dataList)) {
            BigDecimal beforeYearKcb = BigDecimal.ZERO;
            BigDecimal beforeYearCyb = BigDecimal.ZERO;
            BigDecimal lastYearKcb = BigDecimal.ZERO;
            BigDecimal lastYearCyb = BigDecimal.ZERO;
            BigDecimal todayYearKcb = BigDecimal.ZERO;
            BigDecimal todayYearCyb = BigDecimal.ZERO;
            for (IpoH5FinanceListDto itemDto : dataList) {
                if ("流动资产".equals(itemDto.getName())) {
                    transDebtData(id, "037", itemDto);
                    if(null != itemDto.getBeforeYear()){
                        if(null != itemDto.getBeforeYear().getKcbData()){
                            beforeYearKcb = beforeYearKcb.add(itemDto.getBeforeYear().getKcbData());
                        }
                        if(null != itemDto.getBeforeYear().getCybData()){
                            beforeYearCyb = beforeYearCyb.add(itemDto.getBeforeYear().getCybData());
                        }
                    }
                    if(null != itemDto.getLastYear()){
                        if(null != itemDto.getLastYear().getKcbData()){
                            lastYearKcb = lastYearKcb.add(itemDto.getLastYear().getKcbData());
                        }
                        if(null != itemDto.getLastYear().getCybData()){
                            lastYearCyb = lastYearCyb.add(itemDto.getLastYear().getCybData());
                        }
                    }
                    if(null != itemDto.getTodayYear()){
                        if(null != itemDto.getTodayYear().getKcbData()){
                            todayYearKcb = todayYearKcb.add(itemDto.getTodayYear().getKcbData());
                        }
                        if(null != itemDto.getTodayYear().getCybData()){
                            todayYearCyb = todayYearCyb.add(itemDto.getTodayYear().getCybData());
                        }
                    }

                } else if ("非流动资产".equals(itemDto.getName())) {
                    transDebtData(id, "058", itemDto);
                    if(null != itemDto.getBeforeYear()){
                        if(null != itemDto.getBeforeYear().getKcbData()){
                            beforeYearKcb = beforeYearKcb.add(itemDto.getBeforeYear().getKcbData());
                        }
                        if(null != itemDto.getBeforeYear().getCybData()){
                            beforeYearCyb = beforeYearCyb.add(itemDto.getBeforeYear().getCybData());
                        }
                    }
                    if(null != itemDto.getLastYear()){
                        if(null != itemDto.getLastYear().getKcbData()){
                            lastYearKcb = lastYearKcb.add(itemDto.getLastYear().getKcbData());
                        }
                        if(null != itemDto.getLastYear().getCybData()){
                            lastYearCyb = lastYearCyb.add(itemDto.getLastYear().getCybData());
                        }
                    }
                    if(null != itemDto.getTodayYear()){
                        if(null != itemDto.getTodayYear().getKcbData()){
                            todayYearKcb = todayYearKcb.add(itemDto.getTodayYear().getKcbData());
                        }
                        if(null != itemDto.getTodayYear().getCybData()){
                            todayYearCyb = todayYearCyb.add(itemDto.getTodayYear().getCybData());
                        }
                    }

                } else if ("流动负债".equals(itemDto.getName())) {
                    transDebtData(id, "089", itemDto);
                    if(null != itemDto.getBeforeYear()){
                        if(null != itemDto.getBeforeYear().getKcbData()){
                            beforeYearKcb = beforeYearKcb.subtract(itemDto.getBeforeYear().getKcbData());
                        }
                        if(null != itemDto.getBeforeYear().getCybData()){
                            beforeYearCyb = beforeYearCyb.subtract(itemDto.getBeforeYear().getCybData());
                        }
                    }
                    if(null != itemDto.getLastYear()){
                        if(null != itemDto.getLastYear().getKcbData()){
                            lastYearKcb = lastYearKcb.subtract(itemDto.getLastYear().getKcbData());
                        }
                        if(null != itemDto.getLastYear().getCybData()){
                            lastYearCyb = lastYearCyb.subtract(itemDto.getLastYear().getCybData());
                        }
                    }
                    if(null != itemDto.getTodayYear()){
                        if(null != itemDto.getBeforeYear().getKcbData()){
                            todayYearKcb = todayYearKcb.subtract(itemDto.getTodayYear().getKcbData());
                        }
                        if(null != itemDto.getBeforeYear().getCybData()){
                            todayYearCyb = todayYearCyb.subtract(itemDto.getTodayYear().getCybData());
                        }
                    }
                } else if ("非流动负债".equals(itemDto.getName())) {
                    transDebtData(id, "100", itemDto);
                    if(null != itemDto.getBeforeYear()){
                        if(null != itemDto.getBeforeYear().getKcbData()){
                            beforeYearKcb = beforeYearKcb.subtract(itemDto.getBeforeYear().getKcbData());
                        }
                        if(null != itemDto.getBeforeYear().getCybData()){
                            beforeYearCyb = beforeYearCyb.subtract(itemDto.getBeforeYear().getCybData());
                        }
                    }
                    if(null != itemDto.getLastYear()){
                        if(null != itemDto.getLastYear().getKcbData()){
                            lastYearKcb = lastYearKcb.subtract(itemDto.getLastYear().getKcbData());
                        }
                        if(null != itemDto.getLastYear().getCybData()){
                            lastYearCyb = lastYearCyb.subtract(itemDto.getLastYear().getCybData());
                        }
                    }
                    if(null != itemDto.getTodayYear()){
                        if(null != itemDto.getBeforeYear().getKcbData()){
                            todayYearKcb = todayYearKcb.subtract(itemDto.getTodayYear().getKcbData());
                        }
                        if(null != itemDto.getBeforeYear().getCybData()){
                            todayYearCyb = todayYearCyb.subtract(itemDto.getTodayYear().getCybData());
                        }
                    }
                }
            }
            for (IpoH5FinanceListDto itemDto : dataList) {
                if ("所有者权益合计".equals(itemDto.getName())) {
                    itemDto.getBeforeYear().setKcbData(beforeYearKcb);
                    itemDto.getBeforeYear().setCybData(beforeYearCyb);
                    itemDto.getLastYear().setKcbData(lastYearKcb);
                    itemDto.getLastYear().setCybData(lastYearCyb);
                    itemDto.getTodayYear().setKcbData(todayYearKcb);
                    itemDto.getTodayYear().setCybData(todayYearCyb);
                }
            }
        }
    }

    /**
     * 查询并规范财务数据 平均值
     */
    private void transDebtData(String id, String columnComment, IpoH5FinanceListDto itemDto) {
        IpoH5DetailDto avgParam = new IpoH5DetailDto();
        avgParam.setBid(id);
        avgParam.setColumnComment(columnComment);
        avgParam.setPlateType("0");
        List<IpoH5DetailDto> kcbAverageList = ipoInterfaceService.ipoAvg(avgParam);
        if(CollectionUtils.isNotEmpty(kcbAverageList)){
            for (int i = 0; i < kcbAverageList.size(); i++) {
                if (i == 0 && null != kcbAverageList.get(0)) {
                    itemDto.getTodayYear().setKcbData(new BigDecimal(kcbAverageList.get(0).getCurrValAvg()));
                    itemDto.setOrgCount(kcbAverageList.get(0).getOrgCount());
                } else if (i == 1 && null != kcbAverageList.get(1)) {
                    itemDto.getLastYear().setKcbData(new BigDecimal(kcbAverageList.get(1).getCurrValAvg()));
                } else if (i == 2 && null != kcbAverageList.get(2)) {
                    itemDto.getBeforeYear().setKcbData(new BigDecimal(kcbAverageList.get(2).getCurrValAvg()));
                }
            }
        }else{
            itemDto.getBeforeYear().setKcbData(new BigDecimal("0"));
            itemDto.getLastYear().setKcbData(new BigDecimal("0"));
            itemDto.getTodayYear().setKcbData(new BigDecimal("0"));
        }
        avgParam.setPlateType("1");
        List<IpoH5DetailDto> CybAverageList = ipoInterfaceService.ipoAvg(avgParam);
        if(CollectionUtils.isNotEmpty(CybAverageList)){
            for (int i = 0; i < CybAverageList.size(); i++) {
                if (i == 0 && null != CybAverageList.get(0)) {
                    itemDto.getTodayYear().setCybData(new BigDecimal(CybAverageList.get(0).getCurrValAvg()));
                } else if (i == 1 && null != CybAverageList.get(1)) {
                    itemDto.getLastYear().setCybData(new BigDecimal(CybAverageList.get(1).getCurrValAvg()));
                } else if (i == 2 && null != CybAverageList.get(2)) {
                    itemDto.getBeforeYear().setCybData(new BigDecimal(CybAverageList.get(2).getCurrValAvg()));
                }
            }
        }else{
            itemDto.getBeforeYear().setCybData(new BigDecimal("0"));
            itemDto.getLastYear().setCybData(new BigDecimal("0"));
            itemDto.getTodayYear().setCybData(new BigDecimal("0"));
        }

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
     * @param  industryCsrc（所属行业）   codeOrName（简称代码）  iecResult（审核状态）
     *                      belongsBureau(注册地)
     * @author yangj
     */
    @RequestMapping(value = "/getIpoCaseList", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getIpoCaseList(String ipoPlate,String industryCsrc,String codeOrName,String iecResult,String belongsBureau ) {
        JsonResponse jsonResponse = new JsonResponse();
        IpoCaseListBo ipoCaseListBo = new IpoCaseListBo();
        if (StringUtils.isNotEmpty(ipoPlate)){
            ipoCaseListBo.setIpoPlate(ipoPlate);
        }
        if (StringUtils.isNotEmpty(belongsBureau)) {
            ipoCaseListBo.setBelongsBureau(belongsBureau);
        }
        if (StringUtils.isNotEmpty(codeOrName)) {
            ipoCaseListBo.setCodeOrName(codeOrName);
        }
        if (StringUtils.isNotEmpty(iecResult)) {
            ipoCaseListBo.setIecResult(iecResult);
        }
        if (StringUtils.isNotEmpty(industryCsrc)) {
            ipoCaseListBo.setIndustryCsrc(industryCsrc);
        }
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
        jsonResponse.setResult(result);
        return jsonResponse;
    }
    /**
     * 获取科创板IPO数据
     *
     * @param  （所属行业）   codeOrName（简称代码）  iecResult（审核状态）
     *                      belongsBureau(注册地)
     * @author yangj
     */
    @RequestMapping(value = "/queryIpoCase", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse queryIpoCase(String companyName,String limit) {
        JsonResponse jsonResponse = new JsonResponse();
        Map<String,Object> map = new HashMap<>();
        if (StringUtils.isNotEmpty(companyName)) {
            map.put("companyName",companyName);
        }
        if (StringUtils.isNotEmpty(limit)) {
            map.put("limit",limit);
        }
        map.put("ipoPlate","069001001006");
        List<IpoCaseListVo> ipoCaseListVos = ipoInterfaceService.queryIpoCase(map);
        jsonResponse.setResult(ipoCaseListVos);
        return jsonResponse;
    }
    /**
     * 随机获取科创板已注册IPO数据
     *
     * @param
     * @author yangj
     */
    @RequestMapping(value = "/queryAllMatchIpoCase", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse queryAllMatchIpoCase() {
        JsonResponse jsonResponse = new JsonResponse();
//        List<IpoCaseListVo> result = new ArrayList<>();
        List<IpoCaseListVo> ipoCaseListVos = ipoInterfaceService.queryAllMatchIpoCase();
        Set<IpoCaseListVo> resultSet = new HashSet<>();
        if (ipoCaseListVos.size() <=3){
            jsonResponse.setResult(ipoCaseListVos);
        }else{
            Random random = new Random();
            while(resultSet.size() < 3){
                resultSet.add(ipoCaseListVos.get(random.nextInt(ipoCaseListVos.size())));
            }
            jsonResponse.setResult(resultSet);
        }
        return jsonResponse;
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
     * dxy 专利情况
     *
     * @param id 案例id
     */
    @RequestMapping(value = "/getPatentSituation", method = RequestMethod.GET)
    public IpoH5Dto getPatentSituation(@RequestParam("id") String id, String ipoPlate) {
        return ipoInterfaceService.getPatentSituation(id,ipoPlate);
    }

    /**
     * dxy 拆分上市情况
     * @param id 案例id
     * @return 数组
     */
    @RequestMapping(value = "/spliteData", method = RequestMethod.GET)
    public List<IpoSplitDto> spliteData(@RequestParam("id") String id){
        return ipoInterfaceService.getSpliteData(id);
    }

    /**
     * 发行情况
     * dxy
     * @param id 案例id
     * @return 实体类
     */
    @RequestMapping(value = "/issueData", method = RequestMethod.GET)
    public IpoH5IssueDataDto issueData(@RequestParam("id") String id) {
        return ipoInterfaceService.getIssueData(id);
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
     * lixinwei 案例是否通过
     *
     * @param id 案例主键
     */
    @RequestMapping(value = "/getHeadData", method = RequestMethod.GET)
    public HeadDataVo getHeadData(String id) {
        return companyOverviewService.getHeadData(id);
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
    public Map<String,List<IpoFeedbackDto>> selectNewFeedbackList(String id) {
      Map<String,List<IpoFeedbackDto>> map = new HashMap<>();
      List<IpoFeedbackDto> letterList = new ArrayList<>();
      Map<String, List<IpoFeedbackDto>> ipoFeedbackMap= ipoFeedbackService.selectNewFeedbackList(id);
      // 首先判断是不是核准制. 原逻辑是 除了 上交所科创板 都是核准制, 后期变更 深交所创业板为 注册制+核准制
      if (ipoFeedbackMap.containsKey("ratifyList")){//核准制
          map.put("ratifyList",ipoFeedbackMap.get("ratifyList"));
      }
        if (ipoFeedbackMap.containsKey("registerList")){
            map.put("registerList",ipoFeedbackMap.get("registerList"));
        }
//      List<IpoFeedbackDto> letterList = ipoFeedbackService.selectNewFeedbackList(id);

        for(IpoFeedbackDto dto:letterList){
            List<IpoFeedbackDto> questionList = ipoFeedbackService.selectNewQuestionList(dto.getLetterId(),"","","");
            if(CollectionUtils.isNotEmpty(questionList) && questionList.size() > 0){
                dto.setQuestionLabelList(questionList.get(0).getQuestionLabelList());
            }
        }
        return map;
    }

    /**
     * lixinwei 上市委关注问题
     *
     * @param id 案例主键
     */
    @RequestMapping(value = "/selectNewExamineList", method = RequestMethod.GET)
    public List<IpoFeedbackDto> selectNewExamineList(String id) {
        List<IpoFeedbackDto> letterList = ipoExamineService.selectNewExamineList(id);
        for(IpoFeedbackDto dto:letterList){
            List<IpoFeedbackDto> questionList = ipoFeedbackService.selectNewQuestionList(dto.getLetterId(),"","","");
            if(CollectionUtils.isNotEmpty(questionList) && questionList.size() > 0){
                dto.setQuestionLabelList(questionList.get(0).getQuestionLabelList());
            }
        }
        return letterList;
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
            @RequestParam(value = "validFlag", required = false) String validFlag, @RequestParam("ipoPlate") String ipoPlate) {
        Map<String, List<IntermediaryOrgDto>> list = companyOverviewService.getIntermediaryOrgData(id, validFlag);
        if (list != null){
            List<IntermediaryOrgDto> mainList = list.get("mainList");
            if (mainList != null){
                for (int i=0;i<mainList.size();i++){
                    mainList.get(i).setBid(id);

                    //查询改中介机构的业务量和过会数量
                    if (mainList.get(i).getIntermediaryType().equals("1")){
                        mainList.get(i).setIpoPlate(ipoPlate);
                        IntermediaryOrgDto dto = ipoInterfaceService.queryOrgMarketShare(mainList.get(i));
                        IntermediaryOrgDto dto1  = ipoInterfaceService.queryQrgMarketRank(mainList.get(i));
                        if (dto != null){
                            mainList.get(i).setBusinessVolume(dto.getBusinessVolume());
                            mainList.get(i).setPassing(dto.getPassing());
                            mainList.get(i).setPassingWait(dto.getPassingWait());
                            mainList.get(i).setOrgYear(dto.getOrgYear());
                            if (dto1 != null){
                                mainList.get(i).setBusinessVolumeRank(dto1.getBusinessVolumeRank());
                                mainList.get(i).setPassingRank(dto1.getPassingRank());
                                mainList.get(i).setOrgYear(dto1.getOrgYear());
                            }
                        }
                    }
                    if (mainList.get(i).getIntermediaryType().equals("3")){
                        mainList.get(i).setIpoPlate(ipoPlate);
                        IntermediaryOrgDto dto = ipoInterfaceService.queryOrgMarketShare(mainList.get(i));
                        IntermediaryOrgDto dto1  = ipoInterfaceService.queryQrgMarketRank(mainList.get(i));
                        if (dto != null){
                            mainList.get(i).setBusinessVolume(dto.getBusinessVolume());
                            mainList.get(i).setPassing(dto.getPassing());
                            mainList.get(i).setPassingWait(dto.getPassingWait());
                            mainList.get(i).setOrgYear(dto.getOrgYear());
                            if (dto1 != null){
                                mainList.get(i).setBusinessVolumeRank(dto1.getBusinessVolumeRank());
                                mainList.get(i).setPassingRank(dto1.getPassingRank());
                                mainList.get(i).setOrgYear(dto1.getOrgYear());
                            }
                        }
                    }
                    if (mainList.get(i).getIntermediaryType().equals("4")){
                        mainList.get(i).setIpoPlate(ipoPlate);
                        IntermediaryOrgDto dto = ipoInterfaceService.queryOrgMarketShare(mainList.get(i));
                        IntermediaryOrgDto dto1  = ipoInterfaceService.queryQrgMarketRank(mainList.get(i));
                        if (dto != null){
                            mainList.get(i).setBusinessVolume(dto.getBusinessVolume());
                            mainList.get(i).setPassing(dto.getPassing());
                            mainList.get(i).setPassingWait(dto.getPassingWait());
                            mainList.get(i).setOrgYear(dto.getOrgYear());
                            if (dto1 != null){
                                mainList.get(i).setBusinessVolumeRank(dto1.getBusinessVolumeRank());
                                mainList.get(i).setPassingRank(dto1.getPassingRank());
                                mainList.get(i).setOrgYear(dto1.getOrgYear());
                            }
                        }
                    }
                }
            }
        }
        return list;
    }

    /**
     * lixinwei 可能还想看
     */
    @RequestMapping(value = "/otherIpoCase", method = RequestMethod.GET)
    public List<IpoCaseListVo> otherIpoCase(IpoCaseIndexDto ipoCaseIndexDto) {
        List<IpoCaseListVo> otherIpo = new ArrayList<>();
        otherIpo = ipoInterfaceService.otherIpoCase(ipoCaseIndexDto);
        if(otherIpo != null){
            if (otherIpo.size() == 0){
                otherIpo = ipoInterfaceService.otherIpoCaseNoIndustry(ipoCaseIndexDto);
            }
        }
        return otherIpo;
    }

    @Autowired
    private RestClient restClient;

    /**
     * @return 调用微信接口获取信息
     * @author yangj
     */
    @RequestMapping(value = "/getWXUserInfo")
    @ResponseBody
    public JsonResponse getWXUserInfo(String code) {
        logger.info("标识code"+code);
        JsonResponse jsonResponse = new JsonResponse();
        String getOpenid = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + wechatAppid + "&secret=" + wechatSecret + "&code=" +
                code + "&grant_type=authorization_code";
        ParameterizedTypeReference<String> responseType = new ParameterizedTypeReference<String>() {
        };
        String openStr = restClient.get(getOpenid, responseType, Maps.newHashMap());
        Map<String, String> openMap = JsonUtil.fromJson(openStr, new ParameterizedTypeReference<Map<String, String>>() {
        });
        String errcode = openMap.get("errcode");
        logger.info(openMap.toString());
        if (StringUtils.isNotEmpty(errcode)){
            HashMap<Object, Object> map = new HashMap<>();
            map.put("status","failed");
            jsonResponse.setResult(map);
            return jsonResponse;
        }
        String access_token = openMap.get("access_token");
//        String refresh_token = openMap.get("refresh_token");
        String openid = openMap.get("openid");
//        根据accesstoken 和 openid 获取用户信息
        String getUserInfo = "https://api.weixin.qq.com/sns/userinfo?access_token=" + access_token + "&openid=" + openid + "&lang=zh_CN";
        String userInfoStr = restClient.get(getUserInfo, responseType, Maps.newHashMap());
        Map<String, Object> userInfoMap = JsonUtil.fromJson(userInfoStr, new ParameterizedTypeReference<Map<String, Object>>() {
        });
        logger.info("返回的用户数据:"+userInfoMap.toString());
//        后续可能要把这个做成 记录的 每个用户进来就记录一下 统计点击数
        userInfoMap.put("access_token", "test");
        userInfoMap.put("openid", openid);
        userInfoMap.put("status","success");
        ipoInterfaceService.saveUserInfo(userInfoMap);
        jsonResponse.setResult(userInfoMap);
        return jsonResponse;
    }

    /**
     * @return
     * @author yangj  提交评论
     */
    @RequestMapping(value = "/submitReplay")
    @ResponseBody
    public JsonResponse submitReplay(String headPortrait,String username,String commentText,String openid,String unionid,String caseid ) {
        JsonResponse jsonResponse = new JsonResponse();
        Map<String,Object> map = new HashMap();
        //    敏感词判断
        SensitiveWord sw = new SensitiveWord("CensorWords.txt");
        sw.InitializationWork();
        boolean canNotSubmit = sw.filterInfo(commentText);
        if (canNotSubmit){
            map.put("status","failed");
            jsonResponse.setResult(map);
            return jsonResponse;
        }
        map.put("headPortrait",headPortrait);
        map.put("username",username);
        map.put("commentText",commentText);
        map.put("openid",openid);
        map.put("unionid",unionid);
        map.put("caseid",caseid);
        map.put("commentTime", new Date());
        ipoInterfaceService.submitReplay(map);
        logger.info("提交评论：" + map);
        Map<String, Object> result = new HashMap();
        result.put("status", "success");
        jsonResponse.setResult(result);
        return jsonResponse;
    }

    /**
     * 点赞
     *
     * @return
     * @author yangj
     */
    @RequestMapping(value = "/fabulousYes")
    @ResponseBody
    public JsonResponse fabulousYes(String openid,String unionid,String caseid,String isLike) {
        Map<String ,Object> param = new HashMap();
        param.put("openid",openid);
//        param.put("unionid",unionid);
        param.put("unionid",unionid);
        param.put("caseid",caseid);
        param.put("isLike",isLike);
        logger.info(param.toString());
        JsonResponse jsonResponse = new JsonResponse();
        ipoInterfaceService.fabulousYes(param);
        int fabulous = ipoInterfaceService.fabulousCount(param);
//        int fabulousCount = 1;
//        if (fabulous >= 1000){
//            fabulousCount = fabulous / 10;
//        }
        Map<String, Object> result = new HashedMap();
//        result.put("fabulous",fabulousCount+"K");
        result.put("status","success");
        jsonResponse.setResult(result);
        return jsonResponse;
    }
    /**
     * 收藏
     *
     * @return
     * @author yangj
     */
    @RequestMapping(value = "/collectionYes")
    @ResponseBody
    public JsonResponse collectionYes(String openid,String unionid,String caseid,String isLike) {
        Map<String ,Object> param = new HashMap();
        param.put("openid",openid);
//        param.put("unionid",unionid);
        param.put("unionid",unionid);
        param.put("caseid",caseid);
        param.put("isLike",isLike);
        JsonResponse jsonResponse = new JsonResponse();
        ipoInterfaceService.collectionYes(param);
        logger.info(param.toString());
//        int fabulousCount = 1;
//        if (fabulous >= 1000){
//            fabulousCount = fabulous / 10;
//        }
        Map<String, Object> result = new HashedMap();
//        result.put("fabulous",fabulousCount+"K");
        result.put("status","success");
        jsonResponse.setResult(result);
        return jsonResponse;
    }

    /**
     * 获取评论列表和其他信息
     *
     * @return
     * @author yangj
     */
    @RequestMapping(value = "/getReplay")
    @ResponseBody
    public JsonResponse getReplay(String openid,String unionid,String caseid,Long startPage,Long endPage) {
        Map<String ,Object> param = new HashMap();
        if(!StringUtils.isNotEmpty(openid) || openid == "null" ){
            openid = unionid;
        }
        param.put("openid",openid);
        param.put("unionid",unionid);
        param.put("caseid",caseid);
        param.put("startPage",startPage);
        param.put("endPage",endPage);
        logger.info("参数"+param.toString());
        JsonResponse jsonResponse = new JsonResponse();
//      查询评论
        List<Map<String, Object>> commentList = ipoInterfaceService.getCommentList(param);
        List<Map<String, Object>> selectedList = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:mm");
        SimpleDateFormat allFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (commentList != null && commentList.size() > 0) {
            for (Map<String, Object> map : commentList) {
//            前端展示名称
                String comment_time =  format.format(map.get("comment_time"));
                map.put("headPortrait", map.get("avatar"));
                map.put("username", map.get("comment_from_user"));
                map.put("commentText", map.get("comment_content"));
                map.put("commentTime", comment_time);
                map.put("comment_time",allFormat.format(map.get("comment_time")));
//            如果是精选评论
                if ("1".equals(map.get("is_selected_comment"))) {
                    selectedList.add(map);
                }
            }
        }else{
            commentList = new ArrayList<>();
        }
//        评论数
        int commentNum = ipoInterfaceService.replayCount(param);
//        点赞数
        int fabulous = ipoInterfaceService.fabulousCount(param);
        int collections =  ipoInterfaceService.collectionCount(param);
//        是否点赞
        boolean fabulousYes = ipoInterfaceService.isFabulousYes(param);
        logger.info("是否点赞"+fabulousYes);
//        是否收藏
        boolean collectionYes = ipoInterfaceService.isCollectionYes(param);
        Map<String, Object> result = new HashMap();
        result.put("commentList", commentList);
        result.put("selectedList", selectedList);
        result.put("commentNum", commentNum);
        result.put("fabulous", fabulous);
        result.put("collections", collections);
        result.put("fabulousYes", fabulousYes);
        result.put("collectionYes", collectionYes);
        jsonResponse.setResult(result);
        logger.info("获取评论"+result);
        return jsonResponse;
    }
    /**
     * 分页获取评论列表
     *
     * @return
     * @author yangj
     */
    @RequestMapping(value = "/getOnlyReplay")
    @ResponseBody
    public JsonResponse getOnlyReplay(String openid,String unionid,String caseid,Long startPage,Long endPage,String maxCommentTime) {
        Map<String ,Object> param = new HashMap();
        param.put("openid",openid);
        param.put("unionid",unionid);
        param.put("caseid",caseid);
        param.put("startPage",startPage);
        param.put("endPage",endPage);
        param.put("maxCommentTime",maxCommentTime);
        JsonResponse jsonResponse = new JsonResponse();
//      查询评论
        List<Map<String, Object>> commentList = ipoInterfaceService.getOnlyCommentList(param);
        List<Map<String, Object>> selectedList = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:mm");
        if (commentList != null && commentList.size() > 0) {
            for (Map<String, Object> map : commentList) {
//            前端展示名称
                String comment_time = format.format(map.get("comment_time"));
                map.put("headPortrait", map.get("avatar"));
                map.put("username", map.get("comment_from_user"));
                map.put("commentText", map.get("comment_content"));
                map.put("commentTime", comment_time);
//            如果是精选评论
                if ("1".equals(map.get("is_selected_comment"))) {
                    selectedList.add(map);
                }
            }
        }else{
            commentList = new ArrayList<>();
        }
        Map<String, Object> result = new HashMap();
        result.put("commentList", commentList);
        result.put("selectedList", selectedList);
        jsonResponse.setResult(result);
        logger.info("获取评论"+result);
        return jsonResponse;
    }
    @Autowired
    private WXUtils wxUtils;

    /**
     * 输出二维码
     * @param response
     * @param id
     * @param companyName
     * @throws IOException
     */
    @RequestMapping("/getQrCode")
    public void getAccessToken(HttpServletResponse response, String id, String companyName) throws IOException {
        logger.info("获取二维码：公司id"+id+" 公司简称："+companyName);
        byte[] qrCode = wxUtils.getMiNiQr(id,companyName);
        response.getOutputStream().write(qrCode);
    }



}

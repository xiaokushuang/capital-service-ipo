package com.stock.capital.enterprise.ipoInterfaceH5.controller;

import com.google.common.base.Throwables;
import com.google.common.collect.Maps;
import com.stock.capital.enterprise.ipoCase.controller.IpoCaseOverviewController;
import com.stock.capital.enterprise.ipoCase.controller.IpoFeedbackController;
import com.stock.capital.enterprise.ipoCase.controller.IpoProcessController;
import com.stock.capital.enterprise.ipoCase.dto.CompanyOverviewVo;
import com.stock.capital.enterprise.ipoCase.dto.IntermediaryOrgDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoCaseIndexDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoCaseListBo;
import com.stock.capital.enterprise.ipoCase.dto.IpoFeedbackDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoFinanceDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoPersonInfoDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoValuationDto;
import com.stock.capital.enterprise.ipoCase.dto.MainIncomeVo;
import com.stock.capital.enterprise.ipoCase.dto.OtherMarketInfoDto;
import com.stock.capital.enterprise.ipoCase.dto.SupplierCustomerMainDto;
import com.stock.capital.enterprise.ipoCase.dto.TreeTypeProgressDto;
import com.stock.capital.enterprise.ipoCase.service.*;
import com.stock.capital.enterprise.ipoInterfaceH5.service.IpoInterfaceService;
import com.stock.core.controller.BaseController;
import com.stock.core.dto.JsonResponse;
import com.stock.core.dto.QueryInfo;

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

import java.util.List;
import java.util.Map;

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
     *  IpoH5接口汇总
     * @param id 案例主键
     * @return
     */
    @RequestMapping(value = "ipoCaseH5")
    @ResponseBody
    public Map<String,Object> ipoCaseH5(String id) {
        logger.info("#######【ipoCaseH5接口数据查询开始###########");
        Map<String,Object> resultMap = Maps.newHashMap();
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
            resultMap.put("processList",processList);
        }catch  (Exception e){
            resultMap.put("processList","0");
            logger.error("ipoCaseH5获取上市进展发生错误:{}", Throwables.getStackTraceAsString(e));
        }
        //最新估值
        try {
            List<IpoValuationDto> valuationData = valuationData(id);
            resultMap.put("valuationData",valuationData);
        }catch  (Exception e){
            resultMap.put("valuationData","0");
            logger.error("ipoCaseH5获取最新估值发生错误:{}", Throwables.getStackTraceAsString(e));
        }
        IpoCaseIndexDto ipoCaseIndex = new IpoCaseIndexDto();
        //上市条件、公司信息
        try {
            CompanyOverviewVo caseDetaild = caseDetail(id);
            if (caseDetaild != null){
                ipoCaseIndex.setIndustryCsrc(caseDetaild.getIndustryCsrc());
            }
            resultMap.put("caseDetaild",caseDetaild);
        }catch  (Exception e){
            resultMap.put("caseDetaild","0");
            logger.error("ipoCaseH5获取上市条件、公司信息发生错误:{}", Throwables.getStackTraceAsString(e));
        }
        //主营业务构成
        try {
            MainIncomeVo incomeData = incomeData(id);
            resultMap.put("incomeData",incomeData);
        }catch  (Exception e){
            resultMap.put("incomeData","0");
            logger.error("ipoCaseH5获取主营业务构成发生错误:{}", Throwables.getStackTraceAsString(e));
        }
        //股东信息
        try {
            List<IpoPersonInfoDto> shareHolderData = shareHolderData(id);
            resultMap.put("shareHolderData",shareHolderData);
        }catch  (Exception e){
            resultMap.put("shareHolderData","0");
            logger.error("ipoCaseH5获取股东信息发生错误:{}", Throwables.getStackTraceAsString(e));
        }
        //登录其他资本市场
        try {
            List<OtherMarketInfoDto> marketData = marketData(id);
            resultMap.put("marketData",marketData);
        }catch  (Exception e){
            resultMap.put("marketData","0");
            logger.error("ipoCaseH5获取登录其他资本市场发生错误:{}", Throwables.getStackTraceAsString(e));
        }
        //上交所问询情况
        try {
            List<IpoFeedbackDto> selectNewFeedbackList = selectNewFeedbackList(id);
            resultMap.put("selectNewFeedbackList",selectNewFeedbackList);
        }catch  (Exception e){
            resultMap.put("selectNewFeedbackList","0");
            logger.error("ipoCaseH5获取上交所问询情况发生错误:{}", Throwables.getStackTraceAsString(e));
        }
        //中介结构
        try {
            Map<String, List<IntermediaryOrgDto>> intermediaryOrgData = intermediaryOrgData(id,"");
            resultMap.put("intermediaryOrgData",intermediaryOrgData);
        }catch  (Exception e){
            resultMap.put("intermediaryOrgData","0");
            logger.error("ipoCaseH5获取中介结构发生错误:{}", Throwables.getStackTraceAsString(e));
        }
        //可能还想看
        try {
            List<IpoCaseIndexDto> otherIpoCase = otherIpoCase(ipoCaseIndex);
            resultMap.put("otherIpoCase",otherIpoCase);
        }catch  (Exception e){
            resultMap.put("otherIpoCase","0");
            logger.error("ipoCaseH5获取可能还想看发生错误:{}", Throwables.getStackTraceAsString(e));
        }
        //行业与技术接口
        try {
            JsonResponse<Map> technology = getTechnology(id);
            resultMap.put("technology",technology);
        }catch  (Exception e){
            resultMap.put("technology","0");
            logger.error("ipoCaseH5获取行业与技术发生错误:{}", Throwables.getStackTraceAsString(e));
        }
        //报告期主要供应商及客户情况
        try {
            JsonResponse<Map<String, List<SupplierCustomerMainDto>>> supplierCustomerData = supplierCustomerData(id);
            resultMap.put("supplierCustomerData",supplierCustomerData);
        }catch  (Exception e){
            resultMap.put("supplierCustomerData","0");
            logger.error("ipoCaseH5获取报告期主要供应商及客户情况发生错误:{}", Throwables.getStackTraceAsString(e));
        }
        //发行人财务数据
        try {
            JsonResponse<IpoFinanceDto> financeOverListH5 = selectFinanceOverListH5(id);
            resultMap.put("financeOverListH5",financeOverListH5);
        }catch  (Exception e){
            resultMap.put("financeOverListH5","0");
            logger.error("ipoCaseH5获取发行人财务数据发生错误:{}", Throwables.getStackTraceAsString(e));
        }
        logger.info("#######【ipoCaseH5接口数据查询结束###########");
        return resultMap;

    }

    /**
     * GK 报告期主要供应商及客户情况
     */
    @RequestMapping(value = "/supplierCustomerData", method = RequestMethod.GET)
    public JsonResponse<Map<String, List<SupplierCustomerMainDto>>> supplierCustomerData(
            @RequestParam("id") String id) {
        JsonResponse<Map<String, List<SupplierCustomerMainDto>>> response = new JsonResponse<>();
        response.setResult(companyOverviewService.getSupCusData(id));
        return response;
    }

    /**
     * GK 发行人财务数据
     */
    @RequestMapping(value = "/selectFinanceOverListH5", method = RequestMethod.GET)
    public JsonResponse<IpoFinanceDto> selectFinanceOverListH5(
            @RequestParam("id") String id) {
        JsonResponse<IpoFinanceDto> response = new JsonResponse<>();
        response.setResult(ipoFinanceService.selectFinanceOverListH5(id));
        return response;
    }

    /**
     * 获取下拉框数据
     * @author yangj
     * @return
     */
    @RequestMapping("/querySelectData")
    @ResponseBody
    public Map<String,Object> querySelectData(){
        Map<String,Object> result = ipoInterfaceService.querySelectData();
        return result;
    }

    /**
     * 获取科创板IPO数据
     * @author yangj
     * @param ipoCaseListBo   industryCsrc（所属行业）   codeOrName（简称代码）  iecResult（审核状态）  belongsBureau(注册地)
     * @return
     */
    @RequestMapping(value = "/getIpoCaseList",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getIpoCaseList(@RequestBody IpoCaseListBo ipoCaseListBo){
        QueryInfo<IpoCaseListBo> page = new QueryInfo<>();
//        没有查询条件时 实例化dto
        if (null == ipoCaseListBo){
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
     * dxy
     * 行业与技术接口
     * @param id 案例id
     * @return
     */
    @RequestMapping(value = "/getTechnology", method = RequestMethod.GET)
    public JsonResponse<Map> getTechnology(@RequestParam("id") String id) {
        JsonResponse<Map> response = new JsonResponse<>();
        response.setResult(ipoInterfaceService.getIpoTechnology(id));
        return response;
    }

    /**
     *  lixinwei 上市进展
     * @param id 案例主键
     * @param sortType
     * @return
     */
    @RequestMapping(value = "/selectProcessList", method = RequestMethod.GET)
    public TreeTypeProgressDto selectProcessList(String id) {
        TreeTypeProgressDto resultList = ipoProcessService.selectProcessList(id, "02");
        return resultList;
    }

    /**
     *  lixinwei 最新估值
     * @param id 案例主键
     * @param sortType
     * @return
     */
    @RequestMapping(value = "/valuationData", method = RequestMethod.GET)
    public List<IpoValuationDto> valuationData(String id){
        return companyOverviewService.getVluationData(id);
    }

    /**
     *  lixinwei 上市条件、公司信息
     * @param id 案例主键
     * @param sortType
     * @return
     */
    @RequestMapping(value = "/caseDetail", method = RequestMethod.GET)
    public CompanyOverviewVo caseDetail(String id) {
        return companyOverviewService.getIpoCaseDetail(id);
    }

    /**
     *  lixinwei 主营业务构成
     * @param id 案例主键
     * @param sortType
     * @return
     */
    @RequestMapping(value = "/incomeData", method = RequestMethod.GET)
    public MainIncomeVo incomeData(String id) {
        return companyOverviewService.getIncomeData(id);
    }

    /**
     *  lixinwei 股东信息
     * @param id 案例主键
     * @param sortType
     * @return
     */
    @RequestMapping(value = "/shareHolderData", method = RequestMethod.GET)
    public List<IpoPersonInfoDto> shareHolderData(String id) {
        return companyOverviewService.getShareData(id);
    }

    /**
     *  lixinwei 登录其他资本市场
     * @param id 案例主键
     * @param sortType
     * @return
     */
    @RequestMapping(value = "/marketData", method = RequestMethod.GET)
    public List<OtherMarketInfoDto> marketData(String id) {
        return companyOverviewService.getMarketData(id);
    }

    /**
     *  lixinwei 上交所问询情况
     * @param id 案例主键
     * @param sortType
     * @return
     */
    @RequestMapping(value = "/selectNewFeedbackList", method = RequestMethod.GET)
    public List<IpoFeedbackDto> selectNewFeedbackList(String id) {
        return ipoFeedbackService.selectNewFeedbackList(id);
    }

    /**
     *  lixinwei 中介结构
     * @param id 案例主键
     * @param validFlag 生效失效
     * @return
     */
    @RequestMapping(value = "/intermediaryOrgData", method = RequestMethod.GET)
    public Map<String, List<IntermediaryOrgDto>> intermediaryOrgData(
            @RequestParam("id") String id,
            @RequestParam(value = "validFlag", required = false) String validFlag) {
        return companyOverviewService.getIntermediaryOrgData(id,validFlag);
    }

    /**
     *  lixinwei 可能还想看
     * @param ipoPlate 上市板块
     * @param industryCsrc 证监会行业
     * @return
     */
    @RequestMapping(value = "/otherIpoCase", method = RequestMethod.GET)
    public List<IpoCaseIndexDto> otherIpoCase(IpoCaseIndexDto ipoCaseIndexDto) {
        return ipoInterfaceService.otherIpoCase(ipoCaseIndexDto);
    }

}

package com.stock.capital.enterprise.ipoInterfaceH5.controller;

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
import com.stock.capital.enterprise.ipoCase.service.CompanyOverviewService;
import com.stock.capital.enterprise.ipoCase.service.IpoCaseListService;
import com.stock.capital.enterprise.ipoCase.service.IpoFinanceService;
import com.stock.capital.enterprise.ipoInterfaceH5.service.IpoInterfaceService;
import com.stock.core.controller.BaseController;
import com.stock.core.dto.JsonResponse;
import com.stock.core.dto.QueryInfo;

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
    @RequestMapping(value = "/supplierCustomerData", method = RequestMethod.GET)
    public JsonResponse<IpoFinanceDto> selectFinanceOverListH5(
            @RequestParam("id") String id) {
        JsonResponse<IpoFinanceDto> response = new JsonResponse<>();
        response.setResult(ipoFinanceService.selectFinanceOverListH5(id));
        return response;
    }
    @Autowired
    private IpoCaseListService ipoCaseListService;
    @Autowired
    private IpoProcessController ipoProcessController;
    @Autowired
    private IpoCaseOverviewController ipoCaseOverviewController;
    @Autowired
    private IpoFeedbackController ipoFeedbackController;

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
    public JsonResponse<TreeTypeProgressDto> selectProcessList(String id) {
        return ipoProcessController.selectProcessList(id,"");
    }

    /**
     *  lixinwei 最新估值
     * @param id 案例主键
     * @param sortType
     * @return
     */
    @RequestMapping(value = "/valuationData", method = RequestMethod.GET)
    public JsonResponse<List<IpoValuationDto>> valuationData(String id){
        return ipoCaseOverviewController.valuationData(id);
    }

    /**
     *  lixinwei 上市条件、公司信息
     * @param id 案例主键
     * @param sortType
     * @return
     */
    @RequestMapping(value = "/caseDetail", method = RequestMethod.GET)
    public JsonResponse<CompanyOverviewVo> caseDetail(String id) {
        return ipoCaseOverviewController.caseDetail(id);
    }

    /**
     *  lixinwei 主营业务构成
     * @param id 案例主键
     * @param sortType
     * @return
     */
    @RequestMapping(value = "/incomeData", method = RequestMethod.GET)
    public JsonResponse<MainIncomeVo> incomeData(String id) {
        return ipoCaseOverviewController.incomeData(id);
    }

    /**
     *  lixinwei 主营业务构成
     * @param id 案例主键
     * @param sortType
     * @return
     */
    @RequestMapping(value = "/shareHolderData", method = RequestMethod.GET)
    public JsonResponse<List<IpoPersonInfoDto>> shareHolderData(String id) {
        return ipoCaseOverviewController.shareHolderData(id);
    }

    /**
     *  lixinwei 登录其他资本市场
     * @param id 案例主键
     * @param sortType
     * @return
     */
    @RequestMapping(value = "/marketData", method = RequestMethod.GET)
    public JsonResponse<List<OtherMarketInfoDto>> marketData(String id) {
        return ipoCaseOverviewController.marketData(id);
    }

    /**
     *  lixinwei 上交所问询情况
     * @param id 案例主键
     * @param sortType
     * @return
     */
    @RequestMapping(value = "/selectNewFeedbackList", method = RequestMethod.GET)
    public JsonResponse<List<IpoFeedbackDto>> selectNewFeedbackList(String id) {
        return ipoFeedbackController.selectNewFeedbackList(id);
    }

    /**
     *  lixinwei 中介结构
     * @param id 案例主键
     * @param validFlag 生效失效
     * @return
     */
    @RequestMapping(value = "/intermediaryOrgData", method = RequestMethod.GET)
    public JsonResponse<Map<String, List<IntermediaryOrgDto>>> intermediaryOrgData(
            @RequestParam("id") String id,
            @RequestParam(value = "validFlag", required = false) String validFlag) {
        return ipoCaseOverviewController.intermediaryOrgData(id,validFlag);
    }

    /**
     *  lixinwei 可能还想看
     * @param ipoPlate 上市板块
     * @param industryCsrc 证监会行业
     * @return
     */
    @RequestMapping(value = "/otherIpoCase", method = RequestMethod.GET)
    public JsonResponse<List<IpoCaseIndexDto>> otherIpoCase(IpoCaseIndexDto ipoCaseIndexDto) {
        JsonResponse<List<IpoCaseIndexDto>> response = new JsonResponse<>();
        response.setResult(ipoInterfaceService.otherIpoCase(ipoCaseIndexDto));
        return response;
    }

}

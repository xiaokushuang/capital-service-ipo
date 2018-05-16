package com.stock.capital.enterprise.regulatory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.stock.capital.enterprise.regulatory.dto.StatisticsCompanyDto;
import com.stock.capital.enterprise.regulatory.dto.StatisticsParamDto;
import com.stock.capital.enterprise.regulatory.dto.StatisticsResultDto;
import com.stock.capital.enterprise.regulatory.service.StatisticsService;
import com.stock.core.controller.BaseController;
import com.stock.core.dto.JsonResponse;
import com.stock.core.util.JsonUtil;

@Controller
@RequestMapping("regulatory_statistics")
public class StatisticsController extends BaseController {

    @Autowired
    private StatisticsService statisticsService;

    /**
     * IPO页面初始化
     *
     * @return
     */
    @RequestMapping(value = "ipoInit", method = RequestMethod.GET)
    public ModelAndView ipoInit() {
        ModelAndView mv = new ModelAndView("regulatory/ipoStatistics");
        return mv;
    }

    /**
     * 再融资页面初始化
     *
     * @return
     */
    @RequestMapping(value = "refinanceInit", method = RequestMethod.GET)
    public ModelAndView refinanceInit() {
        ModelAndView mv = new ModelAndView("regulatory/refinanceStatistics");
        return mv;
    }
    
    /**
     * IPO在审项目数据查询
     *
     * @return
     */
    @RequestMapping(value = "ipoQueryInit", method = RequestMethod.GET)
    public ModelAndView ipoQueryInit() {
        ModelAndView mv = new ModelAndView("regulatory/ipoQueryStatistics");
        mv.addObject("belongsPlateList", JsonUtil.toJsonNoNull(statisticsService.getCodeAndName("IPODATA_BELONG_PLATE")));
        mv.addObject("areaList", JsonUtil.toJsonNoNull(statisticsService.getAreaList()));
        mv.addObject("industryList", JsonUtil.toJsonNoNull(statisticsService.getIndustryList()));
        mv.addObject("statisticsParamDto", new StatisticsParamDto());
        return mv;
    }

    /**
     * IPO在审项目数据统计
     *
     * @return response（JSON格式）
     */
    @RequestMapping(value = "/getIPOReviewingStts", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse<List<StatisticsResultDto>> getIPOReviewingStts(String companyCode) {
        JsonResponse<List<StatisticsResultDto>> response = new JsonResponse<List<StatisticsResultDto>>();
        response.setResult(statisticsService.getIPOReviewingStts());
        return response;
    }

    /**
     * IPO保荐机构统计
     *
     * @return response（JSON格式）
     */
    @RequestMapping(value = "/getIPORecommendOrgStts", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse<List<StatisticsResultDto>> getIPORecommendOrgStts(String companyCode) {
        JsonResponse<List<StatisticsResultDto>> response = new JsonResponse<List<StatisticsResultDto>>();
        response.setResult(statisticsService.getIPORecommendOrgStts());
        return response;
    }

    /**
     * IPO会计师事务所统计
     *
     * @return response（JSON格式）
     */
    @RequestMapping(value = "/getIPOAccountantOfficeStts", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse<List<StatisticsResultDto>> getIPOAccountantOfficeStts(String companyCode) {
        JsonResponse<List<StatisticsResultDto>> response = new JsonResponse<List<StatisticsResultDto>>();
        response.setResult(statisticsService.getIPOAccountantOfficeStts());
        return response;
    }

    /**
     * IPO律师事务所统计
     *
     * @return response（JSON格式）
     */
    @RequestMapping(value = "/getIPOLawFirmStts", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse<List<StatisticsResultDto>> getIPOLawFirmStts(String companyCode) {
        JsonResponse<List<StatisticsResultDto>> response = new JsonResponse<List<StatisticsResultDto>>();
        response.setResult(statisticsService.getIPOLawFirmStts());
        return response;
    }

    /**
     * IPO最新一期日期获取
     *
     * @return response（JSON格式）
     */
    @RequestMapping(value = "/getIPOLastTime", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse<String> getIPOLastTime(String companyCode) {
        JsonResponse<String> response = new JsonResponse<String>();
        response.setResult(statisticsService.getIPOLastTime());
        return response;
    }

    /**
     * 发行监管部再融资企业审核状态
     *
     * @return response（JSON格式）
     */
    @RequestMapping(value = "/getRefinanceApproveStatusStts", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse<List<StatisticsResultDto>> getRefinanceApproveStatusStts(String companyCode) {
        JsonResponse<List<StatisticsResultDto>> response = new JsonResponse<List<StatisticsResultDto>>();
        response.setResult(statisticsService.getRefinanceApproveStatusStts());
        return response;
    }

    /**
     * 再融资申请类型情况
     *
     * @return response（JSON格式）
     */
    @RequestMapping(value = "/getRefinanceAppTypeStts", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse<List<StatisticsResultDto>> getRefinanceAppTypeStts(String companyCode) {
        JsonResponse<List<StatisticsResultDto>> response = new JsonResponse<List<StatisticsResultDto>>();
        response.setResult(statisticsService.getRefinanceAppTypeStts());
        return response;
    }

    /**
     * 发行监管部再融资审核保荐机构保荐企业数量
     *
     * @return response（JSON格式）
     */
    @RequestMapping(value = "/getRefinanceRecommendOrgStts", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse<List<StatisticsResultDto>> getRefinanceRecommendOrgStts(String companyCode) {
        JsonResponse<List<StatisticsResultDto>> response = new JsonResponse<List<StatisticsResultDto>>();
        response.setResult(statisticsService.getRefinanceRecommendOrgStts());
        return response;
    }

    /**
     * 再融资最新一期日期获取
     *
     * @return response（JSON格式）
     */
    @RequestMapping(value = "/getRefinanceLastTime", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse<String> getRefinanceLastTime(String companyCode) {
        JsonResponse<String> response = new JsonResponse<String>();
        response.setResult(statisticsService.getRefinanceLastTime());
        return response;
    }
    
    /**
     * IPO在审项目数据查询
     *
     * @return response（JSON格式）
     */
    @RequestMapping(value = "/getIPOAreaDataStts", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse<List<StatisticsResultDto>> getIPOAreaDataStts(StatisticsParamDto statisticsParamDto) {
        JsonResponse<List<StatisticsResultDto>> response = new JsonResponse<List<StatisticsResultDto>>();
        response.setResult(statisticsService.getIPOAreaDataStts(statisticsParamDto));
        return response;
    }
    
    /**
     * 在审项目数据明细
     *
     * @return
     */
    @RequestMapping(value = "viewAreaDetail")
    public ModelAndView viewAreaDetail(StatisticsParamDto statisticsParamDto) {
        ModelAndView mv = new ModelAndView("regulatory/viewAreaDetail");
        List<StatisticsCompanyDto> companyList = statisticsService.queryAreaDetail(statisticsParamDto);
        mv.addObject("companyList",companyList);
        return mv;
    }

}
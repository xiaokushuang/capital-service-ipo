package com.stock.capital.enterprise.api.regulatory.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.stock.capital.enterprise.api.regulatory.dto.StatisticsCompanyDto;
import com.stock.capital.enterprise.api.regulatory.dto.StatisticsParamDto;
import com.stock.capital.enterprise.api.regulatory.dto.StatisticsResultDto;
import com.stock.capital.enterprise.api.regulatory.service.StatisticsService;
import com.stock.core.controller.BaseController;
import com.stock.core.dto.JsonResponse;
import com.stock.core.dto.OptionDto;
import com.stock.core.dto.Page;
import com.stock.core.dto.QueryInfo;
import com.stock.core.util.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@RestController
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
    public ModelAndView ipoInit(String access_token) {
        ModelAndView mv = new ModelAndView("regulatory/ipoStatistics");
        //需求4399 2018/5/24 by liuh Start
        List<OptionDto> areaList = statisticsService.getAreaList();
        for (int i = 0; i < areaList.size(); i++) {
            if(areaList != null && StringUtils.isNotBlank(areaList.get(i).getLabel())){
                //地区特殊处理
                if ("深圳市".equals(areaList.get(i).getLabel())
                        || "大连市".equals(areaList.get(i).getLabel())
                        || "宁波市".equals(areaList.get(i).getLabel())
                        || "厦门市".equals(areaList.get(i).getLabel())
                        || "青岛市".equals(areaList.get(i).getLabel())) {
                    areaList.get(i).setLabel(areaList.get(i).getLabel().replace("市", ""));
                } else{
                    areaList.get(i).setLabel(statisticsService.changeAreaName(areaList.get(i).getLabel()));
                }
            }
        }
        //地区
        mv.addObject("areaList", JsonUtil.toJsonNoNull(areaList));
        //行业
        mv.addObject("industrySelectList", JsonUtil.toJsonNoNull(statisticsService.getIndustryList()));
        //需求4399 2018/5/24 by liuh end
        mv.addObject("statisticsParamDto", new StatisticsParamDto());
        mv.addObject("access_token", access_token);
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
        //地区特殊处理
        List<OptionDto> areaList = statisticsService.getAreaList();
        for (int i = 0; i < areaList.size(); i++) {
            if(areaList != null && StringUtils.isNotBlank(areaList.get(i).getLabel())){
                //地区特殊处理
                if ("深圳市".equals(areaList.get(i).getLabel())
                        || "大连市".equals(areaList.get(i).getLabel())
                        || "宁波市".equals(areaList.get(i).getLabel())
                        || "厦门市".equals(areaList.get(i).getLabel())
                        || "青岛市".equals(areaList.get(i).getLabel())) {
                    areaList.get(i).setLabel(areaList.get(i).getLabel().replace("市", ""));
                } else{
                    areaList.get(i).setLabel(statisticsService.changeAreaName(areaList.get(i).getLabel()));
                }
            }
        }
        mv.addObject("areaList", JsonUtil.toJsonNoNull(areaList));
        //行业处理——待定
//        mv.addObject("industryList", JsonUtil.toJsonNoNull(statisticsService.getIndustryList()));
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

    //需求4439 2018/5/24 by liuh Start
    /**
     * IPO在审项目数据统计-历史
     *
     * @return response（JSON格式）
     */
    @RequestMapping(value = "/getIPOHistory", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse<List<StatisticsResultDto>> getIPOHistory() {
        JsonResponse<List<StatisticsResultDto>> response = new JsonResponse<List<StatisticsResultDto>>();
        response.setResult(statisticsService.getIPOHistory());
        return response;
    }

    /**
     * IPO保荐机构统计
     *
     * @return response（JSON格式）
     */
    @RequestMapping(value = "/getIPORecommendOrgStts", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getIPORecommendOrgStts(StatisticsParamDto dto, Integer draw) {

        String startRow = getRequest().getParameter("start");
        String pageSize = getRequest().getParameter("length");
        // 排序列名字
        String orderColumn = getRequest().getParameter("orderByName");
        String orderByOrder = getRequest().getParameter("order[0][dir]");

        Map<String, Object> condition = Maps.newHashMap();
        //地点
        if (StringUtils.isNotEmpty(dto.getRegistAddr())) {
            condition.put("registAddr", dto.getRegistAddr());
        }
        //行业
        if (StringUtils.isNotEmpty(dto.getIndustry())) {
            condition.put("industry", dto.getIndustry());
        }

        QueryInfo<Map<String, Object>> queryInfo = commonSearch(condition);
        if(StringUtils.isNotEmpty(orderColumn)) {
            queryInfo.setOrderByName(orderColumn);
        }
        queryInfo.setOrderByOrder(orderByOrder);
        queryInfo.setPageSize(Integer.parseInt(pageSize));
        queryInfo.setStartRow(Integer.parseInt(startRow));

        Page<StatisticsResultDto> page = statisticsService.getIPORecommendOrgStts(queryInfo);
        // 设定table返回值
        Map<String, Object> response = Maps.newHashMap();
        response.put("draw", draw);
        response.put("recordsTotal", page.getTotal());
        response.put("recordsFiltered",  page.getTotal());
        response.put("data", page.getData());
        return response;
    }

    /**
     * IPO会计师事务所统计
     *
     * @return response（JSON格式）
     */
    @RequestMapping(value = "/getIPOAccountantOfficeStts", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getIPOAccountantOfficeStts(StatisticsParamDto dto, Integer draw) {
        String startRow = getRequest().getParameter("start");
        String pageSize = getRequest().getParameter("length");
        // 排序列名字
        String orderColumn = getRequest().getParameter("orderByName");
        String orderByOrder = getRequest().getParameter("order[0][dir]");

        Map<String, Object> condition = Maps.newHashMap();
        //地点
        if (StringUtils.isNotEmpty(dto.getRegistAddr())) {
            condition.put("registAddr", dto.getRegistAddr());
        }
        //行业
        if (StringUtils.isNotEmpty(dto.getIndustry())) {
            condition.put("industry", dto.getIndustry());
        }

        QueryInfo<Map<String, Object>> queryInfo = commonSearch(condition);
        if(StringUtils.isNotEmpty(orderColumn)) {
            queryInfo.setOrderByName(orderColumn);
        }
        queryInfo.setOrderByOrder(orderByOrder);
        queryInfo.setPageSize(Integer.parseInt(pageSize));
        queryInfo.setStartRow(Integer.parseInt(startRow));
        Page<StatisticsResultDto> page = statisticsService.getIPOAccountantOfficeStts(queryInfo);

        Map<String, Object> response = Maps.newHashMap();
        response.put("draw", draw);
        response.put("recordsTotal", page.getTotal());
        response.put("recordsFiltered",page.getTotal());
        response.put("data", page.getData());
        return response;
    }

    /**
     * IPO律师事务所统计
     *
     * @return response（JSON格式）
     */
    @RequestMapping(value = "/getIPOLawFirmStts", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getIPOLawFirmStts(StatisticsParamDto dto, Integer draw) {
        String startRow = getRequest().getParameter("start");
        String pageSize = getRequest().getParameter("length");
        // 排序列名字
        String orderColumn = getRequest().getParameter("orderByName");
        String orderByOrder = getRequest().getParameter("order[0][dir]");

        Map<String, Object> condition = Maps.newHashMap();
        //地点
        if (StringUtils.isNotEmpty(dto.getRegistAddr())) {
            condition.put("registAddr", dto.getRegistAddr());
        }
        //行业
        if (StringUtils.isNotEmpty(dto.getIndustry())) {
            condition.put("industry", dto.getIndustry());
        }

        QueryInfo<Map<String, Object>> queryInfo = commonSearch(condition);
        if(StringUtils.isNotEmpty(orderColumn)) {
            queryInfo.setOrderByName(orderColumn);
        }
        queryInfo.setOrderByOrder(orderByOrder);
        queryInfo.setPageSize(Integer.parseInt(pageSize));
        queryInfo.setStartRow(Integer.parseInt(startRow));

        Page<StatisticsResultDto> page = statisticsService.getIPOLawFirmStts(queryInfo);
        // 设定table返回值
        Map<String, Object> response = Maps.newHashMap();
        response.put("draw", draw);
        response.put("recordsTotal", page.getTotal());
        response.put("recordsFiltered", page.getTotal());
        response.put("data", page.getData());
        return response;
    }
    //需求4439 2018/5/24 by liuh Start

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
     * ipo公司所属板块
     * @return response（JSON格式）
     */
    @RequestMapping(value = "/getCodeAndName", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse<List<OptionDto>> getCodeAndName(String codeNo) {
        JsonResponse<List<OptionDto>> response = new JsonResponse<List<OptionDto>>();
        response.setResult(statisticsService.getCodeAndName(codeNo));
        return response;
    }
    
    /**
     * 
     * ipo地区
     * @return response（JSON格式）
     */
    @RequestMapping(value = "/getAreaList", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse<List<OptionDto>> getAreaList() {
        JsonResponse<List<OptionDto>> response = new JsonResponse<List<OptionDto>>();
        response.setResult(statisticsService.getAreaList());
        return response;
    }
    
    /**
     * 在审项目数据明细
     * @return response（JSON格式）
     */
    @RequestMapping(value = "/queryAreaDetail", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse<List<StatisticsCompanyDto>> queryAreaDetail(StatisticsParamDto statisticsParamDto) {
        JsonResponse<List<StatisticsCompanyDto>> response = new JsonResponse<List<StatisticsCompanyDto>>();
        response.setResult(statisticsService.queryAreaDetail(statisticsParamDto));
        return response;
    }

}

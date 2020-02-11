package com.stock.capital.enterprise.regulatory.controller;

import com.stock.core.rest.RestClient;
import com.sun.org.apache.xpath.internal.operations.Bool;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.io.IOException;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.stock.capital.enterprise.regulatory.dto.StatisticsCompanyDto;
import com.stock.capital.enterprise.regulatory.dto.StatisticsParamDto;
import com.stock.capital.enterprise.regulatory.dto.StatisticsResultDto;
import com.stock.capital.enterprise.regulatory.dto.StatisticsReturnDto;
import com.stock.capital.enterprise.regulatory.service.StatisticsService;
import com.stock.core.controller.BaseController;
import com.stock.core.dto.JsonResponse;
import com.stock.core.dto.OptionDto;
import com.stock.core.dto.Page;
import com.stock.core.dto.QueryInfo;
import com.stock.core.dto.TreeDto;
import com.stock.core.log.LogAnnotation;
import com.stock.core.util.DateUtil;
import com.stock.core.util.JsonUtil;
import com.stock.core.web.DownloadView;

@Controller
@RequestMapping("regulatory_statistics")
public class StatisticsController extends BaseController {

    @Autowired
    private StatisticsService statisticsService;
    
    @Resource
	private ServletContext servletContext;

    @Value("#{app['api.baseUrl']}")
    private String apiBaseUrl;

    /**
     * 接口调用
     */
    @Autowired
    private RestClient restClient;
    
    public String[] TRANS_AREA_CITY = { "深圳市", "大连市", "宁波市", "厦门市", "青岛市" };
    public String[] TRANS_AREA_PROVINCE = { "广东", "辽宁", "浙江", "福建", "山东" };
    public String[] TRANS_AREA_PROVINCE_SHOW = { "广东(不含深圳)", "辽宁(不含大连)", "浙江(不含宁波)", "福建(不含厦门)", "山东(不含青岛)" };

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
        if (CollectionUtils.isNotEmpty(areaList)) {
            OptionDto newOpt = new OptionDto();
            for (OptionDto optionDto : areaList) {
                if (Arrays.asList(TRANS_AREA_CITY).contains(optionDto.getLabel())) {
                   optionDto.setLabel(optionDto.getLabel().replace("市", "")); 
                }
                optionDto.setOriginContent(optionDto.getLabel());
                int indexOf = Arrays.asList(TRANS_AREA_PROVINCE).indexOf(optionDto.getLabel());
                if (indexOf > -1) {
                    optionDto.setLabel(TRANS_AREA_PROVINCE_SHOW[indexOf]); 
                 }
            }
            // 添加境外
            newOpt.setValue("99999");
            newOpt.setLabel("境外");
            newOpt.setOriginContent("境外");
            areaList.add(newOpt);
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
    public ModelAndView refinanceInit(String access_token) {
        ModelAndView mv = new ModelAndView("regulatory/refinanceStatistics");
        // demand 5955 start
        List<OptionDto> areaList = statisticsService.getAreaList();
        if (CollectionUtils.isNotEmpty(areaList)) {
            for (OptionDto optionDto : areaList) {
                if (Arrays.asList(TRANS_AREA_CITY).contains(optionDto.getLabel())) {
                   optionDto.setLabel(optionDto.getLabel().replace("市", "")); 
                }
                optionDto.setOriginContent(optionDto.getLabel());
                int indexOf = Arrays.asList(TRANS_AREA_PROVINCE).indexOf(optionDto.getLabel());
                if (indexOf > -1) {
                    optionDto.setLabel(TRANS_AREA_PROVINCE_SHOW[indexOf]); 
                 }
            }
        }
        //地区
        mv.addObject("areaList", JsonUtil.toJsonNoNull(areaList));
        //行业
        mv.addObject("industrySelectList", JsonUtil.toJsonNoNull(statisticsService.getIndustryList()));
        mv.addObject("statisticsParamDto", new StatisticsParamDto());
        mv.addObject("access_token", access_token);
        // demand 5955 end
        return mv;
    }
    
    /**
     * IPO在审项目数据查询
     *
     * @return
     */
    @RequestMapping(value = "ipoQueryInit", method = RequestMethod.GET)
    public ModelAndView ipoQueryInit(String access_token) {
        ModelAndView mv = new ModelAndView("regulatory/ipoQueryStatistics");
        mv.addObject("belongsPlateList", JsonUtil.toJsonNoNull(statisticsService.getCodeAndName("IPODATA_BELONG_PLATE")));
        //地区特殊处理
        List<OptionDto> areaList = statisticsService.getAreaList();
        if (CollectionUtils.isNotEmpty(areaList)) {
            OptionDto newOpt = new OptionDto();
            for (OptionDto optionDto : areaList) {
                if (Arrays.asList(TRANS_AREA_CITY).contains(optionDto.getLabel())) {
                   optionDto.setLabel(optionDto.getLabel().replace("市", "")); 
                }
                optionDto.setOriginContent(optionDto.getLabel());
                int indexOf = Arrays.asList(TRANS_AREA_PROVINCE).indexOf(optionDto.getLabel());
                if (indexOf > -1) {
                    optionDto.setLabel(TRANS_AREA_PROVINCE_SHOW[indexOf]); 
                 }
            }
            
            // 添加境外
            newOpt.setValue("99999");
            newOpt.setLabel("境外");
            newOpt.setOriginContent("境外");
            areaList.add(newOpt);
        }
        mv.addObject("areaList", JsonUtil.toJsonNoNull(areaList));
        //行业处理——待定
//        mv.addObject("industryList", JsonUtil.toJsonNoNull(statisticsService.getIndustryList()));
        mv.addObject("statisticsParamDto", new StatisticsParamDto());
        mv.addObject("access_token", access_token);
        return mv;
    }

    /**
      * IPO在审项目数据查询(接口)
      *
      * @return
      */
     @RequestMapping(value = "ipoQuery", method = RequestMethod.POST)
     @ResponseBody
     public JsonResponse<Map> ipoQuery() { 
 	   Map<String, Object> response = Maps.newHashMap();   
        // 所属行业 
        response.put("belongsPlateList",statisticsService.getCodeAndName("IPODATA_BELONG_PLATE"));
        // 所在地区  
         //地区特殊处理
         List<OptionDto> areaList = statisticsService.getAreaList();
         if (CollectionUtils.isNotEmpty(areaList)) {
             for (OptionDto optionDto : areaList) {
                 if (Arrays.asList(TRANS_AREA_CITY).contains(optionDto.getLabel())) {
                    optionDto.setLabel(optionDto.getLabel().replace("市", "")); 
                 }
                 optionDto.setOriginContent(optionDto.getLabel());
                 int indexOf = Arrays.asList(TRANS_AREA_PROVINCE).indexOf(optionDto.getLabel());
                 if (indexOf > -1) {
                     optionDto.setLabel(TRANS_AREA_PROVINCE_SHOW[indexOf]); 
                  }
             }
         }
         response.put("areaList", areaList);
         JsonResponse<Map> jsonRes = new JsonResponse<Map>(); 
         jsonRes.setResult(response); 
         return jsonRes;
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
        /*response.setResult(statisticsService.getIPOReviewingStts());*/

        ParameterizedTypeReference<JsonResponse<List<StatisticsResultDto>>> responseType = new ParameterizedTypeReference<JsonResponse<List<StatisticsResultDto>>>() {
        };
        String url = apiBaseUrl + "ipoStatistics/getIPOReviewingStts";
        List<StatisticsResultDto> list = restClient.post(url, null, responseType).getResult();
        response.setResult(list);

        return response;
    }

    //需求4399 2018/5/24 by liuh Start
    /**
     * IPO在审项目数据统计-历史
     *
     * @return response（JSON格式）
     */
    @RequestMapping(value = "/getIPOHistory", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse<List<StatisticsResultDto>> getIPOReviewingStts() {
        JsonResponse<List<StatisticsResultDto>> response = new JsonResponse<List<StatisticsResultDto>>();
        //response.setResult(statisticsService.getIPOHistory());
        ParameterizedTypeReference<JsonResponse<List<StatisticsResultDto>>> responseType = new ParameterizedTypeReference<JsonResponse<List<StatisticsResultDto>>>() {
        };
        String url = apiBaseUrl + "ipoStatistics/getIPOHistory";
        List<StatisticsResultDto> list = restClient.post(url, null, responseType).getResult();
        response.setResult(list);
        return response;
    }

    /**
     * IPO保荐机构统计
     *
     * @return response（JSON格式）
     */
    @RequestMapping(value = "/getIPORecommendOrgStts", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getIPORecommendOrgSttsForm(StatisticsParamDto dto, Integer draw) {
        String startRow = getRequest().getParameter("start");
        String pageSize = getRequest().getParameter("length");
        // 排序列名字
        String orderColumn = getRequest().getParameter("orderByName");
        String orderByOrder = getRequest().getParameter("order[0][dir]");
        
        Map<String, Object> condition = Maps.newHashMap();
        //地点
        condition.put("registAddr", dto.getRegistAddr());
        //行业
        condition.put("industry", dto.getIndustry());
        
        QueryInfo<Map<String, Object>> queryInfo = commonSearch(condition);
        if(StringUtils.isNotEmpty(orderColumn)) {
            queryInfo.setOrderByName(orderColumn);
        } 
        queryInfo.setOrderByOrder(orderByOrder);
        queryInfo.setPageSize(Integer.parseInt(pageSize));
        queryInfo.setStartRow(Integer.parseInt(startRow));

//        queryInfo.setCondition(condition);
//        getRequest().getSession().setAttribute(getRequest().getRequestURI(), queryInfo);
        
        Page<StatisticsResultDto> page = statisticsService.getIPORecommendOrgStts(queryInfo);
       
        List<StatisticsResultDto> list = Lists.newArrayList();
        int total = 0;
        if (page != null) {
            list = page.getData();
            total = page.getTotal();
        }
        // 设定table返回值
        Map<String, Object> response = Maps.newHashMap();
        response.put("draw", draw);
        response.put("recordsTotal", total);
        response.put("recordsFiltered", total);
        response.put("data", list);

        return response;
    }
    
    /**
     * IPO数据概览-保荐机构查询(微服)
     *
     * @return response（JSON格式）
     */
    @RequestMapping(value = "/getIPORecommendOrgStts1", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse<StatisticsReturnDto> getIPORecommendOrgSttsJson(@RequestBody QueryInfo<Map<String, Object>> queryInfo) {
        JsonResponse<StatisticsReturnDto> response = new JsonResponse<>();
        /*Page<StatisticsResultDto> page = statisticsService.getIPORecommendOrgStts(queryInfo);
        int total = 0;
        List<StatisticsResultDto> list = Lists.newArrayList();
        StatisticsReturnDto dto = new StatisticsReturnDto();
        if(page != null) {
            list = page.getData();
            total = page.getTotal();
        }
        dto.setRecommendOrgSttsList(list);
        dto.setTotal(total);
        response.setResult(dto);*/

        ParameterizedTypeReference<JsonResponse<StatisticsReturnDto>> responseType = new ParameterizedTypeReference<JsonResponse<StatisticsReturnDto>>() {
        };
        String url = apiBaseUrl + "ipoStatistics/getIPORecommendOrgSttsJson";
        StatisticsReturnDto list = restClient.post(url, queryInfo, responseType).getResult();
        response.setResult(list);

        return response;
    }
    
    /**
     * IPO会计师事务所统计
     *
     * @return response（JSON格式）
     */
    @RequestMapping(value = "/getIPOAccountantOfficeStts", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getIPOAccountantOfficeSttsForm(StatisticsParamDto dto, Integer draw) {
//        JsonResponse<List<StatisticsResultDto>> response = new JsonResponse<List<StatisticsResultDto>>();
//      response.setResult(statisticsService.getIPOAccountantOfficeStts());
//      List<StatisticsResultDto> list = statisticsService.getIPOAccountantOfficeStts(dto);
//      // 设定table返回值
//     Map<String, Object> response = Maps.newHashMap();
//     response.put("draw", draw);
//     response.put("recordsTotal", list.size());
//     response.put("recordsFiltered", list.size());
//     response.put("data", list);
//     return response;
      String startRow = getRequest().getParameter("start");
      String pageSize = getRequest().getParameter("length");
      // 排序列名字
      String orderColumn = getRequest().getParameter("orderByName");
      String orderByOrder = getRequest().getParameter("order[0][dir]");
      
      Map<String, Object> condition = Maps.newHashMap();
      //地点
      condition.put("registAddr", dto.getRegistAddr());
      //行业
      condition.put("industry", dto.getIndustry());
      
      QueryInfo<Map<String, Object>> queryInfo = commonSearch(condition);
      if(StringUtils.isNotEmpty(orderColumn)) {
          queryInfo.setOrderByName(orderColumn);
      }
      queryInfo.setOrderByOrder(orderByOrder);
      queryInfo.setPageSize(Integer.parseInt(pageSize));
      queryInfo.setStartRow(Integer.parseInt(startRow));

//      queryInfo.setCondition(condition);
//      getRequest().getSession().setAttribute(getRequest().getRequestURI(), queryInfo);
      
      Page<StatisticsResultDto> page = statisticsService.getIPOAccountantOfficeStts(queryInfo);
     
      List<StatisticsResultDto> list = Lists.newArrayList();
      int total = 0;
      if (page != null) {
          list = page.getData();
          total = page.getTotal();
      }
      // 设定table返回值
      Map<String, Object> response = Maps.newHashMap();
      response.put("draw", draw);
      response.put("recordsTotal", total);
      response.put("recordsFiltered", total);
      response.put("data", list);

      return response;
    }
    
    /**
     * IPO数据概览-会计事务所(微服)
     *
     * @return response（JSON格式）
     */
    @RequestMapping(value = "/getIPOAccountantOfficeStts1", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse<StatisticsReturnDto> getIPOAccountantOfficeSttsJson(@RequestBody QueryInfo<Map<String, Object>> queryInfo) {
        JsonResponse<StatisticsReturnDto> response = new JsonResponse<>();
        /*Page<StatisticsResultDto> page = statisticsService.getIPOAccountantOfficeStts(queryInfo);
        int total = 0;
        List<StatisticsResultDto> list = Lists.newArrayList();
        StatisticsReturnDto dto = new StatisticsReturnDto();
        if(page != null) {
            list = page.getData();
            total = page.getTotal();
        }
        dto.setAccountantOfficeSttsList(list);;
        dto.setTotal(total);
        response.setResult(dto);*/

        ParameterizedTypeReference<JsonResponse<StatisticsReturnDto>> responseType = new ParameterizedTypeReference<JsonResponse<StatisticsReturnDto>>() {
        };
        String url = apiBaseUrl + "ipoStatistics/getIPOAccountantOfficeSttsJson";
        StatisticsReturnDto list = restClient.post(url, queryInfo, responseType).getResult();
        response.setResult(list);

        return response;
    }
        
    /**
     * IPO律师事务所统计
     *
     * @return response（JSON格式）
     */
    @RequestMapping(value = "/getIPOLawFirmStts", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getIPOLawFirmSttsForm(StatisticsParamDto dto, Integer draw) {
//      JsonResponse<List<StatisticsResultDto>> response = new JsonResponse<List<StatisticsResultDto>>();
//      response.setResult(statisticsService.getIPOLawFirmStts());
//      List<StatisticsResultDto> list = statisticsService.getIPOLawFirmStts(dto);
//      // 设定table返回值
//         Map<String, Object> response = Maps.newHashMap();
//         response.put("draw", draw);
//         response.put("recordsTotal", list.size());
//         response.put("recordsFiltered", list.size());
//         response.put("data", list);
//      return response;
      String startRow = getRequest().getParameter("start");
      String pageSize = getRequest().getParameter("length");
      // 排序列名字
      String orderColumn = getRequest().getParameter("orderByName");
      String orderByOrder = getRequest().getParameter("order[0][dir]");
      
      Map<String, Object> condition = Maps.newHashMap();
      //地点
      condition.put("registAddr", dto.getRegistAddr());
      //行业
      condition.put("industry", dto.getIndustry());
      
      QueryInfo<Map<String, Object>> queryInfo = commonSearch(condition);
      if(StringUtils.isNotEmpty(orderColumn)) {
          queryInfo.setOrderByName(orderColumn);
      }
      queryInfo.setOrderByOrder(orderByOrder);
      queryInfo.setPageSize(Integer.parseInt(pageSize));
      queryInfo.setStartRow(Integer.parseInt(startRow));

//      queryInfo.setCondition(condition);
//      getRequest().getSession().setAttribute(getRequest().getRequestURI(), queryInfo);
      
      Page<StatisticsResultDto> page = statisticsService.getIPOLawFirmStts(queryInfo);
     
      List<StatisticsResultDto> list = Lists.newArrayList();
      int total = 0;
      if (page != null) {
          list = page.getData();
          total = page.getTotal();
      }
      // 设定table返回值
      Map<String, Object> response = Maps.newHashMap();
      response.put("draw", draw);
      response.put("recordsTotal", total);
      response.put("recordsFiltered", total);
      response.put("data", list);

      return response;
    }
    
    /**
     * IPO数据概览-律师事务所(微服)
     *
     * @return response（JSON格式）
     */
    @RequestMapping(value = "/getIPOLawFirmStts1", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse<StatisticsReturnDto> getIPOLawFirmSttsJson(@RequestBody QueryInfo<Map<String, Object>> queryInfo) {
        JsonResponse<StatisticsReturnDto> response = new JsonResponse<>();
        /*Page<StatisticsResultDto> page = statisticsService.getIPOLawFirmStts(queryInfo);
        int total = 0;
        List<StatisticsResultDto> list = Lists.newArrayList();
        StatisticsReturnDto dto = new StatisticsReturnDto();
        if(page != null) {
            list = page.getData();
            total = page.getTotal();
        }
        dto.setLawFirmSttsList(list);
        dto.setTotal(total);
        response.setResult(dto);*/

        ParameterizedTypeReference<JsonResponse<StatisticsReturnDto>> responseType = new ParameterizedTypeReference<JsonResponse<StatisticsReturnDto>>() {
        };
        String url = apiBaseUrl + "ipoStatistics/getIPOLawFirmSttsJson";
        StatisticsReturnDto list = restClient.post(url, queryInfo, responseType).getResult();
        response.setResult(list);

        return response;
    }
        
    //需求4399 2018/5/24 by liuh end

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
    public JsonResponse<Page<StatisticsResultDto>> getRefinanceRecommendOrgStts(@RequestBody QueryInfo<Map<String, Object>> queryInfo) {
        JsonResponse<Page<StatisticsResultDto>> response = new JsonResponse<>();
        Page<StatisticsResultDto> page = statisticsService.getRefinanceRecommendOrgStts(queryInfo);
        response.setResult(page);
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
        for(StatisticsCompanyDto dto : companyList){
        	if(dto.getIpoAreaLabel().contains("科创板")){
        		if("已反馈".equals(dto.getStatusLabel())){
        			dto.setStatusLabel("已问询");
        		}
        		if("已通过发审会".equals(dto.getStatusLabel())){
        			dto.setStatusLabel("上市委会议通过");
        		}
        		if("中止审查".equals(dto.getStatusLabel())){
        			dto.setStatusLabel("中止");
        		}
        	}
        }
        mv.addObject("companyList",companyList);
        mv.addObject("statisticsParamDto",statisticsParamDto);
        return mv;
    }
    
    /**
     * 
     * Excel导出--ipo在审数据明细
      * @throws IOException 
     * 
     */
     @RequestMapping(value = "ipoDetailExport")
     public ModelAndView ipoDetailExport(StatisticsParamDto statisticsParamDto) throws IOException{
         ModelAndView mv = new ModelAndView();
         mv.setView(new DownloadView());
         Map<String, Object> fileInfo = Maps.newHashMap();
         String timeStr = DateUtil.getDateStr(new Date(), "yyyyMMdd");
         fileInfo.put("fileName", "IPO在审项目数据明细_"+ timeStr+".xls");
         // 从文件服务器下载文件
         fileInfo.put("fileBytes", statisticsService.ipoDetailExport(statisticsParamDto));
         mv.addObject(DownloadView.EXPORT_FILE, fileInfo.get("fileBytes"));
         mv.addObject(DownloadView.EXPORT_FILE_NAME, fileInfo.get("fileName"));
         mv.addObject(DownloadView.EXPORT_FILE_TYPE, DownloadView.FILE_TYPE.XLS);
         return mv;
     }
     
     /**
      * 保荐机构数据明细
      *
      * @return
      */
     @RequestMapping(value = "viewCommendDetail")
     public ModelAndView viewCommendDetail(StatisticsParamDto statisticsParamDto) {
         ModelAndView mv = new ModelAndView("regulatory/viewCommendDetail");
         List<StatisticsResultDto> companyList = statisticsService.queryCommendDetail(statisticsParamDto);
         mv.addObject("companyList",companyList);
         mv.addObject("quasiListedLand",statisticsParamDto.getQuasiListedLand());
         mv.addObject("statisticsParamDto",statisticsParamDto);
         return mv;
     }
     
     /**
      * 律师事务所数据明细
      *
      * @return
      */
     @RequestMapping(value = "viewLawDetail")
     public ModelAndView viewLawDetail(StatisticsParamDto statisticsParamDto) {
         ModelAndView mv = new ModelAndView("regulatory/viewLawDetail");
         List<StatisticsResultDto> companyList = statisticsService.queryLawDetail(statisticsParamDto);
         mv.addObject("companyList",companyList);
         mv.addObject("quasiListedLand",statisticsParamDto.getQuasiListedLand());
         mv.addObject("statisticsParamDto",statisticsParamDto);
         return mv;
     }
     
     /**
      * 会计事务所数据明细
      *
      * @return
      */
     @RequestMapping(value = "viewAccountDetail")
     public ModelAndView viewAccountDetail(StatisticsParamDto statisticsParamDto) {
         ModelAndView mv = new ModelAndView("regulatory/viewAccountDetail");
         List<StatisticsResultDto> companyList = statisticsService.queryAccountDetail(statisticsParamDto);
         mv.addObject("companyList",companyList);
         mv.addObject("quasiListedLand",statisticsParamDto.getQuasiListedLand());
         mv.addObject("statisticsParamDto",statisticsParamDto);
         return mv;
     }
     
     /**
      * 
      * Excel导出--ipo保荐机构/律师事务所/会计师事务所
       * @throws IOException 
      * 
      */
      @RequestMapping(value = "ipoCommendDetailExport")
      public ModelAndView ipoCommendDetailExport(String flag , StatisticsParamDto statisticsParamDto, String detailType) throws IOException{
    	  ModelAndView mv = new ModelAndView();
    	  if("refinance".equals(detailType)){
              mv.setView(new DownloadView());
              Map<String, Object> fileInfo = Maps.newHashMap();
              String timeStr = DateUtil.getDateStr(new Date(), "yyyyMMdd");
              fileInfo.put("fileName", "IPO保荐机构数据明细_"+ timeStr+".xls");
              // 从文件服务器下载文件
              fileInfo.put("fileBytes", statisticsService.ipoRefinanceDetailExport(statisticsParamDto));
              mv.addObject(DownloadView.EXPORT_FILE, fileInfo.get("fileBytes"));
              mv.addObject(DownloadView.EXPORT_FILE_NAME, fileInfo.get("fileName"));
              mv.addObject(DownloadView.EXPORT_FILE_TYPE, DownloadView.FILE_TYPE.XLS);
    	  } else {
    	  if("1".equals(flag)){
              mv.setView(new DownloadView());
              Map<String, Object> fileInfo = Maps.newHashMap();
              String timeStr = DateUtil.getDateStr(new Date(), "yyyyMMdd");
              fileInfo.put("fileName", "IPO保荐机构数据明细_"+ timeStr+".xls");
              // 从文件服务器下载文件
              fileInfo.put("fileBytes", statisticsService.ipoCommendDetailExport(statisticsParamDto,flag));
              mv.addObject(DownloadView.EXPORT_FILE, fileInfo.get("fileBytes"));
              mv.addObject(DownloadView.EXPORT_FILE_NAME, fileInfo.get("fileName"));
              mv.addObject(DownloadView.EXPORT_FILE_TYPE, DownloadView.FILE_TYPE.XLS);
          }else if("2".equals(flag)){
              mv.setView(new DownloadView());
              Map<String, Object> fileInfo = Maps.newHashMap();
              String timeStr = DateUtil.getDateStr(new Date(), "yyyyMMdd");
              fileInfo.put("fileName", "IPO律师事务所数据明细_"+ timeStr+".xls");
              // 从文件服务器下载文件
              fileInfo.put("fileBytes", statisticsService.ipoCommendDetailExport(statisticsParamDto,flag));
              mv.addObject(DownloadView.EXPORT_FILE, fileInfo.get("fileBytes"));
              mv.addObject(DownloadView.EXPORT_FILE_NAME, fileInfo.get("fileName"));
              mv.addObject(DownloadView.EXPORT_FILE_TYPE, DownloadView.FILE_TYPE.XLS);
          }else if("3".equals(flag)){
              mv.setView(new DownloadView());
              Map<String, Object> fileInfo = Maps.newHashMap();
              String timeStr = DateUtil.getDateStr(new Date(), "yyyyMMdd");
              fileInfo.put("fileName", "IPO会计师事务所数据明细_"+ timeStr+".xls");
              // 从文件服务器下载文件
              fileInfo.put("fileBytes", statisticsService.ipoCommendDetailExport(statisticsParamDto,flag));
              mv.addObject(DownloadView.EXPORT_FILE, fileInfo.get("fileBytes"));
              mv.addObject(DownloadView.EXPORT_FILE_NAME, fileInfo.get("fileName"));
              mv.addObject(DownloadView.EXPORT_FILE_TYPE, DownloadView.FILE_TYPE.XLS);
              
          }
    	  }
    	  return mv;
      }
     
      /**
       * demand 5955 - 再融资-保荐机构数据明细
       *
       * @return
       */
      @RequestMapping(value = "queryRefinanceDetail")
      public ModelAndView queryRefinanceDetail(StatisticsParamDto statisticsParamDto) {
          ModelAndView mv = new ModelAndView("regulatory/viewRefinanceDetail");
          List<StatisticsResultDto> companyList = statisticsService.queryRefinanceDetail(statisticsParamDto);
          mv.addObject("companyList",companyList);
          mv.addObject("statisticsParamDto",statisticsParamDto);
          return mv;
      }
      
      /**
       * demand 5955 再融资表格查询
       *
       * @return response（JSON格式）
       */
      @RequestMapping(value = "/getRefinanceRecommendOrgStts1", method = RequestMethod.POST)
      @ResponseBody
      public Map<String, Object> getRefinanceRecommendOrgSttsForm(StatisticsParamDto dto, Integer draw) {
          String startRow = getRequest().getParameter("start");
          String pageSize = getRequest().getParameter("length");
          // 排序列名字
          String orderColumn = getRequest().getParameter("orderByName");
          String orderByOrder = getRequest().getParameter("order[0][dir]");
          
          Map<String, Object> condition = Maps.newHashMap();
          //地点
          condition.put("registAddr", dto.getRegistAddr());
          //行业
          condition.put("industry", dto.getIndustry());
          
          QueryInfo<Map<String, Object>> queryInfo = commonSearch(condition);
          if(StringUtils.isNotEmpty(orderColumn)) {
              queryInfo.setOrderByName(orderColumn);
          } 
          queryInfo.setOrderByOrder(orderByOrder);
          queryInfo.setPageSize(Integer.parseInt(pageSize));
          queryInfo.setStartRow(Integer.parseInt(startRow));

//          queryInfo.setCondition(condition);
//          getRequest().getSession().setAttribute(getRequest().getRequestURI(), queryInfo);
          
          Page<StatisticsResultDto> page = statisticsService.getRefinanceRecommendOrgStts(queryInfo);
         
          List<StatisticsResultDto> list = Lists.newArrayList();
          int total = 0;
          if (page != null) {
              list = page.getData();
              total = page.getTotal();
          }
          // 设定table返回值
          Map<String, Object> response = Maps.newHashMap();
          response.put("draw", draw);
          response.put("recordsTotal", total);
          response.put("recordsFiltered", total);
          response.put("data", list);

          return response;
      }
      
      
      //excel down
//      @RequestMapping("download")
//      @ResponseBody
//      public ModelAndView download(String access_token,String belongsPlate,String registAddr) {
//          //String templatesPath = getRequest().getSession().getServletContext().getRealPath("/WEB-INF/templates/IPO在审项目数据.xlsx");
//    	  InputStream inputStream = servletContext.getResourceAsStream("/WEB-INF/templates/IPO在审项目数据.xlsx");
//    	  ModelAndView mv = new ModelAndView();
//          try {
//              mv.setView(new DownloadView());
//              InputStream is = statisticsService.exportExcel(inputStream, belongsPlate, registAddr);
//              mv.addObject(DownloadView.EXPORT_FILE, is);
//              mv.addObject(DownloadView.EXPORT_FILE_NAME, "IPO在审项目数据.xlsx");
//              mv.addObject(DownloadView.EXPORT_FILE_TYPE, DownloadView.FILE_TYPE.XLSX);
//              mv.addObject(DownloadView.EXPORT_FILE_SIZE, is.available());
//              mv.addObject("access_token", access_token);
//          } catch (FileNotFoundException e) {
//              e.printStackTrace();
//          } catch (IOException e) {
//              e.printStackTrace();
//          }
//          return mv;
//      }
      
      /**
       * 获取所有下拉列表
       *
       * @return response（JSON格式）
       */
      @ApiOperation(value = "获取所有下拉列表", notes = "获取所有下拉列表")
      @LogAnnotation(name = "获取所有下拉列表")
      @RequestMapping(value = "/getAllDropDownList", method = RequestMethod.POST)
      @ResponseBody
      public StatisticsReturnDto getAllDropDownList() {
          StatisticsReturnDto dto = new StatisticsReturnDto();
          //地区
          List<OptionDto> areaList = statisticsService.getAreaList();
          if (CollectionUtils.isNotEmpty(areaList)) {
              for (OptionDto optionDto : areaList) {
                  if (Arrays.asList(TRANS_AREA_CITY).contains(optionDto.getLabel())) {
                     optionDto.setLabel(optionDto.getLabel().replace("市", "")); 
                  }
                  optionDto.setOriginContent(optionDto.getLabel());
                  int indexOf = Arrays.asList(TRANS_AREA_PROVINCE).indexOf(optionDto.getLabel());
                  if (indexOf > -1) {
                      optionDto.setLabel(TRANS_AREA_PROVINCE_SHOW[indexOf]); 
                   }
              }
              // 添加境外
              OptionDto newOpt = new OptionDto();
              newOpt.setValue("99999");
              newOpt.setLabel("境外");
              newOpt.setOriginContent("境外");
              areaList.add(newOpt);
          }
          dto.setAreaList(areaList);
          //行业
          List<TreeDto> industryList = statisticsService.getIndustryList();
          dto.setIndustryList(industryList);
          //板块
          List<OptionDto> belongsPlateList = statisticsService.getCodeAndName("IPODATA_BELONG_PLATE");
          dto.setBelongsPlateList(belongsPlateList);
          return dto;
      }
      
      /**
       * 取得ipo数据概览详情数据
       */
      @ApiOperation(value = "取得ipo数据概览详情数据", notes = "取得ipo数据概览详情数据")
      @LogAnnotation(name = "取得ipo数据概览详情数据")
      @RequestMapping(value = "/getIpoDataOverviewDetail", method = RequestMethod.POST)
      @ResponseBody
      public JsonResponse<StatisticsReturnDto> getIpoDataOverviewDetail(@ApiParam(value = "ipo数据概览查询dto") @RequestBody StatisticsParamDto dto) {
          JsonResponse<StatisticsReturnDto> response = new JsonResponse<StatisticsReturnDto>();
          /*StatisticsReturnDto returnDto = new StatisticsReturnDto();
          returnDto = statisticsService.getIpoDataOverviewDetail(dto);
          response.setResult(returnDto);*/
          ParameterizedTypeReference<JsonResponse<StatisticsReturnDto>> responseType = new ParameterizedTypeReference<JsonResponse<StatisticsReturnDto>>() {
          };
          String url = apiBaseUrl + "ipoStatistics/getIpoDataOverviewDetail";
          StatisticsReturnDto list = restClient.post(url, dto, responseType).getResult();
          response.setResult(list);
          return response;
      }

    /**
     * 取得ipo辅导数据概览详情数据
     */
    @ApiOperation(value = "取得ipo数据概览详情数据", notes = "取得ipo数据概览详情数据")
    @LogAnnotation(name = "取得ipo数据概览详情数据")
    @RequestMapping(value = "/getIpoDataOverviewFdDetail", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse<StatisticsReturnDto> getIpoDataOverviewFdDetail(@ApiParam(value = "ipo数据概览查询dto") @RequestBody StatisticsParamDto dto) {
        JsonResponse<StatisticsReturnDto> response = new JsonResponse<StatisticsReturnDto>();
        ParameterizedTypeReference<JsonResponse<StatisticsReturnDto>> responseType = new ParameterizedTypeReference<JsonResponse<StatisticsReturnDto>>() {
        };
        String url = apiBaseUrl + "ipoStatistics/getIPOfdCaseDetail";
        StatisticsReturnDto list = restClient.post(url, dto, responseType).getResult();
        response.setResult(list);
        return response;
    }
      
      /**
       * 
       * Excel导出--ipo保荐机构/律师事务所/会计师事务所
        * @throws IOException 
       * 
       */
       @RequestMapping(value = "ipoDataOverviewDetailExport")
       public ModelAndView ipoDataOverviewDetailExport(@RequestBody StatisticsParamDto statisticsParamDto, HttpServletResponse response) throws IOException{
           ModelAndView mv = new ModelAndView();
           String flag = statisticsParamDto.getTabFlag();
           Map<String, Object> fileInfo = Maps.newHashMap();
           if("first".equals(flag)){
               mv.setView(new DownloadView());
               String timeStr = DateUtil.getDateStr(new Date(), "yyyyMMdd");
               fileInfo.put("fileName", "IPO保荐机构数据明细_"+ timeStr+".xls");
               // 从文件服务器下载文件
               fileInfo.put("fileBytes", statisticsService.ipoCommendDetailExport(statisticsParamDto,flag));
               mv.addObject(DownloadView.EXPORT_FILE, fileInfo.get("fileBytes"));
               mv.addObject(DownloadView.EXPORT_FILE_NAME, fileInfo.get("fileName"));
               mv.addObject(DownloadView.EXPORT_FILE_TYPE, DownloadView.FILE_TYPE.XLS);
           }else if("second".equals(flag)){
               mv.setView(new DownloadView());
               String timeStr = DateUtil.getDateStr(new Date(), "yyyyMMdd");
               fileInfo.put("fileName", "IPO律师事务所数据明细_"+ timeStr+".xls");
               // 从文件服务器下载文件
               fileInfo.put("fileBytes", statisticsService.ipoCommendDetailExport(statisticsParamDto,flag));
               mv.addObject(DownloadView.EXPORT_FILE, fileInfo.get("fileBytes"));
               mv.addObject(DownloadView.EXPORT_FILE_NAME, fileInfo.get("fileName"));
               mv.addObject(DownloadView.EXPORT_FILE_TYPE, DownloadView.FILE_TYPE.XLS);
           }else if("third".equals(flag)){
               mv.setView(new DownloadView());
               String timeStr = DateUtil.getDateStr(new Date(), "yyyyMMdd");
               fileInfo.put("fileName", "IPO会计师事务所数据明细_"+ timeStr+".xls");
               // 从文件服务器下载文件
               fileInfo.put("fileBytes", statisticsService.ipoCommendDetailExport(statisticsParamDto,flag));
               mv.addObject(DownloadView.EXPORT_FILE, fileInfo.get("fileBytes"));
               mv.addObject(DownloadView.EXPORT_FILE_NAME, fileInfo.get("fileName"));
               mv.addObject(DownloadView.EXPORT_FILE_TYPE, DownloadView.FILE_TYPE.XLS);
           }else if("four".equals(flag)){
               mv.setView(new DownloadView());
               String timeStr = DateUtil.getDateStr(new Date(), "yyyyMMdd");
               fileInfo.put("fileName", "在审数据明细_"+ timeStr+".xls");
               // 从文件服务器下载文件
               fileInfo.put("fileBytes", statisticsService.ipoCommendDetailExport(statisticsParamDto,flag));
               mv.addObject(DownloadView.EXPORT_FILE, fileInfo.get("fileBytes"));
               mv.addObject(DownloadView.EXPORT_FILE_NAME, fileInfo.get("fileName"));
               mv.addObject(DownloadView.EXPORT_FILE_TYPE, DownloadView.FILE_TYPE.XLS);
           }
           response.setHeader("fileName", java.net.URLEncoder.encode(fileInfo.get("fileName").toString(), "utf-8"));
           return mv;
       }

    /**
     *
     * Excel导出--ipo辅导保荐机构/律师事务所/会计师事务所
     * @throws IOException
     *
     */
    @RequestMapping(value = "ipoDataOverviewFdDetailExport")
    public ModelAndView ipoDataOverviewFdDetailExport(@RequestBody StatisticsParamDto statisticsParamDto, HttpServletResponse response) throws IOException{
        ModelAndView mv = new ModelAndView();
        String flag = statisticsParamDto.getTabFlag();
        Map<String, Object> fileInfo = Maps.newHashMap();
        if("first".equals(flag)){
            mv.setView(new DownloadView());
            String timeStr = DateUtil.getDateStr(new Date(), "yyyyMMdd");
            fileInfo.put("fileName", "IPO辅导案例辅导机构数据明细_"+ timeStr+".xls");
            // 从文件服务器下载文件
            fileInfo.put("fileBytes", statisticsService.ipoCommendFdDetailExport(statisticsParamDto,flag));
            mv.addObject(DownloadView.EXPORT_FILE, fileInfo.get("fileBytes"));
            mv.addObject(DownloadView.EXPORT_FILE_NAME, fileInfo.get("fileName"));
            mv.addObject(DownloadView.EXPORT_FILE_TYPE, DownloadView.FILE_TYPE.XLS);
        }else if("second".equals(flag)){
            mv.setView(new DownloadView());
            String timeStr = DateUtil.getDateStr(new Date(), "yyyyMMdd");
            fileInfo.put("fileName", "IPO辅导案例律师事务所数据明细_"+ timeStr+".xls");
            // 从文件服务器下载文件
            fileInfo.put("fileBytes", statisticsService.ipoCommendFdDetailExport(statisticsParamDto,flag));
            mv.addObject(DownloadView.EXPORT_FILE, fileInfo.get("fileBytes"));
            mv.addObject(DownloadView.EXPORT_FILE_NAME, fileInfo.get("fileName"));
            mv.addObject(DownloadView.EXPORT_FILE_TYPE, DownloadView.FILE_TYPE.XLS);
        }else if("third".equals(flag)){
            mv.setView(new DownloadView());
            String timeStr = DateUtil.getDateStr(new Date(), "yyyyMMdd");
            fileInfo.put("fileName", "IPO辅导案例会计师事务所数据明细_"+ timeStr+".xls");
            // 从文件服务器下载文件
            fileInfo.put("fileBytes", statisticsService.ipoCommendFdDetailExport(statisticsParamDto,flag));
            mv.addObject(DownloadView.EXPORT_FILE, fileInfo.get("fileBytes"));
            mv.addObject(DownloadView.EXPORT_FILE_NAME, fileInfo.get("fileName"));
            mv.addObject(DownloadView.EXPORT_FILE_TYPE, DownloadView.FILE_TYPE.XLS);
        }else {
            mv.setView(new DownloadView());
            String timeStr = DateUtil.getDateStr(new Date(), "yyyyMMdd");
            fileInfo.put("fileName", "IPO辅导案例数据明细"+ timeStr+".xls");
            // 从文件服务器下载文件
            fileInfo.put("fileBytes", statisticsService.ipoCommendFdDetailExport(statisticsParamDto,flag));
            mv.addObject(DownloadView.EXPORT_FILE, fileInfo.get("fileBytes"));
            mv.addObject(DownloadView.EXPORT_FILE_NAME, fileInfo.get("fileName"));
            mv.addObject(DownloadView.EXPORT_FILE_TYPE, DownloadView.FILE_TYPE.XLS);
        }
        response.setHeader("fileName", java.net.URLEncoder.encode(fileInfo.get("fileName").toString(), "utf-8"));
        return mv;
    }
       
       /**
        * IPO在审项目数据查询
        */
       @ApiOperation(value = "IPO在审项目数据查询", notes = "IPO在审项目数据查询")
       @LogAnnotation(name = "IPO在审项目数据查询")
       @RequestMapping(value = "/ipoItemDataQuery", method = RequestMethod.POST)
       @ResponseBody
       public JsonResponse<StatisticsReturnDto> ipoItemDataQuery(@ApiParam(value = "ipo数据概览查询dto") @RequestBody QueryInfo<StatisticsParamDto> dto) {
           JsonResponse<StatisticsReturnDto> response = new JsonResponse<StatisticsReturnDto>();
           /*StatisticsReturnDto returnDto = new StatisticsReturnDto();
           returnDto = statisticsService.ipoItemDataQuery(dto);
           response.setResult(returnDto);*/

           ParameterizedTypeReference<JsonResponse<StatisticsReturnDto>> responseType = new ParameterizedTypeReference<JsonResponse<StatisticsReturnDto>>() {
           };
           String url = apiBaseUrl + "ipoStatistics/ipoItemDataQuery";
           StatisticsReturnDto list = restClient.post(url, dto, responseType).getResult();
           response.setResult(list);

           return response;
       }
       
       /**
        * 
        * Excel导出--ipo在审项目
         * @throws IOException 
        * 
        */
        @RequestMapping(value = "ipoItemDataExport")
        public ModelAndView ipoItemDataExport(@RequestBody QueryInfo<StatisticsParamDto> statisticsParamDto, HttpServletResponse response) throws Exception{
//            InputStream inputStream = servletContext.getResourceAsStream("/WEB-INF/templates/IPO在审项目数据.xlsx");
            ModelAndView mv = new ModelAndView();
            mv.setView(new DownloadView());
            String filePath = "/WEB-INF/templates/IPO在审项目数据.xlsx";
//            InputStream is = statisticsService.exportExcel(filePath, statisticsParamDto);
            mv.addObject(DownloadView.EXPORT_FILE, statisticsService.exportExcel(filePath, statisticsParamDto));
            mv.addObject(DownloadView.EXPORT_FILE_NAME, "IPO在审项目数据.xlsx");
            mv.addObject(DownloadView.EXPORT_FILE_TYPE, DownloadView.FILE_TYPE.XLSX);
            response.setHeader("fileName", java.net.URLEncoder.encode("IPO在审项目数据.xlsx", "utf-8"));
            return mv;
        }
        
        /**
         * 取得ipo在审项目详情数据
         */
        @ApiOperation(value = "取得ipo在审项目详情数据", notes = "取得ipo在审项目详情数据")
        @LogAnnotation(name = "取得ipo在审项目详情数据")
        @RequestMapping(value = "/getIpoItemDataDetail", method = RequestMethod.POST)
        @ResponseBody
        public JsonResponse<StatisticsReturnDto> getIpoItemDataDetail(@ApiParam(value = "ipo数据概览查询dto") @RequestBody StatisticsParamDto dto) {
            JsonResponse<StatisticsReturnDto> response = new JsonResponse<StatisticsReturnDto>();
            /*StatisticsReturnDto returnDto = new StatisticsReturnDto();
            returnDto = statisticsService.getIpoItemDataDetail(dto);
            response.setResult(returnDto);*/
            ParameterizedTypeReference<JsonResponse<StatisticsReturnDto>> responseType = new ParameterizedTypeReference<JsonResponse<StatisticsReturnDto>>() {
            };
            String url = apiBaseUrl + "ipoStatistics/getIpoItemDataDetail";
            StatisticsReturnDto list = restClient.post(url, dto, responseType).getResult();
            response.setResult(list);
            return response;
        }
        
        /**
         * 
         * Excel导出--ipo在审项目详情数据
          * @throws IOException 
         * 
         */
         @RequestMapping(value = "ipoItemDataDetailExport")
         public ModelAndView ipoItemDataDetailExport(@RequestBody StatisticsParamDto statisticsParamDto, HttpServletResponse response) throws IOException{
             ModelAndView mv = new ModelAndView();
             mv.setView(new DownloadView());
             Map<String, Object> fileInfo = Maps.newHashMap();
             String timeStr = DateUtil.getDateStr(new Date(), "yyyyMMdd");
             fileInfo.put("fileName", "IPO在审项目数据明细_"+ timeStr+".xls");
             // 从文件服务器下载文件
             fileInfo.put("fileBytes", statisticsService.ipoDetailExport(statisticsParamDto));
             mv.addObject(DownloadView.EXPORT_FILE, fileInfo.get("fileBytes"));
             mv.addObject(DownloadView.EXPORT_FILE_NAME, fileInfo.get("fileName"));
             mv.addObject(DownloadView.EXPORT_FILE_TYPE, DownloadView.FILE_TYPE.XLS);
             response.setHeader("fileName", java.net.URLEncoder.encode(fileInfo.get("fileName").toString(), "utf-8"));
             return mv;
         }
         
         /**
          * 通过公司code查询ipo最新得id
          */
         @ApiOperation(value = "通过公司code查询ipo", notes = "通过公司code查询ipo")
         @LogAnnotation(name = "通过公司code查询ipo")
         @RequestMapping(value = "/getIpoItemByCompanyCodeSelectId", method = RequestMethod.POST)
         @ResponseBody
         public JsonResponse<Map<String, Object>> getIpoItemByCompanyCodeSelectId( @ApiParam(value = "ipo数据概览查询dto") @RequestBody StatisticsParamDto dto) {
             JsonResponse<Map<String, Object>> json = new JsonResponse<Map<String, Object>>();
             Map<String, Object> map = statisticsService.getIpoItemByCompanyCodeSelectId(dto.getStockCode());
             json.setResult(map);
             return json;
         }
         
}

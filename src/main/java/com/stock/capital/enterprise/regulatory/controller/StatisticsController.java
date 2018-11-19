package com.stock.capital.enterprise.regulatory.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.stock.capital.enterprise.regulatory.service.StatisticsService;
import com.stock.core.controller.BaseController;
import com.stock.core.dto.JsonResponse;
import com.stock.core.dto.OptionDto;
import com.stock.core.dto.Page;
import com.stock.core.dto.QueryInfo;
import com.stock.core.util.DateUtil;
import com.stock.core.util.JsonUtil;
import com.stock.core.web.DownloadView;

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
        // demand 5955 start
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
        mv.addObject("statisticsParamDto", new StatisticsParamDto());
        // demand 5955 end
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
        response.setResult(statisticsService.getIPOReviewingStts());
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
    
    @RequestMapping(value = "/getIPORecommendOrgStts1", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse<Page<StatisticsResultDto>> getIPORecommendOrgSttsJson(@RequestBody QueryInfo<Map<String, Object>> queryInfo) {
        JsonResponse<Page<StatisticsResultDto>> response = new JsonResponse<>();
        Page<StatisticsResultDto> page = statisticsService.getIPORecommendOrgStts(queryInfo);
        response.setResult(page);
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
    
    @RequestMapping(value = "/getIPOAccountantOfficeStts1", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse<Page<StatisticsResultDto>> getIPOAccountantOfficeSttsJson(@RequestBody QueryInfo<Map<String, Object>> queryInfo) {
        JsonResponse<Page<StatisticsResultDto>> response = new JsonResponse<>();
        Page<StatisticsResultDto> page = statisticsService.getIPOAccountantOfficeStts(queryInfo);
        response.setResult(page);
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
    
    @RequestMapping(value = "/getIPOLawFirmStts1", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse<Page<StatisticsResultDto>> getIPOLawFirmSttsJson(@RequestBody QueryInfo<Map<String, Object>> queryInfo) {
        JsonResponse<Page<StatisticsResultDto>> response = new JsonResponse<>();
        Page<StatisticsResultDto> page = statisticsService.getIPOLawFirmStts(queryInfo);
        response.setResult(page);
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
      public ModelAndView ipoCommendDetailExport(String flag , StatisticsParamDto statisticsParamDto) throws IOException{
    	  ModelAndView mv = new ModelAndView();
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
}

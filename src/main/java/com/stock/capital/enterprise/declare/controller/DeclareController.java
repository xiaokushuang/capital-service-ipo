package com.stock.capital.enterprise.declare.controller;

 
import java.util.HashMap;
import java.util.List;
import java.util.Map;  

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;  
import com.stock.capital.enterprise.common.service.CommonService;
import com.stock.capital.enterprise.declare.dto.DecalreRelationLawDto;
import com.stock.capital.enterprise.declare.dto.DeclareFileDto;
import com.stock.capital.enterprise.declare.dto.LawTableDto;
import com.stock.capital.enterprise.declare.dto.NoticeHelperDto;
import com.stock.capital.enterprise.declare.service.DeclareService;
import com.stock.capital.enterprise.api.financeStatistics.dto.Param;
import com.stock.core.controller.BaseController; 
import com.stock.core.dto.JsonResponse;
import com.stock.core.rest.RestClient; 
import com.stock.core.util.JsonUtil; 

/**
 * 信息披露画面Controller
 * 
 * @author xuehui
 *
 */
@Controller
@RequestMapping("declare")
public class DeclareController extends BaseController {
    @Autowired
    DeclareService declareService; 
    @Autowired
    private CommonService commonService; 
    /**
     * 接口调用
     */
    @Autowired
    private RestClient restClient;
 
    /**
     * 接口地址前缀
     */
    @Value("${api.baseUrl}")
    private String apiBaseUrl; 
    /**
     * 公告小助手
     * 
     * @return 业务分类树数据
     */
    @RequestMapping(value = "getZtree", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> noticeHelper(@RequestBody Param param) {
    	
  	  // 设定table返回值
        Map<String, Object> response = Maps.newHashMap();   
    	  
        response.put("data",  declareService.getDeclareType(param.getParam()));
 
    	 return response; 
    }
    /**
     * 公告小助手
     * 
     * @return 获取某个分类的详细信息
     */
    @RequestMapping(value = "TableData", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> TableData(@RequestBody Param param) {
    	
  	  // 设定table返回值
        Map<String, Object> response = Maps.newHashMap();   
    	  
        response.put("data",  declareService.getDeclareType(param.getParam()));
 
    	 return response; 
    }
    
    /**
     * 公告小助手数据
     * 
     * @param typeId
     *            业务分类id
     * @param typeName
     *            业务分类名
     * @return
     */
    @RequestMapping(value = "getHelperData", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse<NoticeHelperDto> getHelperData(@RequestBody Param param) { 
        NoticeHelperDto dto = declareService.getHelperData(param.getTypeId(),param.getTypeName());
        JsonResponse<NoticeHelperDto> response = new JsonResponse<NoticeHelperDto>();
        response.setResult(dto);
        return response;
    }

  /**
     * 根据业务类型取得模板文件
     * 
     * @param typeId
     *            业务id
     * @param typeName
     *            业务名称
     * @param declareTypeNo
     *            业务分类编号
     * @return 模板文件列表
     */
    @RequestMapping(value = "getTempalteFile", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse<List<DeclareFileDto>> getTempalteFile(String typeId, String typeName, String declareTypeNo) {
        List<DeclareFileDto> templareList = declareService.getTempalteFile(typeId, typeName, declareTypeNo);
        JsonResponse<List<DeclareFileDto>> jsonRes = new JsonResponse<List<DeclareFileDto>>();
        jsonRes.setResult(templareList);
        return jsonRes;
    }
    
    /**
     * 获取法律table
     * 
     * @param typeId
     *            业务id
     * @param typeName
     *            业务名称
     * @param declareTypeNo
     *            业务分类编号
     * @return 模板文件列表
     */
    @RequestMapping(value = "getRelationLawTable", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse<LawTableDto> getRelationLawTable(@RequestBody Param param) {
       
      JsonResponse<LawTableDto> jsonRes = new JsonResponse<LawTableDto>(); 
        
      // 相关法律法规获取
      String url = apiBaseUrl + "laws_manage/declareRelationLaw";
      MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
      ParameterizedTypeReference<List<DecalreRelationLawDto>> responseType1 = new ParameterizedTypeReference<List<DecalreRelationLawDto>>() {
      }; 
      params.add("typeId", param.getTypeId());//${startRow}, ${pageSize} orderByOrder
      List<DecalreRelationLawDto> lawRuletotal = restClient.post(url, params, responseType1);
      params.add("startRow", param.getFromPaper());
      params.add("pageSize", param.getLength());
      params.add("orderColumn", param.getOrderByName()); 
      params.add("orderByOrder", param.getOrderByOrder()); 
      List<DecalreRelationLawDto> lawRule = restClient.post(url, params, responseType1);
      LawTableDto ltd = new LawTableDto();
      ltd.setData(lawRule);
      ltd.setTotal(lawRuletotal.size());
      jsonRes.setResult(ltd);
        return jsonRes;
    }
    
    /**
     * 获取地区和行业数据
     * 
     * @param typeId
     *            业务id
     * @param typeName
     *            业务名称
     * @param declareTypeNo
     *            业务分类编号
     * @return 模板文件列表
     */
    @RequestMapping(value = "getAreaAndIndustry", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse<Map> getAreaAndIndustry() { 
    	 // 设定table返回值
       Map<String, Object> response = Maps.newHashMap();   
        // 所属行业 
       response.put("industrySelectList", commonService.getIndustryList());
        // 所在地区 
       response.put("areaList", commonService.getProvincesList());
        JsonResponse<Map> jsonRes = new JsonResponse<Map>(); 
        jsonRes.setResult(response);
        return jsonRes;
    }
    
}

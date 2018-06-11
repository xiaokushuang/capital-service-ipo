package com.stock.capital.enterprise.declare.controller;

 
import java.util.Map; 
 






import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod; 
 





import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;  
import com.stock.capital.enterprise.common.service.CommonService;
import com.stock.capital.enterprise.declare.service.DeclareService;
import com.stock.capital.enterprise.financeStatistics.dto.Param;
import com.stock.core.controller.BaseController; 
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
    	  
        response.put("data", JsonUtil.toJson(declareService.getDeclareType(param.getParam())));
 
    	 return response; 
    }
}

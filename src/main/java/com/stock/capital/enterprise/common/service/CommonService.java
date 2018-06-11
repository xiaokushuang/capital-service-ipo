package com.stock.capital.enterprise.common.service;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.google.common.base.Throwables;
import com.stock.capital.enterprise.common.constant.Global;
 
import com.stock.core.dto.JsonResponse;
import com.stock.core.dto.MenuDto;
import com.stock.core.dto.OptionDto;
import com.stock.core.dto.TreeDto;
import com.stock.core.exception.ApplicationException;
import com.stock.core.rest.RestClient;
import com.stock.core.service.BaseService;
import com.stock.core.util.DateUtil;
import com.stock.core.util.SmsClient;
import com.stock.core.util.WebUtil;

@Service
public class CommonService extends BaseService {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(CommonService.class);
     
    private String apiBaseUrl = "http://dev-sz.valueonline.cn/capital-cloud-api/"; 
    @Autowired
    private MessageSource appMessageSource;
	
    @Autowired
    private SmsClient smsClient;

    /**
     * 接口调用
     */
    @Autowired
    private RestClient restClient;
 
    
    @Autowired
    private RestTemplate restTemplate;
   
	//lanyuxin 2017-08-22 begin 
	//地区检索到市级
	public List<TreeDto> getProvincesList() {
        ParameterizedTypeReference<JsonResponse<List<TreeDto>>> responseType = new ParameterizedTypeReference<JsonResponse<List<TreeDto>>>() {
        };
        String url = apiBaseUrl + "declareInfo/postDeclareIndexAreas";
        List<TreeDto> list = restClient.post(url, null, responseType).getResult();
        return list;
    }
	//地区检索到市级（不含港澳台）
        public List<TreeDto> getOtherProvincesList() {
            ParameterizedTypeReference<JsonResponse<List<TreeDto>>> responseType = new ParameterizedTypeReference<JsonResponse<List<TreeDto>>>() {
            };
            String url = apiBaseUrl + "declareInfo/postDeclareOtherAreas";
            List<TreeDto> list = restClient.post(url, null, responseType).getResult();
            return list;
        }
	
	//市场板块
	public List<TreeDto> getBoardSelectList(String flag) {
        ParameterizedTypeReference<JsonResponse<List<TreeDto>>> responseType = new ParameterizedTypeReference<JsonResponse<List<TreeDto>>>() {
        };
        MultiValueMap<String, String> request = new LinkedMultiValueMap<String, String>();
        String url = apiBaseUrl + "declareInfo/postDeclareIndexBoardss";
        request.add("flag", flag);
        List<TreeDto> list = restClient.post(url, request, responseType).getResult();
       
        return list;
    }
	//公告类型(沪深，新三板)
		public List<TreeDto> getTypeSelectList(String flag) {
	        ParameterizedTypeReference<JsonResponse<List<TreeDto>>> responseType = new ParameterizedTypeReference<JsonResponse<List<TreeDto>>>() {
	        };
	        MultiValueMap<String, String> request = new LinkedMultiValueMap<String, String>();
			String url = apiBaseUrl + "declareInfo/postDeclareIndexTypeLabel";
	        request.add("plateCode", flag);
	        List<TreeDto> list = restClient.post(url, request, responseType).getResult();
	       
	        return list;
	    }
		//公告类型(港股)
				public List<TreeDto> getTypeSelectListHK(String flag) {
			        ParameterizedTypeReference<JsonResponse<List<TreeDto>>> responseType = new ParameterizedTypeReference<JsonResponse<List<TreeDto>>>() {
			        };
			        MultiValueMap<String, String> request = new LinkedMultiValueMap<String, String>();
					String url = apiBaseUrl + "declareInfo/postDeclareIndexTypeLabelHK";
			        request.add("plateCode", flag);
			        List<TreeDto> list = restClient.post(url, request, responseType).getResult();
			       
			        return list;
			    }
	//lanyuxin 2017-08-22 end
				
    
}

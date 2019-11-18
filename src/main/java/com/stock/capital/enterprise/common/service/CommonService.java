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
import org.springframework.util.Base64Utils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.google.common.base.Charsets;
import com.google.common.base.Throwables;
import com.stock.capital.enterprise.common.constant.Global;
 
import com.stock.core.dto.JsonResponse;
import com.stock.core.dto.MenuDto;
import com.stock.core.dto.OptionDto;
import com.stock.core.dto.TreeDto;
import com.stock.core.exception.ApplicationException;
import com.stock.core.rest.RestClient;
import com.stock.core.service.BaseService;
import com.stock.core.util.CryptoUtil;
import com.stock.core.util.DateUtil;
import com.stock.core.util.JsonUtil;
import com.stock.core.util.SmsClient;
import com.stock.core.util.WebUtil;

@Service
public class CommonService extends BaseService {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(CommonService.class);
     
	@Value("#{app['api.baseUrl']}")
	private String apiBaseUrl;
	
	@Value("#{app['service.gui.baseUrl']}")
    private String guiBaseUrl;
    
    @Value("#{app['service.gui.clientId']}")
    private String clientId;
    
    @Value("#{app['service.gui.clientSecret']}")
    private String clientSecret;

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
				
				
			/**
		     * 处理用户搜索提示
		     */
		    public void dealWithSuggest(String suggestStr,String indexType) {
				//solr 处理用户搜索
		        MultiValueMap<String, String> param = new LinkedMultiValueMap<String, String>();
		        param.add("suggestStr", suggestStr);
		        param.add("indexType",indexType);
		        String urls = apiBaseUrl + "common/dealWithSuggest";
		        ParameterizedTypeReference<JsonResponse<Boolean>> responseType = new ParameterizedTypeReference<JsonResponse<Boolean>>() {
		        };
		        restClient.post(urls, param, responseType);
				//Es 处理用户搜索
				MultiValueMap<String, String> paramEs = new LinkedMultiValueMap<String, String>();
				param.add("suggestStr", suggestStr);
				param.add("indexType",indexType);
				String urlsEs = apiBaseUrl + "common/dealWithSuggestByEs";
				ParameterizedTypeReference<JsonResponse<Boolean>> responseTypeEs = new ParameterizedTypeReference<JsonResponse<Boolean>>() {
				};
				restClient.post(urlsEs, paramEs, responseTypeEs);
		    }
			public List<TreeDto> getIndustryList() {
				 ParameterizedTypeReference<JsonResponse<List<TreeDto>>> responseType = new ParameterizedTypeReference<JsonResponse<List<TreeDto>>>() {
			        };
			        String url = apiBaseUrl + "declareInfo/postDeclareIndexIndustry";
			        List<TreeDto> list = restClient.post(url, null, responseType).getResult();
			        return list;
			}
			
		    /**
		     * 
		     * 取得gui的访问令牌
		     *
		     * @return
		     *
		     */
		    public String getGuiAccessToken() {
		        Map<String, String> accessToken = null;
		        String result = null;
		        String url = guiBaseUrl + "/oauth/token?grant_type=client_credentials&response_type=token&client_id="+clientId+"&client_secret="+clientSecret;
		        ParameterizedTypeReference<Map<String, String>> responseType = new ParameterizedTypeReference<Map<String, String>>() {
		        };
		        try {
		            ResponseEntity<Map<String, String>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, responseType);
		            accessToken = responseEntity.getBody();
		            result = accessToken.get("access_token");
		        } catch(Exception e) {
		            logger.error("get gui access token error. caused by {}", Throwables.getStackTraceAsString(e));
		        }
		        return result;
		    }
		 
		    /**
			 * 解密调用微服接口的数据
			 * @param encryptData
			 * @return
			 */
			public Map<String, Object> getEncryptData(String encryptData){
				String iv = Base64Utils.encodeToString(Global.IV.getBytes(Charsets.UTF_8));
				String encryptKey = Base64Utils.encodeToString(Global.ENCRYPT_KEY.getBytes(Charsets.UTF_8));
				String key = Global.ENCRYPT_KEY + Global.IV;
				String rtn = encryptData.replace(key, "");
				rtn = CryptoUtil.decrypt(rtn, encryptKey, CryptoUtil.AES_CIPHER_ALGORITHM_CBC, iv);
				Map<String, Object> map = JsonUtil.fromJson(rtn, Map.class);
				String jsonString = JsonUtil.toJson(map.get("result"));
		        Map<String, Object> dataMap = JsonUtil.fromJson(jsonString, Map.class);
		        return dataMap;
			}
}

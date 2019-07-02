package com.stock.capital.enterprise.utils;

import com.stock.capital.enterprise.ipoInterfaceH5.controller.IpoInterfaceController;
import com.stock.core.rest.RestClient;
import com.stock.core.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by yangj on 2019/7/1 0001.
 * 操作微信工具类
 */
@Component
public class WXUtils {

    private static final Logger logger = LoggerFactory.getLogger(WXUtils.class);
//   保存accsee_token
    public static AtomicReference<String> atomicReference = new AtomicReference<String>();

    @Autowired
    private RestClient restClient;

    @Value("${minipro.appid}")
    private String appid;
    @Value("${minipro.secret}")
    private String secret;
    @Value("${wechat.access.token}")
    private String accessTokenUrl;
    @Autowired
    private RestTemplate restTemplate;
    //每隔一个小时刷一次
//    @Scheduled(cron = "0 0 0/1 * * ?")
//    @PostConstruct
//    public void updateAccessToken(){
//        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appid+"&secret="+secret;
//        ParameterizedTypeReference<Map<String,Object>> responseType = new ParameterizedTypeReference<Map<String, Object>>() {
//        };
//        Map<String, Object> map = restClient.get(url, responseType);
//        logger.info("定时获取access_token:"+map.get("access_token"));
//        atomicReference.set((String) map.get("access_token"));
//    }



    /**
     * 获取二维码
     * @param id
     * @param companyName
     * @return
     */
    public byte[] getminiqrQr(String id, String companyName) {
        ParameterizedTypeReference<String> responseType = new ParameterizedTypeReference<String>() {
        };
        InputStream inputStream = null;
        OutputStream outputStream = null;
        byte[] result = null;
        try {
            String accseeToken = restClient.get(accessTokenUrl, responseType);
//            String url = "https://api.weixin.qq.com/cgi-bin/wxaapp/createwxaqrcode?access_token="+atomicReference.get();
            String url = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token="+accseeToken;
            Map<String,Object> param = new HashMap<>();
////            param.put("path", "pages/index/index?ipoId=" + id + "&companyName=" + companyName);
            param.put("page", "pages/index/index");
            param.put("scene","ipoId=" + id );
            param.put("width", 430);
            String json = JsonUtil.toJson(param);
//            param.put("auto_color", false);
//            Map<String,Object> line_color = new HashMap<>();
//            line_color.put("r", 0);
//            line_color.put("g", 0);
//            line_color.put("b", 0);
//            param.put("line_color", line_color);
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            HttpEntity requestEntity = new HttpEntity(param,headers);
            logger.info("调用接口获取二维码流文件");
            ResponseEntity<byte[]> entity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, byte[].class,new Object());
            result = entity.getBody();

////            判断是否成功 如果失败刷新token
//            String json = JsonUtil.toJson(result);
//            Map map = JsonUtil.fromJson(json, Map.class);
//            map.get("errorcode")
        } catch (Exception e) {
        } finally {
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return  result;
    }
}

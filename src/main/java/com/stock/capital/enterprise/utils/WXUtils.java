package com.stock.capital.enterprise.utils;

import com.stock.capital.enterprise.ipoInterfaceH5.controller.IpoFileUploadController;
import com.stock.capital.enterprise.ipoInterfaceH5.controller.IpoInterfaceController;
import com.stock.core.rest.RestClient;
import com.stock.core.util.JsonUtil;
import org.apache.commons.lang3.StringUtils;
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
//   保存accsee_token 在集群环境下没有办法应用
//    public static AtomicReference<String> atomicReference = new AtomicReference<String>();

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

    @Autowired
    private IpoFileUploadController fileUploadController;

    /**
     * 获取二维码
     * @param id
     * @param companyName
     * @return
     */
    public byte[] getMiNiQr(String id, String companyName) {
        ParameterizedTypeReference<String> responseType = new ParameterizedTypeReference<String>() {
        };
        InputStream inputStream = null;
        OutputStream outputStream = null;
        byte[] result = null;
        try {
            String accseeToken = restClient.get(accessTokenUrl, responseType);
            String url = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token="+accseeToken;
            Map<String,Object> param = new HashMap<>();
            param.put("page", "pages/index/index");
            param.put("scene","ipoId=" + id );
            param.put("width", 430);
//            透明底色
            param.put("is_hyaline", true);
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            HttpEntity requestEntity = new HttpEntity(param,headers);
            logger.info("调用微信接口获取二维码流文件");
            ResponseEntity<byte[]> entity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, byte[].class,new Object());
            result = entity.getBody();
        } catch (Exception e) {
            logger.info("获取二维码流文件时出错:更新token"+e.getMessage());
            fileUploadController.updateAccessToken();
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

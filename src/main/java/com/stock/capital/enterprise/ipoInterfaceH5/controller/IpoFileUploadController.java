package com.stock.capital.enterprise.ipoInterfaceH5.controller;


import com.obs.services.ObsClient;
import com.stock.core.controller.BaseController;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.ByteArrayInputStream;

@Api(tags = {"IPOH5接口"}, description = "IPOH5接口")
@Controller
@RequestMapping("ipoFileUpload")
public class IpoFileUploadController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(IpoFileUploadController.class);


    /**
     * 文件上传
     * @param content 上传的json内容
     * @param fileName 上传的文件名字
     */
    @RequestMapping(value = "/fileUpload", method = RequestMethod.GET)
    public void fileUpload(String content,String fileName) {
        String endPoint = "https://obs.cn-north-1.myhwclouds.com";
        String ak = "YGJSHR3Z4JN5SQFNPETD";
        String sk = "uJtCMfVxpjYHrvdtjH6zPmFIZ6E5iRHTF4oj4LRa";
        // 创建ObsClient实例
        ObsClient obsClient = new ObsClient(ak, sk, endPoint);
        obsClient.putObject("obs-repo", "ipo-dev/"+fileName+".txt", new ByteArrayInputStream(content.getBytes()));
    }




}

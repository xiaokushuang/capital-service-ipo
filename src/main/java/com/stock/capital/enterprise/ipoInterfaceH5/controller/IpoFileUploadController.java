package com.stock.capital.enterprise.ipoInterfaceH5.controller;


import com.obs.services.ObsClient;
import com.stock.capital.enterprise.ipoCase.dto.IpoCaseListBo;
import com.stock.core.controller.BaseController;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.ByteArrayInputStream;
import java.util.Map;

@Api(tags = {"IPOH5接口"}, description = "IPOH5接口")
@Controller
@RequestMapping("ipoFileUpload")
public class IpoFileUploadController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(IpoFileUploadController.class);

    @Autowired
    private IpoInterfaceController ipoInterfaceController;

    /**
     *
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

    /**
     * 每晚定时把所有科创版数据生成json文件放到华为云上
     */
    public void ipoDataUpload(){
        //查询科创版所有案例
        String ipoPlate = "069001001006";//科创版
        IpoCaseListBo ipoCaseListBo = new IpoCaseListBo();
        ipoCaseListBo.setIpoPlate(ipoPlate);
        Map<String, Object> ipoCaseList = ipoInterfaceController.getIpoCaseList(ipoCaseListBo);
        if (ipoCaseList != null){

        }
    }



}

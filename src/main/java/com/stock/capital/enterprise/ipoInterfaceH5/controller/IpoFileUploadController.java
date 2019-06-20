package com.stock.capital.enterprise.ipoInterfaceH5.controller;

import com.obs.services.ObsClient;
import com.obs.services.model.ObjectMetadata;
import com.stock.capital.enterprise.ipoCase.dto.IpoCaseListVo;
import com.stock.capital.enterprise.ipoInterfaceH5.service.IpoInterfaceService;
import com.stock.core.controller.BaseController;
import com.stock.core.util.JsonUtil;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = {"IPOH5接口"}, description = "IPOH5接口")
@Controller
@RequestMapping("ipoFileUpload")
public class IpoFileUploadController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(IpoFileUploadController.class);

    @Autowired
    private IpoInterfaceController ipoInterfaceController;

    @Autowired
    private IpoInterfaceService ipoInterfaceService;

    /**
     * 文件上传
     *
     * @param content  上传的json内容
     * @param fileName 上传的文件名字
     */
    @RequestMapping(value = "/fileUpload", method = RequestMethod.GET)
    public void fileUpload(String content, String fileName) {
        String endPoint = "https://obs.cn-north-1.myhwclouds.com";
        String ak = "YGJSHR3Z4JN5SQFNPETD";
        String sk = "uJtCMfVxpjYHrvdtjH6zPmFIZ6E5iRHTF4oj4LRa";
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType("text/plain;charset=UTF-8");
        // 创建ObsClient实例
        ObsClient obsClient = new ObsClient(ak, sk, endPoint);
        //测试地址
        //obsClient.putObject("obs-repo", "ipo-dev/"+fileName+".txt", new ByteArrayInputStream(content.getBytes()));
        //生产地址
        obsClient.putObject("obs-repo", "ipo/" + fileName + ".json", new ByteArrayInputStream(content.getBytes()), metadata);
    }

    /**
     * 每晚定时把所有科创版数据生成json文件放到华为云上
     */
    @Scheduled(cron = "0 30 18,12,15 * * ? ")
    public void ipoDataUpload() {
        //查询科创版所有案例
        logger.info("#######【将IpoH5的数据生成json文件放到华为云的同步开始执行###########");
        List<IpoCaseListVo> ipoCaseList = ipoInterfaceService.queryIpoCase(new HashMap());
        logger.info("#######【将IpoH5的数据生成json文件放到华为云时查询到有" + ipoCaseList.size() + "条科创版数据###########");
        if (ipoCaseList != null) {
            for (int i = 0; i < ipoCaseList.size(); i++) {
                try {
                    Map<String, Object> data = ipoInterfaceController.ipoCaseH5(ipoCaseList.get(i).getId());
                    fileUpload(JsonUtil.toJsonNoNull(data), ipoCaseList.get(i).getId());
                } catch (Exception e) {
                    logger.info("#######【将IpoH5的数据的json文件上传华为云时主键：" + ipoCaseList.get(i).getId() + "数据出错###########");
                }
            }
        }
    }

    /**
     * 每晚定时把所有科创版数据生成json文件放到华为云上
     */
    @Scheduled(cron = "0 20 18,12,15 * * ? ")
    public void ipoMarchDataUpload() {
        //查询科创版所有案例
        logger.info("#######【将IpoH5的数据生成json文件放到华为云的同步开始执行###########");
        List<IpoCaseListVo> ipoCaseList = ipoInterfaceService.queryAllMatchIpoCase();
        logger.info("#######【将Ipo案例注册生效的数据生成json文件放到华为云时查询到有" + ipoCaseList.size() + "条科创版数据###########");
        if (ipoCaseList != null) {
            try {
                fileUpload(JsonUtil.toJsonNoNull(ipoCaseList),"ipoRandomData");
            } catch (Exception e) {
                logger.info("#######【将IpoH5的数据的json文件上传华为云时： ipoRandomData 数据出错###########");
            }
        }
    }
}

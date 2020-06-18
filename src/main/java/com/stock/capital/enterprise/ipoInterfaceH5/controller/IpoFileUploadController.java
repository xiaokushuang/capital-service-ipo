package com.stock.capital.enterprise.ipoInterfaceH5.controller;

import com.obs.services.ObsClient;
import com.obs.services.model.ObjectMetadata;
import com.stock.capital.enterprise.ipoCase.dto.IpoCaseListVo;
import com.stock.capital.enterprise.ipoInterfaceH5.service.IpoInterfaceService;
import com.stock.core.controller.BaseController;
import com.stock.core.rest.RestClient;
import com.stock.core.util.JsonUtil;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;
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

    @Value("${obs.upload}")
    private String obsUpload;

    @Autowired
    private RestClient restClient;

    @Value("${minipro.appid}")
    private String appid;
    @Value("${minipro.secret}")
    private String secret;

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

        obsClient.putObject("obs-repo", obsUpload + fileName + ".json", new ByteArrayInputStream(content.getBytes()), metadata);
    }

    /**
     * 每晚定时把所有科创版数据生成json文件放到华为云上
     */
    @Scheduled(cron = "0 15 02 * * ? ")
    public void ipoDataUpload() {
        //查询科创版所有案例
        logger.info("#######【将IpoH5科创板的数据生成json文件放到华为云的同步开始执行###########");
        Map<String, Object> map = new HashMap<>();
        map.put("ipoPlate","069001001006");
        List<IpoCaseListVo> ipoCaseList = ipoInterfaceService.queryIpoCase(map);
        logger.info("#######【将IpoH5科创板的数据生成json文件放到华为云时查询到有" + ipoCaseList.size() + "条科创版数据###########");
        if (ipoCaseList != null) {
            for (int i = 0; i < ipoCaseList.size(); i++) {
                try {
                    Map<String, Object> data = ipoInterfaceController.ipoCaseH5(ipoCaseList.get(i).getId(),"069001001006");
                    fileUpload(JsonUtil.toJsonNoNull(data), ipoCaseList.get(i).getId());
                    logger.info("#######【将IpoH5科创板的数据生成json文件放到华为云的同步" + ipoCaseList.get(i).getId() + "成功###########");
                } catch (Exception e) {
                    logger.info("#######【将IpoH5科创板的数据的json文件上传华为云时主键：" + ipoCaseList.get(i).getId() + "数据出错###########");
                }
            }
        }
    }

    /**
     * 每晚定时把所有创业板版数据生成json文件放到华为云上
     */
    //@Scheduled(cron = "0 25 02 * * ? ")
    @Scheduled(initialDelay = 2000, fixedDelay = 20000000)
    public void ipoDataCybUpload() {
        //查询科创版所有案例
        logger.info("#######【将IpoH5创业板的数据生成json文件放到华为云的同步开始执行###########");
        Map<String, Object> map = new HashMap<>();
        map.put("ipoPlate","069001002002");
        List<IpoCaseListVo> ipoCaseList = ipoInterfaceService.queryIpoCase(map);
        logger.info("#######【将IpoH5创业板的数据生成json文件放到华为云时查询到有" + ipoCaseList.size() + "条科创版数据###########");
        if (ipoCaseList != null) {
            for (int i = 0; i < ipoCaseList.size(); i++) {
                try {
                    Map<String, Object> data = ipoInterfaceController.ipoCaseH5(ipoCaseList.get(i).getId(),"069001002002");
                    fileUpload(JsonUtil.toJsonNoNull(data), ipoCaseList.get(i).getId());
                    logger.info("#######【将IpoH5创业板的数据生成json文件放到华为云的同步" + ipoCaseList.get(i).getId() + "成功###########");
                } catch (Exception e) {
                    logger.info("#######【将IpoH5创业板的数据的json文件上传华为云时主键：" + ipoCaseList.get(i).getId() + "数据出错###########");
                }
            }
        }
    }

    /**
     * 每晚定时把所有科创版数据生成json文件放到华为云上
     */
    @Scheduled(cron = "0 18 02 * * ? ")
    public void ipoMarchDataUpload() {
        //查询科创版所有案例
        logger.info("#######【将IpoH5的数据生成json文件放到华为云的同步开始执行###########");
        List<IpoCaseListVo> ipoCaseList = ipoInterfaceService.queryAllMatchIpoCase();
        logger.info("#######【将Ipo案例注册生效的数据生成json文件放到华为云时查询到有" + ipoCaseList.size() + "条科创版数据###########");
        if (ipoCaseList != null) {
            try {
                fileUpload(JsonUtil.toJsonNoNull(ipoCaseList), "ipoRandomData");
            } catch (Exception e) {
                logger.info("#######【将IpoH5的数据的json文件上传华为云时： ipoRandomData 数据出错###########");
            }
        }
    }

    /**
     * 每晚定时把所有创业板数据生成json文件放到华为云上
     */
    @Scheduled(cron = "0 28 02 * * ? ")
    public void ipoMarchCybDataUpload() {
        //查询科创版所有案例
        logger.info("#######【将IpoH5的数据生成json文件放到华为云的同步开始执行###########");
        List<IpoCaseListVo> ipoCaseCybList = ipoInterfaceService.queryAllMatchIpoCaseCyb();
        logger.info("#######【将Ipo案例注册生效的数据生成json文件放到华为云时查询到有" + ipoCaseCybList.size() + "条科创版数据###########");
        if (ipoCaseCybList != null) {
            try {
                fileUpload(JsonUtil.toJsonNoNull(ipoCaseCybList), "ipoRandomCybData");
            } catch (Exception e) {
                logger.info("#######【将IpoH5的数据的json文件上传华为云时： ipoRandomData 数据出错###########");
            }
        }
    }

    /**
     * 每晚定时把所有科创版数据生成json文件放到华为云上(登录页的联想)
     */
    @Scheduled(cron = "0 10 02 * * ? ")
    public void ipoCaseDataUpload() {
        //查询科创版所有案例
        logger.info("#######【将科创板IpoH5登录页的联想的数据生成json文件放到华为云的同步开始执行###########");
        Map<String, Object> map = new HashMap<>();
        map.put("ipoPlate","069001001006");
        List<IpoCaseListVo> ipoCaseList = ipoInterfaceService.queryIpoCase(map);
        logger.info("#######【将科创板IpoH5登录页的联想的数据生成json文件放到华为云时查询到有" + ipoCaseList.size() + "条科创版数据###########");
        if (ipoCaseList != null) {
            try {
                fileUpload(JsonUtil.toJsonNoNull(ipoCaseList), "ipoCaseData");
            } catch (Exception e) {
                logger.info("#######【将科创板IpoH5登录页的联想的数据生成json文件上传华为云时： ipoCaseData 数据出错###########");
            }
        }
    }

    /**
     * 每晚定时把所有创业版数据生成json文件放到华为云上(登录页的联想)
     */
    @Scheduled(cron = "0 20 02 * * ? ")
    public void ipoCaseCybDataUpload() {
        //查询创业版所有案例
        logger.info("#######【将创业板IpoH5登录页的联想的数据生成json文件放到华为云的同步开始执行###########");
        Map<String, Object> map = new HashMap<>();
        map.put("ipoPlate","069001002002");
        List<IpoCaseListVo> ipoCaseCybList = ipoInterfaceService.queryIpoCase(map);
        logger.info("#######【将创业板IpoH5登录页的联想的数据生成json文件放到华为云时查询到有" + ipoCaseCybList.size() + "条科创版数据###########");
        if (ipoCaseCybList != null) {
            try {
                fileUpload(JsonUtil.toJsonNoNull(ipoCaseCybList), "ipoCaseCybData");
            } catch (Exception e) {
                logger.info("#######【将创业板IpoH5登录页的联想的数据生成json文件上传华为云时： ipoCaseData 数据出错###########");
            }
        }
    }

    @RequestMapping(value = "/ipoDataUploadSpecComById")
    public void ipoDataUploadSpecComById(Map map) {
        //查询科创版所有案例
        logger.info("#######【将IpoH5的数据生成json文件放到华为云的同步开始执行###########");
        map.put("ipoPlate","069001001006");
        List<IpoCaseListVo> ipoCaseList = ipoInterfaceService.queryIpoCaseById(map);
        logger.info("#######【将IpoH5的数据生成json文件放到华为云时查询到有" + ipoCaseList.size() + "条科创版数据###########");
        if (ipoCaseList != null) {
            for (int i = 0; i < ipoCaseList.size(); i++) {
                try {
                    Map<String, Object> data = ipoInterfaceController.ipoCaseH5(ipoCaseList.get(i).getId(),"069001001006");
                    fileUpload(JsonUtil.toJsonNoNull(data), ipoCaseList.get(i).getId());
                    logger.info("#######【将IpoH5的数据生成json文件放到华为云的同步" + ipoCaseList.get(i).getId() + "成功###########");
                } catch (Exception e) {
                    logger.info("#######【将IpoH5的数据的json文件上传华为云时主键：" + ipoCaseList.get(i).getId() + "数据出错###########");
                }
            }
        }
    }

    @RequestMapping(value = "/ipoCybDataUploadSpecComById")
    public void ipoCybDataUploadSpecComById(Map map) {
        //查询科创版所有案例
        logger.info("#######【将IpoH5的数据生成json文件放到华为云的同步开始执行###########");
        map.put("ipoPlate","069001002002");
        List<IpoCaseListVo> ipoCaseList = ipoInterfaceService.queryIpoCaseById(map);
        logger.info("#######【将IpoH5的数据生成json文件放到华为云时查询到有" + ipoCaseList.size() + "条科创版数据###########");
        if (ipoCaseList != null) {
            for (int i = 0; i < ipoCaseList.size(); i++) {
                try {
                    Map<String, Object> data = ipoInterfaceController.ipoCaseH5(ipoCaseList.get(i).getId(),"069001002002");
                    fileUpload(JsonUtil.toJsonNoNull(data), ipoCaseList.get(i).getId());
                    logger.info("#######【将IpoH5的数据生成json文件放到华为云的同步" + ipoCaseList.get(i).getId() + "成功###########");
                } catch (Exception e) {
                    logger.info("#######【将IpoH5的数据的json文件上传华为云时主键：" + ipoCaseList.get(i).getId() + "数据出错###########");
                }
            }
        }
    }

    @RequestMapping(value = "/ipoDataUploadAllCom")
    public void ipoDataUploadAllCom(@RequestParam(value = "id", required = false, defaultValue = "") String id) {
        ipoCaseDataUpload();
        ipoCaseCybDataUpload();
        ipoMarchDataUpload();
        ipoMarchCybDataUpload();
        if (StringUtils.isEmpty(id)) {
            ipoDataUpload();
            ipoDataCybUpload();
        } else {
            Map tempMap = new HashMap();
            tempMap.put("id", id);
            ipoDataUploadSpecComById(tempMap);
            ipoCybDataUploadSpecComById(tempMap);
        }
    }
    //每隔一个小时刷一次
    @Scheduled(cron = "0 0 0/1 * * ?")
    @PostConstruct
    public void updateAccessToken(){
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appid+"&secret="+secret;
        ParameterizedTypeReference<Map<String,Object>> responseType = new ParameterizedTypeReference<Map<String, Object>>() {
        };
        Map<String, Object> map = restClient.get(url, responseType);
        logger.info("每小时刷新一次,定时刷新access_token:"+map.get("access_token"));
//        采用wechatAccessToken名字存储token
        fileUpload((String) map.get("access_token"),"wechatAccessToken");
    }
}

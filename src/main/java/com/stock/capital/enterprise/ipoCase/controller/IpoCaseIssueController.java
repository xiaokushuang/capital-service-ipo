package com.stock.capital.enterprise.ipoCase.controller;

import com.stock.capital.enterprise.ipoCase.dto.*;
import com.stock.capital.enterprise.ipoCase.service.IpoProcessService;
import com.stock.capital.enterprise.ipoCase.service.IssueSituationService;
import com.stock.core.dto.JsonResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"IPO发行情况接口类"}, description = "IPO发行情况接口描述")
@RestController
@RequestMapping("issue")
public class IpoCaseIssueController {

    @Autowired
    private IssueSituationService issueSituationService;

    @Autowired
    private IpoProcessService ipoProcessService;


    @ApiOperation(value = "发行数据接口", notes = "发行数据接口描述")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "案例id", required = true, paramType = "query",
                    dataType = "String")
    })
    @RequestMapping(value = "/issueData", method = RequestMethod.GET)
    public JsonResponse<IssueDataDto> issueData(@RequestParam("id") String id) {
        JsonResponse<IssueDataDto> response = new JsonResponse<>();
        response.setResult(issueSituationService.getIssueData(id));
        return response;
    }


    @ApiOperation(value = "发行费用接口", notes = "发行费用接口描述")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "案例id", required = true, paramType = "query",
                    dataType = "String")
    })
    @RequestMapping(value = "/issueFeeData", method = RequestMethod.GET)
    public JsonResponse<List<IssueFeeDto>> issueFeeData(@RequestParam("id") String id) {
        JsonResponse<List<IssueFeeDto>> response = new JsonResponse<>();
        response.setResult(issueSituationService.getIssueFeeData(id));
        return response;
    }
    @RequestMapping(value = "/getPlacementData", method = RequestMethod.GET)
    public JsonResponse<StrategicPlacementMainDto> getPlacementData(@RequestParam("id") String id) {
        JsonResponse<StrategicPlacementMainDto> response = new JsonResponse<>();
        response.setResult(issueSituationService.getPlacementData(id));
        return response;
    }

    @RequestMapping(value = "/getPriceAfterIssuance", method = RequestMethod.GET)
    public JsonResponse<Map<String,Object>> getPriceAfterIssuance(@RequestParam("id") String id) {
        JsonResponse<Map<String,Object>> response = new JsonResponse<>();
        List<List<Object>> echartResult = new LinkedList<>();
        List<Map<String,Object>> tableResult = new LinkedList<>();
        Map<String,Object> result = null;
        BigDecimal issuePrice = null;
//        获取发行后股本
        boolean issueFlag = false;
        IssueDataDto issueData = issueSituationService.getIssueData(id);
        if (issueData != null && issueData.getNextIssueNum() != null){
            issueFlag = true;
        }
//        先判断是否存在上市节点 通过processType 存储证券代码
        IpoProListDto ipoProgressDto =  ipoProcessService.getIssueData(id);
        if (ipoProgressDto != null && StringUtils.isNotEmpty(ipoProgressDto.getProcessTime()) && StringUtils.isNotEmpty(ipoProgressDto.getProgressType())){
            List<Map<String,Object>> fromList = issueSituationService.getPriceAfterIssuance(ipoProgressDto.getProcessTime(),ipoProgressDto.getProgressType());
            if (fromList !=null && fromList.size() > 0){
                result = new HashMap<>();
//                在这里获取第一天 第五天 第10天  第二十天的数据 包括计算涨跕福
                for (int i =0;i<fromList.size();i++) {
                    Map<String, Object> stringStringMap = fromList.get(i);
                    List<Object> subs = new LinkedList<>();
//                    日期
//                    收盘价
//                    如果没有数据 就跳过
                    if (stringStringMap.get("OPEN") == null || stringStringMap.get("NEW") == null ){
                        continue;
                    }
                    subs.add(stringStringMap.get("trade_date"));
                    subs.add(stringStringMap.get("OPEN"));
//                    开盘价
                    subs.add(stringStringMap.get("NEW"));
//                    最低价
                    subs.add(stringStringMap.get("LOW"));
//                    最高价
                    subs.add(stringStringMap.get("HIGH"));
//                    成交量
                    subs.add(stringStringMap.get("TVOL"));
                    echartResult.add(subs);
                    if (i == 0 ){
                        issuePrice = (BigDecimal) stringStringMap.get("LCLOSE");
                        putData("发行首日",tableResult,stringStringMap,issuePrice,issueFlag,issueData);

                    }
                    if (i == 4){
                        putData("发行后第5个交易日",tableResult,stringStringMap,issuePrice, issueFlag, issueData);
                    }
                    if (i == 9){
                        putData("发行后第10个交易日",tableResult,stringStringMap,issuePrice, issueFlag, issueData);
                    }
                    if (i == 19){
                        putData("发行后第20个交易日",tableResult,stringStringMap,issuePrice, issueFlag, issueData);
                    }
                }
                result.put("echartResult",echartResult);
                result.put("tableResult",tableResult);
            }
        }
        response.setResult(result);
        return response;
    }

    private void putData(String title, List<Map<String, Object>> tableResult, Map<String, Object> stringStringMap, BigDecimal issuePrice, boolean issueFlag, IssueDataDto issueData) {
        BigDecimal newPrice = (BigDecimal) stringStringMap.get("NEW");
        BigDecimal radio = newPrice.divide(issuePrice, 4, BigDecimal.ROUND_HALF_UP).subtract(new BigDecimal(1)).multiply(new BigDecimal(100));
        stringStringMap.put("title",title);
        stringStringMap.put("CHG",radio);
        if (issueFlag){
            BigDecimal marketValue = newPrice.multiply(issueData.getNextIssueNum()).divide(new BigDecimal(10000), 2, BigDecimal.ROUND_HALF_UP);
            stringStringMap.put("marketValue",marketValue);
        }else{
            stringStringMap.put("marketValue",null);
        }
        tableResult.add(stringStringMap);
    }


    @ApiOperation(value = "估算费用接口", notes = "估算费用接口描述")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "案例id", required = true, paramType = "query",dataType = "String")
    })
    @RequestMapping(value = "/estimateType", method = RequestMethod.GET)
    public JsonResponse<String> estimateType(@RequestParam("id") String id){
        JsonResponse<String> response = new JsonResponse<>();
        response.setResult(issueSituationService.getEstimateType(id));
        return response;
    }

    @ApiOperation(value = "同行业上市公司综合毛利率接口", notes = "同行业上市公司综合毛利率接口描述")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "案例id", required = true, paramType = "query",
                    dataType = "String")
    })
    @RequestMapping(value = "/industryRateData", method = RequestMethod.GET)
    public JsonResponse<List<IndustryCompareRateDto>> industryRateData(
            @RequestParam("id") String id) {
        JsonResponse<List<IndustryCompareRateDto>> response = new JsonResponse<>();
        response.setResult(issueSituationService.getIndustryRateData(id));
        return response;
    }
}

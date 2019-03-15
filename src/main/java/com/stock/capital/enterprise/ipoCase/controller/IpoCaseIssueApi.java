package com.stock.capital.enterprise.ipoCase.controller;

import com.stock.capital.enterprise.ipoCase.dto.IndustryCompareRateDto;
import com.stock.capital.enterprise.ipoCase.dto.IssueDataDto;
import com.stock.capital.enterprise.ipoCase.dto.IssueFeeDto;
import com.stock.capital.enterprise.ipoCase.service.IssueSituationService;
import com.stock.core.dto.JsonResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"IPO发行情况接口类"}, description = "IPO发行情况接口描述")
@RestController
@RequestMapping("api/issue")
public class IpoCaseIssueApi {

    @Autowired
    private IssueSituationService issueSituationService;


    @ApiOperation(value = "发行数据接口", notes = "发行数据接口描述")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "orgCode", value = "东财内码", required = true, paramType = "query",
            dataType = "String")
    })
    @RequestMapping(value = "/issueData", method = RequestMethod.GET)
    public JsonResponse<IssueDataDto> issueData(@RequestParam("orgCode") String orgCode) {
        JsonResponse<IssueDataDto> response = new JsonResponse<>();
        response.setResult(issueSituationService.getIssueData(orgCode));
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

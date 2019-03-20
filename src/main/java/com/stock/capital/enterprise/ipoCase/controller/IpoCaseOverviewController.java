package com.stock.capital.enterprise.ipoCase.controller;

import com.stock.capital.enterprise.ipoCase.dto.CompanyOverviewVo;
import com.stock.capital.enterprise.ipoCase.dto.IntermediaryOrgDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoPersonInfoDto;
import com.stock.capital.enterprise.ipoCase.dto.MainCompetitorInfoDto;
import com.stock.capital.enterprise.ipoCase.dto.MainIncomeVo;
import com.stock.capital.enterprise.ipoCase.dto.OtherMarketInfoDto;
import com.stock.capital.enterprise.ipoCase.dto.SupplierCustomerMainDto;
import com.stock.capital.enterprise.ipoCase.service.CompanyOverviewService;
import com.stock.core.dto.JsonResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"IPO公司概览接口类"}, description = "IPO公司概览接口描述")
@RestController
@RequestMapping("companyOverview")
public class IpoCaseOverviewController {

    @Autowired
    private CompanyOverviewService companyOverviewService;

    @ApiOperation(value = "案例详细接口", notes = "案例详细接接口描述")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "案例id", required = true, paramType = "query",
            dataType = "String")
    })
    @RequestMapping(value = "/caseDetail", method = RequestMethod.GET)
    public JsonResponse<CompanyOverviewVo> caseDetail(@RequestParam("id") String id) {
        JsonResponse<CompanyOverviewVo> response = new JsonResponse<>();
        CompanyOverviewVo companyOverviewVo = companyOverviewService.getIpoCaseDetail(id);
        response.setResult(companyOverviewVo);
        return response;
    }

    @ApiOperation(value = "资本市场接口", notes = "资本市场接口描述")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "案例id", required = true, paramType = "query",
            dataType = "String")
    })
    @RequestMapping(value = "/marketData", method = RequestMethod.GET)
    public JsonResponse<List<OtherMarketInfoDto>> marketData(@RequestParam("id") String id) {
        JsonResponse<List<OtherMarketInfoDto>> response = new JsonResponse<>();
        response.setResult(companyOverviewService.getMarketData(id));
        return response;
    }

    @ApiOperation(value = "股东信息接口", notes = "股东信息接口描述")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "案例id", required = true, paramType = "query",
            dataType = "String")
    })
    @RequestMapping(value = "/shareHolderData", method = RequestMethod.GET)
    public JsonResponse<List<IpoPersonInfoDto>> shareHolderData(@RequestParam("id") String id) {
        JsonResponse<List<IpoPersonInfoDto>> response = new JsonResponse<>();
        response.setResult(companyOverviewService.getShareData(id));
        return response;
    }

    @ApiOperation(value = "主要竞争对手接口", notes = "主要竞争对手接口描述")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "案例id", required = true, paramType = "query",
            dataType = "String")
    })
    @RequestMapping(value = "/competitorData", method = RequestMethod.GET)
    public JsonResponse<List<MainCompetitorInfoDto>> competitorData(@RequestParam("id") String id) {
        JsonResponse<List<MainCompetitorInfoDto>> response = new JsonResponse<>();
        response.setResult(companyOverviewService.getCompetitorData(id));
        return response;
    }

    @ApiOperation(value = "报告期主要供应商及客户接口", notes = "报告期主要供应商及客户接口描述")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "案例id", required = true, paramType = "query",
            dataType = "String")
    })
    @RequestMapping(value = "/supplierCustomerData", method = RequestMethod.GET)
    public JsonResponse<Map<String, List<SupplierCustomerMainDto>>> supplierCustomerData(
        @RequestParam("id") String id) {
        JsonResponse<Map<String, List<SupplierCustomerMainDto>>> response = new JsonResponse<>();
        response.setResult(companyOverviewService.getSupCusData(id));
        return response;
    }

    @ApiOperation(value = "主营业务收入构成接口", notes = "主营业务收入构成接口描述")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "案例id", required = true, paramType = "query",
            dataType = "String")
    })
    @RequestMapping(value = "/incomeData", method = RequestMethod.GET)
    public JsonResponse<MainIncomeVo> incomeData(@RequestParam("id") String id) {
        JsonResponse<MainIncomeVo> response = new JsonResponse<>();
        response.setResult(companyOverviewService.getIncomeData(id));
        return response;
    }

    @ApiOperation(value = "中介机构接口", notes = "中介机构接口描述")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "案例id", required = true, paramType = "query",
            dataType = "String"),
        @ApiImplicitParam(name = "validFlag", value = "状态", paramType = "query",
            dataType = "String")
    })
    @RequestMapping(value = "/intermediaryOrgData", method = RequestMethod.GET)
    public JsonResponse<Map<String, List<IntermediaryOrgDto>>> intermediaryOrgData(
        @RequestParam("id") String id,
        @RequestParam(value = "validFlag", required = false) String validFlag) {
        JsonResponse<Map<String, List<IntermediaryOrgDto>>> response = new JsonResponse<>();
        response.setResult(companyOverviewService.getIntermediaryOrgData(id, validFlag));
        return response;
    }
}

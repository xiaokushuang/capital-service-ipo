package com.stock.capital.enterprise.ipoCase.controller;

import com.stock.capital.enterprise.ipoCase.dto.IpoFinanceDto;
import com.stock.capital.enterprise.ipoCase.service.IpoFinanceService;
import com.stock.core.controller.BaseController;
import com.stock.core.dto.JsonResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = {"IPO财务信息接口类"}, description = "IPO财务信息接口类描述")
@RestController
@RequestMapping("ipoFinance")
public class IpoFinanceController extends BaseController {
    @Autowired
    private IpoFinanceService ipoFinanceService;

    @ApiOperation(value = "财务信息 资产与负债情况接口", notes = "资产与负债情况接口描述")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "案例id", required = true, paramType = "query", dataType = "String")
    })
    @RequestMapping(value = "/selectFinanceList", method = RequestMethod.GET)
    public JsonResponse<IpoFinanceDto> selectFinanceList(String id) {
        JsonResponse<IpoFinanceDto> response = new JsonResponse<>();
        IpoFinanceDto ipoFinanceDto = ipoFinanceService.selectFinanceList(id);
        response.setResult(ipoFinanceDto);
        return response;
    }

    @ApiOperation(value = "财务信息 财务总体情况接口", notes = "财务总体情况接口描述")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "案例id", required = true, paramType = "query", dataType = "String")
    })
    @RequestMapping(value = "/selectFinanceOverList", method = RequestMethod.GET)
    public JsonResponse<IpoFinanceDto> selectFinanceOverList(String id) {
        JsonResponse<IpoFinanceDto> response = new JsonResponse<>();
        IpoFinanceDto ipoFinanceDto = ipoFinanceService.selectFinanceOverList(id);
        response.setResult(ipoFinanceDto);
        return response;
    }

    @ApiOperation(value = "财务信息 收入与利润情况接口", notes = "收入与利润情况接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "案例id", required = true, paramType = "query", dataType = "String")
    })
    @RequestMapping(value = "/selectFinanceProfitList", method = RequestMethod.GET)
    public JsonResponse<IpoFinanceDto> selectFinanceProfitList(String id) {
        JsonResponse<IpoFinanceDto> response = new JsonResponse<>();
        IpoFinanceDto ipoFinanceDto = ipoFinanceService.selectFinanceProfitList(id);
        response.setResult(ipoFinanceDto);
        return response;
    }

    @ApiOperation(value = "财务信息 主要财务指标接口", notes = "主要财务指标接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "案例id", required = true, paramType = "query", dataType = "String")
    })
    @RequestMapping(value = "/selectMainIndexList", method = RequestMethod.GET)
    public JsonResponse<IpoFinanceDto> selectMainIndexList(String id) {
        JsonResponse<IpoFinanceDto> response = new JsonResponse<>();
        IpoFinanceDto ipoFinanceDto = ipoFinanceService.selectMainIndexList(id);
        response.setResult(ipoFinanceDto);
        return response;
    }
}

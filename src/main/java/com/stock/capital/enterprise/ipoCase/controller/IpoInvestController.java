package com.stock.capital.enterprise.ipoCase.controller;

import com.stock.capital.enterprise.ipoCase.dto.IpoInvestItemDto;
import com.stock.capital.enterprise.ipoCase.service.IpoInvestService;
import com.stock.core.controller.BaseController;
import com.stock.core.dto.JsonResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = {"募集资金接口类"}, description = "募集资金接口类描述")
@RestController
@RequestMapping("ipoInvest")
public class IpoInvestController extends BaseController {
    @Autowired
    private IpoInvestService ipoInvestService;

    @ApiOperation(value = "ipo募集资金接口", notes = "根据caseId获取募集资金")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "案例id", required = true, paramType = "query", dataType = "String")
    })
    @RequestMapping(value = "/selectInvestItem", method = RequestMethod.GET)
    public JsonResponse<List<IpoInvestItemDto>> selectInvestItem(String id) {
        JsonResponse<List<IpoInvestItemDto>> response = new JsonResponse<>();
        List<IpoInvestItemDto> resultList = ipoInvestService.selectInvestItem(id);
        response.setResult(resultList);
        return response;
    }

}

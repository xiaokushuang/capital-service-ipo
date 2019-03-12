package com.stock.capital.enterprise.ipoCase.controller;

import com.stock.capital.enterprise.ipoCase.dto.TreeTypeProgressDto;
import com.stock.capital.enterprise.ipoCase.service.IpoProcessService;
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

@Api(tags = {"IPO进程接口类"},description = "")
@RestController
@RequestMapping("api/ipoProcess")
public class IpoProcessController extends BaseController {
    @Autowired
    private IpoProcessService ipoProcessService;

    @ApiOperation(value = "ipo进程接口", notes = "根据caseId获取ipo进程")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主表id", required = true, dataType = "String")
    })
    @RequestMapping(value = "/selectProcessList", method = RequestMethod.POST)
    public JsonResponse<TreeTypeProgressDto> selectProcessList(String id){
        JsonResponse<TreeTypeProgressDto> response = new JsonResponse<>();
        TreeTypeProgressDto resultList =  ipoProcessService.selectProcessList(id);
        response.setResult(resultList);
        return response;
    }
}

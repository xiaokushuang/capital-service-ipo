package com.stock.capital.enterprise.ipoCase.controller;

import com.stock.capital.enterprise.ipoCase.dto.IpoExamineDto;
import com.stock.capital.enterprise.ipoCase.service.IpoExamineService;
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

@Api(tags = {"IPO审核反馈接口类"}, description = "IPO审核反馈接口类描述")
@RestController
@RequestMapping("ipoExamine")
public class IpoExamineController extends BaseController {
    @Autowired
    private IpoExamineService ipoExamineService;

    @ApiOperation(value = "IPO审核反馈初始化接口", notes = "IPO审核反馈初始化接口描述")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "案例id", required = true, paramType = "query", dataType = "String"),
    })
    @RequestMapping(value = "/selectFeedbackList", method = RequestMethod.GET)
    public JsonResponse<IpoExamineDto> selectExamineList(String id){
        JsonResponse<IpoExamineDto> response = new JsonResponse<>();
        IpoExamineDto resultDto = ipoExamineService.selectExamineList(id);
        response.setResult(resultDto);
        return response;
    }

}

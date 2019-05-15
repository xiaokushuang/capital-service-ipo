package com.stock.capital.enterprise.ipoCase.controller;

import com.stock.capital.enterprise.ipoCase.dto.IpoExamineDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoFeedbackDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoMemberDto;
import com.stock.capital.enterprise.ipoCase.service.IpoExamineService;
import com.stock.capital.enterprise.ipoCase.service.IpoFeedbackService;
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

@Api(tags = {"IPO审核反馈接口类"}, description = "IPO审核反馈接口类描述")
@RestController
@RequestMapping("ipoExamine")
public class IpoExamineController extends BaseController {
    @Autowired
    private IpoExamineService ipoExamineService;
    @Autowired
    private IpoFeedbackService ipoFeedbackService;

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

    @ApiOperation(value = "IPO发审委委员接口", notes = "IPO发审委委员接口描述")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "案例id", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "examineDate", value = "发审会日期", required = true, paramType = "query", dataType = "String"),
    })
    @RequestMapping(value = "/selectMemberList", method = RequestMethod.GET)
    public JsonResponse<List<IpoMemberDto>> selectMemberList(String id,String examineDate){
        JsonResponse<List<IpoMemberDto>> response = new JsonResponse<>();
        List<IpoMemberDto> resultDto = ipoExamineService.selectMemberList(id,examineDate);
        response.setResult(resultDto);
        return response;
    }

    @ApiOperation(value = "IPO审核反馈初始化接口", notes = "IPO审核反馈初始化接口描述")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "案例id", required = true, paramType = "query", dataType = "String"),
    })
    @RequestMapping(value = "/selectNewExamineList", method = RequestMethod.GET)
    public JsonResponse<IpoFeedbackDto> selectNewExamineList(String id){
        JsonResponse<IpoFeedbackDto> response = new JsonResponse<>();
        IpoFeedbackDto resultDto = ipoExamineService.selectNewExamineList(id);
        response.setResult(resultDto);
        return response;
    }

    @ApiOperation(value = "IPO审核反馈问题列表接口", notes = "IPO审核反馈问题列表接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "letterId", value = "函件id", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "firstLabelId", value = "所属一级标签", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "secondLabelId", value = "所属二级标签", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "onlyResponse", value = "是否只展示有回复问题 1:是", paramType = "query", dataType = "String")
    })
    @RequestMapping(value = "/selectNewQuestionList", method = RequestMethod.GET)
    public JsonResponse<List<IpoFeedbackDto>> selectNewQuestionList(
            String letterId, String firstLabelId, String secondLabelId, String onlyResponse) {
        JsonResponse<List<IpoFeedbackDto>> response = new JsonResponse<>();
        List<IpoFeedbackDto> resultList = ipoFeedbackService.selectNewQuestionList(
                letterId, firstLabelId, secondLabelId, onlyResponse);
        response.setResult(resultList);
        return response;
    }
}

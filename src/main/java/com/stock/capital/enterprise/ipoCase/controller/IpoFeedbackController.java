package com.stock.capital.enterprise.ipoCase.controller;

import com.stock.capital.enterprise.ipoCase.dto.IpoFeedbackDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoQuestionLabelDto;
import com.stock.capital.enterprise.ipoCase.service.IpoFeedbackService;
import com.stock.core.controller.BaseController;
import com.stock.core.dto.JsonResponse;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = {"IPO反馈意见接口类"}, description = "IPO反馈意见接口类描述")
@RestController
@RequestMapping("ipoFeedback")
public class IpoFeedbackController extends BaseController {
    @Autowired
    private IpoFeedbackService ipoFeedbackService;

    @ApiOperation(value = "反馈意见初始化接口", notes = "反馈意见初始化接口描述")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "案例id", required = true, paramType = "query", dataType = "String"),
    })
    @RequestMapping(value = "/selectFeedbackList", method = RequestMethod.GET)
    public JsonResponse<List<IpoFeedbackDto>> selectFeedbackList(IpoFeedbackDto ipoFeedbackDto) {
        JsonResponse<List<IpoFeedbackDto>> response = new JsonResponse<>();
        List<IpoFeedbackDto> resultList = ipoFeedbackService.selectFeedbackList(ipoFeedbackDto);
        response.setResult(resultList);
        return response;
    }

    @ApiOperation(value = "二级标签列表接口", notes = "二级标签列表接口描述")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "letterId", value = "函件id", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "parentId", value = "一级标签id", required = true, paramType = "query", dataType = "String")
    })
    @RequestMapping(value = "/selectSecondLabelList", method = RequestMethod.GET)
    public JsonResponse<List<IpoQuestionLabelDto>> selectSecondLabelList(String letterId, String parentId) {
        JsonResponse<List<IpoQuestionLabelDto>> response = new JsonResponse<>();
        List<IpoQuestionLabelDto> resultList = ipoFeedbackService.selectSecondLabelList(letterId, parentId);
        response.setResult(resultList);
        return response;
    }

    @ApiOperation(value = "反馈意见问题列表接口", notes = "反馈意见问题列表接口描述")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "letterId", value = "函件id", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "firstLabelId", value = "所属一级标签", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "secondLabelId", value = "所属二级标签", paramType = "query", dataType = "String")
    })
    @RequestMapping(value = "/selectQuestionListByLetterId", method = RequestMethod.GET)
    public JsonResponse<List<IpoFeedbackDto>> selectQuestionListByLetterId(IpoFeedbackDto ipoFeedbackDto) {
        JsonResponse<List<IpoFeedbackDto>> response = new JsonResponse<>();
        List<IpoFeedbackDto> resultList = ipoFeedbackService.selectQuestionListByLetterId(ipoFeedbackDto);
        response.setResult(resultList);
        return response;
    }


    @ApiOperation(value = "新反馈意见初始化接口", notes = "反馈意见初始化接口描述")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "案例id", required = true, paramType = "query", dataType = "String"),
    })
    @RequestMapping(value = "/selectNewFeedbackList", method = RequestMethod.GET)
    public JsonResponse<Map<String,List<IpoFeedbackDto>>> selectNewFeedbackList(String id) {
        JsonResponse<Map<String,List<IpoFeedbackDto>>> response = new JsonResponse<>();
        Map<String, List<IpoFeedbackDto>> ipoFeedbackMap= ipoFeedbackService.selectNewFeedbackList(id);
        response.setResult(ipoFeedbackMap);
        return response;
    }

    @ApiOperation(value = "新反馈意见问题列表接口", notes = "反馈意见问题列表接口描述")
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

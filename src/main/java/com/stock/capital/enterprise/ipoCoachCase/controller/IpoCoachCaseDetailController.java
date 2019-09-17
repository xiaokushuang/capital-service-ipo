package com.stock.capital.enterprise.ipoCoachCase.controller;

import com.stock.capital.enterprise.ipoCoachCase.dto.IpoCoachCaseDto;
import com.stock.capital.enterprise.ipoCoachCase.service.IpoCoachCaseDetailService;
import com.stock.core.controller.BaseController;
import com.stock.core.dto.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("ipoCoachCaseDetail")
public class IpoCoachCaseDetailController extends BaseController {

    @Autowired
    IpoCoachCaseDetailService ipoCoachCaseDetailService;

    @RequestMapping("queryCoachTitleInfo")
    public JsonResponse<IpoCoachCaseDto> queryCoachTitleInfo(String id) {
        JsonResponse<IpoCoachCaseDto> jsonResponse = new JsonResponse<>();
        jsonResponse.setResult(ipoCoachCaseDetailService.queryCoachTitleInfo(id));
        return jsonResponse;
    }

    @RequestMapping("queryCoachBasicInfo")
    public JsonResponse<IpoCoachCaseDto> queryCoachBasicInfo(String id){
        JsonResponse<IpoCoachCaseDto> jsonResponse = new JsonResponse<>();
        jsonResponse.setResult(ipoCoachCaseDetailService.queryCoachBasicInfo(id));
        return jsonResponse;
    }

}

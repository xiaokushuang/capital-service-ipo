package com.stock.capital.enterprise.ipoCoachCase.controller;

import com.stock.capital.enterprise.ipoCase.dto.TreeTypeProgressDto;
import com.stock.capital.enterprise.ipoCoachCase.dto.IpoCoachCaseDto;
import com.stock.capital.enterprise.ipoCoachCase.service.IpoCoachCaseDetailService;
import com.stock.core.controller.BaseController;
import com.stock.core.dto.JsonResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("ipoCoachCaseDetail")
public class IpoCoachCaseDetailController extends BaseController {

    @Autowired
    IpoCoachCaseDetailService ipoCoachCaseDetailService;

    @RequestMapping("queryCoachTitleInfo")
    @ResponseBody
    public JsonResponse<IpoCoachCaseDto> queryCoachTitleInfo(String id) {
        JsonResponse<IpoCoachCaseDto> jsonResponse = new JsonResponse<>();
        IpoCoachCaseDto ipoCoachCaseDto = new IpoCoachCaseDto();
        ipoCoachCaseDto = ipoCoachCaseDetailService.queryCoachTitleInfo(id);
        TreeTypeProgressDto processResult = ipoCoachCaseDetailService.queryIpoProcessByCaseId(id);
        if (processResult !=null && processResult.getTreeList() != null && processResult.getTreeList().size() > 0) {
            String listSize = String.valueOf(processResult.getTreeList().get(0).getProList().size() - 1);
            String endDate = processResult.getTreeList().get(0).getProList().get(0).getRelaList().get(0).getPublishTime();
            String startDate = processResult.getTreeList().get(0).getProList().get(Integer.parseInt(listSize)).getRelaList().get(0).getPublishTime();
            ipoCoachCaseDto.setLastDate(endDate);
            ipoCoachCaseDto.setTreeList(processResult.getTreeList());
            if(StringUtils.isNotEmpty(startDate) &&StringUtils.isNotEmpty(endDate)){
                ipoCoachCaseDto.setAllTime(ipoCoachCaseDetailService.getTimeDistance(startDate,endDate));
            }
        }
;
        jsonResponse.setResult(ipoCoachCaseDto);
        return jsonResponse;
    }

    @RequestMapping("queryCoachBasicInfo")
    @ResponseBody
    public JsonResponse<IpoCoachCaseDto> queryCoachBasicInfo(String id) {
        JsonResponse<IpoCoachCaseDto> jsonResponse = new JsonResponse<>();
        jsonResponse.setResult(ipoCoachCaseDetailService.queryCoachBasicInfo(id));
        return jsonResponse;
    }

}

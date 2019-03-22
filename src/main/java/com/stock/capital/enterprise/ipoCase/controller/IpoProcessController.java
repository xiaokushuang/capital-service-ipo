package com.stock.capital.enterprise.ipoCase.controller;

import com.stock.capital.enterprise.ipoCase.dto.TreeTypeProgressDto;
import com.stock.capital.enterprise.ipoCase.service.IpoProcessService;
import com.stock.core.controller.BaseController;
import com.stock.core.dto.JsonResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = {"IPO进程接口类"}, description = "IPO进程接口类描述")
@RestController
@RequestMapping("ipoProcess")
public class IpoProcessController extends BaseController {
    @Autowired
    private IpoProcessService ipoProcessService;

    @Value("#{app['pdf.baseUrl']}")
    private String pdfBaseUrl;
    @Value("#{app['file.viewPath']}")
    private String fileViewPath;

    @ApiOperation(value = "ipo进程接口", notes = "根据caseId获取ipo进程")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "案例id", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "sortType", value = "排序方式 01：正序 02：倒序  默认02", required = false, paramType = "query", dataType = "String"),
    })
    @RequestMapping(value = "/selectProcessList", method = RequestMethod.GET)
    public JsonResponse<TreeTypeProgressDto> selectProcessList(String id,String sortType) {
        if(StringUtils.isEmpty(sortType)){
            sortType = "02";
        }
        JsonResponse<TreeTypeProgressDto> response = new JsonResponse<>();
        TreeTypeProgressDto resultList = ipoProcessService.selectProcessList(id,sortType,pdfBaseUrl,fileViewPath);
        response.setResult(resultList);
        return response;
    }
}

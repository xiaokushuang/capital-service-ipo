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

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

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

    @ApiOperation(value = "ipo进程接口", notes = "根据caseId获取ipo进程")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "案例id", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "sortType", value = "排序方式 01：正序 02：倒序  默认02", required = false, paramType = "query", dataType = "String"),
    })
    @RequestMapping(value = "/selectProcessList", method = RequestMethod.GET)
    public JsonResponse<TreeTypeProgressDto> selectProcessList(String id, String sortType) {
        if (StringUtils.isEmpty(sortType)) {
            sortType = "02";
        }
        JsonResponse<TreeTypeProgressDto> response = new JsonResponse<>();
        TreeTypeProgressDto resultList = ipoProcessService.selectProcessList(id, sortType);
        response.setResult(resultList);
        return response;
    }

    @ApiOperation(value = "ipo进程接口", notes = "根据caseId获取ipo进程")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fileId", value = "文件id", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "fileType", value = "文件类型 01：公告 02：文件", required = true, paramType = "query", dataType = "String"),
    })
    @RequestMapping(value = "/downloadFile", method = RequestMethod.GET)
    public void downLoadFile(String fileId, String fileType, HttpServletResponse response) {
//        id = "AN201901201287197198";
//        id = "AN201901201287197198,AN201901201287197197";
//        id = "1106725448754480921,1106725448754109589";
//        id = "1106725448754480921";
//        fileType = "02";
        if (fileId.contains(",")) {
            if (fileType.equals("01")) {
                ipoProcessService.downloadMultiplyAnnounce(fileId, response);
            } else {
                ipoProcessService.downloadMultiplyFile(fileId, response);
            }
        } else {
            if (fileType.equals("01")) {
                ipoProcessService.downloadSingleAnnounce(fileId, response);
            } else {
                ipoProcessService.downloadSingleFile(fileId,response);
            }
        }
    }
}

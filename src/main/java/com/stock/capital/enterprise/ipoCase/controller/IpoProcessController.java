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

import javax.servlet.http.HttpServletRequest;
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

    @Value("#{app['service.baseUrl']}")
    private String serviceBaseUrl;

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
        resultList.setServiceBaseUrl(serviceBaseUrl);
        response.setResult(resultList);
        return response;
    }

    @ApiOperation(value = "ipo文件下载接口", notes = "ipo文件下载接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fileId", value = "文件id", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "fileType", value = "文件类型 01：公告 02：文件", required = true, paramType = "query", dataType = "String"),
    })
    @RequestMapping(value = "/downloadFile", method = RequestMethod.GET)
    public JsonResponse<String> downLoadFile(String fileId, String fileType, HttpServletResponse response, HttpServletRequest request) {
        JsonResponse<String> result = new JsonResponse<>();
        String fileName =  "";
        if (fileId.contains(",")) {
            if (fileType.equals("01")) {
                fileName = ipoProcessService.downloadMultiplyAnnounce(fileId, response,request);
            } else {
                fileName = ipoProcessService.downloadMultiplyFile(fileId, response,request);
            }
        } else {
            if (fileType.equals("01")) {
                fileName = ipoProcessService.downloadSingleAnnounce(fileId, response,request);
            } else {
                fileName = ipoProcessService.downloadSingleFile(fileId,response,request);
            }
        }
        result.setResult(fileName);
        return result;
    }

    @ApiOperation(value = "拆分上市文件下载接口", notes = "拆分上市文件下载接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fileId", value = "文件id", required = true, paramType = "query", dataType = "String")
    })
    @RequestMapping(value = "/downloadSplitFile", method = RequestMethod.GET)
    public JsonResponse<String> downLoadSplitFile(String fileId, HttpServletResponse response, HttpServletRequest request) {
        JsonResponse<String> result = new JsonResponse<>();
        String fileName =  ipoProcessService.downloadSplitFile(fileId,response,request);
        result.setResult(fileName);
        return result;
    }

    @ApiOperation(value = "检查所选文件是否存在", notes = "检查所选文件是否存在")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fileId", value = "文件id", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "fileType", value = "文件类型 01：公告 02：文件", required = true, paramType = "query", dataType = "String"),
    })
    @RequestMapping(value = "/getServiceBaseUrl", method = RequestMethod.GET)
    public JsonResponse<String> getServiceBaseUrl(){
        JsonResponse<String> result = new JsonResponse<>();
        result.setResult(serviceBaseUrl);
        return result;
    }
    @RequestMapping(value = "/checkFile", method = RequestMethod.GET)
    public JsonResponse<String> checkFile(String fileId, String fileType) {
        JsonResponse<String> result = new JsonResponse<>();
        String resultStr;
        if (fileId.contains(",")) {
            if (fileType.equals("01")) {
                resultStr = ipoProcessService.checkMultiplyAnnounce(fileId);
            } else {
                resultStr = ipoProcessService.checkMultiplyFile(fileId);
            }
        } else {
            if (fileType.equals("01")) {
                resultStr = ipoProcessService.checkSingleAnnounce(fileId);
            } else {
                resultStr = ipoProcessService.checkSingleFile(fileId);
            }
        }
        result.setResult(resultStr);
        return result;
    }
}

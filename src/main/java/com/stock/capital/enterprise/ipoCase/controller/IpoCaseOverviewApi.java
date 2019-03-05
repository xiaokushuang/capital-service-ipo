package com.stock.capital.enterprise.ipoCase.controller;

import com.stock.capital.enterprise.ipoCase.dto.CompanyOverviewVo;
import com.stock.capital.enterprise.ipoCase.sample.ResponseBean;
import com.stock.core.dto.JsonResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"IPO公司概览接口类"},description = "IPO公司概览接口描述")
@RestController
@RequestMapping("api/companyOverview")
public class IpoCaseOverviewApi {

    @ApiOperation(value = "公司概览接口", notes = "公司概览接口描述")
    @ApiImplicitParams({
	    @ApiImplicitParam(name = "id", value = "案例id", required = true, dataType = "String")
	})
    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public JsonResponse<CompanyOverviewVo> init(@RequestParam("id") String id){
        JsonResponse<CompanyOverviewVo> response = new JsonResponse<>();
        CompanyOverviewVo companyOverviewVo = new CompanyOverviewVo();
        response.setResult(companyOverviewVo);
        return response;
    }
}

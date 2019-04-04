package com.stock.capital.enterprise.ipoCase.sample;

import com.stock.core.dto.JsonResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"IPO接口样例类"},description = "IPO样例类接口描述")
@RestController
@RequestMapping("api")
public class ApiDocSample {

    @ApiOperation(value = "样例接口", notes = "样例接口描述")
    @ApiImplicitParams({
	    @ApiImplicitParam(name = "companyCode", value = "公司代码", required = true, dataType = "String"),
	    @ApiImplicitParam(name = "userId", value = "用户id", dataType = "String")
	})
    @RequestMapping(value = "/sample", method = RequestMethod.POST)
    public JsonResponse<ResponseBean> sample(String companyCode,String userId){
        JsonResponse<ResponseBean> response = new JsonResponse<>();
        ResponseBean bean = new ResponseBean();
        bean.setUserName("swagger");
        bean.setCompanyName("valueonline");
        response.setResult(bean);
        return response;
    }
}

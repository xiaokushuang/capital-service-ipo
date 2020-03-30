package com.stock.capital.enterprise.exportWord.controller;

import com.stock.capital.enterprise.exportWord.serviec.IpoExportWordService;
import com.stock.core.dto.JsonResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"IPO导出word"}, description = "IPO导出word")
@RestController
@RequestMapping(value = "exportWord")
public class IpoExportWordController {


  @Autowired
  private IpoExportWordService exportWordService;

  @RequestMapping("getCompanyInformation")
  @ApiImplicitParams({
      @ApiImplicitParam(name = "caseId", value = "案例id", required = true, paramType = "query",
          dataType = "String")
  })
  public JsonResponse<Map<String, Object>> getCompanyInformation(
      @RequestParam("caseId") String caseId) {
    JsonResponse response = new JsonResponse();
    Map<String,Object> resultMap = exportWordService.getCompanyInformation(caseId);
    response.setResult(resultMap);
    return response;
  }

}

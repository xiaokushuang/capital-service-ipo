package com.stock.capital.enterprise.ipoCase.controller;

import com.stock.capital.enterprise.ipoCase.dao.IpoCaseBizMapper;
import com.stock.capital.enterprise.ipoCase.dao.IpoExamineMapper;
import com.stock.capital.enterprise.ipoCase.dao.IpoFeedbackMapper;
import com.stock.capital.enterprise.ipoCase.dto.CompanyOverviewVo;
import com.stock.capital.enterprise.ipoCase.dto.HeadDataVo;
import com.stock.capital.enterprise.ipoCase.dto.IndustryCompareRateDto;
import com.stock.capital.enterprise.ipoCase.dto.IntermediaryOrgDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoExamineBaseDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoSplitDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoValuationDto;
import com.stock.capital.enterprise.ipoCase.dto.IssuerIndustryStatusDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoFeedbackDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoPersonInfoDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoTechnologyVo;
import com.stock.capital.enterprise.ipoCase.dto.MainCompetitorInfoDto;
import com.stock.capital.enterprise.ipoCase.dto.MainIncomeVo;
import com.stock.capital.enterprise.ipoCase.dto.OtherMarketInfoDto;
import com.stock.capital.enterprise.ipoCase.dto.SupplierCustomerMainDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoAssociatedCaseVo;
import com.stock.capital.enterprise.ipoCase.service.CompanyOverviewService;
import com.stock.capital.enterprise.ipoCase.service.IpoFeedbackService;
import com.stock.capital.enterprise.ipoCase.service.IssueSituationService;
import com.stock.core.dto.JsonResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"IPO公司概览接口类"}, description = "IPO公司概览接口描述")
@RestController
@RequestMapping("companyOverview")
public class IpoCaseOverviewController {

    @Autowired
    private CompanyOverviewService companyOverviewService;
    @Autowired
    private IpoFeedbackMapper ipoFeedbackMapper;
    @Autowired
    private IpoExamineMapper ipoExamineMapper;
    @Autowired
    private IpoCaseBizMapper ipoCaseBizMapper;
    @Autowired
    private IpoFeedbackService ipoFeedbackService;
    @Autowired
    private IssueSituationService issueSituationService;


    @ApiOperation(value = "案例详细接口", notes = "案例详细接接口描述")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "案例id", required = true, paramType = "query",
            dataType = "String")
    })
    @RequestMapping(value = "/caseDetail", method = RequestMethod.GET)
    public JsonResponse<CompanyOverviewVo> caseDetail(@RequestParam("id") String id) {
        JsonResponse<CompanyOverviewVo> response = new JsonResponse<>();
        CompanyOverviewVo companyOverviewVo = companyOverviewService.getIpoCaseDetail(id);
        response.setResult(companyOverviewVo);
        return response;
    }

    @ApiOperation(value = "关联案例列表", notes = "关联案例列表接口描述")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "案例id", required = true, paramType = "query",
            dataType = "String")
    })
    @RequestMapping(value = "/associatedCaseList", method = RequestMethod.GET)
    public JsonResponse<List<IpoAssociatedCaseVo>> associatedCaseList(@RequestParam("id") String id){
      JsonResponse<List<IpoAssociatedCaseVo>> response = new JsonResponse<>();
      List<IpoAssociatedCaseVo> result = companyOverviewService.getAssociatedCaseList(id);
      response.setResult(result);
      return response;
  }

    @ApiOperation(value = "资本市场接口", notes = "资本市场接口描述")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "案例id", required = true, paramType = "query",
            dataType = "String")
    })
    @RequestMapping(value = "/marketData", method = RequestMethod.GET)
    public JsonResponse<List<OtherMarketInfoDto>> marketData(@RequestParam("id") String id) {
        JsonResponse<List<OtherMarketInfoDto>> response = new JsonResponse<>();
        response.setResult(companyOverviewService.getMarketData(id));
        return response;
    }

  @ApiOperation(value = "拆分上市情况接口", notes = "拆分上市情况接口描述")
  @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "案例id", required = true, paramType = "query",
          dataType = "String")
  })
  @RequestMapping(value = "/spliteData", method = RequestMethod.GET)
  public JsonResponse< List<IpoSplitDto>> spliteData(@RequestParam("id") String id){
    JsonResponse< List<IpoSplitDto>> response = new JsonResponse<>();
    response.setResult(companyOverviewService.getSpliteData(id));
    return response;
  }

  @ApiOperation(value = "最近一次估值情况接口", notes = "最近一次估值情况接口描述")
  @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "案例id", required = true, paramType = "query",
      dataType = "String")
  })
  @RequestMapping(value = "/valuationData", method = RequestMethod.GET)
  public JsonResponse< List<IpoValuationDto>> valuationData(@RequestParam("id") String id){
    JsonResponse< List<IpoValuationDto>> response = new JsonResponse<>();
    response.setResult(companyOverviewService.getVluationData(id));
    return response;
  }


    @ApiOperation(value = "股东信息接口", notes = "股东信息接口描述")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "案例id", required = true, paramType = "query",
            dataType = "String")
    })
    @RequestMapping(value = "/shareHolderData", method = RequestMethod.GET)
    public JsonResponse<List<IpoPersonInfoDto>> shareHolderData(@RequestParam("id") String id) {
        JsonResponse<List<IpoPersonInfoDto>> response = new JsonResponse<>();
        response.setResult(companyOverviewService.getShareData(id));
        return response;
    }

    @ApiOperation(value = "主要竞争对手接口", notes = "主要竞争对手接口描述")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "案例id", required = true, paramType = "query",
            dataType = "String")
    })
    @RequestMapping(value = "/competitorData", method = RequestMethod.GET)
    public JsonResponse<List<MainCompetitorInfoDto>> competitorData(@RequestParam("id") String id) {
        JsonResponse<List<MainCompetitorInfoDto>> response = new JsonResponse<>();
        response.setResult(companyOverviewService.getCompetitorData(id));
        return response;
    }
    
   @ApiOperation(value = "发行人的行业地位接口", notes = "发行人的行业地位接口描述")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "案例id", required = true, paramType = "query",
            dataType = "String")
    })
    @RequestMapping(value = "/industryStatusData", method = RequestMethod.GET)
    public JsonResponse<List<IssuerIndustryStatusDto>> industryStatusData(@RequestParam("id") String id) {
       JsonResponse<List<IssuerIndustryStatusDto>> response = new JsonResponse<>();
        response.setResult(companyOverviewService.getindustryStatusData(id));
       return response;
    }

    @ApiOperation(value = "科技创新", notes = "科技创新情况")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "案例id", required = true, paramType = "query", dataType = "String")
    })
    @RequestMapping(value = "/patentData", method = RequestMethod.GET)
    public JsonResponse<IpoTechnologyVo> patentData(@RequestParam("id") String id){
        JsonResponse<IpoTechnologyVo> response = new JsonResponse<>();
        response.setResult(companyOverviewService.getPatentData(id));
        return response;
    }


    @ApiOperation(value = "报告期主要供应商及客户接口", notes = "报告期主要供应商及客户接口描述")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "案例id", required = true, paramType = "query",
            dataType = "String")
    })
    @RequestMapping(value = "/supplierCustomerData", method = RequestMethod.GET)
    public JsonResponse<Map<String, List<SupplierCustomerMainDto>>> supplierCustomerData(
        @RequestParam("id") String id) {
        JsonResponse<Map<String, List<SupplierCustomerMainDto>>> response = new JsonResponse<>();
        response.setResult(companyOverviewService.getSupCusData(id));
        return response;
    }

    @ApiOperation(value = "主营业务收入构成接口", notes = "主营业务收入构成接口描述")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "案例id", required = true, paramType = "query",
            dataType = "String")
    })
    @RequestMapping(value = "/incomeData", method = RequestMethod.GET)
    public JsonResponse<MainIncomeVo> incomeData(@RequestParam("id") String id) {
        JsonResponse<MainIncomeVo> response = new JsonResponse<>();
        response.setResult(companyOverviewService.getIncomeData(id));
        return response;
    }

    @ApiOperation(value = "中介机构接口", notes = "中介机构接口描述")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "案例id", required = true, paramType = "query",
            dataType = "String"),
        @ApiImplicitParam(name = "validFlag", value = "状态", paramType = "query",
            dataType = "String")
    })
    @RequestMapping(value = "/intermediaryOrgData", method = RequestMethod.GET)
    public JsonResponse<Map<String, List<IntermediaryOrgDto>>> intermediaryOrgData(
        @RequestParam("id") String id,
        @RequestParam(value = "validFlag", required = false) String validFlag) {
        JsonResponse<Map<String, List<IntermediaryOrgDto>>> response = new JsonResponse<>();
        response.setResult(companyOverviewService.getIntermediaryOrgData(id, validFlag));
        return response;
    }

    @ApiOperation(value = "案例详细头部数据接口", notes = "案例详细头部数据接接口描述")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "案例id", required = true, paramType = "query",
            dataType = "String")
    })
    @RequestMapping(value = "/headData", method = RequestMethod.GET)
    public JsonResponse<HeadDataVo> headData(@RequestParam("id") String id) {
        JsonResponse<HeadDataVo> response = new JsonResponse<>();
        HeadDataVo headDataVo = companyOverviewService.getHeadData(id);
        //判断是否显示反馈意见
        List<IpoFeedbackDto> ipoFeedbackList = ipoFeedbackService.selectNewFeedbackList(id);
//        List<String> processDateList = ipoFeedbackMapper.selectFeedbackProcess(id);
        if(CollectionUtils.isNotEmpty(ipoFeedbackList)){
            headDataVo.setHaveFeedback(0);
        }else{
            headDataVo.setHaveFeedback(1);
        }
        //判断是否显示审核结果
        //查询发审会基础信息
        List<IpoExamineBaseDto> baseList = ipoExamineMapper.selectExamineBaseList(id);
        if(CollectionUtils.isNotEmpty(baseList)){
            headDataVo.setHaveExamine(0);
        }else{
            headDataVo.setHaveExamine(1);
        }
        //判断是否显示发行概况
        //检查是否有上市进程
        List<String> publicProcessList = ipoFeedbackMapper.selectPublicProcess(id);
        if(CollectionUtils.isNotEmpty(publicProcessList)){
            headDataVo.setHavePublic(0);
        }else{
            headDataVo.setHavePublic(1);
        }

        List<IssuerIndustryStatusDto> industryList =  companyOverviewService.getindustryStatusData(id);
        List<MainCompetitorInfoDto> companyOverviewList = companyOverviewService.getCompetitorData(id);
        List<Map> techList = ipoCaseBizMapper.selectTechnologyByBid(id);
        List<IndustryCompareRateDto> indusList = issueSituationService.getIndustryRateData(id);
        techList.removeAll(Collections.singleton(null));
        if (CollectionUtils.isEmpty(industryList) && CollectionUtils.isEmpty(indusList) &&
         CollectionUtils.isEmpty(companyOverviewList) && CollectionUtils.isEmpty(techList)){
            headDataVo.setIsGray(1);
        } else {
            headDataVo.setIsGray(0);
        }
        response.setResult(headDataVo);
        return response;
    }
}

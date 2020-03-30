package com.stock.capital.enterprise.exportWord.serviec;

import com.stock.capital.enterprise.ipoCase.dto.CompanyOverviewVo;
import com.stock.capital.enterprise.ipoCase.dto.IndustryCompareRateDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoSplitDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoTechnologyVo;
import com.stock.capital.enterprise.ipoCase.dto.IpoValuationDto;
import com.stock.capital.enterprise.ipoCase.dto.IssuerIndustryStatusDto;
import com.stock.capital.enterprise.ipoCase.dto.MainCompetitorInfoDto;
import com.stock.capital.enterprise.ipoCase.dto.MainIncomeVo;
import com.stock.capital.enterprise.ipoCase.dto.OtherMarketInfoDto;
import com.stock.capital.enterprise.ipoCase.dto.SupplierCustomerMainDto;
import com.stock.capital.enterprise.ipoCase.service.CompanyOverviewService;
import com.stock.capital.enterprise.ipoCase.service.IssueSituationService;
import com.stock.core.dto.JsonResponse;
import com.stock.core.service.BaseService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IpoExportWordService extends BaseService {

  @Autowired
  public CompanyOverviewService companyOverviewService;

  @Autowired
  public IssueSituationService issueSituationService;

  private static final Logger logger = LoggerFactory.getLogger(IpoExportWordService.class);


  public void getCompanyInformation(String caseId){
    long start,end;
    start = System.currentTimeMillis();

    logger.info("导出word api 开始");

    JsonResponse<Map<String,Object>> response = new JsonResponse<>();
    Map<String,Object> resultMap = new HashMap<>();
//    二、公司概况
    CompanyOverviewVo companyInformation = companyOverviewService.getIpoCaseDetail(caseId);//公司概览-拟上市板块 模块
    List<OtherMarketInfoDto> otherMarkInfo = companyOverviewService.getMarketData(caseId);//公司概览-登陆其他资本市场 模块
    List<IpoSplitDto> splitList = companyOverviewService.getSpliteData(caseId);// 拆分上市情况
    List<IpoValuationDto> valuationList = companyOverviewService.getVluationData(caseId);//最近一次估值情况
    CompanyOverviewVo equityStructureVo = companyOverviewService.getIpoCaseDetail(caseId);//股权结构图， 股权结构标签 structureLabel 图 structureUrl
    resultMap.put("companyInformation",companyInformation);
    resultMap.put("otherMarkInfo",otherMarkInfo);
    resultMap.put("splitList",splitList);
    resultMap.put("valuationList",valuationList);
    resultMap.put("equityStructure",equityStructureVo);

//    三、公司生产经营情况
    MainIncomeVo mainIncomeVo = companyOverviewService.getIncomeData(caseId);//主营业务收入构成
    //supplierMainList  报告期前五名供应商情况
    //customerMainList  报告期前五名客户情况
    // FIXME: 2020/3/26 数据需要验证
    Map<String, List<SupplierCustomerMainDto>> supplierInformation = companyOverviewService.getSupCusData(caseId);//主营业务收入构成
    resultMap.put("mainIncome",mainIncomeVo);
    resultMap.put("supplierInformation",supplierInformation);

//    四、行业与技术
    List<IssuerIndustryStatusDto> industryStatusDtoList = companyOverviewService.getindustryStatusData(caseId);//行业地位
    List<MainCompetitorInfoDto> competitorData = companyOverviewService.getCompetitorData(caseId);//主要竞争对手简介
    List<IndustryCompareRateDto> industryCompareList = issueSituationService.getIndustryRateData(caseId);//行业毛利率对比
    /**专利情况：patentData 备注patentRemarks;
     * 研发投入：devData 研发时间：devDate 研发备注：devRemarks
     * 核心技术及研发技术人员：coreData 研发时间：coreDate 备注：coreRemarks
     */
    IpoTechnologyVo ipoTechnologyVo = companyOverviewService.getPatentData(caseId);
    resultMap.put("industryStatusDtoList",industryStatusDtoList);
    resultMap.put("competitorData",competitorData);
    resultMap.put("industryCompareList",industryCompareList);
    resultMap.put("ipoTechnology",ipoTechnologyVo);

//    五、财务信息


    end = System.currentTimeMillis();
    logger.info("导出word api结束" +  "Run Time:" + (end - start) + "(ms)");
  }

}

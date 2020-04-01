package com.stock.capital.enterprise.exportWord.serviec;

import com.stock.capital.enterprise.ipoCase.dto.CompanyOverviewVo;
import com.stock.capital.enterprise.ipoCase.dto.IndustryCompareRateDto;
import com.stock.capital.enterprise.ipoCase.dto.IntermediaryOrgDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoFeedbackDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoFinanceDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoInvestItemDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoSplitDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoTechnologyVo;
import com.stock.capital.enterprise.ipoCase.dto.IpoValuationDto;
import com.stock.capital.enterprise.ipoCase.dto.IssueDataDto;
import com.stock.capital.enterprise.ipoCase.dto.IssueFeeDto;
import com.stock.capital.enterprise.ipoCase.dto.IssuerIndustryStatusDto;
import com.stock.capital.enterprise.ipoCase.dto.MainCompetitorInfoDto;
import com.stock.capital.enterprise.ipoCase.dto.MainIncomeVo;
import com.stock.capital.enterprise.ipoCase.dto.OtherMarketInfoDto;
import com.stock.capital.enterprise.ipoCase.dto.StrategicPlacementMainDto;
import com.stock.capital.enterprise.ipoCase.dto.SupplierCustomerMainDto;
import com.stock.capital.enterprise.ipoCase.service.CompanyOverviewService;
import com.stock.capital.enterprise.ipoCase.service.IpoFeedbackService;
import com.stock.capital.enterprise.ipoCase.service.IpoFinanceService;
import com.stock.capital.enterprise.ipoCase.service.IpoInvestService;
import com.stock.capital.enterprise.ipoCase.service.IssueSituationService;
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

  @Autowired
  public IpoFinanceService ipoFinanceService;

  @Autowired
  public IpoInvestService ipoInvestService;

  @Autowired
  public IpoFeedbackService ipoFeedbackService;

  private static final Logger logger = LoggerFactory.getLogger(IpoExportWordService.class);


  public Map<String,Object> getCompanyInformation(String caseId){
    long start,end;
    start = System.currentTimeMillis();

    logger.info("导出word api 开始");

    Map<String,Object> resultMap = new HashMap<>();
//    二、公司概况
    //公司概览-拟上市板块 模块
    //股权结构图， 股权结构标签 structureLabel 图 structureUrl
    CompanyOverviewVo companyInformation = companyOverviewService.getIpoCaseDetail(caseId);
    List<OtherMarketInfoDto> otherMarkInfo = companyOverviewService.getMarketData(caseId);//公司概览-登陆其他资本市场 模块
    List<IpoSplitDto> splitList = companyOverviewService.getSpliteData(caseId);// 拆分上市情况
    List<IpoValuationDto> valuationList = companyOverviewService.getVluationData(caseId);//最近一次估值情况
//    CompanyOverviewVo equityStructureVo = companyOverviewService.getIpoCaseDetail(caseId);
    resultMap.put("companyInformation",companyInformation);
    resultMap.put("otherMarkInfo",otherMarkInfo);
    resultMap.put("splitList",splitList);
    resultMap.put("valuationList",valuationList);
//    resultMap.put("equityStructure",equityStructureVo);

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
//   财务总体情况：表头日期：dateList 数据（倒序排列）：IpoFinanceOverList
    IpoFinanceDto ipoFinance = ipoFinanceService.selectFinanceOverList(caseId);
//   资产与负债情况
//    dateList 资产类项目列表：ipoAssetItemList 负债类项目列表：ipoDebtItemList 权益类项目列表：ipoEquityItemList
    IpoFinanceDto assetLiability = ipoFinanceService.selectFinanceList(caseId);
//    收入与利润情况 表头日期：dateLis 收入类项目：IpoProfitItemList 成本类项目：IpoCostItemList 利润类项目：IpoReturnOverList
    IpoFinanceDto incomeProfit = ipoFinanceService.selectFinanceProfitList(caseId);
//    主要财务指标 表头日期：dateLis  主要财务指标：IpoMainIndexList
    IpoFinanceDto financialIndex = ipoFinanceService.selectMainIndexList(caseId);
    resultMap.put("ipoFinance",ipoFinance);
    resultMap.put("assetLiability",assetLiability);
    resultMap.put("incomeProfit",incomeProfit);
    resultMap.put("financialIndex",financialIndex);

    //    六、募集资金运用
    List<IpoInvestItemDto> ipoInvestItem = ipoInvestService.selectInvestItem(caseId);
    resultMap.put("ipoInvestItem",ipoInvestItem);

//    七、问询与回复（科创板注册制） 八、反馈意见（核准制）
    List<IpoFeedbackDto> ipoFeedbackList = ipoFeedbackService.selectNewFeedbackList(caseId);
    resultMap.put("ipoFeedbackList",ipoFeedbackList);



//    十一、发行概况
    IssueDataDto issueData = issueSituationService.getIssueData(caseId);//发行数据
    List<IssueFeeDto> issueFeeList = issueSituationService.getIssueFeeData(caseId);//发行费用
    StrategicPlacementMainDto strategicPlacementMain = issueSituationService.getPlacementData(caseId);//发行费用
    resultMap.put("issueData",issueData);
    resultMap.put("issueFeeList",issueFeeList);
    resultMap.put("strategicPlacementMain",strategicPlacementMain);

//  十二、中介机构
    Map<String, List<IntermediaryOrgDto>> intermediaryOrgList = companyOverviewService.getIntermediaryOrgData(caseId, "1");//生效的中介机构
    resultMap.put("intermediaryOrgList",intermediaryOrgList);
    end = System.currentTimeMillis();


    logger.info("导出word api结束" +  "Run Time:" + (end - start) + "(ms)");
    return resultMap;
  }

}

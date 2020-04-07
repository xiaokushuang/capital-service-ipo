package com.stock.capital.enterprise.exportWord.serviec;

import com.stock.capital.enterprise.ipoCase.dto.CompanyOverviewVo;
import com.stock.capital.enterprise.ipoCase.dto.HeadDataVo;
import com.stock.capital.enterprise.ipoCase.dto.IndustryCompareRateDto;
import com.stock.capital.enterprise.ipoCase.dto.IntermediaryOrgDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoFeedbackDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoFinanceDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoInvestItemDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoProListDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoProgressDto;
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
import com.stock.capital.enterprise.ipoCase.dto.TreeTypeProgressDto;
import com.stock.capital.enterprise.ipoCase.service.CompanyOverviewService;
import com.stock.capital.enterprise.ipoCase.service.IpoExamineService;
import com.stock.capital.enterprise.ipoCase.service.IpoFeedbackService;
import com.stock.capital.enterprise.ipoCase.service.IpoFinanceService;
import com.stock.capital.enterprise.ipoCase.service.IpoInvestService;
import com.stock.capital.enterprise.ipoCase.service.IpoProcessService;
import com.stock.capital.enterprise.ipoCase.service.IssueSituationService;
import com.stock.core.service.BaseService;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
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

  @Autowired
  public IpoExamineService ipoExamineService;

  @Autowired
  public IpoProcessService ipoProcessService;


  private static final Logger logger = LoggerFactory.getLogger(IpoExportWordService.class);


  public Map<String,Object> getCompanyInformation(String caseId){
    long start,end;
    start = System.currentTimeMillis();
//  只对IPO 做导出， IPO 辅导不做导出
    logger.info("导出word api 开始");

    Map<String,Object> resultMap = new HashMap<>();

//    第一页
    HeadDataVo head = companyOverviewService.getHeadData("98197418692119801");// 审核历时 IPO进程
    resultMap.put("head",head);

//    一、IPO进程
    TreeTypeProgressDto treeTypeProgress = ipoProcessService.selectProcessList("98197418692119801", "02");

    List<IpoProgressDto> treeList = treeTypeProgress.getTreeList();
    for (int i = 0; i < treeList.size(); i++) {
      if (treeList.get(i).getProList() != null) {
        IpoProgressDto ipoProgress = treeList.get(i);
        Integer durationDay = 0;
        for (int j = 0; j < ipoProgress.getProList().size(); j++) {
          IpoProListDto ipoPro = ipoProgress.getProList().get(j);
          Integer lastDay = Integer
              .parseInt(StringUtils.isNotBlank(ipoPro.getLastDay()) ? ipoPro.getLastDay() : "0");
          durationDay += lastDay;
        }
        ipoProgress.setDurationDay(durationDay+""); //总计天数

        Collections.reverse(ipoProgress.getProList());
      }
    }

    resultMap.put("treeTypeProgress",treeTypeProgress);

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
    IpoFinanceDto ipoFinance = ipoFinanceService.selectFinanceOverList("97952444248599350");
//   资产与负债情况
//    dateList 资产类项目列表：ipoAssetItemList 负债类项目列表：ipoDebtItemList 权益类项目列表：ipoEquityItemList
    IpoFinanceDto assetLiability = ipoFinanceService.selectFinanceList("97952444248599350");
//    收入与利润情况 表头日期：dateLis 收入类项目：IpoProfitItemList 成本类项目：IpoCostItemList 利润类项目：IpoReturnOverList
    IpoFinanceDto incomeProfit = ipoFinanceService.selectFinanceProfitList("97952444248599350");
//    主要财务指标 表头日期：dateLis  主要财务指标：IpoMainIndexList
    IpoFinanceDto financialIndex = ipoFinanceService.selectMainIndexList("97952444248599350");
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

//    九、审核结果及关注问题（注册制）
    IpoFeedbackDto ipoFeedbackDto = ipoExamineService.selectExamineBaseList("98197418692115791"); //baseList
    List<IpoFeedbackDto> resultList = ipoExamineService.selectNewExamineList("98197418692115791");
    resultMap.put("ipoFeedbackDto",ipoFeedbackDto);
    resultMap.put("resultList",resultList);

//    十一、发行概况
    IssueDataDto issueData = issueSituationService.getIssueData("97952444248599350");//发行数据
    List<IssueFeeDto> issueFeeList = issueSituationService.getIssueFeeData("97952444248599350");//发行费用
    StrategicPlacementMainDto strategicPlacementMain = issueSituationService.getPlacementData("97952444248599350");//发行费用
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

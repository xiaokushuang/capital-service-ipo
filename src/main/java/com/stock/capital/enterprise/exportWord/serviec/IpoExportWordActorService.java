package com.stock.capital.enterprise.exportWord.serviec;

import com.google.common.base.Splitter;
import com.google.common.base.Throwables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.spire.doc.FileFormat;
import com.stock.capital.enterprise.exportWord.controller.IpoExportWordActorController;
import com.stock.capital.enterprise.ipoCase.dto.*;
import com.stock.core.service.BaseService;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.Units;
import org.apache.poi.wp.usermodel.Paragraph;
import org.apache.poi.xddf.usermodel.chart.*;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBarChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBarSer;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@EnableAsync
@Service
public class IpoExportWordActorService extends BaseService {

    @Autowired
    private IpoExportWordService ipoExportWordService;

    @Autowired
    private POIWordInsertChartUtils poiWordInsertChartUtils;

    private static final int COLUMN_LANGUAGES = 0;
    private static final int COLUMN_COUNTRIES = 1;
    private static final int COLUMN_SPEAKERS = 2;

    public static List<String> patternList = new ArrayList();

    private static final Logger logger = LoggerFactory.getLogger(IpoExportWordActorController.class);

    //需要处理的节点名称
    static {
        patternList.add("mc:AlternateContent");
        patternList.add("mc:Choice");
        patternList.add("w:drawing");
        patternList.add("wp:anchor");
        patternList.add("a:graphic");
        patternList.add("a:graphicData");
        patternList.add("wps:wsp");
        patternList.add("wps:txbx");
        patternList.add("w:txbxContent");
        patternList.add("w:p");
        patternList.add("w:r");
        patternList.add("w:t");
    }

//    存储filePath 判断是否word生成完毕
    public static  ConcurrentHashMap<String,String> docMap = new ConcurrentHashMap<>();

    @Async
    public void createWordAsync(String caseId, String filePath){
        logger.info("#######【开始异步导出caseId"+caseId+"】###########");
        try {
            Resource resource = new ClassPathResource("templates/IPO导出word模板.docx");
            Map<String, Object> exportMap = exportWordCase(resource.getInputStream(), caseId);
            logger.info("#######【poi导出完成，开始刷新目录】###########");
            com.spire.doc.Document doc = new com.spire.doc.Document((InputStream) exportMap.get("inputStream"));
            logger.info("#######【转spire】###########");
            doc.updateTableOfContents();
            logger.info("#######【更新目录完成】###########");
            doc.saveToFile(filePath + ".docx", FileFormat.Docx);
            doc.close();
            docMap.put(filePath,"success");
            logger.info("#######【导出完成】###########");
        }catch (Exception e) {
            logger.info("#######【spire错误：" + e + "】###########"+ Throwables.getStackTraceAsString(e));
        }
    }

    public Map<String,Object> exportWordCase(InputStream inputStream, String caseId) throws Exception {
      Map<String,Object> exportMap = new HashMap<>();
      Map<String,Object> dataMap = ipoExportWordService.getCompanyInformation(caseId);
      Map<String,String> wordMap = new HashMap<>();
      wordMap.put("#公司名字#",isNull(((CompanyOverviewVo)dataMap.get("companyInformation")).getCompanyZhName()));
      wordMap.put("#公司简称#",isNull(((CompanyOverviewVo)dataMap.get("companyInformation")).getCompanyName()));
      wordMap.put("#证券代码#",isNull(((CompanyOverviewVo)dataMap.get("companyInformation")).getCompanyCode()));
      wordMap.put("#拟上市板块#",isNull(((CompanyOverviewVo)dataMap.get("companyInformation")).getIpoPlate()));
      if (StringUtils.isNotEmpty(((CompanyOverviewVo)dataMap.get("companyInformation")).getIpoPlate())){
        if (((CompanyOverviewVo)dataMap.get("companyInformation")).getIpoPlate().equals("上交所科创板")){
            wordMap.put("#板块类型#","注册制");
            wordMap.put("#问询回复/反馈意见#","问询与回复");
            wordMap.put("#审核关注问题#","审核结果及注册");
        }else {
            wordMap.put("#板块类型#","核准制");
            wordMap.put("#问询回复/反馈意见#","反馈意见");
            wordMap.put("#审核关注问题#","审核结果及注册");
        }
      }
      wordMap.put("#企业性质#",((CompanyOverviewVo)dataMap.get("companyInformation")).getCompanyNature());
      if (StringUtils.isNotEmpty(((CompanyOverviewVo)dataMap.get("companyInformation")).getAddrProv()) ||
              StringUtils.isNotEmpty(((CompanyOverviewVo)dataMap.get("companyInformation")).getAddrProv()) ||
              StringUtils.isNotEmpty(((CompanyOverviewVo)dataMap.get("companyInformation")).getAddrCity())||
              StringUtils.isNotEmpty(((CompanyOverviewVo)dataMap.get("companyInformation")).getAddrArea())){
          if("北京".equals(((CompanyOverviewVo)dataMap.get("companyInformation")).getAddrProv())||
                  "上海".equals(((CompanyOverviewVo)dataMap.get("companyInformation")).getAddrProv())||
                  "天津".equals(((CompanyOverviewVo)dataMap.get("companyInformation")).getAddrProv())||
                  "重庆".equals(((CompanyOverviewVo)dataMap.get("companyInformation")).getAddrProv())){
              wordMap.put("#注册地址#",isEm(((CompanyOverviewVo)dataMap.get("companyInformation")).getAddrCountry())
                      +isEm(((CompanyOverviewVo)dataMap.get("companyInformation")).getAddrCity())
                      +isEm(((CompanyOverviewVo)dataMap.get("companyInformation")).getAddrArea()));
          }else {
              wordMap.put("#注册地址#",isEm(((CompanyOverviewVo)dataMap.get("companyInformation")).getAddrCountry())
                      +isEm(((CompanyOverviewVo)dataMap.get("companyInformation")).getAddrProv())
                      +isEm(((CompanyOverviewVo)dataMap.get("companyInformation")).getAddrCity())
                      +isEm(((CompanyOverviewVo)dataMap.get("companyInformation")).getAddrArea()));
          }

      }else {
          wordMap.put("#注册地址#",isNull(((CompanyOverviewVo)dataMap.get("companyInformation")).getAddrCountry()));
      }

      String placing="";
        if (((CompanyOverviewVo)dataMap.get("companyInformation")).getPlacingMechanism()!=null){
            placing=((CompanyOverviewVo)dataMap.get("companyInformation")).getPlacingMechanism().replaceAll(",","；");
        }

      wordMap.put("#注册资本#",twoMarkThStr(((CompanyOverviewVo)dataMap.get("companyInformation")).getRegisteredAssets()+"")+"万元");
      wordMap.put("#战略新兴行业#",isNull(((CompanyOverviewVo)dataMap.get("companyInformation")).getStrageticIndustries()));
      wordMap.put("#证监会行业#",isNull(((CompanyOverviewVo)dataMap.get("companyInformation")).getIndustryCsrc()));
      wordMap.put("#实际控制人#",isNull(((CompanyOverviewVo)dataMap.get("companyInformation")).getActualController()));
      wordMap.put("#控股股东#",isNull(((CompanyOverviewVo)dataMap.get("companyInformation")).getControlShareholder()));
      wordMap.put("#配售机制#",isNull(placing));
      wordMap.put("#上市标准#",issueConditionName(((CompanyOverviewVo)dataMap.get("companyInformation")).getIssueCondition()));
      wordMap.put("#主营业务#",isNull(((CompanyOverviewVo)dataMap.get("companyInformation")).getMajorBusinesses()));
      List<OtherMarketInfoDto> otherList = (List<OtherMarketInfoDto>)dataMap.get("otherMarkInfo");
      List<IpoSplitDto> splitList = (List<IpoSplitDto>)dataMap.get("splitList");

      if (((CompanyOverviewVo)dataMap.get("companyInformation")).getStructureLabel()==null){
          wordMap.put("#股权结构类型#","彻底删除");
      }else {
          wordMap.put("#股权结构类型#",isEm(((CompanyOverviewVo)dataMap.get("companyInformation")).getStructureLabel())+" ");
      }
      List<IssuerIndustryStatusDto> industryStatusDtoList = (List<IssuerIndustryStatusDto>)dataMap.get("industryStatusDtoList");
      MainIncomeVo mainIncomeVo = (MainIncomeVo)dataMap.get("mainIncome");
      if (mainIncomeVo!=null &&mainIncomeVo.getMainIncomeInfoList() != null){
          wordMap.put("#主营业务描述#","彻底删除");
          wordMap.put("#主营业务单位#","单位：万元");
      }else {
          wordMap.put("#主营业务描述#","彻底删除");
          wordMap.put("#主营业务单位#","彻底删除");
      }

      IpoFeedbackDto ipoFeedback=  ((IpoFeedbackDto)dataMap.get("ipoFeedbackDto"));
      List<IpoExamineBaseDto> baseList=ipoFeedback.getBaseList();
        List<IpoFeedbackDto> resultList=(List<IpoFeedbackDto>)dataMap.get("resultList");

        List<SupplierCustomerMainDto> supplierMainList = ((Map<String,List<SupplierCustomerMainDto>>)dataMap.get("supplierInformation")).get("supplierMainList");
      List<SupplierCustomerMainDto> customerMainList = ((Map<String,List<SupplierCustomerMainDto>>)dataMap.get("supplierInformation")).get("customerMainList");
      List<IpoFeedbackDto> ipoFeedbackList =  (List<IpoFeedbackDto>)dataMap.get("ipoFeedbackList");
      List<MainCompetitorInfoDto> competitorData = (List<MainCompetitorInfoDto>)dataMap.get("competitorData");
      if (competitorData!=null && competitorData.size()>0){
          wordMap.put("#主要竞争对手详细#","彻底删除");
      }else {
          wordMap.put("#主要竞争对手详细#","彻底删除");
      }
      List<IndustryCompareRateDto> industryCompareList = (List<IndustryCompareRateDto>)dataMap.get("industryCompareList");

      IpoTechnologyVo ipoTechnology = (IpoTechnologyVo)dataMap.get("ipoTechnology");
      if (ipoTechnology != null && ipoTechnology.getCoreData() != null&&ipoTechnology.getCoreData().size()>0){
          wordMap.put("#研发人员备注#","    " +ipoTechnology.getRemarksData().getCoreRemarks()+" ");
      }else {
          wordMap.put("#研发人员备注#","彻底删除");
      }
      if (ipoTechnology != null && ipoTechnology.getDevData() != null&&ipoTechnology.getDevData().size()>0){
          wordMap.put("#研发投入备注#",ipoTechnology.getRemarksData().getDevRemarks()+" ");
          wordMap.put("#研发投入单位#","单位：万元");
      }else {
          wordMap.put("#研发投入备注#","彻底删除");
          wordMap.put("#研发投入单位#","彻底删除");
        }
      IssueDataDto issueData = (IssueDataDto)dataMap.get("issueData");
      IpoFinanceDto ipoFinance = (IpoFinanceDto)dataMap.get("ipoFinance");
      IpoFinanceDto assetLiability = (IpoFinanceDto)dataMap.get("assetLiability");
      IpoFinanceDto financialIndex = (IpoFinanceDto)dataMap.get("financialIndex");
      IpoFinanceDto incomeProfit = (IpoFinanceDto)dataMap.get("incomeProfit");
      if(incomeProfit!=null&&incomeProfit.getIpoProfitItemList()!=null &&incomeProfit.getIpoCostItemList()!=null&&incomeProfit.getIpoReturnOverList()!=null){
          wordMap.put("#收入与利润情况单位#","单位：万元");
          wordMap.put("#收入与利润情况详情#","彻底删除");
      }else {
          wordMap.put("#收入与利润情况单位#","彻底删除");
          wordMap.put("#收入与利润情况详情#","彻底删除");

      }
      List<IpoInvestItemDto> ipoInvestItem = (List<IpoInvestItemDto>)dataMap.get("ipoInvestItem");
      if (ipoInvestItem != null && ipoInvestItem.size()>0){
          wordMap.put("#募集资金详情#","彻底删除");
          wordMap.put("#募集资金备注#",ipoInvestItem.get(0).getInvestRemark()+" ");
      }else {
          wordMap.put("#募集资金详情#","彻底删除");
          wordMap.put("#募集资金备注#","彻底删除");
      }
        if (ipoInvestItem != null && ipoInvestItem.size()>0){
            wordMap.put("#募集资金单位#","单位：万元");
        }else {
            wordMap.put("#募集资金单位#","彻底删除");
        }
      List<IssueFeeDto> issueFeeList = (List<IssueFeeDto>)dataMap.get("issueFeeList");
      if (issueFeeList!=null && issueFeeList.size()>0){
          wordMap.put("#发行费用单位#","单位：万元");
      }else {
          wordMap.put("#发行费用单位#","彻底删除");
      }
      if (issueFeeList!=null && issueFeeList.size()>0){
        wordMap.put("#发行费用描述#","彻底删除");
      }else {
        wordMap.put("#发行费用描述#","彻底删除");
      }
      StrategicPlacementMainDto strategicPlacementMain =(StrategicPlacementMainDto)dataMap.get("strategicPlacementMain");
      if (strategicPlacementMain!=null && strategicPlacementMain.getSubs()!=null && strategicPlacementMain.getSubs().size()>0){
          wordMap.put("#战略配售情况#","彻底删除");
      }else {
          wordMap.put("#战略配售情况#","彻底删除");
      }
      if (ipoTechnology != null && ipoTechnology.getPatentData() != null &&ipoTechnology.getPatentData().size()>0){
          wordMap.put("#专利情况详情#","彻底删除");
      }else {
          wordMap.put("#专利情况详情#","彻底删除");
      }

      if(ipoFinance != null && ipoFinance.getIpoFinanceOverList() != null){
          wordMap.put("#财务总体情况详情#","彻底删除");
          wordMap.put("#财务总体情况详情单位#","单位：万元");
      }else {
          wordMap.put("#财务总体情况详情#","彻底删除");
          wordMap.put("#财务总体情况详情单位#","彻底删除");
      }

      if (financialIndex != null && financialIndex.getIpoMainIndexList() != null){
          wordMap.put("#财务指标表格情况#","彻底删除");
      }else {
          wordMap.put("#财务指标表格情况#","彻底删除");
      }

      if (assetLiability == null || ipoFinance.getIpoFinanceOverList() == null || ipoFinance.getIpoFinanceOverList().size()==0){
          wordMap.put("#资产与负债情况#","彻底删除");
          wordMap.put("#资产与负债情况单位#","彻底删除");
      }else {
          wordMap.put("#资产与负债情况#","彻底删除");
          wordMap.put("#资产与负债情况单位#","单位：万元");
      }
      Map<String, List<IntermediaryOrgDto>> intermediaryOrgList = (Map<String, List<IntermediaryOrgDto>>)dataMap.get("intermediaryOrgList");
        List<IntermediaryOrgDto> mainList=new ArrayList<>();
        List<IntermediaryOrgDto> moreList=new ArrayList<>();
        if (intermediaryOrgList != null){
            if (intermediaryOrgList.get("mainList") != null){
                mainList = intermediaryOrgList.get("mainList");
            }
            if (intermediaryOrgList.get("moreList") != null){
                moreList = intermediaryOrgList.get("moreList");
            }
        }
      TreeTypeProgressDto treeTypeProgress = (TreeTypeProgressDto)dataMap.get("treeTypeProgress");
        List<Map<String,Object>> listTreeTypeProgress=new ArrayList<>();
      if (treeTypeProgress != null && treeTypeProgress.getTreeList() != null){
          int max=0;
          List<IpoProListDto> list0=new ArrayList<>();
          List<IpoProListDto> list1=new ArrayList<>();
          List<IpoProListDto> list2=new ArrayList<>();
          for (int p=0;p<treeTypeProgress.getTreeList().size();p++){
              if ("00".equals(treeTypeProgress.getTreeList().get(p).getTreeTypeCode())){
                  list0=treeTypeProgress.getTreeList().get(p).getProList();
                  if (list0.size()>max){
                      max=list0.size();
                  }
              }else if ("01".equals(treeTypeProgress.getTreeList().get(p).getTreeTypeCode())){
                  list1=treeTypeProgress.getTreeList().get(p).getProList();
                  if (list1.size()>max){
                      max=list1.size();
                  }
              }else if ("02".equals(treeTypeProgress.getTreeList().get(p).getTreeTypeCode())){
                  list2=treeTypeProgress.getTreeList().get(p).getProList();
                  if (list2.size()>max){
                      max=list2.size();
                  }
              }
          }
         listTreeTypeProgress=new ArrayList<>(max);
          for (int p=0;p<max;p++){
              Map<String,Object> map0=new HashMap<>();
              if(p<list0.size()){
                  map0.put("cell0",isNull(list0.get(p).getProcessTime()));
                  map0.put("cell1",isNull(list0.get(p).getProgressName()));
              }else {
                  map0.put("cell0","--");
                  map0.put("cell1","--");
              }
              if(p<list1.size()){
                  map0.put("cell2",isNull(list1.get(p).getProcessTime()));
                  map0.put("cell3",isNull(list1.get(p).getProgressName()));
              }else {
                  map0.put("cell2","--");
                  map0.put("cell3","--");
              }
              if(p<list2.size()){
                  map0.put("cell4",isNull(list2.get(p).getProcessTime()));
                  map0.put("cell5",isNull(list2.get(p).getProgressName()));
              }else {
                  map0.put("cell4","--");
                  map0.put("cell5","--");
              }
              listTreeTypeProgress.add(map0);
          }



          for (int i=0;i<treeTypeProgress.getTreeList().size();i++){
              if ("02".equals(treeTypeProgress.getTreeList().get(i).getTreeTypeCode())){
                  wordMap.put("#发行历时#",treeTypeProgress.getTreeList().get(i).getDurationDay());
              }else if ("01".equals(treeTypeProgress.getTreeList().get(i).getTreeTypeCode())){
                  wordMap.put("#审核历时#",treeTypeProgress.getTreeList().get(i).getDurationDay());
              }else if ("00".equals(treeTypeProgress.getTreeList().get(i).getTreeTypeCode())){
                  wordMap.put("#辅导历时#",treeTypeProgress.getTreeList().get(i).getDurationDay());
              }
          }
      }
      String imgUrl = "";
      if (StringUtils.isNotEmpty(((CompanyOverviewVo)dataMap.get("companyInformation")).getStructureUrl())){
          imgUrl = ((CompanyOverviewVo)dataMap.get("companyInformation")).getStructureUrl();
      }
      List<SupplierCustomerMainDto> supplierInformation = ((Map<String,List<SupplierCustomerMainDto>>)dataMap.get("supplierInformation")).get("supplierMainList");
      SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
      XWPFDocument xdoc = new XWPFDocument(inputStream);
      Map<String, POIXMLDocumentPart> chartsMap = new HashMap<String, POIXMLDocumentPart>();
      List<POIXMLDocumentPart> relations = xdoc.getRelations();
      for (POIXMLDocumentPart poixmlDocumentPart : relations) {
          String str = poixmlDocumentPart.toString();
          if (poixmlDocumentPart instanceof XWPFChart) {
              String key = str.replaceAll("Name: ", "")
                 .replaceAll(" - Content Type: application/vnd\\.openxmlformats-officedocument\\.drawingml\\.chart\\+xml", "").trim();
              chartsMap.put(key, poixmlDocumentPart);
          }
      }
//        if (mainIncomeVo != null && mainIncomeVo.getMainIncomeInfoList() != null){
//            List<String> titleArr = new ArrayList<String>();
//            titleArr.add("近三年主营业务趋势");
//            List<String> fldNameArr = new ArrayList<String>();
//            List<Map<String, String>> listItemsByType = new ArrayList<Map<String, String>>();
//            fldNameArr.add("item1");
//            if (StringUtils.isNotEmpty(mainIncomeVo.getOnePeriodForIncome())){
//                fldNameArr.add(mainIncomeVo.getOnePeriodForIncome());
//                titleArr.add(mainIncomeVo.getOnePeriodForIncome());
//            }
////            if (StringUtils.isNotEmpty(mainIncomeVo.getThirdYearForIncome())){
////                fldNameArr.add(mainIncomeVo.getThirdYearForIncome());
////                titleArr.add(mainIncomeVo.getThirdYearForIncome());
////
////            }
////            if (StringUtils.isNotEmpty(mainIncomeVo.getSecondYearForIncome())){
////                fldNameArr.add(mainIncomeVo.getSecondYearForIncome());
////                titleArr.add(mainIncomeVo.getSecondYearForIncome());
////
////            }
////            if (StringUtils.isNotEmpty(mainIncomeVo.getFirstYearForIncome())){
////                fldNameArr.add(mainIncomeVo.getFirstYearForIncome());
////                titleArr.add(mainIncomeVo.getFirstYearForIncome());
////            }
//
//
//            for (int i=0;i<mainIncomeVo.getMainIncomeInfoList().size();i++){
//                if (!mainIncomeVo.getMainIncomeInfoList().get(i).getBusinessName().equals("合计")){
//                    Map<String, String> base1 = new HashMap<String, String>();
//                    if (StringUtils.isNotEmpty(mainIncomeVo.getMainIncomeInfoList().get(i).getBusinessName())){
//                        base1.put("item1", mainIncomeVo.getMainIncomeInfoList().get(i).getBusinessName());
//                    }
//                    if (StringUtils.isNotEmpty(mainIncomeVo.getOnePeriodForIncome())){
//                        base1.put(mainIncomeVo.getOnePeriodForIncome(), isNullBig(mainIncomeVo.getMainIncomeInfoList().get(i).getOnePeriodAmount()));
//                    }
////                    if (StringUtils.isNotEmpty(mainIncomeVo.getThirdYearForIncome())){
////                        base1.put(mainIncomeVo.getThirdYearForIncome(), isNullBig(mainIncomeVo.getMainIncomeInfoList().get(i).getThirdYearAmount()));
////                    }
////                    if (StringUtils.isNotEmpty(mainIncomeVo.getSecondYearForIncome())){
////                        base1.put(mainIncomeVo.getSecondYearForIncome(), isNullBig(mainIncomeVo.getMainIncomeInfoList().get(i).getSecondYearAmount()));
////                    }
////                    if (StringUtils.isNotEmpty(mainIncomeVo.getFirstYearForIncome())){
////                        base1.put(mainIncomeVo.getFirstYearForIncome(), isNullBig(mainIncomeVo.getMainIncomeInfoList().get(i).getFirstYearAmount()));
////                    }
//                    listItemsByType.add(base1);
//                }
//
//            }
////            listItemsByType.add(base1);
////            listItemsByType.add(base2);
////            listItemsByType.add(base3);
////            listItemsByType.add(base4);
//
////饼图
////            POIXMLDocumentPart poixmlDocumentPart1 = chartsMap.get("/word/charts/chart1.xml");
////            new PoiWordTools().replacePieCharts(poixmlDocumentPart1, titleArr, fldNameArr, listItemsByType);
//        }

//文本框
        if(((IpoFeedbackDto)dataMap.get("ipoFeedbackDto")).getBaseList()!=null){
            int len=((IpoFeedbackDto)dataMap.get("ipoFeedbackDto")).getBaseList().size();
            if (((CompanyOverviewVo)dataMap.get("companyInformation")).getIpoPlate().equals("上交所科创板")){
                wordMap.put("审核结果类型","注册结果:"+isNull(((IpoFeedbackDto)dataMap.get("ipoFeedbackDto")).getBaseList().get(len-1).getIecResultStr()));
                wordMap.put("审核日期","注册日期:"+isNull(((IpoFeedbackDto)dataMap.get("ipoFeedbackDto")).getBaseList().get(len-1).getExamineDateStr()));
            }else {
                wordMap.put("审核结果类型","审核结果:"+isNull(((IpoFeedbackDto)dataMap.get("ipoFeedbackDto")).getBaseList().get(len-1).getIecResultStr()));
                wordMap.put("审核日期","审核日期:"+isNull(((IpoFeedbackDto)dataMap.get("ipoFeedbackDto")).getBaseList().get(len-1).getExamineDateStr()));

            }
        }else {
            if (((CompanyOverviewVo)dataMap.get("companyInformation")).getIpoPlate().equals("上交所科创板")){
                wordMap.put("审核结果类型","注册结果:"+"--");
                wordMap.put("审核日期","注册日期:"+"--");
            }else {
                wordMap.put("审核结果类型","审核结果:"+"--");
                wordMap.put("审核日期","审核日期:"+"--");
            }

        }

        wordMap.put("审核历时","审核历时:"+isNull(wordMap.get("#审核历时#"))+"天");
        wordMap.put("IPO进程详情","IPO进程:"+isNull(((HeadDataVo)dataMap.get("head")).getProcessLabel()));
        wordMap.put("公司名称详情",isNull(((CompanyOverviewVo)dataMap.get("companyInformation")).getCompanyName()));

        if(((HeadDataVo)dataMap.get("head")).getProcessLabel()!=null
                &&("已上市".equals(((HeadDataVo)dataMap.get("head")).getProcessLabel())||"已发行".equals(((HeadDataVo)dataMap.get("head")).getProcessLabel()))){
            wordMap.put("拟上市板块","上市板块:"+isNull(((CompanyOverviewVo)dataMap.get("companyInformation")).getIpoPlate()));
            wordMap.put("#拟上市板块表格文字#","上市板块");
        }else {
            wordMap.put("拟上市板块","拟上市板块:"+isNull(((CompanyOverviewVo)dataMap.get("companyInformation")).getIpoPlate()));
            wordMap.put("#拟上市板块表格文字#","拟上市板块");
        }




        wordMap.put("注册地址说明",wordMap.get("#注册地址#"));
        wordMap.put("证监会行业说明",isNull(((CompanyOverviewVo)dataMap.get("companyInformation")).getIndustryCsrc()));
        for (XWPFParagraph paragraph : xdoc.getParagraphs())
            for (XmlObject object : paragraph.getCTP().getRArray()) {
                XmlCursor cursor = object.newCursor();
                eachchild(cursor, 0, wordMap);
            }
      int ids = 123;
      Iterator<XWPFParagraph> itPara = xdoc.getParagraphsIterator();
      XpwfUtils test = new XpwfUtils();
      while (itPara.hasNext()) {
          XWPFParagraph paragraph = (XWPFParagraph) itPara.next();
          Set<String> set = wordMap.keySet();
          Iterator<String> iterator = set.iterator();
          while1: while (iterator.hasNext()) {
              String key = iterator.next();
              List<XWPFRun> run = paragraph.getRuns();
              int size = run.size();
              for (int i = 0; i < size; i++) {
                  if (run.get(i).text() != null && run.get(i).text().indexOf(key) != -1) {
                      if (StringUtils.isNotEmpty(key) && StringUtils.isNotEmpty(wordMap.get(key))) {
                          String value = wordMap.get(key);
                          if (" ".equals(value)) {
                              value = "";
                          }
                          String text = run.get(i).text().replaceAll(key, value);
                          if (text != null) {
                              if (text.indexOf("\n") == -1) {
                                  run.get(i).setText(text, 0);
                                  continue while1;
                              } else {
                                  replaceNeedBreak(paragraph, text, key);
                                  break while1;
                              }
                          }
                      }
                  }
              }
          }


          if(StringUtils.isEmpty(paragraph.getText())){//如果段落为空
              continue;
          } else if("#资本市场#".equals(paragraph.getText())){
              String content = "";
              for (int b=0;b<otherList.size();b++){
                  if (StringUtils.isNotEmpty(otherList.get(b).getMarketType())){
                      content = content + otherList.get(b).getMarketType()+"\n";
                  }
//                  if (StringUtils.isNotEmpty(otherList.get(b).getCompanyName())){
//                      content = content +"公司代码："+ otherList.get(b).getCompanyName();
//                  }
                  if (StringUtils.isNotEmpty(otherList.get(b).getCompanyCode())){
                      content = content +"公司代码："+ otherList.get(b).getCompanyCode()+"\n";
                  }
                  if (StringUtils.isNotEmpty(otherList.get(b).getListTime()+"")){
                      content = content + "上市日/挂牌日："+isNullTime(otherList.get(b).getListTime())+"\n";
                  }
                  if (StringUtils.isNotEmpty(otherList.get(b).getDelistTime()+"")){
                      content = content + "退市日/摘牌日："+isNullTime(otherList.get(b).getDelistTime())+"\n";
                  }
              }
              if ("".equals(content)){
                  content="彻底删除";
              }

              String text = paragraph.getRuns().get(0).text().replaceAll("#资本市场#", content);
              if (text != null) {
                  if (text.indexOf("\n") == -1) {
                      paragraph.getRuns().get(0).setText(text, 0);
                  } else {
                      replaceNeedBreak(paragraph, text, "#资本市场#");
                  }
              }
              continue;
          }else if("#拆分上市情况#".equals(paragraph.getText())){
              String content = "";
              for (int b=0;b<splitList.size();b++){

                  if (StringUtils.isNotEmpty(splitList.get(b).getCompanyName())){
                      String[] arr=splitList.get(b).getCompanyName().split("\\(|\\)");
                      content = content + "上市公司：" + arr[0]+"公司("+""+arr[1]+")"+"\n";
                  }
                  if (StringUtils.isNotEmpty(splitList.get(b).getSplitMarket())){
                      content = content + "所在市场：" + splitList.get(b).getSplitMarket()+"\n";
                  }
                  if (splitList.get(b).getShareProportion()!=null){
                      content = content + "直接或间接持有人股份：" + isNullBigZero(splitList.get(b).getShareProportion())+""+"%\n";
                  }
              }
              if ("".equals(content)){
                  content="彻底删除";
              }
              String text = paragraph.getRuns().get(0).text().replaceAll("#拆分上市情况#", content);
              if (text != null) {
                  if (text.indexOf("\n") == -1) {
                      paragraph.getRuns().get(0).setText(text, 0);
                  } else {
                      replaceNeedBreak(paragraph, text, "#拆分上市情况#");
                  }
              }
              continue;
          }else if("#最近一次估值#".equals(paragraph.getText())){
              String content = "";
              if ((List<IpoValuationDto>)dataMap.get("valuationList") != null && ((List<IpoValuationDto>) dataMap.get("valuationList")).size()>0){
                  if (StringUtils.isNotEmpty(((List<IpoValuationDto>)dataMap.get("valuationList")).get(0).getValuationDate())){
                      content = content + "时间：" + ((List<IpoValuationDto>)dataMap.get("valuationList")).get(0).getValuationDate()+"\n";
                  }
                  if ((((List<IpoValuationDto>)dataMap.get("valuationList")).get(0).getValuationPrice())!=null){
                      content = content + "股价：" + twoMarkThStr(((List<IpoValuationDto>)dataMap.get("valuationList")).get(0).getValuationPrice().toString())+((List<IpoValuationDto>)dataMap.get("valuationList")).get(0).getValuationPriceUnit()+"/股\n";
                  }
                  if ((((List<IpoValuationDto>)dataMap.get("valuationList")).get(0).getValuationEquity())!=null){
                      content = content + "总股本：" + twoMarkThStr(((List<IpoValuationDto>)dataMap.get("valuationList")).get(0).getValuationEquity().toString())+"万股\n";
                  }
                  if ((((List<IpoValuationDto>)dataMap.get("valuationList")).get(0).getValuationValue())!=null){
                      content = content + "估值：" + twoMarkThStr(((List<IpoValuationDto>)dataMap.get("valuationList")).get(0).getValuationValue().floatValue()/10000+"")+"亿"+((List<IpoValuationDto>)dataMap.get("valuationList")).get(0).getValuationEquityUnit()+"\n";
                  }
                  if (StringUtils.isNotEmpty(((List<IpoValuationDto>)dataMap.get("valuationList")).get(0).getValuationMemo())){
                      content = content + ((List<IpoValuationDto>)dataMap.get("valuationList")).get(0).getValuationMemo()+"\n";
                  }
              }else {
                  content="彻底删除";
              }
              if ("".equals(content)){
                  content="彻底删除";
              }

              String text = paragraph.getRuns().get(0).text().replaceAll("#最近一次估值#", content);
              if (text != null) {
                  if (text.indexOf("\n") == -1) {
                      paragraph.getRuns().get(0).setText(text, 0);
                  } else {
                      replaceNeedBreak(paragraph, text, "#最近一次估值#");
                  }
              }
              continue;
          }else if("#行业地位#".equals(paragraph.getText())){
              String content = "";
              if (industryStatusDtoList!=null && industryStatusDtoList.size()>0){
                  if (industryStatusDtoList.size()>0 && StringUtils.isNotEmpty(industryStatusDtoList.get(0).getIndustryStatusOverview())){
                      content = content + industryStatusDtoList.get(0).getIndustryStatusOverview()+"\n";
                  }
                  String text = paragraph.getRuns().get(0).text().replaceAll("#行业地位#", content);
                  if (text != null) {
                      if (text.indexOf("\n") == -1) {
                          paragraph.getRuns().get(0).setText(text, 0);
                      } else {
                          replaceNeedBreak(paragraph, text, "#行业地位#");
                      }
                  }
              }else {
                  content="彻底删除";
                  String text = paragraph.getRuns().get(0).text().replaceAll("#行业地位#", content);
                  if (text != null) {
                      if (text.indexOf("\n") == -1) {
                          paragraph.getRuns().get(0).setText(text, 0);
                      } else {
                          replaceNeedBreak(paragraph, text, "#行业地位#");
                      }
                  }
              }
              continue;
          }else if ("#中介机构#".equals(paragraph.getText())){
              test.clearParagraph(paragraph);
              int sort=0;
              if (mainList!=null && mainList.size()>0){
                  for (int p=0;p<mainList.size();p++){
                      XmlCursor cursor1 = paragraph.getCTP().newCursor();
                      cursor1.toNextSibling();
                      XWPFParagraph newpa1 = xdoc.insertNewParagraph(cursor1);
                      sort++;
                      XWPFRun run1 = newpa1.createRun();
                      run1.setText(sort+"、"+mainList.get(p).getIntermediaryTypeName());
                      run1.setFontSize(16);
                      run1.setColor("333399");
                      run1.setBold(true);
                      newpa1.setStyle("3");
                      newpa1.setSpacingAfter(0);
                      newpa1.setSpacingBefore(12);
                      run1.setFontFamily("微软雅黑");
                      XmlCursor cursor2 = newpa1.getCTP().newCursor();
                      cursor2.toNextSibling();
                      XWPFParagraph newpa2 = xdoc.insertNewParagraph(cursor2);
                      XWPFRun run2 = newpa2.createRun();
                      newpa2.setSpacingBetween(1, LineSpacingRule.AUTO);
                      run2.setFontFamily("宋体");
                      run2.setBold(false);
                      run2.setFontSize(11);
                      if (mainList.get(p).getIntermediaryType().equals("1")){
                          if (StringUtils.isNotEmpty(mainList.get(p).getOrgName()))
                              run2.setText("    "+"机构名称:" + mainList.get(p).getOrgName());
                          run2.addBreak();
                          if (StringUtils.isNotEmpty(mainList.get(p).getRepresentPerson())){
                              run2.setText("    "+"保荐代表人:" + mainList.get(p).getRepresentPerson());
                              run2.addBreak();
                          }
                          if (StringUtils.isNotEmpty(mainList.get(p).getAssistPerson() )){
                              run2.setText("    "+"项目协办人:" +mainList.get(p).getAssistPerson() );
                              run2.addBreak();
                          }
                          if (StringUtils.isNotEmpty(mainList.get(p).getAgentPerson())){
                              run2.setText("    "+"项目经办人:" +mainList.get(p).getAgentPerson()  );
                              run2.addBreak();
                          }
                      }
                      if (mainList.get(p).getIntermediaryType().equals("3")){
                          if (StringUtils.isNotEmpty(mainList.get(p).getOrgName())){
                              run2.setText("    "+"机构名称:" + mainList.get(p).getOrgName() );
                              run2.addBreak();
                          }
                          if (StringUtils.isNotEmpty(mainList.get(p).getAgentPerson())){
                              run2.setText("    "+"经办律师:" + mainList.get(p).getAgentPerson());
                              run2.addBreak();
                          }
                      }
                      if (mainList.get(p).getIntermediaryType().equals("4")){
                          if (StringUtils.isNotEmpty(mainList.get(p).getOrgName())){
                              run2.setText("    "+"机构名称:" + mainList.get(p).getOrgName() );
                              run2.addBreak();
                          }
                          if (StringUtils.isNotEmpty(mainList.get(p).getAgentPerson())){
                              run2.setText("    "+"注册会计师:" + mainList.get(p).getAgentPerson());
                              run2.addBreak();
                          }
                      }
                      if (mainList.get(p).getIntermediaryType().equals("2")){
                          if (StringUtils.isNotEmpty(mainList.get(p).getOrgName())){
                              run2.setText("    "+"机构名称:" + mainList.get(p).getOrgName() );
                              run2.addBreak();
                          }
                          if (StringUtils.isNotEmpty(mainList.get(p).getAgentPerson())){
                              run2.setText("    "+"项目经办人:" + mainList.get(p).getAgentPerson());
                              run2.addBreak();
                          }
                      }
                      if (mainList.get(p).getIntermediaryType().equals("5")){
                          if (StringUtils.isNotEmpty( mainList.get(p).getOrgName())){
                              run2.setText("    "+"机构名称:" +  mainList.get(p).getOrgName() );
                              run2.addBreak();
                          }
                          if (StringUtils.isNotEmpty(mainList.get(p).getAgentPerson())){
                              run2.setText("    "+"注册资产评估师:" + mainList.get(p).getAgentPerson());
                              run2.addBreak();
                          }
                      }
                      if (mainList.get(p).getIntermediaryType().equals("6")){
                          if (StringUtils.isNotEmpty(mainList.get(p).getOrgName())){
                              run2.setText("    "+"机构名称:" +  mainList.get(p).getOrgName());
                              run2.addBreak();
                          }
                          if (StringUtils.isNotEmpty(mainList.get(p).getAgentPerson() )){
                              run2.setText("    "+"经办人:" + mainList.get(p).getAgentPerson() );
                              run2.addBreak();
                          }
                      }
                      paragraph=newpa2;
                  }
              }
              if (moreList!=null && moreList.size()>0){
                  for (int p=0;p<moreList.size();p++){
                      XmlCursor cursor1 = paragraph.getCTP().newCursor();
                      cursor1.toNextSibling();
                      XWPFParagraph newpa1 = xdoc.insertNewParagraph(cursor1);
                      sort++;
                      XWPFRun run1 = newpa1.createRun();
                      run1.setText(sort+"、"+moreList.get(p).getIntermediaryTypeName());
                      run1.setFontSize(16);
                      run1.setColor("333399");
                      run1.setBold(true);
                      newpa1.setStyle("3");
                      newpa1.setSpacingAfter(0);
                      newpa1.setSpacingBefore(12);
                      run1.setFontFamily("微软雅黑");
                      XmlCursor cursor2 = newpa1.getCTP().newCursor();
                      cursor2.toNextSibling();
                      XWPFParagraph newpa2 = xdoc.insertNewParagraph(cursor2);
                      XWPFRun run2 = newpa2.createRun();
                      newpa2.setSpacingBetween(1, LineSpacingRule.AUTO);
                      run2.setFontFamily("宋体");
                      run2.setBold(false);
                      run2.setFontSize(11);
                      if (moreList.get(p).getIntermediaryType().equals("1")){
                          if (StringUtils.isNotEmpty(moreList.get(p).getOrgName()))
                              run2.setText("    "+"机构名称:" + moreList.get(p).getOrgName());
                          run2.addBreak();
                          if (StringUtils.isNotEmpty(moreList.get(p).getRepresentPerson())){
                              run2.setText("    "+"保荐代表人:" + moreList.get(p).getRepresentPerson());
                              run2.addBreak();
                          }
                          if (StringUtils.isNotEmpty(moreList.get(p).getAssistPerson() )){
                              run2.setText("    "+"项目协办人:" +moreList.get(p).getAssistPerson() );
                              run2.addBreak();
                          }
                          if (StringUtils.isNotEmpty(moreList.get(p).getAgentPerson())){
                              run2.setText("    "+"项目经办人:" +moreList.get(p).getAgentPerson()  );
                              run2.addBreak();
                          }
                      }
                      if (moreList.get(p).getIntermediaryType().equals("3")){
                          if (StringUtils.isNotEmpty(moreList.get(p).getOrgName())){
                              run2.setText("    "+"机构名称:" + moreList.get(p).getOrgName() );
                              run2.addBreak();
                          }
                          if (StringUtils.isNotEmpty(moreList.get(p).getAgentPerson())){
                              run2.setText("    "+"经办律师:" + moreList.get(p).getAgentPerson());
                              run2.addBreak();
                          }
                      }
                      if (moreList.get(p).getIntermediaryType().equals("4")){
                          if (StringUtils.isNotEmpty(moreList.get(p).getOrgName())){
                              run2.setText("    "+"机构名称:" + moreList.get(p).getOrgName() );
                              run2.addBreak();
                          }
                          if (StringUtils.isNotEmpty(moreList.get(p).getAgentPerson())){
                              run2.setText("    "+"注册会计师:" + moreList.get(p).getAgentPerson());
                              run2.addBreak();
                          }
                      }
                      if (moreList.get(p).getIntermediaryType().equals("2")){
                          if (StringUtils.isNotEmpty(moreList.get(p).getOrgName())){
                              run2.setText("    "+"机构名称:" + moreList.get(p).getOrgName() );
                              run2.addBreak();
                          }
                          if (StringUtils.isNotEmpty(moreList.get(p).getAgentPerson())){
                              run2.setText("    "+"项目经办人:" + moreList.get(p).getAgentPerson());
                              run2.addBreak();
                          }
                      }
                      if (moreList.get(p).getIntermediaryType().equals("5")){
                          if (StringUtils.isNotEmpty( moreList.get(p).getOrgName())){
                              run2.setText("    "+"机构名称:" +  moreList.get(p).getOrgName() );
                              run2.addBreak();
                          }
                          if (StringUtils.isNotEmpty(moreList.get(p).getAgentPerson())){
                              run2.setText("    "+"注册资产评估师:" + moreList.get(p).getAgentPerson());
                              run2.addBreak();
                          }
                      }
                      if (moreList.get(p).getIntermediaryType().equals("6")){
                          if (StringUtils.isNotEmpty(moreList.get(p).getOrgName())){
                              run2.setText("    "+"机构名称:" +  moreList.get(p).getOrgName());
                              run2.addBreak();
                          }
                          if (StringUtils.isNotEmpty(moreList.get(p).getAgentPerson() )){
                              run2.setText("    "+"经办人:" + moreList.get(p).getAgentPerson() );
                              run2.addBreak();
                          }
                      }
                      paragraph=newpa2;
                  }
              }
              itPara = xdoc.getParagraphsIterator();
              continue;
          }
          else if("#股权结构图#".equals(paragraph.getText())){
              test.clearParagraph(paragraph);
              try {
                  if (imgUrl!=""){
                      if (imgUrl.indexOf("https") == -1){
                          imgUrl = imgUrl.replaceAll("http","https");
                      }
                      HttpURLConnection connection = (HttpURLConnection) new URL(imgUrl).openConnection();
                      InputStream img = connection.getInputStream();
                      paragraph.getRuns().get(0).addPicture(img, XWPFDocument.PICTURE_TYPE_JPEG, imgUrl, Units.toEMU(400), Units.toEMU(400));
                      paragraph.getRuns().get(0).getCTR().getDrawingList().get(0).getInlineList().get(0).getDocPr().setId(Long.parseLong(ids+""));
                      ids++;

                  }
                  } catch (InvalidFormatException e) {
                  e.printStackTrace();
              }
              continue;
          } else if("#主营业务柱状图#".equals(paragraph.getText())){
              test.clearParagraph(paragraph);
              if (mainIncomeVo != null && mainIncomeVo.getMainIncomeInfoList() != null){
                  List<Double> listCountries = new ArrayList<>();
                  List<Double> listCountries2 = new ArrayList<>();
                  List<Double> listCountries3 = new ArrayList<>();
                  List<Double> listCountries4 = new ArrayList<>();
                  List<String> series = new ArrayList<>();
                  for (int i=mainIncomeVo.getMainIncomeInfoList().size()-1;i>=0;i--){
                      if (!mainIncomeVo.getMainIncomeInfoList().get(i).getBusinessName().equals("合计")){
                          series.add(mainIncomeVo.getMainIncomeInfoList().get(i).getBusinessName());
                          if (StringUtils.isNotEmpty(mainIncomeVo.getOnePeriodForIncome())){
                              listCountries.add(Double.valueOf(toChangeNum(mainIncomeVo.getMainIncomeInfoList().get(i).getOnePeriodAmount()+"")));
                          }
                          if (StringUtils.isNotEmpty(mainIncomeVo.getThirdYearForIncome())){
                              listCountries2.add(Double.valueOf(toChangeNum(mainIncomeVo.getMainIncomeInfoList().get(i).getThirdYearAmount()+"")));
                          }
                          if (StringUtils.isNotEmpty(mainIncomeVo.getSecondYearForIncome())){
                              listCountries3.add(Double.valueOf(toChangeNum(mainIncomeVo.getMainIncomeInfoList().get(i).getSecondYearAmount()+"")));
                          }
                          if (StringUtils.isNotEmpty(mainIncomeVo.getFirstYearForIncome())){
                              listCountries4.add( Double.valueOf(toChangeNum(mainIncomeVo.getMainIncomeInfoList().get(i).getFirstYearAmount()+"")));
                          }
                      }
                  }
                  List<String> listLanguages = new ArrayList<>();
                  List<List<Double>> yValues = Lists.newArrayList();
                  for(Double i:listCountries4){
                      if(i != 0.0){
                          yValues.add(listCountries4);
                          if (StringUtils.isNotEmpty(mainIncomeVo.getFirstYearForIncome())){
                              listLanguages.add(mainIncomeVo.getFirstYearForIncome());
                          }
                          break;
                      }
                  }
                  for(Double i:listCountries3){
                      if(i != 0.0){
                          yValues.add(listCountries3);
                          if (StringUtils.isNotEmpty(mainIncomeVo.getSecondYearForIncome())){
                              listLanguages.add(mainIncomeVo.getSecondYearForIncome());
                          }
                          break;
                      }
                  }
                  for(Double i:listCountries2){
                      if(i != 0.0){
                          yValues.add(listCountries2);
                          if (StringUtils.isNotEmpty(mainIncomeVo.getThirdYearForIncome())){
                              listLanguages.add(mainIncomeVo.getThirdYearForIncome());
                          }
                          break;
                      }
                  }
                  for(Double i:listCountries){
                      if(i != 0.0){
                          yValues.add(listCountries);
                          if (StringUtils.isNotEmpty(mainIncomeVo.getOnePeriodForIncome())){
                              listLanguages.add(mainIncomeVo.getOnePeriodForIncome());
                          }
                          break;
                      }
                  }
                  List<List<Double>> zhuziList = Lists.newArrayList();
                  int len=yValues.get(0).size();
                  for (int k=0;k<len;k++){
                      List<Double> zhuzi = new ArrayList<>();
                      for (int p=0;p<yValues.size();p++){
                          zhuzi.add(yValues.get(p).get(k));
                      }
                      zhuziList.add(zhuzi);
                  }
                  Collections.reverse(series);
                  XWPFChart chart = xdoc.createChart(XDDFChart.DEFAULT_WIDTH * 8, XDDFChart.DEFAULT_HEIGHT * 10);
                  poiWordInsertChartUtils.setSimpleBarData(chart,"1","","",series,listLanguages,zhuziList);
                  xdoc.removeBodyElement(xdoc.getBodyElements().size()-1);
                  itPara = xdoc.getParagraphsIterator();
                  XWPFRun newRun = paragraph.createRun();
                  String relationId = xdoc.getRelationId(chart);
                  java.lang.reflect.Method attach = XWPFChart.class.getDeclaredMethod("attach", String.class, XWPFRun.class);
                  attach.setAccessible(true);
                  attach.invoke(chart, relationId, newRun);
                  chart.setChartWidth(XDDFChart.DEFAULT_WIDTH * 10);
                  chart.setChartHeight(XDDFChart.DEFAULT_HEIGHT * 8);
                  newRun.getCTR().getDrawingList().get(0).getInlineList().get(0).getDocPr().setId(Long.parseLong(ids+""));
                  ids++;
              }
          }
          else if("#问询回复/反馈意见流程#".equals(paragraph.getText())){
              test.clearParagraph(paragraph);
              SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
              if (ipoFeedbackList==null||ipoFeedbackList.size()==0){
                  XWPFRun run2 = paragraph.createRun();
                  run2.setFontFamily("宋体 (正文)");
                  run2.setFontSize(11);
                  run2.setText("彻底删除");
              }
              if (ipoFeedbackList!=null &&ipoFeedbackList.size()>0){
                  for (int b=0;b<ipoFeedbackList.size();b++){
                      XmlCursor cursor1 = paragraph.getCTP().newCursor();
                      cursor1.toNextSibling();
                      XWPFParagraph newpa1 = xdoc.insertNewParagraph(cursor1);
                      int sort=b+1;
                      XWPFRun run1 = newpa1.createRun();
                      run1.setText(sort+"、"+isNullEmp(ipoFeedbackList.get(b).getLetterName()));
                      run1.setFontSize(16);
                      run1.setColor("333399");
                      run1.setBold(true);
                      newpa1.setStyle("3");
                      newpa1.setSpacingAfter(0);
                      newpa1.setSpacingBefore(12);
                      run1.setFontFamily("微软雅黑");
                      XmlCursor cursor2= newpa1.getCTP().newCursor();
                      cursor2.toNextSibling();
                      XWPFParagraph newpa2 = xdoc.insertNewParagraph(cursor2);
                      newpa2.setSpacingBetween(1, LineSpacingRule.AUTO);
                      XWPFRun run2 = newpa2.createRun();
                      run2.setFontFamily("宋体");
                      run2.setFontSize(11);
                      if (StringUtils.isNotEmpty(ipoFeedbackList.get(b).getLetterFileNo())){
                          run2.setText("    "+"函件文号：" + ipoFeedbackList.get(b).getLetterFileNo());
                          run2.addBreak();
                      }
                      if (ipoFeedbackList.get(b).getLetterDate()!=null){
                          run2.setText("    "+"发函时间：" + df.format(ipoFeedbackList.get(b).getLetterDate()));
                          run2.addBreak();
                      }
                      if (ipoFeedbackList.get(b).getReturnDate()!=null){
                          run2.setText("    "+"回函时间：" + df.format(ipoFeedbackList.get(b).getReturnDate()));
                          run2.addBreak();
                      }
                      if (StringUtils.isNotEmpty(ipoFeedbackList.get(b).getIntervalDate())){
                          run2.setText("    "+"回函用时：" + ipoFeedbackList.get(b).getIntervalDate()+"天");
                          run2.addBreak();
                      }
                      if (StringUtils.isNotEmpty(String.valueOf(ipoFeedbackList.get(b).getQuestionCount()))){
                          run2.setText( "    "+"问题数量：共计" + ipoFeedbackList.get(b).getQuestionCount()+"个问题" );
                          run2.addBreak();
                      }
                      if (StringUtils.isNotEmpty(String.valueOf(ipoFeedbackList.get(b).getAnswerCount()))){
                          run2.setText("    "+ "回复情况：共计" + ipoFeedbackList.get(b).getAnswerCount() + "个回复");
                          run2.addBreak();
                      }
                      XmlCursor cursor3= newpa2.getCTP().newCursor();
                      cursor3.toNextSibling();
                      XWPFParagraph newpa3 = xdoc.insertNewParagraph(cursor3);
                      //插入图表
                      List<IpoQuestionLabelDto> list=ipoFeedbackList.get(b).getQuestionLabelList();
                      //XWPFChart chart = xdoc.getCharts().get(0);
                      List<String> series = new ArrayList<>();
                      series.add("countries");
                      // Category Axis Data
                      List<String> listLanguages = new ArrayList<>(10);
                      // Values
                      List<Double> listCountries = new ArrayList<>(10);
                      Collections.sort(list, new Comparator<IpoQuestionLabelDto>() {
                          @Override
                          public int compare(IpoQuestionLabelDto o1, IpoQuestionLabelDto o2) {
                              int i = Integer.parseInt(o2.getLabelCount()) - Integer.parseInt(o1.getLabelCount()) ;
                              if(i == 0){
                                  return  Integer.parseInt(o2.getLabelCount()) - Integer.parseInt(o1.getLabelCount());
                              }
                              return i;
                          }
                      });
                      for(int i = 0; i < list.size();i++) {
                          if (!"全部".equals(list.get(i).getLabelName())){
                              listCountries.add(Double.valueOf(list.get(i).getLabelCount()));
                              listLanguages.add(list.get(i).getLabelName());
                          }
                      }
                      List<List<Double>> yValues = Lists.newArrayList();
                      yValues.add(listCountries);
                      XWPFChart chart = xdoc.createChart(XDDFChart.DEFAULT_WIDTH * 10,  XDDFChart.DEFAULT_HEIGHT * 15);
                      poiWordInsertChartUtils.setSimpleBarData(chart,"0","","",series,listLanguages,yValues);
                      xdoc.removeBodyElement(xdoc.getBodyElements().size()-1);
                      itPara = xdoc.getParagraphsIterator();
                      XWPFRun newRun = newpa3.createRun();
                      String relationId = xdoc.getRelationId(chart);
                      java.lang.reflect.Method attach = XWPFChart.class.getDeclaredMethod("attach", String.class, XWPFRun.class);
                      attach.setAccessible(true);
                      attach.invoke(chart, relationId, newRun);
                      chart.setChartWidth(XDDFChart.DEFAULT_WIDTH * 10);
                      chart.setChartHeight(XDDFChart.DEFAULT_HEIGHT * 8);
                      newRun.getCTR().getDrawingList().get(0).getInlineList().get(0).getDocPr().setId(Long.parseLong(ids+""));
                      ids++;
                      newRun.addBreak();
                      paragraph=newpa3;
                  }
              }

              continue;
          }
          else if ("#审核关注问题流程#".equals(paragraph.getText())){
              test.clearParagraph(paragraph);
              SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
              if (baseList==null||baseList.size()==0){
                  XWPFRun run2 = paragraph.createRun();
                  run2.setFontFamily("宋体 (正文)");
                  run2.setFontSize(11);
                  run2.setText("彻底删除");
              }
              String type=wordMap.get("#板块类型#");
              String titleStr="";
              int sort=0;
              if (baseList!=null && baseList.size()>0){
                  for (int b=0;b<baseList.size();b++){
                      if(((HeadDataVo)dataMap.get("head")).getIsTechBoard()==0){
                          titleStr="发审委会议结果";
                      }else if(((HeadDataVo)dataMap.get("head")).getIsTechBoard()==1){
                          if("35".equals(baseList.get(b).getProcessTypeCode())){
                              titleStr="上市委会议结果";
                          }else if("38".equals(baseList.get(b).getProcessTypeCode())){
                              titleStr="注册结果";
                          }else if("44".equals(baseList.get(b).getProcessTypeCode())){
                              titleStr="上市委会议结果";
                          }
                      }
                      XmlCursor cursor1 = paragraph.getCTP().newCursor();
                      cursor1.toNextSibling();
                      XWPFParagraph newpa1 = xdoc.insertNewParagraph(cursor1);
                      sort++;
                      XWPFRun run1 = newpa1.createRun();
                      run1.setText(sort+"、"+titleStr);
                      run1.setFontSize(16);
                      run1.setColor("333399");
                      run1.setBold(true);
                      newpa1.setStyle("3");
                      newpa1.setSpacingAfter(0);
                      newpa1.setSpacingBefore(12);
                      run1.setFontFamily("微软雅黑");
                      XmlCursor cursor2 = newpa1.getCTP().newCursor();
                      cursor2.toNextSibling();
                      XWPFParagraph newpa2 = xdoc.insertNewParagraph(cursor2);
                      XWPFRun run2 = newpa2.createRun();
                      newpa2.setSpacingBetween(1, LineSpacingRule.AUTO);
                      run2.setFontFamily("宋体");
                      run2.setFontSize(11);
                      if(((HeadDataVo)dataMap.get("head")).getIsTechBoard()==0){
                          if (StringUtils.isNotEmpty(baseList.get(b).getRelationFileTitle())){
                              run2.setText("    "+"审核会议:" + baseList.get(b).getRelationFileTitle());
                              run2.addBreak();
                          }
                          if (StringUtils.isNotEmpty(baseList.get(b).getExamineDate())){
                              run2.setText("    "+"审核时间：" + baseList.get(b).getExamineDate());
                              run2.addBreak();
                          }
                          if (StringUtils.isNotEmpty(baseList.get(b).getIecResultStr())){
                              run2.setText("    "+"审核结果：" + baseList.get(b).getIecResultStr());
                              run2.addBreak();
                          }
                          if (StringUtils.isNotEmpty(baseList.get(b).getMember())){
                              run2.setText("    "+"发审会委员:" + baseList.get(b).getMember());
                              run2.addBreak();
                          }
                      }else if(((HeadDataVo)dataMap.get("head")).getIsTechBoard()==1){
                          if("35".equals(baseList.get(b).getProcessTypeCode())){
                              if (StringUtils.isNotEmpty(baseList.get(b).getRelationFileTitle())){
                                  run2.setText("    "+"审核会议:" + baseList.get(b).getRelationFileTitle());
                                  run2.addBreak();
                              }
                              if (StringUtils.isNotEmpty(baseList.get(b).getExamineDate())){
                                  run2.setText("    "+"审核时间：" + baseList.get(b).getExamineDate());
                                  run2.addBreak();
                              }
                              if (StringUtils.isNotEmpty(baseList.get(b).getIecResultStr())){
                                  run2.setText("    "+"审核结果：" + baseList.get(b).getIecResultStr());
                                  run2.addBreak();
                              }
                              if (StringUtils.isNotEmpty(baseList.get(b).getMember())){
                                  run2.setText("    "+"上市委员会委员:" + baseList.get(b).getMember());
                                  run2.addBreak();
                              }
                          }else if ("38".equals(baseList.get(b).getProcessTypeCode())){
                              if (StringUtils.isNotEmpty(baseList.get(b).getExamineDate())){
                                  run2.setText("    "+"注册时间：" + baseList.get(b).getExamineDate());
                                  run2.addBreak();
                              }
                              if (StringUtils.isNotEmpty(baseList.get(b).getIecResultStr())){
                                  run2.setText("    "+"注册结果：" + baseList.get(b).getIecResultStr());
                                  run2.addBreak();
                              }
                          }else if ("44".equals(baseList.get(b).getProcessTypeCode())){
                              if (StringUtils.isNotEmpty(baseList.get(b).getRelationFileTitle())){
                                  run2.setText("    "+"复审会议:" + baseList.get(b).getRelationFileTitle());
                                  run2.addBreak();
                              }
                              if (StringUtils.isNotEmpty(baseList.get(b).getExamineDate())){
                                  run2.setText("    "+"复审时间：" + baseList.get(b).getExamineDate());
                                  run2.addBreak();
                              }
                              if (StringUtils.isNotEmpty(baseList.get(b).getIecResultStr())){
                                  run2.setText("    "+"复审结果：" + baseList.get(b).getIecResultStr());
                                  run2.addBreak();
                              }
                              if (StringUtils.isNotEmpty(baseList.get(b).getMember())){
                                  run2.setText("    "+"上市委员会委员:" + baseList.get(b).getMember());
                                  run2.addBreak();
                              }

                          }
                      }
                      paragraph=newpa2;
                  }
              }
              if (resultList!=null && resultList.size()>0 ){
                  for (int c=0;c<resultList.size();c++){
                      XmlCursor cursor1 = paragraph.getCTP().newCursor();
                      cursor1.toNextSibling();
                      XWPFParagraph newpa1 = xdoc.insertNewParagraph(cursor1);
                      sort++;
                      XWPFRun run3 = newpa1.createRun();
                      run3.setText(sort+"、"+resultList.get(c).getLetterName());
                      run3.setFontSize(16);
                      newpa1.setSpacingAfter(0);
                      newpa1.setSpacingBefore(12);
                      run3.setColor("333399");
                      run3.setBold(true);
                      newpa1.setStyle("3");
                      run3.setFontFamily("微软雅黑");
                      XmlCursor cursor2 = newpa1.getCTP().newCursor();
                      cursor2.toNextSibling();
                      XWPFParagraph newpa2 = xdoc.insertNewParagraph(cursor2);
                      newpa2.setSpacingBetween(1, LineSpacingRule.AUTO);
                      if (StringUtils.isNotEmpty(resultList.get(c).getLetterFileNo())){
                          XWPFRun run8 = newpa2.createRun();
                          run8.setFontFamily("宋体");
                          run8.setFontSize(11);
                          run8.setText("    函件文号：" + resultList.get(c).getLetterFileNo());
                      }
                      if (resultList.get(c).getLetterDate()!=null){
                          XWPFRun run8 = newpa2.createRun();
                          run8.setFontFamily("宋体");
                          run8.setFontSize(11);
                          run8.setText("    发函时间：" + df.format(resultList.get(c).getLetterDate()));
                      }
                      if (resultList.get(c).getReturnDate()!=null){
                          XWPFRun run8 = newpa2.createRun();
                          run8.setFontFamily("宋体");
                          run8.setFontSize(11);
                          run8.setText("    回函时间：" + df.format(resultList.get(c).getReturnDate()));
                      }
                      if (StringUtils.isNotEmpty(resultList.get(c).getIntervalDate())){
                          XWPFRun run8 = newpa2.createRun();
                          run8.setFontFamily("宋体");
                          run8.setFontSize(11);
                          run8.setText("    回函用时：" + resultList.get(c).getIntervalDate());
                      }
                      if (StringUtils.isNotEmpty(resultList.get(c).getIntervalDate())){
                          XWPFRun run8 = newpa2.createRun();
                          run8.setFontFamily("宋体");
                          run8.setFontSize(11);
                          run8.setText("    问题数量：" + "共计"+resultList.get(c).getQuestionCount()+"个问题");
                      }
                      if (StringUtils.isNotEmpty(resultList.get(c).getIntervalDate())){
                          XWPFRun run8 = newpa2.createRun();
                          run8.setFontFamily("宋体");
                          run8.setFontSize(11);
                          run8.setText("    回复情况：" + "共计"+resultList.get(c).getAnswerCount()+"个回复");
                      }
                      XmlCursor cursor3 = newpa2.getCTP().newCursor();
                      cursor3.toNextSibling();
                      XWPFParagraph newpa3 = xdoc.insertNewParagraph(cursor3);
                      newpa3.setSpacingBetween(1, LineSpacingRule.AUTO);
                      //插入图表
                      List<IpoQuestionLabelDto> list=resultList.get(c).getQuestionLabelList();
                      List<String> series = new ArrayList<>();
                      series.add("countries");
                      List<String> listLanguages = new ArrayList<>();
                      List<Double> listCountries = new ArrayList<>();
                      Collections.sort(list, new Comparator<IpoQuestionLabelDto>() {
                          @Override
                          public int compare(IpoQuestionLabelDto o1, IpoQuestionLabelDto o2) {
                              int i = Integer.parseInt(o2.getLabelCount()) - Integer.parseInt(o1.getLabelCount()) ;
                              if(i == 0){
                                  return  Integer.parseInt(o2.getLabelCount()) - Integer.parseInt(o1.getLabelCount());
                              }
                              return i;
                          }
                      });
                      for(int i = 0; i < list.size();i++) {
                          if(!"全部".equals(list.get(i).getLabelName())){
                              listCountries.add(Double.valueOf(list.get(i).getLabelCount()));
                              listLanguages.add(list.get(i).getLabelName());
                          }
                      }
                      List<List<Double>> yValues = Lists.newArrayList();
                      yValues.add(listCountries);
                      XWPFChart chart = xdoc.createChart(XDDFChart.DEFAULT_WIDTH * 10,  XDDFChart.DEFAULT_HEIGHT * 15);
                      poiWordInsertChartUtils.setSimpleBarData(chart,"0","","",series,listLanguages,yValues);
                      xdoc.removeBodyElement(xdoc.getBodyElements().size()-1);
                      itPara = xdoc.getParagraphsIterator();
                      XWPFRun newRun = newpa3.createRun();
                      String relationId = xdoc.getRelationId(chart);
                      java.lang.reflect.Method attach = XWPFChart.class.getDeclaredMethod("attach", String.class, XWPFRun.class);
                      attach.setAccessible(true);
                      attach.invoke(chart, relationId, newRun);
                      chart.setChartWidth(XDDFChart.DEFAULT_WIDTH * 10);
                      chart.setChartHeight(XDDFChart.DEFAULT_HEIGHT * 8);
                      newRun.getCTR().getDrawingList().get(0).getInlineList().get(0).getDocPr().setId(Long.parseLong(ids+""));
                      ids++;
                      newRun.addBreak();
                      if(resultList.get(c).getQuestionList()!=null){
                          //问
                          List<IpoFeedbackQuestionDto>  questionList=resultList.get(c).getQuestionList();
                          int questTitle=0;
                          for (int d=0;d<questionList.size();d++){
                              questTitle=d+1;
                              XWPFRun run4 = newpa3.createRun();
                              run4.setText("（"+questTitle+"）"+"问");
                              run4.setFontSize(10);
                              run4.setColor("000000");
                              run4.setBold(true);
                              newpa3.setStyle("5");
                              run4.setFontFamily("微软雅黑");
                              run4.addBreak();
                              String questLabel="";
                              for (int e=0;e<questionList.get(d).getQuestionLabelList().size();e++){
                                  questLabel+=questionList.get(d).getQuestionLabelList().get(e).getLabelName()+",";
                              }
                              XWPFRun run6 = newpa3.createRun();
                              run6.setFontFamily("宋体");
                              run6.setFontSize(11);
                              run6.setTextPosition(0);
                              run6.setText("    问题类型:" + questLabel);
                              run6.addBreak();
                              XWPFRun run5 = newpa3.createRun();
                              run5.setFontFamily("宋体");
                              run5.setFontSize(11);
                              run5.setTextPosition(0);
                              String answer="";
                              if (StringUtils.isNotEmpty( questionList.get(d).getQuestion())){
                                  answer="已回复";
                              }
                              run5.setText("    回复情况:" + answer);
                              run5.addBreak();
                              XWPFRun run7= newpa3.createRun();
                              run7.setFontFamily("宋体");
                              run7.setFontSize(11);
                              run7.setTextPosition(1);
                              run7.setText( questionList.get(d).getQuestion());
                              run7.addBreak();
                          }
                      }
                      paragraph=newpa3;
                  }
              }
              itPara = xdoc.getParagraphsIterator();

          }
          else if("#主要供应商情况#".equals(paragraph.getText())){
              test.clearParagraph(paragraph);
              if (supplierMainList!=null && supplierMainList.size()>0){
                  for(int z=supplierMainList.size()-1;z>=0;z--){
                      List<SupplierCustomerInfoDto> list=supplierMainList.get(z).getSupplierCustomerInfoList();
                      if (list.size()>1){
                          int sort=z+1;
                          XmlCursor cursor1 = paragraph.getCTP().newCursor();
                          cursor1.toNextSibling();
                          XWPFParagraph newpa1 = paragraph;
                          if (z==0){
                              newpa1=paragraph;
                          }else {
                              newpa1 = xdoc.insertNewParagraph(cursor1);
                          }
                          //标题
                          XWPFRun newParaRun = newpa1.createRun();
                          newParaRun.setFontFamily("微软雅黑");
                          newParaRun.setFontSize(11);
                          newParaRun.setBold(false);
                          newParaRun.setText("  "+"("+sort+")"+isNullEmp(supplierMainList.get(z).getTitle()));
                          newpa1.setStyle("4");//标题三
                          //单位
                          XmlCursor cursor2 = newpa1.getCTP().newCursor();
                          cursor2.toNextSibling();
                          XWPFParagraph newpa2 = xdoc.insertNewParagraph(cursor2);
                          itPara = xdoc.getParagraphsIterator();
                          XWPFRun newParaRunDW = newpa2.createRun();
                          newpa2.setFontAlignment(3);//对齐方式  右对齐
                          newpa2.setSpacingBetween(1, LineSpacingRule.AUTO);
                          newParaRunDW.setFontFamily("微软雅黑");
                          newParaRunDW.setFontSize(9);
                          newParaRunDW.setColor("333399");
                          newParaRunDW.setBold(true);
                          newParaRunDW.setText("单位：万元");
                          //表格
                          XmlCursor cursor3=newpa2.getCTP().newCursor();
                          cursor3.toNextSibling();
                          XWPFParagraph newpa3 = xdoc.insertNewParagraph(cursor3);
                          itPara = xdoc.getParagraphsIterator();
                          XWPFTable table =xdoc.insertNewTbl(newpa3.getCTP().newCursor());
                          SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
                          //判断空列
                          Map<String,Boolean> map=new HashMap<>();
                          map.put("onePeriod",false);
                          map.put("thirdYear",false);
                          map.put("secondYear",false);
                          map.put("firstYear",false);
                          for (int p=0;p<list.size();p++){
                              if (!"合计".equals(list.get(p).getCompanyName())&&list.get(p).getOnePeriodAmount()!=null){
                                  map.put("onePeriod",true);
                              }
                              if (!"合计".equals(list.get(p).getCompanyName())&&list.get(p).getThirdYearAmount()!=null){
                                  map.put("thirdYear",true);
                              }
                              if (!"合计".equals(list.get(p).getCompanyName())&&list.get(p).getSecondYearAmount()!=null){
                                  map.put("secondYear",true);
                              }
                              if (!"合计".equals(list.get(p).getCompanyName())&&list.get(p).getFirstYearAmount()!=null){
                                  map.put("firstYear",true);
                              }
                          }
                          //第一行赋值
                          int cellSize=0;
                          XWPFTableRow row_0 = table.getRow(0);
                          row_0.getCell(0).setText("");
                          row_0.addNewTableCell().setText("");
                          if (map.get("onePeriod")){
                              cellSize+=2;
                              row_0.addNewTableCell();
                              setTitleBold(cellSize,row_0,df1.format(supplierMainList.get(z).getReportPeriod()),8,true);
                              row_0.addNewTableCell().setText("");
                          }
                          if (map.get("thirdYear")){
                              cellSize+=2;
                              row_0.addNewTableCell();
                              setTitleBold(cellSize,row_0,supplierMainList.get(z).getThirdYearForSupplier(),8,true);
                              row_0.addNewTableCell().setText("");
                          }
                          if (map.get("secondYear")){
                              cellSize+=2;
                              row_0.addNewTableCell();
                              setTitleBold(cellSize,row_0,supplierMainList.get(z).getSecondYearForSupplier(),8,true);
                              row_0.addNewTableCell().setText("");
                          }
                          if (map.get("firstYear")){
                              cellSize+=2;
                              row_0.addNewTableCell();
                              setTitleBold(cellSize,row_0,supplierMainList.get(z).getFirstYearForSupplier(),8,true);
                              row_0.addNewTableCell().setText("");
                          }
                          for (int p=0;p<row_0.getTableCells().size();p++){
                              row_0.getCell(p).setColor("E7F3FF");
                          }
                          //第二行赋值
                          XWPFTableRow row_1 = table.createRow();
                          setTitleBold(0,row_1,"序号",8,true);
                          setTitleBold(1,row_1,"公司",8,true);
                          row_1.getCell(0).setColor("E7F3FF");
                          row_1.getCell(1).setColor("E7F3FF");
                          for (int p=2;p<row_0.getTableCells().size();p+=2){
                              setTitleBold(p,row_1,"金额",8,true);
                              setTitleBold(p+1,row_1,"占比",8,true);
                              row_1.getCell(p).setColor("E7F3FF");
                              row_1.getCell(p+1).setColor("E7F3FF");
                          }
                          //内容赋值
                          for (int n=0;n<list.size();n++){
                              XWPFTableRow row_2 = table.createRow();
                              setTitleBold(0,row_2,n+1+"",8,true);
                              setTitleBold(1,row_2,list.get(n).getCompanyName(),7,false);
                              int order=2;
                              if (map.get("onePeriod")){
                                  setTitleBold(order,row_2,isNullBigDouble(list.get(n).getOnePeriodAmount()),7,false);
                                  order++;
                                  setTitleBold(order,row_2,twoMarkStrBFH(list.get(n).getOnePeriodRatio()+""),7,false);
                                  order++;
                              }
                              if (map.get("thirdYear")){
                                  setTitleBold(order,row_2,isNullBigDouble(list.get(n).getThirdYearAmount()),7,false);
                                  order++;
                                  setTitleBold(order,row_2,twoMarkStrBFH(list.get(n).getThirdYearRatio()+""),7,false);
                                  order++;
                              }
                              if (map.get("secondYear")){
                                  setTitleBold(order,row_2,isNullBigDouble(list.get(n).getSecondYearAmount()),7,false);
                                  order++;
                                  setTitleBold(order,row_2,twoMarkStrBFH(list.get(n).getSecondYearRatio()+""),7,false);
                                  order++;
                              }
                              if (map.get("firstYear")){
                                  setTitleBold(order,row_2,isNullBigDouble(list.get(n).getFirstYearAmount()),7,false);
                                  order++;
                                  setTitleBold(order,row_2,twoMarkStrBFH(list.get(n).getFirstYearRatio()+""),7,false);
                                  order++;
                              }
                              if (n%2!=0){
                                  for (int p=0;p<order;p++){
                                      row_2.getCell(p).setColor("E7F3FF");
                                  }
                              }
                          }
                          //合并行
                          fillTable(table,2000,"1");
                          mergeCellsHorizontal(table,0,0,1);
                          int mergeOrder1=2;
                          int mergeOrder2=3;
                          if (map.get("onePeriod")){
                              mergeCellsHorizontal(table,0,mergeOrder1,mergeOrder2);
                              mergeOrder1+=2;
                              mergeOrder2+=2;
                          }
                          if (map.get("thirdYear")){
                              mergeCellsHorizontal(table,0,mergeOrder1,mergeOrder2);
                              mergeOrder1+=2;
                              mergeOrder2+=2;
                          }
                          if (map.get("secondYear")){
                              mergeCellsHorizontal(table,0,mergeOrder1,mergeOrder2);
                              mergeOrder1+=2;
                              mergeOrder2+=2;
                          }
                          if (map.get("firstYear")){
                              mergeCellsHorizontal(table,0,mergeOrder1,mergeOrder2);
                              mergeOrder1+=2;
                              mergeOrder2+=2;
                          }
                          //边框颜色
                          setBorderStyle(table);
                      }
                      else {
                          XWPFRun newParaRun = paragraph.createRun();
                          newParaRun.setFontFamily("宋体 (正文)");
                          newParaRun.setFontSize(11);
                          newParaRun.setBold(false);
                          newParaRun.setText("彻底删除");
                      }
                  }
              }


              continue;
          }
          else if("#主要客户情况#".equals(paragraph.getText())){
              test.clearParagraph(paragraph);
              if (customerMainList!=null && customerMainList.size()>0){
                  for(int z=customerMainList.size()-1;z>=0;z--){
                      List<SupplierCustomerInfoDto> list=customerMainList.get(z).getSupplierCustomerInfoList();
                      if (list.size()>1){
                          int sort=z+1;
                          XmlCursor cursor1 = paragraph.getCTP().newCursor();
                          cursor1.toNextSibling();
                          XWPFParagraph newpa1 = paragraph;
                          if (z==0){
                              newpa1=paragraph;
                          }else {
                              newpa1 = xdoc.insertNewParagraph(cursor1);
                          }
                          SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
                          //标题
                          XWPFRun newParaRun = newpa1.createRun();
                          newParaRun.setFontFamily("微软雅黑");
                          newParaRun.setFontSize(11);
                          newParaRun.setBold(false);
                          newParaRun.setText("  "+"("+sort+")"+isNullEmp(customerMainList.get(z).getTitle()));
                          newpa1.setStyle("4");
                          //单位
                          XmlCursor cursor2 = newpa1.getCTP().newCursor();
                          cursor2.toNextSibling();
                          XWPFParagraph newpa2 = xdoc.insertNewParagraph(cursor2);
                          itPara = xdoc.getParagraphsIterator();
                          XWPFRun newParaRunDW = newpa2.createRun();
                          newpa2.setFontAlignment(3);//对齐方式  右对齐
                          newpa2.setSpacingBetween(1, LineSpacingRule.AUTO);
                          newParaRunDW.setFontFamily("微软雅黑");
                          newParaRunDW.setFontSize(9);
                          newParaRunDW.setColor("333399");
                          newParaRunDW.setBold(true);
                          newParaRunDW.setText("单位：万元");
                          //表格
                          XmlCursor cursor3=newpa2.getCTP().newCursor();
                          cursor3.toNextSibling();
                          XWPFParagraph newpa3 = xdoc.insertNewParagraph(cursor3);
                          itPara = xdoc.getParagraphsIterator();
                          XWPFTable table =xdoc.insertNewTbl(newpa3.getCTP().newCursor());
                          //判断空列
                          Map<String,Boolean> map=new HashMap<>();
                          map.put("onePeriod",false);
                          map.put("thirdYear",false);
                          map.put("secondYear",false);
                          map.put("firstYear",false);
                          for (int p=0;p<list.size();p++){
                              if (!"合计".equals(list.get(p).getCompanyName())&&list.get(p).getOnePeriodAmount()!=null){
                                  map.put("onePeriod",true);
                              }
                              if (!"合计".equals(list.get(p).getCompanyName())&&list.get(p).getThirdYearAmount()!=null){
                                  map.put("thirdYear",true);
                              }
                              if (!"合计".equals(list.get(p).getCompanyName())&&list.get(p).getSecondYearAmount()!=null){
                                  map.put("secondYear",true);
                              }
                              if (!"合计".equals(list.get(p).getCompanyName())&&list.get(p).getFirstYearAmount()!=null){
                                  map.put("firstYear",true);
                              }
                          }
                          //第一行赋值
                          int cellSize=0;
                          XWPFTableRow row_0 = table.getRow(0);
                          row_0.getCell(0).setText("");
                          row_0.addNewTableCell().setText("");
                          if (map.get("onePeriod")){
                              cellSize+=2;
                              row_0.addNewTableCell();
                              setTitleBold(cellSize,row_0,df1.format(customerMainList.get(z).getReportPeriod()),8,true);
                              row_0.addNewTableCell().setText("");
                          }
                          if (map.get("thirdYear")){
                              cellSize+=2;
                              row_0.addNewTableCell();
                              setTitleBold(cellSize,row_0,customerMainList.get(z).getThirdYearForCustomer(),8,true);
                              row_0.addNewTableCell().setText("");
                          }
                          if (map.get("secondYear")){
                              cellSize+=2;
                              row_0.addNewTableCell();
                              setTitleBold(cellSize,row_0,customerMainList.get(z).getSecondYearForCustomer(),8,true);
                              row_0.addNewTableCell().setText("");
                          }
                          if (map.get("firstYear")){
                              cellSize+=2;
                              row_0.addNewTableCell();
                              setTitleBold(cellSize,row_0,customerMainList.get(z).getFirstYearForCustomer(),8,true);
                              row_0.addNewTableCell().setText("");
                          }
                          for (int p=0;p<row_0.getTableCells().size();p++){
                              row_0.getCell(p).setColor("E7F3FF");
                          }
                          //第二行赋值
                          XWPFTableRow row_1 = table.createRow();
                          setTitleBold(0,row_1,"序号",8,true);
                          setTitleBold(1,row_1,"公司",8,true);
                          row_1.getCell(0).setColor("E7F3FF");
                          row_1.getCell(1).setColor("E7F3FF");
                          for (int p=2;p<row_0.getTableCells().size();p+=2){
                              setTitleBold(p,row_1,"金额",8,true);
                              setTitleBold(p+1,row_1,"占比",8,true);
                              row_1.getCell(p).setColor("E7F3FF");
                              row_1.getCell(p+1).setColor("E7F3FF");
                          }
                          //内容赋值
                          for (int n=0;n<list.size();n++){
                              XWPFTableRow row_2 = table.createRow();
                              setTitleBold(0,row_2,n+1+"",8,true);
                              setTitleBold(1,row_2,list.get(n).getCompanyName(),7,false);
                              int order=2;
                              if (map.get("onePeriod")){
                                  setTitleBold(order,row_2,isNullBigDouble(list.get(n).getOnePeriodAmount()),7,false);
                                  order++;
                                  setTitleBold(order,row_2,twoMarkStrBFH(list.get(n).getOnePeriodRatio()+""),7,false);
                                  order++;
                              }
                              if (map.get("thirdYear")){
                                  setTitleBold(order,row_2,isNullBigDouble(list.get(n).getThirdYearAmount()),7,false);
                                  order++;
                                  setTitleBold(order,row_2,twoMarkStrBFH(list.get(n).getThirdYearRatio()+""),7,false);
                                  order++;
                              }
                              if (map.get("secondYear")){
                                  setTitleBold(order,row_2,isNullBigDouble(list.get(n).getSecondYearAmount()),7,false);
                                  order++;
                                  setTitleBold(order,row_2,twoMarkStrBFH(list.get(n).getSecondYearRatio()+""),7,false);
                                  order++;
                              }
                              if (map.get("firstYear")){
                                  setTitleBold(order,row_2,isNullBigDouble(list.get(n).getFirstYearAmount()),7,false);
                                  order++;
                                  setTitleBold(order,row_2,twoMarkStrBFH(list.get(n).getFirstYearRatio()+""),7,false);
                                  order++;
                              }
                              if (n%2!=0){
                                  for (int p=0;p<order;p++){
                                      row_2.getCell(p).setColor("E7F3FF");
                                  }
                              }
                          }
                          //合并行
                          fillTable(table,2000,"1");
                          mergeCellsHorizontal(table,0,0,1);
                          int mergeOrder1=2;
                          int mergeOrder2=3;
                          if (map.get("onePeriod")){
                              mergeCellsHorizontal(table,0,mergeOrder1,mergeOrder2);
                              mergeOrder1+=2;
                              mergeOrder2+=2;
                          }
                          if (map.get("thirdYear")){
                              mergeCellsHorizontal(table,0,mergeOrder1,mergeOrder2);
                              mergeOrder1+=2;
                              mergeOrder2+=2;
                          }
                          if (map.get("secondYear")){
                              mergeCellsHorizontal(table,0,mergeOrder1,mergeOrder2);
                              mergeOrder1+=2;
                              mergeOrder2+=2;
                          }
                          if (map.get("firstYear")){
                              mergeCellsHorizontal(table,0,mergeOrder1,mergeOrder2);
                              mergeOrder1+=2;
                              mergeOrder2+=2;
                          }
                          //边框颜色
                          setBorderStyle(table);
                      }
                      else {
                          XWPFRun newParaRun = paragraph.createRun();
                          newParaRun.setFontFamily("宋体 (正文)");
                          newParaRun.setFontSize(11);
                          newParaRun.setBold(false);
                          newParaRun.setText("彻底删除");
                      }
                  }
              }
              continue;
          }
          else if("#行业毛利率对比#".equals(paragraph.getText())){
              test.clearParagraph(paragraph);
              if (industryCompareList!=null && industryCompareList.size()>0){
                  for(int z=industryCompareList.size()-1;z>=0;z--){
                      int sort=z+1;
                      XmlCursor cursor1 = paragraph.getCTP().newCursor();
                      cursor1.toNextSibling();
                      XWPFParagraph newpa1 = paragraph;
                      if (z==0){
                          newpa1=paragraph;
                      }else {
                          newpa1 = xdoc.insertNewParagraph(cursor1);
                      }
                      List<IndustryCompareRateDetailDto> list=industryCompareList.get(z).getIndustryCompareRateDetailList();
                      if (industryCompareList.size()==1&&StringUtils.isNotEmpty(industryCompareList.get(z).getTitle())){
                          //标题
                          XWPFRun newParaRun = newpa1.createRun();
                          newParaRun.setFontFamily("微软雅黑");
                          newParaRun.setFontSize(11);
                          newParaRun.setBold(false);
                          newParaRun.setText("  "+"("+sort+")"+isNullEmp(industryCompareList.get(z).getTitle()));
                          newpa1.setStyle("4");
                          newpa1.createRun();
                      }else if (industryCompareList.size()>1){
                          //标题
                          XWPFRun newParaRun = newpa1.createRun();
                          newParaRun.setFontFamily("微软雅黑");
                          newParaRun.setFontSize(11);
                          newParaRun.setBold(false);
                          newParaRun.setText("  "+"("+sort+")"+isNullEmp(industryCompareList.get(z).getTitle()));
                          newpa1.setStyle("4");
                          newpa1.createRun();
                      }
                      //折线图
                      List<String> series = new ArrayList<>();
                      List<String> listLanguages = new ArrayList<>();
//                      listLanguages.add(industryCompareList.get(z).getFirstYear());
//                      listLanguages.add(industryCompareList.get(z).getSecondYear());
//                      listLanguages.add(industryCompareList.get(z).getThirdYear());
                      List<String> listCountries = new ArrayList<>();
                      List<String> listCountries2 = new ArrayList<>(10);
                      List<String> listCountries3 = new ArrayList<>(10);
                      List<List<String>> xTitle = Lists.newArrayList();
                      for (int u=list.size()-1;u>=0;u--){
                          List<String> title=new ArrayList<>();
                          listCountries.add(list.get(u).getThirdYearRate()+"");
                          listCountries2.add(list.get(u).getSecondYearRate()+"");
                          listCountries3.add(list.get(u).getFirstYearRate()+"");
                          series.add(list.get(u).getCompanyName());
                          if (!"null".equals(list.get(u).getFirstYearRate()+"")){
                              title.add(industryCompareList.get(z).getFirstYear());
                          }
                          if (!"null".equals(list.get(u).getSecondYearRate()+"")){
                              title.add(industryCompareList.get(z).getSecondYear());
                          }
                          if (!"null".equals(list.get(u).getThirdYearRate()+"")) {
                              title.add(industryCompareList.get(z).getThirdYear());
                          }
                          xTitle.add(title);
                      }
                      List<List<String>> yValues = Lists.newArrayList();
//                      yValues.add(listCountries3);
//                      yValues.add(listCountries2);
//                      yValues.add(listCountries);
                      for(String i:listCountries3){
                          if(!"null".equals(i)){
                              yValues.add(listCountries3);
                              break;
                          }
                      }
                      for(String i:listCountries2){
                          if(!"null".equals(i)){
                              yValues.add(listCountries2);
                              break;
                          }
                      }
                      for(String i:listCountries){
                          if(!"null".equals(i)){
                              yValues.add(listCountries);
                              break;
                          }
                      }
                      List<List<Double>> zhuziList = Lists.newArrayList();

                      int len=yValues.get(0).size();
                      for (int k=0;k<len;k++){
                          List<Double> zhuzi = new ArrayList<>();
                          for (int p=0;p<yValues.size();p++){
                              if (!"null".equals(yValues.get(p).get(k))){
                                  zhuzi.add(Double.valueOf(yValues.get(p).get(k)));
                              }
                          }
                          zhuziList.add(zhuzi);
                      }
                      XWPFChart chart1 = xdoc.createChart(XDDFChart.DEFAULT_WIDTH * 8, XDDFChart.DEFAULT_HEIGHT * 10);
                      poiWordInsertChartUtils.setSimpleBarDataLine(chart1,"3","","",series,xTitle,zhuziList);
                      xdoc.removeBodyElement(xdoc.getBodyElements().size()-1);
                      itPara = xdoc.getParagraphsIterator();
                      XmlCursor cursor4 = newpa1.getCTP().newCursor();
                      cursor4.toNextSibling();
                      XWPFParagraph newpa4 = xdoc.insertNewParagraph(cursor4);
                      newpa4.setFontAlignment(2);
                      XWPFRun newRun1 = newpa4.createRun();
                      String relationId1 = xdoc.getRelationId(chart1);
                      java.lang.reflect.Method attach1 = XWPFChart.class.getDeclaredMethod("attach", String.class, XWPFRun.class);
                      attach1.setAccessible(true);
                      attach1.invoke(chart1, relationId1, newRun1);
                      chart1.setChartWidth(XDDFChart.DEFAULT_WIDTH * 10);
                      chart1.setChartHeight(XDDFChart.DEFAULT_HEIGHT * 8);
                      newRun1.getCTR().getDrawingList().get(0).getInlineList().get(0).getDocPr().setId(Long.parseLong(ids+""));
                      ids++;
                      newRun1.addBreak();
                      //表格
                      XmlCursor cursor2=newpa4.getCTP().newCursor();
                      cursor2.toNextSibling();
                      XWPFParagraph newpa2 = xdoc.insertNewParagraph(cursor2);
                      itPara = xdoc.getParagraphsIterator();
                      XWPFTable table =xdoc.insertNewTbl(newpa2.getCTP().newCursor());
                      XWPFTableRow row_0 = table.getRow(0);
                      SimpleDateFormat df1 = new SimpleDateFormat("yyyy年");
//                      row_0.getCell(0).setText("");
                      setTitleBold(0,row_0,"企业名称",11,true);
                      row_0.addNewTableCell();
                      setTitleBold(1,row_0,industryCompareList.get(z).getThirdYear(),11,true);
                      row_0.addNewTableCell();
                      setTitleBold(2,row_0,industryCompareList.get(z).getSecondYear(),11,true);
                      row_0.addNewTableCell();
                      setTitleBold(3,row_0,industryCompareList.get(z).getFirstYear(),11,true);
                      for (int p=0;p<4;p++){
                          row_0.getCell(p).setColor("E7F3FF");
                      }
                      for (int n=0;n<list.size();n++){
                          XWPFTableRow row_1 = table.createRow();
                          setTitleBold(0,row_1,list.get(n).getCompanyName(),11,true);
                          setTitleBold(1,row_1,twoMarkStrBFH(list.get(n).getThirdYearRate()+""),11,false);
                          setTitleBold(2,row_1,twoMarkStrBFH(list.get(n).getSecondYearRate()+""),11,false);
                          setTitleBold(3,row_1,twoMarkStrBFH(list.get(n).getFirstYearRate()+""),11,false);
                          if (n%2!=0){
                              for (int p=0;p<4;p++){
                                  row_1.getCell(p).setColor("E7F3FF");
                              }
                          }
                      }
                      fillTable(table,3000,"2");
                      //边框颜色
                      setBorderStyle(table);
                  }
              }else {
                  XWPFRun newParaRun = paragraph.createRun();
                  newParaRun.setFontFamily("宋体 (正文)");
                  newParaRun.setFontSize(11);
                  newParaRun.setBold(false);
                  newParaRun.setText("彻底删除");
              }

              continue;
          }
      }

      Iterator<XWPFTable> itTable = xdoc.getTablesIterator();
      while (itTable.hasNext()) {
          XWPFTable table = itTable.next();
          int rowCnt = 0;
          // 迭代行，默认从0开始
          lableA:
          for (int i = 0; i < table.getNumberOfRows(); i++) {
              XWPFTableRow tr = table.getRow(i);
              List<XWPFTableCell> cellList = tr.getTableCells();
              // 迭代列，默认从0开始
              for (int j = 0; j < cellList.size(); j++) {
                  XWPFTableCell td = cellList.get(j);// 取得单元格
                  if(StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#公司简称#") != -1){
                      test.replaceTableCell(td,wordMap.get("#公司简称#"));
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#公司名字#") != -1) {
                      test.replaceTableCell(td,wordMap.get("#公司名字#"));
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#证券代码#") != -1) {
                      test.replaceTableCell(td,wordMap.get("#证券代码#"));
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#拟上市板块#") != -1) {
                      test.replaceTableCell(td,wordMap.get("#拟上市板块#"));
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#拟上市板块表格文字#") != -1) {
                      test.replaceTableCell(td,wordMap.get("#拟上市板块表格文字#"));
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#企业性质#") != -1) {
                      test.replaceTableCell(td,wordMap.get("#企业性质#"));
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#注册地址#") != -1) {
                      test.replaceTableCell(td,wordMap.get("#注册地址#"));
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#注册资本#") != -1) {
                      test.replaceTableCell(td,wordMap.get("#注册资本#"));
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#配售机制#") != -1) {
                      test.replaceTableCell(td,wordMap.get("#配售机制#"));
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#证监会行业#") != -1) {
                      test.replaceTableCell(td,wordMap.get("#证监会行业#"));
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#实际控制人#") != -1) {
                      test.replaceTableCell(td,wordMap.get("#实际控制人#"));
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#控股股东#") != -1) {
                      test.replaceTableCell(td,wordMap.get("#控股股东#"));
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#上市标准#") != -1) {
                      if (((CompanyOverviewVo)dataMap.get("companyInformation")).getIpoPlate().equals("上交所科创板")){
                          test.replaceTableCellHH(td,wordMap.get("#上市标准#"));
                      }
                  } else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#战略新兴行业#") != -1) {
                      if (((CompanyOverviewVo)dataMap.get("companyInformation")).getIpoPlate().equals("上交所科创板")){
                          test.replaceTableCell(td,wordMap.get("#战略新兴行业#"));
                      }
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#主营业务#") != -1) {
                      test.replaceTableCell(td,wordMap.get("#主营业务#"));
                      if (!((CompanyOverviewVo)dataMap.get("companyInformation")).getIpoPlate().equals("上交所科创板")){
                          test.deleteTableRow(table,table.getNumberOfRows()-2);
                          test.deleteTableRow(table,table.getNumberOfRows()-2);
                      }
                  }
//                  else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#辅导历时#") != -1) {
//                      test.replaceTableCell(td,wordMap.get("#辅导历时#"));
//                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#审核历时#") != -1) {
//                      test.replaceTableCell(td,wordMap.get("#审核历时#"));
//                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#发行历时#") != -1) {
//                      test.replaceTableCell(td,wordMap.get("#发行历时#"));
//                  }
                  else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#每股面值#") != -1) {
                      if(issueData!=null){
                          if (issueData.getParValue()!=null){
                              test.replaceTableCell(td,twoMarkThStr(issueData.getParValue()+"")+"元/股");
                          }else {
                              test.replaceTableCell(td,"- -");
                          }
                      }else {
                          test.deleteTableRow(table,table.getNumberOfRows()-1);
                          test.deleteTableRow(table,table.getNumberOfRows()-1);
                          test.deleteTableRow(table,table.getNumberOfRows()-1);
                          test.deleteTableRow(table,table.getNumberOfRows()-1);
                          test.deleteTableRow(table,table.getNumberOfRows()-1);
                          test.deleteTableRow(table,table.getNumberOfRows()-1);
                          test.deleteTableRow(table,table.getNumberOfRows()-1);
                          test.deleteTableRow(table,table.getNumberOfRows()-1);
                          test.deleteTableRow(table,table.getNumberOfRows()-1);
                          break lableA;
                      }
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#发行价格#") != -1) {
                      if(issueData!=null){
                          if (issueData.getIssuePrice()!=null){
                              test.replaceTableCell(td,twoMarkThStr(issueData.getIssuePrice()+"")+"元/股");
                          }else {
                              test.replaceTableCell(td,"- -");
                          }
                      }
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#发行前总股本#") != -1) {
                      if(issueData!=null){
                          if (issueData.getPreIssueNum()!=null){
                              test.replaceTableCell(td,twoMarkThStr(issueData.getPreIssueNum()+"")+"万股");
                          }else {
                              test.replaceTableCell(td,"- -");
                          }
                      }
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#发行后总股本#") != -1) {
                      if(issueData!=null){
                          if (issueData.getNextIssueNum()!=null){
                              test.replaceTableCell(td,twoMarkThStr(issueData.getNextIssueNum()+"")+"万股");
                          }else {
                              test.replaceTableCell(td,"- -");
                          }
                      }
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#发行数量总计#") != -1) {
                      if(issueData!=null){
                          if (issueData.getShareIssued()!=null){
                              test.replaceTableCell(td,twoMarkThStr(issueData.getShareIssued()+"")+"万股");
                          }else {
                              test.replaceTableCell(td,"- -");
                          }
                      }
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#募集资金总额#") != -1) {
                      if(issueData!=null){
                          if (issueData.getSumFina()!=null){
                              test.replaceTableCell(td,twoMarkThStr(issueData.getSumFina()+"")+"万元");
                          }else {
                              test.replaceTableCell(td,"- -");

                          }
                      }
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#占发行后总股本的比例#") != -1) {
                      if(issueData!=null){
                          if (issueData.getIssuedRatio()!=null){
                              test.replaceTableCell(td,twoMarkStrBFH(issueData.getIssuedRatio()+""));
                          }else {
                              test.replaceTableCell(td,"- -");
                          }
                      }
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#募集资金净额#") != -1) {
                      if(issueData!=null){
                          if (issueData.getNetSumFina()!=null){
                              test.replaceTableCell(td,twoMarkThStr(issueData.getNetSumFina()+"")+"万元");
                          }else {
                              test.replaceTableCell(td,"- -");
                          }
                      }
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#网上发行数量#") != -1) {
                      if(issueData!=null){
                          if (issueData.getShareIssueOn()!=null){
                              test.replaceTableCell(td,twoMarkThStr(issueData.getShareIssueOn()+"")+"万股");
                          }else {
                              test.replaceTableCell(td,"- -");
                          }
                      }
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#网下配售数量#") != -1) {
                      if(issueData!=null){
                          if (issueData.getShareIssued()!=null){
                              test.replaceTableCell(td,twoMarkThStr(issueData.getShareIssued()+"")+"万股");
                          }else {
                              test.replaceTableCell(td,"- -");
                          }
                      }
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#战略配售数量#") != -1) {
                      if(issueData!=null){
                          if (issueData.getSharesTrategicPlace()!=null){
                              test.replaceTableCell(td,twoMarkThStr(issueData.getSharesTrategicPlace()+"")+"万股");
                          }else {
                              test.replaceTableCell(td,"- -");
                          }
                      }
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#超额配售数量#") != -1) {
                      if(issueData!=null){
                          if (issueData.getShareOverPlace()!=null){
                              test.replaceTableCell(td,twoMarkThStr(issueData.getShareOverPlace()+"")+"万股");
                          }else {
                              test.replaceTableCell(td,"- -");
                          }
                      }
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#老股东公开发售股份#") != -1) {
                      if(issueData!=null){
                          if (issueData.getExseNumBse()!=null){
                              test.replaceTableCell(td,twoMarkThStr(issueData.getExseNumBse()+"")+"万股");
                          }else {
                              test.replaceTableCell(td,"- -");
                          }
                      }
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#发行后市盈率#") != -1) {
                      if(issueData!=null){
                          if (issueData.getPeIssueA()!=null){
                              test.replaceTableCell(td,twoMarkThStr(issueData.getPeIssueA()+"")+" 倍");
                          }else {
                              test.replaceTableCell(td,"- -");
                          }
                      }
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#发行前每股收益#") != -1) {
                      if(issueData!=null){
                          if (issueData.getEpsIssueB()!=null){
                              test.replaceTableCell(td,twoMarkThStr(issueData.getEpsIssueB()+"")+"元/股");
                          }else {
                              test.replaceTableCell(td,"- -");
                          }
                      }
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#发行后每股收益#") != -1) {
                      if(issueData!=null){
                          if (issueData.getEpsIssueA()!=null){
                              test.replaceTableCell(td,twoMarkThStr(issueData.getEpsIssueA()+"")+"元/股");
                          }else {
                              test.replaceTableCell(td,"- -");
                          }
                      }
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#发行方式#") != -1) {
                      if(issueData!=null){
                          test.replaceTableCell(td,isNull(issueData.getIssueMethod()+""));
                      }
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#承销方式#") != -1) {
                      if(issueData!=null){
                          test.replaceTableCell(td,isNull(issueData.getUwMethod()+""));
                      }
                  }
                  else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#主营业务表格#") != -1) {
                      if (j + 8 < cellList.size()) {
                          int no = rowCnt+2;
                          if (mainIncomeVo != null && mainIncomeVo.getMainIncomeInfoList() != null){
                              test.setCellNewContentTitleNotTempEight(table, 0, 1, mainIncomeVo.getOnePeriodForIncome(), i);
                              test.setCellNewContentTitleNotTempEight(table, 0, 2, mainIncomeVo.getThirdYearForIncome(), i);
                              test.setCellNewContentTitleNotTempEight(table, 0, 3, mainIncomeVo.getSecondYearForIncome(), i);
                              test.setCellNewContentTitleNotTempEight(table, 0, 4, mainIncomeVo.getFirstYearForIncome(), i);
                              for (int k = 0; k < mainIncomeVo.getMainIncomeInfoList().size(); k++) {
                                  if (no >= table.getNumberOfRows()) {
                                      test.insertTableRowAtIndex(table, no);
                                  }
                                  if ("合计".equals(mainIncomeVo.getMainIncomeInfoList().get(k).getBusinessName())){
                                      test.setCellNewContentBoldEight(table, no, 0, isNull(mainIncomeVo.getMainIncomeInfoList().get(k).getBusinessName()+""), i);
                                      test.setCellNewContentBoldEight(table, no, 1, isNullBigDouble(mainIncomeVo.getMainIncomeInfoList().get(k).getOnePeriodAmount()), i);
                                      test.setCellNewContentBoldEight(table, no, 2, isNullBFH(mainIncomeVo.getMainIncomeInfoList().get(k).getOnePeriodRatio()+""), i);
                                      test.setCellNewContentBoldEight(table, no, 3, isNullBigDouble(mainIncomeVo.getMainIncomeInfoList().get(k).getThirdYearAmount()), i);
                                      test.setCellNewContentBoldEight(table, no, 4, isNullBFH(mainIncomeVo.getMainIncomeInfoList().get(k).getThirdYearRatio()+""), i);
                                      test.setCellNewContentBoldEight(table, no, 5, isNullBigDouble(mainIncomeVo.getMainIncomeInfoList().get(k).getSecondYearAmount()), i);
                                      test.setCellNewContentBoldEight(table, no, 6, isNullBFH(mainIncomeVo.getMainIncomeInfoList().get(k).getSecondYearRatio()+""), i);
                                      test.setCellNewContentBoldEight(table, no, 7, isNullBig00(mainIncomeVo.getMainIncomeInfoList().get(k).getFirstYearAmount()), i);
                                      test.setCellNewContentBoldEight(table, no, 8, isNullBFH00(mainIncomeVo.getMainIncomeInfoList().get(k).getFirstYearRatio()+""), i);
                                  }else {
                                      test.setCellNewContentBoldEight(table, no, 0, isNull(mainIncomeVo.getMainIncomeInfoList().get(k).getBusinessName()+""), i);
                                      test.setCellNewContentEight(table, no, 1, isNullBigDouble(mainIncomeVo.getMainIncomeInfoList().get(k).getOnePeriodAmount()), i);
                                      test.setCellNewContentEight(table, no, 2, isNullBFH(mainIncomeVo.getMainIncomeInfoList().get(k).getOnePeriodRatio()+""), i);
                                      test.setCellNewContentEight(table, no, 3, isNullBigDouble(mainIncomeVo.getMainIncomeInfoList().get(k).getThirdYearAmount()), i);
                                      test.setCellNewContentEight(table, no, 4, isNullBFH(mainIncomeVo.getMainIncomeInfoList().get(k).getThirdYearRatio()+""), i);
                                      test.setCellNewContentEight(table, no, 5, isNullBigDouble(mainIncomeVo.getMainIncomeInfoList().get(k).getSecondYearAmount()), i);
                                      test.setCellNewContentEight(table, no, 6, isNullBFH(mainIncomeVo.getMainIncomeInfoList().get(k).getSecondYearRatio()+""), i);
                                      test.setCellNewContentEight(table, no, 7, isNullBigDouble(mainIncomeVo.getMainIncomeInfoList().get(k).getFirstYearAmount()), i);
                                      test.setCellNewContentEight(table, no, 8, isNullBFH(mainIncomeVo.getMainIncomeInfoList().get(k).getFirstYearRatio()+""), i);
                                  }
                                 no++;
                              }
                              if (mainIncomeVo.getMainIncomeInfoList()==null||mainIncomeVo.getMainIncomeInfoList().size()==0){
                                  test.deleteTableRow(table,table.getNumberOfRows()-1);
                                  test.deleteTableRow(table,table.getNumberOfRows()-1);
                                  test.deleteTableRow(table,table.getNumberOfRows()-1);
                              } else if (mainIncomeVo.getMainIncomeInfoList().size()==1){
                                  test.deleteTableRow(table,table.getNumberOfRows()-1);
                              }
                              break lableA;
                          }else {
                              test.deleteTableRow(table,table.getNumberOfRows()-1);
                              test.deleteTableRow(table,table.getNumberOfRows()-1);
                              test.deleteTableRow(table,table.getNumberOfRows()-1);
                              test.deleteTableRow(table,table.getNumberOfRows()-1);
                          }
                      }
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#行业地位表格#") != -1) {
                      if (j + 2 < cellList.size()) {
                          int no = rowCnt+1;
                          if (industryStatusDtoList!=null&&industryStatusDtoList.size()>0){
                              for (int k = 0; k < industryStatusDtoList.size(); k++) {
                                  if (no >= table.getNumberOfRows()) {
                                      test.insertTableRowAtIndex(table, no);
                                  }
                                  test.setCellNewContentBold(table, no, 0, (k+1)+"", i);
                                  test.setCellNewContent(table, no, 1, isNull(industryStatusDtoList.get(k).getRankingRange()+"")+isNull(industryStatusDtoList.get(k).getRankingIndicator()+""), i);
                                  test.setCellNewContent(table, no, 2, isNull(industryStatusDtoList.get(k).getRanking()+""), i);
                                  no++;
                              }
                          }
                          if (industryStatusDtoList==null||industryStatusDtoList.size()==0){
                              test.deleteTableRow(table,table.getNumberOfRows()-1);
                              test.deleteTableRow(table,table.getNumberOfRows()-1);
                              test.deleteTableRow(table,table.getNumberOfRows()-1);
                          } else if (industryStatusDtoList.size()==1){
                              test.deleteTableRow(table,table.getNumberOfRows()-1);
                          }
                          break lableA;
                      }
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#主要竞争对手表格#") != -1) {
                      if (j + 2 < cellList.size()) {
                          int no = rowCnt+1;
                          for (int k = 0; k < competitorData.size(); k++) {
                              if (no >= table.getNumberOfRows()) {
                                  test.insertTableRowAtIndex(table, no);
                              }
                              test.setCellNewContentBold(table, no, 0, competitorData.get(k).getCompanyName()+"", i);
                              test.setCellNewContent(table, no, 1, competitorData.get(k).getCompanyCode()+"", i);
                              test.setCellNewContent(table, no, 2, competitorData.get(k).getSituation()+"", i);
                              no++;
                          }
                          if (competitorData==null||competitorData.size()==0){
                              test.deleteTableRow(table,table.getNumberOfRows()-1);
                              test.deleteTableRow(table,table.getNumberOfRows()-1);
                              test.deleteTableRow(table,table.getNumberOfRows()-1);
                          } else if (competitorData.size()==1){
                              test.deleteTableRow(table,table.getNumberOfRows()-1);
                          }
                          break lableA;
                      }
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#专利情况表格#") != -1) {
                      if (j + 4 < cellList.size()) {
                          if (ipoTechnology != null && ipoTechnology.getPatentData() != null &&ipoTechnology.getPatentData().size()>0){
                              test.setCellNewContent(table, 1, 1, isNullBigInt(ipoTechnology.getPatentData().get(0).getFm()), i);
                              test.setCellNewContent(table, 1, 2, isNullBigInt(ipoTechnology.getPatentData().get(0).getSy()), i);
                              test.setCellNewContent(table, 1, 3, isNullBigInt(ipoTechnology.getPatentData().get(0).getWg()), i);
                              test.setCellNewContent(table, 1, 4, isNullBigInt(ipoTechnology.getPatentData().get(0).getGw()), i);
                              test.setCellNewContent(table, 1, 5, isNullBigInt(ipoTechnology.getPatentData().get(0).getHj()), i);
                              test.setCellNewContent(table, 2, 1, isNullBFH00(ipoTechnology.getPatentData().get(1).getFm()+""), i);
                              test.setCellNewContent(table, 2, 2, isNullBFH00(ipoTechnology.getPatentData().get(1).getSy()+""), i);
                              test.setCellNewContent(table, 2, 3, isNullBFH00(ipoTechnology.getPatentData().get(1).getWg()+""), i);
                              test.setCellNewContent(table, 2, 4, isNullBFH00(ipoTechnology.getPatentData().get(1).getGw()+""), i);
                              test.setCellNewContent(table, 2, 5, isNullBFH00(ipoTechnology.getPatentData().get(1).getHj()+""), i);
                              break lableA;
                          }else {
                              test.deleteTableRow(table,table.getNumberOfRows()-1);
                              test.deleteTableRow(table,table.getNumberOfRows()-1);
                              test.deleteTableRow(table,table.getNumberOfRows()-1);
                          }
                      }
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#研发投入表格#") != -1) {
                      if (j + 4 < cellList.size()) {
                          int no = rowCnt+1;
                          if (ipoTechnology != null && ipoTechnology.getDevDate() != null && ipoTechnology.getDevData().size()>0){
                              test.setCellNewContentTitleNotTemp(table, 0, 1, ipoTechnology.getDevDate().getFirstYearDate(), i);
                              test.setCellNewContentTitleNotTemp(table, 0, 2, ipoTechnology.getDevDate().getSecondYearDate(), i);
                              test.setCellNewContentTitleNotTemp(table, 0, 3, ipoTechnology.getDevDate().getThirdYearDate(), i);
                              test.setCellNewContentTitleNotTemp(table, 0, 4, ipoTechnology.getDevDate().getForthYearDate(), i);
                              test.setCellNewContentTitleNotTemp(table, 0, 5, "合计", i);
                          }
                          if (ipoTechnology != null && ipoTechnology.getDevData() != null && ipoTechnology.getDevData().size()>0){
                              for (int k = 0; k < ipoTechnology.getDevData().size(); k++) {
                                  if ("研发投入占营业收入的比例".equals(ipoTechnology.getDevData().get(k).getLabelName())){
                                      test.setCellNewContentNotTemp(table, no, 1, isNullBigDoubleBFH(ipoTechnology.getDevData().get(k).getFirstYearValue()), i);
                                      test.setCellNewContentNotTemp(table, no, 2, isNullBigDoubleBFH(ipoTechnology.getDevData().get(k).getSecondYearValue()), i);
                                      test.setCellNewContentNotTemp(table, no, 3, isNullBigDoubleBFH(ipoTechnology.getDevData().get(k).getThirdYearValue()), i);
                                      test.setCellNewContentNotTemp(table, no, 4, isNullBigDoubleBFH(ipoTechnology.getDevData().get(k).getForthYearValue()), i);
                                      test.setCellNewContentNotTemp(table, no, 5, isNullBigDoubleBFH(ipoTechnology.getDevData().get(k).getAllNumValue()), i);
                                  }else {
                                      test.setCellNewContentNotTemp(table, no, 1, isNullBigDouble(ipoTechnology.getDevData().get(k).getFirstYearValue()), i);
                                      test.setCellNewContentNotTemp(table, no, 2, isNullBigDouble(ipoTechnology.getDevData().get(k).getSecondYearValue()), i);
                                      test.setCellNewContentNotTemp(table, no, 3, isNullBigDouble(ipoTechnology.getDevData().get(k).getThirdYearValue()), i);
                                      test.setCellNewContentNotTemp(table, no, 4, isNullBigDouble(ipoTechnology.getDevData().get(k).getForthYearValue()), i);
                                      test.setCellNewContentNotTemp(table, no, 5, isNullBigDouble(ipoTechnology.getDevData().get(k).getAllNumValue()), i);
                                  }
                                    no++;
                              }
                          }else {
                              test.deleteTableRow(table,table.getNumberOfRows()-1);
                              test.deleteTableRow(table,table.getNumberOfRows()-1);
                              test.deleteTableRow(table,table.getNumberOfRows()-1);
                              test.deleteTableRow(table,table.getNumberOfRows()-1);
                          }
                          break lableA;
                      }
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#研发人员表格#") != -1) {
                      if (j + 3 < cellList.size()) {
                          int no = rowCnt+1;
                          if (ipoTechnology != null && ipoTechnology.getCoreDate() != null && ipoTechnology.getCoreData().size()>0){
                              test.setCellNewContentTitleNotTemp(table, 0, 1, ipoTechnology.getCoreDate().getFirstYearDate(), i);
                              test.setCellNewContentTitleNotTemp(table, 0, 3, ipoTechnology.getCoreDate().getSecondYearDate(), i);
                              test.setCellNewContentTitleNotTemp(table, 4, 1, ipoTechnology.getCoreDate().getThirdYearDate(), i);
                              test.setCellNewContentTitleNotTemp(table, 4, 3, ipoTechnology.getCoreDate().getForthYearDate(), i);
                          }
                          if (ipoTechnology != null && ipoTechnology.getCoreData() != null && ipoTechnology.getCoreData().size()>0){
                              for (int k = 0; k < ipoTechnology.getCoreData().size(); k++) {
                                  test.setCellNewContentNotTemp(table, no, 1, isNullBigInt(ipoTechnology.getCoreData().get(k).getFirstYearValue()), i);
                                  test.setCellNewContentNotTemp(table, no, 2, twoMarkStrBFH(ipoTechnology.getCoreData().get(k).getFirstYearPro()+""), i);
                                  test.setCellNewContentNotTemp(table, no, 3, isNullBigInt(ipoTechnology.getCoreData().get(k).getSecondYearValue()), i);
                                  test.setCellNewContentNotTemp(table, no, 4, twoMarkStrBFH(ipoTechnology.getCoreData().get(k).getSecondYearPro()+""), i);
                                  test.setCellNewContentNotTemp(table, no+4, 1, isNullBigInt(ipoTechnology.getCoreData().get(k).getThirdYearValue()), i);
                                  test.setCellNewContentNotTemp(table, no+4, 2, twoMarkStrBFH(ipoTechnology.getCoreData().get(k).getThirdYearPro()+""), i);
                                  test.setCellNewContentNotTemp(table, no+4, 3, isNullBigInt(ipoTechnology.getCoreData().get(k).getForthYearValue()), i);
                                  test.setCellNewContentNotTemp(table, no+4, 4, twoMarkStrBFH(ipoTechnology.getCoreData().get(k).getForthYearPro()+""), i);
                                  no++;
                              }
                          }else {
                              test.deleteTableRow(table,table.getNumberOfRows()-1);
                              test.deleteTableRow(table,table.getNumberOfRows()-1);
                              test.deleteTableRow(table,table.getNumberOfRows()-1);
                              test.deleteTableRow(table,table.getNumberOfRows()-1);
                              test.deleteTableRow(table,table.getNumberOfRows()-1);
                              test.deleteTableRow(table,table.getNumberOfRows()-1);
                              test.deleteTableRow(table,table.getNumberOfRows()-1);
                              test.deleteTableRow(table,table.getNumberOfRows()-1);
                          }
                          break lableA;
                      }
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#财务总体情况表格#") != -1) {
                      if (j + 3 < cellList.size()) {
                          int no = rowCnt+1;
                          if (ipoFinance != null && ipoFinance.getIpoFinanceOverList() != null){
                              List<IpoItemDto> tb = ipoFinance.getIpoFinanceOverList();
                              test.setCellNewContentTitleNotTemp(table, 0, 1, ipoFinance.getDateList().getForthYearDate(), i);
                              test.setCellNewContentTitleNotTemp(table, 0, 2, ipoFinance.getDateList().getThirdYearDate(), i);
                              test.setCellNewContentTitleNotTemp(table, 0, 3, ipoFinance.getDateList().getSecondYearDate(), i);
                              test.setCellNewContentTitleNotTemp(table, 0, 4, ipoFinance.getDateList().getFirstYearDate(), i);
                              for (int k = 0; k < tb.size(); k++) {
                                  test.setCellNewContentNotTemp(table, no, 1, twoMarkThStr(tb.get(k).getForthYearValue()+""), i);
                                  test.setCellNewContentNotTemp(table, no, 2, twoMarkThStr(tb.get(k).getThirdYearValue()+""), i);
                                  test.setCellNewContentNotTemp(table, no, 3, twoMarkThStr(tb.get(k).getSecondYearValue()+""), i);
                                  test.setCellNewContentNotTemp(table, no, 4, twoMarkThStr(tb.get(k).getFirstYearValue()+""), i);
                                  no++;
                              }
                              break lableA;
                          }else {
                              for (int k = 0; k < 13; k++) {
                                  test.deleteTableRow(table,table.getNumberOfRows()-1);
                              }
                              break lableA;
                          }
                      }
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#资产负债情况表格#") != -1) {
                      if (j + 4 < cellList.size()) {
                          int no = rowCnt+1;
                          if (assetLiability != null && ipoFinance.getIpoFinanceOverList() != null) {
                              List<IpoItemDto> tb = new ArrayList<>();

                              if (assetLiability.getIpoAssetItemList().size()>0){
                                  List<IpoItemDto> list=new ArrayList<>();
                                  IpoItemDto dto=new IpoItemDto();
                                  dto.setItemName("资产类项目：");
                                  list.add(dto);
                                  list.addAll(assetLiability.getIpoAssetItemList());
                                  tb.addAll(list);
                              }
                              if (assetLiability.getIpoDebtItemList().size()>0){
                                  List<IpoItemDto> list=new ArrayList<>();
                                  IpoItemDto dto=new IpoItemDto();
                                  dto.setItemName("负债类项目：");
                                  list.add(dto);
                                  list.addAll(assetLiability.getIpoDebtItemList());
                                  tb.addAll(list);
                              }
                              if (assetLiability.getIpoEquityItemList().size()>0){
                                  List<IpoItemDto> list=new ArrayList<>();
                                  IpoItemDto dto=new IpoItemDto();
                                  dto.setItemName("权益类项目：");
                                  list.add(dto);
                                  list.addAll(assetLiability.getIpoEquityItemList());
                                  tb.addAll(list);
                              }
//                              tb.addAll(assetLiability.getIpoAssetItemList());
//                              tb.addAll(assetLiability.getIpoDebtItemList());
//                              tb.addAll(assetLiability.getIpoEquityItemList());
                              test.setCellNewContentTitleNotTemp(table, 0, 0,"项目", i);
                              test.setCellNewContentTitleNotTemp(table, 0, 1, assetLiability.getDateList().getForthYearDate(), i);
                              test.setCellNewContentTitleNotTemp(table, 0, 2, assetLiability.getDateList().getThirdYearDate(), i);
                              test.setCellNewContentTitleNotTemp(table, 0, 3, assetLiability.getDateList().getSecondYearDate(), i);
                              test.setCellNewContentTitleNotTemp(table, 0, 4, assetLiability.getDateList().getFirstYearDate(), i);
                              for (int z=0;z<tb.size();z++){
                                  if (no >= table.getNumberOfRows()) {
                                      test.insertTableRowAtIndex(table, no);
                                  }
                                  if (tb.get(z).getItemName().equals("资产类项目：")||tb.get(z).getItemName().equals("负债类项目：")||tb.get(z).getItemName().equals("权益类项目：")){
                                      test.setCellNewContentBold(table, no, 0, tb.get(z).getItemName() + "", i);
                                      test.setCellNewContent(table, no, 1, twoMarkTh(tb.get(z).getForthYearValue() + ""), i);
                                      test.setCellNewContent(table, no, 2, twoMarkTh(tb.get(z).getThirdYearValue() + ""), i);
                                      test.setCellNewContent(table, no, 3, twoMarkTh(tb.get(z).getSecondYearValue() + ""), i);
                                      test.setCellNewContent(table, no, 4, twoMarkTh(tb.get(z).getFirstYearValue() + ""), i);
                                  }else {
                                      test.setCellNewContentBold(table, no, 0, tb.get(z).getItemName() + "", i);
                                      test.setCellNewContent(table, no, 1, twoMarkThStr(tb.get(z).getForthYearValue() + ""), i);
                                      test.setCellNewContent(table, no, 2, twoMarkThStr(tb.get(z).getThirdYearValue() + ""), i);
                                      test.setCellNewContent(table, no, 3, twoMarkThStr(tb.get(z).getSecondYearValue() + ""), i);
                                      test.setCellNewContent(table, no, 4, twoMarkThStr(tb.get(z).getFirstYearValue() + ""), i);

                                  }
                                  no++;
                              }
                              if (tb==null||tb.size()==0){
                                  test.deleteTableRow(table,table.getNumberOfRows()-1);
                                  test.deleteTableRow(table,table.getNumberOfRows()-1);
                                  test.deleteTableRow(table,table.getNumberOfRows()-1);
                              } else if (tb.size()==1){
                                  test.deleteTableRow(table,table.getNumberOfRows()-1);
                              }
                              break lableA;
                          }else {
                              test.deleteTableRow(table,table.getNumberOfRows()-1);
                              test.deleteTableRow(table,table.getNumberOfRows()-1);
                              test.deleteTableRow(table,table.getNumberOfRows()-1);
                          }
                      }
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#财务指标表格#") != -1) {
                      if (j + 4 < cellList.size()) {
                          int no = rowCnt+1;
                          if (financialIndex != null && financialIndex.getIpoMainIndexList() != null){
                              List<IpoItemDto> tb = financialIndex.getIpoMainIndexList();
                              test.setCellNewContentTitleNotTemp(table, 0, 0, "项目", i);
                              test.setCellNewContentTitleNotTemp(table, 0, 1, financialIndex.getDateList().getForthYearDate(), i);
                              test.setCellNewContentTitleNotTemp(table, 0, 2, financialIndex.getDateList().getThirdYearDate(), i);
                              test.setCellNewContentTitleNotTemp(table, 0, 3, financialIndex.getDateList().getSecondYearDate(), i);
                              test.setCellNewContentTitleNotTemp(table, 0, 4, financialIndex.getDateList().getFirstYearDate(), i);
                              for (int k = 0; k < tb.size(); k++) {
                                  if (no >= table.getNumberOfRows()) {
                                      test.insertTableRowAtIndex(table, no);
                                  }
                                  test.setCellNewContentBold(table, no, 0, isNull(tb.get(k).getItemName()+""), i);
                                  test.setCellNewContent(table, no, 1, twoMarkStr(tb.get(k).getForthYearValue()+""), i);
                                  test.setCellNewContent(table, no, 2, twoMarkStr(tb.get(k).getThirdYearValue()+""), i);
                                  test.setCellNewContent(table, no, 3, twoMarkStr(tb.get(k).getSecondYearValue()+""), i);
                                  test.setCellNewContent(table, no, 4, twoMarkStr(tb.get(k).getFirstYearValue()+""), i);
                                  no++;
                              }
                              if (tb==null||tb.size()==0){
                                  test.deleteTableRow(table,table.getNumberOfRows()-1);
                                  test.deleteTableRow(table,table.getNumberOfRows()-1);
                                  test.deleteTableRow(table,table.getNumberOfRows()-1);
                              } else if (tb.size()==1){
                                  test.deleteTableRow(table,table.getNumberOfRows()-1);
                              }
                              break lableA;
                          }else {
                              test.deleteTableRow(table,table.getNumberOfRows()-1);
                              test.deleteTableRow(table,table.getNumberOfRows()-1);
                              test.deleteTableRow(table,table.getNumberOfRows()-1);
                          }

                      }
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#收入与利润情况表格#") != -1) {
                      if (j + 3 < cellList.size()) {
                          int no = rowCnt+1;
                          if (incomeProfit != null) {
                              List<IpoItemDto> tb = new ArrayList<>();
                              if (incomeProfit.getIpoProfitItemList()!=null&&incomeProfit.getIpoProfitItemList().size()>0){
                                  List<IpoItemDto> list=new ArrayList<>();
                                  IpoItemDto dto=new IpoItemDto();
                                  dto.setItemName("收入类项目：");
                                  list.add(dto);
                                  list.addAll(incomeProfit.getIpoProfitItemList());
                                  tb.addAll(list);
                              }
                              if (incomeProfit.getIpoCostItemList()!=null&&incomeProfit.getIpoCostItemList().size()>0){
                                  List<IpoItemDto> list=new ArrayList<>();
                                  IpoItemDto dto=new IpoItemDto();
                                  dto.setItemName("成本类项目：");
                                  list.add(dto);
                                  list.addAll(incomeProfit.getIpoCostItemList());
                                  tb.addAll(list);
                              }
                              if (incomeProfit.getIpoReturnOverList()!=null&&incomeProfit.getIpoReturnOverList().size()>0){
                                  List<IpoItemDto> list=new ArrayList<>();
                                  IpoItemDto dto=new IpoItemDto();
                                  dto.setItemName("利润类项目：");
                                  list.add(dto);
                                  list.addAll(incomeProfit.getIpoReturnOverList());
                                  tb.addAll(list);
                              }

//                              if (incomeProfit.getIpoProfitItemList()!=null){
//                                  tb.addAll(incomeProfit.getIpoProfitItemList());
//                              }
//                              if (incomeProfit.getIpoCostItemList()!=null){
//                                  tb.addAll(incomeProfit.getIpoCostItemList());
//                              }
//                              if (incomeProfit.getIpoReturnOverList()!=null){
//                                  tb.addAll(incomeProfit.getIpoReturnOverList());
//                              }
                              if (incomeProfit.getDateList()!=null){
                                  test.setCellNewContentTitleNotTemp(table, 0, 1, incomeProfit.getDateList().getForthYearDate(), i);
                                  test.setCellNewContentTitleNotTemp(table, 0, 2, incomeProfit.getDateList().getThirdYearDate(), i);
                                  test.setCellNewContentTitleNotTemp(table, 0, 3, incomeProfit.getDateList().getSecondYearDate(), i);
                                  test.setCellNewContentTitleNotTemp(table, 0, 4, incomeProfit.getDateList().getFirstYearDate(), i);
                              }
                             if (tb.size()>0) {
                                 for (int z = 0; z < tb.size(); z++) {
                                     if (no >= table.getNumberOfRows()) {
                                         test.insertTableRowAtIndex(table, no);
                                     }
                                     if ("收入类项目：".equals(tb.get(z).getItemName())||"成本类项目：".equals(tb.get(z).getItemName())||"利润类项目：".equals(tb.get(z).getItemName())){
                                         test.setCellNewContentBold(table, no, 0, tb.get(z).getItemName(), i);
                                         test.setCellNewContent(table, no, 1, twoMarkTh(tb.get(z).getForthYearValue() + ""), i);
                                         test.setCellNewContent(table, no, 2, twoMarkTh(tb.get(z).getThirdYearValue() + ""), i);
                                         test.setCellNewContent(table, no, 3, twoMarkTh(tb.get(z).getSecondYearValue() + ""), i);
                                         test.setCellNewContent(table, no, 4, twoMarkTh(tb.get(z).getFirstYearValue() + ""), i);
                                     }else {
                                         test.setCellNewContentBold(table, no, 0, tb.get(z).getItemName(), i);
                                         test.setCellNewContent(table, no, 1, twoMarkThStr(tb.get(z).getForthYearValue() + ""), i);
                                         test.setCellNewContent(table, no, 2, twoMarkThStr(tb.get(z).getThirdYearValue() + ""), i);
                                         test.setCellNewContent(table, no, 3, twoMarkThStr(tb.get(z).getSecondYearValue() + ""), i);
                                         test.setCellNewContent(table, no, 4, twoMarkThStr(tb.get(z).getFirstYearValue() + ""), i);
                                     }
                                     no++;
                                 }
                                 break lableA;
                             }
                              if (tb==null||tb.size()==0){
                                  test.deleteTableRow(table,table.getNumberOfRows()-1);
                                  test.deleteTableRow(table,table.getNumberOfRows()-1);
                                  test.deleteTableRow(table,table.getNumberOfRows()-1);
                              } else if (tb.size()==1){
                                  test.deleteTableRow(table,table.getNumberOfRows()-1);
                              }
                          }else {
                              test.deleteTableRow(table,table.getNumberOfRows()-1);
                              test.deleteTableRow(table,table.getNumberOfRows()-1);
                              test.deleteTableRow(table,table.getNumberOfRows()-1);
                          }
                      }
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#募集资金运行表格#") != -1) {
                      if (j + 5 < cellList.size()) {
                          int no = rowCnt+1;
                          for (int k = 0; k < ipoInvestItem.size(); k++) {
                              if (no >= table.getNumberOfRows()) {
                                  test.insertTableRowAtIndex(table, no);
                              }
                              if ("总计".equals(ipoInvestItem.get(k).getItemName())){
                                  test.setCellNewContentBold(table, no, 0, ipoInvestItem.get(k).getItemName(), i);
                                  test.setCellNewContentBold(table, no, 1, isNull(ipoInvestItem.get(k).getItemTypeStr()), i);
                                  test.setCellNewContentBold(table, no, 2, isNullBigZero(ipoInvestItem.get(k).getInvestTotal()), i);
                                  test.setCellNewContentBold(table, no, 3, isNullBigZero(ipoInvestItem.get(k).getInvestPlan()), i);
                                  test.setCellNewContentBold(table, no, 4, isNullBigZeroBFH(ipoInvestItem.get(k).getInvestRateStr()), i);
                                  test.setCellNewContentBold(table, no, 5, isNullBigZero(ipoInvestItem.get(k).getInvestPre()), i);
                              }else {
                                  test.setCellNewContentBold(table, no, 0, ipoInvestItem.get(k).getItemName(), i);
                                  test.setCellNewContent(table, no, 1,isNull(ipoInvestItem.get(k).getItemTypeStr()), i);
                                  test.setCellNewContent(table, no, 2, isNullBigZero(ipoInvestItem.get(k).getInvestTotal()), i);
                                  test.setCellNewContent(table, no, 3, isNullBigZero(ipoInvestItem.get(k).getInvestPlan()), i);
                                  test.setCellNewContent(table, no, 4,isNullBigZeroBFH(ipoInvestItem.get(k).getInvestRateStr()) , i);
                                  test.setCellNewContent(table, no, 5, isNullBigZero(ipoInvestItem.get(k).getInvestPre()), i);
                              }

                              no++;
                          }
                          if (ipoInvestItem==null||ipoInvestItem.size()==0){
                              test.deleteTableRow(table,table.getNumberOfRows()-1);
                              test.deleteTableRow(table,table.getNumberOfRows()-1);
                              test.deleteTableRow(table,table.getNumberOfRows()-1);
                          } else if (ipoInvestItem.size()==1){
                              test.deleteTableRow(table,table.getNumberOfRows()-1);
                          }
                          break lableA;
                      }
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#发行数据表格#") != -1) {
                      if (j + 3 < cellList.size()) {
                          int no = rowCnt+1;
                          for (int k = 0; k < ipoInvestItem.size(); k++) {
                              if (no >= table.getNumberOfRows()) {
                                  test.insertTableRowAtIndex(table, no);
                              }
                              test.setCellNewContent(table, no, 0, ipoInvestItem.get(k).getItemName(), i);
                              if (StringUtils.isNotEmpty(ipoInvestItem.get(k).getItemTypeStr())){
                                  test.setCellNewContent(table, no, 1,ipoInvestItem.get(k).getItemTypeStr(), i);
                              }else {
                                  test.setCellNewContent(table, no, 1,"--", i);
                              }
                              test.setCellNewContent(table, no, 2, ipoInvestItem.get(k).getInvestTotal()+"", i);
                              test.setCellNewContent(table, no, 3, ipoInvestItem.get(k).getInvestPlan()+"", i);
                              test.setCellNewContent(table, no, 4, ipoInvestItem.get(k).getInvestRateStr()+"", i);
                              test.setCellNewContent(table, no, 5, ipoInvestItem.get(k).getInvestPre()+"", i);
                              no++;
                          }
                          break lableA;
                      }
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#发行费用表格#") != -1) {
                      if (j + 3 < cellList.size()) {
                          int no = rowCnt+1;
                          for (int k = 0; k < issueFeeList.size(); k++) {
                              if (no >=table.getNumberOfRows()) {
                                  test.insertTableRowAtIndex(table, no);
                              }
                              if ("合计".equals(issueFeeList.get(k).getFeeType())){
                                  test.setCellNewContentBold(table, no, 0, no+"", i);
                                  test.setCellNewContentBold(table, no, 1, issueFeeList.get(k).getFeeType(), i);
                                  test.setCellNewContentBold(table, no, 2, isNullBigDouble(issueFeeList.get(k).getFeeAmount()), i);
                                  test.setCellNewContentBold(table, no, 3, twoMarkStrBFH(issueFeeList.get(k).getFeeRatio()+""), i);
                              }else {
                                  test.setCellNewContentBold(table, no, 0, no+"", i);
                                  test.setCellNewContent(table, no, 1, issueFeeList.get(k).getFeeType(), i);
                                  test.setCellNewContent(table, no, 2, isNullBigDouble(issueFeeList.get(k).getFeeAmount()), i);
                                  test.setCellNewContent(table, no, 3, twoMarkStrBFH(issueFeeList.get(k).getFeeRatio()+""), i);
                              }

                              no++;
                          }
                          if (issueFeeList==null||issueFeeList.size()==0){
                              test.deleteTableRow(table,table.getNumberOfRows()-1);
                              test.deleteTableRow(table,table.getNumberOfRows()-1);
                              test.deleteTableRow(table,table.getNumberOfRows()-1);
                          } else if (issueFeeList.size()==1){
                              test.deleteTableRow(table,table.getNumberOfRows()-1);
                          }
                          break lableA;
                      }
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#战略配售表格#") != -1) {
                      if (j + 6 < cellList.size()) {
                          int no = rowCnt+1;
                          if (strategicPlacementMain != null && strategicPlacementMain .getSubs() != null){
                              List<StrategicPlacementSubDto> tb = strategicPlacementMain.getSubs();
                              for (int k = 0; k < tb.size(); k++) {
                                  if (no >= table.getNumberOfRows()) {
                                      test.insertTableRowAtIndex(table, no);
                                  }
                                  test.setCellNewContentBold(table, no, 0, no+"", i);
                                  test.setCellNewContent(table, no, 1, tb.get(k).getStrategicInvestorName(), i);
                                  test.setCellNewContent(table, no, 2, tb.get(k).getShareholderType(), i);
                                  test.setCellNewContent(table, no, 3, twoMarkStr(tb.get(k).getAllottedNumberTenThousand()+""), i);
                                  test.setCellNewContent(table, no, 4, twoMarkStrBFH(tb.get(k).getRadio()+""), i);
                                  test.setCellNewContent(table, no, 5, twoMarkStr(tb.get(k).getAllottedAmountTenThousand()+""), i);
                                  test.setCellNewContent(table, no, 6, isNull(tb.get(k).getSalesRestrictionPeriodMonth()+""), i);
                                  no++;
                              }
                              if (tb==null||tb.size()==0){
                                  test.deleteTableRow(table,table.getNumberOfRows()-1);
                                  test.deleteTableRow(table,table.getNumberOfRows()-1);
                                  test.deleteTableRow(table,table.getNumberOfRows()-1);
                              } else if (tb.size()==1){
                                  test.deleteTableRow(table,table.getNumberOfRows()-1);
                              }
                              break lableA;
                          }else {
                              test.deleteTableRow(table,table.getNumberOfRows()-1);
                              test.deleteTableRow(table,table.getNumberOfRows()-1);
                              test.deleteTableRow(table,table.getNumberOfRows()-1);
                              break lableA;
                          }
                      }
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#ipo进程表格#") != -1) {
                      if (j + 5 < cellList.size()) {
                          int no = rowCnt+2;
                          if (listTreeTypeProgress != null){
                              for (int k = 0; k < listTreeTypeProgress.size(); k++) {
                                  if (no >= table.getNumberOfRows()-1) {
                                      test.insertTableRowAtIndex(table, no);
                                  }
                                  test.setCellNewContent(table, no, 0, listTreeTypeProgress.get(k).get("cell0").toString(), i);
                                  test.setCellNewContent(table, no, 1, listTreeTypeProgress.get(k).get("cell1").toString(), i);
                                  test.setCellNewContent(table, no, 2, listTreeTypeProgress.get(k).get("cell2").toString(), i);
                                  test.setCellNewContent(table, no, 3, listTreeTypeProgress.get(k).get("cell3").toString(), i);
                                  test.setCellNewContent(table, no, 4, listTreeTypeProgress.get(k).get("cell4").toString(), i);
                                  test.setCellNewContent(table, no, 5, listTreeTypeProgress.get(k).get("cell5").toString(), i);
                                  no++;
                              }
                              if (listTreeTypeProgress==null||listTreeTypeProgress.size()==0){
                                  test.deleteTableRow(table,table.getNumberOfRows()-2);
                                  test.deleteTableRow(table,table.getNumberOfRows()-2);
                              } else if (listTreeTypeProgress.size()==1){
                                  test.deleteTableRow(table,table.getNumberOfRows()-2);
                              }
                              test.setCellNewContentBold(table, table.getNumberOfRows()-1, 0, "共计历时" + isNullEm(wordMap.get("#辅导历时#")) + "天", i);
                              test.setCellNewContentBold(table, table.getNumberOfRows()-1, 1, "共计历时" + isNullEm(wordMap.get("#审核历时#")) + "天", i);
                              test.setCellNewContentBold(table, table.getNumberOfRows()-1, 2, "共计历时" + isNullEm(wordMap.get("#发行历时#")) + "天", i);
                              break lableA;
                          }
                      }
                  }
              }
          }
      }

        //        多个可以放到list中    测试代码
        List<Integer> lists = Lists.newArrayList();
        Iterator<XWPFParagraph> paragraphsIterator = xdoc.getParagraphsIterator();
        while (paragraphsIterator.hasNext()) {
            XWPFParagraph next = paragraphsIterator.next();
            if (next.getText().indexOf("彻底删除") != -1) {
                int posOfParagraph = xdoc.getPosOfParagraph(next);
                lists.add(posOfParagraph);
            }
        }
        //删除多余
        for (int i = 0; i < lists.size(); i++) {
            Integer posOfParagraph = lists.get(i);
            if (i == 0){
                xdoc.removeBodyElement(posOfParagraph);
            }else{
                xdoc.removeBodyElement(posOfParagraph -i);
            }
        }

        List<Integer> titlLlists = Lists.newArrayList();
        Iterator<XWPFParagraph> paragraphsIteratorTitle = xdoc.getParagraphsIterator();
        while (paragraphsIteratorTitle.hasNext()) {
            XWPFParagraph next = paragraphsIteratorTitle.next();
            if (next.getText().indexOf("2、登陆其他资本市场") != -1) {
                if (otherList==null||otherList.size()<1){
                    int posOfParagraph = xdoc.getPosOfParagraph(next);
                    titlLlists.add(posOfParagraph);
                }
            }
            if (next.getText().indexOf("3、拆分上市情况") != -1) {
                if (splitList==null||splitList.size()<1){
                    int posOfParagraph = xdoc.getPosOfParagraph(next);
                    titlLlists.add(posOfParagraph);
                }
            }
            if (next.getText().indexOf("4、最近一次估值情况") != -1) {
                if ((List<IpoValuationDto>)dataMap.get("valuationList") == null || ((List<IpoValuationDto>) dataMap.get("valuationList")).size()<1){
                    int posOfParagraph = xdoc.getPosOfParagraph(next);
                    titlLlists.add(posOfParagraph);
                }else if(StringUtils.isEmpty(((List<IpoValuationDto>)dataMap.get("valuationList")).get(0).getValuationDate())
                &&(((List<IpoValuationDto>)dataMap.get("valuationList")).get(0).getValuationPrice())==null
                &&(((List<IpoValuationDto>)dataMap.get("valuationList")).get(0).getValuationEquity())==null
                &&(((List<IpoValuationDto>)dataMap.get("valuationList")).get(0).getValuationValue())==null
                &&StringUtils.isEmpty(((List<IpoValuationDto>)dataMap.get("valuationList")).get(0).getValuationMemo())) {
                    int posOfParagraph = xdoc.getPosOfParagraph(next);
                    titlLlists.add(posOfParagraph);
                }
            }
            if (next.getText().indexOf("5、股权结构") != -1) {
                if (imgUrl==""&&((CompanyOverviewVo)dataMap.get("companyInformation")).getStructureLabel()==null){
                    int posOfParagraph = xdoc.getPosOfParagraph(next);
                    titlLlists.add(posOfParagraph);
                }
            }
            if (next.getText().indexOf("三、公司生产经营情况") != -1) {
                if ((mainIncomeVo==null ||mainIncomeVo.getMainIncomeInfoList() == null)&&
                        (supplierMainList==null || supplierMainList.size()<1)&&
                        (customerMainList==null || customerMainList.size()<1)){
                    int posOfParagraph = xdoc.getPosOfParagraph(next);
                    titlLlists.add(posOfParagraph);
                }
            }
            if (next.getText().indexOf("1、主营业务收入构成") != -1) {
                if (mainIncomeVo==null ||mainIncomeVo.getMainIncomeInfoList() == null){
                    int posOfParagraph = xdoc.getPosOfParagraph(next);
                    titlLlists.add(posOfParagraph);
                }
            }
            if (next.getText().indexOf("2、报告期主要供应商情况") != -1) {
                if (supplierMainList==null || supplierMainList.size()==0){
                    int posOfParagraph = xdoc.getPosOfParagraph(next);
                    titlLlists.add(posOfParagraph);
                }else{
                    for (int y=0;y<supplierMainList.size();y++){
                        if (supplierMainList.get(y).getSupplierCustomerInfoList().size()<=1){
                            int posOfParagraph = xdoc.getPosOfParagraph(next);
                            titlLlists.add(posOfParagraph);
                            break;
                        }
                    }
                }

            }
            if (next.getText().indexOf("3、报告期主要客户情况") != -1) {
                if (customerMainList==null || customerMainList.size()<1){
                    int posOfParagraph = xdoc.getPosOfParagraph(next);
                    titlLlists.add(posOfParagraph);
                }else {
                    for (int y=0;y<customerMainList.size();y++){
                        if (customerMainList.get(y).getSupplierCustomerInfoList().size()<=1){
                            int posOfParagraph = xdoc.getPosOfParagraph(next);
                            titlLlists.add(posOfParagraph);
                            break;
                        }
                    }
                }
            }
            if (next.getText().indexOf("四、行业与技术") != -1) {
                if ((industryStatusDtoList==null || industryStatusDtoList.size()==0)
                &&(competitorData==null || competitorData.size()==0)
                &&(industryCompareList==null || industryCompareList.size()==0)
                &&(ipoTechnology == null ||ipoTechnology.getPatentData() == null || ipoTechnology.getPatentData().size()==0)
                &&(ipoTechnology == null || ipoTechnology.getDevData() == null||ipoTechnology.getDevData().size()==0)
                &&(ipoTechnology == null || ipoTechnology.getCoreData() == null||ipoTechnology.getCoreData().size()==0)){
                    int posOfParagraph = xdoc.getPosOfParagraph(next);
                    titlLlists.add(posOfParagraph);
                }
            }
            if (next.getText().indexOf("1、行业地位") != -1) {
                if (industryStatusDtoList==null || industryStatusDtoList.size()==0){
                    int posOfParagraph = xdoc.getPosOfParagraph(next);
                    titlLlists.add(posOfParagraph);
                }
            }
            if (next.getText().indexOf("2、主要竞争对手简介") != -1) {
                if (competitorData==null || competitorData.size()==0){
                    int posOfParagraph = xdoc.getPosOfParagraph(next);
                    titlLlists.add(posOfParagraph);
                }
            }
            if (next.getText().indexOf("3、行业毛利率对比") != -1) {
                if (industryCompareList==null || industryCompareList.size()==0){
                    int posOfParagraph = xdoc.getPosOfParagraph(next);
                    titlLlists.add(posOfParagraph);
                }
            }
            if (next.getText().indexOf("4、专利情况") != -1) {
                if (ipoTechnology == null ||ipoTechnology.getPatentData() == null || ipoTechnology.getPatentData().size()==0){
                    int posOfParagraph = xdoc.getPosOfParagraph(next);
                    titlLlists.add(posOfParagraph);
                }
            }
            if (next.getText().indexOf("5、研发投入") != -1) {
                if (ipoTechnology == null || ipoTechnology.getDevData() == null||ipoTechnology.getDevData().size()==0){
                    int posOfParagraph = xdoc.getPosOfParagraph(next);
                    titlLlists.add(posOfParagraph);
                }
            }
            if (next.getText().indexOf("6、核心技术及研发技术人员") != -1) {
                if (ipoTechnology == null || ipoTechnology.getCoreData() == null||ipoTechnology.getCoreData().size()==0){
                    int posOfParagraph = xdoc.getPosOfParagraph(next);
                    titlLlists.add(posOfParagraph);
                }
            }
            if (next.getText().indexOf("五、财务信息") != -1) {
                if ((ipoFinance == null|| ipoFinance.getIpoFinanceOverList() == null)
                &&(assetLiability == null || ipoFinance.getIpoFinanceOverList() == null || ipoFinance.getIpoFinanceOverList().size()==0)
                &&(incomeProfit==null||incomeProfit.getIpoProfitItemList()==null ||incomeProfit.getIpoCostItemList()==null||incomeProfit.getIpoReturnOverList()==null)
                && (financialIndex == null ||financialIndex.getIpoMainIndexList() == null)){
                    int posOfParagraph = xdoc.getPosOfParagraph(next);
                    titlLlists.add(posOfParagraph);
                }
            }
            if (next.getText().indexOf("1、财务总体情况") != -1) {
                if (ipoFinance == null|| ipoFinance.getIpoFinanceOverList() == null){
                    int posOfParagraph = xdoc.getPosOfParagraph(next);
                    titlLlists.add(posOfParagraph);
                }
            }
            if (next.getText().indexOf("2、资产与负债情况") != -1) {
                if (assetLiability == null || ipoFinance.getIpoFinanceOverList() == null || ipoFinance.getIpoFinanceOverList().size()==0){
                    int posOfParagraph = xdoc.getPosOfParagraph(next);
                    titlLlists.add(posOfParagraph);
                }
            }
            if (next.getText().indexOf("3、收入与利润情况") != -1) {
                if (incomeProfit==null||incomeProfit.getIpoProfitItemList()==null ||incomeProfit.getIpoCostItemList()==null||incomeProfit.getIpoReturnOverList()==null){
                    int posOfParagraph = xdoc.getPosOfParagraph(next);
                    titlLlists.add(posOfParagraph);
                }
            }
            if (next.getText().indexOf("4、主要财务指标") != -1) {
                if (financialIndex == null ||financialIndex.getIpoMainIndexList() == null){
                    int posOfParagraph = xdoc.getPosOfParagraph(next);
                    titlLlists.add(posOfParagraph);
                }
            }
            if (next.getText().indexOf("六、募集资金运用") != -1) {
                if (ipoInvestItem == null || ipoInvestItem.size()==0){
                    int posOfParagraph = xdoc.getPosOfParagraph(next);
                    titlLlists.add(posOfParagraph);
                }
            }
            if (next.getText().indexOf("九、发行概况") != -1) {
                if ( issueData==null&&(issueFeeList==null || issueFeeList.size()==0)&&(strategicPlacementMain==null || strategicPlacementMain.getSubs()==null || strategicPlacementMain.getSubs().size()==0)){
                    int posOfParagraph = xdoc.getPosOfParagraph(next);
                    titlLlists.add(posOfParagraph);
                }
            }
            if (next.getText().indexOf("1、发行数据") != -1) {
                if ( issueData==null){
                    int posOfParagraph = xdoc.getPosOfParagraph(next);
                    titlLlists.add(posOfParagraph);
                }
            }
            if (next.getText().indexOf("2、发行费用") != -1) {
                if (issueFeeList==null || issueFeeList.size()==0){
                    int posOfParagraph = xdoc.getPosOfParagraph(next);
                    titlLlists.add(posOfParagraph);
                }
            }
            if (next.getText().indexOf("3、战略配售情况") != -1) {
                if (strategicPlacementMain==null || strategicPlacementMain.getSubs()==null || strategicPlacementMain.getSubs().size()==0){
                    int posOfParagraph = xdoc.getPosOfParagraph(next);
                    titlLlists.add(posOfParagraph);
                }
            }
            if (next.getText().indexOf("七、反馈意见") != -1) {
                if (ipoFeedbackList==null||ipoFeedbackList.size()==0){
                    int posOfParagraph = xdoc.getPosOfParagraph(next);
                    titlLlists.add(posOfParagraph);
                }
            }
            if (next.getText().indexOf("七、问询与回复") != -1) {
                if (ipoFeedbackList==null||ipoFeedbackList.size()==0){
                    int posOfParagraph = xdoc.getPosOfParagraph(next);
                    titlLlists.add(posOfParagraph);
                }
            }
            if (next.getText().indexOf("八、审核结果及注册") != -1) {
                if (baseList==null||baseList.size()==0){
                    int posOfParagraph = xdoc.getPosOfParagraph(next);
                    titlLlists.add(posOfParagraph);
                }
            }
            if (next.getText().indexOf("十、中介机构") != -1) {
                if ((mainList==null || mainList.size()==0)&&(moreList==null||moreList.size()==0)){
                    int posOfParagraph = xdoc.getPosOfParagraph(next);
                    titlLlists.add(posOfParagraph);
                }
            }
        }
        //删除多余标题
        for (int i = 0; i < titlLlists.size(); i++) {
            Integer posOfParagraph = titlLlists.get(i);
            if (i == 0){
                xdoc.removeBodyElement(posOfParagraph);
            }else{
                xdoc.removeBodyElement(posOfParagraph -i);
            }
        }



//     更新标题  测试代码
        updateTitle(xdoc);



        //xdoc.createTOC();
        //createTOC(xdoc);
      ByteArrayOutputStream os = new ByteArrayOutputStream();

      xdoc.write(os);
      os.close();
      exportMap.put("inputStream",new ByteArrayInputStream(os.toByteArray()));
      exportMap.put("companyName",wordMap.get("#公司名字#"));
      return exportMap;
    }

    /*public static void createTOC(XWPFDocument doc) {
        CTSdtBlock block = doc.getDocument().getBody().addNewSdt();
        TOC toc = new TOC(block);

        *//*当前位置调用添加正文的方法，需要传参XWPFDocumen对象*//*
        List<XWPFParagraph> paragraphs = doc.getParagraphs();
        for (XWPFParagraph par : paragraphs) {
            String parStyle = par.getStyle();
            if (parStyle != null) {

                //获取书签，书签的对应关系很重要，关系到目录能否正常跳转
                List<CTBookmark> bookmarkList = par.getCTP().getBookmarkStartList();
                try {
                    int level = Integer.parseInt(parStyle);

                    //添加标题
                    toc.addRow(level, par.getText(), 1, "112723803");
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }
    }*/


    public void replaceNeedBreak(XWPFParagraph para, String t, String key) {
        XWPFRun run = null;
        List<XWPFRun> beforeRunList=new ArrayList<XWPFRun>();
        List<XWPFRun> afterRunList=new ArrayList<XWPFRun>();
        List<String> beforeTextList=new ArrayList<String>();
        List<String> afterTextList=new ArrayList<String>();
        List<String> list = Splitter.on("\n").splitToList(t);
        int flag=1;
        XWPFRun tmpR = null;
        if (para.getRuns().size() > 0) {
            for (int ri = para.getRuns().size()-1; ri >=0; ri--) {
                if (key.equals(para.getRuns().get(ri).text()) || (StringUtils.isNotEmpty(para.getRuns().get(ri).text()) && para.getRuns().get(ri).text().indexOf(key) != -1)) {
                    flag=0;
                    continue;
                }
                if (flag == 0) {
                    beforeRunList.add(para.getRuns().get(ri));
                    beforeTextList.add(para.getRuns().get(ri).text());
                }else{
                    afterRunList.add(para.getRuns().get(ri));
                    afterTextList.add(para.getRuns().get(ri).text());
                }
                para.removeRun(ri);
            }
            tmpR=para.getRuns().get(0);
        }
        int rc = 1;
        for (int i = 0; i < list.size(); i++) {
            String text = list.get(i);
            if (i > 0) {
                run.addCarriageReturn();
            }
            List<String> tList = Splitter.on("\t").splitToList(text);
            int size = tList.size();
            for (int j = 0; j < size; j++) {
                String s = tList.get(j);
                if (j > 0) {
                    run.addTab();
                }
                run = para.insertNewRun(rc++);
                if (!StringUtils.isEmpty(s)) {
                    if (s.startsWith(" ")) {
                        s = s.trim();
                    }
                }
                //20170815 需求2465 weishisheng end
                run.addTab();
                run.setText(s);
                run.setBold(tmpR.isBold());
                run.setItalic(tmpR.isItalic());
                run.setUnderline(tmpR.getUnderline());
//				run.setColor(tmpR.getColor());
                run.setTextPosition(tmpR.getTextPosition());
                if (tmpR.getFontSize() != -1) {
                    run.setFontSize(tmpR.getFontSize());
                }
                if (tmpR.getFontFamily() != null) {
                    run.setFontFamily(tmpR.getFontFamily());
                }
            }
        }
        para.removeRun(0);
        rc=0;
        tmpR=para.getRuns().get(0);
        if(beforeRunList.size()!=0){
            for(int ri = beforeRunList.size()-1; ri >=0; ri--){
                run=para.insertNewRun(0);
                run.setText(beforeTextList.get(ri));
                run.setBold(tmpR.isBold());
                run.setItalic(tmpR.isItalic());
                run.setUnderline(tmpR.getUnderline());
                run.setTextPosition(tmpR.getTextPosition());
                if (tmpR.getFontSize() != -1) {
                    run.setFontSize(tmpR.getFontSize());
                }
                if (tmpR.getFontFamily() != null) {
                    run.setFontFamily(tmpR.getFontFamily());
                }
            }
        }
        if(afterRunList.size()!=0){
            rc=para.getRuns().size();
            for(int ri=0;ri<afterRunList.size();ri++){
                run=para.insertNewRun(rc++);
                run.setText(afterTextList.get(ri));
                run.setBold(tmpR.isBold());
                run.setItalic(tmpR.isItalic());
                run.setUnderline(tmpR.getUnderline());
                run.setTextPosition(tmpR.getTextPosition());
                if (tmpR.getFontSize() != -1) {
                    run.setFontSize(tmpR.getFontSize());
                }
                if (tmpR.getFontFamily() != null) {
                    run.setFontFamily(tmpR.getFontFamily());
                }
            }
        }
    }

    public static byte[] getImageData(String strUrl) {
        InputStream inStream = null;
        try {
            // new一个URL对象
            URL url = new URL(strUrl);
            // 打开链接
            HttpURLConnection conn = (HttpURLConnection)
                    url.openConnection();
            // 设置请求方式为"GET"
            conn.setRequestMethod("GET");
            // 超时响应时间为5秒
            conn.setConnectTimeout(10 * 1000);
            // 通过输入流获取图片数据
            inStream = conn.getInputStream();
            byte[] data = readInputStream(inStream);
            return data;
        } catch (Exception e) {
            return null;
        } finally {
            if (inStream != null) {
                try {
                    inStream.close();
                } catch (Exception e2) {
                    System.out.println("关闭流失败");
                }
            }
        }
    }

    public static byte[] readInputStream(InputStream inStream) throws
            Exception {
        ByteArrayOutputStream outStream = new
                ByteArrayOutputStream();
        // 创建一个Buffer字符串
        byte[] buffer = new byte[1024];
        // 每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len = 0;
        // 使用一个输入流从buffer里把数据读取出来
        while ((len = inStream.read(buffer)) != -1) {
            // 用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);
        }
        // 关闭输入流
        inStream.close();
        // 把outStream里的数据写入内存
        return outStream.toByteArray();
    }


    public static void eachchild(XmlCursor cursor, int start, Map<String, String> map) {
        for (int i = 0; i < 10; i++) {
            if (cursor.toChild(i)) {
                if (cursor.getDomNode().getNodeName().equals(patternList.get(start))) {
                    if (start == patternList.size() - 1) {
                        String reString = cursor.getTextValue();
                        System.out.println(reString);
                        reString = reString.replaceAll("\\{\\{", "").replaceAll("}}", "");

                        for (String e : map.keySet()) {
                            if (reString.equals(e)) {
                                //    执行替换
                                reString = reString.replaceAll(e, map.get(e));
                            }
                        }
                        cursor.setTextValue(reString);
                    }
                    eachchild(cursor, start + 1, map);
                } else {
                    cursor.toParent();
                }
            }
        }

        cursor.toParent();
    }

    public String issueConditionName(String str){
        String content="";
        if (str!=null){
           String[] arr=str.split(",");
           for (int i=0;i<arr.length;i++){
               if ("101".equals(arr[i])){
                   content+="预计市值不低于人民币10亿元，最近两年净利润均为正且累计净利润不低于人民币5000万元，或者预计市值不低于人民币10亿元，最近一年净利润为正且营业收入不低于人民币1亿元\n";
               }else if ("102".equals(arr[i])){
                   content+="预计市值不低于人民币15亿元，最近一年营业收入不低于人民币2亿元，且最近三年累计研发投入占最近三年累计营业收入的比例不低于15%\n";
               }else if ("103".equals(arr[i])){
                   content+=" 预计市值不低于人民币20亿元，最近一年营业收入不低于人民币3亿元，且最近三年经营活动产生的现金流量净额累计不低于人民币1亿元\n";
               } else if ("104".equals(arr[i])){
                   content+=" 预计市值不低于人民币30亿元，且最近一年营业收入不低于人民币3亿元\n";
               }else if ("105".equals(arr[i])){
                   content+="预计市值不低于人民币40亿元，主要业务或产品需经国家有关部门批准，市场空间大，目前已取得阶段性成果。医药行业企业需至少有一项核心产品获准开展二期临床试验，其他符合科创板定位的企业需具备明显的技术优势并满足相应条件\n";
               }else if ("201".equals(arr[i])){
                   content+="符合相关规定的红筹企业，预计市值不低于人民币100亿元\n";
               }else if ("202".equals(arr[i])){
                   content+="符合相关规定的红筹企业，预计市值不低于人民币50亿元，且最近一年营业收入不低于人民币5亿元\n";
               }else if ("301".equals(arr[i])){
                   content+="发行人具有表决权差异安排的，预计市值不低于人民币100亿元\n";
               } else if ("302".equals(arr[i])){
                   content+="发行人具有表决权差异安排的，预计市值不低于人民币50亿元，且最近一年营业收入不低于人民币5亿元\n";
               }else if ("401".equals(arr[i])){
                   content+="市值不低于2亿元，最近两年净利润均不低于2亿元1500万元且加权平均净资产收益率平均不低于10%，或者最近一年净利润不低于2500万元且加权平均净资产收益率不低于10%\n";
               }else if ("402".equals(arr[i])){
                   content+=" 市值不低于4亿元，最近两年营业收入平均不低于1亿元且增长率不低于30%，最近一年经营活动产生的现金流量净额为正\n";
               }else if ("403".equals(arr[i])){
                   content+="市值不低于8亿元，最近一年营业收入不低于2亿元，最近两年研发投入合计占最近两年营业收入合计比例不低于8%\n";
               }else if ("404".equals(arr[i])){
                   content+="市值不低于15亿元，最近两年研发投入合计不低于5000万元\n";
               }
           }
        }else {
            content="--";
        }
            return content;
    }

    public String isNull(String str){
        if (str==null||"null".equals(str)||"".equals(str)){
            str="--";
        }
        return str;
    }

    public String isNullEm(String str){
        if (str==null||"null".equals(str)||"".equals(str)){
            str="- -";
        }
        return str;
    }

    public String isNullEmp(String str){
        if (str==null||"null".equals(str)||"".equals(str)){
            str="";
        }
        return str;
    }

    public String isNullBigInt(BigDecimal str){
        if (str==null||"null".equals(str)||"".equals(str)){
           return "--";
        }
        return str.intValue()+"";
    }

    public String isNullBigZero(BigDecimal str){
        if (str.compareTo(BigDecimal.ZERO) != 0){
            DecimalFormat df = new DecimalFormat(",###,##0.00");
            return df.format(Float.parseFloat(str.doubleValue()+""));
        }
        return "--";

    }
    public String isNullBigZeroBFH(String str){
        String o="--";
        if ("0.0000%".equals(str)){
            return o;
        }else if (StringUtils.isEmpty(str)){
            return o;
        }
        str=str.substring(0,str.length()-1);
        DecimalFormat df = new DecimalFormat("#0.00");
        return df.format(Float.parseFloat(str+""))+"%";
    }

    public String isNullBig(BigDecimal str){
        if (str==null||"null".equals(str)||"".equals(str)){
            return "0";
        }
        DecimalFormat df = new DecimalFormat("#0.00");
        return df.format(Float.parseFloat(str.doubleValue()+""));
    }

    public String isNullBigDouble(BigDecimal str){
        if (str==null||"null".equals(str)||"".equals(str)){
            return "--";
        }
        DecimalFormat df = new DecimalFormat(",###,##0.00");
        return df.format(Float.parseFloat(str.doubleValue()+""));
    }

    public String isNullBig00(BigDecimal str){
        if (str==null||"null".equals(str)||"".equals(str)||"0.00".equals(str.doubleValue())){
            return "--";
        }
        DecimalFormat df = new DecimalFormat(",###,##0.00");
        return df.format(Float.parseFloat(str.doubleValue()+""));
    }

    public String isNullBFH00(String str){
        if (str==null||"null".equals(str)||"".equals(str)||"0.00".equals(str)||"0.0000".equals(str)){
            return str="--";
        }
        DecimalFormat df = new DecimalFormat("#0.00");
        return df.format(Float.parseFloat(str))+"%";
    }

    public String isNullBigDoubleBFH(BigDecimal str){
        if (str==null||"null".equals(str)||"".equals(str)){
            return "--";
        }
        DecimalFormat df = new DecimalFormat("#0.00");
        return df.format(Float.parseFloat(str.doubleValue()+""))+"%";
    }


    public String isNullBFH(String str){
        if (str==null||"null".equals(str)||"".equals(str)){
            return str="--";
        }
        return str+"%";
    }

    public String isNullTime(Date str){
        if (str==null||"null".equals(str)||"".equals(str)){
            return "--";
        }
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        return sd.format(str);

    }
    public String twoMarkStr(String str){
        if(str == null || "null".equals(str)||"".equals(str)){
            return "--";
        }
        DecimalFormat df = new DecimalFormat("#0.00");
        return df.format(Float.parseFloat(str));
    }

    public String twoMarkTh(String str){
        if(str == null || "null".equals(str)||"".equals(str)){
            return  "";
        }
        DecimalFormat df = new DecimalFormat(",###,##0.00");
        return df.format(Float.parseFloat(str));
    }

    public String twoMarkThStr(String str){
        if(str == null || "null".equals(str)||"".equals(str)){
            return "--";
        }
        DecimalFormat df = new DecimalFormat(",###,##0.00");
        return df.format(Float.parseFloat(str));
    }
    public String twoMarkStrBFH(String str){
        if(str == null || "null".equals(str)||"".equals(str)){
            return "--";
        }
        DecimalFormat df = new DecimalFormat("#0.00");
        return df.format(Float.parseFloat(str))+"%";
    }

    public  String toChangeNum(String str){
        if(str == null || "null".equals(str)||"".equals(str)){
            return "0";
        }
        return str;
    }

    //跨列合并
    public  void mergeCellsHorizontal(XWPFTable table, int row, int fromCell, int toCell) {
        for (int cellIndex = fromCell; cellIndex <= toCell; cellIndex++) {
            XWPFTableCell cell = table.getRow(row).getCell(cellIndex);
            if ( cellIndex == fromCell ) {
                // The first merged cell is set with RESTART merge value
                cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.RESTART);
            } else {
                // Cells which join (merge) the first one, are set with CONTINUE
                cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);
            }
        }
    }

    public  void fillTable(XWPFTable table,int width,String str) {
        for (int rowIndex = 0; rowIndex < table.getNumberOfRows(); rowIndex++) {
            XWPFTableRow row = table.getRow(rowIndex);
            row.setHeight(132);
            for (int colIndex = 0; colIndex < row.getTableCells().size(); colIndex++) {
                XWPFTableCell cell = row.getCell(colIndex);
                if ("1".equals(str)){
                    if (colIndex==0){
                        setCellText(cell, 1500);
                    }else if (colIndex==1){
                        setCellText(cell, 3500);
                    }else {
                        setCellText(cell, width);
                    }
                }else {
                    setCellText(cell, width);
                }

            }
        }
    }

    public  void setCellText(XWPFTableCell cell, int width) {
        CTTc cttc = cell.getCTTc();
        CTTcPr cellPr = cttc.addNewTcPr();
        cellPr.addNewTcW().setW(BigInteger.valueOf(width));
        //cell.setColor(bgcolor);
        CTTcPr ctPr = cttc.addNewTcPr();
        CTShd ctshd = ctPr.addNewShd();
        ctPr.addNewVAlign().setVal(STVerticalJc.CENTER);
        cttc.getPList().get(0).addNewPPr().addNewJc().setVal(STJc.CENTER);
    }

    public  void setBorderStyle(XWPFTable table){
        CTTblBorders borders=table.getCTTbl().getTblPr().addNewTblBorders();
        CTBorder lBorder=borders.addNewLeft();
        lBorder.setVal(STBorder.Enum.forString("none"));
        lBorder.setSz(new BigInteger("1"));
        lBorder.setColor("3399FF");
        CTBorder rBorder=borders.addNewRight();
        rBorder.setVal(STBorder.Enum.forString("none"));
        rBorder.setSz(new BigInteger("1"));
        rBorder.setColor("F2B11F");

        CTBorder tBorder=borders.addNewTop();
        tBorder.setVal(STBorder.Enum.forString("single"));
        tBorder.setSz(new BigInteger("1"));
        tBorder.setColor("5B9BD5");

        CTBorder bBorder=borders.addNewBottom();
        bBorder.setVal(STBorder.Enum.forString("single"));
        bBorder.setSz(new BigInteger("1"));
        bBorder.setColor("5B9BD5");

        CTBorder vBorder=borders.addNewInsideV();
        vBorder.setVal(STBorder.Enum.forString("dotted"));
        vBorder.setSz(new BigInteger("1"));
        vBorder.setColor("5B9BD5");

        CTBorder hBorder=borders.addNewInsideH();
        hBorder.setVal(STBorder.Enum.forString("dotted"));
        hBorder.setSz(new BigInteger("1"));
        hBorder.setColor("5B9BD5");
    }

    public void setTitleBold(int p,XWPFTableRow row_0,String str,int fontSize,Boolean f){
        //标题加粗
        XWPFTableCell headCell = row_0.getCell(p);
        XWPFParagraph para = getCellFirstParagraph(headCell);
        XWPFRun headRun= para.createRun();
        headRun.setFontFamily("宋体");
        headRun.setFontSize(fontSize);
        headRun.setText(str);
        headRun.setBold(f);//是否粗体
        para.setSpacingBetween(1, LineSpacingRule.AUTO);
        //垂直居中
        para.setVerticalAlignment(TextAlignment.CENTER);
        //水平居中
        para.setAlignment(ParagraphAlignment.CENTER);
    }
    /**
     * @Description: 得到单元格第一个Paragraph
     */
    public XWPFParagraph getCellFirstParagraph(XWPFTableCell cell) {
        XWPFParagraph p;
        if (cell.getParagraphs() != null && cell.getParagraphs().size() > 0) {
            p = cell.getParagraphs().get(0);
        } else {
            p = cell.addParagraph();
        }
        return p;
    }


    private static void setBarData(XWPFChart chart, String chartTitle, String[] series, String[] categories, Double[] values1, Double[] values2) {
        XDDFChartAxis bottomAxis = chart.createCategoryAxis(AxisPosition.BOTTOM);
        //bottomAxis.setTitle(series[2]);
        XDDFValueAxis leftAxis = chart.createValueAxis(AxisPosition.LEFT);
//        leftAxis.setTitle(series[0]+","+series[1]);
        leftAxis.setCrosses(AxisCrosses.AUTO_ZERO);
        leftAxis.setMajorTickMark(AxisTickMark.OUT);
        leftAxis.setCrossBetween(AxisCrossBetween.BETWEEN);

        final int numOfPoints = categories.length;
        final String categoryDataRange = chart.formatRange(new CellRangeAddress(1, numOfPoints, COLUMN_LANGUAGES, COLUMN_LANGUAGES));
        final String valuesDataRange = chart.formatRange(new CellRangeAddress(1, numOfPoints, COLUMN_COUNTRIES, COLUMN_COUNTRIES));
        final XDDFDataSource<?> categoriesData = XDDFDataSourcesFactory.fromArray(categories, categoryDataRange, COLUMN_LANGUAGES);
        final XDDFNumericalDataSource<? extends Number> valuesData = XDDFDataSourcesFactory.fromArray(values1, valuesDataRange, COLUMN_COUNTRIES);
        valuesData.setFormatCode("General");

        XDDFBarChartData bar = (XDDFBarChartData) chart.createData(ChartTypes.BAR, bottomAxis, leftAxis);
        bar.setBarGrouping(BarGrouping.CLUSTERED);

        XDDFBarChartData.Series series1 = (XDDFBarChartData.Series) bar.addSeries(categoriesData, valuesData);
        series1.setTitle(series[0], chart.setSheetTitle(series[0], COLUMN_COUNTRIES));

        bar.setVaryColors(true);
        bar.setBarDirection(BarDirection.COL);
        chart.plot(bar);
        List<List<Double>> yValues = Lists.newArrayList();
        yValues.add(Arrays.asList(values1));
        for (int s = 0 ; s < yValues.size(); s++) {
            chart.getCTChart().getPlotArea().getBarChartArray(0).getSerArray(s).addNewDLbls();
            chart.getCTChart().getPlotArea().getBarChartArray(0).getSerArray(s).getDLbls()
                    .addNewDLblPos().setVal(org.openxmlformats.schemas.drawingml.x2006.chart.STDLblPos.CTR);
            chart.getCTChart().getPlotArea().getBarChartArray(0).getSerArray(s).getDLbls().addNewShowVal().setVal(true);
            chart.getCTChart().getPlotArea().getBarChartArray(0).getSerArray(s).getDLbls().addNewShowLegendKey().setVal(false);
            chart.getCTChart().getPlotArea().getBarChartArray(0).getSerArray(s).getDLbls().addNewShowCatName().setVal(false);
            chart.getCTChart().getPlotArea().getBarChartArray(0).getSerArray(s).getDLbls().addNewShowSerName().setVal(false);
        }

        XDDFChartLegend legend = chart.getOrAddLegend();
        legend.setPosition(LegendPosition.BOTTOM);
        legend.setOverlay(false);
        chart.setTitleText(chartTitle);
        chart.setTitleOverlay(false);
        chart.setAutoTitleDeleted(false);
    }

    public String isEm(String str){
        if (str==null||"null".equals(str)){
            return "";
        }
        return str;
    }

    public Boolean exportWordIfSucess(String filePath) {
        String s = docMap.get(filePath);
        if (StringUtils.isNotEmpty(s)){
            docMap.remove(filePath);
            return true;
        }
        return false;
    }

    /**
     * 更新标题
     *
     * @param xdoc
     * @author yangj
     */
    private void updateTitle(XWPFDocument xdoc) {
        List list = new ArrayList();
        Iterator<XWPFParagraph> paragraphsIterator = xdoc.getParagraphsIterator();
        List<XWPFParagraph> paras = xdoc.getParagraphs();
        String preFirstTitle = "";
        Map<String, String> preSecondTitle = Maps.newHashMap();
        while (paragraphsIterator.hasNext()) {
            XWPFParagraph para = paragraphsIterator.next();
            System.out.println(para.getStyleID());//段落级别
            System.out.println(para.getParagraphText());//段落内容

            String titleLvl = getTitleLvl(xdoc, para);//获取段落级别
            if ("a5".equals(titleLvl) || "HTML".equals(titleLvl) || "".equals(titleLvl) || null == titleLvl) {
                titleLvl = "8";
            }
//            一级标题
            if ("0".equals(titleLvl)) {
//                XWPFRun orderBig = para.getRuns().get(0);
//                更新一级标题
                preFirstTitle = setFirstTitleSort(para, preFirstTitle);
//                二级标题
            } else if ("1".equals(titleLvl)) {
                setSecondTitleSort(para, preSecondTitle);
            } else {
                continue;
            }
        }

    }

    /**
     * 设置二级标题
     *
     * @param orderBig
     * @param preSecondTitle
     * @author yangj
     */
    private void setSecondTitleSort(XWPFParagraph orderBig, Map<String, String> preSecondTitle) {
        String text = orderBig.getText();
        String[] texts = text.split("、");
        if (texts[0].indexOf("-") != -1) {
            String[] split = texts[0].split("-");
            if (split.length > 1) {
                String titleSort = split[1];
                String key = split[0];
                String preRun = preSecondTitle.get(split[0]);
                if (StringUtils.isEmpty(preRun)) {
                    preRun = "0";
                }
//                刷新标题
                Integer sort = Integer.valueOf(preRun)+1;
                removeOtherRun(orderBig);
                orderBig.getRuns().get(0).setText(setTitle(sort+"", texts), 0);
                preSecondTitle.put(key, sort+"");
            }
        }

    }

    /**
     * 移除多的run
     *
     * @param orderBig
     */
    private void removeOtherRun(XWPFParagraph orderBig) {
        List<XWPFRun> runs = orderBig.getRuns();
        for (int i = 0; i < runs.size(); i++) {
            if (i > 0) {
                orderBig.getRuns().get(i).setText("",0);
            }
        }
    }

    /**
     * 更新一级标题 可以采用数据库存储数据   按顺序取不使用switch case硬编码
     *
     * @param orderBig
     * @param preRun
     * @return
     * @author yangj
     */
    private String setFirstTitleSort(XWPFParagraph orderBig, String preRun) {
        String text = orderBig.getText();
        String[] texts = text.split("、");
        switch (preRun) {
            case "十":
                if (!"十一".equals(preRun)) {
                    removeOtherRun(orderBig);
                    orderBig.getRuns().get(0).setText(setTitle("十一", texts), 0);
                }
                return "十一";
            case "一":
                if (!"二".equals(texts[0])) {
                    removeOtherRun(orderBig);
                    orderBig.getRuns().get(0).setText(setTitle("二", texts), 0);
                }
                return "二";
            case "二":
                if (!"三".equals(texts[0])) {
                    removeOtherRun(orderBig);
                    orderBig.getRuns().get(0).setText(setTitle("三", texts), 0);
                }
                return "三";
            case "三":
                if (!"四".equals(texts[0])) {
                    removeOtherRun(orderBig);
                    orderBig.getRuns().get(0).setText(setTitle("四", texts), 0);
                }
                return "四";
            case "四":
                if (!"五".equals(texts[0])) {
                    removeOtherRun(orderBig);
                    orderBig.getRuns().get(0).setText(setTitle("五", texts), 0);
                }
                return "五";
            case "五":
                if (!"六".equals(texts[0])) {
                    removeOtherRun(orderBig);
                    orderBig.getRuns().get(0).setText(setTitle("六", texts), 0);
                }
                return "六";
            case "六":
                if (!"七".equals(texts[0])) {
                    removeOtherRun(orderBig);
                    orderBig.getRuns().get(0).setText(setTitle("七", texts), 0);
                }
                return "七";
            case "七":
                if (!"八".equals(texts[0])) {
                    removeOtherRun(orderBig);
                    orderBig.getRuns().get(0).setText(setTitle("八", texts), 0);
                }
                return "八";
            case "八":
                if (!"九".equals(texts[0])) {
                    removeOtherRun(orderBig);
                    orderBig.getRuns().get(0).setText(setTitle("九", texts), 0);
                }
                return "九";
            case "九":
                if (!"十".equals(texts[0])) {
                    removeOtherRun(orderBig);
                    orderBig.getRuns().get(0).setText(setTitle("十", texts), 0);
                }
                return "十";

            default:
                System.out.println(orderBig);
                break;
        }
        return texts[0];
    }

    private String setTitle(String str, String[] texts) {
//        解析不了顿号部分数据给run添加、   可以修改成其他方式
        if (texts.length == 1) {
            str = str + "、";
        } else {
            for (int i = 0; i < texts.length; i++) {
                if (i > 0) {
                    str = str + "、" + texts[i];
                }
            }
        }
        return str;
    }

    /**
     * 96      * Word中的大纲级别，可以通过getPPr().getOutlineLvl()直接提取，但需要注意，Word中段落级别，通过如下三种方式定义：
     * 97      *  1、直接对段落进行定义；
     * 98      *  2、对段落的样式进行定义；
     * 99      *  3、对段落样式的基础样式进行定义。
     * 100      *  因此，在通过“getPPr().getOutlineLvl()”提取时，需要依次在如上三处读取。
     * 101      * @param doc
     * 102      * @param para
     * 103      * @return
     * 104
     */
    private static String getTitleLvl(XWPFDocument doc, XWPFParagraph para) {
        String titleLvl = "";
        try {
            //判断该段落是否设置了大纲级别
            if (para.getCTP().getPPr().getOutlineLvl() != null) {
                // System.out.println("getCTP()");
                //              System.out.println(para.getParagraphText());
                //              System.out.println(para.getCTP().getPPr().getOutlineLvl().getVal());

                return String.valueOf(para.getCTP().getPPr().getOutlineLvl().getVal());
            }
        } catch (Exception e) {

        }

        try {
            //判断该段落的样式是否设置了大纲级别
            if (doc.getStyles().getStyle(para.getStyle()).getCTStyle().getPPr().getOutlineLvl() != null) {

                // System.out.println("getStyle");
                //              System.out.println(para.getParagraphText());
                //              System.out.println(doc.getStyles().getStyle(para.getStyle()).getCTStyle().getPPr().getOutlineLvl().getVal());

                return String.valueOf(doc.getStyles().getStyle(para.getStyle()).getCTStyle().getPPr().getOutlineLvl().getVal());
            }
        } catch (Exception e) {

        }

        try {
            //判断该段落的样式的基础样式是否设置了大纲级别
            if (doc.getStyles().getStyle(doc.getStyles().getStyle(para.getStyle()).getCTStyle().getBasedOn().getVal())
                    .getCTStyle().getPPr().getOutlineLvl() != null) {
                // System.out.println("getBasedOn");
                //              System.out.println(para.getParagraphText());
                String styleName = doc.getStyles().getStyle(para.getStyle()).getCTStyle().getBasedOn().getVal();
                //              System.out.println(doc.getStyles().getStyle(styleName).getCTStyle().getPPr().getOutlineLvl().getVal());

                return String.valueOf(doc.getStyles().getStyle(styleName).getCTStyle().getPPr().getOutlineLvl().getVal());
            }
        } catch (Exception e) {

        }

        try {
            if (para.getStyleID() != null) {
                return para.getStyleID();
            }
        } catch (Exception e) {

        }

        return titleLvl;
    }

}

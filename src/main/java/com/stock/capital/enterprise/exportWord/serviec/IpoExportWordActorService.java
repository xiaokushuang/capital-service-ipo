package com.stock.capital.enterprise.exportWord.serviec;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.*;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

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
            wordMap.put("#问询回复/反馈意见#","问询与回复（注册制）");
            wordMap.put("#审核关注问题#","审核结果及注册（注册制）");
        }else {
            wordMap.put("#板块类型#","核准制");
            wordMap.put("#问询回复/反馈意见#","反馈意见（核准制）");
            wordMap.put("#审核关注问题#","审核结果及注册（核准制）");
        }
      }
      wordMap.put("#企业性质#",((CompanyOverviewVo)dataMap.get("companyInformation")).getCompanyNature());
      if (StringUtils.isEmpty(((CompanyOverviewVo)dataMap.get("companyInformation")).getAddrCountry()) ||
                ((CompanyOverviewVo)dataMap.get("companyInformation")).getAddrCountry().equals("中国")){
          wordMap.put("#注册地址#",isEm(((CompanyOverviewVo)dataMap.get("companyInformation")).getAddrProv())+isEm(((CompanyOverviewVo)dataMap.get("companyInformation")).getAddrCity())
                  +isEm(((CompanyOverviewVo)dataMap.get("companyInformation")).getAddrArea()));
      }else {
          wordMap.put("#注册地址#",isNull(((CompanyOverviewVo)dataMap.get("companyInformation")).getAddrCountry()));
      }
      wordMap.put("#注册资本#",twoMarkThStr(((CompanyOverviewVo)dataMap.get("companyInformation")).getRegisteredAssets()+"")+"万元");
      wordMap.put("#战略新兴行业#",isNull(((CompanyOverviewVo)dataMap.get("companyInformation")).getStrageticIndustries()));
      wordMap.put("#证监会行业#",isNull(((CompanyOverviewVo)dataMap.get("companyInformation")).getIndustryCsrc()));
      wordMap.put("#实际控制人#",isNull(((CompanyOverviewVo)dataMap.get("companyInformation")).getActualController()));
      wordMap.put("#控股股东#",isNull(((CompanyOverviewVo)dataMap.get("companyInformation")).getControlShareholder()));
      wordMap.put("#配售机制#",isNull(((CompanyOverviewVo)dataMap.get("companyInformation")).getPlacingMechanism()));
      wordMap.put("#上市标准#",isNull(((CompanyOverviewVo)dataMap.get("companyInformation")).getIssueConditionName()));
      wordMap.put("#主营业务#",isNull(((CompanyOverviewVo)dataMap.get("companyInformation")).getMajorBusinesses()));
      List<OtherMarketInfoDto> otherList = (List<OtherMarketInfoDto>)dataMap.get("otherMarkInfo");
      List<IpoSplitDto> splitList = (List<IpoSplitDto>)dataMap.get("splitList");

      if (((CompanyOverviewVo)dataMap.get("companyInformation")).getStructureLabel()==null){
          wordMap.put("#股权结构类型#","    暂无股权结构");
      }else {
          wordMap.put("#股权结构类型#",isEm(((CompanyOverviewVo)dataMap.get("companyInformation")).getStructureLabel())+" ");
      }
      List<IssuerIndustryStatusDto> industryStatusDtoList = (List<IssuerIndustryStatusDto>)dataMap.get("industryStatusDtoList");
      MainIncomeVo mainIncomeVo = (MainIncomeVo)dataMap.get("mainIncome");
      if (mainIncomeVo!=null &&mainIncomeVo.getMainIncomeInfoList() != null){
          wordMap.put("#主营业务描述#"," ");
      }else {
          wordMap.put("#主营业务描述#","    暂无主营业务");
      }

      IpoFeedbackDto ipoFeedback=  ((IpoFeedbackDto)dataMap.get("ipoFeedbackDto"));
      List<IpoExamineBaseDto> baseList=ipoFeedback.getBaseList();
        List<IpoFeedbackDto> resultList=(List<IpoFeedbackDto>)dataMap.get("resultList");

        List<SupplierCustomerMainDto> supplierMainList = ((Map<String,List<SupplierCustomerMainDto>>)dataMap.get("supplierInformation")).get("supplierMainList");
      List<SupplierCustomerMainDto> customerMainList = ((Map<String,List<SupplierCustomerMainDto>>)dataMap.get("supplierInformation")).get("customerMainList");
      List<IpoFeedbackDto> ipoFeedbackList =  (List<IpoFeedbackDto>)dataMap.get("ipoFeedbackList");
      List<MainCompetitorInfoDto> competitorData = (List<MainCompetitorInfoDto>)dataMap.get("competitorData");
      if (competitorData!=null && competitorData.size()>0){
          wordMap.put("#主要竞争对手详细#"," ");
      }else {
          wordMap.put("#主要竞争对手详细#","    暂无主要竞争对手简介");
      }
      List<IndustryCompareRateDto> industryCompareList = (List<IndustryCompareRateDto>)dataMap.get("industryCompareList");

      IpoTechnologyVo ipoTechnology = (IpoTechnologyVo)dataMap.get("ipoTechnology");
      if (ipoTechnology != null && ipoTechnology.getCoreData() != null&&ipoTechnology.getCoreData().size()>0){
          wordMap.put("#研发人员备注#",ipoTechnology.getRemarksData().getCoreRemarks()+" ");
      }else {
          wordMap.put("#研发人员备注#","    暂无研发人员信息");
      }
      if (ipoTechnology != null && ipoTechnology.getDevData() != null&&ipoTechnology.getDevData().size()>0){
          wordMap.put("#研发投入备注#",ipoTechnology.getRemarksData().getDevRemarks()+" ");
          wordMap.put("#研发投入单位#","单位：万元");
      }else {
          wordMap.put("#研发投入备注#","    暂无研发投入信息");
          wordMap.put("#研发投入单位#"," ");
        }
      IssueDataDto issueData = (IssueDataDto)dataMap.get("issueData");
      IpoFinanceDto ipoFinance = (IpoFinanceDto)dataMap.get("ipoFinance");
      IpoFinanceDto assetLiability = (IpoFinanceDto)dataMap.get("assetLiability");
      IpoFinanceDto financialIndex = (IpoFinanceDto)dataMap.get("financialIndex");
      IpoFinanceDto incomeProfit = (IpoFinanceDto)dataMap.get("incomeProfit");
      if(incomeProfit!=null&&incomeProfit.getIpoProfitItemList()!=null &&incomeProfit.getIpoCostItemList()!=null&&incomeProfit.getIpoReturnOverList()!=null){
          wordMap.put("#收入与利润情况单位#","单位：万元");
      }else {
          wordMap.put("#收入与利润情况单位#"," ");

      }
      List<IpoInvestItemDto> ipoInvestItem = (List<IpoInvestItemDto>)dataMap.get("ipoInvestItem");
      if (ipoInvestItem != null && ipoInvestItem.size()>0){
          wordMap.put("#募集资金备注#",ipoInvestItem.get(0).getInvestRemark()+" ");
      }else {
          wordMap.put("#募集资金备注#","    暂无募集资金内容");
      }
        if (ipoInvestItem != null && ipoInvestItem.size()>0){
            wordMap.put("#募集资金单位#","单位：万元");
        }else {
            wordMap.put("#募集资金单位#"," ");
        }
      List<IssueFeeDto> issueFeeList = (List<IssueFeeDto>)dataMap.get("issueFeeList");
      if (issueFeeList!=null && issueFeeList.size()>0){
          wordMap.put("#发行费用单位#","单位：万元");
      }else {
          wordMap.put("#发行费用单位#"," ");
      }
      if (issueFeeList!=null && issueFeeList.size()>0){
        wordMap.put("#发行费用描述#"," ");
      }else {
        wordMap.put("#发行费用描述#","    暂无发行费用");
      }
      StrategicPlacementMainDto strategicPlacementMain =(StrategicPlacementMainDto)dataMap.get("strategicPlacementMain");
      if (strategicPlacementMain!=null && strategicPlacementMain.getSubs()!=null && strategicPlacementMain.getSubs().size()>0){
          wordMap.put("#战略配售情况#"," ");
      }else {
          wordMap.put("#战略配售情况#","    暂无战略配售情况");
      }
      Map<String, List<IntermediaryOrgDto>> intermediaryOrgList = (Map<String, List<IntermediaryOrgDto>>)dataMap.get("intermediaryOrgList");
      List<IntermediaryOrgDto> bjorg = new ArrayList<>();
      List<IntermediaryOrgDto> laworg = new ArrayList<>();
      List<IntermediaryOrgDto> finorg = new ArrayList<>();
      List<IntermediaryOrgDto> fzcorg = new ArrayList<>();
      List<IntermediaryOrgDto> yzorg = new ArrayList<>();
      List<IntermediaryOrgDto> yzfhorg = new ArrayList<>();
      List<IntermediaryOrgDto> zcpgorg = new ArrayList<>();
      if (intermediaryOrgList != null){
          if (intermediaryOrgList.get("mainList") != null){
              List<IntermediaryOrgDto> mainList = intermediaryOrgList.get("mainList");
              for (int i=0;i<mainList.size();i++){
                  if (mainList.get(i).getIntermediaryType().equals("1")){
                      bjorg.add(mainList.get(i));
                  }else if (mainList.get(i).getIntermediaryType().equals("3") && mainList.get(i).getOrgType().equals("01")){
                      laworg.add(mainList.get(i));
                  }else if (mainList.get(i).getIntermediaryType().equals("4") && mainList.get(i).getOrgType().equals("01")){
                      finorg.add(mainList.get(i));
                  }else if (mainList.get(i).getIntermediaryType().equals("2") && mainList.get(i).getOrgType().equals("03")){
                      fzcorg.add(mainList.get(i));
                  }else if (mainList.get(i).getIntermediaryType().equals("4") && mainList.get(i).getOrgType().equals("02")){
                      yzorg.add(mainList.get(i));
                  }else if (mainList.get(i).getIntermediaryType().equals("4") && mainList.get(i).getOrgType().equals("03")){
                      yzfhorg.add(mainList.get(i));
                  }else if (mainList.get(i).getIntermediaryType().equals("5") && mainList.get(i).getOrgType().equals("01")){
                      zcpgorg.add(mainList.get(i));
                  }
              }
          }
          if (intermediaryOrgList.get("moreList") != null){
              List<IntermediaryOrgDto> moreList = intermediaryOrgList.get("moreList");
              for (int i=0;i<moreList.size();i++){
                  if (moreList.get(i).getIntermediaryType().equals("1")){
                      bjorg.add(moreList.get(i));
                  }else if (moreList.get(i).getIntermediaryType().equals("3") && moreList.get(i).getOrgType().equals("01")){
                      laworg.add(moreList.get(i));
                  }else if (moreList.get(i).getIntermediaryType().equals("4") && moreList.get(i).getOrgType().equals("01")){
                      finorg.add(moreList.get(i));
                  }else if (moreList.get(i).getIntermediaryType().equals("2") && moreList.get(i).getOrgType().equals("03")){
                      fzcorg.add(moreList.get(i));
                  }else if (moreList.get(i).getIntermediaryType().equals("4") && moreList.get(i).getOrgType().equals("02")){
                      yzorg.add(moreList.get(i));
                  }else if (moreList.get(i).getIntermediaryType().equals("4") && moreList.get(i).getOrgType().equals("03")){
                      yzfhorg.add(moreList.get(i));
                  }else if (moreList.get(i).getIntermediaryType().equals("5") && moreList.get(i).getOrgType().equals("01")){
                      zcpgorg.add(moreList.get(i));
                  }
              }
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
              if (i == 0){
                  wordMap.put("#发行历时#",treeTypeProgress.getTreeList().get(i).getDurationDay());
              }else if (i == 1){
                  wordMap.put("#审核历时#",treeTypeProgress.getTreeList().get(i).getDurationDay());
              }else if (i == 2){
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

//文本框
        if(((IpoFeedbackDto)dataMap.get("ipoFeedbackDto")).getBaseList()!=null){
            int len=((IpoFeedbackDto)dataMap.get("ipoFeedbackDto")).getBaseList().size();
            wordMap.put("审核结果类型","审核结果:"+isNull(((IpoFeedbackDto)dataMap.get("ipoFeedbackDto")).getBaseList().get(len-1).getIecResultStr()));
            wordMap.put("审核日期","审核日期:"+isNull(((IpoFeedbackDto)dataMap.get("ipoFeedbackDto")).getBaseList().get(len-1).getExamineDateStr()));
        }else {
            wordMap.put("审核结果类型","审核结果:"+"--");
            wordMap.put("审核日期","审核日期:"+"--");
        }


        wordMap.put("公司名称详情",isNull(((CompanyOverviewVo)dataMap.get("companyInformation")).getCompanyName()));
//        wordMap.put("公司名称类型",((CompanyOverviewVo)dataMap.get("companyInformation")).getCompanyZhName()+"\n"+"IPO");
        wordMap.put("拟上市板块","拟上市板块:"+isNull(((CompanyOverviewVo)dataMap.get("companyInformation")).getIpoPlate()));

        wordMap.put("审核历时","审核历时:"+isNull(wordMap.get("#审核历时#"))+"天");
        wordMap.put("IPO进程详情","IPO进程:"+isNull(((HeadDataVo)dataMap.get("head")).getProcessLabel()));

        wordMap.put("注册地址说明",isNull(((CompanyOverviewVo)dataMap.get("companyInformation")).getAddrCountry()));
        wordMap.put("证监会行业说明",isNull(((CompanyOverviewVo)dataMap.get("companyInformation")).getIndustryCsrc()));
        for (XWPFParagraph paragraph : xdoc.getParagraphs())
            for (XmlObject object : paragraph.getCTP().getRArray()) {
                XmlCursor cursor = object.newCursor();
                eachchild(cursor, 0, wordMap);
            }


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
          }else if("#资本市场#".equals(paragraph.getText())){
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
                      content = content + "上市公司：" + splitList.get(b).getCompanyName()+"公司\n";
                  }
                  if (StringUtils.isNotEmpty(splitList.get(b).getSplitMarket())){
                      content = content + "所在市场：" + splitList.get(b).getSplitMarket()+"\n";
                  }
                  if (splitList.get(b).getShareProportion()!=null){
                      content = content + "直接或间接持有人股份：" + splitList.get(b).getShareProportion()+""+"%\n";
                  }
              }
              if ("".equals(content)){
                  content="    暂无拆分上市情况";
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
              int flag=0;
              if ((List<IpoValuationDto>)dataMap.get("valuationList") != null && ((List<IpoValuationDto>) dataMap.get("valuationList")).size()>0){
                  if (StringUtils.isNotEmpty(((List<IpoValuationDto>)dataMap.get("valuationList")).get(0).getValuationDate())){
                      content = content + "时间：" + ((List<IpoValuationDto>)dataMap.get("valuationList")).get(0).getValuationDate()+"\n";
                  }
                  if ((((List<IpoValuationDto>)dataMap.get("valuationList")).get(0).getValuationPrice())!=null){
                      content = content + "股价：" + twoMarkThStr(((List<IpoValuationDto>)dataMap.get("valuationList")).get(0).getValuationPrice().toString())+"元/股\n";
                      flag++;
                  }
                  if ((((List<IpoValuationDto>)dataMap.get("valuationList")).get(0).getValuationEquity())!=null){
                      content = content + "总股本：" + twoMarkThStr(((List<IpoValuationDto>)dataMap.get("valuationList")).get(0).getValuationEquity().toString())+"万股\n";
                  }
                  if ((((List<IpoValuationDto>)dataMap.get("valuationList")).get(0).getValuationValue())!=null){
                      content = content + "估值：" + twoMarkThStr(((List<IpoValuationDto>)dataMap.get("valuationList")).get(0).getValuationValue()+"")+"亿元\n";
                  }
                  if (StringUtils.isNotEmpty(((List<IpoValuationDto>)dataMap.get("valuationList")).get(0).getValuationMemo())){
                      content = content + ((List<IpoValuationDto>)dataMap.get("valuationList")).get(0).getValuationMemo()+"\n";
                      flag++;
                  }
              }else {
                  content="    暂无最近一次估值";
              }
              if ("".equals(content)){
                  content="    暂无最近一次估值";
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
                  for (int b=0;b<industryStatusDtoList.size();b++){
                      if (StringUtils.isNotEmpty(industryStatusDtoList.get(b).getRankingRange())){
                          content = content + industryStatusDtoList.get(b).getRankingRange();
                      }
                      if (StringUtils.isNotEmpty(industryStatusDtoList.get(b).getRankingIndicator())){
                          content = content + industryStatusDtoList.get(b).getRankingIndicator();
                      }
                      if (StringUtils.isNotEmpty(industryStatusDtoList.get(b).getRanking())){
                          content = content + "第"+industryStatusDtoList.get(b).getRanking()+"名\n";
                      }
                  }
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
                  test.clearParagraph(paragraph);
                  XWPFRun run = paragraph.createRun();
                  run.setText("    暂无行业信息");
              }


              continue;
          }else if("#保荐机构#".equals(paragraph.getText())){
              String content = "";
              for (int b=0;b<bjorg.size();b++){
                  if (StringUtils.isNotEmpty(bjorg.get(b).getOrgName())){
                      content = content + "机构名称：" + bjorg.get(b).getOrgName() + "\n";
                  }
                  if (StringUtils.isNotEmpty(bjorg.get(b).getRepresentPerson())){
                      content = content + "保荐代表人：" + bjorg.get(b).getRepresentPerson() + "\n";
                  }
                  if (StringUtils.isNotEmpty(bjorg.get(b).getAssistPerson())){
                      content = content + "项目协办人：" + bjorg.get(b).getAssistPerson() + "\n";
                  }
                  if (StringUtils.isNotEmpty(bjorg.get(b).getAgentPerson())){
                      content = content + "项目经办人：" + bjorg.get(b).getAgentPerson() + "\n";
                  }
              }
              if ("".equals(content)){
                  content="    暂无保荐机构";
              }
              String text = paragraph.getRuns().get(0).text().replaceAll("#保荐机构#", content);
              if (text != null) {
                  if (text.indexOf("\n") == -1) {
                      paragraph.getRuns().get(0).setText(text, 0);
                  } else {
                      replaceNeedBreak(paragraph, text, "#保荐机构#");
                  }
              }
              continue;
          }else if("#律师事务所#".equals(paragraph.getText())){
              String content = "";
              for (int b=0;b<laworg.size();b++){
                  if (StringUtils.isNotEmpty(laworg.get(b).getOrgName())){
                      content = content + "机构名称：" + laworg.get(b).getOrgName() + "\n";
                  }
                  if (StringUtils.isNotEmpty(laworg.get(b).getAgentPerson())){
                      content = content + "经办律师：" + laworg.get(b).getAgentPerson() + "\n";
                  }
              }
              if ("".equals(content)){
                  content="    暂无律师事务所";
              }
              String text = paragraph.getRuns().get(0).text().replaceAll("#律师事务所#", content);
              if (text != null) {
                  if (text.indexOf("\n") == -1) {
                      paragraph.getRuns().get(0).setText(text, 0);
                  } else {
                      replaceNeedBreak(paragraph, text, "#律师事务所#");
                  }
              }
              continue;
          }else if("#会计师事务所#".equals(paragraph.getText())){
              String content = "";
              for (int b=0;b<finorg.size();b++){
                  if (StringUtils.isNotEmpty(finorg.get(b).getOrgName())){
                      content = content + "机构名称：" + finorg.get(b).getOrgName() + "\n";
                  }
                  if (StringUtils.isNotEmpty(finorg.get(b).getAgentPerson())){
                      content = content + "注册会计师：" + finorg.get(b).getAgentPerson() + "\n";
                  }
              }
              if ("".equals(content)){
                  content="    暂无会计师事务所";
              }
              String text = paragraph.getRuns().get(0).text().replaceAll("#会计师事务所#", content);
              if (text != null) {
                  if (text.indexOf("\n") == -1) {
                      paragraph.getRuns().get(0).setText(text, 0);
                  } else {
                      replaceNeedBreak(paragraph, text, "#会计师事务所#");
                  }
              }
              continue;
          }else if("#副主承销商#".equals(paragraph.getText())){
              String content = "";
              for (int b=0;b<fzcorg.size();b++){
                  if (StringUtils.isNotEmpty(fzcorg.get(b).getOrgName())){
                      content = content + "机构名称：" + fzcorg.get(b).getOrgName() + "\n";
                  }
                  if (StringUtils.isNotEmpty(fzcorg.get(b).getAgentPerson())){
                      content = content + "项目经办人：" + fzcorg.get(b).getAgentPerson() + "\n";
                  }
              }
              if ("".equals(content)){
                  content="    暂无副主承销商";
              }
              String text = paragraph.getRuns().get(0).text().replaceAll("#副主承销商#", content);
              if (text != null) {
                  if (text.indexOf("\n") == -1) {
                      paragraph.getRuns().get(0).setText(text, 0);
                  } else {
                      replaceNeedBreak(paragraph, text, "#副主承销商#");
                  }
              }
              continue;
          }else if("#验资机构#".equals(paragraph.getText())){
              String content = "";
              for (int b=0;b<yzorg.size();b++){
                  if (StringUtils.isNotEmpty(yzorg.get(b).getOrgName())){
                      content = content + "机构名称：" + yzorg.get(b).getOrgName() + "\n";
                  }
                  if (StringUtils.isNotEmpty(yzorg.get(b).getAgentPerson())){
                      content = content + "注册会计师：" + yzorg.get(b).getAgentPerson() + "\n";
                  }
              }
              if ("".equals(content)){
                  content="    暂无验资机构";
              }
              String text = paragraph.getRuns().get(0).text().replaceAll("#验资机构#", content);
              if (text != null) {
                  if (text.indexOf("\n") == -1) {
                      paragraph.getRuns().get(0).setText(text, 0);
                  } else {
                      replaceNeedBreak(paragraph, text, "#验资机构#");
                  }
              }
              continue;
          }else if("#验资复核机构#".equals(paragraph.getText())){
              String content = "";
              for (int b=0;b<yzfhorg.size();b++){
                  if (StringUtils.isNotEmpty(yzfhorg.get(b).getOrgName())){
                      content = content + "机构名称：" + yzfhorg.get(b).getOrgName() + "\n";
                  }
                  if (StringUtils.isNotEmpty(yzfhorg.get(b).getAgentPerson())){
                      content = content + "注册会计师：" + yzfhorg.get(b).getAgentPerson() + "\n";
                  }
              }
              if ("".equals(content)){
                  content="    暂无验资复核机构";
              }
              String text = paragraph.getRuns().get(0).text().replaceAll("#验资复核机构#", content);
              if (text != null) {
                  if (text.indexOf("\n") == -1) {
                      paragraph.getRuns().get(0).setText(text, 0);
                  } else {
                      replaceNeedBreak(paragraph, text, "#验资复核机构#");
                  }
              }
              continue;
          }else if("#资产评估机构#".equals(paragraph.getText())){
              String content = "";
              for (int b=0;b<zcpgorg.size();b++){
                  if (StringUtils.isNotEmpty(zcpgorg.get(b).getOrgName())){
                      content = content + "机构名称：" + zcpgorg.get(b).getOrgName() + "\n";
                  }
                  if (StringUtils.isNotEmpty(zcpgorg.get(b).getAgentPerson())){
                      content = content + "注册资产评估师：" + zcpgorg.get(b).getAgentPerson() + "\n";
                  }
              }
              if ("".equals(content)){
                  content="    暂无资产评估机构";
              }
              String text = paragraph.getRuns().get(0).text().replaceAll("#资产评估机构#", content);
              if (text != null) {
                  if (text.indexOf("\n") == -1) {
                      paragraph.getRuns().get(0).setText(text, 0);
                  } else {
                      replaceNeedBreak(paragraph, text, "#资产评估机构#");
                  }
              }
              continue;
          }else if("#股权结构图#".equals(paragraph.getText())){
              test.clearParagraph(paragraph);
              try {
                  if (imgUrl!=""){
                      if (imgUrl.indexOf("https") == -1){
                          imgUrl = imgUrl.replaceAll("http","https");
                      }
                      HttpURLConnection connection = (HttpURLConnection) new URL(imgUrl).openConnection();
                      InputStream img = connection.getInputStream();
                      paragraph.getRuns().get(0).addPicture(img, XWPFDocument.PICTURE_TYPE_JPEG, imgUrl, Units.toEMU(400), Units.toEMU(400));

                  }
                  } catch (InvalidFormatException e) {
                  e.printStackTrace();
              }
              continue;
          } else if("#主营业务柱状图#".equals(paragraph.getText())){
              test.clearParagraph(paragraph);
              if (mainIncomeVo != null && mainIncomeVo.getMainIncomeInfoList() != null){
                  List<String> listLanguages = new ArrayList<>();
                  if (StringUtils.isNotEmpty(mainIncomeVo.getFirstYearForIncome())){
                      listLanguages.add(mainIncomeVo.getFirstYearForIncome());
                  }
                  if (StringUtils.isNotEmpty(mainIncomeVo.getSecondYearForIncome())){
                      listLanguages.add(mainIncomeVo.getSecondYearForIncome());
                  }
                  if (StringUtils.isNotEmpty(mainIncomeVo.getThirdYearForIncome())){
                      listLanguages.add(mainIncomeVo.getThirdYearForIncome());
                  }
                  if (StringUtils.isNotEmpty(mainIncomeVo.getOnePeriodForIncome())){
                      listLanguages.add(mainIncomeVo.getOnePeriodForIncome());
                  }

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

                  List<List<Double>> yValues = Lists.newArrayList();
                  yValues.add(listCountries4);
                  yValues.add(listCountries3);
                  yValues.add(listCountries2);
                  yValues.add(listCountries);


                  List<List<Double>> zhuziList = Lists.newArrayList();
                  int len=yValues.get(0).size();
                  for (int k=0;k<len;k++){
                      List<Double> zhuzi = new ArrayList<>();
                      for (int p=0;p<yValues.size();p++){
                          zhuzi.add(yValues.get(p).get(k));
                      }
                      zhuziList.add(zhuzi);
                  }
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
                  newRun.addBreak();

              //饼图
                  XWPFChart chart1 = xdoc.createChart(XDDFChart.DEFAULT_WIDTH * 8, XDDFChart.DEFAULT_HEIGHT * 10);
                  poiWordInsertChartUtils.setSimpleBarData(chart1,"2","","",series,listLanguages,zhuziList);
                  xdoc.removeBodyElement(xdoc.getBodyElements().size()-1);
                  itPara = xdoc.getParagraphsIterator();
                  XWPFRun newRun1 = paragraph.createRun();
                  String relationId1 = xdoc.getRelationId(chart1);
                  java.lang.reflect.Method attach1 = XWPFChart.class.getDeclaredMethod("attach", String.class, XWPFRun.class);
                  attach1.setAccessible(true);
                  attach1.invoke(chart1, relationId1, newRun1);
                  chart1.setChartWidth(XDDFChart.DEFAULT_WIDTH * 10);
                  chart1.setChartHeight(XDDFChart.DEFAULT_HEIGHT * 8);
                  newRun1.addBreak();
              }
          }
          else if("#问询回复/反馈意见流程#".equals(paragraph.getText())){
              test.clearParagraph(paragraph);
              SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
              if (ipoFeedbackList==null||ipoFeedbackList.size()==0){
                  XWPFRun run2 = paragraph.createRun();
                  run2.setFontFamily("宋体");
                  run2.setFontSize(10);
                  run2.setText("    暂无数据");
              }
              if (ipoFeedbackList!=null &&ipoFeedbackList.size()>0){
                  for (int b=0;b<ipoFeedbackList.size();b++){
                      XmlCursor cursor1 = paragraph.getCTP().newCursor();
                      cursor1.toNextSibling();
                      XWPFParagraph newpa1 = xdoc.insertNewParagraph(cursor1);
                      int sort=b+1;
                      XWPFRun run1 = newpa1.createRun();
                      run1.setText(sort+"、"+isNull(ipoFeedbackList.get(b).getLetterName()));
                      run1.setFontSize(16);
                      run1.setColor("333399");
                      run1.setBold(true);
                      newpa1.setStyle("4");
                      run1.setFontFamily("微软雅黑");
                      XmlCursor cursor2= newpa1.getCTP().newCursor();
                      cursor2.toNextSibling();
                      XWPFParagraph newpa2 = xdoc.insertNewParagraph(cursor2);
                      if (StringUtils.isNotEmpty(ipoFeedbackList.get(b).getLetterFileNo())){
                          XWPFRun run2 = newpa2.createRun();
                          run2.setText("函件文号：" + ipoFeedbackList.get(b).getLetterFileNo());
                          run2.addBreak();
                      }
                      if (ipoFeedbackList.get(b).getLetterDate()!=null){
                          XWPFRun run2 = newpa2.createRun();
                          run2.setText("发函时间：" + df.format(ipoFeedbackList.get(b).getLetterDate()));
                          run2.addBreak();
                      }
                      if (ipoFeedbackList.get(b).getReturnDate()!=null){
                          XWPFRun run2 = newpa2.createRun();
                          run2.setText("回函时间：" + df.format(ipoFeedbackList.get(b).getReturnDate()));
                          run2.addBreak();
                      }
                      if (StringUtils.isNotEmpty(ipoFeedbackList.get(b).getIntervalDate())){
                          XWPFRun run2 = newpa2.createRun();
                          run2.setText("回函用时：" + ipoFeedbackList.get(b).getIntervalDate()+"天");
                          run2.addBreak();
                      }
                      if (StringUtils.isNotEmpty(String.valueOf(ipoFeedbackList.get(b).getQuestionCount()))){
                          XWPFRun run2 = newpa2.createRun();
                          run2.setText( "问题数量：共计" + ipoFeedbackList.get(b).getQuestionCount()+"个问题" );
                          run2.addBreak();
                      }
                      if (StringUtils.isNotEmpty(String.valueOf(ipoFeedbackList.get(b).getAnswerCount()))){
                          XWPFRun run2 = newpa2.createRun();
                          run2.setText( "回复情况：共计" + ipoFeedbackList.get(b).getAnswerCount() + "个回复");
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
                      for(int i = 0; i < list.size();i++) {
                          listCountries.add(Double.valueOf(list.get(i).getLabelCount()));
                          listLanguages.add(list.get(i).getLabelName());
                      }
                      String[] categories = listLanguages.toArray(new String[0]);
                      Double[] values1 = listCountries.toArray(new Double[0]);
                      Double[] values2 = listCountries.toArray(new Double[0]);
                      XWPFChart chart = xdoc.createChart(XDDFChart.DEFAULT_WIDTH * 10,  XDDFChart.DEFAULT_HEIGHT * 15);
                      setBarData(chart, "", series.toArray(new String[]{}), categories, values1,values2);
                      xdoc.removeBodyElement(xdoc.getBodyElements().size()-1);
                      itPara = xdoc.getParagraphsIterator();
                      XWPFRun newRun = newpa3.createRun();
                      String relationId = xdoc.getRelationId(chart);
                      java.lang.reflect.Method attach = XWPFChart.class.getDeclaredMethod("attach", String.class, XWPFRun.class);
                      attach.setAccessible(true);
                      attach.invoke(chart, relationId, newRun);
                      chart.setChartWidth(XDDFChart.DEFAULT_WIDTH * 10);
                      chart.setChartHeight(XDDFChart.DEFAULT_HEIGHT * 8);
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
                  run2.setFontFamily("宋体");
                  run2.setFontSize(10);
                  run2.setText("    暂无数据");
              }
              String type=wordMap.get("#板块类型#");
              String titleStr="";
              if ("注册制".equals(type)){
                  titleStr="上市委会议关注问题";
              }else {
                  titleStr="发审委会议关注问题";
              }
              int sort=0;
              if (baseList!=null && baseList.size()>0){
                  for (int b=0;b<baseList.size();b++){
                      XmlCursor cursor1 = paragraph.getCTP().newCursor();
                      cursor1.toNextSibling();
                      XWPFParagraph newpa1 = xdoc.insertNewParagraph(cursor1);
                      sort++;
                      XWPFRun run1 = newpa1.createRun();
                      run1.setText(sort+"、"+titleStr);
                      run1.setFontSize(16);
                      run1.setColor("333399");
                      run1.setBold(true);
                      newpa1.setStyle("4");
                      run1.setFontFamily("微软雅黑");
                      XmlCursor cursor2 = newpa1.getCTP().newCursor();
                      cursor2.toNextSibling();
                      XWPFParagraph newpa2 = xdoc.insertNewParagraph(cursor2);
                      XWPFRun run2 = newpa2.createRun();
                      if (StringUtils.isNotEmpty(baseList.get(b).getRelationFileTitle())){
                          run2.setText("审核会议:" + baseList.get(b).getRelationFileTitle());
                          run2.addBreak();
                      }
                      if (StringUtils.isNotEmpty(baseList.get(b).getExamineDate())){
                          run2.setText("审核时间：" + baseList.get(b).getExamineDate());
                          run2.addBreak();
                      }
                      if (StringUtils.isNotEmpty(baseList.get(b).getIecResultStr())){
                          run2.setText("审核结果：" + baseList.get(b).getIecResultStr());
                          run2.addBreak();
                      }
                      if (StringUtils.isNotEmpty(baseList.get(b).getMember())){
                          run2.setText(" 发审会委员:" + baseList.get(b).getMember());
                          run2.addBreak();
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
                      run3.setColor("333399");
                      run3.setBold(true);
                      newpa1.setStyle("4");
                      run3.setFontFamily("微软雅黑");
                      XmlCursor cursor2 = newpa1.getCTP().newCursor();
                      cursor2.toNextSibling();
                      XWPFParagraph newpa2 = xdoc.insertNewParagraph(cursor2);
                      if (StringUtils.isNotEmpty(resultList.get(c).getLetterFileNo())){
                          XWPFRun run8 = newpa2.createRun();
                          run8.setText("    函件文号：" + resultList.get(c).getLetterFileNo());
                      }
                      if (resultList.get(c).getLetterDate()!=null){
                          XWPFRun run8 = newpa2.createRun();
                          run8.setText("    发函时间：" + df.format(resultList.get(c).getLetterDate()));
                      }
                      if (resultList.get(c).getReturnDate()!=null){
                          XWPFRun run8 = newpa2.createRun();
                          run8.setText("    回函时间：" + df.format(resultList.get(c).getReturnDate()));
                      }
                      if (StringUtils.isNotEmpty(resultList.get(c).getIntervalDate())){
                          XWPFRun run8 = newpa2.createRun();
                          run8.setText("    回函用时：" + resultList.get(c).getIntervalDate());
                      }
                      if (StringUtils.isNotEmpty(resultList.get(c).getIntervalDate())){
                          XWPFRun run8 = newpa2.createRun();
                          run8.setText("    问题数量：" + "共计"+resultList.get(c).getQuestionCount()+"个问题");
                      }
                      if (StringUtils.isNotEmpty(resultList.get(c).getIntervalDate())){
                          XWPFRun run8 = newpa2.createRun();
                          run8.setText("    回复情况：" + "共计"+resultList.get(c).getAnswerCount()+"个回复");
                      }
                      XmlCursor cursor3 = newpa2.getCTP().newCursor();
                      cursor3.toNextSibling();
                      XWPFParagraph newpa3 = xdoc.insertNewParagraph(cursor3);
                      //插入图表
                      List<IpoQuestionLabelDto> list=resultList.get(c).getQuestionLabelList();
                      List<String> series = new ArrayList<>();
                      series.add("countries");
                      List<String> listLanguages = new ArrayList<>();
                      List<Double> listCountries = new ArrayList<>();
                      for(int i = 0; i < list.size();i++) {
                          listCountries.add(Double.valueOf(list.get(i).getLabelCount()));
                          listLanguages.add(list.get(i).getLabelName());
                      }
                      String[] categories = listLanguages.toArray(new String[0]);
                      Double[] values1 = listCountries.toArray(new Double[0]);
                      Double[] values2 = listCountries.toArray(new Double[0]);
                      XWPFChart chart = xdoc.createChart(XDDFChart.DEFAULT_WIDTH * 10,  XDDFChart.DEFAULT_HEIGHT * 15);
                      setBarData(chart, "", series.toArray(new String[]{}), categories, values1,values2);
                      xdoc.removeBodyElement(xdoc.getBodyElements().size()-1);
                      itPara = xdoc.getParagraphsIterator();
                      XWPFRun newRun = newpa3.createRun();
                      String relationId = xdoc.getRelationId(chart);
                      java.lang.reflect.Method attach = XWPFChart.class.getDeclaredMethod("attach", String.class, XWPFRun.class);
                      attach.setAccessible(true);
                      attach.invoke(chart, relationId, newRun);
                      chart.setChartWidth(XDDFChart.DEFAULT_WIDTH * 10);
                      chart.setChartHeight(XDDFChart.DEFAULT_HEIGHT * 8);
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
                              run6.setText("    问题类型:" + questLabel);
                              run6.addBreak();
                              XWPFRun run5 = newpa3.createRun();
                              String answer="";
                              if (StringUtils.isNotEmpty( questionList.get(d).getQuestion())){
                                  answer="已回复";
                              }
                              run5.setText("    回复情况:" + answer);
                              run5.addBreak();
                              XWPFRun run7= newpa3.createRun();
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
                      newParaRun.setFontFamily("宋体");
                      newParaRun.setFontSize(10);
                      newParaRun.setBold(true);
                      newParaRun.setText("("+sort+")"+isNull(supplierMainList.get(z).getTitle()));
                      newpa1.setStyle("4");//标题三
                      newpa1.setSpacingBefore(13);
                      newpa1.setSpacingAfter(13);
                      //表格
                      SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
                      XmlCursor cursor2=newpa1.getCTP().newCursor();
                      cursor2.toNextSibling();
                      XWPFParagraph newpa2 = xdoc.insertNewParagraph(cursor2);
                      itPara = xdoc.getParagraphsIterator();
                      XWPFTable table =xdoc.insertNewTbl(newpa2.getCTP().newCursor());
                      XWPFTableRow row_0 = table.getRow(0);
                      row_0.getCell(0).setText("");
                      row_0.addNewTableCell().setText("");
                      row_0.addNewTableCell().setText("");
                      setTitleBold(2,row_0,df1.format(supplierMainList.get(z).getReportPeriod()),8,true);
                      row_0.addNewTableCell().setText("");
                      row_0.addNewTableCell().setText("");
                      row_0.addNewTableCell();
                      setTitleBold(5,row_0,supplierMainList.get(z).getThirdYearForSupplier(),8,true);
                      row_0.addNewTableCell().setText("");
                      row_0.addNewTableCell().setText("");
                      row_0.addNewTableCell();
                      setTitleBold(8,row_0,supplierMainList.get(z).getSecondYearForSupplier(),8,true);
                      row_0.addNewTableCell().setText("");
                      row_0.addNewTableCell().setText("");
                      row_0.addNewTableCell();
                      setTitleBold(11,row_0,supplierMainList.get(z).getFirstYearForSupplier(),8,true);
                      row_0.addNewTableCell().setText("");
                      row_0.addNewTableCell().setText("");
                      for (int p=0;p<14;p++){
                          row_0.getCell(p).setColor("E7F3FF");
                      }
                      XWPFTableRow row_1 = table.createRow();
                      setTitleBold(0,row_1,"序号",8,true);
                      setTitleBold(1,row_1,"公司",8,true);
                      setTitleBold(2,row_1,"采购内容",8,true);
                      setTitleBold(3,row_1,"金额",8,true);
                      setTitleBold(4,row_1,"占比",8,true);
                      setTitleBold(5,row_1,"采购内容",8,true);
                      setTitleBold(6,row_1,"金额",8,true);
                      setTitleBold(7,row_1,"占比",8,true);
                      setTitleBold(8,row_1,"采购内容",8,true);
                      setTitleBold(9,row_1,"金额",8,true);
                      setTitleBold(10,row_1,"占比",8,true);
                      setTitleBold(11,row_1,"采购内容",8,true);
                      setTitleBold(12,row_1,"金额",8,true);
                      setTitleBold(13,row_1,"占比",8,true);
                      for (int p=0;p<14;p++){
                          row_1.getCell(p).setColor("E7F3FF");
                      }
                      List<SupplierCustomerInfoDto> list=supplierMainList.get(z).getSupplierCustomerInfoList();
                      for (int n=0;n<list.size();n++){
                          XWPFTableRow row_2 = table.createRow();
                          setTitleBold(0,row_2,n+1+"",8,true);
                          setTitleBold(1,row_2,list.get(n).getCompanyName(),8,false);
                          setTitleBold(2,row_2,list.get(n).getOnePeriodContent(),8,false);
                          setTitleBold(3,row_2,twoMarkStr(list.get(n).getOnePeriodAmount()+""),8,false);
                          setTitleBold(4,row_2,twoMarkStrBFH(list.get(n).getOnePeriodRatio()+""),8,false);
                          setTitleBold(5,row_2,list.get(n).getThirdYearContent(),8,false);
                          setTitleBold(6,row_2,twoMarkStr(list.get(n).getThirdYearAmount()+""),8,false);
                          setTitleBold(7,row_2,twoMarkStrBFH(list.get(n).getThirdYearRatio()+""),8,false);
                          setTitleBold(8,row_2,list.get(n).getSecondYearContent(),8,false);
                          setTitleBold(9,row_2,twoMarkStr(list.get(n).getSecondYearAmount()+""),8,false);
                          setTitleBold(10,row_2,twoMarkStrBFH(list.get(n).getSecondYearRatio()+""),8,false);
                          setTitleBold(11,row_2,list.get(n).getFirstYearContent(),8,false);
                          setTitleBold(12,row_2,twoMarkStr(list.get(n).getFirstYearAmount()+""),8,false);
                          setTitleBold(13,row_2,twoMarkStrBFH(list.get(n).getFirstYearRatio()+""),8,false);
                          if (n%2!=0){
                              for (int p=0;p<14;p++){
                                  row_2.getCell(p).setColor("E7F3FF");
                              }
                          }
                      }
                      //合并行
                      fillTable(table,1500);
                      mergeCellsHorizontal(table,0,0,1);
                      mergeCellsHorizontal(table,0,2,4);
                      mergeCellsHorizontal(table,0,5,7);
                      mergeCellsHorizontal(table,0,8,10);
                      mergeCellsHorizontal(table,0,11,13);
                      //边框颜色
                      setBorderStyle(table);
                  }
              }else {
                  XWPFRun newParaRun = paragraph.createRun();
                  newParaRun.setFontFamily("宋体");
                  newParaRun.setFontSize(10);
                  newParaRun.setBold(false);
                  newParaRun.setText("    暂无主要供应商情况");
              }


              continue;
          }
          else if("#主要客户情况#".equals(paragraph.getText())){
              test.clearParagraph(paragraph);
              if (customerMainList!=null && customerMainList.size()>0){
                  for(int z=customerMainList.size()-1;z>=0;z--){
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
                      newParaRun.setFontFamily("宋体");
                      newParaRun.setFontSize(10);
                      newParaRun.setBold(true);
                      newParaRun.setText("("+sort+")"+customerMainList.get(z).getTitle());
                      newpa1.setStyle("4");
                      newpa1.setSpacingBefore(13);
                      newpa1.setSpacingAfter(13);
                      //表格
                      XmlCursor cursor2=newpa1.getCTP().newCursor();
                      cursor2.toNextSibling();
                      XWPFParagraph newpa2 = xdoc.insertNewParagraph(cursor2);
                      itPara = xdoc.getParagraphsIterator();
                      XWPFTable table =xdoc.insertNewTbl(newpa2.getCTP().newCursor());
                      XWPFTableRow row_0 = table.getRow(0);
                      SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
                      row_0.getCell(0).setText("");
                      row_0.addNewTableCell().setText("");
                      row_0.addNewTableCell();
                      setTitleBold(2,row_0,df1.format(customerMainList.get(z).getReportPeriod()),8,true);
                      row_0.addNewTableCell().setText("");
                      row_0.addNewTableCell().setText("");
                      row_0.addNewTableCell();
                      setTitleBold(5,row_0,customerMainList.get(z).getThirdYearForCustomer(),8,true);
                      row_0.addNewTableCell().setText("");
                      row_0.addNewTableCell().setText("");
                      row_0.addNewTableCell();
                      setTitleBold(8,row_0,customerMainList.get(z).getSecondYearForCustomer(),8,true);
                      row_0.addNewTableCell().setText("");
                      row_0.addNewTableCell().setText("");
                      row_0.addNewTableCell();
                      setTitleBold(11,row_0,customerMainList.get(z).getFirstYearForCustomer(),8,true);
                      row_0.addNewTableCell().setText("");
                      row_0.addNewTableCell().setText("");

                      for (int p=0;p<14;p++){
                          row_0.getCell(p).setColor("E7F3FF");
                      }
//                  row_0.addNewTableCell().setText(customerMainList.get(z).get);
//                  row_0.addNewTableCell().setText("");
//                  row_0.addNewTableCell().setText("");
                      XWPFTableRow row_1 = table.createRow();
                      setTitleBold(0,row_1,"序号",8,true);
                      setTitleBold(1,row_1,"公司",8,true);
                      setTitleBold(2,row_1,"交易情况",8,true);
                      setTitleBold(3,row_1,"金额",8,true);
                      setTitleBold(4,row_1,"占比",8,true);
                      setTitleBold(5,row_1,"交易情况",8,true);
                      setTitleBold(6,row_1,"金额",8,true);
                      setTitleBold(7,row_1,"占比",8,true);
                      setTitleBold(8,row_1,"交易情况",8,true);
                      setTitleBold(9,row_1,"金额",8,true);
                      setTitleBold(10,row_1,"占比",8,true);
                      setTitleBold(11,row_1,"交易情况",8,true);
                      setTitleBold(12,row_1,"金额",8,true);
                      setTitleBold(13,row_1,"占比",8,true);

                      for (int p=0;p<14;p++){
                          row_1.getCell(p).setColor("E7F3FF");
                      }
                      List<SupplierCustomerInfoDto> list=customerMainList.get(z).getSupplierCustomerInfoList();
                      for (int n=0;n<list.size();n++){
                          XWPFTableRow row_2 = table.createRow();
                          setTitleBold(0,row_2,n+1+"",8,true);
                          setTitleBold(1,row_2,list.get(n).getCompanyName(),8,false);
                          setTitleBold(2,row_2,list.get(n).getOnePeriodContent(),8,false);
                          setTitleBold(3,row_2,twoMarkStr(list.get(n).getOnePeriodAmount()+""),8,false);
                          setTitleBold(4,row_2,twoMarkStr(list.get(n).getOnePeriodRatio()+"")+"%",8,false);
                          setTitleBold(5,row_2,list.get(n).getThirdYearContent(),8,false);
                          setTitleBold(6,row_2,twoMarkStr(list.get(n).getThirdYearAmount()+""),8,false);
                          setTitleBold(7,row_2,twoMarkStr(list.get(n).getThirdYearRatio()+"")+"%",8,false);
                          setTitleBold(8,row_2,list.get(n).getSecondYearContent(),8,false);
                          setTitleBold(9,row_2,twoMarkStr(list.get(n).getSecondYearAmount()+""),8,false);
                          setTitleBold(10,row_2,twoMarkStr(list.get(n).getSecondYearRatio()+"")+"%",8,false);
                          setTitleBold(11,row_2,list.get(n).getFirstYearContent(),8,false);
                          setTitleBold(12,row_2,twoMarkStr(list.get(n).getFirstYearAmount()+""),8,false);
                          setTitleBold(13,row_2,twoMarkStr(list.get(n).getFirstYearRatio()+"")+"%",8,false);
                          if (n%2!=0){
                              for (int p=0;p<14;p++){
                                  row_2.getCell(p).setColor("E7F3FF");
                              }
                          }

                      }
                      //合并行
                      fillTable(table,1500);
                      mergeCellsHorizontal(table,0,0,1);
                      mergeCellsHorizontal(table,0,2,4);
                      mergeCellsHorizontal(table,0,5,7);
                      mergeCellsHorizontal(table,0,8,10);
                      mergeCellsHorizontal(table,0,11,13);
                      //边框颜色
                      setBorderStyle(table);
                  }
              }else {
                      XWPFRun newParaRun = paragraph.createRun();
                      newParaRun.setFontFamily("微软雅黑");
                      newParaRun.setFontSize(10);
                      newParaRun.setBold(false);
                      newParaRun.setText("    暂无主要客户情况");
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
                      //标题
                      XWPFRun newParaRun = newpa1.createRun();
                      newParaRun.setFontFamily("宋体");
                      newParaRun.setFontSize(10);
                      newParaRun.setBold(true);
                      newParaRun.setText("("+sort+")"+isNull(industryCompareList.get(z).getTitle()));
                      newpa1.setStyle("4");
                      newpa1.setSpacingBefore(13);
                      newpa1.setSpacingAfter(13);
                      newpa1.createRun();
                      //折线图
                      List<String> series = new ArrayList<>();
                      List<String> listLanguages = new ArrayList<>();
                      listLanguages.add(industryCompareList.get(z).getFirstYear());
                      listLanguages.add(industryCompareList.get(z).getSecondYear());
                      listLanguages.add(industryCompareList.get(z).getThirdYear());
                      List<Double> listCountries = new ArrayList<>();
                      List<Double> listCountries2 = new ArrayList<>(10);
                      List<Double> listCountries3 = new ArrayList<>(10);
                      for (int u=list.size()-1;u>=0;u--){
                          listCountries.add(Double.valueOf(toChangeNum(list.get(u).getThirdYearRate()+"")));
                          listCountries2.add(Double.valueOf(toChangeNum(list.get(u).getSecondYearRate()+"")));
                          listCountries3.add(Double.valueOf(toChangeNum(list.get(u).getFirstYearRate()+"")));
                          series.add(list.get(u).getCompanyName());
                      }
                      List<List<Double>> yValues = Lists.newArrayList();
                      yValues.add(listCountries3);
                      yValues.add(listCountries2);
                      yValues.add(listCountries);


                      List<List<Double>> zhuziList = Lists.newArrayList();
                      int len=yValues.get(0).size();
                      for (int k=0;k<len;k++){
                          List<Double> zhuzi = new ArrayList<>();
                          for (int p=0;p<yValues.size();p++){
                              zhuzi.add(yValues.get(p).get(k));
                          }
                          zhuziList.add(zhuzi);
                      }
                      XWPFChart chart1 = xdoc.createChart(XDDFChart.DEFAULT_WIDTH * 8, XDDFChart.DEFAULT_HEIGHT * 10);
                      poiWordInsertChartUtils.setSimpleBarData(chart1,"3","","",series,listLanguages,zhuziList);
                      xdoc.removeBodyElement(xdoc.getBodyElements().size()-1);
                      itPara = xdoc.getParagraphsIterator();
                      XmlCursor cursor4 = newpa1.getCTP().newCursor();
                      cursor4.toNextSibling();
                      XWPFParagraph newpa4 = xdoc.insertNewParagraph(cursor4);
                      XWPFRun newRun1 = newpa4.createRun();
                      String relationId1 = xdoc.getRelationId(chart1);
                      java.lang.reflect.Method attach1 = XWPFChart.class.getDeclaredMethod("attach", String.class, XWPFRun.class);
                      attach1.setAccessible(true);
                      attach1.invoke(chart1, relationId1, newRun1);
                      chart1.setChartWidth(XDDFChart.DEFAULT_WIDTH * 10);
                      chart1.setChartHeight(XDDFChart.DEFAULT_HEIGHT * 8);
                      newRun1.addBreak();
                      //表格
                      XmlCursor cursor2=newpa4.getCTP().newCursor();
                      cursor2.toNextSibling();
                      XWPFParagraph newpa2 = xdoc.insertNewParagraph(cursor2);
                      itPara = xdoc.getParagraphsIterator();
                      XWPFTable table =xdoc.insertNewTbl(newpa2.getCTP().newCursor());
                      XWPFTableRow row_0 = table.getRow(0);
                      SimpleDateFormat df1 = new SimpleDateFormat("yyyy年");
                      row_0.getCell(0).setText("企业名称");
                      row_0.addNewTableCell();
                      setTitleBold(1,row_0,industryCompareList.get(z).getThirdYear(),8,true);
                      row_0.addNewTableCell();
                      setTitleBold(2,row_0,industryCompareList.get(z).getSecondYear(),8,true);
                      row_0.addNewTableCell();
                      setTitleBold(3,row_0,industryCompareList.get(z).getFirstYear(),8,true);
                      for (int p=0;p<4;p++){
                          row_0.getCell(p).setColor("E7F3FF");
                      }
                      for (int n=0;n<list.size();n++){
                          XWPFTableRow row_1 = table.createRow();
                          setTitleBold(0,row_1,list.get(n).getCompanyName(),8,true);
                          setTitleBold(1,row_1,twoMarkStr(list.get(n).getThirdYearRate()+""),8,false);
                          setTitleBold(2,row_1,twoMarkStr(list.get(n).getSecondYearRate()+""),8,false);
                          setTitleBold(3,row_1,twoMarkStr(list.get(n).getFirstYearRate()+""),8,false);
                          if (n%2!=0){
                              for (int p=0;p<4;p++){
                                  row_1.getCell(p).setColor("E7F3FF");
                              }
                          }
                      }
                      fillTable(table,2000);
                      //边框颜色
                      setBorderStyle(table);
                  }
              }else {
                  XWPFRun newParaRun = paragraph.createRun();
                  newParaRun.setFontFamily("宋体");
                  newParaRun.setFontSize(10);
                  newParaRun.setBold(false);
                  newParaRun.setText("    暂无毛利率对比情况");
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
                  if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#公司简称#") != -1) {
                      test.replaceTableCell(td,wordMap.get("#公司简称#"));
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#公司名字#") != -1) {
                      test.replaceTableCell(td,wordMap.get("#公司名字#"));
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#证券代码#") != -1) {
                      test.replaceTableCell(td,wordMap.get("#证券代码#"));
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#拟上市板块#") != -1) {
                      test.replaceTableCell(td,wordMap.get("#拟上市板块#"));
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#企业性质#") != -1) {
                      test.replaceTableCell(td,wordMap.get("#企业性质#"));
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#注册地址#") != -1) {
                      test.replaceTableCell(td,wordMap.get("#注册地址#"));
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#注册资本#") != -1) {
                      test.replaceTableCell(td,wordMap.get("#注册资本#"));
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#战略新兴行业#") != -1) {
                      test.replaceTableCell(td,wordMap.get("#战略新兴行业#"));
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#证监会行业#") != -1) {
                      test.replaceTableCell(td,wordMap.get("#证监会行业#"));
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#实际控制人#") != -1) {
                      test.replaceTableCell(td,wordMap.get("#实际控制人#"));
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#控股股东#") != -1) {
                      test.replaceTableCell(td,wordMap.get("#控股股东#"));
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#配售机制#") != -1) {
                      test.replaceTableCell(td,wordMap.get("#配售机制#"));
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#上市标准#") != -1) {
                      test.replaceTableCell(td,wordMap.get("#上市标准#"));
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#主营业务#") != -1) {
                      test.replaceTableCell(td,wordMap.get("#主营业务#"));
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
                          test.replaceTableCell(td,twoMarkStr(issueData.getParValue()+"")+"元/股");
                      }else {
                          test.replaceTableCell(td,"--");
                      }
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#发行价格#") != -1) {
                      if(issueData!=null){
                          test.replaceTableCell(td,twoMarkStr(issueData.getIssuePrice()+"")+"元/股");
                      }else {
                          test.replaceTableCell(td,"--");
                      }
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#发行前总股本#") != -1) {
                      if(issueData!=null){
                          test.replaceTableCell(td,twoMarkStr(issueData.getPreIssueNum()+"")+"万股");
                      }else {
                          test.replaceTableCell(td,"--");
                      }
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#发行后总股本#") != -1) {
                      if(issueData!=null){
                          test.replaceTableCell(td,twoMarkStr(issueData.getNextIssueNum()+"")+"万股");
                      }else {
                          test.replaceTableCell(td,"--");
                      }
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#发行数量总计#") != -1) {
                      if(issueData!=null){
                          test.replaceTableCell(td,twoMarkStr(issueData.getShareIssued()+"")+"万股");
                      }else {
                          test.replaceTableCell(td,"--");
                      }
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#募集资金总额#") != -1) {
                      if(issueData!=null){
                          test.replaceTableCell(td,twoMarkStr(issueData.getSumFina()+"")+"万元");
                      }else {
                          test.replaceTableCell(td,"--");
                      }
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#占发行后总股本的比例#") != -1) {
                      if(issueData!=null){
                          test.replaceTableCell(td,twoMarkStr(issueData.getIssuedRatio()+"")+"%");
                      }else {
                          test.replaceTableCell(td,"--");
                      }
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#募集资金净额#") != -1) {
                      if(issueData!=null){
                          test.replaceTableCell(td,twoMarkStr(issueData.getNetSumFina()+"")+"万元");
                      }else {
                          test.replaceTableCell(td,"--");
                      }
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#网上发行数量#") != -1) {
                      if(issueData!=null){
                          test.replaceTableCell(td,twoMarkStr(issueData.getShareIssueOn()+"")+"万股");
                      }else {
                          test.replaceTableCell(td,"--");
                      }
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#网下配售数量#") != -1) {
                      if(issueData!=null){
                          test.replaceTableCell(td,twoMarkStr(issueData.getShareIssued()+"")+"万股");
                      }else {
                          test.replaceTableCell(td,"--");
                      }
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#战略配售数量#") != -1) {
                      if(issueData!=null){
                          test.replaceTableCell(td,twoMarkStr(issueData.getSharesTrategicPlace()+""));
                      }else {
                          test.replaceTableCell(td,"--");
                      }
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#超额配售数量#") != -1) {
                      if(issueData!=null){
                          test.replaceTableCell(td,twoMarkStr(issueData.getShareOverPlace()+""));
                      }else {
                          test.replaceTableCell(td,"--");
                      }
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#老股东公开发售股份#") != -1) {
                      if(issueData!=null){
                          test.replaceTableCell(td,twoMarkStr(issueData.getExseNumBse()+""));
                      }else {
                          test.replaceTableCell(td,"--");
                      }
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#发行后市盈率#") != -1) {
                      if(issueData!=null){
                          test.replaceTableCell(td,twoMarkStr(issueData.getPeIssueA()+"")+" 倍");
                      }else {
                          test.replaceTableCell(td,"--");
                      }
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#发行前每股收益#") != -1) {
                      if(issueData!=null){
                          test.replaceTableCell(td,twoMarkStr(issueData.getEpsIssueB()+""));
                      }else {
                          test.replaceTableCell(td,"--");
                      }
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#发行后每股收益#") != -1) {
                      if(issueData!=null){
                          test.replaceTableCell(td,twoMarkStr(issueData.getEpsIssueA()+""));
                      }else {
                          test.replaceTableCell(td,"--");
                      }
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#发行方式#") != -1) {
                      if(issueData!=null){
                          test.replaceTableCell(td,issueData.getIssueMethod()+"");
                      }else {
                          test.replaceTableCell(td,"--");
                      }
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#承销方式#") != -1) {
                      if(issueData!=null){
                          test.replaceTableCell(td,issueData.getUwMethod()+"");
                      }else {
                          test.replaceTableCell(td,"--");
                      }
                  }
                  else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#主营业务表格#") != -1) {
                      if (j + 8 < cellList.size()) {
                          int no = rowCnt+2;
                          if (mainIncomeVo != null && mainIncomeVo.getMainIncomeInfoList() != null){
                              test.setCellNewContentTitleNotTemp(table, 0, 1, mainIncomeVo.getOnePeriodForIncome(), i);
                              test.setCellNewContentTitleNotTemp(table, 0, 2, mainIncomeVo.getThirdYearForIncome(), i);
                              test.setCellNewContentTitleNotTemp(table, 0, 3, mainIncomeVo.getSecondYearForIncome(), i);
                              test.setCellNewContentTitleNotTemp(table, 0, 4, mainIncomeVo.getFirstYearForIncome(), i);
                              for (int k = 0; k < mainIncomeVo.getMainIncomeInfoList().size(); k++) {
                                  if (no >= table.getNumberOfRows()) {
                                      test.insertTableRowAtIndex(table, no);
                                  }
                                  if ("合计".equals(mainIncomeVo.getMainIncomeInfoList().get(k).getBusinessName())){
                                      test.setCellNewContentBold(table, no, 0, isNull(mainIncomeVo.getMainIncomeInfoList().get(k).getBusinessName()+""), i);
                                      test.setCellNewContentBold(table, no, 1, isNull(mainIncomeVo.getMainIncomeInfoList().get(k).getOnePeriodAmount()+""), i);
                                      test.setCellNewContentBold(table, no, 2, isNullBFH(mainIncomeVo.getMainIncomeInfoList().get(k).getOnePeriodRatio()+""), i);
                                      test.setCellNewContentBold(table, no, 3, isNull(mainIncomeVo.getMainIncomeInfoList().get(k).getThirdYearAmount()+""), i);
                                      test.setCellNewContentBold(table, no, 4, isNullBFH(mainIncomeVo.getMainIncomeInfoList().get(k).getThirdYearRatio()+""), i);
                                      test.setCellNewContentBold(table, no, 5, isNull(mainIncomeVo.getMainIncomeInfoList().get(k).getSecondYearAmount()+""), i);
                                      test.setCellNewContentBold(table, no, 6, isNullBFH(mainIncomeVo.getMainIncomeInfoList().get(k).getSecondYearRatio()+""), i);
                                      test.setCellNewContentBold(table, no, 7, isNull(mainIncomeVo.getMainIncomeInfoList().get(k).getFirstYearAmount()+""), i);
                                      test.setCellNewContentBold(table, no, 8, isNullBFH(mainIncomeVo.getMainIncomeInfoList().get(k).getFirstYearRatio()+""), i);
                                  }else {
                                      test.setCellNewContentBold(table, no, 0, isNull(mainIncomeVo.getMainIncomeInfoList().get(k).getBusinessName()+""), i);
                                      test.setCellNewContent(table, no, 1, isNull(mainIncomeVo.getMainIncomeInfoList().get(k).getOnePeriodAmount()+""), i);
                                      test.setCellNewContent(table, no, 2, isNullBFH(mainIncomeVo.getMainIncomeInfoList().get(k).getOnePeriodRatio()+""), i);
                                      test.setCellNewContent(table, no, 3, isNull(mainIncomeVo.getMainIncomeInfoList().get(k).getThirdYearAmount()+""), i);
                                      test.setCellNewContent(table, no, 4, isNullBFH(mainIncomeVo.getMainIncomeInfoList().get(k).getThirdYearRatio()+""), i);
                                      test.setCellNewContent(table, no, 5, isNull(mainIncomeVo.getMainIncomeInfoList().get(k).getSecondYearAmount()+""), i);
                                      test.setCellNewContent(table, no, 6, isNullBFH(mainIncomeVo.getMainIncomeInfoList().get(k).getSecondYearRatio()+""), i);
                                      test.setCellNewContent(table, no, 7, isNull(mainIncomeVo.getMainIncomeInfoList().get(k).getFirstYearAmount()+""), i);
                                      test.setCellNewContent(table, no, 8, isNullBFH(mainIncomeVo.getMainIncomeInfoList().get(k).getFirstYearRatio()+""), i);
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
                              test.setCellNewContent(table, 1, 1, isNull(ipoTechnology.getPatentData().get(0).getFm()+""), i);
                              test.setCellNewContent(table, 1, 2, isNull(ipoTechnology.getPatentData().get(0).getSy()+""), i);
                              test.setCellNewContent(table, 1, 3, isNull(ipoTechnology.getPatentData().get(0).getWg()+""), i);
                              test.setCellNewContent(table, 1, 4, isNull(ipoTechnology.getPatentData().get(0).getGw()+""), i);
                              test.setCellNewContent(table, 1, 5, isNull(ipoTechnology.getPatentData().get(0).getHj()+""), i);
                              test.setCellNewContent(table, 2, 1, twoMarkStr(ipoTechnology.getPatentData().get(1).getFm()+"")+"%", i);
                              test.setCellNewContent(table, 2, 2, twoMarkStr(ipoTechnology.getPatentData().get(1).getSy()+"")+"%", i);
                              test.setCellNewContent(table, 2, 3, twoMarkStr(ipoTechnology.getPatentData().get(1).getWg()+"")+"%", i);
                              test.setCellNewContent(table, 2, 4, twoMarkStr(ipoTechnology.getPatentData().get(1).getGw()+"")+"%", i);
                              test.setCellNewContent(table, 2, 5, twoMarkStr(ipoTechnology.getPatentData().get(1).getHj()+"")+"%", i);
                              break lableA;
                          }else {
                              test.setCellNewContent(table, 1, 1, "--", i);
                              test.setCellNewContent(table, 1, 2,"--", i);
                              test.setCellNewContent(table, 1, 3, "--", i);
                              test.setCellNewContent(table, 1, 4, "--", i);
                              test.setCellNewContent(table, 1, 5,"--", i);
                              test.setCellNewContent(table, 2, 1, "--", i);
                              test.setCellNewContent(table, 2, 2, "--", i);
                              test.setCellNewContent(table, 2, 3, "--", i);
                              test.setCellNewContent(table, 2, 4, "--", i);
                              test.setCellNewContent(table, 2, 5,"--", i);
                              break lableA;
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
                                  test.setCellNewContentNotTemp(table, no, 1, isNull(ipoTechnology.getDevData().get(k).getFirstYearValue()+""), i);
                                  test.setCellNewContentNotTemp(table, no, 2, isNull(ipoTechnology.getDevData().get(k).getSecondYearValue()+""), i);
                                  test.setCellNewContentNotTemp(table, no, 3, isNull(ipoTechnology.getDevData().get(k).getThirdYearValue()+""), i);
                                  test.setCellNewContentNotTemp(table, no, 4, isNull(ipoTechnology.getDevData().get(k).getForthYearValue()+""), i);
                                  test.setCellNewContentNotTemp(table, no, 5, isNull(ipoTechnology.getDevData().get(k).getAllNumValue()+""), i);
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
                              test.setCellNewContentTitleNotTemp(table, 0, 2, ipoTechnology.getCoreDate().getSecondYearDate(), i);
                              test.setCellNewContentTitleNotTemp(table, 0, 3, ipoTechnology.getCoreDate().getThirdYearDate(), i);
                              test.setCellNewContentTitleNotTemp(table, 0, 4, ipoTechnology.getCoreDate().getForthYearDate(), i);
                          }
                          if (ipoTechnology != null && ipoTechnology.getCoreData() != null && ipoTechnology.getCoreData().size()>0){
                              for (int k = 0; k < ipoTechnology.getCoreData().size(); k++) {
                                  test.setCellNewContentNotTemp(table, no, 1, isNull(ipoTechnology.getCoreData().get(k).getFirstYearValue()+""), i);
                                  test.setCellNewContentNotTemp(table, no, 2, isNull(ipoTechnology.getCoreData().get(k).getSecondYearValue()+""), i);
                                  test.setCellNewContentNotTemp(table, no, 3, isNull(ipoTechnology.getCoreData().get(k).getThirdYearValue()+""), i);
                                  test.setCellNewContentNotTemp(table, no, 4, isNull(ipoTechnology.getCoreData().get(k).getForthYearValue()+""), i);
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
                              test.setCellNewContentTitleNotTemp(table, 0, 1, "--", i);
                              test.setCellNewContentTitleNotTemp(table, 0, 2, "--", i);
                              test.setCellNewContentTitleNotTemp(table, 0, 3, "--", i);
                              test.setCellNewContentTitleNotTemp(table, 0, 4, "--", i);
                              for (int k = 0; k < 12; k++) {
                                  test.setCellNewContentNotTemp(table, no, 1, "--", i);
                                  test.setCellNewContentNotTemp(table, no, 2, "--", i);
                                  test.setCellNewContentNotTemp(table, no, 3, "--", i);
                                  test.setCellNewContentNotTemp(table, no, 4, "--", i);
                                  no++;
                              }
                              break lableA;
                          }
                      }
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#资产负债情况表格#") != -1) {
                      if (j + 3 < cellList.size()) {
                          int noOther = rowCnt+1;
                          if (assetLiability != null && ipoFinance.getIpoFinanceOverList() != null) {
                              List<IpoItemDto> tb = new ArrayList<>();
                              tb.addAll(assetLiability.getIpoAssetItemList());
                              tb.addAll(assetLiability.getIpoDebtItemList());
                              tb.addAll(assetLiability.getIpoEquityItemList());
                              test.setCellNewContentTitleNotTemp(table, 0, 1, assetLiability.getDateList().getForthYearDate(), i);
                              test.setCellNewContentTitleNotTemp(table, 0, 2, assetLiability.getDateList().getThirdYearDate(), i);
                              test.setCellNewContentTitleNotTemp(table, 0, 3, assetLiability.getDateList().getSecondYearDate(), i);
                              test.setCellNewContentTitleNotTemp(table, 0, 4, assetLiability.getDateList().getFirstYearDate(), i);
                              for (int n = 1; n < table.getNumberOfRows(); n++) {
                                  XWPFTableRow asstr = table.getRow(n);
                                  List<XWPFTableCell> assCellList = asstr.getTableCells();
                                  int no = n;
                                  XWPFTableCell asstd = assCellList.get(0);// 取得单元格
                                  for (int z=0;z<tb.size();z++){
                                      test.setCellNewContentNotTemp(table, no, 1, "", i);
                                      test.setCellNewContentNotTemp(table, no, 2, "", i);
                                      test.setCellNewContentNotTemp(table, no, 3, "", i);
                                      test.setCellNewContentNotTemp(table, no, 4, "", i);
                                      if (tb.get(z).getItemName().equals(asstd.getText())){
                                          test.setCellNewContentNotTemp(table, no, 1, twoMarkThStr(tb.get(z).getForthYearValue() + ""), i);
                                          test.setCellNewContentNotTemp(table, no, 2, twoMarkThStr(tb.get(z).getThirdYearValue() + ""), i);
                                          test.setCellNewContentNotTemp(table, no, 3, twoMarkThStr(tb.get(z).getSecondYearValue() + ""), i);
                                          test.setCellNewContentNotTemp(table, no, 4, twoMarkThStr(tb.get(z).getFirstYearValue() + ""), i);
                                          break;
                                      }
                                  }
                              }
                          }else {
                              test.setCellNewContentNotTemp(table, 0, 1, "--", i);
                              test.setCellNewContentNotTemp(table, 0, 2, "--", i);
                              test.setCellNewContentNotTemp(table, 0, 3, "--", i);
                              test.setCellNewContentNotTemp(table, 0, 4, "--", i);
                              for (int z=0;z<22;z++){
                                  test.setCellNewContentNotTemp(table, noOther, 1,"--", i);
                                  test.setCellNewContentNotTemp(table, noOther, 2, "--", i);
                                  test.setCellNewContentNotTemp(table, noOther, 3,"--", i);
                                  test.setCellNewContentNotTemp(table, noOther, 4,"--", i);
                                  noOther++;
                              }
                              break lableA;
                          }
                      }
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#财务指标表格#") != -1) {
                      if (j + 3 < cellList.size()) {
                          int no = rowCnt+1;
                          if (financialIndex != null && financialIndex.getIpoMainIndexList() != null){
                              List<IpoItemDto> tb = financialIndex.getIpoMainIndexList();
                              test.setCellNewContentTitleNotTemp(table, 0, 1, financialIndex.getDateList().getForthYearDate(), i);
                              test.setCellNewContentTitleNotTemp(table, 0, 2, financialIndex.getDateList().getThirdYearDate(), i);
                              test.setCellNewContentTitleNotTemp(table, 0, 3, financialIndex.getDateList().getSecondYearDate(), i);
                              test.setCellNewContentTitleNotTemp(table, 0, 4, financialIndex.getDateList().getFirstYearDate(), i);
                              for (int k = 0; k < tb.size(); k++) {
                                  if (table.getRow(no)!=null && table.getRow(no).getCell(0).getText().indexOf(tb.get(k).getItemName())!=-1){
                                      test.setCellNewContentNotTemp(table, no, 1, twoMarkStr(tb.get(k).getForthYearValue()+""), i);
                                      test.setCellNewContentNotTemp(table, no, 2, twoMarkStr(tb.get(k).getThirdYearValue()+""), i);
                                      test.setCellNewContentNotTemp(table, no, 3, twoMarkStr(tb.get(k).getSecondYearValue()+""), i);
                                      test.setCellNewContentNotTemp(table, no, 4, twoMarkStr(tb.get(k).getFirstYearValue()+""), i);
                                      no++;
                                  }
                              }
                              break lableA;
                          }else {
                              test.setCellNewContentTitleNotTemp(table, 0, 1, "--", i);
                              test.setCellNewContentTitleNotTemp(table, 0, 2, "--", i);
                              test.setCellNewContentTitleNotTemp(table, 0, 3, "--", i);
                              test.setCellNewContentTitleNotTemp(table, 0, 4,"--", i);

                              for (int k = 0; k < 8; k++) {
                                  test.setCellNewContentNotTemp(table, no, 1, "--", i);
                                  test.setCellNewContentNotTemp(table, no, 2, "--", i);
                                  test.setCellNewContentNotTemp(table, no, 3, "--", i);
                                  test.setCellNewContentNotTemp(table, no, 4, "--", i);
                                  no++;
                              }
                              break lableA;
                          }
                      }
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#收入与利润情况表格#") != -1) {
                      if (j + 3 < cellList.size()) {
                          if (incomeProfit != null) {
                              List<IpoItemDto> tb = new ArrayList<>();
                              if (incomeProfit.getIpoProfitItemList()!=null){
                                  tb.addAll(incomeProfit.getIpoProfitItemList());
                              }
                              if (incomeProfit.getIpoCostItemList()!=null){
                                  tb.addAll(incomeProfit.getIpoCostItemList());
                              }
                              if (incomeProfit.getIpoReturnOverList()!=null){
                                  tb.addAll(incomeProfit.getIpoReturnOverList());
                              }
                              if (incomeProfit.getDateList()!=null){
                                  test.setCellNewContentTitleNotTemp(table, 0, 1, incomeProfit.getDateList().getForthYearDate(), i);
                                  test.setCellNewContentTitleNotTemp(table, 0, 2, incomeProfit.getDateList().getThirdYearDate(), i);
                                  test.setCellNewContentTitleNotTemp(table, 0, 3, incomeProfit.getDateList().getSecondYearDate(), i);
                                  test.setCellNewContentTitleNotTemp(table, 0, 4, incomeProfit.getDateList().getFirstYearDate(), i);
                              }
                             for (int n = 1; n < table.getNumberOfRows(); n++) {
                                  XWPFTableRow asstr = table.getRow(n);
                                  List<XWPFTableCell> assCellList = asstr.getTableCells();
                                  int no = n;
                                  XWPFTableCell asstd = assCellList.get(0);// 取得单元格
                                 if (tb.size()>0) {
                                     for (int z = 0; z < tb.size(); z++) {
                                         test.setCellNewContentNotTemp(table, no, 1, "", i);
                                         test.setCellNewContentNotTemp(table, no, 2, "", i);
                                         test.setCellNewContentNotTemp(table, no, 3, "", i);
                                         test.setCellNewContentNotTemp(table, no, 4, "", i);
                                         if (tb.get(z).getItemName().equals(asstd.getText())) {
                                             test.setCellNewContentNotTemp(table, no, 1, twoMarkThStr(tb.get(z).getForthYearValue() + ""), i);
                                             test.setCellNewContentNotTemp(table, no, 2, twoMarkThStr(tb.get(z).getThirdYearValue() + ""), i);
                                             test.setCellNewContentNotTemp(table, no, 3, twoMarkThStr(tb.get(z).getSecondYearValue() + ""), i);
                                             test.setCellNewContentNotTemp(table, no, 4, twoMarkThStr(tb.get(z).getFirstYearValue() + ""), i);
                                             break;
                                         }
                                     }
                                 }else{
                                     test.setCellNewContentNotTemp(table, 0, 1, "--", i);
                                     test.setCellNewContentNotTemp(table, 0, 2, "--", i);
                                     test.setCellNewContentNotTemp(table, 0, 3, "--", i);
                                     test.setCellNewContentNotTemp(table, 0, 4, "--", i);
                                      for (int z=0;z<16;z++){
                                          test.setCellNewContentNotTemp(table, no, 1, "--", i);
                                          test.setCellNewContentNotTemp(table, no, 2, "--", i);
                                          test.setCellNewContentNotTemp(table, no, 3, "--", i);
                                          test.setCellNewContentNotTemp(table, no, 4, "--", i);
                                      }
                                  }
                              }
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
                                  if (StringUtils.isNotEmpty(ipoInvestItem.get(k).getItemTypeStr())){
                                      test.setCellNewContentBold(table, no, 1,ipoInvestItem.get(k).getItemTypeStr(), i);
                                  }else {
                                      test.setCellNewContentBold(table, no, 1,"--", i);
                                  }
                                  test.setCellNewContentBold(table, no, 2, twoMarkStr(ipoInvestItem.get(k).getInvestTotal()+""), i);
                                  test.setCellNewContentBold(table, no, 3, twoMarkStr(ipoInvestItem.get(k).getInvestPlan()+""), i);
                                  test.setCellNewContentBold(table, no, 4, ipoInvestItem.get(k).getInvestRateStr()+"", i);
                                  test.setCellNewContentBold(table, no, 5, twoMarkStr(ipoInvestItem.get(k).getInvestPre()+""), i);
                              }else {
                                  test.setCellNewContentBold(table, no, 0, ipoInvestItem.get(k).getItemName(), i);
                                  if (StringUtils.isNotEmpty(ipoInvestItem.get(k).getItemTypeStr())){
                                      test.setCellNewContent(table, no, 1,ipoInvestItem.get(k).getItemTypeStr(), i);
                                  }else {
                                      test.setCellNewContent(table, no, 1,"--", i);
                                  }
                                  test.setCellNewContent(table, no, 2, twoMarkStr(ipoInvestItem.get(k).getInvestTotal()+""), i);
                                  test.setCellNewContent(table, no, 3, twoMarkStr(ipoInvestItem.get(k).getInvestPlan()+""), i);
                                  test.setCellNewContent(table, no, 4, ipoInvestItem.get(k).getInvestRateStr()+"", i);
                                  test.setCellNewContent(table, no, 5, twoMarkStr(ipoInvestItem.get(k).getInvestPre()+""), i);
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
                                  test.setCellNewContentBold(table, no, 2, issueFeeList.get(k).getFeeAmount()+"", i);
                                  test.setCellNewContentBold(table, no, 3, twoMarkStr(issueFeeList.get(k).getFeeRatio()+""), i);
                              }else {
                                  test.setCellNewContentBold(table, no, 0, no+"", i);
                                  test.setCellNewContent(table, no, 1, issueFeeList.get(k).getFeeType(), i);
                                  test.setCellNewContent(table, no, 2, issueFeeList.get(k).getFeeAmount()+"", i);
                                  test.setCellNewContent(table, no, 3, twoMarkStr(issueFeeList.get(k).getFeeRatio()+""), i);
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
                                  test.setCellNewContent(table, no, 0, no+"", i);
                                  test.setCellNewContent(table, no, 1, tb.get(k).getStrategicInvestorName(), i);
                                  test.setCellNewContent(table, no, 2, tb.get(k).getShareholderType(), i);
                                  test.setCellNewContent(table, no, 3, twoMarkStr(tb.get(k).getAllottedNumberTenThousand()+""), i);
                                  test.setCellNewContent(table, no, 4, twoMarkStr(tb.get(k).getRadio()+""), i);
                                  test.setCellNewContent(table, no, 5, twoMarkStr(tb.get(k).getAllottedAmountTenThousand()+""), i);
                                  test.setCellNewContent(table, no, 6, tb.get(k).getSalesRestrictionPeriodMonth()+"", i);
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
                                  test.setCellNewContentBold(table, no, 0, listTreeTypeProgress.get(k).get("cell0").toString(), i);
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
                              test.setCellNewContentBold(table, table.getNumberOfRows()-1, 0, "共计历时" + isNull(wordMap.get("#辅导历时#")) + "天", i);
                              test.setCellNewContentBold(table, table.getNumberOfRows()-1, 1, "共计历时" + isNull(wordMap.get("#审核历时#")) + "天", i);
                              test.setCellNewContentBold(table, table.getNumberOfRows()-1, 2, "共计历时" + isNull(wordMap.get("#发行历时#")) + "天", i);
                              break lableA;
                          }
                      }
                  }
              }
          }
      }
      ByteArrayOutputStream os = new ByteArrayOutputStream();

      xdoc.write(os);
      os.close();
      exportMap.put("inputStream",new ByteArrayInputStream(os.toByteArray()));
      return exportMap;
    }


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

    public String isNull(String str){
        if (str==null||"null".equals(str)||"".equals(str)){
            str="--";
        }
        return str;
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

    public  void fillTable(XWPFTable table,int width) {
        for (int rowIndex = 0; rowIndex < table.getNumberOfRows(); rowIndex++) {
            XWPFTableRow row = table.getRow(rowIndex);
            row.setHeight(132);
            for (int colIndex = 0; colIndex < row.getTableCells().size(); colIndex++) {
                XWPFTableCell cell = row.getCell(colIndex);
                if(rowIndex%2==0){
                    setCellText(cell, width);
                }else{
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
        headRun.setFontSize(fontSize);
        headRun.setText(str);
        headRun.setBold(f);//是否粗体
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
}

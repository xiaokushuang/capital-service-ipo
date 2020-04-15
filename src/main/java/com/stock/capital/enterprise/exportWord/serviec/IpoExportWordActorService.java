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
      wordMap.put("#公司名字#",((CompanyOverviewVo)dataMap.get("companyInformation")).getCompanyZhName());
      wordMap.put("#公司简称#",((CompanyOverviewVo)dataMap.get("companyInformation")).getCompanyName());
      wordMap.put("#证券代码#",((CompanyOverviewVo)dataMap.get("companyInformation")).getCompanyCode());
      wordMap.put("#拟上市板块#",((CompanyOverviewVo)dataMap.get("companyInformation")).getIpoPlate());
      if (StringUtils.isNotEmpty(((CompanyOverviewVo)dataMap.get("companyInformation")).getIpoPlate())){
        if (((CompanyOverviewVo)dataMap.get("companyInformation")).getIpoPlate().equals("上交所科创板")){
            wordMap.put("#板块类型#","注册制");
            wordMap.put("#问询回复/反馈意见#","问询与回复（注册制）");
        }else {
            wordMap.put("#板块类型#","核准制");
            wordMap.put("#问询回复/反馈意见#","反馈意见（核准制）");
        }
      }
      wordMap.put("#企业性质#",((CompanyOverviewVo)dataMap.get("companyInformation")).getCompanyNature());
      if (StringUtils.isEmpty(((CompanyOverviewVo)dataMap.get("companyInformation")).getAddrCountry()) ||
                ((CompanyOverviewVo)dataMap.get("companyInformation")).getAddrCountry().equals("中国")){
          wordMap.put("#注册地址#",((CompanyOverviewVo)dataMap.get("companyInformation")).getAddrProv()+((CompanyOverviewVo)dataMap.get("companyInformation")).getAddrCity()
                  +((CompanyOverviewVo)dataMap.get("companyInformation")).getAddrArea());
      }else {
          wordMap.put("#注册地址#",((CompanyOverviewVo)dataMap.get("companyInformation")).getAddrCountry());
      }
      wordMap.put("#注册资本#",((CompanyOverviewVo)dataMap.get("companyInformation")).getRegisteredAssets()+"");
      wordMap.put("#战略新兴行业#",((CompanyOverviewVo)dataMap.get("companyInformation")).getStrageticIndustries());
      wordMap.put("#证监会行业#",((CompanyOverviewVo)dataMap.get("companyInformation")).getIndustryCsrc());
      wordMap.put("#实际控制人#",((CompanyOverviewVo)dataMap.get("companyInformation")).getActualController());
      wordMap.put("#控股股东#",((CompanyOverviewVo)dataMap.get("companyInformation")).getControlShareholder());
      wordMap.put("#配售机制#",((CompanyOverviewVo)dataMap.get("companyInformation")).getPlacingMechanism());
      wordMap.put("#上市标准#",((CompanyOverviewVo)dataMap.get("companyInformation")).getIssueConditionName());
      wordMap.put("#主营业务#",((CompanyOverviewVo)dataMap.get("companyInformation")).getMajorBusinesses());
      List<OtherMarketInfoDto> otherList = (List<OtherMarketInfoDto>)dataMap.get("otherMarkInfo");
      List<IpoSplitDto> splitList = (List<IpoSplitDto>)dataMap.get("splitList");
      if ((List<IpoValuationDto>)dataMap.get("valuationList") != null && ((List<IpoValuationDto>) dataMap.get("valuationList")).size()>0){
          wordMap.put("#估值时间#",((List<IpoValuationDto>)dataMap.get("valuationList")).get(0).getValuationDate());
          wordMap.put("#估值股价#",((List<IpoValuationDto>)dataMap.get("valuationList")).get(0).getValuationPrice()+"");
          wordMap.put("#估值总股本#",((List<IpoValuationDto>)dataMap.get("valuationList")).get(0).getValuationEquity()+"");
          wordMap.put("#总估值#",((List<IpoValuationDto>)dataMap.get("valuationList")).get(0).getValuationValue()+"");
          wordMap.put("#估值详情#",((List<IpoValuationDto>)dataMap.get("valuationList")).get(0).getValuationMemo());
      }else {
          wordMap.put("#估值时间#","--");
          wordMap.put("#估值股价#","--");
          wordMap.put("#估值总股本#","--");
          wordMap.put("#总估值#","--");
          wordMap.put("#估值详情#","--");
      }
      wordMap.put("#股权结构类型#",((CompanyOverviewVo)dataMap.get("companyInformation")).getStructureLabel());
      List<IssuerIndustryStatusDto> industryStatusDtoList = (List<IssuerIndustryStatusDto>)dataMap.get("industryStatusDtoList");
      MainIncomeVo mainIncomeVo = (MainIncomeVo)dataMap.get("mainIncome");
      List<SupplierCustomerMainDto> supplierMainList = ((Map<String,List<SupplierCustomerMainDto>>)dataMap.get("supplierInformation")).get("supplierMainList");
      List<SupplierCustomerMainDto> customerMainList = ((Map<String,List<SupplierCustomerMainDto>>)dataMap.get("supplierInformation")).get("customerMainList");
      List<IpoFeedbackDto> ipoFeedbackList =  (List<IpoFeedbackDto>)dataMap.get("ipoFeedbackList");
      List<MainCompetitorInfoDto> competitorData = (List<MainCompetitorInfoDto>)dataMap.get("competitorData");
      List<IndustryCompareRateDto> industryCompareList = (List<IndustryCompareRateDto>)dataMap.get("industryCompareList");
      IpoTechnologyVo ipoTechnology = (IpoTechnologyVo)dataMap.get("ipoTechnology");
      if (ipoTechnology != null && ipoTechnology.getRemarksData() != null){
          wordMap.put("#研发投入备注#",ipoTechnology.getRemarksData().getDevRemarks());
          wordMap.put("#研发人员备注#",ipoTechnology.getRemarksData().getCoreRemarks());
      }
      IssueDataDto issueData = (IssueDataDto)dataMap.get("issueData");
      IpoFinanceDto ipoFinance = (IpoFinanceDto)dataMap.get("ipoFinance");
      IpoFinanceDto assetLiability = (IpoFinanceDto)dataMap.get("assetLiability");
      IpoFinanceDto financialIndex = (IpoFinanceDto)dataMap.get("financialIndex");
      IpoFinanceDto incomeProfit = (IpoFinanceDto)dataMap.get("incomeProfit");
      List<IpoInvestItemDto> ipoInvestItem = (List<IpoInvestItemDto>)dataMap.get("ipoInvestItem");
      if (ipoInvestItem != null && ipoInvestItem.size()>0){
          wordMap.put("#募集资金备注#",ipoInvestItem.get(0).getInvestRemark());
      }
      List<IssueFeeDto> issueFeeList = (List<IssueFeeDto>)dataMap.get("issueFeeList");
      StrategicPlacementMainDto strategicPlacementMain =(StrategicPlacementMainDto)dataMap.get("strategicPlacementMain");
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
      if (treeTypeProgress != null && treeTypeProgress.getTreeList() != null){
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
      if (mainIncomeVo != null && mainIncomeVo.getMainIncomeInfoList() != null){
          List<String> titleArr = new ArrayList<String>();
          titleArr.add("近三年主营业务趋势");
          List<String> fldNameArr = new ArrayList<String>();
          List<Map<String, String>> listItemsByType = new ArrayList<Map<String, String>>();
          Map<String, String> base1 = new HashMap<String, String>();
          fldNameArr.add("item1");
          List<String> listLanguages = new ArrayList<>();
          if (StringUtils.isNotEmpty(mainIncomeVo.getOnePeriodForIncome())){
              base1.put("item1", mainIncomeVo.getOnePeriodForIncome());
              listLanguages.add(mainIncomeVo.getOnePeriodForIncome());
          }
          Map<String, String> base2 = new HashMap<String, String>();
          if (StringUtils.isNotEmpty(mainIncomeVo.getThirdYearForIncome())){
              base2.put("item1", mainIncomeVo.getThirdYearForIncome());
              listLanguages.add(mainIncomeVo.getThirdYearForIncome());
          }
          Map<String, String> base3 = new HashMap<String, String>();
          if (StringUtils.isNotEmpty(mainIncomeVo.getSecondYearForIncome())){
              base3.put("item1", mainIncomeVo.getSecondYearForIncome());
              listLanguages.add(mainIncomeVo.getSecondYearForIncome());
          }
          Map<String, String> base4 = new HashMap<String, String>();
          if (StringUtils.isNotEmpty(mainIncomeVo.getFirstYearForIncome())){
              base4.put("item1", mainIncomeVo.getFirstYearForIncome());
              listLanguages.add(mainIncomeVo.getFirstYearForIncome());
          }

          List<Double> listCountries = new ArrayList<>();
          List<Double> listCountries2 = new ArrayList<>();
          List<Double> listCountries3 = new ArrayList<>();
          List<Double> listCountries4 = new ArrayList<>();
          for (int i=0;i<mainIncomeVo.getMainIncomeInfoList().size();i++){
              if (!mainIncomeVo.getMainIncomeInfoList().get(i).getBusinessName().equals("合计")){
                  fldNameArr.add(mainIncomeVo.getMainIncomeInfoList().get(i).getBusinessName());
                  titleArr.add(mainIncomeVo.getMainIncomeInfoList().get(i).getBusinessName());
                  if (StringUtils.isNotEmpty(mainIncomeVo.getOnePeriodForIncome())){
                      base1.put(mainIncomeVo.getMainIncomeInfoList().get(i).getBusinessName(), mainIncomeVo.getMainIncomeInfoList().get(i).getOnePeriodAmount()+"");
                      listCountries.add(Double.valueOf(mainIncomeVo.getMainIncomeInfoList().get(i).getOnePeriodAmount().toString()));
                  }
                  if (StringUtils.isNotEmpty(mainIncomeVo.getThirdYearForIncome())){
                      base2.put(mainIncomeVo.getMainIncomeInfoList().get(i).getBusinessName(), mainIncomeVo.getMainIncomeInfoList().get(i).getThirdYearAmount()+"");
                      listCountries2.add(Double.valueOf(mainIncomeVo.getMainIncomeInfoList().get(i).getThirdYearAmount().toString()));
                  }
                  if (StringUtils.isNotEmpty(mainIncomeVo.getSecondYearForIncome())){
                      base3.put(mainIncomeVo.getMainIncomeInfoList().get(i).getBusinessName(), mainIncomeVo.getMainIncomeInfoList().get(i).getSecondYearAmount()+"");
                      listCountries3.add(Double.valueOf(mainIncomeVo.getMainIncomeInfoList().get(i).getSecondYearAmount().toString()));
                  }
                  if (StringUtils.isNotEmpty(mainIncomeVo.getFirstYearForIncome())){
                      base4.put(mainIncomeVo.getMainIncomeInfoList().get(i).getBusinessName(), mainIncomeVo.getMainIncomeInfoList().get(i).getFirstYearAmount()+"");
                      listCountries4.add( Double.valueOf(mainIncomeVo.getMainIncomeInfoList().get(i).getFirstYearAmount().toString()));
                  }
              }
          }
          listItemsByType.add(base1);
          listItemsByType.add(base2);
          listItemsByType.add(base3);
          listItemsByType.add(base4);
//          POIXMLDocumentPart poixmlDocumentPart0 = chartsMap.get("/word/charts/chart1.xml");
//          new PoiWordTools().replaceBarCharts(poixmlDocumentPart0, titleArr, fldNameArr, listItemsByType);


          POIXMLDocumentPart poixmlDocumentPart1 = chartsMap.get("/word/charts/chart1.xml");
          new PoiWordTools().replacePieCharts(poixmlDocumentPart1, titleArr, fldNameArr, listItemsByType);
      }
        if (industryCompareList != null &&industryCompareList.get(0)!=null){
            IndustryCompareRateDto dto= industryCompareList.get(0);
            // 数据准备
            List<String> titleArr = new ArrayList<String>();// 标题
            titleArr.add("title");
            titleArr.add(dto.getThirdYear());
            titleArr.add(dto.getSecondYear());
            titleArr.add(dto.getFirstYear());
            List<String> fldNameArr = new ArrayList<String>();// 字段名
            fldNameArr.add("item1");
            fldNameArr.add("item2");
            fldNameArr.add("item3");
            fldNameArr.add("item4");

            // 数据集合
            List<Map<String, String>> listItemsByType = new ArrayList<Map<String, String>>();
            for (int z=0;z<dto.getIndustryCompareRateDetailList().size();z++){
                Map<String, String> base1 = new HashMap<String, String>();
                base1.put("item1", dto.getIndustryCompareRateDetailList().get(z).getCompanyName());
                base1.put("item2", dto.getIndustryCompareRateDetailList().get(z).getThirdYearRate().toString());
                base1.put("item3", dto.getIndustryCompareRateDetailList().get(z).getSecondYearRate().toString());
                base1.put("item4", dto.getIndustryCompareRateDetailList().get(z).getFirstYearRate().toString());
                listItemsByType.add(base1);
            }
            POIXMLDocumentPart poixmlDocumentPart3 = chartsMap.get("/word/charts/chart2.xml");
            new PoiWordTools().replaceLineCharts(poixmlDocumentPart3, titleArr, fldNameArr, listItemsByType);
        }
        if (industryCompareList != null &&industryCompareList.get(1)!=null){
            IndustryCompareRateDto dto= industryCompareList.get(1);
            // 数据准备
            List<String> titleArr = new ArrayList<String>();// 标题
            titleArr.add("title");
            titleArr.add(dto.getThirdYear());
            titleArr.add(dto.getSecondYear());
            titleArr.add(dto.getFirstYear());
            List<String> fldNameArr = new ArrayList<String>();// 字段名
            fldNameArr.add("item1");
            fldNameArr.add("item2");
            fldNameArr.add("item3");
            fldNameArr.add("item4");

            // 数据集合
            List<Map<String, String>> listItemsByType = new ArrayList<Map<String, String>>();
            for (int z=0;z<dto.getIndustryCompareRateDetailList().size();z++){
                Map<String, String> base1 = new HashMap<String, String>();
                base1.put("item1", dto.getIndustryCompareRateDetailList().get(z).getCompanyName());
                base1.put("item2", dto.getIndustryCompareRateDetailList().get(z).getThirdYearRate().toString());
                base1.put("item3", dto.getIndustryCompareRateDetailList().get(z).getSecondYearRate().toString());
                base1.put("item4", dto.getIndustryCompareRateDetailList().get(z).getFirstYearRate().toString());
                listItemsByType.add(base1);
            }
            POIXMLDocumentPart poixmlDocumentPart4 = chartsMap.get("/word/charts/chart3.xml");
            new PoiWordTools().replaceLineCharts(poixmlDocumentPart4, titleArr, fldNameArr, listItemsByType);


        }
//文本框
        int len=((IpoFeedbackDto)dataMap.get("ipoFeedbackDto")).getBaseList().size();
        wordMap.put("公司名称详情",((CompanyOverviewVo)dataMap.get("companyInformation")).getCompanyZhName());
//        wordMap.put("公司名称类型",((CompanyOverviewVo)dataMap.get("companyInformation")).getCompanyZhName()+"\n"+"IPO");
        wordMap.put("拟上市板块","拟上市板块:"+((CompanyOverviewVo)dataMap.get("companyInformation")).getIpoPlate());
        wordMap.put("审核结果","审核结果:"+((IpoFeedbackDto)dataMap.get("ipoFeedbackDto")).getBaseList().get(len-1).getIecResultStr());
        wordMap.put("审核日期","审核日期:"+((IpoFeedbackDto)dataMap.get("ipoFeedbackDto")).getBaseList().get(len-1).getExamineDateStr());
        wordMap.put("审核历时","审核历时:"+wordMap.get("#审核历时#")+"天");
        wordMap.put("IPO进程详情","IPO进程:"+((HeadDataVo)dataMap.get("head")).getProcessLabel());

        wordMap.put("注册地址说明",((CompanyOverviewVo)dataMap.get("companyInformation")).getAddrCountry());
        wordMap.put("证监会行业说明",((CompanyOverviewVo)dataMap.get("companyInformation")).getIndustryCsrc());
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
                  if (StringUtils.isNotEmpty(otherList.get(b).getCompanyName())){
                      content = content + otherList.get(b).getCompanyName();
                  }
                  if (StringUtils.isNotEmpty(otherList.get(b).getCompanyCode())){
                      content = content + otherList.get(b).getCompanyCode()+"\n";
                  }
                  if (StringUtils.isNotEmpty(otherList.get(b).getListTime()+"")){
                      content = content + "上市日/挂牌日："+sd.format(otherList.get(b).getListTime())+"\n";
                  }
                  if (StringUtils.isNotEmpty(otherList.get(b).getDelistTime()+"")){
                      content = content + "退市日/摘牌日："+sd.format(otherList.get(b).getDelistTime())+"\n";
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
                  if (StringUtils.isNotEmpty(splitList.get(b).getShareProportion()+"")){
                      content = content + "直接或间接持有人股份：" + splitList.get(b).getShareProportion()+"%\n";
                  }
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
          }else if("#行业地位#".equals(paragraph.getText())){
              String content = "";
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
                  if (imgUrl.indexOf("https") == -1){
                      imgUrl = imgUrl.replaceAll("http","https");
                  }
                  HttpURLConnection connection = (HttpURLConnection) new URL(imgUrl).openConnection();
                  InputStream img = connection.getInputStream();
                  paragraph.getRuns().get(0).addPicture(img, XWPFDocument.PICTURE_TYPE_JPEG, imgUrl, Units.toEMU(400), Units.toEMU(400));
              } catch (InvalidFormatException e) {
                  e.printStackTrace();
              }
              continue;
          } else if("#主营业务柱状图#".equals(paragraph.getText())){
              test.clearParagraph(paragraph);
              if (mainIncomeVo != null && mainIncomeVo.getMainIncomeInfoList() != null){
                  List<String> listLanguages = new ArrayList<>();
                  if (StringUtils.isNotEmpty(mainIncomeVo.getOnePeriodForIncome())){
                      listLanguages.add(mainIncomeVo.getOnePeriodForIncome());
                  }
                  if (StringUtils.isNotEmpty(mainIncomeVo.getThirdYearForIncome())){
                      listLanguages.add(mainIncomeVo.getThirdYearForIncome());
                  }
                  if (StringUtils.isNotEmpty(mainIncomeVo.getSecondYearForIncome())){
                      listLanguages.add(mainIncomeVo.getSecondYearForIncome());
                  }
                  if (StringUtils.isNotEmpty(mainIncomeVo.getFirstYearForIncome())){
                      listLanguages.add(mainIncomeVo.getFirstYearForIncome());
                  }

                  List<Double> listCountries = new ArrayList<>();
                  List<Double> listCountries2 = new ArrayList<>();
                  List<Double> listCountries3 = new ArrayList<>();
                  List<Double> listCountries4 = new ArrayList<>();
                  List<String> series = new ArrayList<>();
                  for (int i=0;i<mainIncomeVo.getMainIncomeInfoList().size();i++){
                      if (!mainIncomeVo.getMainIncomeInfoList().get(i).getBusinessName().equals("合计")){
                          series.add(mainIncomeVo.getMainIncomeInfoList().get(i).getBusinessName());
                          if (StringUtils.isNotEmpty(mainIncomeVo.getOnePeriodForIncome())){
                              listCountries.add(Double.valueOf(mainIncomeVo.getMainIncomeInfoList().get(i).getOnePeriodAmount().toString()));
                          }
                          if (StringUtils.isNotEmpty(mainIncomeVo.getThirdYearForIncome())){
                              listCountries2.add(Double.valueOf(mainIncomeVo.getMainIncomeInfoList().get(i).getThirdYearAmount().toString()));
                          }
                          if (StringUtils.isNotEmpty(mainIncomeVo.getSecondYearForIncome())){
                              listCountries3.add(Double.valueOf(mainIncomeVo.getMainIncomeInfoList().get(i).getSecondYearAmount().toString()));
                          }
                          if (StringUtils.isNotEmpty(mainIncomeVo.getFirstYearForIncome())){
                              listCountries4.add( Double.valueOf(mainIncomeVo.getMainIncomeInfoList().get(i).getFirstYearAmount().toString()));
                          }
                      }
                  }

                  List<List<Double>> yValues = Lists.newArrayList();
                  yValues.add(listCountries);
                  yValues.add(listCountries2);
                  yValues.add(listCountries3);
                  yValues.add(listCountries4);
                  List<List<Double>> zhuziList = Lists.newArrayList();
                  for (int k=0;k<3;k++){
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
              }
          }
          else if("#问询回复/反馈意见流程#".equals(paragraph.getText())){
              test.clearParagraph(paragraph);
              SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
              for (int b=0;b<ipoFeedbackList.size();b++){
                  int sort=b+1;
                  XWPFRun run1 = paragraph.createRun();
                  run1.setText(sort+"、"+ipoFeedbackList.get(b).getLetterName());
                  run1.setFontSize(16);
                  run1.setColor("333399");
                  run1.setBold(true);
                  run1.setFontFamily("微软雅黑");
                  run1.addBreak();
                  if (StringUtils.isNotEmpty(ipoFeedbackList.get(b).getLetterFileNo())){
                      XWPFRun run2 = paragraph.createRun();
                      run2.setText("函件文号：" + ipoFeedbackList.get(b).getLetterFileNo());
                      run2.addBreak();
                  }
                  if (ipoFeedbackList.get(b).getLetterDate()!=null){
                      XWPFRun run2 = paragraph.createRun();
                      run2.setText("发函时间：" + df.format(ipoFeedbackList.get(b).getLetterDate()));
                      run2.addBreak();
                  }
                  if (ipoFeedbackList.get(b).getReturnDate()!=null){
                      XWPFRun run2 = paragraph.createRun();
                      run2.setText("回函时间：" + df.format(ipoFeedbackList.get(b).getReturnDate()));
                      run2.addBreak();
                  }
                  if (StringUtils.isNotEmpty(ipoFeedbackList.get(b).getIntervalDate())){
                      XWPFRun run2 = paragraph.createRun();
                      run2.setText("回函用时：" + ipoFeedbackList.get(b).getIntervalDate()+"天");
                      run2.addBreak();
                  }
                  if (StringUtils.isNotEmpty(String.valueOf(ipoFeedbackList.get(b).getQuestionCount()))){
                      XWPFRun run2 = paragraph.createRun();
                      run2.setText( "问题数量：共计" + ipoFeedbackList.get(b).getQuestionCount()+"个问题" );
                      run2.addBreak();
                  }
                  if (StringUtils.isNotEmpty(String.valueOf(ipoFeedbackList.get(b).getAnswerCount()))){
                      XWPFRun run2 = paragraph.createRun();
                      run2.setText( "回复情况：共计" + ipoFeedbackList.get(b).getAnswerCount() + "个回复");
                      run2.addBreak();
                  }
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
                  XWPFRun newRun = paragraph.createRun();
                  String relationId = xdoc.getRelationId(chart);
                  java.lang.reflect.Method attach = XWPFChart.class.getDeclaredMethod("attach", String.class, XWPFRun.class);
                  attach.setAccessible(true);
                  attach.invoke(chart, relationId, newRun);
                  chart.setChartWidth(XDDFChart.DEFAULT_WIDTH * 10);
                  chart.setChartHeight(XDDFChart.DEFAULT_HEIGHT * 8);
                  newRun.addBreak();
              }
              continue;
          }

          else if("#主要供应商情况#".equals(paragraph.getText())){
              test.clearParagraph(paragraph);
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
                  newParaRun.setFontFamily("微软雅黑");
                  newParaRun.setFontSize(10);
                  newParaRun.setBold(true);
                  newParaRun.setText("("+sort+")"+customerMainList.get(z).getTitle());
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
//                  row_0.addNewTableCell().setText(supplierMainList.get(z).get);
//                  row_0.addNewTableCell().setText("");
//                  row_0.addNewTableCell().setText("");
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
                          setTitleBold(3,row_2,twoMarkStr(list.get(n).getOnePeriodAmount().toString()),8,false);
                          setTitleBold(4,row_2,twoMarkStr(list.get(n).getOnePeriodRatio().toString())+"%",8,false);
                          setTitleBold(5,row_2,list.get(n).getThirdYearContent(),8,false);
                          setTitleBold(6,row_2,twoMarkStr(list.get(n).getThirdYearAmount().toString()),8,false);
                          setTitleBold(7,row_2,twoMarkStr(list.get(n).getThirdYearRatio().toString())+"%",8,false);
                          setTitleBold(8,row_2,list.get(n).getSecondYearContent(),8,false);
                          setTitleBold(9,row_2,twoMarkStr(list.get(n).getSecondYearAmount().toString()),8,false);
                          setTitleBold(10,row_2,twoMarkStr(list.get(n).getSecondYearRatio().toString())+"%",8,false);
                          setTitleBold(11,row_2,list.get(n).getFirstYearContent(),8,false);
                          setTitleBold(12,row_2,twoMarkStr(list.get(n).getFirstYearAmount().toString()),8,false);
                          setTitleBold(13,row_2,twoMarkStr(list.get(n).getFirstYearRatio().toString())+"%",8,false);
                          if (n%2!=0){
                              for (int p=0;p<14;p++){
                                  row_2.getCell(p).setColor("E7F3FF");
                              }
                      }
                  }
                  //合并行
                  fillTable(table);
                  mergeCellsHorizontal(table,0,0,1);
                  mergeCellsHorizontal(table,0,2,4);
                  mergeCellsHorizontal(table,0,5,7);
                  mergeCellsHorizontal(table,0,8,10);
                  mergeCellsHorizontal(table,0,11,13);
                  //边框颜色
                  setBorderStyle(table);
              }

              continue;
          }
          else if("#主要客户情况#".equals(paragraph.getText())){
              test.clearParagraph(paragraph);
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
                  newParaRun.setFontFamily("微软雅黑");
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
                          setTitleBold(3,row_2,twoMarkStr(list.get(n).getOnePeriodAmount().toString()),8,false);
                          setTitleBold(4,row_2,twoMarkStr(list.get(n).getOnePeriodRatio().toString())+"%",8,false);
                          setTitleBold(5,row_2,list.get(n).getThirdYearContent(),8,false);
                          setTitleBold(6,row_2,twoMarkStr(list.get(n).getThirdYearAmount().toString()),8,false);
                          setTitleBold(7,row_2,twoMarkStr(list.get(n).getThirdYearRatio().toString())+"%",8,false);
                          setTitleBold(8,row_2,list.get(n).getSecondYearContent(),8,false);
                          setTitleBold(9,row_2,twoMarkStr(list.get(n).getSecondYearAmount().toString()),8,false);
                          setTitleBold(10,row_2,twoMarkStr(list.get(n).getSecondYearRatio().toString())+"%",8,false);
                          setTitleBold(11,row_2,list.get(n).getFirstYearContent(),8,false);
                          setTitleBold(12,row_2,twoMarkStr(list.get(n).getFirstYearAmount().toString()),8,false);
                          setTitleBold(13,row_2,twoMarkStr(list.get(n).getFirstYearRatio().toString())+"%",8,false);
                          if (n%2!=0){
                              for (int p=0;p<14;p++){
                                  row_2.getCell(p).setColor("E7F3FF");
                              }
                          }

                  }
                  //合并行
                  fillTable(table);
                  mergeCellsHorizontal(table,0,0,1);
                  mergeCellsHorizontal(table,0,2,4);
                  mergeCellsHorizontal(table,0,5,7);
                  mergeCellsHorizontal(table,0,8,10);
                  mergeCellsHorizontal(table,0,11,13);
                  //边框颜色
                  setBorderStyle(table);
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
                  else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#每股面值#") != -1) {
                      if(issueData!=null){
                          test.replaceTableCell(td,twoMarkStr(issueData.getParValue()+""));
                      }else {
                          test.replaceTableCell(td,"--");
                      }
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#发行价格#") != -1) {
                      if(issueData!=null){
                          test.replaceTableCell(td,twoMarkStr(issueData.getIssuePrice()+""));
                      }else {
                          test.replaceTableCell(td,"--");
                      }
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#发行前总股本#") != -1) {
                      if(issueData!=null){
                          test.replaceTableCell(td,twoMarkStr(issueData.getPreIssueNum()+""));
                      }else {
                          test.replaceTableCell(td,"--");
                      }
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#发行后总股本#") != -1) {
                      if(issueData!=null){
                          test.replaceTableCell(td,twoMarkStr(issueData.getNextIssueNum()+""));
                      }else {
                          test.replaceTableCell(td,"--");
                      }
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#发行数量总计#") != -1) {
                      if(issueData!=null){
                          test.replaceTableCell(td,twoMarkStr(issueData.getShareIssued()+""));
                      }else {
                          test.replaceTableCell(td,"--");
                      }
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#募集资金总额#") != -1) {
                      if(issueData!=null){
                          test.replaceTableCell(td,twoMarkStr(issueData.getSumFina()+""));
                      }else {
                          test.replaceTableCell(td,"--");
                      }
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#占发行后总股本的比例#") != -1) {
                      if(issueData!=null){
                          test.replaceTableCell(td,twoMarkStr(issueData.getIssuedRatio()+""));
                      }else {
                          test.replaceTableCell(td,"--");
                      }
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#募集资金净额#") != -1) {
                      if(issueData!=null){
                          test.replaceTableCell(td,twoMarkStr(issueData.getNetSumFina()+""));
                      }else {
                          test.replaceTableCell(td,"--");
                      }
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#网上发行数量#") != -1) {
                      if(issueData!=null){
                          test.replaceTableCell(td,twoMarkStr(issueData.getShareIssueOn()+""));
                      }else {
                          test.replaceTableCell(td,"--");
                      }
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#网下配售数量#") != -1) {
                      if(issueData!=null){
                          test.replaceTableCell(td,twoMarkStr(issueData.getShareIssued()+""));
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
                          test.replaceTableCell(td,twoMarkStr(issueData.getPeIssueA()+""));
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
                                      test.setCellNewContentBold(table, no, 0, mainIncomeVo.getMainIncomeInfoList().get(k).getBusinessName(), i);
                                      test.setCellNewContentBold(table, no, 1, mainIncomeVo.getMainIncomeInfoList().get(k).getOnePeriodAmount()+"", i);
                                      test.setCellNewContentBold(table, no, 2, mainIncomeVo.getMainIncomeInfoList().get(k).getOnePeriodRatio()+"%", i);
                                      test.setCellNewContentBold(table, no, 3, mainIncomeVo.getMainIncomeInfoList().get(k).getThirdYearAmount()+"", i);
                                      test.setCellNewContentBold(table, no, 4, mainIncomeVo.getMainIncomeInfoList().get(k).getThirdYearRatio()+"%", i);
                                      test.setCellNewContentBold(table, no, 5, mainIncomeVo.getMainIncomeInfoList().get(k).getSecondYearAmount()+"", i);
                                      test.setCellNewContentBold(table, no, 6, mainIncomeVo.getMainIncomeInfoList().get(k).getSecondYearRatio()+"%", i);
                                      test.setCellNewContentBold(table, no, 7, mainIncomeVo.getMainIncomeInfoList().get(k).getFirstYearAmount()+"", i);
                                      test.setCellNewContentBold(table, no, 8, mainIncomeVo.getMainIncomeInfoList().get(k).getFirstYearRatio()+"%", i);
                                  }else {
                                      test.setCellNewContentBold(table, no, 0, mainIncomeVo.getMainIncomeInfoList().get(k).getBusinessName(), i);
                                      test.setCellNewContent(table, no, 1, mainIncomeVo.getMainIncomeInfoList().get(k).getOnePeriodAmount()+"", i);
                                      test.setCellNewContent(table, no, 2, mainIncomeVo.getMainIncomeInfoList().get(k).getOnePeriodRatio()+"%", i);
                                      test.setCellNewContent(table, no, 3, mainIncomeVo.getMainIncomeInfoList().get(k).getThirdYearAmount()+"", i);
                                      test.setCellNewContent(table, no, 4, mainIncomeVo.getMainIncomeInfoList().get(k).getThirdYearRatio()+"%", i);
                                      test.setCellNewContent(table, no, 5, mainIncomeVo.getMainIncomeInfoList().get(k).getSecondYearAmount()+"", i);
                                      test.setCellNewContent(table, no, 6, mainIncomeVo.getMainIncomeInfoList().get(k).getSecondYearRatio()+"%", i);
                                      test.setCellNewContent(table, no, 7, mainIncomeVo.getMainIncomeInfoList().get(k).getFirstYearAmount()+"", i);
                                      test.setCellNewContent(table, no, 8, mainIncomeVo.getMainIncomeInfoList().get(k).getFirstYearRatio()+"%", i);
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
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#行业毛利率对比表格#") != -1) {
                      if (j + 3 < cellList.size()) {
                          int no = rowCnt+1;
                          if (industryCompareList != null && industryCompareList.size() > 0 && industryCompareList.get(0).getIndustryCompareRateDetailList() != null){
                              IndustryCompareRateDto m = industryCompareList.get(0);
                              test.setCellNewContent(table, 0, 1, m.getThirdYear(), i);
                              test.setCellNewContent(table, 0, 2, m.getSecondYear(), i);
                              test.setCellNewContent(table, 0, 3, m.getFirstYear(), i);
                              List<IndustryCompareRateDetailDto> tb = industryCompareList.get(0).getIndustryCompareRateDetailList();
                              for (int k = 0; k < tb.size(); k++) {
                                  if (no >= table.getNumberOfRows()) {
                                      test.insertTableRowAtIndex(table, no);
                                  }
                                  test.setCellNewContentBold(table, no, 0, tb.get(k).getCompanyName(), i);
                                  test.setCellNewContent(table, no, 1, tb.get(k).getThirdYearRate()+"", i);
                                  test.setCellNewContent(table, no, 2, tb.get(k).getSecondYearRate()+"", i);
                                  test.setCellNewContent(table, no, 3, tb.get(k).getFirstYearRate()+"", i);
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
                          }
                      }
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#安装业务表格#") != -1) {
                      if (j + 3 < cellList.size()) {
                          int no = rowCnt+1;
                          if (industryCompareList != null && industryCompareList.size() > 0 && industryCompareList.get(1).getIndustryCompareRateDetailList() != null){
                              IndustryCompareRateDto m = industryCompareList.get(0);
                              test.setCellNewContent(table, 0, 1, m.getThirdYear(), i);
                              test.setCellNewContent(table, 0, 2, m.getSecondYear(), i);
                              test.setCellNewContent(table, 0, 3, m.getFirstYear(), i);
                              List<IndustryCompareRateDetailDto> tb = industryCompareList.get(1).getIndustryCompareRateDetailList();
                              for (int k = 0; k < tb.size(); k++) {
                                  if (no >= table.getNumberOfRows()) {
                                      test.insertTableRowAtIndex(table, no);
                                  }
                                  test.setCellNewContentBold(table, no, 0, tb.get(k).getCompanyName(), i);
                                  test.setCellNewContent(table, no, 1, tb.get(k).getThirdYearRate()+"", i);
                                  test.setCellNewContent(table, no, 2, tb.get(k).getSecondYearRate()+"", i);
                                  test.setCellNewContent(table, no, 3, tb.get(k).getFirstYearRate()+"", i);
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
                          }
                      }
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#专利情况表格#") != -1) {
                      if (j + 4 < cellList.size()) {
                          if (ipoTechnology != null && ipoTechnology.getPatentData() != null){
                              test.setCellNewContent(table, 1, 1, twoMarkStr(ipoTechnology.getPatentData().get(0).getFm()+""), i);
                              test.setCellNewContent(table, 1, 2, twoMarkStr(ipoTechnology.getPatentData().get(0).getSy()+""), i);
                              test.setCellNewContent(table, 1, 3, twoMarkStr(ipoTechnology.getPatentData().get(0).getWg()+""), i);
                              test.setCellNewContent(table, 1, 4, twoMarkStr(ipoTechnology.getPatentData().get(0).getGw()+""), i);
                              test.setCellNewContent(table, 1, 5, twoMarkStr(ipoTechnology.getPatentData().get(0).getHj()+""), i);
                              test.setCellNewContent(table, 2, 1, twoMarkStr(ipoTechnology.getPatentData().get(1).getFm()+""), i);
                              test.setCellNewContent(table, 2, 2, twoMarkStr(ipoTechnology.getPatentData().get(1).getSy()+""), i);
                              test.setCellNewContent(table, 2, 3, twoMarkStr(ipoTechnology.getPatentData().get(1).getWg()+""), i);
                              test.setCellNewContent(table, 2, 4, twoMarkStr(ipoTechnology.getPatentData().get(1).getGw()+""), i);
                              test.setCellNewContent(table, 2, 5, twoMarkStr(ipoTechnology.getPatentData().get(1).getHj()+""), i);
                              break lableA;
                          }
                      }
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#研发投入表格#") != -1) {
                      if (j + 4 < cellList.size()) {
                          int no = rowCnt+1;
                          if (ipoTechnology != null && ipoTechnology.getDevDate() != null){
                              test.setCellNewContentTitleNotTemp(table, 0, 1, ipoTechnology.getDevDate().getFirstYearDate(), i);
                              test.setCellNewContentTitleNotTemp(table, 0, 2, ipoTechnology.getDevDate().getSecondYearDate(), i);
                              test.setCellNewContentTitleNotTemp(table, 0, 3, ipoTechnology.getDevDate().getThirdYearDate(), i);
                              test.setCellNewContentTitleNotTemp(table, 0, 4, ipoTechnology.getDevDate().getForthYearDate(), i);
                              test.setCellNewContentTitleNotTemp(table, 0, 5, "合计", i);
                          }
                          if (ipoTechnology != null && ipoTechnology.getDevData() != null){
                              for (int k = 0; k < ipoTechnology.getDevData().size(); k++) {
                                  test.setCellNewContentNotTemp(table, no, 1, ipoTechnology.getDevData().get(k).getFirstYearValue()+"", i);
                                  test.setCellNewContentNotTemp(table, no, 2, ipoTechnology.getDevData().get(k).getSecondYearValue()+"", i);
                                  test.setCellNewContentNotTemp(table, no, 3, ipoTechnology.getDevData().get(k).getThirdYearValue()+"", i);
                                  test.setCellNewContentNotTemp(table, no, 4, ipoTechnology.getDevData().get(k).getForthYearValue()+"", i);
                                  test.setCellNewContentNotTemp(table, no, 5, ipoTechnology.getDevData().get(k).getAllNumValue()+"", i);
                                  no++;
                              }
                          }
                          break lableA;
                      }
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#研发人员表格#") != -1) {
                      if (j + 3 < cellList.size()) {
                          int no = rowCnt+1;
                          if (ipoTechnology != null && ipoTechnology.getCoreDate() != null){
                              test.setCellNewContentTitleNotTemp(table, 0, 1, ipoTechnology.getCoreDate().getFirstYearDate(), i);
                              test.setCellNewContentTitleNotTemp(table, 0, 2, ipoTechnology.getCoreDate().getSecondYearDate(), i);
                              test.setCellNewContentTitleNotTemp(table, 0, 3, ipoTechnology.getCoreDate().getThirdYearDate(), i);
                              test.setCellNewContentTitleNotTemp(table, 0, 4, ipoTechnology.getCoreDate().getForthYearDate(), i);
                          }
                          if (ipoTechnology != null && ipoTechnology.getCoreData() != null){
                              for (int k = 0; k < ipoTechnology.getCoreData().size(); k++) {
                                  test.setCellNewContentNotTemp(table, no, 1, ipoTechnology.getCoreData().get(k).getFirstYearValue()+"", i);
                                  test.setCellNewContentNotTemp(table, no, 2, ipoTechnology.getCoreData().get(k).getSecondYearValue()+"", i);
                                  test.setCellNewContentNotTemp(table, no, 3, ipoTechnology.getCoreData().get(k).getThirdYearValue()+"", i);
                                  test.setCellNewContentNotTemp(table, no, 4, ipoTechnology.getCoreData().get(k).getForthYearValue()+"", i);
                                  no++;
                              }
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
                                  test.setCellNewContentNotTemp(table, no, 1, twoMarkStr(tb.get(k).getForthYearValue()+""), i);
                                  test.setCellNewContentNotTemp(table, no, 2, twoMarkStr(tb.get(k).getThirdYearValue()+""), i);
                                  test.setCellNewContentNotTemp(table, no, 3, twoMarkStr(tb.get(k).getSecondYearValue()+""), i);
                                  test.setCellNewContentNotTemp(table, no, 4, twoMarkStr(tb.get(k).getFirstYearValue()+""), i);
                                  no++;
                              }
                              break lableA;
                          }
                      }
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#资产负债情况表格#") != -1) {
                      if (j + 3 < cellList.size()) {
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
                                      test.setCellNewContentNotTemp(table, no, 1, "--", i);
                                      test.setCellNewContentNotTemp(table, no, 2, "--", i);
                                      test.setCellNewContentNotTemp(table, no, 3, "--", i);
                                      test.setCellNewContentNotTemp(table, no, 4, "--", i);
                                      if (tb.get(z).getItemName().equals(asstd.getText())){
                                          test.setCellNewContentNotTemp(table, no, 1, twoMarkStr(tb.get(z).getForthYearValue() + ""), i);
                                          test.setCellNewContentNotTemp(table, no, 2, twoMarkStr(tb.get(z).getThirdYearValue() + ""), i);
                                          test.setCellNewContentNotTemp(table, no, 3, twoMarkStr(tb.get(z).getSecondYearValue() + ""), i);
                                          test.setCellNewContentNotTemp(table, no, 4, twoMarkStr(tb.get(z).getFirstYearValue() + ""), i);
                                          break;
                                      }
                                  }
                              }
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
                                  test.setCellNewContentNotTemp(table, no, 1, twoMarkStr(tb.get(k).getForthYearValue()+""), i);
                                  test.setCellNewContentNotTemp(table, no, 2, twoMarkStr(tb.get(k).getThirdYearValue()+""), i);
                                  test.setCellNewContentNotTemp(table, no, 3, twoMarkStr(tb.get(k).getSecondYearValue()+""), i);
                                  test.setCellNewContentNotTemp(table, no, 4, twoMarkStr(tb.get(k).getFirstYearValue()+""), i);
                                  no++;
                              }
                              break lableA;
                          }
                      }
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#收入与利润情况表格#") != -1) {
                      if (j + 3 < cellList.size()) {
                          if (incomeProfit != null) {
                              List<IpoItemDto> tb = new ArrayList<>();
                              tb.addAll(incomeProfit.getIpoProfitItemList());
                              tb.addAll(incomeProfit.getIpoCostItemList());
                              tb.addAll(incomeProfit.getIpoReturnOverList());
                              test.setCellNewContentTitleNotTemp(table, 0, 1, incomeProfit.getDateList().getForthYearDate(), i);
                              test.setCellNewContentTitleNotTemp(table, 0, 2, incomeProfit.getDateList().getThirdYearDate(), i);
                              test.setCellNewContentTitleNotTemp(table, 0, 3, incomeProfit.getDateList().getSecondYearDate(), i);
                              test.setCellNewContentTitleNotTemp(table, 0, 4, incomeProfit.getDateList().getFirstYearDate(), i);
                              for (int n = 1; n < table.getNumberOfRows(); n++) {
                                  XWPFTableRow asstr = table.getRow(n);
                                  List<XWPFTableCell> assCellList = asstr.getTableCells();
                                  int no = n;
                                  XWPFTableCell asstd = assCellList.get(0);// 取得单元格
                                  for (int z=0;z<tb.size();z++){
                                      test.setCellNewContentNotTemp(table, no, 1, "--", i);
                                      test.setCellNewContentNotTemp(table, no, 2, "--", i);
                                      test.setCellNewContentNotTemp(table, no, 3, "--", i);
                                      test.setCellNewContentNotTemp(table, no, 4, "--", i);
                                      if (tb.get(z).getItemName().equals(asstd.getText())){
                                          test.setCellNewContentNotTemp(table, no, 1, twoMarkStr(tb.get(z).getForthYearValue() + ""), i);
                                          test.setCellNewContentNotTemp(table, no, 2, twoMarkStr(tb.get(z).getThirdYearValue() + ""), i);
                                          test.setCellNewContentNotTemp(table, no, 3, twoMarkStr(tb.get(z).getSecondYearValue() + ""), i);
                                          test.setCellNewContentNotTemp(table, no, 4, twoMarkStr(tb.get(z).getFirstYearValue() + ""), i);
                                          break;
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
                          }
                      }
                  }else if (StringUtils.isNotEmpty(td.getText()) && td.getText().indexOf("#ipo进程表格#") != -1) {
                      if (j + 5 < cellList.size()) {
                          int no = rowCnt+2;
                          if (treeTypeProgress != null && treeTypeProgress.getTreeList() != null){
                              int k = 0;
                              for (int m=0;m<treeTypeProgress.getTreeList().size();m++){
                                  if (treeTypeProgress.getTreeList().get(m).getProList() != null){
                                      if (treeTypeProgress.getTreeList().get(m).getProList().size() > k){
                                          k = treeTypeProgress.getTreeList().get(m).getProList().size();
                                      }
                                  }
                              }
                              for (int n=0;n<k;n++){
                                  if (no >= table.getNumberOfRows()-1) {
                                      test.insertTableRowAtIndex(table, no);
                                  }
                                  //先赋空 保证表格样式
                                  test.setCellNewContent(table, no, 0, "", i);
                                  test.setCellNewContent(table, no, 1, "", i);
                                  test.setCellNewContent(table, no, 2, "", i);
                                  test.setCellNewContent(table, no, 3, "", i);
                                  test.setCellNewContent(table, no, 4, "", i);
                                  test.setCellNewContent(table, no, 5, "", i);
                                  if (treeTypeProgress.getTreeList().get(2).getProList()!= null && n < treeTypeProgress.getTreeList().get(2).getProList().size()){
                                      test.setCellNewContent(table, no, 0, isNull(treeTypeProgress.getTreeList().get(2).getProList().get(n).getProcessTime()), i);
                                      test.setCellNewContent(table, no, 1, isNull(treeTypeProgress.getTreeList().get(2).getProList().get(n).getProgressName()), i);
                                  }
                                  if (treeTypeProgress.getTreeList().get(1).getProList() != null && n < treeTypeProgress.getTreeList().get(1).getProList().size()){
                                      test.setCellNewContent(table, no, 2, isNull(treeTypeProgress.getTreeList().get(1).getProList().get(n).getProcessTime()), i);
                                      test.setCellNewContent(table, no, 3, isNull(treeTypeProgress.getTreeList().get(1).getProList().get(n).getProgressName()), i);
                                  }
                                  if (treeTypeProgress.getTreeList().get(0).getProList() != null && n < treeTypeProgress.getTreeList().get(0).getProList().size()){
                                      test.setCellNewContent(table, no, 4, isNull(treeTypeProgress.getTreeList().get(0).getProList().get(n).getProcessTime()), i);
                                      test.setCellNewContent(table, no, 5, isNull(treeTypeProgress.getTreeList().get(0).getProList().get(n).getProgressName()), i);
                                  }
                                  no++;
                              }
                              test.setCellNewContent(table, no, 0, "共计历时" + isNull(wordMap.get("#辅导历时#")) + "天", i);
                              test.setCellNewContent(table, no, 1, "共计历时" + isNull(wordMap.get("#审核历时#")) + "天", i);
                              test.setCellNewContent(table, no, 2, "共计历时" + isNull(wordMap.get("#发行历时#")) + "天", i);
                              if ( (treeTypeProgress.getTreeList().get(2).getProList() == null ||treeTypeProgress.getTreeList().get(2).getProList().size()==0)
                                      &&(treeTypeProgress.getTreeList().get(1).getProList() == null||treeTypeProgress.getTreeList().get(1).getProList().size()==0)
                                      &&(treeTypeProgress.getTreeList().get(0).getProList() == null ||treeTypeProgress.getTreeList().get(0).getProList().size()==0)){
                                  test.deleteTableRow(table,table.getNumberOfRows()-2);
                                  test.deleteTableRow(table,table.getNumberOfRows()-2);
                              } else if ( (treeTypeProgress.getTreeList().get(2).getProList()!=null&&treeTypeProgress.getTreeList().get(2).getProList().size()<=1)
                              &&(treeTypeProgress.getTreeList().get(1).getProList()!=null&&treeTypeProgress.getTreeList().get(1).getProList().size()<=1)
                              &&(treeTypeProgress.getTreeList().get(0).getProList()!=null&&treeTypeProgress.getTreeList().get(0).getProList().size()<=1)){
                                  test.deleteTableRow(table,table.getNumberOfRows()-2);
                              }
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

    public String twoMarkStr(String str){
        if(str == null || "null".equals(str)||"".equals(str)){
            return "--";
        }
        DecimalFormat df = new DecimalFormat("#0.00");
        return df.format(Float.parseFloat(str));
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

    public  void fillTable(XWPFTable table) {
        for (int rowIndex = 0; rowIndex < table.getNumberOfRows(); rowIndex++) {
            XWPFTableRow row = table.getRow(rowIndex);
            row.setHeight(132);
            for (int colIndex = 0; colIndex < row.getTableCells().size(); colIndex++) {
                XWPFTableCell cell = row.getCell(colIndex);
                if(rowIndex%2==0){
                    setCellText(cell, 1500);
                }else{
                    setCellText(cell, 1500);
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
}

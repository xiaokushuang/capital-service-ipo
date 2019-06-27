package com.stock.capital.enterprise.ipoInterfaceH5.service;

import com.google.common.collect.Maps;
import com.stock.capital.enterprise.common.constant.Global;
import com.stock.capital.enterprise.ipoCase.dao.IpoCaseListMapper;
import com.stock.capital.enterprise.ipoCase.dto.*;
import com.stock.capital.enterprise.ipoCase.service.CompanyOverviewService;
import com.stock.capital.enterprise.ipoCase.service.IssueSituationService;
import com.stock.capital.enterprise.ipoInterfaceH5.dao.IpoInterfaceBizMapper;
import com.stock.capital.enterprise.ipoInterfaceH5.dao.IpoWechatPermisionBizMapper;
import com.stock.capital.enterprise.ipoInterfaceH5.dto.*;
import com.stock.capital.enterprise.ipoInterfaceH5.dto.IpoH5CoreDevDto;
import com.stock.capital.enterprise.ipoInterfaceH5.dto.IpoH5DetailDto;
import com.stock.capital.enterprise.ipoInterfaceH5.dto.IpoH5Dto;
import com.stock.capital.enterprise.ipoInterfaceH5.dto.IpoH5IndustryBodyDto;
import com.stock.capital.enterprise.ipoInterfaceH5.dto.IpoH5IndustryDto;
import com.stock.capital.enterprise.ipoInterfaceH5.dto.IpoH5IndustryTitleDto;
import com.stock.capital.enterprise.ipoInterfaceH5.dto.IpoH5TechnologyDevDto;
import com.stock.capital.enterprise.ipoInterfaceH5.dto.IpoH5TechnologyDto;
import com.stock.core.service.BaseService;

import com.stock.core.util.BeanUtil;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

import java.util.stream.Collectors;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.eclipse.jetty.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class IpoInterfaceService extends BaseService {

    @Autowired
    private CompanyOverviewService companyOverviewService;

    @Autowired
    private IssueSituationService issueSituationService;

    @Autowired
    private IpoCaseListMapper ipoCaseListMapper;

    @Autowired
    private IpoInterfaceBizMapper ipoInterfaceBizMapper;

    @Autowired
    private IpoWechatPermisionBizMapper ipoWechatPermisionBizMapper;

    /**
     * dxy
     * 科技创新页面
     *
     * @param id
     * @return
     */
    public Map getIpoTechnology(String id) {
        Map result = new HashMap();
//    CompanyOverviewVo companyOverviewVo = companyOverviewService.getIpoCaseDetail(id); // 顶部地位备注
        List<IssuerIndustryStatusDto> industryStatusDtoList = companyOverviewService.getindustryStatusData(id);//发行人的行业地位接口
        List<MainCompetitorInfoDto> mainCompetitorInfoDtoList = companyOverviewService.getCompetitorData(id); // 主要竞争对手
        IpoTechnologyVo ipoTechnologyVo = companyOverviewService.getPatentData(id); // 研发投入 核心技术与研发技术人员
        List<IndustryCompareRateDto> industryCompareRateDtos = issueSituationService.getIndustryRateData(id);// 同行业上市公司综合毛利率接口

//    result.put("companyInfo", companyOverviewVo);
        result.put("industryStatusInfo", industryStatusDtoList);
        result.put("mainCompetitorInfo", mainCompetitorInfoDtoList);
        result.put("technologyInfo", ipoTechnologyDataProcessing(ipoTechnologyVo, id));// 研发投入 核心技术与研发技术人员
        result.put("industryCompareRateInfo", industryCompareRateDataProcessing(industryCompareRateDtos));//毛利率数据处理加工
        return result;
    }
    /**
     * 拆分上市情况
     * dxy
     * @param id 案例id
     * @return list
     */
    public List<IpoSplitDto> getSpliteData(String id) {
        List<IpoSplitDto> resultList = companyOverviewService.getSpliteData(id);
        if (resultList.size() != 0){
            return resultList;
        }
        return null;
    }


    /**
     * 获取下拉框数据
     *
     * @return
     */
    public Map<String, Object> querySelectData() {
        Map<String, Object> result = Maps.newHashMap();
//        所属行业（证监局）
        List<RegTreeDto> sfcList = ipoCaseListMapper.getTreeTagByCode(Global.IPO_SFC);
//        证监会行业
        List<RegTreeDto> industryCrscList = ipoCaseListMapper.getLabelByCode(Global.IPO_SFC_INDUSTRY);
//        审核结果
        List<RegTreeDto> verifyResultList = ipoCaseListMapper.getLabelByCode(Global.IPO_VERIFY_RESULT);

        result.put("sfcList", sfcList);
        result.put("industryCrscList", industryCrscList);
        result.put("verifyResultList", verifyResultList);
        return result;
    }

    public List<IpoCaseListVo> otherIpoCase(IpoCaseIndexDto ipoCaseIndexDto) {
        List<IpoCaseListVo> resultList = ipoInterfaceBizMapper.otherIpoCase(ipoCaseIndexDto);
        for(IpoCaseListVo dto:resultList){
            String URL = "http://www.valueonline.cn/open/";
            URL = URL + dto.getCompanyCode() + "_160.png";
            dto.setCompanyLogo(URL);
        }
        return resultList;
    }

    /**
     * 行业排名
     * 必传：bid 案例主键
     *
     * @param ipoH5Dto
     * @return
     */
    public IpoH5Dto ipoCompanyRank(IpoH5Dto ipoH5Dto) {
        return ipoInterfaceBizMapper.ipoCompanyRank(ipoH5Dto);
    }

    /**
     * 行业平均
     * 必传：plateType 板块类型（科创板：0，创业板：1）
     * bid 案例主键
     * columnComment 指标类型（具体有：经营活动产生的现金流量净额：046，现金及现金等价物净增加额：091,流动资产合计：037,非流动资产合计：058,
     * 流动负债合计：089,非流动负债合计：100,营业收入：016,净利润：056,销售净利率：114,销售毛利率：115,
     * 净资产收益率ROE(加权)：029，基本每股收益：062，经营活动产生的现金流量净额/营业收入：184，总资产周转率：182，资产负债率：157）
     *
     * @param ipoH5DetailDto
     * @return
     */
    public List<IpoH5DetailDto> ipoAvg(IpoH5DetailDto ipoH5DetailDto) {
        //查询所属行业
        IpoH5DetailDto dto = ipoInterfaceBizMapper.queryIndustryCsrc(ipoH5DetailDto);
        ipoH5DetailDto.setIndustryCsrc(dto.getIndustryCsrc());
        return ipoInterfaceBizMapper.ipoAvg(ipoH5DetailDto);
    }

    /**
     * 发行数据
     * dxy
     * @param id 案例id
     * @return dto
     */
    public IpoH5IssueDataDto getIssueData(String id){
        IssueDataDto dto = issueSituationService.getIssueData(id);
        if ( dto!= null) {
            IpoH5IssueDataDto result = new IpoH5IssueDataDto();
            BeanUtil.copy(dto, result);
            return result;
        }
        return null;
    }

    /**
     * 毛利率数据加工处理
     *
     * @param industryCompareRateDtos
     * @return 加工完的数据
     */
    private IpoH5IndustryDto industryCompareRateDataProcessing(List<IndustryCompareRateDto> industryCompareRateDtos) {
        IpoH5IndustryDto industryDto = new IpoH5IndustryDto();

        List<IpoH5IndustryTitleDto> titleDtoList = new ArrayList<>();// title数据
        List<List<IpoH5IndustryBodyDto>> body = new ArrayList<>();// body数据
        for (IndustryCompareRateDto compareRateDto : industryCompareRateDtos) { //对数组进行遍历, 加工数据
            IpoH5IndustryTitleDto title = new IpoH5IndustryTitleDto();
            title.setTitle(compareRateDto.getTitle()); // 找到上方标题
            List<IpoH5IndustryBodyDto> subResultList = new ArrayList<>();// body中的数据
            List<String> companys = new ArrayList<>();// 子标题公司名字
            companys.add(compareRateDto.getCompanyName());//新增本公司名字
            List<String> yearList = new ArrayList<>();//年度
            yearList.add(compareRateDto.getFirstYear().substring(0,compareRateDto.getFirstYear().length() -1 ));
            yearList.add(compareRateDto.getSecondYear().substring(0,compareRateDto.getSecondYear().length() -1 ));
            yearList.add(compareRateDto.getThirdYear().substring(0,compareRateDto.getThirdYear().length() -1 ));
            title.setYears(yearList); // 给年赋值

            if (compareRateDto.getIndustryCompareRateDetailList().size() >= 4) {// 代表多于两家别的公司
                // 如果返回的表格中数量>= 4 代表着至少有两家别的公司,所以company 取值应该是 本公司,低的公司,高的公司

                IpoH5IndustryBodyDto firstYearbodyDto = new IpoH5IndustryBodyDto();// 公司第一年度
                IpoH5IndustryBodyDto secondYearbodyDto = new IpoH5IndustryBodyDto();// 公司第二年度
                IpoH5IndustryBodyDto thirdYearBodyDto = new IpoH5IndustryBodyDto();// 本公司第三年度

                List<IndustryCompareRateDetailDto> tmpList = compareRateDto.getIndustryCompareRateDetailList();
                // 以下为柱状图中数据
                tmpList = tmpList.subList(0, tmpList.size() - 2);

                tmpList = tmpList.stream().
                    filter(item -> item.getThirdYearRate() != null && item.getThirdYearRate().compareTo(BigDecimal.ZERO) != 0 ). // 筛选
                    sorted(Comparator.comparing(IndustryCompareRateDetailDto::getThirdYearRate)).//正序排序
                    collect(Collectors.toList());

                IndustryCompareRateDetailDto companyDto = compareRateDto.getIndustryCompareRateDetailList().get(compareRateDto.getIndustryCompareRateDetailList().size() - 1);//本公司
                IndustryCompareRateDetailDto lowCompanyDto = null;// 低的公司
                IndustryCompareRateDetailDto highCompanyDto = null;// 高的公司

                firstYearbodyDto.setYear(// 第一年
                    compareRateDto.getFirstYear().substring(0, 4));// 年度
                firstYearbodyDto.setCompany(// 本公司
                    companyDto.getFirstYearRate()
                );
                secondYearbodyDto.setYear(// 第二年
                    compareRateDto.getSecondYear().substring(0,4)//年度
                );
                secondYearbodyDto.setCompany(// 本公司
                    companyDto.getSecondYearRate()
                );
                thirdYearBodyDto.setYear(// 第三年
                    compareRateDto.getThirdYear().substring(0,4)
                );
                thirdYearBodyDto.setCompany(//本公司
                    companyDto.getThirdYearRate()
                );

                if (tmpList.size() > 0){// 有另外一个公司的话
                    lowCompanyDto = tmpList.get(0); // 低的公司
                    companys.add(lowCompanyDto.getCompanyName());// 低的公司名称
                    // 三年的低的公司
                    firstYearbodyDto.setLowLevel(
                        lowCompanyDto.getFirstYearRate()
                    );
                    secondYearbodyDto.setLowLevel(
                        lowCompanyDto.getSecondYearRate()
                    );
                    thirdYearBodyDto.setLowLevel(
                        lowCompanyDto.getThirdYearRate()
                    );
                }
                if (tmpList.size() > 1){// 有多个公司及代表有高的公司
                    highCompanyDto = tmpList.get(tmpList.size() - 1); // 高的公司
                    companys.add(highCompanyDto.getCompanyName());//高的公司名称
                    // 三年的高的公司
                    firstYearbodyDto.setHighLevel(//高的公司
                        highCompanyDto.getFirstYearRate()
                    );
                    secondYearbodyDto.setHighLevel(//高的公司
                        highCompanyDto.getSecondYearRate()
                    );
                    thirdYearBodyDto.setHighLevel(// 高的公司
                        highCompanyDto.getThirdYearRate()
                    );
                }

                // 三个对象放入数组中
                subResultList.add(firstYearbodyDto);
                subResultList.add(secondYearbodyDto);
                subResultList.add(thirdYearBodyDto);

            } else { // 只有另外一家公司
                // 如果返回的表格中数量 <4 且 > 1 代表只有一家别的公司, 把对应的公司名字放进去即可.
                if (compareRateDto.getIndustryCompareRateDetailList().size() > 1) {// 如果有另外一家公司

                    companys.add(compareRateDto.getIndustryCompareRateDetailList().get(0).getCompanyName());

                    // 以下为柱状图中数据
                    IpoH5IndustryBodyDto firstYearbodyDto = new IpoH5IndustryBodyDto();// 公司第一年度
                    firstYearbodyDto.setYear(
                            compareRateDto.getFirstYear().substring(0, compareRateDto.getFirstYear().length() - 1));// 年度
                    firstYearbodyDto.setCompany(// 本公司
                            compareRateDto.getIndustryCompareRateDetailList().get(2).getFirstYearRate()
                    );
                    firstYearbodyDto.setLowLevel(// 另外一家公司
                            compareRateDto.getIndustryCompareRateDetailList().get(0).getFirstYearRate()
                    );

                    IpoH5IndustryBodyDto secondYearbodyDto = new IpoH5IndustryBodyDto();// 公司第二年度
                    secondYearbodyDto.setYear(// 年度
                            compareRateDto.getSecondYear().substring(0, compareRateDto.getSecondYear().length() - 1)
                    );
                    secondYearbodyDto.setCompany(// 本公司
                            compareRateDto.getIndustryCompareRateDetailList().get(2).getSecondYearRate()
                    );
                    secondYearbodyDto.setLowLevel(//另外一家公司
                            compareRateDto.getIndustryCompareRateDetailList().get(0).getSecondYearRate()
                    );

                    IpoH5IndustryBodyDto thirdYearBodyDto = new IpoH5IndustryBodyDto();// 本公司第三年度
                    thirdYearBodyDto.setYear(// 年度
                            compareRateDto.getThirdYear().substring(0, compareRateDto.getThirdYear().length() - 1)
                    );
                    thirdYearBodyDto.setCompany(//本公司
                            compareRateDto.getIndustryCompareRateDetailList().get(2).getThirdYearRate()
                    );
                    thirdYearBodyDto.setLowLevel(//另外一家公司
                            compareRateDto.getIndustryCompareRateDetailList().get(0).getThirdYearRate()
                    );

                    // 三个对象放入数组中
                    subResultList.add(firstYearbodyDto);
                    subResultList.add(secondYearbodyDto);
                    subResultList.add(thirdYearBodyDto);
                } else {// 只有本公司
                    // 以下为柱状图中数据
                    IpoH5IndustryBodyDto firstYearbodyDto = new IpoH5IndustryBodyDto();// 公司第一年度
                    firstYearbodyDto.setYear(
                            compareRateDto.getFirstYear().substring(0, compareRateDto.getFirstYear().length() - 1));// 年度
                    firstYearbodyDto.setCompany(// 本公司
                            compareRateDto.getIndustryCompareRateDetailList().get(0).getFirstYearRate()
                    );

                    IpoH5IndustryBodyDto secondYearbodyDto = new IpoH5IndustryBodyDto();// 公司第二年度
                    secondYearbodyDto.setYear(// 年度
                            compareRateDto.getSecondYear().substring(0, compareRateDto.getSecondYear().length() - 1)
                    );
                    secondYearbodyDto.setCompany(// 本公司
                            compareRateDto.getIndustryCompareRateDetailList().get(0).getSecondYearRate()
                    );

                    IpoH5IndustryBodyDto thirdYearBodyDto = new IpoH5IndustryBodyDto();// 本公司第三年度
                    thirdYearBodyDto.setYear(// 年度
                            compareRateDto.getThirdYear().substring(0, compareRateDto.getThirdYear().length() - 1)
                    );
                    thirdYearBodyDto.setCompany(//本公司
                            compareRateDto.getIndustryCompareRateDetailList().get(0).getThirdYearRate()
                    );

                    // 三个对象放入数组中
                    subResultList.add(firstYearbodyDto);
                    subResultList.add(secondYearbodyDto);
                    subResultList.add(thirdYearBodyDto);
                }
            }
            title.setCompanys(companys);// 赋值公司

            titleDtoList.add(title);// 新增title
            body.add(subResultList);// 数组新增 body
        }
        industryDto.setBody(body); // 赋值
        industryDto.setTitles(titleDtoList);// 赋值
        return industryDto;
    }

    /**
     * 科技创新返回数据
     *
     * @param ipoTechnologyVo
     * @param id
     */
    private IpoH5TechnologyDto ipoTechnologyDataProcessing(IpoTechnologyVo ipoTechnologyVo, String id) {
        IpoH5TechnologyDto resultDto = new IpoH5TechnologyDto();

    IpoH5Dto dto = new IpoH5Dto();
    dto.setBid(id);
    IpoH5Dto ipoCompanyRank = ipoCompanyRank(dto);
    /**研发投入**/
    Map<String, List> devResult = ipoDevDataProcessing(ipoTechnologyVo,ipoCompanyRank);// 研发营收
    resultDto.setDevData(devResult);// 研发投入数据存储

    // 核心技术及研发技术人员
    resultDto.setCoreData(coreDevProcessing(ipoCompanyRank, id));

    return resultDto;
}

    /**
     * 研发投入数据处理
     *
     * @param ipoTechnologyVo
     * @param ipoCompanyRank
     * @return
     */
    private Map<String, List> ipoDevDataProcessing(IpoTechnologyVo ipoTechnologyVo, IpoH5Dto ipoCompanyRank) {
        Map<String, List> devResult = new HashMap<>();// 研发营收
        List<IpoH5TechnologyDevDto> incomeList = new ArrayList<>();
        List<IpoH5TechnologyDevDto> expensesCostList = new ArrayList<>();

        BigDecimal hundred = new BigDecimal(100);

        List<IpoTechnologyTableDto> devData = null;
        if (ipoTechnologyVo != null && ipoTechnologyVo.getDevData() != null){
          devData = ipoTechnologyVo.getDevData();//研发投入数据
        }
        List yearList = new ArrayList();
        if (CollectionUtils.isNotEmpty(devData) &&
                StringUtil.isNotBlank(ipoTechnologyVo.getDevDate().getFirstYearDate())) {// 时间不为空
            if (ipoTechnologyVo.getDevDate().getFirstYearDate().indexOf("12-31") >= 0) {// 若头一年时间是以12-31日为结束

                /******************第一年*******************/
                String year = "";
                if (ipoTechnologyVo.getDevDate() != null){
                  year = ipoTechnologyVo.getDevDate().getFirstYearDate();//年份赋值
                }
                IpoH5TechnologyDevDto incomeDto = new IpoH5TechnologyDevDto();// 营业收入
                IpoH5TechnologyDevDto expensesCostDto = new IpoH5TechnologyDevDto(); // 研发支出

                if (year.length() >= 4){
                  yearList.add(year.substring(0,4));
                }

                incomeDto.setYear(year);//year
                expensesCostDto.setYear(year);//year
                BigDecimal researchPlate = null;
                if (ipoCompanyRank != null && StringUtils.isNotBlank(ipoCompanyRank.getResearchPlateFiavg())){
                  researchPlate = new BigDecimal(ipoCompanyRank.getResearchPlateFiavg());//研发投入平均
                }
                BigDecimal taking = null;
                if (ipoCompanyRank != null && StringUtils.isNotBlank(ipoCompanyRank.getTakingFiavg())) {
                  taking = new BigDecimal(ipoCompanyRank.getTakingFiavg());//营业收入平均
                }
                if (devData.size() >= 0 && devData.get(0).getFirstYearValue() != null){
                  expensesCostDto.setCompany(devData.get(0).getFirstYearValue().toPlainString());//传入研发投入
                }
                if (devData.size() >= 1 && devData.get(1).getFirstYearValue() != null){
                  incomeDto.setCompany(devData.get(1).getFirstYearValue().toPlainString());//传入营业收入
                }
                if (devData.size() >= 2 && devData.get(2).getFirstYearValue() != null){
                  expensesCostDto.setProportion(devData.get(2).getFirstYearValue().toPlainString());// 公司研发占比
                }

                if (ipoCompanyRank != null ){
                   incomeDto.setSTIB(ipoCompanyRank.getTakingFiavg());//科创板平均营业收入
                   expensesCostDto.setSTIB(ipoCompanyRank.getResearchPlateFiavg());//科创板平均研发费用
                }

                if (taking != null && BigDecimal.ZERO.compareTo(taking) != 0 && researchPlate != null){
                  BigDecimal industryProportion = researchPlate.divide(taking, 4, RoundingMode.HALF_UP).multiply(hundred);// 科创版平均占比
                  expensesCostDto.setIndustryProportion(industryProportion.toString());//增加科创版平均占比
                }

                incomeList.add(incomeDto);// 第一年营业收入
                expensesCostList.add(expensesCostDto);//第一年研发支出

                /******************第二年*******************/
                year = "";
                if (ipoTechnologyVo != null && ipoTechnologyVo.getDevDate() != null) {
                  year = ipoTechnologyVo.getDevDate().getSecondYearDate();//年份赋值
                }
                incomeDto = new IpoH5TechnologyDevDto();// 营业收入
                expensesCostDto = new IpoH5TechnologyDevDto(); // 研发支出
                if (year.length() >= 4) {
                  yearList.add(year.substring(0, 4));
                }
                incomeDto.setYear(year);//year
                expensesCostDto.setYear(year);//year

              researchPlate = null;
              taking = null;
              if (ipoCompanyRank != null ) {
                if (StringUtils.isNotBlank(ipoCompanyRank.getResearchPlateSeavg())) {
                  researchPlate = new BigDecimal(ipoCompanyRank.getResearchPlateSeavg());//研发投入平均
                }
                if (StringUtils.isNotBlank(ipoCompanyRank.getTakingSeavg())){
                  taking = new BigDecimal(ipoCompanyRank.getTakingSeavg());//营业收入平均
                }
              }

              if (devData.size() >= 0 && devData.get(0).getSecondYearValue() != null){
                expensesCostDto.setCompany(devData.get(0).getSecondYearValue().toPlainString());//传入研发投入
              }
              if (devData.size() >= 1 && devData.get(1).getSecondYearValue() != null){
                incomeDto.setCompany(devData.get(1).getSecondYearValue().toPlainString());//传入营业收入
              }
              if (devData.size() >= 2 && devData.get(2).getSecondYearValue() != null){
                expensesCostDto.setProportion(devData.get(2).getSecondYearValue().toPlainString());// 公司研发占比
              }
              if (ipoCompanyRank != null) {
                incomeDto.setSTIB(ipoCompanyRank.getTakingSeavg());//科创板平均营业收入
                expensesCostDto.setSTIB(ipoCompanyRank.getResearchPlateSeavg());//科创板平均研发费用
              }
              if (taking != null && BigDecimal.ZERO.compareTo(taking) != 0 && researchPlate != null){
                BigDecimal industryProportion = researchPlate.divide(taking, 4, RoundingMode.HALF_UP).multiply(hundred);// 科创版平均占比
                expensesCostDto.setIndustryProportion(industryProportion.toString());//增加科创版平均占比
              }

              incomeList.add(incomeDto);// 第二年营业收入
              expensesCostList.add(expensesCostDto);//第二年研发支出

                /******************第三年*******************/
                year = "";
              if (ipoTechnologyVo.getDevDate() != null) {
                year = ipoTechnologyVo.getDevDate().getThirdYearDate();//年份赋值
              }
                incomeDto = new IpoH5TechnologyDevDto();// 营业收入
                expensesCostDto = new IpoH5TechnologyDevDto(); // 研发支出
              if (year.length() >= 4) {
                yearList.add(year.substring(0, 4));
              }
              incomeDto.setYear(year);//year
              expensesCostDto.setYear(year);//year

              researchPlate = null;
              taking = null;
              if (ipoCompanyRank != null ) {
                if (StringUtils.isNotBlank(ipoCompanyRank.getResearchPlateThavg())) {
                  researchPlate = new BigDecimal(ipoCompanyRank.getResearchPlateThavg());//研发投入平均
                }
                if (StringUtils.isNotBlank(ipoCompanyRank.getTakingThavg())){
                  taking = new BigDecimal(ipoCompanyRank.getTakingThavg());//营业收入平均
                }
              }

              if (devData.size() >= 0 && devData.get(0).getThirdYearValue() != null){
                expensesCostDto.setCompany(devData.get(0).getThirdYearValue().toPlainString());//传入研发投入
              }
              if (devData.size() >= 1 && devData.get(1).getThirdYearValue() != null){
                incomeDto.setCompany(devData.get(1).getThirdYearValue().toPlainString());//传入营业收入
              }
              if (devData.size() >= 2 && devData.get(2).getThirdYearValue() != null){
                expensesCostDto.setProportion(devData.get(2).getThirdYearValue().toPlainString());// 公司研发占比
              }
              if (ipoCompanyRank != null){
                incomeDto.setSTIB(ipoCompanyRank.getTakingThavg());//科创板平均营业收入
                expensesCostDto.setSTIB(ipoCompanyRank.getResearchPlateThavg());//科创板平均研发费用
              }

              if (taking != null && BigDecimal.ZERO.compareTo(taking) != 0 && researchPlate != null) {
                BigDecimal industryProportion = researchPlate.divide(taking, 4, RoundingMode.HALF_UP)
                    .multiply(hundred);// 科创版平均占比
                expensesCostDto.setIndustryProportion(industryProportion.toString());//增加科创版平均占比
              }
                incomeList.add(incomeDto);// 第三年营业收入
                expensesCostList.add(expensesCostDto);//第三年研发支出
            } else { // 头一年不是以12-31日为结束
                /******************第一年*******************/
              String year = "";
              if (ipoTechnologyVo.getDevDate() != null) {
                year = ipoTechnologyVo.getDevDate().getSecondYearDate();//年份赋值
              }
                IpoH5TechnologyDevDto incomeDto = new IpoH5TechnologyDevDto();// 营业收入
                IpoH5TechnologyDevDto expensesCostDto = new IpoH5TechnologyDevDto(); // 研发支出
              if (year.length() >= 4){
                yearList.add(year.substring(0,4));
              }

              incomeDto.setYear(year);//year
              expensesCostDto.setYear(year);//year

                if (ipoCompanyRank.getResearchPlateFiavg() != null){
                    BigDecimal researchPlate = new BigDecimal(ipoCompanyRank.getResearchPlateFiavg());//研发投入平均
                    if (ipoCompanyRank.getTakingFiavg() != null){
                        BigDecimal taking = new BigDecimal(ipoCompanyRank.getTakingFiavg());//营业收入平均
                      if (researchPlate != null && taking != null && BigDecimal.ZERO.compareTo(taking) != 0){
                        BigDecimal industryProportion = researchPlate.divide(taking, 4, RoundingMode.HALF_UP).multiply(hundred);// 科创版平均占比
                        expensesCostDto.setIndustryProportion(industryProportion.toString());//增加科创版平均占比
                      }
                    }
                }

              if (devData.size() >= 0 && devData.get(0).getSecondYearValue() != null){
                expensesCostDto.setCompany(devData.get(0).getSecondYearValue().toPlainString());//传入研发投入
              }
              if (devData.size() >= 1 && devData.get(1).getSecondYearValue() != null){
                incomeDto.setCompany(devData.get(1).getSecondYearValue().toPlainString());//传入营业收入
              }
              if (devData.size() >= 2 && devData.get(2).getSecondYearValue() != null){
                expensesCostDto.setProportion(devData.get(2).getSecondYearValue().toPlainString());// 公司研发占比
              }
              if (ipoCompanyRank != null){
                incomeDto.setSTIB(ipoCompanyRank.getTakingFiavg());//科创板平均营业收入
                expensesCostDto.setSTIB(ipoCompanyRank.getResearchPlateFiavg());//科创板平均研发费用
              }

                incomeList.add(incomeDto);// 第一年营业收入
                expensesCostList.add(expensesCostDto);//第一年研发支出
                /******************第二年*******************/

                year = "";
                if (ipoTechnologyVo.getDevDate() != null) {
                  year = ipoTechnologyVo.getDevDate().getThirdYearDate();//年份赋值
                }
                incomeDto = new IpoH5TechnologyDevDto();// 营业收入
                expensesCostDto = new IpoH5TechnologyDevDto(); // 研发支出
              if (year.length() >= 4) {
                yearList.add(year.substring(0, 4));
              }
                incomeDto.setYear(year);//year
                expensesCostDto.setYear(year);//year

                if (ipoCompanyRank.getResearchPlateSeavg() != null){
                    BigDecimal researchPlate = new BigDecimal(ipoCompanyRank.getResearchPlateSeavg());//研发投入平均
                    if (ipoCompanyRank.getTakingSeavg() != null){
                        BigDecimal taking = new BigDecimal(ipoCompanyRank.getTakingSeavg());//营业收入平均
                      if (researchPlate != null && taking != null && BigDecimal.ZERO.compareTo(taking) != 0) {
                        BigDecimal industryProportion = researchPlate.divide(taking, 4, RoundingMode.HALF_UP).multiply(hundred);// 科创版平均占比
                        expensesCostDto.setIndustryProportion(industryProportion.toString());//增加科创版平均占比
                      }
                    }
                }

              if (devData.size() >= 0 && devData.get(0).getThirdYearValue() != null){
                expensesCostDto.setCompany(devData.get(0).getThirdYearValue().toPlainString());//传入研发投入
              }
              if (devData.size() >= 1 && devData.get(1).getThirdYearValue() != null){
                incomeDto.setCompany(devData.get(1).getThirdYearValue().toPlainString());//传入营业收入
              }
              if (devData.size() >= 2 && devData.get(2).getThirdYearValue() != null){
                expensesCostDto.setProportion(devData.get(2).getThirdYearValue().toPlainString());// 公司研发占比
              }
              if (ipoCompanyRank != null){
                incomeDto.setSTIB(ipoCompanyRank.getTakingSeavg());//科创板平均营业收入
                expensesCostDto.setSTIB(ipoCompanyRank.getResearchPlateSeavg());//科创板平均研发费用
              }

              incomeList.add(incomeDto);// 第二年营业收入
              expensesCostList.add(expensesCostDto);//第二年研发支出
                /******************第三年*******************/
              year = "";
              if (ipoTechnologyVo.getDevDate() != null) {
                year = ipoTechnologyVo.getDevDate().getForthYearDate();//年份赋值
              }

              incomeDto = new IpoH5TechnologyDevDto();// 营业收入
              expensesCostDto = new IpoH5TechnologyDevDto(); // 研发支出
              if (year.length() >= 4){
                yearList.add(year.substring(0,4));
              }
              incomeDto.setYear(year);//year
              expensesCostDto.setYear(year);//year
                if (ipoCompanyRank.getResearchPlateThavg() != null){
                    BigDecimal researchPlate = new BigDecimal(ipoCompanyRank.getResearchPlateThavg());//研发投入平均
                    if (ipoCompanyRank.getTakingThavg() != null){
                        BigDecimal taking = new BigDecimal(ipoCompanyRank.getTakingThavg());//营业收入平均
                      if (researchPlate != null && taking != null && BigDecimal.ZERO.compareTo(taking) != 0) {
                        BigDecimal industryProportion = researchPlate.divide(taking, 4, RoundingMode.HALF_UP).multiply(hundred);// 科创版平均占比
                        expensesCostDto.setIndustryProportion(industryProportion.toString());//增加科创版平均占比
                      }
                    }
                }


              if (devData.size() >= 0 && devData.get(0).getForthYearValue() != null){
                expensesCostDto.setCompany(devData.get(0).getForthYearValue().toPlainString());//传入研发投入
              }
              if (devData.size() >= 1 && devData.get(1).getForthYearValue() != null){
                incomeDto.setCompany(devData.get(1).getForthYearValue().toPlainString());//传入营业收入
              }
              if (devData.size() >= 2 && devData.get(2).getForthYearValue() != null){
                expensesCostDto.setProportion(devData.get(2).getForthYearValue().toPlainString());// 公司研发占比
              }
              if (ipoCompanyRank != null){
                incomeDto.setSTIB(ipoCompanyRank.getTakingThavg());//科创板平均营业收入
                expensesCostDto.setSTIB(ipoCompanyRank.getResearchPlateThavg());//科创板平均研发费用
              }
                incomeList.add(incomeDto);// 第三年营业收入
                expensesCostList.add(expensesCostDto);//第三年研发支出
            }
        }
        incomeList.sort(new Comparator<IpoH5TechnologyDevDto>() {
            @Override
            public int compare(IpoH5TechnologyDevDto o1, IpoH5TechnologyDevDto o2) {
                return o1.getYear().compareTo(o2.getYear());
            }
        });

        expensesCostList.sort(new Comparator<IpoH5TechnologyDevDto>() {
            @Override
            public int compare(IpoH5TechnologyDevDto o1, IpoH5TechnologyDevDto o2) {
                return o1.getYear().compareTo(o2.getYear());
            }
        });

        Collections.sort(yearList);

        devResult.put("income", incomeList);
        devResult.put("expensesCost", expensesCostList);
        devResult.put("years", yearList);
        return devResult;
    }

private List<Map<String, IpoH5CoreDevDto>> coreDevProcessing(IpoH5Dto ipoCompanyRank, String id){
  // 核心技术及研发技术人员
  IpoH5CoreDevDto companyStaff = new IpoH5CoreDevDto();
  IpoH5CoreDevDto industryStaff = new IpoH5CoreDevDto();
  List<IpoH5CoreDevDto> company = ipoInterfaceBizMapper.getCoreDevFromIpoTech(id);
  if (CollectionUtils.isNotEmpty(company)){
    if (company.get(0).getIndexDate().indexOf("12-31") >= 0){// 头一年日期为12月31日
      companyStaff = company.get(0);
    } else {// 第二年日期为12月31日, 取第二年
      companyStaff = company.get(1);
    }
  }
  industryStaff.setIndexDate(companyStaff.getIndexDate());
  industryStaff.setCore(ipoCompanyRank.getResearchPeoCore());
  industryStaff.setDev(ipoCompanyRank.getResearchPeo());
  industryStaff.setPeople(ipoCompanyRank.getCompanyPeo());

  List<Map<String, IpoH5CoreDevDto>> coreData = new ArrayList<>();
  Map<String, IpoH5CoreDevDto> companyMap = new HashMap<>();
  companyMap.put("companyStaff", companyStaff);
  coreData.add(companyMap);
  Map<String, IpoH5CoreDevDto> industryMap = new HashMap<>();
  industryMap.put("industryStaff", industryStaff);
  coreData.add(industryMap);

  return coreData;
}


    //  保存用户信息
    public void saveUserInfo(Map<String, Object> userInfoMap) {
//        判断是否存在 如果存在更新 如果不存在 insert
        List<IpoWechatPermisionDto> list = ipoWechatPermisionBizMapper.queryByUnionidOrOpenid(userInfoMap);
        if (list == null || list.size() == 0) {
            ipoWechatPermisionBizMapper.insert(userInfoMap);
        } else {
            ipoWechatPermisionBizMapper.update(userInfoMap);
        }
    }

    // 提交评论
    public void submitReplay(Map<String, Object> map) {
        ipoWechatPermisionBizMapper.submitReplay(map);
    }

    // 获取评论列表
    public List<Map<String, Object>> getCommentList(Map<String, Object> param) {
        return ipoWechatPermisionBizMapper.getCommentList(param);
    }

    //    点赞
    public void fabulousYes(Map<String, Object> map) {
        List<Map<String, Object>> lists = ipoWechatPermisionBizMapper.isFabulousYes(map);
        if (lists != null && lists.size() > 0) {
//            更新
            ipoWechatPermisionBizMapper.fabulousYes(map);
        } else {
//            保存
            ipoWechatPermisionBizMapper.fabulousNo(map);
        }
    }
    //    收藏
    public void collectionYes(Map<String, Object> map) {
        map.put("option_time",new Date());
        List<Map<String, Object>> lists = ipoWechatPermisionBizMapper.isCollectionYes(map);
        if (lists != null && lists.size() > 0) {
//            更新
            ipoWechatPermisionBizMapper.collectionYes(map);
        } else {
//            保存
            ipoWechatPermisionBizMapper.collectionNo(map);
        }
    }

    //是否点赞
    public boolean isFabulousYes(Map<String, Object> param) {
        List<Map<String, Object>> lists = ipoWechatPermisionBizMapper.isFabulousYes(param);
        boolean fabulousYes = false;
        if (lists != null && lists.size() > 0 && "1".equals(lists.get(0).get("isLike") + "")) {
            fabulousYes = true;
        }
        return fabulousYes;
    }

    //  点赞数
    public int fabulousCount(Map<String, Object> param) {
        return ipoWechatPermisionBizMapper.fabulousCount(param);
    }


    public List<IpoCaseListVo> queryIpoCase(Map<String, Object> map) {
        return ipoInterfaceBizMapper.queryIpoCase(map);
    }

    public List<IpoCaseListVo> queryIpoCaseById(Map<String, Object> map) {
        return ipoInterfaceBizMapper.queryIpoCaseById(map);
    }

    public List<IpoCaseListVo> otherIpoCaseNoIndustry(IpoCaseIndexDto ipoCaseIndexDto) {
        String URL = "http://www.valueonline.cn/open/";
        List<IpoCaseListVo> resultList = ipoInterfaceBizMapper.otherIpoCaseNoIndustry(ipoCaseIndexDto);
        for(IpoCaseListVo dto:resultList){
            URL = URL + dto.getCompanyCode() + "_160.png";
            dto.setCompanyLogo(URL);
        }
        return resultList;
    }

    public IntermediaryOrgDto queryOrgMarketShare(IntermediaryOrgDto intermediaryOrgDto) {
        return ipoInterfaceBizMapper.queryOrgMarketShare(intermediaryOrgDto);
    }

    public List<IpoCaseListVo> queryAllMatchIpoCase() {
        return ipoInterfaceBizMapper.queryAllMatchIpoCase();
    }

    public int collectionCount(Map<String, Object> param) {
        return ipoWechatPermisionBizMapper.collectionCount(param);
    }

    //是否收藏
    public boolean isCollectionYes(Map<String, Object> param) {
        List<Map<String, Object>> lists = ipoWechatPermisionBizMapper.isCollectionYes(param);
        boolean isCollectionYes = false;
        if (lists != null && lists.size() > 0 && "1".equals(lists.get(0).get("state") + "")) {
            isCollectionYes = true;
        }
        return isCollectionYes;
    }

    public int replayCount(Map<String, Object> param) {
        return ipoWechatPermisionBizMapper.replayCount(param);
    }
}
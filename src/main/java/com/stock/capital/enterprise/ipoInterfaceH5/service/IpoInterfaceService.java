package com.stock.capital.enterprise.ipoInterfaceH5.service;

import com.google.common.collect.Maps;
import com.netflix.discovery.converters.Auto;
import com.stock.capital.enterprise.common.constant.Global;
import com.stock.capital.enterprise.ipoCase.dao.IpoCaseListMapper;
import com.stock.capital.enterprise.ipoCase.dto.*;
import com.stock.capital.enterprise.ipoCase.service.CompanyOverviewService;
import com.stock.capital.enterprise.ipoCase.service.IssueSituationService;
import com.stock.capital.enterprise.ipoInterfaceH5.dao.IpoInterfaceBizMapper;
import com.stock.capital.enterprise.ipoInterfaceH5.dto.IpoH5DetailDto;
import com.stock.capital.enterprise.ipoInterfaceH5.dto.IpoH5Dto;
import com.stock.capital.enterprise.ipoInterfaceH5.dto.IpoH5IndustryBodyDto;
import com.stock.capital.enterprise.ipoInterfaceH5.dto.IpoH5IndustryDto;
import com.stock.capital.enterprise.ipoInterfaceH5.dto.IpoH5IndustryTitleDto;
import com.stock.core.service.BaseService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = { Exception.class })
public class IpoInterfaceService extends BaseService {

  @Autowired
  private CompanyOverviewService companyOverviewService;

  @Autowired
  private IssueSituationService issueSituationService;

  @Autowired
  private IpoCaseListMapper ipoCaseListMapper;

  @Autowired
  private IpoInterfaceBizMapper ipoInterfaceBizMapper;

  /**
   * dxy
   * 科技创新页面
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
    // 新增一个数组, 把年份排序放入
    List yearList = new ArrayList();
    for (IndustryCompareRateDto compareRateDto : industryCompareRateDtos) {
      yearList.add(compareRateDto.getFirstYear());
      yearList.add(compareRateDto.getSecondYear());
      yearList.add(compareRateDto.getThirdYear());
    }
    Collections.sort(yearList);

//    result.put("companyInfo", companyOverviewVo);
    result.put("industryStatusInfo", industryStatusDtoList);
    result.put("mainCompetitorInfo", mainCompetitorInfoDtoList);
    result.put("technologyInfo", ipoTechnologyVo);
    result.put("industryCompareRateInfo", industryCompareRateDataProcessing(industryCompareRateDtos));//毛利率数据处理加工
    result.put("sortYear", yearList);
    return result;
  }

  /**
   * 毛利率数据加工处理
   * @param industryCompareRateDtos
   * @return 加工完的数据
   */
  private IpoH5IndustryDto industryCompareRateDataProcessing(List<IndustryCompareRateDto> industryCompareRateDtos){
    IpoH5IndustryDto industryDto = new IpoH5IndustryDto();

    List<IpoH5IndustryTitleDto> titleDtoList = new ArrayList<>();// title数据
    List<List<IpoH5IndustryBodyDto>> body = new ArrayList<>();// body数据
    for (IndustryCompareRateDto compareRateDto : industryCompareRateDtos) { //对数组进行遍历, 加工数据
      IpoH5IndustryTitleDto title = new IpoH5IndustryTitleDto();
      title.setTitle(compareRateDto.getTitle()); // 找到上方标题
      List<IpoH5IndustryBodyDto> subResultList = new ArrayList<>();// body中的数据
      List<String> companys = new ArrayList<>();// 子标题公司名字
      companys.add(compareRateDto.getCompanyName());//新增本公司名字


      if (compareRateDto.getIndustryCompareRateDetailList().size() >= 4){// 代表多于两家别的公司
        // 如果返回的表格中数量>= 4 代表着至少有两家别的公司,所以company 取值应该是 本公司,低的公司,高的公司
        companys.add("低的公司");
        companys.add("高的公司");

        List<IndustryCompareRateDetailDto> tmpList = compareRateDto.getIndustryCompareRateDetailList();
        // 以下为柱状图中数据
        tmpList = tmpList.subList(0, tmpList.size() -2);
        tmpList.sort(new Comparator<IndustryCompareRateDetailDto>() {
          @Override
          public int compare(IndustryCompareRateDetailDto o1, IndustryCompareRateDetailDto o2) {
            return o1.getFirstYearRate().compareTo(o2.getFirstYearRate());
          }
        });

        IpoH5IndustryBodyDto firstYearbodyDto = new IpoH5IndustryBodyDto();// 公司第一年度
        firstYearbodyDto.setYear(
            compareRateDto.getFirstYear().substring(0,compareRateDto.getFirstYear().length() - 1));// 年度
        firstYearbodyDto.setCompany(// 本公司
            compareRateDto.getIndustryCompareRateDetailList().get(compareRateDto.getIndustryCompareRateDetailList().size() -1).getFirstYearRate()
        );
        firstYearbodyDto.setLowLevel(// 低的公司
            tmpList.get(0).getFirstYearRate()
        );
        firstYearbodyDto.setHighLevel(//高的公司
            tmpList.get(tmpList.size() -1).getFirstYearRate()
        );

        tmpList.sort(new Comparator<IndustryCompareRateDetailDto>() {
          @Override
          public int compare(IndustryCompareRateDetailDto o1, IndustryCompareRateDetailDto o2) {
            return o1.getSecondYearRate().compareTo(o2.getSecondYearRate());
          }
        });
        IpoH5IndustryBodyDto secondYearbodyDto = new IpoH5IndustryBodyDto();// 公司第二年度
        secondYearbodyDto.setYear(// 年度
            compareRateDto.getSecondYear().substring(0,compareRateDto.getSecondYear().length()-1)
        );
        secondYearbodyDto.setCompany(// 本公司
            compareRateDto.getIndustryCompareRateDetailList().get(compareRateDto.getIndustryCompareRateDetailList().size() -1).getSecondYearRate()
        );
        secondYearbodyDto.setLowLevel(//低的公司
            tmpList.get(0).getSecondYearRate()
        );
        secondYearbodyDto.setHighLevel(//高的公司
            tmpList.get(tmpList.size() -1).getSecondYearRate()
        );

        tmpList.sort(new Comparator<IndustryCompareRateDetailDto>() {
          @Override
          public int compare(IndustryCompareRateDetailDto o1, IndustryCompareRateDetailDto o2) {
            return o1.getThirdYearRate().compareTo(o2.getThirdYearRate());
          }
        });

        IpoH5IndustryBodyDto thirdYearBodyDto = new IpoH5IndustryBodyDto();// 本公司第三年度
        thirdYearBodyDto.setYear(// 年度
            compareRateDto.getThirdYear().substring(0, compareRateDto.getThirdYear().length()-1)
        );
        thirdYearBodyDto.setCompany(//本公司
            compareRateDto.getIndustryCompareRateDetailList().get(compareRateDto.getIndustryCompareRateDetailList().size() -1).getThirdYearRate()
        );
        thirdYearBodyDto.setLowLevel(//低的公司
            tmpList.get(0).getThirdYearRate()
        );
        thirdYearBodyDto.setHighLevel(//高的公司
            tmpList.get(tmpList.size() -1).getThirdYearRate()
        );

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
              compareRateDto.getFirstYear().substring(0,compareRateDto.getFirstYear().length() - 1));// 年度
          firstYearbodyDto.setCompany(// 本公司
              compareRateDto.getIndustryCompareRateDetailList().get(2).getFirstYearRate()
          );
          firstYearbodyDto.setLowLevel(// 另外一家公司
              compareRateDto.getIndustryCompareRateDetailList().get(0).getFirstYearRate()
          );

          IpoH5IndustryBodyDto secondYearbodyDto = new IpoH5IndustryBodyDto();// 公司第二年度
          secondYearbodyDto.setYear(// 年度
              compareRateDto.getSecondYear().substring(0,compareRateDto.getSecondYear().length()-1)
          );
          secondYearbodyDto.setCompany(// 本公司
              compareRateDto.getIndustryCompareRateDetailList().get(2).getSecondYearRate()
          );
          secondYearbodyDto.setLowLevel(//另外一家公司
              compareRateDto.getIndustryCompareRateDetailList().get(0).getSecondYearRate()
          );

          IpoH5IndustryBodyDto thirdYearBodyDto = new IpoH5IndustryBodyDto();// 本公司第三年度
          thirdYearBodyDto.setYear(// 年度
              compareRateDto.getThirdYear().substring(0, compareRateDto.getThirdYear().length()-1)
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
              compareRateDto.getFirstYear().substring(0,compareRateDto.getFirstYear().length() - 1));// 年度
          firstYearbodyDto.setCompany(// 本公司
              compareRateDto.getIndustryCompareRateDetailList().get(0).getFirstYearRate()
          );

          IpoH5IndustryBodyDto secondYearbodyDto = new IpoH5IndustryBodyDto();// 公司第二年度
          secondYearbodyDto.setYear(// 年度
              compareRateDto.getSecondYear().substring(0,compareRateDto.getSecondYear().length()-1)
          );
          secondYearbodyDto.setCompany(// 本公司
              compareRateDto.getIndustryCompareRateDetailList().get(0).getSecondYearRate()
          );

          IpoH5IndustryBodyDto thirdYearBodyDto = new IpoH5IndustryBodyDto();// 本公司第三年度
          thirdYearBodyDto.setYear(// 年度
              compareRateDto.getThirdYear().substring(0, compareRateDto.getThirdYear().length()-1)
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
     * 获取下拉框数据
     * @return
     */
    public Map<String,Object> querySelectData() {
        Map<String,Object> result = Maps.newHashMap();
//        所属行业（证监局）
        List<RegTreeDto> sfcList = ipoCaseListMapper.getTreeTagByCode(Global.IPO_SFC);
//        证监会行业
        List<RegTreeDto> industryCrscList = ipoCaseListMapper.getLabelByCode(Global.IPO_SFC_INDUSTRY);
//        审核结果
        List<RegTreeDto> verifyResultList = ipoCaseListMapper.getLabelByCode(Global.IPO_VERIFY_RESULT);

        result.put("sfcList",sfcList);
        result.put("industryCrscList",industryCrscList);
        result.put("verifyResultList",verifyResultList);
        return result;
    }

    public List<IpoCaseIndexDto> otherIpoCase(IpoCaseIndexDto ipoCaseIndexDto) {
        return ipoInterfaceBizMapper.otherIpoCase(ipoCaseIndexDto);
    }

    /** 行业排名
     *  必传：bid 案例主键
     * @param ipoH5Dto
     * @return
     */
    public IpoH5Dto ipoCompanyRank(IpoH5Dto ipoH5Dto) {
        return ipoInterfaceBizMapper.ipoCompanyRank(ipoH5Dto);
    }

    /** 行业平均
     *  必传：plateType 板块类型（科创板：0，创业板：1）
     *  bid 案例主键
     *  columnComment 指标类型（具体有：经营活动产生的现金流量净额：046，现金及现金等价物净增加额：091,流动资产/总资产：150,非流动资产/总资产：151,流动负债/负债合计：155,非流动负债/负债合计：156,营业收入：016,净利润：056,销售净利率：114,销售毛利率：115）
     * @param ipoH5DetailDto
     * @return
     */
    public List<IpoH5DetailDto> ipoAvg(IpoH5DetailDto ipoH5DetailDto) {
        //查询所属行业
        IpoH5DetailDto dto = ipoInterfaceBizMapper.queryIndustryCsrc(ipoH5DetailDto);
        ipoH5DetailDto.setIndustryCsrc(dto.getIndustryCsrc());
        return ipoInterfaceBizMapper.ipoAvg(ipoH5DetailDto);
    }
}

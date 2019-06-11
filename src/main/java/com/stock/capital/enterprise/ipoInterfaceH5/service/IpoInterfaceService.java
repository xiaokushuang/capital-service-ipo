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
import com.stock.core.service.BaseService;
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
    IpoTechnologyVo ipoTechnologyVo = companyOverviewService.getPatentData(id); // 科技创新
    List<IndustryCompareRateDto> industryCompareRateDtos = issueSituationService.getIndustryRateData(id);// 同行业上市公司综合毛利率接口
//    result.put("companyInfo", companyOverviewVo);
    result.put("industryStatusInfo", industryStatusDtoList);
    result.put("mainCompetitorInfo", mainCompetitorInfoDtoList);
    result.put("technologyInfo", ipoTechnologyVo);
    result.put("industryCompareRateInfo", industryCompareRateDtos);
    return result;
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

    /**
     *  必传：bid 案例主键
     * @param ipoH5Dto
     * @return
     */
    public IpoH5Dto ipoCompanyRank(IpoH5Dto ipoH5Dto) {
        return ipoInterfaceBizMapper.ipoCompanyRank(ipoH5Dto);
    }

    /**
     *  必传：orgCode 公司code，columnComment 指标类型（具体有："经营活动产生的现金流量净额","现金及现金等价物净增加额","流动资产/总资产","非流动资产/总资产","流动负债/负债合计","非流动负债/负债合计","营业收入","净利润","销售净利率,","销售毛利率"）
     * @param ipoH5DetailDto
     * @return
     */
    public List<IpoH5DetailDto> ipoAvg(IpoH5DetailDto ipoH5DetailDto) {
        return ipoInterfaceBizMapper.ipoAvg(ipoH5DetailDto);
    }
}

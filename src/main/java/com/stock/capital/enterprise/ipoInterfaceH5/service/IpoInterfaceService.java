package com.stock.capital.enterprise.ipoInterfaceH5.service;

import com.google.common.collect.Maps;
import com.netflix.discovery.converters.Auto;
import com.stock.capital.enterprise.common.constant.Global;
import com.stock.capital.enterprise.ipoCase.dao.IpoCaseListMapper;
import com.stock.capital.enterprise.ipoCase.dto.RegTreeDto;
import com.stock.capital.enterprise.ipoCase.dto.CompanyOverviewVo;
import com.stock.capital.enterprise.ipoCase.dto.IndustryCompareRateDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoTechnologyVo;
import com.stock.capital.enterprise.ipoCase.dto.IssuerIndustryStatusDto;
import com.stock.capital.enterprise.ipoCase.dto.MainCompetitorInfoDto;
import com.stock.capital.enterprise.ipoCase.service.CompanyOverviewService;
import com.stock.capital.enterprise.ipoCase.service.IssueSituationService;
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

  /**
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



    @Autowired
    private IpoCaseListMapper ipoCaseListMapper;

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
}

package com.stock.capital.enterprise.ipoCase.service;

import static java.math.BigDecimal.ROUND_HALF_DOWN;

import com.stock.capital.enterprise.ipoCase.dao.IpoCaseBizMapper;
import com.stock.capital.enterprise.ipoCase.dao.IpoIssuerIndustryStatusBizMapper;
import com.stock.capital.enterprise.ipoCase.dto.CompanyOverviewVo;
import com.stock.capital.enterprise.ipoCase.dto.HeadDataVo;
import com.stock.capital.enterprise.ipoCase.dto.IntermediaryOrgDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoPersonInfoDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoSplitDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoTechnologyDateDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoTechnologyPatentDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoTechnologyRemarksDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoTechnologyTableDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoTechnologyVo;
import com.stock.capital.enterprise.ipoCase.dto.IpoValuationDto;
import com.stock.capital.enterprise.ipoCase.dto.MainCompetitorInfoDto;
import com.stock.capital.enterprise.ipoCase.dto.MainIncomeInfoDto;
import com.stock.capital.enterprise.ipoCase.dto.MainIncomeVo;
import com.stock.capital.enterprise.ipoCase.dto.OtherMarketInfoDto;
import com.stock.capital.enterprise.ipoCase.dto.SupplierCustomerMainDto;
import com.stock.capital.enterprise.ipoCase.dto.IssuerIndustryStatusDto;
import com.stock.core.service.BaseService;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CompanyOverviewService extends BaseService {

    @Autowired
    private IpoCaseBizMapper ipoCaseBizMapper;
    
    @Autowired
    private IpoIssuerIndustryStatusBizMapper ipoIssuerIndustryStatusBizMapper;

    @Value("#{app['file.viewPath']}")
    private String fileViewPath;

    /**
     * 案例基础信息
     *
     * @param id 案例id
     * @return companyOverviewVo
     */
    public CompanyOverviewVo getIpoCaseDetail(String id) {
        return ipoCaseBizMapper.getIpoCaseDetail(id);
    }

    /**
     * 登陆其他资本市场信息
     *
     * @param id 案例id
     * @return list
     */
    public List<OtherMarketInfoDto> getMarketData(String id) {
        return ipoCaseBizMapper.getIpoMarketData(id);
    }

    /**
     * 登陆其他资本市场信息
     *
     * @param id 案例id
     * @return list
     */
    public List<IpoSplitDto> getSpliteData(String id) {
        List<IpoSplitDto> list = ipoCaseBizMapper.getSpliteData(id);
        for (IpoSplitDto ipoSplitDto : list) {
            String fileType = ipoSplitDto.getSplitFileName().substring(ipoSplitDto.getSplitFileName().lastIndexOf("."));
            String baseUrl = fileViewPath + "open/ipoFile/" + ipoSplitDto.getSplitFileId() + fileType;
            ipoSplitDto.setFilePath(baseUrl);
        }
        return list;
    }

    /**
     * 最近一次估值情况
     *
     * @param id 案例id
     * @return list
     */
    public List<IpoValuationDto> getVluationData(String id) {
        return ipoCaseBizMapper.getVluationData(id);
    }

    /**
     * 股东信息
     *
     * @param id 案例id
     * @return list
     */
    public List<IpoPersonInfoDto> getShareData(String id) {
        return ipoCaseBizMapper.getIpoShareData(id);
    }

    /**
     * 主要竞争对手简介
     *
     * @param id 案例id
     * @return list
     */
    public List<MainCompetitorInfoDto> getCompetitorData(String id) {
        return ipoCaseBizMapper.getIpoCompetitorData(id);
    }

    /**
     *  科技创新接口
     *
     * @param bid 案例id
     * @return list
     */
    public IpoTechnologyVo getPatentData(String bid) {

        IpoTechnologyVo result = new IpoTechnologyVo();

        //专利情况
        List<IpoTechnologyPatentDto> patent =ipoCaseBizMapper.getCompetitorData(bid);
        // TODO: 2019/5/15 对专利情况数据做数据处理  
        patent = getPatent(patent);
        // 研发投入
        List<IpoTechnologyTableDto> dev = ipoCaseBizMapper.getDevCompute(bid);
        // 核心技术人员
        List<IpoTechnologyTableDto> core = ipoCaseBizMapper.getCoreCompute(bid);
        // 时间
        List<IpoTechnologyDateDto> date = ipoCaseBizMapper.getDate(bid);
        // 备注
        IpoTechnologyRemarksDto remarks = ipoCaseBizMapper.getRemarks(bid);

        result.setPatentData(patent);
        result.setDevData(dev);
        result.setCoreData(core);
        result.setRemarksData(remarks);
        if (date != null){
            if (date.size() > 0) {
                result.setDevDate(date.get(0));
            }
            if (date.size() > 1){
                result.setCoreDate(date.get(1));
            }
        }

        return result;
    }




    /**
     * 发行人的行业地位
     *
     * @param id 案例id
     * @return list
     */
    public List<IssuerIndustryStatusDto> getindustryStatusData(String id) {
        return ipoIssuerIndustryStatusBizMapper.getindustryStatusData(id);
    }    
    
    /**
     * 报告期主要供应商及客户情况
     *
     * @param id 案例id
     * @return list
     */
    public Map<String, List<SupplierCustomerMainDto>> getSupCusData(String id) {
        Map<String, List<SupplierCustomerMainDto>> result = new HashMap<>();
        //报告期前五名供应商情况
        List<SupplierCustomerMainDto> supplierList =
            ipoCaseBizMapper.getSupplierOrCustomerData(id, "1");
        if (supplierList != null && !supplierList.isEmpty()) {
            for (SupplierCustomerMainDto supplierMainDto : supplierList) {
                int lastYearSupplier = getYearByDate(supplierMainDto.getReportPeriod());
                supplierMainDto.setFirstYearForSupplier((lastYearSupplier - 3) + "-12-31");
                supplierMainDto.setSecondYearForSupplier((lastYearSupplier - 2) + "-12-31");
                supplierMainDto.setThirdYearForSupplier((lastYearSupplier - 1) + "-12-31");
            }
            result.put("supplierMainList", supplierList);
        }
        //报告期前五名客户情况
        List<SupplierCustomerMainDto> customerList =
            ipoCaseBizMapper.getSupplierOrCustomerData(id, "2");
        if (customerList != null && !customerList.isEmpty()) {
            for (SupplierCustomerMainDto customerMainDto : customerList) {
                int lastYearCustomer = getYearByDate(customerMainDto.getReportPeriod());
                customerMainDto.setFirstYearForCustomer((lastYearCustomer - 3) + "-12-31");
                customerMainDto.setSecondYearForCustomer((lastYearCustomer - 2) + "-12-31");
                customerMainDto.setThirdYearForCustomer((lastYearCustomer - 1) + "-12-31");
            }
            result.put("customerMainList", customerList);
        }
        return result;
    }

    /**
     * 主营业务收入构成
     *
     * @param id 案例id
     * @return list
     */
    public MainIncomeVo getIncomeData(String id) {
        MainIncomeVo mainIncomeVo = new MainIncomeVo();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<MainIncomeInfoDto> mainIncomeInfoList = ipoCaseBizMapper.getIncomeData(id);
        if (mainIncomeInfoList != null && !mainIncomeInfoList.isEmpty()) {
            int lastYear = getYearByDate(mainIncomeInfoList.get(0).getReportPeriod());
            mainIncomeVo.setFirstYearForIncome((lastYear - 3) + "-12-31");
            mainIncomeVo.setSecondYearForIncome((lastYear - 2) + "-12-31");
            mainIncomeVo.setThirdYearForIncome((lastYear - 1) + "-12-31");
            mainIncomeVo
                .setOnePeriodForIncome(sdf.format(mainIncomeInfoList.get(0).getReportPeriod()));
            BigDecimal onePeriodAmountS = BigDecimal.ZERO;
            BigDecimal onePeriodRatioS = BigDecimal.ZERO;
            BigDecimal thirdYearAmountS = BigDecimal.ZERO;
            BigDecimal thirdYearRatioS = BigDecimal.ZERO;
            BigDecimal secondYearAmountS = BigDecimal.ZERO;
            BigDecimal secondYearRatioS = BigDecimal.ZERO;
            BigDecimal firstYearAmountS = BigDecimal.ZERO;
            BigDecimal firstYearRatioS = BigDecimal.ZERO;
            for (MainIncomeInfoDto dto : mainIncomeInfoList) {
                if (dto.getOnePeriodAmount() != null) {
                    onePeriodAmountS = onePeriodAmountS.add(dto.getOnePeriodAmount());
                }
                if (dto.getOnePeriodRatio() != null) {
                    onePeriodRatioS = onePeriodRatioS.add(dto.getOnePeriodRatio());
                }
                if (dto.getThirdYearAmount() != null) {
                    thirdYearAmountS = thirdYearAmountS.add(dto.getThirdYearAmount());
                }
                if (dto.getThirdYearRatio() != null) {
                    thirdYearRatioS = thirdYearRatioS.add(dto.getThirdYearRatio());
                }
                if (dto.getSecondYearAmount() != null) {
                    secondYearAmountS = secondYearAmountS.add(dto.getSecondYearAmount());
                }
                if (dto.getSecondYearRatio() != null) {
                    secondYearRatioS = secondYearRatioS.add(dto.getSecondYearRatio());
                }
                if (dto.getFirstYearAmount() != null) {
                    firstYearAmountS = firstYearAmountS.add(dto.getFirstYearAmount());
                }
                if (dto.getFirstYearRatio() != null) {
                    firstYearRatioS = firstYearRatioS.add(dto.getFirstYearRatio());
                }
            }
            MainIncomeInfoDto sumDto = new MainIncomeInfoDto();
            sumDto.setBusinessName("合计");
            sumDto.setOnePeriodAmount(onePeriodAmountS);
            sumDto.setOnePeriodRatio(onePeriodRatioS);
            sumDto.setThirdYearAmount(thirdYearAmountS);
            sumDto.setThirdYearRatio(thirdYearRatioS);
            sumDto.setSecondYearAmount(secondYearAmountS);
            sumDto.setSecondYearRatio(secondYearRatioS);
            sumDto.setFirstYearAmount(firstYearAmountS);
            sumDto.setFirstYearRatio(firstYearRatioS);
            mainIncomeInfoList.add(sumDto);
            mainIncomeVo.setMainIncomeInfoList(mainIncomeInfoList);
        }
        return mainIncomeVo;
    }

    /**
     * 中介机构信息
     *
     * @param id        案例id
     * @param validFlag 状态 0:失效 1:生效
     * @return map
     */
    public Map<String, List<IntermediaryOrgDto>> getIntermediaryOrgData(
        String id, String validFlag) {
        Map<String, List<IntermediaryOrgDto>> result = new HashMap<>();
        List<IntermediaryOrgDto> mainList = new ArrayList<>();
        List<IntermediaryOrgDto> moreList = new ArrayList<>();
        List<IntermediaryOrgDto> intermediaryOrgList =
            ipoCaseBizMapper.getIntermediaryOrgData(id, validFlag);
        if (intermediaryOrgList != null && !intermediaryOrgList.isEmpty()) {
            for (IntermediaryOrgDto intermediaryOrgDto : intermediaryOrgList) {
                if (intermediaryOrgDto.getIntermediaryType() != null) {
                    // 保荐机构
                    if ("1".equals(intermediaryOrgDto.getIntermediaryType())) {
                        mainList.add(intermediaryOrgDto);
                    }
                    // 证券公司
                    if ("2".equals(intermediaryOrgDto.getIntermediaryType())) {
                        moreList.add(intermediaryOrgDto);
                    }
                    // 律师事务所
                    if ("3".equals(intermediaryOrgDto.getIntermediaryType())) {
                        if (intermediaryOrgDto.getIntermediaryTypeName() != null && "律师事务所"
                            .equals(intermediaryOrgDto.getIntermediaryTypeName())) {
                            mainList.add(intermediaryOrgDto);
                        } else {
                            moreList.add(intermediaryOrgDto);
                        }
                    }
                    // 会计事务所
                    if ("4".equals(intermediaryOrgDto.getIntermediaryType())) {
                        if (intermediaryOrgDto.getIntermediaryTypeName() != null && "会计师事务所"
                            .equals(intermediaryOrgDto.getIntermediaryTypeName())) {
                            mainList.add(intermediaryOrgDto);
                        } else {
                            moreList.add(intermediaryOrgDto);
                        }
                    }
                    // 资产评估机构
                    if ("5".equals(intermediaryOrgDto.getIntermediaryType())) {
                        moreList.add(intermediaryOrgDto);
                    }

                    // 存托机构
                    if ("6".equals(intermediaryOrgDto.getIntermediaryType())) {
                        moreList.add(intermediaryOrgDto);
                    }
                }
            }
        }
        //主要机构
        result.put("mainList", mainList);
        //更多机构
        result.put("moreList", moreList);
        return result;
    }

    /**
     * 查询案例详情页头部展示数据
     *
     * @param id
     * @return
     */
    public HeadDataVo getHeadData(String id) {
        return ipoCaseBizMapper.getHeadData(id);
    }

    /**
     * 取日期中年份
     *
     * @param date 日期
     * @return int
     */
    private int getYearByDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.YEAR);
    }

    /**
     * 对专利情况数据进行加工
     * @param patent
     * @return
     */
    private List<IpoTechnologyPatentDto> getPatent(List<IpoTechnologyPatentDto> patent){
        if (patent != null) {
            if (patent.size() > 0){
                BigDecimal firstHj = patent.get(0).getHj();
                BigDecimal secondHj = patent.get(1).getHj();
                BigDecimal thirdHj = patent.get(2).getHj();

                BigDecimal firstZb = null;
                BigDecimal secondZb = null;
                BigDecimal thirdZb = null;
                if (firstHj != null && thirdHj != null){
                   firstZb = firstHj.divide(thirdHj, 4, ROUND_HALF_DOWN)
                    .multiply(new BigDecimal(100));
                }
                if (secondHj != null && thirdHj != null){
                    secondZb = secondHj.divide(thirdHj, 4, ROUND_HALF_DOWN)
                        .multiply(new BigDecimal(100));
                }
                if (thirdHj != null) {
                    thirdZb = thirdHj.divide(thirdHj, 4, ROUND_HALF_DOWN)
                        .multiply(new BigDecimal(100));
                }
                patent.get(0).setZb(firstZb);
                patent.get(1).setZb(secondZb);
                patent.get(2).setZb(thirdZb);

                IpoTechnologyPatentDto hjRow = new IpoTechnologyPatentDto();
                hjRow.setLabelName("占比");
                if (patent.get(2).getFm() != null && thirdHj != null) {
                    hjRow.setFm(patent.get(2).getFm().divide(thirdHj, 4, ROUND_HALF_DOWN)
                        .multiply(new BigDecimal(100)));
                }
                if (patent.get(2).getSy() != null && thirdHj != null) {
                    hjRow.setSy(patent.get(2).getSy().divide(thirdHj, 4, ROUND_HALF_DOWN)
                        .multiply(new BigDecimal(100)));
                }
                if (patent.get(2).getWg() != null && thirdHj != null) {
                    hjRow.setWg(patent.get(2).getWg().divide(thirdHj, 4, ROUND_HALF_DOWN)
                        .multiply(new BigDecimal(100)));
                }
                if (thirdHj != null) {
                    hjRow.setHj(
                        thirdHj.divide(thirdHj, 4, ROUND_HALF_DOWN).multiply(new BigDecimal(100)));
                }
                patent.add(hjRow);
            }
        }
        return patent;
    }
}

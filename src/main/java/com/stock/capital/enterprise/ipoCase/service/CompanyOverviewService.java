package com.stock.capital.enterprise.ipoCase.service;

import com.stock.capital.enterprise.ipoCase.dao.IpoCaseBizMapper;
import com.stock.capital.enterprise.ipoCase.dto.CompanyOverviewVo;
import com.stock.capital.enterprise.ipoCase.dto.IntermediaryOrgDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoPersonInfoDto;
import com.stock.capital.enterprise.ipoCase.dto.MainCompetitorInfoDto;
import com.stock.capital.enterprise.ipoCase.dto.MainIncomeInfoDto;
import com.stock.capital.enterprise.ipoCase.dto.MainIncomeVo;
import com.stock.capital.enterprise.ipoCase.dto.OtherMarketInfoDto;
import com.stock.capital.enterprise.ipoCase.dto.SupplierCustomerInfoDto;
import com.stock.capital.enterprise.ipoCase.dto.SupplierCustomerVo;
import com.stock.core.service.BaseService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyOverviewService extends BaseService {

    @Autowired
    private IpoCaseBizMapper ipoCaseBizMapper;

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
     * 报告期主要供应商及客户情况
     *
     * @param id 案例id
     * @return list
     */
    public SupplierCustomerVo getSupCusData(String id) {
        SupplierCustomerVo supplierCustomerVo = new SupplierCustomerVo();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //报告期前五名供应商情况
        List<SupplierCustomerInfoDto> supplierInfoList =
            ipoCaseBizMapper.getSupplierOrCustomerData(id, "1");
        if (supplierInfoList != null && !supplierInfoList.isEmpty()) {
            supplierCustomerVo.setSupplierInfoList(supplierInfoList);
            int lastYearSupplier = getYearByDate(supplierInfoList.get(0).getReportPeriod());
            supplierCustomerVo.setFirstYearForSupplier((lastYearSupplier - 3) + "-12-31");
            supplierCustomerVo.setSecondYearForSupplier((lastYearSupplier - 2) + "-12-31");
            supplierCustomerVo.setThirdYearForSupplier((lastYearSupplier - 1) + "-12-31");
            supplierCustomerVo
                .setOnePeriodForSupplier(sdf.format(supplierInfoList.get(0).getReportPeriod()));
        }
        //报告期前五名客户情况
        List<SupplierCustomerInfoDto> customerInfoList =
            ipoCaseBizMapper.getSupplierOrCustomerData(id, "2");
        if (customerInfoList != null && !customerInfoList.isEmpty()) {
            supplierCustomerVo.setCustomerInfoList(customerInfoList);
            int lastYearCustomer = getYearByDate(customerInfoList.get(0).getReportPeriod());
            supplierCustomerVo.setFirstYearForCustomer((lastYearCustomer - 3) + "-12-31");
            supplierCustomerVo.setSecondYearForCustomer((lastYearCustomer - 2) + "-12-31");
            supplierCustomerVo.setThirdYearForCustomer((lastYearCustomer - 1) + "-12-31");
            supplierCustomerVo
                .setOnePeriodForCustomer(sdf.format(customerInfoList.get(0).getReportPeriod()));
        }
        return supplierCustomerVo;
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
            mainIncomeVo.setMainIncomeInfoList(mainIncomeInfoList);
            int lastYear = getYearByDate(mainIncomeInfoList.get(0).getReportPeriod());
            mainIncomeVo.setFirstYearForIncome((lastYear - 3) + "-12-31");
            mainIncomeVo.setSecondYearForIncome((lastYear - 2) + "-12-31");
            mainIncomeVo.setThirdYearForIncome((lastYear - 1) + "-12-31");
            mainIncomeVo
                .setOnePeriodForIncome(sdf.format(mainIncomeInfoList.get(0).getReportPeriod()));
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
        List<IntermediaryOrgDto> sponsorList = new ArrayList<>();
        List<IntermediaryOrgDto> securityList = new ArrayList<>();
        List<IntermediaryOrgDto> lawyerList = new ArrayList<>();
        List<IntermediaryOrgDto> accountList = new ArrayList<>();
        List<IntermediaryOrgDto> assetList = new ArrayList<>();
        List<IntermediaryOrgDto> intermediaryOrgList =
            ipoCaseBizMapper.getIntermediaryOrgData(id, validFlag);
        if (intermediaryOrgList != null && !intermediaryOrgList.isEmpty()) {
            for (IntermediaryOrgDto intermediaryOrgDto : intermediaryOrgList) {
                if (intermediaryOrgDto.getIntermediaryType() != null) {
                    switch (intermediaryOrgDto.getIntermediaryType()) {
                        case "1":
                            sponsorList.add(intermediaryOrgDto);
                            break;
                        case "2":
                            securityList.add(intermediaryOrgDto);
                            break;
                        case "3":
                            lawyerList.add(intermediaryOrgDto);
                            break;
                        case "4":
                            accountList.add(intermediaryOrgDto);
                            break;
                        case "5":
                            assetList.add(intermediaryOrgDto);
                            break;
                        default:
                            break;
                    }
                }
            }
        }
        //保荐机构
        result.put("sponsors", sponsorList);
        //证券公司
        result.put("securitys", securityList);
        //律师事务所
        result.put("lawyers", lawyerList);
        //会计事务所
        result.put("accounts", accountList);
        //资产评估机构
        result.put("assets", assetList);
        return result;
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
}

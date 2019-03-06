package com.stock.capital.enterprise.ipoCase.service;

import com.stock.capital.enterprise.ipoCase.dao.IpoCaseBizMapper;
import com.stock.capital.enterprise.ipoCase.dto.CompanyOverviewVo;
import com.stock.capital.enterprise.ipoCase.dto.SupplierCustomerInfoDto;
import com.stock.core.service.BaseService;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyOverviewService extends BaseService {

    @Autowired
    private IpoCaseBizMapper ipoCaseBizMapper;

    public CompanyOverviewVo getIpoCompanyOverview(String id) {
        //ipo案例基本信息
        CompanyOverviewVo companyOverviewVo = ipoCaseBizMapper.getIpoCaseDetail(id);
        //登录其他资本市场信息
        companyOverviewVo.setOtherMarketInfoList(ipoCaseBizMapper.getIpoMarketData(id));
        //股东信息
        companyOverviewVo.setShareHolderList(ipoCaseBizMapper.getIpoShareData(id));
        //主要竞争对手信息
        companyOverviewVo.setMainCompetitorInfoList(ipoCaseBizMapper.getIpoCompetitorData(id));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //报告期前五名供应商情况
        List<SupplierCustomerInfoDto> supplierInfoList =
            ipoCaseBizMapper.getSupplierOrCustomerData(id, "1");
        if (supplierInfoList != null && !supplierInfoList.isEmpty()) {
            companyOverviewVo.setSupplierInfoList(supplierInfoList);
            int lastYearSupplier = getYearByDate(supplierInfoList.get(0).getReportPeriod());
            companyOverviewVo.setFirstYearForSupplier((lastYearSupplier - 3) + "-12-31");
            companyOverviewVo.setSecondYearForSupplier((lastYearSupplier - 2) + "-12-31");
            companyOverviewVo.setThirdYearForSupplier((lastYearSupplier - 1) + "-12-31");
            companyOverviewVo
                .setOnePeriodForSupplier(sdf.format(supplierInfoList.get(0).getReportPeriod()));
        }
        //报告期前五名客户情况
        List<SupplierCustomerInfoDto> customerInfoList =
            ipoCaseBizMapper.getSupplierOrCustomerData(id, "2");
        if (customerInfoList != null && !customerInfoList.isEmpty()) {
            companyOverviewVo.setCustomerInfoList(customerInfoList);
            int lastYearCustomer = getYearByDate(customerInfoList.get(0).getReportPeriod());
            companyOverviewVo.setFirstYearForCustomer((lastYearCustomer - 3) + "-12-31");
            companyOverviewVo.setSecondYearForCustomer((lastYearCustomer - 2) + "-12-31");
            companyOverviewVo.setThirdYearForCustomer((lastYearCustomer - 1) + "-12-31");
            companyOverviewVo
                .setOnePeriodForCustomer(sdf.format(customerInfoList.get(0).getReportPeriod()));
        }
        return companyOverviewVo;
    }

    /**
     * 取日期中年份
     *
     * @param date
     * @return int
     */
    private int getYearByDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.YEAR);
    }
}

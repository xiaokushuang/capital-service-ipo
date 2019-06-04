package com.stock.capital.enterprise.ipoInterfaceH5.controller;

import com.stock.capital.enterprise.ipoCase.dto.CompanyOverviewVo;
import com.stock.capital.enterprise.ipoCase.dto.IpoFinanceDto;
import com.stock.capital.enterprise.ipoCase.dto.IssuerIndustryStatusDto;
import com.stock.capital.enterprise.ipoCase.dto.SupplierCustomerMainDto;
import com.stock.capital.enterprise.ipoCase.service.CompanyOverviewService;
import com.stock.capital.enterprise.ipoCase.service.IpoFinanceService;
import com.stock.capital.enterprise.ipoInterfaceH5.service.IpoInterfaceService;
import com.stock.core.controller.BaseController;
import com.stock.core.dto.JsonResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("ipoInterfaceH5")
public class IpoInterfaceController extends BaseController {

    private IpoInterfaceService ipoInterfaceService;

    @Autowired
    private CompanyOverviewService companyOverviewService;
    @Autowired
    private IpoFinanceService ipoFinanceService;

    /**
     * GK 报告期主要供应商及客户情况
     */
    @RequestMapping(value = "/supplierCustomerData", method = RequestMethod.GET)
    public JsonResponse<Map<String, List<SupplierCustomerMainDto>>> supplierCustomerData(
            @RequestParam("id") String id) {
        JsonResponse<Map<String, List<SupplierCustomerMainDto>>> response = new JsonResponse<>();
        response.setResult(companyOverviewService.getSupCusData(id));
        return response;
    }

    /**
     * GK 发行人财务数据
     */
    @RequestMapping(value = "/supplierCustomerData", method = RequestMethod.GET)
    public JsonResponse<IpoFinanceDto> selectFinanceOverListH5(
            @RequestParam("id") String id) {
        JsonResponse<IpoFinanceDto> response = new JsonResponse<>();
        response.setResult(ipoFinanceService.selectFinanceOverListH5(id));
        return response;
    }
}

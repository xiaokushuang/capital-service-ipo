package com.stock.capital.enterprise.ipoInterfaceH5.controller;

import com.stock.capital.enterprise.ipoCase.dto.CompanyOverviewVo;
import com.stock.capital.enterprise.ipoCase.service.CompanyOverviewService;
import com.stock.capital.enterprise.common.constant.Global;
import com.stock.capital.enterprise.ipoCase.dto.IpoCaseIndexDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoCaseListBo;
import com.stock.capital.enterprise.ipoCase.service.IpoCaseListService;
import com.stock.capital.enterprise.ipoInterfaceH5.service.IpoInterfaceService;
import com.stock.core.controller.BaseController;
import com.stock.core.dto.JsonResponse;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import com.stock.core.dto.QueryInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("ipoInterfaceH5")
public class IpoInterfaceController extends BaseController {

    @Autowired
    private IpoInterfaceService ipoInterfaceService;
    @Autowired
    private IpoCaseListService ipoCaseListService;

    /**
     * 获取下拉框数据
     * @author yangj
     * @return
     */
    @RequestMapping("/querySelectData")
    @ResponseBody
    public Map<String,Object> querySelectData(){
        Map<String,Object> result = ipoInterfaceService.querySelectData();
        return result;
    }

    /**
     * 获取科创板IPO数据
     * @author yangj
     * @param ipoCaseListBo   industryCsrc（所属行业）   codeOrName（简称代码）  iecResult（审核状态）  belongsBureau(注册地)
     * @return
     */
    @RequestMapping(value = "/getIpoCaseList",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getIpoCaseList(@RequestBody IpoCaseListBo ipoCaseListBo){
        QueryInfo<IpoCaseListBo> page = new QueryInfo<>();
//        没有查询条件时 实例化dto
        if (null == ipoCaseListBo){
            ipoCaseListBo = new IpoCaseListBo();
        }
//        目前设置科创板条件  通过前台传递科创板条件 方便复用接口
//        ipoCaseListBo.setIpoPlate(Global.OPO_BOARD_61);
        page.setCondition(ipoCaseListBo);
//        获取总数 全部取出来 目前
        Map<String, Object> ipoCaseList = ipoCaseListService.getIpoCaseList(page, false);
        Integer total = (Integer) ipoCaseList.get("total");
//        设置分页参数
        page.setStartRow(0);
        page.setPageSize(total);
        Map<String, Object> result = ipoCaseListService.getIpoCaseList(page, false);
//        可能需要  对重复数据 进行名称的处理
        return result;
    }


    /**
     * dxy
     * 行业与技术接口
     * @param id 案例id
     * @return
     */
    @RequestMapping(value = "/getTechnology", method = RequestMethod.GET)
    public JsonResponse<Map> getTechnology(@RequestParam("id") String id) {
        JsonResponse<Map> response = new JsonResponse<>();
        response.setResult(ipoInterfaceService.getIpoTechnology(id));
        return response;
    }

}

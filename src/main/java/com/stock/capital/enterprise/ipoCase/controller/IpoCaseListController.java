package com.stock.capital.enterprise.ipoCase.controller;

import com.stock.capital.enterprise.ipoCase.dao.IpoCaseListMapper;
import com.stock.capital.enterprise.ipoCase.dto.IpoCaseIndexDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoCaseListBo;
import com.stock.capital.enterprise.ipoCase.service.IpoCaseListService;
import com.stock.core.dto.JsonResponse;
import com.stock.core.dto.QueryInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"IPO检索页接口类"}, description = "IPO检索页接口描述")
@RestController
@RequestMapping("search")
public class IpoCaseListController {

    @Autowired
    private IpoCaseListService ipoCaseListService;

    @Autowired
    private IpoCaseListMapper ipoCaseListMapper;


    @ApiOperation(value = "检索列表接口", notes = "检索列表接口描述")
    @RequestMapping(value = "/listData", method = RequestMethod.POST,
        consumes = MediaType.APPLICATION_JSON_VALUE)
    public JsonResponse<Map<String, Object>> listData(
        @RequestBody QueryInfo<IpoCaseListBo> page) {
        JsonResponse<Map<String, Object>> response = new JsonResponse<>();
        //标识是否签约客户 默认不是
        boolean signSymbol = false;
        String companyId = page.getCondition().getCompanyId();
        if (companyId != null && !"".equals(companyId)) {
            int count = ipoCaseListMapper.queryAuthByCompanyId(companyId);
            // 授权类型:签约 或 证监会 或 金融办
            if (count > 0) {
                //展示id
                signSymbol = true;
            }
        }
        Map<String, Object> map = ipoCaseListService.getIpoCaseList(page, signSymbol);
        List<IpoCaseIndexDto> list = (List<IpoCaseIndexDto>) map.get("data");
        if (list != null && !list.isEmpty()) {
            for (IpoCaseIndexDto indexDto : list) {
                //未正式签约的并未开放的 删除id
                if (!signSymbol && (indexDto.getOpenFlag() == null || "0"
                    .equals(indexDto.getOpenFlag()))) {
                    indexDto.setId(null);
                }
                //将净利润、营业收入、总资产 万元转亿元
                dataChange(indexDto);
            }
            map.put("data", list);
        }
        response.setResult(map);
        return response;
    }

    @ApiOperation(value = "检索页下拉框接口", notes = "检索页下拉框接口描述")
    @RequestMapping(value = "/selectData", method = RequestMethod.GET)
    public JsonResponse<Map<String, Object>> selectData() {
        JsonResponse<Map<String, Object>> response = new JsonResponse<>();
        response.setResult(ipoCaseListService.getIpoSelectData());
        return response;
    }

    @ApiOperation(value = "中介机构联想数据接口", notes = "中介机构联想数据接口描述")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "intermediaryName", value = "中介机构名称或别名", required = true,
            paramType = "query",
            dataType = "String")
    })
    @RequestMapping(value = "/queryIntermediary", method = RequestMethod.GET)
    public JsonResponse<List<Map<String, Object>>> queryIntermediary(
        @RequestParam("intermediaryName") String intermediaryName) {
        JsonResponse<List<Map<String, Object>>> response = new JsonResponse<>();
        response.setResult(ipoCaseListService.queryIntermediary(intermediaryName));
        return response;
    }

    private void dataChange(IpoCaseIndexDto indexDto) {
        if (indexDto.getNetProfitOne() != null) {
            indexDto.setNetProfitOne(BigDecimal.valueOf(indexDto.getNetProfitOne())
                                         .divide(new BigDecimal("10000"), 2,
                                                 BigDecimal.ROUND_HALF_UP).doubleValue());
        }
        if (indexDto.getNetProfitTwo() != null) {
            indexDto.setNetProfitTwo(BigDecimal.valueOf(indexDto.getNetProfitTwo())
                                         .divide(new BigDecimal("10000"), 2,
                                                 BigDecimal.ROUND_HALF_UP).doubleValue());
        }
        if (indexDto.getNetProfitThree() != null) {
            indexDto.setNetProfitThree(BigDecimal.valueOf(indexDto.getNetProfitThree())
                                           .divide(new BigDecimal("10000"), 2,
                                                   BigDecimal.ROUND_HALF_UP).doubleValue());
        }
        if (indexDto.getOperateReveOne() != null) {
            indexDto.setOperateReveOne(BigDecimal.valueOf(indexDto.getOperateReveOne())
                                           .divide(new BigDecimal("10000"), 2,
                                                   BigDecimal.ROUND_HALF_UP).doubleValue());
        }
        if (indexDto.getOperateReveTwo() != null) {
            indexDto.setOperateReveTwo(BigDecimal.valueOf(indexDto.getOperateReveTwo())
                                           .divide(new BigDecimal("10000"), 2,
                                                   BigDecimal.ROUND_HALF_UP).doubleValue());
        }
        if (indexDto.getOperateReveThree() != null) {
            indexDto.setOperateReveThree(BigDecimal.valueOf(indexDto.getOperateReveThree())
                                             .divide(new BigDecimal("10000"), 2,
                                                     BigDecimal.ROUND_HALF_UP).doubleValue());
        }
        if (indexDto.getSunAsset() != null) {
            indexDto.setSunAsset(BigDecimal.valueOf(indexDto.getSunAsset())
                                     .divide(new BigDecimal("10000"), 2,
                                             BigDecimal.ROUND_HALF_UP).doubleValue());
        }

    }
}

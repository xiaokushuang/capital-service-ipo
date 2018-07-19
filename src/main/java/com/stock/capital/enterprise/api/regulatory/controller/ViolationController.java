package com.stock.capital.enterprise.api.regulatory.controller;

import com.stock.capital.enterprise.api.regulatory.dto.*;
import com.stock.capital.enterprise.api.regulatory.service.ViolationService;
import com.stock.core.controller.BaseController;
import com.stock.core.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("violation")
public class ViolationController extends BaseController {

    Logger log = LoggerFactory.getLogger(ViolationController.class);

    @Autowired
    private ViolationService violationService;

    /**
     * 获取code列表
     *
     * @return response（JSON格式）
     */
    @RequestMapping(value = "/getCodeList", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse<List<OptionDto>> getCodeList(String codeNo) {
        JsonResponse<List<OptionDto>> response = new JsonResponse<List<OptionDto>>();
        response.setResult(violationService.getCodeList(codeNo));
        return response;
    }

    /**
     * 获取处罚机构列表
     *
     * @return response（JSON格式）
     */
    @RequestMapping(value = "/getPunishClassList", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse<List<Map<String, Object>>> getPunishClassList(String ids) {
        JsonResponse<List<Map<String, Object>>> response = new JsonResponse<List<Map<String, Object>>>();
        response.setResult(violationService.getPunishClassList(ids));
        return response;
    }

    /**
     * 获取违规类型、处罚类型列表
     *
     * @return response（JSON格式）
     */
    @RequestMapping(value = "/getTypeList", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse<List<Map<String, Object>>> getTypeList(String ids, String treeType) {
        JsonResponse<List<Map<String, Object>>> response = new JsonResponse<List<Map<String, Object>>>();
        response.setResult(violationService.getTypeList(ids, treeType));
        return response;
    }

    /**
     * 需求3851 by weishisheng 2018/03/19
     * 获取所属证监局列表
     *
     * @return response（JSON格式）
     */
    @RequestMapping(value = "/getBelongsToSRBList", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse<List<Map<String, Object>>> getBelongsToSRBList() {
        JsonResponse<List<Map<String, Object>>> response = new JsonResponse<List<Map<String, Object>>>();
        response.setResult(violationService.getBelongsToSRBList());
        return response;
    }
    
    /**
     * 获取主办券商BrokerCodeList
     * 需求1711 Start 2017/09/11 *违规处罚-案例查询-主办券商* byzhaozilong 2017/09/11 end
     * @return response（JSON格式）
     */
    @RequestMapping(value = "/getBrokerCodeList", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse<List<Map<String, Object>>> getBrokerCodeList() {
        JsonResponse<List<Map<String, Object>>> response = new JsonResponse<List<Map<String, Object>>>();
        response.setResult(violationService.getBrokerCodeList());
        return response;
    }

    /**
     * 检索处罚明细
     *
     * @return response（JSON格式）
     */
    @RequestMapping(value = "/ajaxPunishmentIndex", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse<FacetResult<PunishmentSearchDto>> ajaxPunishmentIndex(
            @RequestBody QueryInfo<Map<String, String>> queryInfo) {
        JsonResponse<FacetResult<PunishmentSearchDto>> response = new JsonResponse<FacetResult<PunishmentSearchDto>>();
        response.setResult(violationService.searchPunishment(queryInfo));
        return response;
    }

    /**
     * 检索违规案例
     *
     * @return response（JSON格式）
     */
    @RequestMapping(value = "/ajaxViolationIndex", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse<FacetResult<ViolationSearchDto>> ajaxRegulatoryIndex(
            @RequestBody QueryInfo<Map<String, String>> queryInfo) {
        JsonResponse<FacetResult<ViolationSearchDto>> response = new JsonResponse<FacetResult<ViolationSearchDto>>();
        response.setResult(violationService.searchViolation(queryInfo));
        return response;
    }

    /**
     * 获取违规案例的收藏列表
     *
     * @return response（JSON格式）
     */
    @RequestMapping(value = "/favourList", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse<Page<ViolationFavourDto>> favourList(String userId, String companyCode, String key,
            String violateType, String adoptCondition, String personType, String belongsToSRBType, String punishType, String vioCompanyCode,
            String startDate, String endDate, String startRow, String pageSize, String orderColumn, String orderByOrder) {
        JsonResponse<Page<ViolationFavourDto>> response = new JsonResponse<Page<ViolationFavourDto>>();
        response.setResult(violationService.favourList(userId, companyCode, key, violateType, adoptCondition,
                personType, belongsToSRBType, punishType, vioCompanyCode, startDate, endDate, startRow, pageSize, orderColumn,
                orderByOrder));
        return response;
    }

    /**
     * 获取违规案例详情
     *
     * @return response（JSON格式）
     */
    @RequestMapping(value = "/getViolationDetail", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse<ViolationDetailDto> getViolationDetail(String id) {
        JsonResponse<ViolationDetailDto> response = new JsonResponse<ViolationDetailDto>();
        response.setResult(violationService.getViolationDetail(id));
        return response;
    }

    /**
     * 获取处罚对象列表（主要用于排序）
     *
     * @return response（JSON格式）
     */
    @RequestMapping(value = "/getDetailPersonList", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse<List<ViolationDetailPersonDto>> getDetailPersonList(String id, String orderColumn,
            String orderByOrder) {
        JsonResponse<List<ViolationDetailPersonDto>> response = new JsonResponse<List<ViolationDetailPersonDto>>();
        response.setResult(violationService.getDetailPersonList(id, orderColumn, orderByOrder));
        return response;
    }
    
    /**
     * 违规统计-违规概况-图一数据
     *
     * @return response（JSON格式）
     */
    @RequestMapping(value = "/getViolationOverview", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse<Map<String, String>> getViolationOverview(@RequestBody Map<String, String> queryInfo) {
        JsonResponse<Map<String, String>> response = new JsonResponse<Map<String, String>>();
        response.setResult(violationService.getViolationOverview(queryInfo));
        return response;
    }
    
    
    

}

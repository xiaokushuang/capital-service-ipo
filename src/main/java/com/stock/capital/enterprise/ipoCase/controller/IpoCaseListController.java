package com.stock.capital.enterprise.ipoCase.controller;

import com.stock.capital.enterprise.ipoCase.dao.IpoCaseListMapper;
import com.stock.capital.enterprise.ipoCase.dto.IpoCaseIndexDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoCaseListBo;
import com.stock.capital.enterprise.ipoCase.dto.IpoFavoriteAndNoteDto;
import com.stock.capital.enterprise.ipoCase.dto.LetterClassIndexDto;
import com.stock.capital.enterprise.ipoCase.service.IpoCaseListService;
import com.stock.core.controller.BaseController;
import com.stock.core.dto.FacetResult;
import com.stock.core.dto.JsonResponse;
import com.stock.core.dto.QueryInfo;
import com.stock.core.dto.UserInfo;
import com.stock.core.rest.RestClient;
import com.stock.core.util.CryptoUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.*;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import static com.stock.core.security.UserInfoHolder.getUserInfo;

@Api(tags = {"IPO检索页接口类"}, description = "IPO检索页接口描述")
@RestController
@RequestMapping("search")
public class IpoCaseListController extends BaseController {

    @Autowired
    private IpoCaseListService ipoCaseListService;

    @Autowired
    private IpoCaseListMapper ipoCaseListMapper;

    /**
     * 接口调用
     */
    @Autowired
    private RestClient restClient;

    /**
     * 接口地址前缀
     */
    @Value("#{app['api.baseUrl']}")
    private String apiBaseUrl;


    @ApiOperation(value = "检索列表接口", notes = "检索列表接口描述")
    @RequestMapping(value = "/listData", method = RequestMethod.POST,
        consumes = MediaType.APPLICATION_JSON_VALUE)
    public JsonResponse<Map<String, Object>> listData(
        @RequestBody QueryInfo<IpoCaseListBo> page) {
        JsonResponse<Map<String, Object>> response = new JsonResponse<>();
        //标识是否签约客户 默认不是
        boolean signSymbol = false;
        // 默认没有过期
        boolean overdueSymbol = true;
        String companyId = StringUtils.isNotEmpty(page.getCondition().getCompanyId()) ? page.getCondition().getCompanyId() : getUserInfo().getCompanyId();
        if (StringUtils.isNotEmpty(companyId)) {
            int count = ipoCaseListMapper.queryAuthByCompanyId(companyId);
            // 授权类型:签约 或 证监会 或 金融办
            if (count > 0) {
                //展示id
                signSymbol = true;
            }
        }
        if (StringUtils.isNotEmpty(companyId)) {
            int count = ipoCaseListMapper.queryOverdueAuthByCompanyId(companyId);
            // 授权类型:签约 或 证监会 或 金融办
            if (count <= 0) {
                //展示id
                overdueSymbol = false;
            }
        }
        if (!overdueSymbol){
            signSymbol = false;
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
                if (!overdueSymbol && (indexDto.getOpenFlag() == null || "0"
                    .equals(indexDto.getOpenFlag()))) {
                    indexDto.setId("-----");
                }
                //将净利润、营业收入、总资产 万元转亿元
                dataChange(indexDto);
            }
            map.put("data", list);
        }
        response.setResult(map);
        return response;
    }

    /**
     * 上市公司图谱查询ipo案例
     * @return
     */
    @RequestMapping(value = "/getIpoItemCaseList", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public JsonResponse<Map<String, Object>> getIpoItemCaseList(@RequestBody QueryInfo<IpoCaseListBo> page) {
        JsonResponse<Map<String, Object>> response = new JsonResponse<>();
        List<String> caseIdList = new ArrayList<String>();
        //直接根据案例id查询
        if("0".equals(page.getCondition().getSearchType())){
            FacetResult<LetterClassIndexDto> letterClassIndexList= ipoCaseListService.searchSuperviseLetter(page);
            List<LetterClassIndexDto> letterCaseIdList=letterClassIndexList.getPage().getData();
            if (letterCaseIdList.size()>0){
                for (int i=0;i<letterCaseIdList.size();i++){
                    caseIdList.add("ipo"+letterCaseIdList.get(i).getLetterCaseId());
                }
            }
        }
        List<IpoCaseIndexDto> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        if(caseIdList.size()>0){
            page.getCondition().setCaseIdList(caseIdList);
            map = ipoCaseListService.getIpoCaseList(page, true);
            list = (List<IpoCaseIndexDto>) map.get("data");
            if (list != null && !list.isEmpty()) {
                for (IpoCaseIndexDto indexDto : list) {
                    //将净利润、营业收入、总资产 万元转亿元
                    dataChange(indexDto);
                }
            }
        }
        map.put("data", list);
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
    public JsonResponse<List<Map<String,String>>> queryIntermediary(
        @RequestParam("intermediaryName") String intermediaryName) {
        /*JsonResponse<List<Map<String, Object>>> response = new JsonResponse<>();
        response.setResult(ipoCaseListService.queryIntermediary(intermediaryName));
        return response;*/
        String url = apiBaseUrl + "maaCase/agencyAutocomplete";
        ParameterizedTypeReference<JsonResponse<List<Map<String,String>>>> responseType = new ParameterizedTypeReference<JsonResponse<List<Map<String,String>>>>(){
        };
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.set("agencyName",intermediaryName);
        params.set("companytypecode","001002,005001,006001,006002,006003");
        JsonResponse<List<Map<String,String>>> response =  restClient.post(url, params, responseType);
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




    @ApiOperation(value = "收藏检索列表接口", notes = "收藏检索列表接口描述")
    @RequestMapping(value = "/queryIpoFavoriteList", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public JsonResponse<Map<String, Object>> queryIpoFavoriteList(
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
        Map<String, Object> map = ipoCaseListService.queryIpoFavoriteList(page, signSymbol);

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


    /**
     * 案例笔记查询
     * @param dto
     * @return
     */
    @PostMapping(value = "getCassNote")
    @ResponseBody
    public Map<String, Object> getCassNote(@RequestBody IpoFavoriteAndNoteDto dto) {
        //标题关键字修改，空格分隔
        if (StringUtils.isNotEmpty(dto.getCaseTitle())) {
            dto.setCaseTitleIsList(Arrays.asList(dto.getCaseTitle().trim().split("\\s+")));
        }
        if(StringUtils.isNotEmpty(dto.getNoteTitle())){
            dto.setNoteTitle(dto.getNoteTitle().replaceAll("/", "//").replaceAll("%","/%").replaceAll("_","/_")
            );
        }
        dto.setCompanyCode(getUserInfo().getCompanyId());
        dto.setUserId(getUserInfo().getUserId());
//        dto.setCompanyCode("999000");
//        dto.setUserId("746715777856730981");
        Map<String, Object> map = new HashMap<String, Object>();
        dto.setSignStatus(getSignStatus());
        map = ipoCaseListService.getCassNote(dto);
        //是否为签约用户
        map.put("signStatus",dto.getSignStatus());
        //打开客服需要的信息
        String echatStr = "";
        try {
            echatStr = "companyCode=" + getUserInfo().getCompanyCode() + "&userId=" + getUserInfo().getUserId();
            echatStr = URLEncoder.encode(CryptoUtil.encryptCBC(echatStr, "I2+hozdF4Xa14ZwgaprHSA=="), "utf-8");
        } catch (Exception e) {
            echatStr = "";
        }
        map.put("echatStr",echatStr);
        return map;
    }

    /**
     * 判断是否为签约用户
     * @return
     */
    @PostMapping(value = "getSignStatus")
    @ResponseBody
    public String getSignStatus(){
        String url = apiBaseUrl + "application/selectPermissionByCompanyId";
        ParameterizedTypeReference<String> responseType = new ParameterizedTypeReference<String>() {
        };
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        String companyId =  getUserInfo().getCompanyId();
        String companyCode =  getUserInfo().getCompanyCode();
        //0为未签约用户
        String signStatus = "0";
        params.set("companyId",companyId);
        params.set("companyCode",companyCode);
        if (StringUtils.isNotEmpty(companyId) && StringUtils.isNotEmpty(companyCode)) {
            signStatus = restClient.post(url, params, responseType);
            //特殊公司处理
            if ("0".equals(signStatus)) {
                String isCompanyFlag = ipoCaseListService.isCompanyFlag(companyCode);
                if (StringUtils.isNotEmpty(isCompanyFlag)) {
                    signStatus = "1";
                }
            }
        }
        return signStatus;
    }

    /**
     * 初始化下拉框数据
     * @return
     */
    @PostMapping(value = "initAreaSelect")
    @ResponseBody
    public JsonResponse initAreaSelect(){
        return  ipoCaseListService.initAreaSelect();
    }


}

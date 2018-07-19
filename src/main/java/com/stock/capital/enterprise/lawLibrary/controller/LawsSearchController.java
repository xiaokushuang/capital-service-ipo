package com.stock.capital.enterprise.lawLibrary.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.stock.capital.enterprise.api.regulatory.dto.ViolationParamDto;
import com.stock.capital.enterprise.api.regulatory.service.ViolationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Maps; 
import com.stock.capital.enterprise.common.entity.Code;
import com.stock.capital.enterprise.common.service.CommonService;
import com.stock.capital.enterprise.lawLibrary.dto.CodeDto;
import com.stock.capital.enterprise.lawLibrary.dto.LawDownDto;
import com.stock.capital.enterprise.lawLibrary.dto.SearchDto;
import com.stock.capital.enterprise.lawLibrary.dto.SearchLawsDTO;
import com.stock.capital.enterprise.lawLibrary.dto.StatisticLawDto;
import com.stock.capital.enterprise.lawLibrary.service.LawsSearchService;
import com.stock.core.Constant;
import com.stock.core.controller.BaseController;
import com.stock.core.dto.JsonResponse;
import com.stock.core.dto.OptionDto;
import com.stock.core.dto.QueryInfo;
import com.stock.core.search.SolrSearchUtil;
import com.stock.core.util.BeanUtil;
import com.stock.core.util.DateUtil;
import com.stock.core.util.JsonUtil;
import com.stock.core.util.WebUtil;
import com.stock.core.web.DownloadView;

@Controller
@RequestMapping("lawsearch")
public class LawsSearchController extends BaseController {

    @Autowired
    private LawsSearchService lawsSearchService;

    @Autowired
    private ViolationService violationService;

    @Autowired
    private CommonService commonService;

    /**
     * 接口地址前缀
     */
    @Value("${api.baseUrl}")
    private String apiBaseUrl;

    @Value("${site.baseUrl}")
    private String siteBaseUrl;

    /**
     * 文件浏览URL基础路径
     */
    @Value("${official.viewPath}")
    private String officialViewPath;
    
    @Value("${index.verbShareUrl}")
    private String verbShareUrl;
    /**
     * 价值法库-法规概况页面初始化
     */
    @RequestMapping(value = "/toLawDescription")
    public ModelAndView toLawDescription() {
        ModelAndView mv = new ModelAndView("lawLibrary/lawDescription");
        return mv;
    }

    /**
     * 法规检索-条文阅读页面初始化
     */
    @RequestMapping(value = "/toLawClause")
    public ModelAndView toLawClause(String lawId, String jingdu, String typeId, String type) {
        ModelAndView mv = new ModelAndView("lawLibrary/lawClause");
        mv.addObject("lawId", lawId);
        mv.addObject("jingdu", jingdu);
        mv.addObject("typeId", typeId);
        mv.addObject("siteUrl", siteBaseUrl);
        // 需求190 新增合规交易id参数，合规交易调用该页面时传递该参数，走自己单独流程，获取合规交易精读内容
        mv.addObject("type", type);
        mv.addObject("verbShareUrl", verbShareUrl);
        return mv;
    }

    /**
     * 法规检索-法规详情页面初始化
     */
    @RequestMapping(value = "/toLawDetail")
    public ModelAndView toLawDetail(String lawId, String jingdu, String typeId, String lawItemId, String type, String keyTitle, String keyContent) {
        ModelAndView mv = new ModelAndView("lawLibrary/lawDetail");
        mv.addObject("lawId", lawId);
        mv.addObject("lawItemId", lawItemId);
        mv.addObject("jingdu", jingdu);
        mv.addObject("typeId", typeId);
        mv.addObject("siteUrl", siteBaseUrl);
        mv.addObject("lawViewPath", officialViewPath);
        // 需求1510 文章内追加关键字检索高亮显示 bywangyu 2017/04/08 start
        // 去重
        if(StringUtils.isNotBlank(keyTitle)){
            List<String> keyTitleLst = new ArrayList<String>(new HashSet<String>(Arrays.asList(keyTitle.split(" "))));  
            keyTitle = StringUtils.join(keyTitleLst," ");
        }
        if(StringUtils.isNotBlank(keyContent)){
            List<String> keyContentLst = new ArrayList<String>(new HashSet<String>(Arrays.asList(keyContent.split(" "))));  
            keyContent = StringUtils.join(keyContentLst," ");
        }
        
        mv.addObject("keyTitle", keyTitle);
        mv.addObject("keyContent", keyContent);
        // 需求1510 文章内追加关键字检索高亮显示 bywangyu 2017/04/08 end
        // 需求190 新增合规交易id参数，合规交易调用该页面时传递该参数，走自己单独流程，获取合规交易精读内容
        mv.addObject("type", type);
        mv.addObject("verbShareUrl", verbShareUrl);
        return mv;
    }

    /**
     * 法规概况-获取法律法规业务分类统计
     */
    @RequestMapping(value = "/getLawsTypeWithCount", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse<Object> getLawsTypeWithCount(String parentId, String level, String publishedYear) {
        JsonResponse<Object> result = new JsonResponse<Object>();
        // 需求1644 2017/4/6 bywangyu start
        result.setResult(this.lawsSearchService.getLawTypeWithCount(parentId, level, publishedYear));
        // 需求1644 2017/4/6 bywangyu end
        return result;
    }

    /**
     * 法规概况-获取法律位阶统计
     */
    @RequestMapping(value = "/getLawsClassWithCount", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse<Object> getLawsClassWithCount(String parentId, String level, String publishedYear) {
        JsonResponse<Object> result = new JsonResponse<Object>();
        // 需求1644 2017/4/6 bywangyu start
        result.setResult(this.lawsSearchService.getLawClassWithCount(parentId, level, publishedYear));
        // 需求1644 2017/4/6 bywangyu end
        return result;
    }

    /**
     * 法律概况-根据不同种类统计法律数量(用于法律概况统计)
     *
     * @param type
     *            (1:颁布时间 2：适用范围 3：发文部门) invalid （0：不显示失效法规 1：显示）
     * @return
     */
    @RequestMapping(value = "/getLawsCountWithType", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse<Object> getLawsCountWithType(String type, String invalid, String publishedYear) {
        JsonResponse<Object> result = new JsonResponse<Object>();
        // 需求1644 2017/4/6 bywangyu start
        result.setResult(this.lawsSearchService.getLawsCountWithType(type, invalid, publishedYear));
        // 需求1644 2017/4/6 bywangyu end
        return result;
    }

    /**
     * 价值法库-法规概况页面初始化（AJAX获取）
     */
    @RequestMapping(value = "/lawDescriptionInit", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse<Object> lawDescriptionInit() {
        JsonResponse<Object> result = new JsonResponse<Object>();
        Map<String, Object> data = Maps.newHashMap();
        data.put("nearlyLaws", this.lawsSearchService.getNearlyLaws());
        data.put("hotLaws", this.lawsSearchService.getHotLaws());
        result.setResult(data);
        return result;
    }

    /**
     * 法规检索-获取法规精读条数
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/getLawsJingduCount", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse<Object> getLawsJingduCount(String lawIds, String typeId) {
        JsonResponse<Object> response = new JsonResponse<Object>();
        response.setResult(this.lawsSearchService.getLawsJingduCount(lawIds, typeId));
        return response;
    }

    /**
     * 法规检索-法规详情数据获取
     */
    @RequestMapping(value = "/getLawDetail", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse<Object> getLawDetail(String lawId, String jingdu, String typeId, String type) {
        Map<String, Object> map = Maps.newHashMap();
        JsonResponse<Object> result = new JsonResponse<Object>();
        // 需求190，合规交易调用，查看合规交易精读内容
        Map<String, Object> data = this.lawsSearchService.getLawsDetail(lawId, jingdu, typeId, type);
        Boolean existFavour = this.lawsSearchService.existFavourLaw(lawId);
        map.put("data", data);
        map.put("existFavour", existFavour);
        result.setResult(map);
        return result;
    }

    // 2017/03/24  需求1453改动 by wangchao START
    @RequestMapping(value = "/getViolation", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse<List<String>> getViolation(String lawsId) {
        JsonResponse<List<String>> response = new JsonResponse<List<String>>();
        response.setResult(lawsSearchService.getViolation(lawsId));
        return response;
    }
    // 2017/03/24  需求1453改动 by wangchao END

    /**
     * 价值法库-法规收藏
     */
    @RequestMapping(value = "/saveFavourLaw", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse<Object> saveFavourLaw(String lawId) {
        String ip = WebUtil.getClientIpAddr(getRequest());
        JsonResponse<Object> result = new JsonResponse<Object>();
        Object map = this.lawsSearchService.saveFavourLaw(lawId, ip);
        result.setResult(map);
        return result;
    }

    //2017/06/07 需求1320 chiweiming  start
    /**
     * 价值法库-法规条文收藏
     *
     * */
    @RequestMapping(value = "/saveFavourStructure", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse<Object> saveFavourStructure(String structureId) {
        String ip = WebUtil.getClientIpAddr(getRequest());
        JsonResponse<Object> result = new JsonResponse<Object>();
        Object map = this.lawsSearchService.saveFavourStructure(structureId, ip);
        result.setResult(map);
        return result;
    }

    /**
     * 法规条文收藏状态获取
     * */
    @RequestMapping(value = "structureFavoriteInit")
    @ResponseBody
    public JsonResponse<List<String>> structureFavoriteInit(String lawId) {
        JsonResponse<List<String>> response = new JsonResponse<List<String>>();
        response.setResult(lawsSearchService.structureFavoriteInit(lawId));
        return response;
    }
    //2017/06/07 需求1320 chiweiming  end

    /**
     * 价值法库-法规收藏状态获取
     */
    @RequestMapping(value = "/getFavouriteStatus", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse<Object> getFavouriteStatus(String lawIds) {
        JsonResponse<Object> result = new JsonResponse<Object>();
        Object dataResult = this.lawsSearchService.getFavouriteStatus(lawIds);
        result.setResult(dataResult);
        return result;
    }

    /**
     * 价值法库-保存笔记
     */
    @RequestMapping(value = "/saveLawNote", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse<Boolean> saveLawNote(String lawId, String lawItemId, String note) {
        String ip = WebUtil.getClientIpAddr(getRequest());
        JsonResponse<Boolean> response = new JsonResponse<Boolean>();
        Boolean result = this.lawsSearchService.saveLawNote(lawId, lawItemId, ip, note);
        response.setResult(result);
        return response;
    }

    /**
     * 价值法库-获取笔记
     */
    @RequestMapping(value = "/getLawNote", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse<Object> getLawNote(String lawItemId) {
        JsonResponse<Object> result = new JsonResponse<Object>();
        result.setResult(this.lawsSearchService.getLawNoteContent(lawItemId));
        return result;
    }

    /**
     * 价值法库-增加分享次数
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/addLawShareNums", method = RequestMethod.POST)
    @ResponseBody
    public Object addLawShareNums(String id, String name, String busUrl) {
        return this.lawsSearchService.addLawShareNums(id, name, busUrl);
    }

    /**
     * 价值法库-获取法规失效依据
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getInvalidLaws", method = RequestMethod.POST)
    @ResponseBody
    public Object getInvalidLaws(String id) {
        return this.lawsSearchService.getInvalidLaws(id);
    }

    /**
     * 价值法库-法规检索页面初始化
     */
    @RequestMapping(value = "/toLawSearch")
    public ModelAndView toLawSearch() {
        ModelAndView mv = new ModelAndView("lawLibrary/lawSearch");
        SearchLawsDTO searchLawsDTO = new SearchLawsDTO();
        List<Code> a = this.lawsSearchService.getSource();
        List<Code> b = this.lawsSearchService.getRange();
        List<CodeDto> aDto = new ArrayList<CodeDto>();
        List<CodeDto> bDto = new ArrayList<CodeDto>();
        List<CodeDto> cDto = new ArrayList<CodeDto>();
        for(Code c :a){
        	CodeDto ad = new CodeDto();
        	BeanUtil.copy(c, ad);
        	ad.setFlag("0");
        	aDto.add(ad);
        }
        for(Code f :b){
        	CodeDto bd = new CodeDto();
        	BeanUtil.copy(f, bd);
        	bd.setFlag("0");
        	bDto.add(bd);
        }
        List<Code> cList = lawsSearchService.getCodeList();
        for(int i =0;i<cList.size();i++){
        	CodeDto ad = new CodeDto();
        	BeanUtil.copy(cList.get(i), ad);
        	if(ad.getCodeName().equals("已失效")){
        		ad.setFlag("0");
        	}else{
        		ad.setFlag("1");
        	}
        	
        	cDto.add(ad);
        }
        searchLawsDTO.setInvdLaws(cDto);
        searchLawsDTO.setInvdLawsName("现行有效,待生效");
        searchLawsDTO.setInvdLawsShow("0,1");
        searchLawsDTO.setSourceLst(aDto);
        searchLawsDTO.setRangeLst(bDto);
        searchLawsDTO.setFlag("0");
        mv.addObject("searchAnnounceDto", searchLawsDTO);
        mv.addObject("siteUrl", siteBaseUrl);
        mv.addObject("tagList", lawsSearchService.getMyTagList());
     // 需求2996 t-select控件的替换 2017/11/29 by hezheng start
        mv.addObject("invdLawsLst", JsonUtil.toJsonNoNull(this.lawsSearchService.getCodeList()));
        mv.addObject("rangeSelectLst", JsonUtil.toJsonNoNull(searchLawsDTO.getRangeLst()));
        mv.addObject("sourceSelectLst", JsonUtil.toJsonNoNull(searchLawsDTO.getSourceLst()));
     // 需求2996 t-select控件的替换 2017/11/29 by hezheng end
        
        return mv;
    }

    /**
     * 法规检索-获取法律法规业务分类和法律位阶
     *
     * @return
     */
    @RequestMapping(value = "/getLawsTypeAndClass")
    @ResponseBody
    public JsonResponse<Object> getLawClassAndType() {
        JsonResponse<Object> result = new JsonResponse<Object>();
        Map<String, Object> data = lawsSearchService.getLawClassAndType();
        result.setResult(data);
        return result;
    }

    /**
     * 法规检索-根据业务分类获取法规位阶（用于法律位阶法规数量统计）
     *
     * @return
     */
    @RequestMapping(value = "/getLawsTypeWithClass")
    @ResponseBody
    public JsonResponse<Object> getLawsTypeWithClass(SearchLawsDTO searchLawsDTO) {
        JsonResponse<Object> result = new JsonResponse<Object>();
        Map<String, Object> data = lawsSearchService.getLawsTypeWithClass(searchLawsDTO);
        result.setResult(data);
        return result;
    }

    /**
     * 法规检索-获取法规位阶获取业务分类（用于业务分类法规数量统计）
     *
     * @return
     */
    @RequestMapping(value = "/getLawsClassWithType")
    @ResponseBody
    public JsonResponse<Object> getLawsClassWithType(SearchLawsDTO searchLawsDTO) {
        JsonResponse<Object> result = new JsonResponse<Object>();
        Map<String, Object> data = lawsSearchService.getLawsClassWithType(searchLawsDTO);
        result.setResult(data);
        return result;
    }

    /**
     * 法规检索-法律法规全文检索
     *
     * @return
     */
    @RequestMapping(value = "/msearch")
    @ResponseBody
    public JsonResponse<Object> msearch(SearchLawsDTO searchLawsDTO) {
        String keyOffset = "~5";
        Map<String, String> condition = Maps.newHashMap();
        // 检索类型：法律法规
        String conditionsStr = "laws_lib_type_txt: \"99\"";
        // 依据法律位阶
        conditionsStr = SolrSearchUtil.transArrayStrToConditionStr(conditionsStr, searchLawsDTO.getLawClass(),
                "laws_type_search_txt");
        // 依据法律法规业务分类
        conditionsStr = SolrSearchUtil.transArrayStrToConditionStr(conditionsStr, searchLawsDTO.getLawType(),
                "laws_search_declare_type_txt");
        // 依据发文单位
        conditionsStr = SolrSearchUtil.transArrayStrToConditionStr(conditionsStr, searchLawsDTO.getLawSource(),
                "laws_source_search_txt");
        // 依据适用范围
        conditionsStr = SolrSearchUtil.transArrayStrToConditionStr(conditionsStr, searchLawsDTO.getLawRange(),
                "laws_scopes_txt");
        // 依据颁布日期
        conditionsStr = SolrSearchUtil.transDateStrToConditionStr(conditionsStr, searchLawsDTO.getPublishedDate(),
                "laws_publish_date_dt");
        // 依据生效日期
        conditionsStr = SolrSearchUtil.transDateStrToConditionStr(conditionsStr, searchLawsDTO.getInvalidDate(),
                "laws_effect_date_dt");
        // 不显示失效法规
        if(StringUtils.isNotEmpty(searchLawsDTO.getInvalidTag())){
        	if(!("0,1,2").equals(searchLawsDTO.getInvalidTag())){
        		if(StringUtils.isNoneEmpty(searchLawsDTO.getInvalidTag())){
    				//现行有效
        			if ("0".equals(searchLawsDTO.getInvalidTag())) {
                        String today = DateUtil.getDateStr(new Date(), DateUtil.YYYY_MM_DD);
                        conditionsStr = conditionsStr + " AND laws_invalid_date_dt: { \"" + today + "T16:00:00Z\" TO * }";//小于
                        conditionsStr = conditionsStr + " AND laws_effect_date_dt: { * TO \"" + today + "T16:00:00Z\" ]";//大于
                    }
        			//待生效
        			else if ("1".equals(searchLawsDTO.getInvalidTag())) {
        				String today = DateUtil.getDateStr(new Date(), DateUtil.YYYY_MM_DD);
                        conditionsStr = conditionsStr + " AND laws_effect_date_dt: { \"" + today + "T16:00:00Z\" TO * }";//小于
                    }
        			//已失效
        			else if ("2".equals(searchLawsDTO.getInvalidTag())) {
                        String today = DateUtil.getDateStr(new Date(), DateUtil.YYYY_MM_DD);
                        conditionsStr = conditionsStr + " AND laws_invalid_date_dt: { * TO \"" + today + "T16:00:00Z\" ]";//大于
                    }else if("0,1".equals(searchLawsDTO.getInvalidTag())){
                    	String today = DateUtil.getDateStr(new Date(), DateUtil.YYYY_MM_DD);
                    	conditionsStr = conditionsStr + " AND (( laws_invalid_date_dt: { \"" + today + "T16:00:00Z\" TO * } AND laws_effect_date_dt: { * TO \"" + today + "T16:00:00Z\" ]) OR laws_effect_date_dt: { \"" + today + "T16:00:00Z\" TO * })";
                    }
                    else if("0,2".equals(searchLawsDTO.getInvalidTag())){
                    	 String today = DateUtil.getDateStr(new Date(), DateUtil.YYYY_MM_DD);
                    	 conditionsStr = conditionsStr + " AND (( laws_invalid_date_dt: { \"" + today + "T16:00:00Z\" TO * } AND laws_effect_date_dt: { * TO \"" + today + "T16:00:00Z\" ]) OR laws_invalid_date_dt: { * TO \"" + today + "T16:00:00Z\" ])";
                    }
					else if("1,2".equals(searchLawsDTO.getInvalidTag())){
						String today = DateUtil.getDateStr(new Date(), DateUtil.YYYY_MM_DD);
                        conditionsStr = conditionsStr + " AND (laws_effect_date_dt: { \"" + today + "T16:00:00Z\" TO * }";
                        conditionsStr = conditionsStr + " OR laws_invalid_date_dt: { * TO \"" + today + "T16:00:00Z\" ])";
					}
        		}
        	}
        } 
        
        conditionsStr = conditionsStr + " AND ((";
        // 标题关键字
        Boolean hasTitle = false;
        if (StringUtils.isNotEmpty(searchLawsDTO.getKeyTitle())
                || StringUtils.isNotEmpty(searchLawsDTO.getKeyAndTitle())
                || StringUtils.isNotEmpty(searchLawsDTO.getKeyNotTitle())) {
            hasTitle = true;
            if (StringUtils.isNotEmpty(searchLawsDTO.getKeyTitle())) {
                String[] key = searchLawsDTO.getKeyTitle().trim().split(" ");
                commonService.dealWithSuggest(searchLawsDTO.getKeyTitle(), Constant.SUGGEST_LAW_TITLE_OR);
                conditionsStr = conditionsStr + "laws_title_t:(\"" + key[0] + "\"" + keyOffset;
                for (int i = 1; i < key.length; i++) {
                    if (StringUtils.isNotEmpty(key[i])) {
                        conditionsStr = conditionsStr + " OR \"" + key[i] + "\"" + keyOffset;
                    }
                }
                conditionsStr += ")";
            }
            if (StringUtils.isNotEmpty(searchLawsDTO.getKeyAndTitle())) {
                // 如果keyTitle存在，则加AND
                if (StringUtils.isNotEmpty(searchLawsDTO.getKeyTitle())) {
                    conditionsStr += " AND ";
                }
                String[] keyAnd = searchLawsDTO.getKeyAndTitle().trim().split(" ");
                commonService.dealWithSuggest(searchLawsDTO.getKeyAndTitle(), Constant.SUGGEST_LAW_TITLE_AND);
                conditionsStr = conditionsStr + "laws_title_t:(\"" + keyAnd[0] + "\"" + keyOffset;
                for (int i = 1; i < keyAnd.length; i++) {
                    if (StringUtils.isNotEmpty(keyAnd[i])) {
                        conditionsStr = conditionsStr + " AND \"" + keyAnd[i] + "\"" + keyOffset;
                    }
                }
                conditionsStr += ")";
            }
            if (StringUtils.isNotEmpty(searchLawsDTO.getKeyNotTitle())) {
                // 如果keyAndTitle存在，则加AND
                if (StringUtils.isNotEmpty(searchLawsDTO.getKeyAndTitle())
                        || StringUtils.isNotEmpty(searchLawsDTO.getKeyTitle())) {
                    conditionsStr = conditionsStr + " AND ";
                } else {
                    conditionsStr = conditionsStr + " * ";
                }
                String[] keyNot = searchLawsDTO.getKeyNotTitle().trim().split(" ");
                commonService.dealWithSuggest(searchLawsDTO.getKeyNotTitle(), Constant.SUGGEST_LAW_TITLE_NOT);
                conditionsStr = conditionsStr + "NOT laws_title_t:(\"" + keyNot[0] + "\"" + keyOffset;
                for (int i = 1; i < keyNot.length; i++) {
                    if (StringUtils.isNotEmpty(keyNot[i])) {
                        conditionsStr = conditionsStr + " OR \"" + keyNot[i] + "\"" + keyOffset;
                    }
                }
                conditionsStr += ")";
            }
            conditionsStr += ")";
            condition.put(Constant.SEARCH_HIGHTLIGHT_FIELD, "laws_title_t");
        }
        // 内容关键字
        Boolean hasContent = false;
        if (StringUtils.isNotEmpty(searchLawsDTO.getKeyContent())
                || StringUtils.isNotEmpty(searchLawsDTO.getKeyAndContent())
                || StringUtils.isNotEmpty(searchLawsDTO.getKeyNotContent())) {
            hasContent = true;
            if (hasTitle) {
                conditionsStr += " AND (";
                condition.put(Constant.SEARCH_HIGHTLIGHT_FIELD, "laws_title_t,laws_content_t");
            } else {
                condition.put(Constant.SEARCH_HIGHTLIGHT_FIELD, "laws_content_t,index_type_t");
            }
            if (StringUtils.isNotEmpty(searchLawsDTO.getKeyContent())) {
                String[] key = searchLawsDTO.getKeyContent().trim().split(" ");
                commonService.dealWithSuggest(searchLawsDTO.getKeyContent(), Constant.SUGGEST_LAW_CONTENT_OR);
                conditionsStr = conditionsStr + "laws_content_t:(\"" + key[0] + "\"" + keyOffset;
                for (int i = 1; i < key.length; i++) {
                    if (StringUtils.isNotEmpty(key[i])) {
                        conditionsStr = conditionsStr + " OR \"" + key[i] + "\"" + keyOffset;
                    }
                }
                conditionsStr += ")";
            }
            if (StringUtils.isNotEmpty(searchLawsDTO.getKeyAndContent())) {
                // 如果keyContent存在，则加AND
                if (StringUtils.isNotEmpty(searchLawsDTO.getKeyContent())) {
                    conditionsStr = conditionsStr + " AND ";
                }
                String[] keyAnd = searchLawsDTO.getKeyAndContent().trim().split(" ");
                commonService.dealWithSuggest(searchLawsDTO.getKeyAndContent(), Constant.SUGGEST_LAW_CONTENT_AND);
                conditionsStr = conditionsStr + "laws_content_t:(\"" + keyAnd[0] + "\"" + keyOffset;
                for (int i = 1; i < keyAnd.length; i++) {
                    if (StringUtils.isNotEmpty(keyAnd[i])) {
                        conditionsStr = conditionsStr + " AND \"" + keyAnd[i] + "\"" + keyOffset;
                    }
                }
                conditionsStr += ")";
            }
            if (StringUtils.isNotEmpty(searchLawsDTO.getKeyNotContent())) {
                // 如果keyAndContent存在，则加AND
                if (StringUtils.isNotEmpty(searchLawsDTO.getKeyAndContent())
                        || StringUtils.isNotEmpty(searchLawsDTO.getKeyContent())) {
                    conditionsStr = conditionsStr + " AND ";
                } else {
                    conditionsStr = conditionsStr + " * ";
                }
                String[] keyNot = searchLawsDTO.getKeyNotContent().trim().split(" ");
                commonService.dealWithSuggest(searchLawsDTO.getKeyNotContent(), Constant.SUGGEST_LAW_CONTENT_NOT);
                conditionsStr = conditionsStr + "NOT laws_content_t:(\"" + keyNot[0] + "\"" + keyOffset;
                for (int i = 1; i < keyNot.length; i++) {
                    if (StringUtils.isNotEmpty(keyNot[i])) {
                        conditionsStr = conditionsStr + " OR \"" + keyNot[i] + "\"" + keyOffset;
                    }
                }
                conditionsStr += ")";
            }
            conditionsStr += ")";
        }
        if (hasTitle || hasContent) {
            conditionsStr += ")";
        } else {
            conditionsStr = conditionsStr.substring(0, conditionsStr.length() - 7);
            condition.put(Constant.SEARCH_HIGHTLIGHT_FIELD, "");
        }
        String orderby = "";
        // 排序顺序
        if ("1".equals(searchLawsDTO.getOrderBySeq())) {
            orderby = "desc";
        } else {
            orderby = "asc";
        }
        String orderName = "";
        // 排序根据
        if ("1".equals(searchLawsDTO.getOrderByType())) {
            orderName = "laws_publish_date_dt,laws_type_sort_t";
            if ("1".equals(searchLawsDTO.getOrderBySeq())) {
                orderby = "desc,asc";
            } else {
                orderby = "asc,asc";
            }
        } else if ("2".equals(searchLawsDTO.getOrderByType())) {
            // orderName = "laws_title_t";
        } else if ("3".equals(searchLawsDTO.getOrderByType())) {
            // 要同官网价值法库排序方式一致
            // 当位阶相同时，按重要性降序，重要性也相同时，会按颁布时间降序
            orderName = "laws_type_sort_t,laws_grade_t,laws_publish_date_dt";
            // 2017/10/19 需求1833 排序顺序修改1：倒序，2：正序 by weishisheng start
            if ("1".equals(searchLawsDTO.getOrderBySeq())) {
                orderby = "desc,desc,desc";
            } else {
                orderby = "asc,desc,desc";
            }
            // 2017/10/19 需求1833 排序顺序修改1：倒序，2：正序 by weishisheng end
        } else if ("4".equals(searchLawsDTO.getOrderByType())) {
            orderName = "laws_title_s";
        } else if ("5".equals(searchLawsDTO.getOrderByType())) {
            // 要同官网价值法库排序方式一致
            // 当重要性相同时，按位阶降序，位阶也相同时，按颁布时间降序
            orderName = "laws_grade_t,laws_type_sort_t,laws_publish_date_dt";
            if ("1".equals(searchLawsDTO.getOrderBySeq())) {
                orderby = "desc,asc,desc";
            } else {
                orderby = "asc,asc,desc";
            }
        }else if("6".equals(searchLawsDTO.getOrderByType())){
            orderName = "laws_source_code_s";
        }
        // 处理关键字的检索条件
        JsonResponse<Object> result = new JsonResponse<Object>();
        condition.put(Constant.SEARCH_CONDIATION, conditionsStr);
        condition.put(Constant.SEARCH_FACET_FIELD, "laws_type_search_txt,laws_search_declare_type_txt");
        QueryInfo<Map<String, String>> queryInfo = commonSearch(condition);
        queryInfo.setCondition(condition);
        queryInfo.setOrderByName(orderName);
        queryInfo.setOrderByOrder(orderby);
        queryInfo.setPageSize(searchLawsDTO.getPageSize());
        queryInfo.setStartRow(searchLawsDTO.getPage());
        result.setResult(lawsSearchService.searchLaws(queryInfo));
        return result;
    }

    @RequestMapping(value = "/printLawStmp", method = RequestMethod.GET)
    public ModelAndView printLawStmp(String lawId, String jingdu, String typeId, String type) {
        ModelAndView mv = new ModelAndView("lawLibrary/printLawStmp");
        mv.addObject("lawId", lawId);
        mv.addObject("jingdu", jingdu);
        mv.addObject("typeId", typeId);
        mv.addObject("type", type);
        return mv;
    }

    /**
     * 法规全文-相关案例检索页面初始化
     */
    // 2017/02/28 需求1453 by wangchao 获取法规、条文新旧版本所有关联ID
    @RequestMapping(value = "/toRelativeViolation", method = RequestMethod.POST)
    public ModelAndView toRelativeViolation(String lawId, String lawItemId) {
        ModelAndView mv = new ModelAndView("lawLibrary/relativeViolation");
        ViolationParamDto dto = violationService.getPageInitData();
        mv.addObject("violationDto", dto);
        //需求 2996_修正系统中t-select非标准化写法_2 2017.11.29 by caoxiaocan start
        //获取处罚对象身份
        mv.addObject("punishManIdList", JsonUtil.toJsonNoNull(dto.getPunishManIdtList()));
        //获取申辩情况列表
        mv.addObject("averSituationList", JsonUtil.toJsonNoNull(dto.getAverSituationList()));
        //需求 2996_修正系统中t-select非标准化写法_2 2017.11.29 by caoxiaocan end
        if (StringUtils.isNotEmpty(lawId)) {
            List<String> lawIdsList = lawsSearchService.getLawIdsList(lawId);
            StringBuilder lawBuilder = new StringBuilder("");
            if (lawIdsList != null && lawIdsList.size() > 0) {
                lawIdsList.add(lawId);
                for (int i = 0; i < lawIdsList.size(); i++) {
                    String infoLawId = lawIdsList.get(i);
                    if (StringUtils.isNotEmpty(infoLawId)) {
                        lawBuilder.append(infoLawId + ",");
                    }
                }
                lawId = lawBuilder.toString();
                lawId = lawId.substring(0, lawId.length() - 1);
//            } else {
//                lawIdsList = new ArrayList<String>();
//                lawIdsList.add(lawId);
            }
            mv.addObject("lawId", lawId);
        } else {
            mv.addObject("lawItemId", lawItemId);
        }
        // 2017/02/28 需求1453 by wangchao END
        mv.addObject("siteUrl", siteBaseUrl);
        return mv;
    }

    /**
     * TODO(统计法规数量). <br/>
     * @param statisticLawDto
     * @return
     */
    @RequestMapping(value = "/statisticLawData", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse<List<Map<String, String>>> statisticLawData(StatisticLawDto statisticLawDto){
        JsonResponse<List<Map<String, String>>> response = new JsonResponse<List<Map<String, String>>>();
        response.setResult(lawsSearchService.queryLawStatisticData(statisticLawDto));
        return response;
    }
    /**
     * TODO(按照单个月份统计法规数量). <br/>
     * @param statisticLawDto
     * @return
     */
    @RequestMapping(value = "/statisticDateByPeriod", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse<List<SearchDto>> statisticDateByPeriod(StatisticLawDto statisticLawDto){
        JsonResponse<List<SearchDto>> response = new JsonResponse<List<SearchDto>>();
        response.setResult(lawsSearchService.queryLawStatisticDataByPeriod(statisticLawDto));
        return response;
    }


//    /**
//     * 导出法规word
//     *
//     * @return
//     */
//    @RequestMapping(value = "/exportLaw2Word")
//    public ModelAndView exportResearchRecordInfo(String lawId,String jingdu, String typeId) throws IOException {
//        ModelAndView mv = new ModelAndView();
//        LawDownDto lawDownDto = lawsSearchService.getLawManage(lawId, jingdu, typeId);
//        mv.setView(new DownloadView());
//        mv.addObject(DownloadView.EXPORT_FILE, lawsSearchService.getLawInputStream(lawDownDto));
//        
//        // 需求3884 start
//        mv.addObject(DownloadView.EXPORT_FILE_NAME, lawDownDto.getLawsName()+".docx");
//        mv.addObject(DownloadView.EXPORT_FILE_TYPE, DownloadView.FILE_TYPE.DOCX);
//        // 需求3884 end
//        
//        return mv;
//    }
    
    /**
     * pop定义自定义标签名
     *
     * @return ModelAndView
     */
    @RequestMapping(value = "popTag", method = RequestMethod.POST)
    public ModelAndView popDefineTag() {
        ModelAndView mv = new ModelAndView("lawLibrary/popTag");
        return mv;
    }
    
    /**
     * 验证标签名重复
     *
     * @param
     * @return
     */
    @RequestMapping(value = "validateTagName", method = RequestMethod.POST)
    @ResponseBody
    public boolean validateTagName(String tagNameId) {
        return lawsSearchService.validateTagName(tagNameId);
    }
    
    /**
     * 保存自定义标签
     *
     * @param
     * @return
     */
    @RequestMapping(value = "savePersonalTag", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse<OptionDto> savePersonalTag(SearchLawsDTO searchLawsDTO) {
        JsonResponse<OptionDto> jsonRes = new JsonResponse<OptionDto>();
        jsonRes.setResult(lawsSearchService.savePersonalTag(searchLawsDTO));
        return jsonRes;
    }
    
    /**
     * 选择自定义标签名
     *
     * @return ModelAndView
     */
    @RequestMapping(value = "selMyTag")
    public ModelAndView selMyTag(String tagId) {
    	ModelAndView mv = new ModelAndView("lawLibrary/lawSearch");
    	SearchLawsDTO searchLawsDTO = lawsSearchService.selMyTag(tagId);
        List<Code> a = this.lawsSearchService.getSource();
        List<Code> b = this.lawsSearchService.getRange();
        List<CodeDto> aDto = new ArrayList<CodeDto>();
        List<CodeDto> bDto = new ArrayList<CodeDto>();
        List<CodeDto> cDto = new ArrayList<CodeDto>();
        for(Code c :a){
        	CodeDto ad = new CodeDto();
        	if(searchLawsDTO.getLawSourceList()!=null){
        		for(String as:searchLawsDTO.getLawSourceList()){
            		if(c.getCodeValue().equals(as)){
            			ad.setFlag("1");
            			break;
            		}else{
            			ad.setFlag("0");
            		}
            	}
        	}
        	BeanUtil.copy(c, ad);
        	aDto.add(ad);
        }
        for(Code f :b){
        	CodeDto bd = new CodeDto();
        	if(searchLawsDTO.getLawRangeList()!=null){
        		for(String as:searchLawsDTO.getLawRangeList()){
            		if(f.getCodeValue().equals(as)){
            			bd.setFlag("1");
            			break;
            		}else{
            			bd.setFlag("0");
            		}
            	}
        	}
        	BeanUtil.copy(f, bd);
        	bDto.add(bd);
        }
        List<Code> cList = lawsSearchService.getCodeList();
        
        for(Code c : cList){
        	CodeDto ad = new CodeDto();
        	if(searchLawsDTO.getFlagList()!=null){
        		for(String as:searchLawsDTO.getFlagList()){
            		if(c.getCodeValue().equals(as)){
            			ad.setFlag("1");
            			break;
            		}else{
            			ad.setFlag("0");
            		}
            	}
        	}
        	BeanUtil.copy(c, ad);
        	cDto.add(ad);
        }
        searchLawsDTO.setInvdLaws(cDto);
        searchLawsDTO.setSourceLst(aDto);
        searchLawsDTO.setRangeLst(bDto);
        mv.addObject("siteUrl", siteBaseUrl);
        mv.addObject("tagList", lawsSearchService.getMyTagList());
        mv.addObject("searchAnnounceDto", searchLawsDTO);
        mv.addObject("invdLawsLst", JsonUtil.toJsonNoNull(this.lawsSearchService.getCodeList()));
        mv.addObject("rangeSelectLst", JsonUtil.toJsonNoNull(searchLawsDTO.getRangeLst()));
        mv.addObject("sourceSelectLst", JsonUtil.toJsonNoNull(searchLawsDTO.getSourceLst()));
        return mv;
    }
    
    /**
     * 删除自定义标签
     *
     * @param
     * @return
     */
    @RequestMapping(value = "delMyTag", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse<String> delMyTag(String tagId) {
        JsonResponse<String> jsonRes = new JsonResponse<String>();
        jsonRes.setResult(lawsSearchService.delMyTag(tagId));
        return jsonRes;
    }
    
    /**
     * 获得自定义标签
     *
     * @param
     * @return
     */
    @RequestMapping(value = "getMyTag", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse<SearchLawsDTO> getMyTag(String tagId) {
        JsonResponse<SearchLawsDTO> jsonRes = new JsonResponse<SearchLawsDTO>();
        jsonRes.setResult(lawsSearchService.getMyTag(tagId));
        return jsonRes;
    }
}

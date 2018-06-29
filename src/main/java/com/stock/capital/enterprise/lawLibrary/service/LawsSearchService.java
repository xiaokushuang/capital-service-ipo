package com.stock.capital.enterprise.lawLibrary.service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;
import com.stock.capital.enterprise.lawLibrary.dao.CompanyRuleBizMapper;
import com.stock.capital.enterprise.lawLibrary.dto.InvalidLawsDto;
import com.stock.capital.enterprise.lawLibrary.dto.LawDownDto;
import com.stock.core.util.BeanUtil;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
 
import com.stock.capital.enterprise.common.constant.Global; 
import com.stock.capital.enterprise.common.dao.LawIndexTagMapper;  
import com.stock.capital.enterprise.common.entity.Code; 
import com.stock.capital.enterprise.common.entity.LawIndexTag; 
import com.stock.capital.enterprise.lawLibrary.dto.SearchDto;
import com.stock.capital.enterprise.lawLibrary.dto.SearchLawsDTO;
import com.stock.capital.enterprise.lawLibrary.dto.StatisticLawDto;
import com.stock.capital.enterprise.regulatory.service.ViolationService;
import com.stock.core.dto.FacetResult;
import com.stock.core.dto.JsonResponse;
import com.stock.core.dto.OptionDto;
import com.stock.core.dto.QueryInfo;
import com.stock.core.dto.TreeDto;
import com.stock.core.rest.RestClient;
import com.stock.core.service.BaseService;
import com.stock.core.util.DateSplitUtil;
import com.stock.core.util.DateUtil;

@Service
public class LawsSearchService extends BaseService {

    /**
     * 接口调用
     */
    @Autowired
    private RestClient restClient;
 
    
    @Autowired
    private CompanyRuleBizMapper companyRuleBizMapper;
    
    @Value("${site.baseUrl}")
    private String siteBaseUrl;
    @Autowired
    private ViolationService bs;
    // 2017/02/28 需求1452 by wangchao END
    
    @Autowired
    private LawIndexTagMapper lawIndexTagMapper;

    /**
     * 接口地址前缀
     */
    @Value("${api.baseUrl}")
    private String apiBaseUrl;
    
//    // 需求3884 start
//    @Value("${api.docxUrl}")
//    private String apiDocxUrl;
//    // 需求3884 end

    public Map<String, Object> getLawClassAndType() {
        String url = apiBaseUrl + "laws_search/getLawsTypeAndClass";
        ParameterizedTypeReference<JsonResponse<Map<String, Object>>> responseType = new ParameterizedTypeReference<JsonResponse<Map<String, Object>>>() {
        };
        return restClient.post(url, null, responseType).getResult();
    }

    public Map<String, Object> getLawsTypeWithClass(SearchLawsDTO searchLawsDTO) {
        String url = apiBaseUrl + "laws_search/getLawsTypeWithClass";
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        Date pStart = null;
        Date pEnd = null;
        Date iStart = null;
        Date iEnd = null;
        if (StringUtils.isNotEmpty(searchLawsDTO.getPublishedDate())) {
            // 依据颁布日期
            String[] dateRange = searchLawsDTO.getPublishedDate().split(" 至 ");
            pStart = DateUtil.getDate(dateRange[0], DateUtil.YYYY_MM_DD);
            pEnd = DateUtil.getDate(dateRange[1], DateUtil.YYYY_MM_DD);
        }
        if (StringUtils.isNotEmpty(searchLawsDTO.getInvalidDate())) {
            // 依据失效日期
            String[] dateRange = searchLawsDTO.getInvalidDate().split(" 至 ");
            iStart = DateUtil.getDate(dateRange[0], DateUtil.YYYY_MM_DD);
            iEnd = DateUtil.getDate(dateRange[1], DateUtil.YYYY_MM_DD);
        }
        params.add("publishStart", pStart == null ? "" : DateUtil.getDateStr(pStart, DateUtil.YYYY_MM_DD));
        params.add("publishEnd", pEnd == null ? "" : DateUtil.getDateStr(pEnd, DateUtil.YYYY_MM_DD));
        params.add("invalidStart", iStart == null ? "" : DateUtil.getDateStr(iStart, DateUtil.YYYY_MM_DD));
        params.add("invalidEnd", iEnd == null ? "" : DateUtil.getDateStr(iEnd, DateUtil.YYYY_MM_DD));
        params.add("classId", searchLawsDTO.getClassId());
        params.add("invalid", searchLawsDTO.getInvalidTag());
        params.add("range", searchLawsDTO.getLawRange());
        params.add("source", searchLawsDTO.getLawSource());
        params.add("keyTitle", searchLawsDTO.getKeyTitle());
        params.add("keyAndTitle", searchLawsDTO.getKeyAndTitle());
        params.add("keyNotTitle", searchLawsDTO.getKeyNotTitle());
        params.add("keyContent", searchLawsDTO.getKeyContent());
        params.add("keyAndContent", searchLawsDTO.getKeyAndContent());
        params.add("keyNotContent", searchLawsDTO.getKeyNotContent());
        ParameterizedTypeReference<JsonResponse<Map<String, Object>>> responseType = new ParameterizedTypeReference<JsonResponse<Map<String, Object>>>() {
        };
        return restClient.post(url, params, responseType).getResult();
    }

    public Map<String, Object> getLawsClassWithType(SearchLawsDTO searchLawsDTO) {
        String url = apiBaseUrl + "laws_search/getLawsClassWithType";
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        Date pStart = null;
        Date pEnd = null;
        Date iStart = null;
        Date iEnd = null;
        if (StringUtils.isNotEmpty(searchLawsDTO.getPublishedDate())) {
            // 依据颁布日期
            String[] dateRange = searchLawsDTO.getPublishedDate().split(" 至 ");
            pStart = DateUtil.getDate(dateRange[0], DateUtil.YYYY_MM_DD);
            pEnd = DateUtil.getDate(dateRange[1], DateUtil.YYYY_MM_DD);
        }
        if (StringUtils.isNotEmpty(searchLawsDTO.getInvalidDate())) {
            // 依据失效日期
            String[] dateRange = searchLawsDTO.getInvalidDate().split(" 至 ");
            iStart = DateUtil.getDate(dateRange[0], DateUtil.YYYY_MM_DD);
            iEnd = DateUtil.getDate(dateRange[1], DateUtil.YYYY_MM_DD);
        }
        params.add("publishStart", pStart == null ? "" : DateUtil.getDateStr(pStart, DateUtil.YYYY_MM_DD));
        params.add("publishEnd", pEnd == null ? "" : DateUtil.getDateStr(pEnd, DateUtil.YYYY_MM_DD));
        params.add("invalidStart", iStart == null ? "" : DateUtil.getDateStr(iStart, DateUtil.YYYY_MM_DD));
        params.add("invalidEnd", iEnd == null ? "" : DateUtil.getDateStr(iEnd, DateUtil.YYYY_MM_DD));
        params.add("typeId", searchLawsDTO.getTypeId());
        params.add("invalid", searchLawsDTO.getInvalidTag());
        params.add("range", searchLawsDTO.getLawRange());
        params.add("source", searchLawsDTO.getLawSource());
        params.add("keyTitle", searchLawsDTO.getKeyTitle());
        params.add("keyAndTitle", searchLawsDTO.getKeyAndTitle());
        params.add("keyNotTitle", searchLawsDTO.getKeyNotTitle());
        params.add("keyContent", searchLawsDTO.getKeyContent());
        params.add("keyAndContent", searchLawsDTO.getKeyAndContent());
        params.add("keyNotContent", searchLawsDTO.getKeyNotContent());
        ParameterizedTypeReference<JsonResponse<Map<String, Object>>> responseType = new ParameterizedTypeReference<JsonResponse<Map<String, Object>>>() {
        };
        return restClient.post(url, params, responseType).getResult();
    }

    public List<Code> getRange() {
        String url = apiBaseUrl + "laws_search/getCode";
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("codeNo", "LAW_SCOPE");
        ParameterizedTypeReference<JsonResponse<List<Code>>> responseType = new ParameterizedTypeReference<JsonResponse<List<Code>>>() {
        };
        return restClient.post(url, params, responseType).getResult();
    }

    public List<Code> getSource() {
        String url = apiBaseUrl + "laws_search/getCode";
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("codeNo", "LAW_ISSUE_DEPARTMENT");
        ParameterizedTypeReference<JsonResponse<List<Code>>> responseType = new ParameterizedTypeReference<JsonResponse<List<Code>>>() {
        };
        return restClient.post(url, params, responseType).getResult();
    }

    @SuppressWarnings("rawtypes")
    public FacetResult searchLaws(QueryInfo<Map<String, String>> queryInfo) {
        ParameterizedTypeReference<FacetResult> responseType = new ParameterizedTypeReference<FacetResult>() {
        };
        FacetResult facetResult = restClient.post(apiBaseUrl + "laws_manage/ajaxLawIndex", queryInfo, responseType);
        return facetResult;
    }

    public Map<String, Object> getLawsDetail(String lawId, String jingdu, String typeId, String type) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("id", lawId);
        params.add("jingdu", jingdu);
        params.add("typeId", typeId);
        params.add("type", type);
        String url = apiBaseUrl + "laws_manage/browseNumPlus";
        ParameterizedTypeReference<JsonResponse<String>> responseType = new ParameterizedTypeReference<JsonResponse<String>>() {
        };
        restClient.post(url, params, responseType);
        url = apiBaseUrl + "laws_search/getLawsDetail";
        ParameterizedTypeReference<JsonResponse<Map<String, Object>>> responseType1 = new ParameterizedTypeReference<JsonResponse<Map<String, Object>>>() {
        };
        return restClient.post(url, params, responseType1).getResult();
    }

    // 2017/02/28 需求1453 by wangchao 条文'相关案例'标签
    // 2017/03/24 需求1453改动 by wangchao START
    public List<String> getViolation(String lawsId) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("lawsId", lawsId);
        // 2017/03/24 需求1453改动 by wangchao END
        String url = apiBaseUrl + "laws_search/getViolation";
        ParameterizedTypeReference<JsonResponse<List<String>>> responseType = new ParameterizedTypeReference<JsonResponse<List<String>>>() {
        };
        return restClient.post(url, params, responseType).getResult();
    }

    // 法律更新，获取全部相关法律ID
    public List<String> getLawIdsList(String lawId) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("lawId", lawId);
        String url = apiBaseUrl + "laws_search/getLawIdsList";
        ParameterizedTypeReference<JsonResponse<List<String>>> responseType = new ParameterizedTypeReference<JsonResponse<List<String>>>() {
        };
        return restClient.post(url, params, responseType).getResult();
    }

    // 根据法律ID，获取相关条文ID
    public List<String> getLawItemIdsList(String dis) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("lawIdsList", dis);
        String url = apiBaseUrl + "laws_search/getLawItemIdsList";
        ParameterizedTypeReference<JsonResponse<List<String>>> responseType = new ParameterizedTypeReference<JsonResponse<List<String>>>() {
        };
        return restClient.post(url, params, responseType).getResult();
    }

    // 2017/02/28 需求1453 by wangchao END

    public Object getLawsJingduCount(String lawIds, String typeId) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("lawIds", lawIds);
        params.add("typeId", typeId);
        String url = apiBaseUrl + "laws_search/getLawsJingduCount";
        ParameterizedTypeReference<JsonResponse<List<Map<String, Object>>>> responseType = new ParameterizedTypeReference<JsonResponse<List<Map<String, Object>>>>() {
        };
        return restClient.post(url, params, responseType);
    }

    public Map<String, Object> getInvalidLaws(String lawsId) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("lawsId", lawsId);
        String url = apiBaseUrl + "laws_manage/getInvalidLaws";
        ParameterizedTypeReference<Map<String, Object>> responseType = new ParameterizedTypeReference<Map<String, Object>>() {
        };
        return restClient.post(url, params, responseType);
    }

    public Object addLawShareNums(String id, String title, String shareUrl) {
        String userId = getUserInfo().getInfo().get(Global.PERSON_ID).toString();
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("id", id);
        params.add("shareType", "0");
        params.add("userName", userId);
        params.add("shareUrl", shareUrl);
        params.add("title", title);
        String url = apiBaseUrl + "laws_manage/appShare";
        ParameterizedTypeReference<JsonResponse<Object>> responseType = new ParameterizedTypeReference<JsonResponse<Object>>() {
        };
        return restClient.post(url, params, responseType);
    }

    public Object saveFavourLaw(String lawId, String ip) {
        String userId = getUserInfo().getInfo().get(Global.PERSON_ID).toString();
        String companyCode = getUserInfo().getInfo().get(Global.COMPANY_CODE).toString();
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("userId", userId);
        params.add("companyCode", companyCode);
        params.add("lawId", lawId);
        params.add("ip", ip);
        params.add("source", Global.FAVOURITE_SOURCE_ENTERPRISE);
        String url = apiBaseUrl + "laws_search/lawsFavourSetting";
        ParameterizedTypeReference<Map<String, Object>> responseType = new ParameterizedTypeReference<Map<String, Object>>() {
        };
        return restClient.post(url, params, responseType).get("result");
    }
    //2017/06/07 需求1320 chiweiming  start
    public Object saveFavourStructure(String structureId, String ip) {
        String userId = getUserInfo().getInfo().get(Global.PERSON_ID).toString();
        String companyCode = getUserInfo().getInfo().get(Global.COMPANY_CODE).toString();
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("userId", userId);
        params.add("companyCode", companyCode);
        params.add("lawId", structureId);
        params.add("ip", ip);
        params.add("source", Global.FAVOURITE_SOURCE_ENTERPRISE);
        String url = apiBaseUrl + "laws_search/structureFavourSetting";
        ParameterizedTypeReference<Map<String, Object>> responseType = new ParameterizedTypeReference<Map<String, Object>>() {
        };
        return restClient.post(url, params, responseType).get("result");
    }

    @SuppressWarnings("unchecked")
    public List<String> structureFavoriteInit(String lawId) {
        String userId = getUserInfo().getInfo().get(Global.PERSON_ID).toString();
        String companyCode = getUserInfo().getInfo().get(Global.COMPANY_CODE).toString();
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("userId", userId);
        params.add("companyCode", companyCode);
        params.add("lawId", lawId);
        String url = apiBaseUrl + "laws_search/getStructureFavoriteList";
        ParameterizedTypeReference<Map<String, Object>> responseType = new ParameterizedTypeReference<Map<String, Object>>() {
        };
        return (List<String>) restClient.post(url, params, responseType).get("result");
    }
    //2017/06/07 需求1320 chiweiming  end

    public Boolean existFavourLaw(String lawId) {
        String userId = getUserInfo().getInfo().get(Global.PERSON_ID).toString();
        String companyCode = getUserInfo().getInfo().get(Global.COMPANY_CODE).toString();
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("userId", userId);
        params.add("companyCode", companyCode);
        params.add("lawId", lawId);
        String url = apiBaseUrl + "laws_search/existFavourLaw";
        ParameterizedTypeReference<Map<String, Object>> responseType = new ParameterizedTypeReference<Map<String, Object>>() {
        };
        return (Boolean) restClient.post(url, params, responseType).get("result");
    }

    public Boolean saveLawNote(String lawId, String lawItemId, String ip, String note) {
        String userId = getUserInfo().getInfo().get(Global.PERSON_ID).toString();
        String companyCode = getUserInfo().getInfo().get(Global.COMPANY_CODE).toString();
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("userId", userId);
        params.add("companyCode", companyCode);
        params.add("lawId", lawId);
        params.add("lawItemId", lawItemId);
        params.add("ip", ip);
        params.add("note", note);
        params.add("source", "1");
        String url = apiBaseUrl + "laws_search/lawsNoteSetting";
        ParameterizedTypeReference<JsonResponse<Boolean>> responseType = new ParameterizedTypeReference<JsonResponse<Boolean>>() {
        };
        return restClient.post(url, params, responseType).getResult();
    }

    public Object getLawNoteContent(String lawItemId) {
        String userId = getUserInfo().getInfo().get(Global.PERSON_ID).toString();
        String companyCode = getUserInfo().getInfo().get(Global.COMPANY_CODE).toString();
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("userId", userId);
        params.add("companyCode", companyCode);
        params.add("lawItemId", lawItemId);
        String url = apiBaseUrl + "laws_search/getLawNoteContent";
        ParameterizedTypeReference<Map<String, Object>> responseType = new ParameterizedTypeReference<Map<String, Object>>() {
        };
        return restClient.post(url, params, responseType).get("result");
    }

    public List<Map<String, Object>> getNearlyLaws() {
        String url = apiBaseUrl + "laws_search/getNearlyLaws";
        ParameterizedTypeReference<JsonResponse<List<Map<String, Object>>>> responseType = new ParameterizedTypeReference<JsonResponse<List<Map<String, Object>>>>() {
        };
        return restClient.post(url, null, responseType).getResult();
    }

    public List<Map<String, Object>> getHotLaws() {
        String url = apiBaseUrl + "laws_search/getHotLaws";
        ParameterizedTypeReference<JsonResponse<List<Map<String, Object>>>> responseType = new ParameterizedTypeReference<JsonResponse<List<Map<String, Object>>>>() {
        };
        return restClient.post(url, null, responseType).getResult();
    }

    public Map<String, Object> getLawClassWithCount(String parentId, String level, String publishedYear) {
        String url = apiBaseUrl + "laws_search/getLawsClassWithCount";
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("parentId", parentId);
        params.add("level", level);
        // 不取得法律法规跟节点分类
        params.add("havaRootFlg", "no");
        // 需求1164 2017/03/29 法律法规部件 bywangyu start
        if (StringUtils.isNotBlank(publishedYear) && !"全部".equals(publishedYear)) {
            String publishStart = publishedYear.concat("-01-01");
            String publishEnd = publishedYear.concat("-12-31");
            params.add("publishStart", publishStart);
            params.add("publishEnd", publishEnd);
        }
        // 需求1164 2017/03/29 法律法规部件 bywangyu end
        ParameterizedTypeReference<JsonResponse<Map<String, Object>>> responseType = new ParameterizedTypeReference<JsonResponse<Map<String, Object>>>() {
        };
        return restClient.post(url, params, responseType).getResult();
    }

    public Map<String, Object> getLawTypeWithCount(String parentId, String level, String publishedYear) {
        String url = apiBaseUrl + "laws_search/getLawsTypeWithCount";
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("parentId", parentId);
        params.add("level", level);
        // 需求1164 2017/03/29 法律法规部件 bywangyu start
        if (StringUtils.isNotBlank(publishedYear) && !"全部".equals(publishedYear)) {
            String publishStart = publishedYear.concat("-01-01");
            String publishEnd = publishedYear.concat("-12-31");
            params.add("publishStart", publishStart);
            params.add("publishEnd", publishEnd);
        }
        // 需求1164 2017/03/29 法律法规部件 bywangyu end
        ParameterizedTypeReference<JsonResponse<Map<String, Object>>> responseType = new ParameterizedTypeReference<JsonResponse<Map<String, Object>>>() {
        };
        return restClient.post(url, params, responseType).getResult();
    }

    public Map<String, Object> getLawsCountWithType(String type, String invalid, String publishedYear) {
        String url = apiBaseUrl + "laws_search/getLawsCountWithType";
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("type", type);
        params.add("invalid", invalid);
        // 需求1164 2017/03/29 法律法规部件 bywangyu start
        if (StringUtils.isNotBlank(publishedYear) && !"全部".equals(publishedYear)) {
            String publishStart = publishedYear.concat("-01-01");
            String publishEnd = publishedYear.concat("-12-31");
            params.add("publishStart", publishStart);
            params.add("publishEnd", publishEnd);
        }
        // 需求1164 2017/03/29 法律法规部件 bywangyu end
        ParameterizedTypeReference<JsonResponse<Map<String, Object>>> responseType = new ParameterizedTypeReference<JsonResponse<Map<String, Object>>>() {
        };
        return restClient.post(url, params, responseType).getResult();
    }

    public Object getFavouriteStatus(String lawIds) {
        String userId = getUserInfo().getInfo().get(Global.PERSON_ID).toString();
        String companyCode = getUserInfo().getInfo().get(Global.COMPANY_CODE).toString();
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("userId", userId);
        params.add("companyCode", companyCode);
        params.add("lawIds", lawIds);
        String url = apiBaseUrl + "laws_search/getFavouriteStatus";
        ParameterizedTypeReference<Map<String, Object>> responseType = new ParameterizedTypeReference<Map<String, Object>>() {
        };
        return restClient.post(url, params, responseType).get("result");
    }

    /**
     * TODO(统计法规数据). <br/>
     * @param statisticLawDto
     * @return
     */
    public List<Map<String, String>> queryLawStatisticData(StatisticLawDto statisticLawDto) {
        Map<String,Object> map = constructQueryData(statisticLawDto);
        String startDate = DateUtil.getDateStr((Date)map.get("startDate"),  DateUtil.YYYY_MM_DD);
        String endDate = DateUtil.getDateStr((Date)map.get("endDate"),DateUtil.YYYY_MM_DD);
        if(StringUtils.equals(statisticLawDto.getStatisIndex(), "6")){
            endDate = DateUtil.getDateStr(DateUtil.getLastDayOfMonth((Date)map.get("endDate")),DateUtil.YYYY_MM_DD);
        }
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("startDate", startDate);
        params.add("endDate", endDate);
        params.add("index", statisticLawDto.getStatisIndex());
        String url = apiBaseUrl + "laws_search/queryLawStatisticData";
        ParameterizedTypeReference<JsonResponse<List<Map<String, String>>>> responseType = new ParameterizedTypeReference<JsonResponse<List<Map<String, String>>>>() {
        };
        JsonResponse<List<Map<String, String>>> response = restClient.post(url, params, responseType);
        if(response.isSuccess()){
            return response.getResult();
        }else{
            throw new RuntimeException("queryLawStatisticData error");
        }
    }
    /**
     * TODO(构造统计时间). <br/>
     * @param statisticLawDto
     * @return
     *
     */
    private Map<String, Object> constructQueryData(StatisticLawDto statisticLawDto){
        Map<String,Object> map = new HashMap<String,Object>();
        Date startDate = null;
        Date endDate = getDBTime();
        if ("0".equals(statisticLawDto.getStatisIndex())) {
            startDate = DateUtil.datePlusToDate(DateUtil.datePlusWeeksToDate(getDBTime(), -1), null, 1);
        } else if ("1".equals(statisticLawDto.getStatisIndex())) {
            startDate = DateUtil.datePlusToDate(DateUtil.datePlusMonthsToDate(getDBTime(), -1), null, 1);
        } else if ("2".equals(statisticLawDto.getStatisIndex())) {
            startDate = DateUtil.datePlusToDate(DateUtil.datePlusMonthsToDate(getDBTime(), -3), null, 1);
        } else if ("3".equals(statisticLawDto.getStatisIndex())) {
            startDate = DateUtil.datePlusToDate(DateUtil.datePlusMonthsToDate(getDBTime(), -6), null, 1);
        } else if ("4".equals(statisticLawDto.getStatisIndex())) {
            startDate = DateUtil.datePlusToDate(DateUtil.datePlusYearToDate(getDBTime(), -1), null, 1);
        } else if ("5".equals(statisticLawDto.getStatisIndex())) {
            startDate = DateUtil.datePlusToDate(DateUtil.datePlusYearToDate(getDBTime(), -3), null, 1);
        } else if ("6".equals(statisticLawDto.getStatisIndex())) {
            startDate = statisticLawDto.getQueryStartDate();
            endDate = statisticLawDto.getQueryEndDate();
        }
        map.put("startDate", startDate);
        map.put("endDate", endDate);
        return map;
    }

    /**
     * TODO(按照单独月份统计更新数目). <br/>
     * @param statisticLawDto
     * @return
     *
     */
    public List<SearchDto> queryLawStatisticDataByPeriod(StatisticLawDto statisticLawDto) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("startDate",DateUtil.getDateStr(statisticLawDto.getQueryStartDate(), DateUtil.YYYY_MM_DD));
        params.add("endDate", DateUtil.getDateStr(statisticLawDto.getQueryEndDate(), DateUtil.YYYY_MM_DD));
        String url = apiBaseUrl + "laws_search/queryLawStatisticDataByPeriod";
        ParameterizedTypeReference<JsonResponse<List<SearchDto>>> responseType = new ParameterizedTypeReference<JsonResponse<List<SearchDto>>>() {
        };
        JsonResponse<List<SearchDto>> response = restClient.post(url, params, responseType);
        if(response.isSuccess()){
            return response.getResult();
        }else{
            throw new RuntimeException("queryLawStatisticDataByPeriod error");
        }
    }

    public LawDownDto getLawManage(String lawId,String jingdu, String typeId) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("lawId", lawId);
        //需求2275 2017/08/30 by liuhuan START
        params.add("jingdu", jingdu);
        params.add("typeId", typeId);
        //需求2275 2017/08/30 by liuhuan END
        String url = apiBaseUrl + "laws_search/getLawDownDto";
        ParameterizedTypeReference<JsonResponse<LawDownDto>> responseType = new ParameterizedTypeReference<JsonResponse<LawDownDto>>() {
        };
        LawDownDto lawDownDto =  restClient.post(url, params, responseType).getResult();
        return lawDownDto;
    }

//    public InputStream getLawInputStream(LawDownDto lawDownDto) throws IOException {
//        StringBuilder sb = new StringBuilder();
//        Date invalid = null;
//        String invalidDate = lawDownDto.getLawInvalid();
//        sb.append("<h1>"+lawDownDto.getLawsName()+"</h1>");
//        if(StringUtils.isNotEmpty(invalidDate)){
//            invalid = DateUtil.getDate(invalidDate,DateUtil.YYYY_MM_DD);
//            if(invalid.before(getDBTime())){
//                sb.append("<p>已失效</p>");
//            }else{
//                sb.append("<p>现行有效</p>");
//            }
//        }
//        if(lawDownDto.getPublished() != null){
//            sb.append("<p>"+ DateUtil.getDateStr(lawDownDto.getPublished(),DateUtil.YYYY_MM_DD_ZH)+" 颁布</p>");
//        }
//        if(StringUtils.isNotEmpty(lawDownDto.getLawSign())){
//            sb.append("<p>"+lawDownDto.getLawSign()+"</p>");
//        }
//        if(lawDownDto.getInvalidLaws() != null && lawDownDto.getInvalidLaws().size()>0){
//            if(StringUtils.isNotEmpty(invalidDate)){
//                for(InvalidLawsDto dto : lawDownDto.getInvalidLaws()) {
//                    sb.append("<p>于" + DateUtil.getDateStr(invalid, DateUtil.YYYY_MM_DD_ZH) + "被" +dto.getLawSource()+"实施的<a href='"+siteBaseUrl+"/"+dto.getId()+".html'>"+dto.getLawsName()+"</a>所废止。</p>");
//                }
//            }else{
//                for(InvalidLawsDto dto : lawDownDto.getInvalidLaws()) {
//                    sb.append("<p>于" + DateUtil.getDateStr(invalid, DateUtil.YYYY_MM_DD_ZH) + "被" +dto.getLawSource()+"实施的<a href='"+siteBaseUrl+"/"+dto.getId()+".html'>"+dto.getLawsName()+"</a>所修改。</p>");
//                }
//            }
//        }
//        sb.append("</br>");
//        //bug10438 2018/5/9 by liuh Start
//        //增加内容非空判断
//        if(StringUtils.isNotEmpty(lawDownDto.getLawMarkdownText())) {
//            sb.append(lawDownDto.getLawMarkdownText().replaceAll("<p>@@@</p>",StringUtils.EMPTY));
//        }
//        //bug10438 2018/5/9 by liuh end
//        if(lawDownDto.getAttFiles()!=null){
//            sb.append("</br>");
//            sb.append("<h1>相关附件</h1>");
//            for(Map.Entry<String, String> entry:lawDownDto.getAttFiles().entrySet()) {
//                sb.append("<p>附件：<a href='"+siteBaseUrl.substring(0,siteBaseUrl.indexOf("laws"))+entry.getValue()+"'>"+entry.getKey()+"</a></p>");
//            }
//        }
//        sb.append("<h1>声明</h1>");
//        sb.append("<p>所下载文档由价值在线提供</p>");
//        String content = "<html><head><style></style></head><body>" + sb.toString() + "</body></html>";
//
//        // Bug 10438 start
//        String encoding = "UTF-8";
//
//        if ("GB2312".equals(getEncoding(content))) {
//            encoding = "GB2312";
//        }
//
//        InputStream is = new ByteArrayInputStream(content.getBytes(encoding));
//        // Bug 10438 end
//        
//        // 需求3884 start
//        
//        // request
//        URL url = new URL(apiDocxUrl + "doc2docxService/doc2DocxHandler?suffix=doc");
//        HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
//        urlConn.setDoOutput(true);
//        urlConn.setDoInput(true);
//        urlConn.setRequestMethod("POST");
//        urlConn.setRequestProperty("Content-Type", "text/html");
//        urlConn.setRequestProperty("Cache-Control", "no-cache");
//        urlConn.setRequestProperty("Charsert", "UTF-8");
//        urlConn.connect();
//        urlConn.setConnectTimeout(100000);
//
//        OutputStream out = urlConn.getOutputStream();
//
//        //======================================================
//        ByteArrayOutputStream os = new ByteArrayOutputStream();
//        POIFSFileSystem fs = new POIFSFileSystem();
//        fs.createDocument(is, "WordDocument");
//        fs.writeFilesystem(os);
//        //======================================================
//        
//        InputStream inData = new ByteArrayInputStream(os.toByteArray());
//
//        int bytes = 0;
//        byte[] buffer = new byte[1024];
//        while ((bytes = inData.read(buffer)) != -1) {
//            out.write(buffer, 0, bytes);
//        }
//        is.close();
//        os.close();
//        inData.close();
//        out.close();
//        
//        // response
//        InputStream docxResponseStream = null;
//        
//        if (urlConn.getResponseCode() == 200) {
//            
//            ByteArrayOutputStream docxOs = new ByteArrayOutputStream();
//            
//            int b = 0;
//            byte[] buf = new byte[1024];
//            while ((b = urlConn.getInputStream().read(buf)) != -1) {
//                docxOs.write(buf, 0, b);
//            }
//            docxResponseStream = new ByteArrayInputStream(docxOs.toByteArray());
//        }
//        return docxResponseStream;
//        // 需求3884 end
//    }
    
    public boolean validateTagName(String tagNameId) {
		Map<String,String> param = Maps.newHashMap();
		param.put("tagName",tagNameId.trim());
		param.put("personId",getUserInfo().getInfo().get(Global.PERSON_ID).toString());
		int cout = companyRuleBizMapper.validateTagName(param);
		if (cout > 0) {
			return false;
		} else {
			return true;
		}
	}
    
    @Transactional(rollbackFor = { Exception.class })
	public OptionDto savePersonalTag(SearchLawsDTO searchLawsDTO) {
		OptionDto OptionDto = new OptionDto();
		LawIndexTag lawIndexTag = new LawIndexTag();
		if (StringUtils.isNotEmpty(searchLawsDTO.getLawSource())) {
			lawIndexTag.setUnits(searchLawsDTO.getLawSource());
		}
		if (StringUtils.isNotEmpty(searchLawsDTO.getLawRange())) {
			lawIndexTag.setApplicationScope(searchLawsDTO.getLawRange());
		}
		
		if (StringUtils.isNotEmpty(searchLawsDTO.getPublishedDate())) {
			// 起始日期
			String[] dateRange = searchLawsDTO.getPublishedDate().split(" 至 ");
			Date startDate = DateUtil.getDate(dateRange[0], DateUtil.YYYY_MM_DD);
			Date endDate = DateUtil.getDate(dateRange[1], DateUtil.YYYY_MM_DD);
			lawIndexTag.setPublishedStartDate(startDate);
			lawIndexTag.setPublishedEndDate(endDate);
		}
		
		if (StringUtils.isNotEmpty(searchLawsDTO.getInvalidDate())) {
			// 起始日期
			String[] dateRange = searchLawsDTO.getInvalidDate().split(" 至 ");
			Date startDate = DateUtil.getDate(dateRange[0], DateUtil.YYYY_MM_DD);
			Date endDate = DateUtil.getDate(dateRange[1], DateUtil.YYYY_MM_DD);
			lawIndexTag.setInvalidStartDate(startDate);
			lawIndexTag.setInvalidEndDate(endDate);
		}
		
		
		BeanUtil.copy(searchLawsDTO, lawIndexTag);
		if(StringUtils.isNotEmpty(searchLawsDTO.getInvalidTag())){
			lawIndexTag.setFlag(searchLawsDTO.getInvalidTag());
		}
		lawIndexTag.setTagName(searchLawsDTO.getNewTagName().trim());
		lawIndexTag.setPersonId(getUserInfo().getInfo().get(Global.PERSON_ID).toString());
		lawIndexTag.setCompanyId(getUserInfo().getInfo().get(Global.COMPANY_ID).toString());
		lawIndexTag.setKeyType("");
		
		lawIndexTagMapper.insert(lawIndexTag);
		OptionDto.setValue(lawIndexTag.getId());
		OptionDto.setLabel(lawIndexTag.getTagName());
		return OptionDto;
	}
    
    @Transactional(rollbackFor = { Exception.class })
	public String delMyTag(String tagId) {
    	lawIndexTagMapper.deleteByPrimaryKey(tagId);
		return tagId;
	}
    
    public SearchLawsDTO getMyTag(String tagId){
    	LawIndexTag lawIndexTag = lawIndexTagMapper.selectByPrimaryKey(tagId);
		SearchLawsDTO searchLawsDTO = new SearchLawsDTO();
		BeanUtil.copy(lawIndexTag, searchLawsDTO);
		searchLawsDTO.setInvalidTag(lawIndexTag.getFlag());
		searchLawsDTO.setLawRange(lawIndexTag.getApplicationScope());
		searchLawsDTO.setLawSource(lawIndexTag.getUnits());
		searchLawsDTO.setClassId(lawIndexTag.getId());;
		return searchLawsDTO;
    }
    
    public SearchLawsDTO selMyTag(String tagId) {
		LawIndexTag lawIndexTag = lawIndexTagMapper.selectByPrimaryKey(tagId);
		SearchLawsDTO searchLawsDTO = new SearchLawsDTO();
		BeanUtil.copy(lawIndexTag, searchLawsDTO);
		List<Code> a = this.getSource();
        List<Code> b = this.getRange();
        List<Code> e = this.getCodeList();
		if (lawIndexTag.getPublishedStartDate() != null && lawIndexTag.getPublishedEndDate() != null) {
			searchLawsDTO.setPublishedDate(DateUtil.getDateStr(lawIndexTag.getPublishedStartDate(), DateUtil.YYYY_MM_DD) + " 至 " + DateUtil.getDateStr(lawIndexTag.getPublishedEndDate(), DateUtil.YYYY_MM_DD));
		}
		
		if (lawIndexTag.getInvalidStartDate() != null && lawIndexTag.getInvalidEndDate() != null) {
			searchLawsDTO.setInvalidDate(DateUtil.getDateStr(lawIndexTag.getInvalidStartDate(), DateUtil.YYYY_MM_DD) + " 至 " + DateUtil.getDateStr(lawIndexTag.getInvalidEndDate(), DateUtil.YYYY_MM_DD));
		}
		String lawRangeName = "";
		String lawSourceName = "";
		
		searchLawsDTO.setInvalidTag(lawIndexTag.getFlag());
		searchLawsDTO.setLawRange(lawIndexTag.getApplicationScope());
		searchLawsDTO.setLawSource(lawIndexTag.getUnits());
		if(StringUtils.isNoneEmpty(lawIndexTag.getApplicationScope())){
			List<String> result = Arrays.asList(lawIndexTag.getApplicationScope().split(","));
			for(int i=0;i<result.size();i++){
				for(Code c : b){
					if(result.get(i).equals(c.getCodeValue())){
						if(i==0){
							lawRangeName = lawRangeName + c.getCodeName();
						}else{
							lawRangeName = lawRangeName+","+c.getCodeName();
						}
					}
				}
			}
			searchLawsDTO.setLawRangeList(result);
		}
		if(StringUtils.isNoneEmpty(lawIndexTag.getUnits())){
			List<String> result = Arrays.asList(lawIndexTag.getUnits().split(","));
			for(int i=0;i<result.size();i++){
				for(Code c : a){
					if(result.get(i).equals(c.getCodeValue())){
						if(i==0){
							lawSourceName = lawSourceName + c.getCodeName();
						}else{
							lawSourceName = lawSourceName+","+c.getCodeName();
						}
					}
				}
			}
			searchLawsDTO.setLawSourceList(result);
		}
        String invdLawsName = "";
		if(StringUtils.isNoneEmpty(lawIndexTag.getFlag())){
			List<String> result = Arrays.asList(lawIndexTag.getFlag().split(","));
			for(int i=0;i<result.size();i++){
				for(Code c : e){
					if(result.get(i).equals(c.getCodeValue())){
						if(i==0){
							invdLawsName = invdLawsName + c.getCodeName();
						}else{
							invdLawsName = invdLawsName+","+c.getCodeName();
						}
					}
				}
			}
			searchLawsDTO.setFlagList(result);
		}
		searchLawsDTO.setLawRangeShow(lawIndexTag.getApplicationScope());
		searchLawsDTO.setLawSourceShow(lawIndexTag.getUnits());
		searchLawsDTO.setLawRangeName(lawRangeName);
		searchLawsDTO.setLawSourceName(lawSourceName);
		searchLawsDTO.setInvdLawsName(invdLawsName);
		searchLawsDTO.setInvdLawsShow(lawIndexTag.getFlag());
		return searchLawsDTO;
	}
    
    public List<OptionDto> getMyTagList() {
		return companyRuleBizMapper.getMyTagList(getUserInfo().getInfo().get(Global.PERSON_ID).toString());
	}
    
    public List<Code> getCodeList(){
    	List<Code> cList = new ArrayList<Code>();
        Code code = new Code();
        code.setCodeName("现行有效");
        code.setCodeValue("0");
        Code code1 = new Code();
        code1.setCodeName("待生效");
        code1.setCodeValue("1");
        Code code2 = new Code();
        code2.setCodeName("已失效");
        code2.setCodeValue("2");
        cList.add(code);
        cList.add(code1);
        cList.add(code2);
        return cList;
    }
    
    // Bug 10438 add
    public static String getEncoding(String str) {
        String encode = "GB2312";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s = encode;
                return s;
            }
        } catch (Exception exception) {
        }
        encode = "ISO-8859-1";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s1 = encode;
                return s1;
            }
        } catch (Exception exception1) {
        }
        encode = "UTF-8";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s2 = encode;
                return s2;
            }
        } catch (Exception exception2) {
        }
        encode = "GBK";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s3 = encode;
                return s3;
            }
        } catch (Exception exception3) {
        }
        return "";
    }
}

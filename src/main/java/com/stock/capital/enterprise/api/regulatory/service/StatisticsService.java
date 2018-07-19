package com.stock.capital.enterprise.api.regulatory.service;

import com.google.common.collect.Maps;
import com.stock.capital.enterprise.api.regulatory.dao.StatisticsBizMapper;
import com.stock.capital.enterprise.api.regulatory.dto.StatisticsCompanyDto;
import com.stock.capital.enterprise.api.regulatory.dto.StatisticsParamDto;
import com.stock.capital.enterprise.api.regulatory.dto.StatisticsResultDto;
import com.stock.core.dto.*;
import com.stock.core.rest.RestClient;
import com.stock.core.service.BaseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class StatisticsService extends BaseService {

    @Autowired
    private StatisticsBizMapper statisticsBizMapper;

    /**
     * 接口调用
     */
    @Autowired
    private RestClient restClient;

    /**
     * 接口地址前缀
     */
    @Value("${api.baseUrl}")
    private String apiBaseUrl;

    /**
     * IPO在审项目数据统计
     *
     * @param letterId
     * @return
     */
    public List<StatisticsResultDto> getIPOReviewingStts() {
        String updateTime = statisticsBizMapper.getIPOLastTime();
        Map<String, Object> params = Maps.newHashMap();
        params.put("updateTime", updateTime);
        List<StatisticsResultDto> list = statisticsBizMapper.getIPOReviewingStts(params);
        return list;
    }

    //需求4439 2018/5/24 by liuh Start
    
    /**
     * 
     * IPO在审项目数据统计-历史
     *
     * @return
     *
     */
    public List<StatisticsResultDto> getIPOHistory() {
        return statisticsBizMapper.getIPOHistory(null);
    }
    
    /**
     * IPO保荐机构统计
     *
     * @param 
     * @return
     */
    public Page<StatisticsResultDto> getIPORecommendOrgStts(QueryInfo<Map<String, Object>> queryInfo) {
        String updateTime = statisticsBizMapper.getIPOLastTime();
//        Map<String, Object> params = Maps.newHashMap();
//        params.put("updateTime", updateTime);
        //截止时间
//        dto.setLastUpadteTime(updateTime);
        List<String> areaList = new ArrayList<String>();
        List<String> industryList = new ArrayList<String>();
        
        // 排序改为大写
        if (StringUtils.isNotEmpty(queryInfo.getOrderByOrder())) {
            queryInfo.setOrderByOrder(StringUtils.upperCase(queryInfo.getOrderByOrder()));
        }
        Map<String, Object> map = queryInfo.getCondition();
        //截止时间
        map.put("lastUpadteTime", updateTime);
        map.put("orderByName", queryInfo.getOrderByName());
        map.put("orderByOrder", queryInfo.getOrderByOrder());
        map.put("startRow", queryInfo.getStartRow());
        map.put("pageSize", queryInfo.getPageSize());
        //注册地
        if(map.get("registAddr") != null && map.get("registAddr") != ""){
            areaList = Arrays.asList(map.get("registAddr").toString().split(","));
            map.put("areaList", areaList);
        }
        //所属行业
        if(map.get("industry") != null && map.get("industry") != ""){
            industryList = Arrays.asList(map.get("industry").toString().split(","));
            map.put("industryList", industryList);
        }
        List<StatisticsResultDto> list = statisticsBizMapper.getIPORecommendOrgStts(map);
        int totalCount = statisticsBizMapper.getIPORecommendOrgSttsTotalCount(map);
        Page<StatisticsResultDto> result = new Page<StatisticsResultDto>();
        result.setData(list);
        result.setTotal(totalCount);
        return result;
    }

    /**
     * IPO会计师事务所统计
     *
     * @param letterId
     * @return
     */
    public Page<StatisticsResultDto> getIPOAccountantOfficeStts(QueryInfo<Map<String, Object>> queryInfo) {
        String updateTime = statisticsBizMapper.getIPOLastTime();
//        Map<String, Object> params = Maps.newHashMap();
//        params.put("updateTime", updateTime);
        //截止时间
//        dto.setLastUpadteTime(updateTime);
//        List<String> areaList = new ArrayList<String>();
//        List<String> industryList = new ArrayList<String>();
//        //注册地
//        if(StringUtils.isNotBlank(dto.getRegistAddr())){
//            areaList = Arrays.asList(dto.getRegistAddr().split(","));
//        }
//        //所属行业
//        if(StringUtils.isNotBlank(dto.getIndustry())){
//            industryList = Arrays.asList(dto.getIndustry().split(","));
//        }
//        dto.setAreaList(areaList);
//        dto.setIndustryList(industryList);
//        List<StatisticsResultDto> list = statisticsBizMapper.getIPOAccountantOfficeStts(dto);
//        return list;
        List<String> areaList = new ArrayList<String>();
        List<String> industryList = new ArrayList<String>();
        
        // 排序改为大写
        if (StringUtils.isNotEmpty(queryInfo.getOrderByOrder())) {
            queryInfo.setOrderByOrder(StringUtils.upperCase(queryInfo.getOrderByOrder()));
        }
        Map<String, Object> map = queryInfo.getCondition();
        //截止时间
        map.put("lastUpadteTime", updateTime);
        map.put("orderByName", queryInfo.getOrderByName());
        map.put("orderByOrder", queryInfo.getOrderByOrder());
        map.put("startRow", queryInfo.getStartRow());
        map.put("pageSize", queryInfo.getPageSize());
        //注册地
        if(map.get("registAddr") != null && map.get("registAddr") != ""){
            areaList = Arrays.asList(map.get("registAddr").toString().split(","));
            map.put("areaList", areaList);
        }
        //所属行业
        if(map.get("industry") != null && map.get("industry") != ""){
            industryList = Arrays.asList(map.get("industry").toString().split(","));
            map.put("industryList", industryList);
        }
        List<StatisticsResultDto> list = statisticsBizMapper.getIPOAccountantOfficeStts(map);
        int totalCount = statisticsBizMapper.getIPOAccountantOfficeSttsTotalCount(map);
        Page<StatisticsResultDto> result = new Page<StatisticsResultDto>();
        result.setData(list);
        result.setTotal(totalCount);
        return result;
    }

    /**
     * IPO律师事务所统计
     *
     * @param letterId
     * @return
     */
    public Page<StatisticsResultDto> getIPOLawFirmStts(QueryInfo<Map<String, Object>> queryInfo) {
        String updateTime = statisticsBizMapper.getIPOLastTime();
//        Map<String, Object> params = Maps.newHashMap();
//        params.put("updateTime", updateTime);
//        //截止时间
//        dto.setLastUpadteTime(updateTime);
//        List<String> areaList = new ArrayList<String>();
//        List<String> industryList = new ArrayList<String>();
//        //注册地
//        if(StringUtils.isNotBlank(dto.getRegistAddr())){
//            areaList = Arrays.asList(dto.getRegistAddr().split(","));
//        }
//        //所属行业
//        if(StringUtils.isNotBlank(dto.getIndustry())){
//            industryList = Arrays.asList(dto.getIndustry().split(","));
//        }
//        dto.setAreaList(areaList);
//        dto.setIndustryList(industryList);
//        List<StatisticsResultDto> list = statisticsBizMapper.getIPOLawFirmStts(dto);
//        return list;
        List<String> areaList = new ArrayList<String>();
        List<String> industryList = new ArrayList<String>();
        
        // 排序改为大写
        if (StringUtils.isNotEmpty(queryInfo.getOrderByOrder())) {
            queryInfo.setOrderByOrder(StringUtils.upperCase(queryInfo.getOrderByOrder()));
        }
        Map<String, Object> map = queryInfo.getCondition();
        //截止时间
        map.put("lastUpadteTime", updateTime);
        map.put("orderByName", queryInfo.getOrderByName());
        map.put("orderByOrder", queryInfo.getOrderByOrder());
        map.put("startRow", queryInfo.getStartRow());
        map.put("pageSize", queryInfo.getPageSize());
        //注册地
        if(map.get("registAddr") != null && map.get("registAddr") != ""){
            areaList = Arrays.asList(map.get("registAddr").toString().split(","));
            map.put("areaList", areaList);
        }
        //所属行业
        if(map.get("industry") != null && map.get("industry") != ""){
            industryList = Arrays.asList(map.get("industry").toString().split(","));
            map.put("industryList", industryList);
        }
        List<StatisticsResultDto> list = statisticsBizMapper.getIPOLawFirmStts(map);
        int totalCount = statisticsBizMapper.getIPOLawFirmSttsTotalCount(map);
        Page<StatisticsResultDto> result = new Page<StatisticsResultDto>();
        result.setData(list);
        result.setTotal(totalCount);
        return result;
    }
    //需求4439 2018/5/24 by liuh end

    /**
     * IPO最新一期日期获取
     *
     * @param letterId
     * @return
     */
    public String getIPOLastTime() {
        String updateTime = statisticsBizMapper.getIPOLastTime();
        return updateTime;
    }

    /**
     * 发行监管部再融资企业审核状态
     *
     * @param letterId
     * @return
     */
    public List<StatisticsResultDto> getRefinanceApproveStatusStts() {
        String updateTime = statisticsBizMapper.getRefinanceLastTime();
        Map<String, Object> params = Maps.newHashMap();
        params.put("updateTime", updateTime);
        List<StatisticsResultDto> list = statisticsBizMapper.getRefinanceApproveStatusStts(params);
        return list;
    }

    /**
     * 再融资申请类型情况
     *
     * @param letterId
     * @return
     */
    public List<StatisticsResultDto> getRefinanceAppTypeStts() {
        String updateTime = statisticsBizMapper.getRefinanceLastTime();
        Map<String, Object> params = Maps.newHashMap();
        params.put("updateTime", updateTime);
        List<StatisticsResultDto> list = statisticsBizMapper.getRefinanceAppTypeStts(params);
        return list;
    }

    /**
     * 发行监管部再融资审核保荐机构保荐企业数量
     *
     * @param letterId
     * @return
     */
    public List<StatisticsResultDto> getRefinanceRecommendOrgStts() {
        String updateTime = statisticsBizMapper.getRefinanceLastTime();
        Map<String, Object> params = Maps.newHashMap();
        params.put("updateTime", updateTime);
        List<StatisticsResultDto> list = statisticsBizMapper.getRefinanceRecommendOrgStts(params);
        return list;
    }

    /**
     * 再融资最新一期日期获取
     *
     * @param letterId
     * @return
     */
    public String getRefinanceLastTime() {
        String updateTime = statisticsBizMapper.getRefinanceLastTime();
        return updateTime;
    }

    public List<StatisticsResultDto> getIPOAreaDataStts(StatisticsParamDto statisticsParamDto) {
        String updateTime = statisticsBizMapper.getIPOLastTime();
        //截止时间
        statisticsParamDto.setLastUpadteTime(updateTime);
        //最近一周时间
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(format.parse(updateTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, - 7);
        statisticsParamDto.setLastWeek(format.format(c.getTime()));
        
        List<String> plateList = new ArrayList<String>();
        List<String> areaList = new ArrayList<String>();
        List<String> industryList = new ArrayList<String>();
        //所属板块
        if(StringUtils.isNotBlank(statisticsParamDto.getBelongsPlate())){
            plateList = Arrays.asList(statisticsParamDto.getBelongsPlate().split(","));
        }
        //注册地
        if(StringUtils.isNotBlank(statisticsParamDto.getRegistAddr())){
            areaList = Arrays.asList(statisticsParamDto.getRegistAddr().split(","));
        }
        //所属行业
        if(StringUtils.isNotBlank(statisticsParamDto.getIndustry())){
            industryList = Arrays.asList(statisticsParamDto.getIndustry().split(","));
        }
        statisticsParamDto.setPlateList(plateList);
        statisticsParamDto.setAreaList(areaList);
        statisticsParamDto.setIndustryList(industryList);
        List<StatisticsResultDto> list = statisticsBizMapper.getIPOAreaDataStts(statisticsParamDto);
        return list;
    }

    public List<OptionDto> getCodeAndName(String codeNo) {
        return statisticsBizMapper.getCodeAndName(codeNo);
    }

    public List<OptionDto> getAreaList() {
        return statisticsBizMapper.getAreaList();
    }

    public List<StatisticsCompanyDto> queryAreaDetail(StatisticsParamDto statisticsParamDto) {
        List<String> plateList = new ArrayList<String>();
        List<String> areaList = new ArrayList<String>();
        List<String> industryList = new ArrayList<String>();
        //所属板块
        if(StringUtils.isNotBlank(statisticsParamDto.getBelongsPlate())){
            plateList = Arrays.asList(statisticsParamDto.getBelongsPlate().split(","));
        }
        //注册地
        if(StringUtils.isNotBlank(statisticsParamDto.getRegistAddr())){
            areaList = Arrays.asList(statisticsParamDto.getRegistAddr().split(","));
        }
        //所属行业
        if(StringUtils.isNotBlank(statisticsParamDto.getIndustry())){
            industryList = Arrays.asList(statisticsParamDto.getIndustry().split(","));
        }
        //最近一周时间
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(format.parse(statisticsParamDto.getLastUpadteTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, - 7);
        statisticsParamDto.setLastWeek(format.format(c.getTime()));
        statisticsParamDto.setPlateList(plateList);
        statisticsParamDto.setAreaList(areaList);
        statisticsParamDto.setIndustryList(industryList);
        return statisticsBizMapper.queryAreaDetail(statisticsParamDto);
    }

    //地区特殊处理
    public String changeAreaName(String name) {
        String resultName = "";
        if (org.apache.commons.lang3.StringUtils.isNotBlank(name)) {
            if ("广东".equals(name)) {
                resultName = "广东(不含深圳)";
            } else if ("辽宁".equals(name)) {
                resultName = "辽宁(不含大连)";
            } else if ("浙江".equals(name)) {
                resultName = "浙江(不含宁波)";
            } else if ("福建".equals(name)) {
                resultName = "福建(不含厦门)";
            } else if ("山东".equals(name)) {
                resultName = "山东(不含青岛)";
            } else {
                resultName = name;
            }
        }
        return resultName;
    }

    public List<TreeDto> getIndustryList() {
        ParameterizedTypeReference<JsonResponse<List<TreeDto>>> responseType = new ParameterizedTypeReference<JsonResponse<List<TreeDto>>>() {
        };
        String url = apiBaseUrl + "declareInfo/postDeclareIndexIndustry";
        List<TreeDto> list = restClient.post(url, null, responseType).getResult();
        return list;
    }
}

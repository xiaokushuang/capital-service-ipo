package com.stock.capital.enterprise.api.regulatory.service;

import com.google.common.base.Throwables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.stock.capital.enterprise.api.regulatory.dao.ViolationBizMapper;
import com.stock.capital.enterprise.common.constant.Global;
import com.stock.capital.enterprise.common.service.IndexFieldBindService;
import com.stock.capital.enterprise.api.regulatory.dto.*;
import com.stock.capital.enterprise.api.regulatory.dto.ViolationDetailDto;
import com.stock.core.Constant;
import com.stock.core.dto.*;
import com.stock.core.rest.RestClient;
import com.stock.core.search.SearchServer;
import com.stock.core.search.SolrSearchUtil;
import com.stock.core.service.BaseService;
import com.stock.core.util.DateSplitUtil;
import com.stock.core.util.DateUtil;
import com.stock.core.util.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.response.PivotField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.math.BigDecimal;
import java.util.*;

@Service
public class ViolationService extends BaseService {

    /**
     * 违规类型CODE_NO
     */
    public final String VIOLATE_TYPE_TREE_TYPE = "1";
    /**
     * 处罚类型CODE_NO
     */
    public final String PUNISH_TYPE_TREE_TYPE = "2";
    /**
     * 处罚对象身份CODE_NO
     */
    public final String PERSON_IDT_CODE_NO = "PERSON_TYPE";
    /**
     * 申辩情况CODE_NO
     */
    public final String ADOPT_CONDITION_CODE_NO = "ADOPT_CONDITION";
    /**
     * 所属板块CODE_NO
     */
    public final String STOCK_BOARD_CODE_NO = "STOCK_BOARD";

	Logger log = LoggerFactory.getLogger(ViolationService.class);
	
	//Logger logger = LoggerFactory.getLogger(ActivationService.class);
	        

	@Autowired
	private SearchServer searchServer;

	@Autowired
	private ViolationBizMapper violationBizMapper;

	@Autowired
	private IndexFieldBindService indexFieldBindService;

	/**
	 * 获取code列表
	 *
	 * @param
	 * @return
	 */
	public List<OptionDto> getCodeList(String codeNo) {
		Map<String, Object> map = Maps.newHashMap();
		map.put("codeNo", codeNo);
		return violationBizMapper.getCodeList(map);
	}

	/**
	 * 获取处罚机构列表
	 *
	 * @param
	 * @return
	 */
	public List<Map<String, Object>> getPunishClassList(String ids) {
		Map<String, Object> map = Maps.newHashMap();
		map.put("status", "1");
		if (StringUtils.isNotEmpty(ids)) {
			map.put("ids", ids.split(","));
		}
		return violationBizMapper.getPunishClassList(map);
	}

	/**
	 * 获取未被禁用的违规类型、处罚类型列表
	 *
	 * @param
	 * @return
	 */
	public List<Map<String, Object>> getTypeList(String ids, String treeType) {
		Map<String, Object> map = Maps.newHashMap();
		map.put("treeType", treeType);
		map.put("status", "1");
		if (StringUtils.isNotEmpty(ids)) {
			map.put("ids", ids.split(","));
		}
		List<Map<String, Object>> result = violationBizMapper.getTypeList(map);
		// 去除子级未被禁用、父级被禁用的类型
		if ("1".equals(treeType)) {
			// 过滤二级
			Iterator<Map<String, Object>> a = result.iterator();
			while (a.hasNext()) {
				Map<String, Object> mapA = a.next();
				// 子级
				boolean tag = true;
				String parentId = (String) mapA.get("parentId");
				String levelA = String.valueOf(mapA.get("level"));
				if ("1".equals(levelA) && StringUtils.isNotEmpty(parentId)) {
					Iterator<Map<String, Object>> b = result.iterator();
					while (b.hasNext()) {
						Map<String, Object> mapB = b.next();
						// 父级
						String id = (String) mapB.get("id");
						String levelB = String.valueOf(mapB.get("level"));
						if ("0".equals(levelB) && parentId.equals(id)) {
							tag = false;
							break;
						}
					}
					if (tag) {
						a.remove();
					}
				}
			}
		}
		if ("2".equals(treeType)) {
			// 过滤二级
			Iterator<Map<String, Object>> a = result.iterator();
			while (a.hasNext()) {
				Map<String, Object> mapA = a.next();
				// 子级
				boolean tag = true;
				String parentId = (String) mapA.get("parentId");
				String levelA = String.valueOf(mapA.get("level"));
				if ("1".equals(levelA) && StringUtils.isNotEmpty(parentId)) {
					Iterator<Map<String, Object>> b = result.iterator();
					while (b.hasNext()) {
						Map<String, Object> mapB = b.next();
						// 父级
						String id = (String) mapB.get("id");
						String levelB = String.valueOf(mapB.get("level"));
						if ("0".equals(levelB) && parentId.equals(id)) {
							tag = false;
							break;
						}
					}
					if (tag) {
						a.remove();
					}
				}
			}
			// 过滤三级
			a = result.iterator();
			while (a.hasNext()) {
				Map<String, Object> mapA = a.next();
				// 子级
				boolean tag = true;
				String parentId = (String) mapA.get("parentId");
				String levelA = String.valueOf(mapA.get("level"));
				if ("2".equals(levelA) && StringUtils.isNotEmpty(parentId)) {
					Iterator<Map<String, Object>> b = result.iterator();
					while (b.hasNext()) {
						Map<String, Object> mapB = b.next();
						// 父级
						String id = (String) mapB.get("id");
						String levelB = String.valueOf(mapB.get("level"));
						if ("1".equals(levelB) && parentId.equals(id)) {
							tag = false;
							break;
						}
					}
					if (tag) {
						a.remove();
					}
				}
			}
		}
		return result;
	}
	
	/**
	 * 
         * 需求3851 by weishisheng 2018/03/19
         * 获取所属证监局列表
	 *
	 * @return
	 */
	public List<Map<String, Object>> getBelongsToSRBList() {
            Map<String, Object> map = Maps.newHashMap();
	    return violationBizMapper.getBelongsToSRBList(map);
	}
	
	/**
	 * 获取主办券商BrokerCodeList
	 * 需求1711 Start 2017/09/11 *违规处罚-案例查询-主办券商* byzhaozilong 2017/09/11 end
	 * @param
	 * @return
	 */
	public List<Map<String, Object>> getBrokerCodeList() {
		Map<String, Object> map = Maps.newHashMap();
		return violationBizMapper.getBrokerCodeList(map);
	}

	/**
	 * 获取已被禁用的违规类型、处罚类型列表
	 *
	 * @param
	 * @return
	 */
	public List<Map<String, Object>> getForbidenTypeList(String ids, String treeType) {
		Map<String, Object> map = Maps.newHashMap();
		map.put("treeType", treeType);
		map.put("status", "0");
		if (StringUtils.isNotEmpty(ids)) {
			map.put("ids", ids.split(","));
		}
		return violationBizMapper.getTypeList(map);
	}

	/**
	 * 检索处罚明细
	 *
	 * @param
	 * @return
	 */
	public FacetResult<PunishmentSearchDto> searchPunishment(QueryInfo<Map<String, String>> queryInfo) {
		// 涉及统计的功能，必须排除已被禁用的类型。
		if (queryInfo != null && queryInfo.getCondition() != null && StringUtils.isNotEmpty(queryInfo.getCondition().get(Constant.SEARCH_FACET_FIELD))) {
			// 设置已被禁用的类型列表
			List<String> violateTypeList = Lists.newArrayList();
			List<String> punishmentTypeList = Lists.newArrayList();
			List<Map<String, Object>> forbidenViolateTypeList = getForbidenTypeList(null, "1");
			List<Map<String, Object>> forbidenPunishmentTypeList = getForbidenTypeList(null, "2");
			if (forbidenViolateTypeList.size() > 0 || forbidenPunishmentTypeList.size() > 0) {
				for (Map<String, Object> obj : forbidenViolateTypeList) {
					String value = (String) obj.get("value");
					if (StringUtils.isNotEmpty(value)) {
						violateTypeList.add(value);
					}
				}
				for (Map<String, Object> obj : forbidenPunishmentTypeList) {
					String value = (String) obj.get("value");
					if (StringUtils.isNotEmpty(value)) {
						punishmentTypeList.add(value);
					}
				}
				// 设置检索条件
				Map<String, String> condition = queryInfo.getCondition();
				String conStr = condition.get(Constant.SEARCH_CONDIATION);
				if (StringUtils.isEmpty(conStr)) {
					conStr = "index_type_t:" + Global.PUNISHMENT_INDEX_NAME;
				}
				conStr = SolrSearchUtil.transformListToString(conStr, violateTypeList, "punishment_violate_type_vo_txt", false, false, true);
				conStr = SolrSearchUtil.transformListToString(conStr, punishmentTypeList, "punishment_punish_type_txt", false, false, true);
				condition.put(Constant.SEARCH_CONDIATION, conStr);
				queryInfo.setCondition(condition);
			}
		}
		// 检索
		FacetResult<PunishmentSearchDto> page = searchServer.searchWithFacet(Global.PUNISHMENT_INDEX_NAME, queryInfo, PunishmentSearchDto.class);
		return page;
	}

    /**
     * 检索违规案例
     *
     * @param
     * @return
     */
    public FacetResult<ViolationSearchDto> searchViolation(QueryInfo<Map<String, String>> queryInfo) {
        // 涉及统计的功能，必须排除已被禁用的类型。
        if (queryInfo != null && queryInfo.getCondition() != null
                && StringUtils.isNotEmpty(queryInfo.getCondition().get(Constant.SEARCH_FACET_FIELD))) {
            // 设置已被禁用的类型列表
            List<String> violateTypeList = Lists.newArrayList();
            List<String> punishmentTypeList = Lists.newArrayList();
            List<Map<String, Object>> forbidenViolateTypeList = getForbidenTypeList(null, "1");
            List<Map<String, Object>> forbidenPunishmentTypeList = getForbidenTypeList(null, "2");
            if (forbidenViolateTypeList.size() > 0 || forbidenPunishmentTypeList.size() > 0) {
                for (Map<String, Object> obj : forbidenViolateTypeList) {
                    String value = (String) obj.get("value");
                    if (StringUtils.isNotEmpty(value)) {
                        violateTypeList.add(value);
                    }
                }
                for (Map<String, Object> obj : forbidenPunishmentTypeList) {
                    String value = (String) obj.get("value");
                    if (StringUtils.isNotEmpty(value)) {
                        punishmentTypeList.add(value);
                    }
                }
                // 设置检索条件
                Map<String, String> condition = queryInfo.getCondition();
                String conStr = condition.get(Constant.SEARCH_CONDIATION);
                if (StringUtils.isEmpty(conStr)) {
                    conStr = "index_type_t:" + Global.VIOLATION_INDEX_NAME;
                }
                conStr = SolrSearchUtil.transformListToString(conStr, violateTypeList, "violation_violate_type_vo_txt",
                        false, false, true);
                conStr = SolrSearchUtil.transformListToString(conStr, punishmentTypeList, "violation_punish_type_txt",
                        false, false, true);
                condition.put(Constant.SEARCH_CONDIATION, conStr);
                queryInfo.setCondition(condition);
            }
        }
        // 检索
        FacetResult<ViolationSearchDto> page = searchServer.searchWithFacet(Global.VIOLATION_INDEX_NAME, queryInfo,
                ViolationSearchDto.class);
        if (page.getPage().getTotal() == 0) {
            indexFieldBindService.dealNoResultKeyWord(queryInfo.getCondition().get(Constant.SEARCH_CONDIATION),
                    ViolationSearchDto.class);
        }
        return page;
    }

    /**
     * 获取违规案例的收藏列表
     *
     * @param
     * @return
     */
    public Page<ViolationFavourDto> favourList(String userId, String companyCode, String key, String violateType,
            String adoptCondition, String personType, String belongsToSRBType, String punishType, String vioCompanyCode, String startDate,
            String endDate, String startRow, String pageSize, String orderColumn, String orderByOrder) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("userId", userId);
        params.put("companyCode", companyCode);
        params.put("vioCompanyCode", vioCompanyCode);
        params.put("type", Global.FAVOURITE_TYPE_VIOLATION);
        String[] sources = { Global.FAVOURITE_SOURCE_ENTERPRISE, Global.FAVOURITE_SOURCE_ENTERPRISE_APP };
        params.put("sources", sources);
        params.put("key", key);
        if (StringUtils.isNotEmpty(violateType)) {
            params.put("violateType", violateType);
            params.put("violateTypes", violateType.split(","));
        }
        if (StringUtils.isNotEmpty(adoptCondition)) {
            params.put("adoptCondition", adoptCondition);
            params.put("adoptConditions", adoptCondition.split(","));
        }
        if (StringUtils.isNotEmpty(personType)) {
            params.put("personType", personType);
            params.put("personTypes", personType.split(","));
        }
        if (StringUtils.isNotEmpty(punishType)) {
            params.put("punishType", punishType);
            params.put("punishTypes", punishType.split(","));
        }
        // 需求3851 by weishisheng 2018/03/25 start
        if (StringUtils.isNotEmpty(belongsToSRBType)) {
            params.put("belongsToSRBType", belongsToSRBType);
        }
        // 需求3851 by weishisheng 2018/03/25 start
        params.put("startDate", startDate);
        params.put("endDate", endDate);
        params.put("orderColumn", orderColumn);
        params.put("orderByOrder", orderByOrder);
        params.put("startRow", Integer.valueOf(startRow));
        params.put("pageSize", Integer.valueOf(pageSize));
        List<ViolationFavourDto> lst = violationBizMapper.getFavourList(params);
        int totalCount = violationBizMapper.getFavourListTotalCount(params);
        Page<ViolationFavourDto> page = new Page<ViolationFavourDto>();
        page.setData(lst);
        page.setTotal(totalCount);
        return page;
    }

    /**
     * 获取违规案例详情
     *
     * @param
     * @return
     */
    public ViolationDetailDto getViolationDetail(String id) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("id", id);
        ViolationDetailDto dto = violationBizMapper.getDetail(params);
        if (dto != null) {
            // 获取进程节点详情
            List<ViolationDetailNodeDto> nodeList = violationBizMapper.getDetailProcessNodeList(params);
            for (ViolationDetailNodeDto obj : nodeList) {
                Map<String, Object> tempParams = Maps.newHashMap();
                tempParams.put("id", id);
                tempParams.put("sort", obj.getSort());
                obj.setUrlList(violationBizMapper.getDetailProcessNodeUrlList(tempParams));
            }
            dto.setProcessNodeList(nodeList);
            // 获取处罚对象
            List<ViolationDetailPersonDto> personList = violationBizMapper.getDetailPersonList(params);
            // bug5237 by panqigeng 2017/09/18 start
            // 获取处分类型level,保持跟云端和企业端一致,不显示的时候全都不显示
            if (personList.size() > 0) {
                for (int i = 0; i < personList.size(); i++) {
                    if (StringUtils.isNotEmpty(personList.get(i).getPunishLevel())) {
                        if (personList.get(i).getPunishLevel().contains("、")) {
                            String[] punishLevelString = personList.get(i).getPunishLevel().split("、");
                            for (int j = 0; j < punishLevelString.length; j++) {
                                if ("1".equals(punishLevelString[j])) {
                                    personList.get(i).setPunishType("");
                                }
                            }
                        } else {
                            if ("1".equals(personList.get(i).getPunishLevel())) {
                                personList.get(i).setPunishType("");
                            }
                        }
                    }
                }
            }
            // bug5237 by panqigeng 2017/09/18 end
            dto.setPersonList(personList);
            // 获取关联法规
            List<ViolationDetailLinkDto> lawsList = violationBizMapper.getDetailLawsList(params);
            dto.setLawsList(lawsList);
            // 获取关联案例
            List<ViolationDetailLinkDto> casesList = violationBizMapper.getDetailCasesList(params);
            dto.setCasesList(casesList);
        }
        return dto;
    }

    /**
     * 获取处罚对象列表（主要用于排序）
     *
     * @param
     * @return
     */
    public List<ViolationDetailPersonDto> getDetailPersonList(String id, String orderColumn, String orderByOrder) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("id", id);
        params.put("orderColumn", orderColumn);
        params.put("orderByOrder", orderByOrder);
        // 获取处罚对象
        List<ViolationDetailPersonDto> personList = violationBizMapper.getDetailPersonList(params);
        if (personList.size() > 0) {
            for (int i = 0; i < personList.size(); i++) {
                if (StringUtils.isNotEmpty(personList.get(i).getPunishLevel())) {
                    if (personList.get(i).getPunishLevel().contains("、")) {
                        String[] punishLevelString = personList.get(i).getPunishLevel().split("、");
                        for (int j = 0; j < punishLevelString.length; j++) {
                            if ("1".equals(punishLevelString[j])) {
                                personList.get(i).setPunishType("");
                            }
                        }
                    } else {
                        if ("1".equals(personList.get(i).getPunishLevel())) {
                            personList.get(i).setPunishType("");
                        }
                    }
                }
            }
        }
        return personList;
    }


    /**
     * 检索违规案例和处罚明细索引-违规统计图表数据
     * 
     * @param map
     * @return
     */
    public Map<String, String> getViolationOverview(Map<String, String> map) {
        Map<String, String> result = Maps.newHashMap();
        List<Map<String, String>> resultList = Lists.newArrayList();
        List<String> activeTypeList = Arrays.asList(map.get("activeTypes").split(","));
        for (String activeType : activeTypeList) {
            map.remove("activeTypes");
            map.put("activeTypes", activeType);

            // 各图表统计数据取得
            resultList = getViolationOverviewDataInfo(map);

            // 设置图表数据
            result.put("researchResultTab" + activeType, JsonUtil.toJson(resultList));

            // 设置图表时间选择值
            result.put("dateStartTb" + activeType, map.get("dateStart"));
            result.put("dateEndTb" + activeType, map.get("dateEnd"));
        }

        // 取得指标数据
        if (activeTypeList.contains("1") || activeTypeList.contains("2") 
        		|| activeTypeList.contains("4")|| activeTypeList.contains("5")|| activeTypeList.contains("6")) {
            String dateStartStr = map.get("dateStart");
            String dateEndStr = map.get("dateEnd");
            
            if (StringUtils.isNotEmpty(dateStartStr) && StringUtils.isNotEmpty(dateEndStr)) {
                // 格式化开始结束日期
                Date dateStart = DateUtil.getDate(dateStartStr, DateUtil.YYYY_MM_DD);
                Date dateEnd = DateUtil.getDate(dateEndStr, DateUtil.YYYY_MM_DD);
                String conditionStr = SolrSearchUtil.parseDateKeyWords("violation_update_time_dt", dateStart, dateEnd);
                result.put("violateCount", String.valueOf(getViolateCount(conditionStr)));
                conditionStr = SolrSearchUtil.parseDateKeyWords("punishment_update_time_dt", dateStart, dateEnd);
                Map<String, Object> punishMap = getPunishmentCountAndSum(conditionStr);
                result.put("punishmentCount", String.valueOf(punishMap.get("count")));
                result.put("punishmentAmountCount", String.valueOf(punishMap.get("amountSum")));
            }
        }
        
        return result;
    }

    /**
     * 检索投关-调研概况图表数据
     * 
     * @param map
     * @return
     */
    private List<Map<String, String>> getViolationOverviewDataInfo(Map<String, String> map) {
        List<Map<String, String>> result = new ArrayList<Map<String, String>>();
        // 日期开始时间
        String dateStartStr = map.get("dateStart");
        // 日期结束时间
        String dateEndStr = map.get("dateEnd");
        // 图表类型 0：柱状图 1：扇形图 2地图
        String activeTypes = map.get("activeTypes");
        // 查询日期范围 0：一周 1：30天 2：90天 3：180天 4：360天 5：1080天 6自定义
        String countType = map.get("countType");

        String facetField = "";
        String indexType = "";
        String facetPivotField = "";
        String conditionsStr = "";
        String dataSourceType = map.get("dataSourceType");

        log.debug(
                "*******ResearchOverview*******param ： dateStartStr = {},dateEndStr = {},activeTypes = {}, countType = {}",
                dateStartStr, dateEndStr, activeTypes, countType);
        if (StringUtils.isNotEmpty(dateStartStr) && StringUtils.isNotEmpty(dateEndStr)) {
            // 格式化开始结束日期
            Date dateStart = DateUtil.getDate(dateStartStr, DateUtil.YYYY_MM_DD);
            Date dateEnd = DateUtil.getDate(dateEndStr, DateUtil.YYYY_MM_DD);
            // 添加日期查询的条件
            conditionsStr = SolrSearchUtil.parseDateKeyWords("violation_update_time_dt", dateStart, dateEnd);
            // 各图表数据取得
            if ("1".equals(activeTypes)) {// 扇形图数据(按违规类型分组统计)
                if ("0".equals(dataSourceType)) {
                    // 违规统计
                    facetField = "violation_violate_type_vo_txt";
                    indexType = "violation";
                    result = searchWithFacetInfoFromViolation(indexType, facetField, conditionsStr, true, 100, true);
                } else if ("1".equals(dataSourceType)) {
                    // 处罚对象
                    facetField = "punishment_violate_type_vo_txt";
                    indexType = "punishment";
                    conditionsStr = SolrSearchUtil.parseDateKeyWords("punishment_update_time_dt", dateStart, dateEnd);
                    result = searchWithFacetInfoFromPunishment(indexType, facetField, conditionsStr, true, 100, true);
                } else {
                    // 处罚金额
                    facetField = "punishment_violate_type_vo_txt";
                    facetPivotField = "punishment_punish_amount_d";
                    indexType = "punishment";
                    conditionsStr = SolrSearchUtil.parseDateKeyWords("punishment_update_time_dt", dateStart, dateEnd);
                    result = searchWithStatsInfoFromPunishment(indexType, facetField, facetPivotField, conditionsStr, 100, true);
                }
                result = getViolateClassTypeCount(result, "1");                
            } else if ("2".equals(activeTypes)) {// 扇形图数据(按处罚类型分组统计)
                if ("0".equals(dataSourceType)) {
                    // 违规统计
                    facetField = "violation_punish_type_txt";
                    indexType = "violation";
                    result = searchWithFacetInfoFromViolation(indexType, facetField, conditionsStr, true, 100, true);
                } else if ("1".equals(dataSourceType)) {
                    // 处罚对象
                    facetField = "punishment_punish_type_txt";
                    indexType = "punishment";
                    conditionsStr = SolrSearchUtil.parseDateKeyWords("punishment_update_time_dt", dateStart, dateEnd);
                    result = searchWithFacetInfoFromPunishment(indexType, facetField, conditionsStr, true, 100, true);
                } else {
                    // 处罚金额
                    facetField = "punishment_punish_type_txt";
                    facetPivotField = "punishment_punish_amount_d";
                    indexType = "punishment";
                    conditionsStr = SolrSearchUtil.parseDateKeyWords("punishment_update_time_dt", dateStart, dateEnd);
                    result = searchWithStatsInfoFromPunishment(indexType, facetField, facetPivotField, conditionsStr, 100, true);
                }
                result = getViolateClassTypeCount(result, "2");                
            } else if ("3".equals(activeTypes)) {// 线型图数据
                conditionsStr = "";
                List<Map<String, String>> dateList = Lists.newArrayList();
                if ("0".equals(countType)) {// 以日为单位统计
                    dateList = DateSplitUtil.getDateSplit(dateStartStr, dateEndStr, DateSplitUtil.SPLIT_DAY);
                } else if ("1".equals(countType) || "2".equals(countType)) {// 以7天（周）为单位统计
                    dateList = DateSplitUtil.getDateSplit(dateStartStr, dateEndStr, DateSplitUtil.SPLIT_WEEK);
                } else if ("3".equals(countType) || "4".equals(countType)) {// 以30天（月）为单位统计
                    dateList = DateSplitUtil.getDateSplit(dateStartStr, dateEndStr, DateSplitUtil.SPLIT_MONTH);
                } else if ("5".equals(countType)) {// 以90天（季度）为单位统计
                    dateList = DateSplitUtil.getDateSplit(dateStartStr, dateEndStr, DateSplitUtil.SPLIT_SEASON);
                } else if ("7".equals(countType)) {// 以360天（年）为单位统计
                    dateList = DateSplitUtil.getDateSplit(dateStartStr, dateEndStr, DateSplitUtil.SPLIT_YEARS);
                }
                result = getLineGraphInfo(dateList, countType);

            } else if ("4".equals(activeTypes)) {// 扇形图（所属行业）
                if ("0".equals(dataSourceType)) {
                    // 违规统计
                    facetField = "violation_p_industry_name_s";
                    indexType = "violation";
                    result = searchWithFacetInfoFromViolation(indexType, facetField, conditionsStr, true, 100, false);
                } else if ("1".equals(dataSourceType)) {
                    // 处罚对象
                    facetField = "punishment_p_industry_name_s";
                    indexType = "punishment";
                    conditionsStr = SolrSearchUtil.parseDateKeyWords("punishment_update_time_dt", dateStart, dateEnd);
                    result = searchWithFacetInfoFromPunishment(indexType, facetField, conditionsStr, true, 100, true);
                } else {
                    // 处罚金额
                    facetField = "punishment_p_industry_name_s";
                    facetPivotField = "punishment_punish_amount_d";
                    indexType = "punishment";
                    conditionsStr = SolrSearchUtil.parseDateKeyWords("punishment_update_time_dt", dateStart, dateEnd);
                    result = searchWithStatsInfoFromPunishment(indexType, facetField, facetPivotField, conditionsStr, 100, false);
                }
            } else if ("5".equals(activeTypes)) {// 地图
                if ("0".equals(dataSourceType)) {
                    // 违规统计
                    facetField = "violation_p_com_city_name_s";
                    indexType = "violation";
                    result = searchWithFacetInfoFromViolation(indexType, facetField, conditionsStr, true, 100, false);
                } else if ("1".equals(dataSourceType)) {
                    // 处罚对象
                    facetField = "punishment_p_com_city_name_s";
                    indexType = "punishment";
                    conditionsStr = SolrSearchUtil.parseDateKeyWords("punishment_update_time_dt", dateStart, dateEnd);
                    result = searchWithFacetInfoFromPunishment(indexType, facetField, conditionsStr, true, 100, true);
                } else {
                    // 处罚金额
                    facetField = "punishment_p_com_city_name_s";
                    facetPivotField = "punishment_punish_amount_d";
                    indexType = "punishment";
                    conditionsStr = SolrSearchUtil.parseDateKeyWords("punishment_update_time_dt", dateStart, dateEnd);
                    result = searchWithStatsInfoFromPunishment(indexType, facetField, facetPivotField, conditionsStr, 100, false);
                }
            } else if ("6".equals(activeTypes)) {// 扇形图所属板块
                if ("0".equals(dataSourceType)) {
                    // 违规统计
                    facetField = "violation_com_belongs_plate_name_s";
                    indexType = "violation";
                    result = searchWithFacetInfoFromViolation(indexType, facetField, conditionsStr, true, 100, false);
                } else if ("1".equals(dataSourceType)) {
                    // 处罚对象
                    facetField = "punishment_com_belongs_plate_name_s";
                    indexType = "punishment";
                    conditionsStr = SolrSearchUtil.parseDateKeyWords("punishment_update_time_dt", dateStart, dateEnd);
                    result = searchWithFacetInfoFromPunishment(indexType, facetField, conditionsStr, true, 100, true);
                } else {
                    // 处罚金额
                    facetField = "punishment_com_belongs_plate_name_s";
                    facetPivotField = "punishment_punish_amount_d";
                    indexType = "punishment";
                    conditionsStr = SolrSearchUtil.parseDateKeyWords("punishment_update_time_dt", dateStart, dateEnd);
                    result = searchWithStatsInfoFromPunishment(indexType, facetField, facetPivotField, conditionsStr, 100, false);
                }
            } else if ("7".equals(activeTypes)) {// 处罚对象身份机构
                // 违规统计
                facetField = "punishment_person_identity_txt";
                indexType = "punishment";
                //result = searchWithFacetInfoFromViolation(indexType, facetField, conditionsStr, true, 100, false);
                //searchWithFacetInfoFromPunishment
                conditionsStr = SolrSearchUtil.parseDateKeyWords("punishment_update_time_dt", dateStart, dateEnd);
                result = searchWithFacetInfoFromPunishment(indexType, facetField, conditionsStr, true, 100, false);
                result = getPersonIdentityInfo(result);
            } else if ("8".equals(activeTypes)) {// 监管机构处罚情况
                // 违规统计
                facetField = "violation_punish_organization_t";
                indexType = "violation";
                result = searchWithFacetInfoFromViolation(indexType, facetField, conditionsStr, true, 100, false);
                result = getPunishClassNameInfo(result);
            } else if ("9".equals(activeTypes)) {// 处罚后申辩情况
                // 违规统计
                conditionsStr = "";
                List<Map<String, String>> dateList = Lists.newArrayList();
                if ("0".equals(countType)) {// 以日为单位统计
                    dateList = DateSplitUtil.getDateSplit(dateStartStr, dateEndStr, DateSplitUtil.SPLIT_DAY);
                } else if ("1".equals(countType) || "2".equals(countType)) {// 以7天（周）为单位统计
                    dateList = DateSplitUtil.getDateSplit(dateStartStr, dateEndStr, DateSplitUtil.SPLIT_WEEK);
                } else if ("3".equals(countType) || "4".equals(countType)) {// 以30天（月）为单位统计
                    dateList = DateSplitUtil.getDateSplit(dateStartStr, dateEndStr, DateSplitUtil.SPLIT_MONTH);
                } else if ("5".equals(countType)) {// 以90天（季度）为单位统计
                    dateList = DateSplitUtil.getDateSplit(dateStartStr, dateEndStr, DateSplitUtil.SPLIT_SEASON);
                } else if ("7".equals(countType)) {// 以360天（年）为单位统计
                    dateList = DateSplitUtil.getDateSplit(dateStartStr, dateEndStr, DateSplitUtil.SPLIT_YEARS);
                }
                result = getBarCharInfo(dateList, countType);
            } else if ("10".equals(activeTypes)) {
            	// 处罚金额排名
                // 处罚金额
                facetField = "punishment_person_identity_txt";
            	//facetField = "punishment_person_t";
                facetPivotField = "punishment_punish_amount_d";
                indexType = "punishment";
                conditionsStr = SolrSearchUtil.parseDateKeyWords("punishment_update_time_dt", dateStart, dateEnd);
                result = searchWithStatsInfoFromPunishment(indexType, facetField, facetPivotField, conditionsStr, 100, false);
                result = getPersonIdentityInfo(result);
            }

        }
        log.debug("*******ResearchOverview*******DATA ： {}", JsonUtil.toJson(result));
        return result;
    }

 // 取得分组统计索引内的数据
    private List<Map<String, String>> searchWithFacetInfoFromPunishment(String indexType, String facetField, String conditionsStr, boolean zeroFlag, int cnt, boolean changFlag) {
        List<Map<String, String>> result = new ArrayList<Map<String, String>>();
        QueryInfo<Map<String, String>> queryInfo = new QueryInfo<Map<String, String>>();
        Map<String, String> condition = Maps.newHashMap();
        // 设置查询条件
        condition.put(Constant.SEARCH_CONDIATION, conditionsStr);
        // 设置分组字段
        condition.put(Constant.SEARCH_FACET_FIELD, facetField);

        queryInfo.setCondition(condition);
        log.debug("*******search index data*******");
        FacetResult<PunishmentSearchDto> page = searchServer.searchWithFacet(indexType, queryInfo, PunishmentSearchDto.class);
        List<StatisticsField> field = page.getStatisticsFieldMap().get(facetField);
        int i = 0;
        Map<String, String> dataMap = new HashMap<String, String>();
        for (StatisticsField sf : field) {
            if (i == cnt) {
                break;
            }

            if (zeroFlag && sf.getCount() == 0) {
                continue;
            }

            dataMap = new HashMap<String, String>();
            dataMap.put("name", sf.getFieldId());
            dataMap.put("key", sf.getFieldId());
            dataMap.put("value", String.valueOf(sf.getCount()));

            result.add(dataMap);

            i++;
        }
        return result;
    }
    
    // 取得分组统计索引内的数据并按指定的字段计算加合
    private List<Map<String, String>> searchWithStatsInfoFromPunishment(String indexType, String facetField, String facetPivotField,
            String conditionsStr, int cnt, boolean changFlag) {
        List<Map<String, String>> result = new ArrayList<Map<String, String>>();
        List<Map<String, String>> tempResult = new ArrayList<Map<String, String>>();
        QueryInfo<Map<String, String>> queryInfo = new QueryInfo<Map<String, String>>();
        Map<String, String> condition = Maps.newHashMap();

        // 设置查询条件
        condition.put(Constant.SEARCH_CONDIATION, conditionsStr);
        condition.put(Constant.SEARCH_FACET_PIVOT, "{!stats=piv1}" + facetField);
        condition.put(Constant.SEARCH_STATS_PIVOT, "{!tag=piv1 sum=true}" + facetPivotField);

        queryInfo.setCondition(condition);
        log.debug("*******search index data*******");
        StatsResult<PunishmentSearchDto> page = searchServer.searchWithStats(indexType, queryInfo, PunishmentSearchDto.class);
        List<PivotField> field = page.getStatisticsFieldMap().get(facetField);
        Map<String, String> dataMap = Maps.newHashMap();
        for (PivotField sf : field) {
            dataMap = new HashMap<String, String>();
            dataMap.put("name", String.valueOf(sf.getValue()));

            String sum = "0";
            BigDecimal ret = null;
            try {
//            	sum = String.valueOf(sf.getFieldStatsInfo().get(facetPivotField).getSum());
            	//保留小数点后4位 xing.j 2017/11/15
            	ret = new BigDecimal(String.valueOf(sf.getFieldStatsInfo().get(facetPivotField).getSum()));
            	sum=ret.setScale(4, BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString();
            	//sum=ret.toString();
            	
            } catch (Exception e) {
                log.error("cause by：{}", Throwables.getStackTraceAsString(e));
                sum = "0";
            }
//            if (sum.indexOf(".") != -1) {
//                sum = sum.substring(0, sum.indexOf("."));
//            }
            dataMap.put("value", sum);
            //if ("0".equals(sum)) {
            //TODO
            //xing.j 2018/1/22
            if(sum.indexOf(".") != -1){
	            if ("0".equals(sum.substring(0, sum.indexOf(".")))) {
	                continue;
	            }
        	}else{
        		if ("0".equals(sum)) {
	                continue;
	            }
        	}
            tempResult.add(dataMap);
        }

        Collections.sort(tempResult, new Comparator<Map<String, String>>() {
            @Override
            public int compare(Map<String, String> o1, Map<String, String> o2) {
            	int map1value;
            	int map2value;
            	if (o1.get("value").indexOf(".")!= -1){
            		map1value = Integer.parseInt(o1.get("value").substring(0, o1.get("value").indexOf(".")));
            	}else{
            		map1value = Integer.parseInt(o1.get("value"));
            	}
            	if (o2.get("value").indexOf(".")!= -1){ 
            		map2value = Integer.parseInt(o2.get("value").substring(0, o2.get("value").indexOf(".")));
            	}else{
            		map2value = Integer.parseInt(o2.get("value"));
            	}
                return map2value - map1value;
            }
        });

        Map<String, String> returnMap = Maps.newHashMap();
        String mapName = "";
        for (int i = 0; i < tempResult.size(); i++) {
            if (i == cnt) {
                break;
            }

            returnMap = tempResult.get(i);
            mapName = returnMap.get("name");
            returnMap.put("key", mapName);
            result.add(returnMap);
        }

        return result;
    }

    // 取得分组统计索引内的数据
    private List<Map<String, String>> searchWithFacetInfoFromViolation(String indexType, String facetField, String conditionsStr, boolean zeroFlag, int cnt, boolean changFlag) {
        List<Map<String, String>> result = new ArrayList<Map<String, String>>();
        QueryInfo<Map<String, String>> queryInfo = new QueryInfo<Map<String, String>>();
        Map<String, String> condition = Maps.newHashMap();
        // 设置查询条件
        condition.put(Constant.SEARCH_CONDIATION, conditionsStr);
        // 设置分组字段
        condition.put(Constant.SEARCH_FACET_FIELD, facetField);

        queryInfo.setCondition(condition);
        log.debug("*******search index data*******");
        FacetResult<ViolationSearchDto> page = searchServer.searchWithFacet(indexType, queryInfo, ViolationSearchDto.class);
        List<StatisticsField> field = page.getStatisticsFieldMap().get(facetField);
        int i = 0;
        Map<String, String> dataMap = new HashMap<String, String>();
        for (StatisticsField sf : field) {
            if (i == cnt) {
                break;
            }

            if (zeroFlag && sf.getCount() == 0) {
                continue;
            }

            dataMap = new HashMap<String, String>();
            dataMap.put("name", sf.getFieldId());
            dataMap.put("key", sf.getFieldId());
            dataMap.put("value", String.valueOf(sf.getCount()));

            result.add(dataMap);

            i++;
        }
        return result;
    }

    // 取得分组统计索引内的数据并按指定的字段计算加合
    private List<Map<String, String>> searchWithStatsInfoFromViolation(String indexType, String facetField, String facetPivotField,
            String conditionsStr, int cnt, boolean changFlag) {
        List<Map<String, String>> result = new ArrayList<Map<String, String>>();
        List<Map<String, String>> tempResult = new ArrayList<Map<String, String>>();
        QueryInfo<Map<String, String>> queryInfo = new QueryInfo<Map<String, String>>();
        Map<String, String> condition = Maps.newHashMap();

        // 设置查询条件
        condition.put(Constant.SEARCH_CONDIATION, conditionsStr);
        condition.put(Constant.SEARCH_FACET_PIVOT, "{!stats=piv1}" + facetField);
        condition.put(Constant.SEARCH_STATS_PIVOT, "{!tag=piv1 sum=true}" + facetPivotField);

        queryInfo.setCondition(condition);
        log.debug("*******search index data*******");
        StatsResult<ViolationSearchDto> page = searchServer.searchWithStats(indexType, queryInfo, ViolationSearchDto.class);
        List<PivotField> field = page.getStatisticsFieldMap().get(facetField);
        Map<String, String> dataMap = Maps.newHashMap();
        for (PivotField sf : field) {
            dataMap = new HashMap<String, String>();
            dataMap.put("name", String.valueOf(sf.getValue()));

            String sum = "0";
            try {
                sum = String.valueOf(sf.getFieldStatsInfo().get(facetPivotField).getSum());
            } catch (Exception e) {
                log.error("cause by：{}", Throwables.getStackTraceAsString(e));
                sum = "0";
            }
            if (!"punishment_punish_amount_d".equals(facetPivotField) && sum.indexOf(".") != -1) {
                sum = sum.substring(0, sum.indexOf("."));
            }
            dataMap.put("value", sum);
            if ("0".equals(sum)) {
                continue;
            }
            tempResult.add(dataMap);
        }

        Collections.sort(tempResult, new Comparator<Map<String, String>>() {
            @Override
            public int compare(Map<String, String> o1, Map<String, String> o2) {
                int map1value = Integer.parseInt(o1.get("value"));
                int map2value = Integer.parseInt(o2.get("value"));
                return map2value - map1value;
            }
        });

        Map<String, String> returnMap = Maps.newHashMap();
        String mapName = "";
        for (int i = 0; i < tempResult.size(); i++) {
            if (i == cnt) {
                break;
            }

            returnMap = tempResult.get(i);
            mapName = returnMap.get("name");
            returnMap.put("key", mapName);
            result.add(returnMap);
        }

        return result;
    }

    private List<Map<String, String>> getLineGraphInfo(List<Map<String, String>> dateList, String dataType) {
        List<Map<String, String>> result = Lists.newArrayList();
        List<Map<String, String>> tempList = Lists.newArrayList();
        Map<String, String> dataMap = Maps.newHashMap();
        Map<String, String> returnMap = Maps.newHashMap();
        Map<String, String> tmpDataMap = Maps.newHashMap();
        String conditionsStr = "";
        Date start = new Date();
        Date end = new Date();
        for (Map<String, String> param : dateList) {
            start = DateUtil.getDate(param.get("startDate"), DateUtil.YYYY_MM_DD);
            returnMap = Maps.newHashMap();
            if ("0".equals(dataType)) {
                end = start; 
                // 日期
                returnMap.put("date", DateUtil.getDateStr(start, DateUtil.YYYY_MM_DD));
            } else {
                end = DateUtil.getDate(param.get("endDate"), DateUtil.YYYY_MM_DD);
                returnMap.put("date", DateUtil.getDateStr(start, DateUtil.YYYY_MM_DD) + "至" + DateUtil.getDateStr(end, DateUtil.YYYY_MM_DD));
            }

            tempList = Lists.newArrayList();
            // 获取违规案例的数量
            conditionsStr = SolrSearchUtil.parseDateKeyWords("violation_update_time_dt", start, end);
            tempList.add(getViolationInfo(conditionsStr));
            // 获取处罚对象的数量\处罚金额（万元）
            conditionsStr = SolrSearchUtil.parseDateKeyWords("punishment_update_time_dt", start, end);
            tmpDataMap = getPunishmentAmountInfo(conditionsStr);
            dataMap = new HashMap<String, String>();
            dataMap.put("name", "处罚对象数量");
            dataMap.put("data", tmpDataMap.get("count1"));
            tempList.add(dataMap);
            dataMap = new HashMap<String, String>();
            dataMap.put("name", "处罚金额(万元)");
            dataMap.put("data", tmpDataMap.get("count2"));
            tempList.add(dataMap);

            returnMap.put("dataNum", JsonUtil.toJsonNoNull(tempList));
            result.add(returnMap);
        }

        return result;
    }

    private List<Map<String, String>> getBarCharInfo(List<Map<String, String>> dateList, String dataType) {
        List<Map<String, String>> result = Lists.newArrayList();
        List<Map<String, String>> tempList = Lists.newArrayList();
        //List<Map<String, String>> resultList = Lists.newArrayList();
        Map<String, String> returnMap = Maps.newHashMap();
        String conditionsStr = "";
        Date start = new Date();
        Date end = new Date();
        for (Map<String, String> param : dateList) {
            start = DateUtil.getDate(param.get("startDate"), DateUtil.YYYY_MM_DD);
            returnMap = Maps.newHashMap();
            if ("0".equals(dataType)) {
                end = start; 
                // 日期
                returnMap.put("date", DateUtil.getDateStr(start, DateUtil.YYYY_MM_DD));
            } else {
                end = DateUtil.getDate(param.get("endDate"), DateUtil.YYYY_MM_DD);
                returnMap.put("date", DateUtil.getDateStr(start, DateUtil.YYYY_MM_DD) + "至" + DateUtil.getDateStr(end, DateUtil.YYYY_MM_DD));
            }

            tempList = Lists.newArrayList();          
            // 获取违规案例的数量
            conditionsStr = SolrSearchUtil.parseDateKeyWords("violation_update_time_dt", start, end);
            //TODO
            String[] tempCodes = {"1","2"};
            conditionsStr = SolrSearchUtil.transformArrayToString(conditionsStr,tempCodes,"violation_adopt_condition_t", false, false, false);            
            tempList = searchWithFacetInfoFromViolation("violation", "violation_adopt_condition_t", conditionsStr, true, 100, false);
            if(tempList.size()==2){
            	if(tempList.get(0).get("name").equals("1")){
            		returnMap.put("dataNum1", tempList.get(0).get("value"));
            		returnMap.put("dataNum2", tempList.get(1).get("value"));
            	}else{
            		returnMap.put("dataNum1", tempList.get(1).get("value"));
            		returnMap.put("dataNum2", tempList.get(0).get("value"));
            	}
            }else if(tempList.size()==1){
            	if(tempList.get(0).get("name").equals("1")){
            		returnMap.put("dataNum1", tempList.get(0).get("value"));
            		returnMap.put("dataNum2", "0");
            	}else{
            		returnMap.put("dataNum1", "0");
            		returnMap.put("dataNum2", tempList.get(0).get("value"));
            	}
            }else{
//            	returnMap.put("dataNum1", JsonUtil.toJsonNoNull("0"));
//        		returnMap.put("dataNum2", JsonUtil.toJsonNoNull("0"));
            	//Bug8014 xing.j 2018/1/18
            	returnMap.put("dataNum1", "0");
        		returnMap.put("dataNum2", "0");
            }
            //returnMap.put("dataNum", JsonUtil.toJsonNoNull(tempList));
            result.add(returnMap);
        }

        return result;
    }

    private Map<String, String> getPunishmentAmountInfo(String conditionsStr) {
        QueryInfo<Map<String, String>> queryInfo = new QueryInfo<Map<String, String>>();
        Map<String, String> condition = Maps.newHashMap();
        Map<String, String> resultMap = Maps.newHashMap();

        // 设置查询条件
        condition.put(Constant.SEARCH_CONDIATION, conditionsStr);
        queryInfo.setCondition(condition);
        queryInfo.setPageSize(100000);
        FacetResult<PunishmentSearchDto> page = searchServer.searchWithFacet("punishment", queryInfo, PunishmentSearchDto.class);
        List<PunishmentSearchDto> resultList = Lists.newArrayList();
        BigDecimal amount = new BigDecimal(0);
        String amountStr = "0.0000";
        if (page != null && page.getPage() != null) {
            resultList = page.getPage().getData();
            if (resultList != null && resultList.size() > 0) {
                for (PunishmentSearchDto punishmentItem : resultList) {
                    amount = amount.add(new BigDecimal(punishmentItem.getPunishAmount()));
                }
            }
            //bug7985 xing.j 2018/1/18 start
            //amount=amount.setScale(4, BigDecimal.ROUND_DOWN);
            amountStr = amount.setScale(4, BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString();
            //amountStr=amount.toString();
            
            resultMap.put("count1", String.valueOf(page.getPage().getTotal()));
            //resultMap.put("count2", String.valueOf(amount.setScale(4, BigDecimal.ROUND_HALF_UP)));
            resultMap.put("count2", amountStr);
            //bug7985 xing.j 2018/1/18 end  
            
        } else {
            resultMap.put("count1", "0");
            resultMap.put("count2", "0.0000");
        }

        return resultMap;
    }

    private Map<String, String> getViolationInfo(String conditionsStr) {
        QueryInfo<Map<String, String>> queryInfo = new QueryInfo<Map<String, String>>();
        Map<String, String> condition = Maps.newHashMap();
        Map<String, String> returnMap = Maps.newHashMap();
        // 设置查询条件
        condition.put(Constant.SEARCH_CONDIATION, conditionsStr);
        queryInfo.setCondition(condition);
        FacetResult<PunishmentSearchDto> page = searchServer.searchWithFacet("violation", queryInfo,
                PunishmentSearchDto.class);
        returnMap.put("name", "违规案例数量");
        if (page != null && page.getPage() != null) {
            returnMap.put("data", String.valueOf(page.getPage().getTotal()));
        } else {
            returnMap.put("data", "0");
        }

        return returnMap;
    }

    // 通过违规类型或处罚类型获取类型名
    private String getViolateClassName(String violateId, String treeType) {
        Map<String, String> condition = Maps.newHashMap();
        condition.put("violateId", violateId);
        condition.put("treeType", treeType);
        return violationBizMapper.getTypeNameById(condition);
    }
    
    // 获取某一个父分类的所有子分类及名字
    private List<String> getViolateClass1ParentNo(String violatePId) {
        Map<String, String> condition = Maps.newHashMap();
        condition.put("violatePId", violatePId);
        condition.put("treeType", "1");
        
        return violationBizMapper.getViolateClass1ParentNo(condition);
    }

    // 获取某一个父分类的所有子分类及名字
    private List<String> getViolateClass2ParentNo(String violatePId) {
        Map<String, String> condition = Maps.newHashMap();
        condition.put("violatePId", violatePId);
        condition.put("treeType", "2");
        return violationBizMapper.getViolateClass2ParentNo(condition);
    }

    // 获取违规案例的数量
    private long getViolateCount(String conditionsStr) {
        QueryInfo<Map<String, String>> queryInfo = new QueryInfo<Map<String, String>>();
        Map<String, String> condition = Maps.newHashMap();
        // 设置查询条件
        condition.put(Constant.SEARCH_CONDIATION, conditionsStr);
        queryInfo.setCondition(condition);
        FacetResult<ViolationSearchDto> page = searchServer.searchWithFacet("violation", queryInfo, ViolationSearchDto.class);
        if (page != null && page.getPage() != null) {
            return page.getPage().getTotal();
        }
        return 0;
    }
    
    // 获处罚明细的数量\处罚金额的合计值
    private Map<String, Object> getPunishmentCountAndSum(String conditionsStr) {
        QueryInfo<Map<String, String>> queryInfo = new QueryInfo<Map<String, String>>();
        Map<String, String> condition = Maps.newHashMap();
        Map<String, Object> returnMap = Maps.newHashMap();
        BigDecimal amount = new BigDecimal(0);
        String amountStr="0.0000";
        // 设置查询条件
        condition.put(Constant.SEARCH_CONDIATION, conditionsStr);
        queryInfo.setCondition(condition);
        queryInfo.setPageSize(100000);
        queryInfo.setStartRow(0);
        FacetResult<PunishmentSearchDto> page = searchServer.searchWithFacet("punishment", queryInfo, PunishmentSearchDto.class);
        if (page != null && page.getPage() != null) {
            for (PunishmentSearchDto item : page.getPage().getData() ) {
                amount = amount.add(BigDecimal.valueOf(item.getPunishAmount()));
            }
            //Bug7755 2018/1/18 xing.j start
            //amount=amount.setScale(4, BigDecimal.ROUND_DOWN);
            //amountStr=amount.toString();
            amountStr=amount.setScale(4, BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString();
            //returnMap.put("amountSum", amount);
          //Bug7755 2018/1/18 xing.j end
            returnMap.put("amountSum", amountStr);
            returnMap.put("count", page.getPage().getTotal());
        } else {
            returnMap.put("amountSum", 0);
            returnMap.put("count", 0);
        }
        
        return returnMap;
    }
    
    // 获取违规类型父分类及各个子分类的值
    private List<Map<String, String>> getViolateClassTypeCount(List<Map<String, String>> allViolateClassResult, String treeType) {
        List<Map<String, String>> result = Lists.newArrayList();
        List<Map<String, String>> resultSub = Lists.newArrayList();
        Map<String, String> dataPMap = Maps.newHashMap();
        Map<String, String> dataSMap = Maps.newHashMap();
        String tmpClassId = "";
        String itemClassName = "";
        List<String> allViolateClassP = Lists.newArrayList();
        List<String> allViolateClassS = Lists.newArrayList();
        
        // 获取所有的违规案例父分类
        if ("1".equals(treeType)) {           
            allViolateClassP = getViolateClass1ParentNo(""); 
        } else {
            allViolateClassP = getViolateClass2ParentNo(""); 
        }
        // 取得所有的父分类  
        boolean noSubClass = true;
        if (allViolateClassResult != null && allViolateClassResult.size() > 0) {
            for (Map<String, String> itemPMap : allViolateClassResult) {
                dataPMap = Maps.newHashMap();
                tmpClassId = itemPMap.get("name");
                for (String pClass : allViolateClassP) {
                    if (tmpClassId.equals(pClass)) {
                        // 设定父分类相关的值
                        itemClassName = getViolateClassName(tmpClassId, treeType);
                        dataPMap.put("name", itemClassName);
                        dataPMap.put("value", itemPMap.get("value"));
                        dataPMap.put("key", tmpClassId);
                        // 取得所有的子节点
                        resultSub = Lists.newArrayList();
                        if ("1".equals(treeType)) {           
                            allViolateClassS = getViolateClass1ParentNo(tmpClassId);
                        } else {
                            allViolateClassS = getViolateClass2ParentNo(tmpClassId);
                        }
                        noSubClass = true;
                        if (allViolateClassS != null && allViolateClassS.size() > 0) {
                            for (Map<String, String> itemSMap : allViolateClassResult) {
                                dataSMap = Maps.newHashMap();
                                tmpClassId = itemSMap.get("name");
                                for (String sClass : allViolateClassS) {
                                    if (tmpClassId.equals(sClass)) {
                                        dataSMap.put("name", getViolateClassName(tmpClassId, treeType));
                                        dataSMap.put("value", itemSMap.get("value"));
                                        resultSub.add(dataSMap);
                                        noSubClass = false;
                                        break;
                                    }
                                }
                            }
                            
                            // 当该父分类没有子分类是，设定子分类的值（和父分类的值相等）
                            if (noSubClass) {
                                // 当该父分类没有子分类是，设定子分类的值（和父分类的值相等）
                                dataSMap = Maps.newHashMap();
                                dataSMap.put("name", itemClassName);
                                dataSMap.put("value", itemPMap.get("value"));
                                resultSub.add(dataSMap);
                            }
                        } else {
                            // 当该父分类没有子分类是，设定子分类的值（和父分类的值相等）
                            dataSMap = Maps.newHashMap();
                            dataSMap.put("name", itemClassName);
                            dataSMap.put("value", itemPMap.get("value"));
                            resultSub.add(dataSMap);
                        }
                        
                        dataPMap.put("tableData", JsonUtil.toJson(resultSub));
                        result.add(dataPMap);
                        break;
                    }
                }
            }
        }
        
        return result;
    }
    
    
    //根据处罚对象身份code转成名称
    private List<Map<String, String>> getPersonIdentityInfo(List<Map<String, String>> punishmentPersonIdentityResult) {
    	List<Map<String, String>> result = Lists.newArrayList();
    	Map<String, String> dataMap = Maps.newHashMap();
    	if(punishmentPersonIdentityResult!=null && punishmentPersonIdentityResult.size()>0){
    		for (Map<String, String> ppMap:punishmentPersonIdentityResult){
    			String codeName="";
    			dataMap = Maps.newHashMap();
    			codeName=violationBizMapper.getCodeNameById(ppMap);
    			dataMap.put("name", codeName);
    			dataMap.put("key", ppMap.get("key"));
    			dataMap.put("value", ppMap.get("value"));
    			result.add(dataMap);
    		}
    	}
    	return result;
    }
    
    //根据处罚机构code转成名称
    private List<Map<String, String>> getPunishClassNameInfo(List<Map<String, String>> punishOrganizationResult) {
    	List<Map<String, String>> result = Lists.newArrayList();
    	Map<String, String> dataMap = Maps.newHashMap();
    	if(punishOrganizationResult!=null && punishOrganizationResult.size()>0){
    		for (Map<String, String> poMap:punishOrganizationResult){
    			String organizationName="";
    			dataMap = Maps.newHashMap();
    			organizationName=violationBizMapper.getPunishOrganizationById(poMap);
    			dataMap.put("name", organizationName);
    			dataMap.put("key", poMap.get("key"));
    			dataMap.put("value", poMap.get("value"));
    			result.add(dataMap);
    		}
    	}
    	return result;
    }
    /**
     * 获取页面初始化需要的各种列表
     *
     * @param queryInfo
     * @return
     */
    public ViolationParamDto getPageInitData() {
        ViolationParamDto dto = new ViolationParamDto();
        dto.setPunishManIdtList(getPunishManIdtList());
        dto.setAverSituationList(getAverSituationList());
        return dto;
    }


    /**
     * 获取处罚对象身份
     *
     * @param queryInfo
     * @return
     */
    public List<OptionDto> getPunishManIdtList() {
        return getCodeList(PERSON_IDT_CODE_NO);
    }

    /**
     * 获取申辩情况列表
     *
     * @param queryInfo
     * @return
     */
    public List<OptionDto> getAverSituationList() {
        return getCodeList(ADOPT_CONDITION_CODE_NO);
    }
    /**
     * 获取所属板块
     *
     * @param queryInfo
     * @return
     */
    public List<OptionDto> getStockBoardList() {
        return getCodeList(STOCK_BOARD_CODE_NO);
    }
}

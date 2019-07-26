package com.stock.capital.enterprise.ipoCase.service;

import com.google.common.collect.Maps;
import com.stock.capital.enterprise.ipoCase.dao.IpoCaseListMapper;
import com.stock.capital.enterprise.ipoCase.dto.IpoCaseIndexDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoCaseListBo;
import com.stock.capital.enterprise.ipoCase.dto.RegTreeDto;
import com.stock.core.Constant;
import com.stock.core.dao.DynamicDataSourceHolder;
import com.stock.core.dto.FacetResult;
import com.stock.core.dto.QueryInfo;
import com.stock.core.dto.StatisticsField;
import com.stock.core.search.SearchServer;
import com.stock.core.search.SolrSearchUtil;
import com.stock.core.service.BaseService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IpoCaseListService extends BaseService {

    @Autowired
    private IpoCaseListMapper ipoCaseListMapper;

    @Autowired
    private SearchServer searchServer;

    /**
     * 检索页查询列表
     *
     * @param page 筛选条件
     * @return map
     */
    public Map<String, Object> getIpoCaseList(QueryInfo<IpoCaseListBo> page, Boolean signSymbol) {
        IpoCaseListBo bo = page.getCondition();
        Map<String, String> condition = Maps.newHashMap();
        StringBuilder conditionsStr = new StringBuilder("index_type_t: \"ipocase\"");
        //标题关键字
        if (StringUtils.isNotEmpty(bo.getTitle())) {
            String[] title = bo.getTitle().trim().split(" ");
            conditionsStr.append(" AND " + "ipo_title_s:(*").append(title[0]).append("*");
            for (int i = 1; i < title.length; i++) {
                if (StringUtils.isNotEmpty(title[i])) {
                    conditionsStr.append(" OR *").append(title[i]).append("*");
                }
            }
            conditionsStr.append(")");
        }
        //公司名称/代码
        if (StringUtils.isNotBlank(bo.getCodeOrName())) {
            conditionsStr.append(" AND " + "(ipo_company_code_s:*").append(bo.getCodeOrName()).append("*")
                .append(" OR ").append("ipo_company_name_s:*").append(bo.getCodeOrName()).append("*")
                .append(" OR ").append("ipo_company_full_name_s:*").append(bo.getCodeOrName()).append("*")
                .append(")");
        }
        //中介机构
        if (StringUtils.isNotBlank(bo.getIntermediaryCode())) {
            conditionsStr.append(" AND " + "ipo_intermediary_code_ss:\"")
                .append(bo.getIntermediaryCode()).append("\"");
        }
        //发行人行业（证监会）
        if (StringUtils.isNotBlank(bo.getIndustryCsrc())) {
            conditionsStr =
                assebleBoxCondition(conditionsStr, "ipo_industry_csrc_t", bo.getIndustryCsrc());
        }
        //发行人行业（战略新兴）
        if (StringUtils.isNotBlank(bo.getStrageticIndustries())) {
            conditionsStr =
                    assebleBoxCondition(conditionsStr, "ipo_stragetic_industries_t", bo.getStrageticIndustries());
        }
        //配售机制
        if (StringUtils.isNotBlank(bo.getPlacingMechanism())) {
            conditionsStr =
                    assebleBoxCondition(conditionsStr, "ipo_placing_mechanism_t", bo.getPlacingMechanism());
        }
        //发行人选择的上市条件
        if (StringUtils.isNotBlank(bo.getIssueCondition())) {
            conditionsStr =
                assebleBoxCondition(conditionsStr, "ipo_issue_condition_ss", bo.getIssueCondition());
        }
        //企业性质
        if (StringUtils.isNotBlank(bo.getCompanyNature())) {
            conditionsStr =
                assebleBoxCondition(conditionsStr, "ipo_company_nature_t", bo.getCompanyNature());
        }
        //申报次数
        if (StringUtils.isNotBlank(bo.getIpoNum())) {
            conditionsStr = assebleBoxCondition(conditionsStr, "ipo_declare_num_i", bo.getIpoNum());
        }
        //审核结果
        if (StringUtils.isNotBlank(bo.getIecResult())) {
            conditionsStr =
                assebleBoxCondition(conditionsStr, "ipo_label_result_t", bo.getIecResult());
        }
        //IPO进程
        if (StringUtils.isNotBlank(bo.getCaseStatus())) {
            conditionsStr = assebleBoxCondition(conditionsStr, "ipo_process_t", bo.getCaseStatus());
        }
        //预先披露时间
        if (bo.getYpProcessTime() != null && bo.getYpProcessTime().length > 1) {
            Date[] ypProcessTime = bo.getYpProcessTime();
            conditionsStr.append(" AND ").append(SolrSearchUtil
                                                     .parseDateKeyWords(
                                                         "ipo_advance_disclosure_time_dt",
                                                         ypProcessTime[0],
                                                         ypProcessTime[1]));
        }
        //发审会审核时间
        if (bo.getFsProcessTime() != null && bo.getFsProcessTime().length > 1) {
            Date[] fsProcessTime = bo.getFsProcessTime();
            conditionsStr.append(" AND ").append(SolrSearchUtil
                                                     .parseDateKeyWords(
                                                         "ipo_final_time_dt",
                                                         fsProcessTime[0],
                                                         fsProcessTime[1]));
        }
        //审核历时
        if (bo.getTimeDiff() != null && bo.getTimeDiff().length > 0) {
            String startData = "*";
            String endData = "*";
            Integer[] timeDiff = bo.getTimeDiff();
            if (timeDiff[0] != null) {
                startData = timeDiff[0].toString();
            }
            if (timeDiff.length > 1) {
                if (timeDiff[1] != null) {
                    endData = timeDiff[1].toString();
                }
            }
            conditionsStr.append(" AND ").append("ipo_audit_duration_i").append(":[")
                .append(startData)
                .append(" TO ").append(endData).append("]");

        }
        //发行后市盈率
        if (bo.getPeIssueA() != null && bo.getPeIssueA().length > 0) {
            conditionsStr =
                assebleCondition(conditionsStr, "ipo_pe_issuea_d", bo.getPeIssueA(), false);
        }
        //发行费用
        if (bo.getIssueFee() != null && bo.getIssueFee().length > 0) {
            conditionsStr =
                assebleCondition(conditionsStr, "ipo_issue_fee_d", bo.getIssueFee(), false);
        }
        //高级筛选
        //最近一个年度净利润累计
        if (bo.getProfitOne() != null && bo.getProfitOne().length > 0) {
            conditionsStr =
                assebleCondition(conditionsStr, "ipo_profit_one_d", bo.getProfitOne(), true);
        }
        //最近一个年度营业收入累计
        if (bo.getReveOne() != null && bo.getReveOne().length > 0) {
            conditionsStr =
                assebleCondition(conditionsStr, "ipo_operate_reve_one_d", bo.getReveOne(), true);
        }
        //最近一个年度经营活动现金流量净额累计
        if (bo.getCashFlowOne() != null && bo.getCashFlowOne().length > 0) {
            conditionsStr = assebleCondition(conditionsStr, "ipo_operate_cash_one_d",
                                             bo.getCashFlowOne(), true);
        }
        //最近二个年度净利润累计
        if (bo.getProfitTwo() != null && bo.getProfitTwo().length > 0) {
            conditionsStr =
                assebleCondition(conditionsStr, "ipo_profit_two_d", bo.getProfitTwo(), true);
        }
        //最近二个年度营业收入累计
        if (bo.getReveTwo() != null && bo.getReveTwo().length > 0) {
            conditionsStr =
                assebleCondition(conditionsStr, "ipo_operate_reve_two_d", bo.getReveTwo(), true);
        }
        //最近二个年度经营活动现金流量净额累计
        if (bo.getCashFlowTwo() != null && bo.getCashFlowTwo().length > 0) {
            conditionsStr = assebleCondition(conditionsStr, "ipo_operate_cash_two_d",
                                             bo.getCashFlowTwo(), true);
        }
        //最近三个年度净利润累计
        if (bo.getProfitThree() != null && bo.getProfitThree().length > 0) {
            conditionsStr =
                assebleCondition(conditionsStr, "ipo_profit_three_d", bo.getProfitThree(), true);
        }
        //最近三个年度营业收入累计
        if (bo.getReveThree() != null && bo.getReveThree().length > 0) {
            conditionsStr = assebleCondition(conditionsStr, "ipo_operate_reve_three_d",
                                             bo.getReveThree(), true);
        }
        //最近三个年度经营活动现金流量净额累计
        if (bo.getCashFlowThree() != null && bo.getCashFlowThree().length > 0) {
            conditionsStr = assebleCondition(conditionsStr, "ipo_operate_cash_three_d",
                                             bo.getCashFlowThree(), true);
        }
        //招股书最近一期末总资产
        if (bo.getSunAsset() != null && bo.getSunAsset().length > 0) {
            conditionsStr =
                assebleCondition(conditionsStr, "ipo_sum_asset_d", bo.getSunAsset(), false);
        }
        //招股书最近一期末净资产
        if (bo.getSumShareQuity() != null && bo.getSumShareQuity().length > 0) {
            conditionsStr =
                assebleCondition(
                    conditionsStr, "ipo_sum_share_quity_d", bo.getSumShareQuity(), false);
        }
        //招股书最近一期末无形资产占净资产的比例
        if (bo.getIntangibleAssetRatio() != null && bo.getIntangibleAssetRatio().length > 0) {
            conditionsStr =
                assebleCondition(
                    conditionsStr, "ipo_intangible_asset_ratio_d", bo.getIntangibleAssetRatio(),
                    false);
        }
        //发行前股本总额
        if (bo.getTotalShareIssueB() != null && bo.getTotalShareIssueB().length > 0) {
            conditionsStr =
                assebleCondition(
                    conditionsStr, "ipo_total_share_issueb_d", bo.getTotalShareIssueB(), false);
        }
        //发行后股本总额
        if (bo.getTotalShareIssueA() != null && bo.getTotalShareIssueA().length > 0) {
            conditionsStr =
                assebleCondition(
                    conditionsStr, "ipo_total_share_issuea_d", bo.getTotalShareIssueA(), false);
        }
        //最近一次估值
        if (bo.getValuationValue() != null && bo.getValuationValue().length > 0) {
            conditionsStr =
                    assebleCondition(
                            conditionsStr, "ipo_valuation_d", bo.getValuationValue(), false);
        }
        //左侧树 筛选
        StringBuilder conditionTree = new StringBuilder();
        conditionTree.append("ipo_plate_t").append(",ipo_market_type_ss")
            .append(",ipo_green_passage_t").append(",ipo_special_arrange_ss").append(",ipo_belongs_bureau_t");
        //拟上市板块
        if (StringUtils.isNotBlank(bo.getIpoPlate())) {
            conditionsStr =
                assebleBoxCondition(conditionsStr, "ipo_plate_t", bo.getIpoPlate());
        }
        //登陆其他资本市场
        if (StringUtils.isNotBlank(bo.getMarketType())) {
            conditionsStr =
                assebleBoxCondition(conditionsStr, "ipo_market_type_ss", bo.getMarketType());
        }
        //绿色通道
        if (StringUtils.isNotBlank(bo.getGreenPassage())) {
            conditionsStr =
                assebleBoxCondition(conditionsStr, "ipo_green_passage_t", bo.getGreenPassage());
        }
        //公司治理特殊安排
        if (StringUtils.isNotBlank(bo.getSpecialArrange())) {
            conditionsStr =
                assebleBoxCondition(conditionsStr, "ipo_special_arrange_ss", bo.getSpecialArrange());
        }
        //所属证监局
        if (StringUtils.isNotBlank(bo.getBelongsBureau())) {
            conditionsStr =
                assebleBoxCondition(conditionsStr, "ipo_belongs_bureau_t", bo.getBelongsBureau());
        }
        //默认按发审会审核时间 倒序排列
        String orderByOrder = page.getOrderByOrder();
        if (StringUtils.isEmpty(orderByOrder)) {
            if (signSymbol) {
                orderByOrder = "desc";
            } else {
                orderByOrder = "desc,desc";
            }

        } else {
            if ("ascending".equals(orderByOrder)) {
                orderByOrder = signSymbol ? "asc":"desc,asc";
            } else if ("descending".equals(orderByOrder)) {
                orderByOrder = signSymbol ? "desc":"desc,desc";
            }
        }
        String orderByName = page.getOrderByName();
        if (StringUtils.isEmpty(orderByName)) {
            if (signSymbol) {
                orderByName = "ipo_final_time_dt";
            } else {
                orderByName = "ipo_open_flag_t,ipo_final_time_dt";
            }

        }else {
            if (!signSymbol){
                orderByName = "ipo_open_flag_t," + orderByName;
            }
        }
        condition.put(Constant.SEARCH_CONDIATION, conditionsStr.toString());
        condition.put(Constant.SEARCH_FACET_FIELD, conditionTree.toString());
        QueryInfo<Map<String, String>> queryInfo = new QueryInfo<>();
        queryInfo.setCondition(condition);
        queryInfo.setOrderByName(orderByName);
        queryInfo.setOrderByOrder(orderByOrder);
        queryInfo.setStartRow(page.getStartRow());
        queryInfo.setPageSize(page.getPageSize());
        FacetResult<IpoCaseIndexDto> facetResult =
            searchServer.searchWithFacet("ipocase", queryInfo, IpoCaseIndexDto.class);
        Map<String, Object> resultMap = Maps.newHashMap();
        resultMap.put("total", facetResult.getPage().getTotal());
        resultMap.put("data", facetResult.getPage().getData());
        //获取各个树对应的数字
        List<StatisticsField> plateList = facetResult.getStatisticsFieldMap().get("ipo_plate_t");
        List<StatisticsField> marketList =
            facetResult.getStatisticsFieldMap().get("ipo_market_type_ss");
        List<StatisticsField> greenList =
            facetResult.getStatisticsFieldMap().get("ipo_green_passage_t");
        List<StatisticsField> arrangeList =
            facetResult.getStatisticsFieldMap().get("ipo_special_arrange_ss");
        List<StatisticsField> bureauList =
            facetResult.getStatisticsFieldMap().get("ipo_belongs_bureau_t");
        //查询左侧树
        //拟上市板块树
        List<RegTreeDto> plateTreeTag = ipoCaseListMapper.getTreeTagByCode("IPO_PLATE");
        long plateTreeNum = 0;
        if (plateTreeTag != null && !plateTreeTag.isEmpty()) {
            plateTreeNum = assebleTreeData(plateTreeTag, plateList, false);
        }
        //登陆其他资本市场树
        List<RegTreeDto> marketTreeTag = ipoCaseListMapper.getTreeTagByCode("IPO_CAPITAL_MARKET");
        long marketTreeNum = 0;
        if (marketTreeTag != null && !marketTreeTag.isEmpty()) {
            marketTreeNum = assebleTreeData(marketTreeTag, marketList, false);
        }
        //绿色通道树
        List<RegTreeDto> greenTreeTag = ipoCaseListMapper.getTreeTagByCode("IPO_GREEN_PASSAGE");
        long greenTreeNum = 0;
        if (greenTreeTag != null && !greenTreeTag.isEmpty()) {
            greenTreeNum = assebleTreeData(greenTreeTag, greenList, false);
        }
        //公司治理特殊安排
        List<RegTreeDto> specialArrangeTag = ipoCaseListMapper.getTreeTagByCode("IPO_SPECIAL_ARRANGE");
        long arrangeTreeNum = 0;
        if (specialArrangeTag != null && !specialArrangeTag.isEmpty()) {
            arrangeTreeNum = assebleTreeData(specialArrangeTag, arrangeList, false);
        }
        //所属证监局树
        List<RegTreeDto> sfcTreeTag = ipoCaseListMapper.getTreeTagByCode("SFC");
        long sfcTreeNum = 0;
        if (sfcTreeTag != null && !sfcTreeTag.isEmpty()) {
            sfcTreeNum = assebleTreeData(sfcTreeTag, bureauList, true);
        }
        IpoCaseListBo law = ipoCaseListMapper.querylawId();
        if (law != null){
            if (StringUtils.isNoneBlank(law.getIssueLawId())){
                resultMap.put("issueLawId",law.getIssueLawId());
            }else {
                resultMap.put("issueLawId","746412002835704646");
            }
        }else {
            resultMap.put("issueLawId","746412002835704646");
        }
        resultMap.put("plateTreeTag", plateTreeTag);
        resultMap.put("plateTreeNum", plateTreeNum);
        resultMap.put("marketTreeTag", marketTreeTag);
        resultMap.put("marketTreeNum", marketTreeNum);
        resultMap.put("greenTreeTag", greenTreeTag);
        resultMap.put("greenTreeNum", greenTreeNum);
        resultMap.put("specialArrangeTag", specialArrangeTag);
        resultMap.put("arrangeTreeNum", arrangeTreeNum);
        resultMap.put("sfcTreeTag", sfcTreeTag);
        resultMap.put("sfcTreeNum", sfcTreeNum);
        return resultMap;
    }


    /**
     * 获取下拉框
     *
     * @return map
     */
    public Map<String, Object> getIpoSelectData() {
        Map<String, Object> resultMap = Maps.newHashMap();
        //发行人行业（证监会）
        List<RegTreeDto> industryCrscList = ipoCaseListMapper.getLabelByCode("INDUSTRY_CSRC_2012");
        resultMap.put("industryCrscList", sortSelectList(industryCrscList));
        //企业性质
        List<RegTreeDto> companyNatureList = ipoCaseListMapper.getLabelByCode("REP_COMPANY_NATURE");
        resultMap.put("companyNatureList", sortSelectList(companyNatureList));
        //申报次数
        List<RegTreeDto> ipoNumList = ipoCaseListMapper.getLabelByCode("IPO_NUM");
        resultMap.put("ipoNumList", sortSelectList(ipoNumList));
        //审核结果
        List<RegTreeDto> verifyResultList = ipoCaseListMapper.getLabelByCode("IPO_VERIFY_RESULT");
        resultMap.put("verifyResultList", sortSelectList(verifyResultList));
        //IPO进程
        List<RegTreeDto> processList = ipoCaseListMapper.getLabelByCode("IPO_STATUS");
        resultMap.put("processList", sortSelectList(processList));
        //发行人选择的上市条件
        List<RegTreeDto> issueConditionList = ipoCaseListMapper.getLabelByCode("IPO_ISSUE_CONDITION_SIMPLE");
        resultMap.put("issueConditionList", sortSelectList(issueConditionList));
        //战略新兴行业
        List<RegTreeDto> strageticIndustriesList = ipoCaseListMapper.getLabelByCode("STRAGETIC_EMERGING_INDUSTRIES");
        resultMap.put("strageticIndustriesList", sortSelectList(strageticIndustriesList));
        //配售机制
        List<RegTreeDto> placingMechanism = ipoCaseListMapper.getLabelByCode("IPO_PLACING_MECHANISM");
        resultMap.put("placingMechanism", sortSelectList(placingMechanism));
        return resultMap;
    }


    public List<RegTreeDto> sortSelectList(List<RegTreeDto> selectList) {
        List<RegTreeDto> resultList = new ArrayList<>();
        if (selectList.size() > 0) {
            //获取pId为0的，为父节点
            for (int i = 0; i < selectList.size(); i++) {
                if ("0".equals(selectList.get(i).getpId())) {
                    RegTreeDto tempDto = selectList.get(i);
                    resultList.add(getChildList(tempDto, selectList));
                }
            }
        }
        return resultList;
    }

    public RegTreeDto getChildList(RegTreeDto repTreeTagDto, List<RegTreeDto> selectList) {
        List<RegTreeDto> childList = new ArrayList<>();
        for (int i = 0; i < selectList.size(); i++) {
            if (repTreeTagDto.getId().equals(selectList.get(i).getpId())) {
                RegTreeDto tempDto = selectList.get(i);
                getChildList(tempDto, selectList);
                childList.add(selectList.get(i));
            }
        }
        repTreeTagDto.setChildren(childList);
        return repTreeTagDto;
    }

    /**
     * 联想查询中介机构
     *
     * @param intermediaryName 中介机构名称或别名
     * @return list
     */
    public List<Map<String, Object>> queryIntermediary(String intermediaryName) {
        if (StringUtils.isBlank(intermediaryName)) {
            return new ArrayList<>();
        }
        DynamicDataSourceHolder.setDataSource("touguan");
        List<Map<String, Object>> map = ipoCaseListMapper.queryIntermediary(intermediaryName);
        DynamicDataSourceHolder.cleanDataSource();
        return map;
    }

    /**
     * 数据范围控件拼接查询条件
     *
     * @param conditionStr conditionStr
     * @param fieldName    fieldName
     * @param data         data
     * @param symbol       symbol
     * @return 查询条件
     */
    private StringBuilder assebleCondition(
        StringBuilder conditionStr, String fieldName, Double[] data, Boolean symbol) {
        String startData = "*";
        String endData = "*";
        if (data[0] != null) {
            Double startNum = data[0];
            if (symbol) {
                //亿元转万元 乘以10000
                startNum =
                    BigDecimal.valueOf(startNum).multiply(new BigDecimal("10000")).doubleValue();

            }
            startData = startNum.toString();
        }
        if (data.length > 1) {
            if (data[1] != null) {
                Double endNum = data[1];
                if (symbol) {
                    //亿元转万元 乘以10000
                    endNum =
                        BigDecimal.valueOf(endNum).multiply(new BigDecimal("10000")).doubleValue();
                }
                endData = endNum.toString();
            }
        }
        conditionStr.append(" AND ").append(fieldName).append(":[").append(startData)
            .append(" TO ").append(endData).append("]");
        return conditionStr;
    }

    /**
     * 组装下拉框等查询条件
     *
     * @param conditionsStr conditionsStr
     * @param fieldName     fieldName
     * @param data          data
     * @return 查询条件
     */
    private StringBuilder assebleBoxCondition(
        StringBuilder conditionsStr, String fieldName, String data) {
        String[] conditionArray = data.trim().split(",");
        conditionsStr.append(" AND ").append(fieldName).append(":(\"").append(conditionArray[0])
            .append("\"");
        for (int i = 1; i < conditionArray.length; i++) {
            if (StringUtils.isNotEmpty(conditionArray[i])) {
                conditionsStr.append(" OR \"").append(conditionArray[i]).append("\"");
            }
        }
        conditionsStr.append(")");
        return conditionsStr;
    }

    /**
     * 组装左侧树数据
     *
     * @param treeData 左侧树
     * @param numData  分组后结果
     * @param symbol   标识证监局
     * @return 左侧树总数
     */
    private long assebleTreeData(
        List<RegTreeDto> treeData, List<StatisticsField> numData, Boolean symbol) {
        Map<String, Long> countMap = Maps.newHashMap();
        if (numData != null && !numData.isEmpty()) {
            for (StatisticsField field : numData) {
                countMap.put(field.getFieldId(), field.getCount());
            }
        }
        long num = 0;
        for (RegTreeDto treeDto : treeData) {
            //所属证监局
            if (symbol) {
                String s = treeDto.getLabelName();
                s = s.substring(0, s.length() - 3);
                treeDto.setLabelValue(s);
            }
            treeDto.setName(treeDto.getLabelName() + "(" + 0 + ")");
            if (countMap.get(treeDto.getLabelValue()) != null) {
                long count = countMap.get(treeDto.getLabelValue());
                treeDto.setName(
                    treeDto.getLabelName() + "(" + count + ")");
                num += count;
            }
        }
        return num;
    }
}

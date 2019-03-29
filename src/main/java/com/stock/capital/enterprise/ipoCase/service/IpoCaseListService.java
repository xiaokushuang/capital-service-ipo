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

    public Map<String, Object> getIpoCaseList(QueryInfo<IpoCaseListBo> page) {
        IpoCaseListBo bo = page.getCondition();
        Map<String, String> condition = Maps.newHashMap();
        StringBuilder conditionsStr = new StringBuilder("index_type_t: \"ipocase\"");
        //标题关键字
        if (StringUtils.isNotEmpty(bo.getTitle())) {
            String[] title = bo.getTitle().trim().split(" ");
            conditionsStr.append(" AND " + "ipo_title_t:(\"").append(title[0]).append("\"~5");
            for (int i = 1; i < title.length; i++) {
                if (StringUtils.isNotEmpty(title[i])) {
                    conditionsStr.append(" AND \"").append(title[i]).append("\"~5");
                }
            }
            conditionsStr.append(")");
        }
        //公司名称/代码
        if (StringUtils.isNotBlank(bo.getCodeOrName())) {
            conditionsStr.append(" AND " + "(ipo_company_code_t:\"").append(bo.getCodeOrName())
                .append("\" OR ").append("ipo_company_name_t:\"").append(bo.getCodeOrName())
                .append("\")");
        }
        //中介机构
        if (StringUtils.isNotBlank(bo.getIntermediaryCode())) {
            conditionsStr.append(" AND " + "ipo_intermediary_code_txt:\"")
                .append(bo.getIntermediaryCode()).append("\"");
        }
        //发行人行业（证监会）
        if (StringUtils.isNotBlank(bo.getIndustryCsrc())) {
            conditionsStr =
                assebleBoxCondition(conditionsStr, "ipo_industry_csrc_t", bo.getIndustryCsrc());
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
                assebleBoxCondition(conditionsStr, "ipo_iec_result_t", bo.getIecResult());
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
                                                         "ipo_review_meeting_time_dt",
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
        //左侧树 筛选
        StringBuilder conditionTree = new StringBuilder();
        conditionTree.append("ipo_plate_t").append(",ipo_market_type_txt")
            .append(",ipo_green_passage_t").append(",ipo_belongs_bureau_t");
        //拟上市板块
        if (StringUtils.isNotBlank(bo.getIpoPlate())) {
            conditionsStr =
                assebleBoxCondition(conditionsStr, "ipo_plate_t", bo.getIpoPlate());
        }
        //登陆其他资本市场
        if (StringUtils.isNotBlank(bo.getMarketType())) {
            conditionsStr =
                assebleBoxCondition(conditionsStr, "ipo_market_type_txt", bo.getMarketType());
        }
        //绿色通道
        if (StringUtils.isNotBlank(bo.getGreenPassage())) {
            conditionsStr =
                assebleBoxCondition(conditionsStr, "ipo_green_passage_t", bo.getGreenPassage());
        }
        //所属证监局
        if (StringUtils.isNotBlank(bo.getBelongsBureau())) {
            conditionsStr =
                assebleBoxCondition(conditionsStr, "ipo_belongs_bureau_t", bo.getBelongsBureau());
        }
        //默认按发审会审核时间 倒序排列
        String orderByOrder = page.getOrderByOrder();
        if (StringUtils.isEmpty(orderByOrder)) {
            orderByOrder = "desc";
        } else {
            if ("ascending".equals(orderByOrder)) {
                orderByOrder = "asc";
            } else if ("descending".equals(orderByOrder)) {
                orderByOrder = "desc";
            }
        }
        String orderByName = page.getOrderByName();
        if (StringUtils.isEmpty(orderByName)) {
            orderByName = "ipo_review_meeting_time_dt";
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
        //查询左侧树
        //拟上市板块树
        List<RegTreeDto> plateTreeTag = ipoCaseListMapper.getTreeTagByCode("IPO_PLATE");
        //登陆其他资本市场树
        List<RegTreeDto> marketTreeTag = ipoCaseListMapper.getTreeTagByCode("IPO_CAPITAL_MARKET");
        //绿色通道树
        List<RegTreeDto> greenTreeTag = ipoCaseListMapper.getTreeTagByCode("IPO_GREEN_PASSAGE");
        //所属证监局树
        List<RegTreeDto> sfcTreeTag = ipoCaseListMapper.getTreeTagByCode("SFC");
        resultMap.put("plateTreeTag", plateTreeTag);
        resultMap.put("marketTreeTag", marketTreeTag);
        resultMap.put("greenTreeTag", greenTreeTag);
        resultMap.put("sfcTreeTag", sfcTreeTag);
        //查询各个树对应的数字 改证监局labelValue值为labelName去掉后三个字  s=s.substring(0,s.length()-3)
        return resultMap;
    }


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

}

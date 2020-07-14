package com.stock.capital.enterprise.ipoCase.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.stock.capital.enterprise.common.constant.Global;
import com.stock.capital.enterprise.ipoCase.dao.IpoCaseListMapper;
import com.stock.capital.enterprise.ipoCase.dto.*;
import com.stock.capital.enterprise.utils.SetStartAndEndUtils;
import com.stock.core.dao.DynamicDataSourceHolder;
import com.stock.core.dto.*;
import com.stock.core.rest.RestClient;
import com.stock.core.search.SearchClient;

import com.stock.core.service.BaseService;

import java.math.BigDecimal;
import java.util.*;


import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Service
public class IpoCaseListService extends BaseService {

    private static Logger logger = LoggerFactory.getLogger(IpoCaseListService.class);

    @Autowired
    private IpoCaseListMapper ipoCaseListMapper;

    @Value("#{app['api.baseUrl']}")
    private String apiBaseUrl;

    @Autowired
    private RestClient restClient;

    @Autowired
    private SearchClient searchClient;



    /**
     * 检索页查询列表
     *
     * @param page 筛选条件
     * @return map
     */
    public Map<String, Object> getIpoCaseList(QueryInfo<IpoCaseListBo> page, Boolean signSymbol) {
        IpoCaseListBo bo = page.getCondition();
        Map<String, Object> resultMap = Maps.newHashMap();
//        获取排序
        String orderByOrder = page.getOrderByOrder();
        if (StringUtils.isEmpty(orderByOrder)) {
            if (signSymbol) {
                orderByOrder = "desc";
            } else {
                orderByOrder = "desc,desc";
            }

        } else {
            if ("ascending".equals(orderByOrder)) {
                orderByOrder = signSymbol ? "asc" : "desc,asc";
            } else if ("descending".equals(orderByOrder)) {
                orderByOrder = signSymbol ? "desc" : "desc,desc";
            }
        }
        String orderByName = page.getOrderByName();
        if (StringUtils.isEmpty(orderByName)) {
            if (signSymbol) {
                orderByName = "ipo_final_time_dt";
            } else {
                orderByName = "ipo_open_flag_t,ipo_final_time_dt";
            }

        } else {
            if (!signSymbol) {
                orderByName = "ipo_open_flag_t," + orderByName;
            }
        }
        String caseType = bo.getCaseType();
        FacetResult<IpoCaseIndexDto> facetResult = null;
//        从ES查询
        if (Global.SEARCH_SERVER_IPO_CASE_FLAG.equals("0")) {
            if (Global.CASE_TYPE_ALL.equals(caseType)) {
                bo.setCaseType(null);
            } else if (Global.CASE__TYPE_IPO.equals(caseType)) {
                bo.setCaseType("ipocase");
            } else if (Global.CASE_TYPE_FD.equals(caseType)) {
                bo.setCaseType("ipofdcase");
//                因为ipo和辅导库用了一个时间字段，当选辅导库时，设置ipo次数使查询结果置空
                if(null != bo.getFsProcessTime() && bo.getFsProcessTime().length > 1){
                    bo.setIpoNum("1");
                }
            }
            resetCondition(bo);
            QueryInfo<IpoCaseListBo> queryInfo = new QueryInfo<IpoCaseListBo>();
            queryInfo.setCondition(bo);
            queryInfo.setOrderByName(orderByName);
            queryInfo.setOrderByOrder(orderByOrder);
            queryInfo.setStartRow(page.getStartRow());
            queryInfo.setPageSize(page.getPageSize());
            queryInfo.setQueryId("com.stock.capital.enterprise.ipoCase.dao.IpoCaseEs.ipoCaseSearchByEs");
            facetResult = searchClient.searchWithFacet("ipo_case", queryInfo, IpoCaseIndexDto.class);
        } else {
//            删除掉solor部分代码
        }

        resultMap.put("total", facetResult.getPage().getTotal());
        resultMap.put("data", facetResult.getPage().getData());

        //获取各个树对应的数字
        List<StatisticsField> plateList = facetResult.getStatisticsFieldMap().get("ipo_plate_t");
        List<StatisticsField> marketList =
                facetResult.getStatisticsFieldMap().get("ipo_market_type_ss");
        List<StatisticsField> marketNumList =
                facetResult.getStatisticsFieldMap().get("ipo_market_num_d");
        List<StatisticsField> greenList =
                facetResult.getStatisticsFieldMap().get("ipo_green_passage_t");
        List<StatisticsField> arrangeList =
                facetResult.getStatisticsFieldMap().get("ipo_special_arrange_ss");
        List<StatisticsField> bureauList =
                facetResult.getStatisticsFieldMap().get("ipo_belongs_bureau_t");
//        获取caseType返回
//        String ipoType = facetResult.getStatisticsFieldMap().get("ipo_type_t");
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
//            如果可以查出树的值，则给计算的数据从新赋值
            if (marketNumList != null && marketNumList.size() > 0){
                marketTreeNum = marketNumList.get(0).getCount();
            }
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

        IpoCaseListBo law = ipoCaseListMapper.querylawId();//科创版上市条件
        if (law != null) {
            if (StringUtils.isNoneBlank(law.getIssueLawId())) {
                resultMap.put("issueLawId", law.getIssueLawId());
            } else {
                resultMap.put("issueLawId", "746412002835704646");
            }
        } else {
            resultMap.put("issueLawId", "746412002835704646");
        }
        IpoCaseListBo lawjxc = ipoCaseListMapper.querylawIdjxc();//精选层上市条件
        if (lawjxc != null) {
            if (StringUtils.isNoneBlank(lawjxc.getIssueLawId())) {
                resultMap.put("issueLawJxcId", lawjxc.getIssueLawId());
            } else {
                resultMap.put("issueLawJxcId", "458629827245657197");
            }
        } else {
            resultMap.put("issueLawJxcId", "458629827245657197");
        }

//        746126805383933368  首次公开发行股票并上市管理办法    主板、中小板上市条件
        IpoCaseListBo lawzbzxb = ipoCaseListMapper.querylawIdzbzxb();//主板、中小板上市条件
        if (lawzbzxb != null) {
            if (StringUtils.isNoneBlank(lawzbzxb.getIssueLawId())) {
                resultMap.put("issueLawZbzxbId", lawzbzxb.getIssueLawId());
            } else {
                resultMap.put("issueLawZbzxbId", "746126805383933368");
            }
        } else {
            resultMap.put("issueLawZbzxbId", "746126805383933368");
        }
//        746412002806435811  深圳证券交易所创业板股票上市规则  创业板上市条件
        IpoCaseListBo lawcyb = ipoCaseListMapper.querylawIdcyb();//创业板上市条件
        if (lawcyb != null) {
            if (StringUtils.isNoneBlank(lawcyb.getIssueLawId())) {
                resultMap.put("issueLawCybId", lawcyb.getIssueLawId());
            } else {
                resultMap.put("issueLawCybId", "746412002806435811");
            }
        } else {
            resultMap.put("issueLawCybId", "746412002806435811");
        }

//        746126805383932799  首次公开发行股票并在创业板上市管理办法（2018年修订） 创业板上市条件
      IpoCaseListBo lawcybt = ipoCaseListMapper.querylawIdcybt();//创业板上市条件
      if (lawcybt != null) {
        if (StringUtils.isNoneBlank(lawcybt.getIssueLawId())) {
          resultMap.put("issueLawCybtId", lawcybt.getIssueLawId());
        } else {
          resultMap.put("issueLawCybtId", "746126805383932799");
        }
      } else {
        resultMap.put("issueLawCybtId", "746126805383932799");
      }

//        745777672757626842  国务院办公厅转发证监会关于开展创新企业境内发行股票或存托凭证试点若干意见的通知 红筹企业要求
      IpoCaseListBo lawhc = ipoCaseListMapper.querylawIdhc();//红筹企业要求
      if (lawhc != null) {
        if (StringUtils.isNoneBlank(lawhc.getIssueLawId())) {
          resultMap.put("issueLawHcId", lawhc.getIssueLawId());
        } else {
          resultMap.put("issueLawHcId", "745777672757626842");
        }
      } else {
        resultMap.put("issueLawHcId", "745777672757626842");
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

    private void resetCondition(IpoCaseListBo bo) {

//        去除空格
        if (StringUtils.isNotEmpty(bo.getCodeOrName())){
            bo.setCodeOrName(bo.getCodeOrName().trim());
        }
//        左侧树筛选
//        拟上市板块
        if (StringUtils.isNotEmpty(bo.getIpoPlate())){
            bo.setIpoPlateList(clearEmpty(Arrays.asList(bo.getIpoPlate().trim().split(","))));
        }
//        登录其他资本市场
        if (StringUtils.isNotEmpty(bo.getMarketType())){
            bo.setMarketTypeList(clearEmpty(Arrays.asList(bo.getMarketType().trim().split(","))));
        }
//        绿色通道
        if (StringUtils.isNotEmpty(bo.getGreenPassage())){
            bo.setGreenPassageList(clearEmpty(Arrays.asList(bo.getGreenPassage().trim().split(","))));
        }
//        公司治理特殊安排
        if (StringUtils.isNotEmpty(bo.getSpecialArrange())){
            bo.setSpecialArrangeList(clearEmpty(Arrays.asList(bo.getSpecialArrange().trim().split(","))));
        }
//        所属证监局
        if (StringUtils.isNotEmpty(bo.getBelongsBureau())){
            bo.setBelongsBureauList(clearEmpty(Arrays.asList(bo.getBelongsBureau().trim().split(","))));
        }
//        基本筛选
//        注册地筛选
        if(StringUtils.isNotEmpty(bo.getRegisterArea())){
            bo.setRegisterAreaList(clearEmpty(Arrays.asList(bo.getRegisterArea().trim().split(","))));
        }
        //            标题
        if (StringUtils.isNotEmpty(bo.getTitle())) {
            bo.setTitleList(Arrays.asList(bo.getTitle().trim().split("\\s+")));
        }
//            企业性质
        if (StringUtils.isNotEmpty(bo.getCompanyNature())) {
            bo.setCompanyNatureList(clearEmpty(Arrays.asList(bo.getCompanyNature().trim().split(","))));
        }
//            证监会行业
        if (StringUtils.isNotEmpty(bo.getIndustryCsrc())) {
            bo.setIndustryCsrcList(clearEmpty(Arrays.asList(bo.getIndustryCsrc().trim().split(","))));
        }
//            ipo次数
        if (StringUtils.isNotEmpty(bo.getIpoNum())) {
            bo.setIpoNumList(clearEmpty(Arrays.asList(bo.getIpoNum().trim().split(","))));
        }
//            ipo进程
        if (StringUtils.isNotEmpty(bo.getCaseStatus())) {
            bo.setCaseStatusList(clearEmpty(Arrays.asList(bo.getCaseStatus().trim().split(","))));
        }
//            审核结果
        if (StringUtils.isNotEmpty(bo.getApprResult())) {
            bo.setApprResultList(clearEmpty(Arrays.asList(bo.getApprResult().trim().split(","))));
        }
        //是否拆分上市
        if (StringUtils.isNotEmpty(bo.getIsHidden())) {
            bo.setIsHiddenList(clearEmpty(Arrays.asList(bo.getIsHidden().trim().split(","))));
        }
//            上市条件
        if (StringUtils.isNotEmpty(bo.getIssueCondition())) {
            bo.setIssueConditionList(clearEmpty(Arrays.asList(bo.getIssueCondition().trim().split(","))));
        }
//            配售机制
        if (StringUtils.isNotEmpty(bo.getPlacingMechanism())) {
            bo.setPlacingMechanismList(clearEmpty(Arrays.asList(bo.getPlacingMechanism().trim().split(","))));
        }
//            发行后市盈率
        if (bo.getPeIssueA() != null && bo.getPeIssueA().length > 0) {
            bo.setPeIssueALow(bo.getPeIssueA()[0]);
            if (bo.getPeIssueA().length > 1) {
                bo.setPeIssueAHigh(bo.getPeIssueA()[1]);
            }
        }
//            发行费用
        if (bo.getIssueFee() != null && bo.getIssueFee().length > 0) {
            bo.setIssueFeeLow(bo.getIssueFee()[0]);
            if (bo.getIssueFee().length > 1) {
                bo.setIssueFeeHigh(bo.getIssueFee()[1]);
            }
        }
//            辅导历时
        if (bo.getTimeDiff() != null && bo.getTimeDiff().length > 0) {
            bo.setTimeDiffLow(bo.getTimeDiff()[0]);
            if (bo.getTimeDiff().length > 1) {
                bo.setTimeDiffHigh(bo.getTimeDiff()[1]);
            }
        }
//            辅导备案时间
        if (bo.getFdProcessTime() != null && bo.getFdProcessTime().length > 0) {
            SetStartAndEndUtils.setStartAndEndByReflex(bo, "fdProcessTime", "date", true);
        }
//            上市时间
        if (bo.getPubProcessTime() != null && bo.getPubProcessTime().length > 0) {
            SetStartAndEndUtils.setStartAndEndByReflex(bo, "pubProcessTime", "date", true);
        }
//            受理时间
        if (bo.getYpProcessTime() != null && bo.getYpProcessTime().length > 0) {
            SetStartAndEndUtils.setStartAndEndByReflex(bo, "ypProcessTime", "date", true);
        }
//            审核时间
        if (bo.getFsProcessTime() != null && bo.getFsProcessTime().length > 0) {
            SetStartAndEndUtils.setStartAndEndByReflex(bo, "fsProcessTime", "date", true);
        }
//        高级筛选  亿元转万元
        if (bo.getProfitOne() != null && bo.getProfitOne().length > 0) {
            SetStartAndEndUtils.setStartAndEndByReflex(bo, "profitOne", "double", true);
        }
        //最近一个年度营业收入累计
        if (bo.getReveOne() != null && bo.getReveOne().length > 0) {
            SetStartAndEndUtils.setStartAndEndByReflex(bo, "reveOne", "double", true);
        }
        //最近一个年度经营活动现金流量净额累计
        if (bo.getCashFlowOne() != null && bo.getCashFlowOne().length > 0) {
            SetStartAndEndUtils.setStartAndEndByReflex(bo, "cashFlowOne", "double", true);
        }
        //最近二个年度净利润累计
        if (bo.getProfitTwo() != null && bo.getProfitTwo().length > 0) {
            SetStartAndEndUtils.setStartAndEndByReflex(bo, "profitTwo", "double", true);
        }
        //最近二个年度营业收入累计
        if (bo.getReveTwo() != null && bo.getReveTwo().length > 0) {
            SetStartAndEndUtils.setStartAndEndByReflex(bo, "reveTwo", "double", true);

        }
        //最近二个年度经营活动现金流量净额累计
        if (bo.getCashFlowTwo() != null && bo.getCashFlowTwo().length > 0) {
            SetStartAndEndUtils.setStartAndEndByReflex(bo, "cashFlowTwo", "double", true);

        }
        //最近三个年度净利润累计
        if (bo.getProfitThree() != null && bo.getProfitThree().length > 0) {
            SetStartAndEndUtils.setStartAndEndByReflex(bo, "profitThree", "double", true);
        }
        //最近三个年度营业收入累计
        if (bo.getReveThree() != null && bo.getReveThree().length > 0) {
            SetStartAndEndUtils.setStartAndEndByReflex(bo, "reveThree", "double", true);
        }
        //最近三个年度经营活动现金流量净额累计
        if (bo.getCashFlowThree() != null && bo.getCashFlowThree().length > 0) {
            SetStartAndEndUtils.setStartAndEndByReflex(bo, "cashFlowThree", "double", true);
        }
        //招股书最近一期末总资产
        if (bo.getSunAsset() != null && bo.getSunAsset().length > 0) {
            SetStartAndEndUtils.setStartAndEndByReflex(bo, "sunAsset", "double", false);
        }
        //招股书最近一期末净资产
        if (bo.getSumShareQuity() != null && bo.getSumShareQuity().length > 0) {
            SetStartAndEndUtils.setStartAndEndByReflex(bo, "sumShareQuity", "double", false);
        }
        //招股书最近一期末无形资产占净资产的比例
        if (bo.getIntangibleAssetRatio() != null && bo.getIntangibleAssetRatio().length > 0) {
            SetStartAndEndUtils.setStartAndEndByReflex(bo, "intangibleAssetRatio", "double", false);
        }
        //发行前股本总额
        if (bo.getTotalShareIssueB() != null && bo.getTotalShareIssueB().length > 0) {
            SetStartAndEndUtils.setStartAndEndByReflex(bo, "totalShareIssueB", "double", false);
        }
        //发行后股本总额
        if (bo.getTotalShareIssueA() != null && bo.getTotalShareIssueA().length > 0) {
            SetStartAndEndUtils.setStartAndEndByReflex(bo, "totalShareIssueA", "double", false);
        }
        //最近一次估值
        if (bo.getValuationValue() != null && bo.getValuationValue().length > 0) {
            SetStartAndEndUtils.setStartAndEndByReflex(bo, "valuationValue", "double", false);
        }
    }

    private List<String> clearEmpty(List<String> asList) {
        List<String> result = new ArrayList<>();
        for (String s : asList) {
            if (StringUtils.isNotEmpty(s)){
                result.add(s);
            }
        }
        return result;
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
        processList = sortSelectList(processList);
//        手动添加辅导进程  使用1001
        List<RegTreeDto> fdProcessList = ipoCaseListMapper.getLabelByCode("IPO_FD_CASE_STAGE");
        RegTreeDto fdDto = new RegTreeDto();
        fdDto.setLabelName("辅导进程");
        fdDto.setName("辅导进程");
        fdDto.setLabelValue("1001");
        fdDto.setChildren(fdProcessList);
        processList.add(fdDto);
        resultMap.put("processList", processList);
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
                String key = field.getFieldId();
//                从ES获取的是完整的 所以需要截取方便后面代码使用
                if (symbol) {
                    if (field.getFieldId().indexOf("证监局") != -1) {
                        key = key.substring(0, key.length() - 3);
                    }
                }
                countMap.put(key, field.getCount());
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

    public Map<String, Object> queryIpoFavoriteList(QueryInfo<IpoCaseListBo> page, Boolean signSymbol) {
        //查询所有收藏的案例id
        UserInfo userInfo = getUserInfo();
        String companyId = userInfo.getCompanyId();
        String userId = userInfo.getUserId();

//        String companyId = "999000";
//        String userId = "746633289841890189";
        List<String> caseIds = ipoCaseListMapper.queryIpoFavoriteList(companyId, userId);
        Map<String, Object> resultMap = Maps.newHashMap();
        //案例id
        if (CollectionUtils.isEmpty(caseIds)) {
            return new HashMap<>();
        } else {
            List<String> caseIdList = Lists.newArrayList();
            for (String caseId : caseIds) {
                caseIdList.add("ipo"+caseId);
            }
            IpoCaseListBo bo = page.getCondition();

//        获取排序
            String orderByOrder = page.getOrderByOrder();
            if (StringUtils.isEmpty(orderByOrder)) {
                if (signSymbol) {
                    orderByOrder = "desc";
                } else {
                    orderByOrder = "desc,desc";
                }

            } else {
                if ("ascending".equals(orderByOrder)) {
                    orderByOrder = signSymbol ? "asc" : "desc,asc";
                } else if ("descending".equals(orderByOrder)) {
                    orderByOrder = signSymbol ? "desc" : "desc,desc";
                }
            }
            String orderByName = page.getOrderByName();
            if (StringUtils.isEmpty(orderByName)) {
                if (signSymbol) {
                    orderByName = "ipo_final_time_dt";
                } else {
                    orderByName = "ipo_open_flag_t,ipo_final_time_dt";
                }

            } else {
                if (!signSymbol) {
                    orderByName = "ipo_open_flag_t," + orderByName;
                }
            }
            bo.setCaseType("ipo");
            String caseType = bo.getCaseType();
            FacetResult<IpoCaseIndexDto> facetResult = null;
//        从ES查询
            if (Global.SEARCH_SERVER_IPO_CASE_FLAG.equals("0")) {
                if (Global.CASE_TYPE_ALL.equals(caseType)) {
                    bo.setCaseType(null);
                } else if (Global.CASE__TYPE_IPO.equals(caseType)) {
                    bo.setCaseType("ipocase");
                } else if (Global.CASE_TYPE_FD.equals(caseType)) {
                    bo.setCaseType("ipofdcase");
//                因为ipo和辅导库用了一个时间字段，当选辅导库时，设置ipo次数使查询结果置空
                    if(null != bo.getFsProcessTime() && bo.getFsProcessTime().length > 1){
                        bo.setIpoNum("1");
                    }
                }
                resetCondition(bo);
                bo.setCaseIdList(caseIdList);
                QueryInfo<IpoCaseListBo> queryInfo = new QueryInfo<IpoCaseListBo>();
                queryInfo.setCondition(bo);
                queryInfo.setOrderByName(orderByName);
                queryInfo.setOrderByOrder(orderByOrder);
                queryInfo.setStartRow(page.getStartRow());
                queryInfo.setPageSize(page.getPageSize());
                queryInfo.setQueryId("com.stock.capital.enterprise.ipoCase.dao.IpoCaseEs.ipoCaseSearchByEs");
                facetResult = searchClient.searchWithFacet("ipo_case", queryInfo, IpoCaseIndexDto.class);
            }
            resultMap.put("total", facetResult.getPage().getTotal());
            resultMap.put("data", facetResult.getPage().getData());

            //获取各个树对应的数字
            List<StatisticsField> plateList = facetResult.getStatisticsFieldMap().get("ipo_plate_t");
            List<StatisticsField> marketList =
                    facetResult.getStatisticsFieldMap().get("ipo_market_type_ss");
            List<StatisticsField> marketNumList =
                    facetResult.getStatisticsFieldMap().get("ipo_market_num_d");
            List<StatisticsField> greenList =
                    facetResult.getStatisticsFieldMap().get("ipo_green_passage_t");
            List<StatisticsField> arrangeList =
                    facetResult.getStatisticsFieldMap().get("ipo_special_arrange_ss");
            List<StatisticsField> bureauList =
                    facetResult.getStatisticsFieldMap().get("ipo_belongs_bureau_t");
//        获取caseType返回
//        String ipoType = facetResult.getStatisticsFieldMap().get("ipo_type_t");
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
//            如果可以查出树的值，则给计算的数据从新赋值
                if (marketNumList != null && marketNumList.size() > 0){
                    marketTreeNum = marketNumList.get(0).getCount();
                }
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
            if (law != null) {
                if (StringUtils.isNoneBlank(law.getIssueLawId())) {
                    resultMap.put("issueLawId", law.getIssueLawId());
                } else {
                    resultMap.put("issueLawId", "746412002835704646");
                }
            } else {
                resultMap.put("issueLawId", "746412002835704646");
            }
            IpoCaseListBo lawjxc = ipoCaseListMapper.querylawIdjxc();
            if (lawjxc != null) {
                if (StringUtils.isNoneBlank(lawjxc.getIssueLawId())) {
                    resultMap.put("issueLawJxcId", lawjxc.getIssueLawId());
                } else {
                    resultMap.put("issueLawJxcId", "458629827245657197");
                }
            } else {
                resultMap.put("issueLawJxcId", "458629827245657197");
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
        }
        return resultMap;

    }

    public Map<String, Object> getCassNote(IpoFavoriteAndNoteDto dto) {
        int total = 0;
        List<IpoFavoriteAndNoteDto> maaCaseNoteList = ipoCaseListMapper.getCassNote(dto);
        int endRow = maaCaseNoteList.size();
        if (Integer.parseInt(dto.getStartRow()) + Integer.parseInt(dto.getPageSize()) < endRow) {
            endRow = Integer.parseInt(dto.getStartRow()) + Integer.parseInt(dto.getPageSize());
        }
        if (maaCaseNoteList.size() > 0) {
            total = maaCaseNoteList.size();
            maaCaseNoteList = maaCaseNoteList.subList(Integer.parseInt(dto.getStartRow()), endRow);
        }
        Map<String, Object> result = Maps.newHashMap();
        result.put("recordsTotal", total);
        result.put("maaCasesList", maaCaseNoteList);
        return result;
    }

    public String isCompanyFlag(String companyCode) {
        return ipoCaseListMapper.isCompanyFlag(companyCode);
    }

    /**
     * 调用 api  初始化 地域select
     *
     * @return
     */
    public JsonResponse initAreaSelect() {
        ParameterizedTypeReference<JsonResponse<List<RepTreeTagDto>>> responseType = new ParameterizedTypeReference<JsonResponse<List<RepTreeTagDto>>>() {
        };
        MultiValueMap<String, String> request = new LinkedMultiValueMap<String, String>();
        request.add("type", Global.COUNTRY_OUTSIDE);
        String url = apiBaseUrl + "maaCase/getAreaDataList";
        JsonResponse<List<RepTreeTagDto>> result = restClient.post(url, request, responseType);
        return result;
    }

    /**
     * 获取相关案例
     *
     * @return
     */
    public List<String> getIpoItemCaseIdList(IpoCaseListBo ipoCaseListBo){
        return ipoCaseListMapper.getIpoItemCaseIdList(ipoCaseListBo);
    }


    /**
     * es获取letter_case_id_t
     *
     * @return
     */
    public FacetResult<LetterClassIndexDto> searchSuperviseLetter(QueryInfo<IpoCaseListBo> page) {
        QueryInfo<Map<String, Object>> queryInfo = new QueryInfo<>();
        queryInfo.setQueryId("com.stock.capital.enterprise.ipoCase.dto.LetterInfo.letterInfoOtherSearch");
        queryInfo.setPageSize(5000);
        queryInfo.setStartRow(0);
        queryInfo.setOrderByName("letter_letter_date_dt");
        queryInfo.setOrderByOrder("desc");
        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("questionType", Arrays.asList(page.getCondition().getLetterNodeId().split(",")));
        condition.put("letterApplyModule",Arrays.asList("4".split(",")));
        condition.put("queryFlag", "tree");
        condition.put("groupFlag", "true");
        condition.put("letterType",Arrays.asList(page.getCondition().getCheckCase().split(",")));
        queryInfo.setCondition(condition);
        FacetResult<LetterClassIndexDto> facetResultletterType = searchClient.searchWithFacet(Global.LETTER_INFO_INDEX_TYPE, queryInfo,
                LetterClassIndexDto.class);
        return facetResultletterType;
    }
}

package com.stock.capital.enterprise.ipoCase.service;

import static java.math.BigDecimal.ROUND_HALF_DOWN;

import com.stock.capital.enterprise.common.dao.AttachmentMapper;
import com.stock.capital.enterprise.common.entity.Attachment;
import com.stock.capital.enterprise.common.entity.AttachmentExample;
import com.stock.capital.enterprise.ipoCase.dao.IpoCaseBizMapper;
import com.stock.capital.enterprise.ipoCase.dao.IpoCaseListMapper;
import com.stock.capital.enterprise.ipoCase.dao.IpoIssuerIndustryStatusBizMapper;
import com.stock.capital.enterprise.ipoCase.dto.*;
import com.stock.core.dto.JsonResponse;
import com.stock.core.rest.RestClient;
import com.stock.core.service.BaseService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class CompanyOverviewService extends BaseService {

    @Autowired
    private IpoCaseBizMapper ipoCaseBizMapper;
    
    @Autowired
    private IpoIssuerIndustryStatusBizMapper ipoIssuerIndustryStatusBizMapper;

    @Autowired
    private AttachmentMapper attachmentMapper;

    @Autowired
    private IpoCaseListMapper ipoCaseListMapper;

    @Autowired
    private RestClient restClient;

    @Value("#{app['file.viewPath']}")
    private String fileViewPath;

    @Value("#{app['api.baseUrl']}")
    private String apiBaseUrl;

    /**
     * 案例基础信息
     *
     * @param id 案例id
     * @return companyOverviewVo
     */
    public CompanyOverviewVo getIpoCaseDetail(String id) {
        return ipoCaseBizMapper.getIpoCaseDetail(id);
    }

    /**
     * 详情页关联案例 by dxy
     * @param id 案例id
     * @return List<IpoAssociatedCaseVo>
     */
    public List<IpoAssociatedCaseVo> getAssociatedCaseList(String id, String companyId){

        //标识是否签约客户 默认不是
        boolean signSymbol = false;
        if (companyId != null && !"".equals(companyId)) {
            int count = ipoCaseListMapper.queryAuthByCompanyId(companyId);
            // 授权类型:签约 或 证监会 或 金融办
            if (count > 0) {
                //展示id
                signSymbol = true;
            }
        }

        List<IpoAssociatedCaseVo> result = ipoCaseBizMapper.getAssociatedCaseList(id);
        result = result.stream()
            .filter(dto -> !dto.getCaseId().equals(id))
            .collect(Collectors.toList());
        // TODO: 2019/7/16 根据日期倒序排序, 在排序好后倒序插入序号
        int sort = result.size();
        for (IpoAssociatedCaseVo vo : result) {
            if (!signSymbol && (StringUtils.isBlank(vo.getOpenFlag()) || !vo.getOpenFlag().equals("1"))){//如果是未开放 且 非签约用户, 把caseid 去掉
                vo.setCaseId("");
            }
            vo.setProSort(sort+"");
            sort -- ;
        }
        return result;
    }

    /**
     * 登陆其他资本市场信息
     *
     * @param id 案例id
     * @return list
     */
    public List<OtherMarketInfoDto> getMarketData(String id) {
        return ipoCaseBizMapper.getIpoMarketData(id);
    }

    /**
     * 登陆其他资本市场信息
     *
     * @param id 案例id
     * @return list
     */
    public List<IpoSplitDto> getSpliteData(String id) {
        List<IpoSplitDto> list = ipoCaseBizMapper.getSpliteData(id);
        for (IpoSplitDto ipoSplitDto : list) {
            AttachmentExample example = new AttachmentExample();
            example.createCriteria().andBusinessIdEqualTo(ipoSplitDto.getId());
            List<Attachment> attachmentList = attachmentMapper.selectByExample(example);
//            String fileType = ipoSplitDto.getSplitFileName().substring(ipoSplitDto.getSplitFileName().lastIndexOf("."));
//            String baseUrl = fileViewPath + "open/ipoFile/" + ipoSplitDto.getSplitFileId() + fileType;
//            ipoSplitDto.setFilePath(baseUrl);

            List<IpoFileDto> fileList = new ArrayList<>();
            for (Attachment attachment : attachmentList) {
                IpoFileDto fileDto = new IpoFileDto();
                fileDto.setSplitFileName(attachment.getAttName());
                fileDto.setSplitFileId(attachment.getId());
                String baseUrl = fileViewPath + attachment.getAttUrl().substring(1,attachment.getAttUrl().length());
                fileDto.setFilePath(baseUrl);
                fileList.add(fileDto);
            }
            ipoSplitDto.setFileList(fileList);
        }
        return list;
    }

    /**
     * 最近一次估值情况
     *
     * @param id 案例id
     * @return list
     */
    public List<IpoValuationDto> getVluationData(String id) {
        return ipoCaseBizMapper.getVluationData(id);
    }

    /**
     * 股东信息
     *
     * @param id 案例id
     * @return list
     */
    public List<IpoPersonInfoDto> getShareData(String id) {
        return ipoCaseBizMapper.getIpoShareData(id);
    }

    /**
     * 主要竞争对手简介
     *
     * @param id 案例id
     * @return list
     */
    public List<MainCompetitorInfoDto> getCompetitorData(String id) {
        return ipoCaseBizMapper.getIpoCompetitorData(id);
    }

    /**
     *  科技创新接口
     *
     * @param bid 案例id
     * @return list
     */
    public IpoTechnologyVo getPatentData(String bid) {

        IpoTechnologyVo result = new IpoTechnologyVo();

        //专利情况
        List<IpoTechnologyPatentDto> patent =ipoCaseBizMapper.getCompetitorData(bid);
        int isNotPatentNull = 0;
        for (IpoTechnologyPatentDto dto : patent) {
            if ((dto.getFm() != null && dto.getFm().compareTo(BigDecimal.ZERO)!=0)
                || (dto.getSy() != null && dto.getSy().compareTo(BigDecimal.ZERO) != 0)
                || (dto.getWg() != null && dto.getWg().compareTo(BigDecimal.ZERO) != 0)
                || (dto.getGw() != null && dto.getGw().compareTo(BigDecimal.ZERO) != 0)
            ){
                isNotPatentNull++;
            }
        }
        if (isNotPatentNull == 0){
            patent = new ArrayList<>();
        }


        // 研发投入
        List<IpoTechnologyTableDto> dev = ipoCaseBizMapper.getDevCompute(bid);
        int isNotDevNull = 0;
        for (IpoTechnologyTableDto dto : dev) {
            if ((dto.getFirstYearValue() != null && dto.getFirstYearValue().compareTo(BigDecimal.ZERO)!=0)
                || (dto.getSecondYearValue() != null && dto.getSecondYearValue().compareTo(BigDecimal.ZERO) != 0)
                || (dto.getThirdYearValue() != null && dto.getThirdYearValue().compareTo(BigDecimal.ZERO) != 0)
                || (dto.getForthYearValue() != null && dto.getForthYearValue().compareTo(BigDecimal.ZERO) != 0)){
                isNotDevNull++;
            }
        }
        if (isNotDevNull == 0){
            dev = new ArrayList<>();
        }

        // 核心技术人员
        List<IpoTechnologyTableDto> core = ipoCaseBizMapper.getCoreCompute(bid);
        int isNotCoreNull = 0;
        for (IpoTechnologyTableDto dto : core) {
            if ((dto.getFirstYearPro() != null && dto.getFirstYearPro().compareTo(BigDecimal.ZERO)!=0)
                || (dto.getSecondYearPro() != null && dto.getSecondYearPro().compareTo(BigDecimal.ZERO) != 0)
                || (dto.getThirdYearPro() != null && dto.getThirdYearPro().compareTo(BigDecimal.ZERO) != 0)
                || (dto.getForthYearPro() != null && dto.getForthYearPro().compareTo(BigDecimal.ZERO) != 0)){
                isNotCoreNull++;
            }
        }
        if (isNotCoreNull == 0){
            core = new ArrayList<>();
        }

        // 时间
        List<IpoTechnologyDateDto> date = ipoCaseBizMapper.getDate(bid);
        // 备注
        IpoTechnologyRemarksDto remarks = ipoCaseBizMapper.getRemarks(bid);

        result.setPatentData(patent);
        result.setDevData(dev);
        result.setCoreData(core);
        result.setRemarksData(remarks);
        if (date != null){
            if (date.size() > 0) {
                result.setDevDate(date.get(0));
            }
            if (date.size() > 1){
                result.setCoreDate(date.get(1));
            }
        }

        return result;
    }




    /**
     * 发行人的行业地位
     *
     * @param id 案例id
     * @return list
     */
    public List<IssuerIndustryStatusDto> getindustryStatusData(String id) {
        List<IssuerIndustryStatusDto> result = ipoIssuerIndustryStatusBizMapper.getindustryStatusData(id);
        if (result!= null && result.size() > 0 && result.get(0) != null){
            result = result.stream().//增加筛选条件, 任意不为空保留, 为空则变成空数组
                filter(dto->(StringUtils.isNotBlank(dto.getIndustryStatusOverview())
                || StringUtils.isNotBlank(dto.getRanking())
                || StringUtils.isNotBlank(dto.getRankingIndicator())
                || StringUtils.isNotBlank(dto.getRankingRange())))
                .collect(Collectors.toList());
        } else {
            result = null;
        }
        return result;
    }    
    
    /**
     * 报告期主要供应商及客户情况
     *
     * @param id 案例id
     * @return list
     */
    public Map<String, List<SupplierCustomerMainDto>> getSupCusData(String id) {
        Map<String, List<SupplierCustomerMainDto>> result = new HashMap<>();
        //报告期前五名供应商情况
        List<SupplierCustomerMainDto> supplierList =
            ipoCaseBizMapper.getSupplierOrCustomerData(id, "1");
        if (supplierList != null && !supplierList.isEmpty()) {
            for (SupplierCustomerMainDto supplierMainDto : supplierList) {
                int lastYearSupplier = getYearByDate(supplierMainDto.getReportPeriod());
                supplierMainDto.setFirstYearForSupplier((lastYearSupplier - 3) + "-12-31");
                supplierMainDto.setSecondYearForSupplier((lastYearSupplier - 2) + "-12-31");
                supplierMainDto.setThirdYearForSupplier((lastYearSupplier - 1) + "-12-31");


                supplierMainDto.getSupplierCustomerInfoList().add(addInfoRow(supplierMainDto.getSupplierCustomerInfoList()));
            }
            result.put("supplierMainList", supplierList);
        }
        //报告期前五名客户情况
        List<SupplierCustomerMainDto> customerList =
            ipoCaseBizMapper.getSupplierOrCustomerData(id, "2");
        if (customerList != null && !customerList.isEmpty()) {
            for (SupplierCustomerMainDto customerMainDto : customerList) {
                int lastYearCustomer = getYearByDate(customerMainDto.getReportPeriod());
                customerMainDto.setFirstYearForCustomer((lastYearCustomer - 3) + "-12-31");
                customerMainDto.setSecondYearForCustomer((lastYearCustomer - 2) + "-12-31");
                customerMainDto.setThirdYearForCustomer((lastYearCustomer - 1) + "-12-31");

                customerMainDto.getSupplierCustomerInfoList().add(addInfoRow(customerMainDto.getSupplierCustomerInfoList()));
            }
            result.put("customerMainList", customerList);
        }
        return result;
    }

//    private SupplierCustomerMainDto addMainRow(List<SupplierCustomerInfoDto> list){
//        SupplierCustomerMainDto lastDto = new SupplierCustomerMainDto();
//        lastDto.setCompanyName("合计");
//
//    }

    private SupplierCustomerInfoDto addInfoRow(List<SupplierCustomerInfoDto> list){
        SupplierCustomerInfoDto lastDto = new SupplierCustomerInfoDto();
        lastDto.setCompanyName("合计");
        lastDto.setFirstYearAmount(new BigDecimal(0));
        lastDto.setFirstYearRatio(new BigDecimal(0));
        lastDto.setSecondYearAmount(new BigDecimal(0));
        lastDto.setSecondYearRatio(new BigDecimal(0));
        lastDto.setThirdYearAmount(new BigDecimal(0));
        lastDto.setThirdYearRatio(new BigDecimal(0));
        lastDto.setOnePeriodAmount(new BigDecimal(0));
        lastDto.setOnePeriodRatio(new BigDecimal(0));
        for (SupplierCustomerInfoDto supplierCustomerInfoDto : list) {
            if (supplierCustomerInfoDto.getFirstYearAmount() != null) {
                lastDto.setFirstYearAmount(lastDto.getFirstYearAmount().add(supplierCustomerInfoDto.getFirstYearAmount()));
            }
            if (supplierCustomerInfoDto.getFirstYearRatio() != null) {
                lastDto.setFirstYearRatio(lastDto.getFirstYearRatio().add(supplierCustomerInfoDto.getFirstYearRatio()));
            }
            if (supplierCustomerInfoDto.getSecondYearAmount() != null) {
                lastDto.setSecondYearAmount(lastDto.getSecondYearAmount().add(supplierCustomerInfoDto.getSecondYearAmount()));
            }
            if (supplierCustomerInfoDto.getSecondYearRatio() != null) {
                lastDto.setSecondYearRatio(lastDto.getSecondYearRatio().add(supplierCustomerInfoDto.getSecondYearRatio()));
            }
            if (supplierCustomerInfoDto.getThirdYearAmount() != null) {
                lastDto.setThirdYearAmount(lastDto.getThirdYearAmount().add(supplierCustomerInfoDto.getThirdYearAmount()));
            }
            if (supplierCustomerInfoDto.getThirdYearRatio() != null) {
                lastDto.setThirdYearRatio(lastDto.getThirdYearRatio().add(supplierCustomerInfoDto.getThirdYearRatio()));
            }
            if (supplierCustomerInfoDto.getOnePeriodAmount() != null) {
                lastDto.setOnePeriodAmount(lastDto.getOnePeriodAmount().add(supplierCustomerInfoDto.getOnePeriodAmount()));
            }
            if (supplierCustomerInfoDto.getOnePeriodRatio() != null) {
                lastDto.setOnePeriodRatio(lastDto.getOnePeriodRatio().add(supplierCustomerInfoDto.getOnePeriodRatio()));
            }
        }
        return lastDto;
    }

    /**
     * 主营业务收入构成
     *
     * @param id 案例id
     * @return list
     */
    public MainIncomeVo getIncomeData(String id) {
        MainIncomeVo mainIncomeVo = new MainIncomeVo();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<MainIncomeInfoDto> mainIncomeInfoList = ipoCaseBizMapper.getIncomeData(id);
        if (mainIncomeInfoList != null && !mainIncomeInfoList.isEmpty()) {
            int lastYear = getYearByDate(mainIncomeInfoList.get(0).getReportPeriod());
            mainIncomeVo.setFirstYearForIncome((lastYear - 3) + "-12-31");
            mainIncomeVo.setSecondYearForIncome((lastYear - 2) + "-12-31");
            mainIncomeVo.setThirdYearForIncome((lastYear - 1) + "-12-31");
            mainIncomeVo
                .setOnePeriodForIncome(sdf.format(mainIncomeInfoList.get(0).getReportPeriod()));
            BigDecimal onePeriodAmountS = BigDecimal.ZERO;
            BigDecimal onePeriodRatioS = BigDecimal.ZERO;
            BigDecimal thirdYearAmountS = BigDecimal.ZERO;
            BigDecimal thirdYearRatioS = BigDecimal.ZERO;
            BigDecimal secondYearAmountS = BigDecimal.ZERO;
            BigDecimal secondYearRatioS = BigDecimal.ZERO;
            BigDecimal firstYearAmountS = BigDecimal.ZERO;
            BigDecimal firstYearRatioS = BigDecimal.ZERO;
            for (MainIncomeInfoDto dto : mainIncomeInfoList) {
                if (dto.getOnePeriodAmount() != null) {
                    onePeriodAmountS = onePeriodAmountS.add(dto.getOnePeriodAmount());
                }
                if (dto.getOnePeriodRatio() != null) {
                    onePeriodRatioS = onePeriodRatioS.add(dto.getOnePeriodRatio());
                }
                if (dto.getThirdYearAmount() != null) {
                    thirdYearAmountS = thirdYearAmountS.add(dto.getThirdYearAmount());
                }
                if (dto.getThirdYearRatio() != null) {
                    thirdYearRatioS = thirdYearRatioS.add(dto.getThirdYearRatio());
                }
                if (dto.getSecondYearAmount() != null) {
                    secondYearAmountS = secondYearAmountS.add(dto.getSecondYearAmount());
                }
                if (dto.getSecondYearRatio() != null) {
                    secondYearRatioS = secondYearRatioS.add(dto.getSecondYearRatio());
                }
                if (dto.getFirstYearAmount() != null) {
                    firstYearAmountS = firstYearAmountS.add(dto.getFirstYearAmount());
                }
                if (dto.getFirstYearRatio() != null) {
                    firstYearRatioS = firstYearRatioS.add(dto.getFirstYearRatio());
                }
            }
            MainIncomeInfoDto sumDto = new MainIncomeInfoDto();
            sumDto.setBusinessName("合计");
            sumDto.setOnePeriodAmount(onePeriodAmountS);
            sumDto.setOnePeriodRatio(onePeriodRatioS);
            sumDto.setThirdYearAmount(thirdYearAmountS);
            sumDto.setThirdYearRatio(thirdYearRatioS);
            sumDto.setSecondYearAmount(secondYearAmountS);
            sumDto.setSecondYearRatio(secondYearRatioS);
            sumDto.setFirstYearAmount(firstYearAmountS);
            sumDto.setFirstYearRatio(firstYearRatioS);
            mainIncomeInfoList.add(sumDto);
            mainIncomeVo.setMainIncomeInfoList(mainIncomeInfoList);
        }
        return mainIncomeVo;
    }

    /**
     * 中介机构信息
     *
     * @param id        案例id
     * @param validFlag 状态 0:失效 1:生效
     * @return map
     */
    public Map<String, List<IntermediaryOrgDto>> getIntermediaryOrgData(
        String id, String validFlag) {
        Map<String, List<IntermediaryOrgDto>> result = new HashMap<>();
        List<IntermediaryOrgDto> mainList = new ArrayList<>();
        List<IntermediaryOrgDto> moreList = new ArrayList<>();
        List<IntermediaryOrgDto> intermediaryOrgList =
            ipoCaseBizMapper.getIntermediaryOrgData(id, validFlag);
        if (intermediaryOrgList != null && !intermediaryOrgList.isEmpty()) {
            for (IntermediaryOrgDto intermediaryOrgDto : intermediaryOrgList) {
                if (intermediaryOrgDto.getIntermediaryType() != null) {
                    // 保荐机构
                    if ("1".equals(intermediaryOrgDto.getIntermediaryType())) {
                        mainList.add(intermediaryOrgDto);
                    }
                    // 证券公司
                    if ("2".equals(intermediaryOrgDto.getIntermediaryType())) {
                        moreList.add(intermediaryOrgDto);
                    }
                    // 律师事务所
                    if ("3".equals(intermediaryOrgDto.getIntermediaryType())) {
                        if (intermediaryOrgDto.getIntermediaryTypeName() != null && "律师事务所"
                            .equals(intermediaryOrgDto.getIntermediaryTypeName())) {
                            mainList.add(intermediaryOrgDto);
                        } else {
                            moreList.add(intermediaryOrgDto);
                        }
                    }
                    // 会计事务所
                    if ("4".equals(intermediaryOrgDto.getIntermediaryType())) {
                        if (intermediaryOrgDto.getIntermediaryTypeName() != null && "会计师事务所"
                            .equals(intermediaryOrgDto.getIntermediaryTypeName())) {
                            mainList.add(intermediaryOrgDto);
                        } else {
                            moreList.add(intermediaryOrgDto);
                        }
                    }
                    // 资产评估机构
                    if ("5".equals(intermediaryOrgDto.getIntermediaryType())) {
                        moreList.add(intermediaryOrgDto);
                    }

                    // 存托机构
                    if ("6".equals(intermediaryOrgDto.getIntermediaryType())) {
                        moreList.add(intermediaryOrgDto);
                    }
                }
            }
        }
        //主要机构
        result.put("mainList", mainList);
        //更多机构
        result.put("moreList", moreList);
        return result;
    }

    /**
     * 查询案例详情页头部展示数据
     *
     * @param id
     * @return
     */
    public HeadDataVo getHeadData(String id) {
        return ipoCaseBizMapper.getHeadData(id);
    }

    /**
     * 取日期中年份
     *
     * @param date 日期
     * @return int
     */
    private int getYearByDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.YEAR);
    }

    /**
     * 判断收藏
     */
    public int getDetermineWhetherToCollect(String caseId,boolean favoriteFlag, String ipaddr, String companyId, String userId){
        //判断收藏
        String url = "";
        if(favoriteFlag){
            url = apiBaseUrl + "rep_case_favorite_note_api/saveCaseFavorite";// 收藏 CaseFavoriteAndNoteController.java
        }else{
            url = apiBaseUrl + "rep_case_favorite_note_api/deleteCaseFavorite";// 取消收藏
        }
        ParameterizedTypeReference<JsonResponse<Integer>> responseType = new ParameterizedTypeReference<JsonResponse<Integer>>(){
        };
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.set("caseId",caseId);
        params.set("companyId",companyId);
        params.set("userId",userId);
        params.set("ipaddr",ipaddr);
        params.set("source","1");
        params.set("type","4");
        JsonResponse<Integer> retVal = restClient.post(url, params, responseType);
        return retVal.getResult();
    }

    /**
     * 删除笔记
     */

    public int deleteNotes(String caseId,String note, String ipaddr,String companyId,String userId){
        String url = apiBaseUrl + "rep_case_favorite_note_api/deleteCaseNote";// 删除笔记 CaseFavoriteAndNoteController.java
        ParameterizedTypeReference<JsonResponse<Integer>> responseType = new ParameterizedTypeReference<JsonResponse<Integer>>(){
        };
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.set("caseId",caseId);
        params.set("companyId",companyId);
        params.set("userId",userId);
        params.set("ipaddr",ipaddr);
        params.set("note",note);
        params.set("source","1");
        params.set("type","4");
        JsonResponse<Integer> retVal = restClient.post(url, params, responseType);
        return retVal.getResult();
    }

    /**
     * 判断笔记
     * by ChenYufan
     */

    public int getNoteDetermination(String caseId,String note, String ipaddr,String companyId,String userId){
        String url = apiBaseUrl + "rep_case_favorite_note_api/queryCaseNote";// 查询笔记 CaseFavoriteAndNoteController.java
        ParameterizedTypeReference<JsonResponse<Integer>> responseType = new ParameterizedTypeReference<JsonResponse<Integer>>(){
        };
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.set("caseId",caseId);
        params.set("companyId",companyId);
        params.set("userId",userId);
        params.set("ipaddr",ipaddr);
        params.set("note",note);
        params.set("source","1");
        params.set("type","4");
        JsonResponse<Integer> retVal = restClient.post(url, params, responseType);
        return retVal.getResult();
    }

    public Map<String,String> getCaseFavoriteAndNote(Map<String,String> map){
        return ipoCaseBizMapper.getCaseFavoriteAndNote(map);
    }
}

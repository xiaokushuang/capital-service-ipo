//package com.stock.capital.enterprise.declare.control;
//
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.text.ParseException;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.google.common.base.Splitter;
//import com.google.common.collect.Lists;
//import com.google.common.collect.Maps;
//import com.google.common.net.MediaType;
//import com.stock.capital.enterprise.announcement.dto.SearchAnnounceDto;
//import com.stock.capital.enterprise.announcement.service.AnnouncementService;
//import com.stock.capital.enterprise.common.constant.Global;
//import com.stock.capital.enterprise.common.dao.TaskMapper;
//import com.stock.capital.enterprise.common.model.entity.DeclareIdea;
//import com.stock.capital.enterprise.common.model.entity.DeclareInfo;
//import com.stock.capital.enterprise.common.model.entity.Task;
//import com.stock.capital.enterprise.common.service.CommonService;
//import com.stock.capital.enterprise.declare.dao.DeclareInfoBizMapper;
//import com.stock.capital.enterprise.declare.dto.DeclareApproveDto;
//import com.stock.capital.enterprise.declare.dto.DeclareFileDto;
//import com.stock.capital.enterprise.declare.dto.DeclareIndexDto;
//import com.stock.capital.enterprise.declare.dto.DeclareInfoDto;
//import com.stock.capital.enterprise.declare.dto.DeclareInfoSumDto;
//import com.stock.capital.enterprise.declare.dto.DeclareTypeDto;
//import com.stock.capital.enterprise.declare.dto.InfoDeclareSearchDto;
//import com.stock.capital.enterprise.declare.dto.MeetingDto;
//import com.stock.capital.enterprise.declare.dto.MeetingFileDto;
//import com.stock.capital.enterprise.declare.dto.NoticeHelperDto;
//import com.stock.capital.enterprise.declare.dto.NoticeSearchDto;
//import com.stock.capital.enterprise.declare.dto.QueryNoticeDto;
//import com.stock.capital.enterprise.declare.service.DeclareExportService;
//import com.stock.capital.enterprise.declare.service.DeclareService;
//import com.stock.capital.enterprise.home.service.HomeService;
//import com.stock.capital.enterprise.system.service.SystemManagerService;
//import com.stock.capital.enterprise.utils.StockBoard;
//import com.stock.capital.enterprise.workflow.dto.WorkflowApprovalHistoryDto;
//import com.stock.capital.enterprise.workflow.dto.WorkflowDto;
//import com.stock.capital.enterprise.workflow.service.WorkflowService;
//import com.stock.core.Constant;
//import com.stock.core.controller.BaseController;
//import com.stock.core.dto.JsonResponse;
//import com.stock.core.dto.Page;
//import com.stock.core.dto.QueryInfo;
//import com.stock.core.rest.RestClient;
//import com.stock.core.search.SolrSearchUtil;
//import com.stock.core.util.DateUtil;
//import com.stock.core.util.JsonUtil;
//import com.stock.core.util.WebUtil;
//import com.stock.core.web.DownloadView;
//
///**
// * 信息披露画面Controller
// * 
// * @author xuehui
// *
// */
//@Controller
//@RequestMapping("declare")
//public class DeclareController extends BaseController {
//    @Autowired
//    DeclareService declareService;
//    
//    @Autowired
//    AnnouncementService announcementService;
//    
//    @Autowired
//    private CommonService commonService;
//
//    @Autowired
//    DeclareInfoBizMapper declareInfoBizMapper;
//    
//    @Autowired
//    TaskMapper taskMapper;
//
//    /**
//     * 接口调用
//     */
//    @Autowired
//    private RestClient restClient;
//
//    @Autowired
//    private HomeService homeService;
//
//    @Autowired
//    private SystemManagerService systemManagerService;
//
//    @Autowired
//    private DeclareExportService declareExportService;
//    
//    @Autowired
//    private WorkflowService workflowService;
//    
//    @Value("#{app['api.baseUrl']}")
//    private String apiBaseUrl;
//    /**
//     * 官网浏览URL基础路径
//     */
//    @Value("#{app['official.viewPath']}")
//    private String officialViewPath;
//
//    /**
//     * 信息披露首页
//     * 
//     * @return ModelAndView
//     * @throws ParseException
//     */
//    @RequestMapping(value = "init", method = RequestMethod.POST)
//    public ModelAndView init(String flag) throws ParseException {
//        ModelAndView mv = new ModelAndView("declare/declareIndex");
//        List<DeclareInfoSumDto> disList = declareService.getDeclareInfoList();
//        mv.addObject("disList", disList);
//        DeclareIndexDto did = new DeclareIndexDto();
//        did.setStockBoard(StockBoard.getStockBlock(getUserInfo().getInfo().get(Global.COMPANY_CODE).toString()));
//        mv.addObject("infoDto", did);
//        if (StringUtils.isNotEmpty(flag)) {
//            mv.addObject("backFlag", flag);
//        }
//        return mv;
//    }
//    /**
//     * 审批预览打印
//     * @return ModelAndView
//     */
//    @RequestMapping(value = "/infoStmp", method = RequestMethod.POST)
//    public ModelAndView infoStmp(String id, String flag, String approvalId, String endTime, String apprNote) {
//        ModelAndView mv = new ModelAndView("declare/infoStmp");
//        DeclareInfoDto declareInfoDtoSaved = declareService.getDeclareInfo(id);
//        if(StringUtils.isNotEmpty(approvalId)){
//            declareInfoDtoSaved.setFileList(declareService.getDeclareFile(id, approvalId));
//        }else{
//            declareInfoDtoSaved.setFileList(declareService.getDeclareFile(id));
//        }
//        mv.addObject("infoDto", declareInfoDtoSaved);
//        mv.addObject("approveList", declareService.getApproveList(id));
//        mv.addObject("flag", flag);
//        // 2018/04/17 需求4050 by zhangjinqiu start  备注
//        mv.addObject("endTime", endTime);
//        mv.addObject("apprNote", apprNote);
//        // 2018/04/17 需求4050 by zhangjinqiu end
//        return mv;
//    }
//
//    @RequestMapping(value = "loggingDeclare")
//    public ModelAndView loggingDeclare(String flag){
//        ModelAndView mv = new ModelAndView("declare/loggingDeclare");
//        DeclareInfoDto declareInfoDto = new DeclareInfoDto();
//        declareInfoDto.setMeetingIdFlag(flag);
//        declareInfoDto.setMaxDeclareNo(declareService.getMaxDeclareNo());
//        String approveFiles = systemManagerService.getApproveFileSuffix();
//        mv.addObject("approveFiles", approveFiles);
//        mv.addObject("infoDto",declareInfoDto);
//        return mv;
//    }
//    /**
//     * 创建信披任务
//     * 
//     * @return ModelAndView
//     * @throws ParseException
//     */
//    @RequestMapping(value = "createApproveDeclareInfoTask", method = RequestMethod.POST)
//    @ResponseBody
//    public JsonResponse<List<DeclareApproveDto>> createApproveDeclareInfoTask(DeclareInfoDto declareInfoDto)
//            throws ParseException {
//
//        declareService.createApproveDeclareInfoTask(declareInfoDto);
//        JsonResponse<List<DeclareApproveDto>> jsonRes = new JsonResponse<List<DeclareApproveDto>>();
//        // 需求2449 审批工作流共通化 by weishisheng 2018/1/9 start
////        jsonRes.setResult(declareService.getApproveList(declareInfoDto.getId()));
//        jsonRes.setResult(new ArrayList<DeclareApproveDto>());
//        // 需求2449 审批工作流共通化 by weishisheng 2018/1/9 end
//        // ModelAndView mv = new ModelAndView("declare/declareIndex");
//        // List<DeclareInfoSumDto> disList = declareService.getDeclareInfoList();
//        // mv.addObject("disList", disList);
//        // mv.addObject("infoDto", new DeclareIndexDto());
//        return jsonRes;
//    }
//
//    /**
//     * 公司公告查询
//     * 
//     * @return ModelAndView
//     */
//    @RequestMapping(value = "noticeIndex")
//    public ModelAndView noticeIndex(InfoDeclareSearchDto infoSear) {
//        ModelAndView mv = new ModelAndView("declare/noticeIndex");
//        Map<String, String> condition = Maps.newHashMap();
//        String conditionsStr = StringUtils.EMPTY;
//        InfoDeclareSearchDto infoDeclareSearchDto = new InfoDeclareSearchDto();
//        infoDeclareSearchDto.setCode(infoSear.getCode());
//        infoDeclareSearchDto.setComType(infoSear.getComType());
//        infoDeclareSearchDto.setContent(infoSear.getContent());
//        infoDeclareSearchDto.setStartPublishDate(infoSear.getStartPublishDate());
//        infoDeclareSearchDto.setEndPublishDate(infoSear.getEndPublishDate());
//        if (infoSear.getComType().equals("home")) {
//            conditionsStr = "declare_code_i:" + getUserInfo().getInfo().get(Global.COMPANY_CODE).toString();
//        } else if (infoSear.getComType().equals("toCom")) {
//            mv.addObject("toCom", true);
//            List<String> toComList = declareInfoBizMapper.getToComCodes(getUserInfo().getInfo().get(Global.COMPANY_ID)
//                    .toString());
//            if (toComList.size() > 0) {
//                for (int i = 0; i < toComList.size(); i++) {
//                    if (i == 0) {
//                        conditionsStr = "(declare_code_i:" + toComList.get(i);
//                    } else {
//                        conditionsStr = conditionsStr + " OR " + "declare_code_i:" + toComList.get(i);
//                    }
//                }
//                conditionsStr = conditionsStr + ")";
//            }
//        }
//        String companyCode = StringUtils.EMPTY;
//
//        if (StringUtils.isNotEmpty(infoSear.getCode())) {
//            if (infoSear.getCode().contains(" ")) {
//                companyCode = infoSear.getCode().split("  ")[0];
//            } else {
//                companyCode = infoSear.getCode();
//            }
//        }
//        // 处理关键字的检索条件
//        if (StringUtils.isNotEmpty(infoSear.getContent())) {
//            // List<String> conditionValues = Splitter.on(StringUtils.SPACE).splitToList(infoSear.getContent());
//            // Multimap<String, String> conditions = ArrayListMultimap.create();
//            // for (String conditionValue : conditionValues) {
//            // // 对特殊字符进行转义+ - && || ! ( ) { } [ ] ^ ” ~ * ? : \
//            // conditions.put("declare_content_t", ClientUtils.escapeQueryChars(conditionValue));
//            // }
//            if (StringUtils.isNotEmpty(conditionsStr)) {
//                conditionsStr = conditionsStr + " AND "
//                        + SolrSearchUtil.parseStringKeyWords("declare_content_t", infoSear.getContent(), null);
//                // conditionsStr =
//                // conditionsStr+" AND "+Joiner.on(StringUtils.SPACE).withKeyValueSeparator(":").join(conditions.entries());
//            } else {
//                if (StringUtils.isNotEmpty(infoSear.getCode())) {
//                    conditionsStr = "declare_code_t:*" + companyCode + "*";
//                    conditionsStr = conditionsStr + " AND ("
//                            + SolrSearchUtil.parseStringKeyWords("declare_title_t", infoSear.getContent(), "200");
//                    conditionsStr = conditionsStr + ") OR ("
//                            + SolrSearchUtil.parseStringKeyWords("declare_content_t", infoSear.getContent(), "3") + ")";
//                } else {
//                    conditionsStr = conditionsStr + "("
//                            + SolrSearchUtil.parseStringKeyWords("declare_title_t", infoSear.getContent(), "200");
//                    conditionsStr = conditionsStr + ") OR ("
//                            + SolrSearchUtil.parseStringKeyWords("declare_content_t", infoSear.getContent(), "3") + ")";
//                }
//            }
//        } else if (StringUtils.isNotEmpty(infoSear.getCode())) {
//            conditionsStr = "declare_code_t:*" + companyCode + "*";
//        }
//        // 处理日期类型的检索条件
//        // String startDate = "*";
//        // String endDate = "*";
//        // if (infoSear.getStartPublishDate() != null) {
//        // DateTime d = new DateTime(infoSear.getStartPublishDate());
//        // startDate = d.toString("yyyy-MM-dd'T'HH:mm:ss'Z'");
//        // }
//        // if (infoSear.getEndPublishDate() != null) {
//        // DateTime d = new DateTime(infoSear.getEndPublishDate());
//        // endDate = d.toString("yyyy-MM-dd'T'HH:mm:ss'Z'");
//        // }
//        String publishDate = SolrSearchUtil.parseDateKeyWords("declare_publish_date_dt",
//                infoSear.getStartPublishDate(), infoSear.getEndPublishDate());
//        if (StringUtils.isNotEmpty(publishDate)) {
//            if (StringUtils.isNotEmpty(conditionsStr)) {
//                conditionsStr = conditionsStr + " AND " + publishDate;
//            } else {
//                conditionsStr = conditionsStr + publishDate;
//            }
//        }
//        condition.put(Constant.SEARCH_CONDIATION, conditionsStr);
//        // TODO 公司代码查询cotent高亮问题
////        if (StringUtils.isNotEmpty(infoSear.getCode()) && StringUtils.isEmpty(infoSear.getContent())) {
//            condition.put(Constant.SEARCH_HIGHTLIGHT_FIELD, "declare_title_t,declare_content_t");
////        } else {
////            condition.put(Constant.SEARCH_HIGHTLIGHT_FIELD, "declare_title_t,declare_content_t");
////        }
//        QueryInfo<Map<String, String>> queryInfo = commonSearch(condition);
//        if (StringUtils.isEmpty(infoSear.getContent())) {
//            queryInfo.setOrderByName("declare_publish_date_dt");
//            queryInfo.setOrderByOrder("desc");
//        }
//        String url = apiBaseUrl + "infoSerIndex/ajaxInfoIndex";
//        ParameterizedTypeReference<Page<Map<String, String>>> responseType = new ParameterizedTypeReference<Page<Map<String, String>>>() {
//        };
//        Page<Map<String, String>> page = restClient.post(url, queryInfo, responseType);
//        queryInfo.setTotal(page.getTotal());
//        mv.addObject("queryList", page.getData());
//        mv.addObject("infoDeclareSearchDto", infoDeclareSearchDto);
//        return mv;
//    }
//
//    /**
//     * 创建信息披露
//     * 
//     * @return ModelAndView
//     */
//    @RequestMapping(value = "newDeclare", method = RequestMethod.POST)
//    public ModelAndView newDeclare(String meetingIdFlag,String itemTaskId) {
//        ModelAndView mv = new ModelAndView("declare/createDeclareOne");
//        mv.addObject(
//                "declareTypeList",
//                JsonUtil.toJson(declareService.getDeclareType(StockBoard.getStockBlock(getUserInfo().getInfo().get(Global.COMPANY_CODE)
//                		.toString()))));
//        DeclareTypeDto declareTypeDto = new DeclareTypeDto();
//        if ("0".equals(meetingIdFlag)) {
//            declareTypeDto.setMeetingIdFlag(meetingIdFlag);
//        } else {
//            declareTypeDto.setMeetingIdFlag("1");
//        }
//        mv.addObject("infoDto", declareTypeDto);
//        mv.addObject("stockBoard",
//                StockBoard.getStockBlock(getUserInfo().getInfo().get(Global.COMPANY_CODE).toString()));
//        mv.addObject("itemTaskId", itemTaskId);
//        return mv;
//
//    }
//
//    /**
//     * 信息披露审批
//     * 
//     * @param id
//     *            信披id
//     * @param taskId
//     *            任务id
//     * @return ModelAndView
//     */
//    @RequestMapping(value = "approveDeclareInfo", method = RequestMethod.POST)
//    public ModelAndView approveDeclareInfo(String id, String taskId, String approvalId, String dealerStatus, String taskInstId, String procInstId, String endTime, String apprNote) {
//        ModelAndView mv = new ModelAndView("declare/approveDeclareInfo");
//        DeclareInfoDto declareInfoDtoSaved = declareService.getDeclareInfo(id);
//        mv.addObject("infoDto", declareInfoDtoSaved);
//        mv.addObject("approveList", declareService.getApproveList(id));
//        DeclareApproveDto declareApproveDto = new DeclareApproveDto();
//        declareApproveDto.setId(approvalId);
//        declareApproveDto.setTaskId(taskId);
//        declareApproveDto.setBusinessId(id);
//        declareApproveDto.setFileList(declareService.getDeclareFile(id, approvalId));
//        mv.addObject("declareApproveDto", declareApproveDto);
//        Task tempTask = taskMapper.selectByPrimaryKey(taskId);
//        if(tempTask != null) {
//            mv.addObject("dealerStatus", tempTask.getDealerStatus());
//        }
//        mv.addObject("declareId",id);
//        // 需求2449 审批工作流共通化 by weishisheng 2018/1/9 start
//        mv.addObject("taskId", taskId);
//        mv.addObject("procInstId",procInstId);
//        mv.addObject("taskInstId",taskInstId);
//        if (StringUtils.isNotEmpty(endTime)) {
//            mv.addObject("endTime",DateUtil.getDate(endTime, DateUtil.YYYY_MM_DD));
//        }
//        mv.addObject("apprNote", apprNote);
//        // 需求2449 审批工作流共通化 by weishisheng 2018/1/9 end
//        return mv;
//
//    }
//
//    /**
//     * 信披审核保存
//     * 
//     * @param declareApproveDto
//     *            信披审核信息
//     * @return ModelAndView
//     * @throws ParseException
//     */
//    @RequestMapping(value = "saveApprove")
//    @ResponseBody
//    public JsonResponse<String> saveApprove(DeclareApproveDto declareApproveDto) throws ParseException {
//        declareService.saveApprove(declareApproveDto);
//        JsonResponse<String> jsonRes = new JsonResponse<String>();
//        jsonRes.setResult(new String());
//        return jsonRes;
//    }
//
//    /**
//     * 撤销审批
//     * 
//     * @param declareId
//     *            信披id
//     * @param approvalId
//     *            审批id
//     * 
//     */
//    @RequestMapping(value = "cancelApproval")
//    @ResponseBody
//    public JsonResponse<List<DeclareApproveDto>> cancelApproval(String declareId, String approvalId) {
//        declareService.cancelApproval(declareId, approvalId);
//        JsonResponse<List<DeclareApproveDto>> jsonRes = new JsonResponse<List<DeclareApproveDto>>();
//        jsonRes.setResult(declareService.getApproveList(declareId));
//        return jsonRes;
//    }
//
//    /**
//     * 创建信息披露step2
//     * 
//     * @param declareTypeDto
//     *            选择的所有业务类型数据
//     * @return ModelAndView
//     */
//    @RequestMapping(value = "createDeclareTwo", method = RequestMethod.POST)
//    public ModelAndView createDeclareTwo(DeclareTypeDto declareTypeDto,String itemTaskId) throws Exception {
//        ModelAndView mv;
//        if (declareTypeDto != null && "2".equals(declareTypeDto.getStepFlag())) {
//            mv = new ModelAndView("declare/createDeclareTwo");
//            // 需求396
//            String approveFiles = systemManagerService.getApproveFileSuffix();
//            // 需求396
//            mv.addObject("approveFiles", approveFiles);
//            // if (declareTypeDto.getDeclareIdeaList() != null) {
//            // declareService.saveDeclareIdea(declareTypeDto);
//            // }
//            mv.addObject("lawViewPath", officialViewPath);
//            mv.addObject("stockBoard",
//                    StockBoard.getStockBlock(getUserInfo().getInfo().get(Global.COMPANY_CODE).toString()));
//        } else {
//            mv = new ModelAndView("declare/createDeclareThree");
//
//        }
//        DeclareInfoDto declareInfoDto = new DeclareInfoDto();
//        //demand2097 byzhangjiaming 2017-07-11 start
//        String fromCloud = "true";
//        if(declareTypeDto != null){
//        	declareInfoDto = declareService.getDeclareInfo(declareTypeDto);
//        	List<DeclareFileDto> fileList = declareTypeDto.getDeclareFileList();
//        	if(fileList != null){
//        	 for(int i=0; i<fileList.size(); i++){
//             	if(StringUtils.isNotEmpty(fileList.get(i).getFileId())){
//                    declareService.replaceTemplateContent("", "" ,fileList.get(i).getFileId(), "", fromCloud);
//             	}
//             }
//        	}
//        }
//        //demand2097 byzhangjiaming 2017-07-11 end
//        if (StringUtils.isNotEmpty(declareInfoDto.getId())) {
//            declareService.saveDeclareInfo(declareInfoDto,null);
//            DeclareInfoDto declareInfoDtoSaved = declareService.getDeclareInfo(declareInfoDto.getId());
//            if (StringUtils.isNotEmpty(declareInfoDto.getMeetingIdFlag())) {
//                declareInfoDtoSaved.setMeetingIdFlag(declareInfoDto.getMeetingIdFlag());
//                declareInfoDtoSaved.setPageFlag(declareInfoDto.getPageFlag());
//            }
//            declareInfoDtoSaved.setFileList(declareService.getDeclareFile(declareInfoDto.getId()));
//            //demand2097 byzhangjiaming start
////            declareService.replaceContent(declareInfoDto.getId());
//            //demand2097 byzhangjiaming end
//            declareInfoDto = declareInfoDtoSaved;
//            if (!"2".equals(declareTypeDto.getStepFlag())) {
//                mv.addObject("approveList", declareService.getApproveList(declareInfoDto.getId()));
//            }
//        }
//        WebUtil.getCurrentRequest().getSession().setAttribute("reDeclareInfoDto", declareInfoDto);
//        
//        // 需求2449 审批工作流共通化 by weishisheng 2018/1/9 start
//        setApprovalParameter(declareInfoDto, mv);
//        // 需求2449 审批工作流共通化 by weishisheng 2018/1/9 end
//        mv.addObject("infoDto", declareInfoDto);
//        if(StringUtils.isNotEmpty(declareInfoDto.getId())) {
//            mv.addObject("disclosureDateHis", declareService.getDisclosureDateList(declareInfoDto.getId()));
//        }
//        mv.addObject("personId", getUserInfo().getInfo().get(Global.PERSON_ID).toString());
//        mv.addObject("itemTaskId",itemTaskId);
//        return mv;
//    }
//    
//    /**
//     * 需求2449 审批工作流共通化 by weishisheng 2018/1/9 
//     * 设定审批工作流用的参数
//     *
//     * @param declareInfoDto dto
//     * @param mv view
//     *
//     */
//    private void setApprovalParameter(DeclareInfoDto declareInfoDto, ModelAndView mv) {
//
//        // 取得分类ID列表
//        String categoryIdPrefix = "XP";
//        StringBuilder categoryIdStb = new StringBuilder(0);
//        for (String type : declareInfoDto.getTypeIdList()) {
//            categoryIdStb.append(categoryIdPrefix).append(type).append(",");
//        }
//        String categoryId = "XP,";
//        if (categoryIdStb.length() > 3) {
//            categoryId = categoryIdStb.substring(0, categoryIdStb.length()-1);
//        }
//        
//        if(StringUtils.isNotEmpty(categoryId)){// 子节点分类id转化为一级节点Id,并追加信披根节点
//            categoryId = "XP,".concat(workflowService.getDeclareTypeParentIds(categoryId));
//        }
//        // 取得流程列表
//        List<WorkflowDto> processList = workflowService.getProcessDropdownList(categoryId);
//
//        mv.addObject("businessType", com.stock.capital.enterprise.shareholder.constant.Constant.BUSINESS_TYPE_XP);
//        mv.addObject("approvalEndTime", new Date());
//        mv.addObject("processList", processList);
//        mv.addObject("businessId", declareInfoDto.getId());
//        mv.addObject("categoryId", categoryId);
//        mv.addObject("bizNameAlias", declareInfoDto.getDeclareName());
//    }
//
//    //TODO demand 2097 byzhangjiaming 2017-07-04 start
//    /**
//     * 勾选公告时文件模板替换
//     * @param declareId
//     * @param templateId
//     * @param templateName
//     * @param deNo
//     * 
//     * @return
//     */
//    @RequestMapping(value = "replaceTemplateContent", method = RequestMethod.POST)
//    @ResponseBody
//    public JsonResponse<String> replaceTemplateContent(String declareId, String templateId, String templateName, String deNo) throws Exception {
//    	JsonResponse<String> response = new JsonResponse<String>();
//    	String fromCloud = "false";
//        declareService.replaceTemplateContent(declareId, templateId, templateName, deNo, fromCloud);
//    	response.setResult(new String());
//    	return response;
//    }
//    //TODO demand 2097 byzhangjiaming 2017-07-04 start
//    /**
//     * 信息披露审核预览
//     * 
//     * @param id
//     *            信披id
//     * @return ModelAndView
//     */
//    @RequestMapping(value = "approvalPreView")
//    public ModelAndView approvalPreView(String id, String flag, String approvalId, 
//    		String apprNote, String endTime, String procInstId, String taskId, String opCode) {
//        ModelAndView mv = new ModelAndView("declare/approvalPreView");
//        DeclareInfoDto declareInfoDtoSaved = declareService.getDeclareInfo(id);
//        if(StringUtils.isNotEmpty(approvalId)){
//            declareInfoDtoSaved.setFileList(declareService.getDeclareFile(id, approvalId));
//        }else{
//            declareInfoDtoSaved.setFileList(declareService.getDeclareFile(id));
//        }
//        mv.addObject("infoDto", declareInfoDtoSaved);
//        mv.addObject("approveList", declareService.getApproveList(id));
//        mv.addObject("flag", flag);
//        // 需求2449 审批工作流共通化 by weishisheng 2018/1/9 start
//        mv.addObject("apprNote", apprNote);
//        if (StringUtils.isNotEmpty(endTime)) {
//            mv.addObject("endTime", DateUtil.getDate(endTime, DateUtil.YYYY_MM_DD));
//        }
//        mv.addObject("procInstId", procInstId);
//        mv.addObject("taskId",taskId);
//        mv.addObject("opCode",opCode);
////        if (StringUtils.isEmpty(procInstId)) {
//            // TODO 需求4050
//            List<WorkflowApprovalHistoryDto> apprHistoryList = workflowService.getApprHistoryList(id, declareInfoDtoSaved.getDeclareName(), procInstId);
//            mv.addObject("apprHistoryList", apprHistoryList);
////        }
//        // 需求2449 审批工作流共通化 by weishisheng 2018/1/9 end
//        return mv;
//    }
//
//    /**
//     * 创建信息披露step3
//     * 
//     * @param declareInfoDto
//     *            信披记录
//     * @return ModelAndView
//     */
//    @RequestMapping(value = "createDeclareThree", method = RequestMethod.POST)
//    public ModelAndView createDeclareThree(DeclareInfoDto declareInfoDto) {
//        ModelAndView mv = new ModelAndView("declare/createDeclareThree");
//        DeclareInfoDto infoDto = declareService.getDeclareInfo(declareInfoDto.getId());
//        infoDto.setMeetingIdFlag(declareInfoDto.getMeetingIdFlag());
//        infoDto.setFileList(declareService.getDeclareFile(declareInfoDto.getId()));
//        // 需求2449 审批工作流共通化 by weishisheng 2018/1/9 start
//        setApprovalParameter(declareInfoDto, mv);
//        // 需求2449 审批工作流共通化 by weishisheng 2018/1/9 end
//        mv.addObject("infoDto", infoDto);
//        mv.addObject("disclosureDateHis", declareService.getDisclosureDateList(declareInfoDto.getId()));
//        mv.addObject("approveList", declareService.getApproveList(declareInfoDto.getId()));
//        return mv;
//
//    }
//
//    /**
//     * 保存信息披露
//     * 
//     * @param declareInfoDto
//     */
//    @RequestMapping(value = "saveDeclareInfo", method = RequestMethod.POST)
//    public ModelAndView saveDeclareInfo(DeclareInfoDto declareInfoDto,String itemTaskId) {
//        DeclareInfo declareInfo = declareService.saveDeclareInfo(declareInfoDto,itemTaskId);
//        ModelAndView mv = new ModelAndView("declare/createDeclareTwo");
//        // 需求396
//        String approveFiles = systemManagerService.getApproveFileSuffix();
//        // 需求396
//        mv.addObject("approveFiles", approveFiles);
//        DeclareInfoDto declareInfoDtoSaved = declareService.getDeclareInfo(declareInfo.getId());
//        if (StringUtils.isNotEmpty(declareInfoDto.getMeetingIdFlag())) {
//            declareInfoDtoSaved.setMeetingIdFlag(declareInfoDto.getMeetingIdFlag());
//            declareInfoDtoSaved.setPageFlag(declareInfoDto.getPageFlag());
//        }
//        declareInfoDtoSaved.setFileList(declareService.getDeclareFile(declareInfo.getId()));
//        mv.addObject("infoDto", declareInfoDtoSaved);
//        mv.addObject("disclosureDateHis", declareService.getDisclosureDateList(declareInfoDto.getId()));
//        mv.addObject("saveSuccess", "success");
//        mv.addObject("lawViewPath", officialViewPath);
//        mv.addObject("stockBoard",
//                StockBoard.getStockBlock(getUserInfo().getInfo().get(Global.COMPANY_CODE).toString()));
//        return mv;
//    }
//
//    /**
//     * 保存信息披露并迁移第三步
//     * 
//     * @param declareInfoDto
//     */
//    @RequestMapping(value = "saveAndMoveThree", method = RequestMethod.POST)
//    public ModelAndView saveAndMoveThree(DeclareInfoDto declareInfoDto,String itemTaskId) {
//        DeclareInfo declareInfo = declareService.saveDeclareInfo(declareInfoDto,itemTaskId);
//        ModelAndView mv = new ModelAndView("declare/createDeclareThree");
//        DeclareInfoDto declareInfoDtoSaved = declareService.getDeclareInfo(declareInfo.getId());
//        if (StringUtils.isNotEmpty(declareInfoDto.getMeetingIdFlag())) {
//            declareInfoDtoSaved.setMeetingIdFlag(declareInfoDto.getMeetingIdFlag());
//            declareInfoDtoSaved.setPageFlag(declareInfoDto.getPageFlag());
//        }
//        declareInfoDtoSaved.setFileList(declareService.getDeclareFile(declareInfo.getId()));
//        // 需求2449 审批工作流共通化 by weishisheng 2018/1/9 start
//        setApprovalParameter(declareInfoDto, mv);
//        // 需求2449 审批工作流共通化 by weishisheng 2018/1/9 end
//        mv.addObject("infoDto", declareInfoDtoSaved);
//        mv.addObject("saveSuccess", "success");
//        mv.addObject("approveList", declareService.getApproveList(declareInfoDto.getId()));
//        mv.addObject("disclosureDateHis", declareService.getDisclosureDateList(declareInfoDto.getId()));
//        mv.addObject("stockBoard",
//                StockBoard.getStockBlock(getUserInfo().getInfo().get(Global.COMPANY_CODE).toString()));
//        return mv;
//    }
//
//    /**
//     * 保存或录入下一条信披
//     * @param declareInfoDto
//     * @return
//     */
//    @RequestMapping(value = "saveOrNewLogging",method = RequestMethod.POST)
//    public ModelAndView saveOrNewLogging(DeclareInfoDto declareInfoDto){
//        ModelAndView mv = new ModelAndView("declare/loggingDeclare");
//        DeclareInfo declareInfo = declareService.saveDeclareInfo(declareInfoDto,null);
//        DeclareInfoDto declareInfoDtoSaved;
//        if("1".equals(declareInfoDto.getPageFlag())){
//            declareInfoDtoSaved = declareService.getDeclareInfo(declareInfo.getId());
//            if (StringUtils.isNotEmpty(declareInfoDto.getMeetingIdFlag())) {
//                declareInfoDtoSaved.setMeetingIdFlag(declareInfoDto.getMeetingIdFlag());
//                declareInfoDtoSaved.setPageFlag(declareInfoDto.getPageFlag());
//            }
//            declareInfoDtoSaved.setFileList(declareService.getDeclareFile(declareInfo.getId()));
//            mv.addObject("saveSuccess", "success");
//        }else{
//            declareInfoDtoSaved = new DeclareInfoDto();
//            declareInfoDtoSaved.setMeetingIdFlag(declareInfoDto.getMeetingIdFlag());
//            declareInfoDtoSaved.setMaxDeclareNo(declareService.getMaxDeclareNo());
//        }
//        String approveFiles = systemManagerService.getApproveFileSuffix();
//        mv.addObject("approveFiles", approveFiles);
//        mv.addObject("infoDto",declareInfoDtoSaved);
//        return mv;
//    }
//
//    /**
//     * 保存信息披露并迁移
//     * 
//     * @param declareInfoDto
//     */
//    @RequestMapping(value = "saveAndLeaveDeclare", method = RequestMethod.POST)
//    @ResponseBody
//    public JsonResponse<String> saveAndLeaveDeclare(DeclareInfoDto declareInfoDto) {
//        JsonResponse<String> jsons = new JsonResponse<String>();
//        declareService.saveDeclareInfo(declareInfoDto,null);
//        jsons.setResult(new String());
//        return jsons;
//    }
//
//    /**
//     * 根据业务类型取得模板文件
//     * 
//     * @param typeId
//     *            业务id
//     * @param typeName
//     *            业务名称
//     * @param declareTypeNo
//     *            业务分类编号
//     * @return 模板文件列表
//     */
//    @RequestMapping(value = "getTempalteFile", method = RequestMethod.POST)
//    @ResponseBody
//    public JsonResponse<List<DeclareFileDto>> getTempalteFile(String typeId, String typeName, String declareTypeNo) {
//        List<DeclareFileDto> templareList = declareService.getTempalteFile(typeId, typeName, declareTypeNo);
//        JsonResponse<List<DeclareFileDto>> jsonRes = new JsonResponse<List<DeclareFileDto>>();
//        jsonRes.setResult(templareList);
//        return jsonRes;
//    }
//
//    /**
//     * 根据公告文件生成新的公告文件
//     * 
//     * @param id
//     *            文件id
//     */
//    @RequestMapping(value = "createNoticeOne", method = RequestMethod.POST)
//    @ResponseBody
//    public JsonResponse<DeclareFileDto> createNotice(String id) {
//        DeclareFileDto declareFileDto = declareService.createNotice(id);
//        JsonResponse<DeclareFileDto> jsonRes = new JsonResponse<DeclareFileDto>();
//        jsonRes.setResult(declareFileDto);
//        return jsonRes;
//    }
//
//    @RequestMapping(value = "createNotice")
//    @ResponseBody
//    public JsonResponse<List<DeclareFileDto>> createNotice(@RequestParam("infoFileList") List<String> infoFileList) {
//        List<DeclareFileDto> declareFileDtoList = declareService.createNotice(infoFileList);
//        JsonResponse<List<DeclareFileDto>> jsonRes = new JsonResponse<List<DeclareFileDto>>();
//        jsonRes.setResult(declareFileDtoList);
//        return jsonRes;
//    }
//
//    /**
//     * 提交云平台审核
//     * 
//     * @param id
//     *            信披id
//     * 
//     */
//    @RequestMapping(value = "declareToCloud", method = RequestMethod.POST)
//    @ResponseBody
//    public JsonResponse<String> declareToCloud(String id) {
//        String status = declareService.declareToCloud(id);
//        JsonResponse<String> jsonRes = new JsonResponse<String>();
//        jsonRes.setResult(status);
//        return jsonRes;
//    }
//
//    /**
//     * 最近公告获取
//     * 
//     * @param queryField
//     *            公告名模糊查询
//     * @return
//     */
//    @SuppressWarnings("unchecked")
//    @RequestMapping(value = "ajaxQueryNotice", method = RequestMethod.POST)
//    @ResponseBody
//    public Map<String, Object> ajaxQueryNotice(String queryField) {
//        QueryNoticeDto queryNoticeDto = new QueryNoticeDto();
//        queryNoticeDto.setTypeIdList((List<String>) getRequest().getSession().getAttribute("typeIdList"));
//        queryNoticeDto.setQueryField(queryField);
//        Map<String, Object> ou = super.commonQuery(
//                "com.stock.capital.enterprise.declare.dao.DeclareInfoBizMapper.getNoticeList", queryNoticeDto);
//        return ou;
//
//    }
//
//    /**
//     * 最近公告获取
//     * 
//     * @param declareTypeDto
//     * @return
//     */
//    @RequestMapping(value = "ajaxQueryNoticeOne", method = RequestMethod.POST)
//    @ResponseBody
//    public Map<String, Object> ajaxQueryNoticeOne(DeclareTypeDto declareTypeDto) {
////        QueryNoticeDto queryNoticeDto = new QueryNoticeDto();
//        
//        QueryInfo<DeclareTypeDto> queryInfo = commonSearch(declareTypeDto);
//        declareTypeDto = queryInfo.getCondition();
//
//        if (declareTypeDto.getIsType() != null) {
//            // 查询条件
//            declareTypeDto.setQueryField(declareTypeDto.getSearchInfo());
//            return super.commonQuery("com.stock.capital.enterprise.declare.dao.DeclareInfoBizMapper.getAllNoticeList",
//                    declareTypeDto);
//        } else {
//            // 业务类型设置
//            if (declareTypeDto.getTypeIds() == null) {
//                Map<String, Object> map = Maps.newHashMap();
//                map.put("recordsTotal", 0);
//                map.put("recordsFiltered", 0);
//                map.put("data", "");
//                return map;
//            }
//            declareTypeDto.setTypeIdList(declareTypeDto.getTypeIds());
//            // 查询条件
//            declareTypeDto.setQueryField(declareTypeDto.getSearchInfo());
//            return super.commonQuery("com.stock.capital.enterprise.declare.dao.DeclareInfoBizMapper.getNoticeList",
//                    declareTypeDto);
//        }
//
//    }
//
//    /**
//     * 编辑信息披露
//     * 
//     * @param id
//     *            信披id
//     * @return
//     */
//    @RequestMapping(value = "editDeclareInfo")
//    public ModelAndView editDeclareInfo(String id, String meetingIdFlag,
//            @RequestParam(value = "pageFlag", required = false, defaultValue = "") String pageFlag) {
//        // 需求396
//        String approveFiles = systemManagerService.getApproveFileSuffix();
//        ModelAndView mv = new ModelAndView("declare/createDeclareTwo");
//        // 需求396
//        mv.addObject("approveFiles", approveFiles);
//        DeclareInfoDto declareInfoDtoSaved;
//        if (StringUtils.isNotEmpty(id)) {
//            declareInfoDtoSaved = declareService.getDeclareInfo(id);
//            declareInfoDtoSaved.setFileList(declareService.getDeclareFile(id));
//            //demand2097 byzhangjiaming start
//            declareService.replaceContent(id);
//            //demand2097 byzhangjiaming end
//        } else {
//            declareInfoDtoSaved = (DeclareInfoDto) WebUtil.getCurrentRequest().getSession()
//                    .getAttribute("reDeclareInfoDto");
//        }
//        if (StringUtils.isNotEmpty(meetingIdFlag)) {
//            declareInfoDtoSaved.setMeetingIdFlag(meetingIdFlag);
//        }
//        if (StringUtils.isNotEmpty(pageFlag)) {
//            declareInfoDtoSaved.setPageFlag(pageFlag);
//        }
//        mv.addObject("infoDto", declareInfoDtoSaved);
//        mv.addObject("disclosureDateHis", declareService.getDisclosureDateList(id));
//        mv.addObject("lawViewPath", officialViewPath);
//        mv.addObject("stockBoard",
//                StockBoard.getStockBlock(getUserInfo().getInfo().get(Global.COMPANY_CODE).toString()));
//        return mv;
//    }
//
//    /**
//     * pop会议选择
//     * 
//     * @return ModelAndView
//     */
//    @RequestMapping(value = "popMeeting", method = RequestMethod.POST)
//    public ModelAndView popMeeting(String meetingId) {
//        ModelAndView mv = new ModelAndView("declare/popMeeting");
//        mv.addObject("meetingDto", declareService.getMeetingMap(meetingId));
//        return mv;
//    }
//
//    /**
//     * pop选择关联公告类别
//     * @return ModelAndView
//     */
//    @RequestMapping(value = "popDeclareType",method = RequestMethod.POST)
//    public ModelAndView popDeclareType(String typeListStr){
//        ModelAndView mv = new ModelAndView("declare/popDeclareType");
//        if(StringUtils.isNotEmpty(typeListStr)){
//            String[] typeList = typeListStr.split(",");
//            Map<String,String> typeMap = Maps.newLinkedHashMap();
//            for (String typeStr : typeList){
//                String[] typeDto = typeStr.split("-");
//                typeMap.put(typeStr,typeDto[0]);
//            }
//            mv.addObject("typeMap",typeMap);
//        }
//        mv.addObject("declareTypeList", JsonUtil.toJson(declareService.getDeclareType(StockBoard.getStockBlock(getUserInfo().getInfo().get(Global.COMPANY_CODE)
//        		.toString()))));
//        return mv;
//    }
//
//    /**
//     * pop导入历史信披文件
//     * 
//     * @return ModelAndView
//     */
//    @RequestMapping(value = "popImportInfoFile", method = RequestMethod.POST)
//    public ModelAndView popImportInfoFile(@RequestParam("typeIdList") List<String> typeIdList) {
//        ModelAndView mv = new ModelAndView("declare/popImportInfoFile");
//        mv.addObject("typeIdList", typeIdList);
//        return mv;
//    }
//
//    /**
//     * 公告库
//     * 
//     * @return ModelAndView
//     * 
//     */
//    @RequestMapping(value = "/popLibrary")
//    public ModelAndView popLibrary() {
//        ModelAndView mv = new ModelAndView("declare/searchLibrary");
//        List<String> list = Lists.newArrayList();
//        String industries = "";
//        NoticeSearchDto noticeSearchDto = declareService.getNoticeSearchDto();
//        Map<String, String> industry = declareService.getIndustryNoAndName(getUserInfo().getInfo().get(Global.COMPANY_ID).toString());
//        if(industry != null){
//        	noticeSearchDto.setIndustrySelectShow(industry.get("industryCode"));
//         	if(StringUtils.isNotEmpty(industry.get("industryCode"))){
//         		list = Splitter.on(",").splitToList(industry.get("industryCode"));
//         		if(list != null){
//             		industries = list.get(0);
//             	}
//         	}
// 	
//     	mv.addObject("industry", industries);
//     	mv.addObject("industryName", industry.get("industryName"));}
//        mv.addObject("libraryDto", noticeSearchDto);
//        SearchAnnounceDto searchAnnounceDto = announcementService.getSearchDto("");
//        mv.addObject("searchAnnounceDto", searchAnnounceDto);
//    	// lanyuxin 2017-08-22 begin
//        //所在地区
//     	mv.addObject("provincesList", JsonUtil.toJsonNoNull(commonService.getProvincesList()));
//     	// lanyuxin 2017-08-22 end
//        // 所属行业
//     	mv.addObject("industryList", JsonUtil.toJsonNoNull(commonService.getIndustryList()));
//        // 所属板块
//        mv.addObject("boardSelectList", JsonUtil.toJsonNoNull(searchAnnounceDto.getBoardSelectMul()));
//        // 公告类型
//        mv.addObject("typeSelectList", JsonUtil.toJsonNoNull(searchAnnounceDto.getTypeSelectMul()));
//        return mv;
//    }
//
//    @RequestMapping(value = "getDisclosureDetail", method = RequestMethod.POST)
//    public ModelAndView getDisclosureDetail(String declareId){
//        ModelAndView mv = new ModelAndView("declare/disclosureDetail");
//        mv.addObject("disclosureDetail",declareService.getDisclosureDeatail(declareId));
//        return mv;
//    }
//
//    /**
//     * pop编辑心得
//     * 
//     * @return ModelAndView
//     */
//    @RequestMapping(value = "popEditName", method = RequestMethod.POST)
//    public ModelAndView popEditIdea(String content, String typeId, String ideaId, String privateVal) {
//        ModelAndView mv = new ModelAndView("declare/popEditIdea");
//        mv.addObject("content", content);
//        mv.addObject("typeId", typeId);
//        mv.addObject("ideaId", ideaId);
//        mv.addObject("privateVal", privateVal);
//        return mv;
//    }
//
//    /**
//     * pop信息披露Item
//     * 
//     * @return ModelAndView
//     */
//    @RequestMapping(value = "popDeclareItem", method = RequestMethod.POST)
//    public ModelAndView popDeclareItem(String id) {
//        ModelAndView mv = new ModelAndView("declare/popDeclareItem");
//        mv.addObject("declareItem", declareService.getByPrimaryKey(id));
//        mv.addObject("approveList", declareService.getApproveList(id));
//        return mv;
//    }
//
//    /**
//     * pop公告文件修改记录
//     * 
//     * @return ModelAndView
//     */
//    @RequestMapping(value = "popFileRecord", method = RequestMethod.POST)
//    public ModelAndView popFileRecord(String id) {
//        ModelAndView mv = new ModelAndView("declare/fileRecord");
//        mv.addObject("declareFileId", id);
//        return mv;
//    }
//
//    /**
//     * 查询选择会议
//     */
//    @RequestMapping(value = "ajaxQueryMeetingInit")
//    @ResponseBody
//    public Map<String, Object> ajaxQueryMeetingInit(MeetingDto meetingDto, int draw) {
//        meetingDto.setCompanyId(getUserInfo().getInfo().get(Global.COMPANY_ID).toString());
//        List<MeetingDto> list = declareService.getMeetingInit(meetingDto);
//        Map<String, Object> map = Maps.newHashMap();
//        map.put("draw", draw);
//        map.put("recordsTotal", list.size());
//        map.put("recordsFiltered", list.size());
//        map.put("data", list);
//        return map;
//    }
//
//    /**
//     * 查询选择会议
//     */
//    @RequestMapping(value = "ajaxQueryMeeting")
//    @ResponseBody
//    public Map<String, Object> ajaxQueryMeeting(MeetingDto meetingDto, int draw) {
//        meetingDto.setCompanyId(getUserInfo().getInfo().get(Global.COMPANY_ID).toString());
//        List<MeetingDto> list = declareService.getMeeting(meetingDto);
//        Map<String, Object> map = Maps.newHashMap();
//        map.put("draw", draw);
//        map.put("recordsTotal", list.size());
//        map.put("recordsFiltered", list.size());
//        map.put("data", list);
//        return map;
//    }
//
//    /**
//     * 查询选择会议
//     */
//    @RequestMapping(value = "ajaxMeetingFile")
//    @ResponseBody
//    public Map<String, Object> ajaxMeetingFile(MeetingDto meetingDto, int draw) {
//        meetingDto.setCompanyId(getUserInfo().getInfo().get(Global.COMPANY_ID).toString());
//        if (meetingDto.getMeetingIdList() == null) {
//            List<String> list = new ArrayList<String>();
//            list.add("");
//            meetingDto.setMeetingIdList(list);
//        }
//        List<MeetingFileDto> list = declareService.getMeetingFile(meetingDto);
//        Map<String, Object> map = Maps.newHashMap();
//        map.put("draw", draw);
//        map.put("recordsTotal", list.size());
//        map.put("recordsFiltered", list.size());
//        map.put("data", list);
//        return map;
//    }
//
//    /**
//     * 查询信息披露历史
//     */
//    @RequestMapping(value = "ajaxDeclareHistory")
//    @ResponseBody
//    public Map<String, Object> ajaxDeclareHistory(DeclareIndexDto declareIndexDto) {
//        return super.commonQuery("com.stock.capital.enterprise.declare.dao.DeclareInfoBizMapper.getDeclareInfos",
//                declareIndexDto);
//    }
//
//    /**
//     * 查询所有的信息披露情报
//     */
//    @RequestMapping(value = "ajaxAllDeclare")
//    @ResponseBody
//    public Map<String, Object> ajaxAllDeclare(DeclareIndexDto declareIndexDto) {
//        return super.commonQuery("com.stock.capital.enterprise.declare.dao.DeclareInfoBizMapper.getAllDeclareInfos",
//                declareIndexDto);
//    }
//    /**
//     * 删除信息披露历史
//     * 
//     * @param id
//     *            信披id
//     */
//    @RequestMapping(value = "delHisDeclare", method = RequestMethod.POST)
//    @ResponseBody
//    public JsonResponse<Integer> delHisDeclare(String id) {
//        int delStatus = declareService.delHisDeclare(id);
//        JsonResponse<Integer> jsonRes = new JsonResponse<Integer>();
//        jsonRes.setResult(delStatus);
//        return jsonRes;
//    }
//
//    /**
//     * 公告小助手
//     * 
//     * @return 业务分类树数据
//     */
//    @RequestMapping(value = "noticeHelper", method = RequestMethod.POST)
//    public ModelAndView noticeHelper(String stockBoard) {
//        ModelAndView mv = new ModelAndView("declare/noticeHelper");
//        String ipoFlag = getUserInfo().getInfo().get(Global.IPO_FLAG).toString();
//        //ipoFlag为2时:监管机构版本;ipoFlag为3时:中介机构版本
//        if("2".equals(ipoFlag) || "3".equals(ipoFlag)){
//        	if(StringUtils.isEmpty(stockBoard)){
//        		stockBoard = "04";
//        	}
//        }else{
//        	stockBoard = StockBoard.getStockBlock(getUserInfo().getInfo().get(Global.COMPANY_CODE).toString());
//        }
//        mv.addObject(
//                "declareTypeList",
//                JsonUtil.toJson(declareService.getDeclareType(stockBoard)));
//        DeclareTypeDto declareTypeDto = new DeclareTypeDto();
//        declareTypeDto.setMeetingIdFlag("1");
//        mv.addObject("infoDto", declareTypeDto);
//        mv.addObject("lawViewPath", officialViewPath);
//        mv.addObject("stockBoard", stockBoard);
//    	mv.addObject("ipoFlag", ipoFlag);
//        return mv;
//        
//    }
//
//    /**
//     * 公告小助手数据
//     * 
//     * @param typeId
//     *            业务分类id
//     * @param typeName
//     *            业务分类名
//     * @return
//     */
//    @RequestMapping(value = "getHelperData", method = RequestMethod.POST)
//    @ResponseBody
//    public JsonResponse<NoticeHelperDto> getHelperData(String typeId, String typeName) {
//        NoticeHelperDto dto = declareService.getHelperData(typeId, typeName);
//        JsonResponse<NoticeHelperDto> response = new JsonResponse<NoticeHelperDto>();
//        response.setResult(dto);
//        return response;
//    }
//
//    /**
//     * 获取经验总结数据
//     * 
//     * @param typeId
//     *            业务分类id
//     * @param privateVal
//     *            经验是否公开标志 all：获取所有 mine：获取自己经验 other：获取他人经验
//     * @return
//     */
//    @RequestMapping(value = "getExperienceData", method = RequestMethod.POST)
//    @ResponseBody
//    public JsonResponse<Map<String, Object>> getExperienceData(String typeId, String privateVal) {
//        JsonResponse<Map<String, Object>> response = new JsonResponse<Map<String, Object>>();
//        // 获取经验总结
//        List<DeclareIdea> lst = declareService.getExperienceData(typeId, privateVal);
//        // 获取人员ID
//        String personId = getUserInfo().getInfo().get(Global.PERSON_ID).toString();
//        Map<String, Object> result = Maps.newHashMap();
//        result.put("ideas", lst);
//        result.put("personId", personId);
//        response.setResult(result);
//        return response;
//    }
//
//    /**
//     * 删除未披露信披
//     * 
//     * @param id
//     *            业务分类id
//     * @return
//     * @throws ParseException
//     */
//    @RequestMapping(value = "delUnDeclareInfo", method = RequestMethod.POST)
//    public ModelAndView delUnDeclareInfo(String id) throws ParseException {
//        declareService.delUnDeclareInfo(id);
//        ModelAndView mv = new ModelAndView("declare/declareIndex");
//        List<DeclareInfoSumDto> disList = declareService.getDeclareInfoList();
//        mv.addObject("disList", disList);
//        DeclareIndexDto did = new DeclareIndexDto();
//        did.setStockBoard(StockBoard.getStockBlock(getUserInfo().getInfo().get(Global.COMPANY_CODE).toString()));
//        mv.addObject("infoDto", did);
//        return mv;
//    }
//
//    /**
//     * 文件修改记录
//     * 
//     * @param
//     * @return
//     */
//    @RequestMapping(value = "ajaxFileRecord", method = RequestMethod.POST)
//    @ResponseBody
//    public Map<String, Object> ajaxFileRecord(String id) {
//        return super.commonQuery("com.stock.capital.enterprise.declare.dao.DeclareInfoBizMapper.getFileRecord", id);
//    }
//
//    // 2016/09/02 需求295 by wangchao START
//    /**
//     * 查看文件修改记录，最后三条
//     * 
//     * @param
//     * @return
//     */
//    @RequestMapping(value = "ajaxFileRecordLastThree", method = RequestMethod.POST)
//    @ResponseBody
//    public JsonResponse<String> ajaxFileRecordLastThree(String fileId) {
//        JsonResponse<String> jsonRes = new JsonResponse<String>();
//        jsonRes.setResult(JsonUtil.toJson(declareService.getFileRecord(fileId)));
//        return jsonRes;
//    }
//
//    // 2016/09/02 需求295 by wangchao END
//    // 2016/12/06 需求1083 by wangchao START
//    /**
//     * 查看信披披露
//     *
//     * @return
//     */
//    @RequestMapping(value = "declareRead", method = RequestMethod.POST)
//    public ModelAndView declareRead(String id, String approvalId, String meetingIdFlag, String pageFlag) {
//        ModelAndView mv = new ModelAndView("declare/declareRead");
//        DeclareInfoDto declareInfoDtoSaved = declareService.getDeclareInfo(id);
//        if(StringUtils.isNotEmpty(approvalId)){
//            declareInfoDtoSaved.setFileList(declareService.getDeclareFile(id, approvalId));
//        }else{
//            declareInfoDtoSaved.setFileList(declareService.getDeclareFile(id));
//        }
//        if (StringUtils.isNotEmpty(meetingIdFlag)) {
//            declareInfoDtoSaved.setMeetingIdFlag(meetingIdFlag);
//        }
//        if (StringUtils.isNotEmpty(pageFlag)) {
//            declareInfoDtoSaved.setPageFlag(pageFlag);
//        }
//        mv.addObject("infoDto", declareInfoDtoSaved);
//        mv.addObject("disclosureDateHis", declareService.getDisclosureDateList(id));
//        mv.addObject("approveList", declareService.getApproveList(id));
//        mv.addObject("stockBoard",
//                StockBoard.getStockBlock(getUserInfo().getInfo().get(Global.COMPANY_CODE).toString()));
//        
//        // 需求2449 审批工作流共通化 by weishisheng 2018/1/29 start
//        // TODO 需求4050
//        List<WorkflowApprovalHistoryDto> apprHistoryList = workflowService.getApprHistoryList(declareInfoDtoSaved.getId(), declareInfoDtoSaved.getDeclareName(), "");
//        mv.addObject("approveList", apprHistoryList);
//        // 需求2449 审批工作流共通化 by weishisheng 2018/1/29 end
//        return mv;
//    }
//    /**
//     * 查看信披披露中，下载全部文件
//     * 
//     * @param
//     * @throws Exception
//     * 
//     */
//    @RequestMapping(value = "exportAllFile")
//    public ModelAndView exportAllFile(String selIds, String name) throws Exception {
//        ModelAndView mv = new ModelAndView();
//        mv.setView(new DownloadView());
//        // 从文件服务器下载文件
//        InputStream is = declareService.downSelFile(selIds);
//        mv.addObject(DownloadView.EXPORT_FILE, is);
//        mv.addObject(DownloadView.EXPORT_FILE_NAME, name + ".zip");
//        mv.addObject(DownloadView.EXPORT_FILE_TYPE, MediaType.ZIP);
//        mv.addObject(DownloadView.EXPORT_FILE_SIZE, is.available());
//        return mv;
//    }
////    @RequestMapping(value = "exportAllFile")
////    public ModelAndView exportAllFile(String id, String name) throws IOException {
////        ModelAndView mv = new ModelAndView();
////        mv.setView(new DownloadView());
////        // 从文件服务器下载文件
////        InputStream downFile = declareService.exportAllFile(id);
////        if (downFile != null) {
////            mv.addObject(DownloadView.EXPORT_FILE, downFile);
////            mv.addObject(DownloadView.EXPORT_FILE_NAME, name + ".zip");
////            mv.addObject(DownloadView.EXPORT_FILE_TYPE, MediaType.ZIP);
////        } else {
////            String content = "没有公告文件";
////            downFile = new ByteArrayInputStream(content.getBytes());
////            mv.addObject(DownloadView.EXPORT_FILE, downFile);
////            mv.addObject(DownloadView.EXPORT_FILE_NAME, content + ".txt");
////            mv.addObject(DownloadView.EXPORT_FILE_TYPE, DownloadView.FILE_TYPE.STREAM);
////        }
////        mv.addObject(DownloadView.EXPORT_FILE_SIZE, downFile.available());
////        return mv;
////    }
//    // 2016/12/06 需求1083 by wangchao END
//    /**
//     * 
//     * 返回信披第一步
//     * 
//     * @param declareInfoDto
//     *            信披信息
//     * @return
//     */
//    @RequestMapping(value = "backToStepOne", method = RequestMethod.POST)
//    public ModelAndView backToStepOne(DeclareInfoDto declareInfoDto) {
//        ModelAndView mv = new ModelAndView("declare/createDeclareOne");
//        mv.addObject(
//                "declareTypeList",
//                JsonUtil.toJson(declareService.getDeclareType(StockBoard.getStockBlock(getUserInfo().getInfo().get(Global.COMPANY_CODE)
//                		.toString()))));
//        DeclareTypeDto declareTypeDto = new DeclareTypeDto();
//        declareTypeDto.setSessionFlag("1");
//        if (StringUtils.isNotEmpty(declareInfoDto.getMeetingIdFlag())) {
//            declareTypeDto.setMeetingIdFlag(declareInfoDto.getMeetingIdFlag());
//        }
//        mv.addObject("infoDto", declareTypeDto);
//        mv.addObject("declareInfoDto", declareService.getStepOneInfo(declareInfoDto));
//        return mv;
//    }
//
//    /**
//     * 设置对标公司初始化
//     */
//    @RequestMapping(value = "popSetToCom")
//    public ModelAndView benchmarkCompany() {
//        ModelAndView mv = new ModelAndView("home/benchmarkCompany");
//        mv.addObject("benchmarkCompanyList", homeService.getBenchmarkCompany());
//        return mv;
//    }
//
//    /**
//     * 公告库
//     * 
//     * @return
//     */
//    @RequestMapping(value = "searchLibrary")
//    @ResponseBody
//    //TODO xing.j
//    public Map<String, Object> searchLibrary(NoticeSearchDto noticeSearchDto) {
//        Map<String, Object> map = Maps.newHashMap();
//        Map<String, String> condition = Maps.newHashMap();
//        String conditionStr = StringUtils.EMPTY;
//        if (StringUtils.isNotEmpty(noticeSearchDto.getStockcode())) {
//            conditionStr = "declare_code_t:*" + noticeSearchDto.getStockcode() + "*";
//        }
//        if (StringUtils.isNotEmpty(noticeSearchDto.getTitle())) {
//            if (StringUtils.isNotEmpty(conditionStr)) {
//                conditionStr += " AND "
//                        + SolrSearchUtil.parseStringKeyWords("declare_title_t", noticeSearchDto.getTitle(), null);
//            } else {
//                conditionStr = SolrSearchUtil.parseStringKeyWords("declare_title_t", noticeSearchDto.getTitle(), null);
//            }
//        }
//        
//        //Bug6980 xing.j 2017/12/03 start
//    	if (StringUtils.isNotEmpty(noticeSearchDto.getDateRange())) {
//			// 起始日期
//			String[] dateRange = noticeSearchDto.getDateRange().split(" 至 ");
//			Date startDate = DateUtil.getDate(dateRange[0], DateUtil.YYYY_MM_DD);
//			Date endDate = DateUtil.getDate(dateRange[1], DateUtil.YYYY_MM_DD);
//			noticeSearchDto.setStartDate(startDate);
//			noticeSearchDto.setEndDate(endDate);
//		}
//    	//Bug6980 xing.j 2017/12/03 end
//
//    	String publishDate = SolrSearchUtil.parseDateKeyWords("declare_publish_date_dt",
//                noticeSearchDto.getStartDate(), noticeSearchDto.getEndDate());
//        if (StringUtils.isEmpty(conditionStr)) {
//            conditionStr = publishDate;
//        } else {
//            conditionStr = conditionStr + " AND " + publishDate;
//        }
//
//        // 处理板块
////        conditionStr = announcementService.transformTreeDtoToString(conditionStr,
////                noticeSearchDto.getBoardSelectMul(), "declare_parent_plate_txt", "declare_plate_txt");
//        conditionStr = SolrSearchUtil.transArrayStrToConditionStr(conditionStr, noticeSearchDto.getBoardSelectShow(), "declare_plate_txt");
//        
//        //TODO
//        // 处理分类
////        conditionStr = announcementService.transformTreeDtoToString(conditionStr,
////                noticeSearchDto.getTypeSelectMul(), "declare_p_types_txt", "declare_types_txt");
//        //bug7045 start
//        conditionStr = SolrSearchUtil.transArrayStrToConditionStr(conditionStr, noticeSearchDto.getTypeSelectShow(), "declare_types_txt");
//      //bug7045 end
//        //lanyuxin 2017-08-18 begin
//        // 处理所在地区
//        conditionStr = SolrSearchUtil.transArrayStrToConditionStr(conditionStr, noticeSearchDto.getAreasSelectShow(), "declare_region_txt");
//        // 处理所属行业
//        conditionStr = SolrSearchUtil.transformValueToString(conditionStr, noticeSearchDto.getIndustrySelectShow(), "declare_industries_txt", false, false, false);
//        //lanyuxin 2017-08-18 end
//        String url = apiBaseUrl + "infoSerIndex/searchLibrary";
//        condition.put(Constant.SEARCH_CONDIATION, conditionStr);
//        condition.put(Constant.SEARCH_HIGHTLIGHT_FIELD, "declare_title_t");
//        QueryInfo<Map<String, String>> queryInfo = commonSearch(condition);
//        if (StringUtils.isEmpty(queryInfo.getOrderByName())) {
//            queryInfo.setOrderByName("declare_publish_date_dt");
//            queryInfo.setOrderByOrder("desc");
//        }
//        String draw = getRequest().getParameter("draw");
//        // String toPageState = getRequest().getParameter("toPageState");
//        // if (StringUtils.isNotEmpty(toPageState)) {
//        // queryInfo = (QueryInfo) getRequest().getSession().getAttribute(getRequest().getRequestURI());
//        //
//        // }else{
//        // queryInfo = new QueryInfo();
//        // queryInfo.setCondition(noticeSearchDto);
//        // getRequest().getSession().setAttribute(getRequest().getRequestURI(), queryInfo);
//        // }
//        // String pageSize = getRequest().getParameter("length");
//        // String startRow = getRequest().getParameter("start");
//        // String orderByName = getRequest().getParameter("orderByName");
//        // String orderByOrder = getRequest().getParameter("orderByOrder");
//        // if (pageSize != null) {
//        // queryInfo.setPageSize(Integer.valueOf(pageSize));
//        // } else {
//        // queryInfo.setPageSize(Constant.DEFAULT_PAGE_SIZE);
//        // }
//        // if (startRow != null) {
//        // queryInfo.setStartRow(Long.valueOf(startRow));
//        // } else {
//        // queryInfo.setStartRow(Constant.DEFAULT_START_ROW);
//        // }
//        // if (orderByName != null) {
//        // queryInfo.setOrderByName(orderByName);
//        // queryInfo.setOrderByOrder(orderByOrder);
//        // }
//        ParameterizedTypeReference<Page<Map<String, String>>> responseType = new ParameterizedTypeReference<Page<Map<String, String>>>() {
//        };
//        Page<Map<String, String>> page = restClient.post(url, queryInfo, responseType);
//        map.put("draw", draw);
//        map.put("recordsTotal", page.getTotal());
//        map.put("recordsFiltered", page.getTotal());
//        map.put("data", page.getData());
//        return map;
//    }
//
//    /**
//     * 导出所有公告文件
//     * 
//     * @param id
//     *            信披id
//     * 
//     */
//    @RequestMapping(value = "exportFile")
//    public ModelAndView exportFile(String id, String name) throws IOException {
//        ModelAndView mv = new ModelAndView();
//        mv.setView(new DownloadView());
//        // 从文件服务器下载文件
//        InputStream downFile = declareService.exportFile(id);
//        if (downFile != null) {
//            mv.addObject(DownloadView.EXPORT_FILE, downFile);
//            mv.addObject(DownloadView.EXPORT_FILE_NAME, name + ".zip");
//            mv.addObject(DownloadView.EXPORT_FILE_TYPE, MediaType.ZIP);
//        } else {
//            String content = "没有公告文件";
//            downFile = new ByteArrayInputStream(content.getBytes());
//            mv.addObject(DownloadView.EXPORT_FILE, downFile);
//            mv.addObject(DownloadView.EXPORT_FILE_NAME, content + ".txt");
//            mv.addObject(DownloadView.EXPORT_FILE_TYPE, DownloadView.FILE_TYPE.STREAM);
//        }
//        mv.addObject(DownloadView.EXPORT_FILE_SIZE, downFile.available());
//        return mv;
//    }
//
//    /**
//     * 处理关联会议
//     * 
//     */
//    @RequestMapping(value = "processMeeting", method = RequestMethod.POST)
//    @ResponseBody
//    public JsonResponse<MeetingDto> processMeeting(MeetingDto meetingDto) {
//        MeetingDto md = declareService.processMeeting(meetingDto);
//        JsonResponse<MeetingDto> response = new JsonResponse<MeetingDto>();
//        response.setResult(md);
//        return response;
//    }
//
//    /**
//     * 显示业务分类下相关法律细则
//     * 
//     * @param id
//     *            业务分类id
//     */
//    @RequestMapping(value = "declareRelationLaw")
//    public ModelAndView declareRelationLaw(String id, String typeId) {
//        ModelAndView mv = new ModelAndView("declare/lawText");
//        Map<String, Object> lawData = declareService.getLawTextData(id, typeId);
//        mv.addObject("lawTextList", JsonUtil.toJsonNoNull(lawData.get("lawItemList")));
//        mv.addObject("nodeId", lawData.get("nodeId"));
//        mv.addObject("lawId", id);
//        return mv;
//    }
//
//    /**
//     * 获取法规条文内容
//     * 
//     * @param id
//     *            法规条文id
//     * @return
//     */
//    @RequestMapping(value = "getLawContext", method = RequestMethod.POST)
//    @ResponseBody
//    public JsonResponse<String> getLawContext(String id, String lawId) {
//        String data = StringUtils.EMPTY;
//        if (StringUtils.isEmpty(lawId)) {
//            data = declareService.getLawContext(id);
//        } else {
//            data = declareService.getSingleLaw(lawId);
//        }
//        JsonResponse<String> response = new JsonResponse<String>();
//        response.setResult(data);
//        return response;
//    }
//
//    @RequestMapping(value = "saveIdea", method = RequestMethod.POST)
//    @ResponseBody
//    public JsonResponse<String> saveIdea(String declareIdea, String typeId, String ideaId, String privateVal) {
//        String id = declareService.saveIdea(declareIdea, typeId, ideaId, privateVal);
//        JsonResponse<String> response = new JsonResponse<String>();
//        response.setResult(id);
//        return response;
//    }
//
//    /**
//     * 
//     * @param ideaId
//     * @return
//     */
//    @RequestMapping(value = "deleteIdea", method = RequestMethod.POST)
//    @ResponseBody
//    public JsonResponse<String> deleteIdea(String ideaId) {
//        declareService.deleteIdea(ideaId);
//        JsonResponse<String> response = new JsonResponse<String>();
//        response.setResult(null);
//        return response;
//    }
//    
//    /**
//     * 
//     * 导出信息披露审批日志
//     *
//     * @param businessId
//     * @param declareName
//     * @return
//     * @throws IOException 
//     *
//     */
//    @SuppressWarnings("rawtypes")
//    @RequestMapping(value = "declareApproveExport")
//    public ModelAndView declareApproveExport(String businessId,String declareName) throws IOException {
//        //判断文档名字合规
//        if(declareName.length()>31){
//        	declareName = declareName.substring(0, 31);
//        }
//        //文件名在操作系统中不允许出现  / \ " : | * ? < > 故将其以空替代
//        Pattern pattern = Pattern.compile("[\\s\\\\/:\\*\\?\\\"<>\\|]");
//        Matcher matcher = pattern.matcher(declareName);
//        // 将匹配到的非法字符以空替换
//        declareName = matcher.replaceAll("");
//        
//        ModelAndView mv = new ModelAndView();
//        mv.setView(new DownloadView());
//        mv.addObject(DownloadView.EXPORT_FILE,declareExportService.exportLog(businessId, declareName));
//        mv.addObject(DownloadView.EXPORT_FILE_NAME, declareName+"审批日志.xls");
//        mv.addObject(DownloadView.EXPORT_FILE_TYPE, DownloadView.FILE_TYPE.XLS);
//        return mv;
//    }
//    
//    /**
//     * 关联信批初始化
//     *
//     * @return
//     *
//     */
//    @RequestMapping(value="declareData")
//    public ModelAndView declareData() {
//        ModelAndView mv = new ModelAndView("itemManage/relationDeclare");
//        mv.addObject("declareIndexDto", new DeclareIndexDto());
//        
//        return mv;
//    }
//}

package com.stock.capital.enterprise.ipoCase.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.stock.capital.enterprise.common.constant.Global;
import com.stock.capital.enterprise.common.service.CommonService;
import com.stock.capital.enterprise.ipoCase.dao.IpoFeedbackMapper;
import com.stock.capital.enterprise.ipoCase.dto.CompanyOverviewVo;
import com.stock.capital.enterprise.ipoCase.dto.IpoFeedbackDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoFeedbackIndexDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoFeedbackQuestionDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoQuestionLabelDto;
import com.stock.core.Constant;
import com.stock.core.dto.FacetResult;
import com.stock.core.dto.QueryInfo;
import com.stock.core.dto.StatisticsField;
import com.stock.core.rest.RestClient;
import com.stock.core.service.BaseService;
import com.stock.core.util.JsonUtil;

@Service
public class IpoFeedbackService extends BaseService {
    @Autowired
    private IpoFeedbackMapper ipoFeedbackMapper;

//    @Autowired
//    private SearchServer searchServer;
    
    @Autowired
    private RestClient restClient;

    @Autowired
    private CommonService commonService;
    
    /**
	 * 微服地址前缀
	 */
	@Value("#{app['service.gui.baseUrl']}")
	private String serviceGuiBaseUrl;

    /**
     * 初始化页面方法，查询函件相关信息
     */
    public List<IpoFeedbackDto> selectFeedbackList(IpoFeedbackDto ipoFeedbackDto) {
        //查询该公司是否有反馈意见进程，如果没有则返回空集合
        List<String> feedbackProcess = ipoFeedbackMapper.selectFeedbackProcess(ipoFeedbackDto.getId());
        if (CollectionUtils.isEmpty(feedbackProcess)) {
            return new ArrayList<>();
        }
        CompanyOverviewVo companyOverviewVo = ipoFeedbackMapper.getOrgCode(ipoFeedbackDto.getId());

        //根据案例id查询公司的东财内码
        ipoFeedbackDto.setOrgCode(companyOverviewVo.getOrgCode());
        //查询问题答案列表
        List<IpoFeedbackDto> resultList = ipoFeedbackMapper.selectFeedbackList(ipoFeedbackDto);

        //计算答案个数
        if (CollectionUtils.isNotEmpty(resultList)) {
            for (int i = 0; i < resultList.size(); i++) {
                if (resultList.size() > 1) {
                    if (i == 0) {
                        resultList.get(i).setLetterName("第一次反馈意见");
                    } else if (i == 1) {
                        resultList.get(i).setLetterName("第二次反馈意见");
                    } else if (i == 2) {
                        resultList.get(i).setLetterName("第三次反馈意见");
                    }
                }
                int questionCount = 0;
                int answerCount = 0;
                List<IpoFeedbackQuestionDto> questionList = resultList.get(i).getQuestionList();
                questionCount = questionList.size();
                if (CollectionUtils.isNotEmpty(questionList)) {
                    for (IpoFeedbackQuestionDto questionDto : questionList) {
                        if (StringUtils.isNotEmpty(questionDto.getAnswer())) {
                            answerCount++;
                        }
                    }
                }
                resultList.get(i).setQuestionCount(questionCount);
                resultList.get(i).setAnswerCount(answerCount);
                //查询问题标签一级列表
                List<IpoQuestionLabelDto> firstLabelList = ipoFeedbackMapper
                        .selectFirstLabelList(resultList.get(i).getLetterId());
                resultList.get(i).setQuestionLabelList(firstLabelList);
            }
        }

        return resultList;
    }

    /**
     * 查询二级标签
     */
    public List<IpoQuestionLabelDto> selectSecondLabelList(String letterId, String parentId) {
        List<IpoQuestionLabelDto> secondLabelList = ipoFeedbackMapper.selectSecondLabelList(letterId, parentId);
        return secondLabelList;
    }

    /**
     * 查询问题列表
     */
    public List<IpoFeedbackDto> selectQuestionListByLetterId(IpoFeedbackDto ipoFeedbackDto) {
        //查询问题答案列表

        String secondLabelIds = ipoFeedbackDto.getSecondLabelId();
        if (StringUtils.isNotEmpty(secondLabelIds)) {
            List<String> secondLabelList = Arrays.asList(secondLabelIds.split(","));
            ipoFeedbackDto.setSecondLabelList(secondLabelList);
        }
        List<IpoFeedbackDto> resultList = ipoFeedbackMapper.selectQuestionListByLetterId(ipoFeedbackDto);
        return resultList;
    }


    /**
     * 查询反馈意见新问题分类
     */
    public Map<String, List<IpoFeedbackDto>> selectNewFeedbackList(String id) {
      Map<String, List<IpoFeedbackDto>> resultMap = new HashMap<>();
      CompanyOverviewVo companyOverviewVo = ipoFeedbackMapper.getOrgCode(id);
      String ipoPlate = companyOverviewVo.getIpoPlate();
      if (ipoPlate.equals("069001001006")){//上交所科创板 注册制
        List<IpoFeedbackDto> registerList = selectRegisterFeedbackList(id);//注册制
        resultMap.put("registerList",registerList);
      } else if (ipoPlate.equals("069001002002")){// 深交所创业板 注册制+核准制
        List<IpoFeedbackDto> registerList = selectRegisterFeedbackList(id);//注册制
        List<IpoFeedbackDto> ratifyList=selectRatifyFeedbackList(id);//核准制
        resultMap.put("registerList",registerList);
        resultMap.put("ratifyList",ratifyList);
      } else {// 其他 核准制
        List<IpoFeedbackDto> ratifyList=selectRatifyFeedbackList(id);//核准制
        resultMap.put("ratifyList",ratifyList);
      }
      return resultMap;
    }


    public String between_days(Date startDate, Date endDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");// 自定义时间格式
        Calendar calendar_a = Calendar.getInstance();// 获取日历对象
        Calendar calendar_b = Calendar.getInstance();
        Date date_a = null;
        Date date_b = null;
        try {
            calendar_a.setTime(startDate);// 设置日历
            calendar_b.setTime(endDate);
        } catch (Exception e) {
            e.printStackTrace();//格式化异常
        }

        long time_a = calendar_a.getTimeInMillis();
        long time_b = calendar_b.getTimeInMillis();

        long between_days = (time_b - time_a) / (1000 * 3600 * 24);//计算相差天数

        return between_days + "";
    }

    /**
     * 查询二级标签及问题
     */
    public List<IpoFeedbackDto> selectNewQuestionList(String letterId, String firstLabelId, String secondLabelIds, String onlyResponse) {
        List<IpoFeedbackDto> resultList = new ArrayList<>();
        IpoFeedbackDto resultDto = new IpoFeedbackDto();
        //从云端查询标一二级标签
        Map<String, Map<String, String>> secondLabelMap = ipoFeedbackMapper.selectSecondLabelMap("");
        //如果存在一级标签，则查询一级标签下的二级标签
        Map<String, Map<String, String>> secondSelLabelMap = ipoFeedbackMapper.selectSecondLabelMap(firstLabelId);
        //定义问题标签集合
        List<IpoQuestionLabelDto> secondLabelList = new ArrayList<>();
        //将二级标签用逗号分隔为数组
        List<String> secondLabelParamList = Arrays.asList(secondLabelIds.split(","));
        
        FacetResult<IpoFeedbackIndexDto> facetResult = new FacetResult<IpoFeedbackIndexDto>();
        String orderByName = "letter_question_id_t";
		String orderByOrder = "ASC";
		
//        if(Global.SEARCH_SERVER_LETTER_QA_FLAG.equals("0")) {
        	String accessToken = commonService.getGuiAccessToken();
    		String urls = serviceGuiBaseUrl + "/letter/letter/api/searchLetterQaData?access_token=" + accessToken;
    		ParameterizedTypeReference<String> responseType = new ParameterizedTypeReference<String>() {
    		};
    		
            QueryInfo<Map<String, Object>> queryInfo = new QueryInfo<Map<String, Object>>();
            Map<String, Object> condition = Maps.newHashMap();
            
            if(StringUtils.isNotEmpty(letterId)) {
    			condition.put("letterId", letterId);
    		}
            
            condition.put("groupFlag", "true");
            
            if (StringUtils.isNotEmpty(secondLabelIds) && CollectionUtils.isNotEmpty(secondLabelParamList)) {
    			condition.put("questionTypeList", secondLabelParamList);
    			if (StringUtils.isNotEmpty(firstLabelId)) {
    				condition.put("questionType", firstLabelId);
    			}
    		} else if (StringUtils.isNotEmpty(firstLabelId)) {
    			condition.put("questionType", firstLabelId);
    		}
            
            queryInfo.setQueryId("com.stock.capital.services.letter.api.dao.LetterApiQa.searchLetterQaData");
            queryInfo.setCondition(condition);
    		queryInfo.setStartRow(0);
    		queryInfo.setPageSize(2000);
    		queryInfo.setOrderByName(orderByName);
    		queryInfo.setOrderByOrder(orderByOrder);
    		
    		String encryptData = restClient.post(urls, queryInfo, responseType);
    		// 获取解密后的数据
    		Map<String, Object> index = commonService.getEncryptData(encryptData);
    		if(!MapUtils.isEmpty(index)) {
    			ParameterizedTypeReference<FacetResult<IpoFeedbackIndexDto>> map = new ParameterizedTypeReference<FacetResult<IpoFeedbackIndexDto>>() {
				};
				facetResult = JsonUtil.fromJson(JsonUtil.toJson(index) ,map);
    		}
//    	} else {
//    		Map<String, String> condition = Maps.newHashMap();
//    		StringBuilder conditionsStr = new StringBuilder("index_type_t: \"letterqa\"");
//    		conditionsStr.append(" AND " + "letter_letter_id_t:");
//    		conditionsStr.append(letterId);
//    		
//    		if (StringUtils.isNotEmpty(secondLabelIds) && CollectionUtils.isNotEmpty(secondLabelParamList)) {
//    			conditionsStr.append(" AND " + "letter_question_class_new_id_txt:(").append(secondLabelParamList.get(0));
//    			for (int i = 1; i < secondLabelParamList.size(); i++) {
//    				conditionsStr.append(" OR ").append(secondLabelParamList.get(i));
//    			}
//    			conditionsStr.append(")");
//    			if (StringUtils.isNotEmpty(firstLabelId)) {
//    				conditionsStr.append(" AND " + "letter_question_class_new_id_txt:").append(firstLabelId);
//    			}
//    		} else if (StringUtils.isNotEmpty(firstLabelId)) {
//    			conditionsStr.append(" AND " + "letter_question_class_new_id_txt:");
//    			conditionsStr.append(firstLabelId);
//    		}
//    		String conditionsGroup = "letter_question_class_new_id_txt";
//    		condition.put(Constant.SEARCH_CONDIATION, conditionsStr.toString());
//    		condition.put(Constant.SEARCH_FACET_FIELD, conditionsGroup);
//    		condition.put(Constant.SEARCH_FACET_MIN_COUNT, "1");
//    		QueryInfo<Map<String, String>> queryInfo = new QueryInfo<>();
//    		queryInfo.setCondition(condition);
//    		queryInfo.setStartRow(0);
//    		queryInfo.setPageSize(2000);
//    		queryInfo.setOrderByName(orderByName);
//    		queryInfo.setOrderByOrder(orderByOrder);
//    		facetResult = searchServer.searchWithFacet("letterqa", queryInfo, IpoFeedbackIndexDto.class);
//    	}
        
        List<StatisticsField> labelList =
                facetResult.getStatisticsFieldMap().get("letter_question_class_new_id_txt");
        List<IpoFeedbackIndexDto> questionList = facetResult.getPage().getData();
        //如果选择一级标签全部，二级标签的全部要特殊处理
        if (firstLabelId.equals("")) {
            IpoQuestionLabelDto allLabelDto = new IpoQuestionLabelDto();
            allLabelDto.setLabelName("全部");
            allLabelDto.setLabelCount(String.valueOf(questionList.size()));
            allLabelDto.setSort(0);
            secondLabelList.add(0, allLabelDto);
        }
        //循环标签，将标签个数赋值
        for (StatisticsField labelDto : labelList) {
            //如果标签id等于父id,则将此标签统计个数赋值给全部标签
            if (firstLabelId.equals(labelDto.getFieldId())) {
                IpoQuestionLabelDto allLabelDto = new IpoQuestionLabelDto();
                allLabelDto.setLabelName("全部");
                allLabelDto.setLabelCount(String.valueOf(labelDto.getCount()));
                allLabelDto.setSort(0);
                secondLabelList.add(0, allLabelDto);
            }

            if (null != secondSelLabelMap.get(labelDto.getFieldId())) {
                IpoQuestionLabelDto questionLabelDto = new IpoQuestionLabelDto();
                questionLabelDto.setLabelCode(labelDto.getFieldId());
                questionLabelDto.setLabelName(secondSelLabelMap.get(labelDto.getFieldId()).get("letterClassName"));
                questionLabelDto.setLabelCount(String.valueOf(labelDto.getCount()));
                String sort = secondSelLabelMap.get(labelDto.getFieldId()).get("sort");
                if (StringUtils.isEmpty(sort)) {
                    sort = "1";
                }
                questionLabelDto.setSort(Integer.parseInt(sort));
                secondLabelList.add(questionLabelDto);
            }
        }
        //二级标签排序
        secondLabelList.sort((IpoQuestionLabelDto c1, IpoQuestionLabelDto c2) ->
                (c1.getSort() > c2.getSort() ? 1 : (c1.getSort() == c2.getSort() ? 0 : -1)));
        resultDto.setQuestionLabelList(secondLabelList);
        //定义一个问题列表数组
        List<IpoFeedbackQuestionDto> questionResultList = new ArrayList<>();
        int questionCount = questionList.size();
        int answerCount = 0;
        if (CollectionUtils.isNotEmpty(questionList)) {
            for (IpoFeedbackIndexDto questionDto : questionList) {
                //定义二级标签集合
                List<String> belongSecondLabelList = new ArrayList<>();
                //定义问题、答案DTO
                //判断是否只显示已回复问题
                if ("1".equals(onlyResponse)) {
                    if (StringUtils.isNotEmpty(questionDto.getAnswersContents())) {
                        IpoFeedbackQuestionDto questionResultDto = new IpoFeedbackQuestionDto();
                        questionResultDto.setQuestionId(questionDto.getQuestionId());
                        questionResultDto.setQuestion(questionDto.getQuestContents());
                        questionResultDto.setAnswer(questionDto.getAnswersContents());
                        questionResultDto.setFormatQuestion(questionDto.getQuestionLabelContent());
                        questionResultDto.setFormatAnswer(questionDto.getAnswerLabelContent());
                        List<String> belongLabel = questionDto.getQuestionClassNewId();
                        //循环放入问题所属二级标签
                        if (CollectionUtils.isNotEmpty(belongLabel)) {
                            for (String belongLabelStr : belongLabel) {
                                if (null != secondLabelMap.get(belongLabelStr)) {
                                    belongSecondLabelList.add(secondLabelMap.get(belongLabelStr).get("letterClassName"));
                                }
                            }
                            questionResultDto.setLabelList(belongSecondLabelList);
                        }
                        if (StringUtils.isNotEmpty(questionResultDto.getAnswer())) {
                            answerCount++;
                        }
                        questionResultList.add(questionResultDto);
                    }
                } else {
                    IpoFeedbackQuestionDto questionResultDto = new IpoFeedbackQuestionDto();
                    questionResultDto.setQuestionId(questionDto.getQuestionId());
                    questionResultDto.setQuestion(questionDto.getQuestContents());
                    questionResultDto.setAnswer(questionDto.getAnswersContents());
                    questionResultDto.setFormatQuestion(questionDto.getQuestionLabelContent());
                    questionResultDto.setFormatAnswer(questionDto.getAnswerLabelContent());
                    List<String> belongLabel = questionDto.getQuestionClassNewId();
                    //循环放入问题所属二级标签
                    if (CollectionUtils.isNotEmpty(belongLabel)) {
                        for (String belongLabelStr : belongLabel) {
                            if (null != secondLabelMap.get(belongLabelStr)) {
                                belongSecondLabelList.add(secondLabelMap.get(belongLabelStr).get("letterClassName"));
                            }
                        }
                        questionResultDto.setLabelList(belongSecondLabelList);
                    }
                    if (StringUtils.isNotEmpty(questionResultDto.getAnswer())) {
                        answerCount++;
                    }
                    questionResultList.add(questionResultDto);
                }
            }
        }
        resultDto.setQuestionCount(questionCount);
        resultDto.setAnswerCount(answerCount);
        resultDto.setQuestionList(questionResultList);
        resultList.add(resultDto);
        return resultList;
    }

  /**
   * 注册制  问询与回复
   * @param id
   * @return
   */
  private List<IpoFeedbackDto> selectRegisterFeedbackList(String id) {
    // TODO: 2020/5/21 死值需要改回原来
    id="98114881248925363";//上海硅产业集团股份有限公司

    List<IpoFeedbackDto> resultList = new ArrayList<>();
    //根据案例id查询公司的东财内码
    CompanyOverviewVo companyOverviewVo = ipoFeedbackMapper.getOrgCode(id);
//        反馈意见  问询  审核中心落实函 进程查询
    List<String> processDateList = ipoFeedbackMapper.selectFeedbackProcess(id);//进程树时间
    if (CollectionUtils.isEmpty(processDateList)) {// 无进程返回空
      return new ArrayList<>();
    }
    List<String> letterIds = new ArrayList<>();
    //找函件ID '科创板上市申请文件审核问询函','审核中心意见落实函'
    letterIds = ipoFeedbackMapper.selectKcbLetterIds(companyOverviewVo.getOrgCode(),processDateList);

    int feedbackCount = 0;
    for (int i = 0; i < letterIds.size(); i++) {
      //定义函件对象
      IpoFeedbackDto ipoFeedbackResultDto = new IpoFeedbackDto();
      ipoFeedbackResultDto.setLetterId(letterIds.get(i));
      //定义问题标签集合
      List<IpoQuestionLabelDto> firstLabelList = new ArrayList<>();

      //从云端查询标一二级标签
      Map<String, Map<String, String>> firstLabelMap = ipoFeedbackMapper
          .selectFirstLabelMap();
      Map<String, Map<String, String>> secondLabelMap = ipoFeedbackMapper
          .selectSecondLabelMap("");

      //从索引中查询分类个数
      FacetResult<IpoFeedbackIndexDto> facetResult = new FacetResult<IpoFeedbackIndexDto>();
      String orderByName = "letter_question_id_t";
      String orderByOrder = "ASC";
      String letterId = letterIds.get(i);

      String accessToken = commonService.getGuiAccessToken();
      String urls = serviceGuiBaseUrl + "/letter/letter/api/searchLetterQaData?access_token="
          + accessToken;
      ParameterizedTypeReference<String> responseType = new ParameterizedTypeReference<String>() {
      };

      QueryInfo<Map<String, Object>> queryInfo = new QueryInfo<Map<String, Object>>();
      Map<String, Object> condition = Maps.newHashMap();

      if (StringUtils.isNotEmpty(letterId)) {
        condition.put("letterId", letterId);
      }

      condition.put("groupFlag", "true");

      queryInfo.setQueryId(
          "com.stock.capital.services.letter.api.dao.LetterApiQa.searchLetterQaData");
      queryInfo.setCondition(condition);
      queryInfo.setStartRow(0);
      queryInfo.setPageSize(2000);
      queryInfo.setOrderByName(orderByName);
      queryInfo.setOrderByOrder(orderByOrder);

      String encryptData = restClient.post(urls, queryInfo, responseType);
      // 获取解密后的数据
      Map<String, Object> index = commonService.getEncryptData(encryptData);
      if (!MapUtils.isEmpty(index)) {
        ParameterizedTypeReference<FacetResult<IpoFeedbackIndexDto>> map =
            new ParameterizedTypeReference<FacetResult<IpoFeedbackIndexDto>>() {
            };
        facetResult = JsonUtil.fromJson(JsonUtil.toJson(index), map);
      }
      List<StatisticsField> labelList =
          facetResult.getStatisticsFieldMap().get("letter_question_class_new_id_txt");

      //循环标签，将标签个数赋值
      for (StatisticsField labelDto : labelList) {
        if (null != firstLabelMap.get(labelDto.getFieldId())) {
          IpoQuestionLabelDto questionLabelDto = new IpoQuestionLabelDto();
          questionLabelDto.setLabelCode(labelDto.getFieldId());
          questionLabelDto.setLabelName(
              firstLabelMap.get(labelDto.getFieldId()).get("letterClassName"));
          questionLabelDto.setLabelCount(String.valueOf(labelDto.getCount()));
          String sort = firstLabelMap.get(labelDto.getFieldId()).get("sort");
          if (StringUtils.isEmpty(sort)) {
            sort = "1";
          }
          questionLabelDto.setSort(Integer.parseInt(sort));
          firstLabelList.add(questionLabelDto);
        }
      }
      //一级标签排序
      firstLabelList.sort((IpoQuestionLabelDto c1, IpoQuestionLabelDto c2) ->
          (c1.getSort() > c2.getSort() ? 1 : (c1.getSort() == c2.getSort() ? 0 : -1)));

      List<IpoFeedbackIndexDto> questionList = facetResult.getPage().getData();
      //一级标签添加全部标签
      IpoQuestionLabelDto questionLabelDto = new IpoQuestionLabelDto();
      questionLabelDto.setLabelCode(null);
      questionLabelDto.setLabelName("全部");
      questionLabelDto.setLabelCount(String.valueOf(questionList.size()));
      firstLabelList.add(0, questionLabelDto);

      ipoFeedbackResultDto.setQuestionLabelList(firstLabelList);
      //定义一个问题列表数组
      List<IpoFeedbackQuestionDto> questionResultList = new ArrayList<>();
      int questionCount = questionList.size();
      int answerCount = 0;
      if (CollectionUtils.isNotEmpty(questionList)) {
        //添加前台需要展示的函件类型名称
        String letterTypeName = questionList.get(0).getLetterTypeName();
        if ("审核中心意见落实函".equals(letterTypeName)) {
          ipoFeedbackResultDto.setLetterName("落实函");
        } else {
          if (feedbackCount == 0) {
            ipoFeedbackResultDto.setLetterName("第一次问询");
          } else if (feedbackCount == 1) {
            ipoFeedbackResultDto.setLetterName("第二次问询");
          } else if (feedbackCount == 2) {
            ipoFeedbackResultDto.setLetterName("第三次问询");
          } else if (feedbackCount == 3) {
            ipoFeedbackResultDto.setLetterName("第四次问询");
          } else if (feedbackCount == 4) {
            ipoFeedbackResultDto.setLetterName("第五次问询");
          } else if (feedbackCount == 5) {
            ipoFeedbackResultDto.setLetterName("第六次问询");
          } else if (feedbackCount == 6) {
            ipoFeedbackResultDto.setLetterName("第七次问询");
          } else if (feedbackCount == 7) {
            ipoFeedbackResultDto.setLetterName("第八次问询");
          } else if (feedbackCount == 8) {
            ipoFeedbackResultDto.setLetterName("第九次问询");
          } else if (feedbackCount == 9) {
            ipoFeedbackResultDto.setLetterName("第十次问询");
          }
          feedbackCount++;
        }
        for (IpoFeedbackIndexDto questionDto : questionList) {
          //定义二级标签集合
          List<String> secondLabelList = new ArrayList<>();
          //定义问题、答案DTO
          IpoFeedbackQuestionDto questionResultDto = new IpoFeedbackQuestionDto();
          questionResultDto.setQuestionId(questionDto.getQuestionId());
          questionResultDto.setQuestion(questionDto.getQuestContents());
          questionResultDto.setAnswer(questionDto.getAnswersContents());
          questionResultDto.setFormatQuestion(questionDto.getQuestionLabelContent());
          questionResultDto.setFormatAnswer(questionDto.getAnswerLabelContent());
          List<String> belongLabel = questionDto.getQuestionClassNewId();
          //循环放入问题所属二级标签
          if (CollectionUtils.isNotEmpty(belongLabel)) {
            for (String belongLabelStr : belongLabel) {
              if (null != secondLabelMap.get(belongLabelStr)) {
                secondLabelList
                    .add(secondLabelMap.get(belongLabelStr).get("letterClassName"));
              }
            }
          }
          questionResultDto.setLabelList(secondLabelList);
          questionResultList.add(questionResultDto);
          if (StringUtils.isNotEmpty(questionResultDto.getAnswer())) {
            answerCount++;
          }
        }
      }
      ipoFeedbackResultDto.setQuestionCount(questionCount);
      ipoFeedbackResultDto.setAnswerCount(answerCount);
      ipoFeedbackResultDto.setQuestionList(questionResultList);
      if (questionList != null && questionList.size() > 0) {
        ipoFeedbackResultDto.setLetterFileNo(questionList.get(0).getLetterFileNo());
        ipoFeedbackResultDto.setLetterDate(questionList.get(0).getLetterDate());
        ipoFeedbackResultDto.setReturnDate(questionList.get(0).getLetterReturnDate());
        if (questionList.get(0).getLetterDate() != null
            && questionList.get(0).getLetterReturnDate() != null) {
          ipoFeedbackResultDto.setIntervalDate(between_days(questionList.get(0).getLetterDate(),
              questionList.get(0).getLetterReturnDate()));
        }
      }
      ipoFeedbackResultDto
          .setLetterFileNo(ipoFeedbackMapper.selectFileNo(ipoFeedbackResultDto.getLetterId()));
      resultList.add(ipoFeedbackResultDto);
    }

    return resultList;
  }

  /**
   * 核准制 反馈意见
   * @param id
   * @return
   */
  private List<IpoFeedbackDto> selectRatifyFeedbackList(String id) {
    // TODO: 2020/5/21 死值需要改回原来
    id = "97952444248599354";
    List<IpoFeedbackDto> resultList = new ArrayList<>();
    //根据案例id查询公司的东财内码
    CompanyOverviewVo companyOverviewVo = ipoFeedbackMapper.getOrgCode(id);
//        反馈意见  问询  审核中心落实函 进程查询
    List<String> processDateList = ipoFeedbackMapper.selectFeedbackProcess(id);//进程树时间
    if (CollectionUtils.isEmpty(processDateList)) {// 无进程返回空
      return new ArrayList<>();
    }
    List<String> letterIds = new ArrayList<>();
    letterIds = ipoFeedbackMapper
        .selectLetterIds(companyOverviewVo.getOrgCode(), processDateList);//找函件ID IPO预披露反馈意见
    int feedbackCount = 0;
    for (int i = 0; i < letterIds.size(); i++) {
      //定义函件对象
      IpoFeedbackDto ipoFeedbackResultDto = new IpoFeedbackDto();
      ipoFeedbackResultDto.setLetterId(letterIds.get(i));
      //定义问题标签集合
      List<IpoQuestionLabelDto> firstLabelList = new ArrayList<>();

      //从云端查询标一二级标签
      Map<String, Map<String, String>> firstLabelMap = ipoFeedbackMapper
          .selectFirstLabelMap();
      Map<String, Map<String, String>> secondLabelMap = ipoFeedbackMapper
          .selectSecondLabelMap("");

      //从索引中查询分类个数
      FacetResult<IpoFeedbackIndexDto> facetResult = new FacetResult<IpoFeedbackIndexDto>();
      String orderByName = "letter_question_id_t";
      String orderByOrder = "ASC";
      String letterId = letterIds.get(i);

      String accessToken = commonService.getGuiAccessToken();
      String urls = serviceGuiBaseUrl + "/letter/letter/api/searchLetterQaData?access_token="
          + accessToken;
      ParameterizedTypeReference<String> responseType = new ParameterizedTypeReference<String>() {
      };

      QueryInfo<Map<String, Object>> queryInfo = new QueryInfo<Map<String, Object>>();
      Map<String, Object> condition = Maps.newHashMap();

      if (StringUtils.isNotEmpty(letterId)) {
        condition.put("letterId", letterId);
      }

      condition.put("groupFlag", "true");

      queryInfo.setQueryId(
          "com.stock.capital.services.letter.api.dao.LetterApiQa.searchLetterQaData");
      queryInfo.setCondition(condition);
      queryInfo.setStartRow(0);
      queryInfo.setPageSize(2000);
      queryInfo.setOrderByName(orderByName);
      queryInfo.setOrderByOrder(orderByOrder);

      String encryptData = restClient.post(urls, queryInfo, responseType);
      // 获取解密后的数据
      Map<String, Object> index = commonService.getEncryptData(encryptData);
      if (!MapUtils.isEmpty(index)) {
        ParameterizedTypeReference<FacetResult<IpoFeedbackIndexDto>> map =
            new ParameterizedTypeReference<FacetResult<IpoFeedbackIndexDto>>() {
            };
        facetResult = JsonUtil.fromJson(JsonUtil.toJson(index), map);
      }
      List<StatisticsField> labelList =
          facetResult.getStatisticsFieldMap().get("letter_question_class_new_id_txt");

      //循环标签，将标签个数赋值
      for (StatisticsField labelDto : labelList) {
        if (null != firstLabelMap.get(labelDto.getFieldId())) {
          IpoQuestionLabelDto questionLabelDto = new IpoQuestionLabelDto();
          questionLabelDto.setLabelCode(labelDto.getFieldId());
          questionLabelDto.setLabelName(
              firstLabelMap.get(labelDto.getFieldId()).get("letterClassName"));
          questionLabelDto.setLabelCount(String.valueOf(labelDto.getCount()));
          String sort = firstLabelMap.get(labelDto.getFieldId()).get("sort");
          if (StringUtils.isEmpty(sort)) {
            sort = "1";
          }
          questionLabelDto.setSort(Integer.parseInt(sort));
          firstLabelList.add(questionLabelDto);
        }
      }
      //一级标签排序
      firstLabelList.sort((IpoQuestionLabelDto c1, IpoQuestionLabelDto c2) ->
          (c1.getSort() > c2.getSort() ? 1 : (c1.getSort() == c2.getSort() ? 0 : -1)));

      List<IpoFeedbackIndexDto> questionList = facetResult.getPage().getData();
      //一级标签添加全部标签
      IpoQuestionLabelDto questionLabelDto = new IpoQuestionLabelDto();
      questionLabelDto.setLabelCode(null);
      questionLabelDto.setLabelName("全部");
      questionLabelDto.setLabelCount(String.valueOf(questionList.size()));
      firstLabelList.add(0, questionLabelDto);

      ipoFeedbackResultDto.setQuestionLabelList(firstLabelList);
      //定义一个问题列表数组
      List<IpoFeedbackQuestionDto> questionResultList = new ArrayList<>();
      int questionCount = questionList.size();
      int answerCount = 0;
      if (CollectionUtils.isNotEmpty(questionList)) {
        //添加前台需要展示的函件类型名称
        String letterTypeName = questionList.get(0).getLetterTypeName();
        if (feedbackCount == 0) {
          ipoFeedbackResultDto.setLetterName("第一次反馈意见");
        } else if (feedbackCount == 1) {
          ipoFeedbackResultDto.setLetterName("第二次反馈意见");
        } else if (feedbackCount == 2) {
          ipoFeedbackResultDto.setLetterName("第三次反馈意见");
        } else if (feedbackCount == 3) {
          ipoFeedbackResultDto.setLetterName("第四次反馈意见");
        } else if (feedbackCount == 4) {
          ipoFeedbackResultDto.setLetterName("第五次反馈意见");
        } else if (feedbackCount == 5) {
          ipoFeedbackResultDto.setLetterName("第六次反馈意见");
        } else if (feedbackCount == 6) {
          ipoFeedbackResultDto.setLetterName("第七次反馈意见");
        } else if (feedbackCount == 7) {
          ipoFeedbackResultDto.setLetterName("第八次反馈意见");
        } else if (feedbackCount == 8) {
          ipoFeedbackResultDto.setLetterName("第九次反馈意见");
        } else if (feedbackCount == 9) {
          ipoFeedbackResultDto.setLetterName("第十次反馈意见");
        }
        feedbackCount++;

        for (IpoFeedbackIndexDto questionDto : questionList) {
          //定义二级标签集合
          List<String> secondLabelList = new ArrayList<>();
          //定义问题、答案DTO
          IpoFeedbackQuestionDto questionResultDto = new IpoFeedbackQuestionDto();
          questionResultDto.setQuestionId(questionDto.getQuestionId());
          questionResultDto.setQuestion(questionDto.getQuestContents());
          questionResultDto.setAnswer(questionDto.getAnswersContents());
          questionResultDto.setFormatQuestion(questionDto.getQuestionLabelContent());
          questionResultDto.setFormatAnswer(questionDto.getAnswerLabelContent());
          List<String> belongLabel = questionDto.getQuestionClassNewId();
          //循环放入问题所属二级标签
          if (CollectionUtils.isNotEmpty(belongLabel)) {
            for (String belongLabelStr : belongLabel) {
              if (null != secondLabelMap.get(belongLabelStr)) {
                secondLabelList
                    .add(secondLabelMap.get(belongLabelStr).get("letterClassName"));
              }
            }
          }
          questionResultDto.setLabelList(secondLabelList);
          questionResultList.add(questionResultDto);
          if (StringUtils.isNotEmpty(questionResultDto.getAnswer())) {
            answerCount++;
          }
        }
      }
      ipoFeedbackResultDto.setQuestionCount(questionCount);
      ipoFeedbackResultDto.setAnswerCount(answerCount);
      ipoFeedbackResultDto.setQuestionList(questionResultList);
      if (questionList != null && questionList.size() > 0) {
        ipoFeedbackResultDto.setLetterFileNo(questionList.get(0).getLetterFileNo());
        ipoFeedbackResultDto.setLetterDate(questionList.get(0).getLetterDate());
        ipoFeedbackResultDto.setReturnDate(questionList.get(0).getLetterReturnDate());
        if (questionList.get(0).getLetterDate() != null
            && questionList.get(0).getLetterReturnDate() != null) {
          ipoFeedbackResultDto.setIntervalDate(between_days(questionList.get(0).getLetterDate(),
              questionList.get(0).getLetterReturnDate()));
        }
      }
      ipoFeedbackResultDto
          .setLetterFileNo(ipoFeedbackMapper.selectFileNo(ipoFeedbackResultDto.getLetterId()));
      resultList.add(ipoFeedbackResultDto);
    }
    return resultList;
  }
}

package com.stock.capital.enterprise.ipoCase.service;

import com.google.common.collect.Maps;

import com.stock.capital.enterprise.ipoCase.dao.IpoFeedbackMapper;
import com.stock.capital.enterprise.ipoCase.dto.IpoCaseIndexDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoCaseListBo;
import com.stock.capital.enterprise.ipoCase.dto.IpoFeedbackDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoFeedbackIndexDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoFeedbackQuestionDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoQuestionLabelDto;
import com.stock.core.Constant;
import com.stock.core.dto.FacetResult;
import com.stock.core.dto.QueryInfo;
import com.stock.core.dto.StatisticsField;
import com.stock.core.search.SearchServer;
import com.stock.core.service.BaseService;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class IpoFeedbackService extends BaseService {
    @Autowired
    private IpoFeedbackMapper ipoFeedbackMapper;

    @Autowired
    private SearchServer searchServer;

    /**
     * 初始化页面方法，查询函件相关信息
     */
    public List<IpoFeedbackDto> selectFeedbackList(IpoFeedbackDto ipoFeedbackDto) {
        //查询该公司是否有反馈意见进程，如果没有则返回空集合
        List<String> feedbackProcess = ipoFeedbackMapper.selectFeedbackProcess(ipoFeedbackDto.getId());
        if (CollectionUtils.isEmpty(feedbackProcess)) {
            return new ArrayList<>();
        }

        //根据案例id查询公司的东财内码
        ipoFeedbackDto.setOrgCode(ipoFeedbackMapper.getOrgCode(ipoFeedbackDto.getId()));
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
    public List<IpoFeedbackDto> selectNewFeedbackList(String id) {
        List<IpoFeedbackDto> resultList = new ArrayList<>();
        //根据案例id查询公司的东财内码
        String orgId = ipoFeedbackMapper.getOrgCode(id);
        List<String> processDateList = ipoFeedbackMapper.selectFeedbackProcess(id);
        if (CollectionUtils.isEmpty(processDateList)) {
            return new ArrayList<>();
        }
        List<String> letterIds = ipoFeedbackMapper.selectLetterIds(orgId, processDateList);
//        letterIds = new ArrayList<>();
//        letterIds.add("745777672754254995");
        for (int i = 0; i < letterIds.size(); i++) {
            //定义函件对象
            IpoFeedbackDto ipoFeedbackResultDto = new IpoFeedbackDto();
            ipoFeedbackResultDto.setLetterId(letterIds.get(i));
            //定义问题标签集合
            List<IpoQuestionLabelDto> firstLabelList = new ArrayList<>();

            //从云端查询标一二级标签
            Map<String, Map<String, String>> firstLabelMap = ipoFeedbackMapper.selectFirstLabelMap();
            Map<String, Map<String, String>> secondLabelMap = ipoFeedbackMapper.selectSecondLabelMap();

            //从索引中查询分类个数
            Map<String, String> condition = Maps.newHashMap();
            String letterId = letterIds.get(i);
            StringBuilder conditionsStr = new StringBuilder("index_type_t: \"letterqa\"");
            conditionsStr.append(" AND " + "letter_letter_id_t:");
            conditionsStr.append(letterId);
            String conditionsGroup = "letter_question_class_new_id_txt";
            String orderByName = "letter_question_id_t";
            String orderByOrder = "ASC";
            condition.put(Constant.SEARCH_CONDIATION, conditionsStr.toString());
            condition.put(Constant.SEARCH_FACET_FIELD, conditionsGroup);
            condition.put(Constant.SEARCH_FACET_MIN_COUNT, "1");
            QueryInfo<Map<String, String>> queryInfo = new QueryInfo<>();
            queryInfo.setCondition(condition);
            queryInfo.setStartRow(0);
            queryInfo.setPageSize(2000);
            queryInfo.setOrderByName(orderByName);
            queryInfo.setOrderByOrder(orderByOrder);
            FacetResult<IpoFeedbackIndexDto> facetResult =
                    searchServer.searchWithFacet("letterqa", queryInfo, IpoFeedbackIndexDto.class);
            List<StatisticsField> labelList =
                    facetResult.getStatisticsFieldMap().get("letter_question_class_new_id_txt");

            //循环标签，将标签个数赋值
            for (StatisticsField labelDto : labelList) {
                if (null != firstLabelMap.get(labelDto.getFieldId())) {
                    IpoQuestionLabelDto questionLabelDto = new IpoQuestionLabelDto();
                    questionLabelDto.setLabelCode(labelDto.getFieldId());
                    questionLabelDto.setLabelName(firstLabelMap.get(labelDto.getFieldId()).get("letterClassName"));
                    questionLabelDto.setLabelCount(String.valueOf(labelDto.getCount()));
                    firstLabelList.add(questionLabelDto);
                }
            }

            List<IpoFeedbackIndexDto> questionList = facetResult.getPage().getData();
            //一级标签添加全部标签
            IpoQuestionLabelDto questionLabelDto = new IpoQuestionLabelDto();
            questionLabelDto.setLabelCode("");
            questionLabelDto.setLabelName("全部");
            questionLabelDto.setLabelCount(String.valueOf(questionList.size()));
            firstLabelList.add(0,questionLabelDto);

            ipoFeedbackResultDto.setQuestionLabelList(firstLabelList);
            //定义一个问题列表数组
            List<IpoFeedbackQuestionDto> questionResultList = new ArrayList<>();
            int questionCount = questionList.size();
            int answerCount = 0;
            if (CollectionUtils.isNotEmpty(questionList)) {
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
                                secondLabelList.add(secondLabelMap.get(belongLabelStr).get("letterClassName"));
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
            resultList.add(ipoFeedbackResultDto);
        }
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
            }
        }

        return resultList;
    }

    /**
     * 查询二级标签及问题
     */
    public List<IpoFeedbackDto> selectNewQuestionList(String letterId, String firstLabelId, String secondLabelIds, String onlyResponse) {
        List<IpoFeedbackDto> resultList = new ArrayList<>();
        IpoFeedbackDto resultDto = new IpoFeedbackDto();
        //从云端查询标一二级标签
        Map<String, Map<String, String>> secondLabelMap = ipoFeedbackMapper.selectSecondLabelMap();
        //定义问题标签集合
        List<IpoQuestionLabelDto> secondLabelList = new ArrayList<>();
        //将二级标签用逗号分隔为数组
        List<String> secondLabelParamList = Arrays.asList(secondLabelIds.split(","));
        Map<String, String> condition = Maps.newHashMap();
        StringBuilder conditionsStr = new StringBuilder("index_type_t: \"letterqa\"");
        conditionsStr.append(" AND " + "letter_letter_id_t:");
        conditionsStr.append(letterId);

        if (StringUtils.isNotEmpty(secondLabelIds) && CollectionUtils.isNotEmpty(secondLabelParamList)) {
            conditionsStr.append(" AND " + "letter_question_class_new_id_txt:(").append(secondLabelParamList.get(0));
            for (int i = 1; i < secondLabelParamList.size(); i++) {
                conditionsStr.append(" OR ").append(secondLabelParamList.get(i));
            }
            conditionsStr.append(")");
            if(StringUtils.isNotEmpty(firstLabelId)) {
                conditionsStr.append(" AND " + "letter_question_class_new_id_txt:").append(firstLabelId);
            }
        } else if(StringUtils.isNotEmpty(firstLabelId)) {
            conditionsStr.append(" AND " + "letter_question_class_new_id_txt:");
            conditionsStr.append(firstLabelId);
        }
        String conditionsGroup = "letter_question_class_new_id_txt";
        String orderByName = "letter_question_id_t";
        String orderByOrder = "ASC";
        condition.put(Constant.SEARCH_CONDIATION, conditionsStr.toString());
        condition.put(Constant.SEARCH_FACET_FIELD, conditionsGroup);
        condition.put(Constant.SEARCH_FACET_MIN_COUNT, "1");
        QueryInfo<Map<String, String>> queryInfo = new QueryInfo<>();
        queryInfo.setCondition(condition);
        queryInfo.setStartRow(0);
        queryInfo.setPageSize(2000);
        queryInfo.setOrderByName(orderByName);
        queryInfo.setOrderByOrder(orderByOrder);
        FacetResult<IpoFeedbackIndexDto> facetResult =
                searchServer.searchWithFacet("letterqa", queryInfo, IpoFeedbackIndexDto.class);
        List<StatisticsField> labelList =
                facetResult.getStatisticsFieldMap().get("letter_question_class_new_id_txt");
        List<IpoFeedbackIndexDto> questionList = facetResult.getPage().getData();
        //循环标签，将标签个数赋值
        for (StatisticsField labelDto : labelList) {
            //如果标签id等于父id,则将此标签统计个数赋值给全部标签
            if (firstLabelId.equals(labelDto.getFieldId())) {
                IpoQuestionLabelDto allLabelDto = new IpoQuestionLabelDto();
                allLabelDto.setLabelName("全部");
                allLabelDto.setLabelCount(String.valueOf(labelDto.getCount()));
                secondLabelList.add(0, allLabelDto);
            }

            //如果选择一级标签全部，二级标签的全部要特殊处理
            if (firstLabelId.equals("")) {
                IpoQuestionLabelDto allLabelDto = new IpoQuestionLabelDto();
                allLabelDto.setLabelName("全部");
                allLabelDto.setLabelCount(String.valueOf(questionList.size()));
                secondLabelList.add(0, allLabelDto);
            }

            if (null != secondLabelMap.get(labelDto.getFieldId())) {
                IpoQuestionLabelDto questionLabelDto = new IpoQuestionLabelDto();
                questionLabelDto.setLabelCode(labelDto.getFieldId());
                questionLabelDto.setLabelName(secondLabelMap.get(labelDto.getFieldId()).get("letterClassName"));
                questionLabelDto.setLabelCount(String.valueOf(labelDto.getCount()));
                secondLabelList.add(questionLabelDto);
            }
        }
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


}

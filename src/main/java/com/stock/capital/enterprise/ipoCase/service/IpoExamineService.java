package com.stock.capital.enterprise.ipoCase.service;

import com.google.common.collect.Maps;

import com.stock.capital.enterprise.ipoCase.dao.IpoExamineMapper;
import com.stock.capital.enterprise.ipoCase.dao.IpoFeedbackMapper;
import com.stock.capital.enterprise.ipoCase.dto.IpoExamineBaseDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoExamineDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoFeedbackIndexDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoFeedbackQuestionDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoMemberDto;
import com.stock.core.Constant;
import com.stock.core.dao.DynamicDataSourceHolder;
import com.stock.core.dto.FacetResult;
import com.stock.core.dto.QueryInfo;
import com.stock.core.search.SearchServer;
import com.stock.core.service.BaseService;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class IpoExamineService extends BaseService {
    final static Logger logger = LoggerFactory.getLogger(IpoExamineService.class);
    @Autowired
    private IpoExamineMapper ipoExamineMapper;
    @Autowired
    private IpoFeedbackMapper ipoFeedbackMapper;
    @Autowired
    private SearchServer searchServer;

    public IpoExamineDto selectExamineList(String id) {
        String orgCode = ipoFeedbackMapper.getOrgCode(id);
        IpoExamineDto resultDto = new IpoExamineDto();
        resultDto.setId(id);
        //查询发审会基础信息
        List<IpoExamineBaseDto> baseList = ipoExamineMapper.selectExamineBaseList(id);
        //如果没有发审会信息，则返回空对象
        if(CollectionUtils.isEmpty(baseList)){
            return new IpoExamineDto();
        }
        //处理会议标题
        for (IpoExamineBaseDto baseDto : baseList) {
            String title = baseDto.getRelationFileTitle();
            title = title.substring(0, title.indexOf("会议")) + "工作会议";
            baseDto.setRelationFileTitle(title);
            //查询发审会委员
            String examineDate = baseDto.getExamineDate();
            //查询发审委委员名单
            DynamicDataSourceHolder.setDataSource("dongcai");
            String member = ipoExamineMapper.selectExamineMember(orgCode, examineDate);
            DynamicDataSourceHolder.cleanDataSource();
            baseDto.setMember(member);
        }


        //查询发审会问题及答案列表
        List<IpoFeedbackQuestionDto> questionList = ipoExamineMapper.selectQuestionList(orgCode);
        resultDto.setBaseList(baseList);
        resultDto.setQuestionList(questionList);
        return resultDto;
    }

    /**
     * 查询委员详情
     */
    public List<IpoMemberDto> selectMemberList(String id, String examineDate) {
        //查询发审委委员列表
        String orgCode = ipoFeedbackMapper.getOrgCode(id);
        DynamicDataSourceHolder.setDataSource("dongcai");
        String member = ipoExamineMapper.selectExamineMember(orgCode, examineDate);
        DynamicDataSourceHolder.cleanDataSource();
        List<String> memberList = Arrays.asList(member.split("[,，、]"));
        //根据发审委委员列表和发审会日期，查询委员详细信息
        Date processDate = changeStrToDate(examineDate);
        Date eighteenDate = changeStrToDate("2019-03-07");
        Date seventeenDate = changeStrToDate("2017-10-16");
        String sessionYear = "";
        //根据发审会日期判断发审会委员届次
        if (processDate.compareTo(eighteenDate) > 0) {
            sessionYear = "18";
        } else if (processDate.compareTo(seventeenDate) > 0 && processDate.compareTo(eighteenDate) < 0) {
            sessionYear = "17";
        } else {
            sessionYear = "16";
        }
        List<IpoMemberDto> memberInformationList =
                ipoExamineMapper.selectMemberInformationList(memberList, sessionYear);
        return memberInformationList;
    }

    private Date changeStrToDate(String dateStr) {
        Date date = new Date();
        try {
            date = DateUtils.parseDate(dateStr, "yyyy-MM-dd");
        } catch (ParseException e) {
            e.printStackTrace();
            logger.error("发审会日期格式转换错误");
        }
        return date;
    }

    public IpoExamineDto selectNewExamineList(String id) {
        String orgCode = ipoFeedbackMapper.getOrgCode(id);
        IpoExamineDto resultDto = new IpoExamineDto();
        //查询发审会基础信息
        List<IpoExamineBaseDto> baseList = ipoExamineMapper.selectExamineBaseList(id);
        //如果没有发审会信息，则返回空对象
        if(CollectionUtils.isEmpty(baseList)){
            return new IpoExamineDto();
        }
        //处理会议标题
        for (IpoExamineBaseDto baseDto : baseList) {
            String title = baseDto.getRelationFileTitle();
            title = title.substring(0, title.indexOf("会议")) + "工作会议";
            baseDto.setRelationFileTitle(title);
            //查询发审会委员
            String examineDate = baseDto.getExamineDate();
            //查询发审委委员名单
            DynamicDataSourceHolder.setDataSource("dongcai");
            String member = ipoExamineMapper.selectExamineMember(orgCode, examineDate);
            DynamicDataSourceHolder.cleanDataSource();
            baseDto.setMember(member);
        }
        //查询发审会问题及答案列表

        String letterId = ipoExamineMapper.selectExamineLetterId(orgCode,baseList.get(baseList.size()-1).getExamineDate());


//        List<IpoFeedbackQuestionDto> questionList = ipoExamineMapper.selectQuestionList(orgCode);

        Map<String, String> condition = Maps.newHashMap();
        StringBuilder conditionsStr = new StringBuilder("index_type_t: \"letterqa\"");
        conditionsStr.append(" AND " + "letter_letter_id_t:");
        conditionsStr.append(letterId);
        condition.put(Constant.SEARCH_CONDIATION, conditionsStr.toString());
        condition.put(Constant.SEARCH_FACET_MIN_COUNT, "1");
        QueryInfo<Map<String, String>> queryInfo = new QueryInfo<>();
        queryInfo.setCondition(condition);
        queryInfo.setStartRow(0);
        queryInfo.setPageSize(2000);
        FacetResult<IpoFeedbackIndexDto> facetResult =
                searchServer.searchWithFacet("letterqa", queryInfo, IpoFeedbackIndexDto.class);
        List<IpoFeedbackIndexDto> questionIndexList = facetResult.getPage().getData();
        //定义一个问题列表数组
        List<IpoFeedbackQuestionDto> questionResultList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(questionIndexList)){
            for(IpoFeedbackIndexDto questionDto:questionIndexList){
                IpoFeedbackQuestionDto questionResultDto = new IpoFeedbackQuestionDto();
                questionResultDto.setQuestionId(questionDto.getQuestionId());
                questionResultDto.setQuestion(questionDto.getQuestContents());
                questionResultDto.setAnswer(questionDto.getAnswersContents());
                questionResultDto.setFormatQuestion(questionDto.getQuestionLabelContent());
                questionResultDto.setFormatAnswer(questionDto.getAnswerLabelContent());
                questionResultList.add(questionResultDto);
            }
        }
        resultDto.setBaseList(baseList);
        resultDto.setQuestionList(questionResultList);
        return resultDto;
    }


}

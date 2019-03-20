package com.stock.capital.enterprise.ipoCase.service;

import com.stock.capital.enterprise.ipoCase.dao.IpoFeedbackMapper;
import com.stock.capital.enterprise.ipoCase.dto.IpoFeedbackDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoFeedbackQuestionDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoQuestionLabelDto;
import com.stock.core.service.BaseService;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IpoFeedbackService extends BaseService {
    @Autowired
    private IpoFeedbackMapper ipoFeedbackViewMapper;

    public List<IpoFeedbackDto> selectFeedbackViewList(String id) {
        //根据案例id查询公司的东财内码
        String orgCode = ipoFeedbackViewMapper.getOrgCode(id);
        //查询问题答案列表
        List<IpoFeedbackDto> resultList = ipoFeedbackViewMapper.selectFeedbackViewList(orgCode);

        //计算答案个数
        if(CollectionUtils.isNotEmpty(resultList)){
            for (IpoFeedbackDto dto : resultList) {
                int questionCount = 0;
                int answerCount = 0;
                List<IpoFeedbackQuestionDto> questionList = dto.getQuestionList();
                questionCount = questionList.size();
                if(CollectionUtils.isNotEmpty(questionList)){
                    for(IpoFeedbackQuestionDto questionDto:questionList){
                        if(StringUtils.isNotEmpty(questionDto.getAnswer())){
                            answerCount++;
                        }
                    }
                }
                dto.setQuestionCount(questionCount);
                dto.setAnswerCount(answerCount);
                //查询问题标签一级列表
                List<IpoQuestionLabelDto> firstLabelList = ipoFeedbackViewMapper.selectFirstLabelList(dto.getLetterId());
                dto.setQuestionLabelList(firstLabelList);
            }
        }

        return resultList;
    }

    public List<IpoQuestionLabelDto> secondLabelList(){
        return null;
    }

}

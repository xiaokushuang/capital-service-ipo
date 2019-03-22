package com.stock.capital.enterprise.ipoCase.service;

import com.stock.capital.enterprise.ipoCase.dao.IpoExamineMapper;
import com.stock.capital.enterprise.ipoCase.dao.IpoFeedbackMapper;
import com.stock.capital.enterprise.ipoCase.dto.IpoExamineBaseDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoExamineDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoFeedbackQuestionDto;
import com.stock.core.service.BaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IpoExamineService extends BaseService {
    @Autowired
    private IpoExamineMapper ipoExamineMapper;
    @Autowired
    private IpoFeedbackMapper ipoFeedbackMapper;

    public IpoExamineDto selectExamineList(String id) {
        IpoExamineDto resultDto = new IpoExamineDto();
        resultDto.setId(id);
        //查询发审会基础信息
        List<IpoExamineBaseDto> baseList = ipoExamineMapper.selectExamineBaseList(id);
        //处理会议标题
        for(IpoExamineBaseDto baseDto:baseList){
            String title = baseDto.getRelationFileTitle();
            title = title.substring(0,title.indexOf("会议"))+"工作会议";
            baseDto.setRelationFileTitle(title);
        }
        //查询发审会问题及答案列表
        String orgId = ipoFeedbackMapper.getOrgCode(id);
        List<IpoFeedbackQuestionDto> questionList = ipoExamineMapper.selectQuestionList(orgId);
        resultDto.setBaseList(baseList);
        resultDto.setQuestionList(questionList);
        return resultDto;
    }


}

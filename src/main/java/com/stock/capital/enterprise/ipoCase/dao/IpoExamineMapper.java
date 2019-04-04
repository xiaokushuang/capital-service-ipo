package com.stock.capital.enterprise.ipoCase.dao;

import com.stock.capital.enterprise.ipoCase.dto.IpoExamineBaseDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoFeedbackQuestionDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoMemberDto;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IpoExamineMapper {

    /**
     * 查询发审会基本信息
     */
    List<IpoExamineBaseDto> selectExamineBaseList(String id);

    /**
     * 查询IPO审核结果反馈意见问题
     */
    List<IpoFeedbackQuestionDto> selectQuestionList(String orgCode);

    /**
     * 查询发审委委员
     */
    String selectExamineMember(@Param("orgCode") String orgCode, @Param("examineDate") String examineDate);

    /**
     * 查询发审委委员详细信息
     */
    List<IpoMemberDto> selectMemberInformationList(@Param("memberList") List<String> memberList
            , @Param("sessionYear") String sessionYear);
}

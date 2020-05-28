package com.stock.capital.enterprise.ipoCase.dao;

import com.stock.capital.enterprise.ipoCase.dto.IpoExamineBaseDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoFeedbackDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoFeedbackQuestionDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoMemberDto;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IpoExamineMapper {

    /**
     * 查询发审会基本信息
     * 进程节点
     * '07' 发审会审核 , 核准制
     * '35' 上市委会议,'38' 注册结果,'44' 上市委复审会议, 注册制
     * '72' 挂牌委会议,'90' 核准结果, 新三板
     */
    List<IpoExamineBaseDto> selectExamineBaseList(String id);

    /**
     * 查询IPO审核结果反馈意见问题
     */
    List<IpoFeedbackQuestionDto> selectQuestionList(String orgCode);

    /**
     * 查询发审委委员(从东财查询)(如果自己数据库好用后期删除)
     */
    String selectExamineMember(@Param("orgCode") String orgCode, @Param("examineDate") String examineDate);

    /**
     * 查询发审委委员(从数据库库查询)
     */
    String selectLocalExamineMember(@Param("orgCode") String orgCode, @Param("examineDate") String examineDate);

    /**
     * 查询发审委委员详细信息
     */
    List<IpoMemberDto> selectMemberInformationList(@Param("memberList") List<String> memberList
            , @Param("sessionYear") String sessionYear);

    /**
     * 查询发审委函件Id
     */
    List<IpoFeedbackDto> selectExamineLetterId(@Param("orgCode") String orgCode, @Param("list") List<Date> list);

    /**
     * 查询函件日期
     */
    List<Date> selectLetterDate(String id);

    /**
     * 查询注册反馈意见函件日期
     */
    List<Date> selectRegisterLetterDate(String id);

    /**
     * 查询注册反馈意见
     */
    List<IpoFeedbackDto> selectRegisterLetterId(@Param("orgCode") String orgCode, @Param("list") List<Date> list);
}

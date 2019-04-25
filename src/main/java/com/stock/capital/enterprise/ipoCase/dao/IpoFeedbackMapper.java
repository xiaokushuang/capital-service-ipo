package com.stock.capital.enterprise.ipoCase.dao;

import com.stock.capital.enterprise.ipoCase.dto.IpoFeedbackDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoQuestionLabelDto;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface IpoFeedbackMapper {

    /**
     * 查询ipo反馈意见列表
     */
    List<IpoFeedbackDto> selectFeedbackList(IpoFeedbackDto ipoFeedbackDto);

    /**
     * 查询东财内码
     */
    String getOrgCode(String id);

    /**
     * 查询所属一级标签列表
     */
    List<IpoQuestionLabelDto> selectFirstLabelList(String letterId);

    /**
     * 根据函件问题一级标签查询二级标签
     */
    List<IpoQuestionLabelDto> selectSecondLabelList(@Param("letterId") String letterId, @Param("parentId") String parentId);

    /**
     * 查询问题列表
     */
    List<IpoFeedbackDto> selectQuestionListByLetterId(IpoFeedbackDto ipoFeedbackDto);

    /**
     * 查询反馈意见进程
     */
    List<String> selectFeedbackProcess(String id);

    /**
     * 查询当前公司函件id
     */
    List<String> selectLetterIds(@Param("orgId") String orgId, @Param("processDateList") List<String> processDateList);

    /**
     * 查询问题一级标签
     */
    @MapKey("labelId")
    Map<String, Map<String, String>> selectFirstLabelMap();

    @MapKey("labelId")
    Map<String, Map<String, String>> selectSecondLabelMap(@Param("firstLabelId") String firstLabelId);

    /**
     * 查询进程表上市进程日期
     */
    List<String> selectPublicProcess(String id);
}

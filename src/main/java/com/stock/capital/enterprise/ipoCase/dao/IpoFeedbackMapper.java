package com.stock.capital.enterprise.ipoCase.dao;

import com.stock.capital.enterprise.ipoCase.dto.IpoFeedbackDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoQuestionLabelDto;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IpoFeedbackMapper {

    /**
     * 查询ipo反馈意见列表
     */
    List<IpoFeedbackDto> selectFeedbackViewList(String orgCode);

    /**
     * 查询东财内码
     */
    String getOrgCode(String id);

    /**
     * 查询所属一级标签列表
     */
    List<IpoQuestionLabelDto> selectFirstLabelList(String letterId);
}

package com.stock.capital.enterprise.common.dao;

import com.stock.capital.enterprise.common.entity.LetterRuleMap;
import com.stock.capital.enterprise.common.entity.LetterRuleMapExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface LetterRuleMapMapper {
    long countByExample(LetterRuleMapExample example);

    int deleteByExample(LetterRuleMapExample example);

    int deleteByPrimaryKey(String id);

    int insert(LetterRuleMap record);

    int insertSelective(LetterRuleMap record);

    List<LetterRuleMap> selectByExampleWithRowbounds(LetterRuleMapExample example, RowBounds rowBounds);

    List<LetterRuleMap> selectByExample(LetterRuleMapExample example);

    LetterRuleMap selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") LetterRuleMap record, @Param("example") LetterRuleMapExample example);

    int updateByExample(@Param("record") LetterRuleMap record, @Param("example") LetterRuleMapExample example);

    int updateByPrimaryKeySelective(LetterRuleMap record);

    int updateByPrimaryKey(LetterRuleMap record);
}
package com.stock.capital.enterprise.common.dao;

import com.stock.capital.enterprise.common.entity.LetterDeclareMap;
import com.stock.capital.enterprise.common.entity.LetterDeclareMapExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface LetterDeclareMapMapper {
    long countByExample(LetterDeclareMapExample example);

    int deleteByExample(LetterDeclareMapExample example);

    int deleteByPrimaryKey(String id);

    int insert(LetterDeclareMap record);

    int insertSelective(LetterDeclareMap record);

    List<LetterDeclareMap> selectByExampleWithRowbounds(LetterDeclareMapExample example, RowBounds rowBounds);

    List<LetterDeclareMap> selectByExample(LetterDeclareMapExample example);

    LetterDeclareMap selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") LetterDeclareMap record, @Param("example") LetterDeclareMapExample example);

    int updateByExample(@Param("record") LetterDeclareMap record, @Param("example") LetterDeclareMapExample example);

    int updateByPrimaryKeySelective(LetterDeclareMap record);

    int updateByPrimaryKey(LetterDeclareMap record);
}
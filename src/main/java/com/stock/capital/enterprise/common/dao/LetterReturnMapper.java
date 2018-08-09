package com.stock.capital.enterprise.common.dao;

import com.stock.capital.enterprise.common.entity.LetterReturn;
import com.stock.capital.enterprise.common.entity.LetterReturnExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface LetterReturnMapper {
    long countByExample(LetterReturnExample example);

    int deleteByExample(LetterReturnExample example);

    int deleteByPrimaryKey(String id);

    int insert(LetterReturn record);

    int insertSelective(LetterReturn record);

    List<LetterReturn> selectByExampleWithRowbounds(LetterReturnExample example, RowBounds rowBounds);

    List<LetterReturn> selectByExample(LetterReturnExample example);

    LetterReturn selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") LetterReturn record, @Param("example") LetterReturnExample example);

    int updateByExample(@Param("record") LetterReturn record, @Param("example") LetterReturnExample example);

    int updateByPrimaryKeySelective(LetterReturn record);

    int updateByPrimaryKey(LetterReturn record);
}
package com.stock.capital.enterprise.common.dao;

import com.stock.capital.enterprise.common.entity.Letter;
import com.stock.capital.enterprise.common.entity.LetterExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface LetterMapper {
    long countByExample(LetterExample example);

    int deleteByExample(LetterExample example);

    int deleteByPrimaryKey(String id);

    int insert(Letter record);

    int insertSelective(Letter record);

    List<Letter> selectByExampleWithRowbounds(LetterExample example, RowBounds rowBounds);

    List<Letter> selectByExample(LetterExample example);

    Letter selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Letter record, @Param("example") LetterExample example);

    int updateByExample(@Param("record") Letter record, @Param("example") LetterExample example);

    int updateByPrimaryKeySelective(Letter record);

    int updateByPrimaryKey(Letter record);
}
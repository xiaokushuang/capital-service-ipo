package com.stock.capital.enterprise.common.dao;

import com.stock.capital.enterprise.common.entity.Code;
import com.stock.capital.enterprise.common.entity.CodeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface CodeMapper {
    long countByExample(CodeExample example);

    int deleteByExample(CodeExample example);

    int deleteByPrimaryKey(String id);

    int insert(Code record);

    int insertSelective(Code record);

    List<Code> selectByExampleWithRowbounds(CodeExample example, RowBounds rowBounds);

    List<Code> selectByExample(CodeExample example);

    Code selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Code record, @Param("example") CodeExample example);

    int updateByExample(@Param("record") Code record, @Param("example") CodeExample example);

    int updateByPrimaryKeySelective(Code record);

    int updateByPrimaryKey(Code record);
}
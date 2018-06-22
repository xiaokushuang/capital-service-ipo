package com.stock.capital.enterprise.common.dao;
 
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.stock.capital.enterprise.common.entity.LawIndexTag;
import com.stock.capital.enterprise.common.entity.LawIndexTagExample;
 
public interface LawIndexTagMapper {
    int countByExample(LawIndexTagExample example);

    int deleteByExample(LawIndexTagExample example);

    int deleteByPrimaryKey(String id);

    int insert(LawIndexTag record);

    int insertSelective(LawIndexTag record);

    List<LawIndexTag> selectByExampleWithRowbounds(LawIndexTagExample example, RowBounds rowBounds);

    List<LawIndexTag> selectByExample(LawIndexTagExample example);

    LawIndexTag selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") LawIndexTag record, @Param("example") LawIndexTagExample example);

    int updateByExample(@Param("record") LawIndexTag record, @Param("example") LawIndexTagExample example);

    int updateByPrimaryKeySelective(LawIndexTag record);

    int updateByPrimaryKey(LawIndexTag record);
}
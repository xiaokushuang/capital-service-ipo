package com.stock.capital.enterprise.common.dao;
 
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.stock.capital.enterprise.common.entity.DeclareIdea;
import com.stock.capital.enterprise.common.entity.DeclareIdeaExample;

 
public interface DeclareIdeaMapper {
    int countByExample(DeclareIdeaExample example);

    int deleteByExample(DeclareIdeaExample example);

    int deleteByPrimaryKey(String id);

    int insert(DeclareIdea record);

    int insertSelective(DeclareIdea record);

    List<DeclareIdea> selectByExampleWithRowbounds(DeclareIdeaExample example, RowBounds rowBounds);

    List<DeclareIdea> selectByExample(DeclareIdeaExample example);

    DeclareIdea selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") DeclareIdea record, @Param("example") DeclareIdeaExample example);

    int updateByExample(@Param("record") DeclareIdea record, @Param("example") DeclareIdeaExample example);

    int updateByPrimaryKeySelective(DeclareIdea record);

    int updateByPrimaryKey(DeclareIdea record);
}
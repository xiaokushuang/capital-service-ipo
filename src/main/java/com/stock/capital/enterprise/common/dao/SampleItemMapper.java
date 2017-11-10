package com.stock.capital.enterprise.common.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.stock.capital.enterprise.common.entity.SampleItem;
import com.stock.capital.enterprise.common.entity.SampleItemExample;

public interface SampleItemMapper {
    int countByExample(SampleItemExample example);

    int deleteByExample(SampleItemExample example);

    int deleteByPrimaryKey(String id);

    int insert(SampleItem record);

    int insertSelective(SampleItem record);

    List<SampleItem> selectByExample(SampleItemExample example);

    SampleItem selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SampleItem record, @Param("example") SampleItemExample example);

    int updateByExample(@Param("record") SampleItem record, @Param("example") SampleItemExample example);

    int updateByPrimaryKeySelective(SampleItem record);

    int updateByPrimaryKey(SampleItem record);
}
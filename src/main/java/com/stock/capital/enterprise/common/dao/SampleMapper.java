package com.stock.capital.enterprise.common.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.stock.capital.enterprise.common.entity.Sample;
import com.stock.capital.enterprise.common.entity.SampleExample;

public interface SampleMapper {
    int countByExample(SampleExample example);

    int deleteByExample(SampleExample example);

    int deleteByPrimaryKey(String id);

    int insert(Sample record);

    int insertSelective(Sample record);

    List<Sample> selectByExample(SampleExample example);

    Sample selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Sample record, @Param("example") SampleExample example);

    int updateByExample(@Param("record") Sample record, @Param("example") SampleExample example);

    int updateByPrimaryKeySelective(Sample record);

    int updateByPrimaryKey(Sample record);
}
package com.stock.capital.enterprise.common.dao;

import com.stock.capital.enterprise.common.entity.LabelRtnFile;
import com.stock.capital.enterprise.common.entity.LabelRtnFileExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface LabelRtnFileMapper {
    long countByExample(LabelRtnFileExample example);

    int deleteByExample(LabelRtnFileExample example);

    int deleteByPrimaryKey(String id);

    int insert(LabelRtnFile record);

    int insertSelective(LabelRtnFile record);

    List<LabelRtnFile> selectByExampleWithRowbounds(LabelRtnFileExample example, RowBounds rowBounds);

    List<LabelRtnFile> selectByExample(LabelRtnFileExample example);

    LabelRtnFile selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") LabelRtnFile record, @Param("example") LabelRtnFileExample example);

    int updateByExample(@Param("record") LabelRtnFile record, @Param("example") LabelRtnFileExample example);

    int updateByPrimaryKeySelective(LabelRtnFile record);

    int updateByPrimaryKey(LabelRtnFile record);
}
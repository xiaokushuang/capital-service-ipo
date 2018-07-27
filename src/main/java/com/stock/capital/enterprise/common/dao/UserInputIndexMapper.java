package com.stock.capital.enterprise.common.dao;

import com.stock.capital.enterprise.common.entity.UserInputIndex;
import com.stock.capital.enterprise.common.entity.UserInputIndexExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface UserInputIndexMapper {
    long countByExample(UserInputIndexExample example);

    int deleteByExample(UserInputIndexExample example);

    int deleteByPrimaryKey(String id);

    int insert(UserInputIndex record);

    int insertSelective(UserInputIndex record);

    List<UserInputIndex> selectByExampleWithRowbounds(UserInputIndexExample example, RowBounds rowBounds);

    List<UserInputIndex> selectByExample(UserInputIndexExample example);

    UserInputIndex selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") UserInputIndex record, @Param("example") UserInputIndexExample example);

    int updateByExample(@Param("record") UserInputIndex record, @Param("example") UserInputIndexExample example);

    int updateByPrimaryKeySelective(UserInputIndex record);

    int updateByPrimaryKey(UserInputIndex record);
}
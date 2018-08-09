package com.stock.capital.enterprise.common.dao;

import com.stock.capital.enterprise.common.entity.Favorite;
import com.stock.capital.enterprise.common.entity.FavoriteExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface FavoriteMapper {
    long countByExample(FavoriteExample example);

    int deleteByExample(FavoriteExample example);

    int deleteByPrimaryKey(String id);

    int insert(Favorite record);

    int insertSelective(Favorite record);

    List<Favorite> selectByExampleWithRowbounds(FavoriteExample example, RowBounds rowBounds);

    List<Favorite> selectByExample(FavoriteExample example);

    Favorite selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Favorite record, @Param("example") FavoriteExample example);

    int updateByExample(@Param("record") Favorite record, @Param("example") FavoriteExample example);

    int updateByPrimaryKeySelective(Favorite record);

    int updateByPrimaryKey(Favorite record);
}
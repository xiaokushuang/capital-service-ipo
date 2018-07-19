package com.stock.capital.enterprise.common.dao;

import com.stock.capital.enterprise.common.entity.ViolateManage;
import com.stock.capital.enterprise.common.entity.ViolateManageExample;
import com.stock.capital.enterprise.common.entity.ViolateManageWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ViolateManageMapper {
    long countByExample(ViolateManageExample example);

    int deleteByExample(ViolateManageExample example);

    int deleteByPrimaryKey(String id);

    int insert(ViolateManageWithBLOBs record);

    int insertSelective(ViolateManageWithBLOBs record);

    List<ViolateManageWithBLOBs> selectByExampleWithBLOBsWithRowbounds(ViolateManageExample example, RowBounds rowBounds);

    List<ViolateManageWithBLOBs> selectByExampleWithBLOBs(ViolateManageExample example);

    List<ViolateManage> selectByExampleWithRowbounds(ViolateManageExample example, RowBounds rowBounds);

    List<ViolateManage> selectByExample(ViolateManageExample example);

    ViolateManageWithBLOBs selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ViolateManageWithBLOBs record, @Param("example") ViolateManageExample example);

    int updateByExampleWithBLOBs(@Param("record") ViolateManageWithBLOBs record, @Param("example") ViolateManageExample example);

    int updateByExample(@Param("record") ViolateManage record, @Param("example") ViolateManageExample example);

    int updateByPrimaryKeySelective(ViolateManageWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ViolateManageWithBLOBs record);

    int updateByPrimaryKey(ViolateManage record);
}
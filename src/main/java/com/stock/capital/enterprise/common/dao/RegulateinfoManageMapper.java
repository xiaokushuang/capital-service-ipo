package com.stock.capital.enterprise.common.dao;

import com.stock.capital.enterprise.common.entity.RegulateinfoManage;
import com.stock.capital.enterprise.common.entity.RegulateinfoManageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface RegulateinfoManageMapper {
    long countByExample(RegulateinfoManageExample example);

    int deleteByExample(RegulateinfoManageExample example);

    int deleteByPrimaryKey(String id);

    int insert(RegulateinfoManage record);

    int insertSelective(RegulateinfoManage record);

    List<RegulateinfoManage> selectByExampleWithBLOBsWithRowbounds(RegulateinfoManageExample example, RowBounds rowBounds);

    List<RegulateinfoManage> selectByExampleWithBLOBs(RegulateinfoManageExample example);

    List<RegulateinfoManage> selectByExampleWithRowbounds(RegulateinfoManageExample example, RowBounds rowBounds);

    List<RegulateinfoManage> selectByExample(RegulateinfoManageExample example);

    RegulateinfoManage selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") RegulateinfoManage record, @Param("example") RegulateinfoManageExample example);

    int updateByExampleWithBLOBs(@Param("record") RegulateinfoManage record, @Param("example") RegulateinfoManageExample example);

    int updateByExample(@Param("record") RegulateinfoManage record, @Param("example") RegulateinfoManageExample example);

    int updateByPrimaryKeySelective(RegulateinfoManage record);

    int updateByPrimaryKeyWithBLOBs(RegulateinfoManage record);

    int updateByPrimaryKey(RegulateinfoManage record);
}
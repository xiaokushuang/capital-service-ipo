package com.stock.capital.enterprise.ipoCase.dao;

import com.stock.capital.enterprise.ipoCase.dto.*;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IpoProcessMapper {
    /**
     * 查询进程树
     */
    TreeTypeProgressDto selectProcessList(String id);

    /**
     * 查询股份公司设立时间
     */
    String getPublishDate(String id);

    /**
     * 查询文件信息
     */
    IpoFileRelationDto selectFileDto(String id);

    /**
     * 查询拆分上市文件
     */
    IpoFileRelationDto selectSplitFileDto(String id);

    /**
     * 查询conf_labal表里数据
     * @param labelCode label_code
     * @param ids id
     * @return
     */
    List<String> selectConfLabelBy(@Param("labelCode") String labelCode, @Param("ids") String ids);

    /**
     * 查询法规的id
     * @return
     */
    IpoCaseListBo querylawId();

    /**
     * 法规某一条id
     * @param lawId 法规id
     * @param title 小标题
     * @return 某一条id
     */
    String queryLawNoId(@Param("lawId")String lawId, @Param("title") String title);

    IpoProListDto getIssueData(@Param("id") String id);
}

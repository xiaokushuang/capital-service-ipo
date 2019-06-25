package com.stock.capital.enterprise.ipoInterfaceH5.dao;

import java.util.List;
import java.util.Map;

import com.stock.capital.enterprise.ipoInterfaceH5.dto.IpoWechatPermisionDto;
import org.apache.ibatis.annotations.Param;
/**
*  @author yangj
*/
public interface IpoWechatPermisionBizMapper {


    List<IpoWechatPermisionDto> queryByUnionidOrOpenid(Map<String, Object> userInfoMap);

    void insert(Map<String, Object> userInfoMap);

    void update(Map<String, Object> userInfoMap);

    void submitReplay(Map<String, Object> map);

    List<Map<String,Object>> getCommentList(@Param("caseid") String caseid);

    List<Map<String,Object>> isFabulousYes(Map<String, Object> map);

    void fabulousYes(Map<String, Object> map);

    void fabulousNo(Map<String, Object> map);

    int fabulousCount(Map<String, Object> param);

    List<Map<String,Object>> isCollectionYes(Map<String, Object> map);

    void collectionYes(Map<String, Object> map);

    void collectionNo(Map<String, Object> map);

    int collectionCount(Map<String, Object> param);
}
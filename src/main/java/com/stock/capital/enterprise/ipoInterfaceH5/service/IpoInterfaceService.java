package com.stock.capital.enterprise.ipoInterfaceH5.service;

import com.google.common.collect.Maps;
import com.netflix.discovery.converters.Auto;
import com.stock.capital.enterprise.common.constant.Global;
import com.stock.capital.enterprise.ipoCase.dao.IpoCaseListMapper;
import com.stock.capital.enterprise.ipoCase.dto.RegTreeDto;
import com.stock.core.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = { Exception.class })
public class IpoInterfaceService extends BaseService {


    @Autowired
    private IpoCaseListMapper ipoCaseListMapper;

    /**
     * 获取下拉框数据
     * @return
     */
    public Map<String,Object> querySelectData() {
        Map<String,Object> result = Maps.newHashMap();
//        所属行业（证监局）
        List<RegTreeDto> sfcList = ipoCaseListMapper.getTreeTagByCode(Global.IPO_SFC);
//        证监会行业
        List<RegTreeDto> industryCrscList = ipoCaseListMapper.getLabelByCode(Global.IPO_SFC_INDUSTRY);
//        审核结果
        List<RegTreeDto> verifyResultList = ipoCaseListMapper.getLabelByCode(Global.IPO_VERIFY_RESULT);

        result.put("sfcList",sfcList);
        result.put("industryCrscList",industryCrscList);
        result.put("verifyResultList",verifyResultList);
        return result;
    }
}

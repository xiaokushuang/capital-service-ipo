package com.stock.capital.enterprise.ipoCoachCase.service;

import com.stock.capital.enterprise.ipoCase.dto.IpoFileRelationDto;
import com.stock.capital.enterprise.ipoCase.dto.IpoProListDto;
import com.stock.capital.enterprise.ipoCase.dto.TreeTypeProgressDto;
import com.stock.capital.enterprise.ipoCoachCase.dao.IpoCoachCaseDetailBizMapper;
import com.stock.capital.enterprise.ipoCoachCase.dto.IntermediaryOrgDto;
import com.stock.capital.enterprise.ipoCoachCase.dto.IpoCoachCaseDto;
import com.stock.capital.enterprise.ipoCoachCase.dto.IpoRelatedCaseDto;
import com.stock.capital.enterprise.ipoCoachCase.dto.OtherMarketInfoDto;
import com.stock.core.service.BaseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static com.stock.core.util.DateUtil.YYYY_MM_DD;
import static com.stock.core.util.DateUtil.getDate;

@Service
@Transactional(rollbackFor = {Exception.class})
public class IpoCoachCaseDetailService extends BaseService {

    @Autowired
    IpoCoachCaseDetailBizMapper ipoCoachCaseDetailBizMapper;

    @Value("#{app['file.viewPath']}")
    private String fileViewPath;

    public IpoCoachCaseDto queryCoachTitleInfo(String id) {
        IpoCoachCaseDto ipoCoachCaseDto = new IpoCoachCaseDto();
        ipoCoachCaseDto = ipoCoachCaseDetailBizMapper.queryCoachBasicInfo(id);
//        List<IpoProgressDto> intermediaryOrgDtoList = ipoCoachCaseDetailBizMapper.queryCoachProgress(id);
        return ipoCoachCaseDto;
    }

    public IpoCoachCaseDto queryCoachBasicInfo(String id) {
        IpoCoachCaseDto ipoCoachCaseDto = new IpoCoachCaseDto();
        ipoCoachCaseDto = ipoCoachCaseDetailBizMapper.queryCoachBasicInfo(id);
        List<IpoRelatedCaseDto> ipoRelatedCaseDtoList = ipoCoachCaseDetailBizMapper.getRelaIpoCaseList(id);
        // 查询关联的ipo案例
        if (ipoRelatedCaseDtoList != null) {
            ipoCoachCaseDto.setRelatedCaseDtoList(ipoRelatedCaseDtoList);
        }else{
            ipoCoachCaseDto.setRelatedCaseDtoList(new ArrayList<IpoRelatedCaseDto>());
        }
        //查询中介机构数据
        List<IntermediaryOrgDto> intermediaryOrgDtoList = ipoCoachCaseDetailBizMapper.selectOrgByBid(id);
        if (intermediaryOrgDtoList != null && intermediaryOrgDtoList.size() > 0) {
            ipoCoachCaseDto.setIntermediaryOrgList(intermediaryOrgDtoList);
        }
        //查询其他资本市场情况数据
        List<OtherMarketInfoDto> otherMarketInfoList =
                ipoCoachCaseDetailBizMapper.selectMarketByBid(id);
        if (otherMarketInfoList != null && !otherMarketInfoList.isEmpty() && otherMarketInfoList.size() > 0) {
            ipoCoachCaseDto.setOtherMarketInfoList(otherMarketInfoList);
        }
        return ipoCoachCaseDto;
    }

    public TreeTypeProgressDto queryIpoProcessByCaseId(String caseId) {
        TreeTypeProgressDto treeTypeProgressDto = new TreeTypeProgressDto();
        treeTypeProgressDto = ipoCoachCaseDetailBizMapper.queryIpoProcessByCaseId(caseId);
        if (treeTypeProgressDto == null) {
            treeTypeProgressDto = new TreeTypeProgressDto();
        } else {
            List<IpoProListDto> list = new ArrayList<>();
            list = treeTypeProgressDto.getTreeList().get(0).getProList();
            for (int i = 0; i < list.size(); i++) {
                if (i != list.size() - 1) {
                    String chajitian = getTimeDistance(list.get(i + 1).getRelaList().get(0).getPublishTime(), list.get(i).getRelaList().get(0).getPublishTime());
                    list.get(i).setLastDay(chajitian);
                }
                String time = "";
                for (IpoFileRelationDto item : list.get(i).getRelaList()) {
                    if ("htm".equals(item.getSuffix()) || "html".equals(item.getSuffix())) {
                        String baseUrl = fileViewPath + "open/ipoFile/" + item.getRelaId() + ".png";
                        item.setBaseUrl(baseUrl);
                    } else {
                        String baseUrl = fileViewPath + "open/ipoFile/" + item.getRelaId() + "." + item.getSuffix();
                        item.setBaseUrl(baseUrl);
                    }
                    if (StringUtils.isNotEmpty(item.getPublishTime())) {
                        list.get(i).setProcessTime(item.getPublishTime());
                        time = item.getPublishTime();
                    } else {
                        item.setPublishTime(time);
                    }
                }
            }
        }
        return treeTypeProgressDto;
    }


    /**
     * 计算日期相差天数
     *
     * @param
     * @return
     */
    public String getTimeDistance(String beginDate, String endDate) {
        Date begin = getDate(beginDate, YYYY_MM_DD);
        Date end = getDate(endDate, YYYY_MM_DD);
        Calendar beginCalendar = Calendar.getInstance();
        beginCalendar.setTime(begin);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(end);
        long beginTime = beginCalendar.getTime().getTime();
        long endTime = endCalendar.getTime().getTime();
        int betweenDays = (int) ((endTime - beginTime) / (1000 * 60 * 60 * 24));
        endCalendar.add(Calendar.DAY_OF_MONTH, -betweenDays);
        endCalendar.add(Calendar.DAY_OF_MONTH, -1);
        if (beginCalendar.get(Calendar.DAY_OF_MONTH) == endCalendar.get(Calendar.DAY_OF_MONTH))
            return Integer.toString(betweenDays + 1) + "天";
        else
            return Integer.toString(betweenDays + 0) + "天";
    }
}

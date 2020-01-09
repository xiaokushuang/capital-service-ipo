package com.stock.capital.enterprise.ipoCase.service;

import com.stock.capital.enterprise.ipoCase.dao.IpoInvestItemMapper;
import com.stock.capital.enterprise.ipoCase.dto.IpoInvestItemDto;
import com.stock.core.service.BaseService;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

@Service
public class IpoInvestService extends BaseService {
    @Autowired
    private IpoInvestItemMapper ipoInvestItemMapper;

    public List<IpoInvestItemDto> selectInvestItem(String id) {
        List<IpoInvestItemDto> investItemList = ipoInvestItemMapper.selectIpoInvestItem(id);
        //创建一条数据统计合计
        IpoInvestItemDto sumDto = new IpoInvestItemDto();
        BigDecimal sumPlan = new BigDecimal("0");
        BigDecimal sumTotal = new BigDecimal("0");
        BigDecimal sumPre = new BigDecimal("0");
        BigDecimal sumRate = new BigDecimal("0");
        if(CollectionUtils.isNotEmpty(investItemList)){
            BigDecimal investPlanLimit = investItemList.get(0).getInvestPlanLimit();
            for (IpoInvestItemDto dto : investItemList) {
                BigDecimal rate = new BigDecimal("0");
                if(dto.getInvestPlanLimit().compareTo(BigDecimal.ZERO) != 0){
                    rate = dto.getInvestPlan().divide(dto.getInvestPlanLimit(), 4, BigDecimal.ROUND_HALF_UP);
                    dto.setInvestRateStr(String.valueOf(rate.multiply(new BigDecimal("100"),
                            new MathContext(4, RoundingMode.HALF_UP))) + "%");
                }
                sumPlan = sumPlan.add(dto.getInvestPlan());
                sumTotal = sumTotal.add(dto.getInvestTotal());
                sumPre = sumPre.add(dto.getInvestPre());
//                sumRate = sumRate.add(rate);
            }
            sumRate = sumTotal.divide(investPlanLimit,4,BigDecimal.ROUND_HALF_UP);
        }
        if(CollectionUtils.isNotEmpty(investItemList)){
            sumDto.setItemName("总计");
            sumDto.setInvestTotal(sumTotal);
            sumDto.setInvestPlan(sumPlan);
            sumDto.setInvestPre(sumPre);
            if(sumRate.compareTo(BigDecimal.ZERO) != 0){
                sumDto.setInvestRateStr(String.valueOf(sumRate.multiply(new BigDecimal("100")
                        , new MathContext(4, RoundingMode.HALF_UP))) + "%");
            }
            investItemList.add(sumDto);
        }
        return investItemList;
    }

}

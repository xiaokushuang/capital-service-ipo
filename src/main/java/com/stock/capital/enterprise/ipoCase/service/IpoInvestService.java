package com.stock.capital.enterprise.ipoCase.service;

import com.stock.capital.enterprise.ipoCase.dao.IpoInvestItemMapper;
import com.stock.capital.enterprise.ipoCase.dto.IpoInvestItemDto;
import com.stock.core.service.BaseService;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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
            if (investPlanLimit.compareTo(BigDecimal.ZERO) != 0){
                sumRate = sumPlan.divide(investPlanLimit,4,BigDecimal.ROUND_HALF_UP);
            }else {
                sumRate=new BigDecimal("0") ;
            }

        }
        if(CollectionUtils.isNotEmpty(investItemList)){
            sumDto.setItemName("总计");
            sumDto.setInvestTotal(sumTotal);
            sumDto.setInvestPlan(sumPlan);
            sumDto.setInvestPre(sumPre);
            if(sumRate.compareTo(BigDecimal.ZERO) != 0){
                /**
                 * 大于100 则小数点显示2位有效小数， 小于 100 同样显示 2位 有效小数
                 */
                BigDecimal sum = sumRate.multiply(new BigDecimal("100"));
                BigDecimal tmp = new BigDecimal("100");
                int priecision = 4;
                if (sum.compareTo(tmp) == 1){
                    priecision = 5;
                }
                sumDto.setInvestRateStr(String.valueOf(sumRate.multiply(new BigDecimal("100")
                        , new MathContext(priecision, RoundingMode.HALF_UP))) + "%");
            }
            investItemList.add(sumDto);
        }
        return investItemList;
    }

}

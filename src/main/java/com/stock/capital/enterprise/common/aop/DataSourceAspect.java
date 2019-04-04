package com.stock.capital.enterprise.common.aop;

import com.stock.core.dao.DynamicDataSourceHolder;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Component
@Aspect
@Order(-1)
public class DataSourceAspect {

   @Pointcut("@within(com.stock.capital.enterprise.common.aop.DataSource) || @annotation(com.stock.capital.enterprise.common.aop.DataSource)")
    public void pointCut(){

    }

    @After("pointCut()")
    public void doAfter(){
        DynamicDataSourceHolder.cleanDataSource();
    }
}

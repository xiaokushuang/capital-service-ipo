package com.stock.capital.enterprise.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.stock.capital.enterprise.**.dao")
public class PersistenceConfigurer {

}


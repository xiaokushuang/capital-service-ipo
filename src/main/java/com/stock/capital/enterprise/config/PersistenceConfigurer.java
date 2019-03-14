package com.stock.capital.enterprise.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.stock.capital.enterprise.common.presistence.DataSourceEnum;
import com.stock.capital.enterprise.common.presistence.MultipleDataSource;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
@MapperScan("com.stock.capital.enterprise.**.dao")
public class PersistenceConfigurer {
    @Value("${spring.datasource.druid.cloud.url}")
    private String cloudUrl;

    @Value("${spring.datasource.druid.cloud.username}")
    private String cloudUsername;

    @Value("${spring.datasource.druid.cloud.password}")
    private String cloudPassword;

    @Value("${spring.datasource.druid.dongcai.url}")
    private String dongcaiUrl;

    @Value("${spring.datasource.druid.dongcai.username}")
    private String dongcaiUsername;

    @Value("${spring.datasource.druid.dongcai.password}")
    private String dongcaiPassword;

    @Value("${spring.datasource.druid.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.druid.initial-size}")
    private int initialSize;

    @Value("${spring.datasource.druid.max-active}")
    private int maxActive;

    @Value("${spring.datasource.druid.min-idle}")
    private int minIdle;

    @Value("${spring.datasource.druid.max-wait}")
    private long maxWait;

    @Value("${spring.datasource.druid.validation-query}")
    private String validationQuery;

    @Value("${spring.datasource.druid.validation-query-timeout}")
    private int validationQueryTimeout;


    private DataSource getDataSource(String url, String username, String password) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setInitialSize(initialSize);
        dataSource.setMaxActive(maxActive);
        dataSource.setMinIdle(minIdle);
        dataSource.setMaxWait(maxWait);
        dataSource.setValidationQuery(validationQuery);
        dataSource.setValidationQueryTimeout(validationQueryTimeout);
        return dataSource;
    }

    /**
     * 动态数据源配置
     * @return
     */
    @Bean
    @Primary
    public DataSource dynamicDataSource() {
        MultipleDataSource multipleDataSource = new MultipleDataSource();
        Map< Object, Object > targetDataSources = new HashMap<>();
        DataSource cloud = getDataSource(cloudUrl, cloudUsername, cloudPassword);
        DataSource dongcai = getDataSource(dongcaiUrl, dongcaiUsername, dongcaiPassword);
        targetDataSources.put(DataSourceEnum.CLOUD.getValue(), cloud);
        targetDataSources.put(DataSourceEnum.DONGCAI.getValue(), dongcai);
        //添加数据源
        multipleDataSource.setTargetDataSources(targetDataSources);
        //设置默认数据源
        multipleDataSource.setDefaultTargetDataSource(cloud);
        return multipleDataSource;
    }

    @Bean(name = "transactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("dynamicDataSource")DataSource dynamicDataSource) {
        return new DataSourceTransactionManager(dynamicDataSource);
    }
}


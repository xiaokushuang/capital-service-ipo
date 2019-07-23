package com.stock.capital.enterprise.config;

import com.stock.core.security.TenantInfoInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.concurrent.TimeUnit;

@Configuration
@ComponentScan(basePackages = "com.stock", useDefaultFilters = false, includeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = { Controller.class, ControllerAdvice.class }) })
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {


    /**
     * 描述 : <资源访问处理器>. <br>
     * <p>
     * <可以在jsp中使用/static/**的方式访问/WEB-INF/static/下的内容>
     * </p>
     * 
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
        registry.addResourceHandler("/resources/**/*.js").addResourceLocations("/WEB-INF/views/");
    }

    /**
     * 描述 : <拦截器>. <br>
     * <p>
     * <api访问拦截器>
     * </p>
     *
     * @return
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tenantInfoInterceptor()).addPathPatterns("/**").excludePathPatterns("/error");
    }


}
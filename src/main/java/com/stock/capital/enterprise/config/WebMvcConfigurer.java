package com.stock.capital.enterprise.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.stock.core.exception.ContentNegotiatingExceptionResolver;

@Configuration
@ComponentScan(basePackages = "com.stock", useDefaultFilters = false, includeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = { Controller.class, ControllerAdvice.class }) })
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

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
     * 描述 : <文件上传处理器>. <br>
     * <p>
     * <使用方法说明>
     * </p>
     * 
     * @return
     */
    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver commonsMultipartResolver() {
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        commonsMultipartResolver.setMaxUploadSize(20971520);// 20MB
        return commonsMultipartResolver;
    }
    
    /**
     * 描述 : <异常处理器>. <br>
     * <p>
     * <系统运行时遇到指定的异常将会跳转到指定的页面>
     * </p>
     * 
     * @return
     */
    @Bean(name = "exceptionResolver")
    public ContentNegotiatingExceptionResolver contentNegotiatingExceptionResolver() {
        ContentNegotiatingExceptionResolver contentNegotiatingExceptionResolver = new ContentNegotiatingExceptionResolver();
        contentNegotiatingExceptionResolver.setOrder(Ordered.HIGHEST_PRECEDENCE);
        contentNegotiatingExceptionResolver.setDefaultErrorView("error/500");
        contentNegotiatingExceptionResolver.setNotFoundErrorView("error/404");
        contentNegotiatingExceptionResolver.setExceptionAttribute("exception");
        return contentNegotiatingExceptionResolver;
    }

}
package com.stock.capital.enterprise.config;

import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.session.web.http.DefaultCookieSerializer;
import org.springframework.web.client.RestTemplate;

import com.google.common.base.Charsets;
import com.stock.capital.enterprise.common.tag.EnterprisePagerBuilder;
import com.stock.core.Constant;
import com.stock.core.log.LogEventListener;
import com.stock.core.misc.CodeListBean;
import com.stock.core.misc.EventBusFactoryBean;
import com.stock.core.misc.StaticListBean;
import com.stock.core.rest.RestClient;

@Configuration
public class RootConfigurer {

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
        ConcurrentMapCache concurrentMapCache = new ConcurrentMapCache(Constant.DEFAULT_CACHE);
        Set<Cache> caches = new HashSet<>();
        caches.add(concurrentMapCache);
        simpleCacheManager.setCaches(caches);
        return simpleCacheManager;
    }

    @Bean
//    @LoadBalanced
    public RestTemplate restTemplate() {
        PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
        poolingHttpClientConnectionManager.setMaxTotal(10);
        poolingHttpClientConnectionManager.setDefaultMaxPerRoute(5);
        HttpClient httpClient = HttpClientBuilder.create().setConnectionManager(poolingHttpClientConnectionManager)
                .build();
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(
                httpClient);
        clientHttpRequestFactory.setConnectTimeout(30000);
        clientHttpRequestFactory.setReadTimeout(30000);
        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);
        List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();
        Iterator<HttpMessageConverter<?>> iterator = messageConverters.iterator();
        while (iterator.hasNext()) {
            HttpMessageConverter<?> converter = iterator.next();
            if (converter instanceof StringHttpMessageConverter) {
                iterator.remove();
            }
        }
        messageConverters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return restTemplate;
    }

    @Bean
    public RestClient restClient() {
        RestClient simpleCacheManager = new RestClient(restTemplate());
        return simpleCacheManager;
    }

    @Bean
    public CodeListBean codeListBean() {
        return new CodeListBean();
    }

    @Bean
    public StaticListBean staticListBean() {
        return new StaticListBean();
    }

    @Bean
    public EnterprisePagerBuilder pagerBuilder() {
        return new EnterprisePagerBuilder();
    }

    @Bean
    public EventBusFactoryBean eventBus() {
        return new EventBusFactoryBean();
    }

    @Bean
    public LogEventListener logEventListener() {
        return new LogEventListener();
    }

    @Bean
    public ReloadableResourceBundleMessageSource appMessageSource() {
        ReloadableResourceBundleMessageSource appMessageSource = new ReloadableResourceBundleMessageSource();
        appMessageSource.setBasename("classpath:/messages");
        appMessageSource.setUseCodeAsDefaultMessage(true);
        appMessageSource.setCacheSeconds(5);
        appMessageSource.setDefaultEncoding(Charsets.UTF_8.name());
        return appMessageSource;
    }

    @Bean
    public DefaultCookieSerializer springSessionDefaultRedisSerializer() {
        DefaultCookieSerializer defaultCookieSerializer = new DefaultCookieSerializer();
        defaultCookieSerializer.setCookieMaxAge(-1);
        defaultCookieSerializer.setCookiePath("/");
        defaultCookieSerializer.setCookieName("ipo");
        defaultCookieSerializer.setUseHttpOnlyCookie(true);
        return defaultCookieSerializer;
    }
    
}

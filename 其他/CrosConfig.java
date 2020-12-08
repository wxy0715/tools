package com.hotel.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author wxy
 * @data 2020/3/24 - 18:12
 **/
@Configuration
public class CrosConfig extends  WebMvcConfigurationSupport {
    @Override
    protected void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                //放行哪些原始域
                .allowedOrigins("*")
                //是否发送Cookie信息
                .allowCredentials(true)
                .maxAge(3600)
                //放行哪些原始域(请求方式)
                .allowedMethods("GET", "POST", "PUT", "HEAD","DELETE")
                //放行哪些原始域(头部信息)
                .allowedHeaders("*");
    }
/*    @Override
    protected void configureAsyncSupport(AsyncSupportConfigurer configurer){
        configurer.setTaskExecutor(new ConcurrentTaskExecutor(Executors.newFixedThreadPool(3)));
        configurer.setDefaultTimeout(30000);
    }*/
}

package com.fans.config;

import com.fans.interceptor.AuthActionInterceptor;
import com.fans.interceptor.AuthInterceptor;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;

/**
 * @ClassName WebConfiguration
 * @Description:
 * @Author fan
 * @Date 2019-04-07 12:06
 * @Version 1.0
 **/
@Configuration
public class WebConfiguration extends WebMvcConfigurationSupport {
    @Resource(name = "authInterceptor")
    private AuthInterceptor authInterceptor;
    @Resource(name = "authActionInterceptor")
    private AuthActionInterceptor authActionInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/static/");
        registry.addInterceptor(authActionInterceptor)
                .addPathPatterns("/house/toAdd")
                .addPathPatterns("/accounts/profile").addPathPatterns("/accounts/profileSubmit")
                .addPathPatterns("/house/bookmarked").addPathPatterns("/house/del")
                .addPathPatterns("/house/ownlist").addPathPatterns("/house/add")
                .addPathPatterns("/house/toAdd").addPathPatterns("/agency/agentMsg")
                .addPathPatterns("/comment/leaveComment").addPathPatterns("/comment/leaveBlogComment");
        super.addInterceptors(registry);
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }
}

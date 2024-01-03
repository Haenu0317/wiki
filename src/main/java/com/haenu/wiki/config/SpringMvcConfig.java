package com.haenu.wiki.config;

import com.haenu.wiki.interceptor.ActionInterceptor;
import com.haenu.wiki.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {

    @Resource
    private LoginInterceptor logInterceptor;

    @Resource
    private ActionInterceptor actionInterceptor;

    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(logInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/user/login")
                .excludePathPatterns("/user/save")
                .excludePathPatterns("/category/all")
                .excludePathPatterns("/ebook/list")
                .excludePathPatterns("/doc/all/**")
                .excludePathPatterns("/doc.html/**")
                .excludePathPatterns("/doc.html#/**")
                .excludePathPatterns("/swagger-ui.html")
                //让后台文档不可用可以直接注释掉这两个v2 v3
                .excludePathPatterns("/v2/api-docs")    // swagger api json
                .excludePathPatterns("/v3/api-docs")
                .excludePathPatterns("/swagger-resources/configuration/ui")// 用来获取支持的动作
                .excludePathPatterns("/swagger-resources")       // 用来获取api-docs的URI
                .excludePathPatterns("/swagger-resources/configuration/security")    // 安全选项
                .excludePathPatterns("/swagger-resources/**")
                //补充路径，近期在搭建swagger接口文档时，通过浏览器控制台发现该/webjars路径下的文件被拦截，故加上此过滤条件即可。(2020-10-23)
                .excludePathPatterns("/webjars/**")
                .excludePathPatterns("/error")
                .excludePathPatterns("/redis/**")
                .excludePathPatterns("/doc/all/**")
                .excludePathPatterns("/ebook-snapshot/**")
                .excludePathPatterns("/doc/find-content/**");


    }
}

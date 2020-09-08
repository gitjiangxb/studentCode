package com.nxtele.did.client.config;

import com.nxtele.did.client.config.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebLoginConfig implements WebMvcConfigurer{


    /**
    *                       注意：一定要把 登录拦截 bean 先注入到ioc中  要不然 在拦截器中注入redis的bean就会报错
    * @author gc
    * @date   2020/7/7 15:43
    * @param
    * @return
    */
    @Bean
    LoginInterceptor localInterceptor() {
        return new LoginInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册需要拦截的请求  拦截该项目下所有的请求
        registry.addInterceptor(localInterceptor()).addPathPatterns("/**");
        WebMvcConfigurer.super.addInterceptors(registry);
    }


}

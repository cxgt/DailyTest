package com.example.dailytest.demos.Config;

import com.example.dailytest.demos.Filter.MyFliter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author chenxin
 * @Description
 * @date 2023/12/13 10:37
 */
@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean myFilterRegistrationBean(){
        //注册过滤器
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new MyFliter());
        //添加过滤路径
        filterRegistrationBean.addUrlPatterns("/hello/*");
        return filterRegistrationBean;
    }
}

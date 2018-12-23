package com.sy.springboot.demo2.springbootdemo2;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.sy.springboot.demo2.springbootdemo2.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@MapperScan(basePackages = "com.sy.springboot.demo2.springbootdemo2.mapper")
@EnableTransactionManagement
@EnableScheduling
@ServletComponentScan
@EnableCaching  //开启cache缓存
public class SpringbootDemo2Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDemo2Application.class, args);
    }

    //拦截器配置
    @Configuration
    static class WebMvcConfigurer extends WebMvcConfigurerAdapter {
        @Autowired
        private LoginInterceptor loginInterceptor;

        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            //添加微信授权登陆拦截器
            registry.addInterceptor(loginInterceptor)
                    .addPathPatterns("/**")
                    .excludePathPatterns("/login");
        }

        //解决response乱码,使用FastJson替换Jackson
        @Override
        public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
            super.configureMessageConverters(converters);
            FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
            List<MediaType> fastMediaTypes = new ArrayList<MediaType>();
            fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
            fastConverter.setSupportedMediaTypes(fastMediaTypes);
            converters.add(fastConverter);
        }
    }
}

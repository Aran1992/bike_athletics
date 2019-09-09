package com.h5game.configration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
  * 
  * @Description:跨域访问配置
  * @Project:boot-sis 
  * @File:CORSMyConfiguration.java 
  * @Package:org.niugang.config 
  * @Date:2018年7月12日下午10:22:10
  * @author:niugang 
  * @Copyright (c) 2018, 863263957@qq.com All Rights Reserved. 
  *
  */
@Configuration
public class CORSConfiguration {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
//addMapping 跨域所能访问的路径
//allowedOrigins：那些域可以访问，默认为任何域都可以访问
                registry.addMapping("/**").allowedOrigins("*");
            }
        };
    }
}
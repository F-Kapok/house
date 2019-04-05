package com.fans;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @Description: 入口
 * @Param:
 * @return:
 * @Author: fan
 * @Date: 2018/12/19 17:19
 **/
@SpringBootApplication
@MapperScan(basePackages = "com.fans.mapper")
@ServletComponentScan
@EnableAsync
public class SpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }

}


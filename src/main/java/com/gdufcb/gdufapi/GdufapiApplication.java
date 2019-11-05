package com.gdufcb.gdufapi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan("com.gdufcb.gdufapi.Mapper")
public class GdufapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(GdufapiApplication.class, args);
    }

}

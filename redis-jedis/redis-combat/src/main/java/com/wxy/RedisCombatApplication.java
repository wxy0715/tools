package com.wxy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wxy
 */
@SpringBootApplication
@MapperScan("com.wxy.mapper")
public class RedisCombatApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisCombatApplication.class, args);
    }

}

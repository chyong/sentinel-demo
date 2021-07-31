package com.chenyong.sentinel;

import com.alibaba.csp.sentinel.init.InitExecutor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@MapperScan("com.chenyong.sentinel.mapper")

public class SentinelApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SentinelApplication.class, args);
        // 连接到控制台，与sentinel控制台通信
        System.setProperty("project.name",
                context.getEnvironment().getProperty("spring.application.name", "sentinel"));
        System.setProperty("csp.sentinel.dashboard.server",
                context.getEnvironment().getProperty("sentinel.dashboard.server", "localhost:8080"));
        InitExecutor.doInit();
    }

}

package com.guli.statistics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableFeignClients
@SpringBootApplication
@ComponentScan(basePackages={"com.guli.statistics","com.guli.common"})
@EnableEurekaClient
public class StatisticsApplication {
    public static void main(String[] args) {
        SpringApplication.run(StatisticsApplication.class, args);
    }

}

package com.wyd.xuecheng.content;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {"com.wyd.xuecheng"})
@EnableFeignClients(basePackages={"com.wyd.xuecheng.content.feignclient"})
public class ContentApplication  {
    public static void main(String[] args) {
        SpringApplication.run(ContentApplication.class ,args);
    }
}

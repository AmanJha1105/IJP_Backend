package com.ukg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@EnableEurekaServer
@CrossOrigin
public class IJP_EurekaMain {
    public static void main(String[] args) {
        SpringApplication.run(IJP_EurekaMain.class,args);
    }
}
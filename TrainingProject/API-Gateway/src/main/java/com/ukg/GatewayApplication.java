package com.ukg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("employee-service", r -> r
                        .path("/api/employees/**")
                        .uri("lb://employee-service"))
                .route("application-service", r -> r
                        .path("/api/applications/**")
                        .uri("lb://application-service"))
                .route("job-service", r -> r
                        .path("/api/jobs/**")
                        .uri("lb://job-service"))
                .route("opening-service", r -> r
                        .path("/api/openings/**")
                        .uri("lb://opening-service"))
                .build();
    }
}

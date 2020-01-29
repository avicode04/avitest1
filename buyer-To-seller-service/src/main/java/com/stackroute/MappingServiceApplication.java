package com.stackroute;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Enables Spring Boot auto config and component scanning.
 */
@SpringBootApplication
/**Enable swagger support in the class*/
@EnableSwagger2
@EnableScheduling
@EnableEurekaClient
public class MappingServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(MappingServiceApplication.class, args);
    }

}

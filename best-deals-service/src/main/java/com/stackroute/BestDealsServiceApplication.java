package com.stackroute;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Enables Spring Boot auto config and component scanning.
 */
@SpringBootApplication
/**Enable swagger support in the class*/
@EnableSwagger2
@EnableEurekaClient
public class BestDealsServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(BestDealsServiceApplication.class, args);
    }

}

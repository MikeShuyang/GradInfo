package com.example.gradinfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication
@EnableOpenApi
public class GradInfoApplication {

    public static void main(String[] args) {
        SpringApplication.run(GradInfoApplication.class, args);
    }

}

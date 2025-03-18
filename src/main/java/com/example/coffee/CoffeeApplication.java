package com.example.coffee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.validation.DefaultMessageCodesResolver;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class CoffeeApplication {


    public static void main(String[] args) {
        SpringApplication.run(CoffeeApplication.class, args);
    }

}

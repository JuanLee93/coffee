package com.example.coffee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.DefaultMessageCodesResolver;

@SpringBootApplication
public class CoffeeApplication {

    private static final Logger logger = LoggerFactory.getLogger(CoffeeApplication.class);

    public static void main(String[] args) {

        DefaultMessageCodesResolver codesResolver = new DefaultMessageCodesResolver();

        String[] codes = codesResolver.resolveMessageCodes(
                "Min", "productRequest", "price", int.class);
        for (String code : codes){
            logger.error("codes : "+code);
        }

        SpringApplication.run(CoffeeApplication.class, args);
    }

}

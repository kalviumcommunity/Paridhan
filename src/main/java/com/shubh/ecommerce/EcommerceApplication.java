package com.shubh.ecommerce;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
//@ComponentScan(basePackages = {"com.shubh.ecommerce", "com.Shubh.oauth2.Config"})
public class EcommerceApplication {

    public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);

    }

}
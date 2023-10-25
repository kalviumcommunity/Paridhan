package com.shubh.ecommerce;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
//@ComponentScan(basePackages = {"com.shubh.ecommerce", "com.Shubh.oauth2.Config"})
public class EcommerceApplication {

    public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
//        ApplicationContext context = SpringApplication.run(EcommerceApplication.class, args);
//        if (context.containsBean("authenticationProvider")) {
//            System.out.println("AuthenticationProvider is running.");
//        } else {
//            System.out.println("AuthenticationProvider is not running.");
//        }

    }

}
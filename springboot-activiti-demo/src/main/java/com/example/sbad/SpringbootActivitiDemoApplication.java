package com.example.sbad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class SpringbootActivitiDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootActivitiDemoApplication.class, args);
    }

}

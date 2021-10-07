package com.ssutopia.financial.accountService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class SsUtopiaFinancialAccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsUtopiaFinancialAccountServiceApplication.class, args);
    }

}

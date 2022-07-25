package com.team2.cafein;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CafeInApplication {

    public static void main(String[] args) {
        SpringApplication.run(CafeInApplication.class, args);
    }

}

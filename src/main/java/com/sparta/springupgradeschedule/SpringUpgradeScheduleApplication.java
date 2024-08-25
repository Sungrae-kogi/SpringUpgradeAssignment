package com.sparta.springupgradeschedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringUpgradeScheduleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringUpgradeScheduleApplication.class, args);
    }

}

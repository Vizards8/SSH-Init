package com.example.SSH_Init;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class PersonalProEcommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersonalProEcommerceApplication.class, args);
    }

}

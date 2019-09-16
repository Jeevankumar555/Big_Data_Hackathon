package com.wbaa.meetup.rsvp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.wbaa")
public class RSVPApplication {

    public static void main(String[] args) {

        SpringApplication.run(RSVPApplication.class, args);

    }
}

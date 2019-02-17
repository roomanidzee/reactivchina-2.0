package com.romanidze.reactivchinaconsumer.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.reactive.config.EnableWebFlux;

/**
 * 17.02.2019
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@SpringBootApplication
@ComponentScan({"com.romanidze.reactivchinaconsumer.config", "com.romanidze.reactivchinaconsumer.controllers"})
@EnableWebFlux
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

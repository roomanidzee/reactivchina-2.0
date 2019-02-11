package com.romanidze.reactivchinaproducer.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.cassandra.repository.config.EnableReactiveCassandraRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

/**
 * 11.02.2019
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@SpringBootApplication
@ComponentScan({"com.romanidze.reactivchinaproducer.config", "com.romanidze.reactivchinaproducer.handlers",
                "com.romanidze.reactivchinaproducer.routes", "com.romanidze.reactivchinaproducer.services"})
@EnableReactiveCassandraRepositories(basePackages = "com.romanidze.reactivchinaproducer.repositories")
@EntityScan(basePackages = "com.romanidze.reactivchinaproducer.domain")
@EnableWebFlux
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

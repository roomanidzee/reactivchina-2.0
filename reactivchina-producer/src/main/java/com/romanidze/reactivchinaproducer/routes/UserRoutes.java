package com.romanidze.reactivchinaproducer.routes;

import com.romanidze.reactivchinaproducer.handlers.UserHandler;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;

/**
 * 11.02.2019
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Component
public class UserRoutes {

    @Bean
    public RouterFunction<ServerResponse> routes(UserHandler userHandler){

        return nest(RequestPredicates.path("/users"),
                      route(RequestPredicates.POST("/register"), userHandler::create));

    }

}

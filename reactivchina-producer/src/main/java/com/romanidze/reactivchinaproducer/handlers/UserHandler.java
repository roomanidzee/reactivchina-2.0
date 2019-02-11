package com.romanidze.reactivchinaproducer.handlers;

import com.romanidze.reactivchinaproducer.domain.User;
import com.romanidze.reactivchinaproducer.dto.UserDTO;
import com.romanidze.reactivchinaproducer.services.interfaces.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

/**
 * 11.02.2019
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Component
public class UserHandler {

    private final UserService userService;

    @Autowired
    public UserHandler(UserService userService) {
        this.userService = userService;
    }

    public Mono<ServerResponse> create(ServerRequest req){

        return req.bodyToMono(UserDTO.class)
                  .flatMap(user -> ServerResponse.ok().body(this.userService.createUser(user), User.class));

    }

}

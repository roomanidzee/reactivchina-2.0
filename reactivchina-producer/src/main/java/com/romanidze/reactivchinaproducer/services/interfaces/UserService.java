package com.romanidze.reactivchinaproducer.services.interfaces;

import com.romanidze.reactivchinaproducer.domain.User;
import com.romanidze.reactivchinaproducer.dto.UserDTO;

import reactor.core.publisher.Mono;

/**
 * 11.02.2019
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
public interface UserService {

    Mono<User> createUser(UserDTO userDTO);

}

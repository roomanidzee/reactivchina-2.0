package com.romanidze.reactivchinaproducer.services.implementations;

import com.romanidze.reactivchinaproducer.domain.User;
import com.romanidze.reactivchinaproducer.dto.UserDTO;
import com.romanidze.reactivchinaproducer.repositories.UserRepository;
import com.romanidze.reactivchinaproducer.services.interfaces.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

/**
 * 11.02.2019
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Mono<User> createUser(UserDTO userDTO) {

        User user = UserDTO.dtoToDomain(userDTO);

        return this.userRepository.save(user);

    }
}

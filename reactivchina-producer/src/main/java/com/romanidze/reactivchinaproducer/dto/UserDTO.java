package com.romanidze.reactivchinaproducer.dto;

import com.romanidze.reactivchinaproducer.domain.User;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * 11.02.2019
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String username;
    private Integer age;

    public static User dtoToDomain(UserDTO userDTO){

        return User.builder()
                   .id(UUID.randomUUID())
                   .username(userDTO.getUsername())
                   .age(userDTO.getAge())
                   .build();

    }

    public static UserDTO domainToDTO(User user){

        return UserDTO.builder()
                      .username(user.getUsername())
                      .age(user.getAge())
                      .build();

    }

}

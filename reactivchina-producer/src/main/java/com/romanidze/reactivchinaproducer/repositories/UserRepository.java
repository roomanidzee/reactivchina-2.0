package com.romanidze.reactivchinaproducer.repositories;

import com.romanidze.reactivchinaproducer.domain.User;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;

/**
 * 11.02.2019
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
public interface UserRepository extends ReactiveCassandraRepository<User, String> {
}

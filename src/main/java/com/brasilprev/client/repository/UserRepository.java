package com.brasilprev.client.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.brasilprev.client.entity.User;

public interface UserRepository extends MongoRepository<User, String> {

	Optional<User> findByUsername(String username);

}

package com.brasilprev.client.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.brasilprev.client.entity.Client;

@Repository
public interface ClientRepository extends MongoRepository<Client, String> {

	Optional<Client> findByName(String name);

	Optional<Client> findById(String id);

}

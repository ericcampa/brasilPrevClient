package com.brasilprev.client.service;

import java.util.List;
import java.util.Optional;

import com.brasilprev.client.entity.Client;

public interface ClientService {

	List<Client> findAll();
	Optional<Client> findByName(String name);
	Optional<Client> findById(String id);
	Client create(Client client);
	Client update(Client client);
	void deleteById(String id);
}

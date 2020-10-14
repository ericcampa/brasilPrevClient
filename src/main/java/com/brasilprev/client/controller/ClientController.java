package com.brasilprev.client.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brasilprev.client.entity.Client;
import com.brasilprev.client.service.ClientService;

@RestController
@RequestMapping("/client")
public class ClientController {

	@Autowired
	private ClientService service;
	
	@GetMapping("")
    public ResponseEntity<List<Client>> findAll() {
        List<Client> clients = service.findAll();
		return ResponseEntity.ok(clients);
    }
	
	@GetMapping("/{id}")
	public ResponseEntity<Client> findById(@PathVariable("id") String id) {
		Optional<Client> client = service.findById(id);
		if(client.isPresent()) {
			return new ResponseEntity<Client>(client.get(), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/name/{name}")
    public ResponseEntity<Client> findByName(@PathVariable("name") String name) {
        Optional<Client> client = service.findByName(name);
    	if(client.isPresent()) {
			return new ResponseEntity<Client>(client.get(), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
    }

	@PostMapping("")
    public ResponseEntity<Client> create(@RequestBody Client client) {
		client = service.create(client);
		if(!client.getId().isEmpty()) {
			return new ResponseEntity<Client>(client, HttpStatus.CREATED);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
    }
	
	@PutMapping("")
    public ResponseEntity<Client> update(@RequestBody Client client) {
		return new ResponseEntity<Client>(service.update(client), HttpStatus.OK);

    }
	
	@DeleteMapping("/{id}")
    public ResponseEntity<Client> deleteById(@PathVariable("id") String id) {
		service.deleteById(id);
		return new ResponseEntity<Client>(HttpStatus.OK);
    }
	
}

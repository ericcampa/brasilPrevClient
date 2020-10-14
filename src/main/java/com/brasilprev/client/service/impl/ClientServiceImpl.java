package com.brasilprev.client.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.brasilprev.client.entity.Client;
import com.brasilprev.client.repository.ClientRepository;
import com.brasilprev.client.service.ClientService;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientRepository repository;

	public List<Client> findAll() {
		log.info("Client.findAll");
		return repository.findAll();
	}

	public Optional<Client> findByName(String name) {
		log.info("Client.findByName");
		return repository.findByName(name);
	}

	@Override
	public Optional<Client> findById(String id) {
		log.info("Client.findById");
		return repository.findById(id);
	}

	public Client create(Client client) {
		log.info("Client.create");
		if (client.getId() != null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		repository.save(client);
		log.info("Client.created successfully: " + client.getId());
		return client;

	}

	public Client update(Client client) {
		log.info("Client.update");
		if (client.getId() == null || client.getId().isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		repository.findById(client.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		repository.save(client);
		log.info("Client.update: " + client.getId());
		return client;
	}

	@Override
	public void deleteById(String id) {
		log.info("Client.deleteById");
		repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		repository.deleteById(id);
	}
	
	public String teste() {
		
        String response = "";
        String operation="";
        String S = "crow";
        String T = "cow";
        if(S.equals(T)){
            response = "NOTHING";
        }else if(validateImpossibles(S,T)){
             response = "IMPOSSIBLE";
        }else if(S.length()>T.length()){
          	String substr="";
        	for(int i=1; i<=S.length();i++) {
        		substr = T.substring(0,i);
        		if(!S.contains(substr)) {
        			operation = (String) S.subSequence(i-1, i);
        			S = S.replaceFirst(operation, "");
        		}
        	}
            
            response = "REMOVE " + operation;   
        }else{
        	String teste="";
        	for(int i=1; i<T.length();i++) {
        		teste = S.substring(0,i);
        		if(!T.contains(teste)) {
        			operation = (String) T.subSequence(i-1, i);
        			T = T.replaceFirst(operation, "");
        		}
        		
        	}
        	
        	
        	
            response = "INSERT " + operation;   
        }

        return response;
    }
    
    
    private boolean validateImpossibles(String s, String t){
        if((s.length()==t.length()-1)|| (t.length()==s.length()-1)){
           return false;
        }else{
            return true;
        }
    
	}

}

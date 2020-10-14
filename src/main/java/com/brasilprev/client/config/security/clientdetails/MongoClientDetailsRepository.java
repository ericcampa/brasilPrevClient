package com.brasilprev.client.config.security.clientdetails;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoClientDetailsRepository extends MongoRepository<MongoClientDetails, String> {


	boolean deleteByClientId(String clientId);

}

package com.brasilprev.client.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;

public class ClientTest {

	private String id;
	
	@Before
	public void setUp() {
		id = ObjectId.get().toHexString();
	}
	
	@Test
	public void client() {
		Client client = loadClient();
		assertNotNull(client);
		assertEquals(client.getId(), id);
		assertEquals(client.getName(), "name");
		assertEquals(client.getCpf(), "cpf");
		assertEquals(client.getAddress(), "address");
	}
	
	public Client loadClient(){
		return new Client(id, "name", "cpf", "address");
	}
}

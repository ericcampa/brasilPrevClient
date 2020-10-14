package com.brasilprev.client.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.brasilprev.client.entity.Client;
import com.brasilprev.client.repository.ClientRepository;
import com.brasilprev.client.service.impl.ClientServiceImpl;

public class ClientServiceImplTest {

	@InjectMocks
	private ClientServiceImpl service;

	@Mock
	private ClientRepository repository;

	private String id;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		id = ObjectId.get().toHexString();
	}

//	@Test
//	public void findAll() {
//		when(repository.findAll()).thenReturn(loadClientList());
//		List<Client> result = service.findAll();
//		assertNotNull(result);
//		assertThat(result.size() == 1).isTrue();
//	}
//
//	@Test
//	public void findByName() {
//		when(repository.findByName(Mockito.anyString())).thenReturn(loadClient());
//		Optional<Client> result = service.findByName("");
//		assertThat(result.isPresent()).isTrue();
//	}
//
//	@Test
//	public void findById() {
//		when(repository.findById(Mockito.anyString())).thenReturn(loadClient());
//		Optional<Client> result = service.findById("");
//		assertThat(result.isPresent()).isTrue();
//	}
//
//	@Test(expected = ResponseStatusException.class)
//	public void create() {
//		when(service.create(loadClient().get())).thenThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST))
//				.thenReturn(loadClient().get());
//		Client result = service.create(loadClient().get());
//		assertThat(result==null).isTrue();
//		result = service.create(new Client());
//		assertThat(result!=null).isTrue();
//	}
//
//	@Test(expected = ResponseStatusException.class)
//	public void update() {
//		Client voidClient = new Client();
//		when(service.update(voidClient)).thenThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST)).thenReturn(loadClient().get());
//		Client result = service.update(voidClient);
//		assertThat(result==null).isTrue();
//		result = service.update(loadClient().get());
//		assertThat(result!=null).isTrue();
//	}
//	
//	@Test(expected = ResponseStatusException.class)
//	public void deleteById() {
//		service.deleteById("");
//		Mockito.verify(repository, times(1)).deleteById(id);
//	}
	
	@Test
	public void teste() {
		String a = service.teste();
		System.out.println(a);
	}
	
	
	
	
	
	
	
	
	
	
	

	public Optional<Client> loadClient() {
		return Optional.of(new Client(id, "name", "cpf", "address"));
	}

	public List<Client> loadClientList() {
		List<Client> clients = new ArrayList<>();
		clients.add(new Client(id, "name", "cpf", "address"));
		return clients;
	}
	
}

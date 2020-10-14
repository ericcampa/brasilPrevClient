package com.brasilprev.client.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.brasilprev.client.entity.Client;
import com.brasilprev.client.service.ClientService;
import com.google.gson.Gson;

@WithMockUser
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class ClientControllerTest {

	@Configuration
	static class ConreollweTestCOnfiguration {
		@Bean
		public ClientService clientService() {
			return Mockito.mock(ClientService.class);
		}

		@Bean
		public ClientController clientController() {
			return new ClientController();
		}
	}
	
	
	@Autowired
	private ClientController controller;
	
	@Autowired
	private ClientService service;
	
	private MockMvc mockMvc;

	private String id;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(this.controller).build();
		id = ObjectId.get().toHexString();
	}
	
	@Test
	public void testFindAll() throws Exception{
		Mockito.when(service.findAll()).thenReturn(loadClientList());
		mockMvc.perform(MockMvcRequestBuilders.get("/client")
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$[*].id").isNotEmpty());
	}
	
	@Test
	public void testFindByName() throws Exception{
		Mockito.when(service.findByName(Mockito.anyString())).thenReturn(Optional.empty()).thenReturn(loadClient());
		mockMvc.perform(MockMvcRequestBuilders.get("/client/name/{name}","name")
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isNotFound());
		mockMvc.perform(MockMvcRequestBuilders.get("/client/name/{name}","name")
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("name"));
	}
	
	@Test
	public void testFindById() throws Exception{
		Mockito.when(service.findById(Mockito.anyString())).thenReturn(Optional.empty()).thenReturn(loadClient());
		mockMvc.perform(MockMvcRequestBuilders.get("/client/{id}",id)
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isNotFound());
		mockMvc.perform(MockMvcRequestBuilders.get("/client/{id}",id)
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id));
	}
	
	@Test
	public void testSave() throws Exception{
		Mockito.when(service.create(Mockito.any())).thenReturn(loadClient().get());
		String json = new Gson().toJson(new Client());
		mockMvc.perform(MockMvcRequestBuilders.post("/client")
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
	}
	
	@Test
	public void testUpdate() throws Exception{
		Mockito.when(service.update(Mockito.any())).thenReturn(loadClient().get());
		String json = new Gson().toJson(loadClient().get());
		mockMvc.perform(MockMvcRequestBuilders.put("/client")
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
	}
	
	@Test
	public void testDelete() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/client/{id}",id)
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk());
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

package com.liniitriesit.shopgenerator;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liniitriesit.shopgenerator.domain.User;
import com.liniitriesit.shopgenerator.repository.UserRepository;
import com.liniitriesit.shopgenerator.service.UserRegistrationService;

@SpringBootTest
@WebMvcTest
class ShopGeneratorApplicationTests {
	@Autowired
	private UserRegistrationService userRegistrationService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MockMvc mvc;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	public void testUserRegistration() throws Exception {
		this.mvc.perform(post("/registration")
				.content(asJsonString(new User(null, "adminUser", "adminPassword", "Universe Mastermind", "Milkywway 20")))
				.contentType(MediaType.APPLICATION_JSON))	
				.andExpect(status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("$.userId").exists());
	}
	
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}

}

package com.liniitriesit.shopgenerator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liniitriesit.shopgenerator.domain.User;
import com.liniitriesit.shopgenerator.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/registration")
public class RegistrationController {
	
	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	
	@Bean("passwordEncoder")
	public PasswordEncoder encoder() {
		return new StandardPasswordEncoder("53cr3t");
	}
	
	@Autowired
	private UserRegistrationService userRegistrationService; 
	
	@Autowired
	public RegistrationController(UserRepository userRepository) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	@GetMapping
	public String showRegistrationForm() {
		return "registration";
	}
	
	@PostMapping
	public ResponseEntity processRegistration(@RequestBody User user) {
		User saved = userRepository.save(userRegistrationService.toUser(user, encoder() ));	
		log.info("NEW USER saved: {}", saved.toString());
		return new ResponseEntity<>("Registered " + saved.toString(),HttpStatus.CREATED);
		
	}
}

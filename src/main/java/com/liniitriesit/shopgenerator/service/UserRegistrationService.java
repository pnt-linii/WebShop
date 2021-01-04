package com.liniitriesit.shopgenerator.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.liniitriesit.shopgenerator.domain.User;

@Service
public class UserRegistrationService {
	public User toUser(User user, PasswordEncoder passwordEncoder) {
		return User.builder().username(user.getUsername()).password(passwordEncoder.encode(user.getPassword()))
				.fullname(user.getFullname()).build();
	}
}

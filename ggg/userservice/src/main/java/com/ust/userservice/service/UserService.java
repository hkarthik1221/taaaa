package com.ust.userservice.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.ust.userservice.dto.UserRegistrationDto;
import com.ust.userservice.model.User;

public interface UserService extends UserDetailsService {
	User save(UserRegistrationDto registrationDto);
}
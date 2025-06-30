package com.example.demo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.demo.repository.UserRepo;

@Configuration
public class ApplicationConfig {
	
    @Autowired
    UserRepo userRepo;
		
	@Bean
	UserDetailsService userDetailsService(){
		return username -> userRepo.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("User is not found!"));
	}
}


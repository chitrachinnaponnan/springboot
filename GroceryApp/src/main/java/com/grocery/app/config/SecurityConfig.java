package com.grocery.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
		
		httpSecurity.authorizeHttpRequests(auth -> auth.requestMatchers("/items/**").authenticated()
				.anyRequest().permitAll())
		.formLogin().and().logout();
		
		
		return httpSecurity.build();
		
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		
		UserDetails admin = User.withUsername("chitra")
				.password(passwordEncoder().encode("password"))
				.roles("ADMIN")
				.build();
	
		UserDetails user = User.withUsername("prabha")
				.password(passwordEncoder().encode("password"))
				.roles("USER")
				.build();
		
		
		return new InMemoryUserDetailsManager(admin,user);
		
	}
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}

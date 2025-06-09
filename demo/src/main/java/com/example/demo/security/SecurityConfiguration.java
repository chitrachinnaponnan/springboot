package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http
        .authorizeHttpRequests(auth->
                auth.anyRequest().authenticated());  // All other requests require authentication

      
//        http.formLogin(Customizer.withDefaults());  // Enable form login here I use default login
		http.formLogin(form -> form.defaultSuccessUrl("/todo-list", true));
		http.csrf(csrf -> csrf.disable());
		http.headers(headers -> headers.frameOptions(frameOptionsConfig -> frameOptionsConfig.disable()));
		
      
		return http.build();   
   
	}
	
	
    @Bean
    UserDetailsService myInMemoryUserDetailsService(){

        UserDetails user = User  //for user role
                .withUsername("user")  // Replace <UserName> with the actual username
                .password(passwordEncoder().encode("user"))   // Replace <UserPassword> with the actual password
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);

    }

    // Password Encoding
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}

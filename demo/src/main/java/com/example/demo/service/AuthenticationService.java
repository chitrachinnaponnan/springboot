package com.example.demo.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

	
	public boolean authService(String userName,String password) {
		
		if(userName.equals("user") && password.equals("user"))
			return true;
		return false;
	}
	
	public String getUserName() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String currentUserName = authentication.getName();
	    return currentUserName;

	}
}

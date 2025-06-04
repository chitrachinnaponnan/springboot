package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

	
	public boolean authService(String userName,String password) {
		
		if(userName.equals("user") && password.equals("user"))
			return true;
		return false;
	}
}

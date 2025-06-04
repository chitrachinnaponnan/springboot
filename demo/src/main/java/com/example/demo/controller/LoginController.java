package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.demo.service.AuthenticationService;

@Controller
@SessionAttributes("username")
public class LoginController {

	@Autowired
	AuthenticationService authenticationService;
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login() {
		//Test
		return "login";
	}
	
	
	@RequestMapping(value="login",method=RequestMethod.POST)
	public String tryLogin(@RequestParam String username,@RequestParam String password,ModelMap model) {
		
		if(authenticationService.authService(username, password)) {
			model.put("username", username);
			model.put("password", password);
			
			return "redirect:todo-list";
		}
		
		return "login";
	}
	
}

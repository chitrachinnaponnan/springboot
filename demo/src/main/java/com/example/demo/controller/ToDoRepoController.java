package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.demo.model.ToDo;
import com.example.demo.service.AuthenticationService;
import com.example.demo.service.ToDoRepoService;


@Controller
@SessionAttributes("username")
public class ToDoRepoController {
	
	
	@Autowired
	AuthenticationService authenticationService;
	
	@Autowired
	ToDoRepoService toDoRepoService;
	
	
	@RequestMapping("/todo-list")
	public String getToDo(ModelMap model) {
		List<ToDo> todos = toDoRepoService.getToDo();
		model.addAttribute("todos", todos);
		return "ToDoList";
	}
	
	
	@RequestMapping(value="/add-todo", method=RequestMethod.GET)
	public String getAddToDo(ModelMap model) {
		ToDo todo = new ToDo("", "", "", LocalDate.now(), false);
		model.put("todo", todo);
		return "AddToDo";
	}
	
	@RequestMapping(value="/add-todo", method=RequestMethod.POST)
	public String addToDo(@ModelAttribute("todo") ToDo todo, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			return "AddToDo"; // Just stay on the same page / url
		}
		
		
		String username = authenticationService.getUserName();
		todo.setUserName(username);
		toDoRepoService.addToDo(todo);
		return "redirect:todo-list";
	}
	
	@RequestMapping(value = "/delete-todo",method=RequestMethod.GET)
	public String delete(@RequestParam int id) {
		
		toDoRepoService.delete(id);
		
		return "redirect:todo-list";
	}

	@RequestMapping(value = "/update-todo",method=RequestMethod.GET)
	public String getUpdateToDoPage(@RequestParam int id,ModelMap model) {
		
		ToDo  todo = toDoRepoService.getById(id);
		model.put("todo", todo);

		return "AddToDo";
	}
	
	
	@RequestMapping(value="/update-todo", method=RequestMethod.POST)
	public String updateToDo(@ModelAttribute("todo") ToDo todo, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			return "AddToDo"; // Just stay on the same page / url
		}
		String username = authenticationService.getUserName();
		
		todo.setUserName(username);
		toDoRepoService.updateToDo(todo);
		return "redirect:todo-list";
	}
	
	
	
}

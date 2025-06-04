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
import com.example.demo.service.ToDoService;


@Controller
@SessionAttributes("username")
public class ToDoController {
	
	@Autowired
	private ToDoService todoService;
	
	
	@RequestMapping("/todo-list")
	public String getToDo(ModelMap model) {
		List<ToDo> todos = todoService.getToDo();
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
		
		todoService.addToDo((String)model.get("username"), todo.getTaskName(), todo.getDescription(), todo.getDate(), todo.isDone());
		return "redirect:todo-list";
	}
	
	@RequestMapping(value = "delete-todo",method=RequestMethod.GET)
	public String delete(@RequestParam int id) {
		
		todoService.delete(id);
		
		return "redirect:todo-list";
	}

}

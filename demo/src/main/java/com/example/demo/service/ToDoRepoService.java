package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.ToDo;
import com.example.demo.repository.ToDoRepository;

@Service
public class ToDoRepoService {

	@Autowired
	ToDoRepository toDoRepository;
	
	public void addToDo(ToDo toDo) {
		toDoRepository.save(toDo);
	}
	
	
	public void delete(int id) {
		toDoRepository.deleteById(id);
	}
	
	public ToDo getById(int id) {
		ToDo todoObj =  toDoRepository.findById(id).get();
		return todoObj;
	}
	
	public void updateToDo(ToDo todo) {
		
		toDoRepository.save(todo);
		
	}
	
	public List<ToDo> getToDo(){
		return toDoRepository.findAll();
	}
}

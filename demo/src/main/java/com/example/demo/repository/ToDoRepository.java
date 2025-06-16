package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.ToDo;

public interface ToDoRepository extends JpaRepository<ToDo, Integer>{

}

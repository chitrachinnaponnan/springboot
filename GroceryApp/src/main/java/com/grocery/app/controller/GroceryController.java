package com.grocery.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.grocery.app.model.GroceryItem;
import com.grocery.app.service.GroceryItemService;

@Controller
public class GroceryController {

	@Autowired
    private GroceryItemService groceryItemService;

	
	@GetMapping("/items")
	public String getItems(Model model) {
		model.addAttribute("items", groceryItemService.getAllItems());
		return "list-items";
	}
	
	@GetMapping("/items/new")
	public String showAddForm(Model model) {
	    model.addAttribute("item", new GroceryItem());
	    return "add-item";
	}
	@PostMapping("/items")
	public String addItem(@ModelAttribute GroceryItem item) {
	    groceryItemService.saveItem(item);
	    return "redirect:/items";
	}

}

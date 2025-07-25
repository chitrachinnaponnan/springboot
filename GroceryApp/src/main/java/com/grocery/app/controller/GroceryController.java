package com.grocery.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.grocery.app.model.GroceryItem;
import com.grocery.app.service.GroceryItemService;

import jakarta.validation.Valid;

@Controller
public class GroceryController {

	@Autowired
    private GroceryItemService groceryItemService;

	
//	@GetMapping("/items")
	public String getItems(Model model) {
		model.addAttribute("items", groceryItemService.getAllItems());
		return "list-items";
	}
	
	
   
	@GetMapping("/items")
	public String getPaginatedItems(@RequestParam(defaultValue = "") String keyword,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size,
			@RequestParam(defaultValue = "name") String sortField,
			@RequestParam(defaultValue = "asc") String sortDirection,Model model) {
	
		Page<GroceryItem> gorceryItemPage = groceryItemService.searchItems(keyword,page,size,sortField,sortDirection);
		model.addAttribute("items", gorceryItemPage.getContent());
	    model.addAttribute("currentPage", page);
	    model.addAttribute("totalPages", gorceryItemPage.getTotalPages());
	    model.addAttribute("sortField", sortField);
	    model.addAttribute("sortDir", sortDirection);
	    model.addAttribute("reverseSortDir", sortDirection.equals("asc") ? "desc" : "asc");
	    model.addAttribute("keyword",keyword);

		return "list-items";
	}
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/items/new")
	public String showAddForm(Model model) {
	    model.addAttribute("item", new GroceryItem());
	    return "add-item";
	}
	@PostMapping("/items")
	@PreAuthorize("hasRole('ADMIN')")
	public String addItem(@Valid @ModelAttribute GroceryItem item,Model model,BindingResult results) {
		if (results.hasErrors()) {
	        return "add-item"; // return to form if validation fails
	    }

	    groceryItemService.saveItem(item);
	    return "redirect:/items";
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/items/edit/{id}")
	public String showUpdateForm(@PathVariable Long id, Model model) {
	    GroceryItem item = groceryItemService.getItemById(id);
	    model.addAttribute("item", item);
	    return "edit-item";
	}
	
	@PostMapping("/items/update")
	@PreAuthorize("hasRole('ADMIN')")
	public String updateItem(@ModelAttribute GroceryItem item) {
	    groceryItemService.saveItem(item);
	    return "redirect:/items";
	}

	@GetMapping("/items/delete/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public String deleteItem(@PathVariable Long id) {
	    groceryItemService.deleteItemById(id);
	    return "redirect:/items";
	}



}

package com.grocery.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grocery.app.model.GroceryItem;
import com.grocery.app.repository.GroceryItemRepository;

@Service
public class GroceryItemService {

	 @Autowired
	    private GroceryItemRepository groceryItemRepository;

	    public List<GroceryItem> getAllItems() {
	        return groceryItemRepository.findAll();
	    }
	    
	    public void saveItem(GroceryItem item) {
	        groceryItemRepository.save(item);
	    }

}

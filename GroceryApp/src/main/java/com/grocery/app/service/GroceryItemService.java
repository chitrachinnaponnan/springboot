package com.grocery.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.grocery.app.exception.ItemNotFoundException;
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
	    
	    public GroceryItem getItemById(Long id) {
	    	return groceryItemRepository.findById(id)
	                .orElseThrow(() -> new ItemNotFoundException("Item not found with ID: " + id));


	    }

	    public void deleteItemById(Long id) {
	    	GroceryItem item = groceryItemRepository.findById(id)
	                .orElseThrow(() -> new ItemNotFoundException("Item not found with ID: " + id));
	            groceryItemRepository.delete(item);

	    }

		public Page<GroceryItem> searchItems(String keyword,int page, int size, String sortField, String sortDirection) {
	        Sort sort = sortDirection.equalsIgnoreCase("asc") ? Sort.by(sortField).ascending()
                    : Sort.by(sortField).descending();
	        Pageable pageable = PageRequest.of(page, size,sort);
	        
	        if (keyword == null || keyword.isEmpty()) {
	            return groceryItemRepository.findAll(pageable);
	        } else {
	            return groceryItemRepository.findByNameContainingIgnoreCase(keyword, pageable);
	        }
			
		}


}

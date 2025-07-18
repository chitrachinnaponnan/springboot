package com.grocery.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.grocery.app.model.GroceryItem;
import com.grocery.app.repository.GroceryItemRepository;

@ExtendWith(MockitoExtension.class)
public class GroceryItemServiceTest {

	@InjectMocks
	private GroceryItemService groceryItemService;
	
	@Mock
	private GroceryItemRepository groceryItemRepository;
	
	private GroceryItem groceryItem;
	
	@BeforeEach
    void setup() {
		groceryItem = new GroceryItem();
		groceryItem.setId(1L);
		groceryItem.setName("Tomato");
		groceryItem.setQuantity(2);
    }
	@Test
	void testGetAllItems() {
        List<GroceryItem> list = Arrays.asList(groceryItem);
        when(groceryItemRepository.findAll()).thenReturn(list);
        List<GroceryItem> result = groceryItemService.getAllItems();
        assertEquals(1, result.size());
    }


	@Test
    void testSaveItems() {
		 
		    groceryItemService.saveItem(groceryItem);  // call the method

		    verify(groceryItemRepository).save(groceryItem); // verify that save() was called with the item
		  
		  
    }
	
	
	@Test
	void testSearchItems() {
		String keyword = "milk";
	    int page = 0;
	    int size = 5;
	    String sortField = "name";
	    String sortDirection = "asc";

	    GroceryItem item = new GroceryItem();
	    item.setName("Milk");
	    Page<GroceryItem> pageResult = new PageImpl<>(List.of(item));

	    Pageable pageable = PageRequest.of(page, size, Sort.by(sortField).ascending());

	    when(groceryItemRepository.findByNameContainingIgnoreCase(keyword, pageable))
        .thenReturn(pageResult);

    Page<GroceryItem> result = groceryItemService.searchItems(keyword, page, size, sortField, sortDirection);

    assertEquals(1, result.getTotalElements());
    assertEquals("Milk", result.getContent().get(0).getName());

	}
}

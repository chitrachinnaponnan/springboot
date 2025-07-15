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
		  List<GroceryItem> list = Arrays.asList(groceryItem);
		    groceryItemService.saveItem(groceryItem);  // call the method

		    verify(groceryItemRepository).save(groceryItem); // verify that save() was called with the item
		  
		  
    }
}

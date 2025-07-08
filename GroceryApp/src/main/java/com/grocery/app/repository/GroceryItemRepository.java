package com.grocery.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grocery.app.model.GroceryItem;

@Repository
public interface GroceryItemRepository extends JpaRepository<GroceryItem, Long>  {
    Page<GroceryItem> findByNameContainingIgnoreCase(String keyword, Pageable pageable);

}

package com.productcatalog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.productcatalog.entity.Inventory;

public interface InventoryRepo extends JpaRepository<Inventory, Long>{

	@Query(value = "SELECT i FROM Inventory i WHERE  i.name LIKE '%' || :keyword || '%'")
	List<com.productcatalog.entity.Inventory> searchInventory(String keyword);

}

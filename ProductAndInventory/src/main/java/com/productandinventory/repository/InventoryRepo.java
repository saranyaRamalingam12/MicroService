package com.productandinventory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.productandinventory.entity.Inventory;

public interface InventoryRepo extends JpaRepository<Inventory, Long>{

	@Query(value = "SELECT i FROM Inventory i WHERE  i.name LIKE '%' || :keyword || '%'")
	List<Inventory> searchInventory(String keyword);

}

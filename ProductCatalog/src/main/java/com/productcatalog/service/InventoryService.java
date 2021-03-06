package com.productcatalog.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.productcatalog.dto.InventoryDTO;
import com.productcatalog.entity.Inventory;

@Service
public interface InventoryService 
{
	public Optional<Inventory> getInventoryById(Long id);
		
	public List<Inventory> searchInventory(String keyword);
	
	public Inventory mapDtoToInventory(InventoryDTO inventoryDTO, Inventory inventory, ModelMapper modelMapper);
	
	public InventoryDTO mapInventoryToDTO(InventoryDTO inventoryDTO, Inventory inventory, ModelMapper modelMapper);
}

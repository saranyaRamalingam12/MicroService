package com.productandinventory.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.productandinventory.dto.InventoryDTO;
import com.productandinventory.entity.Inventory;
@Service
public interface InventoryService 
{
	public void saveInventory(Inventory inventory);
 
	public List<Inventory> getInventoryList();
	
	public Inventory getInventoryById(Long id);
	
	public void deleteInventoryById(long id);
	
	public List<Inventory> searchInventory(String keyword);
	
	public Inventory mapDtoToInventory(InventoryDTO inventoryDTO, Inventory inventory, ModelMapper modelMapper);
	
	public InventoryDTO mapInventoryToDTO(InventoryDTO inventoryDTO, Inventory inventory, ModelMapper modelMapper);
}

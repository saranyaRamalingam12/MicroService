package com.productandinventory.service.implementation;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.productandinventory.dto.ProductDTO;
import com.productandinventory.dto.InventoryDTO;
import com.productandinventory.entity.Inventory;
import com.productandinventory.entity.Product;
import com.productandinventory.exceptionhandling.InventoryException;
import com.productandinventory.repository.InventoryRepo;
import com.productandinventory.repository.ProductRepo;
import com.productandinventory.service.InventoryService;
@Service
public class InventoryServiceImpl implements InventoryService 
{
	@Autowired	InventoryRepo inventoryRepository;
	@Autowired	ProductRepo productRepository;

	@Override
	public void saveInventory(Inventory inventory) {
		inventoryRepository.save(inventory);
	}

	@Override
	public List<Inventory> getInventoryList() {
		return inventoryRepository.findAll();
	}

	@Override
	public Inventory getInventoryById(Long inventoryId) {
		return inventoryRepository.findById(inventoryId).orElseThrow(()->new InventoryException(inventoryId));	
	}

	@Override
	public void deleteInventoryById(long inventoryId) {
		inventoryRepository.deleteById(inventoryId);
	}

	@Override
	public List<Inventory> searchInventory(String keyword) {
		 return inventoryRepository.searchInventory(keyword);
	}

	@Override
	public Inventory mapDtoToInventory(InventoryDTO inventoryDTO, Inventory inventory, ModelMapper modelMapper) {
			inventory.setId(inventoryDTO.getId());
			inventory.setName(inventoryDTO.getName());
			if(inventoryDTO.getProductDTO()!=null)
			{
				Product product=	modelMapper.map(inventoryDTO.getProductDTO(),Product.class);
				inventory.setProduct(product);
			}
			inventory.setQuantity(inventoryDTO.getQuantity());
		return inventory;
	}

	@Override
	public InventoryDTO mapInventoryToDTO(InventoryDTO inventoryDTO, Inventory inventory, ModelMapper modelMapper) {
			inventoryDTO=new InventoryDTO();
			inventoryDTO.setId(inventory.getId());
			inventoryDTO.setName(inventory.getName());
			if(inventory.getProduct()!=null)
			{
				ProductDTO productDTO=	modelMapper.map(inventory.getProduct(),ProductDTO.class);
				productDTO.setInventoryDTO(null);
				inventoryDTO.setProductDTO(productDTO);
			}
			inventoryDTO.setQuantity(inventory.getQuantity());	
		return inventoryDTO;
	}

}

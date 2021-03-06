package com.productcatalog.service.implementation;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.productcatalog.dto.InventoryDTO;
import com.productcatalog.dto.ProductDTO;
import com.productcatalog.entity.Inventory;
import com.productcatalog.entity.Product;
import com.productcatalog.repository.InventoryRepo;
import com.productcatalog.repository.ProductRepo;
import com.productcatalog.service.InventoryService;


@Service
public class InventoryServiceImpl implements InventoryService 
{
	@Autowired	InventoryRepo inventoryRepository;
	@Autowired	ProductRepo productRepository;


	@Override
	public Optional<Inventory> getInventoryById(Long id) {
		return inventoryRepository.findById(id);	
	}

	@Override
	public List<Inventory> searchInventory(String keyword) {
		 return inventoryRepository.searchInventory(keyword);
	}

	@Override
	public Inventory mapDtoToInventory(InventoryDTO inventoryDTO, Inventory inventory, ModelMapper modelMapper) {
		try {
			inventory.setId(inventoryDTO.getId());
			inventory.setName(inventoryDTO.getName());
			if(inventoryDTO.getProductDTO()!=null)
			{
				Product product=	modelMapper.map(inventoryDTO.getProductDTO(),Product.class);
				inventory.setProduct(product);
			}
			inventory.setQuantity(inventoryDTO.getQuantity());
		//	inventory=modelMapper.map(inventoryDTO, Inventory.class);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return inventory;
	}

	@Override
	public InventoryDTO mapInventoryToDTO(InventoryDTO inventoryDTO, Inventory inventory, ModelMapper modelMapper) {
		try {
			inventoryDTO=new InventoryDTO();
			inventoryDTO.setId(inventory.getId());
			inventoryDTO.setName(inventory.getName());
			if(inventory.getProduct()!=null)
			{
				ProductDTO productDTO=	modelMapper.map(inventory.getProduct(),ProductDTO.class);
				inventoryDTO.setProductDTO(productDTO);
			}
			inventoryDTO.setQuantity(inventory.getQuantity());
			//inventoryDTO=modelMapper.map(inventory, InventoryDTO.class);
		}catch (Exception e) {
			e.printStackTrace();
		}
	
		return inventoryDTO;
	}



}

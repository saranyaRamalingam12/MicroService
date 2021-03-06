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
import com.productcatalog.exceptionhandling.ProductException;
import com.productcatalog.repository.InventoryRepo;
import com.productcatalog.repository.ProductRepo;
import com.productcatalog.service.InventoryService;
import com.productcatalog.service.ProductService;


@Service
public class ProductServiceImpl implements ProductService
{
	@Autowired	ProductRepo productRepository;
	@Autowired	InventoryRepo inventoryRepository;
	@Autowired	Inventory inventory;
	@Autowired	Product product;
	@Autowired	InventoryDTO inventoryDTO;
	@Autowired 	InventoryService  inventoryService;

	@Override
	public List<Product> getProductList() {
		return productRepository.findAll();
	}

	@Override
	public Product getProductById(Long productId) {
		return productRepository.findById(productId).orElseThrow(()-> new ProductException(productId) );
	}

	@Override
	public List<Product> searchProduct(String keyword) {
		 return productRepository.searchProduct(keyword);
	}

	@Override
	public ProductDTO mapProductToDTO(ProductDTO productDTO, Product product, ModelMapper modelMapper) {
			productDTO=new ProductDTO();
			inventoryDTO=new InventoryDTO();
			productDTO.setId(product.getId());
			productDTO.setProductName(product.getProductName());
			productDTO.setProductPrice(product.getProductPrice());
			productDTO.setBlockCount(product.getBlockCount());
			productDTO.setDescription(product.getDescription());
			productDTO.setImage(product.getImage());
			inventoryDTO.setId(product.getInventory().getId());
			inventoryDTO.setName(product.getInventory().getName());
			inventoryDTO.setQuantity(product.getInventory().getQuantity());
			productDTO.setInventoryDTO(inventoryDTO);
		return productDTO;
	}

	@Override
	public Product mapDtoToProduct(ProductDTO productDTO, Product product, ModelMapper modelMapper) {
		inventory=modelMapper.map(productDTO.getInventoryDTO(), Inventory.class);
		product.setId(productDTO.getId());
		product.setProductName(productDTO.getProductName());
		product.setBlockCount(productDTO.getBlockCount());
		product.setDescription(productDTO.getDescription());
		product.setProductPrice(productDTO.getProductPrice());
		product.setImage(productDTO.getImage());
		product.setInventory(inventory);
		//product=modelMapper.map(productDTO, Product.class);
		return product;	
	}

}

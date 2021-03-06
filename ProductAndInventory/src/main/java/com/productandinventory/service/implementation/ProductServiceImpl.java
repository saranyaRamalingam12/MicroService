package com.productandinventory.service.implementation;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.productandinventory.dto.InventoryDTO;
import com.productandinventory.dto.ProductDTO;
import com.productandinventory.entity.Inventory;
import com.productandinventory.entity.Product;
import com.productandinventory.exceptionhandling.ProductException;
import com.productandinventory.repository.InventoryRepo;
import com.productandinventory.repository.ProductRepo;
import com.productandinventory.service.InventoryService;
import com.productandinventory.service.ProductService;
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
	public void saveProduct(Product product) {
		 productRepository.save(product);
			inventory=product.getInventory();
			inventory.setProduct(product);
			inventoryService.saveInventory(inventory);
	}

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
	public void deleteProductById(long productId) {
		product=productRepository.findById(productId).orElseThrow(()-> new ProductException(productId) );
		inventory=product.getInventory();
		inventory.setProduct(null);
		productRepository.deleteById(productId);
		
	}

	@Override
	public ProductDTO mapProductToDTO(ProductDTO productDTO, Product product, ModelMapper modelMapper) {
		//	productDTO=new ProductDTO();
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

	// call from Cart service
	@Override
	public void blockProduct(int bookedQuantity, Long productId)
	{
			product=getProductById(productId);
			int block=product.getBlockCount();
			product.setBlockCount(block+bookedQuantity);
			saveProduct(product);
	}

	@Override
	public void productBlockRemoval(ProductDTO productDTO) {
		    product=getProductById(productDTO.getId());
			int bookedQuantity=product.getBlockCount()-productDTO.getBlockCount();
			product.setBlockCount(productDTO.getBlockCount());
			saveProduct(product);
			inventory=product.getInventory();
			int quantity=inventory.getQuantity();
			inventory.setQuantity(quantity-bookedQuantity);
			inventoryService.saveInventory(inventory);
	}

	@Override
	public void productBlockRemovalPaymentFailure(ProductDTO productDTO) {
		 product=getProductById(productDTO.getId());
		product.setBlockCount(productDTO.getBlockCount());
		saveProduct(product);
	}

}

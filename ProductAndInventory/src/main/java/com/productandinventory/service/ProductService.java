package com.productandinventory.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.productandinventory.dto.ProductDTO;
import com.productandinventory.entity.Product;
@Service
public interface ProductService
{
	public void saveProduct(Product product) ;
	
	public  List<Product> getProductList();
	
	public Product getProductById(Long id) ;
	
	public List<Product> searchProduct(String keyword);
	
	public void deleteProductById(long id);
	
	public ProductDTO mapProductToDTO(ProductDTO productDTO, Product product, ModelMapper modelMapper);
	
	public Product mapDtoToProduct(ProductDTO productDTO, Product product, ModelMapper modelMapper);

	public void blockProduct(int bookedQuantity, Long productId);

	public void productBlockRemoval(ProductDTO productDTO);

	public void productBlockRemovalPaymentFailure(ProductDTO productDTO);

}

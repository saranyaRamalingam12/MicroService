package com.productcatalog.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.productcatalog.dto.ProductDTO;
import com.productcatalog.entity.Product;

@Service
public interface ProductService
{	
	public  List<Product> getProductList();
	
	public Product getProductById(Long id) ;
	
	public List<Product> searchProduct(String keyword);
		
	public ProductDTO mapProductToDTO(ProductDTO productDTO, Product product, ModelMapper modelMapper);
	
	public Product mapDtoToProduct(ProductDTO productDTO, Product product, ModelMapper modelMapper);

}

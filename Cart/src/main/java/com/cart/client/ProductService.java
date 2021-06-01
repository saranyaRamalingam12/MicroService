package com.cart.client;

import org.springframework.stereotype.Service;

import com.cart.dto.ProductDTO;

@Service
public interface ProductService 
{
	public ProductDTO getProductById(Long id);

	public void productBlock(int bookedQuantity, Long productId);

	public void productBlockRemovalAfterFailure(ProductDTO productDTO);

	public void productBlockRemoval(ProductDTO productDTO);

	

	

}

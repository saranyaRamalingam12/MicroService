package com.orderhistory.client;

import org.springframework.stereotype.Service;

import com.orderhistory.dto.ProductDTO;

@Service
public interface ProductService {

	ProductDTO getProductById(Long id);

}

package com.productcatalog.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.productcatalog.dto.ProductDTO;
import com.productcatalog.entity.Product;
import com.productcatalog.exceptionhandling.ProductException;
import com.productcatalog.service.ProductService;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api/order/ProductCatalog")
public class ProductCatalogController //port 8083
{
	 private static final Logger LOGGER=LoggerFactory.getLogger(ProductCatalogController.class); 

	 
	@Autowired	private Product product;
	@Autowired	private ProductDTO productDTO;
	@Autowired	private ProductService productService;
	@Autowired	private ModelMapper modelMapper;
	
	
	@GetMapping("/getProductList")
	 public  ResponseEntity<List<ProductDTO>> getProductList()
	 {
			List<Product>productList=productService.getProductList();
			if(productList.isEmpty()) {
				LOGGER.warn("Retrieving the List but no element found");
				throw new ProductException("Product List is Empty");
			}
			LOGGER.info("ProductList Received from database");
			List<ProductDTO>dtoList=new ArrayList<>();
			for(Product list:productList){
				productDTO=productService.mapProductToDTO(productDTO, list, modelMapper);
				dtoList.add(productDTO);
			}	
			return new ResponseEntity<>(dtoList, HttpStatus.OK);
	 }
	
	@GetMapping("/editProduct/{productId}")
	public ResponseEntity<ProductDTO> editProduct(@PathVariable("productId") Long productId)
	{
		product=productService.getProductById(productId);
		productDTO=productService.mapProductToDTO(productDTO,product,modelMapper);
		LOGGER.info("The product is retrieved using id");
		return new ResponseEntity<>(productDTO, HttpStatus.OK);
	}
	
	@GetMapping("/searchProduct/{keyword}")
	public ResponseEntity<List<ProductDTO>>  searchProduct(@PathVariable String keyword)
	{
			List<Product>productList=productService.searchProduct(keyword);
			List<ProductDTO>dtoList=new ArrayList<>();
			if(productList.isEmpty()) {
				LOGGER.warn("Searching the Product but no element found");
				throw new ProductException("ProductList is Empty");
			}
			LOGGER.info("The Search is success");
			for(Product pro:productList){
				productDTO=productService.mapProductToDTO(productDTO, pro, modelMapper);
				dtoList.add(productDTO);
			}		
		return new ResponseEntity<>(dtoList, HttpStatus.OK);
	}
	

}

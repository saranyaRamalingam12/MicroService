package com.productandinventory.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.productandinventory.dto.ProductDTO;
import com.productandinventory.entity.Product;
import com.productandinventory.exceptionhandling.ProductException;
import com.productandinventory.service.ProductService;


@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api/order/Product")
public class ProductController //port 8082
{
	 private static final Logger LOGGER=LoggerFactory.getLogger(ProductController.class);
	@Autowired	private Product product;
	@Autowired	private ProductDTO productDTO;
	@Autowired	private ProductService productService;
	@Autowired	private ModelMapper modelMapper;
	
	@PostMapping("/addProduct")
	public ResponseEntity<HttpStatus> addProduct(@RequestBody ProductDTO productDTO)
	{
			product=productService.mapDtoToProduct(productDTO,product,modelMapper);
			productService.saveProduct(product);	
			return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/getProductList")
	 public ResponseEntity<List<ProductDTO>> getProductList()
	 { 
			List<Product>productList=productService.getProductList();
			if(productList.isEmpty()) {
				LOGGER.warn("Retrieving the List but no element found");
				throw new ProductException("Product List is Empty");
			}
			LOGGER.info("ProductList Received from database");
			List<ProductDTO>dtoList=new ArrayList<>();
			for(Product product:productList){
				productDTO=productService.mapProductToDTO(productDTO, product, modelMapper);
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
			if(productList.isEmpty()){
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
	
	@DeleteMapping("/deleteProduct/{id}")
	public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") Long id) 
	{
			productService.deleteProductById(id);
			LOGGER.info("Inventory is deleted ");
			return new ResponseEntity<>(HttpStatus.OK);
	}
	
	//call from Cart Service
	@GetMapping("/productBlock/{bookedQuantity}/{productId}")
	public ResponseEntity<HttpStatus> productBlock(@PathVariable("bookedQuantity") int bookedQuantity,@PathVariable("productId") Long productId)
	{
		    LOGGER.info("Service Call From Cart <-OrderHistory to block the product");
			productService.blockProduct(bookedQuantity,productId);
			return new ResponseEntity<>(HttpStatus.OK);
	}
	
	//call from Cart and OrderHistoryPayment
	@PostMapping("/productBlockRemoval")
	public ResponseEntity<HttpStatus> productBlockRemoval(@RequestBody ProductDTO productDTO)
	{
			LOGGER.info("Service Call From Cart <-OrderHistory to unBlock the product after successful payment");
			productService.productBlockRemoval(productDTO);
			return new ResponseEntity<>(HttpStatus.OK);
	}
	
	//call from Cart and OrderHistoryPayment
		@PostMapping("/productBlockRemovalPaymentFailure")
		public ResponseEntity<HttpStatus> productBlockRemovalPaymentFailure(@RequestBody ProductDTO productDTO)
		{
			LOGGER.info("Service Call From Cart <-OrderHistory to unBlock the product due to  payment failure");
			productService.productBlockRemovalPaymentFailure(productDTO);
			return new ResponseEntity<>(HttpStatus.OK);
		}
}

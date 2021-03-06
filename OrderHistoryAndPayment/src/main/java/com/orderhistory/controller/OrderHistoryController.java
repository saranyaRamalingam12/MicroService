package com.orderhistory.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orderhistory.client.CartService;
import com.orderhistory.client.ItemService;
import com.orderhistory.client.LoginService;
import com.orderhistory.dto.AddressDTO;
import com.orderhistory.dto.CartDTO;
import com.orderhistory.dto.OrderDetailsDTO;
import com.orderhistory.dto.UserDTO;
import com.orderhistory.dto.UserDetailsDTO;
import com.orderhistory.entity.OrderDetails;
import com.orderhistory.exceptionhandling.ResourceNotFoundException;
import com.orderhistory.service.OrderService;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api/order/OrderAndPayment")
public class OrderHistoryController //port 8085
{
	private static final Logger LOGGER=LoggerFactory.getLogger(OrderHistoryController.class);
	@Autowired CartService cartService;
	@Autowired OrderService orderService;
	@Autowired OrderDetails orderDetails;
	@Autowired CartDTO cartDTO;
	@Autowired UserDTO userDTO;
	@Autowired AddressDTO addressDTO;
	@Autowired LoginService loginService;
	@Autowired UserDetailsDTO userDetailsDTO;
	@Autowired ItemService itemService;
	
	
	@PostMapping("/getAddress")
	public ResponseEntity<UserDetailsDTO> getAddress(@RequestBody CartDTO cartDTO)
	{
			userDTO=loginService.findUserById(cartDTO.getUserId());
			userDetailsDTO=orderService.mapUserDTOToUserDetailsDTO(userDTO, cartDTO);
			LOGGER.info("Sending User and Cart Details for Payment");
			return new  ResponseEntity<>(userDetailsDTO, HttpStatus.OK);
	}
	
	@PostMapping("/paymentSuccess")
	public ResponseEntity<HttpStatus> paymentSuccess(@RequestBody UserDetailsDTO userDetailsDTO)
	{
	       
			if(cartService.findByCartId(userDetailsDTO.getCartDTO().getId())==null)
				  throw new ResourceNotFoundException("Cart Id "+userDetailsDTO.getCartDTO().getId()+"doesNot exist");
			userDTO=orderService.mapUserDetailsDTOToUserDTO(userDetailsDTO);
			loginService.mapAddressDTOToEntity(userDTO);//changing address in user
			LOGGER.info("Block Removal - payment success ");
			cartService.productBlockRemoval(userDetailsDTO.getCartDTO());//remove the block and reduce the stock in Inventory
			itemService.removeRefernceOfItemAndAmountReductionInCart(userDetailsDTO.getCartDTO());//removing Item from Cart
			orderService.saveOrderDetails(userDetailsDTO);
			return new ResponseEntity<>( HttpStatus.OK);
	}
	
	@PostMapping("/paymentFailure")
	public ResponseEntity<HttpStatus> paymentFail(@RequestBody UserDetailsDTO userDetailsDTO) 
	{
		if(cartService.findByCartId(userDetailsDTO.getCartDTO().getId())==null)
			  throw new ResourceNotFoundException("Cart Id "+userDetailsDTO.getCartDTO().getId()+"doesNot exist");
		cartService.productBlockRemovalPaymentFailure(userDetailsDTO.getCartDTO());
		LOGGER.info("Block Removal - payment Failure ");
		return new ResponseEntity<>( HttpStatus.OK);
	}
	
	@GetMapping("/OrderHistory/{userId}")
	public List<OrderDetailsDTO> getOrderHistoryForUser(@PathVariable("userId") Long userId)
	{
		List<OrderDetails>order=orderService.getOrderByCartId(userId);
		return orderService.mapOrderToOrderDTO(order);
	}
	
	@GetMapping("/OrderHistoryAdmin")
	public List<OrderDetailsDTO> getOrderHistoryForAdmin()
	{
		List<OrderDetails>order=orderService.getAllOrders();
		return orderService.mapOrderToOrderDTO(order);
	}
}

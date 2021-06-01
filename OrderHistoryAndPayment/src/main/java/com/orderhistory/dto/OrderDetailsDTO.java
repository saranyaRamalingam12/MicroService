package com.orderhistory.dto;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

@Service
public class OrderDetailsDTO
{
	private Long id;
	private UserDTO userDTO;
	private ProductDTO productDTO;
	private int bookedQuantity;
	private double productTotal;
	private LocalDateTime orderDate;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public UserDTO getUserDTO() {
		return userDTO;
	}
	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}
	public ProductDTO getProductDTO() {
		return productDTO;
	}
	public void setProductDTO(ProductDTO productDTO) {
		this.productDTO = productDTO;
	}
	public int getBookedQuantity() {
		return bookedQuantity;
	}
	public void setBookedQuantity(int bookedQuantity) {
		this.bookedQuantity = bookedQuantity;
	}
	public LocalDateTime getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}
	public double getProductTotal() {
		return productTotal;
	}
	public void setProductTotal(double productTotal) {
		this.productTotal = productTotal;
	}
}
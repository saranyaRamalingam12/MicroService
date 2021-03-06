package com.cart.dto;

import org.springframework.stereotype.Component;

@Component
public class SingleItemDTO 
{
	private Long id;
	private double productTotal;
	private int bookedQuantity;
	private ProductDTO productDTO;
	private Long  userId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public double getProductTotal() {
		return productTotal;
	}
	public void setProductTotal(double productTotal) {
		this.productTotal = productTotal;
	}
	public int getBookedQuantity() {
		return bookedQuantity;
	}
	public void setBookedQuantity(int bookedQuantity) {
		this.bookedQuantity = bookedQuantity;
	}
	
	public ProductDTO getProductDTO() {
		return productDTO;
	}
	public void setProductDTO(ProductDTO productDTO) {
		this.productDTO = productDTO;
	}
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	@Override
	public String toString() {
		return "SingleItemDTO [id=" + id + ", productTotal=" + productTotal + ", bookedQuantity=" + bookedQuantity
				+ ", productDTO=" + productDTO + ", userId=" + userId + "]";
	}
	
	

}

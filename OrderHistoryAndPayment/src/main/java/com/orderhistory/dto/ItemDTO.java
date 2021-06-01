package com.orderhistory.dto;

import org.springframework.stereotype.Component;


@Component
public class ItemDTO 
{
	private Long id;
	private double productTotal;
	private int bookedQuantity;
	private ProductDTO productDTO;
		
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
	
	@Override
	public String toString() {
		return "ItemDTO [id=" + id + ", productTotal=" + productTotal + ", bookedQuantity=" + bookedQuantity
				+ ", productId=" + productDTO+ "]";
	}

	
}

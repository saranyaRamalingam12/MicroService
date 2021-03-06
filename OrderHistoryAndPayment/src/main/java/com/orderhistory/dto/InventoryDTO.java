package com.orderhistory.dto;

import org.springframework.stereotype.Service;



@Service
public class InventoryDTO 
{
	private Long id;
	private String name;
	private int quantity;
	private ProductDTO productDTO;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public ProductDTO getProductDTO() {
		return productDTO;
	}
	public void setProductDTO(ProductDTO productDTO) {
		this.productDTO = productDTO;
	}

//	@Override
//	public String toString() {
//		return "Inventory [id=" + id + ", name=" + name + ", quantity=" + quantity + ", product=" + productDTO + "]";
//	}

}

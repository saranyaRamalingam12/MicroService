package com.productandinventory.dto;

import org.springframework.stereotype.Service;

@Service
public class ProductDTO 
{
	private Long id;
	private String productName;
	private String description;
	private double productPrice;
	private int blockCount;
	private String image;
	private InventoryDTO inventoryDTO;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	public int getBlockCount() {
		return blockCount;
	}
	public void setBlockCount(int blockCount) {
		this.blockCount = blockCount;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public InventoryDTO getInventoryDTO() {
		return inventoryDTO;
	}
	public void setInventoryDTO(InventoryDTO inventoryDTO) {
		this.inventoryDTO = inventoryDTO;
	}
//	@Override
//	public String toString() {
//		return "ProductDTO [id=" + id + ", productName=" + productName + ", description=" + description
//				+ ", productPrice=" + productPrice + ", blockCount=" + blockCount + ", image=" + image
//				+ ", inventoryDTO=" + inventoryDTO + "]";
//	}
	
}

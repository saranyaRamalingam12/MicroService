package com.cart.dto;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class CartDTO 
{
	private Long id;
	private double total;
	private Long  userId;
	private List<ItemDTO>itemDTO;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public List<ItemDTO> getItemDTO() {
		return itemDTO;
	}
	public void setItemDTO(List<ItemDTO> itemDTO) {
		this.itemDTO = itemDTO;
	}
	
	@Override
	public String toString() {
		return "CartDTO [id=" + id + ", total=" + total + ", userId=" + userId + ", itemDTO=" + itemDTO + "]";
	}
	
}

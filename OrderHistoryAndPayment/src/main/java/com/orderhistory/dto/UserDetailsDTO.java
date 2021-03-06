package com.orderhistory.dto;

import org.springframework.stereotype.Service;

@Service
public class UserDetailsDTO
{
	private Long id;
	private String name;
	private String email;
	private String password;
	private CartDTO cartDTO;
	private AddressDTO addressDTO;
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public CartDTO getCartDTO() {
		return cartDTO;
	}
	public void setCartDTO(CartDTO cartDTO) {
		this.cartDTO = cartDTO;
	}
	public AddressDTO getAddressDTO() {
		return addressDTO;
	}
	public void setAddressDTO(AddressDTO addressDTO) {
		this.addressDTO = addressDTO;
	}
	
	@Override
	public String toString() {
		return "UserDetailsDTO [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", cartDTO=" + cartDTO + ", addressDTO=" + addressDTO + "]";
	}
	
}

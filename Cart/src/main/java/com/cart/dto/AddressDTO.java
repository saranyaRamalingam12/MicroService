package com.cart.dto;

import org.springframework.stereotype.Service;

@Service
public class AddressDTO 
{
	private Long id;
	private String doorNumber;
	private String street;
	private String city;
	private String state;
	private int zipCode;
	private long phoneNumber;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDoorNumber() {
		return doorNumber;
	}
	public void setDoorNumber(String doorNumber) {
		this.doorNumber = doorNumber;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	public int getZipCode() {
		return zipCode;
	}
	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}
	public long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	@Override
	public String toString() {
		return "Address [id=" + id + ", doorNumber=" + doorNumber + ", street=" + street + ", city=" + city + ", state="
				+ state + ", zipCode=" + zipCode + ", phoneNumber=" + phoneNumber + "]";
	}
	

}

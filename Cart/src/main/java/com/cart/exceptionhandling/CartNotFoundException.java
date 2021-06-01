package com.cart.exceptionhandling;

public class CartNotFoundException extends RuntimeException 
{
	private static final long serialVersionUID = 1L;

	public CartNotFoundException(Long id)
	{
		//super(String.format("Cart with Id %d is Not Found",id));
		super("hii");
	}
}

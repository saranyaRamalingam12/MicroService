package com.cart.exceptionhandling;

public class ItemNotFoundException extends RuntimeException
{

	private static final long serialVersionUID = 1L;
	
	public ItemNotFoundException(Long id)
	{
		 super(String.format("Item with Id %d is Not Found",id));
	}

	public ItemNotFoundException(String msg) {
		super(msg);
	}
}

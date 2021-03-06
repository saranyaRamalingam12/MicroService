package com.cart.exceptionhandling;

public class UserNotFoundException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	public UserNotFoundException(Long  id) 
	{
		 super(String.format("User with Id %d is Not Found",id));
     }
}

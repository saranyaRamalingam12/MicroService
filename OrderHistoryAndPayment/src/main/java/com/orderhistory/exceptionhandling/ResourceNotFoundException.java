package com.orderhistory.exceptionhandling;

public class ResourceNotFoundException extends RuntimeException 
{
	
	private static final long serialVersionUID = 7702943349049313869L;

	public ResourceNotFoundException(String message) {
		super(message);
		
	}

}

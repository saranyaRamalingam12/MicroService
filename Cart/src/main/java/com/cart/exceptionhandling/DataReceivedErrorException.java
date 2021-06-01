package com.cart.exceptionhandling;

public class DataReceivedErrorException extends RuntimeException {

	
	private static final long serialVersionUID = 4914772902067268050L;
	
	public DataReceivedErrorException(String message) {
		super(message);
	}
	

}

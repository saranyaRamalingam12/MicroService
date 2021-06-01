package com.orderhistory.exceptionhandling;

public class DataReceivedErrorException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DataReceivedErrorException(String message) {
		super(message);
	}
	

}

package com.productcatalog.exceptionhandling;

public class ProductException extends RuntimeException 
{

	private static final long serialVersionUID = 1L;

	public ProductException(String string) {
		super(string);
	}
	
	public ProductException(Long productId) {
		super(String.format("ProductId %d does not exist",productId));
	}

}

package com.productandinventory.exceptionhandling;

public class InventoryException extends RuntimeException 
{

	private static final long serialVersionUID = 1L;
	
	public InventoryException(String string) {
		super(string);
	}

	public InventoryException(Long inventoryId) {
		super(String.format("Inventory Id %d does not exist",inventoryId));
	}


}

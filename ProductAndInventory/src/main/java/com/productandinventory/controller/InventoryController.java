package com.productandinventory.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.productandinventory.dto.InventoryDTO;
import com.productandinventory.entity.Inventory;
import com.productandinventory.exceptionhandling.InventoryException;
import com.productandinventory.service.InventoryService;



@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api/order/Inventory")
public class InventoryController 
{
	private static final Logger LOGGER=LoggerFactory.getLogger(InventoryController.class);
	
	@Autowired	private InventoryService inventoryService;
	@Autowired	private Inventory inventory; 
	@Autowired    private InventoryDTO inventoryDTO;
	@Autowired    private ModelMapper modelMapper;
	
	@GetMapping("/getInventoryList")
	 public ResponseEntity<List<InventoryDTO>> getInventoryList()
	 {
			List<Inventory>inList=inventoryService.getInventoryList();
			if(inList.isEmpty())
				throw new InventoryException("Inventory List is Empty");
			LOGGER.info("Inventory List retrieved from database");
			List<InventoryDTO>dtoList=new ArrayList<>();
			for(Inventory list:inList){
				inventoryDTO=inventoryService.mapInventoryToDTO(inventoryDTO, list, modelMapper);
				dtoList.add(inventoryDTO);
			}		
			return new ResponseEntity<>(dtoList, HttpStatus.OK);
	 }
	
	@PostMapping("/addInventory")
	public ResponseEntity<HttpStatus> addInventory(@RequestBody InventoryDTO inventoryDTO)
	{
			inventory=inventoryService.mapDtoToInventory(inventoryDTO,inventory,modelMapper);
			inventoryService.saveInventory(inventory);		
			LOGGER.info("New Inventory is saved in database");
			return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/editInventory/{inventoryId}")
	public ResponseEntity<InventoryDTO> editInventory(@PathVariable("inventoryId") Long inventoryId)
	{
		inventory=inventoryService.getInventoryById(inventoryId);
		LOGGER.info("The inventory is retrieved by id");
		inventoryDTO=inventoryService.mapInventoryToDTO(inventoryDTO,inventory,modelMapper);
		return new ResponseEntity<>(inventoryDTO, HttpStatus.OK);
	}
	
	@GetMapping("/searchInventory/{keyword}")
	public ResponseEntity<List<InventoryDTO>>  searchInventory(@PathVariable String keyword)
	{
			List<Inventory>inList=inventoryService.searchInventory(keyword);
			if(inList.isEmpty()) {
				LOGGER.warn("Searching the Inventory but no element found");
				throw new InventoryException("Inventory List is Empty");
			}
			List<InventoryDTO>dtoList=new ArrayList<>();
			for(Inventory list:inList){
				inventoryDTO=inventoryService.mapInventoryToDTO(inventoryDTO, list, modelMapper);
				dtoList.add(inventoryDTO);
			}	LOGGER.info("The Search is success");
			return new ResponseEntity<>(dtoList, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteInventory/{inventoryId}")
	public ResponseEntity<HttpStatus> deleteInventory(@PathVariable("inventoryId") long inventoryId) 
	{
			inventoryService.deleteInventoryById(inventoryId);
			LOGGER.info("Inventory is deleted ");
			return new ResponseEntity<>(HttpStatus.OK);
	}

}

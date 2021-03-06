package com.cart.service.implementation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cart.client.ProductService;
import com.cart.dto.CartDTO;
import com.cart.dto.ItemDTO;
import com.cart.dto.ProductDTO;
import com.cart.dto.SingleItemDTO;
import com.cart.dto.UserDTO;
import com.cart.entity.Cart;
import com.cart.entity.Item;
import com.cart.exceptionhandling.CartNotFoundException;
import com.cart.exceptionhandling.ItemNotFoundException;
import com.cart.repository.ItemRepo;
import com.cart.service.CartService;
import com.cart.service.ItemService;


@Service
public class ItemServiceImpl implements ItemService
{
	@Autowired Item item;
	@Autowired UserDTO userDTO;
	@Autowired CartService cartService;
	@Autowired ItemRepo itemRepo; 
	@Autowired ItemDTO itemDTO;
	@Autowired ProductDTO productDTO;	
	@Autowired ProductService productService;

		
	@Override
	public void deleteItemById(Long id) {
		itemRepo.deleteById(id);
	}

	@Override
	public void saveItem(Item item) {
		itemRepo.save(item);		
	}
	
	@Override
	public Item findItemById(Long itemId) {
		return itemRepo.findById(itemId).orElseThrow(()->new ItemNotFoundException(itemId));
	}
	
	
	@Override
	public List<Item> addItemToCart(List<Item> list, SingleItemDTO singleItemDTO) 
	{
		item=new Item();
		item=mapSingleITemDTOtoItem(item, singleItemDTO);
		list.add(item);
		return list;
	}
	
	@Override
	public Item mapSingleITemDTOtoItem(Item item, SingleItemDTO singleItemDTO) 
	{
		item.setBookedQuantity(singleItemDTO.getBookedQuantity());
		item.setProductId(singleItemDTO.getProductDTO().getId());
		item.setProductTotal(singleItemDTO.getProductTotal());
		return item;
	}

	@Override
	public List<ItemDTO> getItemFromCart(Cart cart) 
	{
		List<Item>itemList=cart.getItem();
 		List<ItemDTO>itemdtoList=new ArrayList<>();
 		for(Item item1:itemList){
 			itemDTO=new ItemDTO();
 			itemDTO=mapItemToDTO(item1,itemDTO);//breaking the loop product-inventory 11111
 			itemdtoList.add(itemDTO);
 		}
		return itemdtoList;
	}

	public ItemDTO mapItemToDTO(Item item, ItemDTO itemDTO) 
	{
		itemDTO.setBookedQuantity(item.getBookedQuantity());
		itemDTO.setId(item.getId());
		itemDTO.setProductTotal(item.getProductTotal());
		productDTO=productService.getProductById(item.getProductId());
		itemDTO.setProductDTO(productDTO);
		return itemDTO;
	}

	@Override
	public void removeRefernceOfItemAndAmountReductionInCart(Item item, Cart cart) 
	{
		double total= cart.getTotal();
	    total=total-item.getProductTotal();
	    cart.setTotal(total);
	    cartService.saveCart(cart);
	}

	@Override
	public void productBlock(CartDTO cart) {
		if(cart.getItemDTO()==null){
			throw new ItemNotFoundException("Item List is Empty");
		}
		List<ItemDTO>list=cart.getItemDTO();
		for(ItemDTO item:list){
			productService.productBlock(item.getBookedQuantity(),item.getProductDTO().getId());
		}
	}

	@Override
	public void deleteListOfItems(List<Item> itemList) {
		itemRepo.deleteAll(itemList);		
	}

	@Override
	public List<Item> mapItemDtoToItem(CartDTO cartDTO) 
	{
		List<Item>itemList=new ArrayList<>();
		List<ItemDTO>itemDTOList=cartDTO.getItemDTO();
		for(ItemDTO itemDTO:itemDTOList) {
			item=findItemById(itemDTO.getId());
			itemList.add(item);
		}
		return itemList;
	}


	




	
	
	

}

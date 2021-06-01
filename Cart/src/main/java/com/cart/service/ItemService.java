package com.cart.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.cart.dto.CartDTO;
import com.cart.dto.ItemDTO;
import com.cart.dto.SingleItemDTO;
import com.cart.dto.UserDTO;
import com.cart.entity.Cart;
import com.cart.entity.Item;


@Service
public interface ItemService 
{	
		
	public Item mapSingleITemDTOtoItem(Item item, SingleItemDTO singleItemDTO) ;

	public  List<Item> addItemToCart(List<Item> list, SingleItemDTO singleItemDTO);



	public void saveItem(Item item);

	public List<ItemDTO> getItemFromCart(Cart cart);

	public Item findItemById(Long id);

	public void removeRefernceOfItemAndAmountReductionInCart(Item item, Cart cart);

	public void deleteItemById(Long id);

	//public void productBlock(Cart cart);

	public void deleteListOfItems(List<Item> itemList);

	public void productBlock(CartDTO cart);

	public List<Item> mapItemDtoToItem(CartDTO cartDTO);


}

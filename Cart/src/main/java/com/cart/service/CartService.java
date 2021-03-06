package com.cart.service;


import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.cart.dto.CartDTO;
import com.cart.dto.SingleItemDTO;
import com.cart.dto.UserDTO;
import com.cart.entity.Cart;
import com.cart.entity.Item;

@Service
public interface CartService 
{
	
	public UserDTO getUserFromSingleItem(SingleItemDTO singleItemDTO);

	public double calcTotalAmount(Cart cart);

	public Cart savingNewUserToCart(SingleItemDTO singleItemDTO, Cart cart, UserDTO userDTO);

	public UserDTO saveCartInUser(Cart cart, UserDTO userDTO);
	
	public Cart findCartById(Long id);

	public Cart addItemToCart(SingleItemDTO singleItemDTO, Cart cart);

	public CartDTO mapCartToDTOIR(Cart cart, CartDTO cartDTO, ModelMapper modelMapper);

	public Cart saveCart(Cart cart);

	public Cart deleteItem(Item item, Cart cart);

	public Item checkItem(SingleItemDTO singleItemDTO,Long cartId);

	public void blockRemoval(CartDTO cartDTO);

	public void blockRemovalAfterFailure(CartDTO cartDTO);

}

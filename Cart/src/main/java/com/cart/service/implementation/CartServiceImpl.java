package com.cart.service.implementation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cart.client.LoginService;
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
import com.cart.repository.CartRepo;
import com.cart.service.CartService;
import com.cart.service.ItemService;



@Service
public class CartServiceImpl implements CartService
{
	@Autowired	LoginService loginService;
	@Autowired    ItemService itemService;
	@Autowired    CartRepo cartRepo;
	@Autowired    Cart cart1;
	@Autowired    ProductService productService;
	@Autowired    ProductDTO productDTO;
	
	private static final String ITEMERROR = "No Item Found in ItemList";
   //calling Login Service
	@Override
	public UserDTO getUserFromSingleItem(SingleItemDTO singleItemDTO) 
	{
		if(loginService.getUserById(singleItemDTO.getUserId())!=null){
			return loginService.getUserById(singleItemDTO.getUserId());
		}
		return null;
	}

	@Override
	public double calcTotalAmount(Cart cart) 
	{
		List<Item>list=cart.getItem();
		if(list.isEmpty())
			throw new ItemNotFoundException(ITEMERROR);
		double totalAmount=0;
		for(Item item1:list){
			totalAmount+=item1.getProductTotal();
		}
		return totalAmount;
	}

	@Override
	public Cart savingNewUserToCart(SingleItemDTO singleItemDTO, Cart cart, UserDTO userDTO)
	{
		List<Item>list=new ArrayList<>();
		list=itemService.addItemToCart(list,singleItemDTO);
		cart1.setItem(list);
		cart1.setUserId(userDTO.getId());
		cart1.setTotal(calcTotalAmount(cart1));
		cartRepo.save(cart1);
		return cart1;		
	}

	@Override
	public UserDTO saveCartInUser(Cart cart, UserDTO userDTO) 
	{
		userDTO.setCartId(cart.getId());
		loginService.saveCartIdInUser(userDTO);
		return userDTO;
	}

	@Override
	public Cart findCartById(Long cartId) {
		return cartRepo.findById(cartId).orElseThrow(()->new CartNotFoundException(cartId));
	}

	@Override
	public Cart addItemToCart(SingleItemDTO singleItemDTO, Cart cart) {
		List<Item>list=cart.getItem();
		list=itemService.addItemToCart(list,singleItemDTO);
		cart.setItem(list);
		cart.setTotal(calcTotalAmount(cart));
		cartRepo.save(cart);
		return cart;
	}

	@Override
	public CartDTO mapCartToDTOIR(Cart cart, CartDTO cartDTO, ModelMapper modelMapper) 
	{
		if(cart.getItem()==null)
			throw new ItemNotFoundException(ITEMERROR);
		List<ItemDTO>itemList=itemService.getItemFromCart(cart);
		cartDTO.setId(cart.getId());
		cartDTO.setItemDTO(itemList);
		cartDTO.setTotal(cart.getTotal());
		cartDTO.setUserId(cart.getUserId());
		return cartDTO;
	}

	@Override
	public Cart saveCart(Cart cart) {
	return cartRepo.save(cart);
	}

	@Override
	public Cart deleteItem(Item item, Cart cart) {
		if(cart.getItem()==null)
			throw new ItemNotFoundException(ITEMERROR);
		List<Item>itemList=cart.getItem();
		itemList.remove(item);
		cart.setItem(itemList);
		saveCart(cart);
		itemService.deleteItemById(item.getId());
		return cart;
	}

	@Override
	public Item checkItem(SingleItemDTO singleItemDTO,Long cartId) 
	{
		cart1=cartRepo.findById(cartId).orElseThrow(()->new CartNotFoundException(cartId));
		List<Item>list=cart1.getItem();
		for(Item item:list) {
			if(item.getProductId().equals(singleItemDTO.getProductDTO().getId())){
				return item;
			}
		}
		return null;
	}
	
	@Override
	public void blockRemoval(CartDTO cartDTO) 
	{
		if(cartDTO.getItemDTO()==null)
			throw new ItemNotFoundException(ITEMERROR);
		List<ItemDTO>itemList=cartDTO.getItemDTO();
		for(ItemDTO item:itemList){
			 productDTO=productService.getProductById(item.getProductDTO().getId());
			int blockCount=productDTO.getBlockCount();
			productDTO.setBlockCount(blockCount-item.getBookedQuantity());
			productService.productBlockRemoval(productDTO);
		}	
	}
	@Override
	public void blockRemovalAfterFailure(CartDTO cartDTO) 
	{
		if(cartDTO.getItemDTO()==null)
			throw new ItemNotFoundException(ITEMERROR);
		List<ItemDTO>itemList=cartDTO.getItemDTO();
		for(ItemDTO item:itemList) {
			 productDTO=productService.getProductById(item.getProductDTO().getId());
			 int blockCount=productDTO.getBlockCount();
			 productDTO.setBlockCount(blockCount-item.getBookedQuantity());
			 System.out.println(productDTO.toString());
			productService.productBlockRemovalAfterFailure(productDTO);	
		}	
	}
}


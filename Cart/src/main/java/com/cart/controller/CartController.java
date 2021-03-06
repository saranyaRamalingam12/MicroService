package com.cart.controller;

import java.util.List;

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

import com.cart.dto.AddressDTO;
import com.cart.dto.CartDTO;
import com.cart.dto.ItemDTO;
import com.cart.dto.ProductDTO;
import com.cart.dto.SingleItemDTO;
import com.cart.dto.UserDTO;
import com.cart.entity.Cart;
import com.cart.entity.Item;
import com.cart.exceptionhandling.CartNotFoundException;
import com.cart.exceptionhandling.ItemNotFoundException;
import com.cart.exceptionhandling.UserNotFoundException;
import com.cart.service.CartService;
import com.cart.service.ItemService;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api/order/ItemAndCart")
public class CartController //port 8084
{
	private static final Logger LOGGER=LoggerFactory.getLogger(CartController.class);
	AddressDTO addressDTO;
	@Autowired	UserDTO userDTO;
	@Autowired	Item item;
	@Autowired	Cart cart;
	@Autowired	CartService cartService;
	@Autowired	ItemService itemService;
	@Autowired  	ModelMapper modelMapper; 
	@Autowired	ProductDTO productDTO;
	@Autowired   CartDTO cartDTO;

	@PostMapping("/savingItemToCart")
	public ResponseEntity<CartDTO> savingItemAndCart (@RequestBody SingleItemDTO singleItemDTO)
	{
			if(singleItemDTO.getUserId()==null||cartService.getUserFromSingleItem(singleItemDTO)==null){
				throw new UserNotFoundException(singleItemDTO.getUserId());
			}
			userDTO=cartService.getUserFromSingleItem(singleItemDTO);
			if(userDTO.getCartId()==null)//new User check
			{
				LOGGER.info("User - new cart");
				cart=cartService.savingNewUserToCart(singleItemDTO,cart,userDTO);
				cartService.saveCartInUser(cart,userDTO);			    
			}
			else	{
				if(cartService.checkItem(singleItemDTO,userDTO.getCartId())==null)//check if Item is Present
				{
					LOGGER.info("cart exist - new item");
					 cart=cartService.findCartById(userDTO.getCartId());
					 cart=cartService.addItemToCart(singleItemDTO,cart);
				}else
				{
					LOGGER.info("cart exist -  item exist -override values");
					item=cartService.checkItem(singleItemDTO,userDTO.getCartId());
					item.setBookedQuantity(singleItemDTO.getBookedQuantity());
					item.setProductTotal(singleItemDTO.getProductTotal());
					itemService.saveItem(item);
					cart=cartService.findCartById(userDTO.getCartId());
					cart.setTotal(cartService.calcTotalAmount(cart));
					cartService.saveCart(cart);
				}
			}
			cartDTO=cartService.mapCartToDTOIR(cart, cartDTO, modelMapper);
			 return new ResponseEntity<>(cartDTO,HttpStatus.OK);
	}
		
	@GetMapping("/getCart/{cartId}")
	 public ResponseEntity<CartDTO> getCart(@PathVariable("cartId") Long cartId)
	 {
			 cart=cartService.findCartById(cartId);
			 LOGGER.info("finding cart By ID");
			 cartDTO=cartService.mapCartToDTOIR(cart, cartDTO, modelMapper);
			 return new ResponseEntity<>(cartDTO, HttpStatus.OK);
	 }
	
	
	@DeleteMapping("/removeItemFromCart/{itemId}/{cartId}")
	public  ResponseEntity<CartDTO> deleteItem(@PathVariable("itemId") Long itemId,@PathVariable("cartId") Long cartId)
	{
			item=itemService.findItemById(itemId);
			cart=cartService.findCartById(cartId);
			itemService.removeRefernceOfItemAndAmountReductionInCart(item,cart);
			cart=cartService.deleteItem(item,cart);
			cartDTO=cartService.mapCartToDTOIR(cart, cartDTO, modelMapper);
			 LOGGER.info("delete item from  cart ");
			return new ResponseEntity<>(cartDTO, HttpStatus.OK);
	}
	
	
	@PostMapping("/productBlock")
	public ResponseEntity<HttpStatus> blockProduct(@RequestBody CartDTO cartDTO)
	{
			itemService.productBlock(cartDTO);
			return new ResponseEntity<>( HttpStatus.OK);
	}
	
	//call from OrderHistory And Payment
	@GetMapping("/findCartBycartId/{cartId}")
	public ResponseEntity<CartDTO> findCartBycartId(@PathVariable("cartId") Long cartId)
	{
		 cart=cartService.findCartById(cartId);
		 cartDTO=cartService.mapCartToDTOIR(cart, cartDTO, modelMapper);
		 return new ResponseEntity<>(cartDTO, HttpStatus.OK);
	}
	
	
	//call from OrderHistory And Payment
	@PostMapping("/productBlockRemoval")
	public ResponseEntity<HttpStatus> blockProductRemoval(@RequestBody CartDTO cartDTO)
	{
		    cartService.findCartById(cartDTO.getId());
			cartService.blockRemoval(cartDTO);
			return new ResponseEntity<>( HttpStatus.OK);
	}
	
	//call from OrderHistory And Payment
		@PostMapping("/productBlockRemovalPaymentFailure")
		public ResponseEntity<HttpStatus> blockProductRemovalAfterFailure(@RequestBody CartDTO cartDTO)
		{
			cartService.findCartById(cartDTO.getId());
			cartService.blockRemovalAfterFailure(cartDTO);
			return new ResponseEntity<>( HttpStatus.OK);
		}
	
	//call from OrderHistory
	@PostMapping("/removeRefernceOfItem")
	public ResponseEntity<HttpStatus> removeRefernceOfItem(@RequestBody CartDTO cartDTO)
	{
			cart=cartService.findCartById(cartDTO.getId());
			List<ItemDTO>itemList=cartDTO.getItemDTO();
			double total= cart.getTotal();
			for(ItemDTO item1:itemList) {
			    total=total-item1.getProductTotal();
			}
			cart.setTotal(total);
			cartService.saveCart(cart);
			List<Item>list=itemService.mapItemDtoToItem(cartDTO);
			itemService.deleteListOfItems(list);
			return new ResponseEntity<>( HttpStatus.OK);
   }	
	
}

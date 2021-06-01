package com.orderhistory.client;

import java.util.List;

import org.springframework.stereotype.Service;

import com.orderhistory.dto.CartDTO;

@Service
public interface ItemService {

	void removeRefernceOfItemAndAmountReductionInCart(CartDTO cartDTO);

}

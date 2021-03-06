package com.orderhistory.client;

import org.springframework.stereotype.Service;

import com.orderhistory.dto.CartDTO;

@Service
public interface CartService {

	CartDTO findByCartId(Long id);

	void productBlockRemoval(CartDTO cartDTO);

	void productBlockRemovalPaymentFailure(CartDTO cartDTO);

}

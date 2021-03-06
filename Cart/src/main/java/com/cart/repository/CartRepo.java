package com.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cart.entity.Cart;
import com.cart.entity.Item;

@Repository
public interface CartRepo extends JpaRepository<Cart, Long> 
{

	Item findByIdAndItem_productId(Long cartId, Long productId);

}

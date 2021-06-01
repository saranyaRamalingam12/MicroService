package com.orderhistory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orderhistory.entity.OrderDetails;

@Repository
public interface OrderHistoryRepo extends JpaRepository<OrderDetails, Long>
{

	List<OrderDetails> findByuserId(Long userId);

}

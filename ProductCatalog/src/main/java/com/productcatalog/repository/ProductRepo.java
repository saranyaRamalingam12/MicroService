package com.productcatalog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.productcatalog.entity.Product;

public interface ProductRepo extends JpaRepository<Product, Long> {

	@Query(value = "SELECT p FROM Product p WHERE  p.productName LIKE '%' || :keyword || '%'"
			+ " OR p.description LIKE '%' || :keyword || '%'")
	List<Product> searchProduct(String keyword);

}

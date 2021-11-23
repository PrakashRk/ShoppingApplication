	package com.shoppingapp.repository;
	
	import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.shoppingapp.model.Products;
	
	public interface ProductsRepository extends CrudRepository<Products, Integer> {
		
		@Query("SELECT P FROM Products P WHERE P.productCode=?1")
		Products getProductByCode(String pCode);
		
	}
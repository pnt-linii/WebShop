package com.liniitriesit.shopgenerator.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liniitriesit.shopgenerator.domain.ProductSet;

public interface ProductSetRepository extends JpaRepository<ProductSet, String> {	
	List<ProductSet> readAllByShopId(Long id);
}

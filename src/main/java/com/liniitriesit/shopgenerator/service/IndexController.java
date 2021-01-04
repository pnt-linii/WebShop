package com.liniitriesit.shopgenerator.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liniitriesit.shopgenerator.domain.ProductSet;
import com.liniitriesit.shopgenerator.domain.Shop;
import com.liniitriesit.shopgenerator.domain.Shop.ShopType;
import com.liniitriesit.shopgenerator.repository.ProductSetRepository;
import com.liniitriesit.shopgenerator.repository.ShopRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/")
public class IndexController {
	private final ShopRepository shopRepository;
	private final ProductSetRepository productSetRepository; 

	Shop demoShop = Shop.builder()
			.id(Long.valueOf(1))
			.name("Demo Shop 01")
			.address("Example Street 01")
			.owner("Mrs. Owner")			
			.type(ShopType.GROCERIES).build();
	
	ProductSet demoProductSet = ProductSet.builder()
			.id("win20")
			.setName("Wnter Collection")			
			.build();
	

	@Autowired
	public IndexController(ShopRepository shopRepository, ProductSetRepository productSetRepository) {
		this.shopRepository = shopRepository;
		this.productSetRepository = productSetRepository;
		addDemoShop();
	}

	private void addDemoShop() {		
		productSetRepository.save(demoProductSet);		
		shopRepository.save(demoShop);
		demoProductSet.setShop(demoShop);
		demoShop.setProductSet(Arrays.asList(demoProductSet));
		productSetRepository.save(demoProductSet);		
		shopRepository.save(demoShop);
	}
}

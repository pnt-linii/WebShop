package com.liniitriesit.shopgenerator.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.liniitriesit.shopgenerator.domain.ProductSet;
import com.liniitriesit.shopgenerator.domain.Shop;
import com.liniitriesit.shopgenerator.domain.Shop.ShopType;
import com.liniitriesit.shopgenerator.repository.ProductSetRepository;
import com.liniitriesit.shopgenerator.repository.ShopRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/")
public class ShopDesignController {
	
	private final ShopRepository shopRepository;
	private final ProductSetRepository productSetRespository;
		
	@Autowired
	public ShopDesignController(ShopRepository shopRepository, ProductSetRepository productSetRespository) {
		this.shopRepository = shopRepository;
		this.productSetRespository = productSetRespository;	
	}
	
	@GetMapping("/createShop")
	public String showDesignForm(Model model) {
		ShopType[] shopTypes = Shop.ShopType.values();		
		model.addAttribute("shopType", Arrays.asList(shopTypes));
		return model.getAttribute("shopType").toString();
	}
	@PostMapping("/createShop")
	@ResponseStatus(HttpStatus.CREATED)
	public ModelAndView processPostShopDesign(@RequestBody Shop shop, Model model) {
		shopRepository.save(shop);
		return new ModelAndView("redirect:/login");		
	}	

	@GetMapping("/shops")
	Iterable<Shop> getAllShops() {
		log.debug("Get /shops call getAllShops()");
		return this.shopRepository.findAll();
	}

	@GetMapping("/shops/{id}")
	public ResponseEntity processGetShopById(@PathVariable("id") Long id) {
		Optional<Shop> shop = shopRepository.findById(id);
		if (shop.isPresent()) {
			return new ResponseEntity<>(shop.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

	@PatchMapping(path = "/shops/{id}", consumes = "application/json")
	public Shop processPatchShopById(@PathVariable("id") Long id, @RequestBody Shop patch) {
		Shop shop = shopRepository.findById(id).get();
		if (patch.getAddress() != null) {
			shop.setAddress(patch.getAddress());
		}
		if (patch.getUrl() != null) {
			shop.setUrl(patch.getUrl());
		}
		if (patch.getCurrency() != null) {
			shop.setCurrency(patch.getCurrency());
		}
		if (patch.getLocale() != null) {
			shop.setLocale(patch.getLocale());
		}		
		if (patch.getProductSet() != null) {
			shop.setProductSet(patch.getProductSet());
		}
		return shopRepository.save(shop);
	}

	@DeleteMapping("/shops/{id}")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public void processDeleteShop(@PathVariable Long id) {
		try {
			shopRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			log.info("An error occured on an attempt to delete a shop");
		}
	}
	@GetMapping("/shops/{id}/productSets")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public Optional<List<ProductSet>> showProductSetByShopId(@PathVariable Long id) {
		Optional<List<ProductSet>> availableProductSet = Optional.of(productSetRespository.readAllByShopId(id));
		if (availableProductSet.isPresent()) {
			return availableProductSet;
		} else {
			return null;
		}		
	}
	
	
	
	
	
}

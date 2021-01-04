package com.liniitriesit.shopgenerator.domain;

import java.net.URL;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Shop {
	
	@Id
	@GeneratedValue
	private Long id;
	private String name;	
	private String owner;
	private String address;	
	private URL url;
	private ShopType type;
	private String[] currency;
	private String[] locale;
	@OneToMany
	@JoinColumn(name="PRODUCT_SET", referencedColumnName="Id")
	private List<ProductSet> productSet;
	
	public enum ShopType {
		FASHION, GROCERIES, PHARMACY
	}
}


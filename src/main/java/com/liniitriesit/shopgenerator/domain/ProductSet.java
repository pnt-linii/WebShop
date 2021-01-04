package com.liniitriesit.shopgenerator.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductSet {
	@Id
	private String id;	
	private String setName;	
	@NotNull	
	@ManyToOne
	@JoinColumn(name="SHOP_ID", referencedColumnName="Id")
	private Shop shop;
	
}

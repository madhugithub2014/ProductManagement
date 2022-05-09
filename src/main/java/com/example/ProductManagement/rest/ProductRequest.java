package com.example.ProductManagement.rest;

import lombok.Getter;

@Getter
public class ProductRequest {
	private Long id;
	private String name;
	private String description;

	public ProductCommand toCommand() {
		return ProductCommand.builder().id(id).name(name).description(description).build();
	}
}

package com.example.ProductManagement.rest;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductCommand {
	private Long id;
	private String name;
	private String description;
}

package com.example.ProductManagement.service;

import com.example.ProductManagement.domain.Product;
import com.example.ProductManagement.repository.ProductJpaRepository;
import com.example.ProductManagement.rest.ProductCommand;

import java.util.List;


public class ProductService {

    private ProductJpaRepository repository;

    public ProductService(ProductJpaRepository repository) {
        this.repository = repository;
    }

    public Product save(ProductCommand productCommand) {
        return repository.save(Product.builder().id(productCommand.getId()).name(productCommand.getName())
                .description(productCommand.getDescription()).build());

    }

    public List<Product> getProducts() {
        return repository.findAll();
    }

    public Product getProduct(Long productId) {
        return repository.findById(productId).get();
    }

    public Product updateProduct(Long productId, ProductCommand productCommand) {
        Product product = repository.findById(productId).get();
        return repository.save(Product.builder().id(product.getId()).name(productCommand.getName())
                .description(productCommand.getDescription()).build());
    }
}

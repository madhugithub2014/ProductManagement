package com.example.ProductManagement.config;

import com.example.ProductManagement.repository.ProductJpaRepository;
import com.example.ProductManagement.service.ProductService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfig {
    @Bean
    public ProductService productService(ProductJpaRepository repository) {
        return new ProductService(repository);
    }
}

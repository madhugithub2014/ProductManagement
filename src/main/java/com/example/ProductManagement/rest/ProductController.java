package com.example.ProductManagement.rest;

import com.example.ProductManagement.domain.Product;
import com.example.ProductManagement.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static java.util.Optional.ofNullable;
import static org.springframework.http.ResponseEntity.status;


@RestController
@RequestMapping("/v1/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping
    public ResponseEntity save(@RequestBody ProductRequest productRequest) {
        return ofNullable(productService.save(productRequest.toCommand()))
                .map(product -> status(HttpStatus.CREATED).build())
                .orElseGet(() -> status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @GetMapping
    public ResponseEntity getProducts() {
        return ResponseEntity.ok(productService.getProducts());
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable("id") Long productId) {
        return productService.getProduct(productId);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Long productId, @RequestBody ProductRequest productRequest) {
        return ofNullable(productService.updateProduct(productId, productRequest.toCommand()))
                .map(product -> status(HttpStatus.OK).build())
                .orElseGet(() -> status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }
}

package com.example.ProductManagement.rest;

import com.example.ProductManagement.domain.Product;
import com.example.ProductManagement.repository.ProductJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ProductJpaRepository repository;

    @Test
    void should_return_new_product_when_request_received() throws Exception {
        //Given
        RequestBuilder requestBuilder = post("/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(parseJsonFile("product.json"));

        //When
        mockMvc.perform(requestBuilder)
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        //Then
        Product product = repository.findById(1111l).get();
        assertProduct(product);
    }

    @Test
    void should_return_all_product_when_request_received() throws Exception {
        //Given
        repository.save(Product.builder().id(1111l).name("test").description("test description").build());

        //Then
        List<Product> products = repository.findAll();
        assertThat(products.size()).isEqualTo(1);
        Product product = products.get(0);
        assertProduct(product);
    }

    @Test
    void should_return_product_when_request_received_with_product_code() throws Exception {
        //Given
        repository.save(Product.builder().id(1111l).name("test").description("test description").build());

        //Then
        Product product = repository.findById(1111l).get();
        assertProduct(product);
    }

    @Test
    void should_return_product_when_request_received_to_product() throws Exception {
        //Given
        repository.save(Product.builder().id(1111l).name("test").description("test description").build());
        RequestBuilder requestBuilder = put("/v1/products/1111")
                .contentType(MediaType.APPLICATION_JSON)
                .content(parseJsonFile("update-product.json"));

        //When
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        //Then
        Product product = repository.findById(1111l).get();
        assertThat(product.getId()).isEqualTo(1111);
        assertThat(product.getName()).isEqualTo("test2222");
        assertThat(product.getDescription()).isEqualTo("test2222 description");
    }

    private static String parseJsonFile(String fileName) throws IOException {
        File file = ResourceUtils.getFile("classpath:request/" + fileName);
        return new String(Files.readAllBytes(file.toPath()));
    }

    private static void assertProduct(Product product) {
        assertThat(product.getId()).isEqualTo(1111);
        assertThat(product.getName()).isEqualTo("test");
        assertThat(product.getDescription()).isEqualTo("test description");
    }
}
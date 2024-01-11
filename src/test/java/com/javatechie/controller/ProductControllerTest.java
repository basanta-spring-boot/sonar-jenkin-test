package com.javatechie.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javatechie.entity.Product;
import com.javatechie.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void testAddProduct() throws Exception {
        // Setup
        // Configure ProductService.saveProduct(...).
        final Product product = new Product(0, "name", 0.0, "description", "productType");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/products")
                        .content(Objects.requireNonNull(asJsonString(product))).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    private String asJsonString(Product product) {
        try {
            return new ObjectMapper().writeValueAsString(product);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Test
    void testGetProducts() throws Exception {
        // Setup
        // Configure ProductService.getProducts(...).
        final List<Product> products = List.of(new Product(0, "name", 0.0, "description", "productType"));


        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/products")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testGetProducts_ProductServiceReturnsNoItems() throws Exception {
        // Setup

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/products")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("[]");
    }

    @Test
    void testGetProductById() throws Exception {
        // Setup
        // Configure ProductService.getProductById(...).
        final Product product = new Product(0, "name", 0.0, "description", "productType");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/products/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testUpdateProduct() throws Exception {
        // Setup
        // Configure ProductService.updateProduct(...).
        final Product product = new Product(0, "name", 0.0, "description", "productType");
        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(put("/products/{id}", 0)
                        .content(asJsonString(product)).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testDeleteProduct() throws Exception {
        // Setup


        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(delete("/products/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}

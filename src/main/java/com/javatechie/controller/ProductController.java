package com.javatechie.controller;

import com.javatechie.entity.Product;
import com.javatechie.exception.ProductNotFoundException;
import com.javatechie.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping
    public Product addProduct(@RequestBody @Valid Product product) {
        Product saveProduct = service.saveProduct(product);
        return saveProduct;
    }

    @GetMapping
    public List<Product> getProducts() {
        return service.getProducts();
    }

    @GetMapping("/find/{id}")
    public Product getProductById(@PathVariable int id) throws Exception {
        return service.getProductById(id);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable int id, @RequestBody Product productRequest) throws Exception {
        return service.updateProduct(id, productRequest);
    }

    @DeleteMapping("/{id}")
    public long deleteProduct(@PathVariable int id) {
        return service.deleteProduct(id);
    }

}

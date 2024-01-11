package com.javatechie.controller;

import com.javatechie.entity.Product;
import com.javatechie.respository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductInterviewController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/search/{productType}")
    public List<Product> findProductsByType(@PathVariable String productType) {
        return productRepository.findByProductType(productType);
    }

    @GetMapping("/search")
    public List<Product> findProductsByType2(@RequestParam(value = "productType", required = false) String productType) {
        return (productType != null)
                ? productRepository.findByProductType(productType)
                : productRepository.findAll();
    }
}

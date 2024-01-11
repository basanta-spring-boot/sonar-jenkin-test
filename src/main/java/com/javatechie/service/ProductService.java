package com.javatechie.service;

import com.javatechie.entity.Product;
import com.javatechie.exception.ProductNotFoundException;
import com.javatechie.exception.ProductServiceException;
import com.javatechie.respository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;


    public Product saveProduct(Product product) {
        return repository.save(product);
    }

    public List<Product> getProducts() {
        return repository.findAll();
    }

    public Product getProductById(int id) throws ProductNotFoundException {
        try {
            return repository.findById(id).get();

        } catch (Exception ex) {
            throw new ProductNotFoundException("product is not available with id " + id);
        }
    }

    public Product updateProduct(int id, Product productRequest) throws Exception {
        Product existingProduct = null;
        try {
            existingProduct = repository.findById(id).get();
        } catch (Exception ex) {
            throw new ProductServiceException("product not found with id " + id);
        }
        existingProduct.setName(productRequest.getName());
        existingProduct.setDescription(productRequest.getDescription());
        existingProduct.setPrice(productRequest.getPrice());
        existingProduct.setProductType(existingProduct.getProductType());
        return repository.save(existingProduct);
    }

    public long deleteProduct(int id) {
        repository.deleteById(id);
        return repository.count();
    }


    public List<Product> fetchProductsByType(String productType) {
        return repository.findByProductType(productType);
    }

}

package com.javatechie.service;

import com.javatechie.entity.Product;
import com.javatechie.exception.ProductNotFoundException;
import com.javatechie.respository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository mockRepository;

    @InjectMocks
    private ProductService productServiceUnderTest;

    @Test
    void testSaveProduct() {
        // Setup
        final Product product = new Product(0, "name", 0.0, "description", "productType");
        final Product expectedResult = new Product(0, "name", 0.0, "description", "productType");

        // Configure ProductRepository.save(...).
        final Product product1 = new Product(0, "name", 0.0, "description", "productType");
        when(mockRepository.save(new Product(0, "name", 0.0, "description", "productType"))).thenReturn(product1);

        // Run the test
        final Product result = productServiceUnderTest.saveProduct(product);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetProducts() {
        // Setup
        final List<Product> expectedResult = List.of(new Product(0, "name", 0.0, "description", "productType"));

        // Configure ProductRepository.findAll(...).
        final List<Product> products = List.of(new Product(0, "name", 0.0, "description", "productType"));
        when(mockRepository.findAll()).thenReturn(products);

        // Run the test
        final List<Product> result = productServiceUnderTest.getProducts();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetProducts_ProductRepositoryReturnsNoItems() {
        // Setup
        when(mockRepository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<Product> result = productServiceUnderTest.getProducts();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testGetProductById() throws ProductNotFoundException {
        // Setup
        final Product expectedResult = new Product(0, "name", 0.0, "description", "productType");

        // Configure ProductRepository.findById(...).
        final Optional<Product> product = Optional.of(new Product(0, "name", 0.0, "description", "productType"));
        when(mockRepository.findById(0)).thenReturn(product);

        // Run the test
        final Product result = productServiceUnderTest.getProductById(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetProductById_ProductRepositoryReturnsAbsent() {
        // Setup
        when(mockRepository.findById(0)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> productServiceUnderTest.getProductById(0))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void testUpdateProduct() throws Exception {
        // Setup
        final Product productRequest = new Product(0, "name", 0.0, "description", "productType");
        final Product expectedResult = new Product(0, "name", 0.0, "description", "productType");

        // Configure ProductRepository.findById(...).
        final Optional<Product> product = Optional.of(new Product(0, "name", 0.0, "description", "productType"));
        when(mockRepository.findById(0)).thenReturn(product);

        // Configure ProductRepository.save(...).
        final Product product1 = new Product(0, "name", 0.0, "description", "productType");
        when(mockRepository.save(new Product(0, "name", 0.0, "description", "productType"))).thenReturn(product1);

        // Run the test
        final Product result = productServiceUnderTest.updateProduct(0, productRequest);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    //@Test
    void testUpdateProduct_ProductRepositoryFindByIdReturnsAbsent() {
        // Setup
        final Product productRequest = new Product(0, "name", 0.0, "description", "productType");
        when(mockRepository.findById(0)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> productServiceUnderTest.updateProduct(0, productRequest))
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void testDeleteProduct() {
        // Setup
        when(mockRepository.count()).thenReturn(0L);

        // Run the test
        final long result = productServiceUnderTest.deleteProduct(0);

        // Verify the results
        assertThat(result).isEqualTo(0L);
        verify(mockRepository).deleteById(0);
    }
}

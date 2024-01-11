package com.javatechie;

import com.javatechie.entity.Product;
import com.javatechie.respository.ProductRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "PRODUCT SERVICE",version = "v 3.0",description = "PRODUCT SERVICE ENDPOINTS"))
public class SpringDataJpaApplication {
    @Autowired
    private ProductRepository productRepository;

//    @PostConstruct
//    public void init(){
//        List<Product> productList = Stream.of(
//                new Product("Mobile", 9500, "SAMSUNG Galaxy F13 (Sunrise Copper, 64 GB)", "Electronics"),
//                new Product("Keyboard", 9500, "MAC Magic Keyboard", "Electronics"),
//                new Product("Books", 250, "It Ends With Us", "Education"),
//                new Product("Remote Control Toys", 699, "Wembley High Speed Mini 1:24 Scale Rechargeable Remote Control car with Lithium Battery", "Baby&Kids")
//        ).collect(Collectors.toList());
//        productRepository.saveAll(productList);
//
//    }

    public static void main(String[] args) {
        SpringApplication.run(SpringDataJpaApplication.class, args);
    }

}

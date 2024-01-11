package com.javatechie.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Product name shouldn't be NULL OR EMPTY")
    private String name;
    @Min(value = 100, message = "Product price can't be less than 1500")
    private double price;
    @NotBlank(message = "Product description shouldn't be NULL OR EMPTY")
    //@Length(min = 50, max = 1000)
    private String description;
    @NotEmpty(message = "productType must be present")
    private String productType;

//    @Email(message = "invalid email id")
//    @Pattern(regexp = "^[0-9]{10}$")
//    @Past(message = "start date can't be before date from current")


    public Product(String name, double price, String description, String productType) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.productType = productType;
    }

}

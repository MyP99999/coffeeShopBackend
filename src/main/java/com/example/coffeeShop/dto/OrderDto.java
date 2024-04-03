package com.example.coffeeShop.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

// Example DTO structure - make sure it's created in your codebase
@Getter
@Setter
public class OrderDto {
    private Integer userId;
    private Integer productId;
    private Date orderDate;
    private Integer quantity;
    private Double totalPrice;
    private String status;
    // Getters and setters
}

package com.idat.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailsRegistration {
    private String productName;
    private int quantity;
    private Double price;
    private Double totalAmount;
    private Double taxesAmount;
    private Double total;
}

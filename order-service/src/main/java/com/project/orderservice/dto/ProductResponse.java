package com.project.orderservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponse {

    private Long productId;
    private String name;
    private Double price;
    private Integer quantity;
}
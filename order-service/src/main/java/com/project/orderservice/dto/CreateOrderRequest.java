package com.project.orderservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOrderRequest {

    private String customerName;
    private Long productId;
    private Integer quantity;
    private String paymentMethod;
}
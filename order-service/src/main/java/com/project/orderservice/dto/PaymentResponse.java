package com.project.orderservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentResponse {

    private Long id;
    private Double amount;
    private String paymentMethod;
    private String status;
}
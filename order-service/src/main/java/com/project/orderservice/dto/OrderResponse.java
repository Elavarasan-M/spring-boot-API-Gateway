package com.project.orderservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderResponse {

    private Long id;
    private String customerName;
    private Long productId;
    private Long paymentId;
    private Integer quantity;
    private Double totalPrice;
    private String status;

    public OrderResponse() {
    }

    public OrderResponse(Long id,
                         String customerName,
                         Long productId,
                         Long paymentId,
                         Integer quantity,
                         Double totalPrice,
                         String status) {
        this.id = id;
        this.customerName = customerName;
        this.productId = productId;
        this.paymentId = paymentId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    // getters and setters
}
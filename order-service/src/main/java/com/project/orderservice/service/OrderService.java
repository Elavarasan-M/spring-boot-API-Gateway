package com.project.orderservice.service;

import com.project.orderservice.dto.CreateOrderRequest;
import com.project.orderservice.dto.OrderResponse;
import com.project.orderservice.dto.PaymentRequest;
import com.project.orderservice.dto.PaymentResponse;
import com.project.orderservice.dto.ProductResponse;
import com.project.orderservice.entity.Order;
import com.project.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient webClient;

    public OrderService(OrderRepository orderRepository,
                        WebClient.Builder webClientBuilder) {
        this.orderRepository = orderRepository;
        this.webClient = webClientBuilder.build();
    }

    // Create Order
    public Mono<OrderResponse> createOrder(CreateOrderRequest request) {

        return webClient.get()
                .uri("http://localhost:8081/api/v1/products/{productId}", request.getProductId())
                .retrieve()
                .bodyToMono(ProductResponse.class)

                .flatMap(product -> {

                    double totalPrice = product.getPrice() * request.getQuantity();

                    PaymentRequest paymentRequest = new PaymentRequest();
                    paymentRequest.setAmount(totalPrice);
                    paymentRequest.setPaymentMethod(request.getPaymentMethod());

                    return webClient.post()
                            .uri("http://localhost:8083/api/payments")
                            .bodyValue(paymentRequest)
                            .retrieve()
                            .bodyToMono(PaymentResponse.class)

                            .map(payment -> {

                                Order order = new Order();
                                order.setCustomerName(request.getCustomerName());
                                order.setProductId(product.getProductId());
                                order.setPaymentId(payment.getId());
                                order.setQuantity(request.getQuantity());
                                order.setTotalPrice(totalPrice);
                                order.setStatus(payment.getStatus());

                                Order savedOrder = orderRepository.save(order);

                                return convertToResponse(savedOrder);
                            });

                });
    }

    // Get All Orders
    public List<OrderResponse> getAllOrders() {

        return orderRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    // Get Order By Id
    public OrderResponse getOrderById(Long id) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        return convertToResponse(order);
    }

    // Update Order
    public OrderResponse updateOrder(Long id, CreateOrderRequest request) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setCustomerName(request.getCustomerName());
        order.setProductId(request.getProductId());
        order.setQuantity(request.getQuantity());

        Order updatedOrder = orderRepository.save(order);

        return convertToResponse(updatedOrder);
    }

    // Delete Order
    public void deleteOrder(Long id) {

        if (!orderRepository.existsById(id)) {
            throw new RuntimeException("Order not found");
        }

        orderRepository.deleteById(id);
    }

    // Convert Entity to DTO
    private OrderResponse convertToResponse(Order order) {

        return new OrderResponse(
                order.getId(),
                order.getCustomerName(),
                order.getProductId(),
                order.getPaymentId(),
                order.getQuantity(),
                order.getTotalPrice(),
                order.getStatus()
        );
    }

}
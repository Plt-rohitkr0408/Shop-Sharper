package com.shopsharper.auth_service.service;

import com.shopsharper.auth_service.dto.request.OrderPlacedRequest;
import com.shopsharper.auth_service.dto.response.OrderResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    OrderResponse orderPlaced(Long userId, OrderPlacedRequest orderPlacedRequest);
    OrderResponse getOrderById(Long orderId);

    Page<OrderResponse> getUserOrders(Long userId,
                                      Pageable pageable);

    Page<OrderResponse> getOrdersByStatus(String status,
                                          Pageable pageable);

    String cancelOrder(Long orderId);

}

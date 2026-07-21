package com.shopsharper.auth_service.controller;


import com.shopsharper.auth_service.dto.request.OrderPlacedRequest;
import com.shopsharper.auth_service.dto.response.ApiResponse;
import com.shopsharper.auth_service.dto.response.OrderResponse;
import com.shopsharper.auth_service.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

        @PostMapping("/{userId}")
        public ResponseEntity<ApiResponse<OrderResponse>> placeOrder(
                @PathVariable Long userId,
                @Valid @RequestBody OrderPlacedRequest request) {

            OrderResponse response =
                    orderService.orderPlaced(userId, request);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ApiResponse.<OrderResponse>builder()
                            .success(true)
                            .message("Order placed successfully")
                            .data(response)
                            .build());
        }

        @GetMapping("/{orderId}")
        public ResponseEntity<ApiResponse<OrderResponse>> getOrderById(
                @PathVariable Long orderId) {

            OrderResponse response =
                    orderService.getOrderById(orderId);

            return ResponseEntity.ok(
                    ApiResponse.<OrderResponse>builder()
                            .success(true)
                            .message("Order fetched successfully")
                            .data(response)
                            .build()
            );
        }

        @GetMapping("/user/{userId}")
        public ResponseEntity<ApiResponse<Page<OrderResponse>>> getUserOrders(
                @PathVariable Long userId,
                @PageableDefault(size = 10, sort = "orderDate")
                Pageable pageable) {

            Page<OrderResponse> response =
                    orderService.getUserOrders(userId, pageable);

            return ResponseEntity.ok(
                    ApiResponse.<Page<OrderResponse>>builder()
                            .success(true)
                            .message("Orders fetched successfully")
                            .data(response)
                            .build()
            );
        }
        @GetMapping("/status")
        public ResponseEntity<ApiResponse<Page<OrderResponse>>> getOrdersByStatus(
                @RequestParam String status,
                @PageableDefault(size = 10, sort = "orderDate")
                Pageable pageable) {

            Page<OrderResponse> response =
                    orderService.getOrdersByStatus(status, pageable);

            return ResponseEntity.ok(
                    ApiResponse.<Page<OrderResponse>>builder()
                            .success(true)
                            .message("Orders fetched successfully")
                            .data(response)
                            .build()
            );
        }

        @PutMapping("/{orderId}/cancel")
        public ResponseEntity<ApiResponse<String>> cancelOrder(
                @PathVariable Long orderId) {

            String response =
                    orderService.cancelOrder(orderId);

            return ResponseEntity.ok(
                    ApiResponse.<String>builder()
                            .success(true)
                            .message("Order cancelled successfully")
                            .data(response)
                            .build()
            );
        }

    }


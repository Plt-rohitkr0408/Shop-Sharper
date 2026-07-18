package com.shopsharper.auth_service.Mapper;

import com.shopsharper.auth_service.dto.response.OrderItemResponse;
import com.shopsharper.auth_service.dto.response.OrderResponse;
import com.shopsharper.auth_service.entity.Order;
import com.shopsharper.auth_service.entity.OrderItem;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderMapper {
    public OrderItemResponse orderItemResponse(OrderItem orderItem){
        OrderItemResponse orderItemResponse = new OrderItemResponse();
        orderItemResponse.setPrice(orderItem.getPrice());
        orderItemResponse.setQuantity(orderItem.getQuantity());
        orderItemResponse.setProductId(orderItem.getProduct().getId());
        orderItemResponse.setProductName(orderItem.getProduct().getName());
        orderItemResponse.setSubtotal(orderItem.getTotalPrice());
        return orderItemResponse;
    }

    public List<OrderItemResponse> orderItemResponseList(List<OrderItem> orderItemList){
        return orderItemList.stream().map(m -> orderItemResponse(m)).toList();
    }

    public OrderResponse  orderResponse(Order order){
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setOrderDate(order.getOrderDate());
        orderResponse.setStatus(order.getStatus());
        orderResponse.setTotalAmount(order.getTotalAmount());
        orderResponse.setPaymentMethod(order.getPaymentMethod().toString());
        orderResponse.setOrderId(order.getId());
        orderResponse.setItems(orderItemResponseList(order.getOrderItems()));

        return orderResponse;
    }
}

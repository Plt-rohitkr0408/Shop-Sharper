package com.shopsharper.auth_service.service.impl;

import com.shopsharper.auth_service.Mapper.OrderMapper;
import com.shopsharper.auth_service.dto.request.OrderPlacedRequest;
import com.shopsharper.auth_service.dto.response.OrderResponse;
import com.shopsharper.auth_service.entity.*;
import com.shopsharper.auth_service.enums.OrderStatus;
import com.shopsharper.auth_service.enums.PaymentMethod;
import com.shopsharper.auth_service.repository.*;
import com.shopsharper.auth_service.service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private final UserRepository userRepository;
   private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    private  final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    public OrderServiceImpl(UserRepository userRepository, OrderRepository orderRepository, OrderItemRepository orderItemRepository, ProductRepository productRepository, CartRepository cartRepository, CartItemRepository cartItemRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
    }

    public User getUser(Long userId) {
       return userRepository.findById(userId).orElse(null);
    }

    public Cart getCartByUser(User user) {
        return cartRepository.findByUser(user).orElse(null);
    }

    public List<CartItem> getCartItemById(Long cartItemId, User user) {
        return getCartByUser(user).getCartItems() ;
    }

    public Order createOrder(User user, OrderPlacedRequest orderPlacedRequest) {
        return Order.builder().user(user)
                .status(OrderStatus.PENDING)
                .paymentMethod(PaymentMethod.valueOf(orderPlacedRequest.getPaymentMethod()))
                .orderDate(LocalDateTime.now())
                .totalAmount(BigDecimal.ZERO)
                .build();
    }

    private OrderMapper orderMapper ;

    @Override
    public OrderResponse orderPlaced(Long userId, OrderPlacedRequest orderPlacedRequest) {
        User user = getUser(userId);
        Cart cart = getCartByUser(user);

        if(cart.getCartItems().isEmpty()){
            throw new IllegalStateException("Cart is empty");
        }

        Order order = createOrder(user,orderPlacedRequest);
        BigDecimal totalAmount  = BigDecimal.ZERO;

        List<OrderItem> orderItems = new ArrayList<>();

        for(CartItem cartItem : cart.getCartItems()){
            Product product = cartItem.getProduct();
            if(product.getStock() < cartItem.getQuantity()){
                throw new IllegalStateException(product.getName() + " is out of stock");
            }

            product.setStock(product.getStock()-cartItem.getQuantity());
            productRepository.save(product);

            OrderItem orderItem = OrderItem.builder()
                    .order(order)
                    .product(product)
                    .quantity(cartItem.getQuantity())
                    .price(cartItem.getPrice())
                    .totalPrice(cartItem.getSubtotal())
                    .build();

            orderItems.add(orderItem);
            totalAmount = totalAmount.add(cartItem.getSubtotal());

        }
        order.setOrderItems(orderItems);
        order.setTotalAmount(totalAmount);
        Order saveOrder = orderRepository.save(order);

        return  orderMapper.orderResponse(saveOrder);
    }

    @Override
    public OrderResponse getOrderById(Long orderId) {
        return null;
    }

    @Override
    public Page<OrderResponse> getUserOrders(Long userId, Pageable pageable) {
        return null;
    }

    @Override
    public Page<OrderResponse> getOrdersByStatus(String status, Pageable pageable) {
        return null;
    }

    @Override
    public String cancelOrder(Long orderId) {
        return "";
    }
}

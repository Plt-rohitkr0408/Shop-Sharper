package com.shopsharper.auth_service.service.impl;

import com.shopsharper.auth_service.Mapper.CartMapper;
import com.shopsharper.auth_service.dto.request.AddToCartRequest;
import com.shopsharper.auth_service.dto.response.CartItemResponse;
import com.shopsharper.auth_service.dto.response.CartResponse;
import com.shopsharper.auth_service.entity.Cart;
import com.shopsharper.auth_service.entity.CartItem;
import com.shopsharper.auth_service.entity.Product;
import com.shopsharper.auth_service.entity.User;
import com.shopsharper.auth_service.repository.CartItemRepository;
import com.shopsharper.auth_service.repository.CartRepository;
import com.shopsharper.auth_service.repository.ProductRepository;
import com.shopsharper.auth_service.repository.UserRepository;
import com.shopsharper.auth_service.service.CartService;
import lombok.Setter;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    private final CartMapper cartMapper;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    public CartServiceImpl(CartMapper cartMapper, ProductRepository productRepository, UserRepository userRepository, CartRepository cartRepository, CartItemRepository cartItemRepository) {
        this.cartMapper = cartMapper;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
    }

    public User getUser(Long id){
        return userRepository.findById(id).orElseThrow(()->new UsernameNotFoundException("User not found"));
    }

    public Product getProduct(Long id){
        return productRepository.findById(id).orElseThrow(()->new UsernameNotFoundException("Product not found"));
    }

    public Cart getOrCreateCart(User user){
        return cartRepository.findByUser(user).orElseGet(
                ()->{
                    Cart cart = Cart.builder()
                            .user(user)
                            .build();

                    return cartRepository.save(cart);
                }
        );
    }

    @Override
    public CartResponse addToCart(Long userId, AddToCartRequest addToCartRequest) {
        Product product = productRepository.findById(addToCartRequest.getProductId()).orElseThrow(()-> new RuntimeException("product not found"));
        User user = getUser(userId);
        Cart cart = getOrCreateCart(user);
        Optional<CartItem> existingItem = cartItemRepository.findByCartAndProduct(cart, product);
        if(existingItem.isPresent()){
            CartItem cartItem = existingItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + addToCartRequest.getQuantity());
            cartItem.setSubtotal(cartItem.getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())));
            cartItemRepository.save(cartItem);

        }else{
        CartItem cartItem = CartItem.builder()
                .product(product)
                .price(product.getPrice())
                .quantity(addToCartRequest.getQuantity())
                .cart(cart)
                .subtotal(product.getPrice().multiply(BigDecimal.valueOf(addToCartRequest.getQuantity())))
                .build();


        CartItemResponse cartItemResponse = cartMapper.toCartItemResponse(cartItem);

        cart.getCartItems().add(cartItem);
        cartItemRepository.save(cartItem);
        }
        updateCartTotal(cart);
        cartRepository.save(cart);
        return cartMapper.toCartResponse(cart);
    }

    private void updateCartTotal(Cart cart){
        BigDecimal totalPrice = cart.getCartItems().stream()
                .map(cartItem -> cartItem.getSubtotal())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        cart.setTotalPrice(totalPrice);
    }

    @Override
    public CartResponse getCart(Long userId) {
        User user = getUser(userId);
        Cart cart = cartRepository.findByUser(user).orElseThrow(()->new UsernameNotFoundException("User not found"));
        return cartMapper.toCartResponse(cart);
    }

    @Override
    public CartResponse updateCartItem(Long userId, Long productId, Integer quantity) {
        if(quantity <= 0){
            throw new IllegalArgumentException("quantity must be greater than 0");
        }

        User user = getUser(userId);
        Product product = getProduct(productId);
        Cart cart = getOrCreateCart(user);
        CartItem cartItem = cartItemRepository.findByCartAndProduct(cart, product).orElseThrow(()->new UsernameNotFoundException("Product not found"));
        cartItem.setQuantity(quantity);
        cartItem.setSubtotal(product.getPrice().multiply(BigDecimal.valueOf(quantity)));
        cartItemRepository.save(cartItem);
        updateCartTotal(cart);
        cartRepository.save(cart);
        return cartMapper.toCartResponse(cart);
    }

    @Override
    public String  removeProduct(Long userId, Long productId) {
        Product product = getProduct(productId);
        User user = getUser(userId);
        Cart cart = cartRepository.findByUser(user).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        CartItem cartItem = cartItemRepository.findByCartAndProduct(cart, product).orElseThrow(() -> new UsernameNotFoundException("Product not found"));

        cart.getCartItems().remove(cartItem);
        updateCartTotal(cart);
        cartRepository.save(cart);
        return "Product removed successfully";
    }

    @Override
    public String clearCart(Long userId) {
        User user = getUser(userId);
        Cart cart = cartRepository.findByUser(user).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        cart.getCartItems().clear();
        cart.setTotalPrice(BigDecimal.ZERO);
        cartRepository.save(cart);
        return "Cart items removed successfully";
    }

}

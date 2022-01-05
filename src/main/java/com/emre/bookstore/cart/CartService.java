package com.emre.bookstore.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    private final CartRepository cartRepository;

    @Autowired
    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Cart getCart(Long id) {
        return this.cartRepository.findCartByCustomerId(id);
    }
//
//    public Cart postCart(Cart cart, Long customerId) {
//        this.cartRepository.saveCartByCustomerId(cart, customerId);
//        return cart;
//    }
}

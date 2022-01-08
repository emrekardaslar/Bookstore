package com.emre.bookstore.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Cart postCart(Cart cart) {
        this.cartRepository.save(cart);
        return cart;
    }

    public Cart updateCart(Cart cart) {
       Cart oldCart = this.cartRepository.findCartByCustomerId(cart.getCustomerId());
       if (oldCart == null) {
           throw new IllegalStateException("cart with id " + cart.getCustomerId() + " does not exists");
       }
       this.cartRepository.updateCart(cart.getBooks(), cart.getTotal(), cart.getCustomerId());
       return cart;
    }

    public Cart deleteCart(Long customerId) {
        Cart cartToDelete = this.cartRepository.findCartByCustomerId(customerId);
        if (cartToDelete == null) {
            throw new IllegalStateException("cart with id " + customerId + " does not exists");
        }

        this.cartRepository.deleteCartWithCustomerId(customerId);
        return cartToDelete;
    }
}

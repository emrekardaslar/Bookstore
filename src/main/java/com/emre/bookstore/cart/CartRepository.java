package com.emre.bookstore.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findCartByCustomerId(Long id);
    //void saveCartByCustomerId(Cart cart, Long customerId);
    //TODO: write query for saveCartByCustomerId
}

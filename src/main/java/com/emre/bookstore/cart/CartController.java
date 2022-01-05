package com.emre.bookstore.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/cart")
public class CartController {
    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public ResponseEntity<Cart> getCart(Long id) {
        return new ResponseEntity<Cart>(cartService.getCart(id), HttpStatus.OK);
    }

//    @PostMapping
//    public ResponseEntity<Cart> postCart(@RequestBody Cart cart, Long id) {
//        return new ResponseEntity<Cart>(cartService.postCart(cart, id), HttpStatus.OK);
//    }


}

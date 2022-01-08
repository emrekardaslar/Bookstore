package com.emre.bookstore.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "api/v1/cart")
public class CartController {
    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping(path = "{customerId}")
    public ResponseEntity<Cart> getCart(@PathVariable("customerId") Long customerId) {
        return new ResponseEntity<Cart>(cartService.getCart(customerId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Cart> postCart(@RequestBody Cart cart) {
        return new ResponseEntity<Cart>(cartService.postCart(cart), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateCart(@RequestBody Cart cart) {
        return new ResponseEntity<Cart>(cartService.updateCart(cart), HttpStatus.OK);
    }

    @DeleteMapping(path = "{customerId}")
    public ResponseEntity<?> deleteCart(@PathVariable("customerId") Long customerId) {
        try {
            return new ResponseEntity<Cart>(cartService.deleteCart(customerId), HttpStatus.OK);
        }
        catch (Exception e) {
            Map<String, Object> body = new LinkedHashMap<>();
            body.put("status", "400");
            body.put("message", "No cart to delete");
            return new ResponseEntity<Object>(body, HttpStatus.BAD_REQUEST);
        }
    }
}

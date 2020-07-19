package com.finalproject.petology.controller;

import com.finalproject.petology.dao.CartRepo;
import com.finalproject.petology.entity.Cart;
import com.finalproject.petology.entity.Product;
import com.finalproject.petology.service.CartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/carts")
@CrossOrigin(origins = "http://localhost:3000")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/{userId}")
    public Iterable<Cart> getCartByUser(@PathVariable int userId) {
        return cartService.getCartByUser(userId);
    }

    @GetMapping
    public Cart getCartByUserAndProduct(@RequestParam int userId, @RequestParam int productId) {
        Cart findCart = cartService.getCartByUserAndProduct(userId, productId);
        if (findCart == null) {
            return null;
        }
        return findCart;
    }

    @PostMapping("/user/{userId}/product/{productId}")
    public Cart addProductToUserCart(@RequestBody Cart cart, @PathVariable int userId, @PathVariable int productId) {
        return cartService.addProductToUserCart(cart, userId, productId);
    }

    @PutMapping("/user/{userId}/product/{productId}")
    public Cart updateProductToUserCart(@RequestBody Cart cart, @PathVariable int userId, @PathVariable int productId) {
        return cartService.updateProductToUserCart(cart, userId, productId);
    }

    @DeleteMapping("/user/{userId}/product/{productId}")
    public void deleteProductInCartUser(@PathVariable int userId, @PathVariable int productId) {
        cartService.deleteProductInCartUser(userId, productId);
    }

    @GetMapping("/totalPrice/{userId}")
    public int getTotalPrice(@PathVariable int userId) {
        return cartService.getTotalPrice(userId);
    }

    @DeleteMapping("/clear/{userId}")
    public void clearCart(@PathVariable int userId) {
        cartService.clearCart(userId);
    }

}
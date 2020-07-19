package com.finalproject.petology.service;

import com.finalproject.petology.entity.Cart;

public interface CartService {
    public Iterable<Cart> getCartByUser(int userId);

    public Cart getCartByUserAndProduct(int userId, int productId);

    public Cart addProductToUserCart(Cart cart, int userId, int productId);

    public Cart updateProductToUserCart(Cart cart, int userId, int productId);

    public void deleteProductInCartUser(int userId, int productId);

    public int getTotalPrice(int userId);

    public void clearCart(int userId);

}
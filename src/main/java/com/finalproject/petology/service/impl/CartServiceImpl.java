package com.finalproject.petology.service.impl;

import javax.transaction.Transactional;

import com.finalproject.petology.dao.CartRepo;
import com.finalproject.petology.dao.ProductRepo;
import com.finalproject.petology.dao.UserRepo;
import com.finalproject.petology.entity.Cart;
import com.finalproject.petology.entity.Product;
import com.finalproject.petology.entity.User;
import com.finalproject.petology.service.CartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ProductRepo productRepo;

    @Override
    public Iterable<Cart> getCartByUser(int userId) {
        return cartRepo.getCartByUser(userId);
    }

    @Override
    @Transactional
    public Cart getCartByUserAndProduct(int userId, int productId) {
        Cart findCart = cartRepo.getCartByUserAndProduct(userId, productId);
        if (findCart != null) {
            return findCart;
        }
        return null;
    }

    @Override
    @Transactional
    public Cart addProductToUserCart(Cart cart, int userId, int productId) {
        User findUser = userRepo.findById(userId).get();
        Product findProduct = productRepo.findById(productId).get();
        cart.setUser(findUser);
        cart.setProduct(findProduct);
        return cartRepo.save(cart);
    }

    @Override
    @Transactional
    public Cart updateProductToUserCart(Cart cart, int userId, int productId) {
        User findUser = userRepo.findById(userId).get();
        Product findProduct = productRepo.findById(productId).get();
        cart.setUser(findUser);
        cart.setProduct(findProduct);
        return cartRepo.save(cart);
    }

    @Override
    @Transactional
    public void deleteProductInCartUser(int userId, int productId) {
        Cart findCart = cartRepo.getCartByUserAndProduct(userId, productId);
        cartRepo.delete(findCart);
    }

    @Override
    @Transactional
    public int getTotalPrice(int userId) {
        return cartRepo.countTotalPrice(userId);
    }

    @Override
    @Transactional
    public void clearCart(int userId) {
        Iterable<Cart> findCart = cartRepo.getCartByUser(userId);
        findCart.forEach(item -> {
            // System.out.println(item.getId());
            cartRepo.deleteById(item.getId());
        });
    }

}
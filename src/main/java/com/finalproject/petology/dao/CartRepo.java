package com.finalproject.petology.dao;

import java.util.Optional;

import com.finalproject.petology.entity.Cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartRepo extends JpaRepository<Cart, Integer> {
    @Query(value = "SELECT * FROM carts WHERE user_id = :userId AND product_id = :productId", nativeQuery = true)
    public Cart getCartByUserAndProduct(@Param("userId") int userId, @Param("productId") int productId);

    @Query(value = "SELECT * FROM carts WHERE user_id = :userId", nativeQuery = true)
    public Iterable<Cart> getCartByUser(@Param("userId") int userId);

    @Query(value = "SELECT SUM(price*quantity) FROM carts JOIN products ON carts.product_id= products.id WHERE user_id= :userId", nativeQuery = true)
    public int countTotalPrice(@Param("userId") int userId);

    @Query(value = "DELETE FROM carts WHERE user_id = :userId", nativeQuery = true)
    public void clearCartByUser(@Param("userId") int userId);

}
package com.finalproject.petology.dao;

import com.finalproject.petology.entity.TransactionDetail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TransactionDetailRepo extends JpaRepository<TransactionDetail, Integer> {
    @Query(value = "SELECT * FROM transaction_details JOIN transactions ON transaction_details.transaction_id= transactions.id JOIN products ON transaction_details.product_id= products.id WHERE transactions.id = :transactionId", nativeQuery = true)
    public Iterable<TransactionDetail> getTransactionDetailByTransactionId(@Param("transactionId") int transactionId);

    @Query(value = "SELECT COUNT(product_id) FROM transaction_details WHERE product_id = :productId", nativeQuery = true)
    public int getTransactionDetailByProductId(@Param("productId") int productId);

    @Query(value = "SELECT product_id,SUM(quantity) FROM transaction_details GROUP BY product_id", nativeQuery = true)
    public Iterable<Object> getTransactionDetailByBuy();
}
package com.finalproject.petology.dao;

import com.finalproject.petology.entity.Transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TransactionRepo extends JpaRepository<Transaction, Integer> {
    @Query(value = "SELECT * FROM petologydb.transactions WHERE user_id = :userId", nativeQuery = true)
    public Iterable<Transaction> getTransactionByUser(@Param("userId") int userId);

    @Query(value = "SELECT * FROM petologydb.transactions WHERE NOT status = 'Waiting to pay'", nativeQuery = true)
    public Iterable<Transaction> getTransactionByStatusNotPay();

}
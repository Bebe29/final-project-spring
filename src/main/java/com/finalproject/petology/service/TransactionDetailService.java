package com.finalproject.petology.service;

import com.finalproject.petology.entity.TransactionDetail;

public interface TransactionDetailService {
    public TransactionDetail postTransactionDetail(TransactionDetail transactionDetail, int transactionId,
            int productId);

    public void getTransactionDetailByTransactionId(int transactionId, String detail, int userId);

    public int getTransactionDetailByProductId(int productId);

    public Iterable<TransactionDetail> getAllTransaction();

    public Iterable<Object> getTransactionDetailByBuy();
}
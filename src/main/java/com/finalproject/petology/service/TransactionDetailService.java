package com.finalproject.petology.service;

import com.finalproject.petology.entity.TransactionDetail;

public interface TransactionDetailService {
    public TransactionDetail postTransactionDetail(TransactionDetail transactionDetail, int transactionId,
            int productId);

    public Iterable<TransactionDetail> getTransactionDetailByTransactionId(int transactionId);

    public int getTransactionDetailByProductId(int productId);

    public Iterable<TransactionDetail> getAllTransaction();

    public Iterable<Object> getTransactionDetailByBuy();

    public void sendInvoce(String detail, int subTotalPrice, int userId);
}
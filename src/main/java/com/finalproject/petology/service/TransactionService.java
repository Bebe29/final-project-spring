package com.finalproject.petology.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.finalproject.petology.entity.Transaction;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface TransactionService {
    public Transaction addNewTransaction(Transaction transaction, int userId);

    public Iterable<Transaction> getTransactionByUser(int userId);

    public String uploadImagePayment(MultipartFile file, String paymentString)
            throws JsonMappingException, JsonProcessingException;

    public ResponseEntity<Object> downloadImageProduct(String fileName);

    public Transaction updateTransaction(Transaction transaction, int transactionId);

    public Iterable<Transaction> getTransactionByStatusNotPay();
}
package com.finalproject.petology.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.finalproject.petology.entity.Transaction;
import com.finalproject.petology.service.TransactionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/transactions")
@CrossOrigin(origins = "http://localhost:3000")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping("/{userId}")
    public Transaction addNewTransaction(@RequestBody Transaction transaction, @PathVariable int userId) {
        return transactionService.addNewTransaction(transaction, userId);
    }

    @GetMapping("/{userId}")
    public Iterable<Transaction> getTransactionByUser(@PathVariable int userId) {
        return transactionService.getTransactionByUser(userId);
    }

    @PostMapping
    public String uploadImagePayment(@RequestParam("file") MultipartFile file,
            @RequestParam("paymentData") String paymentString) throws JsonMappingException, JsonProcessingException {
        return transactionService.uploadImagePayment(file, paymentString);
    }

    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity<Object> downloadImageProduct(@PathVariable String fileName) {
        return transactionService.downloadImageProduct(fileName);
    }

    @PutMapping("/{transactionId}")
    public Transaction updateTransaction(@RequestBody Transaction transaction, @PathVariable int transactionId) {
        return transactionService.updateTransaction(transaction, transactionId);
    }

    @GetMapping("/admin")
    public Iterable<Transaction> getTransactionByStatusNotPay() {
        return transactionService.getTransactionByStatusNotPay();
    }
}
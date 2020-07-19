package com.finalproject.petology.controller;

import com.finalproject.petology.entity.TransactionDetail;
import com.finalproject.petology.service.TransactionDetailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactionDetails")
@CrossOrigin(origins = "http://localhost:3000")
public class TransactionDetailController {
    @Autowired
    private TransactionDetailService transactionDetailService;

    @PostMapping("/{transactionId}/product/{productId}")
    public TransactionDetail postTransactionDetail(@RequestBody TransactionDetail transactionDetail,
            @PathVariable int transactionId, @PathVariable int productId) {
        return transactionDetailService.postTransactionDetail(transactionDetail, transactionId, productId);
    }

    @GetMapping("/{transactionId}")
    public void getTransactionDetailByTransactionId(@PathVariable int transactionId, @RequestParam String detail,
            @RequestParam int userId) {
        transactionDetailService.getTransactionDetailByTransactionId(transactionId, detail, userId);
    }

    @GetMapping("/{productId}")
    public int getTransactionDetailByProductId(@PathVariable int productId) {
        return transactionDetailService.getTransactionDetailByProductId(productId);
    }

    @GetMapping
    public Iterable<TransactionDetail> getAllTransaction() {
        return transactionDetailService.getAllTransaction();
    }

    @GetMapping("/purchase")
    public Iterable<Object> getTransactionDetailByBuy() {
        return transactionDetailService.getTransactionDetailByBuy();
    }
}
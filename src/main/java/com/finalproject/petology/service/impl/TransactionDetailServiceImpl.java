package com.finalproject.petology.service.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;

import javax.transaction.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.finalproject.petology.dao.PackageRepo;
import com.finalproject.petology.dao.ProductRepo;
import com.finalproject.petology.dao.TransactionDetailRepo;
import com.finalproject.petology.dao.TransactionRepo;
import com.finalproject.petology.dao.UserRepo;
import com.finalproject.petology.entity.Package;
import com.finalproject.petology.entity.Product;
import com.finalproject.petology.entity.Transaction;
import com.finalproject.petology.entity.TransactionDetail;
import com.finalproject.petology.entity.User;
import com.finalproject.petology.service.TransactionDetailService;
import com.finalproject.petology.util.EmailUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionDetailServiceImpl implements TransactionDetailService {
    @Autowired
    private TransactionDetailRepo transactionDetailRepo;

    @Autowired
    private TransactionRepo transactionRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private PackageRepo packageRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private EmailUtil emailUtil;

    @Override
    @Transactional
    public TransactionDetail postTransactionDetail(TransactionDetail transactionDetail, int transactionId,
            int productId) {
        Transaction findTransaction = transactionRepo.findById(transactionId).get();
        transactionDetail.setTransaction(findTransaction);
        Product findProduct = productRepo.findById(productId).get();
        int stock = findProduct.getStock();
        int quantity = transactionDetail.getQuantity();
        if (findProduct.getType().equalsIgnoreCase("Package")) {
            Package findPackage = packageRepo.findByIdInProduct(productId).get();
            findPackage.getProducts().forEach(product -> {
                int stockProduct = product.getStock();
                product.setStock(stockProduct - quantity);
            });
            findProduct.setStock(stock - quantity);
            transactionDetail.setProduct(findProduct);
        } else {
            findProduct.setStock(stock - quantity);
            transactionDetail.setProduct(findProduct);
        }
        return transactionDetailRepo.save(transactionDetail);
    }

    @Override
    @Transactional
    public Iterable<TransactionDetail> getTransactionDetailByTransactionId(int transactionId) {
        return transactionDetailRepo.getTransactionDetailByTransactionId(transactionId);
        // String message = "<h1>Thankyou for your puschase<h1>\n";
        // message += "<p>" + detail + "<p>";
        // User user = userRepo.findById(userId).get();
        // emailUtil.sendEmail(user.getEmail(), "Invoice Purchase", message);
    }

    @Override
    @Transactional
    public void sendInvoce(String detail, int subTotalPrice, int userId) {
        String message = "<h1>Thankyou for your puschase<h1>\n";
        message += detail;
        message += "<h3>Sub Total Price :" + subTotalPrice + "<h3>";
        User user = userRepo.findById(userId).get();
        emailUtil.sendEmail(user.getEmail(), "Invoice Purchase", message);
    }

    @Override
    @Transactional
    public int getTransactionDetailByProductId(int productId) {
        return transactionDetailRepo.getTransactionDetailByProductId(productId);
    }

    @Override
    @Transactional
    public Iterable<TransactionDetail> getAllTransaction() {
        return transactionDetailRepo.findAll();
    }

    @Override
    @Transactional
    public Iterable<Object> getTransactionDetailByBuy() {
        return transactionDetailRepo.getTransactionDetailByBuy();
    }
}
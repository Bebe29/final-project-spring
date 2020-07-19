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
import com.finalproject.petology.dao.TransactionRepo;
import com.finalproject.petology.dao.UserRepo;
import com.finalproject.petology.entity.Transaction;
import com.finalproject.petology.entity.User;
import com.finalproject.petology.service.TransactionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepo transactionRepo;

    @Autowired
    private UserRepo userRepo;

    private String uploadPaymentPath = System.getProperty("user.dir")
            + "\\petology\\src\\main\\resources\\static\\images\\payments\\";

    @Override
    @Transactional
    public Transaction addNewTransaction(Transaction transaction, int userId) {
        User findUser = userRepo.findById(userId).get();
        transaction.setUser(findUser);
        return transactionRepo.save(transaction);
    }

    @Override
    @Transactional
    public Iterable<Transaction> getTransactionByUser(int userId) {
        return transactionRepo.getTransactionByUser(userId);
    }

    @Override
    @Transactional
    public String uploadImagePayment(MultipartFile file, String paymentString)
            throws JsonMappingException, JsonProcessingException {
        Date date = new Date();
        Transaction transaction = new ObjectMapper().readValue(paymentString, Transaction.class);
        String fileExtension = file.getContentType().split("/")[1];
        String newFileName = "Payment-UserId-" + transaction.getUser().getId() + "-" + date.getTime() + "."
                + fileExtension;
        String fileName = StringUtils.cleanPath(newFileName);

        Path path = Paths.get(StringUtils.cleanPath(uploadPaymentPath) + fileName);

        try {
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/transactions/download/")
                .path(fileName).toUriString();
        return fileDownloadUri;
    }

    @Override
    @Transactional
    public ResponseEntity<Object> downloadImageProduct(String fileName) {
        Path path = Paths.get(uploadPaymentPath + fileName);
        Resource resource = null;
        try {
            resource = new UrlResource(path.toUri());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @Override
    @Transactional
    public Transaction updateTransaction(Transaction transaction, int transactionId) {
        Transaction findTransaction = transactionRepo.findById(transactionId).get();
        if (findTransaction == null)
            throw new RuntimeException("Transaction nor found");
        return transactionRepo.save(transaction);
    }

    @Override
    @Transactional
    public Iterable<Transaction> getTransactionByStatusNotPay() {
        return transactionRepo.getTransactionByStatusNotPay();
    }
}
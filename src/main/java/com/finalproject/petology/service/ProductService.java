package com.finalproject.petology.service;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.finalproject.petology.entity.Product;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface ProductService {
    public Iterable<Product> getAllProduct();

    public Product addNewProducts(Product product, int categoryId);

    public String uploadImageProduct(MultipartFile file, String producString)
            throws JsonMappingException, JsonProcessingException;

    public ResponseEntity<Object> downloadImageProduct(String fileName);

    public Product editProduct(Product product);

    public void deleteProduct(int productId);

    public Optional<Product> getProductById(int productId);

    public List<Product> findProductTypeProduct();

    public List<Product> findProductTypePackage();

    public Product addProductToPackage(int productId, int packageId);

    public List<Product> getNewestItem();

    // public Iterable<Product> getPaginationDataProduct(int pageSize, int page);

    // public Iterable<Product> sortProductByNameAsc(int pagesize, int page);

    // public Iterable<Product> sortProductByNameDesc(int pagesize, int page);

    // public Iterable<Product> sortProductByPriceAsc(int pagesize, int page);

    // public Iterable<Product> sortProductByPriceDesc(int pagesize, int page);

    // public Iterable<Product> findProductByNameSortByNameAsc(String searchProduct,
    // int pageSize, int page);

    // public Iterable<Product> findProductByNameSortByNameDesc(String
    // searchProduct, int pageSize, int page);

    // public Iterable<Product> findProductByNameSortByPriceAsc(String
    // searchProduct, int pageSize, int page);

    // public Iterable<Product> findProductByNameSortByPriceDesc(String
    // searchProduct, int pageSize, int page);

}
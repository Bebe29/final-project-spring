package com.finalproject.petology.dao;

import java.util.List;

import com.finalproject.petology.entity.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepo extends JpaRepository<Product, Integer> {
        @Query(value = "SELECT * FROM products WHERE type = 'Product'", nativeQuery = true)
        public List<Product> findProductTypeProduct();

        @Query(value = "SELECT * FROM products WHERE type = 'Package'", nativeQuery = true)
        public List<Product> findProductTypePackage();

        @Query(value = "SELECT * FROM products ORDER BY id DESC LIMIT 4", nativeQuery = true)
        public List<Product> getNewestItem();

        @Query(value = "SELECT * FROM products LIMIT :pageSize OFFSET :offset", nativeQuery = true)
        public Iterable<Product> getPaginationDataProduct(@Param("pageSize") int pageSize, @Param("offset") int offset);

        @Query(value = "SELECT * FROM products WHERE product_name LIKE %:productName% LIMIT :pageSize OFFSET:offset", nativeQuery = true)
        public Iterable<Product> findProductByName(@Param("productName") String productName,
                        @Param("pageSize") int pageSize, @Param("offset") int offset);

}
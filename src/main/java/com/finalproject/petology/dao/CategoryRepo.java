package com.finalproject.petology.dao;

import com.finalproject.petology.entity.Category;
import com.finalproject.petology.entity.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepo extends JpaRepository<Category, Integer> {
    @Query(value = "SELECT * FROM products WHERE category_id = :category_id LIMIT :pageSize OFFSET :offset", nativeQuery = true)
    public Iterable<Product> findProductByCategory(@Param("category_id") String category_id,
            @Param("pageSize") int pageSize, @Param("offset") int offset);
}
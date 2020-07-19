package com.finalproject.petology.controller;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.finalproject.petology.dao.ProductRepo;
import com.finalproject.petology.entity.Product;
import com.finalproject.petology.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public Iterable<Product> getAllProduct() {
        return productService.getAllProduct();
    }

    @GetMapping("/typeProduct")
    public List<Product> findProductTypeProduct() {
        return productService.findProductTypeProduct();
    }

    @GetMapping("/typePackage")
    public List<Product> findProductTypePackage() {
        return productService.findProductTypePackage();
    }

    @PostMapping("/categories/{categoryId}")
    public Product addNewProducts(@RequestBody Product product, @PathVariable int categoryId) {
        return productService.addNewProducts(product, categoryId);
    }

    @PostMapping
    public String uploadImageProduct(@RequestParam("file") MultipartFile file,
            @RequestParam("productData") String productString) throws JsonMappingException, JsonProcessingException {
        return productService.uploadImageProduct(file, productString);
    }

    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity<Object> downloadImageProduct(@PathVariable String fileName) {
        return productService.downloadImageProduct(fileName);
    }

    @PutMapping
    public Product editProduct(@RequestBody Product product) {
        return productService.editProduct(product);
    }

    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable int productId) {
        productService.deleteProduct(productId);
    }

    @GetMapping("/{productId}")
    public Optional<Product> getProductById(@PathVariable int productId) {
        return productService.getProductById(productId);
    }

    @PostMapping("/{productId}/package/{packageId}")
    public Product addProductToPackage(@PathVariable int productId, @PathVariable int packageId) {
        return productService.addProductToPackage(productId, packageId);
    }

    @GetMapping("/newest")
    public List<Product> getNewestItem() {
        return productService.getNewestItem();
    }

    // @GetMapping
    // public Iterable<Product> getPaginationDataProduct(@RequestParam int pageSize,
    // @RequestParam int page) {
    // return productService.getPaginationDataProduct(pageSize, page);
    // }

    // @GetMapping("/nameAsc")
    // public Iterable<Product> sortProductByNameAsc(@RequestParam int pageSize,
    // @RequestParam int page) {
    // return productService.sortProductByNameAsc(pageSize, page);
    // }

    // @GetMapping("/nameDesc")
    // public Iterable<Product> sortProductByNameDesc(@RequestParam int pageSize,
    // @RequestParam int page) {
    // return productService.sortProductByNameDesc(pageSize, page);
    // }

    // @GetMapping("/priceAsc")
    // public Iterable<Product> sortProductByPriceAsc(@RequestParam int pageSize,
    // @RequestParam int page) {
    // return productService.sortProductByPriceAsc(pageSize, page);
    // }

    // @GetMapping("/pricaDesc")
    // public Iterable<Product> sortProductByPriceDesc(@RequestParam int pageSize,
    // @RequestParam int page) {
    // return productService.sortProductByPriceDesc(pageSize, page);
    // }

    // @GetMapping("/{searchProduct}/nameAsc")
    // public Iterable<Product> findProductByNameSortByNameAsc(@PathVariable String
    // searchProduct,
    // @RequestParam int pageSize, @RequestParam int page) {
    // return productService.findProductByNameSortByNameAsc(searchProduct, pageSize,
    // page);
    // }

    // @GetMapping("/{searchProduct}/nameDesc")
    // public Iterable<Product> findProductByNameSortByNameDesc(@PathVariable String
    // searchProduct,
    // @RequestParam int pageSize, @RequestParam int page) {
    // return productService.findProductByNameSortByNameAsc(searchProduct, pageSize,
    // page);
    // }

    // @GetMapping("/{searchProduct}/priceAsc")
    // public Iterable<Product> findProductByNameSortByPriceAsc(@PathVariable String
    // searchProduct,
    // @RequestParam int pageSize, @RequestParam int page) {
    // return productService.findProductByNameSortByNameAsc(searchProduct, pageSize,
    // page);
    // }

    // @GetMapping("/{searchProduct}/priceDesc")
    // public Iterable<Product> findProductByNameSortByPriceDesc(@PathVariable
    // String searchProduct,
    // @RequestParam int pageSize, @RequestParam int page) {
    // return productService.findProductByNameSortByNameAsc(searchProduct, pageSize,
    // page);
    // }

}
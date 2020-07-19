package com.finalproject.petology.controller;

import java.util.List;

import com.finalproject.petology.dao.PackageRepo;
import com.finalproject.petology.entity.Package;
import com.finalproject.petology.entity.Product;
import com.finalproject.petology.service.PackageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/packages")
@CrossOrigin(origins = "http://localhost:3000")
public class PackageController {
    @Autowired
    private PackageService packageService;

    @GetMapping("/{packageId}/products")
    public List<Product> getProductsOfPackage(@PathVariable int packageId) {
        return packageService.getProductsOfPackage(packageId);
    }

    @PostMapping
    public void addPackage(@RequestBody Package package1) {
        packageService.addPackage(package1);
    }

    @DeleteMapping("/{productId}")
    public void deletePackage(@PathVariable int productId) {
        packageService.deletePackage(productId);
    }

}
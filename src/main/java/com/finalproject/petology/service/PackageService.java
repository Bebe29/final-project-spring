package com.finalproject.petology.service;

import java.util.List;

import com.finalproject.petology.entity.Package;
import com.finalproject.petology.entity.Product;

public interface PackageService {
    public void addPackage(Package package1);

    public void deletePackage(int productId);

    public List<Product> getProductsOfPackage(int packageId);
}
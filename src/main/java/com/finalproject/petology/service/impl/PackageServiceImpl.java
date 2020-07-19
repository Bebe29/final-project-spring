package com.finalproject.petology.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.transaction.Transactional;

import com.finalproject.petology.dao.PackageRepo;
import com.finalproject.petology.dao.ProductRepo;
import com.finalproject.petology.entity.Package;
import com.finalproject.petology.entity.Product;
import com.finalproject.petology.service.PackageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PackageServiceImpl implements PackageService {
    @Autowired
    private PackageRepo packageRepo;

    @Autowired
    private ProductRepo productRepo;

    @Override
    @Transactional
    public void addPackage(Package package1) {
        Optional<Package> findPackage = packageRepo.findByIdInProduct(package1.getIdInProduct());
        if (findPackage.equals(Optional.empty())) {
            package1.setId(0);
            packageRepo.save(package1);
        }
    }

    @Override
    @Transactional
    public void deletePackage(int productId) {
        Package findPackage = packageRepo.findByIdInProduct(productId).get();
        if (findPackage != null) {
            findPackage.getProducts().forEach(product -> {
                List<Package> productPackages = product.getPackages();
                productPackages.remove(findPackage);
                productRepo.save(product);
            });
            packageRepo.deleteById(findPackage.getId());
        }
    }

    @Override
    @Transactional
    public List<Product> getProductsOfPackage(int packageId) {
        Package findPackage = packageRepo.findByIdInProduct(packageId).get();
        return findPackage.getProducts();
    }
}
package com.finalproject.petology.dao;

import java.util.Optional;

import com.finalproject.petology.entity.Package;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PackageRepo extends JpaRepository<Package, Integer> {
    public Optional<Package> findByIdInProduct(int idInProduct);
}
package com.example.springassignment.repository;

import com.example.springassignment.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface SupplierRepository extends JpaRepository<Supplier,Integer> {
    Optional<Supplier> findById(Integer id);
    Optional<Supplier> findByName(String name);
}

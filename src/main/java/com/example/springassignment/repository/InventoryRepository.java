package com.example.springassignment.repository;

import com.example.springassignment.model.Inventory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Integer> {
    @Query("select i from Inventory i where i.supplier in (select s.id from  Supplier s where s.name=?1) and i.product in (select p.id from Product p where p.name like %?2%) and i.expire > ?3")
    public Page<Inventory> findAll(String name, String prodName, Date date, PageRequest pageRequest);

    @Query("select i from Inventory i where i.supplier in (select s.id from  Supplier s where s.name=?1) and i.product in (select p.id from Product p where p.name like %?2%)")
    public Page<Inventory> findAllRecords(String name, String product, PageRequest pageRequest);
}

package com.example.springassignment.repository;

import com.example.springassignment.model.InventoryData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface InventoryRepository extends JpaRepository<InventoryData,Long> {
    @Query("SELECT c FROM InventoryData c WHERE (c.supplier like %?1% and c.name like %?2%)")
    public Page<InventoryData> findAllRecords(String name, String product, PageRequest pageRequest);
    @Query("SELECT c FROM InventoryData c WHERE (c.supplier like %?1% and c.name like %?2% and CURRENT_TIMESTAMP <= c.exp)")
    public Page<InventoryData> findAllByName(String name, String product, PageRequest pageRequest);
}

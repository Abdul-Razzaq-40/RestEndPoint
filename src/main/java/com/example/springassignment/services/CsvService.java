package com.example.springassignment.services;

import com.example.springassignment.model.Inventory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface CsvService {
    public Page<Inventory> findAllByName(String id, String product, boolean exp, PageRequest pageRequest);
}

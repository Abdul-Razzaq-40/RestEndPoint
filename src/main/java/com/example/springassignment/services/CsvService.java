package com.example.springassignment.services;

import com.example.springassignment.model.InventoryData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface CsvService {
    public Page<InventoryData> findAllByName(String id, String product, boolean exp, PageRequest pageRequest);
}

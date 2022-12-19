package com.example.springassignment.services;

import com.example.springassignment.model.Inventory;
import com.example.springassignment.repository.InventoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class CsvServiceImpl implements CsvService{
    private final InventoryRepository inventoryRepository;
    public CsvServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public Page<Inventory> findAllByName(String id, String product, boolean exp , PageRequest pageRequest) {
        System.out.println(" +N min inplementation "+id);
        Date date;
        if(exp){
            date=new Date();
            System.out.println("True case");
            return inventoryRepository.findAll(id, product, date, pageRequest);
        }
        else{
            System.out.println("FAlse case");
          return inventoryRepository.findAllRecords(id,product, pageRequest);
        }
    }
}

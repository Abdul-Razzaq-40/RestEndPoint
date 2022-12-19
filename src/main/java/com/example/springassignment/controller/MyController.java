package com.example.springassignment.controller;

import com.example.springassignment.model.Inventory;
import com.example.springassignment.model.Product;
import com.example.springassignment.model.Supplier;
import com.example.springassignment.repository.InventoryRepository;
import com.example.springassignment.repository.ProductRepository;
import com.example.springassignment.repository.SupplierRepository;
import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/csv")
public class MyController {
    @Autowired
    InventoryRepository inventoryRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    SupplierRepository supplierRepository;

    public MyController(InventoryRepository inventoryRepository, ProductRepository productRepository, SupplierRepository supplierRepository) {
        this.inventoryRepository = inventoryRepository;
        this.productRepository = productRepository;
        this.supplierRepository = supplierRepository;
    }

    static Map<String, Supplier> supMap=new HashMap<>();
    static Map<String, Product> prodMap=new HashMap<>();
    @PostMapping("/upload")

    public String UploadData(@RequestParam("file") MultipartFile file) throws Exception{
        List<Inventory> inventoryList= new ArrayList<>();
        List<Supplier> supplierList=new ArrayList<>();
        List<Product> productList=new ArrayList<>();
        InputStream inputStream=file.getInputStream();
        CsvParserSettings settings=new CsvParserSettings();
        settings.setHeaderExtractionEnabled(true);
        CsvParser parser=new CsvParser(settings);
        List<Record> Data=parser.parseAllRecords(inputStream);
        Inventory inventory;
        Product product;
        Supplier supplier;
//        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        for (Record record : Data){
            if(prodMap.containsKey(record.getString("code"))){
                product=prodMap.get(record.getString("code"));
            }
            else{
                product=new Product();
                product.setCode(record.getString("code"));
                product.setName(record.getString("name"));
                product.setCompany(record.getString("company"));
                prodMap.put(product.getCode(),product);
            }
            if(supMap.containsKey(record.getString("supplier"))){
                supplier=supMap.get(record.getString("supplier"));
            }
            else {
                supplier= new Supplier();
                supplier.setName(record.getString("supplier"));
                supMap.put(supplier.getName(),supplier);
            }
            inventory=new Inventory();
            inventory.setStock(Integer.parseInt(record.getString("stock")));
            inventory.setDeal(Integer.parseInt(record.getString("deal")));
            inventory.setFree(Integer.parseInt(record.getString("free")));
//            try {
//                inventory.setExpire(sdf.parse(record.getString("exp")));
//            } catch (ParseException e) {
//                try {
//                    inventory.setExpire(sdf.parse("01/01/1900"));
//                } catch (ParseException ex) {
//                    throw new RuntimeException(ex);
//                }
//            }
            try {
                inventory.setExpire(simpleDateFormat.parse(record.getString("exp")));
            }
            catch (Exception e){
                inventory.setExpire(null);
            }
            inventory.setBatch(record.getString("batch"));
            inventory.setMrp(Float.parseFloat(record.getString("mrp")));
            inventory.setRate(Float.parseFloat(record.getString("rate")));
            inventory.setProduct(product);
            supplier.addInventoryItem(inventory);
            inventoryList.add(inventory);
            productList.add(product);
            supplierList.add(supplier);
        }
        productRepository.saveAll(productList);
        supplierRepository.saveAll(supplierList);
        inventoryRepository.saveAll(inventoryList);
        return "successfully uploaded";
    }
}

package com.example.springassignment.controller;

import com.example.springassignment.model.Inventory;
import com.example.springassignment.services.CsvService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/csv")
public class SearchController {
    private final CsvService filterService;
    public SearchController(CsvService filterService) {
        this.filterService = filterService;
    }

    @RequestMapping(value = "/service/{id}",method = RequestMethod.GET)
    public Page<Inventory> findById(@PathVariable("id")String id, @RequestParam(name = "product", defaultValue = "") String prodName,
                                    @RequestParam(name= "notExp",defaultValue = "false") boolean notExp, @RequestParam(name="page",defaultValue = "0")int page,
                                    @RequestParam(name="size",defaultValue = "10")int size){
        System.out.println("EXECUTE"+id);
        return filterService.findAllByName(id, prodName, notExp, PageRequest.of(page,size));
    }
}

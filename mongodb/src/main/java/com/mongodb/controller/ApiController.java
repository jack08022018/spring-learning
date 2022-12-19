package com.mongodb.controller;


import com.mongodb.repository.GroceryRepository;
import com.mongodb.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class ApiController {
    @Autowired
    private ApiService apiService;

    @Autowired
    private GroceryRepository groceryRepository;

    @GetMapping(value = "/getAllGrocery")
    public <T> T getAllGrocery() {
        return (T) groceryRepository.findAll();
    }

    @GetMapping(value = "/getGroceryByCategory")
    public <T> T getGroceryByCategory() {
        return (T) groceryRepository.findByCategory("snacks");
    }

}

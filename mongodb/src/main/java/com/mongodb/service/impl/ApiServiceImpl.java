package com.mongodb.service.impl;

import com.mongodb.repository.GroceryRepository;
import com.mongodb.service.ApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ApiServiceImpl implements ApiService {

    @Autowired
    private GroceryRepository groceryRepository;

    @Override
    public <T> T getAllGrocery() {
        return (T) groceryRepository.findAll();
    }
}

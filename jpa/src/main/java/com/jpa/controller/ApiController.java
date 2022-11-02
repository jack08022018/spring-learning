package com.jpa.controller;


import com.jpa.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class ApiController {
    @Autowired
    private ApiService apiService;

    @GetMapping(value = "/getRentalMovies")
    public <T> List<T> getRentalMovies(@RequestParam("title") String title) {
        return apiService.getRentalMovies(title);
    }

    @GetMapping(value = "/delete")
    public void delete() {
        apiService.testJpaSave();
    }

    @GetMapping(value = "/handleTransactional")
    public void testSave() {
        apiService.handleTransactional();
    }

    @GetMapping(value = "/handleLargeData")
    public <T> T handleLargeData() {
        return apiService.handleLargeData();
    }

}

package com.jpa.controller;


import com.jpa.entity.EmployeeEntity;
import com.jpa.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @GetMapping(value = "/testJpaSave")
    public <T> T testJpaSave() {
        return apiService.testJpaSave();
    }

    @GetMapping(value = "/handleTransactional")
    public void testSave() {
        apiService.handleTransactional();
    }

    @GetMapping(value = "/handleLargeData")
    public <T> T handleLargeData() {
        return apiService.handleLargeData();
    }

    @GetMapping(value = "/getEmployeePaging")
    public Page<EmployeeEntity> getEmployeeList(@RequestBody EmployeeEntity dto) {
        return apiService.getEmployeeList(dto);
    }

    @GetMapping(value = "/importLargeExcel")
    public void importLargeExcel(@RequestParam("file") MultipartFile file) throws Exception {
        apiService.importExcel(file);
    }

}

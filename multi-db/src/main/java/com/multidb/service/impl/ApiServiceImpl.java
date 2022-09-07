package com.multidb.service.impl;

import com.multidb.repository.employee.SalariesRepository;
import com.multidb.repository.employee.dto.EmployeeInfo;
import com.multidb.repository.sakila.RentalRepository;
import com.multidb.repository.sakila.dto.MovieRentalInfo;
import com.multidb.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.List;

@Service
public class ApiServiceImpl implements ApiService {
    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private SalariesRepository salariesRepository;

    @Override
    public List<MovieRentalInfo> getRentalMovies(ModelMap params) {
        return rentalRepository.getRentalMovies((String) params.get("title"));
    }

    @Override
    public List<EmployeeInfo> getEmployeeSalary(Integer amount) {
        return salariesRepository.getEmployeeSalary(amount);
    }
}

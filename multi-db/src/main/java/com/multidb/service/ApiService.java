package com.multidb.service;

import com.multidb.repository.employee.dto.EmployeeInfo;
import com.multidb.repository.sakila.dto.MovieRentalInfo;
import org.springframework.ui.ModelMap;

import java.util.List;

public interface ApiService {
    List<MovieRentalInfo> getRentalMovies(ModelMap params);
    List<EmployeeInfo> getEmployeeSalary(Integer amount);
}

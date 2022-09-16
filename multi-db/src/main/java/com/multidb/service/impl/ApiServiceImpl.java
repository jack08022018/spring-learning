package com.multidb.service.impl;

import com.multidb.enums.Gender;
import com.multidb.repository.employee.EmployeeRepository;
import com.multidb.repository.employee.SalariesRepository;
import com.multidb.repository.employee.dto.EmployeeInfo;
import com.multidb.repository.employee.entity.EmployeeEntity;
import com.multidb.repository.sakila.RentalRepository;
import com.multidb.repository.sakila.dto.MovieRentalInfo;
import com.multidb.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.Optional;

@Service
public class ApiServiceImpl implements ApiService {
    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private SalariesRepository salariesRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<MovieRentalInfo> getRentalMovies(ModelMap params) {
        return rentalRepository.getRentalMovies((String) params.get("title"));
    }

    @Override
    public List<EmployeeInfo> getEmployeeSalary(Integer amount) {
        return salariesRepository.getEmployeeSalary(amount);
    }

    @Override
    @Transactional(transactionManager = "employeeTransactionManager")
    public void saveEmployee() {
        EmployeeEntity entity = employeeRepository.findById(253406).get();
        entity.setGender(Gender.M);
        employeeRepository.save(entity);
        int a = 1/0;
    }

    @Override
    @Transactional
    public void saveSalary() {

    }

    @Override
    @Transactional
    public void testSave() {
        saveEmployee();
        saveSalary();
    }
}

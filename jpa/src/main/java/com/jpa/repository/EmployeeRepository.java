package com.jpa.repository;

import com.jpa.entity.EmployeeEntity;
import com.jpa.entity.SalariesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {
    EmployeeEntity findByEmpNo(Integer empNo);
}

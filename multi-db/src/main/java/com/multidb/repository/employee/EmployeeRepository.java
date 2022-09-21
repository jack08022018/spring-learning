package com.multidb.repository.employee;

import com.multidb.repository.employee.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {
    @Query(nativeQuery = true, value = """
        SELECT *
        FROM employees A 
        WHERE A.emp_no in (10001,10002)
    """)
    List<EmployeeEntity> getEmployeeTest();
}
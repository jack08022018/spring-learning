package com.multidb.repository.employee;

import com.multidb.repository.employee.dto.EmployeeInfo;
import com.multidb.repository.employee.entity.SalariesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalariesRepository extends JpaRepository<SalariesEntity, Integer> {
    @Query(nativeQuery = true, value = """
        SELECT A.emp_no, A.salary, A.from_date, CONCAT(B.first_name, ' ', B.last_name) AS full_name\s
        FROM salaries A\s
           INNER JOIN employees B ON B.emp_no = A.emp_no\s
        WHERE A.salary < :amount\s
        ORDER BY A.salary
    """)
    List<EmployeeInfo> getEmployeeSalary(@Param("amount") Integer amount);
}
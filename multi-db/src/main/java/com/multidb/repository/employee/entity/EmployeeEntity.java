package com.multidb.repository.employee.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.multidb.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "employees")
public class EmployeeEntity {
//    `employees` (
//            `emp_no` INT(10) NOT NULL,
//	`birth_date` DATE NOT NULL,
//            `first_name` VARCHAR(14) NOT NULL COLLATE 'utf8mb4_0900_ai_ci',
//            `last_name` VARCHAR(16) NOT NULL COLLATE 'utf8mb4_0900_ai_ci',
//            `gender` ENUM('M','F') NOT NULL COLLATE 'utf8mb4_0900_ai_ci',
//            `hire_date` DATE NOT NULL,
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "emp_no")
    private Integer empNo;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "first_name")
    private String first_name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "hire_date")
    private LocalDate hireDate;

    @Transient
    @JsonProperty
    private Integer pageSize;

    @Transient
    @JsonProperty
    private Integer currentPage;
}

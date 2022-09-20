package com.springredis.repository.entity

import com.fasterxml.jackson.annotation.JsonInclude
import com.springredis.enums.Gender
import java.time.LocalDate
import javax.persistence.*

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "employees")
class EmployeeEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "emp_no")
    open var empNo: Int? = null,

    @Column(name = "birth_date")
    open var birthDate: LocalDate? = null,

    @Column(name = "first_name")
    open var first_name: String? = null,

    @Column(name = "last_name")
    open var lastName: String? = null,

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    open var gender: Gender? = null,

    @Column(name = "hire_date")
    open var hireDate: LocalDate? = null
)
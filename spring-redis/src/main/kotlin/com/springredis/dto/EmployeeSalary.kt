package com.springredis.dto

import java.io.Serializable

data class EmployeeSalary (
    var emp_no: String?= null,
    var salary: Int?= null,
    var full_name: String?= null,
    var gender: String?= null
): Serializable
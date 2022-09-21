package com.springredis.repository

import com.springredis.repository.entity.EmployeeEntity
import com.springredis.repository.entity.SalariesEntity

interface CacheDao {
    fun getSalary(id: Int): List<SalariesEntity>
    fun getEmployee(id: Int): List<EmployeeEntity>
}
package com.springredis.repository

import com.springredis.repository.entity.EmployeeEntity
import com.springredis.repository.entity.SalariesEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class CacheDaoImpl: CacheDao {
    @Autowired
    lateinit var salariesRepository: SalariesRepository

    @Autowired
    lateinit var employeeRepository: EmployeeRepository

    @Cacheable("salary")
    override fun getSalary(id: Int): List<SalariesEntity> {
        return salariesRepository.findAll()
    }

    @Cacheable("employee")
    override fun getEmployee(id: Int): List<EmployeeEntity> {
        return employeeRepository.findAll()
    }
}
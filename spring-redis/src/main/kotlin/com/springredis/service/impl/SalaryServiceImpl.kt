package com.springredis.service.impl

import com.fasterxml.jackson.databind.ObjectMapper
import com.springredis.dto.EmployeeSalary
import com.springredis.repository.CacheDao
import com.springredis.repository.EmployeeRepository
import com.springredis.repository.SalariesRepository
import com.springredis.repository.entity.EmployeeEntity
import com.springredis.repository.entity.SalariesEntity
import com.springredis.service.SalaryService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.math.BigInteger
import java.time.LocalDateTime


@Service
class SalaryServiceImpl : SalaryService {
    @Autowired
    @Qualifier("customObjectMapper")
    lateinit var mapper: ObjectMapper

    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @Autowired
    lateinit var salariesRepository: SalariesRepository

    @Autowired
    lateinit var employeeRepository: EmployeeRepository

    @Autowired
    lateinit var cacheDao: CacheDao

    @Cacheable("employeeWithSalary")
    override fun getEmployeeWithSalary(salary: Int, gender: String): List<*> {
//        return salariesRepository.getEmployeeWithSalary(salary, gender)
        val dataProjection = salariesRepository.getEmployeeWithSalary(salary)
        return dataProjection.stream()
            .map{ s -> EmployeeSalary(s.emp_no, s.salary, s.full_name, s.gender) }
            .toList()
    }

    override fun handleData(): List<*> {
//        var salaryData = salariesRepository.findAll()
        var salaryData = salariesRepository.getSalaryTest()
//        println(salaryData.size)

        var employeeData = employeeRepository.findAll()

        var result = employeeData.take(2)

        val start = System.currentTimeMillis()
        println("start: $start ********************************")
        for (s in result) {
            val list = salaryData
                .filter { it.empNo == s?.empNo }
                .toList()
            println(mapper.writeValueAsString(list))
//            val total = salaryData
//                .filter { it.empNo == s?.empNo && it.salary != null }
//                .sumOf { it.salary!!}
            s.salary = list.sumOf { it.salary!!}
        }

        val end = System.currentTimeMillis()
        println("Total time: ${end - start}")
        println("end: $end ********************************")
        return result
//        val listA = listOf<SalariesEntity>(
//            SalariesEntity(empNo = 1001, salary = 1111),
//            SalariesEntity(empNo = 1001, salary = 2222),
//            SalariesEntity(empNo = 1002, salary = 3333),
//        )
//        val listB = listOf<EmployeeEntity>(
//            EmployeeEntity(empNo = 1001),
//        )
//        for (s in listB) {
//            val list = listA
//                .filter { it.empNo == s?.empNo && it.salary != null }
//                .toList()
//            println(mapper.writeValueAsString(list))
//        }
//        return listOf("A")
    }

}
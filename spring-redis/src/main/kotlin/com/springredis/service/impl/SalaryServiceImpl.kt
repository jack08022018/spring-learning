package com.springredis.service.impl

import com.fasterxml.jackson.databind.ObjectMapper
import com.springredis.dto.EmployeeSalary
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

    @Cacheable("employeeWithSalary")
    override fun getEmployeeWithSalary(salary: Int, gender: String): List<*> {
//        return salariesRepository.getEmployeeWithSalary(salary, gender)
        val dataProjection = salariesRepository.getEmployeeWithSalary(salary)
        return dataProjection.stream()
            .map{ s -> EmployeeSalary(s.emp_no, s.salary, s.full_name, s.gender) }
            .toList()
    }

    override fun handleData(): List<*> {
        val salaryList = salariesRepository.findAll()

        val employeeData = employeeRepository.findAll()
        val result = employeeData.take(10).toMutableList()
        result.add(EmployeeEntity(empNo = 9911))

        val start = System.currentTimeMillis()
        println("start: $start ********************************")

        val salaryMap: Map<Int, List<SalariesEntity>> = salaryList
            .groupBy({ it.empNo }, { it })

//        var salaryTree: TreeMap<Int, List<SalariesEntity>> = TreeMap(salaryMap)

        for (emp in result) {
//            emp.salary = salaryList
//                .filter { it.empNo == emp.empNo }
//                ?.sumOf { it.salary!!}
            emp.salary = salaryMap[emp.empNo]
                ?.sumOf { it.salary!!}
        }

        val end = System.currentTimeMillis()
        println("Total time: ${end - start}")
        println("end: $end ********************************")
        return result
    }

}
package com.mysql.service.impl

import com.fasterxml.jackson.databind.ObjectMapper
import com.mysql.enums.Gender
import com.mysql.repository.EmployeeRepository
import com.mysql.repository.SalariesRepository
import com.mysql.service.ApiService
import com.mysql.service.EmployeeService
import com.mysql.service.SalaryService
import com.mysql.service.TitleService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import java.lang.RuntimeException
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@Service
class ApiServiceImpl : ApiService {
    @Autowired
    @Qualifier("customObjectMapper")
    lateinit var mapper: ObjectMapper

    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @Autowired
    lateinit var salaryService: SalaryService

    @Autowired
    lateinit var employeeService: EmployeeService

    @Autowired
    lateinit var titleService: TitleService

    @Transactional
    override fun testSave() {
        employeeService.saveEmployee(Gender.M)
        titleService.saveTitle("aaa")
        salaryService.saveSalary(40000)
    }
}
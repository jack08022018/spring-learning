package com.springvalidate.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.springvalidate.dto.PersonRequest
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.validation.BindingResult
import org.springframework.validation.FieldError
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
@RequestMapping(value = ["/api"])
class ApiController {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    @Autowired
    @Qualifier("customObjectMapper")
    lateinit var mapper: ObjectMapper

    @PostMapping("/createPerson")
    fun createPerson(@Valid @RequestBody request: PersonRequest) {

    }

//    @PostMapping("/createPersonBinding")
//    fun createPerson(@Valid @RequestBody request: PersonRequest, bindingResult: BindingResult): Any? {
//        if(bindingResult.hasErrors()) {
//            return bindingResult.getFieldErrors().associateBy({it -> (it as FieldError).field}, {it.defaultMessage})
//        }
//        return null
//    }

}
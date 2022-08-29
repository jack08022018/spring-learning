package com.security.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.web.bind.annotation.*

@RestControllerAdvice
@RequestMapping(value = ["/api"])
class ApiController {
//    @Autowired
//    lateinit var exportService: ExportService

    @Autowired
    @Qualifier("customObjectMapper")
    lateinit var mapper: ObjectMapper

    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @GetMapping(value = ["/log"])
    fun log(@RequestParam("status") status: String) {
        if(status == "success") {
            logger.info("success")
        }else {
            println(1/0)
        }
    }

}
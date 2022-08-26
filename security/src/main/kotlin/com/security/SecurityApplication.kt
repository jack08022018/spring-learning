package com.security

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@EnableWebMvc
@SpringBootApplication
class SecurityApplication

fun main(args: Array<String>) {
    runApplication<SecurityApplication>(*args)
}

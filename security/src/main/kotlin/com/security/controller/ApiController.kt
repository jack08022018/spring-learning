package com.security.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.security.config.jwt.JwtUtils
import com.security.config.jwt.payload.LoginRequest
import com.security.config.jwt.payload.LoginResponse
import com.security.config.jwt.service.UserDetailsImpl
import com.security.dto.ParamInfo
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate
import java.util.stream.Collectors

@RestController
@RequestMapping(value = ["/api"])
class ApiController {
//    @Autowired
//    lateinit var exportService: ExportService

    @Autowired
    @Qualifier("customObjectMapper")
    lateinit var mapper: ObjectMapper

    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @Autowired
    lateinit var authenticationManager: AuthenticationManager

    @Autowired
    lateinit var jwtUtils: JwtUtils

    @PostMapping("/login")
    fun authenticateUser(@RequestBody loginRequest: LoginRequest): LoginResponse? {
        val authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                loginRequest.username,
                loginRequest.password
            )
        )
        SecurityContextHolder.getContext().authentication = authentication
        val response = jwtUtils.generateJwtToken(authentication)
        val userDetails: UserDetailsImpl = authentication.principal as UserDetailsImpl
        val roles: List<String> = userDetails.getAuthorities().stream()
            .map { item -> item.getAuthority() }
            .collect(Collectors.toList())
        return response
    }

    @GetMapping(value = ["/admin"])
    fun admin(@RequestParam("status") status: String) {
        if (status == "success") {
            logger.info("success")
        } else {
            println(1 / 0)
        }
    }

    @GetMapping(value = ["/user"])
    fun user(@RequestParam("status") status: String) {
        if (status == "success") {
            logger.info("success")
        } else {
            println(1 / 0)
        }
    }

    @Autowired
    @Qualifier("customRestTemplate")
    lateinit var restTemplate: RestTemplate

    @GetMapping(value = ["/rest"])
    fun rest(@RequestBody params: String) {
        try {
            var paramsInfo = mapper.readValues(params, ParamInfo.javaClass)
        }catch (e: Exception) {

        }
    }

}
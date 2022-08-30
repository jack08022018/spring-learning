package com.security.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.security.config.jwt.JwtTokenProvider
import com.security.config.jwt.payload.LoginRequest
import com.security.config.jwt.payload.LoginResponse
import com.security.config.jwt.user.CustomUserDetails
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
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
        if (status == "success") {
            logger.info("success")
        } else {
            println(1 / 0)
        }
    }

    @Autowired
    lateinit var authenticationManager: AuthenticationManager

    @Autowired
    lateinit var tokenProvider: JwtTokenProvider

    @PostMapping("/login")
    fun authenticateUser(@RequestBody loginRequest: LoginRequest): LoginResponse? {

        // Xác thực thông tin người dùng Request lên
        val authentication = authenticationManager!!.authenticate(
            UsernamePasswordAuthenticationToken(
                loginRequest.username,
                loginRequest.password
            )
        )

        // Nếu không xảy ra exception tức là thông tin hợp lệ
        // Set thông tin authentication vào Security Context
        SecurityContextHolder.getContext().authentication = authentication

        // Trả về jwt cho người dùng.
        val jwt: String = tokenProvider.generateToken(authentication.principal as CustomUserDetails)
        return LoginResponse(jwt)
    }

}
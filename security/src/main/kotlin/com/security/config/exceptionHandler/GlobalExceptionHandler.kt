package com.security.config.exceptionHandler

import com.fasterxml.jackson.databind.ObjectMapper
import com.security.common.FunctionCommonUtils
import com.security.config.exceptionHandler.exceptions.ErrorResponse
import com.security.controller.ApiController
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.time.LocalDateTime
import javax.servlet.http.HttpServletRequest

@RestControllerAdvice
class GlobalExceptionHandler : ResponseEntityExceptionHandler() {
    private val logger = LoggerFactory.getLogger(ApiController::class.java)

    @Autowired
    @Qualifier("customObjectMapper")
    lateinit var mapper: ObjectMapper

    @Autowired
    lateinit var functionCommonUtils: FunctionCommonUtils

    @ExceptionHandler(Exception::class)
    fun mainExceptionHandler(
        e: Exception,
        handlerMethod: HandlerMethod?,
        request: HttpServletRequest
    ): ResponseEntity<ErrorResponse> {
        logger.error("mainExceptionHandler\n", e)
        val response = ErrorResponse(
            timestamp = functionCommonUtils.localDateToString(LocalDateTime.now(), "yyyy-MM-dd HH:mm:ss"),
            status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            message = e.message,
            path = request.requestURI,
            trace = e.stackTraceToString()
        )
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(response)
    }

}
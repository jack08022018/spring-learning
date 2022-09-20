package com.springvalidate.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.springvalidate.common.FunctionCommonUtils
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.time.LocalDateTime
import javax.servlet.http.HttpServletRequest


@RestControllerAdvice
class GlobalExceptionHandler : ResponseEntityExceptionHandler() {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    @Autowired
    @Qualifier("customObjectMapper")
    lateinit var mapper: ObjectMapper

    @Autowired
    lateinit var functionCommonUtils: FunctionCommonUtils

    @ExceptionHandler(Exception::class)
    fun mainExceptionHandler(e: Exception, handlerMethod: HandlerMethod?, request: HttpServletRequest): Any {
        logger.error("mainExceptionHandler\n", e)
        val response = ErrorResponse(
            timestamp = functionCommonUtils.localDateToString(LocalDateTime.now(), "yyyy-MM-dd HH:mm:ss"),
            status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            message = e.message!!,
            path = request.requestURI,
            trace = e.stackTraceToString()
        )
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(response)
    }

    override fun handleMethodArgumentNotValid(
        e: MethodArgumentNotValidException, headers: HttpHeaders, status: HttpStatus, request: WebRequest
    ): ResponseEntity<Any> {
        logger.error("handleMethodArgumentNotValid\n", e)
        val response = ErrorResponse(
            timestamp = functionCommonUtils.localDateToString(LocalDateTime.now(), "yyyy-MM-dd HH:mm:ss"),
            status = HttpStatus.BAD_REQUEST.value(),
            message = e.bindingResult.allErrors.associateBy({it -> (it as FieldError).field}, {it.defaultMessage}),
            path = request.contextPath
        )
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(response)
    }

}
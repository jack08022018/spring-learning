package com.security.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.security.config.filters.MainFilter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.scheduling.annotation.EnableScheduling

@Configuration
@EnableScheduling
class BeanConfig {
    @Autowired
    lateinit var env: Environment

    @Bean(name = ["customObjectMapper"])
    fun getObjectMapper(): ObjectMapper? {
        val mapper = ObjectMapper()
        mapper.registerModule(JavaTimeModule())
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        return mapper
    }

    @Bean
    fun mainFilter(): FilterRegistrationBean<MainFilter>? {
        val registrationBean: FilterRegistrationBean<MainFilter> = FilterRegistrationBean<MainFilter>()
        registrationBean.filter = MainFilter()
        registrationBean.addUrlPatterns("/api/*")
        registrationBean.order = 1
        return registrationBean
    }

}

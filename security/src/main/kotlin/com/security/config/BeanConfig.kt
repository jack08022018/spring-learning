package com.security.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.security.config.exceptionHandler.RestTemplateResponseErrorHandler
import com.security.config.filters.MainFilter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.http.client.SimpleClientHttpRequestFactory
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.web.client.RestTemplate

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

    //    @Bean
    //    public FilterRegistrationBean<SubFilter> subFilter() {
    //        FilterRegistrationBean<SubFilter> registrationBean = new FilterRegistrationBean<>();
    //        registrationBean.setFilter(new SubFilter());
    //        registrationBean.addUrlPatterns("/api/*");
    //        registrationBean.setOrder(2);
    //        return registrationBean;
    //    }
    @Bean(name = ["customRestTemplate"])
    fun getRestTemplate(): RestTemplate? {
        val requestFactory = SimpleClientHttpRequestFactory()
        requestFactory.setReadTimeout(10000)
        requestFactory.setConnectTimeout(10000)
        val restTemplate = RestTemplate(requestFactory)
        restTemplate.errorHandler = RestTemplateResponseErrorHandler()
        return restTemplate
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

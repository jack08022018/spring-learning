package com.itext.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.itextpdf.html2pdf.ConverterProperties
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider
import com.itextpdf.layout.font.FontProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.core.env.get

@Configuration
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

    @Bean(name = ["customConverterProperties"])
    fun getConverterProperties(): ConverterProperties? {
        val fontProvider: FontProvider = DefaultFontProvider(false, false, false)
        fontProvider.addDirectory(env.get("pdf.font.folder"))
        var converter: ConverterProperties = ConverterProperties()
        converter.fontProvider = fontProvider
        return converter
    }
}

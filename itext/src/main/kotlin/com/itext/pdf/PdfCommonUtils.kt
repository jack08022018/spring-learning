package com.itext.pdf

import com.itextpdf.html2pdf.ConverterProperties
import com.itextpdf.html2pdf.HtmlConverter
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider
import com.itextpdf.layout.font.FontProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.core.env.Environment
import org.springframework.core.env.get
import org.springframework.stereotype.Component

@Component
class PdfCommonUtils {
    @Autowired
    @Qualifier("customConverterProperties")
    lateinit var converter: ConverterProperties

    fun <T> getElementFromHtml(html: String?): T {
        val list = HtmlConverter.convertToElements(html, converter)
        return list[0] as T
    }
}
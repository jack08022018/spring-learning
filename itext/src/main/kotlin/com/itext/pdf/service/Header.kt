package com.itext.pdf.service

import com.itext.pdf.PdfCommonDto
import com.itext.pdf.PdfCommonUtils
import com.itextpdf.html2pdf.ConverterProperties
import com.itextpdf.kernel.events.Event
import com.itextpdf.kernel.events.IEventHandler
import com.itextpdf.kernel.events.PdfDocumentEvent
import com.itextpdf.kernel.geom.Rectangle
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfPage
import com.itextpdf.kernel.pdf.canvas.PdfCanvas
import com.itextpdf.layout.Canvas
import com.itextpdf.layout.element.Paragraph
import com.itextpdf.layout.properties.TextAlignment

class Header(pdfCommonUtils: PdfCommonUtils, dto: PdfCommonDto) : IEventHandler {
    private val dto: PdfCommonDto = dto
    private val pdfCommonUtils: PdfCommonUtils = pdfCommonUtils
    override fun handleEvent(event: Event) {
        val docEvent: PdfDocumentEvent = event as PdfDocumentEvent
        val pdf: PdfDocument = docEvent.getDocument()
        val page: PdfPage = docEvent.getPage()
        val pageSize = page.pageSize
        val pdfCanvas = PdfCanvas(page.lastContentStream, page.resources, pdf)
        val canvas: Canvas = Canvas(pdfCanvas, pageSize)
//        if(page.getDocument().getNumberOfPages() > 0) {
            buildSmallHeader(canvas, pageSize)
//        }
        canvas.close()
    }

    private fun buildSmallHeader(canvas: Canvas, pageSize: Rectangle) {
        val url = "/src/main/resources/static/images/logo.png"
//            <img style='width: 50px; height: auto;' src='${url}'/>
        val imgHtml = """
            <span style='font-family: Tahoma; font-size: 14px;'>
                Hồ sơ số: 390004598
            </span>
        """.trimIndent()
        canvas.showTextAligned(
            pdfCommonUtils.getElementFromHtml(imgHtml) as Paragraph,
            pageSize.left + 30, pageSize.top - 40, TextAlignment.LEFT
        )
        val imgHtml2 = "<img style='width: 50px; height: auto;' " +
                "src='src/main/resources/static/images/logo.png'/>"
//        val text = "<span style='font-family: Tahoma; font-size: 14px;'>" +
//                "Hồ sơ số: 390004598</span>"
        //        canvas.showTextAligned((Paragraph) PdfCommonUtils.getElementFromHtml(text, converterFont),
//                pageSize.getRight() - 20, pageSize.getTop() - 40, TextAlignment.RIGHT);
    }

}
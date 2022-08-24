package com.itext.controller

import com.itext.service.ExportService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.InputStreamResource
import org.springframework.core.io.Resource
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.lang.Exception

@RestController
@RequestMapping(value = ["/api"])
class ApiController {
    @Autowired
    lateinit var exportService: ExportService

    @GetMapping(value = ["/exportExcel"])
    @Throws(Exception::class)
    fun exportExcel(@RequestParam("file") file: MultipartFile): ResponseEntity<Resource?>? {
        val fileExport = InputStreamResource(exportService.exportExcel(file))
        return ResponseEntity
            .ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=tutorials.xlsx")
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            //.contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
            .body(fileExport)
    }
}
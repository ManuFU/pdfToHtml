package com.example.pdftohtmlKotlin.controller

import com.example.pdftohtmlKotlin.application.ConvertPdfUseCase
import jakarta.annotation.PostConstruct
import org.apache.pdfbox.pdmodel.PDDocument
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.ByteArrayInputStream

@RestController
class FileUploadController(private val convertPdfUseCase: ConvertPdfUseCase) {

    @PostConstruct
    fun init() {
        println("FileUploadController initialized!")
    }
    @PostMapping("/upload")
    // This is a POST endpoint that takes a MultipartFile as input. The MultipartFile is expected to be a PDF file.
    fun uploadPdf(@RequestParam("file") file: MultipartFile): String {
        if (file.isEmpty) {
            throw IllegalArgumentException("File cannot be empty")
        }
        // A ByteArrayInputStream is created from the bytes of the uploaded file.
        // 'use' keyword ensures that the stream is closed after it's no longer needed.
        ByteArrayInputStream(file.bytes).use { inputStream ->

            // The InputStream is loaded into a PDDocument. This is the PDFBox representation of a PDF document.
            PDDocument.load(inputStream).use { document ->

                // The PDDocument is passed to the convertPdfUseCase to be converted into HTML.
                val html = convertPdfUseCase.execute(document)

                // The URL of the converted HTML file is returned as the response of the endpoint.
                return html.url
            }
        }
    }

}

package com.example.pdftohtmlKotlin.application

// ConvertPdfUseCase.kt

import com.example.pdftohtmlKotlin.domain.HtmlFile
import com.example.pdftohtmlKotlin.infrastructure.Converter
import com.example.pdftohtmlKotlin.infrastructure.Storage
import org.apache.pdfbox.pdmodel.PDDocument
import org.springframework.stereotype.Service

@Service
class ConvertPdfUseCase(private val converter: Converter, private val storage: Storage) {
    fun execute(pdf: PDDocument): HtmlFile {
        val html = converter.convert(pdf)
        storage.store(html)
        return html
    }
}

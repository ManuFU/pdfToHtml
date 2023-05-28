package com.example.pdftohtmlKotlin.application


import com.example.pdftohtmlKotlin.domain.PdfFile
import com.example.pdftohtmlKotlin.domain.HtmlFile
import com.example.pdftohtmlKotlin.infrastructure.Converter
import com.example.pdftohtmlKotlin.infrastructure.Storage
import org.apache.pdfbox.pdmodel.PDDocument
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import java.io.ByteArrayInputStream
import java.io.File
import kotlin.test.assertEquals

class ConvertPdfUseCaseTest {
    private lateinit var converter: Converter
    private lateinit var storage: Storage
    private lateinit var convertPdfUseCase: ConvertPdfUseCase

    @BeforeEach
    fun setup() {
        converter = mock()
        storage = mock()
        convertPdfUseCase = ConvertPdfUseCase(converter, storage)
    }

    @Test
    fun `it should convert pdf and store html`() {
        // given
        val pdfPath = "src/test/resources/test.pdf"
        val pdfFile = File(pdfPath)
        val pdfDocument: PDDocument
        pdfFile.inputStream().use { inputStream ->
            pdfDocument = PDDocument.load(inputStream)
        }
        val html = HtmlFile("", "/html/test.html")
        Mockito.`when`(converter.convert(any())).thenReturn(html)

        // when
        val result = convertPdfUseCase.execute(pdfDocument)

        // then
        verify(converter).convert(any())
        verify(storage).store(html)
        assertEquals(html, result)
    }

}

package com.example.pdftohtmlKotlin.infrastructure


import com.example.pdftohtmlKotlin.domain.PdfFile
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.nio.file.Files
import java.nio.file.Paths

import org.apache.pdfbox.pdmodel.PDDocument

class ConverterImplTest {

    @Test
    fun `it should convert pdf to html`() {
        // given
        val pdfPath = "src/test/resources/test.pdf"
        val converter = ConverterImpl()
        val document: PDDocument

        // Read PDF file and load into PDDocument
        Files.newInputStream(Paths.get(pdfPath)).use { inputStream ->
            document = PDDocument.load(inputStream)
        }

        // when
        val html = converter.convert(document)

        // then
        assertTrue(html.content.contains("<html>"))
        assertTrue(html.content.contains("</html>"))
    }
}


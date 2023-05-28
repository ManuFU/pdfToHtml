package com.example.pdftohtmlKotlin.infrastructure

import com.example.pdftohtmlKotlin.domain.PdfFile
import com.example.pdftohtmlKotlin.domain.HtmlFile
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.text.PDFTextStripper
import org.springframework.stereotype.Service
import java.io.ByteArrayInputStream
import java.util.UUID
interface Converter {
    fun convert(pdf: PDDocument): HtmlFile
}

@Service
class ConverterImpl : Converter {
    override fun convert(pdf: PDDocument): HtmlFile {
        val textContent = convertPdfToText(pdf)
        val htmlContent = textToHtml(textContent)
        val url = generateUrl()
        return HtmlFile(htmlContent, url)
    }

    private fun convertPdfToText(pdf: PDDocument): String {
        return PDFTextStripper().getText(pdf)
    }

    private fun textToHtml(textContent: String): String {
        return """
            <html>
                <body>
                    <pre>$textContent</pre>
                </body>
            </html>
        """.trimIndent()
    }

    private fun generateUrl(): String {
        // Generate a unique URL for each HTML file
        val uniqueId = UUID.randomUUID().toString()
        return "/html/$uniqueId.html"
    }
}

package com.example.pdftohtmlKotlin.infrastructure


import com.example.pdftohtmlKotlin.domain.HtmlFile
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.nio.file.Files
import java.nio.file.Paths

class StorageImplTest {
    @Test
    fun `it should store html`() {
        // given
        val htmlContent = "<html></html>"
        val htmlFile = HtmlFile(htmlContent, "/html/test.html")
        val storage = StorageImpl()

        // when
        storage.store(htmlFile)

        // then
        val storedContent = Files.readString(Paths.get("public${htmlFile.url}"))
        assertEquals(htmlContent, storedContent)

        // cleanup
        Files.delete(Paths.get("public${htmlFile.url}"))
    }
}

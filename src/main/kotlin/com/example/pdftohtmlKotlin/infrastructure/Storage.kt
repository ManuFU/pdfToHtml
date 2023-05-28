package com.example.pdftohtmlKotlin.infrastructure

// Storage.kt

import com.example.pdftohtmlKotlin.domain.HtmlFile
import org.springframework.stereotype.Service
import java.nio.file.Files
import java.nio.file.Paths

interface Storage {
    fun store(html: HtmlFile)
}

@Service
class StorageImpl : Storage {
    override fun store(html: HtmlFile) {
        val path = Paths.get("public${html.url}")
        Files.createDirectories(path.parent)
        Files.writeString(path, html.content)
    }
}

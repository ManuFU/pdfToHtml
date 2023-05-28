package com.example.pdftohtmlKotlin.controller

import org.springframework.core.io.InputStreamResource
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.nio.file.Files
import java.nio.file.Paths

@RestController
class FlexibleServingController {
    @GetMapping(value = ["/html/{filename:.+\\.html}"])
    fun flexibleServingController(@PathVariable filename: String): ResponseEntity<InputStreamResource> {
        val file = Paths.get("public/html/$filename")
        val resource = InputStreamResource(Files.newInputStream(file))

        return ResponseEntity.ok()
            .contentLength(Files.size(file))
            .contentType(MediaType.parseMediaType("text/html"))
            .body(resource)
    }
}





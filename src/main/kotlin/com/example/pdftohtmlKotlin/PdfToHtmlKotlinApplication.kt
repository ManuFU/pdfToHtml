package com.example.pdftohtmlKotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@SpringBootApplication
class PdfToHtmlKotlinApplication

fun main(args: Array<String>) {
    runApplication<PdfToHtmlKotlinApplication>(*args)
}

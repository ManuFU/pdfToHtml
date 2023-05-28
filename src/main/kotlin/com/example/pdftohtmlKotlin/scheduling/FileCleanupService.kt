package com.example.pdftohtmlKotlin.scheduling

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.nio.file.Files
import java.nio.file.Paths
import java.util.stream.Collectors

@Service
class FileCleanupService {
    @Scheduled(fixedRate = 12000000)  // Run every 200 minutes
    fun deleteOldFiles() {
        val htmlDirectory = Paths.get("public/html")

        if (Files.exists(htmlDirectory)) {
            val now = System.currentTimeMillis()

            // Get all files in the directory
            val filesInDirectory = Files.list(htmlDirectory).collect(Collectors.toList())

            for (filePath in filesInDirectory) {
                // Check if the file is older than 20 minutes
                if (Files.isRegularFile(filePath) && now - Files.getLastModifiedTime(filePath).toMillis() > 1200000) {
                    // Delete the file
                    Files.delete(filePath)
                }
            }
        }
    }
}

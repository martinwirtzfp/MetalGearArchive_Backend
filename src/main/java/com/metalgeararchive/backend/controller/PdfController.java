package com.metalgeararchive.backend.controller;

import com.metalgeararchive.backend.service.PdfService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@RestController
@RequestMapping("/api/pdf")
@CrossOrigin(origins = "http://localhost:5173")
public class PdfController {

    private final PdfService pdfService;

    public PdfController(PdfService pdfService) {
        this.pdfService = pdfService;
    }

    @PostMapping("/extract")
    public ResponseEntity<Map<String, String>> extractText(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "No file provided"));
        }
        try (InputStream inputStream = file.getInputStream()) {
            byte[] header = inputStream.readNBytes(5);
            if (header.length < 5 || header[0] != '%' || header[1] != 'P' || header[2] != 'D'
                    || header[3] != 'F' || header[4] != '-') {
                return ResponseEntity.badRequest().body(Map.of("error", "Only PDF files are accepted"));
            }
        } catch (IOException e) {
            return ResponseEntity.badRequest().body(Map.of("error", "Failed to read file"));
        }
        try {
            String text = pdfService.extractText(file);
            return ResponseEntity.ok(Map.of("text", text));
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body(Map.of("error", "Failed to read PDF: " + e.getMessage()));
        }
    }
}

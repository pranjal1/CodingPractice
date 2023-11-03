package com.example.demo.controller;

import com.example.demo.service.ParserService;
import com.example.demo.service.ParserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

@RestController
public class MainController {

    @Autowired
    ParserService parserService;

    @GetMapping("/")
    public String healthCheck(){
        return "Hello World!";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "File is empty. Please select a file to upload.";
        }

        try {
            // Read the file content into a string
            String fileContent = new String(file.getBytes());
            String result = parserService.parseFile(Optional.of(fileContent));
            return result;
        } catch (IOException e) {
            return "Failed to read the file: " + e.getMessage();
        }
    }
}

package com.example.FoodDrink.controller;

import com.example.FoodDrink.service.AWSS3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;

@RestController
@RequestMapping("/api/upload")
@RequiredArgsConstructor
public class TestAws {
    private final AWSS3Service awsS3Service;

    @PostMapping
    public ResponseEntity<String> upload(
            @RequestParam("file") MultipartFile file,
            @RequestParam("keyName") String keyName
    ) {
        URL savedFile = awsS3Service.uploadFile(keyName, file);
        return ResponseEntity.ok("SAVED SUCCESFULLY: " + savedFile.toString());
    }
}

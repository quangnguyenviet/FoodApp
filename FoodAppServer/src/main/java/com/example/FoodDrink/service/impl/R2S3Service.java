package com.example.FoodDrink.service.impl;

import com.example.FoodDrink.service.AWSS3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3Client;

import java.net.URL;

@Service
@ConditionalOnProperty(name = "storage.provider", havingValue = "r2")
@RequiredArgsConstructor
@Slf4j
public class R2S3Service implements AWSS3Service {

    @Value("${r2.bucketName}")
    private String bucketName;

    @Value("${r2.publicUrl}")
    private String publicUrl;

    private final S3Client s3Client;

    @Override
    public URL uploadFile(String keyName, MultipartFile file) {
        try {
            // Upload file lên R2
            s3Client.putObject(
                    software.amazon.awssdk.services.s3.model.PutObjectRequest.builder()
                            .bucket(bucketName)
                            .key(keyName)
                            .contentType(file.getContentType())
                            .build(),
                    software.amazon.awssdk.core.sync.RequestBody.fromBytes(file.getBytes())
            );

            // Tạo URL public
            String fileUrl = publicUrl + "/" + keyName;

            log.info("File uploaded successfully: {}", fileUrl);

            return new URL(fileUrl);

        } catch (Exception e) {
            log.error("Error uploading file to R2", e);
            throw new RuntimeException("Upload file failed", e);
        }
    }

    @Override
    public void deleteFile(String keyName) {
        try {
            s3Client.deleteObject(
                    software.amazon.awssdk.services.s3.model.DeleteObjectRequest.builder()
                            .bucket(bucketName)
                            .key(keyName)
                            .build()
            );

            log.info("File deleted successfully: {}", keyName);

        } catch (Exception e) {
            log.error("Error deleting file from R2", e);
            throw new RuntimeException("Delete file failed", e);
        }
    }
}

package com.cloud_storage.service.impl;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.cloud_storage.dto.response.FileUploadResponse;
import com.cloud_storage.exception.UploadFileException;
import com.cloud_storage.service.FileService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class DefaultFileServiceImpl implements FileService {

    @Value("${aws.s3.bucketName}")
    private String bucketName;

    //Thao tac den aws
    private final AmazonS3 s3Client;

    public DefaultFileServiceImpl(AmazonS3 s3Client) {
        this.s3Client = s3Client;
    }

    //    upload
    @Override
    public FileUploadResponse uploadFile(MultipartFile multipartFile) {
//        fileResponse
        FileUploadResponse fileUploadResponse = new FileUploadResponse();
//        Tao dinh dang ngay
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String todayDate = dateTimeFormatter.format(LocalDate.now());
        String filePath = "";
        try {
//            object cua awsS3
//            Chua thong tin vn content type va size cua file
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(multipartFile.getContentType());
            objectMetadata.setContentLength(multipartFile.getSize());

            filePath = todayDate + "/" + multipartFile.getOriginalFilename();
//            put day len, dua len bucket tren aws
            s3Client.putObject(bucketName, filePath, multipartFile.getInputStream(), objectMetadata);

//            response
            fileUploadResponse.setFilePath(filePath);
            fileUploadResponse.setDateTime(LocalDateTime.now());

        } catch (IOException e) {
            throw new UploadFileException("Error upload file with error message: " + e.getMessage());
        }
        return fileUploadResponse;
    }
}

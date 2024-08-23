package com.cloud_storage.web.rest;

import com.cloud_storage.dto.response.FileUploadResponse;
import com.cloud_storage.service.FileService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/files")
public class FileUploadResource {
    private final FileService fileService;

    public FileUploadResource(FileService fileService){
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    public FileUploadResponse uploadFile(@RequestBody MultipartFile file){
        return fileService.uploadFile(file);
    }
}

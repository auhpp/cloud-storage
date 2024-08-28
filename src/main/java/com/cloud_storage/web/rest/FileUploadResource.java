package com.cloud_storage.web.rest;

import com.cloud_storage.dto.request.FileDownloadRequest;
import com.cloud_storage.dto.response.FileDownloadResponse;
import com.cloud_storage.dto.response.FileUploadResponse;
import com.cloud_storage.service.FileService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/files")
public class FileUploadResource {
    private final FileService fileService;

    public FileUploadResource(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    public FileUploadResponse uploadFile(@RequestBody MultipartFile file) {
        return fileService.uploadFile(file);
    }

    @GetMapping("/download")
    public FileDownloadResponse getFile(@RequestBody FileDownloadRequest fileDownloadRequest) throws IOException {
        return fileService.getFile(fileDownloadRequest);
    }

}

package com.cloud_storage.service;

import com.cloud_storage.dto.response.FileUploadResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


public interface FileService {
    FileUploadResponse uploadFile(MultipartFile multipartFile);
}

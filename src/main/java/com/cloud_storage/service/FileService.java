package com.cloud_storage.service;

import com.cloud_storage.dto.request.FileDownloadRequest;
import com.cloud_storage.dto.response.FileDownloadResponse;
import com.cloud_storage.dto.response.FileUploadResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;


public interface FileService {
//    upload file
    FileUploadResponse uploadFile(MultipartFile multipartFile);
//    download file
    FileDownloadResponse getFile(FileDownloadRequest request) throws IOException;
}

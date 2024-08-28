package com.cloud_storage.service.impl;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.cloud_storage.config.S3Properties;
import com.cloud_storage.dto.request.FileDownloadRequest;
import com.cloud_storage.dto.response.FileDownloadResponse;
import com.cloud_storage.dto.response.FileUploadResponse;
import com.cloud_storage.exception.UploadFileException;
import com.cloud_storage.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class DefaultFileServiceImpl implements FileService {

    private final S3Properties s3Properties;

    //Thao tac den aws
    private final AmazonS3 s3Client;

    public DefaultFileServiceImpl(AmazonS3 s3Client, S3Properties s3Properties) {
        this.s3Client = s3Client;
        this.s3Properties = s3Properties;
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
//            chua info ve obj upload to S3
            ObjectMetadata objectMetadata = new ObjectMetadata();
//          set type of obj do. vd: img, doc, pdf
            objectMetadata.setContentType(multipartFile.getContentType());
//            set size of obj
            objectMetadata.setContentLength(multipartFile.getSize());
//           Tao path file duy nhat cho moi file se duoc luu tru tren bucket
            filePath = todayDate + "/" + multipartFile.getOriginalFilename();

//            put day len, dua len bucket tren aws
            s3Client.putObject(s3Properties.getBucketName(), filePath, multipartFile.getInputStream(), objectMetadata);

//            response
            fileUploadResponse.setFilePath(filePath);
            fileUploadResponse.setDateTime(LocalDateTime.now());

        } catch (IOException e) {
            throw new UploadFileException("Error upload file with error message: " + e.getMessage());
        }
        return fileUploadResponse;
    }

    @Override
    public FileDownloadResponse getFile(FileDownloadRequest request) throws IOException {
        S3Object s3Object = s3Client.getObject(request.getBucketName(), request.getNameFile());
//        S3ObjectInputStream inputStream = s3Object.getObjectContent();
        String path = "C:\\Users\\DELL1\\Downloads";

        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdirs(); // Create the directory if it doesn't exist
        }

        File file = new File(directory, request.getNameFile());
//        FileOutputStream fos = new FileOutputStream(file);
//        byte[] read_buf = new byte[1024];
//        int read_len = 0;
//        while ((read_len = inputStream.read(read_buf)) > 0) {
//            fos.write(read_buf, 0, read_len);
//        }
        try (S3ObjectInputStream inputStream = s3Object.getObjectContent();
             FileOutputStream fos = new FileOutputStream(file)) {

            byte[] read_buf = new byte[1024];
            int read_len;
            while ((read_len = inputStream.read(read_buf)) > 0) {
                fos.write(read_buf, 0, read_len);
            }
            inputStream.close();
            fos.close();
        } catch (IOException e) {
            // Handle the exception (e.g., log it or rethrow it)
            e.printStackTrace();
        }
        return FileDownloadResponse.builder().nameFile(request.getNameFile()).filePath(file.getPath()).build();
    }
}

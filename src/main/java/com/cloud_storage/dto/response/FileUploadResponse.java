package com.cloud_storage.dto.response;


import java.time.LocalDateTime;



public class FileUploadResponse {
    private String filePath;
    private LocalDateTime dateTime;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}

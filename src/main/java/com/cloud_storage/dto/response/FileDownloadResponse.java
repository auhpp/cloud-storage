package com.cloud_storage.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FileDownloadResponse {
    private String filePath;
    private String nameFile;
}

package com.cloud_storage.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FileDownloadRequest {
    @JsonProperty("bucket_name")
    private String bucketName;

    @JsonProperty("name_file")
    private String nameFile;
}

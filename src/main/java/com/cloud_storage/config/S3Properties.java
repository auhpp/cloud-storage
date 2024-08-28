package com.cloud_storage.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class S3Properties {
    @Value("${app.aws.s3.bucketName}")
    private String bucketName;
}

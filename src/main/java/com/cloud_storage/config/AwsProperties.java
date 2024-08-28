package com.cloud_storage.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class AwsProperties {
    //    like user name
    @Value("${app.aws.s3.accessKey}")
    private String accessKey;
    //like password
    @Value("${app.aws.s3.secretKey}")
    private String secretKey;
}

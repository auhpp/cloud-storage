package com.cloud_storage.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageConfig {

    private final AwsProperties properties;

    public StorageConfig(AwsProperties properties) {
        this.properties = properties;
    }

    //    khi app run no se auto connect to aws bucket luon
    @Bean
    public AmazonS3 createS3Client() {
//        Thong tin xac thuc
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(properties.getAccessKey(), properties.getSecretKey());
//       build, create s3Client
        return AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withRegion(Regions.AP_SOUTHEAST_2)   //thiet lap khu vuc ma client la app cua minh se run
                .build();                              //se request to aws s3
        //Asian Pacific (Chau a thai binh duong)
    }
}

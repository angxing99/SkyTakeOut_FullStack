package com.sky.service.impl;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.sky.properties.S3Properties;
import com.sky.service.AmazonService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.UUID;
@Service
@ConfigurationProperties(prefix = "sky.aws") // Matches your YAML

public class AmazonServiceImpl implements AmazonService {

    private final AmazonS3 amazonS3;
    private final S3Properties s3Properties;


    @Autowired
    public AmazonServiceImpl(AmazonS3 amazonS3, S3Properties s3Properties) {
        this.amazonS3 = amazonS3;
        this.s3Properties = s3Properties;
    }


    public String uploadFile(File file) {
        try {
            String originalName = file.getName();
            String extension = "";
            int i = originalName.lastIndexOf('.');
            if (i > 0) {
                extension = originalName.substring(i);
            }

            //** Replace file name with UUID to prevent duplicate file get overwrite
            String newFileName = UUID.randomUUID().toString() + extension;

            //** Retrieve bucket name and region from properties
            String bucketName = s3Properties.getS3().getBucketName();
            String region = s3Properties.getRegion();


            // Upload to S3
            PutObjectRequest request = new PutObjectRequest(bucketName, newFileName, file);
            amazonS3.putObject(request);

            // Build public URL
            return String.format("https://%s.s3.%s.amazonaws.com/%s", bucketName, region, newFileName);

        } catch (Exception e) {
            e.printStackTrace();
            return "Error uploading file";
        }
    }

}

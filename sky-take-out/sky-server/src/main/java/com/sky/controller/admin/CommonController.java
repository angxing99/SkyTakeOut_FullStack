package com.sky.controller.admin;


import com.sky.constant.MessageConstant;
import com.sky.result.Result;
import com.sky.service.AmazonService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@RequestMapping("/admin/common")
@Slf4j
public class CommonController {

    @Autowired
    private AmazonService s3Service;

    @PostMapping("/upload")
    @ApiOperation("File Upload")
    public Result<String> upload(@RequestParam("file") MultipartFile file) {
        File tempFile = null;

        try {
            // Create a temp file
            tempFile = File.createTempFile("upload-", "-" + file.getOriginalFilename());
            file.transferTo(tempFile);

            // Upload to S3
            String result = s3Service.uploadFile(tempFile);

            return Result.success(result);

        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(MessageConstant.UPLOAD_FAILED);
        } finally {
            // Cleanup temp file
            if (tempFile != null && tempFile.exists()) {
                tempFile.delete();
            }
        }
    }


}

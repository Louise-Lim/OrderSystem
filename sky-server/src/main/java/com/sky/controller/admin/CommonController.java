package com.sky.controller.admin;

import com.sky.constant.MessageConstant;
import com.sky.result.Result;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@RestController
@RequestMapping("/admin/common")
@ApiOperation("Common API")
@Slf4j
public class CommonController {
    private static String FILE_UPLOAD_PATH = "C:\\Users\\ASUS\\Downloads\\Ordering\\sky-take-out\\sky-server\\src\\main\\resources\\upload";

    @PostMapping("/upload")
    @ApiOperation("Upload File")
    public Result<String> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return Result.error("File is empty");
        }

        File dir = new File(FILE_UPLOAD_PATH);
        if (!dir.exists() || !dir.isDirectory()) {
            boolean created = dir.mkdirs();
            if (created) {
                log.info("Created Directory: {}", FILE_UPLOAD_PATH);
            } else {
                log.warn("Could not create directory or have directory exist: {}", FILE_UPLOAD_PATH);
            }
        }

        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null || originalFilename.isEmpty()) {
            return Result.error("File name invalid");
        }

        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        if (!extension.equalsIgnoreCase(".png") && !extension.equalsIgnoreCase(".jpg") && !extension.equalsIgnoreCase(".jpeg")) {
            return Result.error("File extension not supported");
        }

        originalFilename = UUID.randomUUID().toString() + extension;

        Path targetLocation = Paths.get(FILE_UPLOAD_PATH).resolve(originalFilename).normalize();
        try {
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            log.info("Uploaded File Success: {}", originalFilename);
        } catch (IOException e) {
            log.error("Upload File Fail: {}", originalFilename, e);
            return Result.error(MessageConstant.UPLOAD_FAILED);
        }

        String fileUrl = "http://localhost:8080/static/" + originalFilename;
        return Result.success(fileUrl);
    }
}

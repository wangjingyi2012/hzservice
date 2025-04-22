package com.wjy.hz.service.impl;

import com.wjy.hz.service.FileStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Override
    public String storeFile(MultipartFile file) {
        // 生成唯一的文件名
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

        // 目标保存路径
        Path targetLocation = Paths.get(uploadDir).resolve(fileName);

        try {
            // 保存文件
//            Files.copy(file.getInputStream(), targetLocation);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        // 返回文件名（或路径）
        return fileName;
    }


}

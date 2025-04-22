package com.wjy.hz.controller;

import com.alibaba.fastjson.JSONObject;
import com.wjy.hz.model.api.ApiResponse;
import com.wjy.hz.service.FileStorageService;
import com.wjy.hz.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;

@RestController
public class FileController {

    @Resource
    FileStorageService fileStorageService;

    @Resource
    StudentService studentService;


    @PostMapping("/api/upload/image")
    public ResponseEntity<String> uploadImage(@RequestParam("wangeditor-uploaded-image") MultipartFile image) {
        JSONObject responseJson = new JSONObject();
        try {
            // 这里添加保存文件的逻辑，假设保存后得到了一个可访问的文件URL
            String fileUrl = saveImage(image);

            // 构建返回的JSON数据
            JSONObject dataJson = new JSONObject();
            dataJson.put("url", fileUrl);
            dataJson.put("alt", "Image description"); // 可以是动态数据
            dataJson.put("href", "Image link"); // 可以是动态数据

            responseJson.put("errno", 0);
            responseJson.put("data", dataJson);
        } catch (Exception e) {
            // 发生错误时的逻辑
            responseJson.put("errno", 1);
            responseJson.put("message", "上传失败，原因：" + e.getMessage());
        }

        return ResponseEntity.ok(responseJson.toJSONString());
    }

    private String saveImage(MultipartFile image) throws IOException {
        // 处理图片保存逻辑
        String filePath;
        if (System.getProperty("os.name").startsWith("Windows")) {
            // Windows开发环境路径
            filePath = "C:\\data\\" + image.getOriginalFilename();
        } else {
            // 生产环境路径
            filePath = "/webapp/images/uploads/" + image.getOriginalFilename();
            //System.out.println("Original filename: " + image.getOriginalFilename());
            File destination = new File(filePath);
            image.transferTo(destination);
        }

        // 返回图片的访问URL
        return "http://www.hz-study-system.com/wjy/images/" + image.getOriginalFilename();
    }



    @PostMapping("/api/student/upload-avatar")
    public String uploadAvatar(@RequestParam("avatar") MultipartFile file, @RequestParam("sid") Long studentId) {
        try {
            // 更新用户头像信息
            int result = studentService.uploadAvator(studentId, file);
            return result > 0 ? ApiResponse.ok(studentId) : ApiResponse.intError();
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.intError();
        }
    }

}

package com.wjy.hz.controller;

import com.wjy.hz.model.api.ApiResponse;
import com.wjy.hz.model.dto.BlogDto;
import com.wjy.hz.model.dto.BlogInputDto;
import com.wjy.hz.model.dto.CommentInputDto;
import com.wjy.hz.model.dto.ReviewHomeworkDto;
import com.wjy.hz.service.BlogService;
import com.wjy.hz.service.HomeworkService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
public class HomeworkController {

    @Resource
    HomeworkService homeworkService;

    @GetMapping("/api/homework/homework-list")
    public String homeworkList() {
        return ApiResponse.ok(homeworkService.allHomeworks());
    }

    @GetMapping("/api/homework/student-homework")
    public String homeworkList(@RequestParam("sid") Integer sid,
                               @RequestParam("hid") Integer hid) {
        return ApiResponse.ok(homeworkService.getHomeworkOfUser(sid, hid));
    }

    @GetMapping("/api/teacher/homework/student-homework")
    public String teacherHomeworkList(@RequestParam("sid") Integer sid,
                               @RequestParam("hid") Integer hid) {
        return ApiResponse.ok(homeworkService.getHomeworkOfStudent2Teacher(sid, hid));
    }

    @GetMapping("/api/homework/public/review-progress")
    public String reviewHomeProgress() {
        return ApiResponse.ok(homeworkService.reviewPublicProgress());
    }

    @PostMapping("/api/teacher/homework/review")
    public String reviewHomework(@RequestBody ReviewHomeworkDto dto) {
        return ApiResponse.ok(homeworkService.reviewHomework(dto.getHid(), dto.getSid(), dto.getScore(), dto.getTip()));
    }



    @PostMapping("/api/homework/student-homework-submit")
    public String submitHomework(@RequestParam("sid") Integer sid,
                                 @RequestParam("hid") Integer hid,
                                 @RequestParam("file") MultipartFile file,
                                 @RequestParam(value = "comment", required = false) String comment) {
        // 定义文件保存目录
        String uploadDir = "/webapp/homework/2024-1/";
        // 获取原始文件名，并加上唯一标识符防止文件名冲突
        String originalFilename = file.getOriginalFilename();
        String newFilename = UUID.randomUUID() + "_" + originalFilename;

        // 创建目标文件
        File destFile = new File(uploadDir + newFilename);

        try {
            // 保存文件到指定目录
            file.transferTo(destFile);
        } catch (IOException e) {
            e.printStackTrace();
            return "文件上传失败";
        }

        // 返回文件的访问路径，可以存储在数据库中
        String fileUrl = "http://www.hz-study-system.com/wjy/hm/" + newFilename;

        // 调用服务层保存作业提交记录
        homeworkService.submitHomework(fileUrl, sid, hid, comment);

        return ApiResponse.ok("提交作业成功");
    }



}

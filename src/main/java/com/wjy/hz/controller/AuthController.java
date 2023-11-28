package com.wjy.hz.controller;

import com.wjy.hz.model.api.ApiResponse;
import com.wjy.hz.model.dto.StudentDto;
import com.wjy.hz.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

@RestController
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Resource
    StudentService studentService;

    @PostMapping("/api/student/auth/login")
    public ResponseEntity<String> login(@RequestParam("number") String number, @RequestParam("password") String password) {
        try {
            StudentDto student = studentService.login(number, password);
            if (student == null) {
                logger.info("Login attempt failed for user {}", number);
                return ResponseEntity.status(403).body(ApiResponse.intError());
            }
            logger.info("User {} logged in successfully", number);
            return ResponseEntity.ok(ApiResponse.ok(student));
        } catch (Exception e) {
            logger.error("An error occurred during login", e);
            return ResponseEntity.status(500).body(ApiResponse.sysError());
        }
    }
}

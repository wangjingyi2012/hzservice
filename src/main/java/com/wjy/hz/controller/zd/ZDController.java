package com.wjy.hz.controller.zd;

import com.wjy.hz.model.api.ApiResponse;
import com.wjy.hz.service.ZDService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class ZDController {

    @Resource
    ZDService zdService;

    @GetMapping("/api/zd/attendance-list")
    public String getAttendanceList(
            @RequestParam(name = "page", defaultValue = "1") Integer page,  // 默认值1
            @RequestParam(name = "size", defaultValue = "10") Integer size   // 默认值10
    ) {
        int validPage = page <= 0 ? 1 : page;
        int validSize = size <= 0 ? 10 : size;
        return ApiResponse.ok(zdService.getAttendanceList(validPage, validSize));
    }

    @GetMapping("/api/zd/person-list")
    public String getAllPerson() {
        return ApiResponse.ok(zdService.getAllPerson());
    }
}
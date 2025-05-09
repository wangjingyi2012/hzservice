package com.wjy.hz.controller.zd;

import com.wjy.hz.model.api.ApiResponse;
import com.wjy.hz.service.ZDService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
            @RequestParam(name = "size", defaultValue = "10") Integer size,   // 默认值10
            @RequestParam(name = "personId", required = false) Integer personId  // 新增人员ID筛选参数
    ) {
        int validPage = page <= 0 ? 1 : page;
        int validSize = size <= 0 ? 10 : size;
        return ApiResponse.ok(zdService.getAttendanceList(validPage, validSize, personId));
    }

    @GetMapping("/api/zd/person-list")
    public String getAllPerson() {
        return ApiResponse.ok(zdService.getAllPerson());
    }

    @PostMapping("/api/zd-manager/add-attendance")
    public String addAttendance(@RequestParam("personId") Integer personId,
                                @RequestParam("result") String result,
                                @RequestParam("kda") String kda,
                                @RequestParam("hero") Integer heroId) {
        return ApiResponse.ok(zdService.addAttendance(personId, result, kda, heroId, "战队赛") > 0 ? "ok" : "error");
    }

    @GetMapping("/api/zd-manager/get-heros")
    public String getHeros() {
        return ApiResponse.ok(zdService.getAllHeros());
    }
}
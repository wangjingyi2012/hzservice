package com.wjy.hz.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wjy.hz.service.IPService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
public class IPController {

    @Resource
    IPService ipService;

    @PostMapping("/api/tools/third/ip-input")
    public String ipInput(@RequestBody String ipbody) {
        // 解析请求体为JSON对象
        JSONObject requestBody = JSON.parseObject(ipbody);
        // 从JSON对象中获取IP地址列表
        JSONArray ips = requestBody.getJSONArray("ips");
        List<String> results = new ArrayList<>();

        // 遍历IP地址，使用ipService获取每个IP的地区信息
        for (int i = 0; i < ips.size(); i++) {
            String ip = ips.getString(i);
            String region = ipService.getRegionByIp(ip);
            // 构造返回的结果字符串，这里简单地将IP和地区信息拼接
            results.add(ip + ": " + region);
        }

        // 将结果转换为JSON字符串并返回
        return JSON.toJSONString(results);
    }
}

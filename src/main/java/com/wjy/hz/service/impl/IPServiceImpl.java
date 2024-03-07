package com.wjy.hz.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wjy.hz.service.IPService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class IPServiceImpl implements IPService {

    @Override
    public String getRegionByIp(String ip) {
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();
        String url = "https://searchplugin.csdn.net/api/v1/ip/get?ip=" + ip;
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                JsonNode rootNode = objectMapper.readTree(response.getBody());
                JsonNode codeNode = rootNode.path("code");
                if (codeNode.asInt() == 200) {
                    System.out.println(rootNode);
                    JsonNode dataNode = rootNode.path("data");
                    JsonNode addressNode = dataNode.path("address");
                    String address = addressNode.asText();
                    // 可能需要进一步处理字符串来提取省份信息
//                    return address.split(" ")[1]; // 假设地址格式始终如"中国 浙江 杭州 阿里云"这样
                    return address;
                }
            }
        } catch (Exception e) {
            // 处理异常情况
            e.printStackTrace();
            return "未知地区";
        }
        return "未知地区"; // 如果无法获取地区，则返回默认值
    }
}

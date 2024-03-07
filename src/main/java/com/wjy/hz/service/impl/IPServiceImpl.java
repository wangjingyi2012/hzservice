package com.wjy.hz.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wjy.hz.helper.IPHelper;
import com.wjy.hz.service.IPService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class IPServiceImpl implements IPService {

    public String getRegionByIpV4(String ip) {
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

    @Override
    public String getRegionByIp(String ip) {
        String ipType = IPHelper.getIPType(ip);
        if ("无效".equals(ipType)) {
            return "IP格式错误";
        }

        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();
        // 根据IP类型选择不同的URL
        String baseUrl = "https://qifu.baidu.com/ip/geo/v1/";
        String url = baseUrl + (ipType.equals("IPv4") ? "district" : "ipv6/district") + "?ip=" + ip;

        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                JsonNode rootNode = objectMapper.readTree(response.getBody());
                JsonNode codeNode = rootNode.path("code");
                // 检查返回的状态码是否表示成功
                if ("Success".equals(codeNode.asText())) {
                    JsonNode dataNode = rootNode.path("data");

                    // 提取所需信息
                    String continent = dataNode.path("continent").asText("");
                    String country = dataNode.path("country").asText("");
                    String prov = dataNode.path("prov").asText("");
                    String city = dataNode.path("city").asText("");
                    String district = dataNode.path("district").asText("");
                    String isp = dataNode.path("isp").asText("");

                    // 构造并返回信息字符串，根据需要进行格式调整
//                    return String.format("Continent: %s, Country: %s, Province: %s, City: %s, District: %s, ISP: %s",
//                            continent, country, prov, city, district, isp);

                    return String.format("%s,%s,%s,%s,%s",
                            rootNode.path("data").path("country").asText(""),
                            rootNode.path("data").path("prov").asText(""),
                            rootNode.path("data").path("city").asText(""),
                            rootNode.path("data").path("district").asText(""),
                            rootNode.path("data").path("isp").asText(""));
                }
            }
        } catch (Exception e) {
            // 处理异常情况
            e.printStackTrace();
            return "查询失败或发生异常";
        }
        return "未知地区"; // 如果无法获取地区，则返回默认值
    }

}

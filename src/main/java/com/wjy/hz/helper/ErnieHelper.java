package com.wjy.hz.helper;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wjy.hz.model.dto.baidu.ChatSend;
import okhttp3.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ErnieHelper {

    static final OkHttpClient HTTP_CLIENT = new OkHttpClient().newBuilder().build();

    private static final String API_URL = "https://aip.baidubce.com/rpc/2.0/ai_custom/v1/wenxinworkshop/chat/completions_pro";  // 请替换为真实的API地址
    private static final String API_KEY = "yO3v4jeoGWGIuTF3Bdu0SxCS";  // 请替换为你的API密钥
    private static final String AppSecret = "W5UjiV6moutpHz5AdtBlldUiCA8cLRLl";

    public static void main(String args[]) {
//        System.out.println(askERNIEBot("试一下能不能请求"));
        System.out.println(getToken(API_KEY, AppSecret));
    }

    public static String getToken(String appKey, String appSecret) {
        try {
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "");
            Request request = new Request.Builder()
                    .url("https://aip.baidubce.com/oauth/2.0/token?client_id=" +  appKey+ "&client_secret=" + appSecret + "&grant_type=client_credentials")
                    .method("POST", body)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Accept", "application/json")
                    .build();
            Response response = HTTP_CLIENT.newCall(request).execute();
            return JSON.parseObject(response.body().string()).getString("access_token");
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }

    }

    public static String askERNIEBot(List<ChatSend> questions)  {
        String token = getToken(API_KEY, AppSecret);
        Map<String, List<ChatSend>> messages = new HashMap<>();
        messages.put("messages", questions);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)  // 连接超时时间
                .writeTimeout(10, TimeUnit.SECONDS)  // 写入超时时间
                .readTimeout(30, TimeUnit.SECONDS)  // 读取超时时间
                .build();

        try {
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(messages));
            Request request = new Request.Builder()
                    .url("https://aip.baidubce.com/rpc/2.0/ai_custom/v1/wenxinworkshop/chat/completions_pro?access_token=" + token)
                    .method("POST", body)
                    .addHeader("Content-Type", "application/json")
                    .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }
}

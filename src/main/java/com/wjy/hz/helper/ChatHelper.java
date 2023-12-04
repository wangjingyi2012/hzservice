package com.wjy.hz.helper;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpHeaders;

import java.io.IOException;

public class ChatHelper {

    private static final String API_URL = "https://api.openai.com/v1/engines/text-davinci-003/completions";
    private static final String API_KEY = "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";

    public static String sendText(String text) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost request = new HttpPost(API_URL);
            request.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + API_KEY);
            request.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");

            String jsonBody = "{\"prompt\": \"" + text + "\", \"max_tokens\": 1024}";
            request.setEntity(new StringEntity(jsonBody));

            return httpClient.execute(request, httpResponse ->
                    EntityUtils.toString(httpResponse.getEntity()));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}

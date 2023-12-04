package com.wjy.hz.controller;

import com.wjy.hz.model.api.ApiResponse;
import com.wjy.hz.service.ChatService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class ChatController {

    @Resource
    ChatService chatService;

    @GetMapping("/api/chat/chat-list")
    public String chatLiSt(@RequestParam("sid") long sid) {
        return ApiResponse.ok(chatService.chats(sid));
    }

}

package com.wjy.hz.controller;

import com.alibaba.fastjson.JSON;
import com.wjy.hz.model.api.ApiResponse;
import com.wjy.hz.model.dto.ChatInputDto;
import com.wjy.hz.service.ChatService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class ChatController {

    @Resource
    ChatService chatService;

    @GetMapping("/api/chat/chat-list")
    public String chatLiSt(@RequestParam("sid") long sid) {
        return ApiResponse.ok(chatService.chats(sid));
    }

    @PostMapping("/api/chat/send-question")
    public String sendChat(@RequestBody ChatInputDto dto) {
        String cleanAnswer =  chatService.sendText(dto.getSid(), dto.getQuestion());
        System.out.println(cleanAnswer);
        return cleanAnswer == null ? ApiResponse.intError() : ApiResponse.ok(cleanAnswer);
    }

}

package com.wjy.hz.service;

import com.wjy.hz.model.dto.ChatDto;

import java.util.List;

public interface ChatService {

    List<ChatDto> chats(long userId);

    String sendText(long userId, String send);

}

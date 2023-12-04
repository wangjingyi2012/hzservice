package com.wjy.hz.service.impl;

import com.wjy.hz.helper.ChatHelper;
import com.wjy.hz.mapper.ChatMapper;
import com.wjy.hz.mapper.StudentMapper;
import com.wjy.hz.model.dto.ChatDto;
import com.wjy.hz.model.entity.ChatEntity;
import com.wjy.hz.model.entity.StudentEntity;
import com.wjy.hz.service.ChatService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {

    @Resource
    ChatMapper chatMapper;

    @Resource
    StudentMapper studentMapper;



    @Override
    public List<ChatDto> chats(long userId) {
        List<ChatEntity> enties = chatMapper.chats(userId);
        List<ChatDto> dtos = new ArrayList<>();
        enties.forEach(entiy -> {
            ChatDto dto = new ChatDto();
            dto.setId(entiy.getId());
            dto.setSid(userId);
            dto.setSend(entiy.getSend());
            dto.setReceive(entiy.getReceive());
            dto.setTime(entiy.getTime());
            dto.setUsername(entiy.getUsername());
            dtos.add(dto);
        });
        return dtos;
    }

    @Override
    public String sendText(long userId, String send) {
        String chatContent = ChatHelper.sendText(send);
        if (chatContent == null) {
            return null;
        }
        ChatEntity chatEntity = new ChatEntity();
        chatEntity.setUser(userId);
        chatEntity.setSend(send);
        chatEntity.setReceive(chatContent);
        chatEntity.setTime(LocalDateTime.now());
        StudentEntity stu = studentMapper.selectById(userId);
        if (stu == null) {
            return null;
        }
        chatEntity.setUsername(stu.getUsername());
        chatMapper.saveChat(chatEntity);
        return chatContent;
    }
}

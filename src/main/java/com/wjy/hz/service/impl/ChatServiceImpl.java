package com.wjy.hz.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wjy.hz.helper.ChatHelper;
import com.wjy.hz.helper.ErnieHelper;
import com.wjy.hz.mapper.ChatMapper;
import com.wjy.hz.mapper.StudentMapper;
import com.wjy.hz.model.dto.ChatDto;
import com.wjy.hz.model.dto.baidu.ChatSend;
import com.wjy.hz.model.entity.ChatEntity;
import com.wjy.hz.model.entity.StudentEntity;
import com.wjy.hz.service.ChatService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
        Collections.reverse(dtos);
        return dtos;
    }

    @Override
    public String sendText(long userId, String send) {

        if (send == null || send.equals("")) {
            return null;
        }

        // 读取上下文
        List<ChatEntity> chatContexts = chatMapper.chatsContext(userId);
        Collections.reverse(chatContexts);
        List<ChatSend> sends = new ArrayList<>();
        chatContexts.forEach(context -> {
            String cleancontext = context.getCleancontext();
            if (cleancontext != null && !cleancontext.equals("")) {
                ChatSend historyMy = new ChatSend();
                historyMy.setRole("user");
                historyMy.setContent(context.getSend());
                sends.add(historyMy);
                ChatSend historySys = new ChatSend();
                historySys.setRole("assistant");
                historySys.setContent(context.getCleancontext());
                sends.add(historySys);
            }
        });

        // 装载最新的对话
        ChatSend current = new ChatSend("user", send);
        sends.add(current);

        String answer =  ErnieHelper.askERNIEBot(sends);
        if (answer == null) {
            return null;
        }
        JSONObject ansJson = JSON.parseObject(answer);
        String cleanAnswer = ansJson.getString("result");

        ChatEntity chatEntity = new ChatEntity();
        chatEntity.setUser(userId);
        chatEntity.setSend(send);
        chatEntity.setReceive(answer);
        chatEntity.setCleancontext(cleanAnswer);
        chatEntity.setTime(LocalDateTime.now());
        chatEntity.setSendall(JSON.toJSONString(sends));
        StudentEntity stu = studentMapper.selectById(userId);
        if (stu == null) {
            return null;
        }
        chatEntity.setUsername(stu.getUsername());
        chatMapper.saveChat(chatEntity);
        return cleanAnswer;
    }
}

package com.wjy.hz.mapper;

import com.wjy.hz.model.entity.ChatEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ChatMapper {

    @Insert("INSERT INTO chats (user, send, receive, time, username) VALUES (#{user}, #{send}, #{receive}, #{time}, #{username})")
    int saveChat(ChatEntity chat);

    @Select("select * from chats where user = #{userId} limit 10")
    List<ChatEntity> chats(long userId);

}

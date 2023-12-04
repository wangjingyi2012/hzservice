package com.wjy.hz.mapper;

import com.wjy.hz.model.entity.ChatEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ChatMapper {

    @Insert("INSERT INTO chats (user, send, receive, time, username, cleancontext, sendall) VALUES (#{user}, #{send}, #{receive}, #{time}, #{username}, #{cleancontext}, #{sendall})")
    int saveChat(ChatEntity chat);

    @Select("select * from chats where user = #{userId} order by id desc limit 10")
    List<ChatEntity> chats(long userId);

    @Select("select * from chats where user = #{userId} order by id desc limit 2")
    List<ChatEntity> chatsContext(long userId);

}

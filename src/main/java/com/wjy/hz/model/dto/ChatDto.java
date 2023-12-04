package com.wjy.hz.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChatDto {

    private long id;

    private String username;

    private long sid;

    private String send;

    private String receive;

    private LocalDateTime time;

}

package com.wjy.hz.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatEntity {

    private long id;

    private long user;

    private String send;

    private String receive;

    private LocalDateTime time;

    private String username;

}

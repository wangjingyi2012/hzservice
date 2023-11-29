package com.wjy.hz.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentEntity {

    private long id;
    
    private String content;

    private LocalDateTime pubtime;

    private long author;

    private String authname;

    private int anymous;

    private String anyname;

    private long blog;

}

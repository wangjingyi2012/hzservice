package com.wjy.hz.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogDto {

    private long id;

    private String title;

    private String content;

    private LocalDateTime pubtime;

    private String username;

    private int commentCount;

    private String avator;

}

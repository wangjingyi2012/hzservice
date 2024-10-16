package com.wjy.hz.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomeworkEntity {

    private long id;

    private String name;

    private String status;

    private int score;

    private LocalDateTime endtime;

    private String content;

}

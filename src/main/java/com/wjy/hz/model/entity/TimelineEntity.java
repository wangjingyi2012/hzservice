package com.wjy.hz.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimelineEntity {

    private long id;

    private String title;

    private String content;

    private LocalDateTime happend;

    private long sid;

    private String action;

}

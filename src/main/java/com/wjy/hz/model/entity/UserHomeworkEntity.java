package com.wjy.hz.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserHomeworkEntity {

    private long id;

    private long hid;

    private long sid;

    private int score;

    private LocalDateTime scoretime;

    private String comment;

    private String status;

    private String tip;

    private LocalDateTime stime;

    private String file;

}

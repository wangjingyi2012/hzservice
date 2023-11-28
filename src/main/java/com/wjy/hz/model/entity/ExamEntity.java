package com.wjy.hz.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamEntity {

    private long id;

    private String name;

    private String profile;

    private LocalDateTime start;

    private LocalDateTime end;

    private long paper;

    private long course;

}

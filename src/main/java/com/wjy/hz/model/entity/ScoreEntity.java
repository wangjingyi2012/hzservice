package com.wjy.hz.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScoreEntity {

    private long id;

    private long sid;

    private String sname;

    // 考勤分
    private int attendance;

    // 素养分
    private int quality;

    // 提问分
    private int inquiry;

    // 文档分
    private int doc;

    // 代码分
    private int code;

    // 项目分
    private int project;

    private int total;

    private long course;

}

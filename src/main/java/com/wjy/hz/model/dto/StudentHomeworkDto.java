package com.wjy.hz.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StudentHomeworkDto {

    private long sid;

    private long hid;

    private String homeworkName;

    private String homeworkContent;

    private LocalDateTime homeworkEndtime;

    // 如果homework的endtime大于当前时间，那么是‘可提交’，否则是‘提交已截止’
    private String homeworkStatus;

    // 已提交、未提交、如果老师的打分时间不为空，那么演示已批阅
    private String submitStatus;

    // 提交作业的附言
    private String myComment;

    // 老师的评语
    private String teacherTip;

    private LocalDateTime submitTime;

    // 作业的分数
    private Integer homeworkScore;

    // 学生得到的分数
    private Integer studentScore;

    // 提交的顺序
    private Integer submitOrder;

}

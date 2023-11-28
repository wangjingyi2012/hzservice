package com.wjy.hz.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserExamAnsweredStemDto {

    // user_answers 表中记录对应的id
    private long id;

    // 题目ID
    private long stemId;

    // 题干
    private String stemContent;

    // 题目类型
    private String type;

    // 用户本题是否答对，正确为1，错误为0
    private int userCorect;

    // 题目的答案列表
    List<UserExamAnsweredStemAnswerDto> answers;

}

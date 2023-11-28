package com.wjy.hz.model.dto;

import lombok.Data;

@Data
public class UserExamAnsweredStemAnswerDto {

    // 对应answers表的id
    private long id;

    // 题目的id
    private long stemId;

    // 答案的内容
    private String answerContent;

    // 是否是这道题目的正确答案
    private int correct;

    // 用户是否选择了这个答案，选择了为1，没有选择为0
    private int userCheck;

}

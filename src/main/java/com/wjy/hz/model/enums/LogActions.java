package com.wjy.hz.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum LogActions {

    Login("登录"),
    Sign("签到"),
    joinExam("参与考核"),
    learnCourse("学习课程"),
    postBBS("泡论坛"),
    classAnswer("课堂回答提问"),
    classDoc("提交文档"),
    postCode("提交代码");

    private final String code;
}

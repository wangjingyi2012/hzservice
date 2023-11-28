package com.wjy.hz.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserExamEntity {

    private long id;

    private long sid;

    private String srealname;

    private long exam;

    private long clazz;

    private int score;

}

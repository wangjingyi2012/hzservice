package com.wjy.hz.model.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentEntity {

    private long id;

    private String username;

    private String password;

    private String number;

    private String realname;

    private long grade;

    private long clazz;

    private String avator;

    private String motto;

    private String profile;

    // 性别
    private String gender;

    private int tutor;

}

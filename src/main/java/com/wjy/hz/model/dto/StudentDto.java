package com.wjy.hz.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {

    private long id;

    private String username;

    private String realname;

    private String number;

    private String gradeName;

    private String clazzName;

    private String avator;

    private String motto;

    private String profile;

    private String gender;
}

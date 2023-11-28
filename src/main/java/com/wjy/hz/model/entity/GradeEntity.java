package com.wjy.hz.model.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GradeEntity {

    private long id;

    private String name;

    private int year;
}

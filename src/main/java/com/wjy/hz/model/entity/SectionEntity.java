package com.wjy.hz.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SectionEntity {

    private long id;

    private String name;

    private int hour;

    private String pdf;

    private long course;

    private String profile;

}

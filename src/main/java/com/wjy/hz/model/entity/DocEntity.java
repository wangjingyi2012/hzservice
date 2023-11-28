package com.wjy.hz.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocEntity {

    private long id;

    private String name;

    private String profile;

    private String file;

    private long course;

}

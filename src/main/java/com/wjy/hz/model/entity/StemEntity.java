package com.wjy.hz.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StemEntity {

    private long id;

    private String content;

    private String type;

    // 是否算分数
    private int op;

}
